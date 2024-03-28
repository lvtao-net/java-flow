package org.sqsq.flow.utils;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.sqsq.flow.domain.vo.WorkflowVo;

import java.util.Map;

public class FlowJsonUtils {
    public static WorkflowVo loadJson(String name) {
        // 构建文件在classpath中的完整路径
        String path = "flow/" + name + ".flow.json";
        // 使用ResourceUtil读取文件为字符串
        String jsonStr = ResourceUtil.readUtf8Str(path);
        if (jsonStr == null) {
            throw new RuntimeException("未能加载JSON文件: " + path);
        }
        return JSONUtil.toBean(jsonStr, WorkflowVo.class);
    }

    public static boolean evaluateCondition(Map<String, Object> variables, String condition) {
        // 创建SpEL表达式解析器
        ExpressionParser parser = new SpelExpressionParser();
        // 创建评估上下文，并将变量添加到上下文中
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }

        // 替换表达式中的占位符为SpEL格式的变量引用
        String spielExpression = condition.replaceAll("\\$\\{(\\w+)}", "#$1");

        // 解析表达式，并根据给定的变量评估表达式的布尔值
        Boolean result = parser.parseExpression(spielExpression).getValue(context, Boolean.class);

        // 返回评估结果
        return result != null && result;
    }
}
