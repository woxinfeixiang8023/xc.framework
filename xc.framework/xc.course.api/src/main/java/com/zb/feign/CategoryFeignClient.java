package com.zb.feign;

import com.zb.dto.Dto;
import com.zb.pojo.Category;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/3
 * @Version V1.0
 */
@FeignClient("courseserver")
public interface CategoryFeignClient {

    @GetMapping(value = "/getCategoryById/{id}")
    public Category getCategoryById(@PathVariable("id") String id);

    @PostMapping(value = "/getCategoryListByMap")
    public List<Category> getCategoryListByMap(@RequestParam Map<String, Object> param);

    @PostMapping(value = "/getCategoryCountByMap")
    public Integer getCategoryCountByMap(@RequestParam Map<String, Object> param);

    @PostMapping(value = "/insertCategory")
    public Integer insertCategory(@RequestBody Category category);

    @PostMapping(value = "/updateCategory")
    public Integer updateCategory(@RequestBody Category category);

    @DeleteMapping(value = "/delCategoryById/{id}")
    public Integer delCategoryById(@PathVariable("id") String id);
}
