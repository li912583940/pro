/*
Navicat MySQL Data Transfer

Source Server         : 虚拟机mysql
Source Server Version : 50720
Source Host           : 192.168.1.91:3306
Source Database       : pro

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-01-03 16:26:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '公司名称',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `contacts1` varchar(50) DEFAULT NULL COMMENT '联系人1',
  `contact_phone1` varchar(20) DEFAULT NULL COMMENT '联系电话1',
  `contacts2` varchar(50) DEFAULT NULL COMMENT '联系人2',
  `contact_phone2` varchar(20) DEFAULT NULL COMMENT '联系电话2',
  `contacts3` varchar(50) DEFAULT NULL COMMENT '联系人3',
  `contact_phone3` varchar(20) DEFAULT NULL COMMENT '联系电话3',
  `contacts4` varchar(50) DEFAULT NULL COMMENT '联系人4',
  `contact_phone4` varchar(20) DEFAULT NULL COMMENT '联系电话4',
  `contacts5` varchar(50) DEFAULT NULL COMMENT '联系人5',
  `contact_phone5` varchar(20) DEFAULT NULL COMMENT '联系电话5',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_user_name` varchar(50) DEFAULT NULL COMMENT '创建人名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
