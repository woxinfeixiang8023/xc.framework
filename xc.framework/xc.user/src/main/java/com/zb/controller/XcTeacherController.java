package com.zb.controller;

import com.zb.pojo.XcTeacher;
import com.zb.service.XcTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author YuanHaiZhao
 * @Description TODO
 * @date 2020/6/10
 * @Version v1.0
 */
@RestController
@CrossOrigin
public class XcTeacherController {

    @Autowired(required =false)
    private XcTeacherService xcTeacherService;

    /**
     * 根据id查询老师信息
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/getXcTeacherById")
    public XcTeacher getXcTeacherById(String id)throws Exception{
        return xcTeacherService.getXcTeacherById(id);
    }

    /**
     * 管理员添加老师
     * @param xcTeacher
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/insertXcTeacher")
    public Integer insertXcTeacher(XcTeacher xcTeacher)throws Exception{
        return xcTeacherService.insertXcTeacher(xcTeacher);
    }

    /**
     * 管理员更新或用户修改信息
     * @param xcTeacher
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/updateXcTeacher")
    public Integer updateXcTeacher(XcTeacher xcTeacher)throws Exception{
        return xcTeacherService.updateXcTeacher(xcTeacher);
    }

    /**
     * 管理员根据id删除老师信息
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/deleteXcTeacherById")
    public Integer deleteXcTeacherById(String id)throws Exception{
        return xcTeacherService.deleteXcTeacherById(id);
    }

}
