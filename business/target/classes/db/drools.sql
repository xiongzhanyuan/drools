#规则表
CREATE TABLE `td_rule` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '编号',
  `name` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '规则名称',
  `rule` text COLLATE utf8mb4_bin NOT NULL COMMENT '规则内容',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `flag_delete` int(11) DEFAULT NULL COMMENT '状态（1 删除  0  上线   7 下线）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='规则表';

#规则编辑表
CREATE TABLE `td_rule_column` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '编号',
  `name` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `data` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '值',
	 `type` int(11) DEFAULT NULL COMMENT '类型（1 条件  2 结果）',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `flag_delete` int(11) DEFAULT NULL COMMENT '状态（1 删除  0  上线   7 下线）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='规则编辑表';