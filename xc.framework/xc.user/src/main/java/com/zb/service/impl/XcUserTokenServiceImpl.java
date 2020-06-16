package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.pojo.XcUser;
import com.zb.service.XcUserTokenService;
import com.zb.util.MD5;
import com.zb.util.RedisUtil;
import nl.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YuanHaiZhao
 * @Description TODO
 * @date 2020/6/6
 * @Version v1.0
 */
@Service
public class XcUserTokenServiceImpl implements XcUserTokenService {

    @Autowired(required = false)
    private RedisUtil redisUtil;

    /**
     * 创建token
     * @param agentStr
     * @param userCode
     * @return
     */
    @Override
    public String createToken(String agentStr, String userCode) {
        StringBuffer token=new StringBuffer("token-");
        UserAgent userAgent = UserAgent.parseUserAgentString(agentStr);
        //判断登录状态的客户端：手机、电脑
        if (userAgent.getOperatingSystem().isMobileDevice()){
            token.append("MOBILE-");
        }else {
            token.append("PC-");
        }
        //MD5加密 长度32
        token.append(MD5.getMd5(userCode, 32)).append("-");
        //拼接一个时间戳
        token.append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())).append("-");
        //MD5加密 请求头 长度6位
        token.append(MD5.getMd5(agentStr, 6));
        return token.toString();//返回token字符串格式
    }

    /**
     * 保存token
     * @param token
     * @param xcUser
     */
    @Override
    public void saveToken(String token, XcUser xcUser) {
        //判断token的前缀是否是token-MOBILE- 手机移动端
        if (token.startsWith("token-MOBILE-")) {
            //手机客户端存储永久登录信息
            redisUtil.set(token, JSON.toJSONString(xcUser));
        } else {
            // PC客户端 存储2个小时登录信息
            redisUtil.set(token,  JSON.toJSONString(xcUser),60 * 60 * 2);
        }
    }

    /**
     * 取出token值 XcUser对象
     *
     * @param token
     * @return
     */
    /**
     * 验证用户的身份
     *
     * @param token
     * @return
     */
    public XcUser getCurrentUser(String token) {
        String json = redisUtil.get(token).toString();
        if (json == null) {
            return null;
        }
        XcUser xcUser= JSON.parseObject(json, XcUser.class);
        return xcUser;
    }
}
