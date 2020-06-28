package com.zb.controller;

import com.zb.pojo.TeachplanMedia;
import com.zb.service.TeachplanMediaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/11
 * @Version V1.0
 */
@RestController
@Api(value = "媒体资源查询的接口", tags = {"查询媒体资源接口"})
public class TeachplanMediaController {

    @Autowired
    private TeachplanMediaService teachplanMediaService;

    @ApiOperation(value = "查询课程媒体资源", notes = "根据课程计划id查询课程媒体资源")
    @ApiImplicitParam(name = "teachplanId", value = "课程计划id", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/getTeachplanMediaById/{teachplanId}")
    public TeachplanMedia getTeachplanMediaById(@PathVariable("teachplanId") String teachplanId) {
        return teachplanMediaService.getTeachplanMediaById(teachplanId);
    }

}
