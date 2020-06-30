package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.feign.CoursePubFeignClient;
import com.zb.feign.XcUserFeignClient;
import com.zb.mapper.XcLearningCourseMapper;
import com.zb.mapper.XcTaskHisMapper;
import com.zb.pojo.*;
import com.zb.service.XcLearningCourseRecordService;
import com.zb.util.IdWorker;
import com.zb.util.PageUtil;
import com.zb.util.RedisUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/18
 * @Version V1.0
 */
@Service
public class XcLearningCourseRecordServiceImpl implements XcLearningCourseRecordService {

    @Autowired(required = false)
    private XcLearningCourseMapper xcLearningCourseMapper;
    @Autowired(required = false)
    private XcTaskHisMapper xcTaskHisMapper;
    @Autowired
    private XcUserFeignClient xcUserFeignClient;
    @Autowired
    private CoursePubFeignClient coursePubFeignClient;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public boolean addChooseCourse(XcTask xcTask) {
        try {
            //根据商品用户编号和商品编号查询添加的数据记录是否存在
            Map<String, Object> param = new HashMap<>();
            //订单微服模块发的数据
            XcLearningCourse xlc = JSON.parseObject(xcTask.getRequestBody(), XcLearningCourse.class);
            param.put("userId", xlc.getUserId());
            param.put("courseId", xlc.getCourseId());
            XcLearningCourse xcLearningCourse = this.getXcLearningCourseRecordByUserAndCourse(param);
            //之前没有购买记录执行添加
            if (xcLearningCourse == null) {
                xcLearningCourse = new XcLearningCourse();
                xcLearningCourse.setId(IdWorker.getId());
                xcLearningCourse.setCourseId(xlc.getCourseId());
                xcLearningCourse.setUserId(xlc.getUserId());
                xcLearningCourse.setCharge(xlc.getCharge());
                xcLearningCourse.setPrice(xlc.getPrice());
                xcLearningCourse.setValid(xlc.getValid());
                xcLearningCourse.setStartTime(xlc.getStartTime());
                xcLearningCourse.setEndTime(xlc.getEndTime());
                xcLearningCourse.setStatus(1 + "");
                xcLearningCourseMapper.insertXcLearningCourse(xcLearningCourse);
            } else {
                xcLearningCourse = new XcLearningCourse();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
                xcLearningCourse.setStartTime(sdf.format(new Date()));
                xcLearningCourse.setEndTime(sdf.format(new Date()));
                xcLearningCourse.setStatus(1 + "");
                xcLearningCourseMapper.updateXcLearningCourse(xcLearningCourse);
            }
            //将任务写入到xc_learning库的xc_task_his表中， 同样适用幂等 ,如果不存在执行， 添加， 存在执行修改！
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
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public XcLearningCourse getXcLearningCourseRecordByUserAndCourse(Map<String, Object> param) {
        try {
            List<XcLearningCourse> xcLearningCourseListByMap = xcLearningCourseMapper.getXcLearningCourseListByMap(param);
            if (xcLearningCourseListByMap.size() > 0) {
                return xcLearningCourseListByMap.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*@Override
    public PageUtil<CoursePub> getXcLearningCourseListByMap(Integer index, Integer size, String token) {
        try {
            XcUser currentUser = xcUserFeignClient.getCurrentUser(token);
            Map<String, Object> param = new HashMap<>();
            param.put("userId", currentUser.getId());
            param.put("start", (index - 1) * size);
            param.put("size", size);
            List<XcLearningCourse> xcLearningCourseList = xcLearningCourseMapper.getXcLearningCourseListByMap(param);
            Integer count = xcLearningCourseMapper.getXcLearningCourseCountByMap(param);
            List<CoursePub> data = new ArrayList<>();
            for (XcLearningCourse xcLearningCourse : xcLearningCourseList) {
                CoursePub coursePubById = coursePubFeignClient.getCoursePubById(xcLearningCourse.getCourseId());
                data.add(coursePubById);
            }
            PageUtil<CoursePub> page = new PageUtil<>();
            page.setIndex(index);
            page.setSize(size);
            page.setData(data);
            page.setCount(count);
            return page;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer insertXcLearningCourse(XcLearningCourse xcLearningCourse) {
        try {
            return xcLearningCourseMapper.insertXcLearningCourse(xcLearningCourse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer updateXcLearningCourse(XcLearningCourse xcLearningCourse) {
        try {
            return xcLearningCourseMapper.updateXcLearningCourse(xcLearningCourse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delXcLearningCourseById(String id) {
        try {
            return xcLearningCourseMapper.delXcLearningCourseById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/
}
