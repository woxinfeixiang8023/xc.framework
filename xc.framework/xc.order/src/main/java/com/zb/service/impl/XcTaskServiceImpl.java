package com.zb.service.impl;

import com.zb.mapper.XcTaskMapper;
import com.zb.pojo.XcTask;
import com.zb.service.XcTaskService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/17
 * @Version V1.0
 */
@Service
public class XcTaskServiceImpl implements XcTaskService {

    @Autowired(required = false)
    private XcTaskMapper xcTaskMapper;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<XcTask> findByUpdateTimeBefore() {
        try {
            return xcTaskMapper.findByUpdateTimeBefore();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateTaskVersion(String id, Integer version) {
        try {
            XcTask xcTask = new XcTask();
            xcTask.setId(id);
            xcTask.setVersion(version);
            //修改成功获取到锁, 返回1 否则反之
            return xcTaskMapper.updateTaskVersion(xcTask);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void publishTask(XcTask xcTask) {
        try {
            //查询要要发送的任务
            XcTask execTask = xcTaskMapper.getXcTaskById(xcTask.getId());
            if (execTask != null) {
                //发送任务-选择商品的微服务模块
                amqpTemplate.convertAndSend(execTask.getMqExchange(), execTask.getMqRoutingkey(), execTask);
                //修改时间
                xcTaskMapper.updateXcTask(xcTask);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
