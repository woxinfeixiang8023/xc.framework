package com.zb.controller;

import com.alibaba.fastjson.JSON;
import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.pojo.CoursePub;
import com.zb.pojo.XcContent;
import com.zb.service.CoursePubService;
import com.zb.util.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/6
 * @Version V1.0
 */
@RestController
@Api(value = "课程查询的接口", tags = {"查询课程信息接口"})
public class CoursePubController {

    @Autowired
    private CoursePubService coursePubService;
    @Autowired(required = false)
    private RestTemplate restTemplate;

    @ApiOperation(value = "查询分类", notes = "根据父级信息查询分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "父级id", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "name", value = "课程名称", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "mt", value = "一级分类", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "st", value = "二级分类", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "grade", value = "课程等级", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "isHot", value = "是否热评（1.热评 2.否）", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "isNew", value = "是否最新（1.最新 2.否）", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "isRec", value = "是否推荐（1.推荐 2.否）", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "isTop", value = "是否是精品TOP榜（1.是 2.否）", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "index", value = "当前页码数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页显示几条数据", required = true, dataType = "int", paramType = "path")
    })
    @GetMapping(value = "/searchCoursePub")
    public PageUtil<CoursePub> searchCoursePub(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String mt,
            @RequestParam(required = false) String st,
            @RequestParam(required = false) String grade,
            @RequestParam(required = false) Integer isHot,
            @RequestParam(required = false) Integer isNew,
            @RequestParam(required = false) Integer isRec,
            @RequestParam(required = false) Integer isTop,
            @RequestParam(value = "index", required = true, defaultValue = "1") Integer index,
            @RequestParam(value = "size", required = true, defaultValue = "5") Integer size) throws Exception {
        return coursePubService.searchCoursePub(id, name, mt, st, grade, isHot, isNew, isRec, isTop, index, size);
    }

    @GetMapping(value = "/getIndexReadList")
    @ApiOperation(value = "首页查询", notes = "根据推荐二级目录教师推荐查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "st", value = "二级目录", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "isRec", value = "是否推荐", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "isRecommend", value = "教师推荐", required = true, dataType = "String", paramType = "path")
    })
    public Dto getIndexReadList(String st, String isRec, String isRecommend) {
        String myurl = "";
        if (st != null && !"".equals(st)) {
            myurl = "http://localhost:9000/readst?st=" + st;
        }
        if (isRec != null && !"".equals(isRec)) {
            myurl = "http://localhost:9000/readisRec?isRec=" + isRec;
        }
        if (isRecommend != null && !"".equals(isRecommend)) {
            myurl = "http://localhost:9000/readisRecommend?isRecommend=" + isRecommend;
        }
        if ("".equals(myurl)) {
            return null;
        }
        String result = restTemplate.getForObject(myurl, String.class);
        List<CoursePub> coursePubList = null;
        coursePubList = JSON.parseArray(result, CoursePub.class);
        System.out.println("远程调用nginx中的接口程序:" + coursePubList);
        return DtoUtil.returnSuccess("coursePubOk", coursePubList);
    }

    /**
     * 点击按钮抢购方法
     *
     * @param courseId
     * @param token
     * @return
     */
    @GetMapping(value = "/qg")
    public String qg(String courseId, String token) {
        return coursePubService.qgCourse(courseId, token);
    }

    /**
     * 页面轮询检查是否抢购成功的方法
     *
     * @param courseId
     * @param token
     * @return
     */
    @GetMapping(value = "/qgWhile")
    public String qgWhile(String courseId, String token) {
        return coursePubService.qgWhile(courseId, token);
    }

}
