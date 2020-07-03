package com.zb.controller;

import com.alibaba.fastjson.JSON;
import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.form.GetIndexReadListForm;
import com.zb.form.SearchCoursePubFrom;
import com.zb.pojo.CoursePub;
import com.zb.pojo.XcContent;
import com.zb.service.CoursePubService;
import com.zb.util.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

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

    @ApiOperation(value = "查询分类", notes = "根据父级信息查询分类（es）")
    @ApiImplicitParam(name = "searchCoursePub", value = "分类查询实体", required = true, dataType = "SearchCoursePubFrom")
    @PostMapping(value = "/searchCoursePub")
    public Dto searchCoursePub(@RequestBody SearchCoursePubFrom searchCoursePubFrom) throws Exception {
        return DtoUtil.returnSuccess("searchCoursePub", coursePubService.searchCoursePub(searchCoursePubFrom));
    }

    @PostMapping(value = "/getIndexReadList")
    @ApiOperation(value = "首页查询", notes = "根据推荐、二级目录、教师推荐查询")
    @ApiImplicitParam(name = "getIndexReadList", value = "首页查询实体", required = true, dataType = "GetIndexReadListForm")
    public Dto getIndexReadList(@RequestBody GetIndexReadListForm getIndexReadListForm) {
        String myurl = "";
        if (getIndexReadListForm.getSt() != null && !"".equals(getIndexReadListForm.getSt())) {
            myurl = "http://localhost:9000/readst?st=" + getIndexReadListForm.getSt();
        }
        if (getIndexReadListForm.getIsRec() != null && !"".equals(getIndexReadListForm.getIsRec())) {
            myurl = "http://localhost:9000/readisRec?isRec=" + getIndexReadListForm.getIsRec();
        }
        if (getIndexReadListForm.getIsRecommend() != null && !"".equals(getIndexReadListForm.getIsRecommend())) {
            myurl = "http://localhost:9000/readisRecommend?isRecommend=" + getIndexReadListForm.getIsRecommend();
        }
        if ("".equals(myurl)) {
            return null;
        }
        String result = restTemplate.getForObject(myurl, String.class);
        List<CoursePub> coursePubList = null;
        coursePubList = JSON.parseArray(result, CoursePub.class);
        System.out.println("远程调用nginx中的接口程序:" + coursePubList);
        return DtoUtil.returnSuccess("getIndexReadList", coursePubList);
    }

    /**
     * 点击按钮抢购方法
     *
     * @param courseId
     * @param token
     * @return
     */
    @ApiOperation(value = "抢购", notes = "课程id和用户token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "课程id", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "token", value = "用户token", required = true, dataType = "String", paramType = "path"),
    })
    @PostMapping(value = "/qg/{courseId}/{token}")
    public Dto qg(@PathVariable("courseId") String courseId, @PathVariable("token") String token) {
        return DtoUtil.returnSuccess("qg", coursePubService.qgCourse(courseId, token));
    }

    /**
     * 页面轮询检查是否抢购成功的方法
     *
     * @param courseId
     * @param token
     * @return
     */
    @PostMapping(value = "/qgWhile/{courseId}/{token}")
    public Dto qgWhile(@PathVariable("courseId") String courseId, @PathVariable("token") String token) {
        return DtoUtil.returnSuccess("qgWhile", coursePubService.qgWhile(courseId, token));
    }

    @GetMapping(value = "/getCoursePubById/{id}")
    public CoursePub getCoursePubById(@PathVariable("id") String id) {
        return coursePubService.getCoursePubById(id);
    }

    @PostMapping(value = "/getCoursePubListByMap")
    public List<CoursePub> getCoursePubListByMap(@RequestParam Map<String, Object> param) {
        return coursePubService.getCoursePubListByMap(param);
    }

    @PostMapping(value = "/getCoursePubCountByMap")
    public Integer getCoursePubCountByMap(@RequestParam Map<String, Object> param) {
        return coursePubService.getCoursePubCountByMap(param);
    }

    @PostMapping(value = "/insertCoursePub")
    public Integer insertCoursePub(@RequestBody CoursePub coursePub) {
        return coursePubService.insertCoursePub(coursePub);
    }

    @PostMapping(value = "/updateCoursePub")
    public Integer updateCoursePub(@RequestBody CoursePub coursePub) {
        return coursePubService.updateCoursePub(coursePub);
    }

    @DeleteMapping(value = "/delCoursePubById/{id}")
    public Integer delCoursePubById(@PathVariable("id") String id) {
        return coursePubService.delCoursePubById(id);
    }

}
