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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/3
 * @Version V1.0
 */
@RestController
@Api(value = "分类的接口", tags = {"查询分类操作接口"})
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

    @GetMapping(value = "/getcontentList/{categoryId}")
    @ApiOperation(value = "查询轮播图地址", notes = "根据category_id查询轮播图地址")
    @ApiImplicitParam(name = "categoryId", value = "内容类目ID", required = true, dataType = "int", paramType = "path")
    public Dto getcontentList(@PathVariable("categoryId") Integer id) {
        //程序内部发起的http请求， 调用ngix的执行读的操作的接口
        String myurl = "http://localhost:9000/readcontent?id=" + id;
        String result = restTemplate.getForObject(myurl, String.class);
        List<XcContent> contentList = null;
        contentList = JSON.parseArray(result, XcContent.class);
        System.out.println("远程调用nginx中的接口程序:" + contentList);
        return DtoUtil.returnSuccess("contentOk", contentList);
    }
}
