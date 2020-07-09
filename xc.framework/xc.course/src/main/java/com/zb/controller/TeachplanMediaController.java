package com.zb.controller;

import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.form.TeachplanForm;
import com.zb.pojo.Teachplan;
import com.zb.pojo.TeachplanMedia;
import com.zb.service.TeachplanMediaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/11
 * @Version V1.0
 */
@RestController
@Api(value = "媒体资源查询的接口", tags = {"查询媒体资源接口"})
@RequestMapping("/api/course")
public class TeachplanMediaController {

    @Autowired
    private TeachplanMediaService teachplanMediaService;

    @ApiOperation(value = "查询课程媒体资源", notes = "根据课程计划id查询课程媒体资源")
    @ApiImplicitParam(name = "teachplanId", value = "课程计划id", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/getTeachplanMediaById/{teachplanId}")
    public Dto getTeachplanMediaById(@PathVariable("teachplanId") String teachplanId) {
        return DtoUtil.returnSuccess("getTeachplanMediaById", teachplanMediaService.getTeachplanMediaById(teachplanId));
    }

    @PostMapping(value = "/getTeachplanMediaListByMap")
    public List<TeachplanMedia> getTeachplanMediaListByMap(@RequestParam Map<String, Object> param) {
        return teachplanMediaService.getTeachplanMediaListByMap(param);
    }

    @PostMapping(value = "/getTeachplanMediaCountByMap")
    public Integer getTeachplanMediaCountByMap(@RequestParam Map<String, Object> param) {
        return teachplanMediaService.getTeachplanMediaCountByMap(param);
    }

    @PostMapping(value = "/insertTeachplanMedia")
    public Integer insertTeachplanMedia(@RequestBody TeachplanMedia teachplanMedia) {
        return teachplanMediaService.insertTeachplanMedia(teachplanMedia);
    }

    @PostMapping(value = "/updateTeachplanMedia")
    public Integer updateTeachplanMedia(@RequestBody TeachplanMedia teachplanMedia) {
        return teachplanMediaService.updateTeachplanMedia(teachplanMedia);
    }

    @DeleteMapping(value = "/delTeachplanMediaById/{teachplanId}")
    public TeachplanMedia delTeachplanMediaById(@PathVariable("teachplanId") String teachplanId) {
        return teachplanMediaService.delTeachplanMediaById(teachplanId);
    }

}
