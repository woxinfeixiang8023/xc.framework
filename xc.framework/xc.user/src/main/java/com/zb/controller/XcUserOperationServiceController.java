package com.zb.controller;

import com.zb.pojo.XcUser;
import com.zb.service.XcUserOperationService;
import com.zb.form.TakeRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author YuanHaiZhao
 * @Description TODO
 * @date 2020/6/9
 * @Version v1.0
 */
@RestController
@RequestMapping("/api/user")
public class XcUserOperationServiceController {

    @Autowired(required = false)
    private XcUserOperationService xcUserOperationService;

    /**
     * 用户登录
     * @param token
     * @return
     */
    @PostMapping(value = "/getXcUserById")
    public XcUser getXcUserById(String token){
        return xcUserOperationService.getXcUserById(token);
    }


    /**
     * 发送短信
     * @param phone
     * @return
     */
    @PostMapping(value = "/sendPhoneMsg")
    public Integer sendPhoneMsg(String phone){
        return xcUserOperationService.sendPhoneMsg(phone);
    }

    /**
     * 验证短信
     * @param phone
     * @param strCode
     * @return
     */
    @PostMapping(value = "/verifPhoneMsg")
    public String verifPhoneMsg(String phone, String strCode){
        return xcUserOperationService.verifPhoneMsg(phone, strCode);
    }

    /**
     * 修改密码 用户名存储redis
     * @param username
     * @return
     */
    @PostMapping(value = "/changeUsernameSetRedis")
    public String changeUsernameSetRedis(String username){
        return xcUserOperationService.changeUsernameSetRedis(username);
    }

    /**
     * 修改密码 手机号存储redis
     * @param phone
     * @return
     */
    @PostMapping(value = "/changePhoneSetRedis")
    public String changePhoneSetRedis(String phone){
        return xcUserOperationService.changePhoneSetRedis(phone);
    }

    /**
     * 修改密码 新密码存储redis
     * @param password
     * @return
     */
    @PostMapping(value = "/changePasswordSetRedis")
    public String changePasswordSetRedis(String password){
        return xcUserOperationService.changePasswordSetRedis(password);
    }

    /**
     *从redis中取出用户名密码
     * @return
     */
    @GetMapping(value = "/TakeParameters")
    public TakeRedis TakeParameters(){
        return xcUserOperationService.TakeParameters();
    }
}
