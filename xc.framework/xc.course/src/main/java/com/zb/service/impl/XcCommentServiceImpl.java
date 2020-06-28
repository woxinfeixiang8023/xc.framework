package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.feign.XcUserFeignClient;
import com.zb.form.GetXcCommentListByMapForm;
import com.zb.mapper.XcCommentMapper;
import com.zb.pojo.XcComment;
import com.zb.pojo.XcUser;
import com.zb.service.XcCommentService;
import com.zb.util.PageUtil;
import com.zb.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/7
 * @Version V1.0
 */
@Service
public class XcCommentServiceImpl implements XcCommentService {

    @Autowired(required = false)
    private XcCommentMapper xcCommentMapper;
    @Autowired
    private XcUserFeignClient xcUserFeignClient;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public PageUtil<XcComment> getXcCommentListByMap(GetXcCommentListByMapForm getXcCommentListByMapForm) {
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("courseId", getXcCommentListByMapForm.getCourseId());
            param.put("start", (getXcCommentListByMapForm.getIndex() - 1) * getXcCommentListByMapForm.getSize());
            param.put("size", getXcCommentListByMapForm.getSize());
            List<XcComment> data = xcCommentMapper.getXcCommentListByMap(param);
            //循环调用user模块，根据评论的用户id获取用户信息
            for (XcComment xcComment : data) {
                XcUser xcUser = xcUserFeignClient.getXcUserById(xcComment.getUserId());
                xcComment.setUserpic(xcUser.getUserpic());
                xcComment.setName(xcUser.getName());
            }
            Integer count = xcCommentMapper.getXcCommentCountByMap(param);
            PageUtil<XcComment> page = new PageUtil<>();
            page.setData(data);
            page.setIndex(getXcCommentListByMapForm.getIndex());
            page.setCount(count);
            page.setSize(getXcCommentListByMapForm.getSize());
            return page;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer insertXcComment(XcComment xcComment) {
        try {
            return xcCommentMapper.insertXcComment(xcComment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Scheduled(cron = "00 00 00 * * ?")
    @Override
    public void getScheduledScore() {
        List<XcComment> xcCommentList = xcCommentMapper.getXcCommentCourseId();
        for (XcComment xcComment : xcCommentList) {
            XcComment xcCommentScore = xcCommentMapper.getXcCommentScore(xcComment.getCourseId());
            String key = "score:" + xcComment.getCourseId();
            redisUtil.set(key, JSON.toJSONString(xcCommentScore));
        }
    }

    @Override
    public XcComment getXcCommentScore(String courseId) {
        String key = "score:" + courseId;
        XcComment xcComment = null;
        if (redisUtil.hasKey(key)) {
            String json = redisUtil.get(key).toString();
            xcComment = JSON.parseObject(json, XcComment.class);
        } else {
            this.getScheduledScore();
            String json = redisUtil.get(key).toString();
            xcComment = JSON.parseObject(json, XcComment.class);
        }
        return xcComment;
    }

}
