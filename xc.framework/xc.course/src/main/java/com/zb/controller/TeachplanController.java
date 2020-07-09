package com.zb.controller;

import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.form.GetTeachplanListByMapForm;
import com.zb.form.TeachplanForm;
import com.zb.pojo.Teachplan;
import com.zb.service.TeachplanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/10
 * @Version V1.0
 */
@RestController
@Api(value = "目录查询的接口", tags = {"查询课程目录接口"})
@RequestMapping("/api/course")
public class TeachplanController {

    @Autowired
    private TeachplanService teachplanService;

    @ApiOperation(value = "查询课程详细信息", notes = "根据课程id查询课程详细信息")
    @ApiImplicitParam(name = "courseId", value = "课程id", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/getTeachplanFormByCourseId/{courseId}")
    public Dto getTeachplanFormByCourseId(@PathVariable("courseId") String courseId) {
        return DtoUtil.returnSuccess("getTeachplanFormByCourseId", teachplanService.getTeachplanFormByCourseId(courseId));
    }

    @ApiOperation(value = "查询目录", notes = "根查询课据课程id、目录级别、父级id程目录")
    @ApiImplicitParam(name = "getTeachplanListByMapForm", value = "查询目录实体", required = true, dataType = "GetTeachplanListByMapForm")
    @PostMapping(value = "/getTeachplanListByMap")
    public Dto getTeachplanListByMap(@RequestBody GetTeachplanListByMapForm getTeachplanListByMapForm) {
        return DtoUtil.returnSuccess("getTeachplanListByMap", teachplanService.getTeachplanListByMap(getTeachplanListByMapForm));
    }

    @GetMapping(value = "/getTeachplanByCourseId/{courseId}")
    public Teachplan getTeachplanByCourseId(@PathVariable("courseId") String courseId) {
        return teachplanService.getTeachplanByCourseId(courseId);
    }

    @PostMapping(value = "/getTeachplanCountByMap")
    public Integer getTeachplanCountByMap(@RequestParam Map<String, Object> param) {
        return teachplanService.getTeachplanCountByMap(param);
    }

    @PostMapping(value = "/insertTeachplan")
    public Integer insertTeachplan(@RequestBody Teachplan teachplan) {
        return teachplanService.insertTeachplan(teachplan);
    }

    @PostMapping(value = "/updateTeachplan")
    public Integer updateTeachplan(@RequestBody Teachplan teachplan) {
        return teachplanService.updateTeachplan(teachplan);
    }

    @DeleteMapping(value = "/delTeachplanByCourseId/{id}")
    public Integer delTeachplanByCourseId(@PathVariable("id") String id) {
        return teachplanService.delTeachplanByCourseId(id);
    }
}
