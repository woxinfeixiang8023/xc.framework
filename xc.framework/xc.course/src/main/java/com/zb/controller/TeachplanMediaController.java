package com.zb.controller;

import com.zb.pojo.TeachplanMedia;
import com.zb.service.TeachplanMediaService;
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
public class TeachplanMediaController {

    @Autowired
    private TeachplanMediaService teachplanMediaService;

    @GetMapping(value = "/getTeachplanMediaById/{teachplanId}")
    public TeachplanMedia getTeachplanMediaById(@PathVariable("teachplanId") String teachplanId) {
        return teachplanMediaService.getTeachplanMediaById(teachplanId);
    }

}
