package com.zb.mapper;

import com.zb.pojo.XcCourseTempStore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface XcCourseTempStoreMapper {

	public XcCourseTempStore getXcCourseTempStoreById(@Param(value = "id") Long id)throws Exception;

	public List<XcCourseTempStore>	getXcCourseTempStoreListByMap(Map<String,Object> param)throws Exception;

	public Integer getXcCourseTempStoreCountByMap(Map<String,Object> param)throws Exception;

	public Integer insertXcCourseTempStore(XcCourseTempStore xcCourseTempStore)throws Exception;

	public Integer updateXcCourseTempStore(XcCourseTempStore xcCourseTempStore)throws Exception;


}
