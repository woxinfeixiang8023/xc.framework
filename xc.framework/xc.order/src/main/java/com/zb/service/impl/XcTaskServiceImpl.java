package com.zb.service.impl;

import com.zb.mapper.XcTaskHisMapper;
import com.zb.mapper.XcTaskMapper;
import com.zb.pojo.XcTask;
import com.zb.pojo.XcTaskHis;
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
    @Autowired(required = false)
    private XcTaskHisMapper xcTaskHisMapper;
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
    public int getTask(String id, Integer version) {
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

    @Override
    public void finishTask(XcTask xcTask) {
        try {
            //向任务历史表中添加一条数据
            XcTaskHis xcTaskHisById = xcTaskHisMapper.getXcTaskHisById(xcTask.getId());
            XcTaskHis xcTaskHis = new XcTaskHis();
            if (xcTaskHisById == null) {
                xcTaskHis.setId(xcTask.getId());
                xcTaskHis.setCreateTime(xcTask.getCreateTime());
                xcTaskHis.setUpdateTime(xcTask.getUpdateTime());
                xcTaskHis.setDeleteTime(xcTask.getDeleteTime());
                xcTaskHis.setTaskType(xcTask.getTaskType());
                xcTaskHis.setMqExchange(xcTask.getMqExchange());
                xcTaskHis.setMqRoutingkey(xcTask.getMqRoutingkey());
                xcTaskHis.setRequestBody(xcTask.getRequestBody());
                xcTaskHis.setVersion(xcTask.getVersion());
                xcTaskHis.setStatus(xcTask.getStatus());
                xcTaskHis.setErrormsg(xcTask.getErrormsg());
                xcTaskHisMapper.insertXcTaskHis(xcTaskHis);
            } else {
                xcTaskHis.setUpdateTime(xcTask.getUpdateTime());
                xcTaskHis.setVersion(1);
                xcTaskHisMapper.updateXcTaskHis(xcTaskHis);
            }
            //删除任务数据,表述本次流程以结束
            xcTaskMapper.deleteTask(xcTask.getId());
            System.out.println("本次流程执行完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
