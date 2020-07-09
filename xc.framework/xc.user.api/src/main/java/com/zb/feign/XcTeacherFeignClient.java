package com.zb.feign;

import com.zb.pojo.XcTeacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author YuanHaiZhao
 * @Description TODO
 * @date 2020/6/10
 * @Version v1.0
 */
@FeignClient(value = "userServer")
public interface XcTeacherFeignClient {
    @PostMapping(value = "/api/user/getXcTeacherById/{id}")
    public XcTeacher getXcTeacherById(@PathVariable("id") String id) throws Exception;

    @PostMapping(value = "/api/user/insertXcTeacher")
    public Integer insertXcTeacher(@RequestBody XcTeacher xcTeacher) throws Exception;

    @PostMapping(value = "/api/user/updateXcTeacher")
    public Integer updateXcTeacher(@RequestBody XcTeacher xcTeacher) throws Exception;

    @PostMapping(value = "/api/user/deleteXcTeacherById/{id}")
    public Integer deleteXcTeacherById(@PathVariable("id") String id) throws Exception;
}
