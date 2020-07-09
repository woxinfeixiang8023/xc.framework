package com.zb.feign;

import com.zb.pojo.CoursePub;
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
public interface CoursePubFeignClient {
    @GetMapping(value = "/api/course/getCoursePubById/{id}")
    public CoursePub getCoursePubById(@PathVariable("id") String id);

    @PostMapping(value = "/api/course/getCoursePubListByMap")
    public List<CoursePub> getCoursePubListByMap(@RequestParam Map<String, Object> param);

    @PostMapping(value = "/api/course/getCoursePubCountByMap")
    public Integer getCoursePubCountByMap(@RequestParam Map<String, Object> param);

    @PostMapping(value = "/api/course/insertCoursePub")
    public Integer insertCoursePub(@RequestBody CoursePub coursePub);

    @PostMapping(value = "/api/course/updateCoursePub")
    public Integer updateCoursePub(@RequestBody CoursePub coursePub);

    @DeleteMapping(value = "/api/course/delCoursePubById/{id}")
    public Integer delCoursePubById(@PathVariable("id") String id);
}
