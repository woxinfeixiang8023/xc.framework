package com.zb.feign;

import com.zb.pojo.XcOrders;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/16
 * @Version V1.0
 */
@FeignClient(value = "orderserver")
public interface XcOrdersFeignClient {
    @PostMapping(value = "/insertXcOrders")
    public Integer insertXcOrders(XcOrders xcOrders);

    @GetMapping(value = "/findOrderPay/{token}")
    public XcOrders findOrderPay(@PathVariable("token") String token);

    @PostMapping(value = "/updateXcOrders")
    public Integer updateXcOrders(XcOrders xcOrders);

    @PostMapping(value = "/getXcOrderByOrderNo/{orderNo}")
    public XcOrders getXcOrderByOrderNo(@PathVariable("orderNo") String orderNo);
}
