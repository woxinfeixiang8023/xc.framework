package com.zb.service.impl;

import com.zb.mapper.XcTeacherMapper;
import com.zb.pojo.XcTeacher;
import com.zb.service.XcTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author YuanHaiZhao
 * @Description TODO
 * @date 2020/6/10
 * @Version v1.0
 */
@Service
public class XcTeacherServiceImpl implements XcTeacherService {

    @Autowired(required =false)
    private XcTeacherMapper xcTeacherMapper;
    @Override
    public XcTeacher getXcTeacherById(String id) throws Exception {
        return xcTeacherMapper.getXcTeacherById(id);
    }

    @Override
    public Integer insertXcTeacher(XcTeacher xcTeacher) throws Exception {
        return xcTeacherMapper.insertXcTeacher(xcTeacher);
    }

    @Override
    public Integer updateXcTeacher(XcTeacher xcTeacher) throws Exception {
        return xcTeacherMapper.updateXcTeacher(xcTeacher);
    }

    @Override
    public Integer deleteXcTeacherById(String id) throws Exception {
        return xcTeacherMapper.deleteXcTeacherById(id);
    }
}
