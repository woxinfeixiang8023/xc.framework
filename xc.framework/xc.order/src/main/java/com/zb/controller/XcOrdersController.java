package com.zb.controller;

import com.zb.feign.XcUserFeignClient;
import com.zb.pojo.XcOrders;
import com.zb.pojo.XcUser;
import com.zb.service.XcOrdersService;
import com.zb.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/16
 * @Version V1.0
 */
@RestController
@RequestMapping("/api/order")
public class XcOrdersController {

    @Autowired
    private XcOrdersService xcOrdersService;
    @Autowired
    private XcUserFeignClient xcUserFeignClient;

    @PostMapping(value = "/insertXcOrders")
    public Integer insertXcOrders(@RequestBody XcOrders xcOrders) {
        xcOrders.setId(Long.parseLong(IdWorker.getId()));
        xcOrders.setOrderNumber(IdWorker.getId());
        XcUser currentUser = xcUserFeignClient.getCurrentUser(xcOrders.getToken());
        xcOrders.setUserId(currentUser.getId());
        xcOrders.setStatus(0);
        xcOrders.setItemNum(1);
        return xcOrdersService.insertXcOrders(xcOrders);
    }

    @PostMapping(value = "/findOrderPay/{token}")
    public XcOrders findOrderPay(@PathVariable("token") String token) {
        return xcOrdersService.findOrderPay(token);
    }

    @PostMapping(value = "/updateXcOrders")
    public Integer updateXcOrders(@RequestBody XcOrders xcOrders) {
        return xcOrdersService.updateXcOrders(xcOrders);
    }

    @PostMapping(value = "/getXcOrderByOrderNo/{orderNo}")
    public XcOrders getXcOrderByOrderNo(@PathVariable("orderNo") String orderNo) {
        return xcOrdersService.getXcOrderByOrderNo(orderNo);
    }


}
