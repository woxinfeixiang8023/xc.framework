package com.zb.service;

import com.zb.form.GetTeachplanListByMapForm;
import com.zb.form.TeachplanForm;
import com.zb.pojo.Teachplan;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/10
 * @Version V1.0
 */
public interface TeachplanService {
    public List<Teachplan> getTeachplanListByMap(GetTeachplanListByMapForm getTeachplanListByMapForm);

    public TeachplanForm getTeachplanFormByCourseId(String courseId);

    public Teachplan getTeachplanByCourseId(String courseId);

    public Integer getTeachplanCountByMap(Map<String, Object> param);

    public Integer insertTeachplan(Teachplan teachplan);

    public Integer updateTeachplan(Teachplan teachplan);

    public Integer delTeachplanByCourseId(String id);


}
