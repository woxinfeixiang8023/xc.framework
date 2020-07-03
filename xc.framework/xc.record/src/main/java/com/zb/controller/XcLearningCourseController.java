package com.zb.controller;

import com.zb.pojo.CoursePub;
import com.zb.pojo.XcLearningCourse;
import com.zb.service.XcLearningCourseRecordService;
import com.zb.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/30
 * @Version V1.0
 */
@RestController
public class XcLearningCourseController {

    @Autowired
    private XcLearningCourseRecordService xcLearningCourseRecordService;

    @PostMapping(value = "/getXcLearningCourseListByMap/{index}/{size}/{token}")
    public PageUtil<CoursePub> getXcLearningCourseListByMap(@PathVariable("index") Integer index, @PathVariable("size") Integer size, @PathVariable("token") String token) {
        return xcLearningCourseRecordService.getXcLearningCourseListByMap(index, size, token);
    }

    @PostMapping(value = "/insertXcLearningCourse")
    public Integer insertXcLearningCourse(@RequestBody XcLearningCourse xcLearningCourse) {
        return xcLearningCourseRecordService.insertXcLearningCourse(xcLearningCourse);
    }

    @PostMapping(value = "/updateXcLearningCourse")
    public Integer updateXcLearningCourse(@RequestBody XcLearningCourse xcLearningCourse) {
        return xcLearningCourseRecordService.updateXcLearningCourse(xcLearningCourse);
    }

    @DeleteMapping(value = "/delXcLearningCourseById/{id}")
    public Integer delXcLearningCourseById(@PathVariable("id") String id) {
        return xcLearningCourseRecordService.delXcLearningCourseById(id);
    }

}
