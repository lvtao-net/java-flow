package org.sqsq.flow.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sqsq.common.utils.DateUtils;
import org.sqsq.common.utils.StringUtils;
import org.sqsq.flow.common.IFlowUserManagement;
import org.sqsq.flow.constants.FlowTaskStatus;
import org.sqsq.flow.constants.FlowTaskType;
import org.sqsq.flow.domain.FlowTask;
import org.sqsq.flow.domain.FlowTaskLog;
import org.sqsq.flow.domain.vo.ConditionVo;
import org.sqsq.flow.domain.vo.TaskVo;
import org.sqsq.flow.domain.vo.WorkflowVo;
import org.sqsq.flow.service.IWorkflowService;
import org.sqsq.flow.utils.FlowJsonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class WorkflowServiceImpl implements IWorkflowService {
    @Autowired
    private FlowNotifyImpl notice;

    @Autowired
    private FlowTaskServiceImpl taskService;

    @Autowired
    private FlowTaskLogServiceImpl taskLogService;

    @Autowired
    private IFlowUserManagement userManagement;

    @Override
    public Long createTask(String flowName, String taskId, Map<String, Object> data) {
        FlowTask task = new FlowTask();
        task.setFlowName(flowName);
        task.setTaskId(taskId);
        task.setUserId(userManagement.getUserId());
        task.setNode(FlowTaskType.FLOW_START);
        task.setStatus(FlowTaskStatus.FLOW_WAIT);
        task.setData(JSONUtil.toJsonStr(data));
        taskService.insertFlowTask(task);
        Long id = task.getId();
        processTask(flowName, id, null, null);
        return id;
    }

    @Override
    public List<FlowTask> getTaskList(String flowName) {
        FlowTask where = new FlowTask();
        where.setFlowName(flowName);
        where.setUserId(userManagement.getUserId());
        return taskService.selectFlowTaskList(where);
    }

    @Override
    public List<FlowTask> getTodoList(String flowName) {
        return taskService.selectFlowTaskTodoList(flowName, userManagement.getUserId(), userManagement.getRole());
    }

    @Override
    public List<FlowTaskLog> getTaskLogList(Long taskId) {
        FlowTaskLog where = new FlowTaskLog();
        where.setTaskId(taskId);
        return taskLogService.selectFlowTaskLogList(where);
    }

    @Override
    public boolean processTask(String flowName, Long taskId, String command, String remark) {
        //加载配置文件
        WorkflowVo flow = FlowJsonUtils.loadJson(flowName);
        if(flow == null) return false;
        //加载任务
        FlowTask task = taskService.selectFlowTaskById(1L);
        if(task == null) return false; //数据不存在
        if(task.getStatus() != 0) return false; //任务已结束
        //获取到当前的节点 当前的节点是没有处理的
        TaskVo currentNode = flow.getTasks().get(task.getNode());
        if(currentNode == null) return false;
        String next = null;
        //此处判断当前节点是不是有集合
        if(StringUtils.isNotEmpty(currentNode.getApproval()) && currentNode.getApprovalNum() > 0)
        {
            FlowTaskLog logWhere = new FlowTaskLog();
            logWhere.setTaskId(task.getId());
            logWhere.setTaskNode(currentNode.getType());
            int logCount = taskLogService.countFlowTaskLog(logWhere);
            //日志数量如果小于当前的操作数量，则表示没达到操作条件
            if(logCount < currentNode.getApprovalNum()) return false;
        }
        //如果是动作操作 则判断命令
        if(Objects.equals(currentNode.getType(), FlowTaskType.FLOW_ACTION) || Objects.equals(currentNode.getType(), FlowTaskType.FLOW_AUTO)){
            if(StringUtils.isNotEmpty(command) && currentNode.getAction() != null && currentNode.getAction().containsKey(command)){
                //如果command与action中相符，则下个ID为它
                next = processNextTaskNode(flow.getTasks(), currentNode.getAction().get(command), task.getData());
            }else{
                next = processNextTaskNode(flow.getTasks(), currentNode.getNext(), task.getData());
            }
        }
        if(StringUtils.isEmpty(next)) return false; //如果下个节点计算出错，则退出
        task.setNode(next);
        //如果最后一个任务为结束，则需要结束这个任务状态
        if(Objects.equals(next, FlowTaskType.FLOW_END)){
            currentNode = flow.getTasks().get(FlowTaskType.FLOW_END);
            task.setStatus(FlowTaskStatus.FLOW_FINISH);//状态结束
        }
        if(Objects.equals(next, FlowTaskType.FLOW_START)){
            task.setStatus(FlowTaskStatus.FLOW_BACK);//状态表示退回
        }
        //如果任务的事件不为空，则发送事件通知
        if(StringUtils.isNotEmpty(currentNode.getEvent()))
        {
            Map<String, Object> message = new HashMap<>();
            message.put("flow", flowName);
            message.put("taskId", task.getTaskId());
            message.put("command", command);
            message.put("status", task.getStatus());
            message.put("data", task.getData());
            notice.sendMessage(currentNode.getEvent(), message);
        }
        task.setNextId(currentNode.getUser());
        task.setNextRole(currentNode.getRole());
        //更新数据
        if(taskService.updateFlowTask(task) > 0)
        {
            FlowTaskLog log = new FlowTaskLog();
            log.setTaskId(task.getId());
            log.setUserId(userManagement.getUserId());
            log.setOpAction(command);
            log.setCreateTime(DateUtils.getNowDateTime());;
            log.setRemark(remark);
            taskLogService.insertFlowTaskLog(log);
        }
        return true;
    }

    private String processNextTaskNode(Map<String, TaskVo> tasks, String node, String data){
        TaskVo next = tasks.get(node);
        if(Objects.equals(next.getType(), FlowTaskType.FLOW_CONDITION))
        {
            // 解析data 为 json数据
            JSONObject jsonObject = JSONUtil.parseObj(data);
            ConditionVo[] cs = next.getCondition();
            for (ConditionVo c: cs){
                if(FlowJsonUtils.evaluateCondition(jsonObject, c.getWhere())){
                    return c.getNode();
                }
            }
            return next.getNext();
        }
        return node;
    }
}
