package com.zb.service;

import com.zb.pojo.XcLearningCourse;
import com.zb.pojo.XcTask;
import org.springframework.web.bind.annotation.RequestParam;

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
}
