package com.zb.mapper;

import com.zb.pojo.XcMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface XcMenuMapper {

	public XcMenu getXcMenuById(@Param(value = "id") Long id)throws Exception;

	public List<XcMenu>	getXcMenuListByMap(Map<String, Object> param)throws Exception;

	public Integer getXcMenuCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertXcMenu(XcMenu xcMenu)throws Exception;

	public Integer updateXcMenu(XcMenu xcMenu)throws Exception;


}
