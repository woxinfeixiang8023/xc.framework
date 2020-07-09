package com.zb.feign;

import com.zb.pojo.XcUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author YuanHaiZhao
 * @Description TODO
 * @date 2020/6/6
 * @Version v1.0
 */
@FeignClient(value = "userServer")
public interface XcUserFeignClient {

    @PostMapping(value = "/api/user/getXcUserById/{id}")
    public XcUser getXcUserById(@PathVariable("id") String id) throws Exception;

    @PostMapping(value = "/api/user/getXcUserListByMap")
    public List<XcUser> getXcUserListByMap(@RequestParam Map<String, Object> param) throws Exception;

    @PostMapping(value = "/api/user/insertXcUser")
    public Integer insertXcUser(@RequestBody XcUser xcUser) throws Exception;

    @PostMapping(value = "/api/user/updateXcUser")
    public Integer updateXcUser(@RequestBody XcUser xcUser) throws Exception;

    @PostMapping(value = "/api/user/deleteXcUserById/{id}")
    public Integer deleteXcUserById(@PathVariable(value = "id") String id) throws Exception;

    @PostMapping(value = "/api/user/getCurrentUser/{token}")
    public XcUser getCurrentUser(@PathVariable("token") String token);
}
