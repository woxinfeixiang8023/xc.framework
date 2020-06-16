package com.zb.controller;

import com.zb.pojo.XcComment;
import com.zb.service.XcCommentService;
import com.zb.util.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/7
 * @Version V1.0
 */
@RestController
@Api(value = "评论查询的接口", tags = {"查询课程评论信息接口"})
public class XcCommentController {

    @Autowired
    private XcCommentService xcCommentService;

    @ApiOperation(value = "查询所有评论", notes = "根据课程id查询评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "课程id", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "index", value = "当前页码数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页显示几条数据", required = true, dataType = "int", paramType = "path")
    })
    @GetMapping(value = "/getXcCommentListByMap")
    public PageUtil<XcComment> getXcCommentListByMap(String courseId, Integer index, Integer size) {
        return xcCommentService.getXcCommentListByMap(courseId, index, size);
    }

    @ApiOperation(value = "添加评论", notes = "添加评论及用户id")
    @ApiImplicitParam(name = "xcComment", value = "评论实体", required = true, dataType = "XcComment")
    @PostMapping(value = "/insertXcComment")
    public Integer insertXcComment(XcComment xcComment) {
        return xcCommentService.insertXcComment(xcComment);
    }

    @ApiOperation(value = "查询评论分数", notes = "根据课程id查询评论分数")
    @ApiImplicitParam(name = "courseId", value = "课程id", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/getXcCommentScore")
    public XcComment getXcCommentScore(String courseId) {
        return xcCommentService.getXcCommentScore(courseId);
    }


}
