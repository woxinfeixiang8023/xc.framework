package com.zb.service;

import com.zb.pojo.XcTask;

import java.util.List;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/17
 * @Version V1.0
 */
public interface XcTaskService {
    /**
     * 查询一分钟之前的数据
     *
     * @return
     */
    public List<XcTask> findByUpdateTimeBefore();

    /**
     * 获取锁
     *
     * @param id
     * @param version
     */
    public int updateTaskVersion(String id, Integer version);

    /**
     * 根据传递的任务执行mq的发送
     *
     * @param xcTask
     */
    public void publishTask(XcTask xcTask);
}
