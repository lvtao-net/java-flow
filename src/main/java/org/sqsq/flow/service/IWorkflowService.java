package org.sqsq.flow.service;

import org.sqsq.flow.domain.FlowTask;
import org.sqsq.flow.domain.FlowTaskLog;

import java.util.List;
import java.util.Map;

public interface IWorkflowService {
    /**
     * 创建任务
     * @param flowName  流程json文件名称
     * @param taskId    业务ID
     * @param data      任务数据
     */
    public Long createTask(String flowName, String taskId, Map<String, Object> data);

    /**
     * 查询列表
     * @param flowName  流程名称
     */
    public List<FlowTask> getTaskList(String flowName);

    /**
     * 查询待办列表
     * @param flowName 流程名称
     */

    public List<FlowTask> getTodoList(String flowName);

    /**
     * 任务对应的日志
     * @param taskId 任务表中的ID
     */
    public List<FlowTaskLog> getTaskLogList(Long taskId);

    /**
     * 处理任务
     *
     * @param flowName 流程名称
     * @param taskId   任务ID
     * @param command  命令
     * @param remark   备注意见
     */
    public boolean processTask(String flowName, Long taskId, String command, String remark);
}
