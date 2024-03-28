package org.sqsq.flow.service;

import org.sqsq.flow.domain.FlowTask;

import java.util.List;

/**
 * 任务Service接口
 *
 * @author memory
 * @date 2024-03-10
 */
public interface IFlowTaskService
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

    public List<FlowTask> selectFlowTaskTodoList(String flow, Long userId, String role);

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
     * 批量删除任务
     *
     * @param ids 需要删除的任务主键集合
     * @return 结果
     */
    public int deleteFlowTaskByIds(Long[] ids);

    /**
     * 删除任务信息
     *
     * @param id 任务主键
     * @return 结果
     */
    public int deleteFlowTaskById(Long id);
}
