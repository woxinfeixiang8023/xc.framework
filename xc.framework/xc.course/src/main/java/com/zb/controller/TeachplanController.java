package com.zb.controller;

import com.zb.form.TeachplanForm;
import com.zb.pojo.Teachplan;
import com.zb.service.TeachplanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/10
 * @Version V1.0
 */
@RestController
public class TeachplanController {

    @Autowired
    private TeachplanService teachplanService;

    @GetMapping(value = "/getTeachplanFormByCourseId/{courseId}")
    public TeachplanForm getTeachplanFormByCourseId(@PathVariable("courseId") String courseId) {
        return teachplanService.getTeachplanFormByCourseId(courseId);
    }

    @GetMapping(value = "/getTeachplanListByMap")
    public List<Teachplan> getTeachplanListByMap(String courseId, String grade, String parentId) {
        return teachplanService.getTeachplanListByMap(courseId, grade, parentId);
    }

}
