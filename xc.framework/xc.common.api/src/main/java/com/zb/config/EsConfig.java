package com.zb.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/5/13
 * @Version V1.0
 */
@Configuration
public class EsConfig {
    @Value("${myes.host}")
    private String hostList;

    //创建客户端对象
    @Bean
    public RestHighLevelClient createClient() {
        //配件集群环境的代码
        String[] hosts = hostList.split(",");
        HttpHost[] httpHosts = new HttpHost[hosts.length];
        for (int i = 0; i < httpHosts.length; i++) {
            String item = hosts[i];
            httpHosts[i] = new HttpHost(item.split(":")[0], Integer.parseInt(item.split(":")[1]));
        }
        return new RestHighLevelClient(RestClient.builder(httpHosts));
    }


}
