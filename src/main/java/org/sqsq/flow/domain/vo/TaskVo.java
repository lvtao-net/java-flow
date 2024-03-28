package org.sqsq.flow.domain.vo;

import lombok.Data;

import java.util.Map;

@Data
public class TaskVo {
    private String type;
    private String approval;    //审批类型，如果是AND表示所选的人或部分领导需要全部通过，如果为OR则任为其一
    private Integer approvalNum; //审批类型条件
    private String previous;
    private String next;
    private String user;
    private String role;
    private String event;
    private Map<String, String> action;
    private ConditionVo[] condition;
}
