package com.zb.util;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class SMSService {
	/**
	 * 发送给短信
	 * @param to 手机号
	 * @param templated 模板编号
	 * @param datas 生成的随机数
	 */
	public void send(String to, String templated, String[]datas){
		//https://www.yuntongxun.com/member/main
		//创建对象
		CCPRestSmsSDK sdk = new CCPRestSmsSDK();
		//云通讯 控制台：Rest URL(生产)：
		sdk.init("app.cloopen.com", "8883");
		//ACCOUNT SID:主账户ID  AUTH TOKEN:账户授权令牌
		sdk.setAccount("8aaf070870e20ea1017133ddbc632b19", "6bbe56b751ba4bc7a408980f7338e72d");
		//AppID默认
		sdk.setAppId("8aaf070870e20ea1017133ddbcb82b1f");
		HashMap result = sdk.sendTemplateSMS(to, templated, datas);
		System.out.println(result);
	}
/*	public static void main(String[] args) {
		SMSService sms = new SMSService();
		int code = (int)(Math.random()*10000);
		System.out.println(code);
		sms.send("18361211916", "1", new String[]{code+"","1"});
	}*/
}
