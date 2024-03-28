CREATE TABLE `flow_task_log` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `task_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '任务ID',
  `user_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '操作人',
  `task_node` varchar(32) NOT NULL DEFAULT '' COMMENT '操作节点',
  `op_action` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'start' COMMENT '操作动作',
  `create_time` datetime NOT NULL COMMENT '操作时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '意思',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `flow_task` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `flow_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '流程名称',
  `task_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '任务ID',
  `user_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '发起人ID',
  `next_id` varchar(250) NOT NULL DEFAULT '' COMMENT '下一执行人',
  `next_role` varchar(32) NOT NULL DEFAULT '' COMMENT '下一执行组',
  `node` varchar(32) NOT NULL DEFAULT 'start' COMMENT '当前节点',
  `status` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '当前状态',
  `data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '任务数据',
  PRIMARY KEY (`id`),
  KEY `mytask` (`flow_name`,`task_id`,`user_id`,`status`) USING BTREE,
  KEY `mytodo` (`flow_name`,`task_id`,`next_id`,`next_role`,`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
