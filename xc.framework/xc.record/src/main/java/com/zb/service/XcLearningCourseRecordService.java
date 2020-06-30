package com.zb.service;

import com.zb.pojo.CoursePub;
import com.zb.pojo.XcLearningCourse;
import com.zb.pojo.XcTask;
import com.zb.util.PageUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/18
 * @Version V1.0
 */
public interface XcLearningCourseRecordService {
    public boolean addChooseCourse(XcTask xcTask);

    public XcLearningCourse getXcLearningCourseRecordByUserAndCourse(Map<String, Object> param);

    /*public PageUtil<CoursePub> getXcLearningCourseListByMap(Integer index, Integer size, String token);

    public Integer insertXcLearningCourse(XcLearningCourse xcLearningCourse);

    public Integer updateXcLearningCourse(XcLearningCourse xcLearningCourse);

    public Integer delXcLearningCourseById(String id);*/


}
