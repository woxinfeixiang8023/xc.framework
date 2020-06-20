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
@CrossOrigin
public class XcOrdersController {

    @Autowired
    private XcOrdersService xcOrdersService;
    @Autowired
    private XcUserFeignClient xcUserFeignClient;

    @PostMapping(value = "/insertXcOrders")
    public Integer insertXcOrders(XcOrders xcOrders) {
        xcOrders.setId(Long.parseLong(IdWorker.getId()));
        xcOrders.setOrderNumber(IdWorker.getId());
        XcUser currentUser = xcUserFeignClient.getCurrentUser(xcOrders.getToken());
        xcOrders.setUserId(currentUser.getId());
        xcOrders.setStatus(0);
        xcOrders.setItemNum(1);
        return xcOrdersService.insertXcOrders(xcOrders);
    }

    @PostMapping(value = "/findOrderPay")
    public XcOrders findOrderPay(@RequestParam String token) {
        return xcOrdersService.findOrderPay(token);
    }

    @PostMapping(value = "/updateXcOrders")
    public Integer updateXcOrders(XcOrders xcOrders) {
        return xcOrdersService.updateXcOrders(xcOrders);
    }

    @PostMapping(value = "/getXcOrderByOrderNo")
    public XcOrders getXcOrderByOrderNo(String orderNo) {
        return xcOrdersService.getXcOrderByOrderNo(orderNo);
    }


}
