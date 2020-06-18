package com.zb.mapper;


import com.zb.pojo.XcTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface XcTaskMapper {
    /**
     * 根据任务编号查询任务信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    public XcTask getXcTaskById(@Param(value = "id") String id) throws Exception;

    public List<XcTask> getXcTaskListByMap(Map<String, Object> param) throws Exception;

    public Integer getXcTaskCountByMap(Map<String, Object> param) throws Exception;

    public Integer insertXcTask(XcTask xcTask) throws Exception;

    /**
     * 修改任务信息
     *
     * @param xcTask
     * @return
     * @throws Exception
     */
    public Integer updateXcTask(XcTask xcTask) throws Exception;

    /**
     * 查询一分钟之后的任务
     *
     * @return
     * @throws Exception
     */
    public List<XcTask> findByUpdateTimeBefore() throws Exception;

    /**
     * 乐观锁,修改版本号
     *
     * @param xcTask
     * @return
     * @throws Exception
     */
    public int updateTaskVersion(XcTask xcTask) throws Exception;

    /**
     * 删除记录表中的数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public int deleteTask(@Param(value = "id") String id) throws Exception;

}
