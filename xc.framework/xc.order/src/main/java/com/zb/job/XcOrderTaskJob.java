package com.zb.job;

import com.rabbitmq.client.Channel;
import com.zb.config.RabbitMQConfig;
import com.zb.pojo.XcTask;
import com.zb.service.XcTaskService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/17
 * @Version V1.0
 */
@Component
public class XcOrderTaskJob {

    @Autowired
    private XcTaskService xcTaskService;

    @Scheduled(cron = "0/3 * * * * *")
    public void send() {
        //查询一分钟之前的数据
        List<XcTask> byUpdateTimeBefore = xcTaskService.findByUpdateTimeBefore();
        //遍历获取每一个任务
        for (XcTask xcTask : byUpdateTimeBefore) {
            System.out.println(xcTask.getId() + "\t" + xcTask.getRequestBody());
            //当前线程获取锁的信息,防止多线程下， 同一个任务多次执行
            int num = xcTaskService.getTask(xcTask.getId(), xcTask.getVersion());
            if (num > 0) {
                //调用定时发送操作
                xcTaskService.publishTask(xcTask);
            }
        }
    }

    @RabbitListener(queues = RabbitMQConfig.XC_LEARNING_FINISHADDCHOOSECOURSE)
    public void reviceTaskSuccess(XcTask xcTask, Message message, Channel channel) {
        //删除任务同时进行历史存储
        xcTaskService.finishTask(xcTask);
    }

}
