package com.zb.mq;

import com.rabbitmq.client.Channel;
import com.zb.config.RabbitMQConfig;
import com.zb.pojo.XcTask;
import com.zb.service.XcLearningCourseRecordService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/18
 * @Version V1.0
 */
@Component
public class XcTaskMQ {

    @Autowired(required = false)
    private XcLearningCourseRecordService learningCourseRecordService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RabbitListener(queues = RabbitMQConfig.XC_LEARNING_ADDCHOOSECOURSE)
    public void reviceChooseCourseTask(XcTask xcTask, Message message, Channel channel) {
        System.out.println("选择商品模块接收消息队列中的数据");
        System.out.println(xcTask.getId() + "\t" + xcTask.getRequestBody());
        //调用执行添加
        boolean addChooseGoods = learningCourseRecordService.addChooseCourse(xcTask);
        System.out.println(addChooseGoods);
        //添加成功再次发生消息队列
        if (addChooseGoods) {
            amqpTemplate.convertAndSend(RabbitMQConfig.EX_LEARNING_ADDCHOOSECOURSE, RabbitMQConfig.XC_LEARNING_FINISHADDCHOOSECOURSE_KEY, xcTask);
        }
    }

}
