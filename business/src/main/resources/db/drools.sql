/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50637
 Source Host           : 127.0.0.1:3306
 Source Schema         : yueshen

 Target Server Type    : MySQL
 Target Server Version : 50637
 File Encoding         : 65001

 Date: 08/12/2017 10:38:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for td_rule
-- ----------------------------
DROP TABLE IF EXISTS `td_rule`;
CREATE TABLE `td_rule` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '编号',
  `name` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '规则名称',
  `code` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT 'code',
  `rule` text COLLATE utf8mb4_bin NOT NULL COMMENT '规则内容',
  `content` text COLLATE utf8mb4_bin NOT NULL COMMENT '规则内容json',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `flag_delete` int(11) DEFAULT NULL COMMENT '状态（1 删除  0  上线   7 下线）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='规则表';

-- ----------------------------
-- Records of td_rule
-- ----------------------------
BEGIN;
INSERT INTO `td_rule` VALUES ('2a7d621e119f41b89c945a21d6da2a3c', 'sdf', 'sdf', 'package drools.rules import java.util.Map import com.mrd.drools.business.fact.CheckResult  rule ruleName0 agenda-group \"sdf\"  when $map:  Map(get(\"name\")  contains \"xiong\") checkResult : CheckResult();  then checkResult.setResultStr(\"back\");  checkResult.getResultList().add(drools.getRule().getName());  end;', '[[{\"modifyTime\":1512525713000,\"data\":\"name\",\"createTime\":1512525713000,\"flagDelete\":0,\"name\":\"姓名\",\"id\":\"634b25e159694d4689db87ff3abf24a2\",\"type\":\"condition\"},{\"data\":\"contains\",\"name\":\"包含\",\"type\":\"sign\"},{\"data\":\"xiong\",\"name\":\"xiong\",\"type\":\"value\"},{\"modifyTime\":1512525759000,\"data\":\"back\",\"createTime\":1512525759000,\"flagDelete\":0,\"name\":\"打回\",\"id\":\"833cbcd8bb2644f39975fbaddf75c9bb\",\"type\":\"result\"}]]', '2017-12-06 16:00:13', '2017-12-08 09:55:49', 0);
COMMIT;

-- ----------------------------
-- Table structure for td_rule_column
-- ----------------------------
DROP TABLE IF EXISTS `td_rule_column`;
CREATE TABLE `td_rule_column` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '编号',
  `name` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `data` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '值',
  `type` int(11) DEFAULT NULL COMMENT '类型（1 条件  2 结果）',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `flag_delete` int(11) DEFAULT NULL COMMENT '状态（1 删除  0  上线   7 下线）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='规则列表';

-- ----------------------------
-- Records of td_rule_column
-- ----------------------------
BEGIN;
INSERT INTO `td_rule_column` VALUES ('634b25e159694d4689db87ff3abf24a2', '姓名', 'name', 1, '2017-12-06 10:01:53', '2017-12-08 09:49:36', 0);
INSERT INTO `td_rule_column` VALUES ('833cbcd8bb2644f39975fbaddf75c9bb', '打回', 'back', 2, '2017-12-06 10:02:39', '2017-12-06 10:02:39', 0);
INSERT INTO `td_rule_column` VALUES ('b23ef272295a4f9bba97a8b274c85c13', '拒件', 'refuse', 2, '2017-12-06 10:02:15', '2017-12-06 10:02:15', 0);
INSERT INTO `td_rule_column` VALUES ('d8a10f27ad964d2290f0a0e27ed5d001', '同盾分数', 'tdScore', 1, '2017-12-06 10:02:05', '2017-12-06 10:02:05', 0);
INSERT INTO `td_rule_column` VALUES ('ff5c28fd59844facb95136a77fae4c39', '年龄', 'age', 1, '2017-12-06 10:01:41', '2017-12-06 14:45:12', 0);
COMMIT;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '编号',
  `user_name` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `real_name` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '真实姓名',
  `avatar` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `sex` int(11) NOT NULL COMMENT '性别',
  `phone` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号',
  `id_card` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证',
  `department` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '部门',
  `role_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `flag_delete` int(11) DEFAULT NULL COMMENT '是否删除（1 删除  0  未删除）',
  PRIMARY KEY (`id`),
  KEY `USER_NAME_INDEX` (`user_name`) USING HASH,
  KEY `REAL_NAME_INDEX` (`real_name`) USING HASH,
  KEY `CREATE_TIME_INDEX` (`create_time`) USING HASH,
  KEY `FLAG_DELETE_INDEX` (`flag_delete`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户表';

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
INSERT INTO `user_info` VALUES ('1', 'xiongzy', '111111', '熊占远', 'http://oq50tshbr.bkt.clouddn.com/tms/image/364898f5a9db309a9e3560a52774ed96.jpg', 1, '', '12022419910213641X', '技术部', '管理员', '2017-10-04 12:43:03', '2017-10-04 12:43:08', 0);
INSERT INTO `user_info` VALUES ('7c2bbee802df42a7bffbe88466ae2730', 'ceshi1', '111111', '测试1', 'http://oq50tshbr.bkt.clouddn.com/tms/image/faea9aebd253c7058aeeb386edc1bf6d.jpeg', 0, NULL, '120224199102136411', '技术部', 'admin', NULL, NULL, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
