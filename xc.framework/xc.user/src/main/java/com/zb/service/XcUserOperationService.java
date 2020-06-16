package com.zb.service;

import com.zb.pojo.XcUser;
import com.zb.form.TakeRedis;

/**
 * @author YuanHaiZhao
 * @Description TODO
 * @date 2020/6/9
 * @Version v1.0
 */
public interface XcUserOperationService {

    public XcUser getXcUserById(String token);

    public Integer sendPhoneMsg(String phone);

    public String verifPhoneMsg(String phone,String strCode);

    public String changeUsernameSetRedis(String username);

    public String changePhoneSetRedis(String phone);

    public String changePasswordSetRedis(String password);

    public TakeRedis TakeParameters();
}
