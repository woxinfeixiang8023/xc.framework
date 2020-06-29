package com.zb.feign;

import com.zb.pojo.CoursePub;
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
public interface CoursePubFeignClient {
    @GetMapping(value = "/getCoursePubById/{id}")
    public CoursePub getCoursePubById(@PathVariable("id") String id);

    @PostMapping(value = "/getCoursePubListByMap")
    public List<CoursePub> getCoursePubListByMap(@RequestParam Map<String, Object> param);

    @PostMapping(value = "/getCoursePubCountByMap")
    public Integer getCoursePubCountByMap(@RequestParam Map<String, Object> param);

    @PostMapping(value = "/insertCoursePub")
    public Integer insertCoursePub(@RequestBody CoursePub coursePub);

    @PostMapping(value = "/updateCoursePub")
    public Integer updateCoursePub(@RequestBody CoursePub coursePub);

    @DeleteMapping(value = "/delCoursePubById/{id}")
    public Integer delCoursePubById(@PathVariable("id") String id);
}
