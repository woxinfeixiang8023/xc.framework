package com.zb.service;

import com.zb.pojo.TeachplanMedia;
import org.apache.ibatis.annotations.Param;

/**
 * @author XiaChuanKe
 * @Description TODO
 * @Date 2020/6/11
 * @Version V1.0
 */
public interface TeachplanMediaService {
    public TeachplanMedia getTeachplanMediaById(String teachplanId);
}
