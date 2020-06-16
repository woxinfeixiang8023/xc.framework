package com.zb.mapper;

import com.zb.pojo.XcUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface XcUserMapper {
	/**
	 * 管理员根据用户id查询用户个人信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public XcUser getXcUserById(@Param("id") String id)throws Exception;

	/**
	 * 用户或者管理员登录
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<XcUser>	getXcUserListByMap(Map<String, Object> param)throws Exception;

	/**
	 * 用户注册或者管理员添加新的用户
	 * @param xcUser
	 * @return
	 * @throws Exception
	 */
	public Integer insertXcUser(XcUser xcUser)throws Exception;

	/**
	 * 用户或者管理员修改个人信息
	 * @param xcUser
	 * @return
	 * @throws Exception
	 */
	public Integer updateXcUser(XcUser xcUser)throws Exception;

	/**
	 * 管理员根据用户id删除用户信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Integer deleteXcUserById(@Param("id") String id)throws Exception;

}
