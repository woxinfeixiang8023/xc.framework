package com.zb.controller;

import com.alibaba.fastjson.JSON;
import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.pojo.Category;
import com.zb.pojo.XcContent;
import com.zb.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/3
 * @Version V1.0
 */
@RestController
@Api(value = "分类的接口", tags = {"查询分类操作接口"})
@RequestMapping("/api/course")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired(required = false)
    private RestTemplate restTemplate;

    @GetMapping(value = "/getCategoryList/{parentId}")
    @ApiOperation(value = "查询分类", notes = "根据父级信息查询分类")
    @ApiImplicitParam(name = "parentId", value = "父级id", required = true, dataType = "String", paramType = "path")
    public Dto getCategoryList(@PathVariable("parentId") String parentId) {
        List<Category> categoryList = categoryService.getCategoryListByMap(parentId);
        return DtoUtil.returnSuccess("ok", categoryList);
    }

    @GetMapping(value = "/getContentList/{id}")
    @ApiOperation(value = "查询轮播图地址", notes = "根据category_id查询轮播图地址")
    @ApiImplicitParam(name = "id", value = "内容类目ID", required = true, dataType = "int", paramType = "path")
    public Dto getContentList(@PathVariable("id") Integer id) {
        //程序内部发起的http请求， 调用ngix的执行读的操作的接口
        String myurl = "http://localhost:9000/readcontent?id=" + id;
        String result = restTemplate.getForObject(myurl, String.class);
        List<XcContent> contentList = null;
        contentList = JSON.parseArray(result, XcContent.class);
        System.out.println("远程调用nginx中的接口程序:" + contentList);
        return DtoUtil.returnSuccess("contentOk", contentList);
    }

    @GetMapping(value = "/getCategoryById/{id}")
    public Category getCategoryById(@PathVariable("id") String id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping(value = "/getCategoryListByMap")
    public List<Category> getCategoryListByMap(@RequestParam Map<String, Object> param) {
        return categoryService.getCategoryListByMap(param);
    }

    @PostMapping(value = "/getCategoryCountByMap")
    public Integer getCategoryCountByMap(@RequestParam Map<String, Object> param) {
        return categoryService.getCategoryCountByMap(param);
    }

    @PostMapping(value = "/insertCategory")
    public Integer insertCategory(@RequestBody Category category) {
        return categoryService.insertCategory(category);
    }

    @PostMapping(value = "/updateCategory")
    public Integer updateCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }

    @DeleteMapping(value = "/delCategoryById/{id}")
    public Integer delCategoryById(@PathVariable("id") String id) {
        return categoryService.delCategoryById(id);
    }

}
