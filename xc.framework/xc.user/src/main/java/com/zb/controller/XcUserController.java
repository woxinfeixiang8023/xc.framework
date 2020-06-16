package com.zb.controller;

import com.zb.pojo.XcUser;
import com.zb.service.XcUserService;
import com.zb.service.XcUserTokenService;
import com.zb.util.SMSService;
import com.zb.form.UserTokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author YuanHaiZhao
 * @Description TODO
 * @date 2020/6/6
 * @Version v1.0
 */
@RestController
@CrossOrigin
public class XcUserController {

    @Autowired(required = false)
    private XcUserService xcUserService;
    @Autowired(required = false)
    private XcUserTokenService xcUserTokenService;
    @Autowired(required = false)
    private SMSService smsService;

    /**
     * 管理员根据用户id查询用户个人信息 POST:http://localhost:8001/getXcUserById/id
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/getXcUserById/{id}")
    public XcUser getXcUserById(@PathVariable("id") String id) throws Exception {
        return xcUserService.getXcUserById(id);
    }

    /**
     * 用户或者管理员登录 POST:http://localhost:8001/getXcUserListByMap
     *
     * @return
     * @throws Exception
     * @username
     * @password
     */
    @PostMapping(value = "/getXcUserListByMap")
    public UserTokenVo getXcUserListByMap(String username, String password, HttpServletRequest request) throws Exception {
        //调用登录方法 返回登录的对象信息
        XcUser xcUser = xcUserService.getXcUserListByMap(username, password);
        //判断返回的对象是否为空
        if (xcUser != null) {
            //不为空 调用创建token的方法 获取请求头和当前登录对象的Username
            String token = xcUserTokenService.createToken(request.getHeader("user-agent"), xcUser.getUsername());
            //调用存储token的方法 将token和登录对象存储
            xcUserTokenService.saveToken(token, xcUser);
            //创建一个UserTokenVo工具对象
            UserTokenVo userTokenVo = new UserTokenVo();
            userTokenVo.setToken(token);
            userTokenVo.setExpTime(System.currentTimeMillis());
            userTokenVo.setCurrTime(System.currentTimeMillis() * 2 * 60 * 60);
            return userTokenVo;
        }
        return null;
    }

    /**
     * 取出token
     *
     * @param token
     * @return
     */
    @PostMapping(value = "/getCurrentUser/{token}")
    public XcUser getCurrentUser(@PathVariable("token") String token) {
        return xcUserService.getCurrentUser(token);
    }

    /**
     * 用户注册或者管理员添加新的用户 POST:http://localhost:8001/insertXcUser
     *
     * @param xcUser
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/insertXcUser")
    public Integer insertXcUser(XcUser xcUser) throws Exception {
        return xcUserService.insertXcUser(xcUser);
    }

    /**
     * 用户或者管理员修改个人信息 POST:
     *
     * @param xcUser
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/updateXcUser")
    public Integer updateXcUser(XcUser xcUser) throws Exception {
        return xcUserService.updateXcUser(xcUser);
    }

    /**
     * 管理员根据用户id删除用户信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/deleteXcUserById/{id}")
    public Integer deleteXcUserById(@PathVariable("id") String id) throws Exception {
        return xcUserService.deleteXcUserById(id);
    }

}
