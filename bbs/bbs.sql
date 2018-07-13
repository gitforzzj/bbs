/*
Navicat MySQL Data Transfer

Source Server         : zheng
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : bbs

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2018-07-13 15:24:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `account` char(10) NOT NULL,
  `password` char(20) NOT NULL,
  `qx` int(4) NOT NULL,
  `nickName` char(10) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `photoPath` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '123', '0', '爱拼才会赢', 'zzf', 'seebook.jpg');

-- ----------------------------
-- Table structure for board
-- ----------------------------
DROP TABLE IF EXISTS `board`;
CREATE TABLE `board` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` char(20) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `parentId` int(4) DEFAULT NULL,
  `aid` int(4) NOT NULL,
  `boardImg` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `aid` (`aid`),
  KEY `parentId` (`parentId`),
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`aid`) REFERENCES `admin` (`id`),
  CONSTRAINT `board_ibfk_2` FOREIGN KEY (`parentId`) REFERENCES `board` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of board
-- ----------------------------
INSERT INTO `board` VALUES ('1', 'Java Web框架技术', '字符集', null, '1', null);
INSERT INTO `board` VALUES ('4', 'Java程序设计和JSP程序设计', '技术交流', null, '1', null);
INSERT INTO `board` VALUES ('5', 'Java程序设计', 'Java技术交流', '4', '1', '851699e398f444ada79e1ed481cce5c4.jpg');
INSERT INTO `board` VALUES ('6', 'Struts2', 'Struts2交流', '1', '1', 'struts.gif');
INSERT INTO `board` VALUES ('7', 'JSP程序设计', 'JSP技术交流', '4', '1', '32d8ff4e04422e974567e78b9972cb67.jpg');
INSERT INTO `board` VALUES ('8', 'Spring', 'Spring技术贴', '1', '1', 'Spring.GIF');
INSERT INTO `board` VALUES ('9', 'Hibernate', '数据库中间件', '1', '1', 'Hibernate.GIF');
INSERT INTO `board` VALUES ('10', '生活杂谈', null, null, '1', null);
INSERT INTO `board` VALUES ('11', '音乐空间', null, null, '1', null);
INSERT INTO `board` VALUES ('12', '校园生活', null, null, '1', null);
INSERT INTO `board` VALUES ('13', 'EJB与数据结构', null, null, '1', null);
INSERT INTO `board` VALUES ('14', '校园一角', null, '12', '1', 'ddda90162c2374678536bd978076a30f.jpeg');
INSERT INTO `board` VALUES ('15', '聊天交友', null, '10', '1', 'ed9ce074f9bda3896c26d994e83c6285.png');
INSERT INTO `board` VALUES ('16', '心情咖啡', null, '10', '1', '445ddcb6d86e0a34-9cea30ebc0a4e682-70a717f034f2529309d09b115f1c957a.jpg');
INSERT INTO `board` VALUES ('17', '动漫剧场', null, '10', '1', '6b2db66aff996c65d8289275461f1254.jpg');
INSERT INTO `board` VALUES ('18', '华语', null, '11', '1', '20a407512845fd63cd98e3a7ecc5d487.jpg');
INSERT INTO `board` VALUES ('19', '欧美', null, '11', '1', '97e664fb4ce291f518ac29185e74e82c.jpg');
INSERT INTO `board` VALUES ('20', '粤语', null, '11', '1', 'fa187f668302bded-7eb2983c4aa3f770-11a81763bd4b77a3d36b0415ff4784ca.jpg');
INSERT INTO `board` VALUES ('21', '法语', null, '11', '1', '1d53352b5eb8aeac-a896aab09c8f9f9c-93e041ab1e06db91951ae73cc9ea96cb.jpg');
INSERT INTO `board` VALUES ('22', '日语', null, '11', '1', '6fe645e4b5ae27fc5e72921e9cb45ee0.jpg');
INSERT INTO `board` VALUES ('23', '韩语', null, '11', '1', '50e16d6e627f525b6509bc75e4dc84ea.jpg');
INSERT INTO `board` VALUES ('24', 'TOMCAT', 'TOMCAT', null, '1', null);
INSERT INTO `board` VALUES ('25', 'MICRO', 'AAA', null, '1', null);
INSERT INTO `board` VALUES ('26', 'Apache', 'Apache', '24', '1', '74c13645e59863d2cef44a5432401f97.jpg');
INSERT INTO `board` VALUES ('27', 'EJB技术', '', '13', '1', '微信截图_20171214130644.png');
INSERT INTO `board` VALUES ('28', '微软粉丝', '微软实力派', '25', '1', '微信截图_20171214133227.png');
INSERT INTO `board` VALUES ('29', 'Jacarat', 'Jacarat 是 Apache旗下一个开源项目', '26', '1', null);
INSERT INTO `board` VALUES ('30', '数据结构', '', '13', '1', '微信截图_20171214130910.png');
INSERT INTO `board` VALUES ('36', 'SpringMVC', '一起学习springmvc吧', '1', '1', '64b9a803b9e04262fd8f2e846d27b3b4.jpg');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` char(40) NOT NULL,
  `content` longtext,
  `publishTime` datetime DEFAULT NULL,
  `sid` int(4) DEFAULT NULL,
  `bid` int(4) NOT NULL,
  `aid` int(4) DEFAULT NULL,
  `count` int(11) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sid` (`sid`),
  KEY `bid` (`bid`),
  KEY `aid` (`aid`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `student` (`id`),
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`bid`) REFERENCES `board` (`id`),
  CONSTRAINT `post_ibfk_3` FOREIGN KEY (`aid`) REFERENCES `admin` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('64', '学习java好榜样', '<p>\r\n	.................<br type=\"_moz\" />\r\n	55<br type=\"_moz\" />\r\n	578555<br type=\"_moz\" />\r\n	5454<br type=\"_moz\" />\r\n	4514<br type=\"_moz\" />\r\n	1<br type=\"_moz\" />\r\n	51<br type=\"_moz\" />\r\n	64865<br type=\"_moz\" />\r\n	166</p>\r\n', '2017-11-22 19:38:01', null, '5', '1', '00000000095');
INSERT INTO `post` VALUES ('76', '学习struts2。。。。。', '<p>\r\n	同学们&nbsp;456早餐</p>\r\n', '2017-11-22 22:47:37', null, '6', '1', '00000000041');
INSERT INTO `post` VALUES ('91', 'springcould', '<p>\r\n	dsasd</p>\r\n', '2017-11-23 13:41:08', null, '8', '1', '00000000017');
INSERT INTO `post` VALUES ('96', 'struts2 ognl', '<p>\r\n	很重要</p>\r\n', '2017-12-11 19:48:32', null, '6', '1', '00000000032');
INSERT INTO `post` VALUES ('97', 'springmvc', '一起来学习springmvc', '2017-12-14 00:41:24', '9', '36', null, '00000000031');
INSERT INTO `post` VALUES ('98', 'hibernate', 'hibernate是一个orm框架，原本需要面向关系数据库操作可以转化为对面向对象的操作', '2017-12-14 00:43:33', '9', '9', null, '00000000010');
INSERT INTO `post` VALUES ('102', '海贼王', '这一集的海贼王贼好看，路飞要逆天了', '2017-12-14 00:47:45', '7', '17', null, '00000000015');
INSERT INTO `post` VALUES ('103', '火影忍者博人传', '卡卡西对下忍考试实在是太严格，对博人啰嗦了一堆', '2017-12-14 00:52:36', '8', '17', null, '00000000000');
INSERT INTO `post` VALUES ('104', 'spring', 'spring是当下很热门的一个框架，学好赚大钱', '2017-12-14 00:53:29', '8', '8', null, '00000000000');
INSERT INTO `post` VALUES ('105', 'strut1', 'ffff\r<br>fdfs\r<br>fsfs\r<br>ddf ', '2017-12-17 16:35:09', null, '6', '1', '00000000040');
INSERT INTO `post` VALUES ('106', 'dasd', 'cascas', '2017-12-22 08:31:08', null, '6', '1', '00000000004');
INSERT INTO `post` VALUES ('107', '学习struts2', '学习学习', '2018-04-03 13:43:33', '8', '6', '1', '00000000001');

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `publishTime` datetime DEFAULT NULL,
  `pid` int(4) NOT NULL,
  `aid` int(4) DEFAULT NULL,
  `sid` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pid` (`pid`),
  KEY `aid` (`aid`),
  KEY `FK67612EAB9027BEB` (`sid`),
  CONSTRAINT `reply_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `post` (`id`),
  CONSTRAINT `reply_ibfk_2` FOREIGN KEY (`aid`) REFERENCES `admin` (`id`),
  CONSTRAINT `reply_ibfk_3` FOREIGN KEY (`sid`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('56', '二楼说的有道理', '2017-12-15 00:55:18', '64', null, '7');
INSERT INTO `reply` VALUES ('57', '好啊，goggo', '2017-12-15 00:55:43', '97', null, '7');
INSERT INTO `reply` VALUES ('58', 'fff\r\nggg', '2017-12-17 16:33:51', '102', null, null);
INSERT INTO `reply` VALUES ('60', 'rrr\r\nrrr\r\nfff\r\nff', '2017-12-17 16:34:11', '102', null, null);
INSERT INTO `reply` VALUES ('64', 'dasda\r\ndsaasd', '2017-12-17 17:19:21', '64', null, '9');
INSERT INTO `reply` VALUES ('65', 'ddsd\r\ndssdds', '2017-12-17 17:22:02', '64', null, '9');
INSERT INTO `reply` VALUES ('66', 'dsasd\r\nfdff', '2017-12-17 17:23:56', '64', null, '9');
INSERT INTO `reply` VALUES ('68', 'dsds\r<br/>fdfd\r<br/>fdefe', '2017-12-17 17:25:54', '64', null, '9');
INSERT INTO `reply` VALUES ('69', 'dasd\r<br/>dfss', '2017-12-17 17:28:07', '64', null, '9');
INSERT INTO `reply` VALUES ('70', 'dsads\r<br/>5465\r<br/>565', '2017-12-17 17:28:16', '64', null, '9');
INSERT INTO `reply` VALUES ('71', 'wicbshb\r<br/>dsaf', '2017-12-22 08:28:38', '98', null, '9');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `stuNum` char(15) NOT NULL,
  `realName` char(10) DEFAULT NULL,
  `nickName` char(20) DEFAULT NULL,
  `password` char(20) NOT NULL,
  `qq` char(20) DEFAULT NULL,
  `email` char(30) DEFAULT NULL,
  `major` char(20) DEFAULT NULL,
  `className` char(20) DEFAULT NULL,
  `photoPath` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('7', '201525040201', '陈总', 'erp陈', '123', '23333333', '23333333@qq.com', '信管', '2班', '32d8ff4e04422e974567e78b9972cb67.jpg');
INSERT INTO `student` VALUES ('8', '201525040229', '朱总', 'erp朱', '123', '12333333', '12333333@qq.com', '信管', '2班', '210afb06b90530cd220508697daf1ae6.jpg');
INSERT INTO `student` VALUES ('9', '123', '测试用户', '测试专用', '123', '123', '123@qq.com', '测试', '2班', '210afb06b90530cd220508697daf1ae6.jpg');
INSERT INTO `student` VALUES ('10', '111', 'dd52', 'dd', '111122', 'dddd', 'dddd', 'dd2dd', 'dd', '32d8ff4e04422e974567e78b9972cb67.jpg');
