package com.zb.service;

import com.zb.pojo.XcComment;
import com.zb.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface XcCommentService {

    public PageUtil<XcComment> getXcCommentListByMap(String courseId, Integer index, Integer size);

    public Integer insertXcComment(XcComment xcComment);

    public void getScheduledScore();

    public XcComment getXcCommentScore(String courseId);

}
