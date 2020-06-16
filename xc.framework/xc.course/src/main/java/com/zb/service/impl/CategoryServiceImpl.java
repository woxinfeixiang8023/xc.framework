package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.mapper.CategoryMapper;
import com.zb.pojo.Category;
import com.zb.service.CategoryService;
import com.zb.util.RedisUtil;
import com.zb.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/3
 * @Version V1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired(required = false)
    private CategoryMapper categoryMapper;
    @Autowired
    private RedisUtil redisUtil;


    //value = "cache" cache数据来自于ehcache.xml文件中<cache name="cache"> name的数据
    @Cacheable(value = "cache", key = "'parentid:'+#parentId")
    @Override
    public List<Category> getCategoryListByMap(String parentId) {
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("parentid", parentId);
            String key = "category:" + parentId;
            List<Category> categoryList = null;
            if (redisUtil.hasKey(key)) {
                System.out.println("查询redis");
                String json = redisUtil.get(key).toString();
                categoryList = JSON.parseArray(json, Category.class);
            } else {
                System.out.println("查询数据库");
                categoryList = categoryMapper.getCategoryListByMap(param);
                redisUtil.set(key, JSON.toJSONString(categoryList));
            }
            return categoryList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
