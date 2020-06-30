package com.zb;

import com.zb.pojo.CoursePub;
import com.zb.service.CoursePubCartService;
import com.zb.service.CoursePubService;
import com.zb.tools.CanalTools;
import com.zb.tools.EsTools;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2
@EnableCaching
@EnableScheduling
public class XcCourseServerApp {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(XcCourseServerApp.class, args);

        CoursePubService coursePubService = context.getBean(CoursePubService.class);
        coursePubService.coursePubToRedis();
        /*EsTools esTools = context.getBean(EsTools.class);
        esTools.addIndex();
        esTools.importData();*/
        /*CoursePubCartService coursePubCartService = context.getBean(CoursePubCartService.class);
        coursePubCartService.dbToRedisCart();*/

        CanalTools bean = context.getBean(CanalTools.class);
        bean.execution();


    }

    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }
}
