/*
Navicat MySQL Data Transfer

Source Server         : 本都数据库
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : distribution

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-04-25 18:22:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `user_level` varchar(50) DEFAULT NULL COMMENT '用户等级 0消费者，1会员，2，初级合伙人 3，高级合伙人，4,分公司合伙人',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '上级id',
  `extract_money` decimal(30,18) DEFAULT NULL COMMENT '累计提现',
  `surplus_money` decimal(30,18) DEFAULT NULL COMMENT '待提现',
  `income_money` decimal(30,18) DEFAULT NULL COMMENT '累计收入',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_user_relation`;
CREATE TABLE `t_user_relation` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `child_id` varchar(32) DEFAULT NULL COMMENT '下级id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '上级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='当前用户上下级对应关系';

-- ----------------------------
-- Records of t_user_relation
-- ----------------------------
