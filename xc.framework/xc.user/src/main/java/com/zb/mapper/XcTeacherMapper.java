package com.zb.mapper;

import com.zb.pojo.XcTeacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface XcTeacherMapper {

	public XcTeacher getXcTeacherById(@Param(value = "id") String id)throws Exception;

	public List<XcTeacher>	getXcTeacherListByMap(Map<String, Object> param)throws Exception;

	public Integer getXcTeacherCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertXcTeacher(XcTeacher xcTeacher)throws Exception;

	public Integer updateXcTeacher(XcTeacher xcTeacher)throws Exception;

	public Integer deleteXcTeacherById(@Param(value = "id") String id)throws Exception;


}
