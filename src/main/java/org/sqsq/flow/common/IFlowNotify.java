package org.sqsq.flow.common;

import java.util.Map;

public interface IFlowNotify {
    void notify(Map<String, Object> message);
}