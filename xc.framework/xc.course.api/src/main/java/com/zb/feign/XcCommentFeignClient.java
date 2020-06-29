package com.zb.feign;

import com.zb.dto.Dto;
import com.zb.form.GetXcCommentListByMapForm;
import com.zb.pojo.XcComment;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/28
 * @Version V1.0
 */
@FeignClient("courseserver")
public interface XcCommentFeignClient {

    @PostMapping(value = "/getXcCommentListByMap")
    public Dto getXcCommentListByMap(@RequestBody GetXcCommentListByMapForm getXcCommentListByMapForm);

    @PostMapping(value = "/insertXcComment")
    public Dto insertXcComment(@RequestBody XcComment xcComment);

    @GetMapping(value = "/getXcCommentById/{id}")
    public XcComment getXcCommentById(@PathVariable("id") Long id);

    @PostMapping(value = "/updateXcComment")
    public Integer updateXcComment(@RequestBody XcComment xcComment);

    @DeleteMapping(value = "/delXcCommentById/{id}")
    public Integer delXcCommentById(@PathVariable("id") Long id);
}
