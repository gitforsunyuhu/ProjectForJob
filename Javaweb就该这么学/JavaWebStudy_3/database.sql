/*
 Navicat Premium Data Transfer

 Source Server         : mysql_connection
 Source Server Type    : MySQL
 Source Server Version : 50642
 Source Host           : localhost:3306
 Source Schema         : zdmedu

 Target Server Type    : MySQL
 Target Server Version : 50642
 File Encoding         : 65001

 Date: 16/09/2019 22:29:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `pid` int(11) NULL DEFAULT NULL,
  `mess` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 'nb');
INSERT INTO `comment` VALUES (1, '太厉害了');
INSERT INTO `comment` VALUES (2, '性能怪兽');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `gift` int(255) NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '电脑1', 2444.00, 0, './img/1.png', '这是个好电脑。');
INSERT INTO `product` VALUES (2, '电脑2', 34444.00, 0, './img/2.jpg', '这电脑真他妈贵！');
INSERT INTO `product` VALUES (3, '电脑3', 2323232.00, 0, './img/2.jpg', '不错');
INSERT INTO `product` VALUES (4, '电脑4', 232334.00, 0, './img/3.jpg', '还可以');
INSERT INTO `product` VALUES (5, '电脑5', 2323.00, 0, './img/4.jpg', '性能差');
INSERT INTO `product` VALUES (6, '电脑', 2222.90, 0, './img/1.png', '这个电脑nb啊！！！');
INSERT INTO `product` VALUES (7, '电脑', 2222.90, 0, './img/1.png', '这个电脑nb啊！！！');
INSERT INTO `product` VALUES (8, '电脑', 2222.90, 0, './img/1.png', '这个电脑nb啊！！！');
INSERT INTO `product` VALUES (9, '电脑', 2222.90, 0, './img/1.png', '这个电脑nb啊！！！');
INSERT INTO `product` VALUES (10, '电脑', 2222.90, 0, './img/1.png', '这个电脑nb啊！！！');
INSERT INTO `product` VALUES (11, '电脑', 2222.90, 0, './img/1.png', '这个电脑nb啊！！！');
INSERT INTO `product` VALUES (12, '电脑', 2222.90, 0, './img/1.png', '这个电脑nb啊！！！');
INSERT INTO `product` VALUES (13, '电脑', 2222.90, 0, './img/1.png', '这个电脑nb啊！！！');
INSERT INTO `product` VALUES (14, '电脑', 2222.90, 0, './img/1.png', '这个电脑nb啊！！！');
INSERT INTO `product` VALUES (15, '电脑', 2222.90, 0, './img/1.png', '这个电脑nb啊！！！');
INSERT INTO `product` VALUES (16, '电脑', 2222.90, 0, './img/1.png', '这个电脑nb啊！！！');
INSERT INTO `product` VALUES (17, '电脑', 2222.90, 0, './img/1.png', '这个电脑nb啊！！！');
INSERT INTO `product` VALUES (18, '电脑', 2222.90, 0, './img/1.png', '这个电脑nb啊！！！');
INSERT INTO `product` VALUES (19, '电脑', 2222.90, 0, './img/1.png', '这个电脑nb啊！！！');
INSERT INTO `product` VALUES (20, '电脑', 2222.90, 0, './img/1.png', '这个电脑nb啊！！！');
INSERT INTO `product` VALUES (21, '电脑', 2222.90, 0, './img/1.png', '这个电脑nb啊！！！');
INSERT INTO `product` VALUES (22, '电脑', 2222.90, 0, './img/1.png', '这个电脑nb啊！！！');
INSERT INTO `product` VALUES (23, '电脑', 2222.90, 0, './img/1.png', '这个电脑nb啊！！！');
INSERT INTO `product` VALUES (24, '电脑', 2222.90, 0, './img/1.png', '这个电脑nb啊！！！');
INSERT INTO `product` VALUES (25, '电脑', 2222.90, 0, './img/1.png', '这个电脑nb啊！！！');
INSERT INTO `product` VALUES (26, '电脑', 2222.90, 0, './img/1.png', '这个电脑nb啊！！！');
INSERT INTO `product` VALUES (27, '电脑', 2222.90, 0, './img/1.png', '这个电脑nb啊！！！');

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (1, '孙玉虎', '111');
INSERT INTO `staff` VALUES (2, '吉吉潘', '111');
INSERT INTO `staff` VALUES (3, 'sun', 'sun');
INSERT INTO `staff` VALUES (4, 'sun', '11');
INSERT INTO `staff` VALUES (5, 'sunyuhu', 'sunyuhu');
INSERT INTO `staff` VALUES (6, 'zhanghong', 'ss');
INSERT INTO `staff` VALUES (7, 'zhangsong', 'ss');
INSERT INTO `staff` VALUES (8, 'ss', 'ss');
INSERT INTO `staff` VALUES (9, 'æ¾', 'ss');
INSERT INTO `staff` VALUES (10, 'zhang', 'ss');
INSERT INTO `staff` VALUES (11, 'zhang', 'ss');
INSERT INTO `staff` VALUES (12, 'sss', 'sss');
INSERT INTO `staff` VALUES (13, 'ssss', 'ss');

SET FOREIGN_KEY_CHECKS = 1;
