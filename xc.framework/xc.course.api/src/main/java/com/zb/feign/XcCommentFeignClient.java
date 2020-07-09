package com.zb.feign;

import com.zb.dto.Dto;
import com.zb.form.GetXcCommentListByMapForm;
import com.zb.pojo.XcComment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/28
 * @Version V1.0
 */
@FeignClient("courseserver")
public interface XcCommentFeignClient {

    @PostMapping(value = "/api/course/getXcCommentListByMap")
    public Dto getXcCommentListByMap(@RequestBody GetXcCommentListByMapForm getXcCommentListByMapForm);

    @PostMapping(value = "/api/course/insertXcComment")
    public Dto insertXcComment(@RequestBody XcComment xcComment);

    @GetMapping(value = "/api/course/getXcCommentById/{id}")
    public XcComment getXcCommentById(@PathVariable("id") Long id);

    @PostMapping(value = "/api/course/updateXcComment")
    public Integer updateXcComment(@RequestBody XcComment xcComment);

    @DeleteMapping(value = "/api/course/delXcCommentById/{id}")
    public Integer delXcCommentById(@PathVariable("id") Long id);
}
