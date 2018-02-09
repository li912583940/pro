/*
Navicat MySQL Data Transfer

Source Server         : 虚拟机mysql
Source Server Version : 50720
Source Host           : 192.168.1.91:3306
Source Database       : pro

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-01-05 17:25:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `project_file`
-- ----------------------------
DROP TABLE IF EXISTS `project_file`;
CREATE TABLE `project_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `path` varchar(150) DEFAULT NULL COMMENT '文件路径',
  `explain` varchar(500) DEFAULT NULL COMMENT '说明',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_user_name` varchar(50) DEFAULT NULL COMMENT '创建人名称',
  `file_size` int(11) DEFAULT NULL COMMENT '文件大小 单位KB',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project_file
-- ----------------------------
INSERT INTO `project_file` VALUES ('1', '6', 'f', 'fhfhfh', '2018-01-05 15:31:03', null, null, null);
