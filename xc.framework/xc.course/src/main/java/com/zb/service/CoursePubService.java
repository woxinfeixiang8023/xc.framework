package com.zb.service;

import com.rabbitmq.client.Channel;
import com.zb.dto.Page;
import com.zb.pojo.CoursePub;
import com.zb.util.PageUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.amqp.core.Message;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/6
 * @Version V1.0
 */
public interface CoursePubService {
    public PageUtil<CoursePub> searchCoursePub(String id, String name, String mt, String st, String grade, Integer isHot, Integer isNew, Integer isRec, Integer isTop, Integer index, Integer size) throws Exception;

    public void coursePubToRedis();

    public String qgCourse(String courseId, String token);

    public String qgWhile(String courseId, String token);

    public int checkRoomStock(String courseId);

    public void reviceQg(Map<String, Object> param, Message message, Channel channel);

    public int lockCourseStock(String courseId, String uid);

    public void recoverOrderMessage(Map<String, Object> param, Message message, Channel channel);
}
