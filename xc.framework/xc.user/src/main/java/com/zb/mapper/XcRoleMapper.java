package com.zb.mapper;

import com.zb.pojo.XcRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface XcRoleMapper {

	public XcRole getXcRoleById(@Param(value = "id") Long id)throws Exception;

	public List<XcRole>	getXcRoleListByMap(Map<String, Object> param)throws Exception;

	public Integer getXcRoleCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertXcRole(XcRole xcRole)throws Exception;

	public Integer updateXcRole(XcRole xcRole)throws Exception;


}
