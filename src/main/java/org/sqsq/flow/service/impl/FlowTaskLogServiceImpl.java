package org.sqsq.flow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sqsq.common.utils.DateUtils;
import org.sqsq.flow.domain.FlowTaskLog;
import org.sqsq.flow.mapper.FlowTaskLogMapper;
import org.sqsq.flow.service.IFlowTaskLogService;

import java.util.List;

/**
 * 日志Service业务层处理
 *
 * @author memory
 * @date 2024-03-10
 */
@Service
public class FlowTaskLogServiceImpl implements IFlowTaskLogService
{
    @Autowired
    private FlowTaskLogMapper flowTaskLogMapper;

    /**
     * 查询日志
     *
     * @param id 日志主键
     * @return 日志
     */
    @Override
    public FlowTaskLog selectFlowTaskLogById(Long id)
    {
        return flowTaskLogMapper.selectFlowTaskLogById(id);
    }

    /**
     * 查询日志列表
     *
     * @param flowTaskLog 日志
     * @return 日志
     */
    @Override
    public List<FlowTaskLog> selectFlowTaskLogList(FlowTaskLog flowTaskLog)
    {
        return flowTaskLogMapper.selectFlowTaskLogList(flowTaskLog);
    }

    public Integer countFlowTaskLog(FlowTaskLog flowTaskLog){
        return flowTaskLogMapper.countFlowTaskLog(flowTaskLog);
    }

    /**
     * 新增日志
     *
     * @param flowTaskLog 日志
     * @return 结果
     */
    @Override
    public int insertFlowTaskLog(FlowTaskLog flowTaskLog)
    {
        flowTaskLog.setCreateTime(DateUtils.getNowDateTime());;
        return flowTaskLogMapper.insertFlowTaskLog(flowTaskLog);
    }

    /**
     * 修改日志
     *
     * @param flowTaskLog 日志
     * @return 结果
     */
    @Override
    public int updateFlowTaskLog(FlowTaskLog flowTaskLog)
    {
        return flowTaskLogMapper.updateFlowTaskLog(flowTaskLog);
    }

    /**
     * 批量删除日志
     *
     * @param ids 需要删除的日志主键
     * @return 结果
     */
    @Override
    public int deleteFlowTaskLogByIds(Long[] ids)
    {
        return flowTaskLogMapper.deleteFlowTaskLogByIds(ids);
    }

    /**
     * 删除日志信息
     *
     * @param id 日志主键
     * @return 结果
     */
    @Override
    public int deleteFlowTaskLogById(Long id)
    {
        return flowTaskLogMapper.deleteFlowTaskLogById(id);
    }
}
