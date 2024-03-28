package org.sqsq.flow.domain.vo;

import lombok.Data;

import java.util.Map;

@Data
public class WorkflowVo {
    private String name;
    private int version;
    private String desc;
    private Map<String, TaskVo> tasks;
}