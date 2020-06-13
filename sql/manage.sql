/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : manage

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 13/06/2020 22:13:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for d_authority
-- ----------------------------
DROP TABLE IF EXISTS `d_authority`;
CREATE TABLE `d_authority`  (
  `authority_id` int(20) NOT NULL AUTO_INCREMENT,
  `authority_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authority_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `menu_id` int(20) NOT NULL,
  PRIMARY KEY (`authority_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_authority
-- ----------------------------
INSERT INTO `d_authority` VALUES (3, NULL, 'b1', '2019-11-21 15:05:43', 0);
INSERT INTO `d_authority` VALUES (4, NULL, 'b2', '2019-11-21 15:05:43', 0);
INSERT INTO `d_authority` VALUES (5, NULL, 'b3', '2019-11-21 15:05:43', 0);
INSERT INTO `d_authority` VALUES (6, NULL, 'test:test', '2019-11-24 13:47:03', 0);
INSERT INTO `d_authority` VALUES (8, NULL, 'b2', '2019-12-21 02:55:49', 0);
INSERT INTO `d_authority` VALUES (11, NULL, 'b2', '2019-12-21 03:16:15', 0);
INSERT INTO `d_authority` VALUES (12, NULL, 'b3', '2019-12-21 03:16:15', 0);
INSERT INTO `d_authority` VALUES (13, NULL, 'b1', '2019-12-21 03:22:17', 0);
INSERT INTO `d_authority` VALUES (14, NULL, 'b2', '2019-12-21 03:22:17', 0);
INSERT INTO `d_authority` VALUES (15, NULL, 'b3', '2019-12-21 03:22:17', 0);

-- ----------------------------
-- Table structure for d_menu
-- ----------------------------
DROP TABLE IF EXISTS `d_menu`;
CREATE TABLE `d_menu`  (
  `menu_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单编码',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `p_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon_cls` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0-目录，1-菜单，2-按钮',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单地址',
  `authority` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `order_no` int(8) NULL DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_menu
-- ----------------------------
INSERT INTO `d_menu` VALUES (2, 'm_02', '系统管理', 'm_00', 'el-icon-setting', '1', '0', NULL, NULL, '2020-04-19 11:29:28', '2020-05-23 21:23:03', 2);
INSERT INTO `d_menu` VALUES (3, 'm_02_01', '菜单管理', 'm_02', 'el-icon-more-outline', '1', '1', 'menu/index', NULL, '2020-04-19 11:29:28', '2020-05-23 21:38:46', 1);
INSERT INTO `d_menu` VALUES (4, 'm_02_02', '权限管理', 'm_02', 'el-icon-pie-chart', '1', '1', 'authority/index', NULL, '2020-04-19 11:29:28', '2020-05-23 21:38:50', 2);
INSERT INTO `d_menu` VALUES (5, 'm_02_03', '角色管理', 'm_02', 'el-icon-pie-chart', '1', '1', 'role/index', NULL, '2020-04-19 11:29:28', '2020-05-23 21:38:56', 3);
INSERT INTO `d_menu` VALUES (7, 'm_04_05', 'BB三生三世', 'm_04', NULL, '1', '1', NULL, NULL, '2020-05-23 21:35:15', '2020-05-23 21:39:28', 1);
INSERT INTO `d_menu` VALUES (8, 'm_04_06', 'BB三生三世', 'm_04', NULL, '1', '1', NULL, NULL, '2020-05-23 21:35:15', '2020-05-23 21:39:28', 1);
INSERT INTO `d_menu` VALUES (9, 'm_04_07', 'BB三生三世', 'm_04', NULL, '1', '1', NULL, NULL, '2020-05-23 21:35:15', '2020-05-23 21:39:28', 1);
INSERT INTO `d_menu` VALUES (10, 'm_04_08', 'BB三生三世', 'm_04', NULL, '1', '1', NULL, NULL, '2020-05-23 21:35:15', '2020-05-23 21:39:28', 1);
INSERT INTO `d_menu` VALUES (11, 'm_04_09', 'BB三生三世', 'm_04', NULL, '1', '1', NULL, NULL, '2020-05-23 21:35:15', '2020-05-23 21:39:28', 1);
INSERT INTO `d_menu` VALUES (12, 'm_04_10', 'BBBB三生三世', 'm_04', NULL, '1', '1', NULL, NULL, '2020-05-23 21:35:15', '2020-05-23 21:39:28', 1);
INSERT INTO `d_menu` VALUES (54, 'm_03', '测试', 'm_00', 'el-icon-pie-chart', '1', '0', NULL, NULL, '2020-05-23 21:16:18', '2020-05-23 21:39:09', 3);
INSERT INTO `d_menu` VALUES (55, 'm_03_01', '测试01', 'm_03', NULL, '1', '1', NULL, NULL, '2020-05-23 21:16:54', NULL, NULL);
INSERT INTO `d_menu` VALUES (56, 'm_03_02', '测试02', 'm_03', NULL, '1', '1', NULL, NULL, '2020-05-23 21:17:28', NULL, NULL);
INSERT INTO `d_menu` VALUES (57, 'm_03_03', '测试03', 'm_03', NULL, '1', '1', NULL, NULL, '2020-05-23 21:18:14', NULL, NULL);
INSERT INTO `d_menu` VALUES (58, 'm_04', 'AA', 'm_00', 'el-icon-pie-chart', '1', '0', NULL, NULL, '2020-05-23 21:34:49', '2020-05-23 21:39:15', 4);
INSERT INTO `d_menu` VALUES (59, 'm_04_01', 'BB', 'm_04', NULL, '1', '1', NULL, NULL, '2020-05-23 21:35:15', '2020-05-23 21:39:28', 1);
INSERT INTO `d_menu` VALUES (60, 'm_04_02', 'CC', 'm_04', NULL, '1', '1', NULL, NULL, '2020-05-23 21:35:43', '2020-05-23 21:39:33', 2);
INSERT INTO `d_menu` VALUES (61, 'm_04_03', 'DD', 'm_04', NULL, '1', '1', NULL, NULL, '2020-05-23 21:36:06', '2020-05-23 21:39:40', 3);
INSERT INTO `d_menu` VALUES (62, 'm_02_04', '图标', 'm_02', NULL, '1', '1', 'icon/index', NULL, '2020-05-23 22:04:32', NULL, 4);
INSERT INTO `d_menu` VALUES (63, 'm_02_05', '用户管理', 'm_02', NULL, '1', '1', 'user/index', NULL, '2020-05-28 19:47:14', NULL, 5);
INSERT INTO `d_menu` VALUES (64, 'm_02_06', '调度任务', 'm_02', NULL, '0', '1', 'task/taskIndex', NULL, '2020-06-04 20:25:21', NULL, 6);
INSERT INTO `d_menu` VALUES (65, 'm_02_07', 'JOB管理', 'm_02', NULL, '1', '1', 'qrtzJobDetails/index', NULL, '2020-06-07 22:19:31', NULL, 7);

-- ----------------------------
-- Table structure for d_role
-- ----------------------------
DROP TABLE IF EXISTS `d_role`;
CREATE TABLE `d_role`  (
  `role_id` int(20) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_role
-- ----------------------------
INSERT INTO `d_role` VALUES (3, 'aa', '角色BB', '测试BB', '2019-11-21 15:05:43', '2020-05-25 23:13:45');
INSERT INTO `d_role` VALUES (5, 'BB2', 'BB2', '测试BB2', '2019-11-21 15:12:33', NULL);
INSERT INTO `d_role` VALUES (6, 'user2', 'user', '普通管理员', '2019-11-24 12:06:33', NULL);
INSERT INTO `d_role` VALUES (7, 'BB1', 'BB', '测试BB', '2019-12-21 02:55:49', NULL);
INSERT INTO `d_role` VALUES (9, 'user3', 'user', '普通管理员', NULL, NULL);
INSERT INTO `d_role` VALUES (10, 'BB3', 'BB', '测试BB', '2019-12-21 03:16:15', NULL);
INSERT INTO `d_role` VALUES (12, 'user', 'user', '普通管理员', NULL, NULL);
INSERT INTO `d_role` VALUES (13, 'BB4', 'BB', '测试BB', '2019-12-21 03:22:17', NULL);
INSERT INTO `d_role` VALUES (15, 'user1', 'user', '普通管理员', NULL, NULL);
INSERT INTO `d_role` VALUES (16, 'admin222', '管理员', '管理员', NULL, NULL);
INSERT INTO `d_role` VALUES (17, 'admin', 'sadasa', 'aa', '2020-05-03 09:19:17', NULL);
INSERT INTO `d_role` VALUES (18, 'AA', 'sdsd', 'ass', '2020-05-16 23:25:14', NULL);
INSERT INTO `d_role` VALUES (19, 'Test', 'Test', NULL, '2020-05-16 23:26:59', NULL);

-- ----------------------------
-- Table structure for d_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `d_role_authority`;
CREATE TABLE `d_role_authority`  (
  `role_id` int(20) NOT NULL,
  `authority_id` int(20) NOT NULL,
  INDEX `FK1ag0l8lslfkim49om05o638yc`(`authority_id`) USING BTREE,
  INDEX `FKp9j580fe02r7l7sa8pabhugg6`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_role_authority
-- ----------------------------
INSERT INTO `d_role_authority` VALUES (2, 2);
INSERT INTO `d_role_authority` VALUES (3, 3);
INSERT INTO `d_role_authority` VALUES (3, 4);
INSERT INTO `d_role_authority` VALUES (3, 5);
INSERT INTO `d_role_authority` VALUES (5, 3);
INSERT INTO `d_role_authority` VALUES (6, 6);
INSERT INTO `d_role_authority` VALUES (7, 7);
INSERT INTO `d_role_authority` VALUES (7, 8);
INSERT INTO `d_role_authority` VALUES (7, 9);
INSERT INTO `d_role_authority` VALUES (10, 10);
INSERT INTO `d_role_authority` VALUES (10, 11);
INSERT INTO `d_role_authority` VALUES (10, 12);
INSERT INTO `d_role_authority` VALUES (13, 13);
INSERT INTO `d_role_authority` VALUES (13, 14);
INSERT INTO `d_role_authority` VALUES (13, 15);

-- ----------------------------
-- Table structure for d_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `d_role_menu`;
CREATE TABLE `d_role_menu`  (
  `role_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `menu_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  INDEX `FK1yfc81jei0650fv60rrryxiou`(`menu_code`) USING BTREE,
  INDEX `FKmlkcbdiolcbo85s7snx3vl33g`(`role_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_role_menu
-- ----------------------------
INSERT INTO `d_role_menu` VALUES ('aa', 'm_02');
INSERT INTO `d_role_menu` VALUES ('aa', 'm_02_01');
INSERT INTO `d_role_menu` VALUES ('aa', 'm_02_02');
INSERT INTO `d_role_menu` VALUES ('aa', 'm_03');
INSERT INTO `d_role_menu` VALUES ('aa', 'm_03_01');
INSERT INTO `d_role_menu` VALUES ('aa', 'm_03_02');
INSERT INTO `d_role_menu` VALUES ('aa', 'm_03_03');
INSERT INTO `d_role_menu` VALUES ('aa', 'm_04');
INSERT INTO `d_role_menu` VALUES ('aa', 'm_04_02');

-- ----------------------------
-- Table structure for d_user
-- ----------------------------
DROP TABLE IF EXISTS `d_user`;
CREATE TABLE `d_user`  (
  `user_id` int(20) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_user
-- ----------------------------
INSERT INTO `d_user` VALUES (1, 'zs', 'zs', '81ed3986582624ccafad9f914f2863e8', '1', '2019-11-24 12:06:33', NULL);
INSERT INTO `d_user` VALUES (2, 'ls', '李四2', '9c67b6f71b1c4f7019c44fa38a631fe1', '1', '2020-05-28 20:57:59', '2020-05-28 21:45:07');

-- ----------------------------
-- Table structure for d_user_role
-- ----------------------------
DROP TABLE IF EXISTS `d_user_role`;
CREATE TABLE `d_user_role`  (
  `user_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  INDEX `FKcbp7fivs6uejh96ou0ktjblah`(`role_code`) USING BTREE,
  INDEX `FK70gmxlal7qy8uepbxuirqqk6f`(`user_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_user_role
-- ----------------------------
INSERT INTO `d_user_role` VALUES ('1', '6');
INSERT INTO `d_user_role` VALUES ('zs', 'admin222');
INSERT INTO `d_user_role` VALUES ('zs', 'user2');
INSERT INTO `d_user_role` VALUES ('zs', 'BB3');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TRIG_INST_NAME`(`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY`(`SCHED_NAME`, `INSTANCE_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_FT_J_G`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_T_G`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TG`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_J_REQ_RECOVERY`(`SCHED_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_J_GRP`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('quartzScheduler', 'testJob', 'DEFAULT', '测试12', 'com.lp.framework.manage.job.TestJob', '1', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('quartzScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int(11) NULL DEFAULT NULL,
  `INT_PROP_2` int(11) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(20) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(20) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(190) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_J`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_C`(`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_T_G`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_STATE`(`SCHED_NAME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_STATE`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_G_STATE`(`SCHED_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NEXT_FIRE_TIME`(`SCHED_NAME`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST`(`SCHED_NAME`, `TRIGGER_STATE`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
