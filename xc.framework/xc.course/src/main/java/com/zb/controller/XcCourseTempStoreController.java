package com.zb.controller;

import com.zb.service.XcCourseTempStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/17
 * @Version V1.0
 */
@RestController
public class XcCourseTempStoreController {

    @Autowired
    private XcCourseTempStoreService xcCourseTempStoreService;

    @GetMapping(value = "/updateXcCourseTempStore")
    public Integer updateXcCourseTempStore(@RequestParam Map<String, Object> param) {
        return xcCourseTempStoreService.updateXcCourseTempStore(param);
    }

}
