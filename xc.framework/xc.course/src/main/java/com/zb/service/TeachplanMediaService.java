package com.zb.service;

import com.zb.pojo.TeachplanMedia;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/11
 * @Version V1.0
 */
public interface TeachplanMediaService {
    public TeachplanMedia getTeachplanMediaById(String teachplanId);

    public List<TeachplanMedia> getTeachplanMediaListByMap(Map<String, Object> param);

    public Integer getTeachplanMediaCountByMap(Map<String, Object> param);

    public Integer insertTeachplanMedia(TeachplanMedia teachplanMedia);

    public Integer updateTeachplanMedia(TeachplanMedia teachplanMedia);

    public TeachplanMedia delTeachplanMediaById(String teachplanId);
}
