package org.sqsq.flow.service;

import org.sqsq.flow.domain.FlowTaskLog;

import java.util.List;

/**
 * 日志Service接口
 *
 * @author memory
 * @date 2024-03-10
 */
public interface IFlowTaskLogService
{
    /**
     * 查询日志
     *
     * @param id 日志主键
     * @return 日志
     */
    public FlowTaskLog selectFlowTaskLogById(Long id);

    /**
     * 查询日志列表
     *
     * @param flowTaskLog 日志
     * @return 日志集合
     */
    public List<FlowTaskLog> selectFlowTaskLogList(FlowTaskLog flowTaskLog);

    public Integer countFlowTaskLog(FlowTaskLog flowTaskLog);

    /**
     * 新增日志
     *
     * @param flowTaskLog 日志
     * @return 结果
     */
    public int insertFlowTaskLog(FlowTaskLog flowTaskLog);

    /**
     * 修改日志
     *
     * @param flowTaskLog 日志
     * @return 结果
     */
    public int updateFlowTaskLog(FlowTaskLog flowTaskLog);

    /**
     * 批量删除日志
     *
     * @param ids 需要删除的日志主键集合
     * @return 结果
     */
    public int deleteFlowTaskLogByIds(Long[] ids);

    /**
     * 删除日志信息
     *
     * @param id 日志主键
     * @return 结果
     */
    public int deleteFlowTaskLogById(Long id);
}
