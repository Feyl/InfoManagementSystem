/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : management_systems

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 09/06/2021 14:20:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '698d51a19d8a121ce581499d7b701668');

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `class_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `stu_num` int NULL DEFAULT NULL,
  PRIMARY KEY (`no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('119230101', '大数据一班', 3);
INSERT INTO `class` VALUES ('119230102', '大数据二班', 0);
INSERT INTO `class` VALUES ('119230201', '软件工程一班', 0);
INSERT INTO `class` VALUES ('119230202', '软件工程二班', 8);
INSERT INTO `class` VALUES ('119230203', '软件工程三班', 0);
INSERT INTO `class` VALUES ('119230204', '软件工程四班', 0);
INSERT INTO `class` VALUES ('119230301', '电子信息科学与技术一班', 0);
INSERT INTO `class` VALUES ('119230302', '电子信息科学与技术二班', 0);
INSERT INTO `class` VALUES ('119230401', '智能科学与技术一班', 0);
INSERT INTO `class` VALUES ('119230402', '智能科学与技术二班', 0);
INSERT INTO `class` VALUES ('119230501', '人工智能一班', 1);
INSERT INTO `class` VALUES ('119230502', '人工智能二班', 0);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `department` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `major` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `class_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `admission_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('11923010101', 'Swif', '计算机', 'CS', '119230101', '2020-09-02 00:00:00');
INSERT INTO `student` VALUES ('11923010102', 'Json', '计算机', 'CS', '119230101', '2020-09-01 00:00:00');
INSERT INTO `student` VALUES ('11923010110', 'Lucy', '计算机', 'CS', '119230101', '2021-05-19 17:42:02');
INSERT INTO `student` VALUES ('11923020201', 'Anonymous', '人工智能', 'SE', '119230202', '2021-05-30 00:00:00');
INSERT INTO `student` VALUES ('11923020202', 'Jav', '计算机', 'CS', '119230202', '2019-09-01 00:00:00');
INSERT INTO `student` VALUES ('11923020204', 'Jack', '人工智能', 'SE', '119230202', '2021-05-26 20:05:51');
INSERT INTO `student` VALUES ('11923020206', 'Flow', '计算机', 'CS', '119230202', '2021-05-26 20:08:56');
INSERT INTO `student` VALUES ('11923020207', 'Paul', '计算机', 'CS', '119230202', '2021-06-01 00:00:00');
INSERT INTO `student` VALUES ('11923020208', 'Ajax', '人工智能', 'SE', '119230202', '2021-06-01 00:00:00');
INSERT INTO `student` VALUES ('11923020212', 'Go', '人工智能', 'SE', '119230202', '2021-06-01 00:18:54');
INSERT INTO `student` VALUES ('11923020213', 'Doker', '人工智能', 'SE', '119230202', '2021-06-01 16:06:51');
INSERT INTO `student` VALUES ('11923020215', 'Bob', '人工智能', 'SE', '119230202', '2020-09-01 00:00:00');
INSERT INTO `student` VALUES ('11923050101', 'world', '人工智能', 'AI', '119230501', '2021-09-01 00:00:00');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `department` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `class_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `teaching_age` int NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
