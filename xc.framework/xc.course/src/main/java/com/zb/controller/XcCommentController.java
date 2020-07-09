package com.zb.controller;

import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.form.GetXcCommentListByMapForm;
import com.zb.pojo.XcComment;
import com.zb.service.XcCommentService;
import com.zb.util.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/7
 * @Version V1.0
 */
@RestController
@Api(value = "评论查询的接口", tags = {"查询课程评论信息接口"})
@RequestMapping("/api/course")
public class XcCommentController {

    @Autowired
    private XcCommentService xcCommentService;

    @ApiOperation(value = "查询所有评论", notes = "根据课程id分页组合查询评论")
    @ApiImplicitParam(name = "getXcCommentListByMapForm", value = "查询评论实体", required = true, dataType = "GetXcCommentListByMapForm")
    @PostMapping(value = "/getXcCommentListByMap")
    public Dto getXcCommentListByMap(@RequestBody GetXcCommentListByMapForm getXcCommentListByMapForm) {
        PageUtil<XcComment> xcCommentListByMap = xcCommentService.getXcCommentListByMap(getXcCommentListByMapForm);
        return DtoUtil.returnSuccess("getXcCommentListByMap", xcCommentListByMap);
    }

    @ApiOperation(value = "添加评论", notes = "添加评论")
    @ApiImplicitParam(name = "xcComment", value = "评论实体", required = true, dataType = "XcComment")
    @PostMapping(value = "/insertXcComment")
    public Dto insertXcComment(@RequestBody XcComment xcComment) {
        return DtoUtil.returnSuccess("insertXcComment", xcCommentService.insertXcComment(xcComment));
    }

    @ApiOperation(value = "查询评论分数", notes = "根据课程id查询评论分数")
    @ApiImplicitParam(name = "courseId", value = "课程id", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/getXcCommentScore/{courseId}")
    public Dto getXcCommentScore(@PathVariable("courseId") String courseId) {
        return DtoUtil.returnSuccess("getXcCommentScore", xcCommentService.getXcCommentScore(courseId));
    }

    @GetMapping(value = "/getXcCommentById/{id}")
    public XcComment getXcCommentById(@PathVariable("id") Long id) {
        return xcCommentService.getXcCommentById(id);
    }

    @PostMapping(value = "/updateXcComment")
    public Integer updateXcComment(@RequestBody XcComment xcComment) {
        return xcCommentService.updateXcComment(xcComment);
    }

    @DeleteMapping(value = "/delXcCommentById/{id}")
    public Integer delXcCommentById(@PathVariable("id") Long id) {
        return xcCommentService.delXcCommentById(id);
    }

}
