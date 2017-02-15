/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50530
Source Host           : localhost:3306
Source Database       : icommunity

Target Server Type    : MYSQL
Target Server Version : 50530
File Encoding         : 65001

Date: 2016-08-31 20:41:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `comment_user` int(11) NOT NULL COMMENT '用户ID',
  `comment_topic` int(11) NOT NULL COMMENT '话题ID',
  `comment_time` datetime NOT NULL COMMENT '评论时间',
  `comment_content` text NOT NULL COMMENT '评论内容',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------

-- ----------------------------
-- Table structure for tb_community
-- ----------------------------
DROP TABLE IF EXISTS `tb_community`;
CREATE TABLE `tb_community` (
  `community_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '小区表ID',
  `community_location` varchar(100) NOT NULL COMMENT '小区的详细地址',
  `community_name` varchar(30) NOT NULL COMMENT '小区名称',
  `community_regitime` datetime NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`community_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_community
-- ----------------------------

-- ----------------------------
-- Table structure for tb_complains
-- ----------------------------
DROP TABLE IF EXISTS `tb_complains`;
CREATE TABLE `tb_complains` (
  `complains_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '投诉ID',
  `complains_userid` int(11) NOT NULL COMMENT '用户ID',
  `complains_title` varchar(50) NOT NULL COMMENT '投诉标题',
  `complains_content` text NOT NULL COMMENT '投诉内容',
  `complains_datetime` datetime NOT NULL COMMENT '投诉时间',
  `complains_phone` varchar(15) NOT NULL COMMENT '用户手机号',
  `complains_replycontent` text COMMENT '管理员回复内容',
  `complains_replytime` datetime DEFAULT NULL COMMENT '管理员回复时间',
  PRIMARY KEY (`complains_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_complains
-- ----------------------------

-- ----------------------------
-- Table structure for tb_financial
-- ----------------------------
DROP TABLE IF EXISTS `tb_financial`;
CREATE TABLE `tb_financial` (
  `financial_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '统计表ID',
  `financial_userid` int(11) NOT NULL COMMENT '用户ID',
  `financial_funds` varchar(20) NOT NULL COMMENT '支出款项',
  `financial_money` double NOT NULL COMMENT '支出金额',
  `financial_datetime` datetime NOT NULL COMMENT '支出时间',
  PRIMARY KEY (`financial_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_financial
-- ----------------------------

-- ----------------------------
-- Table structure for tb_house
-- ----------------------------
DROP TABLE IF EXISTS `tb_house`;
CREATE TABLE `tb_house` (
  `house_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '房屋ID',
  `community_id` int(11) NOT NULL COMMENT '小区ID',
  `user_id` int(11) NOT NULL COMMENT '业主ID',
  `house_picture` varchar(120) NOT NULL COMMENT '房屋平面图',
  `house_area` double NOT NULL COMMENT '房屋面积',
  `house_preprice` double NOT NULL COMMENT '每平方米价格',
  `house_price` double NOT NULL COMMENT '售价',
  PRIMARY KEY (`house_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_house
-- ----------------------------

-- ----------------------------
-- Table structure for tb_notify
-- ----------------------------
DROP TABLE IF EXISTS `tb_notify`;
CREATE TABLE `tb_notify` (
  `notify_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '通知表ID',
  `notify_userid` int(11) NOT NULL COMMENT '用户ID',
  `notify_titile` varchar(20) NOT NULL COMMENT '标题',
  `notify_content` text NOT NULL COMMENT '正文',
  `notify_datetime` datetime NOT NULL COMMENT '发布时间',
  PRIMARY KEY (`notify_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_notify
-- ----------------------------

-- ----------------------------
-- Table structure for tb_payment
-- ----------------------------
DROP TABLE IF EXISTS `tb_payment`;
CREATE TABLE `tb_payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '缴费表ID',
  `payment_type` varchar(20) NOT NULL COMMENT '缴费类型',
  `payment_starttime` datetime NOT NULL COMMENT '费用产生起始时间',
  `payment_endtime` datetime NOT NULL COMMENT '费用产生结束时间',
  `payment_userid` int(11) NOT NULL COMMENT '用户ID(',
  `payment_complettime` datetime DEFAULT NULL COMMENT '缴费完成时间',
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_payment
-- ----------------------------

-- ----------------------------
-- Table structure for tb_paymenttype
-- ----------------------------
DROP TABLE IF EXISTS `tb_paymenttype`;
CREATE TABLE `tb_paymenttype` (
  `paymenttype_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '缴费表类型ID',
  `paymenttype_name` varchar(20) NOT NULL COMMENT '缴费类型名',
  `paymenttype_money` double NOT NULL COMMENT '缴费单价',
  `paymenttype_units` double NOT NULL COMMENT '缴费单位数量',
  PRIMARY KEY (`paymenttype_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_paymenttype
-- ----------------------------

-- ----------------------------
-- Table structure for tb_praise
-- ----------------------------
DROP TABLE IF EXISTS `tb_praise`;
CREATE TABLE `tb_praise` (
  `praise_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '点赞ID',
  `praise_userid` int(11) NOT NULL,
  `praise_topicid` int(11) NOT NULL,
  PRIMARY KEY (`praise_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_praise
-- ----------------------------

-- ----------------------------
-- Table structure for tb_repair
-- ----------------------------
DROP TABLE IF EXISTS `tb_repair`;
CREATE TABLE `tb_repair` (
  `repair_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自助报修ID',
  `repair_userid` int(11) NOT NULL COMMENT '用户ID',
  `repair_project` varchar(50) NOT NULL COMMENT '报修项目',
  `repair_decldatatime` datetime NOT NULL COMMENT '报修时间',
  `repair_starttime` datetime NOT NULL COMMENT '预期上门维修起始时间',
  `repair_endtime` datetime NOT NULL COMMENT '预期上门维修结束时间',
  `repair_completetime` datetime DEFAULT NULL COMMENT '维修完成时间',
  PRIMARY KEY (`repair_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_repair
-- ----------------------------

-- ----------------------------
-- Table structure for tb_topic
-- ----------------------------
DROP TABLE IF EXISTS `tb_topic`;
CREATE TABLE `tb_topic` (
  `topic_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '话题ID',
  `topic_title` varchar(50) DEFAULT NULL COMMENT '标题',
  `topic_comment` text NOT NULL COMMENT '内容',
  `topic_type` int(11) NOT NULL COMMENT '话题类型',
  `topic_userid` int(11) NOT NULL COMMENT '用户ID(外键)',
  `topic_datetime` datetime NOT NULL COMMENT '发布时间',
  `topicpicture_path` varchar(100) DEFAULT NULL COMMENT '图片路径（自定义界定符）',
  PRIMARY KEY (`topic_id`),
  KEY `fk_topic_topictype` (`topic_type`),
  KEY `fk_topic_user` (`topic_userid`),
  CONSTRAINT `fk_topic_user` FOREIGN KEY (`topic_userid`) REFERENCES `tb_user` (`user_id`),
  CONSTRAINT `fk_topic_topictype` FOREIGN KEY (`topic_type`) REFERENCES `tb_topictype` (`topictype_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_topic
-- ----------------------------

-- ----------------------------
-- Table structure for tb_topictype
-- ----------------------------
DROP TABLE IF EXISTS `tb_topictype`;
CREATE TABLE `tb_topictype` (
  `topictype_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '话题类型ID',
  `topictype_name` varchar(20) NOT NULL COMMENT '话题类型名',
  PRIMARY KEY (`topictype_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_topictype
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_account` int(11) NOT NULL COMMENT '账号',
  `user_password` varchar(20) NOT NULL COMMENT '密码',
  `user_card` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `user_sex` varchar(15) DEFAULT NULL COMMENT '性别',
  `user_realname` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `user_nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  `user_email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `user_head` varchar(100) DEFAULT NULL COMMENT '头像',
  `user_type` int(11) DEFAULT NULL COMMENT '角色类型',
  `user_landstate` int(11) DEFAULT NULL COMMENT '登陆状态',
  `user_community` int(11) DEFAULT NULL COMMENT '小区',
  `user_house` int(11) DEFAULT NULL COMMENT '房屋',
  `user_age` int(11) DEFAULT NULL COMMENT '年龄',
  `user _registertime` datetime NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`user_id`),
  KEY `fk_user_usertype` (`user_type`),
  KEY `fk_user_community` (`user_community`),
  KEY `fk_user_house` (`user_house`),
  CONSTRAINT `fk_user_house` FOREIGN KEY (`user_house`) REFERENCES `tb_house` (`house_id`),
  CONSTRAINT `fk_user_community` FOREIGN KEY (`user_community`) REFERENCES `tb_community` (`community_id`),
  CONSTRAINT `fk_user_usertype` FOREIGN KEY (`user_type`) REFERENCES `tb_usertype` (`usertype_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------

-- ----------------------------
-- Table structure for tb_usertype
-- ----------------------------
DROP TABLE IF EXISTS `tb_usertype`;
CREATE TABLE `tb_usertype` (
  `usertype_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户类型ID',
  `usertype_name` varchar(25) NOT NULL COMMENT '类型名',
  PRIMARY KEY (`usertype_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_usertype
-- ----------------------------
