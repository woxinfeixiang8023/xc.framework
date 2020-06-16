package com.zb.mapper;

import com.zb.pojo.CoursePic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CoursePicMapper {

	public CoursePic getCoursePicById(@Param(value = "id") String id)throws Exception;

	public List<CoursePic>	getCoursePicListByMap(Map<String,Object> param)throws Exception;

	public Integer getCoursePicCountByMap(Map<String,Object> param)throws Exception;

	public Integer insertCoursePic(CoursePic coursePic)throws Exception;

	public Integer updateCoursePic(CoursePic coursePic)throws Exception;


}
