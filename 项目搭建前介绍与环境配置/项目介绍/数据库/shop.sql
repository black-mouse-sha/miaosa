/*
 Navicat Premium Data Transfer

 Source Server         : xianmgu
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : shop

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 16/04/2021 14:55:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for shop_address
-- ----------------------------
DROP TABLE IF EXISTS `shop_address`;
CREATE TABLE `shop_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `province` varchar(255) DEFAULT NULL COMMENT '省名称',
  `province_id` bigint(11) DEFAULT NULL COMMENT '省id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `city` varchar(255) DEFAULT NULL COMMENT '城市名称',
  `city_id` bigint(11) DEFAULT NULL COMMENT '城市id',
  `area` varchar(255) DEFAULT NULL COMMENT '区域名称',
  `area_id` bigint(11) DEFAULT NULL COMMENT '区域id',
  `name` varchar(255) DEFAULT NULL COMMENT '收货人名称',
  `phone` varchar(255) DEFAULT NULL COMMENT '收货人手机号',
  `is_default` int(255) DEFAULT NULL COMMENT '是否是默认地址',
  `post_code` varchar(255) DEFAULT NULL COMMENT '邮政编码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_index` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8 COMMENT='收货地址表';

-- ----------------------------
-- Records of shop_address
-- ----------------------------
BEGIN;
INSERT INTO `shop_address` VALUES (23, '详细地址请见', '河北省', 130000, 13, '唐山市', 130200, '开平区', 130205, '张云鹏', '18945051918', 0, '150000');
INSERT INTO `shop_address` VALUES (24, '111', '吉林省', 220000, 13, '四平市', 220300, '铁东区', 220303, '测试一个', '18988888888', 1, '111111');
INSERT INTO `shop_address` VALUES (25, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 14, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (26, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 17, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (27, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 18, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (28, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 19, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (29, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 20, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (30, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 21, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (31, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 22, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (32, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 23, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (33, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 24, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (34, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 25, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (35, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 26, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (36, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 27, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (37, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 28, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (38, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 29, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (39, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 30, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (40, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 31, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (41, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 32, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (42, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 33, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (43, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 34, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (44, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 35, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (45, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 36, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (46, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 37, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (47, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 38, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (48, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 39, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (49, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 40, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (50, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 41, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (51, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 42, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (52, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 43, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (53, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 44, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (54, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 45, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (55, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 46, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (56, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 47, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (57, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 48, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (58, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 49, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (59, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 50, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (60, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 51, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (61, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 52, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (62, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 53, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (63, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 54, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (64, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 55, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (65, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 56, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (66, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 57, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
INSERT INTO `shop_address` VALUES (67, '滴滴答答滴滴答答滴滴答答的', '天津市', 120000, 58, '天津市', 120100, '河东区', 120102, '张云鹏', '18945051918', 1, '122222');
COMMIT;

-- ----------------------------
-- Table structure for shop_admin
-- ----------------------------
DROP TABLE IF EXISTS `shop_admin`;
CREATE TABLE `shop_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) DEFAULT NULL COMMENT '账号',
  `password` varchar(500) DEFAULT NULL COMMENT '密码',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门id',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `freeze` int(11) DEFAULT NULL COMMENT '是否冻结',
  `face` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `email` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '用户手机号',
  `insert_time` datetime DEFAULT NULL COMMENT '注册时间',
  `sex` int(11) DEFAULT NULL COMMENT '性别,0:男，1:女',
  `type` int(11) DEFAULT NULL COMMENT '用户类型，0:管理员，1:商城会员',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_index` (`id`),
  KEY `username_index` (`username`),
  KEY `phone_index` (`phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_admin
-- ----------------------------
BEGIN;
INSERT INTO `shop_admin` VALUES (1, 'admin', '$2a$10$tGyeiUraHh4xwzHqYPPqyuOT3ZrATU6s09Ky5oAz7oXb.n2qKXw72', 1, 3, '超级管理员', 0, '/public/90bd2326-2175-4f34-bde5-00ba2d374bb6.jpeg', NULL, NULL, '2021-03-31 12:19:38', 0, 0);
INSERT INTO `shop_admin` VALUES (2, '12', '$2a$10$T/O3DPR/cqa8TVkojOJbQ.TtTC21zCmFfzXZ5pAZcNNFkU6Ew29Ce', 1, 3, '32', 0, '/public/b35a918e-2453-4cd5-aa3a-9b65f9c4225f.jpeg', NULL, NULL, '2021-04-02 17:27:41', NULL, 0);
INSERT INTO `shop_admin` VALUES (9, 'abc', '$2a$10$KVy7nwNYc8JuOyyd9BABmOaW1QRe7CUwtZb3TyyyU8Uk4NQV0BnIC', 6, 1, '312', 0, '/public/69a7be31-d037-4e1c-b417-e44c0fb4dbb0.jpeg', NULL, NULL, '2021-04-02 17:27:36', NULL, 0);
INSERT INTO `shop_admin` VALUES (10, 'test', '$2a$10$fIRa2KDGKkBNtaA2KiBskuBUqheFfbn0MTpwlS7.iT1uRFO1K80WS', 6, 2, '123', 0, '/public/071820fa-9772-429c-a0e8-8fb88f9f6fe7.jpeg', NULL, NULL, '2021-04-02 17:18:03', NULL, 0);
INSERT INTO `shop_admin` VALUES (11, '18944444444', '$2a$10$fIRa2KDGKkBNtaA2KiBskuBUqheFfbn0MTpwlS7.iT1uRFO1K80WS', 1, 1, 'ddd', 0, '/public/575f24c9-0baf-4dc9-ab36-d090c8c35e6c.jpeg', NULL, '18944444444', '2021-04-02 17:21:52', 1, 1);
INSERT INTO `shop_admin` VALUES (13, '18945051918', '$2a$10$BlTXeECD6zrP1qo2Uvz/Ze2G.N0NEqcVin5gjWd0t.TA9YnoKlbiO', NULL, NULL, '我是一个测试号1', 1, '/public/bac9d6ae-405b-4749-8d1d-73a06f18e7d1.jpg', '111@qq.com', '18945051918', '2021-04-07 10:58:21', 1, 1);
INSERT INTO `shop_admin` VALUES (14, '18945051919', '$2a$10$umGS/ZS1zE44YtaOD0rIG.MQe9RlYCy1e1KMnQn0Him5Pq7aybmY6', NULL, NULL, '111', 0, '/public/c069095b-81f9-4438-8a7d-7af086f23438.jpg', '111', '18945051919', '2021-04-07 11:08:36', 0, 1);
INSERT INTO `shop_admin` VALUES (17, '18945051920', '$2a$10$KGZwMOgc/PoKXv16zuDw6e50v8ykmfcIrRDWC9ly1rvchqYnRRoR6', NULL, NULL, '测试号1920', 0, '/public/8cdc8420-0524-4d2e-bf11-d41139ec2e5b.jpeg', NULL, '18945051920', '2021-04-15 08:48:19', 0, 1);
INSERT INTO `shop_admin` VALUES (18, '18945051921', '$2a$10$/rZa/FZYQndTGwC4LDmZR.mtNYJjfVKf9VURU36fvUmIL443iRrYm', NULL, NULL, '测试号1921', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051921', '2021-04-15 08:50:14', 0, 1);
INSERT INTO `shop_admin` VALUES (19, '18945051922', '$2a$10$EfhaFAhNx55AQCqlwQmxwON6u7HuFWsSetgb2cc5wwngNJYdRy1L6', NULL, NULL, '测试号1922', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051922', '2021-04-15 08:50:39', 0, 1);
INSERT INTO `shop_admin` VALUES (20, '18945051923', '$2a$10$COXOEZTtoM/ylaVp0TaxNeb8DbVI06/8eqT/XypxYTXornNZ62hK2', NULL, NULL, '测试号1923', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051923', '2021-04-15 08:51:06', 0, 1);
INSERT INTO `shop_admin` VALUES (21, '18945051924', '$2a$10$z.ERWe/bJu1.n8YOk6pJIuB4CDHsUePlFnOIb95HE9EwNzDAl.dum', NULL, NULL, '测试号1924', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051924', '2021-04-15 08:51:12', 0, 1);
INSERT INTO `shop_admin` VALUES (22, '18945051925', '$2a$10$TH84t.wkFjs.fM6Pl9mBH.vZbOYmq5o94alMYt7mnWuFzTjyio2va', NULL, NULL, '测试号1925', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051925', '2021-04-15 08:51:17', 0, 1);
INSERT INTO `shop_admin` VALUES (23, '18945051926', '$2a$10$pS3ayCkCP21NplC.NhMi4eJtG4lKCZyVw3PEaxJgkN5HDSsPUciqy', NULL, NULL, '测试号1926', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051926', '2021-04-15 08:51:22', 0, 1);
INSERT INTO `shop_admin` VALUES (24, '18945051927', '$2a$10$yLaTY2G3jjNWSMtUAgVMdeF5lT6jr5bAj3RVKWL8jUCeB3W9gbJ8C', NULL, NULL, '测试号1927', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051927', '2021-04-15 08:51:27', 0, 1);
INSERT INTO `shop_admin` VALUES (25, '18945051928', '$2a$10$HYfOOqvzKu8Gx4hvkBAM.OnquSSk2P5sdbFLXy3cnwxdzf3RFn.cy', NULL, NULL, '测试号1928', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051928', '2021-04-15 08:51:32', 0, 1);
INSERT INTO `shop_admin` VALUES (26, '18945051929', '$2a$10$qnXwdM5rB2ti9ElL/x6oruMgUYVUOd87gAvjIs648kpK.kxzn2ti.', NULL, NULL, '测试号1929', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051929', '2021-04-15 08:51:38', 0, 1);
INSERT INTO `shop_admin` VALUES (27, '18945051930', '$2a$10$kFM9rvw/ZmtSJNHRQ7Nqq.zVsa4unpE8coLZFRolfUwJT/JmFlvXe', NULL, NULL, '测试号1930', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051930', '2021-04-15 08:51:45', 0, 1);
INSERT INTO `shop_admin` VALUES (28, '18945051931', '$2a$10$WbfCMDD/bo8QYo2nu6EC9uwyrUbXwLJ.azTj.OS.ddEyu5NkxFIMG', NULL, NULL, '测试号1931', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051931', '2021-04-15 08:51:49', 0, 1);
INSERT INTO `shop_admin` VALUES (29, '18945051932', '$2a$10$cl0D4ZcW0i1Gmc/RQagVke3.kiMcFRkdao3ilnrZVTCOBVWp.XF3i', NULL, NULL, '测试号1932', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051932', '2021-04-15 08:51:55', 0, 1);
INSERT INTO `shop_admin` VALUES (30, '18945051933', '$2a$10$Ye9z9nFITHZZosJ1o5nx2.7PmWQy.vPsWLJI11gzYSfZ323rzLHkq', NULL, NULL, '测试号1933', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051933', '2021-04-15 08:51:59', 0, 1);
INSERT INTO `shop_admin` VALUES (31, '18945051934', '$2a$10$udVRVS/gSOUNAYGwp/1sf.kqPGdD4cYCpu0.3cnb3vkt7HltveJ7q', NULL, NULL, '测试号1934', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051934', '2021-04-15 08:52:04', 0, 1);
INSERT INTO `shop_admin` VALUES (32, '18945051935', '$2a$10$9L2n3bt2gFWpsBOH7BCTzuyBLMLLbCEN3mIKYBP3BH4bdRGXVAvDa', NULL, NULL, '测试号1935', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051935', '2021-04-15 08:52:09', 0, 1);
INSERT INTO `shop_admin` VALUES (33, '18945051936', '$2a$10$jXtwCm/qrn4d8Fi5LE3pgu6hLwjnyZAo1txKS5e10C9hEEdABhaEG', NULL, NULL, '测试号1936', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051936', '2021-04-15 08:52:14', 0, 1);
INSERT INTO `shop_admin` VALUES (34, '18945051937', '$2a$10$TTaJcqqtj4ll.oxBgNDEyeoGCgXkSTDRF.JIsVY29l08uGW5ZA9x.', NULL, NULL, '测试号1937', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051937', '2021-04-15 08:52:19', 0, 1);
INSERT INTO `shop_admin` VALUES (35, '18945051938', '$2a$10$.gLLrT7ZHpgz4PaDiARp5.V5Eevs4D1YwiUy.E/HO9F6pB9mqdXzS', NULL, NULL, '测试号1938', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051938', '2021-04-15 08:52:23', 0, 1);
INSERT INTO `shop_admin` VALUES (36, '18945051939', '$2a$10$hGvlp9V.97pDQwM17Lfikub.ZDvD0976XDmlgQnG2mAgILsb7SeMi', NULL, NULL, '测试号1939', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051939', '2021-04-15 08:52:28', 0, 1);
INSERT INTO `shop_admin` VALUES (37, '18945051940', '$2a$10$GrrRPT/mFexpyUKowvyv4uuBiaeqOWl/AsSMOjvpg0p.YQXc4IJNO', NULL, NULL, '测试号1940', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051940', '2021-04-15 08:52:37', 0, 1);
INSERT INTO `shop_admin` VALUES (38, '18945051941', '$2a$10$15ZKIK/I1PfLRu6n3WvKIecDIcUChh1aXzusdqWrxnAqMqWoUpQia', NULL, NULL, '测试号1941', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051941', '2021-04-15 08:52:42', 0, 1);
INSERT INTO `shop_admin` VALUES (39, '18945051942', '$2a$10$T095Q1rgxg6T4PiSsv.r4OFEJKK1cUilGR.NNy0w1pFF9tVyL9bR2', NULL, NULL, '测试号1942', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051942', '2021-04-15 08:53:34', 0, 1);
INSERT INTO `shop_admin` VALUES (40, '18945051943', '$2a$10$LZe9K88MQ2mmhb2Tr27Rv.SObKFtY3oPKq8cNd1oo.LbvQlmHCBc.', NULL, NULL, '测试号1943', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051943', '2021-04-15 08:53:37', 0, 1);
INSERT INTO `shop_admin` VALUES (41, '18945051944', '$2a$10$bx6hgwl49d0/gltms.CC..WbI/FNJOWstS919LzrS22OviLR8zLP.', NULL, NULL, '测试号1944', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051944', '2021-04-15 08:53:41', 0, 1);
INSERT INTO `shop_admin` VALUES (42, '18945051945', '$2a$10$R0lJiFHHs3z2op4Oah9KA.VbsNjaj8ts77U98n2om8.vFxlKGVgZC', NULL, NULL, '测试号1945', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051945', '2021-04-15 08:54:06', 0, 1);
INSERT INTO `shop_admin` VALUES (43, '18945051946', '$2a$10$YZyZxXigbZdRo1i3mS3xIODsqihFmehXdxyEJIu1uP5/1BugrKLAq', NULL, NULL, '测试号1946', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051946', '2021-04-15 08:54:10', 0, 1);
INSERT INTO `shop_admin` VALUES (44, '18945051947', '$2a$10$WcoVwW0V5LIfg4obx5cpZujQsEFwrve.2aDMrYLMBPOGrjUu3aBOm', NULL, NULL, '测试号1947', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051947', '2021-04-15 08:54:15', 0, 1);
INSERT INTO `shop_admin` VALUES (45, '18945051948', '$2a$10$cZf1PXCbnTlYo4Knni9od.scKkTkP1j9hshrDdORGVl6wnSLAbYOm', NULL, NULL, '测试号1948', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051948', '2021-04-15 08:54:19', 0, 1);
INSERT INTO `shop_admin` VALUES (46, '18945051949', '$2a$10$75dlqpsFtInMJUQyBrSiBecqvpOx3vMijMJF2oX2vrwXe5Zamb/TG', NULL, NULL, '测试号1949', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '18945051949', '2021-04-15 08:54:23', 0, 1);
INSERT INTO `shop_admin` VALUES (47, '18945051950', '$2a$10$MYkm1Ur//GqZhgWGnh7RjOXR4owqWHewD/0BvXLAz7ffC7VnTvCDm', NULL, NULL, '测试号1950', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '189450519450', '2021-04-15 08:54:32', 0, 1);
INSERT INTO `shop_admin` VALUES (48, '18945051951', '$2a$10$jpgvFHbvviL56SNvuqf6h.rFsAQbCljhHkFKAgxH8XQQ/T2eQaJvW', NULL, NULL, '测试号1951', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '189450519451', '2021-04-15 08:54:38', 0, 1);
INSERT INTO `shop_admin` VALUES (49, '18945051952', '$2a$10$X8O8N1FnizX1EjRxADulOeQdOlu3pRp5ayT95YD4ytr5/fyh7PeZa', NULL, NULL, '测试号1952', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '189450519452', '2021-04-15 08:54:42', 0, 1);
INSERT INTO `shop_admin` VALUES (50, '18945051953', '$2a$10$wrvarjz5N6xjB.PxKSYsVODyLlQrhiA3CXdP7ql7BuXfNzDP9OA0.', NULL, NULL, '测试号1953', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '189450519453', '2021-04-15 08:54:47', 0, 1);
INSERT INTO `shop_admin` VALUES (51, '18945051954', '$2a$10$cBvy1YveZ2.Ri26lg0lm7uhhzaUr9sCyjJr7pn5R9AoYZSjIAo/3K', NULL, NULL, '测试号1954', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '189450519454', '2021-04-15 08:54:51', 0, 1);
INSERT INTO `shop_admin` VALUES (52, '18945051955', '$2a$10$3ryymAn/AXEjMt1za7r6ReXt.njtwaznkPYsMKL5Y5qhsZTyWMDM2', NULL, NULL, '测试号1955', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '189450519455', '2021-04-15 08:54:55', 0, 1);
INSERT INTO `shop_admin` VALUES (53, '18945051956', '$2a$10$XrLXxu5Zqc8mjvZ1PMn9seDPSwfoGJ1SUOUcQc3IxjxSdo7egwwy.', NULL, NULL, '测试号1956', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '189450519456', '2021-04-15 08:55:00', 0, 1);
INSERT INTO `shop_admin` VALUES (54, '18945051957', '$2a$10$YOgIqlggQ.bnUIATxdU1nu4t7dx64NucspKeXth6lB/APycJUSHjy', NULL, NULL, '测试号1957', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '189450519457', '2021-04-15 08:55:04', 0, 1);
INSERT INTO `shop_admin` VALUES (55, '18945051958', '$2a$10$AOR89iYiUe2lHqdpqPHhj.ioDp5dodfjpq.jUvwhCBRbbud/LoVie', NULL, NULL, '测试号1958', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '189450519458', '2021-04-15 08:55:09', 0, 1);
INSERT INTO `shop_admin` VALUES (56, '18945051959', '$2a$10$647lwO6cdZnGfPv2T7ptdO9faz/9W0IcHHbCBJvgKL5acVDQbVcBG', NULL, NULL, '测试号1959', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '189450519459', '2021-04-15 08:55:14', 0, 1);
INSERT INTO `shop_admin` VALUES (57, '18945051960', '$2a$10$kDcM/2AAw8kZOYQrvp/r/OuwhbUYDKZeA6ap57hToaq3pbugMJWlu', NULL, NULL, '测试号1960', 0, '/public/15329e8f-f09e-4217-858e-18d8288f827a.jpeg', NULL, '189450519460', '2021-04-15 08:55:22', 0, 1);
INSERT INTO `shop_admin` VALUES (58, '18945051961', '$2a$10$LbWsmERtdz0nQabjPpPqbuYIWmSRrTMgGpYq3uJYCf5j5aQ9aGfwe', NULL, NULL, '测试号1961', 0, '/public/1885f17f-1678-4113-9e5e-25938458184f.jpeg', NULL, '18945051961', '2021-04-15 08:56:09', 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for shop_buy_car
-- ----------------------------
DROP TABLE IF EXISTS `shop_buy_car`;
CREATE TABLE `shop_buy_car` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `is_deal` int(255) DEFAULT NULL COMMENT '是否支付',
  `insert_time` datetime DEFAULT NULL COMMENT '创建时间',
  `buy_count` int(255) DEFAULT NULL COMMENT '购买数量',
  `order_no` varchar(255) DEFAULT NULL COMMENT '订单编号(结算后设置)',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id（结算后设置）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_index` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_buy_car
-- ----------------------------
BEGIN;
INSERT INTO `shop_buy_car` VALUES (7, 8, 13, 1, '2021-04-08 12:52:52', 1, 'SPDD-1617928662891aa15083a', NULL);
INSERT INTO `shop_buy_car` VALUES (8, 1, 13, 1, '2021-04-08 12:59:22', 6, 'SPDD-16178722506397543a587', NULL);
INSERT INTO `shop_buy_car` VALUES (11, 12, 13, 1, '2021-04-08 15:12:46', 3, NULL, NULL);
INSERT INTO `shop_buy_car` VALUES (16, 8, 13, 1, '2021-04-09 08:50:46', 3, 'SPDD-161792947787224f84f5a', NULL);
INSERT INTO `shop_buy_car` VALUES (21, 1, 13, 0, '2021-04-14 14:13:20', 1, NULL, NULL);
INSERT INTO `shop_buy_car` VALUES (22, 7, 17, 1, '2021-04-15 15:22:35', 4, 'SPDD-16184713759891683646e', 243);
COMMIT;

-- ----------------------------
-- Table structure for shop_dept
-- ----------------------------
DROP TABLE IF EXISTS `shop_dept`;
CREATE TABLE `shop_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '部门名称',
  `description` varchar(255) DEFAULT NULL COMMENT '部门描述',
  `remark` varchar(255) DEFAULT NULL COMMENT '部门备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_index` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_dept
-- ----------------------------
BEGIN;
INSERT INTO `shop_dept` VALUES (1, '总经办', 'sss1', 'sss1');
INSERT INTO `shop_dept` VALUES (2, '技术部', 'ddd', 'ddd');
INSERT INTO `shop_dept` VALUES (3, '销售部', 'eee', 'eee');
INSERT INTO `shop_dept` VALUES (4, '运营部', 'rrr', 'rrrr');
COMMIT;

-- ----------------------------
-- Table structure for shop_goods
-- ----------------------------
DROP TABLE IF EXISTS `shop_goods`;
CREATE TABLE `shop_goods` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `goods_type_id` bigint(11) DEFAULT NULL COMMENT '商品类型id',
  `price` double(10,2) DEFAULT NULL COMMENT '商品价格',
  `discount` int(255) DEFAULT NULL COMMENT '商品折扣',
  `description` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `remark` varchar(255) DEFAULT NULL COMMENT '商品备注',
  `count` int(11) DEFAULT NULL COMMENT '商品库存',
  `logo` varchar(255) DEFAULT NULL COMMENT '商品logo（保存图片路径）',
  `pics` text COMMENT '商品细节图片（多个图片地址用逗号分隔）',
  `is_on_sale` int(255) DEFAULT NULL COMMENT '是否上架,1:在架，2:下架',
  `insert_time` datetime DEFAULT NULL COMMENT '创建时间',
  `sale_count` int(255) DEFAULT NULL COMMENT '销量',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_index` (`id`) USING BTREE,
  KEY `name_index` (`name`) USING BTREE,
  KEY `type_index` (`goods_type_id`) USING BTREE,
  KEY `status_index` (`is_on_sale`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_goods
-- ----------------------------
BEGIN;
INSERT INTO `shop_goods` VALUES (1, '手机125', 2, 9999.00, 7, '描述', '备注', 988, '/public/025e26be-499a-408e-afb9-dc9b68d30129.jpeg', '/public/693f1a94-1784-4d89-b2b4-cf1e049b8c74.jpeg,/public/92ca9b91-1ef8-40c0-b8db-3b2f34b07072.jpeg,/public/8383a266-767c-4c02-be57-696896ec0c96.jpeg', 1, '2021-04-06 14:17:44', 5);
INSERT INTO `shop_goods` VALUES (6, '尿不湿', 3, 100.00, 9, '尿不湿', '尿不湿', 99999, '/public/055486ee-86d7-4c8c-b4b6-b6cbdf506e59.jpeg', '/public/d268bd4e-3cd1-43be-952d-c2fbcb1b3c8b.jpg', 1, '2021-04-07 08:58:14', 2);
INSERT INTO `shop_goods` VALUES (7, '华为手机1', 2, 2199.00, 9, '123', '1123', 99983, '/public/86b05b9a-ad3c-49a9-9939-3fb144754bea.jpg', '/public/b337b034-ba3f-49c8-81ad-76cfc1155661.jpeg,/public/65574ba7-faad-4f80-9b9e-a544ceb1d81f.jpeg', 1, '2021-04-07 14:00:16', 99996);
INSERT INTO `shop_goods` VALUES (8, '华为手机2', 2, 2199.00, 9, '123', '1123', 99995, '/public/86b05b9a-ad3c-49a9-9939-3fb144754bea.jpg', '/public/b337b034-ba3f-49c8-81ad-76cfc1155661.jpeg,/public/65574ba7-faad-4f80-9b9e-a544ceb1d81f.jpeg', 1, '2021-04-07 14:00:16', 4);
INSERT INTO `shop_goods` VALUES (9, '手机1253', 2, 10.00, 7, '描述', '备注', 0, '/public/025e26be-499a-408e-afb9-dc9b68d30129.jpeg', '/public/693f1a94-1784-4d89-b2b4-cf1e049b8c74.jpeg', 1, '2021-04-06 14:17:44', 5);
INSERT INTO `shop_goods` VALUES (10, '华为手机4', 2, 2199.00, 9, '123', '1123', 99997, '/public/86b05b9a-ad3c-49a9-9939-3fb144754bea.jpg', '/public/b337b034-ba3f-49c8-81ad-76cfc1155661.jpeg,/public/65574ba7-faad-4f80-9b9e-a544ceb1d81f.jpeg', 1, '2021-04-07 14:00:16', 1);
INSERT INTO `shop_goods` VALUES (11, '手机1255', 2, 10.00, 7, '描述', '备注', 999, '/public/025e26be-499a-408e-afb9-dc9b68d30129.jpeg', '/public/693f1a94-1784-4d89-b2b4-cf1e049b8c74.jpeg', 1, '2021-04-06 14:17:44', 1);
INSERT INTO `shop_goods` VALUES (12, '华为手机6', 2, 2199.00, 9, '123', '1123', 99993, '/public/86b05b9a-ad3c-49a9-9939-3fb144754bea.jpg', '/public/b337b034-ba3f-49c8-81ad-76cfc1155661.jpeg,/public/65574ba7-faad-4f80-9b9e-a544ceb1d81f.jpeg', 1, '2021-04-07 14:00:16', 1);
INSERT INTO `shop_goods` VALUES (13, '手机1257', 2, 10.00, 7, '描述', '备注', 999, '/public/025e26be-499a-408e-afb9-dc9b68d30129.jpeg', '/public/693f1a94-1784-4d89-b2b4-cf1e049b8c74.jpeg', 1, '2021-04-06 14:17:44', 1);
INSERT INTO `shop_goods` VALUES (14, '华为手机8', 2, 2199.00, 9, '123', '1123', 99999, '/public/86b05b9a-ad3c-49a9-9939-3fb144754bea.jpg', '/public/b337b034-ba3f-49c8-81ad-76cfc1155661.jpeg,/public/65574ba7-faad-4f80-9b9e-a544ceb1d81f.jpeg', 1, '2021-04-07 14:00:16', 1);
INSERT INTO `shop_goods` VALUES (15, '手机1259', 2, 10.00, 7, '描述', '备注', 999, '/public/025e26be-499a-408e-afb9-dc9b68d30129.jpeg', '/public/693f1a94-1784-4d89-b2b4-cf1e049b8c74.jpeg', 1, '2021-04-06 14:17:44', 1);
INSERT INTO `shop_goods` VALUES (16, '华为手机10', 2, 2199.00, 9, '123', '1123', 99999, '/public/86b05b9a-ad3c-49a9-9939-3fb144754bea.jpg', '/public/b337b034-ba3f-49c8-81ad-76cfc1155661.jpeg,/public/65574ba7-faad-4f80-9b9e-a544ceb1d81f.jpeg', 1, '2021-04-07 14:00:16', 1);
INSERT INTO `shop_goods` VALUES (17, '手机12511', 2, 10.00, 7, '描述', '备注', 996, '/public/025e26be-499a-408e-afb9-dc9b68d30129.jpeg', '/public/693f1a94-1784-4d89-b2b4-cf1e049b8c74.jpeg', 1, '2021-04-06 14:17:44', 1);
INSERT INTO `shop_goods` VALUES (18, '华为手机12', 2, 2199.00, 9, '123', '1123', 99998, '/public/86b05b9a-ad3c-49a9-9939-3fb144754bea.jpg', '/public/b337b034-ba3f-49c8-81ad-76cfc1155661.jpeg,/public/65574ba7-faad-4f80-9b9e-a544ceb1d81f.jpeg', 1, '2021-04-07 14:00:16', 2);
INSERT INTO `shop_goods` VALUES (19, '手机12513', 2, 10.00, 7, '描述', '备注', 999, '/public/025e26be-499a-408e-afb9-dc9b68d30129.jpeg', '/public/693f1a94-1784-4d89-b2b4-cf1e049b8c74.jpeg', 1, '2021-04-06 14:17:44', 1);
INSERT INTO `shop_goods` VALUES (20, '华为手机14', 2, 2199.00, 9, '123', '1123', 99999, '/public/86b05b9a-ad3c-49a9-9939-3fb144754bea.jpg', '/public/b337b034-ba3f-49c8-81ad-76cfc1155661.jpeg,/public/65574ba7-faad-4f80-9b9e-a544ceb1d81f.jpeg', 1, '2021-04-07 14:00:16', 1);
INSERT INTO `shop_goods` VALUES (21, 'sss', 2, 199.00, 10, '11', '11', 19, '/public/6220f1dc-fe63-4b2b-af81-a6dc6eb9557a.jpeg', '/public/e92ade05-7ca8-449b-ad9d-a82d27f1e753.jpeg', 0, '2021-04-13 13:48:16', 0);
COMMIT;

-- ----------------------------
-- Table structure for shop_goods_type
-- ----------------------------
DROP TABLE IF EXISTS `shop_goods_type`;
CREATE TABLE `shop_goods_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '商品类型名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '商品类型备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_index` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_goods_type
-- ----------------------------
BEGIN;
INSERT INTO `shop_goods_type` VALUES (2, '数码电子', '得到的1');
INSERT INTO `shop_goods_type` VALUES (3, '母婴用品', '得到的');
INSERT INTO `shop_goods_type` VALUES (4, '美食', '11');
COMMIT;

-- ----------------------------
-- Table structure for shop_log
-- ----------------------------
DROP TABLE IF EXISTS `shop_log`;
CREATE TABLE `shop_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `path` varchar(255) DEFAULT NULL COMMENT '请求路径',
  `ip` varchar(255) DEFAULT NULL COMMENT '请求客户端的ip',
  `insert_time` datetime DEFAULT NULL COMMENT '创建时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '请求人id',
  `user_account` varchar(255) DEFAULT NULL COMMENT '请求人账号',
  `user_nickname` varchar(255) DEFAULT NULL COMMENT '请求人昵称',
  `method` varchar(255) DEFAULT NULL COMMENT '请求的method',
  `request` text COMMENT '请求内容',
  `response` text COMMENT '响应数据',
  `status_code` int(255) DEFAULT NULL COMMENT '请求的状态码',
  `action` varchar(255) DEFAULT NULL COMMENT '请求行为（暂时无用）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_index` (`id`) USING BTREE,
  KEY `path_index` (`path`) USING BTREE,
  KEY `method_index` (`method`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10940 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for shop_menu
-- ----------------------------
DROP TABLE IF EXISTS `shop_menu`;
CREATE TABLE `shop_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) DEFAULT NULL COMMENT '菜单url',
  `pid` bigint(20) DEFAULT NULL COMMENT '菜单父id',
  `remark` varchar(255) DEFAULT NULL COMMENT '菜单备注',
  `level` varchar(255) DEFAULT NULL COMMENT '菜单等级',
  `is_link` int(255) DEFAULT NULL COMMENT '是否有链接',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_index` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_menu
-- ----------------------------
BEGIN;
INSERT INTO `shop_menu` VALUES (1, '系统设置', 'el-icon-delete-solid', NULL, -1, NULL, NULL, 0);
INSERT INTO `shop_menu` VALUES (2, '用户管理', 'el-icon-eleme', '/user', 1, NULL, NULL, 1);
INSERT INTO `shop_menu` VALUES (3, '角色管理', 'el-icon-setting', '/role', 1, NULL, NULL, 1);
INSERT INTO `shop_menu` VALUES (4, '菜单管理', 'el-icon-delete', '/menu', 1, NULL, NULL, 1);
INSERT INTO `shop_menu` VALUES (5, '修改密码', 'el-icon-s-tools', '/password', 1, NULL, NULL, 1);
INSERT INTO `shop_menu` VALUES (6, '日志管理', 'el-icon-s-release', '/log', 1, NULL, NULL, 1);
INSERT INTO `shop_menu` VALUES (7, '商城管理', 'el-icon-user-solid', NULL, -1, NULL, NULL, 0);
INSERT INTO `shop_menu` VALUES (8, '商品管理', 'el-icon-suitcase', '/goods', 7, NULL, NULL, 1);
INSERT INTO `shop_menu` VALUES (9, '商品类型管理', 'el-icon-s-cooperation', '/goods-type', 7, NULL, NULL, 1);
INSERT INTO `shop_menu` VALUES (10, '活动管理', 'el-icon-phone', '', -1, NULL, NULL, 0);
INSERT INTO `shop_menu` VALUES (14, '员工管理', 'el-icon-s-ticket', NULL, -1, NULL, NULL, NULL);
INSERT INTO `shop_menu` VALUES (15, '部门管理', 'el-icon-bottom-left', '/dept', 14, NULL, NULL, NULL);
INSERT INTO `shop_menu` VALUES (16, '配置部门员工', 'el-icon-user', '/dept-user', 14, NULL, NULL, NULL);
INSERT INTO `shop_menu` VALUES (17, '拼团秒杀', 'el-icon-wallet', '/team', 10, NULL, NULL, NULL);
INSERT INTO `shop_menu` VALUES (18, '订单管理', 'el-icon-folder', '/order', 7, NULL, NULL, NULL);
INSERT INTO `shop_menu` VALUES (19, '商城会员', 'el-icon-s-open', '/shop-user', 7, NULL, NULL, NULL);
INSERT INTO `shop_menu` VALUES (20, '测试模块', 'el-icon-s-ticket', NULL, -1, NULL, NULL, NULL);
INSERT INTO `shop_menu` VALUES (21, '批量获取token', 'el-icon-check', '/tokens', 20, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for shop_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `shop_menu_role`;
CREATE TABLE `shop_menu_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_index` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_menu_role
-- ----------------------------
BEGIN;
INSERT INTO `shop_menu_role` VALUES (51, 1, 2);
INSERT INTO `shop_menu_role` VALUES (52, 2, 2);
INSERT INTO `shop_menu_role` VALUES (53, 3, 2);
INSERT INTO `shop_menu_role` VALUES (54, 4, 2);
INSERT INTO `shop_menu_role` VALUES (55, 5, 2);
INSERT INTO `shop_menu_role` VALUES (56, 6, 2);
INSERT INTO `shop_menu_role` VALUES (57, 9, 2);
INSERT INTO `shop_menu_role` VALUES (58, 7, 2);
INSERT INTO `shop_menu_role` VALUES (59, 1, 4);
INSERT INTO `shop_menu_role` VALUES (60, 2, 4);
INSERT INTO `shop_menu_role` VALUES (61, 3, 4);
INSERT INTO `shop_menu_role` VALUES (62, 4, 4);
INSERT INTO `shop_menu_role` VALUES (63, 5, 4);
INSERT INTO `shop_menu_role` VALUES (64, 6, 4);
INSERT INTO `shop_menu_role` VALUES (65, 7, 4);
INSERT INTO `shop_menu_role` VALUES (66, 8, 4);
INSERT INTO `shop_menu_role` VALUES (67, 9, 4);
INSERT INTO `shop_menu_role` VALUES (68, 4, 7);
INSERT INTO `shop_menu_role` VALUES (69, 6, 7);
INSERT INTO `shop_menu_role` VALUES (70, 1, 7);
INSERT INTO `shop_menu_role` VALUES (74, 4, 10);
INSERT INTO `shop_menu_role` VALUES (75, 5, 10);
INSERT INTO `shop_menu_role` VALUES (76, 1, 10);
INSERT INTO `shop_menu_role` VALUES (80, 3, 8);
INSERT INTO `shop_menu_role` VALUES (81, 4, 8);
INSERT INTO `shop_menu_role` VALUES (82, 1, 8);
INSERT INTO `shop_menu_role` VALUES (83, 4, 6);
INSERT INTO `shop_menu_role` VALUES (84, 5, 6);
INSERT INTO `shop_menu_role` VALUES (85, 9, 6);
INSERT INTO `shop_menu_role` VALUES (86, 1, 6);
INSERT INTO `shop_menu_role` VALUES (87, 7, 6);
INSERT INTO `shop_menu_role` VALUES (149, 1, 1);
INSERT INTO `shop_menu_role` VALUES (150, 2, 1);
INSERT INTO `shop_menu_role` VALUES (151, 3, 1);
INSERT INTO `shop_menu_role` VALUES (152, 4, 1);
INSERT INTO `shop_menu_role` VALUES (153, 5, 1);
INSERT INTO `shop_menu_role` VALUES (154, 6, 1);
INSERT INTO `shop_menu_role` VALUES (155, 7, 1);
INSERT INTO `shop_menu_role` VALUES (156, 8, 1);
INSERT INTO `shop_menu_role` VALUES (157, 9, 1);
INSERT INTO `shop_menu_role` VALUES (158, 18, 1);
INSERT INTO `shop_menu_role` VALUES (159, 19, 1);
INSERT INTO `shop_menu_role` VALUES (160, 10, 1);
INSERT INTO `shop_menu_role` VALUES (161, 17, 1);
INSERT INTO `shop_menu_role` VALUES (162, 14, 1);
INSERT INTO `shop_menu_role` VALUES (163, 15, 1);
INSERT INTO `shop_menu_role` VALUES (164, 16, 1);
INSERT INTO `shop_menu_role` VALUES (165, 20, 1);
INSERT INTO `shop_menu_role` VALUES (166, 21, 1);
COMMIT;

-- ----------------------------
-- Table structure for shop_order
-- ----------------------------
DROP TABLE IF EXISTS `shop_order`;
CREATE TABLE `shop_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(1000) DEFAULT NULL COMMENT '订单编号',
  `insert_time` datetime DEFAULT NULL COMMENT '创建时间',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `team_id` bigint(20) DEFAULT NULL COMMENT '活动id',
  `type` int(255) DEFAULT NULL COMMENT '订单类型:1.普通订单，2.购物车订单，3.拼团秒杀订单',
  `buy_car_id` bigint(20) DEFAULT NULL COMMENT '购物车id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `address_id` bigint(20) DEFAULT NULL COMMENT '地址id',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `city` varchar(255) DEFAULT NULL COMMENT '城市名称',
  `province` varchar(255) DEFAULT NULL COMMENT '省名称',
  `area` varchar(255) DEFAULT NULL COMMENT '区域名称',
  `post_code` varchar(255) DEFAULT NULL COMMENT '邮编',
  `phone` varchar(255) DEFAULT NULL COMMENT '收货人手机号',
  `name` varchar(255) DEFAULT NULL COMMENT '收货人名称',
  `status` int(255) DEFAULT NULL COMMENT '状态：0.待支付，1.已支付，2.已发货，3.确认收货，4.申请退款，5.退款完成',
  `goods_name` varchar(255) DEFAULT NULL COMMENT '购买商品名称',
  `goods_price` double(10,6) DEFAULT NULL COMMENT '商品在购买时的价格（防止连表价格变化）',
  `goods_discount` int(255) DEFAULT NULL COMMENT '商品购买时的折扣',
  `count` int(11) DEFAULT NULL COMMENT '商品购买数量',
  `remark` varchar(255) DEFAULT NULL COMMENT '订单备注',
  `activity_discount` int(255) DEFAULT NULL COMMENT '商品购买时活动折扣',
  `fallback_time` datetime DEFAULT NULL COMMENT '退款时间',
  `fallback_reason` varchar(255) DEFAULT NULL COMMENT '退款原因',
  `fallback_img` text COMMENT '退款截图（暂时无用）',
  `express_no` varchar(255) DEFAULT NULL COMMENT '快递物流编号',
  `activity_name` varchar(255) DEFAULT NULL COMMENT '订单参与活动名称',
  `pay` decimal(11,6) DEFAULT NULL COMMENT '实际付款金额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_index` (`id`),
  KEY `order_no_index` (`order_no`),
  KEY `status_index` (`status`) USING BTREE,
  KEY `phone_index` (`phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=244 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_order
-- ----------------------------
BEGIN;
INSERT INTO `shop_order` VALUES (233, 'PTDD-16184700327205380CB27', '2021-04-15 15:00:33', 18, 14, 3, NULL, 17, NULL, '滴滴答答滴滴答答滴滴答答的', '天津市', '天津市', '河东区', '122222', '18945051918', '张云鹏', 1, '华为手机12', 2199.000000, 9, 1, '活动下单', 1, NULL, NULL, NULL, NULL, '测试秒杀', 197.910000);
INSERT INTO `shop_order` VALUES (243, 'SPDD-16184713759891683646e', '2021-04-15 15:22:56', 7, NULL, 2, 22, 17, 26, '滴滴答答滴滴答答滴滴答答的', '天津市', '天津市', '河东区', '122222', '18945051918', '张云鹏', 1, '华为手机1', 2199.000000, 9, 4, '123123', NULL, NULL, NULL, NULL, NULL, NULL, 7916.400000);
COMMIT;

-- ----------------------------
-- Table structure for shop_role
-- ----------------------------
DROP TABLE IF EXISTS `shop_role`;
CREATE TABLE `shop_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_index` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_role
-- ----------------------------
BEGIN;
INSERT INTO `shop_role` VALUES (1, '超级管理员1');
INSERT INTO `shop_role` VALUES (4, '13dsdf');
INSERT INTO `shop_role` VALUES (5, '22');
INSERT INTO `shop_role` VALUES (6, '33');
INSERT INTO `shop_role` VALUES (7, '44');
INSERT INTO `shop_role` VALUES (8, '55');
INSERT INTO `shop_role` VALUES (9, '66');
INSERT INTO `shop_role` VALUES (10, '77');
INSERT INTO `shop_role` VALUES (11, '88');
INSERT INTO `shop_role` VALUES (13, '991');
INSERT INTO `shop_role` VALUES (14, '123123');
COMMIT;

-- ----------------------------
-- Table structure for shop_team
-- ----------------------------
DROP TABLE IF EXISTS `shop_team`;
CREATE TABLE `shop_team` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '活动名称',
  `size` int(11) DEFAULT NULL COMMENT '活动商品库存',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `insert_time` datetime DEFAULT NULL COMMENT '创建时间',
  `end_time` datetime DEFAULT NULL COMMENT '活动过期时间',
  `status` int(255) DEFAULT NULL COMMENT '活动状态，0:进行中，1:过期，2:活动结束',
  `is_on_sale` int(255) DEFAULT NULL COMMENT '是否在架，0:在架，1:不在架',
  `team_discount` int(255) DEFAULT NULL COMMENT '活动折扣，1-10，10代表不打折，是商品折扣之后再打折',
  `remark` varchar(255) DEFAULT NULL COMMENT '活动备注',
  `has_member` int(255) DEFAULT NULL COMMENT '活动已参与人数默认是0',
  `type` int(255) DEFAULT NULL COMMENT '活动类型，0:拼团，1:秒杀',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_index` (`id`) USING BTREE,
  KEY `status_index` (`status`) USING BTREE,
  KEY `name_index` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_team
-- ----------------------------
BEGIN;
INSERT INTO `shop_team` VALUES (14, '测试秒杀', 10, 18, '2021-04-15 10:56:08', '2021-04-16 00:00:00', 0, 1, 1, '1111', 1, 1);
INSERT INTO `shop_team` VALUES (15, '测试拼团', 15, 18, '2021-04-15 16:30:24', '2021-04-16 00:00:00', 0, 1, 9, '111', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for shop_team_user
-- ----------------------------
DROP TABLE IF EXISTS `shop_team_user`;
CREATE TABLE `shop_team_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `team_id` bigint(20) DEFAULT NULL COMMENT '活动id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_index` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=207 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_team_user
-- ----------------------------
BEGIN;
INSERT INTO `shop_team_user` VALUES (206, 14, 17);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
