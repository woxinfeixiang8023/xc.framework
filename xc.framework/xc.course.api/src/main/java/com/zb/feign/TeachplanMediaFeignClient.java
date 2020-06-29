package com.zb.feign;

import com.zb.dto.Dto;
import com.zb.pojo.TeachplanMedia;
import org.springframework.cloud.netflix.feign.FeignClient;
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
    @GetMapping(value = "/getTeachplanMediaById/{teachplanId}")
    public Dto getTeachplanMediaById(@PathVariable("teachplanId") String teachplanId);

    @PostMapping(value = "/getTeachplanMediaListByMap")
    public List<TeachplanMedia> getTeachplanMediaListByMap(@RequestParam Map<String, Object> param);

    @PostMapping(value = "/getTeachplanMediaCountByMap")
    public Integer getTeachplanMediaCountByMap(@RequestParam Map<String, Object> param);

    @PostMapping(value = "/insertTeachplanMedia")
    public Integer insertTeachplanMedia(@RequestBody TeachplanMedia teachplanMedia);

    @PostMapping(value = "/updateTeachplanMedia")
    public Integer updateTeachplanMedia(@RequestBody TeachplanMedia teachplanMedia);

    @DeleteMapping(value = "/delTeachplanMediaById/{teachplanId}")
    public TeachplanMedia delTeachplanMediaById(@PathVariable("teachplanId") String teachplanId);
}
