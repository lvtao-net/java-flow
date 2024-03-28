package org.sqsq.flow.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sqsq.flow.domain.FlowTask;

import java.util.List;

/**
 * 任务Mapper接口
 *
 * @author memory
 * @date 2024-03-10
 */
@Mapper
public interface FlowTaskMapper
{
    /**
     * 查询任务
     *
     * @param id 任务主键
     * @return 任务
     */
    public FlowTask selectFlowTaskById(Long id);

    /**
     * 查询任务列表
     *
     * @param flowTask 任务
     * @return 任务集合
     */
    public List<FlowTask> selectFlowTaskList(FlowTask flowTask);

    public List<FlowTask> selectFlowTaskTodoList(@Param("flow")String flow, @Param("userId") Long userId, @Param("role") String role);

    /**
     * 新增任务
     *
     * @param flowTask 任务
     * @return 结果
     */
    public int insertFlowTask(FlowTask flowTask);

    /**
     * 修改任务
     *
     * @param flowTask 任务
     * @return 结果
     */
    public int updateFlowTask(FlowTask flowTask);

    /**
     * 删除任务
     *
     * @param id 任务主键
     * @return 结果
     */
    public int deleteFlowTaskById(Long id);

    /**
     * 批量删除任务
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFlowTaskByIds(Long[] ids);
}
