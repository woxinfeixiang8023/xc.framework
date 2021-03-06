package com.zb.service;

import com.zb.pojo.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/3
 * @Version V1.0
 */
public interface CategoryService {
    public List<Category> getCategoryListByMap(String parentId);


    public Category getCategoryById(String id);

    public List<Category> getCategoryListByMap(Map<String, Object> param);

    public Integer getCategoryCountByMap(Map<String, Object> param);

    public Integer insertCategory(Category category);

    public Integer updateCategory(Category category);

    public Integer delCategoryById(String id);


}
