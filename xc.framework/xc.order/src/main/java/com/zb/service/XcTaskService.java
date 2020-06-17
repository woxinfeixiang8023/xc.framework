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
     * 乐观锁,修改版本号
     *
     * @param xcTask
     * @return
     * @throws Exception
     */
    public int updateTaskVersion(XcTask xcTask) throws Exception;
}
