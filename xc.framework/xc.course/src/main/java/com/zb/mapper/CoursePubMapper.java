package com.zb.mapper;

import com.zb.pojo.CoursePub;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CoursePubMapper {

    public CoursePub getCoursePubById(@Param(value = "id") String id) throws Exception;

    public List<CoursePub> getCoursePubListByMap(Map<String, Object> param) throws Exception;

    public Integer getCoursePubCountByMap(Map<String, Object> param) throws Exception;

    public Integer insertCoursePub(CoursePub coursePub) throws Exception;

    public Integer updateCoursePub(CoursePub coursePub) throws Exception;

    public List<CoursePub> importData();
}
