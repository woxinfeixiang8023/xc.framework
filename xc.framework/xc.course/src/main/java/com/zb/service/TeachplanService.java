package com.zb.service;

import com.zb.form.TeachplanForm;
import com.zb.pojo.Teachplan;

import java.util.List;
import java.util.Map;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/10
 * @Version V1.0
 */
public interface TeachplanService {
    public List<Teachplan> getTeachplanListByMap(String courseId, String grade, String parentId);

    public TeachplanForm getTeachplanFormByCourseId(String courseId);

}
