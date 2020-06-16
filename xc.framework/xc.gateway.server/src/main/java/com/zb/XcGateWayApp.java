package com.zb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/3
 * @Version V1.0
 */
@SpringBootApplication
@EnableZuulProxy
public class XcGateWayApp {
    public static void main(String[] args) {
        SpringApplication.run(XcGateWayApp.class, args);
    }
}
