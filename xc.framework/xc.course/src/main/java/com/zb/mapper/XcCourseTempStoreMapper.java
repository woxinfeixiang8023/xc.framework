package com.zb.mapper;

import com.zb.pojo.XcCourseTempStore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface XcCourseTempStoreMapper {

    public XcCourseTempStore getXcCourseTempStoreById(@Param(value = "id") Long id) throws Exception;

    public List<XcCourseTempStore> getXcCourseTempStoreListByMap(Map<String, Object> param) throws Exception;

    public Integer getXcCourseTempStoreCountByMap(Map<String, Object> param) throws Exception;

    public Integer insertXcCourseTempStore(XcCourseTempStore xcCourseTempStore) throws Exception;

    public Integer updateXcCourseTempStore(Map<String, Object> param) throws Exception;

    /**
     * 查询临时库存表的用户是否是第二次下单
     * 查询临时库存的课程状态
     *
     * @param param
     * @return
     */
    public XcCourseTempStore findCourseTempStoreStatus(Map<String, Object> param);
}
