package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.zb.config.DelayRabbitConfig;
import com.zb.feign.XcUserFeignClient;
import com.zb.mapper.XcOrdersMapper;
import com.zb.mapper.XcTaskMapper;
import com.zb.pojo.XcOrders;
import com.zb.pojo.XcTask;
import com.zb.pojo.XcUser;
import com.zb.service.XcOrdersService;
import com.zb.util.IdWorker;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/16
 * @Version V1.0
 */
@Service
public class XcOrdersServiceImpl implements XcOrdersService {

    @Autowired(required = false)
    private XcOrdersMapper xcOrdersMapper;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private XcUserFeignClient xcUserFeignClient;
    @Autowired(required = false)
    private XcTaskMapper xcTaskMapper;

    @Override
    public Integer insertXcOrders(XcOrders xcOrders) {
        try {
            Integer num = xcOrdersMapper.insertXcOrders(xcOrders);
            //封装发送延迟队列的数据
            Map<String, Object> mqData = new HashMap<>();
            mqData.put("orderNumber", xcOrders.getOrderNumber());
            System.out.println("延迟队列五分钟之后检查订单的状态 ");
            amqpTemplate.convertAndSend(DelayRabbitConfig.ORDER_EXCHANGE_NAME1, DelayRabbitConfig.ORDER_DELAY_ROUTING_KEY1, mqData, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setExpiration(60 * 1000 * 5 + "");
                    return message;
                }
            });
            return num;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @RabbitListener(queues = DelayRabbitConfig.ORDER_DELAY_QUEUE1)
    @Override
    public void recoverOrderMessage(Map<String, Object> param, Message message, Channel channel) {
        try {
            System.out.println("五分钟之后获取到订单的状态信息");
            String orderNumber = param.get("orderNumber").toString();
            System.out.println("orderNumber:" + orderNumber);
            System.out.println("1.修改订单的状态");
            //查询订单的状态是否为0 执行以下操作
            List<XcOrders> xcOrdersListByMap = xcOrdersMapper.getXcOrdersListByMap(param);
            if (xcOrdersListByMap.size() > 0) {
                XcOrders xcOrders = xcOrdersListByMap.get(0);
                if (xcOrders.getStatus() == 0) {
                    XcOrders orders = new XcOrders();
                    orders.setOrderNumber(orderNumber);
                    orders.setStatus(1);
                    Integer num = xcOrdersMapper.updateXcOrders(orders);
                    if (num > 0) {
                        System.out.println("订单已取消......");
                    } else {
                        System.out.println("订单取消失败......");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer updateXcOrders(XcOrders xcOrders) {
        try {
            Integer num = xcOrdersMapper.updateXcOrders(xcOrders);
            //向任务表中添加数据， 最终一致性的程序 CAP理论
            if (num > 0) {
                //根据订单的编号查询 用户和商品的信息
                XcTask xcTask = new XcTask();
                xcTask.setId(IdWorker.getId());
                xcTask.setTaskType(1 + "");
                xcTask.setMqExchange("ex_learning_addchoosecourse");
                xcTask.setMqRoutingkey("addchoosecourse");
                Map<String, Object> requestBody = new HashMap<>();
                requestBody.put("userId", xcOrders.getUserId());
                requestBody.put("courseId", xcOrders.getItemId());
                requestBody.put("charge", xcOrders.getPayType());
                requestBody.put("price", xcOrders.getItemPrice());
                requestBody.put("valid", xcOrders.getValid());
                requestBody.put("startTime", xcOrders.getStartTime());
                requestBody.put("endTime", xcOrders.getEndTime());
                xcTask.setRequestBody(JSON.toJSONString(requestBody));
                xcTask.setVersion(1);
                xcTask.setStatus(1 + "");
                xcTaskMapper.insertXcTask(xcTask);
            }
            return num;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public XcOrders findOrderPay(String token) {
        XcUser currentUser = xcUserFeignClient.getCurrentUser(token);
        return xcOrdersMapper.findOrderPay(currentUser.getId());
    }

    @Override
    public XcOrders getXcOrderByOrderNo(String orderNo) {
        return xcOrdersMapper.getXcOrderByOrderNo(orderNo);
    }
}
