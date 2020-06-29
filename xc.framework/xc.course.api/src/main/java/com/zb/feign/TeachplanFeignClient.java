package com.zb.feign;

import com.zb.dto.Dto;
import com.zb.form.GetTeachplanListByMapForm;
import com.zb.pojo.Teachplan;
import org.springframework.cloud.netflix.feign.FeignClient;
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

    @GetMapping(value = "/getTeachplanFormByCourseId/{courseId}")
    public Dto getTeachplanFormByCourseId(@PathVariable("courseId") String courseId);

    @PostMapping(value = "/getTeachplanListByMap")
    public Dto getTeachplanListByMap(@RequestBody GetTeachplanListByMapForm getTeachplanListByMapForm);

    @GetMapping(value = "/getTeachplanByCourseId/{courseId}")
    public Teachplan getTeachplanByCourseId(@PathVariable("courseId") String courseId);

    @PostMapping(value = "/getTeachplanCountByMap")
    public Integer getTeachplanCountByMap(@RequestParam Map<String, Object> param);

    @PostMapping(value = "/insertTeachplan")
    public Integer insertTeachplan(@RequestBody Teachplan teachplan);

    @PostMapping(value = "/updateTeachplan")
    public Integer updateTeachplan(@RequestBody Teachplan teachplan);

    @DeleteMapping(value = "/delTeachplanByCourseId/{id}")
    public Integer delTeachplanByCourseId(@PathVariable("id") String id);
}
