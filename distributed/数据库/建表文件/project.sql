/*
Navicat MySQL Data Transfer

Source Server         : pro
Source Server Version : 50720
Source Host           : 192.168.1.102:3306
Source Database       : pro

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-01-01 23:41:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `project`
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '项目名称 如：晋中监狱会见系统',
  `project_type_id` int(11) DEFAULT NULL COMMENT '项目类别id',
  `customer_id` int(11) DEFAULT NULL COMMENT '客户id',
  `line_info` varchar(500) DEFAULT NULL COMMENT '线路信息',
  `hardware_info` varchar(500) DEFAULT NULL COMMENT '硬件信息',
  `software_info` varchar(500) DEFAULT NULL COMMENT '软件信息',
  `address` varchar(100) DEFAULT NULL COMMENT '项目实施地址',
  `province` varchar(10) DEFAULT NULL COMMENT '省',
  `city` varchar(20) DEFAULT NULL COMMENT '市',
  `district` varchar(20) DEFAULT NULL COMMENT '区',
  `implement_time` datetime DEFAULT NULL COMMENT '实施时间',
  `implement_user_id` int(11) DEFAULT NULL COMMENT '实施人id',
  `implement_user_name` varchar(20) DEFAULT NULL COMMENT '实施人姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_user_name` varchar(20) DEFAULT NULL COMMENT '创建人姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
