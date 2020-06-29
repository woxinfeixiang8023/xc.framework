package com.zb.mapper;

import com.zb.pojo.Teachplan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TeachplanMapper {

    public Teachplan getTeachplanByCourseId(@Param(value = "courseId") String courseId) throws Exception;

    public List<Teachplan> getTeachplanListByMap(Map<String, Object> param) throws Exception;

    public Integer getTeachplanCountByMap(Map<String, Object> param) throws Exception;

    public Integer insertTeachplan(Teachplan teachplan) throws Exception;

    public Integer updateTeachplan(Teachplan teachplan) throws Exception;

    public Integer delTeachplanByCourseId(@Param(value = "id") String id) throws Exception;


}
