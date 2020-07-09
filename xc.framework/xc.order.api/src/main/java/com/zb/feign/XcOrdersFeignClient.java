package com.zb.feign;

import com.zb.pojo.XcOrders;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/16
 * @Version V1.0
 */
@FeignClient(value = "orderserver")
public interface XcOrdersFeignClient {
    @PostMapping(value = "/api/order/insertXcOrders")
    public Integer insertXcOrders(@RequestBody XcOrders xcOrders);

    @PostMapping(value = "/api/order/findOrderPay/{token}")
    public XcOrders findOrderPay(@PathVariable("token") String token);

    @PostMapping(value = "/api/order/updateXcOrders")
    public Integer updateXcOrders(@RequestBody XcOrders xcOrders);

    @PostMapping(value = "/api/order/getXcOrderByOrderNo/{orderNo}")
    public XcOrders getXcOrderByOrderNo(@PathVariable("orderNo") String orderNo);
}
