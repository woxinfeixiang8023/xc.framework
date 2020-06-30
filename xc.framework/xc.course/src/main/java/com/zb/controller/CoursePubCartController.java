package com.zb.controller;

import com.zb.form.CartForm;
import com.zb.pojo.CoursePubCart;
import com.zb.service.CoursePubCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/30
 * @Version V1.0
 */
@RestController
@Api(value = "收藏（购物车）的接口", tags = {"查询收藏（购物车）操作接口"})
public class CoursePubCartController {

    @Autowired
    private CoursePubCartService coursePubCartService;

    @ApiOperation(value = "添加购物车", notes = "根据用户id、商品id、商品数量添加购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户id", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "goodsId", value = "商品id", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "num", value = "商品数量", required = true, dataType = "int", paramType = "path")
    })
    @PostMapping(value = "/addCart/{uid}/{goodsId}/{num}")
    public boolean addCart(@PathVariable("uid") Integer uid, @PathVariable("goodsId") String goodsId, @PathVariable("num") Integer num) {
        return coursePubCartService.addCart(uid, goodsId, num);
    }

    @ApiOperation(value = "修改购物车商品数量", notes = "根据用户id、商品id、商品数量、op操作修改购物车商品数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户id", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "goodsId", value = "商品id", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "num", value = "商品数量", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "op", value = "操作", required = true, dataType = "String", paramType = "path")
    })
    @PostMapping(value = "/updateNum/{uid}/{goodsId}/{num}/{op}")
    public void updateNum(@PathVariable("uid") Integer uid, @PathVariable("goodsId") String goodsId, @PathVariable("num") Integer num, @PathVariable("op") String op) {
        coursePubCartService.updateNum(uid, goodsId, num, op);
    }

    @ApiOperation(value = "删除购物车商品", notes = "根据用户id、商品id、删除购物车商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户id", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "goodsId", value = "商品id", required = true, dataType = "String", paramType = "path"),
    })
    @PostMapping(value = "/delItem//{uid}/{goodsId}")
    public void delItem(@PathVariable("uid") Integer uid, @PathVariable("goodsId") String goodsId) {
        coursePubCartService.delItem(uid, goodsId);
    }

    @ApiOperation(value = "查询购物车所有商品", notes = "根据用户id查询购物车所有商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户id", required = true, dataType = "int", paramType = "path"),
    })
    @PostMapping(value = "/getCartAllItem//{uid}")
    public CoursePubCart getCartAllItem(@PathVariable("uid") Integer uid) {
        return coursePubCartService.getCartAllItem(uid);
    }

}
