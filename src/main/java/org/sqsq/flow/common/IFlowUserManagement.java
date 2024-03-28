package org.sqsq.flow.common;

public interface IFlowUserManagement {
    //获取我当前登录的用户ID
    Long getUserId();

    String getRole();
}
