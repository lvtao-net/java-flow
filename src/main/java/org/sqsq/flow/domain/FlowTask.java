package org.sqsq.flow.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.sqsq.common.core.domain.BaseEntity;
/**
 * 任务对象 flow_task
 *
 * @author memory
 * @date 2024-03-10
 */
@Setter
@Getter
@ToString
public class FlowTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 流程名称 */
    private String flowName;

    /** 任务ID */
    private String taskId;

    /** 发起人 */
    private Long userId;

    /** NEXT */
    private String nextId;

    /** ROLE */
    private String nextRole;

    /** 当前任务节点 */
    private String node;

    /** 状态 */
    private Integer status;

    /** 任务数据 */
    private String data;

}
