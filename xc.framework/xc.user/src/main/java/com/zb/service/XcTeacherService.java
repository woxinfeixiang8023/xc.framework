package com.zb.service;

import com.zb.pojo.XcTeacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author YuanHaiZhao
 * @Description TODO
 * @date 2020/6/10
 * @Version v1.0
 */
public interface XcTeacherService {

    public XcTeacher getXcTeacherById(String id)throws Exception;

    public Integer insertXcTeacher(XcTeacher xcTeacher)throws Exception;

    public Integer updateXcTeacher(XcTeacher xcTeacher)throws Exception;

    public Integer deleteXcTeacherById(String id)throws Exception;
}
