package com.zb.feign;

import com.zb.dto.Dto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/3
 * @Version V1.0
 */
@FeignClient("courseserver")
public interface CategoryFeignClient {
    @GetMapping(value = "/getCategoryList/{parentId}")
    public Dto getCategoryList(@PathVariable("parentId") String parentId);

    @GetMapping(value = "/getcontentList/{categoryId}")
    public Dto getcontentList(@PathVariable("categoryId") Integer id);
}
