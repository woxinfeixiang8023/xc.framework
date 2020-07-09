package com.zb.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/7/3
 * @Version V1.0
 */
@EnableWebSecurity
public class EurekaConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // 禁止掉csrf跨域攻击，以免服务无法注册
                .authorizeRequests() // 设置需要认证所有请求
                .mvcMatchers("/eureka/**").permitAll() // 符合设置路径规则的放行
                .anyRequest().authenticated().and().httpBasic(); // 剩余所有请求都需要认证
    }
}
