package com.zb.mapper;

import com.zb.pojo.XcComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface XcCommentMapper {

    public XcComment getXcCommentById(@Param(value = "id") Long id) throws Exception;

    public List<XcComment> getXcCommentListByMap(Map<String, Object> param) throws Exception;

    public Integer getXcCommentCountByMap(Map<String, Object> param) throws Exception;

    public Integer insertXcComment(XcComment xcComment) throws Exception;

    public Integer updateXcComment(XcComment xcComment) throws Exception;

    public List<XcComment> getXcCommentScore();


}
