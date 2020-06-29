package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.mapper.TeachplanMediaMapper;
import com.zb.pojo.Teachplan;
import com.zb.pojo.TeachplanMedia;
import com.zb.service.TeachplanMediaService;
import com.zb.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/11
 * @Version V1.0
 */
@Service
public class TeachplanMediaServiceImpl implements TeachplanMediaService {

    @Autowired(required = false)
    private TeachplanMediaMapper teachplanMediaMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public TeachplanMedia getTeachplanMediaById(String teachplanId) {
        try {
            String key = "teachplanMedia:" + teachplanId;
            TeachplanMedia teachplanMedia = null;
            if (redisUtil.hasKey(key)) {
                String json = redisUtil.get(key).toString();
                teachplanMedia = JSON.parseObject(json, TeachplanMedia.class);
            } else {
                teachplanMedia = teachplanMediaMapper.getTeachplanMediaByTeachplanId(teachplanId);
                redisUtil.set(key, JSON.toJSONString(teachplanMedia));
            }
            return teachplanMedia;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TeachplanMedia> getTeachplanMediaListByMap(Map<String, Object> param) {
        try {
            return teachplanMediaMapper.getTeachplanMediaListByMap(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getTeachplanMediaCountByMap(Map<String, Object> param) {
        try {
            return teachplanMediaMapper.getTeachplanMediaCountByMap(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer insertTeachplanMedia(TeachplanMedia teachplanMedia) {
        try {
            return teachplanMediaMapper.insertTeachplanMedia(teachplanMedia);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer updateTeachplanMedia(TeachplanMedia teachplanMedia) {
        try {
            return teachplanMediaMapper.updateTeachplanMedia(teachplanMedia);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TeachplanMedia delTeachplanMediaById(String teachplanId) {
        try {
            return teachplanMediaMapper.delTeachplanMediaById(teachplanId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
