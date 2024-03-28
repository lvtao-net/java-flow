package org.sqsq.flow.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.sqsq.common.core.domain.BaseEntity;
/**
 * 日志对象 flow_task_log
 *
 * @author memory
 * @date 2024-03-10
 */
@Setter
@Getter
@ToString
public class FlowTaskLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 任务ID */
    private Long taskId;

    /** 操作类型 */
    private Long userId;

    /** 操作动作 */
    private String opAction;

    /** 操作节点 */
    private String taskNode;

}
