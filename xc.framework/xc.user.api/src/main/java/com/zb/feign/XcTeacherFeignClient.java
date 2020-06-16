package com.zb.feign;

import com.zb.pojo.XcTeacher;
import org.springframework.cloud.netflix.feign.FeignClient;
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
    @PostMapping(value = "/getXcTeacherById/{id}")
    public XcTeacher getXcTeacherById(@PathVariable("id") String id)throws Exception;

    @PostMapping(value = "/getXcTeacherListByMap")
    public List<XcTeacher> getXcTeacherListByMap(@RequestParam Map<String, Object> param)throws Exception;

    @PostMapping(value = "/insertXcTeacher")
    public Integer insertXcTeacher(@RequestBody XcTeacher xcTeacher)throws Exception;

    @PostMapping(value = "/updateXcTeacher")
    public Integer updateXcTeacher(@RequestBody XcTeacher xcTeacher)throws Exception;

    @PostMapping(value = "/deleteXcTeacherById/{id}")
    public Integer deleteXcTeacherById(@PathVariable("id") String id)throws Exception;
}
