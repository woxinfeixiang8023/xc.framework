package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.pojo.XcUser;
import com.zb.service.XcUserOperationService;
import com.zb.util.RedisUtil;
import com.zb.util.SMSService;
import com.zb.form.TakeRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YuanHaiZhao
 * @Description TODO
 * @date 2020/6/9
 * @Version v1.0
 */
@Service
public class XcUserOperationServiceImpl implements XcUserOperationService {
    private String key1;
    private String key2;
    private String key3;
    @Autowired(required = false)
    private RedisUtil redisUtil;
    @Autowired
    private SMSService smsService;

    @Override
    public XcUser getXcUserById(String token) {
        //String key = "token-" + token;
        if (redisUtil.hasKey(token)) {
            System.out.println("redis中查询信息");
            String json = redisUtil.get(token).toString();
            XcUser xcUser = JSON.parseObject(json, XcUser.class);
            return xcUser;
        }
        return null;
    }

    /**
     * 发送短信验证码
     *
     * @param phone
     * @return
     */
    @Override
    public Integer sendPhoneMsg(String phone) {
        int code = (int) (Math.random() * 10000);
        System.out.println("发送的验证码：" + code);
        //phone:18361211916 templated:模板编号 new String[]{code+"","1"}：随机数
        //smsService.send(phone,"1", new String[]{code+"","1"});
        String key = "phone:" + phone;
        redisUtil.set(key, code + "", 60);
        return code;
    }

    /**
     * 验证短信
     *
     * @param phone
     * @param strCode
     * @return
     */
    @Override
    public String verifPhoneMsg(String phone, String strCode) {
        String key = "phone:" + phone;
        if (redisUtil.hasKey(key)) {
            String code = redisUtil.get(key).toString();
            if (strCode.equals(code)) {
                return "success";
            } else {
                return "error";
            }
        }
        return null;
    }

    @Override
    public String changeUsernameSetRedis(String username) {
        key1 = "username:" + username;
        redisUtil.set(key1,username+"",60*3);
        return "success";
    }

    @Override
    public String changePhoneSetRedis(String phone) {
        key2 = "phone:" + phone;
        redisUtil.set(key2,phone+"",60*3);
        return "success";
    }

    @Override
    public String changePasswordSetRedis(String password) {
        key3 = "password:" + password;
        redisUtil.set(key3,password+"",60*3);
        return "success";
    }

    public TakeRedis TakeParameters(){
        String username = null;
        String phone = null;
        String password = null;
        TakeRedis takeRedis=new TakeRedis();
        if (redisUtil.hasKey(key1)){
           username=redisUtil.get(key1).toString();
        }
        if (redisUtil.hasKey(key2)){
            phone=redisUtil.get(key2).toString();
        }
        if (redisUtil.hasKey(key3)) {
            password = redisUtil.get(key3).toString();
        }
        takeRedis.setUsername(username);
        takeRedis.setPhone(phone);
        takeRedis.setPassword(password);
        return takeRedis;
    }

}
