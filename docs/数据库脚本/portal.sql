/*
Navicat MySQL Data Transfer

Source Server         : 47.97.19.223
Source Server Version : 100121
Source Host           : 47.97.19.223:3306
Source Database       : portal

Target Server Type    : MYSQL
Target Server Version : 100121
File Encoding         : 65001

Date: 2018-01-17 18:23:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for portal_contract
-- ----------------------------
DROP TABLE IF EXISTS `portal_contract`;
CREATE TABLE `portal_contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '合同表主键',
  `event_id` int(11) DEFAULT NULL COMMENT '事件唯一标识',
  `company_name` varchar(30) DEFAULT NULL COMMENT '公司名称',
  `contract_type` varchar(30) DEFAULT NULL COMMENT '合同类型',
  `apply_user_name` varchar(30) DEFAULT NULL COMMENT '申请人',
  `apply_dept` varchar(30) DEFAULT NULL COMMENT '申请部门',
  `clauseItem_name` varchar(30) DEFAULT NULL COMMENT '款项名称',
  `item_name` varchar(30) DEFAULT NULL COMMENT '项目名称',
  `contract_code` varchar(30) DEFAULT NULL COMMENT '合同编号',
  `contract_name` varchar(30) DEFAULT NULL COMMENT '合同名称',
  `jia_company` varchar(30) DEFAULT NULL COMMENT '甲方单位',
  `yi_company` varchar(30) DEFAULT NULL COMMENT '乙方单位',
  `bing_company` varchar(30) DEFAULT NULL COMMENT '丙方单位',
  `contract_money` varchar(30) DEFAULT NULL COMMENT '合同金额',
  `pay_money` varchar(30) DEFAULT NULL COMMENT '结算金额',
  `total_pay_money` varchar(30) DEFAULT NULL COMMENT '累计已付金额',
  `apply_money` varchar(30) DEFAULT NULL COMMENT '申请金额',
  `pay_cause` varchar(30) DEFAULT NULL COMMENT '付款事由',
  `pay_way` varchar(30) DEFAULT NULL COMMENT '结算方式',
  `real_pay_money` varchar(30) DEFAULT NULL COMMENT '实付金额',
  `pay_money_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '付款日期',
  `apply_date` datetime DEFAULT NULL COMMENT '申请时间',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否删除（0-未删除；1-已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for portal_department
-- ----------------------------
DROP TABLE IF EXISTS `portal_department`;
CREATE TABLE `portal_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门表主键',
  `name` varchar(32) NOT NULL COMMENT '部门名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `employee_id` int(11) DEFAULT NULL COMMENT '员工表主键',
  `is_delete` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除；1-已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for portal_employee
-- ----------------------------
DROP TABLE IF EXISTS `portal_employee`;
CREATE TABLE `portal_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工信息主键',
  `user_name` varchar(32) NOT NULL COMMENT '员工登录系统用户名，用户在三个系统的唯一标识',
  `password` varchar(32) NOT NULL COMMENT '员工登录系统密码',
  `name` varchar(32) DEFAULT NULL COMMENT '员工姓名',
  `mobile` varchar(32) DEFAULT NULL COMMENT '员工手机号',
  `department_id` int(11) DEFAULT NULL COMMENT '员工所在部门',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除；1-已删除）',
  `token` varchar(64) DEFAULT NULL COMMENT '单点登录token',
  `expire_time` bigint(8) DEFAULT NULL COMMENT '单点登录超时时间',
  `is_admin` int(4) DEFAULT '0' COMMENT '是否管理员（0-否；1-是）',
  PRIMARY KEY (`id`),
  KEY `fk_emp_dept` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for portal_event
-- ----------------------------
DROP TABLE IF EXISTS `portal_event`;
CREATE TABLE `portal_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `event_code` varchar(50) NOT NULL COMMENT '事件唯一标识',
  `title` varchar(32) DEFAULT NULL COMMENT '事件标题',
  `user_name` varchar(50) DEFAULT NULL COMMENT '申请事件的员工唯一标识，三个系统之间员工的唯一标识',
  `apply_time` datetime DEFAULT NULL COMMENT '申请事件时间',
  `operation_time` datetime DEFAULT NULL COMMENT '事件信息最新操作时间',
  `system_code` varchar(50) DEFAULT NULL COMMENT '系统编码（001-业务系统；002-财务系统）',
  `apply_url` varchar(50) DEFAULT NULL COMMENT '申请链接地址',
  `event_status` int(4) DEFAULT '1' COMMENT '事件状态编码(1-未完成 默认值，2-完成)',
  `is_pass` int(4) DEFAULT '0' COMMENT '-1表示未通过 0表示初始值 1表示通过',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否删除（0-未删除；1-已删除）',
  `update_times` int(4) DEFAULT '0' COMMENT '申请修改的次数',
  `read_status` int(4) DEFAULT '0' COMMENT '事件阅读状态，0表示未读(默认)，1表示已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for portal_event_message
-- ----------------------------
DROP TABLE IF EXISTS `portal_event_message`;
CREATE TABLE `portal_event_message` (
  `mess_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息表主键id',
  `event_stream_id` int(11) DEFAULT NULL COMMENT '待办事件流唯一标识',
  `mess_title` varchar(100) DEFAULT NULL COMMENT '消息标题',
  `mess_url` varchar(100) DEFAULT NULL COMMENT '消息链接地址',
  `mess_date` datetime DEFAULT NULL COMMENT '发消息时间',
  `user_name` varchar(50) DEFAULT NULL COMMENT '接收人唯一标志',
  `mess_status` int(4) DEFAULT '0' COMMENT '消息状态（0-未读（默认）；1-已读）',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否删除（0-未删除（默认）；1-已删除）',
  PRIMARY KEY (`mess_id`)
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for portal_event_stream
-- ----------------------------
DROP TABLE IF EXISTS `portal_event_stream`;
CREATE TABLE `portal_event_stream` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '事件流主键',
  `event_id` int(11) DEFAULT NULL COMMENT '关联事件id',
  `system_code` varchar(50) DEFAULT NULL COMMENT '系统标识，用于表示该事件所在系统信息',
  `operation_time` datetime DEFAULT NULL COMMENT '事件操作时间',
  `user_name` varchar(50) DEFAULT NULL COMMENT '接收者用户编码，三个系统之间唯一标识员工信息',
  `approve_result` int(11) DEFAULT '0' COMMENT '1-通过，2-不通过',
  `response_desc` text COMMENT '反馈描述',
  `approve_url` varchar(100) DEFAULT NULL COMMENT '审批链接地址/或者需要申请人修改链接地址',
  `read_code` int(4) DEFAULT '0' COMMENT '事件流阅读状态(0表示未读，1表示已读)',
  `deal_code` int(4) DEFAULT '0' COMMENT '处理状态0表示未处理(默认),1表示已处理',
  `backlog_id` varchar(11) DEFAULT NULL COMMENT '待办事件唯一标识',
  PRIMARY KEY (`id`),
  KEY `fk_event` (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for portal_navi
-- ----------------------------
DROP TABLE IF EXISTS `portal_navi`;
CREATE TABLE `portal_navi` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '导航主键',
  `name` varchar(32) NOT NULL COMMENT '导航名称',
  `url` varchar(32) NOT NULL COMMENT 'portal链接财务和导航的链接地址',
  `department_id` int(11) DEFAULT NULL COMMENT '部门id',
  `create_time` datetime DEFAULT NULL COMMENT '导航创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '导航更改时间',
  `employee_id` int(11) DEFAULT NULL COMMENT '操作人id',
  `is_delete` tinyint(2) NOT NULL DEFAULT '0' COMMENT '导航状态信息（0表示删除；1表示未删除）',
  PRIMARY KEY (`id`),
  KEY `fk_navi_dept` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for portal_navi_emp
-- ----------------------------
DROP TABLE IF EXISTS `portal_navi_emp`;
CREATE TABLE `portal_navi_emp` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工导航关联表主键',
  `employee_id` int(11) NOT NULL COMMENT '员工表主键',
  `navi_id` int(11) NOT NULL COMMENT '导航表主键',
  `is_delete` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除；1-已删除）',
  PRIMARY KEY (`id`),
  KEY `fk_empnavi_emp` (`employee_id`),
  KEY `fk_empnavi_navi` (`navi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for portal_panel
-- ----------------------------
DROP TABLE IF EXISTS `portal_panel`;
CREATE TABLE `portal_panel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '面板表主键',
  `name` varchar(32) NOT NULL COMMENT '面板名称',
  `department_id` int(11) DEFAULT NULL COMMENT '部门表主键',
  `yaxis` varchar(32) NOT NULL COMMENT 'y轴坐标（1提交率2成功率3修改率）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `employee_id` int(11) DEFAULT NULL COMMENT '操作人主键',
  `is_delete` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除1-已删除）',
  `inco_url` varchar(258) NOT NULL COMMENT '图标地址',
  `panel_type` int(4) DEFAULT '1' COMMENT '1表示折线图,2表示柱状体',
  PRIMARY KEY (`id`),
  KEY `fk_panel_dept` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for portal_panel_emp
-- ----------------------------
DROP TABLE IF EXISTS `portal_panel_emp`;
CREATE TABLE `portal_panel_emp` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工面板关联表主键',
  `employee_id` int(11) NOT NULL COMMENT '员工主键',
  `panel_id` int(11) NOT NULL COMMENT '面板表主键',
  `location` int(11) NOT NULL DEFAULT '0' COMMENT '图标位置信息',
  `is_delete` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除；1-以删除）',
  PRIMARY KEY (`id`),
  KEY `fk_panel_emp_emp` (`employee_id`),
  KEY `fk_pael_emp_panel` (`panel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
