package com.zb.mapper;

import com.zb.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CategoryMapper {

    public Category getCategoryById(@Param(value = "id") String id) throws Exception;

    public List<Category> getCategoryListByMap(Map<String, Object> param) throws Exception;

    public Integer getCategoryCountByMap(Map<String, Object> param) throws Exception;

    public Integer insertCategory(Category category) throws Exception;

    public Integer updateCategory(Category category) throws Exception;

    public Integer delCategoryById(@Param(value = "id") String id) throws Exception;

}
