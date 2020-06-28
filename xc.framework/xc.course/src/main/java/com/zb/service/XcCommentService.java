package com.zb.service;

import com.zb.form.GetXcCommentListByMapForm;
import com.zb.pojo.XcComment;
import com.zb.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface XcCommentService {

    public PageUtil<XcComment> getXcCommentListByMap(GetXcCommentListByMapForm getXcCommentListByMapForm);

    public Integer insertXcComment(XcComment xcComment);

    public void getScheduledScore();

    public XcComment getXcCommentScore(String courseId);

}
