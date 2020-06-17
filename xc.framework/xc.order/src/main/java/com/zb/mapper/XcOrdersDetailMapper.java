package com.zb.mapper;


import com.zb.pojo.XcOrdersDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface XcOrdersDetailMapper {

	public XcOrdersDetail getXcOrdersDetailById(@Param(value = "id") Long id)throws Exception;

	public List<XcOrdersDetail>	getXcOrdersDetailListByMap(Map<String,Object> param)throws Exception;

	public Integer getXcOrdersDetailCountByMap(Map<String,Object> param)throws Exception;

	public Integer insertXcOrdersDetail(XcOrdersDetail xcOrdersDetail)throws Exception;

	public Integer updateXcOrdersDetail(XcOrdersDetail xcOrdersDetail)throws Exception;


}
