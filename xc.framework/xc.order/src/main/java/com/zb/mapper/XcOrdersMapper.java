package com.zb.mapper;


import com.zb.pojo.XcOrders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface XcOrdersMapper {

    public XcOrders getXcOrdersById(@Param(value = "id") Long id) throws Exception;

    public List<XcOrders> getXcOrdersListByMap(Map<String, Object> param) throws Exception;

    public Integer getXcOrdersCountByMap(Map<String, Object> param) throws Exception;

    public Integer insertXcOrders(XcOrders xcOrders) throws Exception;

    public Integer updateXcOrders(XcOrders xcOrders) throws Exception;

    public XcOrders findOrderPay(@Param(value = "userId") String userId);

    public XcOrders getXcOrderByOrderNo(@Param(value = "orderNo") String orderNo);


}
