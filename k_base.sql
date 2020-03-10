/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : 127.0.0.1:3306
Source Database       : k_base

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2020-03-10 15:33:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for k_calendars
-- ----------------------------
DROP TABLE IF EXISTS `k_calendars`;
CREATE TABLE `k_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` int(11) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of k_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for k_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `k_fired_triggers`;
CREATE TABLE `k_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of k_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for k_locks
-- ----------------------------
DROP TABLE IF EXISTS `k_locks`;
CREATE TABLE `k_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of k_locks
-- ----------------------------

-- ----------------------------
-- Table structure for k_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `k_paused_trigger_grps`;
CREATE TABLE `k_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of k_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for k_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `k_scheduler_state`;
CREATE TABLE `k_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of k_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `DEPT_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `PARENT_ID` bigint(20) NOT NULL COMMENT '上级部门ID',
  `DEPT_NAME` varchar(100) NOT NULL COMMENT '部门名称',
  `ORDER_NUM` double(20,0) DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`DEPT_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES ('1', '0', '开发部', '1', '2018-01-04 15:42:26', '2019-01-05 21:08:27');
INSERT INTO `t_dept` VALUES ('2', '1', '开发一部1', '1', '2018-01-04 15:42:34', '2019-01-18 00:59:37');
INSERT INTO `t_dept` VALUES ('3', '1', '开发二部', '2', '2018-01-04 15:42:29', '2019-01-05 14:09:39');
INSERT INTO `t_dept` VALUES ('4', '0', '市场部', '2', '2018-01-04 15:42:36', '2019-01-23 06:27:56');
INSERT INTO `t_dept` VALUES ('5', '0', '人事部', '3', '2018-01-04 15:42:32', '2019-01-23 06:27:59');
INSERT INTO `t_dept` VALUES ('6', '0', '测试部', '4', '2018-01-04 15:42:38', '2019-01-17 08:15:47');

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict` (
  `DICT_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典ID',
  `KEYY` bigint(20) NOT NULL COMMENT '键',
  `VALUEE` varchar(100) NOT NULL COMMENT '值',
  `FIELD_NAME` varchar(100) NOT NULL COMMENT '字段名称',
  `TABLE_NAME` varchar(100) NOT NULL COMMENT '表名',
  PRIMARY KEY (`DICT_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES ('1', '0', '男', 'ssex', 't_user');
INSERT INTO `t_dict` VALUES ('2', '1', '女', 'ssex', 't_user');
INSERT INTO `t_dict` VALUES ('3', '2', '保密', 'ssex', 't_user');
INSERT INTO `t_dict` VALUES ('4', '1', '有效', 'status', 't_user');
INSERT INTO `t_dict` VALUES ('5', '0', '锁定', 'status', 't_user');
INSERT INTO `t_dict` VALUES ('6', '0', '菜单', 'type', 't_menu');
INSERT INTO `t_dict` VALUES ('7', '1', '按钮', 'type', 't_menu');
INSERT INTO `t_dict` VALUES ('30', '0', '正常', 'status', 't_job');
INSERT INTO `t_dict` VALUES ('31', '1', '暂停', 'status', 't_job');
INSERT INTO `t_dict` VALUES ('32', '0', '成功', 'status', 't_job_log');
INSERT INTO `t_dict` VALUES ('33', '1', '失败', 'status', 't_job_log');

-- ----------------------------
-- Table structure for t_job
-- ----------------------------
DROP TABLE IF EXISTS `t_job`;
CREATE TABLE `t_job` (
  `JOB_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `BEAN_NAME` varchar(100) NOT NULL COMMENT 'spring bean名称',
  `METHOD_NAME` varchar(100) NOT NULL COMMENT '方法名',
  `PARAMS` varchar(200) DEFAULT NULL COMMENT '参数',
  `CRON_EXPRESSION` varchar(100) NOT NULL COMMENT 'cron表达式',
  `STATUS` char(2) NOT NULL COMMENT '任务状态  0：正常  1：暂停',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`JOB_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_job
-- ----------------------------

-- ----------------------------
-- Table structure for t_job_log
-- ----------------------------
DROP TABLE IF EXISTS `t_job_log`;
CREATE TABLE `t_job_log` (
  `LOG_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `JOB_ID` bigint(20) NOT NULL COMMENT '任务id',
  `BEAN_NAME` varchar(100) NOT NULL COMMENT 'spring bean名称',
  `METHOD_NAME` varchar(100) NOT NULL COMMENT '方法名',
  `PARAMS` varchar(200) DEFAULT NULL COMMENT '参数',
  `STATUS` char(2) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `ERROR` text COMMENT '失败信息',
  `TIMES` decimal(11,0) DEFAULT NULL COMMENT '耗时(单位：毫秒)',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`LOG_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `USERNAME` varchar(50) DEFAULT NULL COMMENT '操作用户',
  `OPERATION` text COMMENT '操作内容',
  `TIME` decimal(11,0) DEFAULT NULL COMMENT '耗时',
  `METHOD` text COMMENT '操作方法',
  `PARAMS` text COMMENT '方法参数',
  `IP` varchar(64) DEFAULT NULL COMMENT '操作者IP',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `location` varchar(50) DEFAULT NULL COMMENT '操作地点',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES ('5', 'kk', '添加菜单', '1033', 'com.kk.novel.userInfo.controller.TMenuController.addMenu()', ' menu: \"com.kk.novel.userInfo.entity.TMenu@4ce87f36\"', '127.0.0.1', '2020-03-08 14:22:36', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('6', 'kk', '更新角色', '3059', 'com.kk.novel.userInfo.controller.TRoleController.updateRole()', ' roleId: \"1\" roleName: \"ROLE_ADMIN\" remark: \"管理员\" createTime: \"2019-12-27 00:00:00\" modifyTime: \"2019-01-23 06:45:29\"', '127.0.0.1', '2020-03-08 14:29:59', '内网IP|0|0|内网IP|内网IP');

-- ----------------------------
-- Table structure for t_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_login_log`;
CREATE TABLE `t_login_log` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(100) NOT NULL COMMENT '用户名',
  `LOGIN_TIME` datetime NOT NULL COMMENT '登录时间',
  `LOCATION` varchar(255) DEFAULT NULL COMMENT '登录地点',
  `IP` varchar(100) DEFAULT NULL COMMENT 'IP地址',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_login_log
-- ----------------------------
INSERT INTO `t_login_log` VALUES ('1', 'kk', '2020-03-10 02:48:00', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('2', 'asad', '2020-03-10 03:23:08', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('3', 'kk', '2020-03-10 03:49:29', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('4', 'cc', '2020-03-10 03:54:26', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('5', 'kk', '2020-03-10 03:56:20', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('6', 'ccs', '2020-03-10 03:57:59', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('7', 'ccs', '2020-03-10 04:04:07', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('8', 'kk', '2020-03-10 04:09:06', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('9', 'kk', '2020-03-10 05:51:45', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('10', 'kk', '2020-03-10 05:53:56', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('11', 'kk', '2020-03-10 06:03:11', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('12', 'kk', '2020-03-10 06:06:30', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('13', 'kk', '2020-03-10 06:07:58', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('14', 'kk', '2020-03-10 06:10:08', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('15', 'kk', '2020-03-10 06:11:07', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('16', 'kk', '2020-03-10 06:17:54', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('17', 'kk', '2020-03-10 06:19:20', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('18', 'kk', '2020-03-10 06:21:53', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('19', 'kk', '2020-03-10 06:23:49', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('20', 'kk', '2020-03-10 06:29:34', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('21', 'kk', '2020-03-10 06:31:08', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('22', 'kk', '2020-03-10 06:32:28', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('23', 'kk', '2020-03-10 06:33:13', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('24', 'kk', '2020-03-10 06:35:35', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('25', 'kk', '2020-03-10 06:36:07', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('26', 'kk', '2020-03-10 06:38:46', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('27', 'kk', '2020-03-10 06:40:18', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('28', 'kk', '2020-03-10 06:46:00', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('29', 'kk', '2020-03-10 07:05:52', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('30', 'kk', '2020-03-10 07:22:57', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('31', 'kk', '2020-03-10 07:23:42', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('32', 'kk', '2020-03-10 07:25:35', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('33', 'kk', '2020-03-10 07:27:30', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `MENU_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单/按钮ID',
  `PARENT_ID` bigint(20) NOT NULL COMMENT '上级菜单ID',
  `MENU_NAME` varchar(50) NOT NULL COMMENT '菜单/按钮名称',
  `PATH` varchar(255) DEFAULT NULL COMMENT '对应路由path',
  `COMPONENT` varchar(255) DEFAULT NULL COMMENT '对应路由组件component',
  `PERMS` varchar(50) DEFAULT NULL COMMENT '权限标识',
  `ICON` varchar(50) DEFAULT NULL COMMENT '图标',
  `TYPE` char(2) NOT NULL COMMENT '类型 0菜单 1按钮',
  `ORDER_NUM` double(20,0) DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `IS_SHOW` decimal(1,0) NOT NULL DEFAULT '1' COMMENT '是否展示',
  PRIMARY KEY (`MENU_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '0', '系统管理', '/system', 'EmptyPageView', null, 'el-icon-setting', '0', '1', '2020-02-25 11:08:58', '2020-03-03 15:31:08', '1');
INSERT INTO `t_menu` VALUES ('2', '1', '用户管理', '/system/user', 'system/user/User', 'user:view', 'el-icon-user-solid', '0', '1', '2020-02-25 11:16:21', '2020-03-03 15:52:25', '1');
INSERT INTO `t_menu` VALUES ('3', '1', '角色管理', '/system/role', 'system/role/Role', 'role:view', 'el-icon-user', '0', '2', '2020-02-25 11:17:38', '2020-03-03 17:54:52', '1');
INSERT INTO `t_menu` VALUES ('4', '1', '菜单管理', '/system/menu', 'system/menu/Menu', 'menu:view', 'el-icon-menu', '0', '3', '2020-02-25 11:18:38', null, '1');
INSERT INTO `t_menu` VALUES ('5', '1', '部门管理', '/system/dept', 'system/dept/Dept', 'dept:view', 'el-icon-s-flag\r\n', '0', '4', '2020-02-25 11:20:07', null, '1');
INSERT INTO `t_menu` VALUES ('6', '4', '新增菜单', null, null, 'menu:add', null, '1', null, '2020-02-28 16:30:53', null, '1');
INSERT INTO `t_menu` VALUES ('7', '4', '修改菜单', null, null, 'menu:update', null, '1', null, '2020-02-28 16:31:58', null, '1');
INSERT INTO `t_menu` VALUES ('8', '4', '删除菜单', null, null, 'menu:delete', null, '1', null, '2020-02-28 16:32:26', null, '1');
INSERT INTO `t_menu` VALUES ('9', '3', '新增角色', null, null, 'role:add', null, '1', null, '2020-02-28 16:33:29', null, '1');
INSERT INTO `t_menu` VALUES ('10', '3', '修改角色', null, null, 'role:update', null, '1', null, '2020-02-28 16:33:56', null, '1');
INSERT INTO `t_menu` VALUES ('11', '3', '删除角色', null, null, 'role:delete', null, '1', null, '2020-02-28 16:34:20', null, '1');
INSERT INTO `t_menu` VALUES ('12', '2', '新增用户', null, null, 'user:add', null, '1', null, '2020-02-28 16:35:00', '2020-03-03 18:25:01', '1');
INSERT INTO `t_menu` VALUES ('13', '2', '修改用户', null, null, 'user:update', null, '1', null, '2020-02-28 16:35:22', '2020-03-03 19:20:29', '1');
INSERT INTO `t_menu` VALUES ('14', '2', '删除用户', null, null, 'user:delete', null, '1', null, '2020-02-28 16:35:46', null, '1');
INSERT INTO `t_menu` VALUES ('15', '5', '新增部门', null, null, 'dept:add', null, '1', null, '2020-02-28 16:36:19', null, '1');
INSERT INTO `t_menu` VALUES ('16', '5', '修改部门', null, null, 'dept:update', null, '1', null, '2020-02-28 16:36:45', null, '1');
INSERT INTO `t_menu` VALUES ('17', '5', '删除部门', null, null, 'dept:delete', null, '1', null, '2020-02-28 16:37:17', null, '1');
INSERT INTO `t_menu` VALUES ('20', '0', '任务调度', '/job', 'EmptyPageView', null, 'el-icon-s-unfold', '0', '2', '2020-03-04 12:38:47', null, '1');
INSERT INTO `t_menu` VALUES ('21', '0', '系统监控', '/monitoring', 'EmptyPageView', null, 'el-icon-video-camera', '0', '3', '2020-03-04 12:39:44', null, '1');
INSERT INTO `t_menu` VALUES ('22', '21', '请求监控', '/monitor/log', 'monitor/httpRecord/HttpRecord', 'log:view', 'el-icon-date', '0', '1', '2020-03-08 14:22:35', null, '1');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `ROLE_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `ROLE_NAME` varchar(20) NOT NULL COMMENT '角色名称',
  `REMARK` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ROLE_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'ROLE_ADMIN', '管理员', '2019-12-27 00:00:00', '2020-03-08 14:29:56');
INSERT INTO `t_role` VALUES ('2', 'ROLE_REGISTER', '注册用户', '2019-01-04 14:11:28', '2020-03-05 10:41:59');
INSERT INTO `t_role` VALUES ('3', 'ROLE_GENERAL', '普通用户', '2019-01-23 07:33:20', '2020-03-05 13:44:50');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `ROLE_ID` bigint(20) NOT NULL,
  `MENU_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`MENU_ID`),
  KEY `ROLE_ID_INDEX` (`ROLE_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('1', '1');
INSERT INTO `t_role_menu` VALUES ('1', '2');
INSERT INTO `t_role_menu` VALUES ('1', '3');
INSERT INTO `t_role_menu` VALUES ('1', '4');
INSERT INTO `t_role_menu` VALUES ('1', '5');
INSERT INTO `t_role_menu` VALUES ('1', '6');
INSERT INTO `t_role_menu` VALUES ('1', '7');
INSERT INTO `t_role_menu` VALUES ('1', '8');
INSERT INTO `t_role_menu` VALUES ('1', '9');
INSERT INTO `t_role_menu` VALUES ('1', '10');
INSERT INTO `t_role_menu` VALUES ('1', '11');
INSERT INTO `t_role_menu` VALUES ('1', '12');
INSERT INTO `t_role_menu` VALUES ('1', '13');
INSERT INTO `t_role_menu` VALUES ('1', '14');
INSERT INTO `t_role_menu` VALUES ('1', '15');
INSERT INTO `t_role_menu` VALUES ('1', '16');
INSERT INTO `t_role_menu` VALUES ('1', '17');
INSERT INTO `t_role_menu` VALUES ('1', '20');
INSERT INTO `t_role_menu` VALUES ('1', '21');
INSERT INTO `t_role_menu` VALUES ('1', '22');
INSERT INTO `t_role_menu` VALUES ('2', '20');
INSERT INTO `t_role_menu` VALUES ('2', '21');
INSERT INTO `t_role_menu` VALUES ('3', '20');
INSERT INTO `t_role_menu` VALUES ('3', '21');

-- ----------------------------
-- Table structure for t_test
-- ----------------------------
DROP TABLE IF EXISTS `t_test`;
CREATE TABLE `t_test` (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `FIELD1` varchar(20) NOT NULL,
  `FIELD2` int(11) NOT NULL,
  `FIELD3` varchar(100) NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_test
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `USER_ID` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `USERNAME` varchar(50) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(128) NOT NULL COMMENT '密码',
  `DEPT_ID` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `EMAIL` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `MOBILE` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `STATUS` char(1) NOT NULL COMMENT '状态 0锁定 1有效',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `LAST_LOGIN_TIME` datetime DEFAULT NULL COMMENT '最近访问时间',
  `SSEX` char(1) DEFAULT NULL COMMENT '性别 0男 1女 2保密',
  `DESCRIPTION` varchar(100) DEFAULT NULL COMMENT '描述',
  `AVATAR` varchar(100) DEFAULT NULL COMMENT '用户头像',
  PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('13', 'kk', 'f379eaf3c831b04de153469d1bec345e', '1', '23214214', '1232132123', '1', '2020-02-02 00:00:00', '2020-03-10 11:38:18', null, '男', '', null);
INSERT INTO `t_user` VALUES ('14', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', null, null, '1', '2020-03-05 13:24:23', '2020-03-05 13:25:34', null, '女', null, null);
INSERT INTO `t_user` VALUES ('16', 'ccs', 'e10adc3949ba59abbe56e057f20f883e', '1', '2', '3213', '0', '2020-03-06 19:10:08', null, null, '男', null, null);
INSERT INTO `t_user` VALUES ('17', 'wewqe', 'e10adc3949ba59abbe56e057f20f883e', '4', 'wqe', null, '1', '2020-03-06 19:11:48', null, null, '女', null, null);
INSERT INTO `t_user` VALUES ('18', 'asad', 'e10adc3949ba59abbe56e057f20f883e', '4', null, null, '1', '2020-03-07 18:02:29', null, null, '男', null, null);

-- ----------------------------
-- Table structure for t_user_config
-- ----------------------------
DROP TABLE IF EXISTS `t_user_config`;
CREATE TABLE `t_user_config` (
  `USER_ID` bigint(20) NOT NULL COMMENT '用户ID',
  `THEME` varchar(10) DEFAULT NULL COMMENT '系统主题 dark暗色风格，light明亮风格',
  `LAYOUT` varchar(10) DEFAULT NULL COMMENT '系统布局 side侧边栏，head顶部栏',
  `MULTI_PAGE` char(1) DEFAULT NULL COMMENT '页面风格 1多标签页 0单页',
  `FIX_SIDERBAR` char(1) DEFAULT NULL COMMENT '页面滚动是否固定侧边栏 1固定 0不固定',
  `FIX_HEADER` char(1) DEFAULT NULL COMMENT '页面滚动是否固定顶栏 1固定 0不固定',
  `COLOR` varchar(20) DEFAULT NULL COMMENT '主题颜色 RGB值',
  PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_user_config
-- ----------------------------
INSERT INTO `t_user_config` VALUES ('1', 'light', 'side', '1', '1', '1', 'rgb(24, 144, 255)');
INSERT INTO `t_user_config` VALUES ('2', 'light', 'head', '0', '1', '1', 'rgb(24, 144, 255)');
INSERT INTO `t_user_config` VALUES ('12', 'dark', 'side', '1', '1', '1', 'rgb(66, 185, 131)');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `USER_ID` bigint(20) NOT NULL COMMENT '用户ID',
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`USER_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('2', '2');
INSERT INTO `t_user_role` VALUES ('12', '72');
INSERT INTO `t_user_role` VALUES ('13', '1');
INSERT INTO `t_user_role` VALUES ('17', '2');
INSERT INTO `t_user_role` VALUES ('18', '2');
