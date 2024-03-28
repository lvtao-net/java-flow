package org.sqsq.flow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sqsq.flow.domain.FlowTask;
import org.sqsq.flow.mapper.FlowTaskMapper;
import org.sqsq.flow.service.IFlowTaskService;

import java.util.List;

/**
 * 任务Service业务层处理
 *
 * @author memory
 * @date 2024-03-10
 */
@Service
public class FlowTaskServiceImpl implements IFlowTaskService
{
    @Autowired
    private FlowTaskMapper flowTaskMapper;

    /**
     * 查询任务
     *
     * @param id 任务主键
     * @return 任务
     */
    @Override
    public FlowTask selectFlowTaskById(Long id)
    {
        return flowTaskMapper.selectFlowTaskById(id);
    }

    /**
     * 查询任务列表
     *
     * @param flowTask 任务
     * @return 任务
     */
    @Override
    public List<FlowTask> selectFlowTaskList(FlowTask flowTask)
    {
        return flowTaskMapper.selectFlowTaskList(flowTask);
    }

    public List<FlowTask> selectFlowTaskTodoList(String flow, Long userId, String role)
    {
        return flowTaskMapper.selectFlowTaskTodoList(flow, userId, role);
    }

    /**
     * 新增任务
     *
     * @param flowTask 任务
     * @return 结果
     */
    @Override
    public int insertFlowTask(FlowTask flowTask)
    {
        return flowTaskMapper.insertFlowTask(flowTask);
    }

    /**
     * 修改任务
     *
     * @param flowTask 任务
     * @return 结果
     */
    @Override
    public int updateFlowTask(FlowTask flowTask)
    {
        return flowTaskMapper.updateFlowTask(flowTask);
    }

    /**
     * 批量删除任务
     *
     * @param ids 需要删除的任务主键
     * @return 结果
     */
    @Override
    public int deleteFlowTaskByIds(Long[] ids)
    {
        return flowTaskMapper.deleteFlowTaskByIds(ids);
    }

    /**
     * 删除任务信息
     *
     * @param id 任务主键
     * @return 结果
     */
    @Override
    public int deleteFlowTaskById(Long id)
    {
        return flowTaskMapper.deleteFlowTaskById(id);
    }
}
