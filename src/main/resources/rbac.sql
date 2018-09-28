/*
Navicat MySQL Data Transfer

Source Server         : bj1804
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : rbac

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-09-28 16:28:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `MENU_ID` varchar(32) NOT NULL COMMENT ' 菜单ID',
  `MENU_NAME` varchar(128) DEFAULT NULL COMMENT ' 菜单名称',
  `PARENT_ID` varchar(32) DEFAULT NULL COMMENT '父ID',
  `LINK_URL` varchar(256) DEFAULT NULL COMMENT '对应地址',
  `STATUS` char(1) DEFAULT NULL COMMENT '状态0--可用  1--不可用',
  `ICON` varchar(32) DEFAULT NULL,
  `REMARK` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('F0', '用户', '0', '', 'Y', '&#xe620;', '');
INSERT INTO `sys_menu` VALUES ('F0001', '用户管理', 'F0', 'jsp/UserList.jsp', 'Y', '&#xe621;', '');
INSERT INTO `sys_menu` VALUES ('F0002', '角色管理', 'F0', 'jsp/RoleList.jsp', 'Y', '&#xe621;', null);
INSERT INTO `sys_menu` VALUES ('F0003', '在线用户', 'F0', 'jsp/OnLine.html', 'Y', '&#xe621;', null);
INSERT INTO `sys_menu` VALUES ('F1', 'LAYUI基础', '0', null, 'Y', '&#xe620', null);
INSERT INTO `sys_menu` VALUES ('F1001', '按钮', 'F1', 'demo/btn.html', 'Y', null, null);
INSERT INTO `sys_menu` VALUES ('F1002', '表单', 'F1', 'demo/form.html', 'Y', '&#xe621;', null);
INSERT INTO `sys_menu` VALUES ('F1003', '表格', 'F1', 'demo/table.html', 'Y', '&#xe621;', null);
INSERT INTO `sys_menu` VALUES ('F1004', '选项卡', 'F1', 'demo/tab-card.html', 'Y', '&#xe621;', null);

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ROLE_ID` varchar(64) NOT NULL COMMENT '角色ID',
  `ROLE_NAME` varchar(32) NOT NULL COMMENT '角色名称',
  `ROLE_DESC` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `ROLE_ACTIVE` int(1) NOT NULL COMMENT '是否可用  0---可用   1---不可用',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('0', '超级超级管理员', '哈哈', '0');
INSERT INTO `sys_role` VALUES ('1', 'CEO', 'CEO', '1');
INSERT INTO `sys_role` VALUES ('6246E4F5BCE4425DA1B072B4C7DF5762', 'hello', '中文', '0');
INSERT INTO `sys_role` VALUES ('A8247FD28E974286B8C96E0CCC3DDCDC', '经理', '		 打豆豆\r\n		', '0');

-- ----------------------------
-- Table structure for `sys_role_menu_ele`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu_ele`;
CREATE TABLE `sys_role_menu_ele` (
  `ROLE_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '角色ID',
  `MENU_ID` varchar(32) NOT NULL COMMENT '菜单ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu_ele
-- ----------------------------
INSERT INTO `sys_role_menu_ele` VALUES ('0', 'F0');
INSERT INTO `sys_role_menu_ele` VALUES ('0', 'F0000');
INSERT INTO `sys_role_menu_ele` VALUES ('0', 'F0001');
INSERT INTO `sys_role_menu_ele` VALUES ('0', 'F0002');
INSERT INTO `sys_role_menu_ele` VALUES ('0', 'F0003');
INSERT INTO `sys_role_menu_ele` VALUES ('0', 'F0004');
INSERT INTO `sys_role_menu_ele` VALUES ('0', 'F0005');
INSERT INTO `sys_role_menu_ele` VALUES ('0', 'F0006');
INSERT INTO `sys_role_menu_ele` VALUES ('0', 'F1');
INSERT INTO `sys_role_menu_ele` VALUES ('0', 'F1001');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `USER_ID` varchar(32) NOT NULL COMMENT '用户ID',
  `LOGIN_NAME` varchar(128) NOT NULL COMMENT '登录名称',
  `LOGIN_PWD` varchar(128) NOT NULL COMMENT '登录密码',
  `USER_NAME` varchar(128) DEFAULT NULL COMMENT '用户名称',
  `STATUS` char(1) DEFAULT NULL COMMENT '用户状态',
  `EMAIL` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `ADDRESS` varchar(256) DEFAULT NULL COMMENT '地址',
  `REMARK` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `LOGIN_NAME` (`LOGIN_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('0', 'admin', 'A66ABB5684C45962D887564F08346E8D', 'admin', '0', '', '', '');
INSERT INTO `sys_user` VALUES ('075BD250CBD946A9AB587CED9747B64B', 'hhhh', 'E61E7DE603852182385DA5E907B4B232', '小胖', '1', 'hhhh@qq.com', 'hhhh', 'hhhh');
INSERT INTO `sys_user` VALUES ('382AD0FC470249209D7074B82FBF264F', 'zhangfen', '202CB962AC59075B964B07152D234B70', '张翼德', '1', 'zhangfen@qq.com', '广东', 'test');
INSERT INTO `sys_user` VALUES ('42C7211EA8BB4E1183DD55B6E0F9ED95', 'zhangsan', '202CB962AC59075B964B07152D234B70', '张三', '0', 'zhangsan@163.com', '广东深圳华美居', '张三官方指定账号');
INSERT INTO `sys_user` VALUES ('543DE6DF8DB44D46BEAE37A31B70FBA7', '', 'D41D8CD98F00B204E9800998ECF8427E', '', '0', '', '', '');
INSERT INTO `sys_user` VALUES ('8A9F38F36DB14352BE08F3F44B54FB9B', 'lvbu', '202CB962AC59075B964B07152D234B70', '吕布', '0', 'lvbu@qq.com', '山东', '武功天下第一');
INSERT INTO `sys_user` VALUES ('CFE01CE0443B4CC796EB21DA4B6B288D', 'qqqq', '3bad6af0fa4b8b330d162e19938ee981', 'qqqq', '0', 'qqq@qq.com', 'qqqq', 'qqqq');
INSERT INTO `sys_user` VALUES ('D0D20517948A49369339A570917D3080', 'liubei', '202CB962AC59075B964B07152D234B70', '刘备', '0', 'liubei@qq.com', '蜀', '天下大事，分久必合，合久必分');

-- ----------------------------
-- Table structure for `sys_user_role_ele`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_ele`;
CREATE TABLE `sys_user_role_ele` (
  `USER_ID` varchar(32) NOT NULL COMMENT '用户ID',
  `ROLE_ID` varchar(32) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_user_role_ele
-- ----------------------------
INSERT INTO `sys_user_role_ele` VALUES ('075BD250CBD946A9AB587CED9747B64B', '0');
INSERT INTO `sys_user_role_ele` VALUES ('075BD250CBD946A9AB587CED9747B64B', '6246E4F5BCE4425DA1B072B4C7DF5762');
INSERT INTO `sys_user_role_ele` VALUES ('543DE6DF8DB44D46BEAE37A31B70FBA7', '0');
INSERT INTO `sys_user_role_ele` VALUES ('543DE6DF8DB44D46BEAE37A31B70FBA7', '6246E4F5BCE4425DA1B072B4C7DF5762');
INSERT INTO `sys_user_role_ele` VALUES ('0', 'A8247FD28E974286B8C96E0CCC3DDCDC');
INSERT INTO `sys_user_role_ele` VALUES ('0', '0');
