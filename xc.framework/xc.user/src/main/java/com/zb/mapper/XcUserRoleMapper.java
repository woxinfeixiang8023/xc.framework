package com.zb.mapper;

import com.zb.pojo.XcUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface XcUserRoleMapper {

	public XcUserRole getXcUserRoleById(@Param(value = "id") Long id)throws Exception;

	public List<XcUserRole>	getXcUserRoleListByMap(Map<String, Object> param)throws Exception;

	public Integer getXcUserRoleCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertXcUserRole(XcUserRole xcUserRole)throws Exception;

	public Integer updateXcUserRole(XcUserRole xcUserRole)throws Exception;


}
