package com.zb.service;

import com.zb.pojo.XcUser;

/**
 * @author YuanHaiZhao
 * @Description TODO
 * @date 2020/6/6
 * @Version v1.0
 */
public interface XcUserTokenService {
    /**
     * 创建token
     * @param agentStr
     * @param userCode
     * @return
     */
    public String createToken(String agentStr, String userCode);

    /**
     * 保存token
     * @param token
     * @param xcUser
     */
    public void saveToken(String token, XcUser xcUser);

}
