package com.zb.feign;

import com.zb.dto.Dto;
import com.zb.pojo.TeachplanMedia;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/28
 * @Version V1.0
 */
@FeignClient("courseserver")
public interface TeachplanMediaFeignClient {
    @GetMapping(value = "/api/course/getTeachplanMediaById/{teachplanId}")
    public Dto getTeachplanMediaById(@PathVariable("teachplanId") String teachplanId);

    @PostMapping(value = "/api/course/getTeachplanMediaListByMap")
    public List<TeachplanMedia> getTeachplanMediaListByMap(@RequestParam Map<String, Object> param);

    @PostMapping(value = "/api/course/getTeachplanMediaCountByMap")
    public Integer getTeachplanMediaCountByMap(@RequestParam Map<String, Object> param);

    @PostMapping(value = "/api/course/insertTeachplanMedia")
    public Integer insertTeachplanMedia(@RequestBody TeachplanMedia teachplanMedia);

    @PostMapping(value = "/api/course/updateTeachplanMedia")
    public Integer updateTeachplanMedia(@RequestBody TeachplanMedia teachplanMedia);

    @DeleteMapping(value = "/api/course/delTeachplanMediaById/{teachplanId}")
    public TeachplanMedia delTeachplanMediaById(@PathVariable("teachplanId") String teachplanId);
}
