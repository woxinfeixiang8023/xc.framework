package com.zb.mapper;

import com.zb.pojo.XcLearningCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface XcLearningCourseMapper {

    public XcLearningCourse getXcLearningCourseById(@Param(value = "id") String id) throws Exception;

    public List<XcLearningCourse> getXcLearningCourseListByMap(Map<String, Object> param) throws Exception;

    public Integer getXcLearningCourseCountByMap(Map<String, Object> param) throws Exception;

    public Integer insertXcLearningCourse(XcLearningCourse xcLearningCourse) throws Exception;

    public Integer updateXcLearningCourse(XcLearningCourse xcLearningCourse) throws Exception;


}
