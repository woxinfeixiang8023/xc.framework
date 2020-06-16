package com.zb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author YuanHaiZhao
 * @Description TODO
 * @date 2020/6/6
 * @Version v1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class XcUserServerApp {
    public static void main(String[] args) {
        SpringApplication.run(XcUserServerApp.class,args);
    }
}
