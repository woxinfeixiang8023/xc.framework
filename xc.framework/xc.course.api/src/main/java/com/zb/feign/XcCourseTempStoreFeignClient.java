package com.zb.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/17
 * @Version V1.0
 */
@FeignClient("courseserver")
public interface XcCourseTempStoreFeignClient {
    @GetMapping(value = "/updateXcCourseTempStore")
    public Integer updateXcCourseTempStore(@RequestParam Map<String, Object> param);
}
