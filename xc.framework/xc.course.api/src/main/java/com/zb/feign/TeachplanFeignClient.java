package com.zb.feign;

import com.zb.dto.Dto;
import com.zb.form.GetTeachplanListByMapForm;
import com.zb.pojo.Teachplan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/28
 * @Version V1.0
 */
@FeignClient("courseserver")
public interface TeachplanFeignClient {

    @GetMapping(value = "/api/course/getTeachplanFormByCourseId/{courseId}")
    public Dto getTeachplanFormByCourseId(@PathVariable("courseId") String courseId);

    @PostMapping(value = "/api/course/getTeachplanListByMap")
    public Dto getTeachplanListByMap(@RequestBody GetTeachplanListByMapForm getTeachplanListByMapForm);

    @GetMapping(value = "/api/course/getTeachplanByCourseId/{courseId}")
    public Teachplan getTeachplanByCourseId(@PathVariable("courseId") String courseId);

    @PostMapping(value = "/api/course/getTeachplanCountByMap")
    public Integer getTeachplanCountByMap(@RequestParam Map<String, Object> param);

    @PostMapping(value = "/api/course/insertTeachplan")
    public Integer insertTeachplan(@RequestBody Teachplan teachplan);

    @PostMapping(value = "/api/course/updateTeachplan")
    public Integer updateTeachplan(@RequestBody Teachplan teachplan);

    @DeleteMapping(value = "/api/course/delTeachplanByCourseId/{id}")
    public Integer delTeachplanByCourseId(@PathVariable("id") String id);
}
