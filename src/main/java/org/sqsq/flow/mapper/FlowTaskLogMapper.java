package org.sqsq.flow.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sqsq.flow.domain.FlowTaskLog;

import java.util.List;

/**
 * 日志Mapper接口
 *
 * @author memory
 * @date 2024-03-10
 */
@Mapper
public interface FlowTaskLogMapper
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
     * 删除日志
     *
     * @param id 日志主键
     * @return 结果
     */
    public int deleteFlowTaskLogById(Long id);

    /**
     * 批量删除日志
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFlowTaskLogByIds(Long[] ids);
}
