package com.zb.service;

import com.rabbitmq.client.Channel;
import com.zb.pojo.XcOrders;
import org.apache.ibatis.annotations.Param;
import org.springframework.amqp.core.Message;

import java.util.Map;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/16
 * @Version V1.0
 */
public interface XcOrdersService {
    public Integer insertXcOrders(XcOrders xcOrders);

    public void recoverOrderMessage(Map<String, Object> param, Message message, Channel channel);

    public Integer updateXcOrders(XcOrders xcOrders);

    public XcOrders findOrderPay(String token);

    public XcOrders getXcOrderByOrderNo(String orderNo);
}
