package com.zb.mapper;

import com.zb.pojo.TeachplanMedia;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TeachplanMediaMapper {

	public TeachplanMedia getTeachplanMediaByTeachplanId(@Param(value = "teachplanId") String teachplanId)throws Exception;

	public List<TeachplanMedia>	getTeachplanMediaListByMap(Map<String,Object> param)throws Exception;

	public Integer getTeachplanMediaCountByMap(Map<String,Object> param)throws Exception;

	public Integer insertTeachplanMedia(TeachplanMedia teachplanMedia)throws Exception;

	public Integer updateTeachplanMedia(TeachplanMedia teachplanMedia)throws Exception;


}
