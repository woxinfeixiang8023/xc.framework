package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.feign.XcTeacherFeignClient;
import com.zb.form.TeachplanForm;
import com.zb.mapper.CategoryMapper;
import com.zb.mapper.CoursePubMapper;
import com.zb.mapper.TeachplanMapper;
import com.zb.pojo.Category;
import com.zb.pojo.CoursePub;
import com.zb.pojo.Teachplan;
import com.zb.pojo.XcTeacher;
import com.zb.service.TeachplanService;
import com.zb.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/10
 * @Version V1.0
 */
@Service
public class TeachplanServiceImpl implements TeachplanService {

    @Autowired(required = false)
    private TeachplanMapper teachplanMapper;
    @Autowired(required = false)
    private CategoryMapper categoryMapper;
    @Autowired(required = false)
    private CoursePubMapper coursePubMapper;
    @Autowired
    private XcTeacherFeignClient xcTeacherFeignClient;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Teachplan> getTeachplanListByMap(String courseId, String grade, String parentId) {
        try {
            List<Teachplan> teachplanList = null;
            if (grade.equals("2")) {
                String key = "teachplanTwo:" + courseId;
                if (redisUtil.hasKey(key)) {
                    String json = redisUtil.get(key).toString();
                    teachplanList = JSON.parseArray(json, Teachplan.class);
                } else {
                    Map<String, Object> param = new HashMap<>();
                    param.put("courseid", courseId);
                    param.put("grade", grade);
                    teachplanList = teachplanMapper.getTeachplanListByMap(param);
                    redisUtil.set(key, JSON.toJSONString(teachplanList));
                }
                return teachplanList;
            } else if (grade.equals("3")) {
                String key = "teachplanThr" + parentId;
                if (redisUtil.hasKey(key)) {
                    String json = redisUtil.get(key).toString();
                    teachplanList = JSON.parseArray(json, Teachplan.class);
                } else {
                    Map<String, Object> param = new HashMap<>();
                    param.put("parentid", parentId);
                    param.put("grade", grade);
                    teachplanList = teachplanMapper.getTeachplanListByMap(param);
                    redisUtil.set(key, JSON.toJSONString(teachplanList));
                }
                return teachplanList;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TeachplanForm getTeachplanFormByCourseId(String courseId) {
        try {
            TeachplanForm teachplanForm = null;
            String key = "teachplanForm:" + courseId;
            if (redisUtil.hasKey(key)) {
                String json = redisUtil.get(key).toString();
                teachplanForm = JSON.parseObject(json, TeachplanForm.class);
            } else {
                CoursePub coursePub = coursePubMapper.getCoursePubById(courseId);
                Category mtCategory = categoryMapper.getCategoryById(coursePub.getMt());
                Category stCategory = categoryMapper.getCategoryById(coursePub.getSt());
                Map<String, Object> param = new HashMap<>();
                param.put("courseid", courseId);
                param.put("grade", 1);
                Teachplan teachplan = null;
                List<Teachplan> listByMap = teachplanMapper.getTeachplanListByMap(param);
                if (listByMap.size() > 0) {
                    teachplan = listByMap.get(0);
                }
                //根据教师coursePub的teacherId查询信息
                XcTeacher xcTeacher = xcTeacherFeignClient.getXcTeacherById(courseId);
                teachplanForm = new TeachplanForm(coursePub.getId(), coursePub.getName(), mtCategory.getName(), stCategory.getName(), coursePub.getGrade(), coursePub.getStudymodel(), coursePub.getDescription(), coursePub.getCharge(), coursePub.getValid(), coursePub.getPrice(), coursePub.getPriceOld(), coursePub.getExpires(), coursePub.getExpirationTime(), coursePub.getPic(), coursePub.getUsers(), coursePub.getStuUsers(), teachplan.getTimelength(), xcTeacher.getName(), xcTeacher.getIntro(), xcTeacher.getResume(), xcTeacher.getPic());
                redisUtil.set(key, JSON.toJSONString(teachplanForm));
            }
            return teachplanForm;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
