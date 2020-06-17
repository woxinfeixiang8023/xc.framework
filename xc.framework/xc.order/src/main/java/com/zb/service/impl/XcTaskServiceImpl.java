package com.zb.service.impl;

import com.zb.mapper.XcTaskMapper;
import com.zb.pojo.XcTask;
import com.zb.service.XcTaskService;
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
    public int updateTaskVersion(XcTask xcTask) {
        try {
            return xcTaskMapper.updateTaskVersion(xcTask);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
