package com.zb.service;

import com.zb.pojo.XcUser;


/**
 * @author YuanHaiZhao
 * @Description TODO
 * @date 2020/6/6
 * @Version v1.0
 */
public interface XcUserService {

    public XcUser getXcUserById(String id) throws Exception;

    public XcUser getXcUserListByMap(String username, String password) throws Exception;

    public Integer insertXcUser(XcUser xcUser) throws Exception;

    public Integer updateXcUser(XcUser xcUser) throws Exception;

    public Integer deleteXcUserById(String id) throws Exception;

    public XcUser getCurrentUser(String token);
}
