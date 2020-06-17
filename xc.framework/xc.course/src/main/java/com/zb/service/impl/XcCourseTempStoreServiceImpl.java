package com.zb.service.impl;

import com.zb.mapper.XcCourseTempStoreMapper;
import com.zb.service.XcCourseTempStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/17
 * @Version V1.0
 */
@Service
public class XcCourseTempStoreServiceImpl implements XcCourseTempStoreService {

    @Autowired(required = false)
    private XcCourseTempStoreMapper xcCourseTempStoreMapper;

    @Override
    public Integer updateXcCourseTempStore(Map<String, Object> param) {
        try {
            return xcCourseTempStoreMapper.updateXcCourseTempStore(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
