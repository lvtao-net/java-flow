package org.sqsq.flow.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.sqsq.flow.common.IFlowNotify;

import java.util.Map;

/*通知其它模块当前业务状态信息*/
@Slf4j
@Service
public class FlowNotifyImpl{
    public void sendMessage(String target, Map<String,Object> message) {
        try {
            Class<?> clazz = Class.forName(target);
            IFlowNotify service = (IFlowNotify) clazz.newInstance();
            service.notify(message);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            log.error(e.getMessage());
        }
    }
}
