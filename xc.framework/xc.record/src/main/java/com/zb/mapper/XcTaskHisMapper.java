package com.zb.mapper;

import com.zb.pojo.XcTaskHis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface XcTaskHisMapper {

	public XcTaskHis getXcTaskHisById(@Param(value = "id") String id)throws Exception;

	public List<XcTaskHis>	getXcTaskHisListByMap(Map<String,Object> param)throws Exception;

	public Integer getXcTaskHisCountByMap(Map<String,Object> param)throws Exception;

	public Integer insertXcTaskHis(XcTaskHis xcTaskHis)throws Exception;

	public Integer updateXcTaskHis(XcTaskHis xcTaskHis)throws Exception;


}
