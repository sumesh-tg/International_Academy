/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.121_3306
Source Server Version : 50142
Source Host           : 192.168.1.121:3306
Source Database       : ia

Target Server Type    : MYSQL
Target Server Version : 50142
File Encoding         : 65001

Date: 2016-06-28 16:16:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_email_id
-- ----------------------------
DROP TABLE IF EXISTS `admin_email_id`;
CREATE TABLE `admin_email_id` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email_id` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of admin_email_id
-- ----------------------------
INSERT INTO `admin_email_id` VALUES ('8', 'øöË«˜µƒ?qZ{3UÎŒþìTÍT\'èSÖXp', 'âº¸Å‰Ð½b£#„¨³^');

-- ----------------------------
-- Table structure for agencies_tbl
-- ----------------------------
DROP TABLE IF EXISTS `agencies_tbl`;
CREATE TABLE `agencies_tbl` (
  `ID` varchar(255) NOT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `BRANCH_ID` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `FIRSTNAME` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `COUNTRY` varchar(255) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `DUMMY_TWO` varchar(255) DEFAULT NULL,
  `DUMMY_THREE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of agencies_tbl
-- ----------------------------
INSERT INTO `agencies_tbl` VALUES ('0180e967cc9e', 'vipin', '135e20f63648', 'ss', 'vipin', 'vi@gmail.com', '9768768768', null, 'sdfdf', null, null);
INSERT INTO `agencies_tbl` VALUES ('02b22c19f0ec', null, '54dcb369e715', 'newww', 'newww', '', '', null, '', null, null);
INSERT INTO `agencies_tbl` VALUES ('31ccf647e58c', 'Geethanath p t', '135e20f63648', 'P T', 'Geethanath', 'geetha@gmail.com', '9747144086', null, 'ernakulam', null, null);
INSERT INTO `agencies_tbl` VALUES ('834d7e4ddaee', null, '54dcb369e715', 'fdg', 'dffdg', '', '', null, '', null, null);
INSERT INTO `agencies_tbl` VALUES ('a3c7d80c7d94', null, '54dcb369e715', 'fdg', 'df', 'fg', '', null, '', null, null);
INSERT INTO `agencies_tbl` VALUES ('bc82bd6c6773', 'Anu', '54dcb369e715', 'lakshmy', 'sree', 'krishnendu@gmail.com', '5676521313', null, 'fdsfdfdsfd', null, null);
INSERT INTO `agencies_tbl` VALUES ('c51356b50b7a', null, '54dcb369e715', 'tes', 'tes', '', '', null, '', null, null);
INSERT INTO `agencies_tbl` VALUES ('dfd9f0488397', null, '54dcb369e715', 'new sdsdfdf', 'new deee', '', '', null, '', null, null);
INSERT INTO `agencies_tbl` VALUES ('fe814908dc6a', 'dfg', '54dcb369e715', 'fdg', 'fdg', 'fdg@dsf.gh', '2343242343', null, 'fdgfdgfg', null, null);

-- ----------------------------
-- Table structure for appointments_tbl
-- ----------------------------
DROP TABLE IF EXISTS `appointments_tbl`;
CREATE TABLE `appointments_tbl` (
  `id` varchar(50) NOT NULL,
  `client_name` varchar(255) DEFAULT NULL,
  `app_events` varchar(255) DEFAULT NULL,
  `app_remarks` varchar(255) DEFAULT NULL,
  `scheduled_by` varchar(255) DEFAULT NULL,
  `branch` varchar(255) DEFAULT NULL,
  `app_date_time` datetime DEFAULT NULL,
  `visible` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of appointments_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for appointment_details
-- ----------------------------
DROP TABLE IF EXISTS `appointment_details`;
CREATE TABLE `appointment_details` (
  `app_details_id` varchar(255) NOT NULL,
  `participant_id` varchar(255) NOT NULL,
  `meeting_id` varchar(255) NOT NULL,
  `invitation_status` varchar(255) DEFAULT NULL,
  `participation_status` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  `snooze_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`app_details_id`),
  KEY `fpk_meeting` (`meeting_id`) USING BTREE,
  CONSTRAINT `appointment_details_ibfk_1` FOREIGN KEY (`meeting_id`) REFERENCES `appointment_master` (`meeting_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of appointment_details
-- ----------------------------
INSERT INTO `appointment_details` VALUES ('PART_00fe1c04cf43', 'bini', 'MEET_373640acced6', null, null, null, 'keerthykp', '2016-05-21 10:23:12', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_0109ed0302d1', 'sreejakr', 'MEET_3f2f4e8f6a57', null, null, null, 'keerthykp', '2016-05-21 09:50:22', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_01227075d8c0', 'user a', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_01411a6458db', 'gh', 'MEET_cb96f4b5cf43', null, null, null, 'vijay', '2016-05-26 14:36:29', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_0144d5c95c00', 'b', 'MEET_cb96f4b5cf43', null, null, null, 'vijay', '2016-05-26 14:36:29', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_01e387479001', 'keerthykp', 'MEET_5c303b862902', 'accepted', null, null, 'keerthykp', '2016-05-26 14:29:52', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_021a337151c5', 'sanoop', 'MEET_23f42a8290bd', null, null, null, 'b', '2016-05-12 21:53:04', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_037ad509ce47', 'b', 'MEET_2f3ff03d9820', 'accepted', null, null, 'b', '2016-05-22 12:26:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_03ee061a87f7', 'sreejakr', 'MEET_36366e05438c', null, null, null, 'keerthykp', '2016-05-20 19:31:22', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_041132658d92', 'test', 'MEET_f7ba251c7cb1', null, null, null, 'vijay', '2016-05-21 14:47:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_041929da2ca0', 'rahulzoft', 'MEET_32d0b8578f24', null, null, null, 'keerthykp', '2016-05-20 17:37:44', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_04be7cdc1507', 'sheela', 'MEET_6426132a06d3', null, null, 'null', 'b', '2016-05-09 09:39:36', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_04d0a133464d', 'admin', 'MEET_c120d9e1328e', null, null, null, 'keerthykp', '2016-05-27 10:37:15', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_050e2ca0072c', 'sumeshzoft', 'MEET_5bc4c022699c', null, null, null, 'keerthykp', '2016-05-20 16:26:35', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_055906fd3f45', 'sandeepsk', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_05d220bd0372', 'sheela', 'MEET_b338d85d0616', null, 'present', null, 'b', '2016-05-10 18:48:00', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_05ff1d60cbf5', 'b', 'MEET_1b98ca8c99a1', 'accepted', null, null, 'b', '2016-05-10 14:42:33', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_06320a5e92d2', 'a', 'MEET_1ec37d5f2d38', null, null, null, 'b', '2016-05-22 12:57:24', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_066f52cc41a9', 'keerthykp', 'MEET_45efd3999453', 'accepted', null, null, 'keerthykp', '2016-05-20 15:33:18', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_06b4e09b3063', 'sumeshzoft', 'MEET_9d8bf7d282e7', null, null, null, 'keerthykp', '2016-05-20 15:45:11', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_06e319d11f60', 'vijay', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_06f55d8d7290', 'keerthykp', 'MEET_6426132a06d3', 'accepted', null, 'null', 'b', '2016-05-09 09:39:36', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_073bc2fccd16', 'keerthykp', 'MEET_05d2315fa15e', 'accepted', 'absent', null, 'b', '2016-05-11 16:17:17', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_076c094ba676', 'rahulzoft', 'MEET_217c4b532308', null, null, null, 'keerthykp', '2016-05-21 11:42:46', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_077cb5f15f10', 'fhyty', 'MEET_14a8fb906408', null, null, null, 'keerthykp', '2016-05-27 10:39:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_07db03641fd0', 'sreejakr', 'MEET_565e170e89bb', null, null, null, 'keerthykp', '2016-05-20 18:26:18', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_0804d565ebcc', 'sheela', 'MEET_1e7bf8abee6d', null, null, null, 'b', '2016-05-31 16:46:34', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_0858085628b9', 'keerthykp', 'MEET_32d0b8578f24', 'accepted', null, null, 'keerthykp', '2016-05-20 17:37:44', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_089d0ee57f9b', 'sheela', 'MEET_14a8fb906408', null, null, null, 'keerthykp', '2016-05-27 10:39:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_09590a70817c', 'keerthykp', 'MEET_12884bd36eec', 'accepted', null, null, 'keerthykp', '2016-05-20 16:03:24', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_097989c96e04', 'vijay', 'MEET_90f8da9d45ee', 'accepted', null, null, 'vijay', '2016-05-21 15:14:37', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_09ec308c887d', 'b', 'MEET_495c6101ad85', 'accepted', null, 'null', 'b', '2016-05-06 10:20:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_09ffc87ba12c', 'keerthykp', 'MEET_823857ccb0a0', null, null, null, 'b', '2016-05-12 10:43:27', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_0a4cf2959983', 'sumeshzoft', 'MEET_c2ae59a6ab70', null, null, null, 'vijay', '2016-05-21 14:48:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_0a706ddcfdb5', 'rahulzoft', 'MEET_28974626dcc7', null, null, null, 'b', '2016-05-22 17:59:47', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_0a762eb8c195', 'keerthykp', 'MEET_393c3ffb8694', 'accepted', null, null, 'keerthykp', '2016-05-20 16:25:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_0ab7be4031f4', 'sanoop', 'MEET_6d70a4aad4cd', null, null, null, 'b', '2016-05-10 18:44:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_0b8f970996b7', 'admin', 'MEET_f7ba251c7cb1', null, null, null, 'vijay', '2016-05-21 14:47:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_0bcb40064be9', 'sanoop', 'MEET_5bc4c022699c', null, null, null, 'keerthykp', '2016-05-20 16:26:35', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_0c336bb4b554', 'keerthykp', 'MEET_713729bcface', 'accepted', null, null, 'keerthykp', '2016-05-26 17:26:44', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_0cfee59193f9', 'keerthykp', 'MEET_1e7bf8abee6d', null, null, null, 'b', '2016-05-31 16:46:34', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_0d50ff3d28a1', 'anu', 'MEET_ff857d0e7d4c', null, null, null, 'keerthykp', '2016-05-20 15:59:00', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_0d6973d08e55', 'sumeshzoft', 'MEET_f22b8a9e1574', null, null, null, 'keerthykp', '2016-06-01 09:47:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_0d69b6d359aa', 'sanoop', 'MEET_c2ae59a6ab70', null, null, null, 'vijay', '2016-05-21 14:48:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_0d7a68141115', 'sheela', 'MEET_fc9095165a81', null, null, null, 'vijay', '2016-05-10 12:11:06', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_0de2d1e1f15e', 'sheela', 'MEET_5ad86cf3f75c', null, null, null, 'keerthykp', '2016-05-27 10:35:41', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_0e4fe5b36bd9', 'sreejakr', 'MEET_e2d66d235092', null, null, null, 'keerthykp', '2016-05-19 12:35:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_0f45f3d6925b', 'keerthykp', 'MEET_f22471fed830', null, null, null, 'b', '2016-05-11 16:16:26', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_0f8e3dcbbf88', 'a', 'MEET_45ab4dacb7f8', 'accepted', null, null, 'b', '2016-05-10 14:48:05', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_0fe4237e24a4', 'sheela', 'MEET_2654114382dc', null, null, null, 'keerthykp', '2016-05-12 15:30:48', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1006abf9df81', 'sumeshzoft', 'MEET_cbaf56d7570c', null, null, null, 'keerthykp', '2016-05-20 18:07:05', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_10698a1cd5ab', 'keerthykp', 'MEET_b866307060aa', 'cancelled', null, null, 'keerthykp', '2016-05-30 10:37:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_10b5d78a7110', 'bini', 'MEET_a48f93eaaf89', null, null, null, 'keerthykp', '2016-05-20 10:52:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_116b77243cc7', 'test', 'MEET_cb96f4b5cf43', null, null, null, 'vijay', '2016-05-26 14:36:29', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_11e04b125280', 'admin', 'MEET_c2ae59a6ab70', null, null, null, 'vijay', '2016-05-21 14:48:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_120a3dd3e41a', 'sheela', 'MEET_b0fa4e4a84b4', null, null, null, 'keerthykp', '2016-05-26 17:25:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1275b34e0de1', 'anu', 'MEET_c2ae59a6ab70', null, null, null, 'vijay', '2016-05-21 14:48:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_12bf2200cd2d', 'bini', 'MEET_4703ec4a8788', null, null, null, 'keerthykp', '2016-05-20 17:08:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_131446344df4', 'keerthykp', 'MEET_a683551160e5', 'accepted', null, null, 'keerthykp', '2016-05-20 18:24:00', null, null, 'y', '60000');
INSERT INTO `appointment_details` VALUES ('PART_138ecfb23678', 'sheela', 'MEET_a683bcd7f0b7', null, null, null, 'b', '2016-05-10 14:04:00', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_13a8c864988e', 'admin', 'MEET_4703ec4a8788', null, null, null, 'keerthykp', '2016-05-20 17:08:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_14a7ac0f94a6', 'keerthykp', 'MEET_e2d66d235092', 'accepted', null, null, 'keerthykp', '2016-05-19 12:35:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1517d198db27', 'sreejakr', 'MEET_0b087335064e', null, null, 'null', 'b', '2016-05-09 19:29:11', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_155edc2c7210', 'admin', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:44', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_157172845b13', 'b', 'MEET_1517ba5b3df4', 'accepted', null, null, 'b', '2016-05-13 19:14:12', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1592fd27f4a0', 'keerthykp', 'MEET_657718c1bd1f', 'accepted', null, null, 'keerthykp', '2016-05-20 11:49:42', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_15a18839b2fa', 'keerthykp', 'MEET_87f3be8c1efd', null, null, null, 'b', '2016-05-11 11:03:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_15afa1f3da41', 'sheela', 'MEET_288bea8bcf16', null, null, null, 'vijay', '2016-06-01 09:38:28', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_15be912496fa', 'a', 'MEET_064a4f090797', 'accepted', null, null, 'a', '2016-05-19 12:01:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_15c34a2a50d9', 'bini', 'MEET_495c6101ad85', null, null, 'null', 'b', '2016-05-06 10:20:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1642de0dd46b', 'user14', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1643b4da63bb', 'keerthykp', 'MEET_05e604ef391d', 'accepted', null, null, 'keerthykp', '2016-05-21 13:05:18', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_16596191c5e4', 'test', 'MEET_217c4b532308', null, null, null, 'keerthykp', '2016-05-21 11:42:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_16701c785546', 'sheela', 'MEET_c17c0e431f11', null, null, null, 'keerthykp', '2016-05-20 19:32:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_169766ce7667', 'vijay', 'MEET_00373abaee7d', 'accepted', null, null, 'vijay', '2016-05-27 11:38:50', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_16e50ac6c23a', 'gh', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:44', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_16e703965ea9', 'vijay', 'MEET_da2bb163749b', null, null, null, 'keerthykp', '2016-05-21 10:40:10', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_179869b08e07', 'sanoop', 'MEET_05e604ef391d', null, null, null, 'keerthykp', '2016-05-21 13:05:18', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1819a87f9af9', 'b', 'MEET_40352e977d5e', 'accepted', null, null, 'b', '2016-05-11 14:35:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_18226e47e0f1', 'anu', 'MEET_4bbacda00e1b', null, null, null, 'vijay', '2016-05-10 12:14:01', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_18248bf21984', 'keerthykp', 'MEET_c87a7f4fb9d2', 'accepted', null, null, 'keerthykp', '2016-05-21 09:49:32', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_18d390e9844b', 'keerthykp', 'MEET_1f6d36ea01a5', 'accepted', 'present', 'reason for meet 1', 'b', '2016-05-11 17:01:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_18efc5172312', 'sreejakr', 'MEET_657718c1bd1f', null, null, null, 'keerthykp', '2016-05-20 11:49:42', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_191a66c2db9c', 'anu', 'MEET_2998a3eeccab', null, null, null, 'keerthykp', '2016-05-21 10:02:39', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1950cd6f58ce', 'sanoop', 'MEET_14a8fb906408', null, null, null, 'keerthykp', '2016-05-27 10:39:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_199c499d391d', 'sheela', 'MEET_33d8df254641', null, null, null, 'keerthykp', '2016-05-21 09:52:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_19a623b887eb', 'b', 'MEET_3a0285858dfc', 'accepted', null, null, 'b', '2016-05-22 12:08:00', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_19ec9fc8a8f8', 'sumeshzoft', 'MEET_393c3ffb8694', null, null, null, 'keerthykp', '2016-05-20 16:25:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1addd5726d9b', 'vijay', 'MEET_2654114382dc', null, null, null, 'keerthykp', '2016-05-12 15:30:48', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1b27034cd203', 'sreejakr', 'MEET_168c88015001', null, null, null, 'keerthykp', '2016-05-20 16:10:31', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1b6033a8a359', 'b', 'MEET_05d2315fa15e', 'accepted', 'present', null, 'b', '2016-05-11 16:17:17', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1ba32aada90b', 'a', 'MEET_a683bcd7f0b7', 'accepted', null, null, 'b', '2016-05-10 14:04:00', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1be20ee38601', 'gh', 'MEET_b0fa4e4a84b4', null, null, null, 'keerthykp', '2016-05-26 17:25:46', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1c35d5fc8aa4', 'rahulzoft', 'MEET_9ba0825c3dda', null, null, null, 'keerthykp', '2016-05-12 15:18:35', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1c507460c2b6', 'keerthykp', 'MEET_0c5b431f06fa', 'accepted', null, null, 'keerthykp', '2016-05-21 11:39:59', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1c89ed5ed4b1', 'gh', 'MEET_825e834e802e', null, null, null, 'keerthykp', '2016-05-31 16:39:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1cd68e61785b', 'a', 'MEET_217c4b532308', null, null, null, 'keerthykp', '2016-05-21 11:42:46', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1cdcce898c07', 'vijay', 'MEET_6d70a4aad4cd', null, null, null, 'b', '2016-05-10 18:44:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1d0049ed7692', 'sreejakr', 'MEET_1f6d36ea01a5', null, 'absent', null, 'b', '2016-05-11 17:01:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1d5fb58a771a', 'sumeshzoft', 'MEET_05e604ef391d', null, null, null, 'keerthykp', '2016-05-21 13:05:18', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1d6067ad9f9a', 'b', 'MEET_aab85c51de75', null, null, 'null', 'b', '2016-05-06 10:22:17', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1dbd829e4d0a', 'sreejakr', 'MEET_baa8a39e82a1', null, null, null, 'b', '2016-05-22 13:10:38', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1de657468b6a', 'sumeshzoft', 'MEET_fe2d4a8b117b', null, null, null, 'keerthykp', '2016-05-20 19:47:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1e12aea3e559', 'fxfgg', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1e701b4ab0ad', 'sheela', 'MEET_90b5a93cf517', null, null, null, 'b', '2016-05-10 10:16:48', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1f6b221ddb0e', 'sanoop', 'MEET_5ad86cf3f75c', null, null, null, 'keerthykp', '2016-05-27 10:35:41', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1f9ebc6f9a56', 'b', 'MEET_823857ccb0a0', 'accepted', null, null, 'b', '2016-05-12 10:43:27', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1fa05bd8b4c3', 'admin', 'MEET_c219056ee583', null, null, null, 'keerthykp', '2016-05-30 10:36:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_1ff67f7a0e13', 'b', 'MEET_3b2a2d8ae889', 'accepted', 'present', null, 'b', '2016-05-11 17:02:31', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_206cad765192', 'sreejakr', 'MEET_b0fa4e4a84b4', null, null, null, 'keerthykp', '2016-05-26 17:25:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_208d87014a50', 'keerthykp', 'MEET_ca5694d4b013', null, null, 'utyut', 'vijay', '2016-05-10 12:44:50', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_20cb43a7047b', 'fhyty', 'MEET_288bea8bcf16', null, null, null, 'vijay', '2016-06-01 09:38:28', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_20f2deb00e0c', 'keerthykp', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:44', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_217d4770cb4d', 'sumeshzoft', 'MEET_1e7bf8abee6d', null, null, null, 'b', '2016-05-31 16:46:34', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_21a22766113f', 'anu', 'MEET_2d2b3d79a47c', null, null, null, 'keerthykp', '2016-05-21 09:48:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_21b049808e86', 'fxfgg', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_21d3fad7e399', 'sreejakr', 'MEET_1e7bf8abee6d', null, null, null, 'b', '2016-05-31 16:46:34', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_2251736a75e1', 'sandy', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_22bf5104f21b', 'sreejakr', 'MEET_567e0361c670', null, null, null, 'keerthykp', '2016-05-20 17:38:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_2356bb04518d', 'user3', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_23bbe7a50adc', 'sheela', 'MEET_12884bd36eec', null, null, null, 'keerthykp', '2016-05-20 16:03:24', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_248352e7d334', 'sanoop', 'MEET_a32cd3bb5a01', null, null, null, 'keerthykp', '2016-05-12 15:27:39', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_248d05b81cd2', 'keerthykp', 'MEET_3b2a2d8ae889', null, 'present', null, 'b', '2016-05-11 17:02:31', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_25c1c43bce8d', 'b', 'MEET_826c189b8022', 'accepted', null, 'null', 'b', '2016-05-06 10:18:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_26d50ba9daca', 'keerthykp', 'MEET_f3c78fb28002', 'accepted', null, null, 'keerthykp', '2016-05-21 11:40:42', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_273f298ccdfd', 'a', 'MEET_0b087335064e', null, null, 'null', 'b', '2016-05-09 19:29:11', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_278aac2a6a6a', 'sreejakr', 'MEET_393c3ffb8694', null, null, null, 'keerthykp', '2016-05-20 16:25:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_282c5b53c313', 'sheela', 'MEET_44553548a622', null, null, null, 'keerthykp', '2016-05-20 16:32:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_28835a583315', 'rahulzoft', 'MEET_9d8bf7d282e7', null, null, null, 'keerthykp', '2016-05-20 15:45:11', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_298d8d5da586', 'anu', 'MEET_3ac22b34afd7', null, null, null, 'keerthykp', '2016-05-20 19:32:01', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_29e5cae3cc23', 'sheela', 'MEET_495c6101ad85', null, null, 'null', 'b', '2016-05-06 10:20:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_2a0c2bf92591', 'sanoop', 'MEET_1e7bf8abee6d', null, null, null, 'b', '2016-05-31 16:46:34', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_2a1c2feca987', 'keerthykp', 'MEET_40352e977d5e', 'accepted', null, 'reason', 'b', '2016-05-11 14:35:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_2aee4c2eccc2', 'sheela', 'MEET_d8e766477181', null, null, null, 'keerthykp', '2016-05-20 17:56:32', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_2b1177db0553', 'bini', 'MEET_05e604ef391d', null, null, null, 'keerthykp', '2016-05-21 13:05:18', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_2b635472e80b', 'user6', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_2b6bee28a050', 'b', 'MEET_b0fa4e4a84b4', null, null, null, 'keerthykp', '2016-05-26 17:25:46', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_2b9969aa4bc6', 'sumeshzoft', 'MEET_2eb3df82d515', null, null, null, 'keerthykp', '2016-05-19 16:36:59', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_2c9c6f277272', 'sanoop', 'MEET_12884bd36eec', null, null, null, 'keerthykp', '2016-05-20 16:03:24', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_2caf5c4845e8', 'keerthykp', 'MEET_770978724442', null, null, 'uh7jh7u', 'b', '2016-05-10 10:40:19', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_2d478b33d722', 'keerthykp', 'MEET_93847d0fc8b6', null, null, null, 'vijay', '2016-05-10 11:47:30', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_2d61c0a83193', 'b', 'MEET_b338d85d0616', 'accepted', 'absent', 'nothng', 'b', '2016-05-10 18:48:00', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_2db40df2a5c7', 'sreejakr', 'MEET_f7ba251c7cb1', null, null, null, 'vijay', '2016-05-21 14:47:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_2e720871d0ef', 'keerthykp', 'MEET_c219056ee583', 'accepted', null, null, 'keerthykp', '2016-05-30 10:36:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_2eae21d30c97', 'a', 'MEET_c2ae59a6ab70', null, null, null, 'vijay', '2016-05-21 14:48:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_2eb122db1716', 'anu', 'MEET_035a1bed1639', null, null, null, 'keerthykp', '2016-05-19 20:04:17', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_2f3b3da541ba', 'sumeshzoft', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:44', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_2f9ddf71180c', 'test', 'MEET_1e7bf8abee6d', null, null, null, 'b', '2016-05-31 16:46:34', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_3123c099dd7a', 'keerthykp', 'MEET_e141a2a351d9', 'accepted', null, null, 'keerthykp', '2016-05-21 11:39:25', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_3149a67e959f', 'rahulzoft', 'MEET_33d8df254641', null, null, null, 'keerthykp', '2016-05-21 09:52:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_3153154c08f4', 'keerthykp', 'MEET_90f8da9d45ee', null, null, null, 'vijay', '2016-05-21 15:14:37', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_315f97d93ec1', 'user8', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_31766ed5ec00', 'sandeep', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_31e891802672', 'vijay', 'MEET_64b4af3fde3b', 'accepted', null, null, 'vijay', '2016-05-21 14:51:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_32718f438882', 'sandeepskrishnan', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_32ab80917909', 'bini', 'MEET_90b5a93cf517', null, null, null, 'b', '2016-05-10 10:16:48', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_32d828f0064d', 'anu', 'MEET_daac10d96378', null, null, null, 'keerthykp', '2016-05-19 19:46:00', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_333aea834b27', 'bini', 'MEET_90f8da9d45ee', null, null, null, 'vijay', '2016-05-21 15:14:37', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_339b75654884', 'sheela', 'MEET_f22b8a9e1574', null, null, null, 'keerthykp', '2016-06-01 09:47:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_3526f53924e3', 'sandeep', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_35f38231ac0e', 'sumeshzoft', 'MEET_b0fa4e4a84b4', null, null, null, 'keerthykp', '2016-05-26 17:25:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_36198e951009', 'keerthykp', 'MEET_ff857d0e7d4c', 'accepted', null, null, 'keerthykp', '2016-05-20 15:59:00', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_364e792f22b1', 'b', 'MEET_1be2b709f9a5', 'accepted', null, null, 'b', '2016-05-12 10:39:26', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_372475b7b2c1', 'user2', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_377c53b1110a', 'rahulzoft', 'MEET_05e604ef391d', null, null, null, 'keerthykp', '2016-05-21 13:05:18', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_38118d736db6', 'sheela', 'MEET_2998a3eeccab', null, null, null, 'keerthykp', '2016-05-21 10:02:39', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_387f6d6a5da5', 'rahulzoft', 'MEET_b0f31e6a39fc', null, null, null, 'keerthykp', '2016-05-12 15:21:20', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_38f950de3654', 'keerthykp', 'MEET_2e1c38571f8c', 'accepted', null, null, 'keerthykp', '2016-05-20 14:07:31', null, null, 'y', 'null');
INSERT INTO `appointment_details` VALUES ('PART_3913b44c9227', 'keerthykp', 'MEET_035a1bed1639', 'accepted', null, null, 'keerthykp', '2016-05-19 20:04:17', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_3954d8fc0561', 'fhyty', 'MEET_825e834e802e', null, null, null, 'keerthykp', '2016-05-31 16:39:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_396d4809001c', 'fhyty', 'MEET_f22b8a9e1574', null, null, null, 'keerthykp', '2016-06-01 09:47:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_397b820ac8f9', 'vijay', 'MEET_f7ba251c7cb1', 'accepted', null, null, 'vijay', '2016-05-21 14:47:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_39c18f2deba6', 'test', 'MEET_288bea8bcf16', null, null, null, 'vijay', '2016-06-01 09:38:28', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_39e88f334ebf', 'vijay', 'MEET_b0fa4e4a84b4', null, null, null, 'keerthykp', '2016-05-26 17:25:46', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_3a373a2c4230', 'sanoop', 'MEET_84716cddecf1', null, null, null, 'b', '2016-05-11 14:33:34', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_3a4e02548540', 'rahulzoft', 'MEET_e2db07020c94', null, null, null, 'b', '2016-05-22 13:01:12', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_3a4efce09faf', 'keerthykp', 'MEET_898e8e9930c5', 'accepted', null, null, 'keerthykp', '2016-05-20 14:05:59', null, null, 'y', '300000');
INSERT INTO `appointment_details` VALUES ('PART_3cad9592b1b0', 'vijay', 'MEET_f22b8a9e1574', null, null, null, 'keerthykp', '2016-06-01 09:47:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_3cb0bc1947ef', 'jitha', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_3d0646356909', 'sheela', 'MEET_a32cd3bb5a01', null, null, null, 'keerthykp', '2016-05-12 15:27:39', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_3db1ea98c390', 'b', 'MEET_54c0f3cb0955', 'accepted', null, null, 'b', '2016-05-22 16:11:50', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_3de241cb0fe8', 'update', 'MEET_825e834e802e', null, null, null, 'keerthykp', '2016-05-31 16:39:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_3e152ec618ae', 'keerthykp', 'MEET_b50ddaaf4c62', 'accepted', null, null, 'keerthykp', '2016-05-31 16:40:44', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_3e71af46636a', 'a', 'MEET_9fdecdaaeda5', 'accepted', 'absent', null, 'b', '2016-05-10 14:46:29', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_3f024da71a44', 'sreejakr', 'MEET_cb96f4b5cf43', null, null, null, 'vijay', '2016-05-26 14:36:29', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_407f10bbb611', 'anu', 'MEET_da2bb163749b', null, null, null, 'keerthykp', '2016-05-21 10:40:10', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_4098474ff58c', 'vijay', 'MEET_cb96f4b5cf43', 'accepted', null, null, 'vijay', '2016-05-26 14:36:29', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_410c17f5bb6a', 'a', 'MEET_a48f93eaaf89', null, null, null, 'keerthykp', '2016-05-20 10:52:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_41626726a3af', 'admin', 'MEET_14a8fb906408', null, null, null, 'keerthykp', '2016-05-27 10:39:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_41a6052f1bfe', 'sreejakr', 'MEET_9fdecdaaeda5', null, 'present', null, 'b', '2016-05-10 14:46:29', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_41d294591ec3', 'b', 'MEET_a857f335bde1', 'accepted', null, null, 'b', '2016-06-20 17:58:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_41e8c93431de', 'sheela', 'MEET_94c2a841191f', null, null, null, 'vijay', '2016-05-21 15:16:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_4210a1d01f2f', 'vijay', 'MEET_4703ec4a8788', null, null, null, 'keerthykp', '2016-05-20 17:08:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_4243073e2a74', 'keerthykp', 'MEET_b0f31e6a39fc', 'accepted', null, null, 'keerthykp', '2016-05-12 15:21:20', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_4243e15eaa0b', 'keerthykp', 'MEET_85a288c8de24', 'accepted', null, null, 'keerthykp', '2016-05-21 10:23:59', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_4248dde9acc0', 'sumeshzoft', 'MEET_5c303b862902', null, null, null, 'keerthykp', '2016-05-26 14:29:52', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_43aa244e3f2c', 'sreejakr', 'MEET_05e604ef391d', null, null, null, 'keerthykp', '2016-05-21 13:05:18', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_43d7e4a8ecd8', 'b', 'MEET_f7ba251c7cb1', null, null, null, 'vijay', '2016-05-21 14:47:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_44511094a6d5', 'keerthykp', 'MEET_5ad86cf3f75c', 'accepted', null, null, 'keerthykp', '2016-05-27 10:35:41', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_4553651167ae', 'b', 'MEET_825e834e802e', null, null, null, 'keerthykp', '2016-05-31 16:39:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_459ac28df823', 'sreejakr', 'MEET_da38506994df', null, null, null, 'b', '2016-05-22 12:17:02', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_45eaa806c708', 'sheela', 'MEET_4703ec4a8788', null, null, null, 'keerthykp', '2016-05-20 17:08:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_472ef6fe4648', 'test', 'MEET_3f2f4e8f6a57', null, null, null, 'keerthykp', '2016-05-21 09:50:22', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_474cd0626a6c', 'anu', 'MEET_217c4b532308', null, null, null, 'keerthykp', '2016-05-21 11:42:46', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_488f0f761679', 'b', 'MEET_4bbacda00e1b', 'accepted', null, null, 'vijay', '2016-05-10 12:14:01', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_48a3a1474ab8', 'fhyty', 'MEET_1e7bf8abee6d', null, null, null, 'b', '2016-05-31 16:46:34', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_48c5aac88408', 'fhyty', 'MEET_b0fa4e4a84b4', null, null, null, 'keerthykp', '2016-05-26 17:25:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_48e0de207a4a', 'rahulzoft', 'MEET_05d2315fa15e', null, 'present', null, 'b', '2016-05-11 16:17:17', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_4993d601d960', 'b', 'MEET_8f8dba0996b7', 'accepted', null, null, 'b', '2016-05-22 14:53:03', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_49ae2f008a6d', 'test', 'MEET_8da037e1ae9f', null, null, null, 'keerthykp', '2016-05-20 11:47:32', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_4a19cb534a54', 'b', 'MEET_b0f31e6a39fc', null, null, null, 'keerthykp', '2016-05-12 15:21:20', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_4aa7bbff6013', 'b', 'MEET_c2ae59a6ab70', null, null, null, 'vijay', '2016-05-21 14:48:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_4b33eb5a516e', 'b', 'MEET_adbcb32d0c2a', null, null, null, 'b', '2016-05-11 14:35:02', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_4b5d26103f88', 'b', 'MEET_a683bcd7f0b7', 'accepted', null, null, 'b', '2016-05-10 14:04:00', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_4be8e7e086b6', 'keerthykp', 'MEET_3ac22b34afd7', 'accepted', null, null, 'keerthykp', '2016-05-20 19:32:01', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_4c505de94e29', 'sheela', 'MEET_90f8da9d45ee', null, null, null, 'vijay', '2016-05-21 15:14:37', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_4c5f1d0ee41e', 'keerthykp', 'MEET_6159dba7901e', 'accepted', null, null, 'keerthykp', '2016-05-20 14:06:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_4e2483a11187', 'vijay', 'MEET_f3c78fb28002', null, null, null, 'keerthykp', '2016-05-21 11:40:42', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_4e993f5daf08', 'jitha', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_4ea2d68cccd9', 'sumeshzoft', 'MEET_2b2e2ae46d84', null, null, null, 'keerthykp', '2016-05-20 15:59:48', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_4ebe9ce710e8', 'admin', 'MEET_ff857d0e7d4c', null, null, null, 'keerthykp', '2016-05-20 15:59:00', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_4f683dfc82a4', 'update', 'MEET_1e7bf8abee6d', null, null, null, 'b', '2016-05-31 16:46:34', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_4fa4ad449dbd', 'keerthykp', 'MEET_9ba0825c3dda', 'accepted', null, null, 'keerthykp', '2016-05-12 15:18:35', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_4ff5a931e31b', 'keerthykp', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5012c27687a1', 'vijay', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5033cae4d7e6', 'sumeshzoft', 'MEET_93847d0fc8b6', null, null, null, 'vijay', '2016-05-10 11:47:30', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_508b8689a66c', 'sheela', 'MEET_d3d2ca8eeff0', null, null, null, 'a', '2016-05-19 12:02:23', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5117f4363a6f', 'keerthykp', 'MEET_2d2b3d79a47c', 'accepted', null, null, 'keerthykp', '2016-05-21 09:48:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5174b5348972', 'sanoop', 'MEET_96b0ac11c0af', null, null, null, 'keerthykp', '2016-05-17 10:28:50', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5192bfcdcfbd', 'sheela', 'MEET_1be2b709f9a5', null, null, null, 'b', '2016-05-12 10:39:26', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_519b28c892fc', 'b', 'MEET_90b5a93cf517', null, null, null, 'b', '2016-05-10 10:16:48', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_51b1405e8049', 'b', 'MEET_d2ebc55dd0a7', 'accepted', null, null, 'b', '2016-05-22 12:48:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_52180947d8b6', 'b', 'MEET_1a7be6f5c061', 'accepted', null, null, 'b', '2016-05-12 10:23:03', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_537d4a682355', 'sumeshzoft', 'MEET_45efd3999453', null, null, null, 'keerthykp', '2016-05-20 15:33:18', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_54adabd17531', 'sheela', 'MEET_2d2b3d79a47c', null, null, null, 'keerthykp', '2016-05-21 09:48:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_559e22f45413', 'gh', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_55f0b89b9437', 'b', 'MEET_58cb206d0d4c', 'accepted', null, null, 'b', '2016-05-21 18:11:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_55fddc47afc5', 'keerthykp', 'MEET_44553548a622', 'accepted', null, null, 'keerthykp', '2016-05-20 16:32:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_564ada502f5e', 'sumeshzoft', 'MEET_00373abaee7d', null, null, null, 'vijay', '2016-05-27 11:38:50', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_56be3273e2a7', 'vijay', 'MEET_825e834e802e', null, null, null, 'keerthykp', '2016-05-31 16:39:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_57cd477dac31', 'b', 'MEET_5b270203b5c4', 'accepted', null, null, 'b', '2016-05-10 18:55:39', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_58084d8bd35a', 'b', 'MEET_23f42a8290bd', 'accepted', null, null, 'b', '2016-05-12 21:53:04', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_581356639ec6', 'keerthykp', 'MEET_96b0ac11c0af', 'accepted', null, null, 'keerthykp', '2016-05-17 10:28:50', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5827d98eb582', 's', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5843f8ea961a', 'admin', 'MEET_3ac22b34afd7', null, null, null, 'keerthykp', '2016-05-20 19:32:01', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_586f8b00923a', 'admin', 'MEET_90f8da9d45ee', null, null, null, 'vijay', '2016-05-21 15:14:37', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_58c383d25f25', 'krishna', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5a38a7b6b3d5', 'sumeshzoft', 'MEET_6159dba7901e', null, null, null, 'keerthykp', '2016-05-20 14:06:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5a4e5dda5cca', 'sheela', 'MEET_aab85c51de75', null, null, 'null', 'b', '2016-05-06 10:22:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5a9507a4292b', 'admin', 'MEET_45ab4dacb7f8', null, null, null, 'b', '2016-05-10 14:48:05', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5ba677e1d4ca', 'a', 'MEET_374fffc26df9', null, null, null, 'keerthykp', '2016-05-19 16:38:11', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5bc647726207', 'vijay', 'MEET_05e604ef391d', null, null, null, 'keerthykp', '2016-05-21 13:05:18', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5c0cd46bdf23', 'sheela', 'MEET_48fab6d56d01', null, null, null, 'keerthykp', '2016-05-21 09:50:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5c0d5ac2e617', 'vijayzoft', 'MEET_826c189b8022', null, null, 'null', 'b', '2016-05-06 10:18:54', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5c4c81863608', 'keerthykp', 'MEET_4b1fb3d562ca', 'accepted', null, null, 'keerthykp', '2016-05-20 16:31:11', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5c696a1f143c', 'b', 'MEET_1e7bf8abee6d', 'accepted', null, null, 'b', '2016-05-31 16:46:34', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5d0412c6da58', 'test', 'MEET_6d70a4aad4cd', null, null, null, 'b', '2016-05-10 18:44:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5d260dc552a3', 'sheela', 'MEET_58cb206d0d4c', null, null, null, 'b', '2016-05-21 18:11:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5d61f9f04d5f', 'sumeshzoft', 'MEET_f4b76cf39505', null, null, 'null', 'b', '2016-05-06 15:31:20', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5deb608e1a75', 'gh', 'MEET_1e7bf8abee6d', null, null, null, 'b', '2016-05-31 16:46:34', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5ebaa6c42b78', 'user3', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5f038cf42329', 'update', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5f52a828bc5b', 'sanoop', 'MEET_90f8da9d45ee', null, null, null, 'vijay', '2016-05-21 15:14:37', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_5fb751c4c662', 'admin', 'MEET_cb96f4b5cf43', null, null, null, 'vijay', '2016-05-26 14:36:29', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_600fc7463791', 'user4', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_600fe96c354b', 'b', 'MEET_8d5e8b5c21e8', null, null, null, 'b', '2016-05-12 10:24:19', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_6014021fa0de', 'sreejakr', 'MEET_8da037e1ae9f', null, null, null, 'keerthykp', '2016-05-20 11:47:32', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_60640303c213', 'b', 'MEET_84716cddecf1', null, null, null, 'b', '2016-05-11 14:33:34', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_6065bb8a6efb', 'anu', 'MEET_13521c8fa8bd', null, null, null, 'keerthykp', '2016-05-20 10:51:19', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_60bf365c311d', 'a', 'MEET_6d70a4aad4cd', 'accepted', null, null, 'b', '2016-05-10 18:44:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_623305794b34', 'sumeshzoft', 'MEET_14a8fb906408', null, null, null, 'keerthykp', '2016-05-27 10:39:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_62719f61d9d8', 'a', 'MEET_85a288c8de24', null, null, null, 'keerthykp', '2016-05-21 10:23:59', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_631fd8070cbe', 'sanoop', 'MEET_f7ba251c7cb1', null, null, null, 'vijay', '2016-05-21 14:47:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_634f4d062f65', 'sreejakr', 'MEET_2b2e2ae46d84', null, null, null, 'keerthykp', '2016-05-20 15:59:48', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_6365faa0a8ee', 'rahulzoft', 'MEET_4bbacda00e1b', null, null, null, 'vijay', '2016-05-10 12:14:01', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_637ef3ce8e1b', 'sumeshzoft', 'MEET_32d0b8578f24', null, null, null, 'keerthykp', '2016-05-20 17:37:44', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_63fada329eb6', 'sheela', 'MEET_1b50ca5bfde7', null, null, null, 'keerthykp', '2016-05-21 10:39:26', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_64098367cd2c', 'user a', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_64219350b1ee', 'sumeshzoft', 'MEET_5ad86cf3f75c', null, null, null, 'keerthykp', '2016-05-27 10:35:41', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_6445e75cf82e', 'vijay', 'MEET_035a1bed1639', null, null, null, 'keerthykp', '2016-05-19 20:04:17', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_64b05aff782e', 'sheela', 'MEET_da2bb163749b', null, null, null, 'keerthykp', '2016-05-21 10:40:10', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_64ba7224982b', 'keerthykp', 'MEET_825e834e802e', 'accepted', null, null, 'keerthykp', '2016-05-31 16:39:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_64dfe6a0acf1', 'test', 'MEET_14a8fb906408', null, null, null, 'keerthykp', '2016-05-27 10:39:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_652500dd8333', 'vijay', 'MEET_daac10d96378', null, null, null, 'keerthykp', '2016-05-19 19:46:00', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_653baf50d8e9', 'b', 'MEET_a96e59031838', 'accepted', null, 'null', 'b', '2016-05-06 10:17:32', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_65509fd0ea37', 'sreejakr', 'MEET_5ad86cf3f75c', null, null, null, 'keerthykp', '2016-05-27 10:35:40', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_657472015da5', 'sreejakr', 'MEET_064a4f090797', null, null, null, 'a', '2016-05-19 12:01:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_65e23c38b52d', 'vijay', 'MEET_c2ae59a6ab70', 'accepted', null, null, 'vijay', '2016-05-21 14:48:44', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_66445529bdfd', 'b', 'MEET_baa8a39e82a1', 'accepted', null, null, 'b', '2016-05-22 13:10:38', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_66bf363abf88', 'sanoop', 'MEET_64b4af3fde3b', null, null, null, 'vijay', '2016-05-21 14:51:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_66fd4916cf16', 'user5', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_675d17f5e41c', 'b', 'MEET_0823609ba7f4', 'accepted', 'present', 'reason for test meeting 2', 'b', '2016-05-10 18:16:59', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_67ae46f005d1', 'rahulzoft', 'MEET_90f8da9d45ee', null, null, null, 'vijay', '2016-05-21 15:14:37', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_681d7ad951d5', 'sumeshzoft', 'MEET_53dcf47e1e4d', null, null, null, 'keerthykp', '2016-05-20 19:33:59', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_68340b7a8898', 'sumeshzoft', 'MEET_23f42a8290bd', null, null, null, 'b', '2016-05-12 21:53:04', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_6ade1630fcdc', 'b', 'MEET_f3c78fb28002', null, null, null, 'keerthykp', '2016-05-21 11:40:42', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_6affc332d552', 'test', 'MEET_a32cd3bb5a01', null, null, null, 'keerthykp', '2016-05-12 15:27:39', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_6c9049bebba7', 'sanoop', 'MEET_567e0361c670', null, null, null, 'keerthykp', '2016-05-20 17:38:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_6d285fab59c9', 'sanoop', 'MEET_495c6101ad85', null, null, 'null', 'b', '2016-05-06 10:20:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_6d7014046eb1', 'keerthykp', 'MEET_fbdef93424f6', 'accepted', null, 'null', 'b', '2016-05-06 10:15:30', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_6d8c9a97fc2f', 'keerthykp', 'MEET_aab85c51de75', null, null, 'null', 'b', '2016-05-06 10:22:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_6d8ee0c66f83', 'sanoop', 'MEET_f22b8a9e1574', null, null, null, 'keerthykp', '2016-06-01 09:47:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_6dd98e3440aa', 'sheela', 'MEET_f7ba251c7cb1', null, null, null, 'vijay', '2016-05-21 14:47:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_6ea29e94e6d2', 'rahulzoft', 'MEET_f7ba251c7cb1', null, null, null, 'vijay', '2016-05-21 14:47:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_6ec9a832aaa9', 'vijay', 'MEET_94c2a841191f', 'accepted', null, null, 'vijay', '2016-05-21 15:16:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_6ee04d1a01ca', 'sanoop', 'MEET_ca5694d4b013', null, null, null, 'vijay', '2016-05-10 12:44:50', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_6f01b95b1c4a', 'sumeshzoft', 'MEET_cb96f4b5cf43', null, null, null, 'vijay', '2016-05-26 14:36:29', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_6f30fc1fb446', 'admin', 'MEET_05e604ef391d', null, null, null, 'keerthykp', '2016-05-21 13:05:18', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_6f68b773b5f1', 'b', 'MEET_57cfb8faac86', 'accepted', null, null, 'b', '2016-05-11 09:34:14', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_7058043549f1', 'sanoop', 'MEET_288bea8bcf16', null, null, null, 'vijay', '2016-06-01 09:38:28', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_70698a173f84', 'b', 'MEET_811cbab3e838', 'accepted', null, null, 'b', '2016-05-10 18:11:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_70a305e37f2d', 'anu', 'MEET_6d70a4aad4cd', null, null, null, 'b', '2016-05-10 18:44:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_70cf322841e1', 'anu', 'MEET_45ab4dacb7f8', null, null, null, 'b', '2016-05-10 14:48:05', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_716e23b59b6a', 'keerthykp', 'MEET_2eb3df82d515', 'accepted', null, null, 'keerthykp', '2016-05-19 16:36:59', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_717788da6965', 'keerthykp', 'MEET_567e0361c670', 'accepted', null, null, 'keerthykp', '2016-05-20 17:38:58', null, null, 'y', '60000');
INSERT INTO `appointment_details` VALUES ('PART_728e79548f28', 'sumeshzoft', 'MEET_8da037e1ae9f', null, null, null, 'keerthykp', '2016-05-20 11:47:32', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_72e179f8857e', 'keerthykp', 'MEET_5b270203b5c4', null, null, 'dont like', 'b', '2016-05-10 18:55:39', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_732886f08e49', 'rahulzoft', 'MEET_c2ae59a6ab70', null, null, null, 'vijay', '2016-05-21 14:48:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_74020d2f8f79', 'anu', 'MEET_48fab6d56d01', null, null, null, 'keerthykp', '2016-05-21 09:50:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_74356bbf1239', 'test', 'MEET_f22b8a9e1574', null, null, null, 'keerthykp', '2016-06-01 09:47:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_74460f40a963', 'vijay', 'MEET_2ae4646e7d1b', 'accepted', null, null, 'vijay', '2016-05-21 15:02:31', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_7481f227e7e5', 'test', 'MEET_5ad86cf3f75c', null, null, null, 'keerthykp', '2016-05-27 10:35:41', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_74bddb22bb6d', 'sumeshzoft', 'MEET_e141a2a351d9', null, null, null, 'keerthykp', '2016-05-21 11:39:25', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_74d87561c97a', 'keerthykp', 'MEET_2ada71eb0d20', 'accepted', null, null, 'keerthykp', '2016-05-20 18:10:35', null, null, 'y', '120000');
INSERT INTO `appointment_details` VALUES ('PART_7532086356f5', 'sheela', 'MEET_6d70a4aad4cd', null, null, null, 'b', '2016-05-10 18:44:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_75377a1eff4c', 'b', 'MEET_4703ec4a8788', null, null, null, 'keerthykp', '2016-05-20 17:08:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_757492f762b4', 'rahulzoft', 'MEET_ca5694d4b013', null, null, null, 'vijay', '2016-05-10 12:44:50', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_759611832ae4', 'sanoop', 'MEET_657718c1bd1f', null, null, null, 'keerthykp', '2016-05-20 11:49:42', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_7638a950e1ce', 'sumeshzoft', 'MEET_daac10d96378', null, null, null, 'keerthykp', '2016-05-19 19:46:00', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_768033d39b36', 'keerthykp', 'MEET_9d8bf7d282e7', 'accepted', null, null, 'keerthykp', '2016-05-20 15:45:11', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_770105672d90', 'bini', 'MEET_8097a5d9aa6e', null, null, null, 'keerthykp', '2016-05-21 10:28:06', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_770121dede42', 'keerthykp', 'MEET_fa8cc84bb2b2', 'accepted', null, null, 'keerthykp', '2016-05-12 15:20:12', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_7862f1e19ba9', 'sreejakr', 'MEET_825e834e802e', null, null, null, 'keerthykp', '2016-05-31 16:39:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_78b428a70a84', 'keerthykp', 'MEET_b0fa4e4a84b4', 'accepted', null, null, 'keerthykp', '2016-05-26 17:25:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_7971054d5e45', 'b', 'MEET_0b087335064e', 'accepted', null, 'null', 'b', '2016-05-09 19:29:11', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_7995b8f6aaad', 'update', 'MEET_5ad86cf3f75c', null, null, null, 'keerthykp', '2016-05-27 10:35:40', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_7a2c5a05d6aa', 'b', 'MEET_f4b76cf39505', null, null, 'null', 'b', '2016-05-06 15:31:20', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_7a2ce7966f04', 'user14', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_7a59c01282b3', 'keerthykp', 'MEET_adbcb32d0c2a', 'accepted', null, null, 'b', '2016-05-11 14:35:02', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_7a7d2a67bc9d', 'admin', 'MEET_217c4b532308', null, null, null, 'keerthykp', '2016-05-21 11:42:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_7ba30d36f7ad', 'sheela', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:44', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_7c14ee3b108c', 'sreejakr', 'MEET_13521c8fa8bd', null, null, null, 'keerthykp', '2016-05-20 10:51:19', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_7c534d759bdf', 'sanoop', 'MEET_cb96f4b5cf43', null, null, null, 'vijay', '2016-05-26 14:36:29', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_7dae9f4a3454', 'b', 'MEET_6d70a4aad4cd', 'accepted', null, null, 'b', '2016-05-10 18:44:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_7e402ee7a659', 'keerthykp', 'MEET_f22b8a9e1574', 'accepted', null, null, 'keerthykp', '2016-06-01 09:47:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_7e4e2d086aa4', 'sreejakr', 'MEET_770978724442', null, null, null, 'b', '2016-05-10 10:40:19', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_7e8f2441cf49', 'sreejakr', 'MEET_90f8da9d45ee', null, null, null, 'vijay', '2016-05-21 15:14:37', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_7eedff6002b4', 'keerthykp', 'MEET_36366e05438c', 'accepted', null, null, 'keerthykp', '2016-05-20 19:31:22', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_7ef1cba9b944', 'sanoop', 'MEET_b866307060aa', 'cancelled', null, null, 'keerthykp', '2016-05-30 10:37:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_7f0ef8fade0b', 'sanoop', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_7f8c3abc49f4', 'keerthykp', 'MEET_2b2e2ae46d84', 'accepted', null, null, 'keerthykp', '2016-05-20 15:59:48', null, null, 'y', '60000');
INSERT INTO `appointment_details` VALUES ('PART_80cf11279be8', 'a', 'MEET_1b98ca8c99a1', null, null, null, 'b', '2016-05-10 14:42:33', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_81231011f700', 'sreejakr', 'MEET_035a1bed1639', null, null, null, 'keerthykp', '2016-05-19 20:04:17', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_812fbc4cb687', 'keerthykp', 'MEET_217c4b532308', 'accepted', null, null, 'keerthykp', '2016-05-21 11:42:46', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_824b9c78a28f', 'b', 'MEET_6426132a06d3', null, null, 'null', 'b', '2016-05-09 09:39:36', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_82970f3695d9', 'sreejakr', 'MEET_0c5b431f06fa', null, null, null, 'keerthykp', '2016-05-21 11:39:59', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_8394ebc4b074', 'sheela', 'MEET_770978724442', null, null, null, 'b', '2016-05-10 10:40:19', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_8435c52cc11e', 'b', 'MEET_2654114382dc', 'accepted', null, null, 'keerthykp', '2016-05-12 15:30:48', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_849a4c0a262f', 'anu', 'MEET_90f8da9d45ee', null, null, null, 'vijay', '2016-05-21 15:14:37', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_84b0f097d182', 'a', 'MEET_d3d2ca8eeff0', 'accepted', null, null, 'a', '2016-05-19 12:02:23', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_84fa231c878f', 'b', 'MEET_1f6d36ea01a5', 'accepted', 'absent', null, 'b', '2016-05-11 17:01:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_852c972d6d11', 'rahulzoft', 'MEET_143011ba99c2', null, null, null, 'b', '2016-05-22 13:12:52', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_8543afd5a3a7', 'sreejakr', 'MEET_3647a4325771', null, null, null, 'b', '2016-05-22 12:53:42', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_8641683fc7b7', 'vijay', 'MEET_1e7bf8abee6d', null, null, null, 'b', '2016-05-31 16:46:34', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_86547d5c1e3d', 'sheela', 'MEET_6159dba7901e', null, null, null, 'keerthykp', '2016-05-20 14:06:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_866253920fda', 'vijay', 'MEET_85a288c8de24', null, null, null, 'keerthykp', '2016-05-21 10:23:59', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_874207e501a7', 'user5', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_87cd2999a9f0', 'sumeshzoft', 'MEET_770978724442', null, null, null, 'b', '2016-05-10 10:40:19', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_87e5def56467', 'admin', 'MEET_35519d39e3ee', null, null, null, 'b', '2016-05-10 15:03:23', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_8808ba5f0250', 'sheela', 'MEET_05d2315fa15e', null, 'absent', null, 'b', '2016-05-11 16:17:17', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_881e21cfd5dc', 'sreejakr', 'MEET_00373abaee7d', null, null, null, 'vijay', '2016-05-27 11:38:50', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_888899650aa8', 'b', 'MEET_217c4b532308', null, null, null, 'keerthykp', '2016-05-21 11:42:46', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_8aaca6fbd858', 'sumeshzoft', 'MEET_aeb8ef5ede3c', null, null, 'null', 'b', '2016-05-06 10:21:17', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_8b111d9bf5cb', 'anu', 'MEET_65c0bbe7d345', null, null, null, 'keerthykp', '2016-05-20 18:25:40', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_8b6886ad3156', 'sheela', 'MEET_45efd3999453', null, null, null, 'keerthykp', '2016-05-20 15:33:18', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_8bfcec1d7207', 'sreejakr', 'MEET_1a7be6f5c061', null, null, null, 'b', '2016-05-12 10:23:03', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_8c7d1fab50c9', 'a', 'MEET_958315c6f8ab', null, null, 'null', 'b', '2016-05-06 15:32:36', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_8ceffa8c5df5', 'sreejakr', 'MEET_f22b8a9e1574', null, null, null, 'keerthykp', '2016-06-01 09:47:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_8d11af954928', 'sreejakr', 'MEET_daac10d96378', null, null, null, 'keerthykp', '2016-05-19 19:46:00', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_8d9af4a175c4', 'admin', 'MEET_37e49f83d27a', null, null, null, 'b', '2016-05-10 18:06:35', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_8de884a174e0', 'keerthykp', 'MEET_a48f93eaaf89', 'accepted', null, null, 'keerthykp', '2016-05-20 10:52:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_8e09f7b06e72', 'rahulzoft', 'MEET_4703ec4a8788', null, null, null, 'keerthykp', '2016-05-20 17:08:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_8e5644c3dbcf', 'sheela', 'MEET_811cbab3e838', null, null, null, 'b', '2016-05-10 18:11:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_8e85a85af9e8', 'rahulzoft', 'MEET_adbcb32d0c2a', null, null, null, 'b', '2016-05-11 14:35:02', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_8ee40157140b', 'sreejakr', 'MEET_1b98ca8c99a1', null, null, null, 'b', '2016-05-10 14:42:33', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_905bb55a7bd2', 'sumeshzoft', 'MEET_d8e766477181', null, null, null, 'keerthykp', '2016-05-20 17:56:32', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_9077ac2efb36', 'sheela', 'MEET_f22471fed830', null, null, null, 'b', '2016-05-11 16:16:26', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_909a284331a4', 'a', 'MEET_a96e59031838', 'accepted', null, 'null', 'b', '2016-05-06 10:17:32', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_90b50744e1e3', 'vijay', 'MEET_ca5694d4b013', null, null, null, 'vijay', '2016-05-10 12:44:50', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_9167ab526298', 'a', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_917d5afacfcf', 'sreejakr', 'MEET_d2ebc55dd0a7', null, null, null, 'b', '2016-05-22 12:48:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_922ee4dd7995', 'sandeepskrishnan', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_92ea4dd8f1a1', 'a', 'MEET_35519d39e3ee', null, null, null, 'b', '2016-05-10 15:03:23', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_92f7da0d16c1', 'test', 'MEET_da28712149b8', null, null, 'null', 'b', '2016-05-06 10:16:37', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_93363ac285b9', 'sumeshzoft', 'MEET_567e0361c670', null, null, null, 'keerthykp', '2016-05-20 17:38:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_93412f01e103', 'keerthykp', 'MEET_57cfb8faac86', 'accepted', null, null, 'b', '2016-05-11 09:34:14', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_93e36fb8bc04', 'keerthykp', 'MEET_2ae4646e7d1b', null, null, null, 'vijay', '2016-05-21 15:02:31', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_942fe45af9de', 'b', 'MEET_28974626dcc7', 'accepted', null, null, 'b', '2016-05-22 17:59:47', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_948069611cae', 'fhyty', 'MEET_5ad86cf3f75c', null, null, null, 'keerthykp', '2016-05-27 10:35:40', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_948a68b210fd', 'sumeshzoft', 'MEET_4703ec4a8788', null, null, null, 'keerthykp', '2016-05-20 17:08:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_96be54d7d25a', 'b', 'MEET_d6e20a44524f', 'accepted', null, null, 'b', '2016-05-22 12:49:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_96c8ab46f772', 'keerthykp', 'MEET_826c189b8022', 'accepted', null, 'null', 'b', '2016-05-06 10:18:54', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_97059a5ca018', 'bini', 'MEET_2eb3df82d515', null, null, null, 'keerthykp', '2016-05-19 16:36:59', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_97540059a8a1', 'sheela', 'MEET_05e604ef391d', null, null, null, 'keerthykp', '2016-05-21 13:05:18', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_975f38cf75d2', 'sreejakr', 'MEET_2eb3df82d515', null, null, null, 'keerthykp', '2016-05-19 16:36:59', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_97ee7e60c4ff', 'vijay', 'MEET_217c4b532308', null, null, null, 'keerthykp', '2016-05-21 11:42:46', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_97f789e09e59', 'sreejakr', 'MEET_84716cddecf1', null, null, null, 'b', '2016-05-11 14:33:34', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_98bfc81be715', 'bini', 'MEET_93847d0fc8b6', null, null, null, 'vijay', '2016-05-10 11:47:31', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_98fce55e5234', 'admin', 'MEET_5c303b862902', null, null, null, 'keerthykp', '2016-05-26 14:29:52', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_99262eacf96b', 'test', 'MEET_90f8da9d45ee', null, null, null, 'vijay', '2016-05-21 15:14:37', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_99571233146b', 'keerthykp', 'MEET_48fab6d56d01', 'accepted', null, null, 'keerthykp', '2016-05-21 09:50:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_9af2534ca2bf', 'update', 'MEET_288bea8bcf16', null, null, null, 'vijay', '2016-06-01 09:38:28', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_9c031a03f98e', 'anu', 'MEET_4703ec4a8788', null, null, null, 'keerthykp', '2016-05-20 17:08:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_9c62c1c43c5c', 'sanoop', 'MEET_2eb3df82d515', null, null, null, 'keerthykp', '2016-05-19 16:36:59', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_9cc4dd9a62fe', 'b', 'MEET_3647a4325771', 'accepted', null, null, 'b', '2016-05-22 12:53:42', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_9cee485abb03', 'admin', 'MEET_00373abaee7d', null, null, null, 'vijay', '2016-05-27 11:38:50', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_9d9374160b2d', 'sanoop', 'MEET_93847d0fc8b6', null, null, null, 'vijay', '2016-05-10 11:47:30', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_9da586e992cf', 'b', 'MEET_246be999d114', 'accepted', null, null, 'b', '2016-05-22 13:07:31', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_9dacaf469d30', 'sreejakr', 'MEET_da28712149b8', null, null, 'null', 'b', '2016-05-06 10:16:37', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_9e4f5b308b93', 'vijay', 'MEET_93847d0fc8b6', null, null, null, 'vijay', '2016-05-10 11:47:31', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_9e52509faa9d', 'sheela', 'MEET_c2ae59a6ab70', null, null, null, 'vijay', '2016-05-21 14:48:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_9ebc8da38bb3', 'sheela', 'MEET_24584a4d4f56', null, null, null, 'b', '2016-05-22 12:33:17', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_9ed002669d11', 'update', 'MEET_14a8fb906408', null, null, null, 'keerthykp', '2016-05-27 10:39:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_9ef4a659309c', 'sandeepsk', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a0284715e6d7', 'sreejakr', 'MEET_5bc4c022699c', null, null, null, 'keerthykp', '2016-05-20 16:26:35', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a0689306a191', 'sreejakr', 'MEET_b50ddaaf4c62', null, null, null, 'keerthykp', '2016-05-31 16:40:44', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a08c9f10f789', 'sumeshzoft', 'MEET_6d70a4aad4cd', null, null, null, 'b', '2016-05-10 18:44:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a0afd80692a6', 'a', 'MEET_23f42a8290bd', 'accepted', null, null, 'b', '2016-05-12 21:53:04', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a16bf6cfd00e', 'keerthykp', 'MEET_fe2d4a8b117b', 'accepted', null, null, 'keerthykp', '2016-05-20 19:47:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a1d2437cca29', 'sheela', 'MEET_cb96f4b5cf43', null, null, null, 'vijay', '2016-05-26 14:36:29', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a241da910414', 'admin', 'MEET_1e7bf8abee6d', null, null, null, 'b', '2016-05-31 16:46:34', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a24daacf01da', 'b', 'MEET_881b9555957d', 'accepted', null, null, 'b', '2016-05-22 13:05:06', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a2bd5d0fbe70', 'a', 'MEET_05e604ef391d', null, null, null, 'keerthykp', '2016-05-21 13:05:18', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a302867686e8', 'keerthykp', 'MEET_0328603674d5', 'accepted', null, null, 'keerthykp', '2016-05-19 15:58:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a303b3fd50c0', 'sanoop', 'MEET_a48f93eaaf89', null, null, null, 'keerthykp', '2016-05-20 10:52:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a35989fc069c', 's', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a35c2a8eedc5', 'sreejakr', 'MEET_4703ec4a8788', null, null, null, 'keerthykp', '2016-05-20 17:08:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a41e8889606c', 'sheela', 'MEET_246be999d114', null, null, null, 'b', '2016-05-22 13:07:31', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a47575e10ed3', 'test', 'MEET_e174f76a0e89', null, null, null, 'keerthykp', '2016-05-20 19:38:03', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a4781d7eedb5', 'rahulzoft', 'MEET_ff857d0e7d4c', null, null, null, 'keerthykp', '2016-05-20 15:59:00', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a4c5399f9581', 'b', 'MEET_f22b8a9e1574', null, null, null, 'keerthykp', '2016-06-01 09:47:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a55148dec583', 'anu', 'MEET_958315c6f8ab', null, null, 'null', 'b', '2016-05-06 15:32:36', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a5cc2e9f87ce', 'sreejakr', 'MEET_d6e20a44524f', null, null, null, 'b', '2016-05-22 12:49:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a64c17d74892', 'sreejakr', 'MEET_0328603674d5', null, null, null, 'keerthykp', '2016-05-19 15:58:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a685589e5b99', 'sanoop', 'MEET_825e834e802e', null, null, null, 'keerthykp', '2016-05-31 16:39:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a6a53103cb93', 'sanoop', 'MEET_0328603674d5', null, null, null, 'keerthykp', '2016-05-19 15:58:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a6a5550ac043', 'sheela', 'MEET_305f48c5cf2a', null, null, null, 'b', '2016-05-22 12:51:41', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a6e8c52f5f36', 'b', 'MEET_f22471fed830', null, null, null, 'b', '2016-05-11 16:16:26', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a7370ba94af5', 'sumeshzoft', 'MEET_ca5694d4b013', null, null, null, 'vijay', '2016-05-10 12:44:50', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a73815cbe1fa', 'rahulzoft', 'MEET_2ae4646e7d1b', null, null, null, 'vijay', '2016-05-21 15:02:31', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a7f266323642', 'anu', 'MEET_c17c0e431f11', null, null, null, 'keerthykp', '2016-05-20 19:32:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a801384ed531', 'anu', 'MEET_45efd3999453', null, null, null, 'keerthykp', '2016-05-20 15:33:18', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a83758f4c51b', 'sreejakr', 'MEET_c2ae59a6ab70', null, null, null, 'vijay', '2016-05-21 14:48:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a8ee58115fae', 'gh', 'MEET_288bea8bcf16', null, null, null, 'vijay', '2016-06-01 09:38:28', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a9049a9a7a82', 'keerthykp', 'MEET_53dcf47e1e4d', 'accepted', null, null, 'keerthykp', '2016-05-20 19:33:59', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_a964c131a97a', 'bini', 'MEET_6159dba7901e', null, null, null, 'keerthykp', '2016-05-20 14:06:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_aa2de05c089c', 'sheela', 'MEET_217c4b532308', null, null, null, 'keerthykp', '2016-05-21 11:42:46', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_aa309b3abaef', 'anu', 'MEET_da28712149b8', null, null, 'null', 'b', '2016-05-06 10:16:37', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_aa5e6cb0c7f6', 'sumeshzoft', 'MEET_94c2a841191f', null, null, null, 'vijay', '2016-05-21 15:16:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_aa5fb18b2887', 'user4', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_aa866f71746f', 'sreejakr', 'MEET_23f42a8290bd', null, null, null, 'b', '2016-05-12 21:53:04', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_aabfaf4eb2fe', 'sanoop', 'MEET_b0f31e6a39fc', null, null, null, 'keerthykp', '2016-05-12 15:21:20', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ab03efb6d4ac', 'sheela', 'MEET_93847d0fc8b6', null, null, null, 'vijay', '2016-05-10 11:47:30', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ab1dadf0104d', 'vijay', 'MEET_14a8fb906408', null, null, null, 'keerthykp', '2016-05-27 10:39:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ac228725a8f0', 'sreejakr', 'MEET_6d70a4aad4cd', null, null, null, 'b', '2016-05-10 18:44:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ac59509f9322', 'sreejakr', 'MEET_c120d9e1328e', null, null, null, 'keerthykp', '2016-05-27 10:37:15', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ac77d5f3e7fc', 'test', 'MEET_05e604ef391d', null, null, null, 'keerthykp', '2016-05-21 13:05:18', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ad42d1fe9c17', 'keerthykp', 'MEET_c120d9e1328e', 'accepted', null, null, 'keerthykp', '2016-05-27 10:37:15', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ad4d94ea139d', 'test', 'MEET_825e834e802e', null, null, null, 'keerthykp', '2016-05-31 16:39:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ad71caa8aeef', 'fhyty', 'MEET_cb96f4b5cf43', null, null, null, 'vijay', '2016-05-26 14:36:29', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_add45dae09a3', 'sumeshzoft', 'MEET_168c88015001', null, null, null, 'keerthykp', '2016-05-20 16:10:31', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ae2f21edfe59', 'b', 'MEET_820fa59850f4', 'accepted', null, null, 'b', '2016-05-22 16:30:02', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_aea8426f8868', 'admin', 'MEET_1517ba5b3df4', null, null, null, 'b', '2016-05-13 19:14:12', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_af74c65846db', 'sanoop', 'MEET_f22471fed830', null, null, null, 'b', '2016-05-11 16:16:26', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_af765a899628', 'sumeshzoft', 'MEET_035a1bed1639', null, null, null, 'keerthykp', '2016-05-19 20:04:17', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_af80fd73067b', 'sumeshzoft', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b00f1711936f', 'rahulzoft', 'MEET_fe2d4a8b117b', null, null, null, 'keerthykp', '2016-05-20 19:47:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b01d72a65fd5', 'sanoop', 'MEET_fa8cc84bb2b2', null, null, null, 'keerthykp', '2016-05-12 15:20:12', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b0afc2287f9d', 'rahulzoft', 'MEET_8d5e8b5c21e8', null, null, null, 'b', '2016-05-12 10:24:19', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b0afd5c1e2a4', 'sanoop', 'MEET_b0fa4e4a84b4', null, null, null, 'keerthykp', '2016-05-26 17:25:46', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b0cf3ece590e', 'sumeshzoft', 'MEET_e2d66d235092', null, null, null, 'keerthykp', '2016-05-19 12:35:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b0e122e6a306', 'keerthykp', 'MEET_00373abaee7d', 'accepted', null, null, 'vijay', '2016-05-27 11:38:50', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b0e2707e1cb8', 'ss', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b0fd6809bb4f', 'admin', 'MEET_f22b8a9e1574', null, null, null, 'keerthykp', '2016-06-01 09:47:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b138863008ad', 'keerthykp', 'MEET_daac10d96378', 'accepted', null, null, 'keerthykp', '2016-05-19 19:46:00', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b16602190c22', 'newuser', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b18c112f4cf8', 'update', 'MEET_cb96f4b5cf43', null, null, null, 'vijay', '2016-05-26 14:36:29', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b1a4336bc826', 'sreejakr', 'MEET_f3c78fb28002', null, null, null, 'keerthykp', '2016-05-21 11:40:42', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b1d54fc67ea2', 'bini', 'MEET_3b2a2d8ae889', null, 'present', null, 'b', '2016-05-11 17:02:31', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b1ecfe7ab5cd', 'anu', 'MEET_f4b76cf39505', null, null, 'null', 'b', '2016-05-06 15:31:20', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b1eefd415829', 'user8', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b203f6511242', 'keerthykp', 'MEET_374fffc26df9', 'accepted', null, null, 'keerthykp', '2016-05-19 16:38:11', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b255f3f40237', 'vijay', 'MEET_5ad86cf3f75c', null, null, null, 'keerthykp', '2016-05-27 10:35:41', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b28d5bed890f', 'rahulzoft', 'MEET_2f3ff03d9820', null, null, null, 'b', '2016-05-22 12:26:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b35096c252af', 'anu', 'MEET_898e8e9930c5', null, null, null, 'keerthykp', '2016-05-20 14:05:59', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b36079f047ef', 'b', 'MEET_da28712149b8', 'accepted', null, 'null', 'b', '2016-05-06 10:16:37', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b3660d7c9210', 'test', 'MEET_4703ec4a8788', null, null, null, 'keerthykp', '2016-05-20 17:08:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b396bd8a703a', 'user7', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b3cd9eaaf999', 'anu', 'MEET_a96e59031838', null, null, 'null', 'b', '2016-05-06 10:17:32', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b3f622c1738a', 'keerthykp', 'MEET_f7ba251c7cb1', null, null, null, 'vijay', '2016-05-21 14:47:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b484e88fd6ae', 'user6', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b4ac4dcd8fdf', 'bini', 'MEET_657718c1bd1f', null, null, null, 'keerthykp', '2016-05-20 11:49:42', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b4ba0864104a', 'keerthykp', 'MEET_8097a5d9aa6e', 'accepted', null, null, 'keerthykp', '2016-05-21 10:28:06', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b5129bec6da2', 'sreejakr', 'MEET_9fdecdaaeda5', null, 'present', 'null', 'b', '2016-05-06 15:31:20', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b5bfac32adc8', 'a', 'MEET_4703ec4a8788', null, null, null, 'keerthykp', '2016-05-20 17:08:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b5d7fd36abbb', 'vijay', 'MEET_4bbacda00e1b', null, null, null, 'vijay', '2016-05-10 12:14:01', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b6997f516c7e', 'sanoop', 'MEET_2e1c38571f8c', null, null, null, 'keerthykp', '2016-05-20 14:07:31', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b6b23e16d63d', 'rahulzoft', 'MEET_881b9555957d', null, null, null, 'b', '2016-05-22 13:05:06', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b737de4eb30e', 'anu', 'MEET_6426132a06d3', null, null, 'null', 'b', '2016-05-09 09:39:36', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b77c57109ce3', 'a', 'MEET_0328603674d5', null, null, null, 'keerthykp', '2016-05-19 15:58:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b7878d688822', 'user2', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b7a1e162d8a6', 'keerthykp', 'MEET_c2ae59a6ab70', null, null, null, 'vijay', '2016-05-21 14:48:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b89180bdc7a5', 'sreejakr', 'MEET_1fc1c8d70455', null, null, null, 'b', '2016-05-21 18:55:30', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b8c708d6081e', 'b', 'MEET_770978724442', 'accepted', null, null, 'b', '2016-05-10 10:40:19', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b93068d86e76', 'b', 'MEET_305f48c5cf2a', 'accepted', null, null, 'b', '2016-05-22 12:51:41', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b993d5f026b7', 'sreejakr', 'MEET_3a0285858dfc', null, null, null, 'b', '2016-05-22 12:08:00', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b998cbce6c73', 'keerthykp', 'MEET_14a8fb906408', 'accepted', null, null, 'keerthykp', '2016-05-27 10:39:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b9b90845719f', 'rahulzoft', 'MEET_36366e05438c', null, null, null, 'keerthykp', '2016-05-20 19:31:22', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b9c2248dd98c', 'a', 'MEET_3b2a2d8ae889', 'accepted', 'present', null, 'b', '2016-05-11 17:02:31', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b9c81a155e95', 'rahulzoft', 'MEET_fa8cc84bb2b2', null, null, null, 'keerthykp', '2016-05-12 15:20:12', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_b9cbd225f5a4', 'sheela', 'MEET_2e1c38571f8c', null, null, null, 'keerthykp', '2016-05-20 14:07:31', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_bbcbe3eb2c07', 'vijay', 'MEET_288bea8bcf16', 'accepted', null, null, 'vijay', '2016-06-01 09:38:28', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_bc8e03eadd99', 'sumeshzoft', 'MEET_9ba0825c3dda', null, null, null, 'keerthykp', '2016-05-12 15:18:35', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_bd6865b8edf1', 'keerthykp', 'MEET_84716cddecf1', null, null, null, 'b', '2016-05-11 14:33:34', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_bdebfb01b6c5', 'sreejakr', 'MEET_93847d0fc8b6', null, null, null, 'vijay', '2016-05-10 11:47:30', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_bf7e35dc8d50', 'admin', 'MEET_a96e59031838', 'accepted', null, 'null', 'b', '2016-05-06 10:17:32', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c01de8d9b99b', 'sumeshzoft', 'MEET_288bea8bcf16', null, null, null, 'vijay', '2016-06-01 09:38:28', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c06f0cb49d9b', 'vijay', 'MEET_5c303b862902', 'accepted', null, null, 'keerthykp', '2016-05-26 14:29:52', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c08f58c35ae5', 'sheela', 'MEET_b0f31e6a39fc', null, null, null, 'keerthykp', '2016-05-12 15:21:20', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c1b1c8dfb045', 'test', 'MEET_c120d9e1328e', null, null, null, 'keerthykp', '2016-05-27 10:37:15', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c1b38933a42f', 'sandy', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c2bab4e72318', 'keerthykp', 'MEET_168c88015001', 'accepted', null, null, 'keerthykp', '2016-05-20 16:10:31', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c2bbc1e728a2', 'b', 'MEET_288bea8bcf16', null, null, null, 'vijay', '2016-06-01 09:38:28', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c3021e7b9556', 'gh', 'MEET_f22b8a9e1574', null, null, null, 'keerthykp', '2016-06-01 09:47:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c35cff33db31', 'sreejakr', 'MEET_958315c6f8ab', null, null, 'null', 'b', '2016-05-06 15:32:36', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c4d57ac4dc7d', 'b', 'MEET_d69fdb48bea9', 'accepted', null, null, 'b', '2016-05-22 12:10:13', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c4e57e26df50', 'admin', 'MEET_6d70a4aad4cd', null, null, null, 'b', '2016-05-10 18:44:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c4eea74cf509', 'keerthykp', 'MEET_3f2f4e8f6a57', 'accepted', null, null, 'keerthykp', '2016-05-21 09:50:22', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c4ef76c4b168', 'admin', 'MEET_825e834e802e', null, null, null, 'keerthykp', '2016-05-31 16:39:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c5414a451b74', 'sumeshzoft', 'MEET_825e834e802e', null, null, null, 'keerthykp', '2016-05-31 16:39:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c5780e2de67f', 'b', 'MEET_45ab4dacb7f8', 'accepted', null, null, 'b', '2016-05-10 14:48:05', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c5e93ea06268', 'a', 'MEET_f7ba251c7cb1', null, null, null, 'vijay', '2016-05-21 14:47:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c6a2d27b3cb9', 'admin', 'MEET_e141a2a351d9', null, null, null, 'keerthykp', '2016-05-21 11:39:25', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c724885e9175', 'sreejakr', 'MEET_288bea8bcf16', null, null, null, 'vijay', '2016-06-01 09:38:28', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c7f3e41735d6', 'sumeshzoft', 'MEET_f7ba251c7cb1', null, null, null, 'vijay', '2016-05-21 14:47:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c8486068e14c', 'sheela', 'MEET_e174f76a0e89', null, null, null, 'keerthykp', '2016-05-20 19:38:03', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c85d25f71b59', 'sheela', 'MEET_6d4309954bf1', null, null, null, 'b', '2016-05-22 12:11:11', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c8c13cdf3eb4', 'a', 'MEET_a32cd3bb5a01', null, null, null, 'keerthykp', '2016-05-12 15:27:39', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c8c9de45f9df', 'keerthykp', 'MEET_d8e766477181', 'accepted', null, null, 'keerthykp', '2016-05-20 17:56:32', null, null, 'y', '60000');
INSERT INTO `appointment_details` VALUES ('PART_c8efc7223b74', 'sanoop', 'MEET_c87a7f4fb9d2', null, null, null, 'keerthykp', '2016-05-21 09:49:32', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c9227071d785', 'b', 'MEET_958315c6f8ab', null, null, 'null', 'b', '2016-05-06 15:32:37', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c9662e14a050', 'fhyty', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:44', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c9a08da1e23c', 'a', 'MEET_fbdef93424f6', 'accepted', null, 'null', 'b', '2016-05-06 10:15:30', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c9bde0670bb6', 'bini', 'MEET_2654114382dc', null, null, null, 'keerthykp', '2016-05-12 15:30:48', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_c9c1db56e6ad', 'sheela', 'MEET_96b0ac11c0af', null, null, null, 'keerthykp', '2016-05-17 10:28:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ca91f53d6760', 'keerthykp', 'MEET_373640acced6', 'accepted', null, null, 'keerthykp', '2016-05-21 10:23:12', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_caf54de26dd2', 'keerthykp', 'MEET_288bea8bcf16', null, null, null, 'vijay', '2016-06-01 09:38:28', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_caff3ba1f241', 'anu', 'MEET_fa8cc84bb2b2', null, null, null, 'keerthykp', '2016-05-12 15:20:12', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_cb294bbdaf0b', 'rahulzoft', 'MEET_2998a3eeccab', null, null, null, 'keerthykp', '2016-05-21 10:02:38', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_cb527a14cba4', 'sumeshzoft', 'MEET_2ada71eb0d20', null, null, null, 'keerthykp', '2016-05-20 18:10:35', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_cc13eb91c98b', 'sheela', 'MEET_53dcf47e1e4d', null, null, null, 'keerthykp', '2016-05-20 19:33:59', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_cccc6d155327', 'test', 'MEET_b0fa4e4a84b4', null, null, null, 'keerthykp', '2016-05-26 17:25:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_cce50c0d8a32', 'keerthykp', 'MEET_2998a3eeccab', 'accepted', null, null, 'keerthykp', '2016-05-21 10:02:39', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_cceda50b0f30', 'b', 'MEET_6bde4b42ea60', 'accepted', null, null, 'b', '2016-06-20 17:59:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ccf083855833', 'a', 'MEET_90b5a93cf517', null, null, null, 'b', '2016-05-10 10:16:48', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ce00077a022f', 'sanoop', 'MEET_2ae4646e7d1b', null, null, null, 'vijay', '2016-05-21 15:02:31', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ce286f0451d0', 'rahulzoft', 'MEET_54c0f3cb0955', null, null, null, 'b', '2016-05-22 16:11:50', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ce75cf24d885', 'b', 'MEET_1fc1c8d70455', 'accepted', null, null, 'b', '2016-05-21 18:55:30', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ce80ad3925a6', 'a', 'MEET_565e170e89bb', null, null, null, 'keerthykp', '2016-05-20 18:26:18', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ceb6961d6142', 'sumeshzoft', 'MEET_b866307060aa', 'cancelled', null, null, 'keerthykp', '2016-05-30 10:37:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_cec531a543e7', 'b', 'MEET_fbdef93424f6', 'accepted', null, 'null', 'b', '2016-05-06 10:15:30', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_cec906b9db2c', 'keerthykp', 'MEET_8da037e1ae9f', 'accepted', null, null, 'keerthykp', '2016-05-20 11:47:32', null, null, 'y', '300000');
INSERT INTO `appointment_details` VALUES ('PART_cf9e6c1332dd', 'sumeshzoft', 'MEET_90f8da9d45ee', null, null, null, 'vijay', '2016-05-21 15:14:37', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_cfe7af4d2cf4', 'keerthykp', 'MEET_4703ec4a8788', 'accepted', null, null, 'keerthykp', '2016-05-20 17:08:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_d01f3d3a97a6', 'ss', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_d06c5ac95da0', 'keerthykp', 'MEET_da2bb163749b', 'accepted', null, null, 'keerthykp', '2016-05-21 10:40:10', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_d0811be9d3bc', 'update', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:44', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_d0c648be1108', 'sumeshzoft', 'MEET_64b4af3fde3b', null, null, null, 'vijay', '2016-05-21 14:51:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_d1982e201ff4', 'sheela', 'MEET_8a755270e3d2', null, null, null, 'a', '2016-05-19 12:00:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_d1b2dd94f891', 'sheela', 'MEET_2ada71eb0d20', null, null, null, 'keerthykp', '2016-05-20 18:10:35', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_d31a184f9310', 'keerthykp', 'MEET_33d8df254641', 'accepted', null, null, 'keerthykp', '2016-05-21 09:52:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_d3389cb00c2d', 'anu', 'MEET_d8e766477181', null, null, null, 'keerthykp', '2016-05-20 17:56:32', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_d411cd1232ba', 'a', 'MEET_898e8e9930c5', null, null, null, 'keerthykp', '2016-05-20 14:05:59', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_d583472ba0b7', 'rahulzoft', 'MEET_374fffc26df9', null, null, null, 'keerthykp', '2016-05-19 16:38:10', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_d6011902f797', 'gh', 'MEET_5ad86cf3f75c', null, null, null, 'keerthykp', '2016-05-27 10:35:41', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_d6f6a1b268e4', 'rahulzoft', 'MEET_fc9095165a81', null, null, null, 'vijay', '2016-05-10 12:11:06', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_d71e78d4f663', 'sheela', 'MEET_4b1fb3d562ca', null, null, null, 'keerthykp', '2016-05-20 16:31:10', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_d73b96347a71', 'newuser', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_d74f5de38735', 'anu', 'MEET_393c3ffb8694', null, null, null, 'keerthykp', '2016-05-20 16:25:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_d751e6230a7d', 'vijay', 'MEET_b866307060aa', 'cancelled', null, null, 'keerthykp', '2016-05-30 10:37:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_d81ee0407b01', 'keerthykp', 'MEET_a32cd3bb5a01', 'accepted', null, null, 'keerthykp', '2016-05-12 15:27:39', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_d849978acad0', 'b', 'MEET_1ec37d5f2d38', 'accepted', null, null, 'b', '2016-05-22 12:57:24', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_d8b28cb749f0', 'b', 'MEET_6d4309954bf1', 'accepted', null, null, 'b', '2016-05-22 12:11:11', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_d90ee4ec2d20', 'sheela', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_d93b94534f6a', 'sreejakr', 'MEET_fbdef93424f6', null, null, 'null', 'b', '2016-05-06 10:15:30', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_daf0b0d4b43a', 'admin', 'MEET_288bea8bcf16', null, null, null, 'vijay', '2016-06-01 09:38:28', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_db2b93a017bd', 'b', 'MEET_05e604ef391d', null, null, null, 'keerthykp', '2016-05-21 13:05:18', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_db830a7011c3', 'sreejakr', 'MEET_aeb8ef5ede3c', null, null, 'null', 'b', '2016-05-06 10:21:17', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_dbabacd6c309', 'keerthykp', 'MEET_0b087335064e', null, null, 'Reason for rejection m11', 'b', '2016-05-09 19:29:11', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_dbe1041769de', 'vijay', 'MEET_fc9095165a81', null, null, null, 'vijay', '2016-05-10 12:11:06', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_dbe8f5872b7a', 'a', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:44', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_dd231921b98d', 'sumeshzoft', 'MEET_4b1fb3d562ca', null, null, null, 'keerthykp', '2016-05-20 16:31:10', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_dddfa279a725', 'update', 'MEET_b0fa4e4a84b4', null, null, null, 'keerthykp', '2016-05-26 17:25:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_de22120efced', 'keerthykp', 'MEET_fc9095165a81', 'accepted', null, null, 'vijay', '2016-05-10 12:11:06', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_defa3893098c', 'b', 'MEET_14a8fb906408', null, null, null, 'keerthykp', '2016-05-27 10:39:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_df0e43d24804', 'b', 'MEET_87f3be8c1efd', 'accepted', null, null, 'b', '2016-05-11 11:03:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_df17854b6ee3', 'keerthykp', 'MEET_cbaf56d7570c', 'accepted', null, null, 'keerthykp', '2016-05-20 18:07:05', null, null, 'y', '120000');
INSERT INTO `appointment_details` VALUES ('PART_df3cbcbe715e', 'sumeshzoft', 'MEET_84716cddecf1', null, null, null, 'b', '2016-05-11 14:33:34', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_df97d0114d4b', 'b', 'MEET_da38506994df', 'accepted', null, null, 'b', '2016-05-22 12:17:02', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_dfa60ba4f114', 'b', 'MEET_24584a4d4f56', 'accepted', null, null, 'b', '2016-05-22 12:33:17', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_dfc211ef1559', 'anu', 'MEET_f7ba251c7cb1', null, null, null, 'vijay', '2016-05-21 14:47:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e05e8e6222a1', 'rahulzoft', 'MEET_6d70a4aad4cd', null, null, null, 'b', '2016-05-10 18:44:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e07343fdb8ac', 'gh', 'MEET_14a8fb906408', null, null, null, 'keerthykp', '2016-05-27 10:39:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e08fa70463bf', 'sanoop', 'MEET_cbaf56d7570c', null, null, null, 'keerthykp', '2016-05-20 18:07:05', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e0ccaee140da', 'keerthykp', 'MEET_565e170e89bb', 'accepted', null, null, 'keerthykp', '2016-05-20 18:26:18', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e17375e5b17d', 'a', 'MEET_90f8da9d45ee', null, null, null, 'vijay', '2016-05-21 15:14:37', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e1b31def54d7', 'test', 'MEET_713729bcface', null, null, null, 'keerthykp', '2016-05-26 17:26:44', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e1b6036e76b0', 'rahulzoft', 'MEET_64b4af3fde3b', null, null, null, 'vijay', '2016-05-21 14:51:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e22df735cb12', 'sheela', 'MEET_65c0bbe7d345', null, null, null, 'keerthykp', '2016-05-20 18:25:40', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e270e07e70b4', 'keerthykp', 'MEET_e174f76a0e89', 'accepted', null, null, 'keerthykp', '2016-05-20 19:38:03', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e2e9e9dfe88e', 'b', 'MEET_143011ba99c2', 'accepted', null, null, 'b', '2016-05-22 13:12:52', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e329666a1159', 'bini', 'MEET_f7ba251c7cb1', null, null, null, 'vijay', '2016-05-21 14:47:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e415e6eb6722', 'anu', 'MEET_33d8df254641', null, null, null, 'keerthykp', '2016-05-21 09:52:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e432dd03d84f', 'sreejakr', 'MEET_6bde4b42ea60', null, null, null, 'b', '2016-06-20 17:59:44', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e50369fd6021', 'anu', 'MEET_05e604ef391d', null, null, null, 'keerthykp', '2016-05-21 13:05:18', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e55baafad6d2', 'vijay', 'MEET_374fffc26df9', null, null, null, 'keerthykp', '2016-05-19 16:38:11', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e5742fac2d67', 'b', 'MEET_c219056ee583', 'rejected', null, 'reason for rejection k may 30 first', 'keerthykp', '2016-05-30 10:36:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e5dc109004d5', 'keerthykp', 'MEET_2654114382dc', 'accepted', null, null, 'keerthykp', '2016-05-12 15:30:48', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e6d9db0f729d', 'sumeshzoft', 'MEET_217c4b532308', null, null, null, 'keerthykp', '2016-05-21 11:42:46', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e746945c39bf', 'sheela', 'MEET_85a288c8de24', null, null, null, 'keerthykp', '2016-05-21 10:23:59', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e78ea8e6b67f', 'sheela', 'MEET_825e834e802e', null, null, null, 'keerthykp', '2016-05-31 16:39:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e88715ae6a6c', 'keerthykp', 'MEET_13521c8fa8bd', 'accepted', null, null, 'keerthykp', '2016-05-20 10:51:19', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e8cd31e659ae', 'sreejakr', 'MEET_373640acced6', null, null, null, 'keerthykp', '2016-05-21 10:23:12', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_e923026e00ab', 'b', 'MEET_35519d39e3ee', 'accepted', null, null, 'b', '2016-05-10 15:03:23', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_eae1e6955562', 'admin', 'MEET_b0fa4e4a84b4', null, null, null, 'keerthykp', '2016-05-26 17:25:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_eb0379e0d3f9', 'bini', 'MEET_217c4b532308', null, null, null, 'keerthykp', '2016-05-21 11:42:46', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_eb2836b3d3df', 'sheela', 'MEET_d749cf96ae15', null, null, null, 'b', '2016-05-22 18:56:30', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ec1d09cfe6f5', 'a', 'MEET_823857ccb0a0', 'accepted', null, null, 'b', '2016-05-12 10:43:27', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ed022783473a', 'sreejakr', 'MEET_820fa59850f4', null, null, null, 'b', '2016-05-22 16:30:02', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ed5d89717019', 'vijay', 'MEET_a683551160e5', null, null, null, 'keerthykp', '2016-05-20 18:24:00', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ed905f558c3b', 'b', 'MEET_5ad86cf3f75c', null, null, null, 'keerthykp', '2016-05-27 10:35:41', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ede0159a3cf8', 'a', 'MEET_40352e977d5e', null, null, null, 'b', '2016-05-11 14:35:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ee2e813d3940', 'sumeshzoft', 'MEET_65c0bbe7d345', null, null, null, 'keerthykp', '2016-05-20 18:25:40', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ee46426e8a8c', 'b', 'MEET_aeb8ef5ede3c', null, null, 'null', 'b', '2016-05-06 10:21:17', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_eedda8398e4f', 'sreejakr', 'MEET_713729bcface', null, null, null, 'keerthykp', '2016-05-26 17:26:44', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ef34028b3f36', 'rahulzoft', 'MEET_898e8e9930c5', null, null, null, 'keerthykp', '2016-05-20 14:05:59', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ef425a710d66', 'sheela', 'MEET_00373abaee7d', null, null, null, 'vijay', '2016-05-27 11:38:50', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ef8a41471516', 'test', 'MEET_a683bcd7f0b7', null, null, null, 'b', '2016-05-10 14:04:00', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f04ce4005ff8', 'sreejakr', 'MEET_9d8bf7d282e7', null, null, null, 'keerthykp', '2016-05-20 15:45:11', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f0b810f1e20b', 'keerthykp', 'MEET_1b50ca5bfde7', 'accepted', null, null, 'keerthykp', '2016-05-21 10:39:26', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f15f8adc659a', 'sanoop', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f188ff490d6c', 'sumeshzoft', 'MEET_96b0ac11c0af', null, null, null, 'keerthykp', '2016-05-17 10:28:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f1b43435a24c', 'sreejakr', 'MEET_14a8fb906408', null, null, null, 'keerthykp', '2016-05-27 10:39:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f1c4f7c08712', 'b', 'MEET_d749cf96ae15', 'accepted', null, null, 'b', '2016-05-22 18:56:30', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f22ff05bac10', 'sheela', 'MEET_daac10d96378', null, null, null, 'keerthykp', '2016-05-19 19:46:00', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f2314f2d11f2', 'sheela', 'MEET_a48f93eaaf89', null, null, null, 'keerthykp', '2016-05-20 10:52:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f24db282fff2', 'sanoop', 'MEET_00373abaee7d', null, null, null, 'vijay', '2016-05-27 11:38:50', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f2a235927af6', 'sheela', 'MEET_a683551160e5', null, null, null, 'keerthykp', '2016-05-20 18:24:00', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f2c41c07c655', 'krishna', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f2f05822c810', 'sumeshzoft', 'MEET_373640acced6', null, null, null, 'keerthykp', '2016-05-21 10:23:12', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f31d03b0d149', 'test', 'MEET_c2ae59a6ab70', null, null, null, 'vijay', '2016-05-21 14:48:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f33f85f0a8bb', 'bini', 'MEET_44553548a622', null, null, null, 'keerthykp', '2016-05-20 16:32:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f34643075193', 'admin', 'MEET_5ad86cf3f75c', null, null, null, 'keerthykp', '2016-05-27 10:35:40', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f3b47a284ab0', 'sheela', 'MEET_cbaf56d7570c', null, null, null, 'keerthykp', '2016-05-20 18:07:05', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f3ca74094638', 'keerthykp', 'MEET_c17c0e431f11', 'accepted', null, null, 'keerthykp', '2016-05-20 19:32:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f4109d360e36', 'sheela', 'MEET_c87a7f4fb9d2', null, null, null, 'keerthykp', '2016-05-21 09:49:32', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f42c60b86f04', 'admin', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f49bef87aa8b', 'vijay', 'MEET_13521c8fa8bd', null, null, null, 'keerthykp', '2016-05-20 10:51:19', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f5567cb11f97', 'sanoop', 'MEET_217c4b532308', null, null, null, 'keerthykp', '2016-05-21 11:42:46', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f56585139554', 'keerthykp', 'MEET_90b5a93cf517', null, null, 'I am suffering from fever', 'b', '2016-05-10 10:16:48', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f57462762da7', 'a', 'MEET_8097a5d9aa6e', null, null, null, 'keerthykp', '2016-05-21 10:28:06', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f577008d84aa', 'bini', 'MEET_c2ae59a6ab70', null, null, null, 'vijay', '2016-05-21 14:48:43', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f5a79d42b8b2', 'a', 'MEET_8a755270e3d2', 'accepted', null, null, 'a', '2016-05-19 12:00:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f603dffe24d0', 'sheela', 'MEET_b866307060aa', 'cancelled', null, null, 'keerthykp', '2016-05-30 10:37:58', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f64c5458943f', 'sheela', 'MEET_823857ccb0a0', null, null, null, 'b', '2016-05-12 10:43:27', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f64c8a7b8a5c', 'b', 'MEET_00373abaee7d', 'accepted', null, null, 'vijay', '2016-05-27 11:38:50', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f6590339a067', 'sreejakr', 'MEET_217c4b532308', null, null, null, 'keerthykp', '2016-05-21 11:42:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f7194dad5151', 'rahulzoft', 'MEET_0823609ba7f4', null, 'absent', null, 'b', '2016-05-10 18:16:59', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f78d47a14ec6', 'keerthykp', 'MEET_65c0bbe7d345', 'accepted', null, null, 'keerthykp', '2016-05-20 18:25:40', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f7fed2cd1f7a', 'a', 'MEET_1f6d36ea01a5', null, 'absent', null, 'b', '2016-05-11 17:01:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f8582d72e59b', 'admin', 'MEET_495c6101ad85', null, null, 'null', 'b', '2016-05-06 10:20:16', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f9c47f7b742d', 'update', 'MEET_f22b8a9e1574', null, null, null, 'keerthykp', '2016-06-01 09:47:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f9c85e617be4', 'sheela', 'MEET_d69fdb48bea9', null, null, null, 'b', '2016-05-22 12:10:13', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_f9ea55b9b997', 'vijay', 'MEET_c219056ee583', null, null, null, 'keerthykp', '2016-05-30 10:36:45', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_fa0840d80d42', 'b', 'MEET_e2db07020c94', 'accepted', null, null, 'b', '2016-05-22 13:01:12', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_fa3068d53db8', 'anu', 'MEET_2b2e2ae46d84', null, null, null, 'keerthykp', '2016-05-20 15:59:48', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_fa53d8144cd6', 'a', 'MEET_8f8dba0996b7', null, null, null, 'b', '2016-05-22 14:53:03', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_fb98b842a473', 'sreejakr', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_fb9dda1b83f9', 'b', 'MEET_37e49f83d27a', 'accepted', null, null, 'b', '2016-05-10 18:06:35', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_fc00499883f2', 'fhyty', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_fc0117c87ef7', 'sanoop', 'MEET_4703ec4a8788', null, null, null, 'keerthykp', '2016-05-20 17:08:55', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_fc40bde63858', 'sumeshzoft', 'MEET_12884bd36eec', null, null, null, 'keerthykp', '2016-05-20 16:03:24', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_fc44b6ef767b', 'b', 'MEET_9fdecdaaeda5', 'accepted', 'present', null, 'b', '2016-05-10 14:46:29', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_fc81f9763418', 'keerthykp', 'MEET_5bc4c022699c', 'accepted', null, null, 'keerthykp', '2016-05-20 16:26:35', null, null, 'y', '60000');
INSERT INTO `appointment_details` VALUES ('PART_fd5066b3e8d3', 'user7', 'MEET_a857f335bde1', null, null, null, 'b', '2016-06-20 17:58:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_fd59de118516', 'keerthykp', 'MEET_6d70a4aad4cd', null, null, null, 'b', '2016-05-10 18:44:56', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_fd6a117540c5', 'b', 'MEET_6159dba7901e', null, null, null, 'keerthykp', '2016-05-20 14:06:49', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_fd6fccdeb403', 'sreejakr', 'MEET_3b2a2d8ae889', null, 'absent', null, 'b', '2016-05-11 17:02:31', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_fdc0723ee305', 'anu', 'MEET_e2d66d235092', null, null, null, 'keerthykp', '2016-05-19 12:35:57', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_fe308c07f281', 'anu', 'MEET_168c88015001', null, null, null, 'keerthykp', '2016-05-20 16:10:31', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ff1e4b6148e2', 'sreejakr', 'MEET_826c189b8022', null, null, 'null', 'b', '2016-05-06 10:18:54', null, null, 'y', null);
INSERT INTO `appointment_details` VALUES ('PART_ff330bdd5071', 'b', 'MEET_90f8da9d45ee', null, null, null, 'vijay', '2016-05-21 15:14:37', null, null, 'y', null);

-- ----------------------------
-- Table structure for appointment_master
-- ----------------------------
DROP TABLE IF EXISTS `appointment_master`;
CREATE TABLE `appointment_master` (
  `meeting_id` varchar(255) NOT NULL,
  `meeting_name` varchar(255) DEFAULT NULL,
  `meeting_description` text,
  `start_date_time` datetime DEFAULT NULL,
  `end_date_time` datetime DEFAULT NULL,
  `anchor` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `meeting_status` varchar(255) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`meeting_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of appointment_master
-- ----------------------------
INSERT INTO `appointment_master` VALUES ('MEET_15ce012b473b', 'new evwnt', 'new evwnt', '2016-05-17 16:53:00', '2016-05-26 16:55:00', 'sandeep', 'hall', 'scheduled', 's', '2016-05-26 16:42:32', null, null, 'y');
INSERT INTO `appointment_master` VALUES ('MEET_1c999d9291b4', 'rtyurtuty', 'tyuytutyu', '2016-05-27 05:00:00', '2016-05-27 09:00:00', 'admin', 'tyutyu', 'scheduled', 'b', '2016-05-26 10:43:48', null, null, 'y');
INSERT INTO `appointment_master` VALUES ('MEET_358c2eae193d', 'test reminder', 'test reminder', '2016-05-26 03:00:00', '2016-05-26 03:01:00', 'admin', 'dfggd', 'scheduled', 'b', '2016-05-26 14:50:04', null, null, 'y');
INSERT INTO `appointment_master` VALUES ('MEET_4ba1a13cb531', 'meetingcvb', 'vcbcvbcvb', '2016-05-27 01:00:00', '2016-05-27 06:00:00', 'admin', 'ceedfsdg', 'scheduled', 'b', '2016-05-26 10:42:14', null, null, 'y');
INSERT INTO `appointment_master` VALUES ('MEET_62bb510f81d4', 'Client meeting', 'sdfsdfsd', '2016-05-26 05:00:00', '2016-05-31 07:00:00', 'a', 'kochi', 'scheduled', 'b', '2016-05-26 14:04:40', null, null, 'y');
INSERT INTO `appointment_master` VALUES ('MEET_62eed3955de6', 'reminder me', 'reminder me', '2016-05-26 15:04:00', '2016-05-26 15:07:00', 'sandeep', 'fxgfdgfd', 'scheduled', 'b', '2016-05-26 14:53:24', null, null, 'y');
INSERT INTO `appointment_master` VALUES ('MEET_6bde4b42ea60', 'fdg', 'fdg', '2016-06-21 08:00:00', '2016-06-21 10:00:00', 'admin', 'dfsg', 'scheduled', 'b', '2016-06-20 17:59:44', null, null, 'y');
INSERT INTO `appointment_master` VALUES ('MEET_70efc0afccc5', 'Project submission', 'sasasasasa', '2016-05-17 00:00:00', '2016-05-24 05:00:00', 'admin', 'calicut', 'scheduled', 'admin', '2016-05-26 15:00:40', null, null, 'y');
INSERT INTO `appointment_master` VALUES ('MEET_7b19b4911ef8', 'Project meeting', 'nnkjnk;jsd', '2016-06-01 13:00:00', '2016-06-02 10:00:00', 'a', 'kannur', 'scheduled', 'b', '2016-05-26 14:03:15', null, null, 'y');
INSERT INTO `appointment_master` VALUES ('MEET_88a64f5d7000', 'gfh', 'gfhgh', '2016-05-27 16:00:00', '2016-05-27 18:50:20', 'a', 'gfh', 'scheduled', 'b', '2016-05-27 15:50:26', null, null, 'y');
INSERT INTO `appointment_master` VALUES ('MEET_8ccf4c1b780f', 'ormappeduthal', 'ariyippu', '2016-05-26 15:20:00', '2016-05-26 15:29:00', 'admin', 'meeting hall', 'scheduled', 'b', '2016-05-26 15:06:55', null, null, 'y');
INSERT INTO `appointment_master` VALUES ('MEET_90f8f54bfc0d', 'test', 'test', '2016-05-26 16:58:00', '2016-05-26 16:59:00', 'b', 'hall', 'scheduled', 's', '2016-05-26 16:45:07', null, null, 'y');
INSERT INTO `appointment_master` VALUES ('MEET_935b0fe7b93e', 'Clent groupmeeting', 'sdfsdfsd', '2016-05-24 09:00:00', '2016-05-25 04:00:00', 'admin', 'sd', 'scheduled', 'b', '2016-05-26 14:09:14', null, null, 'y');
INSERT INTO `appointment_master` VALUES ('MEET_a857f335bde1', 'fdg', 'fdg', '2016-06-21 04:00:00', '2016-06-23 05:58:47', 'update', 'dfg', 'scheduled', 'b', '2016-06-20 17:58:57', null, null, 'y');
INSERT INTO `appointment_master` VALUES ('MEET_bd4b5f46bf91', 'fggff', 'fffff', '2016-05-06 00:00:00', '2016-05-17 11:00:00', 'admin', 'ffff', 'scheduled', 'b', '2016-05-26 13:03:37', null, null, 'y');
INSERT INTO `appointment_master` VALUES ('MEET_d922270c7300', 'reminder', 'reminder', '2016-05-26 03:02:45', '2016-05-26 03:04:00', 'admin', 'hall', 'scheduled', 'b', '2016-05-26 14:51:15', null, null, 'y');
INSERT INTO `appointment_master` VALUES ('MEET_ef7ad01499e3', 'Group meeting', 'kjkllhk							', '2016-05-28 14:00:00', '2016-05-30 00:00:00', 'admin', 'cochin', 'scheduled', 'b', '2016-05-26 14:07:50', null, null, 'y');

-- ----------------------------
-- Table structure for branch_tbl
-- ----------------------------
DROP TABLE IF EXISTS `branch_tbl`;
CREATE TABLE `branch_tbl` (
  `branch_id` varchar(100) NOT NULL,
  `branch_name` varchar(30) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `address` varchar(150) DEFAULT NULL,
  `company_owner` varchar(50) DEFAULT NULL,
  `branch_owner` varchar(50) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `dummy2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of branch_tbl
-- ----------------------------
INSERT INTO `branch_tbl` VALUES ('135e20f63648', 'ernakulam', 'palarivattom', 'srk flat,\npalarivattom,\nkakkanadu p.o,\nkollam', 'arun', 'arun', null, null);
INSERT INTO `branch_tbl` VALUES ('54dcb369e715', 'Kollam', 'Kollam', 'Kollam p.o, Kollam', 'Cristy', 'Cristy', null, null);
INSERT INTO `branch_tbl` VALUES ('73ec84795592', 'Kannur', 'Thalassery', 'kannur P O', 'Ancy', 'Justin', null, null);
INSERT INTO `branch_tbl` VALUES ('ba56e841c0d1', 'Kochi', 'Kakkanad', 'pongalil House\nvgra  p o\nvgra			', 'Geethanath', 'Branch Owner', null, null);
INSERT INTO `branch_tbl` VALUES ('ee8726e341cd', 'kozhikode', 'Thenjipaalam', 'University Road\nThenjipaalam		', 'Srk', 'Saraswathy', null, null);

-- ----------------------------
-- Table structure for counselor_counter_tbl
-- ----------------------------
DROP TABLE IF EXISTS `counselor_counter_tbl`;
CREATE TABLE `counselor_counter_tbl` (
  `id` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `notice` int(11) DEFAULT '0',
  `offer` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `username` (`user_name`) USING BTREE,
  CONSTRAINT `counselor_counter_tbl_ibfk_1` FOREIGN KEY (`user_name`) REFERENCES `login_tbl` (`user_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of counselor_counter_tbl
-- ----------------------------
INSERT INTO `counselor_counter_tbl` VALUES ('counter_74bc48d457c4', 'admin', '0', '0');
INSERT INTO `counselor_counter_tbl` VALUES ('counter_821a35b5c179', 'blnou', '0', '0');
INSERT INTO `counselor_counter_tbl` VALUES ('counter_f70dff2a7d6f', 'sandeeps', '0', '0');

-- ----------------------------
-- Table structure for country_codes_tbl
-- ----------------------------
DROP TABLE IF EXISTS `country_codes_tbl`;
CREATE TABLE `country_codes_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `iso` char(2) DEFAULT NULL,
  `name` varchar(80) NOT NULL,
  `nicename` varchar(80) NOT NULL,
  `iso3` char(3) DEFAULT NULL,
  `numcode` smallint(6) DEFAULT NULL,
  `phonecode` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=244 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of country_codes_tbl
-- ----------------------------
INSERT INTO `country_codes_tbl` VALUES ('1', 'AF', 'AFGHANISTAN', 'Afghanistan', 'AFG', '4', '+93');
INSERT INTO `country_codes_tbl` VALUES ('2', 'AL', 'ALBANIA', 'Albania', 'ALB', '8', '+355');
INSERT INTO `country_codes_tbl` VALUES ('3', 'DZ', 'ALGERIA', 'Algeria', 'DZA', '12', '+213');
INSERT INTO `country_codes_tbl` VALUES ('4', 'AS', 'AMERICAN SAMOA', 'American Samoa', 'ASM', '16', '+1684');
INSERT INTO `country_codes_tbl` VALUES ('5', 'AD', 'ANDORRA', 'Andorra', 'AND', '20', '+376');
INSERT INTO `country_codes_tbl` VALUES ('6', 'AO', 'ANGOLA', 'Angola', 'AGO', '24', '+244');
INSERT INTO `country_codes_tbl` VALUES ('7', 'AI', 'ANGUILLA', 'Anguilla', 'AIA', '660', '+1264');
INSERT INTO `country_codes_tbl` VALUES ('8', 'AQ', 'ANTARCTICA', 'Antarctica', null, null, '+0');
INSERT INTO `country_codes_tbl` VALUES ('9', 'AG', 'ANTIGUA AND BARBUDA', 'Antigua and Barbuda', 'ATG', '28', '+1268');
INSERT INTO `country_codes_tbl` VALUES ('10', 'AR', 'ARGENTINA', 'Argentina', 'ARG', '32', '+54');
INSERT INTO `country_codes_tbl` VALUES ('11', 'AM', 'ARMENIA', 'Armenia', 'ARM', '51', '+374');
INSERT INTO `country_codes_tbl` VALUES ('12', 'AW', 'ARUBA', 'Aruba', 'ABW', '533', '+297');
INSERT INTO `country_codes_tbl` VALUES ('13', 'AU', 'AUSTRALIA', 'Australia', 'AUS', '36', '+61');
INSERT INTO `country_codes_tbl` VALUES ('14', 'AT', 'AUSTRIA', 'Austria', 'AUT', '40', '+43');
INSERT INTO `country_codes_tbl` VALUES ('15', 'AZ', 'AZERBAIJAN', 'Azerbaijan', 'AZE', '31', '+994');
INSERT INTO `country_codes_tbl` VALUES ('16', 'BS', 'BAHAMAS', 'Bahamas', 'BHS', '44', '+1242');
INSERT INTO `country_codes_tbl` VALUES ('17', 'BH', 'BAHRAIN', 'Bahrain', 'BHR', '48', '+973');
INSERT INTO `country_codes_tbl` VALUES ('18', 'BD', 'BANGLADESH', 'Bangladesh', 'BGD', '50', '+880');
INSERT INTO `country_codes_tbl` VALUES ('19', 'BB', 'BARBADOS', 'Barbados', 'BRB', '52', '+1246');
INSERT INTO `country_codes_tbl` VALUES ('20', 'BY', 'BELARUS', 'Belarus', 'BLR', '112', '+375');
INSERT INTO `country_codes_tbl` VALUES ('21', 'BE', 'BELGIUM', 'Belgium', 'BEL', '56', '+32');
INSERT INTO `country_codes_tbl` VALUES ('22', 'BZ', 'BELIZE', 'Belize', 'BLZ', '84', '+501');
INSERT INTO `country_codes_tbl` VALUES ('23', 'BJ', 'BENIN', 'Benin', 'BEN', '204', '+229');
INSERT INTO `country_codes_tbl` VALUES ('24', 'BM', 'BERMUDA', 'Bermuda', 'BMU', '60', '+1441');
INSERT INTO `country_codes_tbl` VALUES ('25', 'BT', 'BHUTAN', 'Bhutan', 'BTN', '64', '+975');
INSERT INTO `country_codes_tbl` VALUES ('26', 'BO', 'BOLIVIA', 'Bolivia', 'BOL', '68', '+591');
INSERT INTO `country_codes_tbl` VALUES ('27', 'BA', 'BOSNIA AND HERZEGOVINA', 'Bosnia and Herzegovina', 'BIH', '70', '+387');
INSERT INTO `country_codes_tbl` VALUES ('28', 'BW', 'BOTSWANA', 'Botswana', 'BWA', '72', '+267');
INSERT INTO `country_codes_tbl` VALUES ('29', 'BV', 'BOUVET ISLAND', 'Bouvet Island', null, null, '+0');
INSERT INTO `country_codes_tbl` VALUES ('30', 'BR', 'BRAZIL', 'Brazil', 'BRA', '76', '+55');
INSERT INTO `country_codes_tbl` VALUES ('31', 'IO', 'BRITISH INDIAN OCEAN TERRITORY', 'British Indian Ocean Territory', null, null, '+246');
INSERT INTO `country_codes_tbl` VALUES ('32', 'BN', 'BRUNEI DARUSSALAM', 'Brunei Darussalam', 'BRN', '96', '+673');
INSERT INTO `country_codes_tbl` VALUES ('33', 'BG', 'BULGARIA', 'Bulgaria', 'BGR', '100', '+359');
INSERT INTO `country_codes_tbl` VALUES ('34', 'BF', 'BURKINA FASO', 'Burkina Faso', 'BFA', '854', '+226');
INSERT INTO `country_codes_tbl` VALUES ('35', 'BI', 'BURUNDI', 'Burundi', 'BDI', '108', '+257');
INSERT INTO `country_codes_tbl` VALUES ('36', 'KH', 'CAMBODIA', 'Cambodia', 'KHM', '116', '+855');
INSERT INTO `country_codes_tbl` VALUES ('37', 'CM', 'CAMEROON', 'Cameroon', 'CMR', '120', '+237');
INSERT INTO `country_codes_tbl` VALUES ('38', 'CA', 'CANADA', 'Canada', 'CAN', '124', '+1');
INSERT INTO `country_codes_tbl` VALUES ('39', 'CV', 'CAPE VERDE', 'Cape Verde', 'CPV', '132', '+238');
INSERT INTO `country_codes_tbl` VALUES ('40', 'KY', 'CAYMAN ISLANDS', 'Cayman Islands', 'CYM', '136', '+1345');
INSERT INTO `country_codes_tbl` VALUES ('41', 'CF', 'CENTRAL AFRICAN REPUBLIC', 'Central African Republic', 'CAF', '140', '+236');
INSERT INTO `country_codes_tbl` VALUES ('42', 'TD', 'CHAD', 'Chad', 'TCD', '148', '+235');
INSERT INTO `country_codes_tbl` VALUES ('43', 'CL', 'CHILE', 'Chile', 'CHL', '152', '+56');
INSERT INTO `country_codes_tbl` VALUES ('44', 'CN', 'CHINA', 'China', 'CHN', '156', '+86');
INSERT INTO `country_codes_tbl` VALUES ('45', 'CX', 'CHRISTMAS ISLAND', 'Christmas Island', null, null, '+61');
INSERT INTO `country_codes_tbl` VALUES ('46', 'CC', 'COCOS (KEELING) ISLANDS', 'Cocos (Keeling) Islands', null, null, '+672');
INSERT INTO `country_codes_tbl` VALUES ('47', 'CO', 'COLOMBIA', 'Colombia', 'COL', '170', '+57');
INSERT INTO `country_codes_tbl` VALUES ('48', 'KM', 'COMOROS', 'Comoros', 'COM', '174', '+269');
INSERT INTO `country_codes_tbl` VALUES ('49', 'CG', 'CONGO', 'Congo', 'COG', '178', '+242');
INSERT INTO `country_codes_tbl` VALUES ('50', 'CD', 'CONGO, THE DEMOCRATIC REPUBLIC OF THE', 'Congo, the Democratic Republic of the', 'COD', '180', '+242');
INSERT INTO `country_codes_tbl` VALUES ('51', 'CK', 'COOK ISLANDS', 'Cook Islands', 'COK', '184', '+682');
INSERT INTO `country_codes_tbl` VALUES ('52', 'CR', 'COSTA RICA', 'Costa Rica', 'CRI', '188', '+506');
INSERT INTO `country_codes_tbl` VALUES ('53', 'CI', 'COTE D\'IVOIRE', 'Cote D\'Ivoire', 'CIV', '384', '+225');
INSERT INTO `country_codes_tbl` VALUES ('54', 'HR', 'CROATIA', 'Croatia', 'HRV', '191', '+385');
INSERT INTO `country_codes_tbl` VALUES ('55', 'CU', 'CUBA', 'Cuba', 'CUB', '192', '+53');
INSERT INTO `country_codes_tbl` VALUES ('56', 'CY', 'CYPRUS', 'Cyprus', 'CYP', '196', '+357');
INSERT INTO `country_codes_tbl` VALUES ('57', 'CZ', 'CZECH REPUBLIC', 'Czech Republic', 'CZE', '203', '+420');
INSERT INTO `country_codes_tbl` VALUES ('58', 'DK', 'DENMARK', 'Denmark', 'DNK', '208', '+45');
INSERT INTO `country_codes_tbl` VALUES ('59', 'DJ', 'DJIBOUTI', 'Djibouti', 'DJI', '262', '+253');
INSERT INTO `country_codes_tbl` VALUES ('60', 'DM', 'DOMINICA', 'Dominica', 'DMA', '212', '+1767');
INSERT INTO `country_codes_tbl` VALUES ('61', 'DO', 'DOMINICAN REPUBLIC', 'Dominican Republic', 'DOM', '214', '+1809');
INSERT INTO `country_codes_tbl` VALUES ('62', 'EC', 'ECUADOR', 'Ecuador', 'ECU', '218', '+593');
INSERT INTO `country_codes_tbl` VALUES ('63', 'EG', 'EGYPT', 'Egypt', 'EGY', '818', '+20');
INSERT INTO `country_codes_tbl` VALUES ('64', 'SV', 'EL SALVADOR', 'El Salvador', 'SLV', '222', '+503');
INSERT INTO `country_codes_tbl` VALUES ('65', 'GQ', 'EQUATORIAL GUINEA', 'Equatorial Guinea', 'GNQ', '226', '+240');
INSERT INTO `country_codes_tbl` VALUES ('66', 'ER', 'ERITREA', 'Eritrea', 'ERI', '232', '+291');
INSERT INTO `country_codes_tbl` VALUES ('67', 'EE', 'ESTONIA', 'Estonia', 'EST', '233', '+372');
INSERT INTO `country_codes_tbl` VALUES ('68', 'ET', 'ETHIOPIA', 'Ethiopia', 'ETH', '231', '+251');
INSERT INTO `country_codes_tbl` VALUES ('69', 'FK', 'FALKLAND ISLANDS (MALVINAS)', 'Falkland Islands (Malvinas)', 'FLK', '238', '+500');
INSERT INTO `country_codes_tbl` VALUES ('70', 'FO', 'FAROE ISLANDS', 'Faroe Islands', 'FRO', '234', '+298');
INSERT INTO `country_codes_tbl` VALUES ('71', 'FJ', 'FIJI', 'Fiji', 'FJI', '242', '+679');
INSERT INTO `country_codes_tbl` VALUES ('72', 'FI', 'FINLAND', 'Finland', 'FIN', '246', '+358');
INSERT INTO `country_codes_tbl` VALUES ('73', 'FR', 'FRANCE', 'France', 'FRA', '250', '+33');
INSERT INTO `country_codes_tbl` VALUES ('74', 'GF', 'FRENCH GUIANA', 'French Guiana', 'GUF', '254', '+594');
INSERT INTO `country_codes_tbl` VALUES ('75', 'PF', 'FRENCH POLYNESIA', 'French Polynesia', 'PYF', '258', '+689');
INSERT INTO `country_codes_tbl` VALUES ('76', 'TF', 'FRENCH SOUTHERN TERRITORIES', 'French Southern Territories', null, null, '+0');
INSERT INTO `country_codes_tbl` VALUES ('77', 'GA', 'GABON', 'Gabon', 'GAB', '266', '+241');
INSERT INTO `country_codes_tbl` VALUES ('78', 'GM', 'GAMBIA', 'Gambia', 'GMB', '270', '+220');
INSERT INTO `country_codes_tbl` VALUES ('79', 'GE', 'GEORGIA', 'Georgia', 'GEO', '268', '+995');
INSERT INTO `country_codes_tbl` VALUES ('80', 'DE', 'GERMANY', 'Germany', 'DEU', '276', '+49');
INSERT INTO `country_codes_tbl` VALUES ('81', 'GH', 'GHANA', 'Ghana', 'GHA', '288', '+233');
INSERT INTO `country_codes_tbl` VALUES ('82', 'GI', 'GIBRALTAR', 'Gibraltar', 'GIB', '292', '+350');
INSERT INTO `country_codes_tbl` VALUES ('83', 'GR', 'GREECE', 'Greece', 'GRC', '300', '+30');
INSERT INTO `country_codes_tbl` VALUES ('84', 'GL', 'GREENLAND', 'Greenland', 'GRL', '304', '+299');
INSERT INTO `country_codes_tbl` VALUES ('85', 'GD', 'GRENADA', 'Grenada', 'GRD', '308', '+1473');
INSERT INTO `country_codes_tbl` VALUES ('86', 'GP', 'GUADELOUPE', 'Guadeloupe', 'GLP', '312', '+590');
INSERT INTO `country_codes_tbl` VALUES ('87', 'GU', 'GUAM', 'Guam', 'GUM', '316', '+1671');
INSERT INTO `country_codes_tbl` VALUES ('88', 'GT', 'GUATEMALA', 'Guatemala', 'GTM', '320', '+502');
INSERT INTO `country_codes_tbl` VALUES ('89', 'GN', 'GUINEA', 'Guinea', 'GIN', '324', '+224');
INSERT INTO `country_codes_tbl` VALUES ('90', 'GW', 'GUINEA-BISSAU', 'Guinea-Bissau', 'GNB', '624', '+245');
INSERT INTO `country_codes_tbl` VALUES ('91', 'GY', 'GUYANA', 'Guyana', 'GUY', '328', '+592');
INSERT INTO `country_codes_tbl` VALUES ('92', 'HT', 'HAITI', 'Haiti', 'HTI', '332', '+509');
INSERT INTO `country_codes_tbl` VALUES ('93', 'HM', 'HEARD ISLAND AND MCDONALD ISLANDS', 'Heard Island and Mcdonald Islands', null, null, '+0');
INSERT INTO `country_codes_tbl` VALUES ('94', 'VA', 'HOLY SEE (VATICAN CITY STATE)', 'Holy See (Vatican City State)', 'VAT', '336', '+39');
INSERT INTO `country_codes_tbl` VALUES ('95', 'HN', 'HONDURAS', 'Honduras', 'HND', '340', '+504');
INSERT INTO `country_codes_tbl` VALUES ('96', 'HK', 'HONG KONG', 'Hong Kong', 'HKG', '344', '+852');
INSERT INTO `country_codes_tbl` VALUES ('97', 'HU', 'HUNGARY', 'Hungary', 'HUN', '348', '+36');
INSERT INTO `country_codes_tbl` VALUES ('98', 'IS', 'ICELAND', 'Iceland', 'ISL', '352', '+354');
INSERT INTO `country_codes_tbl` VALUES ('99', 'IN', 'INDIA', 'India', 'IND', '356', '+91');
INSERT INTO `country_codes_tbl` VALUES ('100', 'ID', 'INDONESIA', 'Indonesia', 'IDN', '360', '+62');
INSERT INTO `country_codes_tbl` VALUES ('101', 'IR', 'IRAN, ISLAMIC REPUBLIC OF', 'Iran, Islamic Republic of', 'IRN', '364', '+98');
INSERT INTO `country_codes_tbl` VALUES ('102', 'IQ', 'IRAQ', 'Iraq', 'IRQ', '368', '+964');
INSERT INTO `country_codes_tbl` VALUES ('103', 'IE', 'IRELAND', 'Ireland', 'IRL', '372', '+353');
INSERT INTO `country_codes_tbl` VALUES ('104', 'IL', 'ISRAEL', 'Israel', 'ISR', '376', '+972');
INSERT INTO `country_codes_tbl` VALUES ('105', 'IT', 'ITALY', 'Italy', 'ITA', '380', '+39');
INSERT INTO `country_codes_tbl` VALUES ('106', 'JM', 'JAMAICA', 'Jamaica', 'JAM', '388', '+1876');
INSERT INTO `country_codes_tbl` VALUES ('107', 'JP', 'JAPAN', 'Japan', 'JPN', '392', '+81');
INSERT INTO `country_codes_tbl` VALUES ('108', 'JO', 'JORDAN', 'Jordan', 'JOR', '400', '+962');
INSERT INTO `country_codes_tbl` VALUES ('109', 'KZ', 'KAZAKHSTAN', 'Kazakhstan', 'KAZ', '398', '+7');
INSERT INTO `country_codes_tbl` VALUES ('110', 'KE', 'KENYA', 'Kenya', 'KEN', '404', '+254');
INSERT INTO `country_codes_tbl` VALUES ('111', 'KI', 'KIRIBATI', 'Kiribati', 'KIR', '296', '+686');
INSERT INTO `country_codes_tbl` VALUES ('112', 'KP', 'KOREA, DEMOCRATIC PEOPLE\'S REPUBLIC OF', 'Korea, Democratic People\'s Republic of', 'PRK', '408', '+850');
INSERT INTO `country_codes_tbl` VALUES ('113', 'KR', 'KOREA, REPUBLIC OF', 'Korea, Republic of', 'KOR', '410', '+82');
INSERT INTO `country_codes_tbl` VALUES ('114', 'KW', 'KUWAIT', 'Kuwait', 'KWT', '414', '+965');
INSERT INTO `country_codes_tbl` VALUES ('115', 'KG', 'KYRGYZSTAN', 'Kyrgyzstan', 'KGZ', '417', '+996');
INSERT INTO `country_codes_tbl` VALUES ('116', 'LA', 'LAO PEOPLE\'S DEMOCRATIC REPUBLIC', 'Lao People\'s Democratic Republic', 'LAO', '418', '+856');
INSERT INTO `country_codes_tbl` VALUES ('117', 'LV', 'LATVIA', 'Latvia', 'LVA', '428', '+371');
INSERT INTO `country_codes_tbl` VALUES ('118', 'LB', 'LEBANON', 'Lebanon', 'LBN', '422', '+961');
INSERT INTO `country_codes_tbl` VALUES ('119', 'LS', 'LESOTHO', 'Lesotho', 'LSO', '426', '+266');
INSERT INTO `country_codes_tbl` VALUES ('120', 'LR', 'LIBERIA', 'Liberia', 'LBR', '430', '+231');
INSERT INTO `country_codes_tbl` VALUES ('121', 'LY', 'LIBYAN ARAB JAMAHIRIYA', 'Libyan Arab Jamahiriya', 'LBY', '434', '+218');
INSERT INTO `country_codes_tbl` VALUES ('122', 'LI', 'LIECHTENSTEIN', 'Liechtenstein', 'LIE', '438', '+423');
INSERT INTO `country_codes_tbl` VALUES ('123', 'LT', 'LITHUANIA', 'Lithuania', 'LTU', '440', '+370');
INSERT INTO `country_codes_tbl` VALUES ('124', 'LU', 'LUXEMBOURG', 'Luxembourg', 'LUX', '442', '+352');
INSERT INTO `country_codes_tbl` VALUES ('125', 'MO', 'MACAO', 'Macao', 'MAC', '446', '+853');
INSERT INTO `country_codes_tbl` VALUES ('126', 'MK', 'MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF', 'Macedonia, the Former Yugoslav Republic of', 'MKD', '807', '+389');
INSERT INTO `country_codes_tbl` VALUES ('127', 'MG', 'MADAGASCAR', 'Madagascar', 'MDG', '450', '+261');
INSERT INTO `country_codes_tbl` VALUES ('128', 'MW', 'MALAWI', 'Malawi', 'MWI', '454', '+265');
INSERT INTO `country_codes_tbl` VALUES ('129', 'MY', 'MALAYSIA', 'Malaysia', 'MYS', '458', '+60');
INSERT INTO `country_codes_tbl` VALUES ('130', 'MV', 'MALDIVES', 'Maldives', 'MDV', '462', '+960');
INSERT INTO `country_codes_tbl` VALUES ('131', 'ML', 'MALI', 'Mali', 'MLI', '466', '+223');
INSERT INTO `country_codes_tbl` VALUES ('132', 'MT', 'MALTA', 'Malta', 'MLT', '470', '+356');
INSERT INTO `country_codes_tbl` VALUES ('133', 'MH', 'MARSHALL ISLANDS', 'Marshall Islands', 'MHL', '584', '+692');
INSERT INTO `country_codes_tbl` VALUES ('134', 'MQ', 'MARTINIQUE', 'Martinique', 'MTQ', '474', '+596');
INSERT INTO `country_codes_tbl` VALUES ('135', 'MR', 'MAURITANIA', 'Mauritania', 'MRT', '478', '+222');
INSERT INTO `country_codes_tbl` VALUES ('136', 'MU', 'MAURITIUS', 'Mauritius', 'MUS', '480', '+230');
INSERT INTO `country_codes_tbl` VALUES ('137', 'YT', 'MAYOTTE', 'Mayotte', null, null, '+269');
INSERT INTO `country_codes_tbl` VALUES ('138', 'MX', 'MEXICO', 'Mexico', 'MEX', '484', '+52');
INSERT INTO `country_codes_tbl` VALUES ('139', 'FM', 'MICRONESIA, FEDERATED STATES OF', 'Micronesia, Federated States of', 'FSM', '583', '+691');
INSERT INTO `country_codes_tbl` VALUES ('140', 'MD', 'MOLDOVA, REPUBLIC OF', 'Moldova, Republic of', 'MDA', '498', '+373');
INSERT INTO `country_codes_tbl` VALUES ('141', 'MC', 'MONACO', 'Monaco', 'MCO', '492', '+377');
INSERT INTO `country_codes_tbl` VALUES ('142', 'MN', 'MONGOLIA', 'Mongolia', 'MNG', '496', '+976');
INSERT INTO `country_codes_tbl` VALUES ('143', 'MS', 'MONTSERRAT', 'Montserrat', 'MSR', '500', '+1664');
INSERT INTO `country_codes_tbl` VALUES ('144', 'MA', 'MOROCCO', 'Morocco', 'MAR', '504', '+212');
INSERT INTO `country_codes_tbl` VALUES ('145', 'MZ', 'MOZAMBIQUE', 'Mozambique', 'MOZ', '508', '+258');
INSERT INTO `country_codes_tbl` VALUES ('146', 'MM', 'MYANMAR', 'Myanmar', 'MMR', '104', '+95');
INSERT INTO `country_codes_tbl` VALUES ('147', 'NA', 'NAMIBIA', 'Namibia', 'NAM', '516', '+264');
INSERT INTO `country_codes_tbl` VALUES ('148', 'NR', 'NAURU', 'Nauru', 'NRU', '520', '+674');
INSERT INTO `country_codes_tbl` VALUES ('149', 'NP', 'NEPAL', 'Nepal', 'NPL', '524', '+977');
INSERT INTO `country_codes_tbl` VALUES ('150', 'NL', 'NETHERLANDS', 'Netherlands', 'NLD', '528', '+31');
INSERT INTO `country_codes_tbl` VALUES ('151', 'AN', 'NETHERLANDS ANTILLES', 'Netherlands Antilles', 'ANT', '530', '+599');
INSERT INTO `country_codes_tbl` VALUES ('152', 'NC', 'NEW CALEDONIA', 'New Caledonia', 'NCL', '540', '+687');
INSERT INTO `country_codes_tbl` VALUES ('153', 'NZ', 'NEW ZEALAND', 'New Zealand', 'NZL', '554', '+64');
INSERT INTO `country_codes_tbl` VALUES ('154', 'NI', 'NICARAGUA', 'Nicaragua', 'NIC', '558', '+505');
INSERT INTO `country_codes_tbl` VALUES ('155', 'NE', 'NIGER', 'Niger', 'NER', '562', '+227');
INSERT INTO `country_codes_tbl` VALUES ('156', 'NG', 'NIGERIA', 'Nigeria', 'NGA', '566', '+234');
INSERT INTO `country_codes_tbl` VALUES ('157', 'NU', 'NIUE', 'Niue', 'NIU', '570', '+683');
INSERT INTO `country_codes_tbl` VALUES ('158', 'NF', 'NORFOLK ISLAND', 'Norfolk Island', 'NFK', '574', '+672');
INSERT INTO `country_codes_tbl` VALUES ('159', 'MP', 'NORTHERN MARIANA ISLANDS', 'Northern Mariana Islands', 'MNP', '580', '+1670');
INSERT INTO `country_codes_tbl` VALUES ('160', 'NO', 'NORWAY', 'Norway', 'NOR', '578', '+47');
INSERT INTO `country_codes_tbl` VALUES ('161', 'OM', 'OMAN', 'Oman', 'OMN', '512', '+968');
INSERT INTO `country_codes_tbl` VALUES ('162', 'PK', 'PAKISTAN', 'Pakistan', 'PAK', '586', '+92');
INSERT INTO `country_codes_tbl` VALUES ('163', 'PW', 'PALAU', 'Palau', 'PLW', '585', '+680');
INSERT INTO `country_codes_tbl` VALUES ('164', 'PS', 'PALESTINIAN TERRITORY, OCCUPIED', 'Palestinian Territory, Occupied', null, null, '+970');
INSERT INTO `country_codes_tbl` VALUES ('165', 'PA', 'PANAMA', 'Panama', 'PAN', '591', '+507');
INSERT INTO `country_codes_tbl` VALUES ('166', 'PG', 'PAPUA NEW GUINEA', 'Papua New Guinea', 'PNG', '598', '+675');
INSERT INTO `country_codes_tbl` VALUES ('167', 'PY', 'PARAGUAY', 'Paraguay', 'PRY', '600', '+595');
INSERT INTO `country_codes_tbl` VALUES ('168', 'PE', 'PERU', 'Peru', 'PER', '604', '+51');
INSERT INTO `country_codes_tbl` VALUES ('169', 'PH', 'PHILIPPINES', 'Philippines', 'PHL', '608', '+63');
INSERT INTO `country_codes_tbl` VALUES ('170', 'PN', 'PITCAIRN', 'Pitcairn', 'PCN', '612', '+0');
INSERT INTO `country_codes_tbl` VALUES ('171', 'PL', 'POLAND', 'Poland', 'POL', '616', '+48');
INSERT INTO `country_codes_tbl` VALUES ('172', 'PT', 'PORTUGAL', 'Portugal', 'PRT', '620', '+351');
INSERT INTO `country_codes_tbl` VALUES ('173', 'PR', 'PUERTO RICO', 'Puerto Rico', 'PRI', '630', '+1787');
INSERT INTO `country_codes_tbl` VALUES ('174', 'QA', 'QATAR', 'Qatar', 'QAT', '634', '+974');
INSERT INTO `country_codes_tbl` VALUES ('175', 'RE', 'REUNION', 'Reunion', 'REU', '638', '+262');
INSERT INTO `country_codes_tbl` VALUES ('176', 'RO', 'ROMANIA', 'Romania', 'ROM', '642', '+40');
INSERT INTO `country_codes_tbl` VALUES ('177', 'RU', 'RUSSIAN FEDERATION', 'Russian Federation', 'RUS', '643', '+70');
INSERT INTO `country_codes_tbl` VALUES ('178', 'RW', 'RWANDA', 'Rwanda', 'RWA', '646', '+250');
INSERT INTO `country_codes_tbl` VALUES ('179', 'SH', 'SAINT HELENA', 'Saint Helena', 'SHN', '654', '+290');
INSERT INTO `country_codes_tbl` VALUES ('180', 'KN', 'SAINT KITTS AND NEVIS', 'Saint Kitts and Nevis', 'KNA', '659', '+1869');
INSERT INTO `country_codes_tbl` VALUES ('181', 'LC', 'SAINT LUCIA', 'Saint Lucia', 'LCA', '662', '+1758');
INSERT INTO `country_codes_tbl` VALUES ('182', 'PM', 'SAINT PIERRE AND MIQUELON', 'Saint Pierre and Miquelon', 'SPM', '666', '+508');
INSERT INTO `country_codes_tbl` VALUES ('183', 'VC', 'SAINT VINCENT AND THE GRENADINES', 'Saint Vincent and the Grenadines', 'VCT', '670', '+1784');
INSERT INTO `country_codes_tbl` VALUES ('184', 'WS', 'SAMOA', 'Samoa', 'WSM', '882', '+684');
INSERT INTO `country_codes_tbl` VALUES ('185', 'SM', 'SAN MARINO', 'San Marino', 'SMR', '674', '+378');
INSERT INTO `country_codes_tbl` VALUES ('186', 'ST', 'SAO TOME AND PRINCIPE', 'Sao Tome and Principe', 'STP', '678', '+239');
INSERT INTO `country_codes_tbl` VALUES ('187', 'SA', 'SAUDI ARABIA', 'Saudi Arabia', 'SAU', '682', '+966');
INSERT INTO `country_codes_tbl` VALUES ('188', 'SN', 'SENEGAL', 'Senegal', 'SEN', '686', '+221');
INSERT INTO `country_codes_tbl` VALUES ('189', 'CS', 'SERBIA AND MONTENEGRO', 'Serbia and Montenegro', null, null, '+381');
INSERT INTO `country_codes_tbl` VALUES ('190', 'SC', 'SEYCHELLES', 'Seychelles', 'SYC', '690', '+248');
INSERT INTO `country_codes_tbl` VALUES ('191', 'SL', 'SIERRA LEONE', 'Sierra Leone', 'SLE', '694', '+232');
INSERT INTO `country_codes_tbl` VALUES ('192', 'SG', 'SINGAPORE', 'Singapore', 'SGP', '702', '+65');
INSERT INTO `country_codes_tbl` VALUES ('193', 'SK', 'SLOVAKIA', 'Slovakia', 'SVK', '703', '+421');
INSERT INTO `country_codes_tbl` VALUES ('194', 'SI', 'SLOVENIA', 'Slovenia', 'SVN', '705', '+386');
INSERT INTO `country_codes_tbl` VALUES ('195', 'SB', 'SOLOMON ISLANDS', 'Solomon Islands', 'SLB', '90', '+677');
INSERT INTO `country_codes_tbl` VALUES ('196', 'SO', 'SOMALIA', 'Somalia', 'SOM', '706', '+252');
INSERT INTO `country_codes_tbl` VALUES ('197', 'ZA', 'SOUTH AFRICA', 'South Africa', 'ZAF', '710', '+27');
INSERT INTO `country_codes_tbl` VALUES ('198', 'GS', 'SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS', 'South Georgia and the South Sandwich Islands', null, null, '+0');
INSERT INTO `country_codes_tbl` VALUES ('199', 'ES', 'SPAIN', 'Spain', 'ESP', '724', '+34');
INSERT INTO `country_codes_tbl` VALUES ('200', 'LK', 'SRI LANKA', 'Sri Lanka', 'LKA', '144', '+94');
INSERT INTO `country_codes_tbl` VALUES ('201', 'SD', 'SUDAN', 'Sudan', 'SDN', '736', '+249');
INSERT INTO `country_codes_tbl` VALUES ('202', 'SR', 'SURINAME', 'Suriname', 'SUR', '740', '+597');
INSERT INTO `country_codes_tbl` VALUES ('203', 'SJ', 'SVALBARD AND JAN MAYEN', 'Svalbard and Jan Mayen', 'SJM', '744', '+47');
INSERT INTO `country_codes_tbl` VALUES ('204', 'SZ', 'SWAZILAND', 'Swaziland', 'SWZ', '748', '+268');
INSERT INTO `country_codes_tbl` VALUES ('205', 'SE', 'SWEDEN', 'Sweden', 'SWE', '752', '+46');
INSERT INTO `country_codes_tbl` VALUES ('206', 'CH', 'SWITZERLAND', 'Switzerland', 'CHE', '756', '+41');
INSERT INTO `country_codes_tbl` VALUES ('207', 'SY', 'SYRIAN ARAB REPUBLIC', 'Syrian Arab Republic', 'SYR', '760', '+963');
INSERT INTO `country_codes_tbl` VALUES ('208', 'TW', 'TAIWAN, PROVINCE OF CHINA', 'Taiwan, Province of China', 'TWN', '158', '+886');
INSERT INTO `country_codes_tbl` VALUES ('209', 'TJ', 'TAJIKISTAN', 'Tajikistan', 'TJK', '762', '+992');
INSERT INTO `country_codes_tbl` VALUES ('210', 'TZ', 'TANZANIA, UNITED REPUBLIC OF', 'Tanzania, United Republic of', 'TZA', '834', '+255');
INSERT INTO `country_codes_tbl` VALUES ('211', 'TH', 'THAILAND', 'Thailand', 'THA', '764', '+66');
INSERT INTO `country_codes_tbl` VALUES ('212', 'TL', 'TIMOR-LESTE', 'Timor-Leste', null, null, '+670');
INSERT INTO `country_codes_tbl` VALUES ('213', 'TG', 'TOGO', 'Togo', 'TGO', '768', '+228');
INSERT INTO `country_codes_tbl` VALUES ('214', 'TK', 'TOKELAU', 'Tokelau', 'TKL', '772', '+690');
INSERT INTO `country_codes_tbl` VALUES ('215', 'TO', 'TONGA', 'Tonga', 'TON', '776', '+676');
INSERT INTO `country_codes_tbl` VALUES ('216', 'TT', 'TRINIDAD AND TOBAGO', 'Trinidad and Tobago', 'TTO', '780', '+1868');
INSERT INTO `country_codes_tbl` VALUES ('217', 'TN', 'TUNISIA', 'Tunisia', 'TUN', '788', '+216');
INSERT INTO `country_codes_tbl` VALUES ('218', 'TR', 'TURKEY', 'Turkey', 'TUR', '792', '+90');
INSERT INTO `country_codes_tbl` VALUES ('219', 'TM', 'TURKMENISTAN', 'Turkmenistan', 'TKM', '795', '+7370');
INSERT INTO `country_codes_tbl` VALUES ('220', 'TC', 'TURKS AND CAICOS ISLANDS', 'Turks and Caicos Islands', 'TCA', '796', '+1649');
INSERT INTO `country_codes_tbl` VALUES ('221', 'TV', 'TUVALU', 'Tuvalu', 'TUV', '798', '+688');
INSERT INTO `country_codes_tbl` VALUES ('222', 'UG', 'UGANDA', 'Uganda', 'UGA', '800', '+256');
INSERT INTO `country_codes_tbl` VALUES ('223', 'UA', 'UKRAINE', 'Ukraine', 'UKR', '804', '+380');
INSERT INTO `country_codes_tbl` VALUES ('224', 'AE', 'UNITED ARAB EMIRATES', 'United Arab Emirates', 'ARE', '784', '+971');
INSERT INTO `country_codes_tbl` VALUES ('225', 'GB', 'UNITED KINGDOM', 'United Kingdom', 'GBR', '826', '+44');
INSERT INTO `country_codes_tbl` VALUES ('226', 'US', 'UNITED STATES', 'United States', 'USA', '840', '+1');
INSERT INTO `country_codes_tbl` VALUES ('227', 'UM', 'UNITED STATES MINOR OUTLYING ISLANDS', 'United States Minor Outlying Islands', null, null, '+1');
INSERT INTO `country_codes_tbl` VALUES ('228', 'UY', 'URUGUAY', 'Uruguay', 'URY', '858', '+598');
INSERT INTO `country_codes_tbl` VALUES ('229', 'UZ', 'UZBEKISTAN', 'Uzbekistan', 'UZB', '860', '+998');
INSERT INTO `country_codes_tbl` VALUES ('230', 'VU', 'VANUATU', 'Vanuatu', 'VUT', '548', '+678');
INSERT INTO `country_codes_tbl` VALUES ('231', 'VE', 'VENEZUELA', 'Venezuela', 'VEN', '862', '+58');
INSERT INTO `country_codes_tbl` VALUES ('232', 'VN', 'VIET NAM', 'Viet Nam', 'VNM', '704', '+84');
INSERT INTO `country_codes_tbl` VALUES ('233', 'VG', 'VIRGIN ISLANDS, BRITISH', 'Virgin Islands, British', 'VGB', '92', '+1284');
INSERT INTO `country_codes_tbl` VALUES ('234', 'VI', 'VIRGIN ISLANDS, U.S.', 'Virgin Islands, U.s.', 'VIR', '850', '+1340');
INSERT INTO `country_codes_tbl` VALUES ('235', 'WF', 'WALLIS AND FUTUNA', 'Wallis and Futuna', 'WLF', '876', '+681');
INSERT INTO `country_codes_tbl` VALUES ('236', 'EH', 'WESTERN SAHARA', 'Western Sahara', 'ESH', '732', '+212');
INSERT INTO `country_codes_tbl` VALUES ('237', 'YE', 'YEMEN', 'Yemen', 'YEM', '887', '+967');
INSERT INTO `country_codes_tbl` VALUES ('238', 'ZM', 'ZAMBIA', 'Zambia', 'ZMB', '894', '+260');
INSERT INTO `country_codes_tbl` VALUES ('239', 'ZW', 'ZIMBABWE', 'Zimbabwe', 'ZWE', '716', '+263');

-- ----------------------------
-- Table structure for country_state_district
-- ----------------------------
DROP TABLE IF EXISTS `country_state_district`;
CREATE TABLE `country_state_district` (
  `Country` varchar(255) NOT NULL,
  `State` varchar(255) DEFAULT NULL,
  `District` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=662 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of country_state_district
-- ----------------------------
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'Nellore', '1');
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'Cuddapah', '2');
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'Karim Nagar', '3');
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'Kurnool', '4');
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'West Godavari', '5');
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'Srikakulam', '6');
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'Anantpur', '7');
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'Adilabad', '8');
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'Chittor', '9');
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'east Godavari', '10');
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'Guntur', '11');
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'Hyderabad Urban', '12');
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'Khammam', '13');
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'Krishna', '14');
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'Mehboobnagar', '15');
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'Medak', '16');
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'Nalgonda', '17');
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'Nizamabad', '18');
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'Prakasam', '19');
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'Ranga Reddy', '20');
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'Vishakapatnam', '21');
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'Vizianagaram', '22');
INSERT INTO `country_state_district` VALUES ('India', 'Andhra Pradesh', 'Warangal', '23');
INSERT INTO `country_state_district` VALUES ('India', 'Arunachal Pradesh', 'Diban Valley', '24');
INSERT INTO `country_state_district` VALUES ('India', 'Arunachal Pradesh', 'East Kameng Seppa', '25');
INSERT INTO `country_state_district` VALUES ('India', 'Arunachal Pradesh', 'Itanagar', '26');
INSERT INTO `country_state_district` VALUES ('India', 'Arunachal Pradesh', 'East Siang(Passighat)', '27');
INSERT INTO `country_state_district` VALUES ('India', 'Arunachal Pradesh', 'Lohit(Tezu)', '28');
INSERT INTO `country_state_district` VALUES ('India', 'Arunachal Pradesh', 'Lower Subansiri(Ziro)', '29');
INSERT INTO `country_state_district` VALUES ('India', 'Arunachal Pradesh', 'Khonsa', '30');
INSERT INTO `country_state_district` VALUES ('India', 'Arunachal Pradesh', 'Roin', '31');
INSERT INTO `country_state_district` VALUES ('India', 'Arunachal Pradesh', 'Tawang', '32');
INSERT INTO `country_state_district` VALUES ('India', 'Arunachal Pradesh', 'Changalang', '33');
INSERT INTO `country_state_district` VALUES ('India', 'Arunachal Pradesh', 'Dibang Valley', '34');
INSERT INTO `country_state_district` VALUES ('India', 'Arunachal Pradesh', 'Daporijo', '35');
INSERT INTO `country_state_district` VALUES ('India', 'Arunachal Pradesh', 'Bomdila', '36');
INSERT INTO `country_state_district` VALUES ('India', 'Arunachal Pradesh', 'Alog( West Siang )', '37');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'Karimganj', '38');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'Darrang', '39');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'Dibrugarh', '40');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'Morigaon', '41');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'Tinsukia', '42');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'Bongaigaon', '43');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'Nalbari', '44');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'Kokrajhar', '45');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'Kamrup', '46');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'Karbi-Anglong', '47');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'Nagaon', '48');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'N.C.Hills', '49');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'Dhemaji', '50');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'Hailakandi', '51');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'Lakhimpur', '52');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'Sonitpur', '53');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'Dhubri', '54');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'Goalpara', '55');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'Barpeta', '56');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'Golaghat', '57');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'Jorhat', '58');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'Sibsagar', '59');
INSERT INTO `country_state_district` VALUES ('India', 'Assam', 'Cachar', '60');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Muzaffarpur', '61');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Jehanabad', '62');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Gaya', '63');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Patna', '64');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Saran( Chapra )', '65');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Darbhanga', '66');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Saharsa', '67');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Purnea', '68');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Bhagalpur', '69');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Munger', '70');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Aurangabad', '71');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Bhojpur(Arah)', '72');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Begusarai', '73');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'East Champaran', '74');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Gopalganj', '75');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Jamui', '76');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Katihar', '77');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Khagaria', '78');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Madhepura', '79');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Madhubani', '80');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Nalanda', '81');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Nawada', '82');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Rohtas(Sasaram)', '83');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Samastipur', '84');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Sitamarhi', '85');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Siwan', '86');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Vaishali(Hajipur)', '87');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'West Champaran', '88');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Kishanganj', '89');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Araria', '90');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Bhabua', '91');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Banka', '92');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Buxar', '93');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Supaul', '94');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Sekhpura', '95');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Seohar', '96');
INSERT INTO `country_state_district` VALUES ('India', 'Bihar', 'Lakhisarai', '97');
INSERT INTO `country_state_district` VALUES ('India', 'Chhattisgarh', 'Bastar', '98');
INSERT INTO `country_state_district` VALUES ('India', 'Chhattisgarh', 'Bilaspur', '99');
INSERT INTO `country_state_district` VALUES ('India', 'Chhattisgarh', 'Dantewada', '100');
INSERT INTO `country_state_district` VALUES ('India', 'Chhattisgarh', 'Dhamtari', '101');
INSERT INTO `country_state_district` VALUES ('India', 'Chhattisgarh', 'Durg', '102');
INSERT INTO `country_state_district` VALUES ('India', 'Chhattisgarh', 'Janjgir-Champa', '103');
INSERT INTO `country_state_district` VALUES ('India', 'Chhattisgarh', 'Jashpur', '104');
INSERT INTO `country_state_district` VALUES ('India', 'Chhattisgarh', 'Kanker', '105');
INSERT INTO `country_state_district` VALUES ('India', 'Chhattisgarh', 'Kawardha', '106');
INSERT INTO `country_state_district` VALUES ('India', 'Chhattisgarh', 'Korba', '107');
INSERT INTO `country_state_district` VALUES ('India', 'Chhattisgarh', 'Koriya', '108');
INSERT INTO `country_state_district` VALUES ('India', 'Chhattisgarh', 'Mahasamund', '109');
INSERT INTO `country_state_district` VALUES ('India', 'Chhattisgarh', 'Raigarh', '110');
INSERT INTO `country_state_district` VALUES ('India', 'Chhattisgarh', 'Rajnandgaon', '111');
INSERT INTO `country_state_district` VALUES ('India', 'Chhattisgarh', 'Surguja', '112');
INSERT INTO `country_state_district` VALUES ('India', 'Chhattisgarh', 'Raipur', '113');
INSERT INTO `country_state_district` VALUES ('India', 'Goa', 'North Goa', '114');
INSERT INTO `country_state_district` VALUES ('India', 'Goa', 'South Goa', '115');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Panchmahals', '116');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Vadodara', '117');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Amrela', '118');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Kheda', '119');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Ahmedabad', '120');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Valsad', '121');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Junagadh', '122');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Mehsana', '123');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Banaskantha', '124');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Gandhinagar', '125');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Bharuch', '126');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Dangs', '127');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Jamnagar', '128');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Rajkot', '129');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Surat', '130');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Sabarkantha', '131');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Kutch', '132');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Bhavnagar', '133');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Surendranagar', '134');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Navsari', '135');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Anand', '136');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Narmada', '137');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Patan', '138');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Porbander', '139');
INSERT INTO `country_state_district` VALUES ('India', 'Gujarat', 'Dahod', '140');
INSERT INTO `country_state_district` VALUES ('India', 'Haryana', 'Gurgaon', '141');
INSERT INTO `country_state_district` VALUES ('India', 'Haryana', 'Rohtak', '142');
INSERT INTO `country_state_district` VALUES ('India', 'Haryana', 'Ambala', '143');
INSERT INTO `country_state_district` VALUES ('India', 'Haryana', 'Bhiwani', '144');
INSERT INTO `country_state_district` VALUES ('India', 'Haryana', 'Faridabad', '145');
INSERT INTO `country_state_district` VALUES ('India', 'Haryana', 'Hissar', '146');
INSERT INTO `country_state_district` VALUES ('India', 'Haryana', 'Jind', '147');
INSERT INTO `country_state_district` VALUES ('India', 'Haryana', 'Kaithal', '148');
INSERT INTO `country_state_district` VALUES ('India', 'Haryana', 'Karnal', '149');
INSERT INTO `country_state_district` VALUES ('India', 'Haryana', 'Kurukshetra', '150');
INSERT INTO `country_state_district` VALUES ('India', 'Haryana', 'Mahendragarh', '151');
INSERT INTO `country_state_district` VALUES ('India', 'Haryana', 'Panchkula', '152');
INSERT INTO `country_state_district` VALUES ('India', 'Haryana', 'Panipat', '153');
INSERT INTO `country_state_district` VALUES ('India', 'Haryana', 'Sonipat', '154');
INSERT INTO `country_state_district` VALUES ('India', 'Haryana', 'Yamunanagar', '155');
INSERT INTO `country_state_district` VALUES ('India', 'Haryana', 'Sirsa', '156');
INSERT INTO `country_state_district` VALUES ('India', 'Haryana', 'Rewari', '157');
INSERT INTO `country_state_district` VALUES ('India', 'Haryana', 'Jhanjhar', '158');
INSERT INTO `country_state_district` VALUES ('India', 'Haryana', 'Fatehabad', '159');
INSERT INTO `country_state_district` VALUES ('India', 'Himachal Pradesh', 'Sirmour', '160');
INSERT INTO `country_state_district` VALUES ('India', 'Himachal Pradesh', 'Hamirpur', '161');
INSERT INTO `country_state_district` VALUES ('India', 'Himachal Pradesh', 'Kullu', '162');
INSERT INTO `country_state_district` VALUES ('India', 'Himachal Pradesh', 'Solan', '163');
INSERT INTO `country_state_district` VALUES ('India', 'Himachal Pradesh', 'Mandi', '164');
INSERT INTO `country_state_district` VALUES ('India', 'Himachal Pradesh', 'Chamba', '165');
INSERT INTO `country_state_district` VALUES ('India', 'Himachal Pradesh', 'Bilaspur', '166');
INSERT INTO `country_state_district` VALUES ('India', 'Himachal Pradesh', 'Kangra', '167');
INSERT INTO `country_state_district` VALUES ('India', 'Himachal Pradesh', 'Kinnaur', '168');
INSERT INTO `country_state_district` VALUES ('India', 'Himachal Pradesh', 'Lahaul-Spiti', '169');
INSERT INTO `country_state_district` VALUES ('India', 'Himachal Pradesh', 'Shimla', '170');
INSERT INTO `country_state_district` VALUES ('India', 'Himachal Pradesh', 'Una', '171');
INSERT INTO `country_state_district` VALUES ('India', 'Jammu & Kashmir', 'Kathua', '172');
INSERT INTO `country_state_district` VALUES ('India', 'Jammu & Kashmir', 'Badgan', '173');
INSERT INTO `country_state_district` VALUES ('India', 'Jammu & Kashmir', 'Poonch', '174');
INSERT INTO `country_state_district` VALUES ('India', 'Jammu & Kashmir', 'Rajauri', '175');
INSERT INTO `country_state_district` VALUES ('India', 'Jammu & Kashmir', 'Baramula', '176');
INSERT INTO `country_state_district` VALUES ('India', 'Jammu & Kashmir', 'Doda', '177');
INSERT INTO `country_state_district` VALUES ('India', 'Jammu & Kashmir', 'Udhampur', '178');
INSERT INTO `country_state_district` VALUES ('India', 'Jammu & Kashmir', 'Jammu', '179');
INSERT INTO `country_state_district` VALUES ('India', 'Jammu & Kashmir', 'Kupwara', '180');
INSERT INTO `country_state_district` VALUES ('India', 'Jammu & Kashmir', 'Pulwama', '181');
INSERT INTO `country_state_district` VALUES ('India', 'Jammu & Kashmir', 'Anantnag', '182');
INSERT INTO `country_state_district` VALUES ('India', 'Jammu & Kashmir', 'Srinagar', '183');
INSERT INTO `country_state_district` VALUES ('India', 'Jammu & Kashmir', 'Leh', '184');
INSERT INTO `country_state_district` VALUES ('India', 'Jammu & Kashmir', 'Kargil', '185');
INSERT INTO `country_state_district` VALUES ('India', 'Jharkhand', 'Deoghar', '186');
INSERT INTO `country_state_district` VALUES ('India', 'Jharkhand', 'Dhanbad', '187');
INSERT INTO `country_state_district` VALUES ('India', 'Jharkhand', 'Giridih', '188');
INSERT INTO `country_state_district` VALUES ('India', 'Jharkhand', 'Godda', '189');
INSERT INTO `country_state_district` VALUES ('India', 'Jharkhand', 'Gumla', '190');
INSERT INTO `country_state_district` VALUES ('India', 'Jharkhand', 'Hazaribagh', '191');
INSERT INTO `country_state_district` VALUES ('India', 'Jharkhand', 'Lohardaga', '192');
INSERT INTO `country_state_district` VALUES ('India', 'Jharkhand', 'Palamu', '193');
INSERT INTO `country_state_district` VALUES ('India', 'Jharkhand', 'Ranchi', '194');
INSERT INTO `country_state_district` VALUES ('India', 'Jharkhand', 'Dumka', '195');
INSERT INTO `country_state_district` VALUES ('India', 'Jharkhand', 'Chaibasa', '196');
INSERT INTO `country_state_district` VALUES ('India', 'Jharkhand', 'Jamshedpur', '197');
INSERT INTO `country_state_district` VALUES ('India', 'Jharkhand', 'Bokaro', '198');
INSERT INTO `country_state_district` VALUES ('India', 'Jharkhand', 'Chatra', '199');
INSERT INTO `country_state_district` VALUES ('India', 'Jharkhand', 'Garhwa', '200');
INSERT INTO `country_state_district` VALUES ('India', 'Jharkhand', 'Koderma', '201');
INSERT INTO `country_state_district` VALUES ('India', 'Jharkhand', 'Pakur', '202');
INSERT INTO `country_state_district` VALUES ('India', 'Jharkhand', 'Sahebganj', '203');
INSERT INTO `country_state_district` VALUES ('India', 'Jharkhand', 'Simdega', '204');
INSERT INTO `country_state_district` VALUES ('India', 'Jharkhand', 'Latehar', '205');
INSERT INTO `country_state_district` VALUES ('India', 'Jharkhand', 'Saraikela', '206');
INSERT INTO `country_state_district` VALUES ('India', 'Jharkhand', 'Jamtara', '207');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Mysore', '208');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Gulberga', '209');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Chitradurga', '210');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Kolar', '211');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Bijapur', '212');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Dakshina Kannada', '213');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Raichur', '214');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Bellary', '215');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Belgaum', '216');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Hassan', '217');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Dharwad', '218');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Bangalore Rural', '219');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Shimoga', '220');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Mandya', '221');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Chickmagalur', '222');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Bangalore Urban', '223');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Madikeri', '224');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Tumkur', '225');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Bidar', '226');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Karwar', '227');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Udupi', '228');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Davanagare', '229');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Chamrajnagar', '230');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Koppal', '231');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Haveri', '232');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Gadak', '233');
INSERT INTO `country_state_district` VALUES ('India', 'Karnataka', 'Yadgir', '234');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Sindi', '249');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Vidisha', '250');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Jabalpur', '251');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Bhopal', '252');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Hoshangabad', '253');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Indore', '254');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Rewa', '255');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Satna', '256');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Shahdol', '257');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Chhindwara', '258');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Ratlam', '259');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Balaghat', '260');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Betul', '261');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Bhind', '262');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Mandla', '263');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Chhattarpur', '264');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Damoh', '265');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Datia', '266');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Dewas', '267');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Dhar', '268');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Guna', '269');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Gwalior', '270');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Jhabua', '271');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Sehore', '272');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Mandsaur', '273');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Narsinghpur', '274');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Panna', '275');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Raisen', '276');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Rajgarh', '277');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Sagar', '278');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Seoni', '279');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Morena', '280');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Shivpuri', '281');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Shajapur', '282');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Tikamgarh', '283');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Ujjain', '284');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Khandwa', '285');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Khargone', '286');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Dindori', '287');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Umaria', '288');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Badwani', '289');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Sheopur', '290');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Katni', '291');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Neemuch', '292');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Harda', '293');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Anooppur', '294');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Burhanpur', '295');
INSERT INTO `country_state_district` VALUES ('India', 'Madhya Pradesh', 'Ashoknagar', '296');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Aurangabad', '297');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Bandra', '298');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Nagpur', '299');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Pune', '300');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Akola', '301');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Chandrapur', '302');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Jalgaon', '303');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Parbhani', '304');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Sholapur', '305');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Thane', '306');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Latur', '307');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Mumbai-City', '308');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Buldhana', '309');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Dhule', '310');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Kolhpur', '311');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Nanded', '312');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Raigad', '313');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Amravati', '314');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Nashik', '315');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Wardha', '316');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Ahmednagar', '317');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Beed', '318');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Bhandara', '319');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Gadchiroli', '320');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Jalna', '321');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Osmanabad', '322');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Ratnagiri', '323');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Sangli', '324');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Satara', '325');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Sindudurg', '326');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Yavatmal', '327');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Nandurbar', '328');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Washim', '329');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Gondia', '330');
INSERT INTO `country_state_district` VALUES ('India', 'Maharashtra', 'Hingoli', '331');
INSERT INTO `country_state_district` VALUES ('India', 'Manipur', 'Imphal East', '332');
INSERT INTO `country_state_district` VALUES ('India', 'Manipur', 'Imphal West', '333');
INSERT INTO `country_state_district` VALUES ('India', 'Manipur', 'Thoubal', '334');
INSERT INTO `country_state_district` VALUES ('India', 'Manipur', 'Bishnupur', '335');
INSERT INTO `country_state_district` VALUES ('India', 'Manipur', 'Chandel', '336');
INSERT INTO `country_state_district` VALUES ('India', 'Manipur', 'Churachandpur', '337');
INSERT INTO `country_state_district` VALUES ('India', 'Manipur', 'Senapati', '338');
INSERT INTO `country_state_district` VALUES ('India', 'Manipur', 'Ukhrul', '339');
INSERT INTO `country_state_district` VALUES ('India', 'Manipur', 'Tamenglong', '340');
INSERT INTO `country_state_district` VALUES ('India', 'Meghalaya', 'Ri-Bhoi District', '341');
INSERT INTO `country_state_district` VALUES ('India', 'Meghalaya', 'South Garo Hills', '342');
INSERT INTO `country_state_district` VALUES ('India', 'Meghalaya', 'East Khasi Hill', '343');
INSERT INTO `country_state_district` VALUES ('India', 'Meghalaya', 'East Garo Hill', '344');
INSERT INTO `country_state_district` VALUES ('India', 'Meghalaya', 'West Garo Hill', '345');
INSERT INTO `country_state_district` VALUES ('India', 'Meghalaya', 'Jaintia Hill', '346');
INSERT INTO `country_state_district` VALUES ('India', 'Meghalaya', 'West Khasi Hill', '347');
INSERT INTO `country_state_district` VALUES ('India', 'Mizoram', 'Luglei District', '348');
INSERT INTO `country_state_district` VALUES ('India', 'Mizoram', 'Chimtipui District', '349');
INSERT INTO `country_state_district` VALUES ('India', 'Mizoram', 'Aizawal', '350');
INSERT INTO `country_state_district` VALUES ('India', 'Mizoram', 'Champhai', '351');
INSERT INTO `country_state_district` VALUES ('India', 'Mizoram', 'Mamit', '352');
INSERT INTO `country_state_district` VALUES ('India', 'Mizoram', 'Kolasib', '353');
INSERT INTO `country_state_district` VALUES ('India', 'Mizoram', 'Serchhip', '354');
INSERT INTO `country_state_district` VALUES ('India', 'Mizoram', 'Lawngtlai', '355');
INSERT INTO `country_state_district` VALUES ('India', 'Nagaland', 'Wokha', '356');
INSERT INTO `country_state_district` VALUES ('India', 'Nagaland', 'Phek', '357');
INSERT INTO `country_state_district` VALUES ('India', 'Nagaland', 'Tuensang', '358');
INSERT INTO `country_state_district` VALUES ('India', 'Nagaland', 'Mon', '359');
INSERT INTO `country_state_district` VALUES ('India', 'Nagaland', 'Kohima', '360');
INSERT INTO `country_state_district` VALUES ('India', 'Nagaland', 'Zunheboto', '361');
INSERT INTO `country_state_district` VALUES ('India', 'Nagaland', 'Mokokchung', '362');
INSERT INTO `country_state_district` VALUES ('India', 'Nagaland', 'Dimapur', '363');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Khurda', '364');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Navaragpur', '365');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Navapada', '366');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Gajapati', '367');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Boudh', '368');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Bhadrak', '369');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Ganjam', '370');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Dhenkanal', '371');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Angul', '372');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Puri', '373');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Cuttak', '374');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Sambalpur', '375');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Kalhandi', '376');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Koraput', '377');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Phulbani', '378');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Balangir', '379');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Bargah', '380');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Deogarh', '381');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Jagatsinghpur', '382');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Jajpur', '383');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Jharsuguda', '384');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Kendrapara', '385');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Malkangiri', '386');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Nayagarh', '387');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Rayagada', '388');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Sonepur', '389');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Balasore', '390');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Mayurbhanj', '391');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Keonjhar', '392');
INSERT INTO `country_state_district` VALUES ('India', 'Odisha (Orissa)', 'Sundergarh', '393');
INSERT INTO `country_state_district` VALUES ('India', 'Punjab', 'Sangrur', '394');
INSERT INTO `country_state_district` VALUES ('India', 'Punjab', 'Jalandhar', '395');
INSERT INTO `country_state_district` VALUES ('India', 'Punjab', 'Ludhiana', '396');
INSERT INTO `country_state_district` VALUES ('India', 'Punjab', 'Bhatinda', '397');
INSERT INTO `country_state_district` VALUES ('India', 'Punjab', 'Kapurthala', '398');
INSERT INTO `country_state_district` VALUES ('India', 'Punjab', 'Patiala', '399');
INSERT INTO `country_state_district` VALUES ('India', 'Punjab', 'Amritsar', '400');
INSERT INTO `country_state_district` VALUES ('India', 'Punjab', 'Ferozepur', '401');
INSERT INTO `country_state_district` VALUES ('India', 'Punjab', 'Fatehgarh Saheb', '402');
INSERT INTO `country_state_district` VALUES ('India', 'Punjab', 'Ropar', '403');
INSERT INTO `country_state_district` VALUES ('India', 'Punjab', 'Gurdaspur', '404');
INSERT INTO `country_state_district` VALUES ('India', 'Punjab', 'Hosiarpur', '405');
INSERT INTO `country_state_district` VALUES ('India', 'Punjab', 'Faridkot', '406');
INSERT INTO `country_state_district` VALUES ('India', 'Punjab', 'Mansa', '407');
INSERT INTO `country_state_district` VALUES ('India', 'Punjab', 'Moga', '408');
INSERT INTO `country_state_district` VALUES ('India', 'Punjab', 'Muktsar', '409');
INSERT INTO `country_state_district` VALUES ('India', 'Punjab', 'Navansahar', '410');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Jaipur', '411');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Barmer', '412');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Dungarpur', '413');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Jodhpur', '414');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Kota', '415');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Udaipur', '416');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Bikaner', '417');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Dausa', '418');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Bundi', '419');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Sikar', '420');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Tonk', '421');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Jaisalmer', '422');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Nagaur', '423');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Rajsamand', '424');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Banswara', '425');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Bhilwara', '426');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Ajmer', '427');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Alwar', '428');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Bharatpur', '429');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Chittorgarh', '430');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Churu', '431');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Dholpur', '432');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Ganganagar', '433');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Jalor', '434');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Jhalawar', '435');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Jhunjhunu', '436');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Pali', '437');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Sawai Madhopur', '438');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Sirohi', '439');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Baran', '440');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Hanumangarh', '441');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Karauli', '442');
INSERT INTO `country_state_district` VALUES ('India', 'Sikkim', 'East', '443');
INSERT INTO `country_state_district` VALUES ('India', 'Sikkim', 'South', '444');
INSERT INTO `country_state_district` VALUES ('India', 'Sikkim', 'West', '445');
INSERT INTO `country_state_district` VALUES ('India', 'Sikkim', 'North', '446');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Chennai', '447');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Coimbotore', '448');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Cuddalorei', '449');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Dharmapuri', '450');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Dindigul', '451');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Erode', '452');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Kancheepuram', '453');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Kanniyakumari ', '454');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Karur', '455');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Madurai', '456');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Nagapattinam', '457');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Namakkal', '458');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Nilgiris', '459');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Perambalur', '460');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Pudukkottai', '461');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Ramanathapuram', '462');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Salem', '463');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Sivaganga', '464');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Thanjavur', '465');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Theni', '466');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Thoothkudi', '467');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Tiruchiorappalli', '468');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Tirunelveli', '469');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Tiruvallur', '470');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Tiruvannamalai', '471');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Tiruvarur', '472');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Vellore', '473');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Villupuram', '474');
INSERT INTO `country_state_district` VALUES ('India', 'Tamil Nadu', 'Virudhunagar', '475');
INSERT INTO `country_state_district` VALUES ('India', 'Tripura', 'North District', '476');
INSERT INTO `country_state_district` VALUES ('India', 'Tripura', 'South District', '477');
INSERT INTO `country_state_district` VALUES ('India', 'Tripura', 'West District', '478');
INSERT INTO `country_state_district` VALUES ('India', 'Tripura', 'Dhalai District', '479');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Allahabad', '480');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Aligarh', '481');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Bareilly', '482');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Gonda', '483');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Hardoi', '484');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Kanpur Dehat', '485');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Ghaziabad', '486');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Unnav', '487');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Varanasi', '488');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Faizabad', '489');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Gorakpur', '490');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Jhansi', '491');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Lucknow', '492');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Agra', '493');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Meerut', '494');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Moradabad', '495');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Barabanki', '496');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Mainpuri', '497');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Etawah', '498');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Gazipur', '499');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Etah', '500');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Muzaffar Nagar', '501');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Saharanpur', '502');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Bulandshehar', '503');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Mathura', '504');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Firozabad', '505');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Budaun', '506');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Shahjahanpur', '507');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Pilibhit', '508');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Bijnor', '509');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Rampur', '510');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Kanpur(Nagar)', '511');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Farrukhabad', '512');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Fatehpur', '513');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Pratapgarh', '514');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Jalaun', '515');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Hamirpur', '516');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Lalitpur', '517');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Mirzapur', '518');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Basti', '519');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Deoria', '520');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Raebareili', '521');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Sitapur', '522');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Banda', '523');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Lakhimpur-Khedi', '524');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Bahraich', '525');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Sultanpur', '526');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Mau', '527');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Azamgarh', '528');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Jaunpur', '529');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Balia', '530');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Bhadoi', '531');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Padrauna', '532');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Maharajganj', '533');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Siddharth Nagar', '534');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Sunbhadra', '535');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Mahoba', '536');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Ambedkarnagar', '537');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Gautam Bodda Nagar', '538');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Maha Maya Nagar', '539');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'jyotiba Phoole Nagar', '540');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Kaushambi', '541');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Shooji Maharaj', '542');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Chandauli', '543');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Balrampur', '544');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Shravati', '545');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Bagpat', '546');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Kanooj', '547');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Oraiyya', '548');
INSERT INTO `country_state_district` VALUES ('India', 'Uttar Pradesh', 'Sant Kabir Nagar', '549');
INSERT INTO `country_state_district` VALUES ('India', 'Uttarakhand', 'Nainital', '550');
INSERT INTO `country_state_district` VALUES ('India', 'Uttarakhand', 'Almora', '551');
INSERT INTO `country_state_district` VALUES ('India', 'Uttarakhand', 'Pitoragarh', '552');
INSERT INTO `country_state_district` VALUES ('India', 'Uttarakhand', 'Udhamsingh Nagar', '553');
INSERT INTO `country_state_district` VALUES ('India', 'Uttarakhand', 'Bageshwar', '554');
INSERT INTO `country_state_district` VALUES ('India', 'Uttarakhand', 'Champawat', '555');
INSERT INTO `country_state_district` VALUES ('India', 'Uttarakhand', 'Garhwal(Pauri)', '556');
INSERT INTO `country_state_district` VALUES ('India', 'Uttarakhand', 'Tehri-Garhwal', '557');
INSERT INTO `country_state_district` VALUES ('India', 'Uttarakhand', 'Chamoli( Gopeshwar )', '558');
INSERT INTO `country_state_district` VALUES ('India', 'Uttarakhand', 'Uttarkashi', '559');
INSERT INTO `country_state_district` VALUES ('India', 'Uttarakhand', 'Dehradun', '560');
INSERT INTO `country_state_district` VALUES ('India', 'Uttarakhand', 'Rudraprayag', '561');
INSERT INTO `country_state_district` VALUES ('India', 'Uttarakhand', 'Haridwar', '562');
INSERT INTO `country_state_district` VALUES ('India', 'West Bengal', 'Howrah', '563');
INSERT INTO `country_state_district` VALUES ('India', 'West Bengal', 'Darjeeling', '564');
INSERT INTO `country_state_district` VALUES ('India', 'West Bengal', 'Medinipur', '565');
INSERT INTO `country_state_district` VALUES ('India', 'West Bengal', 'Murshidabad', '566');
INSERT INTO `country_state_district` VALUES ('India', 'West Bengal', 'Coochbehar', '567');
INSERT INTO `country_state_district` VALUES ('India', 'West Bengal', 'Malda', '568');
INSERT INTO `country_state_district` VALUES ('India', 'West Bengal', 'Birbhum', '569');
INSERT INTO `country_state_district` VALUES ('India', 'West Bengal', 'North 24 Parganas', '570');
INSERT INTO `country_state_district` VALUES ('India', 'West Bengal', 'South 24 Parganas', '571');
INSERT INTO `country_state_district` VALUES ('India', 'West Bengal', 'Bankura', '572');
INSERT INTO `country_state_district` VALUES ('India', 'West Bengal', 'Bardhaman', '573');
INSERT INTO `country_state_district` VALUES ('India', 'West Bengal', 'Jalpaiguri', '574');
INSERT INTO `country_state_district` VALUES ('India', 'West Bengal', 'Hooghly', '575');
INSERT INTO `country_state_district` VALUES ('India', 'West Bengal', 'Nadia', '576');
INSERT INTO `country_state_district` VALUES ('India', 'West Bengal', 'Dakshin Dinajpur', '577');
INSERT INTO `country_state_district` VALUES ('India', 'West Bengal', 'Purulia', '578');
INSERT INTO `country_state_district` VALUES ('India', 'West Bengal', 'Uttar Dinajpur', '579');
INSERT INTO `country_state_district` VALUES ('India', 'West Bengal', 'Siliguri', '580');
INSERT INTO `country_state_district` VALUES ('India', 'Delhi', 'New Delhi', '581');
INSERT INTO `country_state_district` VALUES ('India', 'Delhi', 'Central', '582');
INSERT INTO `country_state_district` VALUES ('India', 'Delhi', 'North', '583');
INSERT INTO `country_state_district` VALUES ('India', 'Delhi', 'North West', '584');
INSERT INTO `country_state_district` VALUES ('India', 'Delhi', 'West', '585');
INSERT INTO `country_state_district` VALUES ('India', 'Delhi', 'South West', '586');
INSERT INTO `country_state_district` VALUES ('India', 'Delhi', 'North', '587');
INSERT INTO `country_state_district` VALUES ('India', 'Delhi', 'North East', '588');
INSERT INTO `country_state_district` VALUES ('India', 'Pondicherry', 'Karikal', '589');
INSERT INTO `country_state_district` VALUES ('India', 'Pondicherry', 'Mahe', '590');
INSERT INTO `country_state_district` VALUES ('India', 'Pondicherry', 'Yaman', '591');
INSERT INTO `country_state_district` VALUES ('India', 'Pondicherry', 'Pondicherry', '592');
INSERT INTO `country_state_district` VALUES ('India', 'Lakshdweep', 'Lakshdweep', '593');
INSERT INTO `country_state_district` VALUES ('India', 'Daman & Diu', 'Dama', '594');
INSERT INTO `country_state_district` VALUES ('India', 'Daman & Diu', 'Diu', '595');
INSERT INTO `country_state_district` VALUES ('India', 'Dadra & Nagar', 'Dadra', '596');
INSERT INTO `country_state_district` VALUES ('India', 'Chandigarh', 'Chandigarh', '597');
INSERT INTO `country_state_district` VALUES ('India', 'Andaman & Nicobar', 'Andaman ', '598');
INSERT INTO `country_state_district` VALUES ('India', 'Andaman & Nicobar', 'Nicobar', '599');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Jaipur', '600');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Barmer', '601');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Dungarpur', '602');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Jodhpur', '603');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Kota', '604');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Udaipur', '605');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Bikaner', '606');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Dausa', '607');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Bundi', '608');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Sikar', '609');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Tonk', '610');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Jaisalmer', '611');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Nagaur', '612');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Rajsamand', '613');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Banswara', '614');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Bhilwara', '615');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Ajmer', '616');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Alwar', '617');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Bharatpur', '618');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Chittorgarh', '619');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Churu', '620');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Dholpur', '621');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Ganganagar', '622');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Jalor', '623');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Jhalawar', '624');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Jhunjhunu', '625');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Pali', '626');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Sawai Madhopur', '627');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Sirohi', '628');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Baran', '629');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Hanumangarh', '630');
INSERT INTO `country_state_district` VALUES ('India', 'Rajasthan', 'Karauli', '631');
INSERT INTO `country_state_district` VALUES ('Pakistan', null, null, '632');
INSERT INTO `country_state_district` VALUES ('Pakistan', 'Islamabad', null, '633');
INSERT INTO `country_state_district` VALUES ('Pakistan', 'Islamabad', 'Pakistan', '634');
INSERT INTO `country_state_district` VALUES ('africa', null, null, '636');
INSERT INTO `country_state_district` VALUES ('india', null, null, '639');
INSERT INTO `country_state_district` VALUES ('india', null, null, '640');
INSERT INTO `country_state_district` VALUES ('india', null, null, '641');
INSERT INTO `country_state_district` VALUES ('africa', null, null, '642');
INSERT INTO `country_state_district` VALUES ('india', null, null, '643');
INSERT INTO `country_state_district` VALUES ('India', 'kerala', null, '647');
INSERT INTO `country_state_district` VALUES ('India', 'kerala', 'kottayam', '648');
INSERT INTO `country_state_district` VALUES ('India', 'kerala', 'kannur', '649');
INSERT INTO `country_state_district` VALUES ('India', 'kerala', 'Alappuzha', '650');
INSERT INTO `country_state_district` VALUES ('India', 'kerala', 'Ernakulam', '651');
INSERT INTO `country_state_district` VALUES ('India', 'kerala', 'Idukki', '652');
INSERT INTO `country_state_district` VALUES ('India', 'kerala', 'Kasaragod', '653');
INSERT INTO `country_state_district` VALUES ('India', 'kerala', 'Kollam', '654');
INSERT INTO `country_state_district` VALUES ('India', 'kerala', 'Kozhikode', '655');
INSERT INTO `country_state_district` VALUES ('India', 'kerala', 'Malappuram', '656');
INSERT INTO `country_state_district` VALUES ('India', 'kerala', 'Palakkad', '657');
INSERT INTO `country_state_district` VALUES ('India', 'kerala', 'Pathanamthitta', '658');
INSERT INTO `country_state_district` VALUES ('India', 'kerala', 'Thiruvananthapuram', '659');
INSERT INTO `country_state_district` VALUES ('India', 'kerala', 'Thrissur', '660');
INSERT INTO `country_state_district` VALUES ('India', 'kerala', 'Wayanad', '661');

-- ----------------------------
-- Table structure for enquiry_assesment_children_tbl
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_assesment_children_tbl`;
CREATE TABLE `enquiry_assesment_children_tbl` (
  `children_id` varchar(255) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `enquiry_id` varchar(255) DEFAULT NULL,
  `country_relative` varchar(255) DEFAULT NULL,
  `relation` varchar(255) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`children_id`),
  KEY `child_enq` (`enquiry_id`) USING BTREE,
  CONSTRAINT `enquiry_assesment_children_tbl_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_assesment_children_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for enquiry_assesment_languagetest_tbl
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_assesment_languagetest_tbl`;
CREATE TABLE `enquiry_assesment_languagetest_tbl` (
  `spouse_language_test_id` varchar(100) NOT NULL,
  `enquiry_id` varchar(100) DEFAULT NULL,
  `test` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `reading` varchar(100) DEFAULT NULL,
  `writing` varchar(100) DEFAULT NULL,
  `speaking` varchar(100) DEFAULT NULL,
  `listening` varchar(100) DEFAULT NULL,
  `overall` varchar(100) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`spouse_language_test_id`),
  KEY `languagetest` (`enquiry_id`) USING BTREE,
  CONSTRAINT `enquiry_assesment_languagetest_tbl_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_assesment_languagetest_tbl
-- ----------------------------
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kan051ea2e9ad94', 'kan_14132433f76b', 'Gate', null, null, null, null, null, '2', 'sumeshzoft', '2016-06-14 17:32:12', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kan07deedb73596', 'kan_0e2069aaa132', 'Gate', null, null, null, null, null, '2', 'sumeshzoft', '2016-06-14 17:31:14', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kan0f98811405ab', 'kan_e73e0db3ce2c', 'Gate', null, null, null, null, null, '1.5', 'sumeshzoft', '2016-06-14 17:35:47', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kan16be3f4fba02', 'kan_64d0ee4ec807', 'MOH', null, null, null, null, null, '1.5', 'jitha', '2016-06-14 17:46:21', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kan17c971c28c9f', 'kan_d2d606026a15', 'online Test', null, null, null, null, null, '1', 'jitha', '2016-06-14 17:47:17', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kan1e619572cf9a', 'kol_a7433dc10ad7', 'NA', null, null, null, null, null, '2.5', 'jitha', '2016-06-14 17:23:24', 'jitha', '2016-06-14 17:26:43', 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kan218004b2d7fb', 'kan_0814cfa90746', 'MOH', null, null, null, null, null, '1', 'sumeshzoft', '2016-06-14 17:34:26', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kan5bb54c416ae9', 'kan_67aee97bdabe', 'NA', null, null, null, null, null, '2.5', 'sumeshzoft', '2016-06-13 16:16:35', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kan5fb497babaa4', 'kan_a2c0869fdcde', 'NA', null, null, null, null, null, '1.5', 'jitha', '2016-06-14 17:02:30', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kan699313cdaa22', 'kan_f6a01e7d4e70', 'NA', null, null, null, null, null, '1', 'jitha', '2016-06-14 17:07:30', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kan720e5b3a7ded', 'kan_d8377f8e03ff', 'NA', null, null, null, null, null, '1', 'sumeshzoft', '2016-06-14 17:33:06', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kan75360f1a6c41', 'kan_4f9cddfd4e5c', 'NA', null, null, null, null, null, '2', 'jitha', '2016-06-14 09:58:17', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kan7575ca68ddea', 'kol_d0cc31695858', 'Gate', null, null, null, null, null, '1', 'jitha', '2016-06-14 17:24:13', 'jitha', '2016-06-14 17:26:36', 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kan76e3c39b82f5', 'kol_4d5f5f43ac8b', 'Gate', null, null, null, null, null, '2.5', 'jitha', '2016-06-14 17:50:28', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kan7f0279a474a0', 'kan_1efb63d594e0', 'Gate', null, null, null, null, null, '2', 'sumeshzoft', '2016-06-14 17:13:16', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kan831afc51ef99', 'kan_d14f72fc2e86', 'NA', null, null, null, null, null, '3', 'jitha', '2016-06-14 17:03:04', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kan864e80c5c8da', 'kan_9f3f69f63db5', 'Gate', null, null, null, null, null, '1.5', 'sumeshzoft', '2016-06-14 17:11:41', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kan8f890b5d9925', 'kan_0999ea3e41ec', 'Gate', null, null, null, null, null, '1', 'jitha', '2016-06-14 17:01:35', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kan91872426f1e9', 'kan_cd5e74d67cd1', 'MOH', null, null, null, null, null, '2', 'sumeshzoft', '2016-06-14 17:43:31', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kan9b2f07cd613d', 'kan_a8087d458991', 'NA', null, null, null, null, null, '2.5', 'sumeshzoft', '2016-06-13 15:36:07', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kana08d151c70c2', 'kan_3811aca90659', 'Gate', null, null, null, null, null, '1.5', 'jitha', '2016-06-14 17:25:19', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kana2307f818c16', 'koc_78df79df877c', 'NA', null, null, null, null, null, '3', 'jitha', '2016-06-14 09:58:29', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kana6c8fd64e8f2', 'kan_fd9a9a06b595', 'NA', null, null, null, null, null, '2.5', 'jitha', '2016-06-14 09:58:23', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kanade10e800776', 'kan_faa02d7d2026', 'Gate', null, null, null, null, null, '1.5', 'sumeshzoft', '2016-06-13 15:36:13', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kanb3e0a9a942e8', 'kan_dec1a454c24a', 'MOH', null, null, null, null, null, '2', 'sumeshzoft', '2016-06-14 17:13:39', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kanb52c3e675ce0', 'kan_76371a02a618', 'MOH', null, null, null, null, null, '1.5', 'jitha', '2016-06-14 17:02:05', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kanf0f1dbf160c1', 'kan_7d4957ffac9c', 'Gate', null, null, null, null, null, '3', 'jitha', '2016-06-14 17:25:56', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kanf1910ebad5c8', 'kan_805877d725d7', 'online Test', null, null, null, null, null, '2', 'sumeshzoft', '2016-06-14 17:35:06', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kanf4262bc5b7e7', 'kan_fadfe7897cb7', 'MOH', null, null, null, null, null, '3', 'jitha', '2016-06-14 17:26:30', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kanf65891e40be2', 'kol_f9cc84fdcc92', 'NA', null, null, null, null, null, '3.5', 'sumeshzoft', '2016-06-14 17:12:21', 'sumeshzoft', '2016-06-14 17:14:09', 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Koc451babc0b5f3', 'koc_cdc8ad6f110f', 'NA', null, null, null, null, null, '2.5', 'sandeep', '2016-06-14 12:48:50', 'admin', '2016-06-15 10:09:30', 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Koc475b5f987b96', 'koc_a7041eface62', 'NA', null, null, null, null, null, '2.5', 's', '2016-06-14 11:52:48', 'admin', '2016-06-15 10:06:32', 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Koc53fb5de4457f', 'koc_b896ec0b0699', 'Gate', null, null, null, null, null, '1', 'sandeep', '2016-06-14 12:33:55', 'admin', '2016-06-15 10:06:59', 'n');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Koc6bf15ea80daa', 'koc_b896ec0b0699', 'MOH', null, null, null, null, null, '1.5', 'sandeep', '2016-06-14 12:34:05', 'admin', '2016-06-15 10:06:59', 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kol08a6533001e4', 'kol_902ef03d9e76', 'MOH', null, null, null, null, null, '2', 'admin', '2016-06-15 10:14:37', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kol0cf044c4409e', 'koc_dfca21935856', 'MOH', null, null, null, null, null, '2.5', 'admin', '2016-06-15 10:11:13', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kol15319df94eb0', 'kol_87b16f6c6dae', 'Gate', null, null, null, null, null, '1.5', 'a', '2016-06-14 12:36:37', 'a', '2016-06-14 12:36:44', 'n');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kol172ebea6fa17', 'kan_f89c4cc5fcd5', 'Gate', null, null, null, null, null, '1.5', 'admin', '2016-06-15 10:03:12', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kol189e39a6bb3b', 'kol_66ebfd1a5540', 'MOH', null, null, null, null, null, '1.5', 'a', '2016-06-14 11:58:49', 'admin', '2016-06-15 10:13:56', 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kol2f2c695c96e0', 'kol_e77e9f96475b', 'Gate', null, null, null, null, null, '1.5', 'admin', '2016-06-15 10:19:49', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kol345954075770', 'kol_d435309754f4', 'MOH', null, null, null, null, null, '1.5', 'admin', '2016-06-15 10:17:42', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kol38777d613a38', 'koc_228d6705c090', 'online Test', null, null, null, null, null, '4', 'admin', '2016-06-15 10:29:09', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kol3ea56a25fa27', 'kol_87b16f6c6dae', 'online Test', null, null, null, null, null, '1.5', 'a', '2016-06-14 12:36:44', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kol42403316a8a2', 'kol_af76ca381901', 'NA', null, null, null, null, null, '3', 'admin', '2016-06-15 10:16:21', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kol472c3549752d', 'kan_60284b9c5c17', 'MOH', null, null, null, null, null, '1.5', 'admin', '2016-06-15 10:03:37', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kol4c2f47fdbc13', 'kol_7c38fd4e7311', 'NA', null, null, null, null, null, '1.5', 'a', '2016-06-14 12:11:36', 'admin', '2016-06-15 10:14:01', 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kol570e4c3b453a', 'kol_fe7fcbb38341', 'MOH', null, null, null, null, null, '1.5', 'a', '2016-06-14 12:58:39', 'a', '2016-06-14 12:58:43', 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kol6a04d104e756', 'koc_e454886ba276', 'Gate', null, null, null, null, null, '2.5', 'admin', '2016-06-15 10:35:38', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kol6af4969dc029', 'koc_00c9d19f0ec4', 'Gate', null, null, null, null, null, '3', 'admin', '2016-06-15 10:28:10', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kol869b6d082021', 'koc_d82424c77dc1', 'NA', null, null, null, null, null, '4.5', 'admin', '2016-06-15 10:30:26', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kolb324dbcf4bbe', 'koc_b95c59f14d15', 'NA', null, null, null, null, null, '3.5', 'admin', '2016-06-15 10:07:48', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kolcaf723fecea6', 'kol_87b16f6c6dae', 'MOH', null, null, null, null, null, '1.5', 'a', '2016-06-14 12:36:26', 'a', '2016-06-14 12:36:44', 'n');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kold12d0bbc8d53', 'koc_4b1623ba84a3', 'NA', null, null, null, null, null, '3', 'admin', '2016-06-15 10:04:14', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Koldcee64b3d4f8', 'kol_178922ed2ba8', 'MOH', null, null, null, null, null, '1.5', 'a', '2016-06-13 14:49:46', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Koldde85a9dd72b', 'koc_cc1b4827b853', 'online Test', null, null, null, null, null, '2.5', 'admin', '2016-06-15 10:08:42', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kolf0f407a44824', 'kol_399d5eb36484', 'Gate', null, null, null, null, null, '2.5', 'admin', '2016-06-13 18:12:46', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kolf2476c5003cd', 'kol_b9749879089d', 'NA', null, null, null, null, null, '1', 'admin', '2016-06-13 18:12:40', null, null, 'y');
INSERT INTO `enquiry_assesment_languagetest_tbl` VALUES ('Kolf7b0fdb943d2', 'kol_eb5db4536e5b', 'online Test', null, null, null, null, null, '2', 'admin', '2016-06-15 10:20:42', null, null, 'y');

-- ----------------------------
-- Table structure for enquiry_assesment_othertest_details_tbl
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_assesment_othertest_details_tbl`;
CREATE TABLE `enquiry_assesment_othertest_details_tbl` (
  `id` varchar(255) NOT NULL,
  `enquiry_id` varchar(255) DEFAULT NULL,
  `test_name` varchar(50) DEFAULT NULL,
  `test_score` double DEFAULT NULL,
  `test_status` varchar(255) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `othertest_enq` (`enquiry_id`) USING BTREE,
  CONSTRAINT `enquiry_assesment_othertest_details_tbl_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_assesment_othertest_details_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for enquiry_assesment_spouse_exp_tbl
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_assesment_spouse_exp_tbl`;
CREATE TABLE `enquiry_assesment_spouse_exp_tbl` (
  `spouse_exp_id` varchar(100) NOT NULL,
  `enquiry_id` varchar(100) DEFAULT NULL,
  `profession` varchar(100) DEFAULT NULL,
  `duration` varchar(100) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`spouse_exp_id`),
  KEY `enquiry_id` (`enquiry_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_assesment_spouse_exp_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for enquiry_assesment_spouse_languagetest_tbl
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_assesment_spouse_languagetest_tbl`;
CREATE TABLE `enquiry_assesment_spouse_languagetest_tbl` (
  `spouse_language_test_id` varchar(100) NOT NULL,
  `enquiry_id` varchar(100) DEFAULT NULL,
  `test` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `reading` varchar(100) DEFAULT NULL,
  `writing` varchar(100) DEFAULT NULL,
  `speaking` varchar(100) DEFAULT NULL,
  `listening` varchar(100) DEFAULT NULL,
  `overall` varchar(100) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`spouse_language_test_id`),
  KEY `enquiry_id` (`enquiry_id`) USING BTREE,
  CONSTRAINT `enquiry_assesment_spouse_languagetest_tbl_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_assesment_spouse_languagetest_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for enquiry_assesment_spouse_qualification
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_assesment_spouse_qualification`;
CREATE TABLE `enquiry_assesment_spouse_qualification` (
  `spouse_qual_id` varchar(100) NOT NULL,
  `enquiry_id` varchar(100) DEFAULT NULL,
  `level` varchar(100) DEFAULT NULL,
  `field` varchar(100) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`spouse_qual_id`),
  KEY `idd` (`enquiry_id`) USING BTREE,
  CONSTRAINT `enquiry_assesment_spouse_qualification_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_assesment_spouse_qualification
-- ----------------------------

-- ----------------------------
-- Table structure for enquiry_assesment_status_tbl
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_assesment_status_tbl`;
CREATE TABLE `enquiry_assesment_status_tbl` (
  `assement_status_id` varchar(255) NOT NULL,
  `enquiry_id` varchar(255) DEFAULT NULL,
  `enquiry_status` int(30) DEFAULT NULL,
  `remarks` text,
  `priority` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  `branch` varchar(30) DEFAULT NULL,
  `counsellor` varchar(30) DEFAULT NULL,
  `reference` varchar(30) DEFAULT NULL,
  `dummy` varchar(255) DEFAULT NULL,
  `dummy2` varchar(255) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`assement_status_id`),
  UNIQUE KEY `enquiryI` (`enquiry_id`) USING BTREE,
  CONSTRAINT `enquiry_assesment_status_tbl_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_assesment_status_tbl
-- ----------------------------
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_0110a5eb9ac7', 'kan_d14f72fc2e86', '2', '', '0', null, 'null', 'Kannur', 'jitha', 'null', null, null, 'user3', '2016-06-14 15:16:38', '2016-06-15 17:58:36', 'user3');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_015fdefaedfd', 'koc_b95c59f14d15', '3', '', '0', null, 'null', 'Kochi', 'Not Assigned', 'null', null, null, 'sandy', '2016-06-13 14:46:05', '2016-06-17 13:47:45', 'sandeep');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_03517a01cada', 'kan_e73e0db3ce2c', '2', '', '0', null, 'null', 'Kannur', 'Not Assigned', 'null', null, null, 'user3', '2016-06-13 15:40:05', '2016-06-15 17:45:13', 'user3');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_0940aa087ef6', 'kan_76371a02a618', '6', 'okey', '0', '2016-06-23', 'null', 'Kannur', 'Not Assigned', 'null', null, null, 'user3', '2016-06-14 15:21:10', '2016-06-15 11:35:14', 'sumeshzoft');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_0df42def0919', 'kan_805877d725d7', '2', '', '0', null, 'null', 'Kannur', 'Not Assigned', 'null', null, null, 'user3', '2016-06-13 15:13:45', '2016-06-15 17:46:32', 'user3');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_1376513af974', 'koc_619929d9bc25', '7', 'fgh', '0', '2016-07-01', 'null', 'Kochi', 'Not Assigned', 'null', null, null, 'sandy', '2016-06-17 13:49:09', '2016-06-17 14:26:20', 'sandeep');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_15b451b163eb', 'kan_cd5e74d67cd1', '2', '', '0', null, 'null', 'Kannur', 'jitha', 'null', null, null, 'user3', '2016-06-14 09:46:33', '2016-06-16 09:28:50', 'jitha');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_18ceb7127e12', 'koc_e454886ba276', '1', 'call later', '0', null, 'null', 'Kochi', 'Not Assigned', 'null', null, null, 'sandy', '2016-06-13 15:10:54', '2016-06-13 19:01:43', 'sandeep');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_21a40be9fb0a', 'kol_d33b0ffcb552', '1', 'not picking', '0', null, 'null', 'Kochi', 'Not Assigned', 'null', null, null, 'b', '2016-06-13 15:36:36', '2016-06-14 11:06:44', 'sandeep');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_27467947168f', 'kol_d435309754f4', '4', 'gfhg', '0', null, 'null', 'Kollam', 'sanoop', 'null', null, null, 'b', '2016-06-13 10:41:16', '2016-06-20 10:25:57', 'b');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_29994d390646', 'kol_e77e9f96475b', '4', '', '0', null, 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-14 10:07:21', '2016-06-20 10:25:40', 'b');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_32ddaf27b0dd', 'kol_0c1764954c6d', '7', 't65465', '0', '2016-06-13', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-13 14:18:42', '2016-06-16 17:21:20', 'a');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_36851be80256', 'koc_cdc8ad6f110f', '7', 'not picking', '0', '2016-06-13', 'null', 'Kochi', 'Not Assigned', 'null', null, null, 'sandy', '2016-06-13 15:08:25', '2016-06-14 12:47:32', 'sandeep');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_39a166dd84d6', 'koc_b896ec0b0699', '3', '', '0', null, 'null', 'Kochi', 'Not Assigned', 'null', null, null, 'sandy', '2016-06-14 10:47:52', '2016-06-17 14:45:32', 'sandeep');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_39f734123c4b', 'koc_4b1623ba84a3', '4', '', '0', null, 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'sandy', '2016-06-13 15:10:06', '2016-06-17 15:00:37', 'sheela');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_4308fc7c3346', 'kan_fd9a9a06b595', '4', '', '0', null, 'null', 'Kannur', 'Not Assigned', 'null', null, null, 'user3', '2016-06-13 15:41:03', '2016-06-15 11:35:21', 'sumeshzoft');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_439723112439', 'koc_dfca21935856', '4', '', '0', null, 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'sandy', '2016-06-14 10:57:57', '2016-06-17 14:46:39', 'sandeep');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_45fd486be342', 'kan_9f3f69f63db5', '2', '', '0', null, 'null', 'Kannur', 'sumeshzoft', 'null', null, null, 'user3', '2016-06-14 15:19:47', '2016-06-15 17:37:01', 'user3');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_46e6c82f70ae', 'kol_05dddca31839', '7', 'tyeryert', '0', '2016-06-13', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-13 14:27:48', '2016-06-17 12:01:21', 'a');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_480ab6b8c39a', 'kan_faa02d7d2026', '2', 'aa', '0', null, 'null', 'Kannur', 'Not Assigned', 'null', null, null, 'user3', '2016-06-13 15:01:30', '2016-06-15 17:58:52', 'user3');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_4d7cfc81ab8e', 'kol_17cf27990d9e', '2', 'editedddd', '1', null, 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-20 14:20:29', '2016-06-21 15:21:40', 'b');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_53158ae6735c', 'kol_fe7fcbb38341', '6', 'rtret', '0', '2016-06-17', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-13 14:29:11', '2016-06-17 13:43:10', 'a');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_578d7c688cb3', 'kol_60c98adacc3e', '6', 'fegergerge', '0', '2016-06-17', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-17 14:23:23', '2016-06-17 14:24:58', 'sheela');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_603bdf5d3fb8', 'kol_7ff348fe6b96', '5', 'okey', '0', '2016-06-30', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-20 13:10:14', '2016-06-20 15:22:24', 'a');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_640465e9451b', 'kol_b29f45a47919', '5', 'grtetrtrt', '0', '2016-06-17', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'keerthykp', '2016-06-17 14:42:17', '2016-06-17 14:44:11', 'sheela');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_682c20ad1e9d', 'kol_66ebfd1a5540', '7', 'try', '0', '2016-06-13', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-13 10:40:51', '2016-06-17 12:43:12', 'a');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_6b85f8337930', 'kol_6fd78a762953', '4', '', '0', null, 'null', 'Kollam', 'a', 'null', null, null, 'b', '2016-06-17 12:56:12', '2016-06-20 14:38:14', 'a');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_6ba1e47dc526', 'kan_60284b9c5c17', '5', 'kghjkfghj', '1', '2016-06-17', 'null', 'Kochi', 'Not Assigned', 'null', null, null, 'user3', '2016-06-14 09:42:22', '2016-06-17 14:31:55', 'sandeep');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_7a6ec0ef09a7', 'kol_1b03a7918ff0', '4', 'ghgfjfgh', '0', null, 'null', 'Kollam', 'a', 'null', null, null, 'keerthykp', '2016-06-17 14:20:51', '2016-06-17 15:42:00', 'sheela');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_9093324499bf', 'koc_78df79df877c', '2', '', '0', null, 'null', 'Kannur', 'Not Assigned', 'null', null, null, 'sandy', '2016-06-13 15:11:34', '2016-06-15 11:35:22', 'sumeshzoft');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_a59b52c6846e', 'kan_0814cfa90746', '2', '', '0', null, 'null', 'Kannur', 'sumeshzoft', 'null', null, null, 'user3', '2016-06-13 15:05:38', '2016-06-15 17:46:16', 'user3');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_a6beed24cdd5', 'kol_7c38fd4e7311', '2', '', '0', null, 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-13 17:42:04', '2016-06-17 12:52:06', 'sheela');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_a99c74108a1c', 'kan_0e2069aaa132', '2', '', '0', null, 'null', 'Kannur', 'Not Assigned', 'null', null, null, 'user3', '2016-06-14 09:47:21', '2016-06-15 17:45:25', 'user3');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_ae46771ff942', 'kan_d8377f8e03ff', '4', '', '0', null, 'null', 'Kannur', 'Not Assigned', 'null', null, null, 'user3', '2016-06-14 11:36:07', '2016-06-15 17:12:45', 'jitha');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_b6e7fefcb7e3', 'koc_00c9d19f0ec4', '1', 'not picking', '0', null, 'null', 'Kochi', 'Not Assigned', 'null', null, null, 'sandy', '2016-06-13 15:07:01', '2016-06-14 11:05:45', 'sandeep');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_bf8bbdb8ed85', 'kol_b63d0fcd8aef', '4', '', '0', null, 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'keerthykp', '2016-06-17 14:10:12', '2016-06-20 10:26:04', 'b');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_c13276da4557', 'kol_2b3eb6eaddd3', '9', 'gvf', '1', '2016-06-29', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-20 14:27:15', '2016-06-20 15:23:09', 'a');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_c272efd2d020', 'kan_4f9cddfd4e5c', '2', '', '0', null, 'null', 'Kannur', 'Not Assigned', 'null', null, null, 'user3', '2016-06-13 14:54:15', '2016-06-16 09:25:55', 'user3');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_c8b1d0449cee', 'kan_f89c4cc5fcd5', '2', '', '0', null, 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'user3', '2016-06-13 15:07:27', '2016-06-17 15:32:40', 'sheela');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_d5391b245d00', 'kol_178922ed2ba8', '1', 'hjk', '0', null, 'null', 'Kollam', 'sanoop', 'null', null, null, 'b', '2016-06-13 10:41:44', '2016-06-17 13:43:30', 'a');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_f433891475bb', 'kan_f6a01e7d4e70', '2', '', '0', null, 'null', 'Kannur', 'jitha', 'null', null, null, 'user3', '2016-06-13 14:57:29', '2016-06-15 17:44:55', 'user3');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_f74b4ab9e70a', 'kol_a8717f31260b', '6', 'ret', '0', '2016-07-01', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-14 14:17:19', '2016-06-17 12:26:15', 'a');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_fbf9366f112e', 'kol_87b16f6c6dae', '9', 'okey i willl', '0', '2016-06-13', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-13 10:42:20', '2016-06-17 12:52:06', 'sheela');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_fd958a53d9dd', 'kol_66a39843f66c', '5', 'cvbg', '0', '2016-06-30', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-20 13:08:19', '2016-06-20 15:23:00', 'a');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('as_fe424b7e1316', 'kol_dcca7aeb8073', '6', 'hgj', '0', '2016-06-30', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-20 14:14:16', '2016-06-20 14:16:32', 'b');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_0331a610deb9', 'kol_47a290eaa29d', '1', '', '0', '2016-06-20', 'null', 'Kollam', 'sheela', 'null', null, null, 'b', '2016-06-20 12:56:23', '2016-06-20 12:56:23', 'b');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_0efe20e820ad', 'kol_43c73f9a76f6', '1', '', '0', '2016-06-14', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-14 09:54:32', '2016-06-17 12:02:33', 'a');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_0f1647e02c5b', 'kan_3811aca90659', '1', '', '0', '2016-06-14', 'null', 'Kannur', 'Not Assigned', 'null', null, null, 'user3', '2016-06-14 09:48:04', '2016-06-14 18:17:10', 'jitha');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_132662ac1cb7', 'kol_c913b2e4fa1d', '1', '', '0', '2016-06-17', 'null', 'Kollam', 'sheela', 'null', null, null, 'keerthykp', '2016-06-17 14:35:47', '2016-06-17 14:35:48', 'keerthykp');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_13abe6a4cabd', 'kol_008a6e0d3c74', '1', '', '0', '2016-06-17', 'null', 'Kollam', 'a', 'null', null, null, 'b', '2016-06-17 12:55:23', '2016-06-17 12:55:23', 'b');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_1867b15b9fa9', 'koc_228d6705c090', '1', '', '0', '2016-06-14', 'null', 'Kochi', 'Not Assigned', 'null', null, null, 'sandy', '2016-06-14 10:00:00', '2016-06-17 14:12:57', 'sandeep');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_1a6687353dae', 'kol_b89f7b63f70f', '1', '', '0', '2016-06-14', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-14 13:56:36', '2016-06-17 12:01:37', 'a');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_1bf259edb43d', 'kan_dec1a454c24a', '1', '', '0', '2016-06-14', 'null', 'Kannur', 'sumeshzoft', 'null', null, null, 'user14', '2016-06-14 11:07:05', '2016-06-14 11:07:05', 'user14');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_1e9671cbb0da', 'kol_f76f27840190', '1', '', '0', '2016-06-14', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-14 14:15:38', '2016-06-17 11:59:50', 'a');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_2f16bb6ab1a8', 'kol_902ef03d9e76', '1', '', '0', '2016-06-14', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-14 13:57:57', '2016-06-17 15:23:08', 'a');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_3af19af35209', 'kol_b672c8fe2b6b', '1', '', '0', '2016-06-20', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-20 13:11:27', '2016-06-20 15:22:18', 'a');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_42c6141ea47e', 'koc_a7041eface62', '1', '', '0', '2016-06-14', 'null', 'Kochi', 'Not Assigned', 'null', null, null, 'sandy', '2016-06-14 10:30:48', '2016-06-17 14:46:00', 'sandeep');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_4877dcfacbdd', 'kan_d2d606026a15', '1', '', '0', '2016-06-13', 'null', 'Kannur', 'Not Assigned', 'null', null, null, 'user3', '2016-06-13 15:02:26', '2016-06-15 17:12:42', 'jitha');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_4e7eb4c9889d', 'koc_1dd89e2611ea', '1', '', '0', '2016-06-13', 'null', 'Kochi', 'Not Assigned', 'null', null, null, 'sandy', '2016-06-13 15:07:44', '2016-06-13 18:45:16', 'sandeep');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_4f619f70580c', 'kol_cddfe245a085', '1', '', '0', '2016-06-15', 'null', 'Kochi', 'sandeepskrishnan', 'null', null, null, 'b', '2016-06-15 12:10:57', '2016-06-15 12:10:57', 'b');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_5dfaafa0b4b6', 'kan_1efb63d594e0', '1', '', '0', '2016-06-14', 'null', 'Kannur', 'sumeshzoft', 'null', null, null, 'user3', '2016-06-14 10:39:29', '2016-06-14 10:39:29', 'user3');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_5fb6703ff531', 'kol_af76ca381901', '1', '', '0', '2016-06-13', 'null', 'Kochi', 'Not Assigned', 'null', null, null, 'b', '2016-06-13 19:00:02', '2016-06-14 12:47:34', 'sandeep');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_63fa67f86322', 'kol_ebae93075120', '1', '', '0', '2016-06-14', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-14 13:55:18', '2016-06-20 15:22:39', 'a');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_6ad32bb51a54', 'kan_a8087d458991', '1', '', '0', '2016-06-13', 'null', 'Kannur', 'Not Assigned', 'null', null, null, 'user3', '2016-06-13 15:06:22', '2016-06-14 18:17:14', 'jitha');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_6b01a3eb2a02', 'kan_7d4957ffac9c', '1', '', '0', '2016-06-14', 'null', 'Kannur', 'Not Assigned', 'null', null, null, 'user3', '2016-06-14 10:34:22', '2016-06-14 18:17:11', 'jitha');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_6da68ee07ebe', 'koc_d82424c77dc1', '1', '', '0', '2016-06-14', 'null', 'Kochi', 'Not Assigned', 'null', null, null, 'sandy', '2016-06-14 10:58:55', '2016-06-14 11:05:39', 'sandeep');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_6facbe5caef3', 'kol_b9749879089d', '1', '', '0', '2016-06-13', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-13 14:26:54', '2016-06-16 17:21:09', 'a');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_746de6722aa5', 'kol_eb5db4536e5b', '1', '', '0', '2016-06-14', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-14 17:43:24', '2016-06-17 15:58:17', 'sheela');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_79b1582d1508', 'kol_428755f2c9f8', '1', '', '0', '2016-06-20', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-20 15:43:15', '2016-06-20 15:43:15', 'b');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_7cfb9e240bd6', 'kol_d7a4f085ca0f', '1', '', '0', '2016-06-17', 'null', 'Kollam', 'a', 'null', null, null, 'b', '2016-06-17 14:39:49', '2016-06-17 14:39:49', 'b');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_7fb332905563', 'kan_a2c0869fdcde', '1', '', '0', '2016-06-14', 'null', 'Kannur', 'Not Assigned', 'null', null, null, 'user14', '2016-06-14 16:30:03', '2016-06-14 17:57:09', 'jitha');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_8cfac8d89e29', 'kan_64d0ee4ec807', '1', '', '0', '2016-06-13', 'null', 'Kannur', 'Not Assigned', 'null', null, null, 'user3', '2016-06-13 15:04:17', '2016-06-15 17:12:44', 'jitha');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_965ce9c9b824', 'kol_4d5f5f43ac8b', '1', '', '0', '2016-06-13', 'null', 'Kannur', 'jitha', 'null', null, null, 'b', '2016-06-13 17:40:14', '2016-06-16 09:28:33', 'jitha');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_99b506a3261f', 'koc_8754df24a47b', '1', '', '0', '2016-06-14', 'null', 'Kochi', 'Not Assigned', 'null', null, null, 'sandy', '2016-06-14 10:44:52', '2016-06-17 14:18:40', 's');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_9cb4fc672d75', 'kol_a7433dc10ad7', '1', '', '0', '2016-06-13', 'null', 'Kannur', 'jitha', 'null', null, null, 'b', '2016-06-13 17:31:58', '2016-06-16 09:28:34', 'jitha');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_b684fcb717fb', 'koc_a71b02703901', '1', '', '0', '2016-06-17', 'null', 'Kochi', 'sandeepskrishnan', 'null', null, null, 'sandy', '2016-06-17 13:50:16', '2016-06-17 13:52:12', 's');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_bacb45d28c8f', 'kan_14132433f76b', '1', '', '0', '2016-06-14', 'null', 'Kannur', 'Not Assigned', 'null', null, null, 'user3', '2016-06-14 15:18:57', '2016-06-15 17:12:54', 'jitha');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_c9baaf7d5841', 'kan_0999ea3e41ec', '1', '', '0', '2016-06-14', 'null', 'Kannur', 'sumeshzoft', 'null', null, null, 'user14', '2016-06-14 11:10:18', '2016-06-15 16:24:01', 'user3');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_cee865e26eb5', 'kol_fa4ce2149bb4', '1', '', '0', '2016-06-14', 'null', 'Kollam', 'sanoop', 'null', null, null, 'b', '2016-06-14 17:49:34', '2016-06-14 17:49:34', 'b');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_d1541e09f7c0', 'kol_91e547ed7bb1', '1', '', '0', '2016-06-17', 'null', 'Kollam', 'a', 'null', null, null, 'b', '2016-06-17 14:38:03', '2016-06-17 14:38:03', 'b');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_d1e73de14f1f', 'kol_f9cc84fdcc92', '1', '', '0', '2016-06-14', 'null', 'Kannur', 'sumeshzoft', 'null', null, null, 'b', '2016-06-14 13:06:38', '2016-06-14 13:06:38', 'b');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_d7c6783ec254', 'kol_b379890ae41d', '1', '', '0', '2016-06-14', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-14 09:58:04', '2016-06-16 16:55:53', 'a');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_d80abe2414fe', 'kol_e6429f1a8bb0', '1', '', '0', '2016-06-17', 'null', 'Kollam', 'a', 'null', null, null, 'b', '2016-06-17 14:31:56', '2016-06-17 14:31:56', 'b');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_de680b3ab32c', 'kan_67aee97bdabe', '1', '', '1', '2016-06-13', 'null', 'Kannur', 'Not Assigned', 'null', null, null, 'user3', '2016-06-13 15:38:01', '2016-06-14 17:57:18', 'jitha');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_e1b67ced7a19', 'kol_49c7d8d21eba', '1', 'rutuytryrtyr', '0', '2016-06-14', 'null', 'Kochi', 'Not Assigned', 'null', null, null, 'b', '2016-06-14 12:34:11', '2016-06-17 14:18:11', 'sandeep');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_e684c317d08a', 'kol_63da30698bfa', '1', '', '0', '2016-06-14', 'null', 'Kollam', 'a', 'null', null, null, 'b', '2016-06-14 14:16:37', '2016-06-14 14:16:37', 'b');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_e76f74212d4b', 'kol_2bd08b71d7ea', '1', '', '0', '2016-06-14', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-14 12:38:12', '2016-06-16 17:21:35', 'a');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_ec7cf7e9be64', 'kol_d0cc31695858', '1', '', '0', '2016-06-14', 'null', 'Kannur', 'Not Assigned', 'null', null, null, 'b', '2016-06-14 13:57:15', '2016-06-15 17:12:51', 'jitha');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_efc57fa87295', 'kan_fadfe7897cb7', '1', '', '0', '2016-06-14', 'null', 'Kannur', 'Not Assigned', 'null', null, null, 'user3', '2016-06-14 09:49:21', '2016-06-14 18:17:12', 'jitha');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_efe90b5e3736', 'koc_cc1b4827b853', '1', '', '0', '2016-06-14', 'null', 'Kochi', 'Not Assigned', 'null', null, null, 'sandy', '2016-06-14 11:00:11', '2016-06-17 14:45:25', 'sandeep');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_f45ec593749a', 'kol_d14b4ae570dd', '1', '', '0', '2016-06-20', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-20 15:42:34', '2016-06-20 15:42:34', 'b');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_fb1421670530', 'kol_8cdccfd7fe58', '1', '', '0', '2016-06-13', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-13 14:25:09', '2016-06-16 16:54:26', 'a');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_fd8fa3c0e8f2', 'kol_399d5eb36484', '1', '', '0', '2016-06-13', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-13 14:26:05', '2016-06-17 12:00:15', 'a');
INSERT INTO `enquiry_assesment_status_tbl` VALUES ('staus_ff8502cbcd4f', 'kol_79f0550ce37c', '1', '', '0', '2016-06-13', 'null', 'Kollam', 'Not Assigned', 'null', null, null, 'b', '2016-06-13 14:10:12', '2016-06-17 12:43:58', 'a');

-- ----------------------------
-- Table structure for enquiry_assessment_admission_test_tbl
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_assessment_admission_test_tbl`;
CREATE TABLE `enquiry_assessment_admission_test_tbl` (
  `admission_test_id` varchar(255) NOT NULL,
  `test` varchar(255) DEFAULT NULL,
  `overall` varchar(255) DEFAULT NULL,
  `enquiry_id` varchar(255) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`admission_test_id`),
  KEY `admm_frr_key` (`enquiry_id`) USING BTREE,
  CONSTRAINT `enquiry_assessment_admission_test_tbl_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_assessment_admission_test_tbl
-- ----------------------------
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_00001e558a6d', 'GMAT', '3.5', 'kol_f9cc84fdcc92', 'sumeshzoft', '2016-06-14 17:14:34', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_0b9718db27b3', 'GMAT', '2', 'koc_b95c59f14d15', 'admin', '2016-06-15 10:07:56', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_0c06a6839c33', 'NA', '1.5', 'kan_dec1a454c24a', 'sumeshzoft', '2016-06-14 17:13:45', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_0fba885da51a', 'NA', '1.5', 'kol_af76ca381901', 'admin', '2016-06-15 10:16:30', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_1749d09a48c8', 'GMAT', '2', 'kan_64d0ee4ec807', 'jitha', '2016-06-14 17:46:29', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_1a782ce333c4', 'NA', '2.5', 'koc_4b1623ba84a3', 'admin', '2016-06-15 10:04:23', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_1f38c6a837d9', 'GMAT', '2.5', 'kan_d8377f8e03ff', 'sumeshzoft', '2016-06-14 17:33:14', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_20a622a34043', 'GMAT', '2.5', 'kol_eb5db4536e5b', 'admin', '2016-06-15 10:20:49', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_2266b54aa967', 'NA', '2.5', 'kan_67aee97bdabe', 'jitha', '2016-06-14 17:57:52', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_26bf0feadf9b', 'GMAT', '3', 'kan_805877d725d7', 'sumeshzoft', '2016-06-14 17:35:20', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_32290f18caaa', 'GMAT', '1.5', 'kan_a2c0869fdcde', 'jitha', '2016-06-14 17:02:35', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_3b28abacf2cf', 'NA', '4.5', 'kol_178922ed2ba8', 'a', '2016-06-14 14:08:13', '2016-06-14 14:08:45', 'a', 'n');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_41af325cfb5e', 'NA', '1.5', 'kan_4f9cddfd4e5c', 'sumeshzoft', '2016-06-14 17:39:17', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_45f96f654d46', 'NA', '2.5', 'kan_fd9a9a06b595', 'sumeshzoft', '2016-06-14 17:39:42', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_484da2c0c807', 'NA', '1.5', 'kan_d14f72fc2e86', 'jitha', '2016-06-14 17:02:58', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_4e471f53e25a', 'NA', '2', 'kan_a8087d458991', 'jitha', '2016-06-14 17:26:59', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_52b35bb434e3', 'NA', '1.5', 'koc_a7041eface62', 'admin', '2016-06-15 10:06:39', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_68065b8e32b2', 'GMAT', '1.5', 'kan_fadfe7897cb7', 'jitha', '2016-06-14 17:27:05', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_707967a61354', 'GMAT', '1.5', 'kan_1efb63d594e0', 'sumeshzoft', '2016-06-14 17:13:22', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_7539c30fbdb3', 'GMAT', '1.5', 'kan_e73e0db3ce2c', 'sumeshzoft', '2016-06-14 17:35:55', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_764f46a46b96', 'NA', '2.5', 'kan_f89c4cc5fcd5', 'admin', '2016-06-15 10:03:19', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_76ed7b9db118', 'NA', '2', 'kol_a7433dc10ad7', 'jitha', '2016-06-14 17:26:51', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_7f07998d002f', 'NA', '3.5', 'kan_0814cfa90746', 'sumeshzoft', '2016-06-14 17:34:33', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_801536d9410c', 'GMAT', '1.5', 'kol_66ebfd1a5540', 'a', '2016-06-14 16:09:32', '2016-06-14 17:12:12', 'a', 'n');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_8288d0fd9222', 'GMAT', '1', 'kol_fe7fcbb38341', 'a', '2016-06-14 14:15:45', '2016-06-14 14:15:48', 'a', 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_85b8bd911f69', 'GMAT', '1', 'kan_faa02d7d2026', 'jitha', '2016-06-14 17:58:09', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_86cdee51ee63', 'NA', '1.5', 'kan_0e2069aaa132', 'sumeshzoft', '2016-06-14 17:31:26', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_8e5f7fbd7c11', 'GMAT', '2', 'koc_78df79df877c', 'admin', '2016-06-15 10:05:01', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_907d4504afd5', 'GMAT', '1', 'koc_dfca21935856', 'admin', '2016-06-15 10:13:11', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_914d9dd1b22c', 'GMAT', '1.5', 'kol_ebae93075120', 'a', '2016-06-14 16:47:02', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_934667ff6ff1', 'NA', '2', 'kan_7d4957ffac9c', 'jitha', '2016-06-14 17:26:04', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_983e98d0da9a', 'NA', '2.5', 'koc_cdc8ad6f110f', 'admin', '2016-06-15 10:09:37', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_99d3a07a8a7f', 'GMAT', '1.5', 'kol_902ef03d9e76', 'admin', '2016-06-15 10:14:47', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_9e0ea58e1655', 'NA', '3.5', 'kan_14132433f76b', 'sumeshzoft', '2016-06-14 17:32:17', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_aaf5e67aca67', 'NA', '3.5', 'koc_cc1b4827b853', 'admin', '2016-06-15 10:08:50', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_b4f76f0f8694', 'GMAT', '2', 'kol_7c38fd4e7311', 'admin', '2016-06-15 10:14:07', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_b56efda76843', 'NA', '1.5', 'kan_d2d606026a15', 'jitha', '2016-06-14 17:47:21', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_b89916c4f4ec', 'GMAT', '1.5', 'kan_3811aca90659', 'jitha', '2016-06-14 17:25:25', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_bc5d4d04e704', 'GMAT', '1', 'kan_60284b9c5c17', 'admin', '2016-06-15 10:03:46', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_becf1b3375e1', 'GMAT', '1.5', 'kol_178922ed2ba8', 'a', '2016-06-14 14:08:45', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_cc1c02fbe43e', 'NA', '2.5', 'kol_d435309754f4', 'admin', '2016-06-15 10:17:53', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_df9426b64f6b', 'GMAT', '1', 'kol_87b16f6c6dae', 'a', '2016-06-14 14:06:03', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_e067f936efd3', 'GMAT', '1.5', 'kol_e77e9f96475b', 'admin', '2016-06-15 10:19:58', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_e233702ea518', 'GMAT', '1.5', 'kan_76371a02a618', 'jitha', '2016-06-14 17:02:09', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_e2dcb3a5c8e5', 'NA', '1.5', 'kan_0999ea3e41ec', 'jitha', '2016-06-14 17:01:40', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_e314049e2ba6', 'GMAT', '1.5', 'kol_d0cc31695858', 'jitha', '2016-06-14 17:24:18', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_e3db7178a97a', 'NA', '4.5', 'kol_66ebfd1a5540', 'a', '2016-06-14 17:12:05', '2016-06-14 17:12:13', 'a', 'n');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_e677b89d2278', 'GMAT', '4', 'koc_b896ec0b0699', 'admin', '2016-06-15 10:07:18', null, null, 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_f1b19ad06aba', 'GMAT', '1', 'kol_66ebfd1a5540', 'a', '2016-06-14 16:09:43', '2016-06-14 17:12:13', 'a', 'y');
INSERT INTO `enquiry_assessment_admission_test_tbl` VALUES ('ad_fd83bd205b3b', 'NA', '1.5', 'kol_ebae93075120', 'a', '2016-06-14 16:46:54', '2016-06-14 16:47:02', 'a', 'n');

-- ----------------------------
-- Table structure for enquiry_assessment_langskills_tbl
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_assessment_langskills_tbl`;
CREATE TABLE `enquiry_assessment_langskills_tbl` (
  `lang_skill_id` varchar(255) NOT NULL,
  `language` varchar(255) DEFAULT NULL,
  `proficiency` varchar(255) DEFAULT NULL,
  `enquiry_id` varchar(255) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`lang_skill_id`),
  KEY `enquiry_assessment_langskills_tbl_ibfk_1` (`enquiry_id`) USING BTREE,
  CONSTRAINT `enquiry_assessment_langskills_tbl_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_assessment_langskills_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for enquiry_assessment_migrate_tbl
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_assessment_migrate_tbl`;
CREATE TABLE `enquiry_assessment_migrate_tbl` (
  `migrate_id` varchar(255) NOT NULL,
  `enquiry_id` varchar(255) NOT NULL DEFAULT '0',
  `migrate` varchar(50) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `profession` varchar(50) DEFAULT NULL,
  `choice` varchar(50) DEFAULT NULL,
  `spouse_accompany` varchar(50) DEFAULT NULL,
  `children_accompany` varchar(50) DEFAULT NULL,
  `special_comment` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `application_type` varchar(100) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`migrate_id`),
  KEY `enq_work_forign` (`enquiry_id`) USING BTREE,
  CONSTRAINT `enquiry_assessment_migrate_tbl_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_assessment_migrate_tbl
-- ----------------------------
INSERT INTO `enquiry_assessment_migrate_tbl` VALUES ('Kan5150a9bccd95', 'kan_e73e0db3ce2c', 'Migrate', 'temporary', 'Architect', '1', null, null, null, 'Australia', null, 'Melbourne', 'user3', '2016-06-13 15:40:05', '2016-06-14 17:36:35', 'sumeshzoft', 'y');
INSERT INTO `enquiry_assessment_migrate_tbl` VALUES ('Kan5a7ebdad9999', 'kan_14132433f76b', 'Migrate', 'Freevisa', 'Junior Engineer', '2', null, null, null, 'Australia', null, 'Brisbane', 'user3', '2016-06-14 15:18:57', '2016-06-14 17:32:52', 'sumeshzoft', 'y');
INSERT INTO `enquiry_assessment_migrate_tbl` VALUES ('Kan664d0a0245cf', 'kan_64d0ee4ec807', 'Migrate', 'Freevisa', 'Software Engineer', '1', null, null, null, 'Canada', null, 'Ottawa', 'user3', '2016-06-13 15:04:16', '2016-06-14 17:46:48', 'jitha', 'y');
INSERT INTO `enquiry_assessment_migrate_tbl` VALUES ('Kan8c994eec794e', 'kan_d8377f8e03ff', 'Migrate', 'Permanent', 'Teacher', '2', null, null, null, 'USA', null, 'philadelphia', 'user3', '2016-06-14 11:36:07', '2016-06-14 17:33:44', 'sumeshzoft', 'y');
INSERT INTO `enquiry_assessment_migrate_tbl` VALUES ('Kan90abc83bdb40', 'kan_0814cfa90746', 'Migrate', 'temporary', 'Chemical Engineer', '1', null, null, null, 'Canada', null, 'Ottawa', 'user3', '2016-06-13 15:05:38', '2016-06-14 17:34:54', 'sumeshzoft', 'y');
INSERT INTO `enquiry_assessment_migrate_tbl` VALUES ('Kand0927c44bb6b', 'kan_d2d606026a15', 'Migrate', 'temporary', 'Chemical Engineer', '1', null, null, null, 'New Zealnd', null, 'Hamilton - Waikato', 'user3', '2016-06-13 15:02:26', '2016-06-14 17:50:08', 'jitha', 'y');
INSERT INTO `enquiry_assessment_migrate_tbl` VALUES ('Kandb526484372d', 'kan_0e2069aaa132', 'Migrate', 'temporary', 'Software Engineer', '1', null, null, null, 'Australia', null, 'Brisbane', 'user3', '2016-06-14 09:47:21', '2016-06-14 17:31:57', 'sumeshzoft', 'y');
INSERT INTO `enquiry_assessment_migrate_tbl` VALUES ('Kane1ebc1c94310', 'kan_805877d725d7', 'Migrate', 'temporary', 'Teacher', '1', null, null, null, 'Australia', null, 'Brisbane', 'user3', '2016-06-13 15:13:45', '2016-06-14 17:36:23', 'sumeshzoft', 'y');
INSERT INTO `enquiry_assessment_migrate_tbl` VALUES ('Koc38bcbfc78351', 'koc_1dd89e2611ea', 'Migrate', null, null, null, null, null, null, 'Canada', null, null, 'sandy', '2016-06-13 15:07:44', null, null, 'y');
INSERT INTO `enquiry_assessment_migrate_tbl` VALUES ('Kocdae809c4a0fb', 'koc_8754df24a47b', 'Migrate', null, null, null, null, null, null, 'USA', null, null, 'sandy', '2016-06-14 10:44:52', null, null, 'y');
INSERT INTO `enquiry_assessment_migrate_tbl` VALUES ('Kol38f8189ae4fc', 'kol_d0cc31695858', 'Migrate', 'Freevisa', 'Doctor/Surgen', '2', null, null, null, 'Canada', null, 'Ottawa', 'b', '2016-06-14 13:57:15', '2016-06-14 17:24:34', 'jitha', 'y');
INSERT INTO `enquiry_assessment_migrate_tbl` VALUES ('Kol4747dd1853f5', 'kol_79f0550ce37c', 'Migrate', null, null, null, null, null, null, 'Canada', null, null, 'b', '2016-06-13 14:10:12', null, null, 'y');
INSERT INTO `enquiry_assessment_migrate_tbl` VALUES ('Kol4d409e3db122', 'kol_43c73f9a76f6', 'Migrate', null, null, null, null, null, null, 'Australia', null, null, 'b', '2016-06-14 09:54:32', null, null, 'y');
INSERT INTO `enquiry_assessment_migrate_tbl` VALUES ('Kol7ddb7e7ed0ff', 'kol_8cdccfd7fe58', 'Migrate', null, null, null, null, null, null, 'Canada', null, null, 'b', '2016-06-13 14:25:09', null, null, 'y');
INSERT INTO `enquiry_assessment_migrate_tbl` VALUES ('Kolf56e707cdfa0', 'kol_b379890ae41d', 'Migrate', null, null, null, null, null, null, 'USA', null, null, 'b', '2016-06-14 09:58:04', null, null, 'y');

-- ----------------------------
-- Table structure for enquiry_assessment_other_skills_tbl
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_assessment_other_skills_tbl`;
CREATE TABLE `enquiry_assessment_other_skills_tbl` (
  `other_skill_id` varchar(255) NOT NULL,
  `enquiry_id` varchar(255) DEFAULT NULL,
  `other_skill` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`other_skill_id`),
  KEY `enquiry_assessment_other_skills_tbl_ibfk_1` (`enquiry_id`) USING BTREE,
  CONSTRAINT `enquiry_assessment_other_skills_tbl_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_assessment_other_skills_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for enquiry_assessment_plustwo_tbl
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_assessment_plustwo_tbl`;
CREATE TABLE `enquiry_assessment_plustwo_tbl` (
  `plustwo_id` varchar(255) NOT NULL,
  `enquiry_id` varchar(255) NOT NULL DEFAULT '0',
  `board` varchar(255) DEFAULT NULL,
  `mark` varchar(10) DEFAULT NULL,
  `status` varchar(11) DEFAULT NULL,
  `medium_of_instruction` varchar(255) DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`plustwo_id`),
  UNIQUE KEY `sslc_forign` (`enquiry_id`) USING BTREE,
  CONSTRAINT `enquiry_assessment_plustwo_tbl_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_assessment_plustwo_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for enquiry_assessment_relatives_tbl
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_assessment_relatives_tbl`;
CREATE TABLE `enquiry_assessment_relatives_tbl` (
  `relative_id` varchar(255) NOT NULL,
  `country_relative` varchar(255) DEFAULT NULL,
  `relationship` varchar(255) DEFAULT NULL,
  `enquiry_id` varchar(255) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`relative_id`),
  KEY `enquiry_assessment_relatives_tbl_ibfk_1` (`enquiry_id`) USING BTREE,
  CONSTRAINT `enquiry_assessment_relatives_tbl_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_assessment_relatives_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for enquiry_assessment_spouse_details_tbl
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_assessment_spouse_details_tbl`;
CREATE TABLE `enquiry_assessment_spouse_details_tbl` (
  `spouse_id` varchar(255) NOT NULL,
  `spouse_Name` varchar(40) DEFAULT NULL,
  `enquiry_id` varchar(255) NOT NULL DEFAULT '0',
  `spouse_age` varchar(10) DEFAULT NULL,
  `sslc_board` varchar(255) DEFAULT NULL,
  `plus2_board` varchar(255) DEFAULT NULL,
  `sslc_medium` varchar(255) DEFAULT NULL,
  `plus2_field` varchar(255) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`spouse_id`),
  KEY `enq_id` (`enquiry_id`) USING BTREE,
  CONSTRAINT `enquiry_assessment_spouse_details_tbl_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_assessment_spouse_details_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for enquiry_assessment_sslc_tbl
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_assessment_sslc_tbl`;
CREATE TABLE `enquiry_assessment_sslc_tbl` (
  `sslc_id` varchar(255) NOT NULL,
  `enquiry_id` varchar(255) NOT NULL DEFAULT '0',
  `board` varchar(50) DEFAULT NULL,
  `mark` varchar(10) DEFAULT NULL,
  `status` varchar(11) DEFAULT NULL,
  `medium_of_instruction` varchar(50) DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sslc_id`),
  UNIQUE KEY `sslc_forign` (`enquiry_id`) USING BTREE,
  CONSTRAINT `enquiry_assessment_sslc_tbl_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_assessment_sslc_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for enquiry_assessment_study_tbl
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_assessment_study_tbl`;
CREATE TABLE `enquiry_assessment_study_tbl` (
  `study_pgm_id` varchar(255) NOT NULL,
  `country` varchar(255) DEFAULT NULL,
  `program_level` varchar(255) DEFAULT NULL,
  `program_field` varchar(255) DEFAULT NULL,
  `intake` varchar(40) DEFAULT NULL,
  `institution` varchar(255) DEFAULT NULL,
  `enquiry_id` varchar(255) NOT NULL,
  `study` varchar(255) DEFAULT NULL,
  `otherUniversity` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `amount` varchar(255) DEFAULT NULL,
  `sem_fees` varchar(255) DEFAULT NULL,
  `choice` varchar(20) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `children_accompany` varchar(20) DEFAULT NULL,
  `spouse_accompany` varchar(20) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`study_pgm_id`),
  KEY `enq_id_study_forign` (`enquiry_id`) USING BTREE,
  CONSTRAINT `enquiry_assessment_study_tbl_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_assessment_study_tbl
-- ----------------------------
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_01c7c2de625a', 'Australia', null, null, null, null, 'kol_6fd78a762953', 'Study', null, 'Melbourne', null, null, null, null, null, null, null, 'b', '2016-06-17 12:56:12', null, null, 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_02d9ea3132b0', 'Canada', 'Bachelor', 'BSc cs', null, null, 'kol_fe7fcbb38341', 'Study', null, 'Ottawa', null, null, null, '1', null, null, null, 'b', '2016-06-13 14:29:11', '2016-06-14 13:03:29', 'a', 'n');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_05fbf7f57c69', 'USA', 'Master', 'computer Application', null, null, 'koc_cdc8ad6f110f', 'Study', null, 'philadelphia', null, null, null, '1', null, null, null, 'sandy', '2016-06-13 15:08:24', '2016-06-14 12:59:58', 'sandeep', 'n');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_0c778c297939', 'Canada', null, null, null, null, 'kol_c913b2e4fa1d', 'Study', null, 'Ottawa', null, null, null, null, null, null, null, 'keerthykp', '2016-06-17 14:35:47', null, null, 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_18eee266d06d', 'New Zealnd', 'Master', 'computer Application', null, null, 'kol_87b16f6c6dae', 'Study', null, 'Auckland', null, null, null, '1', null, null, null, 'b', '2016-06-13 10:42:20', '2016-06-14 16:57:09', 'a', 'n');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_2297d9d8c101', 'Australia', 'Bachelor', 'BSc cs', null, null, 'kol_66ebfd1a5540', 'Study', null, 'Sydney', null, null, null, '1', null, null, null, 'b', '2016-06-13 10:40:50', '2016-06-13 18:02:38', 'a', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_2613f8894904', 'Canada', null, null, null, null, 'kol_47a290eaa29d', 'Study', null, null, null, null, null, null, null, null, null, 'b', '2016-06-20 12:56:23', null, null, 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_2d0e976a79be', 'Australia', null, null, null, null, 'koc_a71b02703901', 'Study', null, 'Melbourne', null, null, null, null, null, null, null, 'sandy', '2016-06-17 13:50:16', null, null, 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_2f325e48bea3', 'Australia', 'Bachelor', 'BSc cs', null, null, 'kan_76371a02a618', 'Study', null, 'Brisbane', null, null, null, '1', null, null, null, 'user3', '2016-06-14 15:21:10', '2016-06-14 15:50:34', 'user3', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_2fb32befca48', 'USA', 'PhD/Doctorate', 'Doctor of Psychology', null, null, 'kol_7c38fd4e7311', 'Study', null, 'philadelphia', null, null, null, '1', null, null, null, 'b', '2016-06-13 17:42:04', '2016-06-15 10:14:15', 'admin', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_337beb514f4d', 'Australia', 'Master', 'computer Application', null, null, 'kol_178922ed2ba8', 'Study', null, 'Sydney', null, null, null, '1', null, null, null, 'b', '2016-06-13 10:41:44', '2016-06-13 14:50:10', 'a', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_3607bc662b6e', 'New Zealnd', 'Master', 'computer Application', null, null, 'koc_78df79df877c', 'Study', null, 'Wellington', null, null, null, '1', null, null, null, 'sandy', '2016-06-13 15:11:34', '2016-06-14 10:00:21', 'jitha', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_3b903c09c697', 'Canada', 'Bachelor', 'BSc cs', null, null, 'kol_fe7fcbb38341', 'Study', null, 'Ottawa', null, null, null, '2', null, null, null, 'a', '2016-06-14 13:02:17', '2016-06-14 13:03:29', 'a', 'n');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_3c116bed4423', 'USA', null, null, null, null, 'kol_1b03a7918ff0', 'Study', null, 'New York', null, null, null, null, null, null, null, 'keerthykp', '2016-06-17 14:20:51', null, null, 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_3dccbb19c17b', 'Canada', 'Master', 'computer Application', null, null, 'kol_af76ca381901', 'Study', null, 'Ottawa', null, null, null, '1', null, null, null, 'b', '2016-06-13 19:00:02', '2016-06-15 10:17:26', 'admin', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_404eab529f3b', 'USA', 'Language Course', 'Spoken English', null, null, 'kan_60284b9c5c17', 'Study', null, 'New York', null, null, null, '1', null, null, null, 'user3', '2016-06-14 09:42:22', '2016-06-14 09:51:02', 'user3', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_456b60471c40', 'Canada', null, null, null, null, 'kol_b63d0fcd8aef', 'Study', null, 'Ottawa', null, null, null, null, null, null, null, 'keerthykp', '2016-06-17 14:10:12', null, null, 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_47a29d268ef1', 'New Zealnd', 'Bachelor', 'B.COM', null, null, 'koc_dfca21935856', 'Study', null, 'Auckland', null, null, null, '1', null, null, null, 'sandy', '2016-06-14 10:57:57', '2016-06-15 10:13:29', 'admin', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_4803fb506700', 'Canada', 'Bachelor', 'BSc cs', null, null, 'kan_4f9cddfd4e5c', 'Study', null, 'Ottawa', null, null, null, '1', null, null, null, 'user3', '2016-06-13 14:54:15', '2016-06-13 15:19:07', 'user3', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_4a05e56907bc', 'Canada', null, null, null, null, 'kol_d14b4ae570dd', 'Study', null, 'Ottawa', null, null, null, null, null, null, null, 'b', '2016-06-20 15:42:34', null, null, 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_4f83bb043829', 'New Zealnd', 'Bachelor', 'BA History', null, null, 'koc_b896ec0b0699', 'Study', null, 'Hamilton - Waikato', null, null, null, '2', null, null, null, 'krishna', '2016-06-14 13:01:06', '2016-06-14 13:01:21', 'krishna', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_563f497a35fc', 'Australia', 'Master', 'science', null, null, 'koc_b95c59f14d15', 'Study', null, 'Melbourne', null, null, null, '1', null, null, null, 'sandy', '2016-06-13 14:46:05', '2016-06-15 10:08:21', 'admin', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_5ddd03e11740', 'USA', null, null, null, null, 'kol_cddfe245a085', 'Study', null, 'New York', null, null, null, null, null, null, null, 'b', '2016-06-15 12:10:56', null, null, 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_6825a455b12c', 'Australia', 'PhD/Doctorate', 'Doctor of Psychology', null, null, 'kol_902ef03d9e76', 'Study', null, 'Brisbane', null, null, null, '1', null, null, null, 'b', '2016-06-14 13:57:57', '2016-06-15 10:16:03', 'admin', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_687525d80c9c', 'Australia', null, null, null, null, 'koc_619929d9bc25', 'Study', null, 'Sydney', null, null, null, null, null, null, null, 'sandy', '2016-06-17 13:49:09', null, null, 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_7d43d81e6f43', 'USA', 'Master', 'MBA', null, null, 'koc_cdc8ad6f110f', 'Study', null, 'philadelphia', null, null, null, '2', null, null, null, 'sandeep', '2016-06-14 12:59:58', '2016-06-14 13:00:29', 'sandeep', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_7ec71da53b4a', 'Canada', 'Bachelor', 'BSc cs', null, null, 'kol_d435309754f4', 'Study', null, 'Ottawa', null, null, null, '1', null, null, null, 'b', '2016-06-13 10:41:16', '2016-06-15 10:19:21', 'admin', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_842de484c51d', 'Australia', null, null, null, null, 'kol_2b3eb6eaddd3', 'Study', null, null, null, null, null, null, null, null, null, 'b', '2016-06-20 14:27:15', null, null, 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_9e0d7e484e13', 'USA', 'Language Course', 'Spoken English', null, null, 'koc_cc1b4827b853', 'Study', null, 'New York', null, null, null, '1', null, null, null, 'sandy', '2016-06-14 11:00:11', '2016-06-15 10:09:17', 'admin', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_9eb322ea9d87', 'Canada', 'Bachelor', 'BSc cs', null, null, 'koc_a7041eface62', 'Study', null, 'Ottawa', null, null, null, '1', null, null, null, 'sandy', '2016-06-14 10:30:48', '2016-06-14 11:54:11', 's', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_a642bfdf3cc7', 'New Zealnd', 'Bachelor', 'BSc cs', null, null, 'koc_b896ec0b0699', 'Study', null, 'Hamilton - Waikato', null, null, null, '1', null, null, null, 'sandy', '2016-06-14 10:47:51', '2016-06-14 13:00:55', 'krishna', 'n');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_a6de4705247a', 'Canada', 'Bachelor', 'Btech', null, null, 'kan_0999ea3e41ec', 'Study', null, 'Ottawa', null, null, null, '1', null, null, null, 'user14', '2016-06-14 11:10:18', '2016-06-14 14:28:53', 'user3', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_ac0cb30b1eb3', 'Canada', 'Master', 'MA English', null, null, 'kol_ebae93075120', 'Study', null, 'Ottawa', null, null, null, '1', null, null, null, 'b', '2016-06-14 13:55:18', '2016-06-15 10:26:58', 'admin', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_b2102bebba94', 'Australia', 'Master', 'computer Application', null, null, 'kol_17cf27990d9e', 'Study', null, 'Sydney', null, null, null, '1', null, null, null, 'b', '2016-06-20 14:20:29', '2016-06-20 14:50:02', 'a', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_b441bb1550c3', 'Australia', null, null, null, null, 'kol_7ff348fe6b96', 'Study', null, null, null, null, null, null, null, null, null, 'b', '2016-06-20 13:10:14', null, null, 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_bc96a6774a77', 'Australia', null, null, null, null, 'kol_428755f2c9f8', 'Study', null, null, null, null, null, null, null, null, null, 'b', '2016-06-20 15:43:15', null, null, 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_be6d5b997c91', 'Canada', 'PhD/Doctorate', 'Doctor of Social Work', null, null, 'kol_e77e9f96475b', 'Study', null, 'Ottawa', null, null, null, '1', null, null, null, 'b', '2016-06-14 10:07:21', '2016-06-15 10:20:22', 'admin', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_bfcc53f64ef1', 'Canada', 'PhD/Doctorate', 'Doctor of Psychology', null, null, 'kol_eb5db4536e5b', 'Study', null, 'Ottawa', null, null, null, '1', null, null, null, 'b', '2016-06-14 17:43:24', '2016-06-15 10:22:01', 'admin', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_c0b7d9e55968', 'Canada', 'PhD/Doctorate', 'Doctor of Psychology', null, null, 'kol_fe7fcbb38341', 'Study', null, 'Ottawa', null, null, null, '3', null, null, null, 'a', '2016-06-14 13:02:39', '2016-06-14 13:03:35', 'a', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_da0f8b5fd6f1', 'Australia', null, null, null, null, 'kol_66a39843f66c', 'Study', null, null, null, null, null, null, null, null, null, 'b', '2016-06-20 13:08:19', null, null, 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_e3a253097a63', 'New Zealnd', 'PhD/Doctorate', 'Doctor of Psychology', null, null, 'kol_87b16f6c6dae', 'Study', null, 'Auckland', null, null, null, '2', null, null, null, 'a', '2016-06-14 16:57:09', null, null, 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_e580e3d2c3ce', 'Canada', 'Master', 'computer Application', null, null, 'kol_b672c8fe2b6b', 'Study', null, '', null, null, null, '2', null, null, null, 'b', '2016-06-20 13:11:27', '2016-06-23 11:23:06', 'b', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_eab1719df75c', 'USA', 'Language Course', 'Spoken English', null, null, 'kan_d14f72fc2e86', 'Study', null, 'philadelphia', null, null, null, '1', null, null, null, 'user3', '2016-06-14 15:16:38', '2016-06-14 15:50:44', 'user3', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_f7468b6649ea', 'New Zealnd', 'Bachelor', 'BSc cs', null, null, 'koc_4b1623ba84a3', 'Study', null, 'Auckland', null, null, null, '1', null, null, null, 'sandy', '2016-06-13 15:10:06', '2016-06-15 10:05:15', 'admin', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_fc30f87ca8d5', 'USA', 'Master', 'MA Malayalam', null, null, 'kan_f89c4cc5fcd5', 'Study', null, 'New York', null, null, null, '1', null, null, null, 'b', '2016-06-13 16:49:36', '2016-06-14 09:50:35', 'user3', 'y');
INSERT INTO `enquiry_assessment_study_tbl` VALUES ('st_fef77b0918c1', 'Australia', 'Master', 'MTECH', null, null, 'kan_fd9a9a06b595', 'Study', null, 'Sydney', null, null, null, '1', null, null, null, 'user3', '2016-06-13 15:41:03', '2016-06-14 09:50:45', 'user3', 'y');

-- ----------------------------
-- Table structure for enquiry_assessment_technical_skills_tbl
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_assessment_technical_skills_tbl`;
CREATE TABLE `enquiry_assessment_technical_skills_tbl` (
  `tech_id` varchar(255) NOT NULL,
  `enquiry_id` varchar(255) DEFAULT NULL,
  `technology` varchar(255) DEFAULT NULL,
  `knowledge_level` varchar(255) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tech_id`),
  KEY `enquiry_assessment_technical_skills_tbl_ibfk_1` (`enquiry_id`) USING BTREE,
  CONSTRAINT `enquiry_assessment_technical_skills_tbl_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_assessment_technical_skills_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for enquiry_assessment_teritary_qualification
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_assessment_teritary_qualification`;
CREATE TABLE `enquiry_assessment_teritary_qualification` (
  `teriary_quali_id` varchar(255) NOT NULL,
  `enquiry_id` varchar(255) DEFAULT NULL,
  `teriary_quali_field` varchar(255) DEFAULT NULL,
  `teriary_quali_level` varchar(255) DEFAULT NULL,
  `university` varchar(255) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`teriary_quali_id`),
  KEY `enquiry_id` (`enquiry_id`) USING BTREE,
  CONSTRAINT `enquiry_assessment_teritary_qualification_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_assessment_teritary_qualification
-- ----------------------------
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_00d6ae2e55f0', 'kol_66ebfd1a5540', 'Master', 'Mphil', null, 'a', '2016-06-13 15:17:02', '2016-06-13 17:38:53', 'a', 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_02138d5b3b13', 'kan_e73e0db3ce2c', 'BSc cs', 'Bachelor', null, 'sumeshzoft', '2016-06-14 17:35:35', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_03e59874808a', 'kan_60284b9c5c17', 'Bachelor', 'BSc cs', null, 'sandeep', '2016-06-14 11:41:48', '2016-06-14 11:42:13', 'sandeep', 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_073048c5458f', 'koc_d82424c77dc1', 'Doctor of Psychology', 'PhD/Doctorate', null, 'admin', '2016-06-15 10:30:15', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_0919843159a8', 'kan_f89c4cc5fcd5', 'computer Application', 'Master', null, 'admin', '2016-06-15 10:03:06', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_15aa4a3d8523', 'kan_fadfe7897cb7', 'MTECH', 'Master', null, 'jitha', '2016-06-14 17:26:22', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_190c5b819fca', 'kol_a7433dc10ad7', 'Doctor of Psychology', 'PhD/Doctorate', null, 'jitha', '2016-06-14 17:23:20', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_1bc979ef480b', 'kan_4f9cddfd4e5c', 'BSc cs', 'Bachelor', null, 'jitha', '2016-06-14 09:57:53', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_1ed7c7d0dc02', 'kol_4d5f5f43ac8b', 'computer Application', 'Master', null, 'jitha', '2016-06-14 17:50:21', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_253f8caab423', 'kan_a2c0869fdcde', 'Spoken English', 'Language Course', null, 'jitha', '2016-06-14 17:02:25', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_2927b0c46722', 'kol_902ef03d9e76', 'MTECH', 'Master', null, 'admin', '2016-06-15 10:14:32', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_2a77855ac542', 'kan_a8087d458991', 'Btech', 'Bachelor', null, 'sumeshzoft', '2016-06-13 15:35:40', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_2d0439e4c72e', 'kan_dec1a454c24a', 'Spoken English', 'Language Course', null, 'sumeshzoft', '2016-06-14 17:13:33', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_38037fad4b7c', 'kol_7c38fd4e7311', 'PhD/Doctorate', 'Doctor of Psychology', null, 'a', '2016-06-14 11:44:15', '2016-06-14 11:44:39', 'a', 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_3d83bc609167', 'kol_d0cc31695858', 'Doctor of Psychology', 'PhD/Doctorate', null, 'jitha', '2016-06-14 17:24:09', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_3f4324a6dc83', 'kol_d435309754f4', 'computer Application', 'Master', null, 'admin', '2016-06-15 10:17:36', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_41e8b40ffef1', 'kan_1efb63d594e0', 'computer Application', 'Master', null, 'sumeshzoft', '2016-06-14 17:13:11', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_48b5b99ef4f3', 'kan_9f3f69f63db5', 'BBA', 'Bachelor', null, 'sumeshzoft', '2016-06-14 17:11:33', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_4d6f613cd8de', 'koc_b95c59f14d15', 'MTECH', 'Master', null, 'admin', '2016-06-15 10:07:39', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_4e7b7a74fca7', 'kan_0814cfa90746', 'Spoken English', 'Language Course', null, 'sumeshzoft', '2016-06-14 17:34:20', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_4ec2f0b0911d', 'koc_4b1623ba84a3', 'MA English', 'Master', null, 'admin', '2016-06-15 10:04:02', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_4ecabe9868a6', 'kol_fe7fcbb38341', 'BA History', 'Bachelor', null, 'a', '2016-06-13 17:38:47', '2016-06-14 11:50:39', 'a', 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_5041810079bb', 'kan_d2d606026a15', 'computer Application', 'Master', null, 'jitha', '2016-06-14 17:47:09', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_5118d142c4ce', 'kol_af76ca381901', 'MBA', 'Master', null, 'admin', '2016-06-14 11:47:38', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_52e9023464ee', 'kan_7d4957ffac9c', 'Photoshop', 'Short Course', null, 'jitha', '2016-06-14 17:25:44', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_6761d5fc1e2a', 'kan_cd5e74d67cd1', 'Spoken English', 'Language Course', null, 'sumeshzoft', '2016-06-14 17:43:26', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_6c5598cfc6b2', 'kan_76371a02a618', 'MBA', 'Master', null, 'jitha', '2016-06-14 17:02:00', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_6ebec1516ba7', 'kol_399d5eb36484', 'BBA', 'Bachelor', null, 'a', '2016-06-13 14:37:32', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_75dd8d20b149', 'kan_d14f72fc2e86', 'Doctor of Social Work', 'PhD/Doctorate', null, 'jitha', '2016-06-14 17:02:52', '2016-06-14 17:08:23', 'jitha', 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_823823d5ba07', 'kan_d8377f8e03ff', 'BBA', 'Bachelor', null, 'sumeshzoft', '2016-06-14 17:32:58', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_8c1465dd92f6', 'kan_0999ea3e41ec', 'Btech', 'Bachelor', null, 'jitha', '2016-06-14 17:01:27', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_9450d289d955', 'kol_e77e9f96475b', 'Doctor of Social Work', 'PhD/Doctorate', null, 'admin', '2016-06-15 10:19:42', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_95f3407aea35', 'koc_e454886ba276', 'MA Malayalam', 'Master', null, 'admin', '2016-06-15 10:35:32', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_9b20e42a413a', 'kan_faa02d7d2026', 'Doctor of Social Work', 'PhD/Doctorate', null, 'sumeshzoft', '2016-06-13 15:35:48', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_9c899aac5441', 'koc_dfca21935856', 'Drawing', 'Short Course', null, 'admin', '2016-06-15 10:10:02', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_9e42e72b5ac2', 'kol_ebae93075120', 'BSc cs', 'Bachelor', null, 'a', '2016-06-14 17:21:53', '2016-06-14 17:22:05', 'a', 'n');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_a81dd8b294cd', 'kan_fd9a9a06b595', 'Btech', 'Bachelor', null, 'jitha', '2016-06-14 09:57:58', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_ac5fc884767f', 'kan_f6a01e7d4e70', 'Photoshop', 'Short Course', null, 'jitha', '2016-06-14 17:07:25', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_ae6c2be8863a', 'koc_b896ec0b0699', 'Doctor of Psychology', 'PhD/Doctorate', null, 'sandeep', '2016-06-14 12:33:49', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_b27f5fcf19e3', 'koc_a7041eface62', 'BSc cs', 'Bachelor', null, 's', '2016-06-14 11:53:08', '2016-06-14 11:54:47', 's', 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_b4cab753d5db', 'kan_67aee97bdabe', 'BSc cs', 'Bachelor', null, 'jitha', '2016-06-14 17:57:46', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_b4d3b3cda79d', 'koc_228d6705c090', 'Spoken English', 'Language Course', null, 'admin', '2016-06-15 10:29:00', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_b9b60567aeb3', 'koc_00c9d19f0ec4', 'MTECH', 'Master', null, 'admin', '2016-06-15 10:28:00', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_bc1e7a4ca0a8', 'kol_05dddca31839', 'Btech', 'Bachelor', null, 'a', '2016-06-13 14:37:24', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_be38382c8009', 'kol_eb5db4536e5b', 'computer Application', 'Master', null, 'admin', '2016-06-15 10:20:33', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_cbd75f4a43e8', 'kol_178922ed2ba8', 'computer Application', 'Master', null, 'a', '2016-06-13 14:49:11', '2016-06-15 10:13:36', 'admin', 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_d2b2b5d6e702', 'kol_87b16f6c6dae', 'MBA', 'Master', null, 'a', '2016-06-13 17:38:40', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_dc06619e4144', 'kol_f9cc84fdcc92', 'Spoken English', 'Language Course', null, 'sumeshzoft', '2016-06-14 17:12:15', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_dfca76bdbc32', 'kan_805877d725d7', 'Spoken English', 'Language Course', null, 'sumeshzoft', '2016-06-14 17:35:00', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_e17c2b2b3d9d', 'kol_ebae93075120', 'MTECH', 'Master', null, 'a', '2016-06-14 17:21:59', '2016-06-14 17:22:05', 'a', 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_e2761dd6afed', 'kan_3811aca90659', 'Photoshop', 'Short Course', null, 'jitha', '2016-06-14 17:25:06', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_e8bd26bbe23a', 'kan_0e2069aaa132', 'computer Application', 'Master', null, 'sumeshzoft', '2016-06-14 17:31:08', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_ec4de9f556cd', 'koc_78df79df877c', 'MTECH', 'Master', null, 'jitha', '2016-06-14 09:58:05', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_f247d4424900', 'kan_64d0ee4ec807', 'BSc cs', 'Bachelor', null, 'jitha', '2016-06-13 16:11:49', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_f6b3ed7b1c2e', 'kol_b9749879089d', 'BSC Maths', 'Bachelor', null, 'a', '2016-06-13 14:37:40', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_fa2b7031ce2b', 'koc_cc1b4827b853', 'Spoken English', 'Language Course', null, 'admin', '2016-06-15 10:08:33', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_fa4efb101f46', 'koc_b896ec0b0699', 'computer Application', 'Master', null, 'sandeep', '2016-06-14 12:33:35', '2016-06-14 12:33:49', 'sandeep', 'n');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_fdc11865c2af', 'kan_14132433f76b', 'Photoshop', 'Short Course', null, 'sumeshzoft', '2016-06-14 17:32:06', null, null, 'y');
INSERT INTO `enquiry_assessment_teritary_qualification` VALUES ('quali_fdf0781a6057', 'koc_cdc8ad6f110f', 'MBA', 'Master', null, 'admin', '2016-06-14 11:47:52', null, null, 'y');

-- ----------------------------
-- Table structure for enquiry_assessment_training_tbl
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_assessment_training_tbl`;
CREATE TABLE `enquiry_assessment_training_tbl` (
  `training_id` varchar(255) NOT NULL,
  `enquiry_id` varchar(255) NOT NULL DEFAULT '0',
  `training` varchar(50) DEFAULT NULL,
  `batch` varchar(50) DEFAULT NULL,
  `timing` varchar(50) DEFAULT NULL,
  `duration` varchar(50) DEFAULT NULL,
  `from_date` varchar(50) DEFAULT NULL,
  `to_date` varchar(100) DEFAULT NULL,
  `remarks` varchar(100) DEFAULT NULL,
  `choice` varchar(100) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`training_id`),
  KEY `enq_work_forign` (`enquiry_id`) USING BTREE,
  CONSTRAINT `enquiry_assessment_training_tbl_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_assessment_training_tbl
-- ----------------------------
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kan159f94ade909', 'kan_d14f72fc2e86', 'Spoken English', 'Morning Batch', null, null, '2016-06-19', null, null, null, 'user3', '2016-06-14 15:16:38', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kan38516aa2c0eb', 'kan_a8087d458991', 'Personality development', 'Afternoon Batch', null, null, '2016-07-05', null, null, null, 'user3', '2016-06-13 15:06:22', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kan3a6f34e25c05', 'kan_faa02d7d2026', 'Spoken English', 'Afternoon Batch', null, null, '2016-06-23', null, null, null, 'user3', '2016-06-13 15:01:30', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kan520d17bc1b12', 'kan_1efb63d594e0', 'Spoken English', 'Morning Batch', null, null, '2016-06-30', null, null, null, 'user3', '2016-06-14 10:39:29', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kan8866747df4e4', 'kan_7d4957ffac9c', 'Personality development', 'Afternoon Batch', null, null, '2016-06-22', null, null, null, 'user3', '2016-06-14 10:34:22', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kan97f1e8be9e7a', 'kan_dec1a454c24a', 'IELTS', 'Night batch', null, null, '2016-06-19', null, null, null, 'user14', '2016-06-14 11:07:05', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kanc013a1f764c5', 'kan_fadfe7897cb7', 'FRENCH', 'Morning Batch', null, null, '2016-06-27', null, null, null, 'user3', '2016-06-14 09:49:21', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kand97f6e344739', 'kan_67aee97bdabe', 'Personality development', 'Afternoon Batch', null, null, '2016-06-29', null, null, null, 'user3', '2016-06-13 15:38:01', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kane18663e48aaf', 'kan_3811aca90659', 'Spoken English', 'Night batch', null, null, '2016-06-28', null, null, null, 'user3', '2016-06-14 09:48:04', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kocaee2db15ea20', 'koc_8754df24a47b', 'IELTS', 'Morning Batch', null, null, '2016-06-16', null, null, null, 'sandy', '2016-06-14 10:44:52', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kol096b1f49adb3', 'kol_49c7d8d21eba', 'Spoken English', 'Afternoon Batch', null, null, '2016-06-22', null, null, null, 'b', '2016-06-14 12:34:07', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kol09cf383ef704', 'kol_902ef03d9e76', 'Personality development', 'Morning Batch', null, null, '2016-07-06', null, null, null, 'b', '2016-06-14 13:57:57', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kol0c774e65e7ad', 'kol_7ff348fe6b96', 'IELTS', 'Evening Batch', null, null, '2016-07-07', null, null, null, 'b', '2016-06-20 13:10:14', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kol0e7cedde8632', 'kol_cddfe245a085', 'IELTS', 'Morning Batch', null, null, '2016-07-07', null, null, null, 'b', '2016-06-15 12:10:56', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kol1f0c185ee45f', 'kol_7c38fd4e7311', 'Personality development', 'Morning Batch', null, null, '2016-06-28', null, null, null, 'b', '2016-06-13 17:42:04', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kol253d275b7cdc', 'kol_05dddca31839', 'Personality development', 'Morning Batch', null, null, '2016-06-29', null, null, null, 'b', '2016-06-13 14:27:48', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kol348f31d3028a', 'kol_f76f27840190', 'Personality development', 'Afternoon Batch', null, null, '2016-06-23', null, null, null, 'b', '2016-06-14 14:15:38', '2016-06-15 10:22:27', 'b', 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kol38960be790c7', 'kol_b379890ae41d', 'Spoken English', 'Afternoon Batch', null, null, '2016-06-30', null, null, null, 'b', '2016-06-14 09:58:00', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kol38a741a537b7', 'kol_a8717f31260b', 'Personality development', 'Night batch', null, null, '2016-06-16', null, null, null, 'b', '2016-06-14 14:17:19', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kol42950f602159', 'kol_66a39843f66c', 'IELTS', 'Morning Batch', null, null, '2016-07-08', null, null, null, 'b', '2016-06-20 13:08:19', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kol466f381728ce', 'kol_2b3eb6eaddd3', 'IELTS', 'Evening Batch', null, null, '2016-07-01', null, null, null, 'b', '2016-06-20 14:27:15', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kol4f904368ef91', 'kol_a7433dc10ad7', 'Personality development', 'Night batch', null, null, '2016-06-24', null, null, null, 'b', '2016-06-13 17:31:58', '2016-06-13 17:33:21', 'b', 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kol5539556799d6', 'kol_43c73f9a76f6', 'IELTS', 'midnight batch', null, null, '2016-06-29', null, null, null, 'b', '2016-06-14 09:54:27', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kol5ea10410fce9', 'kol_b89f7b63f70f', 'Personality development', 'Morning Batch', null, null, '2016-06-29', null, null, null, 'b', '2016-06-14 13:56:36', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kol6b6f3d87b8fa', 'kol_fe7fcbb38341', 'Spoken English', 'Afternoon Batch', null, null, '2016-06-29', null, null, null, 'b', '2016-06-13 14:29:11', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kol75b41674649c', 'kol_79f0550ce37c', 'Spoken English', 'Morning Batch', null, null, '2016-06-22', null, null, null, 'b', '2016-06-13 14:10:08', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kol7f9907a7b59e', 'kol_2bd08b71d7ea', 'Personality development', 'Night batch', null, null, '2016-06-23', null, null, null, 'b', '2016-06-14 12:38:07', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kol823309c08775', 'kol_b672c8fe2b6b', 'IELTS', 'Afternoon Batch', null, null, '2016-06-30', null, null, null, 'b', '2016-06-20 13:11:27', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kol82dbc79c1d23', 'kol_60c98adacc3e', 'Personality development', 'Night batch', null, null, '2016-07-06', null, null, null, 'b', '2016-06-17 14:23:23', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kol9f53b47df66f', 'kol_b63d0fcd8aef', 'IELTS', 'Morning Batch', null, null, '2016-06-30', null, null, null, 'keerthykp', '2016-06-17 14:10:07', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kola20c661fad1d', 'kol_ebae93075120', 'Personality development', 'Night batch', null, null, '2016-06-24', null, null, null, 'b', '2016-06-14 13:55:18', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kola4f1a2fdb145', 'kol_dcca7aeb8073', 'IELTS', 'Evening Batch', null, null, '2016-06-23', null, null, null, 'b', '2016-06-20 14:14:16', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kola4f59b71c6d2', 'kol_17cf27990d9e', 'IELTS', 'Evening Batch', null, null, '2016-07-07', null, null, null, 'b', '2016-06-20 14:20:29', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kola555b1a0ce29', 'kol_c913b2e4fa1d', 'Personality development', 'Night batch', null, null, '2016-07-01', null, null, null, 'keerthykp', '2016-06-17 14:35:47', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kolb1e4c308a7b4', 'kol_d0cc31695858', 'Personality development', 'Morning Batch', null, null, '2016-06-29', null, null, null, 'b', '2016-06-14 13:57:15', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kolb65739b0994a', 'kol_63da30698bfa', 'Spoken English', 'Morning Batch', null, null, '2016-06-30', null, null, null, 'b', '2016-06-14 14:16:37', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kolb6ec5f5b5961', 'kol_91e547ed7bb1', 'Personality development', 'Night batch', null, null, '2016-06-30', null, null, null, 'b', '2016-06-17 14:38:03', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kolc2ded08f26b6', 'kol_f9cc84fdcc92', 'Personality development', 'Night batch', null, null, '2016-07-01', null, null, null, 'b', '2016-06-14 13:06:25', '2016-06-14 14:02:12', 'b', 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kold153d9480bb5', 'kol_47a290eaa29d', 'IELTS', 'Morning Batch', null, null, '2016-06-30', null, null, null, 'b', '2016-06-20 12:56:23', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kold22f11eb3cf6', 'kol_e6429f1a8bb0', 'Personality development', 'Morning Batch', null, null, '2016-06-24', null, null, null, 'b', '2016-06-17 14:31:56', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kold3cda6df511b', 'kol_fa4ce2149bb4', 'Personality development', 'Night batch', null, null, '2016-06-16', null, null, null, 'b', '2016-06-14 17:49:34', null, null, 'y');
INSERT INTO `enquiry_assessment_training_tbl` VALUES ('Kolef98118d1a8f', 'kol_b9749879089d', 'IELTS', 'Morning Batch', null, null, '2016-06-23', null, null, null, 'b', '2016-06-13 14:26:54', null, null, 'y');

-- ----------------------------
-- Table structure for enquiry_assessment_work_exp_tbl
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_assessment_work_exp_tbl`;
CREATE TABLE `enquiry_assessment_work_exp_tbl` (
  `work_exp_id` varchar(255) NOT NULL,
  `enquiry_id` varchar(255) NOT NULL DEFAULT '0',
  `profession` varchar(50) DEFAULT NULL,
  `duration` varchar(11) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`work_exp_id`),
  KEY `workexp_forign` (`enquiry_id`) USING BTREE,
  CONSTRAINT `enquiry_assessment_work_exp_tbl_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_assessment_work_exp_tbl
-- ----------------------------
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_00b5073c7ceb', 'kan_fd9a9a06b595', 'Software Engineer', '2 year', 'sumeshzoft', '2016-06-14 17:39:51', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_029061e14f84', 'kan_67aee97bdabe', 'Doctor/Surgen', '1 month', 'jitha', '2016-06-14 17:57:57', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_05bf4f8e11ca', 'koc_4b1623ba84a3', 'Software Engineer', '6 month', 'admin', '2016-06-15 10:04:45', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_07baa4579fa7', 'koc_b95c59f14d15', 'Doctor/Surgen', '2 year', 'admin', '2016-06-15 10:08:07', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_0d7d9064ca52', 'kol_178922ed2ba8', 'Teacher', '3 year', 'a', '2016-06-14 17:11:04', '2016-06-14 19:08:57', 'a', 'n');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_12833c071261', 'koc_cdc8ad6f110f', 'Software Engineer', '2 year', 'admin', '2016-06-15 10:09:46', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_14cf4d47924a', 'kol_ebae93075120', 'Software Engineer', '6 month', 'a', '2016-06-14 16:47:14', '2016-06-14 16:49:26', 'a', 'n');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_19d27434c593', 'koc_b896ec0b0699', 'Doctor/Surgen', '1 year', 'admin', '2016-06-15 10:07:27', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_22d5a4e30d71', 'kol_178922ed2ba8', 'Architect', '6 month', 'a', '2016-06-14 17:09:48', '2016-06-14 19:08:58', 'a', 'n');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_2508b13409cc', 'kan_dec1a454c24a', 'Architect', '1 year', 'sumeshzoft', '2016-06-14 17:13:51', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_2fd7afcb891b', 'kol_902ef03d9e76', 'Software Engineer', '6 month', 'admin', '2016-06-15 10:14:52', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_354fae99410a', 'kol_ebae93075120', 'Software Engineer', '2 month', 'a', '2016-06-14 16:47:08', '2016-06-14 16:49:27', 'a', 'n');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_3a1ecc523d53', 'kan_d8377f8e03ff', 'Doctor/Surgen', '1 year', 'sumeshzoft', '2016-06-14 17:33:22', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_3c5ee8ca3356', 'kan_1efb63d594e0', 'Software Engineer', '6 month', 'sumeshzoft', '2016-06-14 17:13:26', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_3d820d27729d', 'kol_f9cc84fdcc92', 'Software Engineer', '2 month', 'sumeshzoft', '2016-06-14 17:12:30', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_3e9f7bbdb9fc', 'kol_ebae93075120', 'Architect', '6 month', 'a', '2016-06-14 16:46:24', '2016-06-14 16:49:27', 'a', 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_3fb6314d97b0', 'koc_a7041eface62', 'Software Engineer', '1 year', 'admin', '2016-06-15 10:06:49', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_40dbc09ec43c', 'kan_0999ea3e41ec', 'Software Engineer', '6 month', 'jitha', '2016-06-14 17:01:46', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_43610905e625', 'kan_d14f72fc2e86', 'Architect', '6 month', 'jitha', '2016-06-14 17:03:09', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_46c42175a964', 'koc_228d6705c090', 'Doctor/Surgen', '1 year', 'admin', '2016-06-15 10:29:27', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_5486ebf40e4d', 'kan_f6a01e7d4e70', 'Software Engineer', '6 month', 'jitha', '2016-06-14 17:07:35', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_58ab21214595', 'kol_d0cc31695858', 'Software Engineer', '6 month', 'jitha', '2016-06-14 17:24:22', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_5c643f47d3cb', 'kol_66ebfd1a5540', 'Architect', '3 year', 'a', '2016-06-14 16:03:38', '2016-06-14 16:35:15', 'a', 'n');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_5d12ad060b50', 'kan_64d0ee4ec807', 'Architect', '1 month', 'jitha', '2016-06-14 17:46:35', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_6133babe0768', 'kol_d435309754f4', 'Software Engineer', '2 year', 'admin', '2016-06-15 10:18:15', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_64b809f4e359', 'kol_66ebfd1a5540', 'Software Engineer', '2 month', 'a', '2016-06-14 16:35:11', '2016-06-14 16:35:15', 'a', 'n');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_6931ced1c0ae', 'kol_fe7fcbb38341', 'Doctor/Surgen', '1 year', 'admin', '2016-06-15 10:26:40', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_6b6588f00f32', 'kan_60284b9c5c17', 'Junior Engineer', '2 month', 'sandeep', '2016-06-14 17:01:22', '2016-06-14 17:02:08', 'sandeep', 'n');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_7345dbc1bcd8', 'kan_60284b9c5c17', 'Teacher', '1 year', 'sandeep', '2016-06-14 17:00:07', '2016-06-14 17:02:08', 'sandeep', 'n');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_7ceb503bd7ff', 'kol_f76f27840190', 'Software Engineer', '1 month', 'a', '2016-06-15 19:38:25', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_7d413fa8c465', 'kan_a2c0869fdcde', 'Doctor/Surgen', '1 month', 'jitha', '2016-06-14 17:02:42', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_81a562fa4e05', 'kol_66ebfd1a5540', 'Nurse', '2 month', 'a', '2016-06-14 15:59:40', '2016-06-14 16:35:15', 'a', 'n');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_84503f557b71', 'kol_66ebfd1a5540', 'Doctor/Surgen', '1 year', 'a', '2016-06-14 15:59:51', '2016-06-14 16:35:15', 'a', 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_8a4dcca8d00e', 'kan_4f9cddfd4e5c', 'Doctor/Surgen', '1 month', 'sumeshzoft', '2016-06-14 17:39:22', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_8a8450343e45', 'kol_178922ed2ba8', 'Junior Engineer', '2 month', 'a', '2016-06-14 17:10:03', '2016-06-14 19:08:58', 'a', 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_8acc57f9c47f', 'kan_7d4957ffac9c', 'Architect', '1 year', 'jitha', '2016-06-14 17:26:10', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_8b1cc06e8830', 'kan_0814cfa90746', 'Software Engineer', '2 year', 'sumeshzoft', '2016-06-14 17:34:40', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_8d9bcaa95cbb', 'kan_805877d725d7', 'Doctor/Surgen', '1 year', 'sumeshzoft', '2016-06-14 17:35:11', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_8e65645a3955', 'kol_178922ed2ba8', 'Software Engineer', '2 year', 'a', '2016-06-14 17:09:55', '2016-06-14 19:08:57', 'a', 'n');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_a05986dcdfac', 'koc_e454886ba276', 'Nurse', '1 year', 'admin', '2016-06-15 10:35:43', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_a0bac64e2ea5', 'kol_87b16f6c6dae', 'Architect', '2 month', 'a', '2016-06-14 16:53:41', '2016-06-14 16:53:47', 'a', 'n');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_a19d900558c7', 'kan_60284b9c5c17', 'Nurse', '2 month', 'sandeep', '2016-06-14 16:59:45', '2016-06-14 17:02:08', 'sandeep', 'n');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_a5add5c7645a', 'kan_14132433f76b', 'Software Engineer', '2 year', 'sumeshzoft', '2016-06-14 17:32:30', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_a7bf2f65b02e', 'kan_f89c4cc5fcd5', 'Software Engineer', '6 month', 'admin', '2016-06-15 10:03:25', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_a91df483f57e', 'kol_a7433dc10ad7', 'Doctor/Surgen', '1 year', 'jitha', '2016-06-14 17:23:30', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_ae75b4235dac', 'kol_4d5f5f43ac8b', 'Software Engineer', '2 month', 'jitha', '2016-06-14 17:50:33', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_ae78c312a980', 'kol_eb5db4536e5b', 'Architect', '2 month', 'admin', '2016-06-15 10:20:58', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_af2b0d983fef', 'kan_fadfe7897cb7', 'Architect', '1 year', 'jitha', '2016-06-14 17:27:13', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_af7ebc6451da', 'koc_cc1b4827b853', 'Teacher', '2 month', 'admin', '2016-06-15 10:09:05', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_b192f7784115', 'kan_3811aca90659', 'Doctor/Surgen', '6 month', 'jitha', '2016-06-14 17:25:34', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_b5e8cb194ffc', 'kan_faa02d7d2026', 'Doctor/Surgen', '1 year', 'jitha', '2016-06-14 17:58:16', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_b6b42f06ce0d', 'kol_87b16f6c6dae', 'Doctor/Surgen', '1 year', 'a', '2016-06-14 16:53:32', '2016-06-14 16:53:47', 'a', 'n');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_b75cfbfb3fae', 'kan_60284b9c5c17', 'Chemical Engineer', '3 year', 'sandeep', '2016-06-14 16:59:59', '2016-06-14 17:02:08', 'sandeep', 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_b8fd443e000b', 'koc_d82424c77dc1', 'Teacher', '3 year', 'admin', '2016-06-15 10:30:37', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_be1784849b1b', 'kan_9f3f69f63db5', 'Architect', '1 month', 'sumeshzoft', '2016-06-14 17:11:50', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_c0a0b784dd86', 'kol_87b16f6c6dae', 'Doctor/Surgen', '6 month', 'a', '2016-06-14 16:53:47', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_c6adae989a0c', 'kan_cd5e74d67cd1', 'Architect', '2 month', 'sumeshzoft', '2016-06-14 17:43:38', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_c85d5335bf8b', 'kan_76371a02a618', 'Architect', '6 month', 'jitha', '2016-06-14 17:02:16', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_cb0bd19505aa', 'kan_e73e0db3ce2c', 'Doctor/Surgen', '2 month', 'sumeshzoft', '2016-06-14 17:36:01', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_d2baa87f9eb3', 'kan_0e2069aaa132', 'Software Engineer', '2 month', 'sumeshzoft', '2016-06-14 17:31:37', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_d568ae60b8b7', 'koc_78df79df877c', 'Software Engineer', '1 month', 'admin', '2016-06-15 10:05:27', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_d89c6028abdc', 'kol_af76ca381901', 'Teacher', '3 year', 'admin', '2016-06-15 10:17:17', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_ddc25ce4682d', 'kol_e77e9f96475b', 'Doctor/Surgen', '1 month', 'admin', '2016-06-15 10:20:04', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_e75f15b3ec97', 'kan_d2d606026a15', 'Architect', '1 year', 'jitha', '2016-06-14 17:47:32', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_e7fbedc97cc7', 'kol_7c38fd4e7311', 'Chemical Engineer', '2 month', 'a', '2016-06-14 16:55:02', '2016-06-14 16:55:31', 'a', 'n');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_eb9b70abafbe', 'koc_dfca21935856', 'Architect', '1 year', 'admin', '2016-06-15 10:13:18', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_ede682cf647f', 'kol_7c38fd4e7311', 'Chemical Engineer', '3 year', 'a', '2016-06-14 16:55:31', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_fbbeca5f8fee', 'kol_7c38fd4e7311', 'Doctor/Surgen', '2 month', 'a', '2016-06-14 16:55:14', '2016-06-14 16:55:31', 'a', 'n');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_fc5c855cd115', 'koc_00c9d19f0ec4', 'Software Engineer', '2 year', 'admin', '2016-06-15 10:28:29', null, null, 'y');
INSERT INTO `enquiry_assessment_work_exp_tbl` VALUES ('wk_fc76f4fdcde6', 'kol_ebae93075120', 'Doctor/Surgen', '2 month', 'a', '2016-06-14 16:46:17', '2016-06-14 16:49:26', 'a', 'n');

-- ----------------------------
-- Table structure for enquiry_assessment_work_tbl
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_assessment_work_tbl`;
CREATE TABLE `enquiry_assessment_work_tbl` (
  `work_id` varchar(255) NOT NULL,
  `enquiry_id` varchar(255) NOT NULL DEFAULT '0',
  `work` varchar(50) DEFAULT NULL,
  `profession` varchar(50) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `choice` varchar(50) DEFAULT NULL,
  `spouce_accompany` varchar(11) DEFAULT '0',
  `children_accompany` varchar(11) DEFAULT '0',
  `special_comment` varchar(100) DEFAULT NULL,
  `min_salary` varchar(100) DEFAULT NULL,
  `max_salary` varchar(100) DEFAULT NULL,
  `currency` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `industry` varchar(100) DEFAULT NULL,
  `employer_choice` varchar(100) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  `skill_area` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`work_id`,`enquiry_id`),
  KEY `enq_work_forign` (`enquiry_id`) USING BTREE,
  CONSTRAINT `enquiry_assessment_work_tbl_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_assessment_work_tbl
-- ----------------------------
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_04357bbaf7f9', 'kan_0999ea3e41ec', 'Work', null, 'Ottawa', null, null, null, null, null, null, null, 'Canada', null, null, 'b', '2016-06-16 17:28:32', null, null, 'y', null);
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_0dba7c74f80a', 'kol_49c7d8d21eba', 'Work', null, 'New York', null, null, null, null, null, null, null, 'USA', null, null, 'b', '2016-06-14 12:34:11', null, null, 'y', null);
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_1393d64259d9', 'kol_b29f45a47919', 'Work', null, 'Ottawa', null, null, null, null, null, null, null, 'Canada', null, null, 'keerthykp', '2016-06-17 14:42:17', null, null, 'y', null);
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_1a9095688af9', 'kan_f6a01e7d4e70', 'Work', 'Software Engineer', 'Ottawa', null, null, null, null, '25000', '678090', 'Angola(AOA)', 'Canada', null, '1', 'b', '2016-06-13 16:46:35', '2016-06-14 17:08:02', 'jitha', 'y', 'Doctor/Surgen');
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_2d8bda82e86b', 'kol_f9cc84fdcc92', 'Work', 'Teacher', 'Ottawa', null, null, null, null, '40000', '200000', 'Angola(AOA)', 'Canada', null, '2', 'b', '2016-06-14 14:02:12', '2016-06-14 17:12:59', 'sumeshzoft', 'y', 'Chemical Engineer');
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_35aa11710821', 'koc_00c9d19f0ec4', 'Work', 'Software Engineer', 'Melbourne', null, null, null, null, '30000', '200000', 'American Samoa(USD)', 'Australia', null, '1', 'sandy', '2016-06-13 15:07:01', '2016-06-15 10:28:52', 'admin', 'y', 'Software Engineer');
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_3879d950e086', 'koc_228d6705c090', 'Work', 'Doctor/Surgen', 'Ottawa', null, null, null, null, '35000', '678090', 'Anguilla(XCD)', 'Canada', null, '1', 'sandy', '2016-06-14 10:00:00', '2016-06-15 10:29:44', 'admin', 'y', 'Doctor/Surgen');
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_46fe847a3260', 'kol_a7433dc10ad7', 'Work', 'Doctor/Surgen', 'Ottawa', null, null, null, null, '35000', '100500', 'Algeria(DZD)', 'Canada', null, '2', 'b', '2016-06-13 17:33:21', '2016-06-14 17:24:03', 'jitha', 'y', 'Doctor/Surgen');
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_5241b557cfb0', 'kol_05dddca31839', 'Work', null, 'Melbourne', null, null, null, null, null, null, null, 'Australia', null, null, 'b', '2016-06-13 14:27:48', null, null, 'y', null);
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_5beb89608110', 'kol_0c1764954c6d', 'Work', null, 'Ottawa', null, null, null, null, null, null, null, 'Canada', null, null, 'b', '2016-06-13 16:48:21', null, null, 'y', null);
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_68447058b619', 'kol_b89f7b63f70f', 'Work', null, 'Sydney', null, null, null, null, null, null, null, 'Australia', null, null, 'b', '2016-06-14 13:56:36', null, null, 'y', null);
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_691f0900bfe4', 'kan_cd5e74d67cd1', 'Work', 'Chemical Engineer', 'Ottawa', null, null, null, null, '30000', '100500', 'Algeria(DZD)', 'Canada', null, '1', 'user3', '2016-06-14 09:46:33', '2016-06-14 17:43:55', 'sumeshzoft', 'y', 'Nurse');
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_6f1e1e896b8c', 'koc_d82424c77dc1', 'Work', 'Nurse', 'Ottawa', null, null, null, null, '25000', '5600888', 'Angola(AOA)', 'Canada', null, '1', 'sandy', '2016-06-14 10:58:55', '2016-06-15 10:35:22', 'admin', 'y', 'Teacher');
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_80f39a7b45f8', 'kol_008a6e0d3c74', 'Work', null, 'Melbourne', null, null, null, null, null, null, null, 'Australia', null, null, 'b', '2016-06-17 12:55:23', null, null, 'y', null);
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_8236f4e550b1', 'kol_d33b0ffcb552', 'Work', null, 'Ottawa', null, null, null, null, null, null, null, 'Canada', null, null, 'b', '2016-06-13 15:36:36', null, null, 'y', null);
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_9451195734c5', 'kol_b9749879089d', 'Work', 'Nurse', 'New York', null, null, null, null, '25000', '999900', 'Afghanistan(AFN)', 'USA', null, '1', 'b', '2016-06-13 14:26:54', '2016-06-13 18:14:50', 'admin', 'y', 'Nurse');
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_9e9eeee1602a', 'kol_4d5f5f43ac8b', 'Work', 'Architect', 'philadelphia', null, null, null, null, '25000', '200000', 'American Samoa(USD)', 'USA', null, '2', 'b', '2016-06-13 17:40:14', '2016-06-14 17:50:56', 'jitha', 'y', 'Software Engineer');
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_a87044852ab1', 'kol_60c98adacc3e', 'Work', null, 'Brisbane', null, null, null, null, null, null, null, 'Australia', null, null, 'b', '2016-06-17 14:23:23', null, null, 'y', null);
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_b387f1db3c14', 'kol_91e547ed7bb1', 'Work', null, 'Ottawa', null, null, null, null, null, null, null, 'Canada', null, null, 'b', '2016-06-17 14:38:03', null, null, 'y', null);
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_c2a96b10d836', 'kol_f76f27840190', 'Work', null, 'Ottawa', null, null, null, null, null, null, null, 'Canada', null, null, 'b', '2016-06-15 10:22:27', null, null, 'y', null);
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_cb729bd4dfe6', 'kol_d7a4f085ca0f', 'Work', null, 'Ottawa', null, null, null, null, null, null, null, 'Canada', null, null, 'b', '2016-06-17 14:39:49', null, null, 'y', null);
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_cd411838b0b1', 'kol_47a290eaa29d', 'Work', null, null, null, null, null, null, null, null, null, 'Canada', null, null, 'b', '2016-06-20 12:56:23', null, null, 'y', null);
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_d05b1fcd589f', 'kol_2bd08b71d7ea', 'Work', null, 'Ottawa', null, null, null, null, null, null, null, 'Canada', null, null, 'b', '2016-06-14 12:38:12', null, null, 'y', null);
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_d0abc67b1531', 'kol_dcca7aeb8073', 'Work', null, null, null, null, null, null, null, null, null, 'Australia', null, null, 'b', '2016-06-20 14:14:16', null, null, 'y', null);
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_d0e35ee6adc3', 'koc_e454886ba276', 'Work', 'Nurse', 'Melbourne', null, null, null, null, '25000', '999900', 'Albania(ALL)', 'Australia', null, '2', 'sandy', '2016-06-13 15:10:54', '2016-06-15 10:35:55', 'admin', 'y', 'Chemical Engineer');
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_d7c645cf0f73', 'kan_9f3f69f63db5', 'Work', 'Architect', 'Ottawa', null, null, null, null, '35000', '888888', 'Albania(ALL)', 'Canada', null, '2', 'user3', '2016-06-14 15:19:47', '2016-06-14 17:12:07', 'sumeshzoft', 'y', 'Software Engineer');
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_d85cf7715bb4', 'kol_f76f27840190', 'Work', null, 'Ottawa', null, null, null, null, null, null, null, 'Canada', null, null, 'b', '2016-06-14 14:15:38', null, null, 'n', null);
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_dbbcc2178891', 'kol_63da30698bfa', 'Work', null, 'New York', null, null, null, null, null, null, null, 'USA', null, null, 'b', '2016-06-14 14:16:37', null, null, 'y', null);
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_e679bd677fdc', 'kol_fa4ce2149bb4', 'Work', null, 'philadelphia', null, null, null, null, null, null, null, 'USA', null, null, 'b', '2016-06-14 17:49:34', null, null, 'y', null);
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_f32ced952ec3', 'kol_399d5eb36484', 'Work', null, 'Auckland', null, null, null, null, null, null, null, 'New Zealnd', null, null, 'b', '2016-06-13 14:26:04', null, null, 'y', null);
INSERT INTO `enquiry_assessment_work_tbl` VALUES ('wk_ffd7737de3bb', 'kol_0c1764954c6d', 'Work', null, 'Ottawa', null, null, null, null, null, null, null, 'Canada', null, null, 'b', '2016-06-13 14:18:37', null, null, 'n', null);

-- ----------------------------
-- Table structure for enquiry_contact_numbers_tbl
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_contact_numbers_tbl`;
CREATE TABLE `enquiry_contact_numbers_tbl` (
  `contact_id` varchar(255) NOT NULL,
  `enquiry_id` varchar(255) DEFAULT NULL,
  `std_isd_code` varchar(255) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `std_isd_code2` varchar(255) DEFAULT NULL,
  `contact_number2` varchar(255) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`contact_id`),
  KEY `cnt_f_key` (`enquiry_id`) USING BTREE,
  CONSTRAINT `enquiry_contact_numbers_tbl_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_contact_numbers_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for enquiry_count
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_count`;
CREATE TABLE `enquiry_count` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) DEFAULT NULL,
  `count` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_count
-- ----------------------------

-- ----------------------------
-- Table structure for enquiry_datemodified_tbl
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_datemodified_tbl`;
CREATE TABLE `enquiry_datemodified_tbl` (
  `id` varchar(255) NOT NULL,
  `enquiry_id` varchar(255) DEFAULT NULL,
  `modified_date` date DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `modikey` (`enquiry_id`) USING BTREE,
  CONSTRAINT `enquiry_datemodified_tbl_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_datemodified_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for enquiry_details
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_details`;
CREATE TABLE `enquiry_details` (
  `enquiry_id` varchar(255) NOT NULL,
  `contact_name` varchar(30) DEFAULT NULL,
  `contact_number` varchar(15) DEFAULT NULL,
  `contact_email` varchar(30) DEFAULT NULL,
  `branch` varchar(20) DEFAULT NULL,
  `country` varchar(80) DEFAULT NULL,
  `state` varchar(30) DEFAULT NULL,
  `district` varchar(30) DEFAULT NULL,
  `enquiry_source` varchar(30) DEFAULT NULL,
  `enquiry_assigning` varchar(30) DEFAULT NULL,
  `other_enquiry` text,
  `rating` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `edate` date DEFAULT NULL,
  `std_code` varchar(10) DEFAULT NULL,
  `read_flag` varchar(11) DEFAULT '0',
  `staff_usrname` varchar(100) DEFAULT '0',
  `staff_branch` varchar(100) DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  `proposed_training_date` date DEFAULT NULL,
  `important_flag` int(11) DEFAULT NULL,
  `proposed_training_time` varchar(20) DEFAULT NULL,
  `completion_flag` int(11) DEFAULT '0',
  `enquiry_method` varchar(100) DEFAULT NULL,
  `search_dummy` varchar(10) DEFAULT NULL,
  `updated_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `contact_number2` varchar(100) DEFAULT NULL,
  `std_code2` varchar(30) DEFAULT NULL,
  `department` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`enquiry_id`),
  KEY `branch` (`branch`) USING BTREE,
  KEY `enquiry_id` (`enquiry_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_details
-- ----------------------------
INSERT INTO `enquiry_details` VALUES ('kan_0814cfa90746', 'leena', '9262767627', 'leena@ll.cc', 'Kannur', 'India', 'kerala', 'Alappuzha', 'Direct', 'sumeshzoft', null, '0', '0', '2016-06-13', '+91', null, 'user3', 'Kannur', null, null, '0', null, null, 'Direct', 'keerthykp', null, '', '', 'Night batch');
INSERT INTO `enquiry_details` VALUES ('kan_0999ea3e41ec', 'swathy', '2215642156', 'swathy@ff.cc', 'Kannur', 'India', 'kerala', 'kottayam', 'Direct', 'sumeshzoft', null, '0', '0', '2016-06-14', '+91', null, 'user14', 'Kannur', null, null, '0', null, null, 'Direct', 'b', null, '', '', 'Afternoon Batch');
INSERT INTO `enquiry_details` VALUES ('kan_0e2069aaa132', 'jomon', '4362242352', 'jomon@vv.cc', 'Kannur', 'India', 'kerala', 'Malappuram', 'phone', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'user3', 'Kannur', null, null, '0', null, '0', 'News paper', 'user3', '2016-06-14 17:55:52', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kan_14132433f76b', 'yamuna', '8768678683', 'yyy@mm.cc', 'Kannur', 'India', 'kerala', 'Kozhikode', 'facebook', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'user3', 'Kannur', null, null, '0', null, '0', 'Method one', 'user3', '2016-06-15 17:23:05', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kan_1efb63d594e0', 'anil', '3424526354', 'anil@gg.cc', 'Kannur', 'India', 'kerala', 'Palakkad', 'Direct', 'sumeshzoft', null, '0', '0', '2016-06-14', '+91', '0', 'user3', 'Kannur', null, null, '0', null, '0', 'Direct', 'user3', '2016-06-14 17:40:02', '', '', 'Morning Batch');
INSERT INTO `enquiry_details` VALUES ('kan_3811aca90659', 'divya', '3226126215', 'divya@dd.cc', 'Kannur', 'India', 'kerala', 'Wayanad', 'mobile', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'user3', 'Kannur', null, null, '0', null, '0', 'Method one', 'user3', '2016-06-14 18:27:12', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kan_4f9cddfd4e5c', 'Omana', '4324234234', 'Omana@oo.cc', 'Kannur', 'India', 'kerala', 'Idukki', 'phone', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', '0', 'user3', 'Kannur', null, null, '0', null, '0', 'Phone', 'user3', '2016-06-15 11:40:27', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kan_60284b9c5c17', 'sanu', '2351673173', 'sanu@gg.cc', 'Kochi', 'India', 'kerala', 'Kasaragod', 'facebook', 'Not Assigned', null, '1', '0', '2016-06-14', '+91', null, 'user3', 'Kannur', null, null, '0', null, '0', 'Tv', 'b', '2016-06-17 14:32:26', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kan_64d0ee4ec807', 'unnimol', '3235265426', 'unnimol@gg.cc', 'Kannur', 'India', 'kerala', 'Malappuram', 'facebook', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', '0', 'user3', 'Kannur', null, null, '0', null, '0', 'News paper', 'user3', '2016-06-15 17:22:45', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kan_67aee97bdabe', 'sandra', '9211271627', 'sandra@ss.com', 'Kannur', 'India', 'kerala', 'Wayanad', 'facebook', 'Not Assigned', null, '1', '0', '2016-06-13', '+91', '0', 'user3', 'Kannur', null, null, '0', null, '0', 'Phone', 'user3', '2016-06-14 18:07:32', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kan_76371a02a618', 'hanitha', '1424242341', 'hhh@gg.cc', 'Kannur', 'India', 'kerala', 'Thrissur', 'mobile', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'user3', 'Kannur', null, null, '0', null, '0', 'Method 4', 'user3', '2016-06-15 11:40:14', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kan_7d4957ffac9c', 'santhy', '2421546215', 'sandu@ff.cc', 'Kannur', 'India', 'kerala', 'Malappuram', 'Direct', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'user3', 'Kannur', null, null, '0', null, '0', 'Direct', 'user3', '2016-06-14 18:27:12', '', '', 'Morning Batch');
INSERT INTO `enquiry_details` VALUES ('kan_805877d725d7', 'kavitha', '7676575756', 'kavi@gmail.com', 'Kannur', 'India', 'kerala', 'Thrissur', 'orkut', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', '0', 'user3', 'Kannur', null, null, '0', null, '0', 'News paper', 'user3', '2016-06-15 17:22:45', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kan_9f3f69f63db5', 'kalyany', '8232313123', 'kallu@dd.cc', 'Kannur', 'India', 'kerala', 'Kasaragod', 'Direct', 'sumeshzoft', null, '0', '0', '2016-06-14', '+91', '0', 'user3', 'Kannur', null, null, '0', null, '0', 'Direct', 'user3', '2016-06-15 17:37:01', '', '', 'Evening Batch');
INSERT INTO `enquiry_details` VALUES ('kan_a2c0869fdcde', 'saadiya', '3653737637', 'saadi@dd.cc', 'Kannur', 'India', 'kerala', 'Kollam', 'mobile', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'user14', 'Kannur', null, null, '0', null, '0', 'Phone', 'user14', '2016-06-14 18:07:12', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kan_a8087d458991', 'jaanu', '3476347683', 'jaanu@gg.jj', 'Kannur', 'India', 'kerala', 'Kozhikode', 'chats', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', '0', 'user3', 'Kannur', null, null, '0', null, '0', 'Phone', 'user3', '2016-06-14 18:27:32', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kan_cd5e74d67cd1', 'geetha', '7252525252', 'geetha@gg.cc', 'Kannur', 'India', 'kerala', 'Kozhikode', 'messenger', 'jitha', null, '0', '0', '2016-06-14', '+91', '0', 'user3', 'Kannur', null, null, '0', null, '0', 'News paper', 'user3', '2016-06-16 09:28:31', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kan_d14f72fc2e86', 'aswathy', '7653745637', 'aswathy@ff.cc', 'Kannur', 'India', 'kerala', 'Kasaragod', 'Direct', 'jitha', null, '0', '0', '2016-06-14', '+91', '0', 'user3', 'Kannur', null, null, '0', null, '0', 'Direct', 'user3', '2016-06-15 17:37:47', '', '', 'Morning Batch');
INSERT INTO `enquiry_details` VALUES ('kan_d2d606026a15', 'haritha', '7878787887', 'harith@hh.cc', 'Kannur', 'India', 'kerala', 'Kollam', 'messenger', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', '0', 'user3', 'Kannur', null, null, '0', null, '0', 'News paper', 'user3', '2016-06-15 17:22:45', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kan_d8377f8e03ff', 'saneesh', '9232326323', 'saneesh@ss.cc', 'Kannur', 'India', 'kerala', 'Thrissur', 'facebook', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'user3', 'Kannur', null, null, '0', null, '0', 'News paper', 'user3', '2016-06-15 17:22:45', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kan_dec1a454c24a', 'amritha', '8326753276', 'amritha@ff.cc', 'Kannur', 'India', 'kerala', 'Kollam', 'Direct', 'sumeshzoft', null, '0', '0', '2016-06-14', '+91', '0', 'user14', 'Kannur', null, null, '0', null, '0', 'Direct', 'user14', '2016-06-14 17:40:01', '', '', 'Morning Batch');
INSERT INTO `enquiry_details` VALUES ('kan_e73e0db3ce2c', 'kiran', '9226262626', 'kiran@kk.cc', 'Kannur', 'India', 'kerala', 'Thiruvananthapuram', 'mobile', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', null, 'user3', 'Kannur', null, null, '0', null, '0', 'Method one', 'b', '2016-06-14 17:56:14', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kan_f6a01e7d4e70', 'kasthoory', '8932313120', 'kastu@kk.cc', 'Kannur', 'India', 'kerala', 'Kollam', 'orkut', 'jitha', null, '0', '0', '2016-06-13', '+91', null, 'user3', 'Kannur', null, null, '0', null, '0', 'Direct', 'b', '2016-06-15 17:36:37', '', '', 'Evening Batch');
INSERT INTO `enquiry_details` VALUES ('kan_f89c4cc5fcd5', 'meena', '9121212121', 'meena123@gg.cc', 'Kollam', 'India', 'kerala', 'Thrissur', 'chats', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', '0', 'user3', 'Kannur', null, null, '0', null, '0', 'Method one', 'b', '2016-06-17 15:41:50', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kan_faa02d7d2026', 'hiran', '8267267256', 'hiran@gg.cc', 'Kannur', 'India', 'kerala', 'Idukki', 'mobile', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', '0', 'user3', 'Kannur', null, null, '0', null, '0', 'News paper', 'user3', '2016-06-14 18:07:32', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kan_fadfe7897cb7', 'kanaka', '1215212514', 'kkk@nn.cc', 'Kannur', 'India', 'kerala', 'Thiruvananthapuram', 'chats', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'user3', 'Kannur', null, null, '0', null, '0', 'Method 3', 'user3', '2016-06-14 18:27:12', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kan_fd9a9a06b595', 'lisy', '9131513513', 'lisy@gg.cc', 'Kannur', 'India', 'kerala', 'Malappuram', 'orkut', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', '0', 'user3', 'Kannur', null, null, '0', null, '0', 'News paper', 'user3', '2016-06-15 11:40:27', '', '', null);
INSERT INTO `enquiry_details` VALUES ('koc_00c9d19f0ec4', 'vishnu', '5434534534', 'vishnu@vishnu.con', 'Kochi', 'India', 'kerala', 'Ernakulam', 'mobile', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', '0', 'sandy', 'Kochi', null, null, '1', null, '0', 'News paper', 'sandy', '2016-06-14 11:08:58', '', '', null);
INSERT INTO `enquiry_details` VALUES ('koc_1dd89e2611ea', 'anoop', '6565689898', 'anoop@anoop.anoop', 'Kochi', 'India', 'kerala', 'Idukki', 'phone', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', '0', 'sandy', 'Kochi', null, null, '0', null, '0', 'Phone', 'sandy', '2016-06-13 18:48:27', '', '', null);
INSERT INTO `enquiry_details` VALUES ('koc_228d6705c090', 'nikhil', '5768948975', 'nikhil@nikhil.org', 'Kochi', 'India', 'kerala', 'Alappuzha', 'Direct', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'sandy', 'Kochi', null, null, '0', null, '0', 'Direct', 'sandy', '2016-06-17 14:16:13', '', '', 'Evening Batch');
INSERT INTO `enquiry_details` VALUES ('koc_4b1623ba84a3', 'akhil', '8976544532', 'akhil@akhil.akhil', 'Kollam', 'India', 'kerala', 'Idukki', 'chats', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', '0', 'sandy', 'Kochi', null, null, '0', null, '0', 'Phone', 'sandy', '2016-06-17 15:02:10', '', '', null);
INSERT INTO `enquiry_details` VALUES ('koc_619929d9bc25', 'arjun', '2478989898', 'arjun@arjun.com', 'Kochi', 'India', 'kerala', 'Alappuzha', 'Direct', 'Not Assigned', null, '0', '0', '2016-06-17', '+91', '0', 'sandy', 'Kochi', null, null, '0', null, '0', 'Direct', 'sandy', '2016-06-17 14:29:33', '', '', 'Night batch');
INSERT INTO `enquiry_details` VALUES ('koc_78df79df877c', 'sumesh', '5675675346', 'sumesh@sumesh.com', 'Kannur', 'India', 'kerala', 'Alappuzha', 'facebook', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', null, 'sandy', 'Kochi', null, null, '0', null, '0', 'Phone', 'b', '2016-06-15 11:40:27', '', '', null);
INSERT INTO `enquiry_details` VALUES ('koc_8754df24a47b', 'arun', '9735438743', 'arun@arun.arun', 'Kochi', 'India', 'kerala', 'Kollam', 'messenger', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'sandy', 'Kochi', null, null, '0', null, '0', 'Direct', 'sandy', '2016-06-17 14:20:26', '', '', 'Morning Batch');
INSERT INTO `enquiry_details` VALUES ('koc_a7041eface62', 'tharun', '8546784786', 'tharun@tharun.org', 'Kochi', 'India', 'kerala', 'Thiruvananthapuram', 'Direct', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'sandy', 'Kochi', null, null, '0', null, '0', 'Direct', 'sandy', '2016-06-17 14:47:46', '', '', 'Night batch');
INSERT INTO `enquiry_details` VALUES ('koc_a71b02703901', 'biju', '6564334356', 'biju@biju.biju', 'Kochi', 'India', 'kerala', 'Ernakulam', 'Direct', 'sandeepskrishnan', null, '0', '0', '2016-06-17', '+91', '0', 'sandy', 'Kochi', null, null, '0', null, '0', 'Direct', 'sandy', '2016-06-17 13:52:12', '', '', 'Evening Batch');
INSERT INTO `enquiry_details` VALUES ('koc_b896ec0b0699', 'jithin', '5736547635', 'jithin@jithi.org', 'Kochi', 'India', 'kerala', 'kottayam', 'Direct', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'sandy', 'Kochi', null, null, '0', null, '0', 'Direct', 'sandy', '2016-06-17 14:47:26', '', '', 'Night batch');
INSERT INTO `enquiry_details` VALUES ('koc_b95c59f14d15', 'sanikrishna', '9874555445', 'sanikrisnan@hhf.com', 'Kochi', 'India', 'kerala', 'Alappuzha', 'facebook', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', null, 'sandy', 'Kochi', null, null, '0', null, '0', 'Phone', 'b', '2016-06-17 13:50:53', '', '', null);
INSERT INTO `enquiry_details` VALUES ('koc_cc1b4827b853', 'amal', '4654563546', 'amal@amal.com', 'Kochi', 'India', 'kerala', 'Alappuzha', 'phone', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'sandy', 'Kochi', null, null, '0', null, '0', 'Phone', 'sandy', '2016-06-17 14:47:06', '', '', null);
INSERT INTO `enquiry_details` VALUES ('koc_cdc8ad6f110f', 'shine', '5464564645', 'shine@shine.shine', 'Kochi', 'India', 'kerala', 'Alappuzha', 'mobile', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', '0', 'sandy', 'Kochi', null, null, '0', null, '0', 'Tv', 'sandy', '2016-06-14 12:50:37', '', '', null);
INSERT INTO `enquiry_details` VALUES ('koc_d82424c77dc1', 'antony', '6654336456', 'antony@antony.sss', 'Kochi', 'India', 'kerala', 'kottayam', 'mobile', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'sandy', 'Kochi', null, null, '1', null, '0', 'Phone', 'sandy', '2016-06-14 11:08:58', '', '', null);
INSERT INTO `enquiry_details` VALUES ('koc_dfca21935856', 'anil', '9783646573', 'anil@anil.con', 'Kollam', 'India', 'kerala', 'kottayam', 'phone', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'sandy', 'Kochi', null, null, '0', null, '0', 'Tv', 'sandy', '2016-06-17 14:47:57', '', '', null);
INSERT INTO `enquiry_details` VALUES ('koc_e454886ba276', 'abhijith', '7654564564', 'abhijith@abijth.com', 'Kochi', 'India', 'kerala', 'Kollam', 'facebook', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', '0', 'sandy', 'Kochi', null, null, '0', null, '0', 'Phone', 'sandy', '2016-06-13 19:04:47', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_008a6e0d3c74', 'geethu', '8978776785', 'geet@gmail.com', 'Kollam', 'India', 'kerala', 'Alappuzha', 'orkut', 'a', null, '0', '0', '2016-06-17', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Direct', 'b', '2016-06-17 12:55:23', '', '', 'Evening Batch');
INSERT INTO `enquiry_details` VALUES ('kol_05dddca31839', '5enq', '5345545435', 'cdf@yahoo.com', 'Kollam', 'India', 'kerala', 'Kollam', 'facebook', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Tv', 'b', '2016-06-17 12:08:29', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_0c1764954c6d', '1enq', '4553545454', '1enqffgg@gmail.com', 'Kollam', 'India', 'kerala', 'Alappuzha', 'phone', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', null, 'b', 'Kollam', null, null, '0', null, '0', 'Phone', 'b', '2016-06-16 17:22:51', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_178922ed2ba8', 'sreee', '4654654654', 'sr@gmail.com', 'Kollam', 'India', 'kerala', 'Ernakulam', 'Direct', 'sanoop', null, '0', '0', '2016-06-13', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Phone', 'b', '2016-06-17 13:43:30', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_17cf27990d9e', 'haritha', '7435345435', 'sumesh.tg@zoftsolutions.com', 'Kollam', 'India', 'kerala', 'kannur', 'facebook', 'Not Assigned', null, '1', '0', '2016-06-20', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Phone', 'b', '2016-06-20 15:39:08', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_1b03a7918ff0', '4enq', '5435434534', 'teytdfedfryutu@gmail.com', 'Kollam', 'India', 'kerala', 'Kollam', 'phone', 'a', null, '0', '0', '2016-06-17', '+91', '1', 'keerthykp', 'Kollam', null, null, '0', null, '1', 'Direct', 'keerthykp', '2016-06-17 15:42:00', '', '', 'Night batch');
INSERT INTO `enquiry_details` VALUES ('kol_2b3eb6eaddd3', 'anoop', '7234324732', 'sumesh.tg@zoftsolutions.com', 'Kollam', 'India', 'kerala', 'Idukki', 'facebook', 'Not Assigned', null, '1', '0', '2016-06-20', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Phone', 'b', '2016-06-22 20:08:15', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_2bd08b71d7ea', 'arun2', '7563423232', 'arun2@gmail.com', 'Kollam', 'India', 'kerala', 'Idukki', 'phone', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Phone', 'b', '2016-06-16 17:22:51', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_399d5eb36484', '3enq', '2343343545', 'rgrer@yahoo.com', 'Kollam', 'India', 'kerala', 'Kasaragod', 'mobile', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'News paper', 'b', '2016-06-17 12:08:29', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_428755f2c9f8', 'kanjana', '5465465465', 'UIUI', 'Kollam', 'India', 'kerala', 'Alappuzha', 'phone', 'Not Assigned', null, '0', '0', '2016-06-20', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Phone', 'b', '2016-06-23 10:40:47', '9879879879', '04864', null);
INSERT INTO `enquiry_details` VALUES ('kol_43c73f9a76f6', 'vinayaka', '3425564646', 'vinayaka@gmail.com', 'Kollam', 'India', 'kerala', 'Kasaragod', 'mobile', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'News paper', 'b', '2016-06-17 12:08:29', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_47a290eaa29d', 'sreerag', '7654756474', 'ss@gmail.com', 'Kollam', 'India', 'kerala', 'Idukki', 'mobile', 'sheela', null, '0', '0', '2016-06-20', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Direct', 'b', '2016-06-20 12:56:23', '', '', 'Evening Batch');
INSERT INTO `enquiry_details` VALUES ('kol_49c7d8d21eba', 'arun1', '5465655443', 'arun@gmail.com', 'Kochi', 'India', 'kerala', 'Alappuzha', 'mobile', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Phone', 'b', '2016-06-17 14:19:58', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_4d5f5f43ac8b', 'cccccccccc', '5453454343', 'nmbnhgjhgjjghgj@gmail.com', 'Kannur', 'India', 'kerala', 'Ernakulam', 'phone', 'jitha', null, '0', '0', '2016-06-13', '+91', null, 'b', 'Kollam', null, null, '0', null, '0', 'Phone', 'b', '2016-06-16 09:28:33', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_60c98adacc3e', '5enq', '4565646545', 'ewreretret@yahoo.com', 'Kollam', 'India', 'kerala', 'Ernakulam', 'mobile', 'Not Assigned', null, '0', '0', '2016-06-17', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Direct', 'b', '2016-06-17 14:26:17', '', '', 'Night batch');
INSERT INTO `enquiry_details` VALUES ('kol_63da30698bfa', 'arun 2', '5464575676', 'arun2@gmail.com', 'Kollam', 'India', 'kerala', 'Kasaragod', 'facebook', 'a', null, '0', '0', '2016-06-14', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Direct', 'b', '2016-06-14 14:16:37', '', '', 'Afternoon Batch');
INSERT INTO `enquiry_details` VALUES ('kol_66a39843f66c', 'kannan', '9832432434', 'ssds@rdsfd.hgjhg', 'Kollam', 'India', 'kerala', 'Idukki', 'phone', 'Not Assigned', null, '0', '0', '2016-06-20', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Tv', 'b', '2016-06-23 10:55:10', '65765', '04734', null);
INSERT INTO `enquiry_details` VALUES ('kol_66ebfd1a5540', 'sumesh', '8435435435', 'sume@gmail.com', 'Kollam', 'India', 'kerala', 'Idukki', 'messenger', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', null, 'b', 'Kollam', null, null, '0', null, '0', 'Tv', 'b', '2016-06-17 12:51:32', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_6fd78a762953', 'ganelia', '9345354354', 'gane@gmail.com', 'Kollam', 'India', 'kerala', 'Alappuzha', 'orkut', 'a', null, '0', '0', '2016-06-17', '+91', '1', 'b', 'Kollam', null, null, '0', null, '1', 'Direct', 'b', '2016-06-20 14:38:14', '', '', 'Evening Batch');
INSERT INTO `enquiry_details` VALUES ('kol_79f0550ce37c', '1enq', '3424553543', 'sss@ghg.com', 'Kollam', 'India', 'kerala', 'kannur', 'phone', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Phone', 'b', '2016-06-23 12:54:03', '9877876876', '04734', null);
INSERT INTO `enquiry_details` VALUES ('kol_7c38fd4e7311', 'eeeeeeeeee', '2354353535', 'eeeeeeeeee@gmail.com', 'Kollam', 'India', 'kerala', 'Alappuzha', 'facebook', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Phone', 'b', '2016-06-17 12:54:37', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_7ff348fe6b96', 'jagatheesh', '9923231231', 'dummyenquiry@gmail.com', 'Kollam', 'India', 'kerala', 'Idukki', 'mobile', 'Not Assigned', null, '0', '0', '2016-06-20', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Tv', 'b', '2016-06-20 15:39:08', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_87b16f6c6dae', 'ganesh', '8034534534', 'gana@gmail.com', 'Kollam', 'India', 'kerala', 'Idukki', 'mobile', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', null, 'b', 'Kollam', null, null, '0', null, '0', 'Tv', 'b', '2016-06-17 12:54:37', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_8cdccfd7fe58', '2enq', '3465564565', 'vffgjyj@gmail.com', 'Kollam', 'India', 'kerala', 'Idukki', 'facebook', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Method one', 'b', '2016-06-16 16:55:41', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_902ef03d9e76', 'arun7', '3242343432', 'arun7@gmail.com', 'Kollam', 'India', 'kerala', 'Ernakulam', 'phone', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Phone', 'b', '2016-06-17 15:24:28', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_91e547ed7bb1', '10enq', '3455434445', 'sddsfdffd@gmail.com', 'Kollam', 'India', 'kerala', 'Ernakulam', 'phone', 'a', null, '0', '0', '2016-06-17', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Direct', 'b', '2016-06-17 14:38:03', '', '', 'Night batch');
INSERT INTO `enquiry_details` VALUES ('kol_a7433dc10ad7', 'ssssssssss', '3432435545', 'ssssssss@yahoo.com', 'Kannur', 'India', 'kerala', 'kannur', 'mobile', 'jitha', null, '0', '0', '2016-06-13', '+91', null, 'b', 'Kollam', null, null, '0', null, '0', 'Tv', 'b', '2016-06-16 09:28:34', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_a8717f31260b', 'sheela 1', '3565676575', 'sheelaone@gmail.com', 'Kollam', 'India', 'kerala', 'kannur', 'facebook', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Direct', 'b', '2016-06-17 12:42:55', '', '', 'Morning Batch');
INSERT INTO `enquiry_details` VALUES ('kol_af76ca381901', 'test', '9989898989', 'test@test.test', 'Kochi', 'India', 'kerala', 'Ernakulam', 'phone', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Phone', 'b', '2016-06-14 12:50:37', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_b29f45a47919', '14enq', '6466768788', 'rewrerewr@gmail.com', 'Kollam', 'India', 'kerala', 'Idukki', 'phone', 'Not Assigned', null, '0', '0', '2016-06-17', '+91', '0', 'keerthykp', 'Kollam', null, null, '0', null, '0', 'Direct', 'keerthykp', '2016-06-17 14:45:37', '', '', 'Evening Batch');
INSERT INTO `enquiry_details` VALUES ('kol_b379890ae41d', 'vyshak k menon', '3242345545', 'fghte@yahoo.com', 'Kollam', 'India', 'kerala', 'Alappuzha', 'phone', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'News paper', 'b', '2016-06-16 16:57:21', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_b63d0fcd8aef', '1enq', '5464765767', 'eretytytryr@gmail.com', 'Kollam', 'India', 'kerala', 'Idukki', 'phone', 'Not Assigned', null, '0', '0', '2016-06-17', '+91', '1', 'keerthykp', 'Kollam', null, null, '0', null, '1', 'Direct', 'keerthykp', '2016-06-20 10:26:04', '', '', 'Morning Batch');
INSERT INTO `enquiry_details` VALUES ('kol_b672c8fe2b6b', 'appu', '9345354353', 'sumesh.tg@zoftsolutions.com', 'Kollam', 'India', 'kerala', 'Idukki', 'phone', 'Not Assigned', null, '0', '0', '2016-06-20', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'News paper', 'b', '2016-06-20 15:39:08', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_b89f7b63f70f', 'arun5', '4564676575', 'arun5@gmail.com', 'Kollam', 'India', 'kerala', 'Ernakulam', 'mobile', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Phone', 'b', '2016-06-17 12:08:29', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_b9749879089d', '4enq', '5654644646', 'dgghrthjhtr@yahoo.com', 'Kollam', 'India', 'kerala', 'kannur', 'orkut', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Tv', 'b', '2016-06-16 17:22:31', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_c913b2e4fa1d', '9enq', '2322122212', 'eetrt@gmail.com', 'Kollam', 'India', 'kerala', 'Ernakulam', 'phone', 'sheela', null, '0', '0', '2016-06-17', '+91', '0', 'keerthykp', 'Kollam', null, null, '0', null, '0', 'Direct', 'keerthykp', '2016-06-17 14:35:48', '', '', 'Night batch');
INSERT INTO `enquiry_details` VALUES ('kol_cddfe245a085', 'mohan', '5466564564', 'svdfbfbdgbf@gmail.com', 'Kochi', 'India', 'kerala', 'Alappuzha', 'mobile', 'sandeepskrishnan', null, '0', '0', '2016-06-15', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Direct', 'b', '2016-06-15 12:10:57', '', '', 'Night batch');
INSERT INTO `enquiry_details` VALUES ('kol_d0cc31695858', 'arun6', '2343355446', 'arun6@gmail.com', 'Kannur', 'India', 'kerala', 'kannur', 'facebook', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Tv', 'b', '2016-06-15 17:23:05', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_d14b4ae570dd', 'test2', '9456465465', '', 'Kollam', 'India', 'kerala', 'Idukki', 'mobile', 'Not Assigned', null, '0', '0', '2016-06-20', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'News paper', 'b', '2016-06-20 15:42:34', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_d33b0ffcb552', 'hari', '3422423423', 'hari@hari.com', 'Kochi', 'India', 'kerala', 'Alappuzha', 'phone', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', '0', 'b', 'Kollam', null, null, '1', null, '0', 'Phone', 'b', '2016-06-14 11:09:57', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_d435309754f4', 'suresh', '8089890980', 'sur@gmail.com', 'Kollam', 'India', 'kerala', 'Alappuzha', 'phone', 'sanoop', null, '0', '0', '2016-06-13', '+91', '1', 'b', 'Kollam', null, null, '0', null, '1', 'Phone', 'b', '2016-06-20 10:25:57', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_d7a4f085ca0f', '12enq', '1321312143', 'rtrete@gmail.com', 'Kollam', 'India', 'kerala', 'Alappuzha', 'Direct', 'a', null, '0', '0', '2016-06-17', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Direct', 'b', '2016-06-17 14:39:49', '', '', 'Night batch');
INSERT INTO `enquiry_details` VALUES ('kol_dcca7aeb8073', 'muhasssinn', '9766576576', 'sumesh.tg@zoftsolutions.com', 'Kollam', 'India', 'kerala', 'Idukki', 'mobile', 'Not Assigned', null, '0', '0', '2016-06-20', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'News paper', 'b', '2016-06-20 14:14:16', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_e6429f1a8bb0', '7enq', '5435534534', 'fghghh@yahoo.com', 'Kollam', 'India', 'kerala', 'Kasaragod', 'phone', 'a', null, '0', '0', '2016-06-17', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Direct', 'b', '2016-06-17 14:31:56', '', '', 'Morning Batch');
INSERT INTO `enquiry_details` VALUES ('kol_e77e9f96475b', 'muhassin', '6214512541', 'muhu@gg.cc', 'Kollam', 'India', 'kerala', 'Thrissur', 'mobile', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '1', 'b', 'Kollam', null, null, '0', null, '1', 'News paper', 'b', '2016-06-20 10:25:40', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_eb5db4536e5b', 'test1', '7689768678', 'test2@gmail.com', 'Kollam', 'India', 'Andhra Pradesh', 'Adilabad', 'messenger', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Direct', 'b', '2016-06-17 16:02:37', '', '', 'Night batch');
INSERT INTO `enquiry_details` VALUES ('kol_ebae93075120', 'arun4', '3423454654', 'arun4@gmail.com', 'Kollam', 'India', 'kerala', 'Kasaragod', 'facebook', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Tv', 'b', '2016-06-20 15:39:08', '', '', null);
INSERT INTO `enquiry_details` VALUES ('kol_f76f27840190', 'arun1', '3435355654', 'saassgfh@gmail.com', 'Kollam', 'India', 'kerala', 'Idukki', 'phone', 'Not Assigned', null, '0', '0', '2016-06-14', '+91', null, 'b', 'Kollam', null, null, '0', null, '0', 'Direct', 'b', '2016-06-17 12:08:29', '', '', 'Night batch');
INSERT INTO `enquiry_details` VALUES ('kol_f9cc84fdcc92', 'arun3', '1232323434', 'arun33@gmail.com', 'Kannur', 'India', 'kerala', 'Alappuzha', 'phone', 'sumeshzoft', null, '0', '0', '2016-06-14', '+91', null, 'b', 'Kollam', null, null, '0', null, null, 'Direct', 'b', '2016-06-14 17:41:12', '', '', 'Night batch');
INSERT INTO `enquiry_details` VALUES ('kol_fa4ce2149bb4', 'hai', '3244546546', 'retytjjk.ll@gmail.com', 'Kollam', 'India', 'Arunachal Pradesh', 'Changalang', 'Direct', 'sanoop', null, '0', '0', '2016-06-14', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Direct', 'b', '2016-06-14 17:49:34', '', '', 'Night batch');
INSERT INTO `enquiry_details` VALUES ('kol_fe7fcbb38341', '6enq', '2354546564', 'ganreterta@gmail.com', 'Kollam', 'India', 'kerala', 'Alappuzha', 'mobile', 'Not Assigned', null, '0', '0', '2016-06-13', '+91', '0', 'b', 'Kollam', null, null, '0', null, '0', 'Phone', 'b', '2016-06-17 14:22:21', '', '', null);

-- ----------------------------
-- Table structure for enquiry_personal_details_tbl
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_personal_details_tbl`;
CREATE TABLE `enquiry_personal_details_tbl` (
  `person_id` varchar(255) NOT NULL,
  `enquiry_id` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `marial_status` varchar(15) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `state` varchar(30) DEFAULT NULL,
  `district` varchar(30) DEFAULT NULL,
  `branch` varchar(30) DEFAULT NULL,
  `stdcode1` varchar(30) DEFAULT NULL,
  `stdcode2` varchar(30) DEFAULT NULL,
  `phone1` varchar(255) DEFAULT NULL,
  `phone2` varchar(255) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `passport` varchar(255) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`person_id`),
  UNIQUE KEY `enquiryId` (`enquiry_id`) USING BTREE,
  KEY `branchh_key` (`branch`) USING BTREE,
  CONSTRAINT `enquiry_personal_details_tbl_ibfk_1` FOREIGN KEY (`branch`) REFERENCES `enquiry_details` (`branch`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_personal_details_tbl
-- ----------------------------
INSERT INTO `enquiry_personal_details_tbl` VALUES ('PED0002f127b605', 'kol_66a39843f66c', '19', 'Male', 'Married', 'ssds@rdsfd.hgjhg', 'kerala', 'Idukki', 'Kollam', '+91', '04734', '9832432434', '65765', 'India', null, null, 'b', '2016-06-23 10:43:28', '2016-06-23 10:55:10', 'b', 'y');
INSERT INTO `enquiry_personal_details_tbl` VALUES ('PED08ee9241f5b5', 'kan_14132433f76b', '21', 'Female', 'Married', 'yyy@mm.cc', 'kerala', 'Kozhikode', 'Kannur', '+91', '+4535345', '8768678683', '3453453453', 'India', null, null, 'jitha', '2016-06-14 17:54:12', null, null, 'y');
INSERT INTO `enquiry_personal_details_tbl` VALUES ('PED2097f81d56da', 'kol_d14b4ae570dd', '20', 'Male', 'Divorced', '', 'kerala', 'Idukki', 'Kollam', '+91', '04734', '9456465465', '6575676576', 'India', null, null, 'b', '2016-06-23 10:08:29', null, null, 'y');
INSERT INTO `enquiry_personal_details_tbl` VALUES ('PED22a290b74dfa', 'kol_428755f2c9f8', '19', 'Male', 'Single', 'UIUI', 'kerala', 'Alappuzha', 'Kollam', '+91', '04864', '5465465465', '9879879879', 'India', null, null, 'b', '2016-06-23 10:24:56', '2016-06-23 10:41:01', 'b', 'y');
INSERT INTO `enquiry_personal_details_tbl` VALUES ('PED4df83b5b78eb', 'kol_b672c8fe2b6b', '19', 'Female', 'Married', '', 'kerala', 'Idukki', 'Kollam', '+91', '04864', '9345354353', '657657', 'India', null, null, 'b', '2016-06-23 10:52:04', null, null, 'y');
INSERT INTO `enquiry_personal_details_tbl` VALUES ('PED54e0ec0a33ad', 'kol_f9cc84fdcc92', '19', 'Male', 'Single', 'arun33@gmail.com', 'kerala', 'Alappuzha', 'Kannur', '+91', '+676', '1232323434', '4656464646', 'India', '2005-06-15', null, 'sumeshzoft', '2016-06-15 11:42:06', null, null, 'y');
INSERT INTO `enquiry_personal_details_tbl` VALUES ('PED612c602b9b6d', 'kan_d2d606026a15', '20', 'Female', 'Single', 'harith@hh.cc', 'kerala', 'Kollam', 'Kannur', '+91', '+4546756', '7878787887', '3223232323', 'India', null, null, 'jitha', '2016-06-14 17:56:33', null, null, 'y');
INSERT INTO `enquiry_personal_details_tbl` VALUES ('PED6eb659f6f992', 'kan_0999ea3e41ec', '19', 'Female', 'Single', 'swathy@ff.cc', 'kerala', 'kottayam', 'Kannur', '+91', '+233', '2215642156', '3442342424', 'India', '1997-06-14', null, 'admin', '2016-06-14 11:43:51', null, null, 'y');
INSERT INTO `enquiry_personal_details_tbl` VALUES ('PED73e0cbf66e64', 'kan_d8377f8e03ff', '20', 'Male', 'Married', 'saneesh@ss.cc', 'kerala', 'Thrissur', 'Kannur', '+91', '+3232423', '9232326323', '4234324234', 'India', null, null, 'jitha', '2016-06-14 17:54:45', null, null, 'y');
INSERT INTO `enquiry_personal_details_tbl` VALUES ('PED784f5cff99ee', 'koc_cc1b4827b853', '19', 'Male', 'Married', 'amal@amal.com', 'kerala', 'Alappuzha', 'Kollam', '+91', '222', '4654563546', '22222', 'India', '2016-06-14', null, 's', '2016-06-14 12:00:45', null, null, 'y');
INSERT INTO `enquiry_personal_details_tbl` VALUES ('PED7cf4b7defb7a', 'kol_d0cc31695858', '22', 'Male', 'Single', 'arun6@gmail.com', 'kerala', 'kannur', 'Kannur', '+91', '+9567567', '2343355446', '2342442424', 'India', null, null, 'jitha', '2016-06-14 17:55:27', null, null, 'y');
INSERT INTO `enquiry_personal_details_tbl` VALUES ('PEDa0be3e7d9d45', 'kol_79f0550ce37c', '20', 'Male', 'Married', 'sss@ghg.com', 'kerala', 'kannur', 'Kollam', '+91', '04734', '3424553543', '9877876876', 'India', null, null, 'b', '2016-06-23 12:53:37', '2016-06-23 12:54:22', 'b', 'y');
INSERT INTO `enquiry_personal_details_tbl` VALUES ('PEDac8f9fe38d25', 'kan_64d0ee4ec807', '24', 'Female', 'Single', 'unnimol@gg.cc', 'kerala', 'Malappuram', 'Kannur', '+91', '+5643434', '3235265426', '3434545445', 'India', null, null, 'jitha', '2016-06-14 17:55:59', null, null, 'y');
INSERT INTO `enquiry_personal_details_tbl` VALUES ('PEDb7337d000458', 'kan_0e2069aaa132', '18', 'Male', 'Single', 'jomon@vv.cc', 'kerala', 'Malappuram', 'Kannur', '+91', '+6757576', '4362242352', '5675756757', 'India', null, null, 'jitha', '2016-06-14 17:53:41', null, null, 'y');

-- ----------------------------
-- Table structure for enquiry_sources
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_sources`;
CREATE TABLE `enquiry_sources` (
  `enquiry_source_id` int(11) NOT NULL AUTO_INCREMENT,
  `source_name` varchar(255) DEFAULT NULL,
  `source_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`enquiry_source_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1016 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of enquiry_sources
-- ----------------------------
INSERT INTO `enquiry_sources` VALUES ('1001', 'Direct', null);
INSERT INTO `enquiry_sources` VALUES ('1002', 'phone', null);
INSERT INTO `enquiry_sources` VALUES ('1003', 'mobile', null);
INSERT INTO `enquiry_sources` VALUES ('1004', 'facebook', null);
INSERT INTO `enquiry_sources` VALUES ('1005', 'orkut', null);
INSERT INTO `enquiry_sources` VALUES ('1006', 'messenger', null);
INSERT INTO `enquiry_sources` VALUES ('1007', 'chats', null);
INSERT INTO `enquiry_sources` VALUES ('1008', 'Whatsapp', null);
INSERT INTO `enquiry_sources` VALUES ('1009', 'mobile', null);
INSERT INTO `enquiry_sources` VALUES ('1010', 'letter', null);
INSERT INTO `enquiry_sources` VALUES ('1011', 'news', null);
INSERT INTO `enquiry_sources` VALUES ('1012', 'news papper', null);
INSERT INTO `enquiry_sources` VALUES ('1013', 'message', null);
INSERT INTO `enquiry_sources` VALUES ('1014', 'post', null);
INSERT INTO `enquiry_sources` VALUES ('1015', 'walkin', 'default');

-- ----------------------------
-- Table structure for history_enquiry_assigning_tbl
-- ----------------------------
DROP TABLE IF EXISTS `history_enquiry_assigning_tbl`;
CREATE TABLE `history_enquiry_assigning_tbl` (
  `hid` varchar(255) NOT NULL,
  `assigned_by` varchar(255) DEFAULT NULL,
  `assigned_to` varchar(255) DEFAULT NULL,
  `assigned_branch` varchar(255) DEFAULT NULL,
  `assigned_date` timestamp NULL DEFAULT NULL,
  `enquiry_id` varchar(255) DEFAULT NULL,
  `holded_by` varchar(255) DEFAULT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  `current_status` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `study_required` varchar(255) DEFAULT NULL,
  `work_required` varchar(255) DEFAULT NULL,
  `training_required` varchar(255) DEFAULT NULL,
  `migration_required` varchar(255) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `latest_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`hid`),
  KEY `henq` (`enquiry_id`) USING BTREE,
  CONSTRAINT `history_enquiry_assigning_tbl_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of history_enquiry_assigning_tbl
-- ----------------------------
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_005a0006c267', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:41:50', 'koc_1dd89e2611ea', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-13 15:41:49', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_00655fbc9c6c', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 13:00:19', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-16 13:00:19', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_006d6517e9b8', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 11:06:49', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '7', 'not picking', '', '', '', '', 'sandeep', '2016-06-14 11:06:49', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_007c2938114f', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-15 17:21:57', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-15 17:21:57', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_00bc5bbf9794', 'user3', 'sumeshzoft', 'Kannur', null, 'kan_0999ea3e41ec', 'Not Assigned', '', '1', '', '', '', '', '', 'user3', '2016-06-15 16:24:01', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_010d22d5715b', 'sheela', 'a', 'Kollam', '2016-06-13 11:40:40', 'kol_d435309754f4', 'a', 'Registration Follow Up', '6', 'gfhg', '', '', '', '', 'sheela', '2016-06-13 11:40:40', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_011761da1c02', 'sandeep', 'Not Assigned', 'Kochi', '2016-06-13 18:47:40', 'kan_f89c4cc5fcd5', 's', 'Registration Follow Up', '1', '', '', '', '', '', 'sandeep', '2016-06-13 18:47:38', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0117aaf60c67', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:37:08', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 15:37:08', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_012a6aa74f7b', 'a', 'Not Assigned', 'Kollam', '2016-06-17 11:31:31', 'kol_87b16f6c6dae', 'sheela', 'Registration Follow Up', '9', 'okey i willl', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'a', '2016-06-17 11:31:31', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_013c95038cd5', 'user3', 'jitha', 'Kannur', null, 'kan_d14f72fc2e86', 'jitha', 'Assessment Pending', '2', '', 'Language Course-Spoken English', '', 'Spoken English', '', 'user3', '2016-06-15 17:37:47', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_013f448a4064', 'Self Assigned', 'jitha', 'Kannur', '2016-06-16 09:28:32', 'kan_cd5e74d67cd1', 'Not Assigned', 'null', '4', '', '', 'Chemical Engineer', '', '', 'jitha', '2016-06-16 09:28:32', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_014b4a006115', 'a', 'Not Assigned', 'Kollam', '2016-06-17 15:28:54', 'kol_e77e9f96475b', 'sheela', 'Registration Follow Up', '4', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-17 15:28:54', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_015f85d9fcaa', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:40:37', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-16 16:40:36', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_016d67b68cd0', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:47:15', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 12:47:15', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_02496ace22cc', 'user3', 'Not Assigned', 'Kannur', '2016-06-13 15:02:27', 'kan_d2d606026a15', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-13 15:02:26', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_02aa763c594b', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:56:24', 'kol_79f0550ce37c', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'a', '2016-06-16 16:56:23', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_02ea63df2ed1', 'jitha', 'Not Assigned', 'Kannur', '2016-06-14 12:08:57', 'koc_78df79df877c', 'sumeshzoft', 'Registration Follow Up', '2', '', 'Master-computer Application', '', '', '', 'jitha', '2016-06-14 12:08:58', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_03980bbece40', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:19:39', 'kol_902ef03d9e76', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 17:19:39', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_03c7bf1e60d3', 'user3', 'Not Assigned', 'Kannur', null, 'kan_60284b9c5c17', 'Not Assigned', 'Assessment Pending', '3', '', 'Language Course-Spoken English', '', '', '', 'user3', '2016-06-15 17:43:32', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_03e6a1238752', 'b', 'sandeepskrishnan', 'Kollam', '2016-06-15 12:10:57', 'kol_cddfe245a085', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-15 12:10:57', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_04459de40617', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 12:46:52', 'kan_f89c4cc5fcd5', 'Not Assigned', 'null', '1', '', 'Master-MA Malayalam', '', '', '', 'sandeep', '2016-06-14 12:46:52', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_044627272d34', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 15:36:31', 'kan_d2d606026a15', 'Not Assigned', 'null', '1', '', '', '', '', '', 'jitha', '2016-06-13 15:36:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0478ce3c0132', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 12:03:47', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '7', 'not picking', '', '', '', '', 's', '2016-06-14 12:03:47', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_04db0037df43', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-20 15:14:15', 'kol_b672c8fe2b6b', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'a', '2016-06-20 15:14:15', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_05a9061f5cf4', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 18:44:52', 'kol_d33b0ffcb552', 'Not Assigned', 'null', '1', 'not picking', '', '', '', '', 'sandeep', '2016-06-13 18:44:50', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_061158939277', 'user3', 'Not Assigned', 'Kannur', null, 'kan_e73e0db3ce2c', 'Not Assigned', 'Assessment Pending', '3', '', '', '', '', 'Architect', 'user3', '2016-06-15 16:29:30', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_062e355b8d00', 'b', 'Not Assigned', 'Kollam', '2016-06-20 14:20:29', 'kol_17cf27990d9e', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-20 14:20:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_062f6bffd85d', 'b', 'Not Assigned', 'Kollam', '2016-06-13 17:32:00', 'kol_a7433dc10ad7', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-13 17:31:58', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_065abe26197a', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:38:46', 'kan_fd9a9a06b595', 'Not Assigned', 'null', '4', '', 'Master-MTECH', '', '', '', 'sumeshzoft', '2016-06-14 17:38:46', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_06936676f6c0', 'a', 'Not Assigned', 'Kollam', '2016-06-17 11:28:09', 'kol_7c38fd4e7311', 'sheela', 'Registration Follow Up', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-17 11:28:09', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_06bee21ce5b8', 'sandy', 's', 'Kochi', '2016-06-17 13:49:09', 'koc_619929d9bc25', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'sandy', '2016-06-17 13:49:10', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_06ccc7786cb3', 'sheela', 'Not Assigned', 'Kollam', '2016-06-13 15:16:38', 'kol_178922ed2ba8', 'sheela', 'Assessment Pending', '6', 'hjk', '', '', '', '', 'sheela', '2016-06-13 13:16:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_06dc553a656f', 'user3', 'Not Assigned', 'Kannur', null, 'kan_d8377f8e03ff', 'Not Assigned', 'Assessment Pending', '4', '', '', '', '', 'Teacher', 'user3', '2016-06-15 16:26:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_083f3b25d9a1', 'sheela', 'sheela', 'Kollam', '2016-06-13 13:36:15', 'kol_66ebfd1a5540', 'sheela', 'Assessment Pending', '7', 'try', '', '', '', '', 'sheela', '2016-06-13 11:36:23', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0892e6cf985d', 'user3', 'Not Assigned', 'Kannur', null, 'kan_4f9cddfd4e5c', 'Not Assigned', 'Assessment Pending', '4', '', 'Bachelor-BSc cs', '', '', '', 'user3', '2016-06-14 15:38:28', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_08e7fb8c1fdd', 's', 'sandeep', 'Kochi', '2016-06-17 13:57:59', 'koc_619929d9bc25', 'sandeep', 'Registration Follow Up', '1', '', '', '', '', '', 's', '2016-06-17 13:57:59', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_097357755955', 'sheela', 'sheela', 'Kollam', '2016-06-13 15:35:41', 'kol_d435309754f4', 'sheela', 'Assessment Pending', '7', 'gfhg', '', '', '', '', 'sheela', '2016-06-13 12:35:47', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_09a71b260df1', 'b', 'Not Assigned', 'Kollam', '2016-06-13 14:27:49', 'kol_05dddca31839', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-13 14:27:48', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_09fa5b8114cd', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 18:38:42', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '6', 'not picking', '', '', '', '', 'sandeep', '2016-06-13 18:38:40', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_09fe6e4da6ba', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 15:15:15', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-14 15:15:15', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0a43c9ce6abe', 'user3', 'Not Assigned', 'Kannur', '2016-06-14 09:48:04', 'kan_3811aca90659', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-14 09:48:04', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0a6c22d47d47', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:45:48', 'kan_14132433f76b', 'Not Assigned', 'null', '1', '', '', '', '', 'Junior Engineer', 'jitha', '2016-06-14 17:45:48', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0ab700e5cfaf', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:37:53', 'kol_43c73f9a76f6', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'a', '2016-06-16 15:37:53', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0ad38d5db507', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:37:27', 'kol_79f0550ce37c', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'a', '2016-06-16 15:37:27', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0b20296e7805', 'a', 'Not Assigned', 'Kollam', '2016-06-17 11:01:50', 'kol_178922ed2ba8', 'sheela', 'Fee Payment Follow up', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-17 11:01:50', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0b237108926e', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 15:42:30', 'kan_a8087d458991', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'sumeshzoft', '2016-06-13 15:42:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0b279dcb0bd4', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:37:20', 'kol_0c1764954c6d', 'Not Assigned', 'null', '7', 't65465', '', '', '', '', 'a', '2016-06-16 15:37:20', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0b735c6b4f8b', 'Self Assigned', 'a', 'Kollam', '2016-06-13 15:05:01', 'kol_b9749879089d', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'a', '2016-06-13 15:05:00', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0bad822c8140', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:21:45', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 16:21:45', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0bb0d6a43571', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 17:01:05', 'kan_64d0ee4ec807', 'Not Assigned', 'null', '1', '', '', '', '', '', 'jitha', '2016-06-13 17:01:03', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0c3d942df7c8', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 18:07:35', 'koc_e454886ba276', 'Not Assigned', 'null', '1', 'call later', '', '', '', '', 'sandeep', '2016-06-13 18:07:33', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0c5dc011dcaf', 'Self Assigned', 'sheela', 'Kollam', '2016-06-13 12:59:24', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '6', 'fds', '', '', '', '', 'sheela', '2016-06-13 12:59:23', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0c85c868cb2d', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:40:25', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 16:40:25', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0c867074a9d7', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:06:20', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 15:06:20', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0d2de3200a53', 'sandy', 'Not Assigned', 'Kochi', '2016-06-14 11:00:11', 'koc_cc1b4827b853', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'sandy', '2016-06-14 11:00:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0d337c5e9150', 'a', 'Not Assigned', 'Kollam', '2016-06-17 12:26:15', 'kol_a8717f31260b', 'sheela', 'Registration Follow Up', '6', 'ret', '', '', 'Personality development', '', 'a', '2016-06-17 12:26:15', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0d41206aa46a', 'user3', 'jitha', 'Kannur', null, 'kan_f6a01e7d4e70', 'jitha', 'Assessment Pending', '2', '', '', 'Software Engineer', '', '', 'user3', '2016-06-15 17:36:37', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0d607c291103', 'a', 'a', 'Kollam', null, 'kol_b63d0fcd8aef', 'a', 'Assessment Pending', '4', '', '', '', 'IELTS', '', 'a', '2016-06-17 15:05:07', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0d6e5c38d557', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-15 17:12:43', 'kan_805877d725d7', 'Not Assigned', 'null', '1', '', '', '', '', 'Teacher', 'jitha', '2016-06-15 17:12:43', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0d73a6312b86', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 13:00:14', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 13:00:14', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0d75f85fa062', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:47:21', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 12:47:21', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0d950cb5481c', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:49:55', 'kol_05dddca31839', 'Not Assigned', 'null', '7', 'tyeryert', '', '', 'Personality development', '', 'a', '2016-06-16 16:49:55', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0dc8765741df', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-17 14:45:25', 'koc_cc1b4827b853', 'Not Assigned', 'null', '1', '', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-17 14:45:25', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0de3027fc5cf', 's', 'Not Assigned', 'Kochi', null, 'koc_4b1623ba84a3', 's', 'Assessment Pending', '2', '', '', '', '', '', 's', '2016-06-14 11:39:58', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0e8e87b4aeff', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 18:01:11', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', '', '', '', '', 'a', '2016-06-13 18:01:09', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0e93c33ce7ca', 'sandeep', 'Not Assigned', 'Kochi', '2016-06-17 14:46:38', 'koc_dfca21935856', 'a', 'Registration Follow Up', '4', '', 'Bachelor-B.COM', '', '', '', 'sandeep', '2016-06-17 14:46:39', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0eaf12bab546', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:58:56', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 16:58:56', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0eba99dfc05a', 'sheela', 'sheela', 'Kollam', '2016-06-13 15:34:10', 'kol_d435309754f4', 'sheela', 'Assessment Pending', '7', 'gfhg', '', '', '', '', 'sheela', '2016-06-13 12:34:17', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0ebcaf934172', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:18:49', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 12:18:49', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0edbaf6ee231', 'user3', 'jitha', 'Kannur', '2016-06-13 14:57:30', 'kan_f6a01e7d4e70', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-13 14:57:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0f14e0d12aee', 'a', 'Not Assigned', 'Kollam', '2016-06-17 09:59:55', 'kol_178922ed2ba8', 'sheela', 'Registration Follow Up', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-17 09:59:55', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0f18837c2710', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:11:08', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 16:11:08', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0f7d11a40c60', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:22:36', 'kol_902ef03d9e76', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 12:22:36', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0fc66a80ee0b', 'user3', 'jitha', 'Kannur', null, 'kan_f6a01e7d4e70', 'jitha', 'Assessment Pending', '2', '', '', 'Software Engineer', '', '', 'user3', '2016-06-15 17:44:55', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_0ff6903c0c2e', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 11:27:22', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-17 11:27:22', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_10061d6d4292', 'sheela', 'a', 'Kollam', '2016-06-17 15:28:16', 'kol_e77e9f96475b', 'a', 'Registration Follow Up', '4', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'sheela', '2016-06-17 15:28:16', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1030b54c6393', 'b', 'Not Assigned', 'Kollam', null, 'kol_17cf27990d9e', 'Not Assigned', 'Assessment Pending', '3', '', '', '', 'IELTS', '', 'b', '2016-06-20 14:21:26', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_107c58fe6cb1', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 16:53:16', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-14 16:53:16', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_10d116f8d617', 'Self Assigned', 'sheela', 'Kollam', '2016-06-17 12:16:55', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'okey i willl', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'sheela', '2016-06-17 12:16:55', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_11333586614c', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 16:33:33', 'kan_67aee97bdabe', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'sumeshzoft', '2016-06-13 16:33:32', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1156d7b4dad2', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:53:54', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-16 12:53:54', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_115b29afc13d', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 17:37:47', 'kol_fe7fcbb38341', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'a', '2016-06-13 17:37:45', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_11b4720c1ac5', 'Self Assigned', 'a', 'Kollam', '2016-06-20 14:41:14', 'kol_7ff348fe6b96', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'a', '2016-06-20 14:41:14', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_12039e5f3c74', 'b', 'Not Assigned', 'Kollam', '2016-06-14 13:57:57', 'kol_902ef03d9e76', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-14 13:57:57', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_12061306d7ed', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 18:17:11', 'kan_7d4957ffac9c', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'jitha', '2016-06-14 18:17:11', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1223339e9824', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:41:24', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 12:41:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_122b672713dc', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 17:48:41', 'kol_178922ed2ba8', 'Not Assigned', 'null', '6', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-13 17:48:39', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_12378d0317a9', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:21:20', 'kol_0c1764954c6d', 'Not Assigned', 'null', '7', 't65465', '', '', '', '', 'a', '2016-06-16 17:21:20', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1239b661a8b1', 'Self Assigned', 'sheela', 'Kollam', '2016-06-17 10:24:24', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'sheela', '2016-06-17 10:24:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_129baf1b5861', 'b', 'Not Assigned', 'Kollam', null, 'kol_e77e9f96475b', 'Not Assigned', 'Assessment Pending', '4', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'b', '2016-06-20 10:25:40', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_12bf6badcf5d', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:27:58', 'kan_e73e0db3ce2c', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sumeshzoft', '2016-06-14 17:27:59', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_12c4b3e85a20', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 17:29:34', 'kan_faa02d7d2026', 'Not Assigned', 'null', '6', 'aa', '', '', 'Spoken English', '', 'jitha', '2016-06-13 17:29:32', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_12d2059e30c0', 'Self Assigned', 'sumeshzoft', 'Kannur', '2016-06-14 17:35:04', 'kan_76371a02a618', 'Not Assigned', 'null', '1', '', 'Bachelor-BSc cs', '', '', '', 'sumeshzoft', '2016-06-14 17:35:04', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_12df251f730d', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 17:44:53', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-13 17:44:52', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_12f6bd44f52b', 'a', 'Not Assigned', 'Kollam', '2016-06-17 11:58:55', 'kol_66ebfd1a5540', 'sheela', 'Registration Follow Up', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-17 11:58:55', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_130a48d707af', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:42:27', 'koc_4b1623ba84a3', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:42:25', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1338e6651172', 'user3', 'Not Assigned', 'Kannur', '2016-06-13 15:07:28', 'kan_f89c4cc5fcd5', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-13 15:07:27', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_134e554ce5e5', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:21:35', 'kol_2bd08b71d7ea', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-16 17:21:35', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_13543a140809', 's', 'Not Assigned', 'Kochi', '2016-06-13 15:53:43', 'koc_4b1623ba84a3', 'sandeep', 'Registration Follow Up', '1', '', '', '', '', '', 's', '2016-06-13 15:53:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1354d5b4f10a', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:37:43', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 15:37:43', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_13a30fc5e9c6', 'Self Assigned', 'a', 'Kollam', '2016-06-16 11:37:56', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 11:37:56', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_13d780185978', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:56:55', 'kan_0999ea3e41ec', 'Not Assigned', 'null', '1', '', 'Bachelor-Btech', '', '', '', 'jitha', '2016-06-14 17:56:55', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_13f0d2430be7', 'sheela', 'Not Assigned', 'Kollam', '2016-06-17 12:09:02', 'kol_66ebfd1a5540', 'a', 'Registration Follow Up', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'sheela', '2016-06-17 12:09:02', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_146836b5a934', 'user3', 'jitha', 'Kannur', null, 'kan_d14f72fc2e86', 'jitha', 'Assessment Pending', '2', '', 'Language Course-Spoken English', '', 'Spoken English', '', 'user3', '2016-06-15 17:58:36', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_14a48ea1b464', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 12:13:05', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'sheela', '2016-06-17 12:13:05', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_14d09c12d624', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 15:59:18', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-14 15:59:18', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_14e5c6bd45e0', 'b', 'Not Assigned', 'Kollam', '2016-06-20 13:11:27', 'kol_b672c8fe2b6b', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-20 13:11:27', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_14e7a30b587d', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 18:04:46', 'koc_b95c59f14d15', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-13 18:04:45', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_14fcef1010ae', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 09:57:13', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'okey i willl', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'a', '2016-06-17 09:57:13', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_158170613b43', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 17:21:32', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-14 17:21:32', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1583f818fe24', 'a', 'Not Assigned', 'Kollam', '2016-06-17 12:25:42', 'kol_fe7fcbb38341', 'a', 'Assessment Pending', '6', 'rtret', 'PhD/Doctorate-Doctor of Psychology', '', 'Spoken English', '', 'a', '2016-06-17 12:25:50', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_15edabc9492c', 'b', 'sanoop', 'Kollam', '2016-06-14 17:49:34', 'kol_fa4ce2149bb4', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-14 17:49:34', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_16492c129b35', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 11:39:42', 'koc_b95c59f14d15', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-14 11:39:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_16561a441e24', 'Self Assigned', 'a', 'Kollam', '2016-06-17 10:37:53', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-17 10:37:53', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_16969958a817', 'Self Assigned', 's', 'Kochi', '2016-06-13 15:53:05', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '3', '', '', '', '', '', 's', '2016-06-13 15:53:03', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_16b4328749ff', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-20 15:17:02', 'kol_66a39843f66c', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'a', '2016-06-20 15:17:02', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_170f9da9d0d9', 'sheela', 'sheela', 'Kollam', '2016-06-13 12:19:13', 'kol_87b16f6c6dae', 'sheela', 'Assessment Pending', '6', 'fds', '', '', '', '', 'sheela', '2016-06-13 11:19:23', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_173c55fb2fad', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 15:33:55', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-14 15:33:55', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1792ed907a4c', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:06:14', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 15:06:14', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_17b09216d937', 's', 'Not Assigned', 'Kochi', '2016-06-13 17:33:27', 'koc_b95c59f14d15', 'jitha', 'Registration Follow Up', '1', '', '', '', '', '', 's', '2016-06-13 17:33:25', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_17be4c3c2c75', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 11:11:26', 'koc_a7041eface62', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-14 11:11:26', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_17bed088e9f6', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:41:36', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-16 12:41:36', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_185d44ae8706', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 18:42:14', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '6', 'not picking', '', '', '', '', 'sandeep', '2016-06-13 18:42:12', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_18a8441477dc', 'sandeep', 'sandeep', 'Kochi', null, 'kan_60284b9c5c17', 'sandeep', 'Assessment Pending', '4', 'call back', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-17 14:22:02', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_18b57472dfe6', 'user3', 'sumeshzoft', 'Kannur', '2016-06-14 15:19:47', 'kan_9f3f69f63db5', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-14 15:19:47', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_18c2091245e9', 'Self Assigned', 'sandeep', 'Kochi', '2016-06-17 13:56:56', 'koc_b896ec0b0699', 'Not Assigned', 'null', '3', '', 'Bachelor-BA History', '', '', '', 'sandeep', '2016-06-17 13:56:56', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_197ae8766b0d', 's', 'Not Assigned', 'Kochi', null, 'koc_78df79df877c', 's', 'Assessment Pending', '2', '', '', '', '', '', 's', '2016-06-13 15:51:40', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_19b60bc89c0d', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:19:18', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 17:19:18', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1a5f0b69495a', 'jitha', 'Not Assigned', 'Kannur', '2016-06-13 17:59:19', 'koc_b95c59f14d15', 'sandeep', 'Registration Follow Up', '1', '', '', '', '', '', 'jitha', '2016-06-13 17:59:18', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1acae2f8044f', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 09:57:43', 'koc_78df79df877c', 'Not Assigned', 'null', '2', '', '', '', '', '', 'jitha', '2016-06-14 09:57:43', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1aea03ea4f10', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:19:34', 'kol_fe7fcbb38341', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Spoken English', '', 'a', '2016-06-16 17:19:34', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1b0161f6db57', 'sheela', 'Not Assigned', 'Kollam', '2016-06-17 12:09:02', 'kol_7c38fd4e7311', 'a', 'Registration Follow Up', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'sheela', '2016-06-17 12:09:02', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1b0180a60c30', 'a', 'Not Assigned', 'Kollam', null, 'kol_7c38fd4e7311', 'a', 'Assessment Pending', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 11:40:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1b04de12e8d7', 'a', 'a', 'Kollam', null, 'kol_e77e9f96475b', 'a', 'Assessment Pending', '4', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-17 15:23:28', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1b5411e0fdbb', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-20 15:17:08', 'kol_2b3eb6eaddd3', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'a', '2016-06-20 15:17:08', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1bcb295021c8', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 18:15:43', 'kol_d33b0ffcb552', 'Not Assigned', 'null', '1', 'not picking', '', '', '', '', 'sandeep', '2016-06-13 18:15:41', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1c43eb475175', 'Self Assigned', 's', 'Kochi', '2016-06-13 16:17:17', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '3', '', '', '', '', '', 's', '2016-06-13 16:17:15', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1c534d65954c', 'a', 'a', 'Kollam', '2016-07-01 12:35:00', 'kol_a8717f31260b', 'a', 'Assessment Pending', '6', 'ret', '', '', 'Personality development', '', 'a', '2016-06-17 12:26:07', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1c59188feea6', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:54:14', 'kol_79f0550ce37c', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'a', '2016-06-16 16:54:14', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1cae9fd898d5', 's', 'Not Assigned', 'Kochi', '2016-06-14 16:17:48', 'koc_cdc8ad6f110f', 's', 'Assessment Pending', '6', 'not picking', '', '', '', '', 's', '2016-06-13 16:17:52', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1cb2d422e66e', 'Self Assigned', 'sanoop', 'Kollam', '2016-06-13 14:53:55', 'kol_0c1764954c6d', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sanoop', '2016-06-13 14:53:54', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1cec6c035bc1', 'sheela', 'sheela', 'Kollam', '2016-06-17 19:43:10', 'kol_b29f45a47919', 'sheela', 'Assessment Pending', '5', 'grtetrtrt', '', '', '', '', 'sheela', '2016-06-17 14:43:47', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1d39142e935f', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:17:20', 'koc_b95c59f14d15', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:17:18', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1d459a0bb008', 's', 'sandeep', 'Kochi', '2016-06-17 13:57:59', 'kan_60284b9c5c17', 'sandeep', 'Registration Follow Up', '3', '', 'Language Course-Spoken English', '', '', '', 's', '2016-06-17 13:57:59', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1dde8f5dad20', 'a', 'Not Assigned', 'Kollam', '2016-06-30 14:41:48', 'kol_7ff348fe6b96', 'a', 'Assessment Pending', '5', 'okey', '', '', 'IELTS', '', 'a', '2016-06-20 14:41:57', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1def490aea9d', 'b', 'Not Assigned', 'Kollam', '2016-06-13 19:00:04', 'kol_af76ca381901', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-13 19:00:02', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1dfc05bf9f83', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-20 15:22:23', 'kol_7ff348fe6b96', 'Not Assigned', 'null', '5', 'okey', '', '', 'IELTS', '', 'a', '2016-06-20 15:22:23', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1e00eb773a01', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:20:43', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'okey i willl', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'a', '2016-06-16 15:20:43', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1e195589090f', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:21:51', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-16 16:21:50', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1e2d1dbda210', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:21:09', 'kol_b9749879089d', 'Not Assigned', 'null', '1', '', '', 'Nurse', 'IELTS', '', 'a', '2016-06-16 17:21:09', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1e2d27722ed4', 's', 's', 'Kochi', null, 'koc_78df79df877c', 's', 'Assessment Pending', '4', '', '', '', '', '', 's', '2016-06-13 15:44:45', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1e5607a46263', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 18:17:12', 'kan_fadfe7897cb7', 'Not Assigned', 'null', '1', '', '', '', 'FRENCH', '', 'jitha', '2016-06-14 18:17:12', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1ea4570801a6', 'b', 'Not Assigned', 'Kollam', '2016-06-13 14:29:12', 'kol_fe7fcbb38341', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-13 14:29:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1ecfdc12fe08', 'Self Assigned', 's', 'Kochi', '2016-06-13 15:14:04', 'kan_f89c4cc5fcd5', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:14:03', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1ed495e36bfb', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 15:32:40', 'kan_f89c4cc5fcd5', 'Not Assigned', 'null', '2', '', 'Master-MA Malayalam', '', '', '', 'sheela', '2016-06-17 15:32:40', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1eef8681c5ca', 'Self Assigned', 'sheela', 'Kollam', '2016-06-17 10:24:30', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'sheela', '2016-06-17 10:24:30', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1f2f72ef7870', 'user14', 'Not Assigned', 'Kannur', '2016-06-14 16:30:03', 'kan_a2c0869fdcde', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user14', '2016-06-14 16:30:03', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1f6804dc39b7', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 11:28:02', 'kol_b379890ae41d', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'a', '2016-06-16 11:28:02', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1f8a1e85dfae', 'Self Assigned', 's', 'Kochi', '2016-06-13 18:58:48', 'kan_f89c4cc5fcd5', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 18:58:46', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1fddf90fd0c2', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-17 14:40:55', 'koc_b896ec0b0699', 'Not Assigned', 'null', '3', '', 'Bachelor-BA History', '', '', '', 'sandeep', '2016-06-17 14:40:55', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_1fe6128176c6', 'user3', 'sumeshzoft', 'Kannur', null, 'kan_0814cfa90746', 'Not Assigned', '', '1', '', '', '', '', '', 'user3', '2016-06-15 16:24:52', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_202d8cef7bba', 'a', 'Not Assigned', 'Kollam', '2016-06-13 19:04:06', 'kol_178922ed2ba8', 'a', 'Assessment Pending', '6', 'hjk', '', '', '', '', 'a', '2016-06-13 12:04:12', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_203555680955', 'Self Assigned', 'sanoop', 'Kollam', '2016-06-13 14:22:06', 'kol_d435309754f4', 'Not Assigned', 'null', '8', 'gfhg', '', '', '', '', 'sanoop', '2016-06-13 14:22:05', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_20aff122463b', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 17:21:26', 'kol_902ef03d9e76', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-14 17:21:26', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_20be161a6825', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 11:30:59', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'fds', '', '', '', '', 'a', '2016-06-14 11:30:59', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_20dcbda23c5f', 'Self Assigned', 'jitha', 'Kannur', '2016-06-14 12:08:48', 'koc_78df79df877c', 'Not Assigned', 'null', '2', '', 'Master-computer Application', '', '', '', 'jitha', '2016-06-14 12:08:48', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_20e26cca8cb8', 'a', 'Not Assigned', 'Kollam', '2016-06-16 16:36:11', 'kol_d435309754f4', 'a', 'Assessment Pending', '7', 'gfhg', '', '', '', '', 'a', '2016-06-13 12:36:15', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_212d79ba8bc8', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 15:52:11', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'fds', '', '', '', '', 'a', '2016-06-13 15:52:10', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_21a1a96d93c6', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 11:05:45', 'koc_00c9d19f0ec4', 'Not Assigned', 'null', '1', 'not picking', '', '', '', '', 'sandeep', '2016-06-14 11:05:45', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_21e0a5e81448', 'Self Assigned', 'sandeep', 'Kochi', '2016-06-17 14:45:52', 'kan_f89c4cc5fcd5', 'Not Assigned', 'null', '2', '', 'Master-MA Malayalam', '', '', '', 'sandeep', '2016-06-17 14:45:52', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_21ff52180110', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 15:42:13', 'kan_4f9cddfd4e5c', 'Not Assigned', 'null', '1', '', 'Bachelor-BSc cs', '', '', '', 'jitha', '2016-06-13 15:42:12', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_22d26552776a', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 16:59:29', 'kan_60284b9c5c17', 'Not Assigned', 'null', '2', '', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-14 16:59:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_22fa49b17811', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 10:39:08', 'kan_f89c4cc5fcd5', 'Not Assigned', 'null', '1', '', 'Master-MA Malayalam', '', '', '', 'sandeep', '2016-06-14 10:39:08', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2329d212480a', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:22:51', 'kan_a8087d458991', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'jitha', '2016-06-14 17:22:51', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_238cd535b961', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-15 12:45:49', 'kol_4d5f5f43ac8b', 'Not Assigned', 'null', '1', '', '', 'Architect', '', '', 'sumeshzoft', '2016-06-15 12:45:49', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_240f7c837e2d', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 12:19:32', 'kan_fd9a9a06b595', 'Not Assigned', 'null', '1', '', 'Master-MTECH', '', '', '', 'jitha', '2016-06-14 12:19:32', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_244f5783fcfb', 'sumeshzoft', 'Not Assigned', 'Kannur', '2016-06-23 17:42:23', 'kan_76371a02a618', 'sumeshzoft', 'Assessment Pending', '6', 'okey', 'Bachelor-BSc cs', '', '', '', 'sumeshzoft', '2016-06-14 17:36:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_248a6ef32518', 'sheela', 'Not Assigned', 'Kollam', '2016-06-17 10:26:03', 'kol_178922ed2ba8', 'a', 'Fee Payment Follow up', '1', 'hjk', 'Master-computer Application', '', '', '', 'sheela', '2016-06-17 10:26:03', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_24a9165e110d', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 16:16:23', 'kan_a8087d458991', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'sumeshzoft', '2016-06-13 16:16:21', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2527359adc53', 'user3', 'Not Assigned', 'Kannur', '2016-06-14 11:36:07', 'kan_d8377f8e03ff', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-14 11:36:07', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_25a2288891c6', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:49:06', 'kol_43c73f9a76f6', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'a', '2016-06-16 15:49:06', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_25dd654a80c5', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 20:07:42', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'fds', '', '', '', '', 'a', '2016-06-13 20:07:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_25ffdc882bb5', 'Self Assigned', 'a', 'Kollam', '2016-06-17 11:31:15', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'okey i willl', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'a', '2016-06-17 11:31:15', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_262856519a4b', 'Self Assigned', 'sheela', 'Kollam', '2016-06-13 11:03:06', 'kol_d435309754f4', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sheela', '2016-06-13 11:03:06', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2686dbbfd52e', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:50:15', 'kol_399d5eb36484', 'Not Assigned', 'null', '1', '', '', '', '', '', 'a', '2016-06-16 16:50:15', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_279e21992b56', 'user3', 'Not Assigned', 'Kannur', '2016-06-13 15:38:02', 'kan_67aee97bdabe', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-13 15:38:01', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_27a9d0203a5f', 'Self Assigned', 'a', 'Kollam', '2016-06-17 12:01:09', 'kol_05dddca31839', 'Not Assigned', 'null', '7', 'tyeryert', '', '', 'Personality development', '', 'a', '2016-06-17 12:01:09', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_27daca069ea6', 'keerthykp', 'sheela', 'Kollam', '2016-06-17 14:35:48', 'kol_c913b2e4fa1d', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'keerthykp', '2016-06-17 14:35:48', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_27ea2bfef31c', 'a', 'Not Assigned', 'Kollam', '2016-06-13 14:57:37', 'kol_399d5eb36484', 'sanoop', 'Fee Payment Follow up', '1', '', '', '', '', '', 'a', '2016-06-13 14:57:36', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_283ea3eda553', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 12:47:32', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '7', 'not picking', '', '', '', '', 'sandeep', '2016-06-14 12:47:32', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2841fb2e3e62', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:35:53', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 12:35:53', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2853cb00fa06', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:11:38', 'kol_b379890ae41d', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'a', '2016-06-16 16:11:37', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_289517440e3d', 'sandy', 'Not Assigned', 'Kochi', '2016-06-14 10:58:55', 'koc_d82424c77dc1', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'sandy', '2016-06-14 10:58:55', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_289696c7ab8d', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 14:35:33', 'kol_b9749879089d', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'a', '2016-06-13 14:35:32', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_28e44ebc1999', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 17:44:51', 'kol_178922ed2ba8', 'Not Assigned', 'null', '6', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-13 17:44:49', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_292e1d894556', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 18:38:13', 'koc_e454886ba276', 'Not Assigned', 'null', '1', 'call later', '', '', '', '', 'sandeep', '2016-06-13 18:38:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_296ddb3283b7', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:15:11', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 12:15:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2a1ed5d990f1', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 18:43:16', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 18:43:16', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2a41caa71df0', 'sandeep', 'Not Assigned', 'Kochi', '2016-06-14 10:35:40', 'kan_f89c4cc5fcd5', 's', 'Registration Follow Up', '1', '', 'Master-MA Malayalam', '', '', '', 'sandeep', '2016-06-14 10:35:40', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2b1265243a70', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 14:15:25', 'kol_fe7fcbb38341', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Spoken English', '', 'a', '2016-06-14 14:15:25', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2b44b8f0044c', 'a', 'a', 'Kollam', null, 'kol_7c38fd4e7311', 'a', 'Assessment Pending', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 11:38:25', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2b5b6ee2b6e6', 'sandy', 'Not Assigned', 'Kochi', '2016-06-13 14:46:06', 'koc_b95c59f14d15', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'sandy', '2016-06-13 14:46:05', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2b973fad2ab3', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:41:42', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 12:41:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2beab7003437', 'sheela', 'sheela', 'Kollam', '2016-06-17 13:35:46', 'kol_d435309754f4', 'sheela', 'Assessment Pending', '7', 'gfhg', '', '', '', '', 'sheela', '2016-06-13 11:35:53', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2c089c69064a', 'sandy', 'Not Assigned', 'Kochi', '2016-06-14 10:00:00', 'koc_228d6705c090', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'sandy', '2016-06-14 10:00:00', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2d04fe060f45', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-20 14:49:31', 'kol_2b3eb6eaddd3', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'a', '2016-06-20 14:49:31', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2d44541df7a6', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 16:06:03', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-14 16:06:03', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2d665c0a3ebf', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:44:06', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 17:44:06', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2d83b6397257', 'a', 'Not Assigned', 'Kollam', '2016-06-17 12:43:12', 'kol_ebae93075120', 'sheela', 'Registration Follow Up', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-17 12:43:12', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2dbbf96a4a53', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 09:46:49', 'koc_4b1623ba84a3', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-14 09:46:50', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2e19c15efa8f', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 10:45:44', 'kan_7d4957ffac9c', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'jitha', '2016-06-14 10:45:44', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2e1ea9843026', 'sheela', 'sheela', 'Kollam', '2016-06-13 14:39:23', 'kol_d435309754f4', 'sheela', 'Assessment Pending', '6', 'gfhg', '', '', '', '', 'sheela', '2016-06-13 11:39:30', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2e3bb7648b03', 'a', 'Not Assigned', 'Kollam', '2016-06-17 11:01:21', 'kol_7c38fd4e7311', 'sheela', 'Registration Follow Up', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-17 11:01:21', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2e6ad859c796', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 16:22:52', 'kan_a8087d458991', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'sumeshzoft', '2016-06-13 16:22:50', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2e787df1fc0e', 's', 's', 'Kochi', '2016-06-13 18:17:24', 'koc_cdc8ad6f110f', 's', 'Assessment Pending', '6', 'not picking', '', '', '', '', 's', '2016-06-13 16:17:37', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2f032ac235c2', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 11:42:08', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-14 11:42:08', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2f34601ccf21', 's', 'sandeep', 'Kochi', '2016-06-17 13:58:00', 'koc_b896ec0b0699', 'sandeep', 'Registration Follow Up', '3', '', 'Bachelor-BA History', '', '', '', 's', '2016-06-17 13:58:00', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_2f82011f2dd6', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:40:30', 'koc_00c9d19f0ec4', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:40:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3039283d4322', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:56:30', 'kol_b9749879089d', 'Not Assigned', 'null', '1', '', '', 'Nurse', 'IELTS', '', 'a', '2016-06-16 16:56:30', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_311459cffd30', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 11:00:29', 'kan_f89c4cc5fcd5', 'Not Assigned', 'null', '1', '', 'Master-MA Malayalam', '', '', '', 's', '2016-06-14 11:00:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_31f55c75cc42', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 17:01:06', 'kan_805877d725d7', 'Not Assigned', 'null', '1', '', '', '', '', '', 'jitha', '2016-06-13 17:01:05', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_320d9d5641ed', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:21:39', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 16:21:39', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_32dac4addc05', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 14:23:20', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-14 14:23:20', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_33378bfea287', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 10:46:46', 'kan_f89c4cc5fcd5', 'Not Assigned', 'null', '1', '', 'Master-MA Malayalam', '', '', '', 's', '2016-06-14 10:46:46', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_33853a5ccaef', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 18:38:11', 'kol_d33b0ffcb552', 'Not Assigned', 'null', '1', 'not picking', '', '', '', '', 'sandeep', '2016-06-13 18:38:09', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_33c18bf4d50f', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-15 11:35:21', 'kan_fd9a9a06b595', 'Not Assigned', 'null', '4', '', 'Master-MTECH', '', '', '', 'sumeshzoft', '2016-06-15 11:35:21', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3445b7e49cc2', 'a', 'Not Assigned', 'Kollam', '2016-06-17 11:59:49', 'kol_f76f27840190', 'sheela', 'Registration Follow Up', '1', '', '', '', 'Personality development', '', 'a', '2016-06-17 11:59:50', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_34b3def21054', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 15:11:27', 'kan_4f9cddfd4e5c', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sumeshzoft', '2016-06-13 15:11:26', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3516ca9ce1dd', 'sheela', 'a', 'Kollam', '2016-06-17 12:25:04', 'kol_a8717f31260b', 'a', 'Registration Follow Up', '1', '', '', '', 'Personality development', '', 'sheela', '2016-06-17 12:25:04', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3577de9a7576', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 15:36:29', 'kan_805877d725d7', 'Not Assigned', 'null', '1', '', '', '', '', '', 'jitha', '2016-06-13 15:36:28', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_358cd853ac36', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 16:53:22', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'fds', '', '', '', '', 'a', '2016-06-14 16:53:22', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_35c704375722', 'b', 'Not Assigned', 'Kollam', '2016-06-20 13:10:14', 'kol_7ff348fe6b96', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-20 13:10:14', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_362011fd3798', 'sheela', 'sheela', 'Kollam', '2016-06-17 20:49:54', 'kol_60c98adacc3e', 'sheela', 'Assessment Pending', '6', 'fegergerge', '', '', 'Personality development', '', 'sheela', '2016-06-17 14:24:25', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_363438ba198e', 'Self Assigned', 's', 'Kochi', '2016-06-17 14:18:28', 'koc_8754df24a47b', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 's', '2016-06-17 14:18:28', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_364939713354', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 16:10:45', 'kan_64d0ee4ec807', 'Not Assigned', 'null', '1', '', '', '', '', '', 'jitha', '2016-06-13 16:10:44', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3663e45ef945', 'b', 'Not Assigned', 'Kollam', '2016-06-13 17:42:06', 'kol_7c38fd4e7311', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-13 17:42:04', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_36f0866293c6', 'b', 'sheela', 'Kollam', '2016-06-17 14:23:23', 'kol_60c98adacc3e', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-17 14:23:23', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_373da80be759', 's', 'Not Assigned', 'Kochi', '2016-06-13 15:53:43', 'koc_b95c59f14d15', 'sandeep', 'Registration Follow Up', '1', '', '', '', '', '', 's', '2016-06-13 15:53:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3777a7b993bb', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 10:45:52', 'kan_fadfe7897cb7', 'Not Assigned', 'null', '1', '', '', '', 'FRENCH', '', 'jitha', '2016-06-14 10:45:52', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_37787d3a3a22', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 15:32:33', 'kol_eb5db4536e5b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'sheela', '2016-06-17 15:32:33', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_379a0500e16e', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:53:15', 'kol_902ef03d9e76', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 14:53:15', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_37ace573be2a', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:37:06', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'okey i willl', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'a', '2016-06-16 14:37:06', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_37f93ff29ebb', 'jitha', 'Not Assigned', 'Kannur', '2016-06-13 17:59:19', 'koc_cdc8ad6f110f', 'sandeep', 'Registration Follow Up', '6', 'not picking', '', '', '', '', 'jitha', '2016-06-13 17:59:18', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_37fda4a79c80', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:21:30', 'kol_b89f7b63f70f', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-16 17:21:30', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_38362233e91b', 'b', 'Not Assigned', 'Kollam', '2016-06-14 13:57:16', 'kol_d0cc31695858', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-14 13:57:16', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_384d0cda6777', 'user3', 'Not Assigned', 'Kannur', '2016-06-14 15:18:57', 'kan_14132433f76b', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-14 15:18:57', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_384dab047dc4', 'jitha', 'sumeshzoft', 'Kannur', '2016-06-13 17:10:29', 'kan_67aee97bdabe', 'sumeshzoft', 'Registration Follow Up', '1', '', '', '', 'Personality development', '', 'jitha', '2016-06-13 17:10:27', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_388fb791e633', 'sheela', 'Not Assigned', 'Kollam', '2016-06-17 14:44:11', 'kol_b29f45a47919', 'a', 'Fee Payment Follow up', '5', 'grtetrtrt', '', '', '', '', 'sheela', '2016-06-17 14:44:11', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_389aa223b779', 'b', 'Not Assigned', 'Kollam', '2016-06-20 14:14:16', 'kol_dcca7aeb8073', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-20 14:14:16', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_38eb723dfddd', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:28:41', 'koc_4b1623ba84a3', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-13 15:28:40', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_396369b3c40a', 'Self Assigned', 'a', 'Kollam', '2016-06-17 12:02:22', 'kol_43c73f9a76f6', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'a', '2016-06-17 12:02:22', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_39ce2f184c47', 'a', 'Not Assigned', 'Kollam', '2016-06-17 11:59:19', 'kol_ebae93075120', 'sheela', 'Registration Follow Up', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-17 11:59:19', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_39f89fb3c49c', 'Self Assigned', 'sheela', 'Kollam', '2016-06-17 12:08:29', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'sheela', '2016-06-17 12:08:28', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3ad4fee63553', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 17:31:34', 'kol_178922ed2ba8', 'Not Assigned', 'null', '6', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-13 17:31:32', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3b197b4e04fd', 'sandeep', 'Not Assigned', 'Kochi', '2016-06-17 14:13:39', 'koc_8754df24a47b', 's', 'Registration Follow Up', '1', '', '', '', 'IELTS', '', 'sandeep', '2016-06-17 14:13:39', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3b64d99a46ff', 'sheela', 'Not Assigned', 'Kollam', '2016-06-13 13:07:50', 'kol_87b16f6c6dae', 'sheela', 'Assessment Pending', '9', 'fds', '', '', '', '', 'sheela', '2016-06-13 13:01:03', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3b7ec569f4a6', 'user3', 'Not Assigned', 'Kannur', null, 'kan_805877d725d7', 'Not Assigned', 'Assessment Pending', '2', '', '', '', '', 'Teacher', 'user3', '2016-06-15 17:46:32', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3bdc2a2d87d4', 'Self Assigned', 'a', 'Kollam', '2016-06-13 12:58:57', 'kol_d435309754f4', 'Not Assigned', 'null', '7', 'gfhg', '', '', '', '', 'a', '2016-06-13 12:58:57', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3be4750eba54', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 13:15:22', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', '', '', '', '', 'sanoop', '2016-06-13 13:15:21', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3bfdcf07ccef', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:06:04', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-16 15:06:04', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3c4ff6d04a1d', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 12:47:26', 'koc_b896ec0b0699', 'Not Assigned', 'null', '3', '', '', '', '', '', 'sandeep', '2016-06-14 12:47:26', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3c606f60b022', 'user3', 'Not Assigned', 'Kannur', null, 'kan_e73e0db3ce2c', 'Not Assigned', 'Assessment Pending', '2', '', '', '', '', 'Architect', 'user3', '2016-06-15 17:45:13', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3d35afb34877', 'b', 'Not Assigned', 'Kollam', null, 'kol_6fd78a762953', 'Not Assigned', 'Assessment Pending', '4', '', '', '', '', '', 'b', '2016-06-20 10:26:07', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3d9656cca272', 'jitha', 'jitha', 'Kannur', null, 'kan_cd5e74d67cd1', 'jitha', 'Assessment Pending', '2', '', '', 'Chemical Engineer', '', '', 'jitha', '2016-06-16 09:28:50', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3ddfaec6db03', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:50:10', 'kol_2bd08b71d7ea', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-16 16:50:10', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3de675de747a', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 14:35:32', 'kol_399d5eb36484', 'Not Assigned', 'null', '1', '', '', '', '', '', 'a', '2016-06-13 14:35:31', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3e0660d3026d', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 15:47:55', 'kan_64d0ee4ec807', 'Not Assigned', 'null', '1', '', '', '', '', '', 'jitha', '2016-06-13 15:47:53', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3ec2719040d8', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:37:03', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 15:37:03', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3f28f66c604d', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:38:43', 'kan_0999ea3e41ec', 'Not Assigned', 'null', '1', '', 'Bachelor-Btech', '', '', '', 'sumeshzoft', '2016-06-14 17:38:43', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3f3c23b4d2e7', 'user3', 'Not Assigned', 'Kannur', '2016-06-13 15:01:31', 'kan_faa02d7d2026', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-13 15:01:30', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3f9552765400', 'Self Assigned', 'a', 'Kollam', '2016-06-17 11:57:57', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-17 11:57:57', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3fc98b390e1f', 'b', 'Not Assigned', 'Kollam', '2016-06-30 14:16:25', 'kol_dcca7aeb8073', 'Not Assigned', 'Assessment Pending', '6', 'hgj', '', '', 'IELTS', '', 'b', '2016-06-20 14:16:32', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3fe69d54200f', 'sheela', 'sheela', 'Kollam', '2016-06-13 14:33:09', 'kol_d435309754f4', 'sheela', 'Assessment Pending', '5', 'gfhg', '', '', '', '', 'sheela', '2016-06-13 11:33:18', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_3ff46f5665a8', 'Self Assigned', 'a', 'Kollam', '2016-06-17 12:01:04', 'kol_b89f7b63f70f', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-17 12:01:04', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4033a0e82a5a', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:21:41', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:21:40', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_407edc595092', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:38:44', 'kan_a2c0869fdcde', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sumeshzoft', '2016-06-14 17:38:44', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_40ad874890d8', 'Self Assigned', 'a', 'Kollam', '2016-06-17 13:42:12', 'kol_fe7fcbb38341', 'Not Assigned', 'null', '6', 'rtret', 'PhD/Doctorate-Doctor of Psychology', '', 'Spoken English', '', 'a', '2016-06-17 13:42:12', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_40de3f6ae478', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 12:47:41', 'koc_dfca21935856', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-14 12:47:41', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4144ff7de99e', 'sheela', 'Not Assigned', 'Kollam', '2016-06-17 12:13:30', 'kol_178922ed2ba8', 'a', 'Registration Follow Up', '1', 'hjk', 'Master-computer Application', '', '', '', 'sheela', '2016-06-17 12:13:30', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4186d1b740ef', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 18:46:25', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '6', 'not picking', '', '', '', '', 'sandeep', '2016-06-13 18:46:23', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_427400dc6151', 'Self Assigned', 'sandeep', 'Kochi', '2016-06-17 13:56:45', 'koc_619929d9bc25', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-17 13:56:45', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_42898269c2dd', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:05:52', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 15:05:52', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_42f26f677f63', 'b', 'a', 'Kollam', '2016-06-17 14:39:49', 'kol_d7a4f085ca0f', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-17 14:39:49', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_42fffc73f640', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 10:42:58', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-17 10:42:58', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4356a506afc7', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 18:20:56', 'koc_b95c59f14d15', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-13 18:20:54', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_43afd2331ffd', 'sheela', 'Not Assigned', 'Kollam', '2016-06-17 12:52:05', 'kol_fe7fcbb38341', 'a', 'Registration Follow Up', '6', 'rtret', 'PhD/Doctorate-Doctor of Psychology', '', 'Spoken English', '', 'sheela', '2016-06-17 12:52:05', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_43e48970626d', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:41:30', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 12:41:30', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_43eac46a30ef', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 10:46:00', 'kan_f89c4cc5fcd5', 'Not Assigned', 'null', '1', '', 'Master-MA Malayalam', '', '', '', 's', '2016-06-14 10:46:00', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_444391a30783', 'user3', 'Not Assigned', 'Kannur', null, 'kan_60284b9c5c17', 'Not Assigned', 'Assessment Pending', '2', '', 'Language Course-Spoken English', '', '', '', 'user3', '2016-06-14 15:39:07', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_445c0145c98b', 'Self Assigned', 'sandeep', 'Kochi', '2016-06-17 13:56:59', 'koc_cc1b4827b853', 'Not Assigned', 'null', '1', '', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-17 13:57:00', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_44bfd41c58fa', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 17:48:42', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', '', '', '', '', 'a', '2016-06-13 17:48:40', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_44cf93512052', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-20 15:08:18', 'kol_7ff348fe6b96', 'Not Assigned', 'null', '5', 'okey', '', '', 'IELTS', '', 'a', '2016-06-20 15:08:18', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_44e8ee81b711', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:37:18', 'kol_fe7fcbb38341', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Spoken English', '', 'a', '2016-06-16 14:37:18', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_45484fd268e4', 'sheela', 'sheela', 'Kollam', null, 'kol_b63d0fcd8aef', 'sheela', 'Assessment Pending', '4', '', '', '', 'IELTS', '', 'sheela', '2016-06-17 15:33:33', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_45b87f077aa4', 'b', 'a', 'Kollam', '2016-06-14 14:16:37', 'kol_63da30698bfa', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-14 14:16:37', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_460372072504', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:45:49', 'kan_d8377f8e03ff', 'Not Assigned', 'null', '1', '', '', '', '', 'Teacher', 'jitha', '2016-06-14 17:45:49', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_460e988c2887', 'a', 'sheela', 'Kollam', '2016-06-13 11:28:02', 'kol_87b16f6c6dae', 'sheela', 'Fee Payment Follow up', '6', 'fds', '', '', '', '', 'a', '2016-06-13 11:28:02', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4610c7f28385', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 15:46:21', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-14 15:46:21', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4625cc81d983', 'Self Assigned', 'sheela', 'Kollam', '2016-06-17 10:20:23', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'sheela', '2016-06-17 10:20:23', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_464762723d93', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:08:41', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 12:08:41', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4655084478e1', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 09:46:44', 'kol_af76ca381901', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-14 09:46:44', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_469b08cfe156', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 13:37:56', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-17 13:37:56', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_46bd0a0f7330', 'user3', 'Not Assigned', 'Kannur', null, 'kan_cd5e74d67cd1', 'Not Assigned', 'Assessment Pending', '4', '', '', 'Chemical Engineer', '', '', 'user3', '2016-06-15 17:44:19', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_47bacbfd9a67', 'user14', 'jitha', 'Kannur', '2016-06-14 11:10:18', 'kan_0999ea3e41ec', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user14', '2016-06-14 11:10:18', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_47c7c08c038e', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:27:51', 'kan_805877d725d7', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sumeshzoft', '2016-06-14 17:27:51', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_47d1cb5f2c79', 'b', 'Not Assigned', 'Kollam', '2016-06-20 15:43:15', 'kol_428755f2c9f8', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-20 15:43:15', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_47faaea4b5d8', 'Self Assigned', 's', 'Kochi', '2016-06-13 17:37:44', 'koc_00c9d19f0ec4', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 17:37:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4831187e155f', 'a', 'Not Assigned', 'Kollam', '2016-06-17 11:31:31', 'kol_178922ed2ba8', 'sheela', 'Registration Follow Up', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-17 11:31:32', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_485ae99178ca', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 11:42:03', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-14 11:42:03', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_48852efbe267', 'Self Assigned', 'a', 'Kollam', '2016-06-17 10:42:52', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-17 10:42:52', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_48b34baa5bfe', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 12:11:26', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-14 12:11:26', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_48bdc93c6775', 'sandy', 'Not Assigned', 'Kochi', '2016-06-14 10:44:52', 'koc_8754df24a47b', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'sandy', '2016-06-14 10:44:52', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_494f49601379', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:59:02', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 16:59:02', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4a34d9848d38', 'a', 'Not Assigned', 'Kollam', '2016-06-17 10:38:17', 'kol_7c38fd4e7311', 'sheela', 'Registration Follow Up', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-17 10:38:17', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4a4547f18c4f', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:27:48', 'kan_14132433f76b', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sumeshzoft', '2016-06-14 17:27:48', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4a56f8bca0bb', 'user3', 'Not Assigned', 'Kannur', '2016-06-13 15:05:39', 'kan_0814cfa90746', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-13 15:05:38', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4a5ec2da2d81', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:22:19', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'okey i willl', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'a', '2016-06-16 16:22:19', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4a8b8d03ac55', 's', 's', 'Kochi', '2016-06-13 19:38:07', 'koc_e454886ba276', 's', 'Assessment Pending', '7', 'call later', '', '', '', '', 's', '2016-06-13 17:38:19', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4ad724212464', 'Self Assigned', 'sheela', 'Kollam', '2016-06-17 10:20:28', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'sheela', '2016-06-17 10:20:28', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4b1affdc511d', 'Self Assigned', 'a', 'Kollam', '2016-06-17 12:42:19', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-17 12:42:19', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4b1e441de0d0', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:44:35', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'okey i willl', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'a', '2016-06-16 17:44:35', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4b28056def7d', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:53:36', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 12:53:36', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4b6ec6f9b6fc', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 18:17:13', 'kol_d0cc31695858', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', 'Doctor/Surgen', 'jitha', '2016-06-14 18:17:13', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4b825afbb0f0', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-15 11:35:13', 'kan_0999ea3e41ec', 'Not Assigned', 'null', '1', '', 'Bachelor-Btech', '', '', '', 'sumeshzoft', '2016-06-15 11:35:13', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4b838052d71b', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:38:28', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-13 15:38:27', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4b95657bb768', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:27:49', 'kan_d8377f8e03ff', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sumeshzoft', '2016-06-14 17:27:49', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4bb06d8c1e27', 'b', 'a', 'Kollam', '2016-06-17 12:55:23', 'kol_008a6e0d3c74', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-17 12:55:23', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4bc0fd66c3d9', 'b', 'Not Assigned', 'Kollam', '2016-06-14 13:56:36', 'kol_b89f7b63f70f', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-14 13:56:36', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4c1af38ba8bb', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:44:42', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 16:44:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4c63bdf66fa4', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 18:38:59', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-16 18:38:59', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4c9e8d2318ee', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 11:11:28', 'koc_b896ec0b0699', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-14 11:11:28', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4ca89f37f382', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-15 11:35:30', 'kan_cd5e74d67cd1', 'Not Assigned', 'null', '1', '', '', 'Chemical Engineer', '', '', 'sumeshzoft', '2016-06-15 11:35:30', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4cbae78cb79b', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 10:19:59', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-17 10:19:59', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4ccdff4af7b0', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 16:18:10', 'koc_b95c59f14d15', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 16:18:08', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4cf54ee93a68', 's', 'sandeepskrishnan', 'Kochi', '2016-06-17 13:52:12', 'koc_a71b02703901', 'sandeepskrishnan', 'Fee Payment Follow up', '1', '', '', '', '', '', 's', '2016-06-17 13:52:12', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4d2c673a4df1', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:40:15', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 16:40:14', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4d333ed9ea39', 'a', 'a', 'Kollam', '2016-06-13 12:17:30', 'kol_87b16f6c6dae', 'a', 'Assessment Pending', '6', 'fds', '', '', '', '', 'a', '2016-06-13 11:17:41', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4da25dd2e0da', 'Self Assigned', 'sandeep', 'Kochi', '2016-06-14 12:36:24', 'koc_b95c59f14d15', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-14 12:36:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4ddd409edaba', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 12:36:16', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'fds', '', '', '', '', 'a', '2016-06-14 12:36:16', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4dde13dc1aba', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 18:12:32', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '6', 'not picking', '', '', '', '', 'sandeep', '2016-06-13 18:12:30', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4e207a55a129', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:40:32', 'kol_d33b0ffcb552', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:40:30', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4e67a5ba420b', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-15 18:26:44', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-15 18:26:44', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4ee9b8b36070', 'b', 'Not Assigned', 'Kollam', null, 'kol_b63d0fcd8aef', 'Not Assigned', 'Assessment Pending', '4', '', '', '', 'IELTS', '', 'b', '2016-06-20 10:26:04', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4f63ce24f3ef', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:28:46', 'koc_b95c59f14d15', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-13 15:28:45', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4f904c868397', 'sandeep', 'Not Assigned', 'Kochi', '2016-06-17 15:31:44', 'kan_60284b9c5c17', 'sandeep', 'Assessment Pending', '5', 'kghjkfghj', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-17 14:31:55', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_4f9462fca007', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 18:17:18', 'kol_a7433dc10ad7', 'Not Assigned', 'null', '1', '', '', 'Doctor/Surgen', 'Personality development', '', 'jitha', '2016-06-14 18:17:18', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5001e83f3620', 'b', 'Not Assigned', 'Kollam', '2016-06-14 12:34:11', 'kol_49c7d8d21eba', 'enquiry', 'Assessment Pending', 'Assessment Pending', 'rutuytryrtyr', '', '', '', '', 'b', '2016-06-14 12:34:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5011e7e29869', 'sandeep', 'sandeep', 'Kochi', null, 'koc_b95c59f14d15', 'sandeep', 'Assessment Pending', '2', '', '', '', '', '', 'sandeep', '2016-06-14 12:40:47', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5046db5f2da1', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:47:01', 'kol_902ef03d9e76', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 14:47:00', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_507469171184', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 12:17:06', 'kol_902ef03d9e76', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'sheela', '2016-06-17 12:17:06', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_50cd8e99a95e', 'sheela', 'sheela', 'Kollam', '2016-06-30 16:00:31', 'kol_87b16f6c6dae', 'sheela', 'Assessment Pending', '9', 'fds', '', '', '', '', 'sheela', '2016-06-13 13:00:36', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_51336acd597f', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:45:54', 'kan_e73e0db3ce2c', 'Not Assigned', 'null', '1', '', '', '', '', 'Architect', 'jitha', '2016-06-14 17:45:55', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5157c990b697', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:44:12', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 17:44:12', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5175252a89b2', 'Self Assigned', 'sanoop', 'Kollam', '2016-06-13 14:45:38', 'kol_05dddca31839', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'sanoop', '2016-06-13 14:45:37', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_51783dbd1f92', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 16:18:08', 'koc_78df79df877c', 'Not Assigned', 'null', '2', '', '', '', '', '', 's', '2016-06-13 16:18:07', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_51a39bb67e72', 'user3', 'Not Assigned', 'Kannur', null, 'kan_f89c4cc5fcd5', 'Not Assigned', 'Assessment Pending', '2', '', 'Master-MA Malayalam', '', '', '', 'user3', '2016-06-14 15:38:34', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_51fb508ec66a', 's', 'Not Assigned', 'Kochi', '2016-06-13 15:53:43', 'koc_78df79df877c', 'sandeep', 'Registration Follow Up', '2', '', '', '', '', '', 's', '2016-06-13 15:53:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_52192b5e7e94', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:44:07', 'kol_902ef03d9e76', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 16:44:06', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_523d126bd505', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 17:37:44', 'kol_178922ed2ba8', 'Not Assigned', 'null', '6', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-13 17:37:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_524ca016855d', 'Self Assigned', 'sheela', 'Kollam', '2016-06-17 15:32:50', 'kol_b63d0fcd8aef', 'Not Assigned', 'null', '4', '', '', '', 'IELTS', '', 'sheela', '2016-06-17 15:32:50', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_52a0a9aca3f4', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-20 15:22:44', 'kol_17cf27990d9e', 'Not Assigned', 'null', '3', '', 'Master-computer Application', '', 'IELTS', '', 'a', '2016-06-20 15:22:44', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_52bb5efcdb8f', 'b', 'a', 'Kollam', '2016-06-17 12:56:12', 'kol_6fd78a762953', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-17 12:56:12', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_535e07428812', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-17 14:31:27', 'koc_b896ec0b0699', 'Not Assigned', 'null', '3', '', 'Bachelor-BA History', '', '', '', 'sandeep', '2016-06-17 14:31:27', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_53704e3a7cf6', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:39:07', 'kan_cd5e74d67cd1', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sumeshzoft', '2016-06-14 17:39:07', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_53793da042ae', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-15 11:35:14', 'kan_76371a02a618', 'Not Assigned', 'null', '6', 'okey', 'Bachelor-BSc cs', '', '', '', 'sumeshzoft', '2016-06-15 11:35:14', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_53cc1a919ec0', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 11:42:19', 'kol_fe7fcbb38341', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'a', '2016-06-14 11:42:19', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_542cf4465674', 'sandy', 'Not Assigned', 'Kochi', '2016-06-14 10:47:52', 'koc_b896ec0b0699', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'sandy', '2016-06-14 10:47:52', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5441672fda80', 's', 'Not Assigned', 'Kochi', '2016-06-14 16:51:13', 'koc_cdc8ad6f110f', 's', 'Assessment Pending', '6', 'not picking', '', '', '', '', 's', '2016-06-13 16:51:18', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_54be0c61402a', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:48:55', 'kol_b379890ae41d', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'a', '2016-06-16 15:48:55', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_54d52472613c', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 11:05:50', 'koc_8754df24a47b', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'sandeep', '2016-06-14 11:05:50', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_54f9e584ff8b', 'b', 'a', 'Kollam', '2016-06-17 14:38:03', 'kol_91e547ed7bb1', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-17 14:38:03', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5563f5c87c13', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 11:31:12', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 11:31:12', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_558ebb266721', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:28:04', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 17:28:04', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_55f530507a44', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 11:05:14', 'koc_b896ec0b0699', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-14 11:05:14', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5626dbf82ebe', 'Self Assigned', 's', 'Kochi', '2016-06-13 17:37:41', 'kol_d33b0ffcb552', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 17:37:39', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5643382d44f4', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:05:58', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 15:05:58', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_56a23f0a24f4', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 12:47:24', 'koc_a7041eface62', 'Not Assigned', 'null', '1', '', 'Bachelor-BSc cs', '', '', '', 'sandeep', '2016-06-14 12:47:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_56f96cb21e7a', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 14:40:48', 'kol_79f0550ce37c', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'sanoop', '2016-06-13 14:40:47', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_570a91d7f2c0', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 18:45:17', 'koc_1dd89e2611ea', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-13 18:45:16', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_572e3f53cc8f', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:48:43', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 15:48:43', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_572ffefbaac6', 's', 'Not Assigned', 'Kochi', null, 'kol_d33b0ffcb552', 's', 'Assessment Pending', '1', 'not picking', '', '', '', '', 's', '2016-06-13 17:39:02', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_574c9e01604b', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:11:14', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 16:11:14', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_575509477210', 'sanoop', 'Not Assigned', 'Kollam', '2016-06-13 14:47:18', 'kol_05dddca31839', 'a', 'Ticketing Follow up', '5', 'tyeryert', '', '', 'Personality development', '', 'sanoop', '2016-06-13 14:47:17', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_57b6ca109216', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 15:42:31', 'kan_faa02d7d2026', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'sumeshzoft', '2016-06-13 15:42:30', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_57b9fd35c0f8', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:37:14', 'kol_05dddca31839', 'Not Assigned', 'null', '7', 'tyeryert', '', '', 'Personality development', '', 'a', '2016-06-16 15:37:14', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_57e3ceb26014', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:50:00', 'kol_b89f7b63f70f', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-16 16:50:00', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_57f258c4d4dd', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:57:20', 'kan_faa02d7d2026', 'Not Assigned', 'null', '6', 'aa', '', '', 'Spoken English', '', 'jitha', '2016-06-14 17:57:20', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_58875363dd04', 'sandeep', 'Not Assigned', 'Kochi', '2016-07-01 14:31:30', 'koc_619929d9bc25', 'sandeep', 'Assessment Pending', '7', 'fgh', '', '', '', '', 'sandeep', '2016-06-17 14:23:37', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_588d2a6d24d2', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 12:11:19', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-14 12:11:19', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_58a26c8ac459', 'Self Assigned', 's', 'Kochi', '2016-06-13 16:51:05', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '6', 'not picking', '', '', '', '', 's', '2016-06-13 16:51:03', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5905afed0f89', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 17:01:09', 'kan_e73e0db3ce2c', 'Not Assigned', 'null', '1', '', '', '', '', '', 'jitha', '2016-06-13 17:01:07', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5976d3696ffb', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 10:49:28', 'koc_4b1623ba84a3', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-14 10:49:28', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5a0ef966a4c9', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 10:59:44', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-17 10:59:44', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5a176e99ecc7', 'a', 'a', 'Kollam', '2016-06-13 21:41:22', 'kol_d435309754f4', 'a', 'Assessment Pending', '8', 'gfhg', '', '', '', '', 'a', '2016-06-13 11:41:28', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5a55ff370064', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 16:39:12', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', '', '', '', '', 'a', '2016-06-13 16:39:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5ab8735d59d4', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:24:44', 'koc_e454886ba276', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:24:43', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5aca4b76bab3', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-17 14:18:11', 'kol_49c7d8d21eba', 'Not Assigned', 'null', '1', 'rutuytryrtyr', '', '', 'Spoken English', '', 'sandeep', '2016-06-17 14:18:11', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5ae92a5b6d5f', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 10:20:05', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-17 10:20:05', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5b0649330f91', 'admin', 'Not Assigned', 'Kollam', '2016-06-13 17:15:21', 'koc_78df79df877c', 'sumeshzoft', 'Registration Follow Up', '2', '', '', '', '', '', 'admin', '2016-06-13 17:15:19', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5b72a6712bee', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:15:16', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 17:15:16', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5b985095fed2', 'sandeep', 'sandeep', 'Kochi', null, 'koc_b95c59f14d15', 'sandeep', 'Assessment Pending', '4', '', '', '', '', '', 'sandeep', '2016-06-14 12:42:04', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5bad6809faca', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 16:16:22', 'kan_67aee97bdabe', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'sumeshzoft', '2016-06-13 16:16:20', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5bba533adacb', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 16:18:06', 'koc_4b1623ba84a3', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 16:18:05', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5bdeccf596f3', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 13:00:24', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 13:00:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5c382c09e8be', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 17:12:12', 'kan_4f9cddfd4e5c', 'Not Assigned', 'null', '1', '', 'Bachelor-BSc cs', '', '', '', 'sumeshzoft', '2016-06-13 17:12:10', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5c5943184b30', 'sheela', 'a', 'Kollam', '2016-06-13 11:14:11', 'kol_87b16f6c6dae', 'a', 'Registration Follow Up', '1', '', '', '', '', '', 'sheela', '2016-06-13 11:14:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5c991bd88290', 'Self Assigned', 'a', 'Kollam', '2016-06-20 15:22:34', 'kol_2b3eb6eaddd3', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'a', '2016-06-20 15:22:34', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5cc055e885fd', 'sandy', 'Not Assigned', 'Kochi', '2016-06-13 15:11:35', 'koc_78df79df877c', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'sandy', '2016-06-13 15:11:34', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5ce921144109', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 17:09:31', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '6', 'not picking', '', '', '', '', 's', '2016-06-13 17:09:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5cec8364d3ba', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:27:47', 'kan_0e2069aaa132', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sumeshzoft', '2016-06-14 17:27:47', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5cf7c8f21764', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 18:03:04', 'koc_4b1623ba84a3', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-13 18:03:02', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5d32f031ce72', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 12:47:55', 'koc_228d6705c090', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-14 12:47:55', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5d6bb1b4d2ed', 'b', 'sumeshzoft', 'Kollam', '2016-06-14 13:06:38', 'kol_f9cc84fdcc92', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-14 13:06:38', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5d9db398f4af', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-20 14:50:44', 'kol_b672c8fe2b6b', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'a', '2016-06-20 14:50:45', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5e0132fe9e6a', 's', 's', 'Kochi', '2016-06-13 19:38:23', 'kol_d33b0ffcb552', 's', 'Assessment Pending', '6', 'not picking', '', '', '', '', 's', '2016-06-13 17:38:34', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5e45c0bfdf48', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 11:31:17', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 11:31:17', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5e5522c3b192', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 14:48:28', 'kol_178922ed2ba8', 'Not Assigned', 'null', '6', 'hjk', '', '', '', '', 'a', '2016-06-13 14:48:27', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5e5973c07842', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:19:49', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 15:19:49', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5e72de10740e', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 15:34:01', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-14 15:34:01', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5ebf57b8431d', 'sheela', 'a', 'Kollam', '2016-06-13 12:36:02', 'kol_d435309754f4', 'a', 'Registration Follow Up', '7', 'gfhg', '', '', '', '', 'sheela', '2016-06-13 12:36:01', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5f6176efc3cb', 'Self Assigned', 'a', 'Kollam', '2016-06-17 12:42:24', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-17 12:42:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5f9213e2c8c8', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-20 15:22:39', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-20 15:22:39', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5fd30ee3c142', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 14:00:46', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-14 14:00:46', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_5fdd148000c0', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:21:57', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 15:21:57', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6025a93480e2', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 19:08:46', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-14 19:08:46', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_61cd3cb2f93f', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:44:40', 'kol_fe7fcbb38341', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Spoken English', '', 'a', '2016-06-16 17:44:40', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_621e8c97450d', 'user3', 'Not Assigned', 'Kannur', null, 'kan_cd5e74d67cd1', 'Not Assigned', 'Assessment Pending', '4', '', '', 'Chemical Engineer', '', '', 'user3', '2016-06-15 17:36:26', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_62373c97859c', 'Self Assigned', 'jitha', 'Kannur', '2016-06-13 17:29:37', 'kan_67aee97bdabe', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'jitha', '2016-06-13 17:29:35', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_62a5b729ab1b', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-17 14:03:35', 'koc_b896ec0b0699', 'Not Assigned', 'null', '3', '', 'Bachelor-BA History', '', '', '', 'sandeep', '2016-06-17 14:03:35', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_62fe91df0c9c', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 18:28:06', 'koc_e454886ba276', 'Not Assigned', 'null', '1', 'call later', '', '', '', '', 'sandeep', '2016-06-13 18:28:04', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6309524f5e4d', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:22:03', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 15:22:03', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_63405447c9c2', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-17 14:09:55', 'koc_b896ec0b0699', 'Not Assigned', 'null', '3', '', 'Bachelor-BA History', '', '', '', 'sandeep', '2016-06-17 14:09:55', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_639606c188f9', 'Self Assigned', 'sheela', 'Kollam', '2016-06-13 11:13:46', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sheela', '2016-06-13 11:13:46', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_63a621e1e77c', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 19:05:37', 'kan_f89c4cc5fcd5', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-13 19:05:35', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6406a7281b5a', 'sheela', 'Not Assigned', 'Kollam', '2016-06-17 12:09:17', 'kol_ebae93075120', 'a', 'Registration Follow Up', '1', '', 'Master-MA English', '', 'Personality development', '', 'sheela', '2016-06-17 12:09:17', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_645ad83856e7', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-17 14:40:53', 'koc_a7041eface62', 'Not Assigned', 'null', '1', '', 'Bachelor-BSc cs', '', '', '', 'sandeep', '2016-06-17 14:40:53', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_64fa556e920d', 'sandy', 'Not Assigned', 'Kochi', '2016-06-13 15:08:26', 'koc_cdc8ad6f110f', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'sandy', '2016-06-13 15:08:25', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_65212dcd9385', 'user3', 'Not Assigned', 'Kannur', '2016-06-14 09:49:21', 'kan_fadfe7897cb7', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-14 09:49:21', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_658287106917', 'Self Assigned', 'a', 'Kollam', '2016-06-17 10:59:33', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-17 10:59:33', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_65b8f0ab9d5d', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:13:43', 'koc_78df79df877c', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:13:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_660618591d2d', 's', 'Not Assigned', 'Kochi', null, 'koc_cdc8ad6f110f', 's', 'Assessment Pending', '3', '', '', '', '', '', 's', '2016-06-13 15:44:34', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6685a1487059', 'Self Assigned', 'a', 'Kollam', '2016-06-20 14:38:14', 'kol_6fd78a762953', 'Not Assigned', 'null', '4', '', '', '', '', '', 'a', '2016-06-20 14:38:14', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_668990974a64', 'a', 'Not Assigned', 'Kollam', '2016-06-17 15:05:14', 'kol_b63d0fcd8aef', 'sheela', 'Fee Payment Follow up', '4', '', '', '', 'IELTS', '', 'a', '2016-06-17 15:05:14', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_66a52d2ddb9f', 'Self Assigned', 'a', 'Kollam', '2016-06-17 11:00:59', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-17 11:00:59', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_66a974acde61', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 09:42:39', 'kan_60284b9c5c17', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-14 09:42:39', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_66b4fe689cc7', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 11:05:39', 'koc_d82424c77dc1', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-14 11:05:39', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_66c24dde011a', 'sandeep', 'sandeep', 'Kochi', null, 'koc_619929d9bc25', 'sandeep', 'Assessment Pending', '3', '', '', '', '', '', 'sandeep', '2016-06-17 14:22:30', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6701b21ad988', 'Self Assigned', 'a', 'Kollam', '2016-06-17 11:31:09', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-17 11:31:09', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_67a797715274', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 16:46:07', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-14 16:46:07', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_67a7bec65095', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 17:01:07', 'kan_d2d606026a15', 'Not Assigned', 'null', '1', '', '', '', '', '', 'jitha', '2016-06-13 17:01:06', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_67b071095bec', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 18:38:38', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'okey i willl', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'a', '2016-06-16 18:38:38', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_67bcd399d416', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:15:23', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 17:15:23', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_67ef508b94e8', 'jitha', 'Not Assigned', 'Kannur', '2016-06-14 11:13:33', 'kan_0999ea3e41ec', 'sumeshzoft', 'Registration Follow Up', '1', '', '', '', '', '', 'jitha', '2016-06-14 11:13:33', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_684a0fdc4b9a', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 18:28:04', 'kol_d33b0ffcb552', 'Not Assigned', 'null', '1', 'not picking', '', '', '', '', 'sandeep', '2016-06-13 18:28:02', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6857757fc225', 'a', 'a', 'Kollam', null, 'kol_e77e9f96475b', 'a', 'Assessment Pending', '4', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-17 15:28:37', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_68cb4757f1e1', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 14:30:23', 'kol_0c1764954c6d', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sanoop', '2016-06-13 14:30:23', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6901acef0c07', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 11:41:38', 'kan_60284b9c5c17', 'Not Assigned', 'null', '1', '', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-14 11:41:38', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6916e6a105fd', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:23:01', 'kol_a7433dc10ad7', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'jitha', '2016-06-14 17:23:01', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6922f2d8571e', 'a', 'sheela', 'Kollam', '2016-06-13 13:03:05', 'kol_d435309754f4', 'sheela', 'Registration Follow Up', '8', 'gfhg', '', '', '', '', 'a', '2016-06-13 13:03:04', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_69450559b00c', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:23:07', 'kan_0999ea3e41ec', 'Not Assigned', 'null', '1', '', 'Bachelor-Btech', '', '', '', 'jitha', '2016-06-14 17:23:07', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_694aa700ae53', 'a', 'Not Assigned', 'Kollam', '2016-06-17 12:43:12', 'kol_66ebfd1a5540', 'sheela', 'Registration Follow Up', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-17 12:43:12', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6957aafe9d52', 'sandeep', 's', 'Kochi', '2016-06-17 13:57:24', 'koc_619929d9bc25', 's', 'Registration Follow Up', '1', '', '', '', '', '', 'sandeep', '2016-06-17 13:57:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6975806f7448', 'a', 'a', 'Kollam', null, 'koc_4b1623ba84a3', 'a', 'Assessment Pending', '4', '', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-17 14:47:20', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_69c934796ab6', 'Self Assigned', 'sandeep', 'Kochi', '2016-06-14 12:31:25', 'koc_b896ec0b0699', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-14 12:31:25', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6a5c400eeb1a', 'a', 'Not Assigned', 'Kollam', '2016-06-17 11:00:03', 'kol_66ebfd1a5540', 'sheela', 'Registration Follow Up', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-17 11:00:03', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6a7aaaac677f', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 13:06:47', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 13:06:47', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6a7b164f964d', 'b', 'Not Assigned', 'Kollam', '2016-06-13 15:36:37', 'kol_d33b0ffcb552', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-13 15:36:36', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6b5486395262', 'a', 'Not Assigned', 'Kollam', '2016-06-17 10:34:00', 'kol_66ebfd1a5540', 'sheela', 'Registration Follow Up', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-17 10:34:00', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6b83dba35783', 'sheela', 'Not Assigned', 'Kollam', '2016-06-17 15:00:37', 'koc_4b1623ba84a3', 'a', 'Registration Follow Up', '4', '', 'Bachelor-BSc cs', '', '', '', 'sheela', '2016-06-17 15:00:37', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6bdc921a8c28', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 16:10:46', 'kan_805877d725d7', 'Not Assigned', 'null', '1', '', '', '', '', '', 'jitha', '2016-06-13 16:10:45', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6bed41ef0795', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:57:11', 'kan_76371a02a618', 'Not Assigned', 'null', '6', 'okey', 'Bachelor-BSc cs', '', '', '', 'jitha', '2016-06-14 17:57:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6c0acbd944e8', 'sumeshzoft', 'Not Assigned', 'Kannur', '2016-06-13 17:10:56', 'kan_a8087d458991', 'jitha', 'Registration Follow Up', '1', '', '', '', 'Personality development', '', 'sumeshzoft', '2016-06-13 17:10:54', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6c16ccbdd509', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 15:52:18', 'kol_fe7fcbb38341', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'a', '2016-06-13 15:52:17', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6c33ef357b88', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-17 14:09:35', 'koc_dfca21935856', 'Not Assigned', 'null', '1', '', 'Bachelor-B.COM', '', '', '', 'sandeep', '2016-06-17 14:09:35', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6c6051e77c0a', 'sandeep', 'sandeep', 'Kochi', '2016-06-17 14:33:42', 'kan_60284b9c5c17', 'sandeep', 'Assessment Pending', '6', 'call back', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-17 14:20:59', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6cca8589e19a', 's', 's', 'Kochi', '2016-06-13 18:37:50', 'koc_00c9d19f0ec4', 's', 'Assessment Pending', '6', 'not picking', '', '', '', '', 's', '2016-06-13 17:38:01', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6cf69d340a87', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:46:49', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 14:46:49', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6d582e41872e', 'Self Assigned', 's', 'Kochi', '2016-06-14 11:39:44', 'koc_4b1623ba84a3', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-14 11:39:44', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6d75ec3c3dba', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-20 15:16:55', 'kol_7ff348fe6b96', 'Not Assigned', 'null', '5', 'okey', '', '', 'IELTS', '', 'a', '2016-06-20 15:16:55', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6db2025ae4de', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-17 14:09:29', 'kol_eb5db4536e5b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'sandeep', '2016-06-17 14:09:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6db534f3a09c', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:22:01', 'kol_fe7fcbb38341', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Spoken English', '', 'a', '2016-06-16 16:22:01', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6dc3254cf3f4', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:56:17', 'kol_05dddca31839', 'Not Assigned', 'null', '7', 'tyeryert', '', '', 'Personality development', '', 'a', '2016-06-16 16:56:17', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6e00c58cca2c', 'sheela', 'Not Assigned', 'Kollam', '2016-06-13 13:08:59', 'kol_d435309754f4', 'sanoop', 'Registration Follow Up', '8', 'gfhg', '', '', '', '', 'sheela', '2016-06-13 13:08:59', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6e519cfb178e', 's', 'Not Assigned', 'Kochi', null, 'koc_00c9d19f0ec4', 's', 'Assessment Pending', '1', 'not picking', '', '', '', '', 's', '2016-06-13 17:39:47', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6ea9951d72c3', 'jitha', 'sumeshzoft', 'Kannur', '2016-06-13 17:10:29', 'kan_faa02d7d2026', 'sumeshzoft', 'Registration Follow Up', '6', 'aa', '', '', 'Spoken English', '', 'jitha', '2016-06-13 17:10:27', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6f0b28794074', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:44:29', 'kol_902ef03d9e76', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 17:44:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6f650e2068cb', 'a', 'a', 'Kollam', '2016-06-14 12:18:20', 'kol_87b16f6c6dae', 'a', 'Assessment Pending', '7', 'fds', '', '', '', '', 'a', '2016-06-13 11:18:28', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6f74a169b8f1', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:44:18', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 14:44:18', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6f892197c375', 'Self Assigned', 'jitha', 'Kannur', '2016-06-13 17:10:11', 'kan_67aee97bdabe', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'jitha', '2016-06-13 17:10:10', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_6fb0bad49487', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 16:39:14', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'fds', '', '', '', '', 'a', '2016-06-13 16:39:13', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_703befaeeeeb', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:37:38', 'kol_b89f7b63f70f', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-16 15:37:38', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_704cc7b5d1c8', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-17 13:45:14', 'koc_cc1b4827b853', 'Not Assigned', 'null', '1', '', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-17 13:45:14', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_707ea93e9697', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-17 14:46:00', 'koc_a7041eface62', 'Not Assigned', 'null', '1', '', 'Bachelor-BSc cs', '', '', '', 'sandeep', '2016-06-17 14:46:00', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_708cbb2b0a03', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:22:08', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 15:22:08', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_70b83739db8f', 'user3', 'Not Assigned', 'Kannur', null, 'kan_0e2069aaa132', 'Not Assigned', 'Assessment Pending', '2', '', '', '', '', 'Software Engineer', 'user3', '2016-06-15 17:45:25', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_70be32d82f2e', 'sheela', 'Not Assigned', 'Kollam', '2016-06-17 10:24:40', 'kol_66ebfd1a5540', 'a', 'Registration Follow Up', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'sheela', '2016-06-17 10:24:40', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_710273282d6c', 'user3', 'Not Assigned', 'Kannur', '2016-06-14 09:47:21', 'kan_0e2069aaa132', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-14 09:47:21', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_71191b3279b0', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 09:57:42', 'kan_fd9a9a06b595', 'Not Assigned', 'null', '1', '', 'Master-MTECH', '', '', '', 'jitha', '2016-06-14 09:57:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_72238efa98b3', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 17:21:21', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', '', '', '', '', 'a', '2016-06-14 17:21:21', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_72fa36971f45', 'b', 'Not Assigned', 'Kollam', '2016-06-13 10:41:15', 'kol_d435309754f4', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-13 10:41:16', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_730f1b7c5311', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-20 15:14:04', 'kol_2b3eb6eaddd3', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'a', '2016-06-20 15:14:04', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_73143b4b296c', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 15:11:12', 'kol_399d5eb36484', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sanoop', '2016-06-13 15:11:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7323aab94fab', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 17:21:38', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-14 17:21:38', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_737b686b3664', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 17:44:52', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', '', '', '', '', 'a', '2016-06-13 17:44:51', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_73a4be617d02', 'Self Assigned', 's', 'Kochi', '2016-06-13 17:29:03', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '6', 'not picking', '', '', '', '', 's', '2016-06-13 17:29:01', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_73b2595ad5fe', 'user3', 'Not Assigned', 'Kannur', null, 'kan_faa02d7d2026', 'Not Assigned', 'Assessment Pending', '2', 'aa', '', '', 'Spoken English', '', 'user3', '2016-06-15 17:58:52', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_73d2cd5b10b6', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:13:42', 'koc_4b1623ba84a3', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:13:41', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_743b801d09c6', 'Self Assigned', 's', 'Kochi', '2016-06-13 15:53:03', 'koc_b95c59f14d15', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:53:02', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7446202d4a31', 'sheela', 'Not Assigned', 'Kollam', '2016-06-17 12:52:06', 'kol_87b16f6c6dae', 'a', 'Registration Follow Up', '9', 'okey i willl', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'sheela', '2016-06-17 12:52:06', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7456f569b6be', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-17 14:22:15', 'koc_a7041eface62', 'Not Assigned', 'null', '1', '', 'Bachelor-BSc cs', '', '', '', 'sandeep', '2016-06-17 14:22:15', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_748e571c6202', 'a', 'sheela', 'Kollam', '2016-06-17 15:23:36', 'kol_e77e9f96475b', 'sheela', 'Registration Follow Up', '4', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-17 15:23:36', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7496d7c777be', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:35:58', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 12:35:58', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_74eb16d905d4', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 18:43:05', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'okey i willl', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'a', '2016-06-16 18:43:05', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7504fc23fedc', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-17 14:03:18', 'kan_60284b9c5c17', 'Not Assigned', 'null', '3', '', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-17 14:03:18', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_751ede85d9bf', 'b', 'Not Assigned', 'Kollam', '2016-06-13 10:41:44', 'kol_178922ed2ba8', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-13 10:41:45', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_762c496705d7', 'Self Assigned', 'sandeep', 'Kochi', '2016-06-17 13:56:47', 'kan_60284b9c5c17', 'Not Assigned', 'null', '3', '', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-17 13:56:47', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_76388b501d46', 'sumeshzoft', 'Not Assigned', 'Kannur', '2016-06-13 17:10:56', 'kan_faa02d7d2026', 'jitha', 'Registration Follow Up', '6', 'aa', '', '', 'Spoken English', '', 'sumeshzoft', '2016-06-13 17:10:54', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_764457058ce6', 'sheela', 'a', 'Kollam', '2016-06-17 14:58:39', 'koc_4b1623ba84a3', 'a', 'Registration Follow Up', '4', '', 'Bachelor-BSc cs', '', '', '', 'sheela', '2016-06-17 14:58:39', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_76902f8b3a83', 'a', 'a', 'Kollam', null, 'koc_4b1623ba84a3', 'a', 'Assessment Pending', '4', '', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-17 15:00:00', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_76c4b8b7af9b', 'Self Assigned', 'sandeep', 'Kochi', '2016-06-17 14:39:06', 'koc_dfca21935856', 'Not Assigned', 'null', '1', '', 'Bachelor-B.COM', '', '', '', 'sandeep', '2016-06-17 14:39:06', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_774559fd227a', 'jitha', 'Not Assigned', 'Kannur', '2016-06-13 17:30:05', 'kan_67aee97bdabe', 'sumeshzoft', 'Registration Follow Up', '1', '', '', '', 'Personality development', '', 'jitha', '2016-06-13 17:30:03', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7751c0906ec8', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-20 15:14:09', 'kol_7ff348fe6b96', 'Not Assigned', 'null', '5', 'okey', '', '', 'IELTS', '', 'a', '2016-06-20 15:14:09', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_77c928e464f9', 'sheela', 'a', 'Kollam', '2016-06-17 15:42:00', 'kol_1b03a7918ff0', 'a', 'Registration Follow Up', '4', 'ghgfjfgh', '', '', '', '', 'sheela', '2016-06-17 15:42:00', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7863e14174c2', 'b', 'Not Assigned', 'Kollam', '2016-06-20 15:42:34', 'kol_d14b4ae570dd', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-20 15:42:34', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_79458e33160a', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 12:17:00', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'sheela', '2016-06-17 12:17:00', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_795510efc6d6', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:52:29', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 14:52:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_79ee4f5cedbb', 'sandeep', 's', 'Kochi', '2016-06-17 13:57:24', 'kan_60284b9c5c17', 's', 'Registration Follow Up', '3', '', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-17 13:57:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7a21446cc627', 'Self Assigned', 'sheela', 'Kollam', '2016-06-17 12:51:51', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'sheela', '2016-06-17 12:51:50', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7a68a0c9c18a', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:03:06', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 12:03:06', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7acf41892e50', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:23:08', 'kan_76371a02a618', 'Not Assigned', 'null', '1', '', 'Bachelor-BSc cs', '', '', '', 'jitha', '2016-06-14 17:23:08', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7b9eb1269f1c', 'Self Assigned', 'sheela', 'Kollam', '2016-06-17 14:57:48', 'koc_4b1623ba84a3', 'Not Assigned', 'null', '4', '', 'Bachelor-BSc cs', '', '', '', 'sheela', '2016-06-17 14:57:48', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7c100eedf33c', 'a', 'Not Assigned', 'Kollam', '2016-06-30 15:22:55', 'kol_66a39843f66c', 'a', 'Assessment Pending', '5', 'cvbg', '', '', 'IELTS', '', 'a', '2016-06-20 15:23:00', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7c4ac2c2c69f', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:38:01', 'koc_4b1623ba84a3', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:38:00', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7c536dd1d2f9', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:13:45', 'koc_b95c59f14d15', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:13:44', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7c86db19788a', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-20 15:13:58', 'kol_66a39843f66c', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'a', '2016-06-20 15:13:58', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7ca7a4535666', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:40:20', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 16:40:20', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7cb6853751f2', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-15 17:12:54', 'kan_14132433f76b', 'Not Assigned', 'null', '1', '', '', '', '', 'Junior Engineer', 'jitha', '2016-06-15 17:12:54', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7d01fde04b74', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-15 17:28:43', 'kol_b379890ae41d', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'a', '2016-06-15 17:28:43', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7d12a996c711', 'b', 'Not Assigned', 'Kollam', '2016-06-13 10:40:50', 'kol_66ebfd1a5540', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-13 10:40:51', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7d44b6610d8a', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:58:53', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 14:58:53', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7d5bd11996f4', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:19:12', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 17:19:12', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7e4800f13ab8', 'sandy', 's', 'Kochi', '2016-06-17 13:50:17', 'koc_a71b02703901', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'sandy', '2016-06-17 13:50:17', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7e4d54b2cfac', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-20 15:16:50', 'kol_b672c8fe2b6b', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'a', '2016-06-20 15:16:50', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7e6d1bd4b90a', 'sumeshzoft', 'Not Assigned', 'Kannur', '2016-06-13 16:26:44', 'kan_faa02d7d2026', 'sumeshzoft', 'Assessment Pending', '6', 'aa', '', '', 'Spoken English', '', 'sumeshzoft', '2016-06-13 16:26:55', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7e863c726d81', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 14:23:26', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', '', '', '', '', 'a', '2016-06-14 14:23:26', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7e89ecf477ee', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 16:33:36', 'kan_faa02d7d2026', 'Not Assigned', 'null', '6', 'aa', '', '', 'Spoken English', '', 'sumeshzoft', '2016-06-13 16:33:34', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7e9237689e1b', 'sandeep', 'sandeep', 'Kochi', null, 'koc_b95c59f14d15', 'sandeep', 'Assessment Pending', '4', '', '', '', '', '', 'sandeep', '2016-06-14 12:36:37', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7ed394fbacfd', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 11:52:38', 'koc_a7041eface62', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-14 11:52:38', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_7fe10adee4f3', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 14:22:09', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', '', '', '', '', 'sanoop', '2016-06-13 14:22:08', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8018e4b794bd', 'sandeep', 'Not Assigned', 'Kochi', '2016-06-17 13:58:24', 'koc_619929d9bc25', 's', 'Registration Follow Up', '1', '', '', '', '', '', 'sandeep', '2016-06-17 13:58:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_803626ba49de', 'sandy', 'Not Assigned', 'Kochi', '2016-06-13 15:10:55', 'koc_e454886ba276', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'sandy', '2016-06-13 15:10:54', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8182a8b3901b', 'Self Assigned', 'sheela', 'Kollam', '2016-06-17 12:24:07', 'kol_fe7fcbb38341', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Spoken English', '', 'sheela', '2016-06-17 12:24:07', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_818380f57bf8', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:08:34', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 12:08:34', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_82096d229bd7', 'Self Assigned', 'a', 'Kollam', '2016-06-17 11:01:06', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-17 11:01:06', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_822d3f6c0fac', 'Self Assigned', 's', 'Kochi', '2016-06-13 15:38:04', 'koc_78df79df877c', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:38:03', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8288a8ea3366', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:22:49', 'kan_fadfe7897cb7', 'Not Assigned', 'null', '1', '', '', '', 'FRENCH', '', 'jitha', '2016-06-14 17:22:49', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_82b57ede031f', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 10:37:58', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-17 10:37:58', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_82bbb0673289', 'Self Assigned', 'a', 'Kollam', '2016-06-17 12:00:26', 'kol_79f0550ce37c', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'a', '2016-06-17 12:00:26', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_82fca19e473e', 'sanoop', 'sanoop', 'Kollam', '2016-06-13 20:01:08', 'kol_05dddca31839', 'sanoop', 'Assessment Pending', '7', 'tyeryert', '', '', 'Personality development', '', 'sanoop', '2016-06-13 15:01:18', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_833f2f5d8227', 'Self Assigned', 's', 'Kochi', '2016-06-13 15:44:18', 'koc_78df79df877c', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:44:17', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_836469ea52c1', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:24:41', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 14:24:41', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_836cde18c368', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 12:13:10', 'kol_902ef03d9e76', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'sheela', '2016-06-17 12:13:10', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_83827c65fd65', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:56:58', 'kan_fd9a9a06b595', 'Not Assigned', 'null', '4', '', 'Master-MTECH', '', '', '', 'jitha', '2016-06-14 17:56:58', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_83dcc69c17d1', 'a', 'Not Assigned', 'Kollam', '2016-06-13 11:46:56', 'kol_66ebfd1a5540', 'sheela', 'Registration Follow Up', '7', 'try', '', '', '', '', 'a', '2016-06-13 11:46:56', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_83ed67a891d3', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 15:59:29', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-14 15:59:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_842633c7c120', 'sandeep', 'sandeep', 'Kochi', null, 'koc_dfca21935856', 'sandeep', 'Assessment Pending', '4', '', 'Bachelor-B.COM', '', '', '', 'sandeep', '2016-06-17 14:40:38', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_84e94f95877b', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:46:55', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-16 14:46:55', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8567f62617b5', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:29:34', 'koc_e454886ba276', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:29:33', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_85683bb507f9', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:37:33', 'kol_902ef03d9e76', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 15:37:33', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_856f0470988b', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-17 13:45:20', 'koc_b896ec0b0699', 'Not Assigned', 'null', '3', '', 'Bachelor-BA History', '', '', '', 'sandeep', '2016-06-17 13:45:20', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_85b87311cf9b', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:54:26', 'kol_8cdccfd7fe58', 'Not Assigned', 'null', '1', '', '', '', '', '', 'a', '2016-06-16 16:54:26', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_85f6e51108d2', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 15:04:02', 'kol_fe7fcbb38341', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'a', '2016-06-13 15:04:01', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8681109b9012', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 09:57:00', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-17 09:57:00', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_86bc6d883c97', 'a', 'sheela', 'Kollam', '2016-06-13 11:32:29', 'kol_d435309754f4', 'sheela', 'Registration Follow Up', '1', '', '', '', '', '', 'a', '2016-06-13 11:32:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8744229d0029', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 12:00:16', 'koc_cc1b4827b853', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-14 12:00:16', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8772a18a5dfa', 'Self Assigned', 'jitha', 'Kannur', '2016-06-13 17:10:14', 'kan_faa02d7d2026', 'Not Assigned', 'null', '6', 'aa', '', '', 'Spoken English', '', 'jitha', '2016-06-13 17:10:12', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_87aa60d71585', 'sandeep', 'a', 'Kochi', '2016-06-17 14:46:38', 'koc_4b1623ba84a3', 'a', 'Registration Follow Up', '2', '', 'Bachelor-BSc cs', '', '', '', 'sandeep', '2016-06-17 14:46:38', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_87b8acec5bd3', 'b', 'Not Assigned', 'Kollam', null, 'kol_17cf27990d9e', 'Not Assigned', 'Assessment Pending', '2', '', 'Master-computer Application', '', 'IELTS', '', 'b', '2016-06-21 15:21:40', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8852e0597b3f', 'Self Assigned', 'jitha', 'Kannur', '2016-06-13 17:59:00', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '6', 'not picking', '', '', '', '', 'jitha', '2016-06-13 17:58:58', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8858aa021e7f', 's', 's', 'Kochi', null, 'koc_e454886ba276', 's', 'Assessment Pending', '1', 'call later', '', '', '', '', 's', '2016-06-13 17:39:21', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_887c3a724366', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 15:27:41', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-14 15:27:41', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_888e6beb36dc', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 11:58:39', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-14 11:58:39', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_88e580ce1d3d', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 10:43:03', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-17 10:43:03', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_89376d4f70cd', 'sandeep', 'Not Assigned', 'Kochi', '2016-06-14 10:37:07', 'kan_f89c4cc5fcd5', 's', 'Registration Follow Up', '1', '', 'Master-MA Malayalam', '', '', '', 'sandeep', '2016-06-14 10:37:07', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_89585cfdc7ac', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 11:20:43', 'kol_79f0550ce37c', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'a', '2016-06-16 11:20:43', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_89d407b1352a', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:44:03', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 14:44:03', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_89d73577167d', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:22:48', 'kan_7d4957ffac9c', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'jitha', '2016-06-14 17:22:48', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_89dc91ebfc09', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:21:35', 'koc_b95c59f14d15', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:21:34', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8a10337c6eac', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:43:06', 'kol_a7433dc10ad7', 'Not Assigned', 'null', '1', '', '', 'Doctor/Surgen', 'Personality development', '', 'sumeshzoft', '2016-06-14 17:43:06', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8a1b5c37e45f', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 15:21:34', 'kan_60284b9c5c17', 'Not Assigned', 'null', '1', '', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-14 15:21:34', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8a6cfbdbf872', 'sandy', 'Not Assigned', 'Kochi', '2016-06-13 15:07:45', 'koc_1dd89e2611ea', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'sandy', '2016-06-13 15:07:44', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8b2457b05df1', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 18:24:52', 'koc_4b1623ba84a3', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-13 18:24:51', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8b56a6d0955a', 's', 'Not Assigned', 'Kochi', '2016-06-13 15:14:52', 'koc_cdc8ad6f110f', 'sandeep', 'Registration Follow Up', '1', '', '', '', '', '', 's', '2016-06-13 15:14:51', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8be4cc18b1e0', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 11:05:11', 'kan_60284b9c5c17', 'Not Assigned', 'null', '1', '', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-14 11:05:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8c4861497f26', 'Self Assigned', 'sheela', 'Kollam', '2016-06-17 12:13:00', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'sheela', '2016-06-17 12:13:00', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8c4fe6eeb43c', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 15:07:51', 'kol_8cdccfd7fe58', 'Not Assigned', 'null', '1', '', '', '', '', '', 'a', '2016-06-13 15:07:50', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8ce81f389cf8', 'b', 'a', 'Kollam', '2016-06-17 14:31:56', 'kol_e6429f1a8bb0', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-17 14:31:56', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8ce96009f711', 'a', 'sheela', 'Kollam', '2016-06-17 15:41:31', 'kol_1b03a7918ff0', 'sheela', 'Registration Follow Up', '4', 'ghgfjfgh', '', '', '', '', 'a', '2016-06-17 15:41:32', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8d1a233fa49e', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 20:07:48', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-13 20:07:48', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8dec3c3c4b5b', 'a', 'sheela', 'Kollam', '2016-06-13 11:28:02', 'kol_66ebfd1a5540', 'sheela', 'Fee Payment Follow up', '1', '', '', '', '', '', 'a', '2016-06-13 11:28:02', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8e4be006fc6c', 'sanoop', 'sanoop', 'Kollam', '2016-06-13 16:00:18', 'kol_05dddca31839', 'sanoop', 'Assessment Pending', '5', 'tyeryert', '', '', 'Personality development', '', 'sanoop', '2016-06-13 14:46:36', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8e6b76aec879', 'user3', 'jitha', 'Kannur', null, 'kan_f6a01e7d4e70', 'jitha', 'Assessment Pending', '1', '', '', 'Software Engineer', '', '', 'user3', '2016-06-15 17:44:10', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8e7836c0ebe6', 's', 'Not Assigned', 'Kochi', '2016-06-17 13:52:50', 'koc_619929d9bc25', 'sandeep', 'Registration Follow Up', '1', '', '', '', '', '', 's', '2016-06-17 13:52:50', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8e78d8a5d7d5', 'Self Assigned', 'sandeep', 'Kochi', '2016-06-17 14:22:09', 'koc_619929d9bc25', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-17 14:22:09', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8e9dce176542', 'sheela', 'a', 'Kollam', '2016-06-13 11:19:50', 'kol_87b16f6c6dae', 'a', 'Fee Payment Follow up', '6', 'fds', '', '', '', '', 'sheela', '2016-06-13 11:19:50', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8ea7ee97e5dc', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:53:21', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-16 14:53:21', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8f3cf3451273', 'sheela', 'a', 'Kollam', '2016-06-13 12:34:30', 'kol_d435309754f4', 'a', 'Fee Payment Follow up', '7', 'gfhg', '', '', '', '', 'sheela', '2016-06-13 12:34:30', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8f62ecde802a', 'sandeep', 'Not Assigned', 'Kochi', '2016-06-17 14:14:12', 'kol_49c7d8d21eba', 's', 'Registration Follow Up', '1', 'rutuytryrtyr', '', '', 'Spoken English', '', 'sandeep', '2016-06-17 14:14:12', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8f6a664bb8ea', 'Self Assigned', 'sheela', 'Kollam', '2016-06-13 11:02:42', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sheela', '2016-06-13 11:02:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8fa8e8fd0d13', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:39:05', 'koc_e454886ba276', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:39:04', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_8fcbdcfa2c23', 'Self Assigned', 'jitha', 'Kannur', '2016-06-16 09:28:34', 'kol_a7433dc10ad7', 'Not Assigned', 'null', '1', '', '', 'Doctor/Surgen', 'Personality development', '', 'jitha', '2016-06-16 09:28:34', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9073ec89a37c', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:37:12', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 14:37:12', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_90807e771df4', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:28:49', 'koc_78df79df877c', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-13 15:28:48', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_910025679c16', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 15:35:17', 'kan_64d0ee4ec807', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sumeshzoft', '2016-06-13 15:35:16', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_91136a145f30', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 13:00:07', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 13:00:07', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9167d9debf17', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 16:05:58', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-14 16:05:58', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_91be7f711275', 'a', 'sheela', 'Kollam', '2016-06-13 12:30:33', 'kol_d435309754f4', 'sheela', 'Fee Payment Follow up', '8', 'gfhg', '', '', '', '', 'a', '2016-06-13 12:30:33', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9215546a4e4e', 'a', 'a', 'Kollam', '2016-06-13 13:01:38', 'kol_d435309754f4', 'a', 'Assessment Pending', '6', 'gfhg', '', '', '', '', 'a', '2016-06-13 13:01:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_923d7641c22f', 'b', 'Not Assigned', 'Kollam', '2016-06-13 14:18:43', 'kol_0c1764954c6d', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-13 14:18:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_92a6d29b243c', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:21:56', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 16:21:56', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_92abdad60874', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 11:05:23', 'koc_cc1b4827b853', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-14 11:05:23', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_92b051a3ce52', 'Self Assigned', 's', 'Kochi', '2016-06-13 15:13:53', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:13:52', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_92b2ad40fb1d', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 14:38:35', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-14 14:38:35', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_93544c12f203', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 15:42:12', 'kan_fd9a9a06b595', 'Not Assigned', 'null', '1', '', '', '', '', '', 'jitha', '2016-06-13 15:42:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_936e0a17b989', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 10:45:53', 'kan_67aee97bdabe', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'jitha', '2016-06-14 10:45:53', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9453652a7ed6', 'a', 'sheela', 'Kollam', '2016-06-13 11:42:35', 'kol_d435309754f4', 'sheela', 'Fee Payment Follow up', '8', 'gfhg', '', '', '', '', 'a', '2016-06-13 11:42:35', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_94b43f6037ff', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:45:50', 'kol_d0cc31695858', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', 'Doctor/Surgen', 'jitha', '2016-06-14 17:45:50', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_94f4389ed168', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 18:26:55', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '6', 'not picking', '', '', '', '', 'sandeep', '2016-06-13 18:26:53', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_94fea447eb94', 'b', 'Not Assigned', 'Kollam', '2016-06-14 12:38:12', 'kol_2bd08b71d7ea', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-14 12:38:12', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_95466dc71700', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 12:42:29', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-17 12:42:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9549a7199f24', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:38:25', 'koc_b95c59f14d15', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-13 15:38:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_958bb5a6428b', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 15:35:13', 'kan_faa02d7d2026', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'sumeshzoft', '2016-06-13 15:35:12', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_967e1a3193d8', 'jitha', 'Not Assigned', 'Kannur', '2016-06-13 17:30:17', 'kan_a8087d458991', 'sumeshzoft', 'Registration Follow Up', '1', '', '', '', 'Personality development', '', 'jitha', '2016-06-13 17:30:15', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_968ea99359b2', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:46:44', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 14:46:44', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9698d1494c87', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 14:15:31', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'fds', '', '', '', '', 'a', '2016-06-14 14:15:31', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_970bc3f1ff6d', 'sheela', 'Not Assigned', 'Kollam', '2016-06-17 12:24:49', 'kol_902ef03d9e76', 'a', 'Registration Follow Up', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'sheela', '2016-06-17 12:24:49', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_973a6df5fe7c', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:11:26', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 16:11:25', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_97bd92cead68', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 17:44:54', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'fds', '', '', '', '', 'a', '2016-06-13 17:44:53', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_97d0baddff7f', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 17:37:46', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'fds', '', '', '', '', 'a', '2016-06-13 17:37:44', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_98046e75a5cb', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:45:51', 'kan_64d0ee4ec807', 'Not Assigned', 'null', '1', '', '', '', '', '', 'jitha', '2016-06-14 17:45:51', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_98dd93b76560', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:56:11', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 16:56:10', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_997cf504cbf4', 'b', 'sheela', 'Kollam', '2016-06-20 12:56:23', 'kol_47a290eaa29d', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-20 12:56:23', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_99f7052f127e', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 12:48:00', 'kol_49c7d8d21eba', 'Not Assigned', 'null', '1', 'rutuytryrtyr', '', '', 'Spoken English', '', 'sandeep', '2016-06-14 12:48:00', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9a178828a161', 'sheela', 'sheela', 'Kollam', null, 'koc_4b1623ba84a3', 'sheela', 'Assessment Pending', '4', '', 'Bachelor-BSc cs', '', '', '', 'sheela', '2016-06-17 14:58:27', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9a309a3c3029', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 15:42:18', 'kan_805877d725d7', 'Not Assigned', 'null', '1', '', '', '', '', '', 'jitha', '2016-06-13 15:42:16', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9a4a44af1608', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:35:48', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 12:35:48', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9aee416a83c2', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:15:17', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-16 12:15:17', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9b5766ce9df5', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-15 17:12:51', 'kol_d0cc31695858', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', 'Doctor/Surgen', 'jitha', '2016-06-15 17:12:51', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9b668af559bf', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 14:08:01', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-14 14:08:01', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9b67d77b5e0d', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 11:58:08', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-17 11:58:08', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9bba44af6ced', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 11:05:13', 'koc_a7041eface62', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-14 11:05:13', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9bc43a71f465', 'Self Assigned', 'a', 'Kollam', '2016-06-17 12:00:04', 'kol_399d5eb36484', 'Not Assigned', 'null', '1', '', '', '', '', '', 'a', '2016-06-17 12:00:04', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9c64bfb89f0b', 'Self Assigned', 'a', 'Kollam', '2016-06-17 10:32:56', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-17 10:32:56', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9c9e8d9382fe', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 14:07:56', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-14 14:07:56', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9ca58ffb90d5', 'a', 'Not Assigned', 'Kollam', '2016-06-17 12:02:33', 'kol_43c73f9a76f6', 'sheela', 'Registration Follow Up', '1', '', '', '', 'IELTS', '', 'a', '2016-06-17 12:02:33', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9cd35dd2da7d', 'Self Assigned', 'jitha', 'Kannur', '2016-06-13 17:59:02', 'koc_b95c59f14d15', 'Not Assigned', 'null', '1', '', '', '', '', '', 'jitha', '2016-06-13 17:59:00', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9d3a2b24a4a7', 'sheela', 'sheela', 'Kollam', '2016-06-13 15:59:45', 'kol_87b16f6c6dae', 'sheela', 'Assessment Pending', '7', 'fds', '', '', '', '', 'sheela', '2016-06-13 12:59:50', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9d7c32d11a57', 'b', 'a', 'Kollam', '2016-06-14 14:15:38', 'kol_f76f27840190', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-14 14:15:38', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9dd48d4c2700', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:22:14', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 15:22:14', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9e714116bc85', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:22:27', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 16:22:27', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9f08c1e1b87d', 's', 'Not Assigned', 'Kochi', null, 'koc_78df79df877c', 's', 'Assessment Pending', '1', '', '', '', '', '', 's', '2016-06-13 15:43:26', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9f36cd2c9eb7', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:44:10', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 14:44:10', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_9ff7f094dacf', 'Self Assigned', 'sheela', 'Kollam', '2016-06-13 13:15:53', 'kol_178922ed2ba8', 'Not Assigned', 'null', '6', 'hjk', '', '', '', '', 'sheela', '2016-06-13 13:15:52', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a010292433b8', 's', 'sandeep', 'Kochi', '2016-06-13 15:29:48', 'kan_f89c4cc5fcd5', 'sandeep', 'Registration Follow Up', '1', '', '', '', '', '', 's', '2016-06-13 15:29:47', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a0212b089af4', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 14:38:29', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-14 14:38:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a0318c249bbb', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 15:47:50', 'kan_4f9cddfd4e5c', 'Not Assigned', 'null', '1', '', 'Bachelor-BSc cs', '', '', '', 'jitha', '2016-06-13 15:47:49', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a06bad8fe4f2', 'sanoop', 'sanoop', 'Kollam', null, 'kol_d435309754f4', 'sanoop', 'Assessment Pending', '4', 'gfhg', '', '', '', '', 'sanoop', '2016-06-13 14:25:13', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a07ff23f7f54', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 12:58:27', 'kol_fe7fcbb38341', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'a', '2016-06-14 12:58:27', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a092fe84d102', 'user3', 'Not Assigned', 'Kannur', '2016-06-13 15:41:04', 'kan_fd9a9a06b595', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-13 15:41:03', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a153340ba672', 'jitha', 'sumeshzoft', 'Kannur', '2016-06-13 17:10:29', 'kan_a8087d458991', 'sumeshzoft', 'Registration Follow Up', '1', '', '', '', 'Personality development', '', 'jitha', '2016-06-13 17:10:27', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a20ba7067610', 'a', 'Not Assigned', 'Kollam', '2016-06-17 12:43:12', 'kol_178922ed2ba8', 'sheela', 'Registration Follow Up', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-17 12:43:12', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a228bd6018e4', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 13:06:58', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 13:06:58', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a24da91228cc', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-17 14:09:41', 'koc_cc1b4827b853', 'Not Assigned', 'null', '1', '', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-17 14:09:41', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a24e1655ebdd', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 14:35:30', 'kol_05dddca31839', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-13 14:35:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a263fb391c68', 'sheela', 'Not Assigned', 'Kollam', '2016-06-17 10:20:45', 'kol_7c38fd4e7311', 'a', 'Fee Payment Follow up', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'sheela', '2016-06-17 10:20:45', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a2b19c99d144', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 14:00:41', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'fds', '', '', '', '', 'a', '2016-06-14 14:00:41', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a2ec2f58c5f6', 'Self Assigned', 's', 'Kochi', '2016-06-13 15:42:30', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:42:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a371d158c7ef', 'a', 'Not Assigned', 'Kollam', '2016-06-17 12:01:21', 'kol_05dddca31839', 'sheela', 'Registration Follow Up', '7', 'tyeryert', '', '', 'Personality development', '', 'a', '2016-06-17 12:01:21', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a40a10123ee8', 's', 'Not Assigned', 'Kochi', '2016-06-13 18:59:00', 'kan_f89c4cc5fcd5', 'sandeep', 'Registration Follow Up', '1', '', '', '', '', '', 's', '2016-06-13 18:58:59', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a42f10f2ff98', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:21:24', 'koc_4b1623ba84a3', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:21:23', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a43aef3eb62e', 'b', 'Not Assigned', 'Kollam', '2016-06-13 17:40:16', 'kol_4d5f5f43ac8b', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-13 17:40:14', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a50e88cd61ca', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 11:39:47', 'kan_f89c4cc5fcd5', 'Not Assigned', 'null', '1', '', 'Master-MA Malayalam', '', '', '', 's', '2016-06-14 11:39:47', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a520db30506c', 'sheela', 'Not Assigned', 'Kollam', '2016-06-17 12:17:17', 'kol_87b16f6c6dae', 'a', 'Registration Follow Up', '9', 'okey i willl', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'sheela', '2016-06-17 12:17:17', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a59137ef76a4', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:56:36', 'kol_fe7fcbb38341', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Spoken English', '', 'a', '2016-06-16 16:56:36', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a5dfb7f92172', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:57:18', 'kan_67aee97bdabe', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'jitha', '2016-06-14 17:57:18', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a5ef00e7efd5', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 15:16:35', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', '', '', '', '', 'a', '2016-06-13 15:16:34', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a657f6c1e639', 'user14', 'sumeshzoft', 'Kannur', '2016-06-14 11:07:05', 'kan_dec1a454c24a', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user14', '2016-06-14 11:07:05', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a66acd62a9a2', 'Self Assigned', 'a', 'Kollam', '2016-06-17 11:58:03', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-17 11:58:03', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a690a562b9d7', 'sumeshzoft', 'Not Assigned', 'Kannur', '2016-06-13 17:10:56', 'kan_67aee97bdabe', 'jitha', 'Registration Follow Up', '1', '', '', '', 'Personality development', '', 'sumeshzoft', '2016-06-13 17:10:54', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a691e7a15982', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 18:39:05', 'kol_fe7fcbb38341', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Spoken English', '', 'a', '2016-06-16 18:39:05', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a6c717039c39', 'a', 'Not Assigned', 'Kollam', '2016-06-13 15:05:34', 'kol_b9749879089d', 'sanoop', 'Ticketing Follow up', '1', '', '', '', 'IELTS', '', 'a', '2016-06-13 15:05:33', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a72efcc46987', 'Self Assigned', 'sheela', 'Kollam', '2016-06-17 12:51:34', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'okey i willl', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'sheela', '2016-06-17 12:51:34', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a79840f4a765', 'a', 'Not Assigned', 'Kollam', '2016-06-17 11:28:27', 'kol_66ebfd1a5540', 'sheela', 'Registration Follow Up', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-17 11:28:27', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a7bcb8524a97', 'Self Assigned', 'sandeep', 'Kochi', '2016-06-14 09:39:51', 'kan_f89c4cc5fcd5', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-14 09:39:51', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a7bef89e7dbf', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:59:09', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-16 14:59:09', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a7eb18509e4d', 'Self Assigned', 'a', 'Kollam', '2016-06-17 12:42:13', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-17 12:42:13', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a82ae9272579', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 10:38:23', 'kan_f89c4cc5fcd5', 'Not Assigned', 'null', '1', '', 'Master-MA Malayalam', '', '', '', 'sandeep', '2016-06-14 10:38:23', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a8b62c899ae6', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:49:00', 'kol_902ef03d9e76', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 15:49:00', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a8d8cee04d1e', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 15:04:52', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-14 15:04:52', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a8de1798e030', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 09:46:48', 'koc_b95c59f14d15', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-14 09:46:48', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a8f32a781d7c', 'Self Assigned', 'a', 'Kollam', '2016-06-13 18:01:10', 'kol_178922ed2ba8', 'Not Assigned', 'null', '6', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-13 18:01:08', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a93c87a7d58f', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 17:45:47', 'koc_00c9d19f0ec4', 'Not Assigned', 'null', '1', 'not picking', '', '', '', '', 's', '2016-06-13 17:45:46', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a9d2eafb904a', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-15 11:35:19', 'kan_4f9cddfd4e5c', 'Not Assigned', 'null', '4', '', 'Bachelor-BSc cs', '', '', '', 'sumeshzoft', '2016-06-15 11:35:19', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_a9fad072c6d4', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 11:06:44', 'kol_d33b0ffcb552', 'Not Assigned', 'null', '1', 'not picking', '', '', '', '', 'sandeep', '2016-06-14 11:06:44', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_aa040328b20c', 'user3', 'jitha', 'Kannur', '2016-06-14 15:16:38', 'kan_d14f72fc2e86', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-14 15:16:38', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_aa638218a981', 'sheela', 'Not Assigned', 'Kollam', '2016-06-13 11:28:34', 'kol_87b16f6c6dae', 'a', 'Registration Follow Up', '6', 'fds', '', '', '', '', 'sheela', '2016-06-13 11:28:34', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_aaf066f2bb01', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 16:53:10', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-14 16:53:10', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ab0dca1693b2', 'Self Assigned', 'sandeep', 'Kochi', '2016-06-17 14:20:37', 'kan_60284b9c5c17', 'Not Assigned', 'null', '3', '', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-17 14:20:37', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ab536fb64e4b', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:40:09', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'okey i willl', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'a', '2016-06-16 16:40:09', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ab8a8fc9dc73', 'a', 'Not Assigned', 'Kollam', '2016-06-17 14:48:05', 'koc_4b1623ba84a3', 'sheela', 'Registration Follow Up', '4', '', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-17 14:48:05', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_abaa74f7b23b', 'sandeep', 'sandeep', 'Kochi', null, 'koc_b896ec0b0699', 'sandeep', 'Assessment Pending', '4', '', '', '', '', '', 'sandeep', '2016-06-14 12:33:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_abb3ac7dbe5e', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:10:58', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-16 16:10:58', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_abeda523f9cb', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:47:05', 'kol_fe7fcbb38341', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Spoken English', '', 'a', '2016-06-16 12:47:05', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ac359c046278', 'user3', 'Not Assigned', 'Kannur', '2016-06-13 14:54:16', 'kan_4f9cddfd4e5c', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-13 14:54:15', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ad65c22ead8a', 'a', 'Not Assigned', 'Kollam', null, 'kol_178922ed2ba8', 'a', 'Assessment Pending', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-13 18:02:46', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ad6ea6bc4644', 'sandeep', 'Not Assigned', 'Kochi', '2016-06-17 14:40:19', 'kol_eb5db4536e5b', 'sheela', 'Registration Follow Up', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'sandeep', '2016-06-17 14:40:19', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ae12f7a7e6dc', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:46:04', 'kol_4d5f5f43ac8b', 'Not Assigned', 'null', '1', '', '', '', '', '', 'jitha', '2016-06-14 17:46:04', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ae68e1862082', 's', 's', 'Kochi', null, 'kan_60284b9c5c17', 's', 'Assessment Pending', '4', 'call back', 'Language Course-Spoken English', '', '', '', 's', '2016-06-17 14:28:54', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ae6dae858169', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:16:47', 'koc_00c9d19f0ec4', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:16:46', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_aed8fbbd82e9', 'sheela', 'Not Assigned', 'Kollam', '2016-06-17 10:22:49', 'kol_ebae93075120', 'a', 'Registration Follow Up', '1', '', 'Master-MA English', '', 'Personality development', '', 'sheela', '2016-06-17 10:22:49', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_af3bb034d773', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 10:59:32', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 10:59:32', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_af79b3f65fff', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:17:15', 'koc_78df79df877c', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:17:14', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_afc986a176a0', 'a', 'a', 'Kollam', '2016-06-13 18:34:49', 'kol_d435309754f4', 'a', 'Assessment Pending', '6', 'gfhg', '', '', '', '', 'a', '2016-06-13 12:34:54', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b07e84bd8821', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:58:58', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 14:58:58', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b07ea4245e25', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:42:29', 'koc_b95c59f14d15', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:42:27', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b193d628f5d2', 'a', 'a', 'Kollam', '2016-06-13 18:41:00', 'kol_d435309754f4', 'a', 'Assessment Pending', '7', 'gfhg', '', '', '', '', 'a', '2016-06-13 11:41:06', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b1ae0a612fa6', 'sandeep', 'Not Assigned', 'Kochi', '2016-06-13 19:04:59', 'koc_cdc8ad6f110f', 'sandeep', 'Assessment Pending', '7', 'not picking', '', '', '', '', 'sandeep', '2016-06-13 19:05:03', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b1e00d53a804', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-20 15:22:18', 'kol_b672c8fe2b6b', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'a', '2016-06-20 15:22:18', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b1e6c17cc90c', 'Self Assigned', 'a', 'Kollam', '2016-06-17 09:59:24', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'okey i willl', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'a', '2016-06-17 09:59:25', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b21c06a18809', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-15 17:12:42', 'kan_d2d606026a15', 'Not Assigned', 'null', '1', '', '', '', '', 'Chemical Engineer', 'jitha', '2016-06-15 17:12:42', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b23130dd0595', 'Self Assigned', 'sandeep', 'Kochi', '2016-06-17 14:27:53', 'kan_60284b9c5c17', 'Not Assigned', 'null', '3', 'call back', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-17 14:27:53', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b258f5717cf5', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:45:59', 'kan_d2d606026a15', 'Not Assigned', 'null', '1', '', '', '', '', '', 'jitha', '2016-06-14 17:45:59', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b2696a93b1b1', 'a', 'Not Assigned', 'Kollam', '2016-06-17 12:43:58', 'kol_79f0550ce37c', 'sheela', 'Registration Follow Up', '1', '', '', '', 'Spoken English', '', 'a', '2016-06-17 12:43:58', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b27562dae972', 'Self Assigned', 'a', 'Kollam', '2016-06-13 10:42:51', 'kol_d435309754f4', 'Not Assigned', 'null', '1', '', '', '', '', '', 'a', '2016-06-13 10:42:52', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b276c6790a1a', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 19:07:59', 'koc_b95c59f14d15', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-13 19:07:57', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b27a828f4ce5', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:55:53', 'kol_b379890ae41d', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'a', '2016-06-16 16:55:53', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b284d82e02ec', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 17:45:45', 'kol_d33b0ffcb552', 'Not Assigned', 'null', '1', 'not picking', '', '', '', '', 's', '2016-06-13 17:45:43', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b387e82119ad', 'Self Assigned', 'sumeshzoft', 'Kannur', '2016-06-13 16:22:53', 'kan_faa02d7d2026', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'sumeshzoft', '2016-06-13 16:22:51', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b38cce1d0426', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 10:43:11', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '1', '', '', '', '', '', 'a', '2016-06-13 10:43:12', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b3aaf4106196', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:20:05', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 15:20:05', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b3e6bff75470', 'a', 'Not Assigned', 'Kollam', '2016-06-17 09:59:41', 'kol_87b16f6c6dae', 'sheela', 'Registration Follow Up', '9', 'okey i willl', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'a', '2016-06-17 09:59:41', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b41707b35052', 'sandeep', 'Not Assigned', 'Kochi', null, 'koc_b896ec0b0699', 'sandeep', 'Assessment Pending', '3', '', '', '', '', '', 'sandeep', '2016-06-14 12:35:26', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b4742c182d61', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:35:42', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 12:35:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b4f96c7ae435', 'Self Assigned', 'sheela', 'Kollam', '2016-06-17 12:51:39', 'kol_fe7fcbb38341', 'Not Assigned', 'null', '6', 'rtret', 'PhD/Doctorate-Doctor of Psychology', '', 'Spoken English', '', 'sheela', '2016-06-17 12:51:39', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b51f7f3980ba', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 18:14:13', 'koc_b95c59f14d15', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-13 18:14:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b533a027e389', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 16:39:11', 'kol_178922ed2ba8', 'Not Assigned', 'null', '6', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-13 16:39:09', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b5424b0378ea', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 19:01:31', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '6', 'not picking', '', '', '', '', 'sandeep', '2016-06-13 19:01:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b59963deefcc', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:01:08', 'kan_76371a02a618', 'Not Assigned', 'null', '1', '', 'Bachelor-BSc cs', '', '', '', 'jitha', '2016-06-14 17:01:08', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b5def34f18b4', 'sheela', 'a', 'Kollam', '2016-06-13 11:14:11', 'kol_66ebfd1a5540', 'a', 'Registration Follow Up', '1', '', '', '', '', '', 'sheela', '2016-06-13 11:14:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b61021fe2623', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:56:04', 'kol_2bd08b71d7ea', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-16 16:56:04', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b6762e205c05', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:54:33', 'kol_43c73f9a76f6', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'a', '2016-06-16 16:54:33', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b71d91777368', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 17:37:45', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', '', '', '', '', 'a', '2016-06-13 17:37:43', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b756bc1143d5', 'Self Assigned', 'jitha', 'Kannur', '2016-06-13 17:29:36', 'kan_a8087d458991', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'jitha', '2016-06-13 17:29:34', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b7b426bc4252', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:53:42', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 12:53:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b84ecbfdc2dc', 'sandeep', 'Not Assigned', 'Kochi', '2016-06-17 13:47:45', 'koc_b95c59f14d15', 's', 'Registration Follow Up', '3', '', 'Master-science', '', '', '', 'sandeep', '2016-06-17 13:47:45', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b87a177a8e6a', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:57:09', 'kan_a2c0869fdcde', 'Not Assigned', 'null', '1', '', 'Master-computer Application', '', '', '', 'jitha', '2016-06-14 17:57:09', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b88540017aac', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 15:36:34', 'kan_4f9cddfd4e5c', 'Not Assigned', 'null', '1', '', 'Bachelor-BSc cs', '', '', '', 'jitha', '2016-06-13 15:36:33', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b9136f38b170', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 10:27:42', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 10:27:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b933294f3739', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-15 17:22:03', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-15 17:22:03', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b98776c89edf', 'Self Assigned', 'sanoop', 'Kollam', '2016-06-13 15:00:55', 'kol_05dddca31839', 'Not Assigned', 'null', '5', 'tyeryert', '', '', 'Personality development', '', 'sanoop', '2016-06-13 15:00:54', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_b9fab706dc5a', 'Self Assigned', 'sandeep', 'Kochi', '2016-06-17 14:45:42', 'koc_4b1623ba84a3', 'Not Assigned', 'null', '2', '', 'Bachelor-BSc cs', '', '', '', 'sandeep', '2016-06-17 14:45:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ba27a4c1381a', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:54:19', 'kol_b379890ae41d', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'a', '2016-06-16 16:54:19', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_baf3a3b6dc3a', 'sandeep', 'Not Assigned', 'Kochi', '2016-06-17 14:46:23', 'kan_f89c4cc5fcd5', 'a', 'Registration Follow Up', '2', '', 'Master-MA Malayalam', '', '', '', 'sandeep', '2016-06-17 14:46:23', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_bafcf683cf47', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:03:12', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-16 12:03:12', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_bb5936262c91', 'sheela', 'sheela', 'Kollam', null, 'koc_4b1623ba84a3', 'sheela', 'Assessment Pending', '4', '', 'Bachelor-BSc cs', '', '', '', 'sheela', '2016-06-17 15:00:22', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_bbc5012d95a5', 'sheela', 'a', 'Kollam', '2016-06-17 15:33:55', 'kol_b63d0fcd8aef', 'a', 'Registration Follow Up', '4', '', '', '', 'IELTS', '', 'sheela', '2016-06-17 15:33:55', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_bc0eaa45267e', 'Self Assigned', 'sheela', 'Kollam', '2016-06-17 15:58:04', 'kol_eb5db4536e5b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'sheela', '2016-06-17 15:58:04', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_bc8eab2dcabb', 'b', 'Not Assigned', 'Kollam', '2016-06-14 09:58:04', 'kol_b379890ae41d', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-14 09:58:04', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_bc92bd5a20f3', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:27:50', 'kan_0814cfa90746', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sumeshzoft', '2016-06-14 17:27:50', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_bc9ac75dd6e5', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 17:12:15', 'kan_64d0ee4ec807', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sumeshzoft', '2016-06-13 17:12:13', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_bccfccba631d', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-15 17:12:45', 'kan_d8377f8e03ff', 'Not Assigned', 'null', '4', '', '', '', '', 'Teacher', 'jitha', '2016-06-15 17:12:45', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_bd040a89d77b', 'sandeep', 'Not Assigned', 'Kochi', '2016-06-17 13:58:24', 'koc_b896ec0b0699', 's', 'Registration Follow Up', '3', '', 'Bachelor-BA History', '', '', '', 'sandeep', '2016-06-17 13:58:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_bd18c868396a', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-15 17:12:44', 'kan_64d0ee4ec807', 'Not Assigned', 'null', '1', '', '', '', '', 'Software Engineer', 'jitha', '2016-06-15 17:12:44', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_bd4516553f20', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 12:47:34', 'kol_af76ca381901', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-14 12:47:34', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_bd47f39eb78d', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:01:07', 'kan_0999ea3e41ec', 'Not Assigned', 'null', '1', '', 'Bachelor-Btech', '', '', '', 'jitha', '2016-06-14 17:01:07', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_bdcf5fa4a770', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 15:09:28', 'kol_79f0550ce37c', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'sheela', '2016-06-13 15:09:27', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_be061b03c4a8', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 16:10:41', 'kan_4f9cddfd4e5c', 'Not Assigned', 'null', '1', '', 'Bachelor-BSc cs', '', '', '', 'jitha', '2016-06-13 16:10:39', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_be3bc7965409', 'Self Assigned', 'a', 'Kollam', '2016-06-17 11:27:45', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-17 11:27:46', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_be583fec8c94', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 11:31:05', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-14 11:31:05', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_bec3b9270562', 'Self Assigned', 'sheela', 'Kollam', '2016-06-17 12:08:45', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'sheela', '2016-06-17 12:08:44', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_bed5754b2999', 's', 's', 'Kochi', null, 'koc_e454886ba276', 's', 'Assessment Pending', '1', 'call later', '', '', '', '', 's', '2016-06-13 17:39:28', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_bee976db154a', 'a', 'Not Assigned', 'Kollam', '2016-06-17 12:01:37', 'kol_b89f7b63f70f', 'sheela', 'Registration Follow Up', '1', '', '', '', 'Personality development', '', 'a', '2016-06-17 12:01:37', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_bf49d2da4fe6', 'b', 'sanoop', 'Kollam', null, 'kol_d435309754f4', 'sanoop', 'Assessment Pending', '4', 'gfhg', 'Bachelor-BSc cs', '', '', '', 'b', '2016-06-20 10:25:57', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_bfd0dd1cd02e', 'sheela', 'a', 'Kollam', '2016-06-13 11:36:42', 'kol_66ebfd1a5540', 'a', 'Fee Payment Follow up', '7', 'try', '', '', '', '', 'sheela', '2016-06-13 11:36:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c086a827a89c', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:03:00', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 12:03:00', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c0b87b9cb0d6', 'b', 'Not Assigned', 'Kollam', '2016-06-13 14:10:13', 'kol_79f0550ce37c', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-13 14:10:12', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c0bdd105c347', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 13:06:53', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 13:06:53', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c1358b38f636', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:49:13', 'kol_2bd08b71d7ea', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-16 15:49:13', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c1459fc1e85e', 'b', 'Not Assigned', 'Kollam', '2016-06-13 10:42:19', 'kol_87b16f6c6dae', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-13 10:42:20', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c14aa1da2679', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 15:04:57', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-14 15:04:57', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c14cfe81ea4e', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 10:33:02', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-17 10:33:02', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c183fa29858d', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 15:23:08', 'kol_902ef03d9e76', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-17 15:23:08', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c20e45f6fda5', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-20 14:41:20', 'kol_66a39843f66c', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'a', '2016-06-20 14:41:20', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c2c3af697784', 'sheela', 'Not Assigned', 'Kollam', '2016-06-13 10:46:23', 'kol_d435309754f4', 'a', 'Registration Follow Up', '1', '', '', '', '', '', 'sheela', '2016-06-13 10:46:23', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c366c1b5c287', 'sheela', 'a', 'Kollam', '2016-06-13 11:28:34', 'kol_d435309754f4', 'a', 'Registration Follow Up', '1', '', '', '', '', '', 'sheela', '2016-06-13 11:28:34', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c3c243c6f243', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 15:11:05', 'kol_0c1764954c6d', 'Not Assigned', 'null', '7', 't65465', '', '', '', '', 'sanoop', '2016-06-13 15:11:04', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c3e4075e9b43', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 16:39:53', 'kol_fe7fcbb38341', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'a', '2016-06-13 16:39:51', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c3ff9ba196be', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:33:39', 'koc_00c9d19f0ec4', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-13 15:33:37', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c409599fb6c4', 'Self Assigned', 'sandeep', 'Kochi', '2016-06-17 14:14:00', 'kol_49c7d8d21eba', 'Not Assigned', 'null', '1', 'rutuytryrtyr', '', '', 'Spoken English', '', 'sandeep', '2016-06-17 14:14:00', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c44209baf774', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:15:32', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 17:15:32', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c46fa45fc031', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 09:46:46', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '7', 'not picking', '', '', '', '', 'sandeep', '2016-06-14 09:46:46', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c4ca5013d9db', 'sandy', 'Not Assigned', 'Kochi', '2016-06-13 15:07:03', 'koc_00c9d19f0ec4', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'sandy', '2016-06-13 15:07:02', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c4f753798204', 'sheela', 'a', 'Kollam', '2016-06-13 12:02:15', 'kol_178922ed2ba8', 'a', 'Fee Payment Follow up', '1', '', '', '', '', '', 'sheela', '2016-06-13 12:02:15', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c513aabf2c1b', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-15 17:22:17', 'kol_05dddca31839', 'Not Assigned', 'null', '7', 'tyeryert', '', '', 'Personality development', '', 'a', '2016-06-15 17:22:17', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c52cbfcd728d', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:22:25', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-16 12:22:25', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c53e0cf76b22', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 10:21:16', 'kol_0c1764954c6d', 'Not Assigned', 'null', '7', 't65465', '', '', '', '', 'a', '2016-06-16 10:21:16', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c5ca9c283ff4', 'Self Assigned', 's', 'Kochi', '2016-06-13 15:52:57', 'koc_78df79df877c', 'Not Assigned', 'null', '2', '', '', '', '', '', 's', '2016-06-13 15:52:56', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c5d83e030a4d', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-17 14:45:32', 'koc_b896ec0b0699', 'Not Assigned', 'null', '3', '', 'Bachelor-BA History', '', '', '', 'sandeep', '2016-06-17 14:45:32', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c5e31b9ace66', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 18:28:13', 'koc_00c9d19f0ec4', 'Not Assigned', 'null', '1', 'not picking', '', '', '', '', 'sandeep', '2016-06-13 18:28:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c5fceafef56f', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:40:30', 'kol_fe7fcbb38341', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Spoken English', '', 'a', '2016-06-16 16:40:30', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c62dd3889b79', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 18:38:48', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 18:38:48', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c63c001eea38', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:15:11', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 17:15:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c652339a209a', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:15:05', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 12:15:05', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c655761a6615', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 09:57:41', 'kan_4f9cddfd4e5c', 'Not Assigned', 'null', '1', '', 'Bachelor-BSc cs', '', '', '', 'jitha', '2016-06-14 09:57:41', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c6d328360b95', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 10:35:23', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 10:35:23', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c6fe3cc35c80', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 10:36:47', 'kan_f89c4cc5fcd5', 'Not Assigned', 'null', '1', '', 'Master-MA Malayalam', '', '', '', 'sandeep', '2016-06-14 10:36:47', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c7488be1c944', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:28:58', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-13 15:28:57', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c7957de31f0e', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:52:24', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 14:52:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c84cda3ac663', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 11:27:27', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-17 11:27:28', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c87926e5bacd', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-15 17:22:10', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-15 17:22:10', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c8b15512846f', 'user3', 'Not Assigned', 'Kannur', '2016-06-14 09:46:34', 'kan_cd5e74d67cd1', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-14 09:46:34', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c8e001a20963', 'sandy', 'Not Assigned', 'Kochi', '2016-06-14 10:30:48', 'koc_a7041eface62', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'sandy', '2016-06-14 10:30:48', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c8ec1538b54f', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:40:53', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-16 14:40:53', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c910571a5560', 'b', 'Not Assigned', 'Kollam', '2016-06-14 10:07:21', 'kol_e77e9f96475b', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-14 10:07:21', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c923756b2977', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 15:42:16', 'kan_64d0ee4ec807', 'Not Assigned', 'null', '1', '', '', '', '', '', 'jitha', '2016-06-13 15:42:15', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c93e12761441', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:58:47', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 14:58:47', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c94ee316bc51', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 16:10:42', 'kan_fd9a9a06b595', 'Not Assigned', 'null', '1', '', '', '', '', '', 'jitha', '2016-06-13 16:10:40', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c9cce1cc531f', 'sandeep', 'Not Assigned', 'Kochi', '2016-06-17 13:48:05', 'kol_eb5db4536e5b', 's', 'Fee Payment Follow up', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'sandeep', '2016-06-17 13:48:06', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_c9e33c227564', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 11:11:25', 'kan_60284b9c5c17', 'Not Assigned', 'null', '1', '', 'Language Course-Spoken English', '', '', '', 's', '2016-06-14 11:11:25', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ca2a4f1be5a3', 's', 'sandeep', 'Kochi', '2016-06-17 14:30:27', 'kan_60284b9c5c17', 'sandeep', 'Registration Follow Up', '4', 'kghjkfghj', 'Language Course-Spoken English', '', '', '', 's', '2016-06-17 14:30:28', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ca2d9c67f0ed', 'sandeep', 's', 'Kochi', '2016-06-17 13:57:24', 'koc_b896ec0b0699', 's', 'Registration Follow Up', '3', '', 'Bachelor-BA History', '', '', '', 'sandeep', '2016-06-17 13:57:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ca9b265d5624', 'Self Assigned', 'sheela', 'Kollam', '2016-06-17 12:08:34', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'sheela', '2016-06-17 12:08:34', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_cab33e96aea5', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 18:45:20', 'koc_4b1623ba84a3', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-13 18:45:18', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_cac9cb47967e', 'Self Assigned', 'jitha', 'Kannur', '2016-06-16 09:28:33', 'kol_4d5f5f43ac8b', 'Not Assigned', 'null', '1', '', '', 'Architect', '', '', 'jitha', '2016-06-16 09:28:33', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_cb0bc96db944', 'b', 'Not Assigned', 'Kollam', '2016-06-20 14:27:15', 'kol_2b3eb6eaddd3', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-20 14:27:15', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_cb29af488c92', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:59:04', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 14:59:04', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_cb30d7c663b3', 'sanoop', 'Not Assigned', 'Kollam', '2016-06-13 15:01:33', 'kol_05dddca31839', 'a', 'Ticketing Follow up', '7', 'tyeryert', '', '', 'Personality development', '', 'sanoop', '2016-06-13 15:01:32', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_cbd024c4eaf1', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:43:57', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-16 14:43:57', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_cbd20ea9ce24', 'Self Assigned', 's', 'Kochi', '2016-06-13 15:53:06', 'koc_4b1623ba84a3', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:53:05', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_cc29038d2075', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 15:42:29', 'kan_67aee97bdabe', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'sumeshzoft', '2016-06-13 15:42:27', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ccd4570d060f', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:21:15', 'kol_399d5eb36484', 'Not Assigned', 'null', '1', '', '', '', '', '', 'a', '2016-06-16 17:21:15', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_cce0de87d74b', 'user3', 'Not Assigned', 'Kannur', '2016-06-13 15:40:06', 'kan_e73e0db3ce2c', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-13 15:40:05', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_cd87173f7eaf', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:11:49', 'kol_b89f7b63f70f', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-16 16:11:48', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_cdaa8bd65a43', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 16:33:35', 'kan_a8087d458991', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'sumeshzoft', '2016-06-13 16:33:33', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_cdc7362f186f', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 15:46:14', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-14 15:46:14', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_cdddbef869b3', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:01:09', 'kan_a2c0869fdcde', 'Not Assigned', 'null', '1', '', '', '', '', '', 'jitha', '2016-06-14 17:01:09', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_cdf0ce0ad338', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:22:31', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 12:22:31', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_cdfa3a5e77e1', 'Self Assigned', 'a', 'Kollam', '2016-06-20 15:22:29', 'kol_66a39843f66c', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'a', '2016-06-20 15:22:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_cdfc7e58524a', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 14:39:34', 'kol_0c1764954c6d', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sanoop', '2016-06-13 14:39:33', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ce182ab4062c', 'b', 'Not Assigned', 'Kollam', '2016-06-13 14:26:05', 'kol_399d5eb36484', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-13 14:26:05', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ce7331d60124', 'sandeep', 'Not Assigned', 'Kochi', '2016-06-17 14:26:20', 'koc_619929d9bc25', 's', 'Registration Follow Up', '7', 'fgh', '', '', '', '', 'sandeep', '2016-06-17 14:26:20', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_cf97bd724bc5', 'user3', 'sumeshzoft', 'Kannur', '2016-06-14 10:39:29', 'kan_1efb63d594e0', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-14 10:39:29', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_cfd9e7813206', 'a', 'Not Assigned', 'Kollam', '2016-06-17 12:00:15', 'kol_399d5eb36484', 'sheela', 'Registration Follow Up', '1', '', '', '', '', '', 'a', '2016-06-17 12:00:15', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d001be506a20', 'a', 'Not Assigned', 'Kollam', '2016-06-17 12:00:42', 'kol_79f0550ce37c', 'sheela', 'Registration Follow Up', '1', '', '', '', 'Spoken English', '', 'a', '2016-06-17 12:00:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d032f7a0cb43', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 09:57:07', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-17 09:57:07', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d05c317af316', 'a', 'sanoop', 'Kollam', '2016-06-17 13:43:30', 'kol_178922ed2ba8', 'sanoop', 'Fee Payment Follow up', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-17 13:43:30', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d072295ca653', 's', 'Not Assigned', 'Kochi', '2016-06-13 20:22:21', 'koc_cdc8ad6f110f', 's', 'Assessment Pending', '6', 'not picking', '', '', '', '', 's', '2016-06-13 16:22:26', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d0a03670100d', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-15 18:26:39', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-15 18:26:38', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d0efc96c5d48', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 10:45:47', 'kan_3811aca90659', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'jitha', '2016-06-14 10:45:47', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d14fa5d99383', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 10:33:25', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 10:33:25', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d1a4db6de0ae', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:44:17', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 17:44:17', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d1f174772f42', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:44:23', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-16 17:44:23', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d233c54cf959', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:25:30', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 14:25:30', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d34db044ec12', 'sandy', 'Not Assigned', 'Kochi', '2016-06-13 15:10:07', 'koc_4b1623ba84a3', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'sandy', '2016-06-13 15:10:06', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d3558c1c7216', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:22:50', 'kol_d0cc31695858', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'jitha', '2016-06-14 17:22:50', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d3cc72d8abc1', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:52:19', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 14:52:19', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d426a8881270', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:47:10', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 12:47:10', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d45ba7ed9755', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 18:17:14', 'kan_a8087d458991', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'jitha', '2016-06-14 18:17:14', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d4b9e4d71b7d', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-17 13:58:41', 'koc_a7041eface62', 'Not Assigned', 'null', '1', '', 'Bachelor-BSc cs', '', '', '', 's', '2016-06-17 13:58:41', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d4c3cb75583b', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:17:12', 'koc_4b1623ba84a3', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:17:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d4f6d6546e35', 'keerthykp', 'a', 'Kollam', '2016-06-17 14:20:51', 'kol_1b03a7918ff0', 'enquiry', 'Assessment Pending', 'Assessment Pending', 'ghgfjfgh', '', '', '', '', 'keerthykp', '2016-06-17 14:20:51', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d53706770c06', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 13:07:04', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 13:07:04', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d567bea2dc84', 'user3', 'Not Assigned', 'Kannur', '2016-06-13 15:06:23', 'kan_a8087d458991', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-13 15:06:22', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d602b17e0709', 'Self Assigned', 'sheela', 'Kollam', '2016-06-17 12:24:13', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'sheela', '2016-06-17 12:24:13', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d6402dcefd5b', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-17 14:09:47', 'koc_a7041eface62', 'Not Assigned', 'null', '1', '', 'Bachelor-BSc cs', '', '', '', 'sandeep', '2016-06-17 14:09:47', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d6566b3b2a4f', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 15:04:17', 'kol_0c1764954c6d', 'Not Assigned', 'null', '7', 't65465', '', '', '', '', 'a', '2016-06-13 15:04:15', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d66829710b52', 'sanoop', 'Not Assigned', 'Kollam', '2016-06-13 16:54:03', 'kol_0c1764954c6d', 'sanoop', 'Assessment Pending', '7', 't65465', '', '', '', '', 'sanoop', '2016-06-13 14:54:12', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d6c8497afc87', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:19:59', 'kol_902ef03d9e76', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 15:19:59', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d6ca090399e4', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:02:54', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 12:02:54', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d6e0992785cb', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:19:24', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-16 17:19:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d6ec9da3d1b9', 'Self Assigned', 'a', 'Kollam', '2016-06-17 11:27:54', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-17 11:27:54', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d72282cf54f2', 'sandeep', 's', 'Kochi', '2016-06-17 14:28:15', 'kan_60284b9c5c17', 's', 'Registration Follow Up', '2', 'call back', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-17 14:28:15', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d73385a122f1', 'sandeep', 'Not Assigned', 'Kochi', null, 'koc_b95c59f14d15', 'sandeep', 'Assessment Pending', '3', '', '', '', '', '', 'sandeep', '2016-06-14 12:36:45', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d7432c85b76f', 'b', 'Not Assigned', 'Kollam', '2016-06-13 14:25:10', 'kol_8cdccfd7fe58', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-13 14:25:09', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d7512859374f', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 15:59:24', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-14 15:59:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d77fa5296dfa', 'b', 'sheela', 'Kollam', '2016-06-14 14:17:19', 'kol_a8717f31260b', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-14 14:17:19', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d7814408193d', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 17:12:13', 'kan_fd9a9a06b595', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sumeshzoft', '2016-06-13 17:12:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d7946e3e6b01', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 17:45:46', 'koc_e454886ba276', 'Not Assigned', 'null', '1', 'call later', '', '', '', '', 's', '2016-06-13 17:45:45', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d7bfd378bb6f', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 12:08:40', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'sheela', '2016-06-17 12:08:40', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d7c222558e01', 's', 'sandeep', 'Kochi', '2016-06-17 13:58:00', 'koc_cc1b4827b853', 'sandeep', 'Registration Follow Up', '1', '', 'Language Course-Spoken English', '', '', '', 's', '2016-06-17 13:58:00', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d88bc167547f', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:44:01', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 16:44:01', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d95bf78285e7', 'Self Assigned', 'sandeep', 'Kochi', '2016-06-17 13:47:19', 'koc_b95c59f14d15', 'Not Assigned', 'null', '3', '', 'Master-science', '', '', '', 'sandeep', '2016-06-17 13:47:19', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d96a710b7321', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 10:20:35', 'kol_902ef03d9e76', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'sheela', '2016-06-17 10:20:35', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d974c4404537', 'Self Assigned', 'sandeep', 'Kochi', '2016-06-17 14:13:21', 'koc_8754df24a47b', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'sandeep', '2016-06-17 14:13:21', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d9e09ee06d1d', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 12:13:16', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'okey i willl', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'sheela', '2016-06-17 12:13:16', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d9eb6b2c1274', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:06:09', 'kol_902ef03d9e76', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 15:06:09', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_d9faf4d72631', 's', 'Not Assigned', 'Kochi', '2016-06-17 14:18:40', 'koc_8754df24a47b', 'sandeep', 'Registration Follow Up', '1', '', '', '', 'IELTS', '', 's', '2016-06-17 14:18:40', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_da72a650961c', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:20:11', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 15:20:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_db46c613a69b', 'sandeep', 'Not Assigned', 'Kochi', '2016-06-17 13:58:24', 'koc_cc1b4827b853', 's', 'Registration Follow Up', '1', '', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-17 13:58:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_db6d430f1446', 'keerthykp', 'sheela', 'Kollam', '2016-06-17 14:42:17', 'kol_b29f45a47919', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'keerthykp', '2016-06-17 14:42:17', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_dbcaa4f1e269', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 10:22:32', 'kol_b379890ae41d', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'a', '2016-06-16 10:22:32', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_dbe32583eaa1', 'user3', 'Not Assigned', 'Kannur', null, 'kan_fd9a9a06b595', 'Not Assigned', 'Assessment Pending', '4', '', 'Master-MTECH', '', '', '', 'user3', '2016-06-14 15:38:50', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_dc094d905392', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 11:31:06', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-16 11:31:06', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_dcb9f95af0f0', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-20 14:49:36', 'kol_17cf27990d9e', 'Not Assigned', 'null', '3', '', '', '', 'IELTS', '', 'a', '2016-06-20 14:49:36', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_dd3f3b36e76f', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:11:43', 'kol_43c73f9a76f6', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'a', '2016-06-16 16:11:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_dd4401d84f60', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:53:26', 'kol_fe7fcbb38341', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Spoken English', '', 'a', '2016-06-16 14:53:26', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_dd63c5c64e32', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:19:54', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-16 15:19:54', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_dd7984571990', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 11:38:03', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 11:38:03', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_dd95164b6442', 'a', 'sheela', 'Kollam', '2016-06-13 10:44:10', 'kol_d435309754f4', 'sheela', 'Fee Payment Follow up', '1', '', '', '', '', '', 'a', '2016-06-13 10:44:10', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_de14e50e4899', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 10:59:38', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-17 10:59:38', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_de2ce828d028', 'a', 'sheela', 'Kollam', '2016-06-17 15:00:07', 'koc_4b1623ba84a3', 'sheela', 'Registration Follow Up', '4', '', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-17 15:00:07', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_de2ddacda8a3', 'sandeep', 'Not Assigned', 'Kochi', null, 'kan_60284b9c5c17', 'sandeep', 'Assessment Pending', '3', 'call back', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-17 14:27:45', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_de3490900f95', 'sandeep', 'sandeep', 'Kochi', null, 'koc_b95c59f14d15', 'sandeep', 'Assessment Pending', '2', '', '', '', '', '', 'sandeep', '2016-06-14 12:41:15', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_de722e03d9c6', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 10:20:11', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-17 10:20:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_dec537935fed', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 11:42:14', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'fds', '', '', '', '', 'a', '2016-06-14 11:42:14', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_dee793d26ee4', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:57:29', 'kan_fadfe7897cb7', 'Not Assigned', 'null', '1', '', '', '', 'FRENCH', '', 'jitha', '2016-06-14 17:57:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_df6fbf22fed7', 'user3', 'Not Assigned', 'Kannur', null, 'kan_f89c4cc5fcd5', 'Not Assigned', 'Assessment Pending', '2', '', 'Master-MA Malayalam', '', '', '', 'user3', '2016-06-14 16:05:14', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e02e43db21cb', 'a', 'Not Assigned', 'Kollam', '2016-06-29 15:23:04', 'kol_2b3eb6eaddd3', 'a', 'Assessment Pending', '9', 'gvf', '', '', 'IELTS', '', 'a', '2016-06-20 15:23:09', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e0976ebb4975', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:27:04', 'koc_00c9d19f0ec4', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:27:03', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e100c97a91d6', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:36:53', 'kol_d33b0ffcb552', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:36:52', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e12a5a411048', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:53:47', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 12:53:47', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e133f57159dc', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 11:57:52', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-17 11:57:52', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e14f438f4609', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:37:48', 'kol_b379890ae41d', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'a', '2016-06-16 15:37:48', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e168da0a2b85', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:45:47', 'kan_0e2069aaa132', 'Not Assigned', 'null', '1', '', '', '', '', 'Software Engineer', 'jitha', '2016-06-14 17:45:47', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e16e03471de7', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:52:35', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 14:52:35', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e17d4de5b8ab', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 14:31:09', 'kol_fe7fcbb38341', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'sanoop', '2016-06-13 14:31:08', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e1dc10b0be75', 's', 'Not Assigned', 'Kochi', null, 'koc_e454886ba276', 's', 'Assessment Pending', '1', 'call later', '', '', '', '', 's', '2016-06-13 17:39:41', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e24387b7c429', 'Self Assigned', 'a', 'Kollam', '2016-06-17 12:43:47', 'kol_79f0550ce37c', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'a', '2016-06-17 12:43:47', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e2500964822d', 'Self Assigned', 'sandeep', 'Kochi', '2016-06-17 14:12:45', 'koc_228d6705c090', 'Not Assigned', 'null', '1', '', '', 'Doctor/Surgen', '', '', 'sandeep', '2016-06-17 14:12:45', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e2630229a7ec', 's', 'Not Assigned', 'Kochi', '2016-06-13 15:53:43', 'koc_cdc8ad6f110f', 'sandeep', 'Registration Follow Up', '3', '', '', '', '', '', 's', '2016-06-13 15:53:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e292d6173b93', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-15 17:13:14', 'kol_4d5f5f43ac8b', 'Not Assigned', 'null', '1', '', '', 'Architect', '', '', 'jitha', '2016-06-15 17:13:14', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e2d26ab5e1ed', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:38:45', 'kan_4f9cddfd4e5c', 'Not Assigned', 'null', '4', '', 'Bachelor-BSc cs', '', '', '', 'sumeshzoft', '2016-06-14 17:38:45', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e2eead853d51', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-17 13:45:09', 'koc_dfca21935856', 'Not Assigned', 'null', '1', '', 'Bachelor-B.COM', '', '', '', 'sandeep', '2016-06-17 13:45:09', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e2ff02acdfd0', 'sheela', 'Not Assigned', 'Kollam', '2016-06-17 14:24:58', 'kol_60c98adacc3e', 'a', 'Fee Payment Follow up', '6', 'fegergerge', '', '', 'Personality development', '', 'sheela', '2016-06-17 14:24:58', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e31436998c20', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 10:56:06', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 10:56:06', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e33e36296d51', 'Self Assigned', 'a', 'Kollam', '2016-06-17 15:23:14', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-17 15:23:14', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e363aad02a6f', 'Self Assigned', 'sandeep', 'Kochi', '2016-06-17 14:39:34', 'kol_eb5db4536e5b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'sandeep', '2016-06-17 14:39:34', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e3771ad6b188', 'user3', 'Not Assigned', 'Kannur', '2016-06-14 09:42:22', 'kan_60284b9c5c17', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-14 09:42:22', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e3d267bade1c', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:59:34', 'kol_fe7fcbb38341', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Spoken English', '', 'a', '2016-06-16 14:59:34', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e4074a5de2b8', 'a', 'Not Assigned', 'Kollam', '2016-06-17 15:34:27', 'kol_b63d0fcd8aef', 'sheela', 'Registration Follow Up', '4', '', '', '', 'IELTS', '', 'a', '2016-06-17 15:34:27', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e41c8ec51cc1', 'sandeep', 'Not Assigned', 'Kochi', '2016-06-17 13:58:24', 'kan_60284b9c5c17', 's', 'Registration Follow Up', '3', '', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-17 13:58:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e42140a7ca4d', 'sandy', 'Not Assigned', 'Kochi', '2016-06-14 10:57:57', 'koc_dfca21935856', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'sandy', '2016-06-14 10:57:57', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e42fc659667f', 'a', 'Not Assigned', 'Kollam', '2016-06-17 13:43:09', 'kol_fe7fcbb38341', 'sheela', 'Registration Follow Up', '6', 'rtret', 'PhD/Doctorate-Doctor of Psychology', '', 'Spoken English', '', 'a', '2016-06-17 13:43:10', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e436781f16b7', 'user3', 'Not Assigned', 'Kannur', '2016-06-13 15:04:18', 'kan_64d0ee4ec807', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-13 15:04:17', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e4f69d31ab98', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-20 15:08:23', 'kol_66a39843f66c', 'Not Assigned', 'null', '1', '', '', '', 'IELTS', '', 'a', '2016-06-20 15:08:23', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e51d7139074e', 'b', 'sandeep', 'Kollam', '2016-06-14 17:43:24', 'kol_eb5db4536e5b', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-14 17:43:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e53070971800', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 18:03:06', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '6', 'not picking', '', '', '', '', 'sandeep', '2016-06-13 18:03:04', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e58815aac63e', 'sheela', 'Not Assigned', 'Kollam', '2016-06-17 12:24:40', 'kol_e77e9f96475b', 'a', 'Registration Follow Up', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'sheela', '2016-06-17 12:24:40', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e600505ab5d0', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 14:58:35', 'kol_0c1764954c6d', 'Not Assigned', 'null', '7', 't65465', '', '', '', '', 'sanoop', '2016-06-13 14:58:34', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e611c3007e8b', 'a', 'a', 'Kollam', null, 'kol_1b03a7918ff0', 'a', 'Assessment Pending', '4', 'ghgfjfgh', '', '', '', '', 'a', '2016-06-17 15:41:23', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e6253b0e4aae', 'Self Assigned', 'a', 'Kollam', '2016-06-13 11:27:31', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', '', '', '', '', '', 'a', '2016-06-13 11:27:31', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e63344b5352b', 'Self Assigned', 'sandeep', 'Kochi', '2016-06-14 12:40:25', 'koc_b95c59f14d15', 'Not Assigned', 'null', '3', '', '', '', '', '', 'sandeep', '2016-06-14 12:40:25', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e7035bbb91d2', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 14:22:07', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'fds', '', '', '', '', 'sanoop', '2016-06-13 14:22:07', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e703d1889471', 'keerthykp', 'a', 'Kollam', '2016-06-17 14:10:12', 'kol_b63d0fcd8aef', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'keerthykp', '2016-06-17 14:10:12', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e78591171485', 's', 'Not Assigned', 'Kochi', '2016-06-13 17:29:26', 'koc_cdc8ad6f110f', 'jitha', 'Fee Payment Follow up', '6', 'not picking', '', '', '', '', 's', '2016-06-13 17:29:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e7a593c2677d', 'a', 'Not Assigned', 'Kollam', '2016-06-17 15:16:36', 'kol_6fd78a762953', 'sheela', 'Registration Follow Up', '4', '', '', '', '', '', 'a', '2016-06-17 15:16:36', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e7c458dba332', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 15:35:12', 'kan_a8087d458991', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'sumeshzoft', '2016-06-13 15:35:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e7df85c83ef6', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:44:27', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 16:44:26', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e83c22b000f7', 'sheela', 'a', 'Kollam', '2016-06-17 12:24:27', 'kol_fe7fcbb38341', 'a', 'Registration Follow Up', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Spoken English', '', 'sheela', '2016-06-17 12:24:28', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e8ab9521fe6a', 'Self Assigned', 'a', 'Kollam', '2016-06-17 09:59:14', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-17 09:59:14', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e8d108a08cbb', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:53:24', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'okey i willl', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'a', '2016-06-16 14:53:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e8eeb1df5fdc', 'a', 'a', 'Kollam', '2016-06-17 19:42:11', 'kol_d435309754f4', 'a', 'Assessment Pending', '8', 'gfhg', '', '', '', '', 'a', '2016-06-13 11:42:16', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e8f551f2694a', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:25:36', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 14:25:36', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e9189e3b540e', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 11:05:33', 'kan_f89c4cc5fcd5', 'Not Assigned', 'null', '1', '', 'Master-MA Malayalam', '', '', '', 'sandeep', '2016-06-14 11:05:33', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e94ea1f4d0bf', 'Self Assigned', 'a', 'Kollam', '2016-06-13 13:02:37', 'kol_d435309754f4', 'Not Assigned', 'null', '8', 'gfhg', '', '', '', '', 'a', '2016-06-13 13:02:37', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e96d0d87f019', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 16:16:24', 'kan_faa02d7d2026', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'sumeshzoft', '2016-06-13 16:16:23', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e9a12abf63e6', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 18:38:43', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 18:38:43', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e9b5a6630fda', 'a', 'a', 'Kollam', null, 'kol_6fd78a762953', 'a', 'Assessment Pending', '4', '', '', '', '', '', 'a', '2016-06-17 15:16:30', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_e9c284d8dd2d', 'a', 'Not Assigned', 'Kollam', '2016-06-17 10:43:48', 'kol_178922ed2ba8', 'sheela', 'Registration Follow Up', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-17 10:43:48', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ea73841d14e3', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 17:09:35', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-14 17:09:35', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_eb17a0d6c700', 'a', 'sheela', 'Kollam', '2016-06-13 11:18:51', 'kol_87b16f6c6dae', 'sheela', 'Registration Follow Up', '7', 'fds', '', '', '', '', 'a', '2016-06-13 11:18:51', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_eb30ca8c4b4a', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 13:15:27', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'fds', '', '', '', '', 'sanoop', '2016-06-13 13:15:27', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_eba79da9276d', 'sheela', 'a', 'Kollam', '2016-06-13 11:43:42', 'kol_d435309754f4', 'a', 'Fee Payment Follow up', '8', 'gfhg', '', '', '', '', 'sheela', '2016-06-13 11:43:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ebb37c632b1b', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 10:54:08', 'kol_05dddca31839', 'Not Assigned', 'null', '7', 'tyeryert', '', '', 'Personality development', '', 'a', '2016-06-16 10:54:08', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ebf538e659de', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 19:01:45', 'koc_e454886ba276', 'Not Assigned', 'null', '1', 'call later', '', '', '', '', 'sandeep', '2016-06-13 19:01:43', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ec63ae072943', 'user3', 'sumeshzoft', 'Kannur', null, 'kan_9f3f69f63db5', 'sumeshzoft', 'Assessment Pending', '2', '', '', 'Architect', '', '', 'user3', '2016-06-15 17:37:01', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ec75aaaf834b', 'user3', 'sumeshzoft', 'Kannur', null, 'kan_0814cfa90746', 'sumeshzoft', 'Assessment Pending', '2', '', '', '', '', 'Chemical Engineer', 'user3', '2016-06-15 17:46:16', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ec8b69dfdc69', 'sandeep', 's', 'Kochi', '2016-06-17 13:57:24', 'koc_cc1b4827b853', 's', 'Registration Follow Up', '1', '', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-17 13:57:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ec8ddf4d7fa4', 'Self Assigned', 'jitha', 'Kannur', '2016-06-13 17:10:13', 'kan_a8087d458991', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'jitha', '2016-06-13 17:10:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ecb703ae6d47', 'a', 'sheela', 'Kollam', '2016-06-13 11:28:02', 'kol_178922ed2ba8', 'sheela', 'Fee Payment Follow up', '1', '', '', '', '', '', 'a', '2016-06-13 11:28:02', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ecc8d8a2e5d2', 'b', 'Not Assigned', 'Kollam', '2016-06-14 09:54:32', 'kol_43c73f9a76f6', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-14 09:54:32', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ecfb4e183ed2', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 16:22:51', 'kan_67aee97bdabe', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'sumeshzoft', '2016-06-13 16:22:49', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ed0aa6828a7c', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 12:47:19', 'kan_60284b9c5c17', 'Not Assigned', 'null', '1', '', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-14 12:47:19', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ed0f22457d58', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:55:59', 'kol_902ef03d9e76', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 16:55:59', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ed30e9dc0694', 'user3', 'Not Assigned', 'Kannur', '2016-06-14 15:21:10', 'kan_76371a02a618', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-14 15:21:10', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ed3e58ec2ce9', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:21:25', 'kol_05dddca31839', 'Not Assigned', 'null', '7', 'tyeryert', '', '', 'Personality development', '', 'a', '2016-06-16 17:21:25', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_edd7bddb8530', 'Self Assigned', 's', 'Kochi', '2016-06-13 17:37:42', 'koc_e454886ba276', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 17:37:41', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ee8714dff6a4', 'Self Assigned', 'a', 'Kollam', '2016-06-13 14:57:03', 'kol_399d5eb36484', 'Not Assigned', 'null', '1', '', '', '', '', '', 'a', '2016-06-13 14:57:02', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_eeb1c73e5281', 'sheela', 'sheela', 'Kollam', '2016-06-13 14:33:46', 'kol_d435309754f4', 'sheela', 'Assessment Pending', '5', 'gfhg', '', '', '', '', 'sheela', '2016-06-13 11:33:57', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_efc4cf0705f0', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 17:09:30', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-14 17:09:30', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_efdf97a07636', 'sandeep', 'sandeep', 'Kochi', null, 'kan_60284b9c5c17', 'sandeep', 'Assessment Pending', '2', 'call back', 'Language Course-Spoken English', '', '', '', 'sandeep', '2016-06-17 14:28:07', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f0256378e925', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:11:32', 'kol_902ef03d9e76', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'a', '2016-06-16 16:11:32', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f04903190410', 'user3', 'Not Assigned', 'Kannur', '2016-06-13 15:13:46', 'kan_805877d725d7', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-13 15:13:45', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f05c4c425c19', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 14:24:24', 'kol_178922ed2ba8', 'Not Assigned', 'null', '6', 'hjk', '', '', '', '', 'sanoop', '2016-06-13 14:24:23', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f0805ee16bdf', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 15:21:30', 'koc_78df79df877c', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 15:21:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f13886e82529', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 12:19:31', 'kan_4f9cddfd4e5c', 'Not Assigned', 'null', '1', '', 'Bachelor-BSc cs', '', '', '', 'jitha', '2016-06-14 12:19:31', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f170ca386fec', 'b', 'Not Assigned', 'Kollam', '2016-06-13 14:26:55', 'kol_b9749879089d', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-13 14:26:55', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f177cf713b5c', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 13:42:05', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-17 13:42:05', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f1c7822e2cb7', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 15:47:52', 'kan_fd9a9a06b595', 'Not Assigned', 'null', '1', '', '', '', '', '', 'jitha', '2016-06-13 15:47:50', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f1f5f9662c4b', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 18:24:42', 'koc_b95c59f14d15', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-13 18:24:40', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f219883fe648', 'b', 'Not Assigned', 'Kollam', '2016-06-14 13:55:18', 'kol_ebae93075120', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-14 13:55:18', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f2a36c46149a', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:58:42', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'okey i willl', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'a', '2016-06-16 14:58:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f335a77dd1d4', 'user3', 'Not Assigned', 'Kannur', '2016-06-14 10:34:22', 'kan_7d4957ffac9c', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'user3', '2016-06-14 10:34:22', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f36cb1394f20', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-14 12:47:27', 'koc_cc1b4827b853', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-14 12:47:27', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f3774a41d8f6', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 09:59:19', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-17 09:59:19', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f37784f39c63', 'sandeep', 'Not Assigned', 'Kochi', null, 'koc_b95c59f14d15', 'sandeep', 'Assessment Pending', '3', '', '', '', '', '', 'sandeep', '2016-06-14 15:21:25', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f380bfe9a70b', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 10:43:30', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', '', '', '', '', '', 'a', '2016-06-13 10:43:31', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f3c6dc8fe01b', 'sheela', 'Not Assigned', 'Kollam', '2016-06-17 12:52:20', 'kol_e77e9f96475b', 'a', 'Registration Follow Up', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'sheela', '2016-06-17 12:52:20', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f3c7ce2e4cb0', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:22:47', 'kan_3811aca90659', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'jitha', '2016-06-14 17:22:47', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f3cafd507eb6', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-13 15:47:56', 'kan_805877d725d7', 'Not Assigned', 'null', '1', '', '', '', '', '', 'jitha', '2016-06-13 15:47:54', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f40d7388bdfb', 'a', 'sheela', 'Kollam', '2016-06-13 12:35:18', 'kol_d435309754f4', 'sheela', 'Registration Follow Up', '6', 'gfhg', '', '', '', '', 'a', '2016-06-13 12:35:18', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f42fba7d0d5a', 'Self Assigned', 's', 'Kochi', '2016-06-13 17:33:14', 'koc_b95c59f14d15', 'Not Assigned', 'null', '1', '', '', '', '', '', 's', '2016-06-13 17:33:12', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f481ad274ab4', 'user3', 'Not Assigned', 'Kannur', null, 'kan_4f9cddfd4e5c', 'Not Assigned', 'Assessment Pending', '2', '', 'Bachelor-BSc cs', '', '', '', 'user3', '2016-06-16 09:25:55', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f49287d3aee9', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 18:17:10', 'kan_3811aca90659', 'Not Assigned', 'null', '1', '', '', '', 'Spoken English', '', 'jitha', '2016-06-14 18:17:10', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f4f9853514ff', 'b', 'Not Assigned', 'Kollam', '2016-06-20 13:08:19', 'kol_66a39843f66c', 'enquiry', 'Assessment Pending', 'Assessment Pending', '', '', '', '', '', 'b', '2016-06-20 13:08:19', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f577de0ee7ad', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-14 17:57:05', 'kan_4f9cddfd4e5c', 'Not Assigned', 'null', '4', '', 'Bachelor-BSc cs', '', '', '', 'jitha', '2016-06-14 17:57:05', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f5b18dba9daf', 'user3', 'jitha', 'Kannur', null, 'kan_d14f72fc2e86', 'jitha', 'Assessment Pending', '1', '', 'Language Course-Spoken English', '', 'Spoken English', '', 'user3', '2016-06-15 17:42:56', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f5d58690adc3', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 10:43:22', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '1', '', '', '', '', '', 'a', '2016-06-13 10:43:22', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f5f1a1d08010', 'Self Assigned', 'sheela', 'Kollam', '2016-06-17 12:24:19', 'kol_902ef03d9e76', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'sheela', '2016-06-17 12:24:19', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f62054356b41', 'a', 'Not Assigned', 'Kollam', '2016-06-15 14:15:52', 'kol_d435309754f4', 'a', 'Assessment Pending', '8', 'gfhg', '', '', '', '', 'a', '2016-06-13 13:01:59', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f687dc241310', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-13 19:00:31', 'kol_af76ca381901', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-13 19:00:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f6f1f52310a0', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 18:38:53', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 18:38:53', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f6f963dda896', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:25:24', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 14:25:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f76604d352d8', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 12:08:39', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 12:08:39', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f771c09f805a', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-17 14:03:29', 'koc_a7041eface62', 'Not Assigned', 'null', '1', '', 'Bachelor-BSc cs', '', '', '', 'sandeep', '2016-06-17 14:03:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f822f16dd4cd', 'sandeep', 'Not Assigned', 'Kochi', '2016-06-17 14:12:57', 'koc_228d6705c090', 's', 'Registration Follow Up', '1', '', '', 'Doctor/Surgen', '', '', 'sandeep', '2016-06-17 14:12:57', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f88a8d383dce', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 10:37:47', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'okey i willl', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'a', '2016-06-17 10:37:47', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f8c0c2780797', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-17 10:33:07', 'kol_e77e9f96475b', 'Not Assigned', 'null', '1', '', 'PhD/Doctorate-Doctor of Social Work', '', '', '', 'a', '2016-06-17 10:33:07', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f90361e2f5ce', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 15:48:49', 'kol_b89f7b63f70f', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-16 15:48:49', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f91365e56376', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 16:50:05', 'kol_0c1764954c6d', 'Not Assigned', 'null', '7', 't65465', '', '', '', '', 'a', '2016-06-16 16:50:05', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f9887e11c686', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-13 17:48:43', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '1', '', '', '', 'Personality development', '', 'a', '2016-06-13 17:48:42', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f9966810bbf4', 'sheela', 'Not Assigned', 'Kollam', '2016-06-17 15:58:17', 'kol_eb5db4536e5b', 'a', 'Registration Follow Up', '1', '', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'sheela', '2016-06-17 15:58:17', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_f9dea5e8e63f', 'Self Assigned', 's', 'Kochi', '2016-06-13 16:22:15', 'koc_cdc8ad6f110f', 'Not Assigned', 'null', '6', 'not picking', '', '', '', '', 's', '2016-06-13 16:22:13', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_fa316d8c191f', 'Self Assigned', 'sheela', 'Kollam', '2016-06-17 12:51:45', 'kol_7c38fd4e7311', 'Not Assigned', 'null', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'sheela', '2016-06-17 12:51:45', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_fa5092108e4e', 'Self Assigned', 'Not Assigned', 'Kochi', '2016-06-17 14:03:24', 'koc_619929d9bc25', 'Not Assigned', 'null', '1', '', '', '', '', '', 'sandeep', '2016-06-17 14:03:24', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_fa9b95f13d9a', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 17:19:29', 'kol_87b16f6c6dae', 'Not Assigned', 'null', '9', 'okey i willl', 'PhD/Doctorate-Doctor of Psychology', '', '', '', 'a', '2016-06-16 17:19:29', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_fac75f75ff9c', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 15:27:46', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-14 15:27:46', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_fb38059789f1', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:40:46', 'kol_ebae93075120', 'Not Assigned', 'null', '1', '', 'Master-MA English', '', 'Personality development', '', 'a', '2016-06-16 14:40:46', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_fb935ab4cb0a', 'Self Assigned', 'Not Assigned', 'Kannur', '2016-06-15 11:35:22', 'koc_78df79df877c', 'Not Assigned', 'null', '2', '', 'Master-computer Application', '', '', '', 'sumeshzoft', '2016-06-15 11:35:22', null, null, 'y');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_fd95cac00d20', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 18:43:10', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-16 18:43:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_fe214add48e1', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-16 14:40:40', 'kol_178922ed2ba8', 'Not Assigned', 'null', '1', 'hjk', 'Master-computer Application', '', '', '', 'a', '2016-06-16 14:40:40', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_feef5985599a', 's', 's', 'Kochi', null, 'koc_78df79df877c', 's', 'Assessment Pending', '4', '', '', '', '', '', 's', '2016-06-13 15:40:53', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ff2476f0d8bd', 'Self Assigned', 'Not Assigned', 'Kollam', '2016-06-14 11:31:11', 'kol_66ebfd1a5540', 'Not Assigned', 'null', '7', 'try', 'Bachelor-BSc cs', '', '', '', 'a', '2016-06-14 11:31:11', null, null, 'n');
INSERT INTO `history_enquiry_assigning_tbl` VALUES ('his_ff982e5a8afb', 'sheela', 'Not Assigned', 'Kollam', '2016-06-17 12:52:06', 'kol_7c38fd4e7311', 'a', 'Registration Follow Up', '2', '', 'PhD/Doctorate-Doctor of Psychology', '', 'Personality development', '', 'sheela', '2016-06-17 12:52:06', null, null, 'y');

-- ----------------------------
-- Table structure for login_auditor
-- ----------------------------
DROP TABLE IF EXISTS `login_auditor`;
CREATE TABLE `login_auditor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) DEFAULT NULL,
  `ipaddress` varchar(30) DEFAULT NULL,
  `attempt` int(11) DEFAULT NULL,
  `last_attempt_time` varchar(20) DEFAULT NULL,
  `attempt_date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of login_auditor
-- ----------------------------
INSERT INTO `login_auditor` VALUES ('2', '43559755fd97', '192.168.0.18', '1967', '12:48:48', '2016-03-24');
INSERT INTO `login_auditor` VALUES ('3', '59243ca0d9a4', '192.168.0.18', '2109', '13:28:23', '2016-03-24');
INSERT INTO `login_auditor` VALUES ('4', 'f1719f4369fa', '192.168.0.15', '10', '17:22:33', '2015-09-10');
INSERT INTO `login_auditor` VALUES ('5', null, '192.168.1.195', '1', '10:52:00', '2015-12-16');
INSERT INTO `login_auditor` VALUES ('6', '3d37502f7aec', '192.168.1.195', '6', '17:17:16', '2016-03-22');
INSERT INTO `login_auditor` VALUES ('10', 'id_d52f8c6ea486', '192.168.1.126', '3', '15:58:19', '2016-05-09');
INSERT INTO `login_auditor` VALUES ('11', 'id_f40f61d41951', '192.168.1.121', '5', '13:51:49', '2016-03-22');

-- ----------------------------
-- Table structure for login_details_tbl
-- ----------------------------
DROP TABLE IF EXISTS `login_details_tbl`;
CREATE TABLE `login_details_tbl` (
  `login_details_id` varchar(255) NOT NULL,
  `login_id` varchar(255) DEFAULT NULL,
  `first_name` varchar(30) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `branch_id` varchar(100) DEFAULT NULL,
  `ext_number` varchar(255) DEFAULT NULL,
  `login` int(255) DEFAULT NULL,
  PRIMARY KEY (`login_details_id`),
  KEY `branch_i` (`branch_id`) USING BTREE,
  KEY `login_i` (`login_id`) USING BTREE,
  CONSTRAINT `login_details_tbl_ibfk_1` FOREIGN KEY (`branch_id`) REFERENCES `branch_tbl` (`branch_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `login_details_tbl_ibfk_2` FOREIGN KEY (`login_id`) REFERENCES `login_tbl` (`login_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of login_details_tbl
-- ----------------------------
INSERT INTO `login_details_tbl` VALUES ('000a7a8f9bee', 'id_1eaf683d737f', 'newsdf', 'ndsfsd', 'sumeshzoft@gmail.com', '4564654654', '04a3c42146e2', null, null);
INSERT INTO `login_details_tbl` VALUES ('0c9f35a6cb7c', 'id_b8b54d20a040', 'updatedd', 'st', 'sumeshzoft@gmail.com', '6786786786', '135e20f63648', null, null);
INSERT INTO `login_details_tbl` VALUES ('1', '1', 'Administrator', 'Admin', 'ia@gmail.com', '809908808', '54dcb369e715', null, '1');
INSERT INTO `login_details_tbl` VALUES ('441e5706cdf8', 'id_2335a6e9b173', 'Sreeja', 'KR', 'ss@gmail.com', '1232212312', '54dcb369e715', null, null);
INSERT INTO `login_details_tbl` VALUES ('4f0ab2cccb18', 'id_a8ef0bc648a2', 'sheela', 'kollam', 'ss@gghdsf.com', '3245356465', '54dcb369e715', null, null);
INSERT INTO `login_details_tbl` VALUES ('78cd9b17f20b', 'id_8dbf4d168a41', 'arun', 'zofts', 'sdsad@gmail.com', '2323213213', '54dcb369e715', null, null);
INSERT INTO `login_details_tbl` VALUES ('87f079c667a5', 'd27b627b817b', 'sumesh', 'tg', 'sumeshzoft@gmail.com', '8089007012', '73ec84795592', null, null);
INSERT INTO `login_details_tbl` VALUES ('963af8cddb18', 'id_1e727a9e7b71', 'keerthy', 'kp', 'sumeshzoft@gmail.com', '8089007012', '54dcb369e715', null, null);
INSERT INTO `login_details_tbl` VALUES ('a975a41abf32', 'id_bcb936ba5fd0', 'anu', 'asd', 'asd@gmail.com', '5667678879', '54dcb369e715', null, null);
INSERT INTO `login_details_tbl` VALUES ('cb1a73d057b5', 'id_3dcd87b79d1e', 'sanoop', 'ss', 'ss@gmail.com', '3454365465', '54dcb369e715', null, null);
INSERT INTO `login_details_tbl` VALUES ('cd2fa2388c50', 'id_b8b45606c458', 'jins', 'zoft', 'hhh@gmail.com', '6531276351', '54dcb369e715', null, null);
INSERT INTO `login_details_tbl` VALUES ('f4f670bfa60b', 'id_ed703f62302d', 'vijay', 'g n', 'vv@gmail.com', '8973457987', '04a3c42146e2', null, null);
INSERT INTO `login_details_tbl` VALUES ('LOGD_320ec7689c84', 'LOG_10a337b3b237', 'user4', 'user44', 'user4@yahoo.com', '3545475674', '04a3c42146e2', null, null);
INSERT INTO `login_details_tbl` VALUES ('LOGD_381f9ab6f9d6', 'LOG_35db3ceeb7eb', 'fefret', 'erwertrwet', 'gggrt@gmail.com', '6768676887', '135e20f63648', null, null);
INSERT INTO `login_details_tbl` VALUES ('LOGD_39023c61fa72', 'LOG_239750ab2410', 'user7', 'user77', 'user7@gmail.com', '5665678967', '54dcb369e715', null, null);
INSERT INTO `login_details_tbl` VALUES ('LOGD_48251fe90e45', 'LOG_da3772413a34', 'dfgfgg', 'fgghhf', 'hgfhfgh@gmail.com', '4353454345', '135e20f63648', null, null);
INSERT INTO `login_details_tbl` VALUES ('LOGD_488254453f43', 'LOG_157516b66290', 'user5', 'user55', 'user5@gmail.com', '5465678998', '54dcb369e715', null, null);
INSERT INTO `login_details_tbl` VALUES ('LOGD_6b44580b44ea', 'LOG_c2edad976ff9', 'ssk', 'ssk', 'sandeepzoft@gmail.com', '6546546546', 'ba56e841c0d1', null, null);
INSERT INTO `login_details_tbl` VALUES ('LOGD_6e2e8adf366d', 'LOG_50de33d3bf3f', 'user6', 'user666', 'user6@gmail.com', '8675454434', '04a3c42146e2', null, null);
INSERT INTO `login_details_tbl` VALUES ('LOGD_72039bc9633d', 'LOG_aac69582c055', 'Sandeep', 'Krishnan', 'sandeepzoft@gmail.com', '9898787878', 'ba56e841c0d1', null, null);
INSERT INTO `login_details_tbl` VALUES ('LOGD_8292483d4267', 'LOG_b361f2dddcd6', 'jitha', 'pv', 'kpkeerthy661@gmail.com', '3423423423', '73ec84795592', null, null);
INSERT INTO `login_details_tbl` VALUES ('LOGD_8a0139bf270d', 'LOG_8a54f4ce3b97', 'user2', 'user222', 'vdgdsgfg@gmail.com', '7896789789', '54dcb369e715', null, null);
INSERT INTO `login_details_tbl` VALUES ('LOGD_8bdd43bb3602', 'LOG_dfda8948d58d', 's', 's', 'sandeepzoft@gmail.com', '4546465456', 'ba56e841c0d1', null, null);
INSERT INTO `login_details_tbl` VALUES ('LOGD_9b5e5af9af66', 'LOG_d2de0a6ea12a', 'ssk', 'ssk', 'sandeepzoft@gmail.com', '9899959979', 'ba56e841c0d1', null, null);
INSERT INTO `login_details_tbl` VALUES ('LOGD_9c4924719a90', 'LOG_fd23e7564738', 'admin', 'admin', 'sandeepzoft@gmail.com', '4598678945', 'ba56e841c0d1', null, null);
INSERT INTO `login_details_tbl` VALUES ('LOGD_9d167833d530', 'LOG_35cfe0e876c8', 'user3', 'user33', 'user3@gmail.com', '3443563465', '73ec84795592', null, null);
INSERT INTO `login_details_tbl` VALUES ('LOGD_a079f1e095f6', 'LOG_08e55ffb7f6e', 'user a', 'user aa', 'fegfgeg@gmail.com', '545445644', '54dcb369e715', null, null);
INSERT INTO `login_details_tbl` VALUES ('LOGD_b5820a7d4eab', 'LOG_eed5bb763adc', 'new', 'new', 'sandeepzoft@gmail.com', '9995989879', 'ba56e841c0d1', null, null);
INSERT INTO `login_details_tbl` VALUES ('LOGD_dfff488846a5', 'LOG_64cbe47ba519', 'anu', 'anu', 'kpkeerthy661@gmail.com', '5441541411', '73ec84795592', null, null);
INSERT INTO `login_details_tbl` VALUES ('LOGD_e064c0096a51', 'LOG_4ec28d311fae', 'ss', 'ss', 'sandeepzoft@gmail.com', '5456546546', 'ba56e841c0d1', null, null);
INSERT INTO `login_details_tbl` VALUES ('LOGD_f1d5427b8664', 'LOG_4076ecb66e5e', 'Sandy', 'Sandy', 'sandeepzoft@gmail.com', '5687987979', 'ba56e841c0d1', null, null);

-- ----------------------------
-- Table structure for login_tbl
-- ----------------------------
DROP TABLE IF EXISTS `login_tbl`;
CREATE TABLE `login_tbl` (
  `login_id` varchar(255) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  `login` int(11) DEFAULT NULL,
  `flag` int(11) DEFAULT '0',
  PRIMARY KEY (`login_id`),
  UNIQUE KEY `user_name` (`user_name`) USING BTREE,
  KEY `role_id` (`role_id`) USING BTREE,
  CONSTRAINT `login_tbl_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role_tbl` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of login_tbl
-- ----------------------------
INSERT INTO `login_tbl` VALUES ('1', 'admin', 'admin', '1', '1', '1');
INSERT INTO `login_tbl` VALUES ('d27b627b817b', 'sumeshzoft', 'd12c23895bf1', '2', null, '1');
INSERT INTO `login_tbl` VALUES ('f1719f4369fa', 'c', 'c', '2', '1', '1');
INSERT INTO `login_tbl` VALUES ('id_1e727a9e7b71', 'keerthykp', 'q', '3', null, '1');
INSERT INTO `login_tbl` VALUES ('id_1eaf683d737f', 'fhyty', '123', '2', null, '1');
INSERT INTO `login_tbl` VALUES ('id_2335a6e9b173', 'sreejakr', '11', '3', null, '1');
INSERT INTO `login_tbl` VALUES ('id_2d7a67515f0e', 'new', '90b87bf9', '2', null, '0');
INSERT INTO `login_tbl` VALUES ('id_320fab2d8ad7', 'test1', 'test1', '3', null, '1');
INSERT INTO `login_tbl` VALUES ('id_3dcd87b79d1e', 'sanoop', 'sanoop', '2', null, '1');
INSERT INTO `login_tbl` VALUES ('id_4970b023d730', 'ssk', 'ssk', '3', null, '0');
INSERT INTO `login_tbl` VALUES ('id_8330eb504c4e', 'arunzoft', 'test', '2', null, '1');
INSERT INTO `login_tbl` VALUES ('id_8dbf4d168a41', 'a', 'a', '2', null, '1');
INSERT INTO `login_tbl` VALUES ('id_a8ef0bc648a2', 'sheela', '122', '2', null, '1');
INSERT INTO `login_tbl` VALUES ('id_b8b45606c458', 'b', 'b', '3', null, '1');
INSERT INTO `login_tbl` VALUES ('id_b8b54d20a040', 'update', 'fa1e4f5299a5', '2', null, '1');
INSERT INTO `login_tbl` VALUES ('id_bcb936ba5fd0', 'gh', '3c045bb9', '1', null, '0');
INSERT INTO `login_tbl` VALUES ('id_ed703f62302d', 'vijay', 'vijay', '3', null, '1');
INSERT INTO `login_tbl` VALUES ('LOG_08e55ffb7f6e', 'user a', '2bba304500b4', '2', null, '0');
INSERT INTO `login_tbl` VALUES ('LOG_10a337b3b237', 'user4', 'ef78f811d24e', '3', null, '0');
INSERT INTO `login_tbl` VALUES ('LOG_157516b66290', 'user5', 'd990b6e0555b', '3', null, '0');
INSERT INTO `login_tbl` VALUES ('LOG_239750ab2410', 'user7', '559f2681284e', '3', null, '0');
INSERT INTO `login_tbl` VALUES ('LOG_35cfe0e876c8', 'user3', 'uuu', '3', null, '1');
INSERT INTO `login_tbl` VALUES ('LOG_35db3ceeb7eb', 'user8', 'a67c9c5dd061', '2', null, '0');
INSERT INTO `login_tbl` VALUES ('LOG_4076ecb66e5e', 'sandy', 's', '3', null, '1');
INSERT INTO `login_tbl` VALUES ('LOG_4ec28d311fae', 'ss', 'f4711e67d533', '1', null, '0');
INSERT INTO `login_tbl` VALUES ('LOG_50de33d3bf3f', 'user6', '5b7db9774564', '3', null, '0');
INSERT INTO `login_tbl` VALUES ('LOG_64cbe47ba519', 'user14', 'usr', '3', null, '1');
INSERT INTO `login_tbl` VALUES ('LOG_8a54f4ce3b97', 'user2', 'ad8ed6465666', '3', null, '0');
INSERT INTO `login_tbl` VALUES ('LOG_8cd999f460e3', 'xyz', '51bf032fc0b9', '3', null, '0');
INSERT INTO `login_tbl` VALUES ('LOG_aac69582c055', 'sandeep', 's', '2', null, '1');
INSERT INTO `login_tbl` VALUES ('LOG_b361f2dddcd6', 'jitha', 'jjj', '2', null, '1');
INSERT INTO `login_tbl` VALUES ('LOG_c2edad976ff9', 'sandeepsk', 'a435034b63ac', '1', null, '0');
INSERT INTO `login_tbl` VALUES ('LOG_d2de0a6ea12a', 'sandeepskrishnan', '49116f5d3e85', '2', null, '0');
INSERT INTO `login_tbl` VALUES ('LOG_da3772413a34', 'fxfgg', '8674b6ab9567', '2', null, '0');
INSERT INTO `login_tbl` VALUES ('LOG_dd4a1ff55840', 'muhassin', 'null', '3', null, '0');
INSERT INTO `login_tbl` VALUES ('LOG_dfda8948d58d', 's', 's', '2', null, '1');
INSERT INTO `login_tbl` VALUES ('LOG_eed5bb763adc', 'newuser', 'f0ca408c686c', '2', null, '0');
INSERT INTO `login_tbl` VALUES ('LOG_fd23e7564738', 'krishna', 's', '1', null, '1');

-- ----------------------------
-- Table structure for log_table
-- ----------------------------
DROP TABLE IF EXISTS `log_table`;
CREATE TABLE `log_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operation_key` varchar(255) DEFAULT NULL,
  `opertaion` varchar(255) DEFAULT NULL,
  `tablename` varchar(255) DEFAULT NULL,
  `operation_status` int(11) DEFAULT NULL,
  `system_name` varchar(255) DEFAULT NULL,
  `log_time` timestamp NULL DEFAULT NULL,
  `client_access` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of log_table
-- ----------------------------

-- ----------------------------
-- Table structure for mastertbl_admission_test
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_admission_test`;
CREATE TABLE `mastertbl_admission_test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admission_test` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_admission_test
-- ----------------------------
INSERT INTO `mastertbl_admission_test` VALUES ('1', 'NA');
INSERT INTO `mastertbl_admission_test` VALUES ('2', 'GMAT');

-- ----------------------------
-- Table structure for mastertbl_age
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_age`;
CREATE TABLE `mastertbl_age` (
  `age_id` int(255) NOT NULL AUTO_INCREMENT,
  `age` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`age_id`)
) ENGINE=InnoDB AUTO_INCREMENT=508 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_age
-- ----------------------------
INSERT INTO `mastertbl_age` VALUES ('408', '1');
INSERT INTO `mastertbl_age` VALUES ('409', '2');
INSERT INTO `mastertbl_age` VALUES ('410', '3');
INSERT INTO `mastertbl_age` VALUES ('411', '4');
INSERT INTO `mastertbl_age` VALUES ('412', '5');
INSERT INTO `mastertbl_age` VALUES ('413', '6');
INSERT INTO `mastertbl_age` VALUES ('414', '7');
INSERT INTO `mastertbl_age` VALUES ('415', '8');
INSERT INTO `mastertbl_age` VALUES ('416', '9');
INSERT INTO `mastertbl_age` VALUES ('417', '10');
INSERT INTO `mastertbl_age` VALUES ('418', '11');
INSERT INTO `mastertbl_age` VALUES ('419', '12');
INSERT INTO `mastertbl_age` VALUES ('420', '13');
INSERT INTO `mastertbl_age` VALUES ('421', '14');
INSERT INTO `mastertbl_age` VALUES ('422', '15');
INSERT INTO `mastertbl_age` VALUES ('423', '16');
INSERT INTO `mastertbl_age` VALUES ('424', '17');
INSERT INTO `mastertbl_age` VALUES ('425', '18');
INSERT INTO `mastertbl_age` VALUES ('426', '19');
INSERT INTO `mastertbl_age` VALUES ('427', '20');
INSERT INTO `mastertbl_age` VALUES ('428', '21');
INSERT INTO `mastertbl_age` VALUES ('429', '22');
INSERT INTO `mastertbl_age` VALUES ('430', '23');
INSERT INTO `mastertbl_age` VALUES ('431', '24');
INSERT INTO `mastertbl_age` VALUES ('432', '25');
INSERT INTO `mastertbl_age` VALUES ('433', '26');
INSERT INTO `mastertbl_age` VALUES ('434', '27');
INSERT INTO `mastertbl_age` VALUES ('435', '28');
INSERT INTO `mastertbl_age` VALUES ('436', '29');
INSERT INTO `mastertbl_age` VALUES ('437', '30');
INSERT INTO `mastertbl_age` VALUES ('438', '31');
INSERT INTO `mastertbl_age` VALUES ('439', '32');
INSERT INTO `mastertbl_age` VALUES ('440', '33');
INSERT INTO `mastertbl_age` VALUES ('441', '34');
INSERT INTO `mastertbl_age` VALUES ('442', '35');
INSERT INTO `mastertbl_age` VALUES ('443', '36');
INSERT INTO `mastertbl_age` VALUES ('444', '37');
INSERT INTO `mastertbl_age` VALUES ('445', '38');
INSERT INTO `mastertbl_age` VALUES ('446', '39');
INSERT INTO `mastertbl_age` VALUES ('447', '40');
INSERT INTO `mastertbl_age` VALUES ('448', '41');
INSERT INTO `mastertbl_age` VALUES ('449', '42');
INSERT INTO `mastertbl_age` VALUES ('450', '43');
INSERT INTO `mastertbl_age` VALUES ('451', '44');
INSERT INTO `mastertbl_age` VALUES ('452', '45');
INSERT INTO `mastertbl_age` VALUES ('453', '46');
INSERT INTO `mastertbl_age` VALUES ('454', '47');
INSERT INTO `mastertbl_age` VALUES ('455', '48');
INSERT INTO `mastertbl_age` VALUES ('456', '49');
INSERT INTO `mastertbl_age` VALUES ('457', '50');
INSERT INTO `mastertbl_age` VALUES ('458', '51');
INSERT INTO `mastertbl_age` VALUES ('459', '52');
INSERT INTO `mastertbl_age` VALUES ('460', '53');
INSERT INTO `mastertbl_age` VALUES ('461', '54');
INSERT INTO `mastertbl_age` VALUES ('462', '55');
INSERT INTO `mastertbl_age` VALUES ('463', '56');
INSERT INTO `mastertbl_age` VALUES ('464', '57');
INSERT INTO `mastertbl_age` VALUES ('465', '58');
INSERT INTO `mastertbl_age` VALUES ('466', '59');
INSERT INTO `mastertbl_age` VALUES ('467', '60');
INSERT INTO `mastertbl_age` VALUES ('468', '61');
INSERT INTO `mastertbl_age` VALUES ('469', '62');
INSERT INTO `mastertbl_age` VALUES ('470', '63');
INSERT INTO `mastertbl_age` VALUES ('471', '64');
INSERT INTO `mastertbl_age` VALUES ('472', '65');
INSERT INTO `mastertbl_age` VALUES ('473', '66');
INSERT INTO `mastertbl_age` VALUES ('474', '67');
INSERT INTO `mastertbl_age` VALUES ('475', '68');
INSERT INTO `mastertbl_age` VALUES ('476', '69');
INSERT INTO `mastertbl_age` VALUES ('477', '70');
INSERT INTO `mastertbl_age` VALUES ('478', '71');
INSERT INTO `mastertbl_age` VALUES ('479', '72');
INSERT INTO `mastertbl_age` VALUES ('480', '73');
INSERT INTO `mastertbl_age` VALUES ('481', '74');
INSERT INTO `mastertbl_age` VALUES ('482', '75');
INSERT INTO `mastertbl_age` VALUES ('483', '76');
INSERT INTO `mastertbl_age` VALUES ('484', '77');
INSERT INTO `mastertbl_age` VALUES ('485', '78');
INSERT INTO `mastertbl_age` VALUES ('486', '79');
INSERT INTO `mastertbl_age` VALUES ('487', '80');
INSERT INTO `mastertbl_age` VALUES ('488', '81');
INSERT INTO `mastertbl_age` VALUES ('489', '82');
INSERT INTO `mastertbl_age` VALUES ('490', '83');
INSERT INTO `mastertbl_age` VALUES ('491', '84');
INSERT INTO `mastertbl_age` VALUES ('492', '85');
INSERT INTO `mastertbl_age` VALUES ('493', '86');
INSERT INTO `mastertbl_age` VALUES ('494', '87');
INSERT INTO `mastertbl_age` VALUES ('495', '88');
INSERT INTO `mastertbl_age` VALUES ('496', '89');
INSERT INTO `mastertbl_age` VALUES ('497', '90');
INSERT INTO `mastertbl_age` VALUES ('498', '91');
INSERT INTO `mastertbl_age` VALUES ('499', '92');
INSERT INTO `mastertbl_age` VALUES ('500', '93');
INSERT INTO `mastertbl_age` VALUES ('501', '94');
INSERT INTO `mastertbl_age` VALUES ('502', '95');
INSERT INTO `mastertbl_age` VALUES ('503', '96');
INSERT INTO `mastertbl_age` VALUES ('504', '97');
INSERT INTO `mastertbl_age` VALUES ('505', '98');
INSERT INTO `mastertbl_age` VALUES ('506', '99');
INSERT INTO `mastertbl_age` VALUES ('507', '100');

-- ----------------------------
-- Table structure for mastertbl_application_type
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_application_type`;
CREATE TABLE `mastertbl_application_type` (
  `app_type_id` varchar(100) NOT NULL,
  `application` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`app_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_application_type
-- ----------------------------
INSERT INTO `mastertbl_application_type` VALUES ('1', 'Family,Single');
INSERT INTO `mastertbl_application_type` VALUES ('2', 'Single');

-- ----------------------------
-- Table structure for mastertbl_app_status
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_app_status`;
CREATE TABLE `mastertbl_app_status` (
  `app_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `app_status` varchar(50) DEFAULT NULL,
  `date_reason_enable` tinyint(4) DEFAULT NULL,
  `from_mail` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `email_body` longtext,
  `enable` tinyint(4) DEFAULT NULL,
  `pro_completion_status` int(11) DEFAULT '0',
  PRIMARY KEY (`app_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_app_status
-- ----------------------------
INSERT INTO `mastertbl_app_status` VALUES ('1', 'Enquiry filed', '0', 'ia.offer@gmail.com', 'sub', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\"><i>Dear [name],</i></font></p><p>Thanks for being our awesome customer!<br>\nWe have received your enquiry and would like to thank you for calling to\n us. If your inquiry is urgent, please use the telephone number listed \nbelow, to talk to one of our staff members. Otherwise, we will reply by \nemail shortly.<br>\nTalk to you soon,</p><p><font face=\"Segoe UI\"><i>Regards</i></font></p><p><b><font size=\"4\" color=\"#e64d4d\">International Academy</font></b><br>2nd Floor-Office, 3rd Floor Training Centre<br>Brite House, Karimpatta Cross Road<br>Pallimukku Cochin-682 016, <br>Kerala State, India<br><b>Phone No. +91 484 4155111</b><br><b>Fax No. +91 484 4155100<br><font color=\"#0000ff\"><a href=\"mailto:Email%3Aia.offer@gmail.com\" target=\"_blank\">E-mail</a><a href=\"http:///\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http:///&amp;source=gmail&amp;ust=1463720323192000&amp;usg=AFQjCNGD4EFVx5oP7QFwRZpcEzG1Xgihwg\">: offer@intaca.in</a></font></b><br><a href=\"http://Website.www.internationalacademy.in\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://Website.www.internationalacademy.in&amp;source=gmail&amp;ust=1463720323192000&amp;usg=AFQjCNHseUIFzFhXLu1BXTKde9NH2cDxsw\"><b>website: www.internationalacademy.in</b></a><br><br><b><span style=\"font-size:small;font-family:\'Trebuchet MS\',sans-serif;color:rgb(0,32,96)\" lang=\"EN-AU\"><img class=\"CToWUd\" src=\"https://ci6.googleusercontent.com/proxy/r2Cn3amIVnLTLy2Tsk4WdSeg4taQTmwlHh3_lWqYwNHljfookUXUuNrzEnEDbhRC7dDtPlNOoQ=s0-d-e1-ft#http://i59.tinypic.com/9zq2dk.jpg\">&nbsp;We’re on</span><span style=\"font-size:small;font-family:\'Trebuchet MS\',sans-serif;color:blue\">&nbsp;</span><span style=\"font-size:small;font-family:\'Trebuchet MS\',sans-serif;color:blue\" lang=\"EN-AU\"><a href=\"http://www.facebook.com/intaca.in\" style=\"color:rgb(17,85,204)\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://www.facebook.com/intaca.in&amp;source=gmail&amp;ust=1463720323192000&amp;usg=AFQjCNGfLLuqyMSpfOI05rA-AOCe8V7yYg\">Facebook</a></span><span style=\"font-size:small;font-family:\'Trebuchet MS\',sans-serif;color:rgb(0,32,96)\" lang=\"EN-AU\">. Don’t forget to ‘Like’ us !</span></b><br><span style=\"font-size:15pt;line-height:23px;font-family:Webdings;color:green;background-color:white\">P</span><span style=\"font-size:13pt;line-height:19px;font-family:Arial,sans-serif;color:green;background-color:white\">&nbsp;</span><span style=\"font-size:8.5pt;line-height:12px;font-family:Arial,sans-serif;color:green;background-color:white\">Please consider the environment before printing this e-mail.</span></p><p><font face=\"Segoe UI\"><i><br></i></font></p><pre id=\"line1\"><br></pre></body></html>', '1', '0');
INSERT INTO `mastertbl_app_status` VALUES ('2', 'Assessment in progress', '0', 'ia.offer@gmail.com', 'sub', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p style=\"font-family: \'\';\"><font face=\"Segoe UI\"><i>Dear [name],</i></font></p><p style=\"font-family: \'\';\">Thanks for being our awesome customer!<br>We have received your enquiry and would like to thank you for calling to us. If your inquiry is urgent, please use the telephone number listed below, to talk to one of our staff members. Otherwise, we will reply by email shortly.<br>Talk to you soon,</p><p style=\"font-family: \'\';\"><font face=\"Segoe UI\"><i>Regards</i></font></p><p style=\"font-family: \'\';\"><b><font size=\"4\" color=\"#e64d4d\">International Academy</font></b><br>2nd Floor-Office, 3rd Floor Training Centre<br>Brite House, Karimpatta Cross Road<br>Pallimukku Cochin-682 016,&nbsp;<br>Kerala State, India<br><b>Phone No. +91 484 4155111</b><br><b>Fax No. +91 484 4155100<br><font color=\"#0000ff\"><a href=\"mailto:Email%3Aia.offer@gmail.com\" target=\"_blank\">E-mail</a><a href=\"http:/\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http:///&amp;source=gmail&amp;ust=1463720323192000&amp;usg=AFQjCNGD4EFVx5oP7QFwRZpcEzG1Xgihwg\">: offer@intaca.in</a></font></b><br><a href=\"http://website.www.internationalacademy.in/\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://Website.www.internationalacademy.in&amp;source=gmail&amp;ust=1463720323192000&amp;usg=AFQjCNHseUIFzFhXLu1BXTKde9NH2cDxsw\"><b>website: www.internationalacademy.in</b></a><br><br><b><span lang=\"EN-AU\" style=\"font-size: small; font-family: \'Trebuchet MS\', sans-serif; color: rgb(0, 32, 96);\"><img class=\"CToWUd\" src=\"https://ci6.googleusercontent.com/proxy/r2Cn3amIVnLTLy2Tsk4WdSeg4taQTmwlHh3_lWqYwNHljfookUXUuNrzEnEDbhRC7dDtPlNOoQ=s0-d-e1-ft#http://i59.tinypic.com/9zq2dk.jpg\">&nbsp;We’re on</span><span style=\"font-size: small; font-family: \'Trebuchet MS\', sans-serif; color: blue;\">&nbsp;</span><span lang=\"EN-AU\" style=\"font-size: small; font-family: \'Trebuchet MS\', sans-serif; color: blue;\"><a href=\"http://www.facebook.com/intaca.in\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://www.facebook.com/intaca.in&amp;source=gmail&amp;ust=1463720323192000&amp;usg=AFQjCNGfLLuqyMSpfOI05rA-AOCe8V7yYg\" style=\"color: rgb(17, 85, 204);\">Facebook</a></span><span lang=\"EN-AU\" style=\"font-size: small; font-family: \'Trebuchet MS\', sans-serif; color: rgb(0, 32, 96);\">. Don’t forget to ‘Like’ us !</span></b><br><span style=\"font-size: 15pt; line-height: 23px; font-family: Webdings; color: green; background-color: white;\">P</span><span style=\"font-size: 13pt; line-height: 19px; font-family: Arial, sans-serif; color: green; background-color: white;\">&nbsp;</span><span style=\"font-size: 8.5pt; line-height: 12px; font-family: Arial, sans-serif; color: green; background-color: white;\">Please consider the environment before printing this e-mail.</span></p><p style=\"font-family: \'\';\"><font face=\"Segoe UI\"><i><br></i></font></p></body></html>', '1', '0');
INSERT INTO `mastertbl_app_status` VALUES ('3', 'Registration open', '0', 'ia.offer@gmail.com', 'sub', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\"><i>email for&nbsp;Subject Access Later</i></font></p></body></html>', '1', '0');
INSERT INTO `mastertbl_app_status` VALUES ('4', 'Registration completed', '0', 'ia.offer@gmail.com', 'sub', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\"><i><b>email for&nbsp;Subject registration completed</b></i></font></p></body></html>', '1', '1');
INSERT INTO `mastertbl_app_status` VALUES ('5', 'Register Now', '1', 'ia.offer@gmail.com', 'sub', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\"><i>email for&nbsp;Subject Access Later</i></font></p></body></html>', '1', '0');
INSERT INTO `mastertbl_app_status` VALUES ('6', 'Call Not Picking', '1', 'ia.offer@gmail.com', 'sub', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\"><i><b>email for&nbsp;Subject Access call not picking</b></i></font></p></body></html>', '1', '0');
INSERT INTO `mastertbl_app_status` VALUES ('7', 'Call Attended & Call Later', '1', 'ia.offer@gmail.com', 'sub', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\"><i>email for&nbsp;Subject Access Later</i></font></p></body></html>', '1', '0');
INSERT INTO `mastertbl_app_status` VALUES ('8', 'Not Completed Call Later', '1', 'ia.offer@gmail.com', 'sub', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\"><i>email for&nbsp;Subject Access Later</i></font></p></body></html>', '1', '0');
INSERT INTO `mastertbl_app_status` VALUES ('9', 'Will Contact Future', '1', 'ia.offer@gmail.com', 'sub', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\"><i>email for&nbsp;Subject Access Later</i></font></p></body></html>', '1', '0');
INSERT INTO `mastertbl_app_status` VALUES ('10', 'Not Interested', '1', 'ia.offer@gmail.com', 'sub', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\"><i>email for&nbsp;Subject Access Later</i></font></p></body></html>', '1', '0');
INSERT INTO `mastertbl_app_status` VALUES ('11', 'Fake Call', '0', 'ia.offer@gmail.com', 'sub', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\"><i>email for&nbsp;Subject Access Later</i></font></p></body></html>', '1', '0');
INSERT INTO `mastertbl_app_status` VALUES ('12', 'Number Not Exist', '1', 'ia.offer@gmail.com', 'sub', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\"><i>email for&nbsp;Subject Access SSSS</i></font></p></body></html>', '0', '0');
INSERT INTO `mastertbl_app_status` VALUES ('13', 'Confirm Soon', '1', 'ia.offer@gmail.com', 'sub', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\"><i>email for&nbsp;Subject Access Later</i></font></p></body></html>', '1', '0');
INSERT INTO `mastertbl_app_status` VALUES ('14', 'Cancelled', '1', 'ia.offer@gmail.com', 'sub', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p style=\"font-family: \'\';\"><font face=\"Segoe UI\"><i>Dear [name],</i></font></p><p style=\"font-family: \'\';\">Thanks for being our awesome customer!<br>We have received your enquiry and would like to thank you for calling to us. If your inquiry is urgent, please use the telephone number listed below, to talk to one of our staff members. Otherwise, we will reply by email shortly.<br>Talk to you soon,</p><p style=\"font-family: \'\';\"><font face=\"Segoe UI\"><i>Regards</i></font></p><p style=\"font-family: \'\';\"><b><font size=\"4\" color=\"#e64d4d\">International Academy</font></b><br>2nd Floor-Office, 3rd Floor Training Centre<br>Brite House, Karimpatta Cross Road<br>Pallimukku Cochin-682 016,&nbsp;<br>Kerala State, India<br><b>Phone No. +91 484 4155111</b><br><b>Fax No. +91 484 4155100<br><font color=\"#0000ff\"><a href=\"mailto:Email%3Aia.offer@gmail.com\" target=\"_blank\">E-mail</a><a href=\"http:/\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http:///&amp;source=gmail&amp;ust=1463720323192000&amp;usg=AFQjCNGD4EFVx5oP7QFwRZpcEzG1Xgihwg\">: offer@intaca.in</a></font></b><br><a href=\"http://website.www.internationalacademy.in/\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://Website.www.internationalacademy.in&amp;source=gmail&amp;ust=1463720323192000&amp;usg=AFQjCNHseUIFzFhXLu1BXTKde9NH2cDxsw\"><b>website: www.internationalacademy.in</b></a><br><br><b><span lang=\"EN-AU\" style=\"font-size: small; font-family: \'Trebuchet MS\', sans-serif; color: rgb(0, 32, 96);\"><img class=\"CToWUd\" src=\"https://ci6.googleusercontent.com/proxy/r2Cn3amIVnLTLy2Tsk4WdSeg4taQTmwlHh3_lWqYwNHljfookUXUuNrzEnEDbhRC7dDtPlNOoQ=s0-d-e1-ft#http://i59.tinypic.com/9zq2dk.jpg\">&nbsp;We’re on</span><span style=\"font-size: small; font-family: \'Trebuchet MS\', sans-serif; color: blue;\">&nbsp;</span><span lang=\"EN-AU\" style=\"font-size: small; font-family: \'Trebuchet MS\', sans-serif; color: blue;\"><a href=\"http://www.facebook.com/intaca.in\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://www.facebook.com/intaca.in&amp;source=gmail&amp;ust=1463720323192000&amp;usg=AFQjCNGfLLuqyMSpfOI05rA-AOCe8V7yYg\" style=\"color: rgb(17, 85, 204);\">Facebook</a></span><span lang=\"EN-AU\" style=\"font-size: small; font-family: \'Trebuchet MS\', sans-serif; color: rgb(0, 32, 96);\">. Don’t forget to ‘Like’ us !</span></b><br><span style=\"font-size: 15pt; line-height: 23px; font-family: Webdings; color: green; background-color: white;\">P</span><span style=\"font-size: 13pt; line-height: 19px; font-family: Arial, sans-serif; color: green; background-color: white;\">&nbsp;</span><span style=\"font-size: 8.5pt; line-height: 12px; font-family: Arial, sans-serif; color: green; background-color: white;\">Please consider the environment before printing this e-mail.</span></p><p><br></p></body></html>', '1', '0');
INSERT INTO `mastertbl_app_status` VALUES ('15', 'Will Walk in', '1', 'ia.offer@gmail.com', 'sub', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\"><i>email for&nbsp;Subject Access Later</i></font></p></body></html>', '1', '0');
INSERT INTO `mastertbl_app_status` VALUES ('16', 'Registration Perspective', '1', 'ia.offer@gmail.com', 'sub', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\"><i>email for&nbsp;Subject Access Later</i></font></p></body></html>', '1', '0');
INSERT INTO `mastertbl_app_status` VALUES ('17', 'Assess Now', '1', 'ia.offer@gmail.com', 'sub', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\"><i>email for&nbsp;Subject Access Later</i></font></p></body></html>', '1', '0');
INSERT INTO `mastertbl_app_status` VALUES ('40', 'status 1', '0', 'sdad', 'sdsads', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p style=\"font-family: \'\';\"><font face=\"Segoe UI\"><i>Dear [name],</i></font></p><p style=\"font-family: \'\';\">Thanks for being our awesome customer!<br>We have received your enquiry and would like to thank you for calling to us. If your inquiry is urgent, please use the telephone number listed below, to talk to one of our staff members. Otherwise, we will reply by email shortly.<br>Talk to you soon,</p><p style=\"font-family: \'\';\"><font face=\"Segoe UI\"><i>Regards</i></font></p><p style=\"font-family: \'\';\"><b><font size=\"4\" color=\"#e64d4d\">International Academy</font></b><br>2nd Floor-Office, 3rd Floor Training Centre<br>Brite House, Karimpatta Cross Road<br>Pallimukku Cochin-682 016,&nbsp;<br>Kerala State, India<br><b>Phone No. +91 484 4155111</b><br><b>Fax No. +91 484 4155100<br><font color=\"#0000ff\"><a href=\"mailto:Email%3Aia.offer@gmail.com\" target=\"_blank\">E-mail</a><a href=\"http:/\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http:///&amp;source=gmail&amp;ust=1463720323192000&amp;usg=AFQjCNGD4EFVx5oP7QFwRZpcEzG1Xgihwg\">: offer@intaca.in</a></font></b><br><a href=\"http://website.www.internationalacademy.in/\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://Website.www.internationalacademy.in&amp;source=gmail&amp;ust=1463720323192000&amp;usg=AFQjCNHseUIFzFhXLu1BXTKde9NH2cDxsw\"><b>website: www.internationalacademy.in</b></a><br><br><b><span lang=\"EN-AU\" style=\"font-size: small; font-family: \'Trebuchet MS\', sans-serif; color: rgb(0, 32, 96);\"><img class=\"CToWUd\" src=\"https://ci6.googleusercontent.com/proxy/r2Cn3amIVnLTLy2Tsk4WdSeg4taQTmwlHh3_lWqYwNHljfookUXUuNrzEnEDbhRC7dDtPlNOoQ=s0-d-e1-ft#http://i59.tinypic.com/9zq2dk.jpg\">&nbsp;We’re on</span><span style=\"font-size: small; font-family: \'Trebuchet MS\', sans-serif; color: blue;\">&nbsp;</span><span lang=\"EN-AU\" style=\"font-size: small; font-family: \'Trebuchet MS\', sans-serif; color: blue;\"><a href=\"http://www.facebook.com/intaca.in\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://www.facebook.com/intaca.in&amp;source=gmail&amp;ust=1463720323192000&amp;usg=AFQjCNGfLLuqyMSpfOI05rA-AOCe8V7yYg\" style=\"color: rgb(17, 85, 204);\">Facebook</a></span><span lang=\"EN-AU\" style=\"font-size: small; font-family: \'Trebuchet MS\', sans-serif; color: rgb(0, 32, 96);\">. Don’t forget to ‘Like’ us !</span></b><br><span style=\"font-size: 15pt; line-height: 23px; font-family: Webdings; color: green; background-color: white;\">P</span><span style=\"font-size: 13pt; line-height: 19px; font-family: Arial, sans-serif; color: green; background-color: white;\">&nbsp;</span><span style=\"font-size: 8.5pt; line-height: 12px; font-family: Arial, sans-serif; color: green; background-color: white;\">Please consider the environment before printing this e-mail.</span></p><p style=\"font-family: \'\';\"><font face=\"Segoe UI\"><i><br></i></font></p></body></html>', '0', '0');
INSERT INTO `mastertbl_app_status` VALUES ('41', 'status 2', '0', 'fggdg123@gmail.com', 'ddsqw', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p style=\"font-family: \'\';\"><font face=\"Segoe UI\"><i>Dear [name],</i></font></p><p style=\"font-family: \'\';\">Thanks for being our awesome customer!<br>We have received your enquiry and would like to thank you for calling to us. If your inquiry is urgent, please use the telephone number listed below, to talk to one of our staff members. Otherwise, we will reply by email shortly.<br>Talk to you soon,</p><p style=\"font-family: \'\';\"><font face=\"Segoe UI\"><i>Regards</i></font></p><p style=\"font-family: \'\';\"><b><font size=\"4\" color=\"#e64d4d\">International Academy</font></b><br>2nd Floor-Office, 3rd Floor Training Centre<br>Brite House, Karimpatta Cross Road<br>Pallimukku Cochin-682 016,&nbsp;<br>Kerala State, India<br><b>Phone No. +91 484 4155111</b><br><b>Fax No. +91 484 4155100<br><font color=\"#0000ff\"><a href=\"mailto:Email%3Aia.offer@gmail.com\" target=\"_blank\">E-mail</a><a href=\"http:/\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http:///&amp;source=gmail&amp;ust=1463720323192000&amp;usg=AFQjCNGD4EFVx5oP7QFwRZpcEzG1Xgihwg\">: offer@intaca.in</a></font></b><br><a href=\"http://website.www.internationalacademy.in/\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://Website.www.internationalacademy.in&amp;source=gmail&amp;ust=1463720323192000&amp;usg=AFQjCNHseUIFzFhXLu1BXTKde9NH2cDxsw\"><b>website: www.internationalacademy.in</b></a><br><br><b><span lang=\"EN-AU\" style=\"font-size: small; font-family: \'Trebuchet MS\', sans-serif; color: rgb(0, 32, 96);\"><img class=\"CToWUd\" src=\"https://ci6.googleusercontent.com/proxy/r2Cn3amIVnLTLy2Tsk4WdSeg4taQTmwlHh3_lWqYwNHljfookUXUuNrzEnEDbhRC7dDtPlNOoQ=s0-d-e1-ft#http://i59.tinypic.com/9zq2dk.jpg\">&nbsp;We’re on</span><span style=\"font-size: small; font-family: \'Trebuchet MS\', sans-serif; color: blue;\">&nbsp;</span><span lang=\"EN-AU\" style=\"font-size: small; font-family: \'Trebuchet MS\', sans-serif; color: blue;\"><a href=\"http://www.facebook.com/intaca.in\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://www.facebook.com/intaca.in&amp;source=gmail&amp;ust=1463720323192000&amp;usg=AFQjCNGfLLuqyMSpfOI05rA-AOCe8V7yYg\" style=\"color: rgb(17, 85, 204);\">Facebook</a></span><span lang=\"EN-AU\" style=\"font-size: small; font-family: \'Trebuchet MS\', sans-serif; color: rgb(0, 32, 96);\">. Don’t forget to ‘Like’ us !</span></b><br><span style=\"font-size: 15pt; line-height: 23px; font-family: Webdings; color: green; background-color: white;\">P</span><span style=\"font-size: 13pt; line-height: 19px; font-family: Arial, sans-serif; color: green; background-color: white;\">&nbsp;</span><span style=\"font-size: 8.5pt; line-height: 12px; font-family: Arial, sans-serif; color: green; background-color: white;\">Please consider the environment before printing this e-mail.</span></p><p style=\"font-family: \'\';\"><font face=\"Segoe UI\"><i><br></i></font></p></body></html>', '0', '0');
INSERT INTO `mastertbl_app_status` VALUES ('42', 'status 3', '1', 'ia.offer@gmail.com', 'subject for status 3', '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p style=\"font-family: \'\';\"><font face=\"Segoe UI\"><i>Dear [name],</i></font></p><p style=\"font-family: \'\';\">Thanks for being our awesome customer!<br>We have received your enquiry and would like to thank you for calling to us. If your inquiry is urgent, please use the telephone number listed below, to talk to one of our staff members. Otherwise, we will reply by email shortly.<br>Talk to you soon,</p><p style=\"font-family: \'\';\"><font face=\"Segoe UI\"><i>Regards</i></font></p><p style=\"font-family: \'\';\"><b><font size=\"4\" color=\"#e64d4d\">International Academy</font></b><br>2nd Floor-Office, 3rd Floor Training Centre<br>Brite House, Karimpatta Cross Road<br>Pallimukku Cochin-682 016,&nbsp;<br>Kerala State, India<br><b>Phone No. +91 484 4155111</b><br><b>Fax No. +91 484 4155100<br><font color=\"#0000ff\"><a href=\"mailto:Email%3Aia.offer@gmail.com\" target=\"_blank\">E-mail</a><a href=\"http:/\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http:///&amp;source=gmail&amp;ust=1463720323192000&amp;usg=AFQjCNGD4EFVx5oP7QFwRZpcEzG1Xgihwg\">: offer@intaca.in</a></font></b><br><a href=\"http://website.www.internationalacademy.in/\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://Website.www.internationalacademy.in&amp;source=gmail&amp;ust=1463720323192000&amp;usg=AFQjCNHseUIFzFhXLu1BXTKde9NH2cDxsw\"><b>website: www.internationalacademy.in</b></a><br><br><b><span lang=\"EN-AU\" style=\"font-size: small; font-family: \'Trebuchet MS\', sans-serif; color: rgb(0, 32, 96);\"><img class=\"CToWUd\" src=\"https://ci6.googleusercontent.com/proxy/r2Cn3amIVnLTLy2Tsk4WdSeg4taQTmwlHh3_lWqYwNHljfookUXUuNrzEnEDbhRC7dDtPlNOoQ=s0-d-e1-ft#http://i59.tinypic.com/9zq2dk.jpg\">&nbsp;We’re on</span><span style=\"font-size: small; font-family: \'Trebuchet MS\', sans-serif; color: blue;\">&nbsp;</span><span lang=\"EN-AU\" style=\"font-size: small; font-family: \'Trebuchet MS\', sans-serif; color: blue;\"><a href=\"http://www.facebook.com/intaca.in\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=http://www.facebook.com/intaca.in&amp;source=gmail&amp;ust=1463720323192000&amp;usg=AFQjCNGfLLuqyMSpfOI05rA-AOCe8V7yYg\" style=\"color: rgb(17, 85, 204);\">Facebook</a></span><span lang=\"EN-AU\" style=\"font-size: small; font-family: \'Trebuchet MS\', sans-serif; color: rgb(0, 32, 96);\">. Don’t forget to ‘Like’ us !</span></b><br><span style=\"font-size: 15pt; line-height: 23px; font-family: Webdings; color: green; background-color: white;\">P</span><span style=\"font-size: 13pt; line-height: 19px; font-family: Arial, sans-serif; color: green; background-color: white;\">&nbsp;</span><span style=\"font-size: 8.5pt; line-height: 12px; font-family: Arial, sans-serif; color: green; background-color: white;\">Please consider the environment before printing this e-mail.</span></p><p style=\"font-family: \'\';\"><font face=\"Segoe UI\"><i><br></i></font></p></body></html>', '1', '0');

-- ----------------------------
-- Table structure for mastertbl_batch_timing
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_batch_timing`;
CREATE TABLE `mastertbl_batch_timing` (
  `time_id` varchar(255) NOT NULL,
  `timing` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`time_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_batch_timing
-- ----------------------------
INSERT INTO `mastertbl_batch_timing` VALUES ('3', 'Evening Batch');
INSERT INTO `mastertbl_batch_timing` VALUES ('48a0c4f12250', 'Night batch');
INSERT INTO `mastertbl_batch_timing` VALUES ('5', 'Morning Batch');
INSERT INTO `mastertbl_batch_timing` VALUES ('6', 'Afternoon Batch');
INSERT INTO `mastertbl_batch_timing` VALUES ('971e45e9e6df', 'midnight batch');

-- ----------------------------
-- Table structure for mastertbl_country
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_country`;
CREATE TABLE `mastertbl_country` (
  `country_id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_country
-- ----------------------------
INSERT INTO `mastertbl_country` VALUES ('1', 'Australia');
INSERT INTO `mastertbl_country` VALUES ('2', 'USA');
INSERT INTO `mastertbl_country` VALUES ('3', 'Canada');
INSERT INTO `mastertbl_country` VALUES ('4', 'UK');
INSERT INTO `mastertbl_country` VALUES ('5', 'New Zealand');
INSERT INTO `mastertbl_country` VALUES ('6', 'Germany');

-- ----------------------------
-- Table structure for mastertbl_course_management
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_course_management`;
CREATE TABLE `mastertbl_course_management` (
  `course_id` varchar(255) NOT NULL,
  `course` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_course_management
-- ----------------------------
INSERT INTO `mastertbl_course_management` VALUES ('1', 'IELTS');
INSERT INTO `mastertbl_course_management` VALUES ('1b5337395451', 'Personality development');
INSERT INTO `mastertbl_course_management` VALUES ('3', 'Spoken English');
INSERT INTO `mastertbl_course_management` VALUES ('b3cad15dd90f', 'FRENCH');

-- ----------------------------
-- Table structure for mastertbl_course_type
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_course_type`;
CREATE TABLE `mastertbl_course_type` (
  `course_id` varchar(255) NOT NULL,
  `course_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_course_type
-- ----------------------------
INSERT INTO `mastertbl_course_type` VALUES ('1', 'Regular');
INSERT INTO `mastertbl_course_type` VALUES ('2', 'Crash');
INSERT INTO `mastertbl_course_type` VALUES ('3', 'Weekly');
INSERT INTO `mastertbl_course_type` VALUES ('4', 'Yearly');
INSERT INTO `mastertbl_course_type` VALUES ('7fce53bd7a90', 'Monthly');

-- ----------------------------
-- Table structure for mastertbl_currency_list
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_currency_list`;
CREATE TABLE `mastertbl_currency_list` (
  `currency_id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(100) DEFAULT NULL,
  `currency` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`currency_id`)
) ENGINE=InnoDB AUTO_INCREMENT=231 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_currency_list
-- ----------------------------
INSERT INTO `mastertbl_currency_list` VALUES ('1', 'Afghanistan', 'AFN');
INSERT INTO `mastertbl_currency_list` VALUES ('2', 'Albania', 'ALL');
INSERT INTO `mastertbl_currency_list` VALUES ('3', 'Algeria', 'DZD');
INSERT INTO `mastertbl_currency_list` VALUES ('4', 'American Samoa', 'USD');
INSERT INTO `mastertbl_currency_list` VALUES ('5', 'Andorra', 'EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('6', 'Angola', 'AOA');
INSERT INTO `mastertbl_currency_list` VALUES ('7', 'Anguilla', 'XCD');
INSERT INTO `mastertbl_currency_list` VALUES ('9', 'Antigua and Barbuda', 'XCD');
INSERT INTO `mastertbl_currency_list` VALUES ('10', 'Argentina', 'ARS');
INSERT INTO `mastertbl_currency_list` VALUES ('11', 'Armenia', 'AMD');
INSERT INTO `mastertbl_currency_list` VALUES ('12', 'Aruba', 'AWG');
INSERT INTO `mastertbl_currency_list` VALUES ('13', 'Australia', 'AUD');
INSERT INTO `mastertbl_currency_list` VALUES ('14', 'Austria', 'EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('15', 'Azerbaijan', 'AZM');
INSERT INTO `mastertbl_currency_list` VALUES ('16', 'The Bahamas', 'BSD');
INSERT INTO `mastertbl_currency_list` VALUES ('17', 'Bahrain', 'BHD');
INSERT INTO `mastertbl_currency_list` VALUES ('18', 'Bangladesh', 'BDT');
INSERT INTO `mastertbl_currency_list` VALUES ('19', 'Barbados', 'BBD');
INSERT INTO `mastertbl_currency_list` VALUES ('20', 'Belarus', 'BYR');
INSERT INTO `mastertbl_currency_list` VALUES ('21', 'Belgium', 'EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('22', 'Belize', 'BZD');
INSERT INTO `mastertbl_currency_list` VALUES ('23', 'Benin', 'XOF');
INSERT INTO `mastertbl_currency_list` VALUES ('24', 'Bermuda', 'BMD');
INSERT INTO `mastertbl_currency_list` VALUES ('25', 'Bhutan', 'BTN');
INSERT INTO `mastertbl_currency_list` VALUES ('26', 'Bolivia', 'BOB');
INSERT INTO `mastertbl_currency_list` VALUES ('27', 'Botswana', 'BWP');
INSERT INTO `mastertbl_currency_list` VALUES ('28', 'Brazil', 'BRL');
INSERT INTO `mastertbl_currency_list` VALUES ('29', 'British Virgin Islands', 'USD');
INSERT INTO `mastertbl_currency_list` VALUES ('30', 'Brunei', 'BND');
INSERT INTO `mastertbl_currency_list` VALUES ('31', 'Bulgaria', 'BGN');
INSERT INTO `mastertbl_currency_list` VALUES ('32', 'Burkina Faso', 'XOF');
INSERT INTO `mastertbl_currency_list` VALUES ('33', 'Burundi', 'BIF');
INSERT INTO `mastertbl_currency_list` VALUES ('34', 'Cambodia', 'KHR');
INSERT INTO `mastertbl_currency_list` VALUES ('35', 'Cameroon', 'XAF');
INSERT INTO `mastertbl_currency_list` VALUES ('36', 'Canada', 'CAD');
INSERT INTO `mastertbl_currency_list` VALUES ('37', 'Cape Verde', 'CVE');
INSERT INTO `mastertbl_currency_list` VALUES ('38', 'Cayman Islands', 'KYD');
INSERT INTO `mastertbl_currency_list` VALUES ('39', 'Central African Republic', 'XAF');
INSERT INTO `mastertbl_currency_list` VALUES ('40', 'Chad', 'XAF');
INSERT INTO `mastertbl_currency_list` VALUES ('41', 'Chile', 'CLP');
INSERT INTO `mastertbl_currency_list` VALUES ('42', 'China', 'CNY');
INSERT INTO `mastertbl_currency_list` VALUES ('43', 'Christmas Island', 'AUD');
INSERT INTO `mastertbl_currency_list` VALUES ('44', 'Cocos (Keeling) Islands', 'AUD');
INSERT INTO `mastertbl_currency_list` VALUES ('45', 'Colombia', 'COP');
INSERT INTO `mastertbl_currency_list` VALUES ('46', 'Comoros', 'KMF');
INSERT INTO `mastertbl_currency_list` VALUES ('47', 'Cook Islands', 'NZD');
INSERT INTO `mastertbl_currency_list` VALUES ('48', 'Costa Rica', 'CRC');
INSERT INTO `mastertbl_currency_list` VALUES ('49', 'Côte d\'Ivoire', 'XOF');
INSERT INTO `mastertbl_currency_list` VALUES ('50', 'Croatia', 'HRK');
INSERT INTO `mastertbl_currency_list` VALUES ('51', 'Cuba', 'CUP');
INSERT INTO `mastertbl_currency_list` VALUES ('52', 'Cyprus', 'CYP');
INSERT INTO `mastertbl_currency_list` VALUES ('53', 'Czech Republic', 'CZK');
INSERT INTO `mastertbl_currency_list` VALUES ('54', 'Denmark', 'DKK');
INSERT INTO `mastertbl_currency_list` VALUES ('55', 'Djibouti', 'DJF');
INSERT INTO `mastertbl_currency_list` VALUES ('56', 'Dominica', 'XCD');
INSERT INTO `mastertbl_currency_list` VALUES ('57', 'Dominican Republic', 'DOP');
INSERT INTO `mastertbl_currency_list` VALUES ('58', 'Ecuador', 'USD');
INSERT INTO `mastertbl_currency_list` VALUES ('59', 'Egypt', 'EGP');
INSERT INTO `mastertbl_currency_list` VALUES ('60', 'El Salvador', 'USD; SVC');
INSERT INTO `mastertbl_currency_list` VALUES ('61', 'Equatorial Guinea', 'XAF');
INSERT INTO `mastertbl_currency_list` VALUES ('62', 'Eritrea', 'ERN');
INSERT INTO `mastertbl_currency_list` VALUES ('63', 'Estonia', 'EEK');
INSERT INTO `mastertbl_currency_list` VALUES ('64', 'Ethiopia', 'ETB');
INSERT INTO `mastertbl_currency_list` VALUES ('65', 'Faeroe Islands', 'DKK');
INSERT INTO `mastertbl_currency_list` VALUES ('66', 'Falkland Islands', 'FKP');
INSERT INTO `mastertbl_currency_list` VALUES ('67', 'Fiji', 'FJD');
INSERT INTO `mastertbl_currency_list` VALUES ('68', 'Finland', 'EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('69', 'Former Yugoslav Republic of Macedonia', 'MKD');
INSERT INTO `mastertbl_currency_list` VALUES ('70', 'France', 'EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('71', 'French Guiana', 'EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('72', 'French Polynesia', 'XPF');
INSERT INTO `mastertbl_currency_list` VALUES ('73', 'Gabon', 'XAF');
INSERT INTO `mastertbl_currency_list` VALUES ('74', 'The Gambia', 'GMD');
INSERT INTO `mastertbl_currency_list` VALUES ('75', 'Georgia', 'GEL');
INSERT INTO `mastertbl_currency_list` VALUES ('76', 'Germany', 'EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('77', 'Ghana', 'GHC');
INSERT INTO `mastertbl_currency_list` VALUES ('78', 'Gibraltar', 'GIP');
INSERT INTO `mastertbl_currency_list` VALUES ('79', 'Greece', 'EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('80', 'Greenland', 'DKK');
INSERT INTO `mastertbl_currency_list` VALUES ('81', 'Grenada', 'XCD');
INSERT INTO `mastertbl_currency_list` VALUES ('82', 'Guadeloupe', 'EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('83', 'Guam', 'USD');
INSERT INTO `mastertbl_currency_list` VALUES ('84', 'Guatemala', 'GTQ');
INSERT INTO `mastertbl_currency_list` VALUES ('85', 'Guinea', 'GNF');
INSERT INTO `mastertbl_currency_list` VALUES ('86', 'Guinea-Bissau', 'XOF');
INSERT INTO `mastertbl_currency_list` VALUES ('87', 'Guyana', 'GYD');
INSERT INTO `mastertbl_currency_list` VALUES ('88', 'Haiti', 'HTG');
INSERT INTO `mastertbl_currency_list` VALUES ('90', 'Honduras', 'HNL');
INSERT INTO `mastertbl_currency_list` VALUES ('91', 'Hong Kong', 'HKD');
INSERT INTO `mastertbl_currency_list` VALUES ('92', 'Hungary', 'HUF');
INSERT INTO `mastertbl_currency_list` VALUES ('93', 'Iceland', 'ISK');
INSERT INTO `mastertbl_currency_list` VALUES ('94', 'India', 'INR');
INSERT INTO `mastertbl_currency_list` VALUES ('95', 'Indonesia', 'IDR');
INSERT INTO `mastertbl_currency_list` VALUES ('96', 'Iran', 'IRR');
INSERT INTO `mastertbl_currency_list` VALUES ('97', 'Iraq', 'IQD');
INSERT INTO `mastertbl_currency_list` VALUES ('98', 'Ireland', 'EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('99', 'Israel', 'ILS');
INSERT INTO `mastertbl_currency_list` VALUES ('100', 'Italy', 'EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('101', 'Jamaica', 'JMD');
INSERT INTO `mastertbl_currency_list` VALUES ('102', 'Japan', 'JPY');
INSERT INTO `mastertbl_currency_list` VALUES ('103', 'Jordan', 'JOD');
INSERT INTO `mastertbl_currency_list` VALUES ('104', 'Kazakhstan', 'KZT');
INSERT INTO `mastertbl_currency_list` VALUES ('105', 'Kenya', 'KES');
INSERT INTO `mastertbl_currency_list` VALUES ('106', 'Kuwait', 'KWD');
INSERT INTO `mastertbl_currency_list` VALUES ('107', 'Kyrgyzstan', 'KGS');
INSERT INTO `mastertbl_currency_list` VALUES ('108', 'Laos', 'LAK');
INSERT INTO `mastertbl_currency_list` VALUES ('109', 'Latvia', 'LVL');
INSERT INTO `mastertbl_currency_list` VALUES ('110', 'Lebanon', 'LBP');
INSERT INTO `mastertbl_currency_list` VALUES ('111', 'Lesotho', 'LSL');
INSERT INTO `mastertbl_currency_list` VALUES ('112', 'Liberia', 'LRD');
INSERT INTO `mastertbl_currency_list` VALUES ('113', 'Libya', 'LYD');
INSERT INTO `mastertbl_currency_list` VALUES ('114', 'Liechtenstein', 'CHF');
INSERT INTO `mastertbl_currency_list` VALUES ('115', 'Lithuania', 'LTL');
INSERT INTO `mastertbl_currency_list` VALUES ('116', 'Luxembourg', 'EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('117', 'Macau', 'MOP');
INSERT INTO `mastertbl_currency_list` VALUES ('118', 'Madagascar', 'MGF');
INSERT INTO `mastertbl_currency_list` VALUES ('119', 'Malawi', 'MWK');
INSERT INTO `mastertbl_currency_list` VALUES ('120', 'Malaysia', 'MYR');
INSERT INTO `mastertbl_currency_list` VALUES ('121', 'Maldives', 'MVR');
INSERT INTO `mastertbl_currency_list` VALUES ('122', 'Mali', 'XOF');
INSERT INTO `mastertbl_currency_list` VALUES ('123', 'Malta', 'MTL');
INSERT INTO `mastertbl_currency_list` VALUES ('124', 'Marshall Islands', 'USD');
INSERT INTO `mastertbl_currency_list` VALUES ('125', 'Martinique', 'EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('126', 'Mauritania', 'MRO');
INSERT INTO `mastertbl_currency_list` VALUES ('127', 'Mauritius', 'MUR');
INSERT INTO `mastertbl_currency_list` VALUES ('128', 'Mayotte', 'EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('129', 'Mexico', 'MXN');
INSERT INTO `mastertbl_currency_list` VALUES ('130', 'Micronesia', 'USD');
INSERT INTO `mastertbl_currency_list` VALUES ('131', 'Moldova', 'MDL');
INSERT INTO `mastertbl_currency_list` VALUES ('132', 'Monaco', 'EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('133', 'Mongolia', 'MNT');
INSERT INTO `mastertbl_currency_list` VALUES ('134', 'Montserrat', 'XCD');
INSERT INTO `mastertbl_currency_list` VALUES ('135', 'Morocco', 'MAD');
INSERT INTO `mastertbl_currency_list` VALUES ('136', 'Mozambique', 'MZM');
INSERT INTO `mastertbl_currency_list` VALUES ('137', 'Myanmar', 'MMK');
INSERT INTO `mastertbl_currency_list` VALUES ('138', 'Namibia', 'NAD; ZAR');
INSERT INTO `mastertbl_currency_list` VALUES ('139', 'Nauru', 'AUD');
INSERT INTO `mastertbl_currency_list` VALUES ('140', 'Nepal', 'NPR');
INSERT INTO `mastertbl_currency_list` VALUES ('141', 'Netherlands', 'EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('142', 'Netherlands Antilles', 'ANG');
INSERT INTO `mastertbl_currency_list` VALUES ('143', 'New Caledonia', 'XPF');
INSERT INTO `mastertbl_currency_list` VALUES ('144', 'New Zealand', 'NZD');
INSERT INTO `mastertbl_currency_list` VALUES ('145', 'Nicaragua', 'NIO');
INSERT INTO `mastertbl_currency_list` VALUES ('146', 'Niger', 'XOF');
INSERT INTO `mastertbl_currency_list` VALUES ('147', 'Nigeria', 'NGN');
INSERT INTO `mastertbl_currency_list` VALUES ('148', 'Niue', 'NZD');
INSERT INTO `mastertbl_currency_list` VALUES ('149', 'Norfolk Island', 'AUD');
INSERT INTO `mastertbl_currency_list` VALUES ('150', 'North Korea', 'KPW');
INSERT INTO `mastertbl_currency_list` VALUES ('151', 'Northern Marianas', 'USD');
INSERT INTO `mastertbl_currency_list` VALUES ('152', 'Norway', 'NOK');
INSERT INTO `mastertbl_currency_list` VALUES ('153', 'Oman', 'OMR');
INSERT INTO `mastertbl_currency_list` VALUES ('154', 'Pakistan', 'PKR');
INSERT INTO `mastertbl_currency_list` VALUES ('155', 'Palau', 'USD');
INSERT INTO `mastertbl_currency_list` VALUES ('156', 'Panama', 'PAB');
INSERT INTO `mastertbl_currency_list` VALUES ('157', 'Papua New Guinea', 'PGK');
INSERT INTO `mastertbl_currency_list` VALUES ('158', 'Paraguay', 'PYG');
INSERT INTO `mastertbl_currency_list` VALUES ('159', 'Peru', 'PEN');
INSERT INTO `mastertbl_currency_list` VALUES ('160', 'Philippines', 'PHP');
INSERT INTO `mastertbl_currency_list` VALUES ('161', 'Pitcairn Islands', 'NZD');
INSERT INTO `mastertbl_currency_list` VALUES ('162', 'Poland', 'PLN');
INSERT INTO `mastertbl_currency_list` VALUES ('163', 'Portugal', 'EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('164', 'Puerto Rico', 'USD');
INSERT INTO `mastertbl_currency_list` VALUES ('165', 'Qatar', 'QAR');
INSERT INTO `mastertbl_currency_list` VALUES ('166', 'Réunion', 'EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('167', 'Romania', 'ROL');
INSERT INTO `mastertbl_currency_list` VALUES ('168', 'Russia', 'RUB');
INSERT INTO `mastertbl_currency_list` VALUES ('169', 'Rwanda', 'RWF');
INSERT INTO `mastertbl_currency_list` VALUES ('170', 'Saint Helena', 'SHP');
INSERT INTO `mastertbl_currency_list` VALUES ('171', 'Saint Kitts and Nevis', 'XCD');
INSERT INTO `mastertbl_currency_list` VALUES ('172', 'Saint Lucia', 'XCD');
INSERT INTO `mastertbl_currency_list` VALUES ('173', 'Saint Pierre and Miquelon', 'EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('174', 'Saint Vincent and the Grenadines', 'XCD');
INSERT INTO `mastertbl_currency_list` VALUES ('175', 'Samoa', 'WST');
INSERT INTO `mastertbl_currency_list` VALUES ('176', 'San Marino', 'EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('177', 'São Tomé and Príncipe', 'STD');
INSERT INTO `mastertbl_currency_list` VALUES ('178', 'Saudi Arabia', 'SAR');
INSERT INTO `mastertbl_currency_list` VALUES ('179', 'Senegal', 'XOF');
INSERT INTO `mastertbl_currency_list` VALUES ('180', 'Serbia and Montenegro', 'YUM; EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('181', 'Seychelles', 'SCR');
INSERT INTO `mastertbl_currency_list` VALUES ('182', 'Sierra Leone', 'SLL');
INSERT INTO `mastertbl_currency_list` VALUES ('183', 'Singapore', 'SGD');
INSERT INTO `mastertbl_currency_list` VALUES ('184', 'Slovakia', 'SKK');
INSERT INTO `mastertbl_currency_list` VALUES ('185', 'Slovenia', 'SIT');
INSERT INTO `mastertbl_currency_list` VALUES ('186', 'Solomon Islands', 'SBD');
INSERT INTO `mastertbl_currency_list` VALUES ('187', 'Somalia', 'SOS');
INSERT INTO `mastertbl_currency_list` VALUES ('188', 'South Africa', 'ZAR');
INSERT INTO `mastertbl_currency_list` VALUES ('190', 'South Korea', 'KRW');
INSERT INTO `mastertbl_currency_list` VALUES ('191', 'Spain', 'EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('192', 'Sri Lanka', 'LKR');
INSERT INTO `mastertbl_currency_list` VALUES ('193', 'Sudan', 'SDD');
INSERT INTO `mastertbl_currency_list` VALUES ('194', 'Suriname', 'SRG');
INSERT INTO `mastertbl_currency_list` VALUES ('195', 'Svalbard and Jan Mayen', 'NOK');
INSERT INTO `mastertbl_currency_list` VALUES ('196', 'Swaziland', 'SZL');
INSERT INTO `mastertbl_currency_list` VALUES ('197', 'Sweden', 'SEK');
INSERT INTO `mastertbl_currency_list` VALUES ('198', 'Switzerland', 'CHF');
INSERT INTO `mastertbl_currency_list` VALUES ('199', 'Syria', 'SYP');
INSERT INTO `mastertbl_currency_list` VALUES ('200', 'Taiwan', 'TWD');
INSERT INTO `mastertbl_currency_list` VALUES ('201', 'Tajikistan', 'TJS');
INSERT INTO `mastertbl_currency_list` VALUES ('202', 'Tanzania', 'TZS');
INSERT INTO `mastertbl_currency_list` VALUES ('203', 'Thailand', 'THB');
INSERT INTO `mastertbl_currency_list` VALUES ('204', 'Timor-Leste', 'USD');
INSERT INTO `mastertbl_currency_list` VALUES ('205', 'Togo', 'XOF');
INSERT INTO `mastertbl_currency_list` VALUES ('206', 'Tokelau', 'NZD');
INSERT INTO `mastertbl_currency_list` VALUES ('207', 'Tonga', 'TOP');
INSERT INTO `mastertbl_currency_list` VALUES ('208', 'Trinidad and Tobago', 'TTD');
INSERT INTO `mastertbl_currency_list` VALUES ('209', 'Tunisia', 'TND');
INSERT INTO `mastertbl_currency_list` VALUES ('210', 'Turkey', 'TRL');
INSERT INTO `mastertbl_currency_list` VALUES ('211', 'Turkmenistan', 'TMM');
INSERT INTO `mastertbl_currency_list` VALUES ('212', 'Turks and Caicos Islands', 'USD');
INSERT INTO `mastertbl_currency_list` VALUES ('213', 'Tuvalu', 'AUD');
INSERT INTO `mastertbl_currency_list` VALUES ('214', 'Uganda', 'UGX');
INSERT INTO `mastertbl_currency_list` VALUES ('215', 'Ukraine', 'UAH');
INSERT INTO `mastertbl_currency_list` VALUES ('216', 'United Arab Emirates', 'AED');
INSERT INTO `mastertbl_currency_list` VALUES ('217', 'United Kingdom', 'GBP');
INSERT INTO `mastertbl_currency_list` VALUES ('218', 'United States', 'USD');
INSERT INTO `mastertbl_currency_list` VALUES ('220', 'Uruguay', 'UYU');
INSERT INTO `mastertbl_currency_list` VALUES ('221', 'US Virgin Islands', 'USD');
INSERT INTO `mastertbl_currency_list` VALUES ('222', 'Uzbekistan', 'UZS');
INSERT INTO `mastertbl_currency_list` VALUES ('223', 'Vanuatu', 'VUV');
INSERT INTO `mastertbl_currency_list` VALUES ('224', 'The Hoy See (Vatican City State)', 'EUR');
INSERT INTO `mastertbl_currency_list` VALUES ('225', 'Venezuela', 'VEB');
INSERT INTO `mastertbl_currency_list` VALUES ('226', 'Vietnam', 'VND');
INSERT INTO `mastertbl_currency_list` VALUES ('227', 'Wallis and Futuna', 'XPF');
INSERT INTO `mastertbl_currency_list` VALUES ('228', 'Yemen', 'YER');
INSERT INTO `mastertbl_currency_list` VALUES ('229', 'Zambia', 'ZMK');
INSERT INTO `mastertbl_currency_list` VALUES ('230', 'Zimbabwe', 'ZWD');

-- ----------------------------
-- Table structure for mastertbl_department
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_department`;
CREATE TABLE `mastertbl_department` (
  `department_id` varchar(255) NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_department
-- ----------------------------
INSERT INTO `mastertbl_department` VALUES ('3', 'Evening Batch');
INSERT INTO `mastertbl_department` VALUES ('48a0c4f12250', 'Night batch');
INSERT INTO `mastertbl_department` VALUES ('5', 'Morning Batch');
INSERT INTO `mastertbl_department` VALUES ('6', 'Afternoon Batch');

-- ----------------------------
-- Table structure for mastertbl_enquiry_methods
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_enquiry_methods`;
CREATE TABLE `mastertbl_enquiry_methods` (
  `enquiry_method_id` int(11) NOT NULL AUTO_INCREMENT,
  `method_name` varchar(255) DEFAULT NULL,
  `method_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`enquiry_method_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1009 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_enquiry_methods
-- ----------------------------
INSERT INTO `mastertbl_enquiry_methods` VALUES ('1001', 'Direct', 'default');
INSERT INTO `mastertbl_enquiry_methods` VALUES ('1002', 'Phone', null);
INSERT INTO `mastertbl_enquiry_methods` VALUES ('1003', 'Tv', null);
INSERT INTO `mastertbl_enquiry_methods` VALUES ('1004', 'News paper', null);
INSERT INTO `mastertbl_enquiry_methods` VALUES ('1005', 'Method one', null);
INSERT INTO `mastertbl_enquiry_methods` VALUES ('1006', 'Method 2', null);
INSERT INTO `mastertbl_enquiry_methods` VALUES ('1007', 'Method 3', '');
INSERT INTO `mastertbl_enquiry_methods` VALUES ('1008', 'Method 4', null);

-- ----------------------------
-- Table structure for mastertbl_exam_board
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_exam_board`;
CREATE TABLE `mastertbl_exam_board` (
  `exam_board_id` varchar(255) NOT NULL,
  `exam_board` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`exam_board_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_exam_board
-- ----------------------------
INSERT INTO `mastertbl_exam_board` VALUES ('14', 'VHSE');
INSERT INTO `mastertbl_exam_board` VALUES ('15', 'SERT');
INSERT INTO `mastertbl_exam_board` VALUES ('2', 'CBSE');
INSERT INTO `mastertbl_exam_board` VALUES ('4', 'HSE');
INSERT INTO `mastertbl_exam_board` VALUES ('78e064482843', 'Board Of Technical Education');

-- ----------------------------
-- Table structure for mastertbl_experience_duration
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_experience_duration`;
CREATE TABLE `mastertbl_experience_duration` (
  `experience_duration_id` varchar(255) NOT NULL,
  `duration` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`experience_duration_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_experience_duration
-- ----------------------------
INSERT INTO `mastertbl_experience_duration` VALUES ('1', '1 mnth');
INSERT INTO `mastertbl_experience_duration` VALUES ('2', '6 to 1 year');
INSERT INTO `mastertbl_experience_duration` VALUES ('5', '1 to 2 years');
INSERT INTO `mastertbl_experience_duration` VALUES ('535c52567429', '1.5years');
INSERT INTO `mastertbl_experience_duration` VALUES ('6', '2.5 years');
INSERT INTO `mastertbl_experience_duration` VALUES ('886ff03e55ae', 'more than 3 years');

-- ----------------------------
-- Table structure for mastertbl_industry
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_industry`;
CREATE TABLE `mastertbl_industry` (
  `industry_id` varchar(100) NOT NULL DEFAULT '',
  `industry` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`industry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_industry
-- ----------------------------
INSERT INTO `mastertbl_industry` VALUES ('1', 'Healthcare');
INSERT INTO `mastertbl_industry` VALUES ('2', 'IT Software');
INSERT INTO `mastertbl_industry` VALUES ('3', 'IT Hardware');

-- ----------------------------
-- Table structure for mastertbl_institute
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_institute`;
CREATE TABLE `mastertbl_institute` (
  `institute_id` varchar(255) NOT NULL,
  `institute` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`institute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_institute
-- ----------------------------
INSERT INTO `mastertbl_institute` VALUES ('1', 'Annamala', 'Australia');
INSERT INTO `mastertbl_institute` VALUES ('3', 'USTGlobal', 'USA');
INSERT INTO `mastertbl_institute` VALUES ('4', 'Saaranga', 'Canada');
INSERT INTO `mastertbl_institute` VALUES ('4278f8745b78', 'ETC', 'USA');
INSERT INTO `mastertbl_institute` VALUES ('5', 'TIME', 'USA');

-- ----------------------------
-- Table structure for mastertbl_intake
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_intake`;
CREATE TABLE `mastertbl_intake` (
  `intake_id` int(11) NOT NULL AUTO_INCREMENT,
  `intake` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`intake_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_intake
-- ----------------------------
INSERT INTO `mastertbl_intake` VALUES ('1', '1 month');
INSERT INTO `mastertbl_intake` VALUES ('2', '2 months');

-- ----------------------------
-- Table structure for mastertbl_jobtype
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_jobtype`;
CREATE TABLE `mastertbl_jobtype` (
  `jobtype_id` varchar(255) NOT NULL,
  `jobtype` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`jobtype_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_jobtype
-- ----------------------------
INSERT INTO `mastertbl_jobtype` VALUES ('3', 'Part Time');
INSERT INTO `mastertbl_jobtype` VALUES ('5735029c223d', 'Night Shifts');
INSERT INTO `mastertbl_jobtype` VALUES ('7', 'Full Time');
INSERT INTO `mastertbl_jobtype` VALUES ('8', 'saturday & sunday');
INSERT INTO `mastertbl_jobtype` VALUES ('9', 'vaccation class');
INSERT INTO `mastertbl_jobtype` VALUES ('f1185e139d97', 'Monday - friday');

-- ----------------------------
-- Table structure for mastertbl_language
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_language`;
CREATE TABLE `mastertbl_language` (
  `language_id` varchar(255) NOT NULL,
  `language` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`language_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_language
-- ----------------------------
INSERT INTO `mastertbl_language` VALUES ('1', 'English');
INSERT INTO `mastertbl_language` VALUES ('26', 'Malayalam');
INSERT INTO `mastertbl_language` VALUES ('3', 'Hindi');
INSERT INTO `mastertbl_language` VALUES ('53', 'French');
INSERT INTO `mastertbl_language` VALUES ('54', 'Arabic');
INSERT INTO `mastertbl_language` VALUES ('56', 'German');
INSERT INTO `mastertbl_language` VALUES ('9110bf1afdba', 'Kannada');

-- ----------------------------
-- Table structure for mastertbl_location
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_location`;
CREATE TABLE `mastertbl_location` (
  `location_id` varchar(100) NOT NULL DEFAULT '',
  `location` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_location
-- ----------------------------
INSERT INTO `mastertbl_location` VALUES ('1', 'Melbourne', 'Australia');
INSERT INTO `mastertbl_location` VALUES ('4', 'Sydney', 'Australia');
INSERT INTO `mastertbl_location` VALUES ('5', 'Brisbane', 'Australia');
INSERT INTO `mastertbl_location` VALUES ('cn_117ee8a18647', 'Ottawa', 'Canada');
INSERT INTO `mastertbl_location` VALUES ('cn_1896e82afe84', '', 'Nigeria');
INSERT INTO `mastertbl_location` VALUES ('cn_2e6b7c4c5886', '', 'USA');
INSERT INTO `mastertbl_location` VALUES ('cn_3de6267b7480', '', 'Switzerland');
INSERT INTO `mastertbl_location` VALUES ('cn_40b271e447d0', 'philadelphia', 'USA');
INSERT INTO `mastertbl_location` VALUES ('cn_527967bbe102', '', 'Canada');
INSERT INTO `mastertbl_location` VALUES ('cn_59c9e7b1addb', 'Hamilton - Waikato', 'New Zealnd');
INSERT INTO `mastertbl_location` VALUES ('cn_5eab8d308dce', '', 'India');
INSERT INTO `mastertbl_location` VALUES ('cn_6c3bfd00cff4', '', 'Sri lanka');
INSERT INTO `mastertbl_location` VALUES ('cn_6cbe5459c3e8', 'perth', 'Australia');
INSERT INTO `mastertbl_location` VALUES ('cn_71b3c86d85ca', '', 'Australia');
INSERT INTO `mastertbl_location` VALUES ('cn_8fb2529afd03', 'New York', 'USA');
INSERT INTO `mastertbl_location` VALUES ('cn_beee6f175bb2', '', 'Pakistan');
INSERT INTO `mastertbl_location` VALUES ('cn_d6d0e6d68936', '', 'New Zealnd');
INSERT INTO `mastertbl_location` VALUES ('cn_d9bd19409831', 'Auckland', 'New Zealnd');
INSERT INTO `mastertbl_location` VALUES ('cn_f5ffce8f41a3', 'Wellington', 'New Zealnd');

-- ----------------------------
-- Table structure for mastertbl_migration_category
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_migration_category`;
CREATE TABLE `mastertbl_migration_category` (
  `migrate_category_id` varchar(255) NOT NULL,
  `migrate_category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`migrate_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_migration_category
-- ----------------------------
INSERT INTO `mastertbl_migration_category` VALUES ('11', 'temporary');
INSERT INTO `mastertbl_migration_category` VALUES ('1f12637cdcd7', 'Contract based');
INSERT INTO `mastertbl_migration_category` VALUES ('6', 'Freevisa');
INSERT INTO `mastertbl_migration_category` VALUES ('9', 'Permanent');
INSERT INTO `mastertbl_migration_category` VALUES ('e00ed9ec7be9', 'visit');

-- ----------------------------
-- Table structure for mastertbl_other_skills
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_other_skills`;
CREATE TABLE `mastertbl_other_skills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `skill` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_other_skills
-- ----------------------------
INSERT INTO `mastertbl_other_skills` VALUES ('1', 'Driving', 'Two Wheeler');
INSERT INTO `mastertbl_other_skills` VALUES ('2', 'Driving', 'Four Wheeler');
INSERT INTO `mastertbl_other_skills` VALUES ('3', 'Driving ', 'Light Motor Vehicle');
INSERT INTO `mastertbl_other_skills` VALUES ('4', 'Swimming', 'Free Style');
INSERT INTO `mastertbl_other_skills` VALUES ('5', 'NA', null);
INSERT INTO `mastertbl_other_skills` VALUES ('6', 'NA', 'NA');
INSERT INTO `mastertbl_other_skills` VALUES ('7', 'df', null);

-- ----------------------------
-- Table structure for mastertbl_other_test
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_other_test`;
CREATE TABLE `mastertbl_other_test` (
  `other_test_id` varchar(255) NOT NULL,
  `other_test` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`other_test_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_other_test
-- ----------------------------
INSERT INTO `mastertbl_other_test` VALUES ('06a3a8ff9540', 'NA');
INSERT INTO `mastertbl_other_test` VALUES ('1', 'Gate');
INSERT INTO `mastertbl_other_test` VALUES ('15', 'MOH');
INSERT INTO `mastertbl_other_test` VALUES ('3dc1fa0d4ef9', 'online Test');

-- ----------------------------
-- Table structure for mastertbl_profession
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_profession`;
CREATE TABLE `mastertbl_profession` (
  `proffesion_id` varchar(255) NOT NULL,
  `profession` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`proffesion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_profession
-- ----------------------------
INSERT INTO `mastertbl_profession` VALUES ('1', 'Nurse');
INSERT INTO `mastertbl_profession` VALUES ('11', 'Software Engineer');
INSERT INTO `mastertbl_profession` VALUES ('13', 'Doctor/Surgen');
INSERT INTO `mastertbl_profession` VALUES ('169ac73993a1', 'Architect');
INSERT INTO `mastertbl_profession` VALUES ('2', 'Teacher');
INSERT INTO `mastertbl_profession` VALUES ('2bfa69f422e4', 'Chemical Engineer');
INSERT INTO `mastertbl_profession` VALUES ('5dcd4f361f3e', 'Junior Engineer');
INSERT INTO `mastertbl_profession` VALUES ('b26ca6c238a2', 'NA');

-- ----------------------------
-- Table structure for mastertbl_program_field
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_program_field`;
CREATE TABLE `mastertbl_program_field` (
  `program_field_id` varchar(255) NOT NULL,
  `program_level_id` varchar(255) DEFAULT NULL,
  `program_field` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`program_field_id`),
  KEY `programlevel&field` (`program_level_id`) USING BTREE,
  CONSTRAINT `mastertbl_program_field_ibfk_1` FOREIGN KEY (`program_level_id`) REFERENCES `mastertbl_program_level` (`program_level_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_program_field
-- ----------------------------
INSERT INTO `mastertbl_program_field` VALUES ('1', '2', 'BSc cs');
INSERT INTO `mastertbl_program_field` VALUES ('10', '2', 'BA History');
INSERT INTO `mastertbl_program_field` VALUES ('11', '3', 'computer Application');
INSERT INTO `mastertbl_program_field` VALUES ('12', '3', 'MBA');
INSERT INTO `mastertbl_program_field` VALUES ('13', '3', 'MA English');
INSERT INTO `mastertbl_program_field` VALUES ('14', '3', 'MTECH');
INSERT INTO `mastertbl_program_field` VALUES ('15', '7', 'Photoshop');
INSERT INTO `mastertbl_program_field` VALUES ('16', '7', 'Animation');
INSERT INTO `mastertbl_program_field` VALUES ('17', '7', 'Drawing');
INSERT INTO `mastertbl_program_field` VALUES ('18', '7', 'Web Design');
INSERT INTO `mastertbl_program_field` VALUES ('19', '6', 'Spoken English');
INSERT INTO `mastertbl_program_field` VALUES ('20', '4', 'Doctor of Psychology');
INSERT INTO `mastertbl_program_field` VALUES ('21', '4', 'Doctor of Social Work');
INSERT INTO `mastertbl_program_field` VALUES ('22', '4', 'Doctor of Medicine\r\n(Medicinae Doctoris)');
INSERT INTO `mastertbl_program_field` VALUES ('23', '4', 'Doctor of Computer Science');
INSERT INTO `mastertbl_program_field` VALUES ('3', '2', 'BBA');
INSERT INTO `mastertbl_program_field` VALUES ('33a7771d7aa1', '3', 'science');
INSERT INTO `mastertbl_program_field` VALUES ('37', '2', 'Btech');
INSERT INTO `mastertbl_program_field` VALUES ('4', '2', 'B.COM');
INSERT INTO `mastertbl_program_field` VALUES ('5', '2', 'BSC Maths');
INSERT INTO `mastertbl_program_field` VALUES ('5ec316dd01cd', '3', 'MA Malayalam');
INSERT INTO `mastertbl_program_field` VALUES ('6', '2', 'BSC Physics');
INSERT INTO `mastertbl_program_field` VALUES ('7', '2', 'BSC Computer Science');
INSERT INTO `mastertbl_program_field` VALUES ('8', '2', 'BA Economics');
INSERT INTO `mastertbl_program_field` VALUES ('9', '2', 'BA English');
INSERT INTO `mastertbl_program_field` VALUES ('d2a8c19f43ff', '3', 'Mphil');

-- ----------------------------
-- Table structure for mastertbl_program_level
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_program_level`;
CREATE TABLE `mastertbl_program_level` (
  `program_level_id` varchar(255) NOT NULL,
  `program_level` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`program_level_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_program_level
-- ----------------------------
INSERT INTO `mastertbl_program_level` VALUES ('2', 'Bachelor');
INSERT INTO `mastertbl_program_level` VALUES ('3', 'Master');
INSERT INTO `mastertbl_program_level` VALUES ('4', 'PhD/Doctorate');
INSERT INTO `mastertbl_program_level` VALUES ('5', 'Prep Course');
INSERT INTO `mastertbl_program_level` VALUES ('6', 'Language Course');
INSERT INTO `mastertbl_program_level` VALUES ('7', 'Short Course');
INSERT INTO `mastertbl_program_level` VALUES ('PGML_b7e9310a7434', 'gf gf ');

-- ----------------------------
-- Table structure for mastertbl_purpose
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_purpose`;
CREATE TABLE `mastertbl_purpose` (
  `purpose_id` varchar(255) NOT NULL,
  `purpose` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_purpose
-- ----------------------------
INSERT INTO `mastertbl_purpose` VALUES ('PU_1006c5fb1ae9', 'Registration Follow Up');
INSERT INTO `mastertbl_purpose` VALUES ('PU_e6ddbb04ae41', 'Fee Payment Follow up');
INSERT INTO `mastertbl_purpose` VALUES ('PU_1971a7260c57', 'Ticketing Follow up');
INSERT INTO `mastertbl_purpose` VALUES ('PU_29d1622351e5', 'Others to type');
INSERT INTO `mastertbl_purpose` VALUES ('PU_f7d6f63275b8', 'admission follow up');

-- ----------------------------
-- Table structure for mastertbl_qualification_duration
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_qualification_duration`;
CREATE TABLE `mastertbl_qualification_duration` (
  `duration_id` varchar(255) NOT NULL,
  `duration` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`duration_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_qualification_duration
-- ----------------------------
INSERT INTO `mastertbl_qualification_duration` VALUES ('5', '1 year');
INSERT INTO `mastertbl_qualification_duration` VALUES ('56ca4b6d515a', '2.5year');
INSERT INTO `mastertbl_qualification_duration` VALUES ('6', '1.5 year');
INSERT INTO `mastertbl_qualification_duration` VALUES ('8', '3 year');
INSERT INTO `mastertbl_qualification_duration` VALUES ('d4bc9f21ea8c', 'mor than 3 year');
INSERT INTO `mastertbl_qualification_duration` VALUES ('d7b23bb557df', '2 year');

-- ----------------------------
-- Table structure for mastertbl_salary
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_salary`;
CREATE TABLE `mastertbl_salary` (
  `salary_id` varchar(255) NOT NULL,
  `salary` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`salary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_salary
-- ----------------------------
INSERT INTO `mastertbl_salary` VALUES ('10', '40000');
INSERT INTO `mastertbl_salary` VALUES ('11', '50000');
INSERT INTO `mastertbl_salary` VALUES ('13', '678090');
INSERT INTO `mastertbl_salary` VALUES ('14', '200000');
INSERT INTO `mastertbl_salary` VALUES ('15', '5600888');
INSERT INTO `mastertbl_salary` VALUES ('16', '999900');
INSERT INTO `mastertbl_salary` VALUES ('17', '888888');
INSERT INTO `mastertbl_salary` VALUES ('2d0856832a26', '70000');
INSERT INTO `mastertbl_salary` VALUES ('635d04b8db3b', '100500');
INSERT INTO `mastertbl_salary` VALUES ('7', '25000');
INSERT INTO `mastertbl_salary` VALUES ('8', '35000');
INSERT INTO `mastertbl_salary` VALUES ('9', '30000');

-- ----------------------------
-- Table structure for mastertbl_score_management
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_score_management`;
CREATE TABLE `mastertbl_score_management` (
  `score_id` varchar(255) NOT NULL,
  `score` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`score_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_score_management
-- ----------------------------
INSERT INTO `mastertbl_score_management` VALUES ('1', '1');
INSERT INTO `mastertbl_score_management` VALUES ('2485ffc64d71', '4');
INSERT INTO `mastertbl_score_management` VALUES ('2debb4a4cac3', '5.5');
INSERT INTO `mastertbl_score_management` VALUES ('3be65379782b', '6.5');
INSERT INTO `mastertbl_score_management` VALUES ('577d995428f9', '7');
INSERT INTO `mastertbl_score_management` VALUES ('7fb8594a4d14', '2');
INSERT INTO `mastertbl_score_management` VALUES ('8e36cc6bc656', '6');
INSERT INTO `mastertbl_score_management` VALUES ('99e0ada2996b', '1.5');
INSERT INTO `mastertbl_score_management` VALUES ('a12e3501a38c', '3');
INSERT INTO `mastertbl_score_management` VALUES ('c15b01b7e379', '2.5');
INSERT INTO `mastertbl_score_management` VALUES ('d4eb4f82200a', '4.5');
INSERT INTO `mastertbl_score_management` VALUES ('df6751565ae7', '5');
INSERT INTO `mastertbl_score_management` VALUES ('f5c0dfcb11a2', '3.5');

-- ----------------------------
-- Table structure for mastertbl_suggested_course
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_suggested_course`;
CREATE TABLE `mastertbl_suggested_course` (
  `course_id` varchar(255) NOT NULL,
  `country` varchar(50) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `university` varchar(100) DEFAULT NULL,
  `level` varchar(100) DEFAULT NULL,
  `course` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_suggested_course
-- ----------------------------
INSERT INTO `mastertbl_suggested_course` VALUES ('1', 'Canada', 'California', 'MG University', 'Master', 'MCA');
INSERT INTO `mastertbl_suggested_course` VALUES ('163cd2545a8d', 'Germany', 'Hesse', 'MG University', 'Master', 'B.COM');
INSERT INTO `mastertbl_suggested_course` VALUES ('2', 'UK', 'California', 'MG University', 'Master', 'MBA');
INSERT INTO `mastertbl_suggested_course` VALUES ('3', 'Canada', 'California', 'MG University', 'Bachelor', 'BBA');
INSERT INTO `mastertbl_suggested_course` VALUES ('4', 'USA', 'California', 'MG University', 'Master', 'MBA');
INSERT INTO `mastertbl_suggested_course` VALUES ('77e076bf981f', 'Australia', 'Queensland', 'MG University', 'Bachelor', 'MTECH');
INSERT INTO `mastertbl_suggested_course` VALUES ('ab84d9251c60', 'Germany', 'Thuringia', 'MG University', 'Language Course', 'BA English');

-- ----------------------------
-- Table structure for mastertbl_task_follow_status
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_task_follow_status`;
CREATE TABLE `mastertbl_task_follow_status` (
  `task_follow_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_follow_status` varchar(100) DEFAULT NULL,
  `enable` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`task_follow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_task_follow_status
-- ----------------------------
INSERT INTO `mastertbl_task_follow_status` VALUES ('1', 'Register Now', '1');
INSERT INTO `mastertbl_task_follow_status` VALUES ('2', 'Call Not Picking', '1');
INSERT INTO `mastertbl_task_follow_status` VALUES ('3', 'Call Attended & Call Later', '1');
INSERT INTO `mastertbl_task_follow_status` VALUES ('4', 'Not Completed Call Later', '1');
INSERT INTO `mastertbl_task_follow_status` VALUES ('5', 'Will Contact Future', '1');
INSERT INTO `mastertbl_task_follow_status` VALUES ('6', 'Not Interested', '1');
INSERT INTO `mastertbl_task_follow_status` VALUES ('7', 'Fake Call', '1');
INSERT INTO `mastertbl_task_follow_status` VALUES ('8', 'Number Not Exist', '1');
INSERT INTO `mastertbl_task_follow_status` VALUES ('9', 'Confirm Soon', '1');
INSERT INTO `mastertbl_task_follow_status` VALUES ('10', 'Cancelled', '1');
INSERT INTO `mastertbl_task_follow_status` VALUES ('11', 'Will Walk in', '1');
INSERT INTO `mastertbl_task_follow_status` VALUES ('12', 'Registration Perspective', '1');
INSERT INTO `mastertbl_task_follow_status` VALUES ('13', 'Assess Now', '1');

-- ----------------------------
-- Table structure for mastertbl_technology
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_technology`;
CREATE TABLE `mastertbl_technology` (
  `tech_id` int(11) NOT NULL AUTO_INCREMENT,
  `technology` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tech_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_technology
-- ----------------------------
INSERT INTO `mastertbl_technology` VALUES ('1', 'NA');
INSERT INTO `mastertbl_technology` VALUES ('2', 'JAVA');
INSERT INTO `mastertbl_technology` VALUES ('3', 'C');
INSERT INTO `mastertbl_technology` VALUES ('4', 'C++');
INSERT INTO `mastertbl_technology` VALUES ('5', 'C#');
INSERT INTO `mastertbl_technology` VALUES ('6', 'VB');
INSERT INTO `mastertbl_technology` VALUES ('7', 'ASP');
INSERT INTO `mastertbl_technology` VALUES ('8', 'JSP');
INSERT INTO `mastertbl_technology` VALUES ('9', 'MS OFFICE');
INSERT INTO `mastertbl_technology` VALUES ('10', 'GRAPHICS DESIGN');
INSERT INTO `mastertbl_technology` VALUES ('11', 'ANIMATION');
INSERT INTO `mastertbl_technology` VALUES ('12', 'spring');

-- ----------------------------
-- Table structure for mastertbl_thread_delay
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_thread_delay`;
CREATE TABLE `mastertbl_thread_delay` (
  `thread_delay_id` int(11) NOT NULL,
  `thread_delay` int(11) NOT NULL,
  PRIMARY KEY (`thread_delay_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_thread_delay
-- ----------------------------
INSERT INTO `mastertbl_thread_delay` VALUES ('1', '15');

-- ----------------------------
-- Table structure for mastertbl_timing
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_timing`;
CREATE TABLE `mastertbl_timing` (
  `timing_id` varchar(255) NOT NULL,
  `timing` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`timing_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_timing
-- ----------------------------
INSERT INTO `mastertbl_timing` VALUES ('1', '9.00am - 11.00am');
INSERT INTO `mastertbl_timing` VALUES ('2', '1.30pm - 3.30pm');
INSERT INTO `mastertbl_timing` VALUES ('3', '10.00am - 12.00pm');
INSERT INTO `mastertbl_timing` VALUES ('4', '9.00am - 4.00pm');
INSERT INTO `mastertbl_timing` VALUES ('7', '8.00am - 12.00am');
INSERT INTO `mastertbl_timing` VALUES ('8', '11.00am - 4.00pm');
INSERT INTO `mastertbl_timing` VALUES ('a1d18949f21a', '7.00am - 10.00am');

-- ----------------------------
-- Table structure for mastertbl_training_program
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_training_program`;
CREATE TABLE `mastertbl_training_program` (
  `training_program_id` int(11) NOT NULL AUTO_INCREMENT,
  `program` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`training_program_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_training_program
-- ----------------------------

-- ----------------------------
-- Table structure for mastertbl_university
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_university`;
CREATE TABLE `mastertbl_university` (
  `university_id` varchar(255) NOT NULL,
  `university` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`university_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_university
-- ----------------------------
INSERT INTO `mastertbl_university` VALUES ('1', 'cusat');
INSERT INTO `mastertbl_university` VALUES ('2', 'Kerala University');
INSERT INTO `mastertbl_university` VALUES ('3', 'MG University');
INSERT INTO `mastertbl_university` VALUES ('39b0fe5f99ac', 'Calicut University');
INSERT INTO `mastertbl_university` VALUES ('87857f5c9786', 'Kannur University');

-- ----------------------------
-- Table structure for mastertbl_wrok_category
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_wrok_category`;
CREATE TABLE `mastertbl_wrok_category` (
  `work_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`work_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mastertbl_wrok_category
-- ----------------------------

-- ----------------------------
-- Table structure for master_common_pool_config
-- ----------------------------
DROP TABLE IF EXISTS `master_common_pool_config`;
CREATE TABLE `master_common_pool_config` (
  `id` varchar(30) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `flag_limit` int(11) DEFAULT NULL,
  `enquiry_limit` int(11) DEFAULT NULL,
  `enquiry_duration` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fkkey_pool` (`user_name`) USING BTREE,
  CONSTRAINT `master_common_pool_config_ibfk_1` FOREIGN KEY (`user_name`) REFERENCES `login_tbl` (`user_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of master_common_pool_config
-- ----------------------------
INSERT INTO `master_common_pool_config` VALUES ('erew', 'a', '15', '15', '75');
INSERT INTO `master_common_pool_config` VALUES ('POOL_010bd3267a8d', 'sanoop', '10', '10', '180');
INSERT INTO `master_common_pool_config` VALUES ('POOL_0d2a47ee2d83', 'fhyty', '15', '12', '172800');
INSERT INTO `master_common_pool_config` VALUES ('POOL_6d8f405d88b0', 'jitha', '5', '10', '600');
INSERT INTO `master_common_pool_config` VALUES ('POOL_86377e6d23e1', 'sumeshzoft', '5', '10', '300');
INSERT INTO `master_common_pool_config` VALUES ('POOL_8b1d397d574c', 'fxfgg', '13', '14', '10000');
INSERT INTO `master_common_pool_config` VALUES ('POOL_94b44f205dbb', 's', '5', '5', '180');
INSERT INTO `master_common_pool_config` VALUES ('POOL_a2a00ed1473e', 'update', '5', '10', '900600');
INSERT INTO `master_common_pool_config` VALUES ('POOL_b2d854e56063', 'sandeep', '5', '10', '100');
INSERT INTO `master_common_pool_config` VALUES ('POOL_c486a8d19ccd', 'sheela', '5', '13', '180');
INSERT INTO `master_common_pool_config` VALUES ('POOL_d8cd24f401b5', 'user a', '5', '5', '600');

-- ----------------------------
-- Table structure for master_counselore_timelimit
-- ----------------------------
DROP TABLE IF EXISTS `master_counselore_timelimit`;
CREATE TABLE `master_counselore_timelimit` (
  `id` varchar(255) NOT NULL,
  `time_limit` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of master_counselore_timelimit
-- ----------------------------
INSERT INTO `master_counselore_timelimit` VALUES ('time_1234', '39', 'Days');

-- ----------------------------
-- Table structure for master_counselor_limit
-- ----------------------------
DROP TABLE IF EXISTS `master_counselor_limit`;
CREATE TABLE `master_counselor_limit` (
  `id` varchar(255) NOT NULL,
  `login_id` varchar(255) DEFAULT NULL,
  `enquiry_limit` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `limit_key` (`login_id`) USING BTREE,
  CONSTRAINT `master_counselor_limit_ibfk_1` FOREIGN KEY (`login_id`) REFERENCES `login_tbl` (`login_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of master_counselor_limit
-- ----------------------------
INSERT INTO `master_counselor_limit` VALUES ('lm_52f01224cdb4', 'id_8330eb504c4e', '78');
INSERT INTO `master_counselor_limit` VALUES ('lm_7b58ab328286', 'id_b8b54d20a040', '34');
INSERT INTO `master_counselor_limit` VALUES ('lm_ab1aba0caebc', 'd27b627b817b', '61');

-- ----------------------------
-- Table structure for master_enquiry_status
-- ----------------------------
DROP TABLE IF EXISTS `master_enquiry_status`;
CREATE TABLE `master_enquiry_status` (
  `enq_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `enquiry_status` varchar(255) DEFAULT NULL,
  `feature` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`enq_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of master_enquiry_status
-- ----------------------------
INSERT INTO `master_enquiry_status` VALUES ('1', 'Register Now', null);
INSERT INTO `master_enquiry_status` VALUES ('2', 'Call Not Picking', null);
INSERT INTO `master_enquiry_status` VALUES ('3', 'Call Attended & Call Later', null);
INSERT INTO `master_enquiry_status` VALUES ('4', 'Not Completed Call Later', null);
INSERT INTO `master_enquiry_status` VALUES ('5', 'Will Contact Future', null);
INSERT INTO `master_enquiry_status` VALUES ('6', 'Not Interested', null);
INSERT INTO `master_enquiry_status` VALUES ('7', 'Fake Call', null);
INSERT INTO `master_enquiry_status` VALUES ('8', 'Number Not Exist', null);
INSERT INTO `master_enquiry_status` VALUES ('9', 'Confirm Soon', null);
INSERT INTO `master_enquiry_status` VALUES ('10', 'Cancelled', null);
INSERT INTO `master_enquiry_status` VALUES ('11', 'Will Walk in', null);
INSERT INTO `master_enquiry_status` VALUES ('12', 'Registration Perspective', null);
INSERT INTO `master_enquiry_status` VALUES ('13', 'Assess Now', null);
INSERT INTO `master_enquiry_status` VALUES ('14', 'Registered', null);

-- ----------------------------
-- Table structure for migration_country
-- ----------------------------
DROP TABLE IF EXISTS `migration_country`;
CREATE TABLE `migration_country` (
  `id` varchar(255) NOT NULL,
  `country` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of migration_country
-- ----------------------------
INSERT INTO `migration_country` VALUES ('075f95838dff', 'Sri Lanka');
INSERT INTO `migration_country` VALUES ('3', 'Canada');
INSERT INTO `migration_country` VALUES ('4', 'Africa');
INSERT INTO `migration_country` VALUES ('5', 'India');
INSERT INTO `migration_country` VALUES ('8d94c98d406c', 'Brazil');

-- ----------------------------
-- Table structure for newoffers_table
-- ----------------------------
DROP TABLE IF EXISTS `newoffers_table`;
CREATE TABLE `newoffers_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` date DEFAULT NULL,
  `users` varchar(255) DEFAULT NULL,
  `title` text,
  `description` text,
  `expired_date` date DEFAULT NULL,
  `branch_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `branchId` (`branch_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of newoffers_table
-- ----------------------------

-- ----------------------------
-- Table structure for notice_table
-- ----------------------------
DROP TABLE IF EXISTS `notice_table`;
CREATE TABLE `notice_table` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `created_date` date DEFAULT NULL,
  `title` text,
  `description` text,
  `expired_date` date DEFAULT NULL,
  `branch_id` varchar(100) DEFAULT NULL,
  `from_date` date DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `branch` (`branch_id`) USING BTREE,
  CONSTRAINT `notice_table_ibfk_1` FOREIGN KEY (`branch_id`) REFERENCES `branch_tbl` (`branch_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of notice_table
-- ----------------------------
INSERT INTO `notice_table` VALUES ('not_03e0283c2eaf', '2015-09-14', 'jbkjlkj', 'bknkmnkl', '2015-09-16', null, null, null);
INSERT INTO `notice_table` VALUES ('not_0bc241efc25e', '2016-05-21', 'dfg', 'fdgfdg', '2016-05-27', null, null, null);
INSERT INTO `notice_table` VALUES ('not_1ca3cb0274ec', '2015-09-11', 'Campus Recriutomet', 'gfkgjdlk', '2015-09-18', '73ec84795592', null, null);
INSERT INTO `notice_table` VALUES ('not_2a34661d50d9', '2015-09-11', 'qwerty', 'aaaasssssddddffffgggghhhhjjjjjkkkklllllllll', '2015-09-11', '54dcb369e715', null, null);
INSERT INTO `notice_table` VALUES ('not_2da929dc0073', '2016-01-18', 'Client Meeting !', 'Today 5.30 pm we have a client meeting scheduled . Warm welcome to you to this event ! ', '2017-01-28', null, null, null);
INSERT INTO `notice_table` VALUES ('not_4c5a7c87fb9d', '2015-09-14', 'jsdfjsghfae', 'befksnbfvdsng', '2015-09-15', null, null, null);
INSERT INTO `notice_table` VALUES ('not_53753116907a', '2016-01-18', 'New Notice !', 'Complete 100 enquiries this month . You will get surprice GiFt Hurry Up!', '2017-01-20', null, null, null);
INSERT INTO `notice_table` VALUES ('not_69ebdeb21e48', '2015-09-14', 'gud ecening....', 'helloooooo', '2015-09-15', null, null, null);
INSERT INTO `notice_table` VALUES ('NOT_9fed63919c48', '2016-05-25', 'test all', 'test all', '2016-06-03', null, null, null);
INSERT INTO `notice_table` VALUES ('NOT_c2cb7f35c08f', '2016-05-25', 'new notice', 'testeeeeeeeeeeeeeeeee', '2016-05-26', null, null, null);
INSERT INTO `notice_table` VALUES ('NOT_f74b64dab696', '2016-05-25', 'notice 10', 'notice 10notice 10notice 10notice 10notice 10notice 10notice 10notice 10notice 10notice 10notice 10notice 10notice 10', '2016-06-09', '04a3c42146e2', null, null);
INSERT INTO `notice_table` VALUES ('not_f8c661260ea8', '2016-05-21', '77777777777777777', 'tytyu', '2016-06-10', null, null, null);

-- ----------------------------
-- Table structure for privilage_tbl
-- ----------------------------
DROP TABLE IF EXISTS `privilage_tbl`;
CREATE TABLE `privilage_tbl` (
  `privilage_id` varchar(255) NOT NULL,
  `privilage` varchar(150) DEFAULT NULL,
  `privilage_desc` varchar(255) DEFAULT NULL,
  `dummy2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`privilage_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of privilage_tbl
-- ----------------------------
INSERT INTO `privilage_tbl` VALUES ('1', 'ALL', 'alll', null);
INSERT INTO `privilage_tbl` VALUES ('2', 'Enquiry', 'nil', null);
INSERT INTO `privilage_tbl` VALUES ('4', 'counsellor', 'counselor privileges', null);

-- ----------------------------
-- Table structure for reminder_tbl
-- ----------------------------
DROP TABLE IF EXISTS `reminder_tbl`;
CREATE TABLE `reminder_tbl` (
  `rem_id` varchar(25) NOT NULL,
  `meeting_id` varchar(100) DEFAULT NULL,
  `participant_id` varchar(100) DEFAULT NULL,
  `reminder_date` datetime DEFAULT NULL,
  `reminder_desc` text,
  `created_user` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `read_flag` int(5) DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `branch` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`rem_id`),
  KEY `fkey_remindersss` (`meeting_id`),
  CONSTRAINT `reminder_tbl_ibfk_1` FOREIGN KEY (`meeting_id`) REFERENCES `appointment_master` (`meeting_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of reminder_tbl
-- ----------------------------
INSERT INTO `reminder_tbl` VALUES ('REM_00789c4553c1', 'MEET_b866307060aa', 'keerthykp', '2016-06-02 02:50:00', 'reminder desc', 'keerthykp', '2016-05-30 10:37:58', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_06adc3a0a6f7', 'MEET_713729bcface', 'keerthykp', '2016-05-30 04:50:00', 'reminder desc', 'keerthykp', '2016-05-26 17:26:44', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_08adf0f3c825', 'MEET_5c303b862902', 'keerthykp', '2016-05-26 16:19:07', 'reminder desc', 'keerthykp', '2016-05-26 14:29:52', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_0bf147c5b9aa', 'MEET_a96e59031838', 'a', '2016-05-07 01:50:00', 'reminder desc', 'null', '2016-05-22 18:26:02', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_19135195c71f', 'MEET_c219056ee583', 'keerthykp', '2016-05-30 13:26:29', 'reminder desc', 'keerthykp', '2016-05-30 10:36:45', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_241675e0b77d', 'MEET_5c303b862902', 'vijay', '2016-05-26 16:19:07', 'reminder desc', 'vijay', '2016-05-26 14:31:13', '0', null, null, 'kozhikode');
INSERT INTO `reminder_tbl` VALUES ('REM_243ed7dc1203', 'MEET_b0fa4e4a84b4', 'keerthykp', '2016-05-27 07:50:00', 'reminder desc', 'keerthykp', '2016-05-26 17:25:46', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_2f46d74a7058', 'MEET_5ad86cf3f75c', 'keerthykp', '2016-05-27 17:25:20', 'reminder desc', 'keerthykp', '2016-05-27 10:35:41', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_36162a9005bc', 'MEET_d749cf96ae15', 'b', '2016-05-22 18:53:10', 'reminder desc', 'b', '2016-05-22 18:56:30', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_41dae0af45d1', 'MEET_b866307060aa', 'vijay', '2016-06-02 02:50:00', 'reminder desc', 'vijay', '2016-05-30 10:41:33', '0', null, null, 'kozhikode');
INSERT INTO `reminder_tbl` VALUES ('REM_47aecf1951a9', 'MEET_00373abaee7d', 'b', '2016-05-31 05:50:00', 'reminder desc', 'b', '2016-05-30 10:44:02', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_49b02ece86fa', 'MEET_1e7bf8abee6d', 'b', '2016-06-01 02:50:00', 'reminder desc', 'b', '2016-05-31 16:46:35', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_55e70f11dc78', 'MEET_825e834e802e', 'keerthykp', '2016-05-31 00:29:42', 'reminder desc', 'keerthykp', '2016-05-31 16:39:49', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_6785ba5028de', 'MEET_23f42a8290bd', 'a', '2016-05-12 02:50:00', 'reminder desc', 'null', '2016-05-22 18:26:26', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_686efe7680c1', 'MEET_b50ddaaf4c62', 'keerthykp', '2016-06-21 23:50:00', 'reminder desc', 'keerthykp', '2016-05-31 16:40:45', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_6d566e57aaeb', 'MEET_14a8fb906408', 'keerthykp', '2016-05-30 03:50:00', 'reminder desc', 'keerthykp', '2016-05-27 10:39:16', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_6e57d479b7d3', 'MEET_c120d9e1328e', 'keerthykp', '2016-05-27 12:26:38', 'reminder desc', 'keerthykp', '2016-05-27 10:37:15', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_7f581d9531bb', 'MEET_00373abaee7d', 'keerthykp', '2016-05-31 05:50:00', 'reminder desc', 'keerthykp', '2016-05-27 11:41:33', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_9171ee4b8fc5', 'MEET_a857f335bde1', 'b', '2016-06-21 03:50:00', 'reminder desc', 'b', '2016-06-20 17:58:58', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_98fc9b719c90', 'MEET_6426132a06d3', 'keerthykp', '2016-05-25 23:50:00', 'reminder desc', 'keerthykp', '2016-05-26 14:39:15', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_aeea5c4b3ae0', 'MEET_00373abaee7d', 'vijay', '2016-05-31 05:50:00', 'reminder desc', 'vijay', '2016-05-27 11:38:50', '0', null, null, 'kozhikode');
INSERT INTO `reminder_tbl` VALUES ('REM_b0f56a67c31a', 'MEET_6d70a4aad4cd', 'a', '2016-05-10 07:34:28', 'reminder desc', 'null', '2016-05-22 18:26:12', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_ca72a6194cae', 'MEET_c219056ee583', 'b', '2016-05-30 13:26:29', 'reminder desc', 'b', '2016-05-30 10:43:34', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_d0d62b6abfd9', 'MEET_6bde4b42ea60', 'b', '2016-06-21 07:50:00', 'reminder desc', 'b', '2016-06-20 17:59:45', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_e69903151632', 'MEET_a683bcd7f0b7', 'a', '2016-05-12 07:50:00', 'reminder desc', 'null', '2016-05-22 18:26:36', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_ef351000227e', 'MEET_823857ccb0a0', 'a', '2016-05-12 02:50:00', 'reminder desc', 'a', '2016-05-22 18:29:50', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_f066f510ac12', 'MEET_3b2a2d8ae889', 'a', '2016-05-11 23:50:00', 'reminder desc', 'null', '2016-05-22 18:26:18', '0', null, null, 'Kollam');
INSERT INTO `reminder_tbl` VALUES ('REM_f3dd57b59b68', 'MEET_288bea8bcf16', 'vijay', '2016-06-02 05:50:00', 'reminder desc', 'vijay', '2016-06-01 09:38:29', '0', null, null, 'kozhikode');
INSERT INTO `reminder_tbl` VALUES ('REM_f50e679785f8', 'MEET_cb96f4b5cf43', 'vijay', '2016-05-26 19:25:59', 'reminder desc', 'vijay', '2016-05-26 14:36:29', '0', null, null, 'kozhikode');
INSERT INTO `reminder_tbl` VALUES ('REM_ff13a9474f4e', 'MEET_f22b8a9e1574', 'keerthykp', '2016-06-01 13:37:40', 'reminder desc', 'keerthykp', '2016-06-01 09:47:56', '0', null, null, 'Kollam');

-- ----------------------------
-- Table structure for resource_tbl
-- ----------------------------
DROP TABLE IF EXISTS `resource_tbl`;
CREATE TABLE `resource_tbl` (
  `resource_id` varchar(255) NOT NULL,
  `privilage_id` varchar(255) DEFAULT NULL,
  `resource_name` varchar(150) DEFAULT NULL,
  `dummy` varchar(255) DEFAULT NULL,
  `dummy2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`resource_id`),
  KEY `privilage` (`privilage_id`) USING BTREE,
  CONSTRAINT `resource_tbl_ibfk_1` FOREIGN KEY (`privilage_id`) REFERENCES `privilage_tbl` (`privilage_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of resource_tbl
-- ----------------------------
INSERT INTO `resource_tbl` VALUES ('1', '1', 'dsd', null, null);

-- ----------------------------
-- Table structure for role_tbl
-- ----------------------------
DROP TABLE IF EXISTS `role_tbl`;
CREATE TABLE `role_tbl` (
  `role_id` varchar(255) NOT NULL,
  `role` varchar(100) DEFAULT NULL,
  `privilage_id` varchar(255) NOT NULL,
  `dummy` varchar(255) DEFAULT NULL,
  `dummy2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  KEY `privilage_id` (`privilage_id`) USING BTREE,
  CONSTRAINT `role_tbl_ibfk_1` FOREIGN KEY (`privilage_id`) REFERENCES `privilage_tbl` (`privilage_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of role_tbl
-- ----------------------------
INSERT INTO `role_tbl` VALUES ('1', 'ROLE_ADMIN', '1', null, null);
INSERT INTO `role_tbl` VALUES ('2', 'ROLE_COUNSELOR', '2', null, null);
INSERT INTO `role_tbl` VALUES ('3', 'ROLE_OFFICE', '2', null, null);

-- ----------------------------
-- Table structure for source_tbl
-- ----------------------------
DROP TABLE IF EXISTS `source_tbl`;
CREATE TABLE `source_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `source` varchar(30) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of source_tbl
-- ----------------------------
INSERT INTO `source_tbl` VALUES ('1', 'TV', 'From TV');
INSERT INTO `source_tbl` VALUES ('2', 'Phone', 'From Phone');
INSERT INTO `source_tbl` VALUES ('3', 'Internet', 'From Internet');
INSERT INTO `source_tbl` VALUES ('6', 'ss', 'ss');
INSERT INTO `source_tbl` VALUES ('7', 'gg', 'ggg');
INSERT INTO `source_tbl` VALUES ('8', 'dd', 'ddd');
INSERT INTO `source_tbl` VALUES ('9', 'ttttttttt', 'tt');
INSERT INTO `source_tbl` VALUES ('10', 'hh', 'hh');
INSERT INTO `source_tbl` VALUES ('11', 'jins', 'jins');
INSERT INTO `source_tbl` VALUES ('12', 'ee', 'ee');
INSERT INTO `source_tbl` VALUES ('13', 'ggg', 'ggggg');
INSERT INTO `source_tbl` VALUES ('14', 'ffff', 'fff');
INSERT INTO `source_tbl` VALUES ('15', 'hhh', 'hh');
INSERT INTO `source_tbl` VALUES ('16', 'hhh', 'h');

-- ----------------------------
-- Table structure for std_code_tbl
-- ----------------------------
DROP TABLE IF EXISTS `std_code_tbl`;
CREATE TABLE `std_code_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `place` varchar(50) DEFAULT NULL,
  `std_code` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of std_code_tbl
-- ----------------------------
INSERT INTO `std_code_tbl` VALUES ('1', 'Adimaly', '04864');
INSERT INTO `std_code_tbl` VALUES ('2', 'Adoor', '04734');
INSERT INTO `std_code_tbl` VALUES ('3', 'Agathy', '04894');
INSERT INTO `std_code_tbl` VALUES ('4', 'Alathur', '04922');
INSERT INTO `std_code_tbl` VALUES ('5', 'Alleppy', '0477');
INSERT INTO `std_code_tbl` VALUES ('6', 'Amini', '04891');
INSERT INTO `std_code_tbl` VALUES ('7', 'Androth', '04893');
INSERT INTO `std_code_tbl` VALUES ('8', 'Attingal', '0470');
INSERT INTO `std_code_tbl` VALUES ('9', 'Badagra', '0496');
INSERT INTO `std_code_tbl` VALUES ('10', 'Bitra', '04890');
INSERT INTO `std_code_tbl` VALUES ('11', 'Calicut', '0495');
INSERT INTO `std_code_tbl` VALUES ('12', 'Cannanote', '0497');
INSERT INTO `std_code_tbl` VALUES ('13', 'Chetlet', '04899');
INSERT INTO `std_code_tbl` VALUES ('14', 'Ernakulam', '0484');
INSERT INTO `std_code_tbl` VALUES ('15', 'Irinjalakuda', '0480');
INSERT INTO `std_code_tbl` VALUES ('16', 'Kadamath', '04897');
INSERT INTO `std_code_tbl` VALUES ('17', 'Kalpeni', '04895');
INSERT INTO `std_code_tbl` VALUES ('18', 'Kalpetta', '04936');
INSERT INTO `std_code_tbl` VALUES ('19', 'Kanhangad', '04997');
INSERT INTO `std_code_tbl` VALUES ('20', 'Kanjirapally', '04828');
INSERT INTO `std_code_tbl` VALUES ('21', 'Karungapally', '0476');
INSERT INTO `std_code_tbl` VALUES ('22', 'Kasargode', '04994');
INSERT INTO `std_code_tbl` VALUES ('23', 'Kavarathy', '04896');
INSERT INTO `std_code_tbl` VALUES ('24', 'Kiltan', '04898');
INSERT INTO `std_code_tbl` VALUES ('25', 'Koduvayur', '04923');
INSERT INTO `std_code_tbl` VALUES ('26', 'Kollam', '0474');
INSERT INTO `std_code_tbl` VALUES ('27', 'Kottayam', '0481');
INSERT INTO `std_code_tbl` VALUES ('28', 'Kunnamkulam', '04885');
INSERT INTO `std_code_tbl` VALUES ('29', 'Mananthodi', '04935');
INSERT INTO `std_code_tbl` VALUES ('30', 'Manjeri', '0483');
INSERT INTO `std_code_tbl` VALUES ('31', 'Mannarghat', '04924');
INSERT INTO `std_code_tbl` VALUES ('32', 'Mavelikkara', '0479');
INSERT INTO `std_code_tbl` VALUES ('33', 'Minicoy', '04892');
INSERT INTO `std_code_tbl` VALUES ('34', 'Munnar', '04865');
INSERT INTO `std_code_tbl` VALUES ('35', 'Muattupuzha', '0485');
INSERT INTO `std_code_tbl` VALUES ('36', 'Nedumandad', '04728');
INSERT INTO `std_code_tbl` VALUES ('37', 'Nedumgandam', '04868');
INSERT INTO `std_code_tbl` VALUES ('38', 'Nilambur', '04931');
INSERT INTO `std_code_tbl` VALUES ('39', 'Palai', '04822');
INSERT INTO `std_code_tbl` VALUES ('40', 'Pallakad/Palghat', '0491');
INSERT INTO `std_code_tbl` VALUES ('41', 'Pathanamthitta', '0468');
INSERT INTO `std_code_tbl` VALUES ('42', 'Payyanur', '04985');
INSERT INTO `std_code_tbl` VALUES ('43', 'Peermedu', '04869');
INSERT INTO `std_code_tbl` VALUES ('44', 'Perinthalmanna', '04933');
INSERT INTO `std_code_tbl` VALUES ('45', 'Punalur', '0475');
INSERT INTO `std_code_tbl` VALUES ('46', 'Ranni', '04735');
INSERT INTO `std_code_tbl` VALUES ('47', 'Shertallai', '0478');
INSERT INTO `std_code_tbl` VALUES ('48', 'Shoranur', '04926');
INSERT INTO `std_code_tbl` VALUES ('49', 'Taliparamba', '04982');
INSERT INTO `std_code_tbl` VALUES ('50', 'Tellicherry', '0490');
INSERT INTO `std_code_tbl` VALUES ('51', 'Thiruvananthapuram', '0471');
INSERT INTO `std_code_tbl` VALUES ('52', 'Thodupuzha', '04862');
INSERT INTO `std_code_tbl` VALUES ('53', 'Tirur', '0494');
INSERT INTO `std_code_tbl` VALUES ('54', 'Tiruvalla', '0469');
INSERT INTO `std_code_tbl` VALUES ('55', 'Trichur', '0487');
INSERT INTO `std_code_tbl` VALUES ('56', 'Uppala', '04998');
INSERT INTO `std_code_tbl` VALUES ('57', 'Vadakkanchery', '04884');
INSERT INTO `std_code_tbl` VALUES ('58', 'Vaikom', '04829');

-- ----------------------------
-- Table structure for study_country
-- ----------------------------
DROP TABLE IF EXISTS `study_country`;
CREATE TABLE `study_country` (
  `id` varchar(255) NOT NULL,
  `country` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of study_country
-- ----------------------------
INSERT INTO `study_country` VALUES ('1', 'Australia');
INSERT INTO `study_country` VALUES ('2', 'USA');
INSERT INTO `study_country` VALUES ('431b8a0db5e2', 'Bangladesh');
INSERT INTO `study_country` VALUES ('5', 'New Zeland');
INSERT INTO `study_country` VALUES ('7', 'UK');
INSERT INTO `study_country` VALUES ('8', 'India');
INSERT INTO `study_country` VALUES ('9', 'Germany');
INSERT INTO `study_country` VALUES ('e7f71bb5bc48', 'Africa');

-- ----------------------------
-- Table structure for study_work_migration_training_mstr_tbl
-- ----------------------------
DROP TABLE IF EXISTS `study_work_migration_training_mstr_tbl`;
CREATE TABLE `study_work_migration_training_mstr_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operation_area` varchar(15) DEFAULT NULL,
  `functional_area` varchar(40) DEFAULT NULL,
  `course_level` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of study_work_migration_training_mstr_tbl
-- ----------------------------
INSERT INTO `study_work_migration_training_mstr_tbl` VALUES ('1', 'Study', 'Engineering', 'IT');
INSERT INTO `study_work_migration_training_mstr_tbl` VALUES ('2', 'Study', 'Engineering', 'EC');
INSERT INTO `study_work_migration_training_mstr_tbl` VALUES ('7', 'Work', 'engineering', 'ec');
INSERT INTO `study_work_migration_training_mstr_tbl` VALUES ('8', 'Migration', 'Engineering', 'CE');
INSERT INTO `study_work_migration_training_mstr_tbl` VALUES ('9', 'Work', 'MBA', 'HR');
INSERT INTO `study_work_migration_training_mstr_tbl` VALUES ('10', 'Migration', 'Engineering', 'B.Tech');
INSERT INTO `study_work_migration_training_mstr_tbl` VALUES ('11', 'Training', 'Morning batch', '9.00 am - 11.00 am');
INSERT INTO `study_work_migration_training_mstr_tbl` VALUES ('12', 'Work', 'Enginering', 'be');
INSERT INTO `study_work_migration_training_mstr_tbl` VALUES ('13', 'Study', 'DFDFD', 'ddddd');
INSERT INTO `study_work_migration_training_mstr_tbl` VALUES ('14', 'Migration', 'Eng', 'yyy');
INSERT INTO `study_work_migration_training_mstr_tbl` VALUES ('15', 'Migration', 'Engineering', 'ff');
INSERT INTO `study_work_migration_training_mstr_tbl` VALUES ('16', 'Migration', 'Management', 'MBA');
INSERT INTO `study_work_migration_training_mstr_tbl` VALUES ('17', 'Study', 'dd', 'ddd');
INSERT INTO `study_work_migration_training_mstr_tbl` VALUES ('18', 'Work', 'Engineering', 'MCA');
INSERT INTO `study_work_migration_training_mstr_tbl` VALUES ('19', 'Study', 'Nursing', 'Nursing');
INSERT INTO `study_work_migration_training_mstr_tbl` VALUES ('20', 'Training', 'Aftrnoon batch', '1.30pm-3.30pm');
INSERT INTO `study_work_migration_training_mstr_tbl` VALUES ('21', 'Training', 'Evening batch', '10.am t0 12.00pm');
INSERT INTO `study_work_migration_training_mstr_tbl` VALUES ('22', 'Training', 'Any', '9.00am - 4.00pm');
INSERT INTO `study_work_migration_training_mstr_tbl` VALUES ('23', 'Study', 'Ecnomics,Social Sciences,Law', 'Short Course');
INSERT INTO `study_work_migration_training_mstr_tbl` VALUES ('24', 'Study', 'Natural & Computer Science', 'Language Course');
INSERT INTO `study_work_migration_training_mstr_tbl` VALUES ('25', 'Study', 'Art & Music', 'Master');

-- ----------------------------
-- Table structure for task_details
-- ----------------------------
DROP TABLE IF EXISTS `task_details`;
CREATE TABLE `task_details` (
  `line_no` varchar(30) NOT NULL,
  `task_id` varchar(30) DEFAULT NULL,
  `remarks` text,
  `assessment_substatus` int(11) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `latest_flag` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`line_no`),
  KEY `fk_task` (`task_id`) USING BTREE,
  CONSTRAINT `task_details_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `task_master` (`task_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of task_details
-- ----------------------------
INSERT INTO `task_details` VALUES (' tk_0014d4acf091', 'task_0a23372b453c', 'contact future', '5', 'b', '2016-05-12 15:19:45', 'b', '2016-05-12 15:22:27', 'y');
INSERT INTO `task_details` VALUES (' tk_031ae7bd49c5', 'task_13bc9eb24b8b', 'jkjk', null, 'b', '2016-05-12 14:20:48', 'b', '2016-05-17 17:25:12', 'y');
INSERT INTO `task_details` VALUES (' tk_0386741f6472', 'task_b064f6918461', 'okey', '6', 'a', '2016-05-12 15:58:54', 'a', '2016-05-12 21:03:58', 'y');
INSERT INTO `task_details` VALUES (' tk_048806823d95', 'task_05add06e4a01', 'uyi', null, 'b', '2016-05-17 18:58:14', 'b', '2016-06-20 17:49:41', 'n');
INSERT INTO `task_details` VALUES (' tk_04f4073f06fb', 'task_05add06e4a01', 'dfgdg', null, 'b', '2016-05-17 18:58:14', 'b', '2016-05-17 18:58:19', 'n');
INSERT INTO `task_details` VALUES (' tk_05ad3c45db6b', 'task_b09fd31456d6', 'call not picking', '2', 'b', '2016-05-12 12:26:03', 'b', '2016-05-12 12:27:00', 'y');
INSERT INTO `task_details` VALUES (' tk_0626d6b69f56', 'task_0a39de72f7e2', 'oh realtuyyyyyyyyyyyyyyyyyyyyyyyyy yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy looo                     dsfjgfjd jdfgljfdgjlfjgl jlfj gjflkgjlfkg', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 18:16:17', 'n');
INSERT INTO `task_details` VALUES (' tk_067ab408a7ec', 'task_9fecc47fb46c', 'jhk', '2', 'b', '2016-05-12 11:37:16', 'b', '2016-05-13 19:11:22', 'n');
INSERT INTO `task_details` VALUES (' tk_0728a6c48295', 'task_901f87b1d372', 'cancelled', '10', 'b', '2016-05-12 14:28:13', 'b', '2016-05-12 14:29:25', 'y');
INSERT INTO `task_details` VALUES (' tk_07b082ecc09d', 'task_374d08f73925', 'okey i will do', null, 'a', '2016-05-12 20:30:26', 'a', '2016-05-12 20:30:53', 'y');
INSERT INTO `task_details` VALUES (' tk_08660f392aa7', 'task_17fb252f951b', 'not completed', '4', 'b', '2016-05-12 11:40:17', 'b', '2016-05-12 11:41:53', 'n');
INSERT INTO `task_details` VALUES (' tk_08e39b98b2a0', 'task_5c6eb021c731', 'fake call', '7', 'b', '2016-05-12 15:07:23', 'b', '2016-05-12 15:08:08', 'n');
INSERT INTO `task_details` VALUES (' tk_09534061b56f', 'task_8f818cc331fa', 'gh', null, 'b', '2016-05-17 18:57:08', 'b', '2016-06-20 17:17:07', 'n');
INSERT INTO `task_details` VALUES (' tk_099655584497', 'task_1b6190278dc1', 'confirm soon', '9', 'b', '2016-05-12 15:31:21', 'b', '2016-05-12 15:31:46', 'n');
INSERT INTO `task_details` VALUES (' tk_09f49d33b5e7', 'task_6122b785893b', 'not interested', '6', 'b', '2016-05-12 15:09:19', 'b', '2016-05-12 15:10:50', 'n');
INSERT INTO `task_details` VALUES (' tk_0aee3c44f645', 'task_740aba4d1b77', 'reg per', '12', 'b', '2016-05-12 16:00:31', 'b', '2016-05-12 16:01:05', 'y');
INSERT INTO `task_details` VALUES (' tk_0af2c558eb5c', 'task_25314e81f388', 'not interested', '6', 'b', '2016-05-12 14:23:16', 'b', '2016-05-12 14:25:03', 'n');
INSERT INTO `task_details` VALUES (' tk_0cbbd858d76a', 'task_42422935b37d', 'call not picking', '2', 'b', '2016-05-12 11:22:39', 'b', '2016-05-12 11:26:04', 'n');
INSERT INTO `task_details` VALUES (' tk_0e9bfe078f8a', 'task_f3c8c12d96e8', 'register', '1', 'b', '2016-05-12 11:15:56', 'b', '2016-05-12 11:16:19', 'n');
INSERT INTO `task_details` VALUES (' tk_0f973ee80ec1', 'task_f3c8c12d96e8', 'Not picking the call', '10', 'b', '2016-05-12 11:15:56', 'b', '2016-05-12 20:40:56', 'y');
INSERT INTO `task_details` VALUES (' tk_10cb5ba7248c', 'task_f6375bf0c3cb', 'call not picking', '2', 'b', '2016-05-12 14:18:07', 'b', '2016-05-12 14:19:44', 'n');
INSERT INTO `task_details` VALUES (' tk_111e5080c4d7', 'task_7086d84c5b78', 'cancelled', '10', 'b', '2016-05-12 11:48:42', 'b', '2016-05-12 11:49:55', 'y');
INSERT INTO `task_details` VALUES (' tk_11572a889838', 'task_1b6190278dc1', 'number not exist', '8', 'b', '2016-05-12 15:31:21', 'b', '2016-05-12 15:32:52', 'n');
INSERT INTO `task_details` VALUES (' tk_11681a4303ce', 'task_16d7108c119f', 'confirm soon', '9', 'b', '2016-05-12 15:28:02', 'b', '2016-05-12 15:28:23', 'n');
INSERT INTO `task_details` VALUES (' tk_174494b3b38c', 'task_82e47984babb', 'not interested', '6', 'b', '2016-05-12 14:56:18', 'b', '2016-05-12 14:57:30', 'n');
INSERT INTO `task_details` VALUES (' tk_17b6fd48cfcd', 'task_2c3735ede6e8', 'fake call', '7', 'b', '2016-05-12 17:07:03', 'b', '2016-05-12 17:07:55', 'y');
INSERT INTO `task_details` VALUES (' tk_1a414ecdc53f', 'task_13bc9eb24b8b', 'confirm soon', '9', 'b', '2016-05-12 14:20:48', 'b', '2016-05-12 14:21:38', 'n');
INSERT INTO `task_details` VALUES (' tk_1ac51eb3d4d4', 'task_5142fee1e138', 'contact future', '5', 'b', '2016-05-12 17:01:30', 'b', '2016-05-12 17:03:07', 'y');
INSERT INTO `task_details` VALUES (' tk_1d86d9635440', 'task_8e264f1b5db5', 'call not picking', '2', 'b', '2016-05-12 12:11:19', 'b', '2016-05-12 12:11:54', 'n');
INSERT INTO `task_details` VALUES (' tk_1dda2728b06e', 'task_8f08c5a755cd', 'contact future', '5', 'b', '2016-05-12 14:30:08', 'b', '2016-05-12 14:31:55', 'y');
INSERT INTO `task_details` VALUES (' tk_1e1712f31d33', 'task_1ad73e293866', 'contact future', '5', 'sumeshzoft', '2016-05-17 09:58:03', 'sumeshzoft', '2016-05-17 12:55:40', 'y');
INSERT INTO `task_details` VALUES (' tk_1f05252fd452', 'task_50ac93aaa1d4', 'cancelled', '10', 'b', '2016-05-12 11:21:14', 'b', '2016-05-12 11:21:34', 'n');
INSERT INTO `task_details` VALUES (' tk_1fa65eeab787', 'task_8c9ff93fc508', 'confirm soon', '9', 'b', '2016-05-12 16:04:12', 'b', '2016-05-12 16:04:28', 'n');
INSERT INTO `task_details` VALUES (' tk_1fb8e330c033', 'task_05add06e4a01', 'u', null, 'b', '2016-05-17 18:58:14', 'b', '2016-06-20 17:19:14', 'n');
INSERT INTO `task_details` VALUES (' tk_213aa5e832c2', 'task_137e7869a44c', 'cal attended & later', '3', 'b', '2016-05-12 14:59:55', 'b', '2016-05-12 15:00:25', 'n');
INSERT INTO `task_details` VALUES (' tk_21a62afc88da', 'task_9e372c457cd0', 'not completed', '4', 'b', '2016-05-12 11:17:20', 'b', '2016-05-12 11:18:04', 'n');
INSERT INTO `task_details` VALUES (' tk_227dfb007f53', 'task_05add06e4a01', '1', null, 'b', '2016-05-17 18:58:14', 'b', '2016-06-20 17:43:11', 'n');
INSERT INTO `task_details` VALUES (' tk_22f77e43374e', 'task_0a39de72f7e2', 'ohhhhhhhhhhhhhhh', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 18:08:30', 'n');
INSERT INTO `task_details` VALUES (' tk_233947a63412', 'task_0a23372b453c', 'confirm soon', '9', 'b', '2016-05-12 15:19:45', 'b', '2016-05-12 15:20:20', 'n');
INSERT INTO `task_details` VALUES (' tk_24b88f988e86', 'task_9e372c457cd0', 'yui', '9', 'sanoop', '2016-05-12 11:17:20', 'sanoop', '2016-05-17 11:39:00', 'y');
INSERT INTO `task_details` VALUES (' tk_24c2e106f766', 'task_b064f6918461', 'not exist', '8', 'b', '2016-05-12 15:58:54', 'b', '2016-05-12 15:59:15', 'n');
INSERT INTO `task_details` VALUES (' tk_2513ba2abb4c', 'task_dbe3c0f712e2', 'not interested', '6', 'b', '2016-05-12 17:12:22', 'b', '2016-05-12 17:13:04', 'n');
INSERT INTO `task_details` VALUES (' tk_2523032f70fa', 'task_25ceda8951cf', 'number not exist', '8', 'b', '2016-05-12 17:22:56', 'b', '2016-05-12 17:24:06', 'n');
INSERT INTO `task_details` VALUES (' tk_26b15cd39010', 'task_3e0933006565', 'confirm soon', '9', 'b', '2016-05-12 16:57:21', 'b', '2016-05-12 16:58:31', 'y');
INSERT INTO `task_details` VALUES (' tk_274449917be1', 'task_bb232be6db08', 'not completed', '4', 'b', '2016-05-12 12:13:06', 'b', '2016-05-12 12:14:08', 'y');
INSERT INTO `task_details` VALUES (' tk_2b16082b5ac9', 'task_35f0c8691a16', 'contact future', '5', 'b', '2016-05-12 17:21:41', 'b', '2016-05-12 17:22:19', 'n');
INSERT INTO `task_details` VALUES (' tk_2f58d84da40c', 'task_b11fd3cae151', 'call not complete', '4', 'b', '2016-05-12 16:06:47', 'b', '2016-05-12 16:07:44', 'y');
INSERT INTO `task_details` VALUES (' tk_2fddf0a1a6d1', 'task_4070a226680e', 'cancelled', '10', 'b', '2016-05-12 16:05:28', 'b', '2016-05-12 16:05:48', 'n');
INSERT INTO `task_details` VALUES (' tk_30cdf8f74a7a', 'task_0a39de72f7e2', 'jknljk', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 18:08:17', 'n');
INSERT INTO `task_details` VALUES (' tk_30f4a2923f55', 'task_8f818cc331fa', 'fgh', null, 'b', '2016-05-17 18:57:08', 'b', '2016-06-20 17:17:01', 'n');
INSERT INTO `task_details` VALUES (' tk_31171d7f5663', 'task_4070a226680e', 'reg now', '1', 'b', '2016-05-12 16:05:28', 'b', '2016-05-12 16:05:41', 'n');
INSERT INTO `task_details` VALUES (' tk_31e8239e2f85', 'task_8a7310abe1be', 'register', '1', 'b', '2016-05-12 17:13:58', 'b', '2016-05-12 17:14:31', 'n');
INSERT INTO `task_details` VALUES (' tk_32dfa8bb30ba', 'task_238632711ba2', 'not completed', '4', 'b', '2016-05-12 15:03:50', 'b', '2016-05-12 15:04:54', 'n');
INSERT INTO `task_details` VALUES (' tk_331838a675b7', 'task_755e1fd60fa3', 'fake call', '7', 'b', '2016-05-12 16:51:01', 'b', '2016-05-12 16:53:22', 'n');
INSERT INTO `task_details` VALUES (' tk_33a95f58c33a', 'task_05add06e4a01', 'uyhi', null, 'b', '2016-05-17 18:58:14', 'b', '2016-06-20 17:35:14', 'n');
INSERT INTO `task_details` VALUES (' tk_34358cf2fb29', 'task_05add06e4a01', 'hjhj', null, 'b', '2016-05-17 18:58:14', 'b', '2016-06-20 17:21:13', 'n');
INSERT INTO `task_details` VALUES (' tk_34397912b35d', 'task_7e5394ea327e', 'cal attended & later', '3', 'b', '2016-05-12 11:42:53', 'b', '2016-05-12 11:44:51', 'n');
INSERT INTO `task_details` VALUES (' tk_34dc09259f18', 'task_134c470966dc', 'cal attended & later', '3', 'b', '2016-05-12 15:12:23', 'b', '2016-05-12 15:13:46', 'y');
INSERT INTO `task_details` VALUES (' tk_36505509eb53', 'task_f8da0b09d2b2', 'confim soon', '9', 'b', '2016-05-12 15:26:37', 'b', '2016-05-12 15:27:04', 'y');
INSERT INTO `task_details` VALUES (' tk_36fa0c47a0ee', 'task_acc3dfde9965', 'will walk in', '11', 'b', '2016-05-12 16:55:39', 'b', '2016-05-12 16:56:31', 'n');
INSERT INTO `task_details` VALUES (' tk_3745a95c0312', 'task_590f237bca9e', 'number not exist', '8', 'b', '2016-05-12 14:26:10', 'b', '2016-05-12 14:26:54', 'n');
INSERT INTO `task_details` VALUES (' tk_3829eadc7393', 'task_3a4dfdf2551c', 'confirm soon', '9', 'b', '2016-05-12 17:18:58', 'b', '2016-05-12 17:19:52', 'n');
INSERT INTO `task_details` VALUES (' tk_3872df1b874f', 'task_8f818cc331fa', 'ghhg', null, 'b', '2016-05-17 18:57:08', 'b', '2016-06-20 17:17:23', 'n');
INSERT INTO `task_details` VALUES (' tk_3a3a4187abc4', 'task_dbe3c0f712e2', 'okeyy', null, 'b', '2016-05-12 17:12:22', 'b', '2016-06-20 16:26:53', 'y');
INSERT INTO `task_details` VALUES (' tk_3ab12f4bb913', 'task_0a39de72f7e2', 'okkdfgfd dfgjdflgjf gfdgjfdjgjfd fdgdfjgljfdgj fjglfdjgjfdgjfg jfgjfdgjfdjg fdjgfdjgl', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 18:15:56', 'n');
INSERT INTO `task_details` VALUES (' tk_3b439e7d98a7', 'task_13bc9eb24b8b', 'not completed', '4', 'b', '2016-05-12 14:20:48', 'b', '2016-05-12 14:22:02', 'n');
INSERT INTO `task_details` VALUES (' tk_3d59903a1ea4', 'task_07e81c0e4920', 'contact future', '5', 'b', '2016-05-12 17:05:23', 'b', '2016-05-12 17:06:12', 'n');
INSERT INTO `task_details` VALUES (' tk_3dbf66695a1e', 'task_1f840d77e29e', 'cancelled', '10', 'b', '2016-05-12 12:19:19', 'b', '2016-05-12 12:20:30', 'y');
INSERT INTO `task_details` VALUES (' tk_3e281ae4ff2d', 'task_05add06e4a01', 'ghyj', null, 'b', '2016-05-17 18:58:14', 'b', '2016-06-20 17:25:03', 'n');
INSERT INTO `task_details` VALUES (' tk_3e2b6034a8e6', 'task_7e5394ea327e', 'call not picking', '2', 'b', '2016-05-12 11:42:53', 'b', '2016-05-12 11:45:04', 'y');
INSERT INTO `task_details` VALUES (' tk_3f55f78ee84d', 'task_acc1b09fe929', 'fake call', '7', 'b', '2016-05-12 17:09:57', 'b', '2016-05-12 17:11:19', 'n');
INSERT INTO `task_details` VALUES (' tk_40043068895e', 'task_13bc9eb24b8b', 'kkk', null, 'b', '2016-05-12 14:20:48', 'b', '2016-05-17 17:25:08', 'n');
INSERT INTO `task_details` VALUES (' tk_40343e10064b', 'task_40e043fa71e5', 'not interested', '6', 'b', '2016-05-12 17:03:43', 'b', '2016-05-12 17:04:10', 'n');
INSERT INTO `task_details` VALUES (' tk_40bc430d7d1f', 'task_8f40379b82d5', 'will walk in', '11', 'b', '2016-05-12 11:27:59', 'b', '2016-05-12 11:28:21', 'n');
INSERT INTO `task_details` VALUES (' tk_416140a650bd', 'task_8c4ca9d838b1', 'confirm soon', '9', 'b', '2016-05-12 11:19:07', 'b', '2016-05-12 11:19:55', 'n');
INSERT INTO `task_details` VALUES (' tk_419f609285a2', 'task_8a7310abe1be', 'not completed', '4', 'b', '2016-05-12 17:13:58', 'b', '2016-05-12 17:14:19', 'n');
INSERT INTO `task_details` VALUES (' tk_42a486ec9413', 'task_5fd4acc31f5a', 'cancelled', '10', 'b', '2016-05-12 12:27:35', 'b', '2016-05-12 12:28:07', 'n');
INSERT INTO `task_details` VALUES (' tk_42f277aff0bc', 'task_1d574be3a683', 'cancelled', '10', 'b', '2016-05-12 15:52:54', 'b', '2016-05-12 15:58:24', 'y');
INSERT INTO `task_details` VALUES (' tk_438519d2ddeb', 'task_efbec4302dca', 'contact future', '5', 'b', '2016-05-12 12:28:59', 'b', '2016-05-12 12:30:08', 'n');
INSERT INTO `task_details` VALUES (' tk_44ac0be48803', 'task_5fd4acc31f5a', 'cal attended & later', '3', 'b', '2016-05-12 12:27:35', 'b', '2016-05-12 12:27:55', 'n');
INSERT INTO `task_details` VALUES (' tk_461734db62f8', 'task_8a7310abe1be', 'number not exist', '8', 'b', '2016-05-12 17:13:58', 'b', '2016-05-12 17:14:50', 'y');
INSERT INTO `task_details` VALUES (' tk_4791d5c12afa', 'task_8f08c5a755cd', 'confirm soon', '9', 'b', '2016-05-12 14:30:08', 'b', '2016-05-12 14:31:18', 'n');
INSERT INTO `task_details` VALUES (' tk_48d1e8ee0190', 'task_8c4ca9d838b1', 'fake call', '7', 'b', '2016-05-12 11:19:07', 'b', '2016-05-12 11:19:32', 'n');
INSERT INTO `task_details` VALUES (' tk_48e12175878d', 'task_efbec4302dca', 'not interested', '6', 'b', '2016-05-12 12:28:59', 'b', '2016-05-12 12:30:21', 'y');
INSERT INTO `task_details` VALUES (' tk_4a17649bdb3f', 'task_590f237bca9e', 'will walk in', '11', 'b', '2016-05-12 14:26:10', 'b', '2016-05-12 14:27:18', 'n');
INSERT INTO `task_details` VALUES (' tk_4a4cbe4495ac', 'task_370af67c40f7', 'contact future', '5', 'b', '2016-05-12 15:16:43', 'b', '2016-05-12 15:17:13', 'n');
INSERT INTO `task_details` VALUES (' tk_4b8e38530c5c', 'task_7e5394ea327e', 'cancelled', '10', 'b', '2016-05-12 11:42:53', 'b', '2016-05-12 11:44:01', 'n');
INSERT INTO `task_details` VALUES (' tk_4d111c3f6cf7', 'task_17fb252f951b', 'confirm soon', '9', 'b', '2016-05-12 11:40:17', 'b', '2016-05-12 11:42:12', 'y');
INSERT INTO `task_details` VALUES (' tk_4d26f7363da3', 'task_5142fee1e138', 'confirm soon', '9', 'b', '2016-05-12 17:01:30', 'b', '2016-05-12 17:02:48', 'n');
INSERT INTO `task_details` VALUES (' tk_4ddc58f7d490', 'task_05add06e4a01', 'kkk', null, 'b', '2016-05-17 18:58:14', 'b', '2016-06-20 17:57:32', 'y');
INSERT INTO `task_details` VALUES (' tk_4df93558cf33', 'task_af9eece61eeb', 'contact future', '5', 'b', '2016-05-12 15:29:25', 'b', '2016-05-12 15:30:49', 'y');
INSERT INTO `task_details` VALUES (' tk_505a197829ff', 'task_8e264f1b5db5', 'cal attended & later', '3', 'b', '2016-05-12 12:11:19', 'b', '2016-05-12 12:12:07', 'y');
INSERT INTO `task_details` VALUES (' tk_51522f9e2b32', 'task_bb232be6db08', 'cancelled', '10', 'b', '2016-05-12 12:13:06', 'b', '2016-05-12 12:13:30', 'n');
INSERT INTO `task_details` VALUES (' tk_5203b92f981e', 'task_1ad73e293866', 'soon', '9', 'b', '2016-05-17 09:58:03', 'b', '2016-05-17 09:58:22', 'n');
INSERT INTO `task_details` VALUES (' tk_53a9feb3cba9', 'task_05add06e4a01', 'hjhj', null, 'b', '2016-05-17 18:58:14', 'b', '2016-06-20 17:25:10', 'n');
INSERT INTO `task_details` VALUES (' tk_5573a3900ad4', 'task_3a4dfdf2551c', 'contact future', '5', 'b', '2016-05-12 17:18:58', 'b', '2016-05-12 17:20:15', 'n');
INSERT INTO `task_details` VALUES (' tk_5574bc308cd3', 'task_f6375bf0c3cb', 'cal attended & later', '3', 'b', '2016-05-12 14:18:07', 'b', '2016-05-12 14:19:58', 'y');
INSERT INTO `task_details` VALUES (' tk_5685fb046f47', 'task_211beb1fafa1', 'cal attended & later', '3', 'b', '2016-05-12 17:08:29', 'b', '2016-05-12 17:09:23', 'y');
INSERT INTO `task_details` VALUES (' tk_5807f8cd9d3a', 'task_8e264f1b5db5', 'register', '1', 'b', '2016-05-12 12:11:19', 'b', '2016-05-12 12:11:40', 'n');
INSERT INTO `task_details` VALUES (' tk_586390dfbae7', 'task_9fecc47fb46c', 'fake call', '7', 'b', '2016-05-12 11:37:16', 'b', '2016-05-12 11:38:11', 'n');
INSERT INTO `task_details` VALUES (' tk_592c971512de', 'task_1d574be3a683', 'Assess now', '13', 'b', '2016-05-12 15:52:54', 'b', '2016-05-12 15:53:49', 'n');
INSERT INTO `task_details` VALUES (' tk_59dc4a6512fe', 'task_b09fd31456d6', 'will walk in', '11', 'b', '2016-05-12 12:26:03', 'b', '2016-05-12 12:26:27', 'n');
INSERT INTO `task_details` VALUES (' tk_5c99ddd5021a', 'task_3a4dfdf2551c', 'cal attended & later', '3', 'b', '2016-05-12 17:18:58', 'b', '2016-05-12 17:20:32', 'y');
INSERT INTO `task_details` VALUES (' tk_5d3dd446df79', 'task_370af67c40f7', 'contact future', '5', 'b', '2016-05-12 15:16:43', 'b', '2016-05-12 15:17:26', 'n');
INSERT INTO `task_details` VALUES (' tk_5e6aedda392c', 'task_25314e81f388', 'fake call', '7', 'b', '2016-05-12 14:23:16', 'b', '2016-05-12 14:25:25', 'y');
INSERT INTO `task_details` VALUES (' tk_5e6cb127ff9b', 'task_c2ecc69a3d2d', 'will walk in', '11', 'b', '2016-05-12 12:17:07', 'b', '2016-05-12 12:18:15', 'n');
INSERT INTO `task_details` VALUES (' tk_5e7855cded7d', 'task_b11fd3cae151', 'not exst', '8', 'b', '2016-05-12 16:06:47', 'b', '2016-05-12 16:07:15', 'n');
INSERT INTO `task_details` VALUES (' tk_5e7ec2fc43e5', 'task_b064f6918461', 'confirm soon', '9', 'b', '2016-05-12 15:58:54', 'b', '2016-05-12 15:59:51', 'n');
INSERT INTO `task_details` VALUES (' tk_5fba049e0ac7', 'task_af9eece61eeb', 'number not exist', '8', 'b', '2016-05-12 15:29:25', 'b', '2016-05-12 15:30:37', 'n');
INSERT INTO `task_details` VALUES (' tk_602243d1bdee', 'task_4070a226680e', 'not interested', '6', 'b', '2016-05-12 16:05:28', 'b', '2016-05-12 16:06:07', 'n');
INSERT INTO `task_details` VALUES (' tk_632a2e725c6c', 'task_8c9ff93fc508', 'call not picking', '2', 'b', '2016-05-12 16:04:12', 'b', '2016-05-12 16:04:57', 'y');
INSERT INTO `task_details` VALUES (' tk_636a9bae2af7', 'task_ea94ef9cf0a5', 'erer', null, 'b', '2016-05-17 18:58:36', 'b', '2016-05-19 20:06:20', 'n');
INSERT INTO `task_details` VALUES (' tk_6545551b59e6', 'task_740aba4d1b77', 'will walk in', '11', 'b', '2016-05-12 16:00:31', 'b', '2016-05-12 16:00:57', 'n');
INSERT INTO `task_details` VALUES (' tk_663017148bbd', 'task_acc3dfde9965', 'confirm soon', '9', 'b', '2016-05-12 16:55:39', 'b', '2016-05-12 16:56:09', 'n');
INSERT INTO `task_details` VALUES (' tk_6819b594b1f6', 'task_0a39de72f7e2', 'ohhh super', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 17:47:42', 'n');
INSERT INTO `task_details` VALUES (' tk_6837ca6c4458', 'task_17fb252f951b', 'contact future', '5', 'b', '2016-05-12 11:40:17', 'b', '2016-05-12 11:41:38', 'n');
INSERT INTO `task_details` VALUES (' tk_685f5d68c983', 'task_755e1fd60fa3', 'confirm soon', '9', 'b', '2016-05-12 16:51:01', 'b', '2016-05-12 16:51:42', 'n');
INSERT INTO `task_details` VALUES (' tk_68bfdc960dbf', 'task_35f0c8691a16', 'confirm soon', '9', 'b', '2016-05-12 17:21:41', 'b', '2016-05-12 17:22:05', 'n');
INSERT INTO `task_details` VALUES (' tk_69ffa278ba39', 'task_211beb1fafa1', 'contact future', '5', 'b', '2016-05-12 17:08:29', 'b', '2016-05-12 17:09:08', 'n');
INSERT INTO `task_details` VALUES (' tk_6a77033a9d36', 'task_b064f6918461', 'register now', '1', 'b', '2016-05-12 15:58:54', 'b', '2016-05-12 16:00:02', 'n');
INSERT INTO `task_details` VALUES (' tk_6ac520489564', 'task_1b6190278dc1', 'fake call', '7', 'b', '2016-05-12 15:31:21', 'b', '2016-05-12 15:33:03', 'y');
INSERT INTO `task_details` VALUES (' tk_6b09966f9c61', 'task_4070a226680e', 'reg per', '12', 'b', '2016-05-12 16:05:28', 'b', '2016-05-12 16:06:14', 'y');
INSERT INTO `task_details` VALUES (' tk_6b86c9f78904', 'task_a1378a4a657d', 'not interested', '6', 'b', '2016-05-17 10:31:22', 'b', '2016-05-17 12:23:44', 'n');
INSERT INTO `task_details` VALUES (' tk_6bd72530a6ae', 'task_acc3dfde9965', 'number not exist', '8', 'b', '2016-05-12 16:55:39', 'b', '2016-05-12 16:56:48', 'y');
INSERT INTO `task_details` VALUES (' tk_6d836eb37efa', 'task_c2ecc69a3d2d', 'number not exist', '8', 'b', '2016-05-12 12:17:07', 'b', '2016-05-12 12:17:55', 'n');
INSERT INTO `task_details` VALUES (' tk_6ea4d286ae25', 'task_7cebfe2fca11', 'not completed', '4', 'b', '2016-05-12 12:21:51', 'b', '2016-05-12 12:22:44', 'n');
INSERT INTO `task_details` VALUES (' tk_6eb502a307fb', 'task_cf38b378e45b', 'not completed', '4', 'b', '2016-05-12 16:59:39', 'b', '2016-05-12 17:00:09', 'n');
INSERT INTO `task_details` VALUES (' tk_7137a53f5492', 'task_740aba4d1b77', 'reg now', '1', 'b', '2016-05-12 16:00:31', 'b', '2016-05-12 16:00:49', 'n');
INSERT INTO `task_details` VALUES (' tk_73cd6bcc9c7d', 'task_ea94ef9cf0a5', 'ewr', null, 'b', '2016-05-17 18:58:36', 'b', '2016-05-19 20:06:37', 'y');
INSERT INTO `task_details` VALUES (' tk_74d3ec9838d0', 'task_fa9395224d15', 'register', '1', 'b', '2016-05-12 11:34:34', 'b', '2016-05-12 11:35:21', 'n');
INSERT INTO `task_details` VALUES (' tk_76b97d4c6356', 'task_370af67c40f7', 'register', '1', 'b', '2016-05-12 15:16:43', 'b', '2016-05-12 15:18:20', 'y');
INSERT INTO `task_details` VALUES (' tk_7799fc9497cc', 'task_6122b785893b', 'call not picking', '2', 'b', '2016-05-12 15:09:19', 'b', '2016-05-12 15:11:37', 'y');
INSERT INTO `task_details` VALUES (' tk_779e632525c4', 'task_af9eece61eeb', 'not interested', '6', 'b', '2016-05-12 15:29:25', 'b', '2016-05-12 15:29:49', 'n');
INSERT INTO `task_details` VALUES (' tk_7a2e1876a1a9', 'task_25ceda8951cf', 'register', '1', 'b', '2016-05-12 17:22:56', 'b', '2016-05-12 17:24:35', 'y');
INSERT INTO `task_details` VALUES (' tk_7c415b473189', 'task_07e81c0e4920', 'register', '1', 'b', '2016-05-12 17:05:23', 'b', '2016-05-12 17:06:41', 'y');
INSERT INTO `task_details` VALUES (' tk_7caff853a657', 'task_d5dbc0ab3ef2', 'number not exist', '8', 'b', '2016-05-12 12:23:29', 'b', '2016-05-12 12:25:06', 'y');
INSERT INTO `task_details` VALUES (' tk_7cc404f76c64', 'task_25314e81f388', 'contact future', '5', 'b', '2016-05-12 14:23:16', 'b', '2016-05-12 14:23:58', 'n');
INSERT INTO `task_details` VALUES (' tk_7d1cb3e3b554', 'task_134c470966dc', 'register', '1', 'b', '2016-05-12 15:12:23', 'b', '2016-05-12 15:13:07', 'n');
INSERT INTO `task_details` VALUES (' tk_7f8ea3d43012', 'task_2c3735ede6e8', 'cal attended & later', '3', 'b', '2016-05-12 17:07:03', 'b', '2016-05-12 17:07:21', 'n');
INSERT INTO `task_details` VALUES (' tk_7fb848e67d29', 'task_7c91e54e1de7', 'not interested', '6', 'b', '2016-05-12 11:29:17', 'b', '2016-05-12 11:29:35', 'n');
INSERT INTO `task_details` VALUES (' tk_7ff40945f272', 'task_901f87b1d372', 'cal attended & later', '3', 'b', '2016-05-12 14:28:13', 'b', '2016-05-12 14:29:08', 'n');
INSERT INTO `task_details` VALUES (' tk_812d4c89e89f', 'task_dab5c6f5e2ba', 'jhkjk', null, 'b', '2016-05-17 12:01:14', 'b', '2016-05-17 17:25:30', 'y');
INSERT INTO `task_details` VALUES (' tk_8184575a4c8c', 'task_0a39de72f7e2', ';l\';l\';l\'', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 18:08:23', 'n');
INSERT INTO `task_details` VALUES (' tk_830d3617dbd3', 'task_8f818cc331fa', 'ghgh', null, 'b', '2016-05-17 18:57:08', 'b', '2016-06-20 17:17:34', 'y');
INSERT INTO `task_details` VALUES (' tk_8403ac774894', 'task_8d12c35c675f', 'cal attended & later', '3', 'b', '2016-05-12 11:31:07', 'b', '2016-05-12 11:33:19', 'y');
INSERT INTO `task_details` VALUES (' tk_8436e385506b', 'task_0a39de72f7e2', 'ijo', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 17:56:14', 'n');
INSERT INTO `task_details` VALUES (' tk_84912c520cc9', 'task_7c91e54e1de7', 'not completed', '4', 'b', '2016-05-12 11:29:17', 'b', '2016-05-12 11:30:05', 'y');
INSERT INTO `task_details` VALUES (' tk_857db8a3ec11', 'task_238632711ba2', 'fake call', '7', 'b', '2016-05-12 15:03:50', 'b', '2016-05-12 15:06:06', 'y');
INSERT INTO `task_details` VALUES (' tk_86c04e83667e', 'task_bb232be6db08', 'confirm soon', '9', 'b', '2016-05-12 12:13:06', 'b', '2016-05-12 12:13:46', 'n');
INSERT INTO `task_details` VALUES (' tk_86e346d19563', 'task_b03f38f314ee', 'number not exist', '8', 'b', '2016-05-12 11:45:34', 'b', '2016-05-12 11:47:04', 'y');
INSERT INTO `task_details` VALUES (' tk_889fca3a9494', 'task_c5b87d7cd09a', 'fake call', '7', 'b', '2016-05-12 12:14:47', 'b', '2016-05-12 12:16:12', 'y');
INSERT INTO `task_details` VALUES (' tk_88e148878a30', 'task_dbe3c0f712e2', 'will walk in', '11', 'b', '2016-05-12 17:12:22', 'b', '2016-05-12 17:13:28', 'n');
INSERT INTO `task_details` VALUES (' tk_8986807b7a16', 'task_ad3d3284da66', 'call not picking', '2', 'b', '2016-05-12 15:14:21', 'b', '2016-05-12 15:16:14', 'y');
INSERT INTO `task_details` VALUES (' tk_8af49263a5b6', 'task_8f40379b82d5', 'fake call', '7', 'b', '2016-05-12 11:27:59', 'b', '2016-05-12 11:28:47', 'y');
INSERT INTO `task_details` VALUES (' tk_8b426c33dd50', 'task_1f840d77e29e', 'cal attended & later', '3', 'b', '2016-05-12 12:19:19', 'b', '2016-05-12 12:20:14', 'n');
INSERT INTO `task_details` VALUES (' tk_8c31c7f10e34', 'task_0a39de72f7e2', 'ok i will do ', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 17:47:04', 'n');
INSERT INTO `task_details` VALUES (' tk_8ca5761cee44', 'task_40e043fa71e5', 'cancelled', '10', 'b', '2016-05-12 17:03:43', 'b', '2016-05-12 17:04:46', 'y');
INSERT INTO `task_details` VALUES (' tk_8d0a21fd4cdd', 'task_ea94ef9cf0a5', 'gfhg', null, 'b', '2016-05-17 18:58:36', 'b', '2016-05-19 20:03:20', 'n');
INSERT INTO `task_details` VALUES (' tk_8d1ef097fd0c', 'task_8f818cc331fa', 'ghgh', null, 'b', '2016-05-17 18:57:08', 'b', '2016-06-20 17:17:10', 'n');
INSERT INTO `task_details` VALUES (' tk_8d4c710c60ef', 'task_0a39de72f7e2', 'ui', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 17:58:18', 'n');
INSERT INTO `task_details` VALUES (' tk_8d72032aaad9', 'task_1f840d77e29e', 'call not picking', '2', 'b', '2016-05-12 12:19:19', 'b', '2016-05-12 12:19:43', 'n');
INSERT INTO `task_details` VALUES (' tk_8e7a36b65ada', 'task_35f0c8691a16', 'number not exist', '8', 'b', '2016-05-12 17:21:41', 'b', '2016-05-12 17:22:35', 'y');
INSERT INTO `task_details` VALUES (' tk_8edfd7146e01', 'task_b09fd31456d6', 'register', '1', 'b', '2016-05-12 12:26:03', 'b', '2016-05-12 12:26:49', 'n');
INSERT INTO `task_details` VALUES (' tk_8f222ea9b9f4', 'task_07e81c0e4920', 'number not exist', '8', 'b', '2016-05-12 17:05:23', 'b', '2016-05-12 17:06:28', 'n');
INSERT INTO `task_details` VALUES (' tk_8f3c35d63fb6', 'task_fb874fe8e9c1', 'will walk in', '11', 'b', '2016-05-12 15:22:50', 'b', '2016-05-12 15:26:56', 'n');
INSERT INTO `task_details` VALUES (' tk_8f43640c6816', 'task_7086d84c5b78', 'confirm soon', '9', 'b', '2016-05-12 11:48:42', 'b', '2016-05-12 11:49:34', 'n');
INSERT INTO `task_details` VALUES (' tk_91c6b3c17452', 'task_c791ac6ce31d', 'dfdf', null, 'b', '2016-05-17 18:56:48', 'b', '2016-05-17 18:56:54', 'y');
INSERT INTO `task_details` VALUES (' tk_9270c4e800de', 'task_5142fee1e138', 'cancelled', '10', 'b', '2016-05-12 17:01:30', 'b', '2016-05-12 17:02:32', 'n');
INSERT INTO `task_details` VALUES (' tk_938406ebd7e9', 'task_4070a226680e', 'not exist', '8', 'b', '2016-05-12 16:05:28', 'b', '2016-05-12 16:05:57', 'n');
INSERT INTO `task_details` VALUES (' tk_946bed7e55ca', 'task_ea94ef9cf0a5', 'erer', null, 'b', '2016-05-17 18:58:36', 'b', '2016-05-19 20:06:22', 'n');
INSERT INTO `task_details` VALUES (' tk_962f7bf01b2b', 'task_ea94ef9cf0a5', 'ytuy', null, 'b', '2016-05-17 18:58:36', 'b', '2016-05-19 20:04:18', 'n');
INSERT INTO `task_details` VALUES (' tk_97006c4f40a7', 'task_82e47984babb', 'register', '1', 'b', '2016-05-12 14:56:18', 'b', '2016-05-12 14:58:01', 'n');
INSERT INTO `task_details` VALUES (' tk_982bc8ec14f3', 'task_9e372c457cd0', 'put on hold', '2', 'b', '2016-05-12 11:17:20', 'b', '2016-05-12 21:02:05', 'n');
INSERT INTO `task_details` VALUES (' tk_98def898e88d', 'task_05add06e4a01', 'uyiui', null, 'b', '2016-05-17 18:58:14', 'b', '2016-06-20 17:49:45', 'n');
INSERT INTO `task_details` VALUES (' tk_9accc2544e5a', 'task_40e043fa71e5', 'cal attended & later', '3', 'b', '2016-05-12 17:03:43', 'b', '2016-05-12 17:04:24', 'n');
INSERT INTO `task_details` VALUES (' tk_9b13ad185ed6', 'task_fb874fe8e9c1', 'not interested', '6', 'b', '2016-05-12 15:22:50', 'b', '2016-05-12 15:26:28', 'n');
INSERT INTO `task_details` VALUES (' tk_9c8a082fd2ca', 'task_a1378a4a657d', 'not exixt', '8', 'b', '2016-05-17 10:31:22', 'b', '2016-05-17 12:24:25', 'n');
INSERT INTO `task_details` VALUES (' tk_9f165eda542b', 'task_9fecc47fb46c', 'number not exist', '8', 'b', '2016-05-12 11:37:16', 'b', '2016-05-12 11:37:57', 'n');
INSERT INTO `task_details` VALUES (' tk_9f565e7645a9', 'task_0a39de72f7e2', 'hgj', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 18:21:00', 'n');
INSERT INTO `task_details` VALUES (' tk_9ff7ebebc484', 'task_50ac93aaa1d4', 'will walk in', '11', 'b', '2016-05-12 11:21:14', 'b', '2016-05-12 11:21:51', 'n');
INSERT INTO `task_details` VALUES (' tk_a1fa357d0207', 'task_0a39de72f7e2', ';l\'', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 18:21:02', 'n');
INSERT INTO `task_details` VALUES (' tk_a259091f72ac', 'task_acc1b09fe929', 'not completed', '4', 'b', '2016-05-12 17:09:57', 'b', '2016-05-12 17:10:24', 'n');
INSERT INTO `task_details` VALUES (' tk_a2fbedfdd259', 'task_8c9ff93fc508', 'fake call', '7', 'b', '2016-05-12 16:04:12', 'b', '2016-05-12 16:04:36', 'n');
INSERT INTO `task_details` VALUES (' tk_a31d70a01a89', 'task_05add06e4a01', 'iuoi', null, 'b', '2016-05-17 18:58:14', 'b', '2016-06-20 17:39:50', 'n');
INSERT INTO `task_details` VALUES (' tk_a36f172f0ba3', 'task_0a39de72f7e2', 'ohhh', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 17:56:19', 'n');
INSERT INTO `task_details` VALUES (' tk_a55407e3a7ae', 'task_e93490d4455c', 'not exist', '8', 'b', '2016-05-17 09:55:40', 'b', '2016-05-17 09:56:32', 'y');
INSERT INTO `task_details` VALUES (' tk_a5c0488d40ce', 'task_b11fd3cae151', 'will walk in', '11', 'b', '2016-05-12 16:06:47', 'b', '2016-05-12 16:07:32', 'n');
INSERT INTO `task_details` VALUES (' tk_a5fb60ff3dc2', 'task_50ac93aaa1d4', 'not completed', '4', 'b', '2016-05-12 11:21:14', 'b', '2016-05-12 11:22:00', 'y');
INSERT INTO `task_details` VALUES (' tk_a69c7d7fe960', 'task_b11fd3cae151', 'contact future', '5', 'b', '2016-05-12 16:06:47', 'b', '2016-05-12 16:07:06', 'n');
INSERT INTO `task_details` VALUES (' tk_a6ba9879f091', 'task_8c4ca9d838b1', '', '9', 'b', '2016-05-12 11:19:07', 'b', '2016-05-12 11:19:56', 'y');
INSERT INTO `task_details` VALUES (' tk_a8c20d3cff86', 'task_5c6eb021c731', 'not completed', '4', 'b', '2016-05-12 15:07:23', 'b', '2016-05-12 15:08:29', 'n');
INSERT INTO `task_details` VALUES (' tk_a94eb9f690d9', 'task_aa5505db7499', 'cancelled', '10', 'b', '2016-05-12 16:54:11', 'b', '2016-05-12 16:55:07', 'y');
INSERT INTO `task_details` VALUES (' tk_aa2d4bc0ab7d', 'task_590f237bca9e', 'register', '1', 'b', '2016-05-12 14:26:10', 'b', '2016-05-12 14:27:43', 'y');
INSERT INTO `task_details` VALUES (' tk_ac0f62b3a863', 'task_aa5505db7499', 'cal attended & later', '3', 'b', '2016-05-12 16:54:11', 'b', '2016-05-12 16:54:52', 'n');
INSERT INTO `task_details` VALUES (' tk_ac12f2d0f6b6', 'task_0a39de72f7e2', 'lookking good', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 17:47:22', 'n');
INSERT INTO `task_details` VALUES (' tk_acfc4cd8502f', 'task_238632711ba2', 'contact future', '5', 'b', '2016-05-12 15:03:50', 'b', '2016-05-12 15:05:21', 'n');
INSERT INTO `task_details` VALUES (' tk_ad123ff9e3ec', 'task_1d574be3a683', 'fake call', '7', 'b', '2016-05-12 15:52:54', 'b', '2016-05-12 15:53:13', 'n');
INSERT INTO `task_details` VALUES (' tk_ad512de1fd24', 'task_9e372c457cd0', 'not interested', '6', 'b', '2016-05-12 11:17:20', 'b', '2016-05-12 11:18:26', 'n');
INSERT INTO `task_details` VALUES (' tk_ae756d0d73ff', 'task_42422935b37d', 'register', '1', 'b', '2016-05-12 11:22:39', 'b', '2016-05-12 11:25:45', 'n');
INSERT INTO `task_details` VALUES (' tk_af5e2d59d96c', 'task_f8da0b09d2b2', 'fake call', '7', 'b', '2016-05-12 15:26:37', 'b', '2016-05-12 15:26:50', 'n');
INSERT INTO `task_details` VALUES (' tk_af6de232e5e0', 'task_ad3d3284da66', 'cancelled', '10', 'b', '2016-05-12 15:14:21', 'b', '2016-05-12 15:14:56', 'n');
INSERT INTO `task_details` VALUES (' tk_b0917006556e', 'task_8c9ff93fc508', 'contact future', '5', 'b', '2016-05-12 16:04:12', 'b', '2016-05-12 16:04:46', 'n');
INSERT INTO `task_details` VALUES (' tk_b39648da4434', 'task_2c3735ede6e8', 'not completed', '4', 'b', '2016-05-12 17:07:03', 'b', '2016-05-12 17:07:34', 'n');
INSERT INTO `task_details` VALUES (' tk_b445aa574a19', 'task_a1378a4a657d', 'ghfgh', '9', 'sanoop', '2016-05-17 10:31:22', 'sanoop', '2016-05-17 12:24:55', 'y');
INSERT INTO `task_details` VALUES (' tk_b4f91edd1141', 'task_c2ecc69a3d2d', 'register', '1', 'b', '2016-05-12 12:17:07', 'b', '2016-05-12 12:18:36', 'y');
INSERT INTO `task_details` VALUES (' tk_b8d6f1c33f0d', 'task_b03f38f314ee', 'will walk in', '11', 'b', '2016-05-12 11:45:34', 'b', '2016-05-12 11:46:26', 'n');
INSERT INTO `task_details` VALUES (' tk_b9e80fa09958', 'task_ad3d3284da66', 'not completed', '2', 'b', '2016-05-12 15:14:21', 'b', '2016-05-12 15:15:54', 'n');
INSERT INTO `task_details` VALUES (' tk_ba37be402618', 'task_f3c8c12d96e8', 'not picking call', '2', 'b', '2016-05-12 11:15:56', 'b', '2016-05-12 11:16:28', 'n');
INSERT INTO `task_details` VALUES (' tk_bb67d1ea899f', 'task_16d7108c119f', 'call not picking', '2', 'b', '2016-05-12 15:28:02', 'b', '2016-05-12 15:29:01', 'y');
INSERT INTO `task_details` VALUES (' tk_bb7295bc951a', 'task_5c6eb021c731', 'contact future', '5', 'b', '2016-05-12 15:07:23', 'b', '2016-05-12 15:08:35', 'y');
INSERT INTO `task_details` VALUES (' tk_bc3b8b717a19', 'task_6a835af3fed4', 'jk', null, 'b', '2016-05-17 11:56:49', 'b', '2016-05-17 17:25:26', 'y');
INSERT INTO `task_details` VALUES (' tk_bca681c0ac6e', 'task_13bc9eb24b8b', 'cancelled', '10', 'b', '2016-05-12 14:20:48', 'b', '2016-05-12 14:21:16', 'n');
INSERT INTO `task_details` VALUES (' tk_bd25402fe155', 'task_b11fd3cae151', 'assess now', '13', 'b', '2016-05-12 16:06:47', 'b', '2016-05-12 16:07:23', 'n');
INSERT INTO `task_details` VALUES (' tk_bdd6ceac1ca8', 'task_7c91e54e1de7', 'contact future', '5', 'b', '2016-05-12 11:29:17', 'b', '2016-05-12 11:29:48', 'n');
INSERT INTO `task_details` VALUES (' tk_be7a123bc415', 'task_9fecc47fb46c', 'new remark', '9', 'sheela', '2016-05-12 11:37:16', 'sheela', '2016-05-17 11:51:34', 'y');
INSERT INTO `task_details` VALUES (' tk_c0ec4e5170cb', 'task_0a39de72f7e2', ';\';\'', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 18:08:19', 'n');
INSERT INTO `task_details` VALUES (' tk_c2142329fd85', 'task_0a39de72f7e2', 'jkl', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 18:06:13', 'n');
INSERT INTO `task_details` VALUES (' tk_c3830f19166a', 'task_0a23372b453c', 'cal attended & later', '3', 'b', '2016-05-12 15:19:45', 'b', '2016-05-12 15:22:09', 'n');
INSERT INTO `task_details` VALUES (' tk_c3fb86fde4da', 'task_05add06e4a01', 'hj', null, 'b', '2016-05-17 18:58:14', 'b', '2016-06-20 17:21:09', 'n');
INSERT INTO `task_details` VALUES (' tk_c442a7bc8389', 'task_ea94ef9cf0a5', 'fghgh', null, 'b', '2016-05-17 18:58:36', 'b', '2016-05-19 20:03:22', 'n');
INSERT INTO `task_details` VALUES (' tk_c4ce0016b8a6', 'task_aa5505db7499', 'call not picking', '2', 'b', '2016-05-12 16:54:11', 'b', '2016-05-12 16:54:40', 'n');
INSERT INTO `task_details` VALUES (' tk_c54542e78858', 'task_acc1b09fe929', 'call not picking', '2', 'b', '2016-05-12 17:09:57', 'b', '2016-05-12 17:11:34', 'y');
INSERT INTO `task_details` VALUES (' tk_c621a25ddd00', 'task_90e35c0f2e18', 'not interested', '6', 'b', '2016-05-12 11:47:29', 'b', '2016-05-12 11:48:06', 'n');
INSERT INTO `task_details` VALUES (' tk_c64f715b5812', 'task_1d574be3a683', 'cnfirm soon', '9', 'b', '2016-05-12 15:52:54', 'b', '2016-05-12 15:53:23', 'n');
INSERT INTO `task_details` VALUES (' tk_c7277066d429', 'task_0a39de72f7e2', 'fnnnnnnnnnnnn', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 18:08:37', 'n');
INSERT INTO `task_details` VALUES (' tk_c72854c71eae', 'task_0a39de72f7e2', 'gfyuytuyt', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 18:08:26', 'n');
INSERT INTO `task_details` VALUES (' tk_c7a1ff6de5e0', 'task_cf38b378e45b', 'fake call', '7', 'b', '2016-05-12 16:59:39', 'b', '2016-05-12 17:00:35', 'n');
INSERT INTO `task_details` VALUES (' tk_c7b8e7dad17b', 'task_fa9395224d15', 'will walk in', '11', 'b', '2016-05-12 11:34:34', 'b', '2016-05-12 11:36:28', 'y');
INSERT INTO `task_details` VALUES (' tk_c832ebb78521', 'task_82e47984babb', 'call not picking', '2', 'b', '2016-05-12 14:56:18', 'b', '2016-05-12 14:58:48', 'y');
INSERT INTO `task_details` VALUES (' tk_c87cef2df9a0', 'task_7cebfe2fca11', 'contact future', '5', 'b', '2016-05-12 12:21:51', 'b', '2016-05-12 12:23:02', 'y');
INSERT INTO `task_details` VALUES (' tk_c947f2710239', 'task_efbec4302dca', 'confirm soon', '9', 'b', '2016-05-12 12:28:59', 'b', '2016-05-12 12:29:24', 'n');
INSERT INTO `task_details` VALUES (' tk_ca3884df00fd', 'task_90e35c0f2e18', 'contact future', '5', 'b', '2016-05-12 11:47:29', 'b', '2016-05-12 11:48:18', 'y');
INSERT INTO `task_details` VALUES (' tk_ca4e87ad6f1f', 'task_8f40379b82d5', 'number not exist', '8', 'b', '2016-05-12 11:27:59', 'b', '2016-05-12 11:28:36', 'n');
INSERT INTO `task_details` VALUES (' tk_ca9306ee9a34', 'task_137e7869a44c', 'confirm soon', '9', 'b', '2016-05-12 14:59:55', 'b', '2016-05-12 15:02:08', 'y');
INSERT INTO `task_details` VALUES (' tk_caf0fb594ffe', 'task_16d7108c119f', 'not completed', '4', 'b', '2016-05-12 15:28:02', 'b', '2016-05-12 15:28:42', 'n');
INSERT INTO `task_details` VALUES (' tk_cb4f21080eeb', 'task_0a39de72f7e2', '\'\'\'', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 18:08:32', 'n');
INSERT INTO `task_details` VALUES (' tk_cc58f5161364', 'task_05add06e4a01', 'jkjk', null, 'b', '2016-05-17 18:58:14', 'b', '2016-06-20 17:35:18', 'n');
INSERT INTO `task_details` VALUES (' tk_cc90bf0006b5', 'task_0a39de72f7e2', ';l\';', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 18:21:04', 'y');
INSERT INTO `task_details` VALUES (' tk_cf11e70160e1', 'task_f8da0b09d2b2', 'cancelled', '10', 'b', '2016-05-12 15:26:37', 'b', '2016-05-12 15:26:57', 'n');
INSERT INTO `task_details` VALUES (' tk_cf59e608a48a', 'task_9e372c457cd0', 'contact future', '5', 'b', '2016-05-12 11:17:20', 'b', '2016-05-12 11:18:14', 'n');
INSERT INTO `task_details` VALUES (' tk_d05dc331ea07', 'task_d5dbc0ab3ef2', 'fake call', '7', 'b', '2016-05-12 12:23:29', 'b', '2016-05-12 12:24:08', 'n');
INSERT INTO `task_details` VALUES (' tk_d094664ba05c', 'task_755e1fd60fa3', 'register', '1', 'b', '2016-05-12 16:51:01', 'b', '2016-05-12 16:53:37', 'y');
INSERT INTO `task_details` VALUES (' tk_d17dbc486b79', 'task_7ea55b29c0da', 'okey', null, 'b', '2016-05-12 20:54:58', 'b', '2016-05-20 13:06:20', 'y');
INSERT INTO `task_details` VALUES (' tk_d2bdca518b19', 'task_c5b87d7cd09a', 'contact future', '5', 'b', '2016-05-12 12:14:47', 'b', '2016-05-12 12:15:07', 'n');
INSERT INTO `task_details` VALUES (' tk_d2e38738b37a', 'task_25ceda8951cf', 'not completed', '4', 'b', '2016-05-12 17:22:56', 'b', '2016-05-12 17:24:22', 'n');
INSERT INTO `task_details` VALUES (' tk_d455047c980c', 'task_8c4ca9d838b1', 'number not exist', '8', 'b', '2016-05-12 11:19:07', 'b', '2016-05-12 11:19:44', 'n');
INSERT INTO `task_details` VALUES (' tk_d5e48d02a92a', 'task_1d574be3a683', 'will contact future', '5', 'b', '2016-05-12 15:52:54', 'b', '2016-05-12 15:53:38', 'n');
INSERT INTO `task_details` VALUES (' tk_d810adcfe7a5', 'task_9fecc47fb46c', 'dfdfg', '6', 'sheela', '2016-05-12 11:37:16', 'sheela', '2016-05-17 11:38:36', 'n');
INSERT INTO `task_details` VALUES (' tk_d8fb7ecb01d6', 'task_05add06e4a01', 'vbnbv', null, 'b', '2016-05-17 18:58:14', 'b', '2016-06-20 17:49:59', 'n');
INSERT INTO `task_details` VALUES (' tk_daf94bd35aa8', 'task_f3c8c12d96e8', 'call attended & later', '3', 'b', '2016-05-12 11:15:56', 'b', '2016-05-12 11:16:49', 'n');
INSERT INTO `task_details` VALUES (' tk_dbb16617f7a7', 'task_8d12c35c675f', 'cancelled', '10', 'b', '2016-05-12 11:31:07', 'b', '2016-05-12 11:32:11', 'n');
INSERT INTO `task_details` VALUES (' tk_de400ff43449', 'task_9fecc47fb46c', 'not interested', '6', 'b', '2016-05-12 11:37:16', 'b', '2016-05-12 11:39:39', 'n');
INSERT INTO `task_details` VALUES (' tk_de9603b1afdd', 'task_c5b87d7cd09a', 'not interested', '6', 'b', '2016-05-12 12:14:47', 'b', '2016-05-12 12:15:53', 'n');
INSERT INTO `task_details` VALUES (' tk_dffed4ef70a7', 'task_13bc9eb24b8b', 'okk', null, 'b', '2016-05-12 14:20:48', 'b', '2016-05-17 17:25:04', 'n');
INSERT INTO `task_details` VALUES (' tk_e06851d6a63a', 'task_8d12c35c675f', 'not completed', '4', 'b', '2016-05-12 11:31:07', 'b', '2016-05-12 11:31:29', 'n');
INSERT INTO `task_details` VALUES (' tk_e1ad360e6221', 'task_42422935b37d', 'fake call', '7', 'b', '2016-05-12 11:22:39', 'b', '2016-05-12 11:26:30', 'y');
INSERT INTO `task_details` VALUES (' tk_e1b4153ce7b5', 'task_6122b785893b', 'number not exist', '8', 'b', '2016-05-12 15:09:19', 'b', '2016-05-12 15:11:18', 'n');
INSERT INTO `task_details` VALUES (' tk_e2749d1810b2', 'task_ad3d3284da66', 'confirm soon', '9', 'b', '2016-05-12 15:14:21', 'b', '2016-05-12 15:15:31', 'n');
INSERT INTO `task_details` VALUES (' tk_e2e48fbbc71d', 'task_0a39de72f7e2', 'yuyu', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 17:46:56', 'n');
INSERT INTO `task_details` VALUES (' tk_e336f1f3247d', 'task_05add06e4a01', 'gffh', null, 'b', '2016-05-17 18:58:14', 'b', '2016-06-20 17:43:03', 'n');
INSERT INTO `task_details` VALUES (' tk_e4ef62617383', 'task_05add06e4a01', 'uiui', null, 'b', '2016-05-17 18:58:14', 'b', '2016-06-20 17:49:39', 'n');
INSERT INTO `task_details` VALUES (' tk_e5ccd71f1629', 'task_0a39de72f7e2', 'yui', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 18:00:24', 'n');
INSERT INTO `task_details` VALUES (' tk_e62f42078dd8', 'task_740aba4d1b77', 'cancelled', '10', 'b', '2016-05-12 16:00:31', 'b', '2016-05-12 16:00:42', 'n');
INSERT INTO `task_details` VALUES (' tk_e68e632372e3', 'task_fb874fe8e9c1', 'register', '1', 'b', '2016-05-12 15:22:50', 'b', '2016-05-12 15:27:13', 'y');
INSERT INTO `task_details` VALUES (' tk_e8a042c705da', 'task_3e0933006565', 'register', '1', 'b', '2016-05-12 16:57:21', 'b', '2016-05-12 16:57:54', 'n');
INSERT INTO `task_details` VALUES (' tk_e8eda696075f', 'task_90e35c0f2e18', 'fake call', '7', 'b', '2016-05-12 11:47:29', 'b', '2016-05-12 11:47:52', 'n');
INSERT INTO `task_details` VALUES (' tk_e93e65464246', 'task_211beb1fafa1', 'confirm soon', '9', 'b', '2016-05-12 17:08:29', 'b', '2016-05-12 17:08:56', 'n');
INSERT INTO `task_details` VALUES (' tk_ecb67235506c', 'task_b03f38f314ee', 'register', '1', 'b', '2016-05-12 11:45:34', 'b', '2016-05-12 11:45:53', 'n');
INSERT INTO `task_details` VALUES (' tk_ed1135f84fe2', 'task_0a39de72f7e2', 'okkk', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 18:15:36', 'n');
INSERT INTO `task_details` VALUES (' tk_edb792a1e919', 'task_cf38b378e45b', 'number not exist', '8', 'b', '2016-05-12 16:59:39', 'b', '2016-05-12 17:00:58', 'y');
INSERT INTO `task_details` VALUES (' tk_f182c5155177', 'task_7cebfe2fca11', 'confirm soon', '9', 'b', '2016-05-12 12:21:51', 'b', '2016-05-12 12:22:24', 'n');
INSERT INTO `task_details` VALUES (' tk_f2b5ae9b9697', 'task_5fd4acc31f5a', 'confirm soon', '9', 'b', '2016-05-12 12:27:35', 'b', '2016-05-12 12:28:20', 'y');
INSERT INTO `task_details` VALUES (' tk_f37dbd71801f', 'task_7086d84c5b78', 'not completed', '4', 'b', '2016-05-12 11:48:42', 'b', '2016-05-12 11:49:15', 'n');
INSERT INTO `task_details` VALUES (' tk_f3dcbb2aec0b', 'task_137e7869a44c', 'cancelled', '10', 'b', '2016-05-12 14:59:55', 'b', '2016-05-12 15:01:14', 'n');
INSERT INTO `task_details` VALUES (' tk_f60dca4991bb', 'task_dbe3c0f712e2', 'not completed', '4', 'b', '2016-05-12 17:12:22', 'b', '2016-05-12 17:12:45', 'n');
INSERT INTO `task_details` VALUES (' tk_f653d60a8509', 'task_fa9395224d15', 'cal attended & later', '3', 'b', '2016-05-12 11:34:34', 'b', '2016-05-12 11:34:56', 'n');
INSERT INTO `task_details` VALUES (' tk_f721ce269c6c', 'task_f6375bf0c3cb', 'register', '1', 'b', '2016-05-12 14:18:07', 'b', '2016-05-12 14:18:28', 'n');
INSERT INTO `task_details` VALUES (' tk_f7d5ad0d2ece', 'task_901f87b1d372', 'call not picking', '2', 'b', '2016-05-12 14:28:13', 'b', '2016-05-12 14:28:52', 'n');
INSERT INTO `task_details` VALUES (' tk_f7d693db537a', 'task_8f08c5a755cd', 'not completed', '4', 'b', '2016-05-12 14:30:08', 'b', '2016-05-12 14:31:35', 'n');
INSERT INTO `task_details` VALUES (' tk_f8688960c642', 'task_d5dbc0ab3ef2', 'not interested', '6', 'b', '2016-05-12 12:23:29', 'b', '2016-05-12 12:23:49', 'n');
INSERT INTO `task_details` VALUES (' tk_fb6ae40f0519', 'task_134c470966dc', 'call not picking', '2', 'b', '2016-05-12 15:12:23', 'b', '2016-05-12 15:13:25', 'n');
INSERT INTO `task_details` VALUES (' tk_fbe32df9ad9e', 'task_ea94ef9cf0a5', 'edrer', null, 'b', '2016-05-17 18:58:36', 'b', '2016-05-19 20:06:04', 'n');
INSERT INTO `task_details` VALUES (' tk_fc4e5c4be514', 'task_3e0933006565', 'call not picking', '2', 'b', '2016-05-12 16:57:21', 'b', '2016-05-12 16:58:10', 'n');
INSERT INTO `task_details` VALUES (' tk_ff476df660df', 'task_0a39de72f7e2', 'gfh', null, 'b', '2016-05-20 16:14:07', 'b', '2016-06-21 18:13:27', 'n');

-- ----------------------------
-- Table structure for task_master
-- ----------------------------
DROP TABLE IF EXISTS `task_master`;
CREATE TABLE `task_master` (
  `task_id` varchar(30) NOT NULL,
  `ticket_no` int(6) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `task_name` varchar(255) DEFAULT NULL,
  `task_description` text,
  `assigned_to` varchar(255) DEFAULT NULL,
  `keyword` varchar(50) DEFAULT NULL,
  `task_status` varchar(100) DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_user` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `latest_flag` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`ticket_no`,`task_id`),
  KEY `task_id` (`task_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6233 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of task_master
-- ----------------------------
INSERT INTO `task_master` VALUES ('task_f3c8c12d96e8', '006127', 'Task1', 'Task1', 'sumeshzoft', 'kol_34e2c9ed586f', 'Closed', 'b', '2016-05-12 11:15:56', 'b', '2016-05-12 20:41:23', 'y');
INSERT INTO `task_master` VALUES ('task_9e372c457cd0', '006128', 'Task2', 'Task2', 'sanoop', 'kol_2034d6c1141a', 'On Hold', 'b', '2016-05-12 11:17:20', 'b', '2016-05-12 21:02:24', 'y');
INSERT INTO `task_master` VALUES ('task_8c4ca9d838b1', '006129', 'Task3', 'Task3', 'a', 'kol_8a2f18e04874', 'Open', 'b', '2016-05-12 11:19:07', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_50ac93aaa1d4', '006130', 'Task4', 'Task4', 'sheela', 'kol_61fb513cd836', 'Closed', 'b', '2016-05-12 11:21:14', 'sheela', '2016-05-17 10:53:21', 'y');
INSERT INTO `task_master` VALUES ('task_42422935b37d', '006131', 'Task5', 'Task5', 'test', 'kol_a8aede121fd0', 'Open', 'b', '2016-05-12 11:22:39', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_8f40379b82d5', '006132', 'Task6', 'Task6', 'rahulzoft', 'kol_399afcd4895b', 'Open', 'b', '2016-05-12 11:27:59', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_7c91e54e1de7', '006133', 'task7', 'task7', 'sumeshzoft', 'kol_7abf915fd234', 'Open', 'b', '2016-05-12 11:29:17', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_8d12c35c675f', '006134', 'task8', 'task8', 'sanoop', 'kol_0bee6bc02001', 'Open', 'b', '2016-05-12 11:31:07', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_fa9395224d15', '006135', 'task9', 'task9', 'a', 'kol_a6684400f7b8', 'Open', 'b', '2016-05-12 11:34:34', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_9fecc47fb46c', '006136', 'task10', 'task10', 'sheela', 'kol_5962bb3e38dc', 'Open', 'b', '2016-05-12 11:37:16', 'b', '2016-05-17 11:55:44', 'y');
INSERT INTO `task_master` VALUES ('task_17fb252f951b', '006137', 'task11', 'task11', 'test', 'kol_54c8a00a7e3f', 'Open', 'b', '2016-05-12 11:40:17', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_7e5394ea327e', '006138', 'task12', 'task12', 'sheela', 'kol_89e86915c264', 'Open', 'b', '2016-05-12 11:42:53', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_b03f38f314ee', '006139', 'task13', 'task13', 'rahulzoft', 'kol_3db089197dec', 'Open', 'b', '2016-05-12 11:45:34', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_90e35c0f2e18', '006140', 'task14', 'task14', 'test', 'kol_2ed96658acf6', 'Open', 'b', '2016-05-12 11:47:29', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_7086d84c5b78', '006141', 'task15', 'task15	', 'sheela', 'kol_411678d6e6f4', 'Open', 'b', '2016-05-12 11:48:42', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_8e264f1b5db5', '006142', 'task16', 'task16', 'sumeshzoft', 'kol_e31c469ed158', 'Open', 'b', '2016-05-12 12:11:19', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_bb232be6db08', '006143', 'task17', 'task17', 'sanoop', 'kol_94f7ef8498f3', 'Open', 'b', '2016-05-12 12:13:06', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_c5b87d7cd09a', '006144', 'task18', 'task18', 'a', 'kol_220ba87e9706', 'Open', 'b', '2016-05-12 12:14:47', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_c2ecc69a3d2d', '006145', 'task19', 'task19\n', 'sheela', 'kol_0f4f8231e40d', 'Open', 'b', '2016-05-12 12:17:07', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_1f840d77e29e', '006146', 'task20', 'task20', 'test', 'kol_736dd34b8d4b', 'Open', 'b', '2016-05-12 12:19:19', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_7cebfe2fca11', '006147', 'task21', 'task21	', 'rahulzoft', 'kol_b5af66e95ee4', 'Open', 'b', '2016-05-12 12:21:51', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_d5dbc0ab3ef2', '006148', 'task22', 'task22', 'rahulzoft', 'kol_8f82d4961270', 'Open', 'b', '2016-05-12 12:23:29', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_b09fd31456d6', '006149', 'task23', 'task23	', 'rahulzoft', 'kol_89d422afc07b', 'Open', 'b', '2016-05-12 12:26:03', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_5fd4acc31f5a', '006150', 'task24', 'task24', 'rahulzoft', 'kol_f613fcac7272', 'Open', 'b', '2016-05-12 12:27:35', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_efbec4302dca', '006151', 'task25', 'task25', 'sheela', 'kol_a75015360b30', 'Open', 'b', '2016-05-12 12:28:59', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_f6375bf0c3cb', '006152', 'task27', 'task27', 'sanoop', 'kol_0cf2ebbc4d6e', 'Open', 'b', '2016-05-12 14:18:07', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_13bc9eb24b8b', '006153', 'task28', 'task28', 'test', 'kol_08334ee9e63b', 'On Hold', 'b', '2016-05-12 14:20:48', 'b', '2016-05-17 10:57:02', 'y');
INSERT INTO `task_master` VALUES ('task_25314e81f388', '006154', 'task29', 'task29', 'rahulzoft', 'kol_6a712685e209', 'Open', 'b', '2016-05-12 14:23:16', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_590f237bca9e', '006155', 'task30', 'task30', 'test', 'kol_a27067d49134', 'Open', 'b', '2016-05-12 14:26:10', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_901f87b1d372', '006156', 'task31', 'task31', 'test', 'kol_62d37c8617a1', 'Open', 'b', '2016-05-12 14:28:13', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_8f08c5a755cd', '006157', 'task32', 'task32', 'rahulzoft', 'kol_c782c4a57949', 'Open', 'b', '2016-05-12 14:30:08', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_82e47984babb', '006158', 'task33', 'task33			', 'sheela', 'kol_4c12054cb98e', 'Open', 'b', '2016-05-12 14:56:18', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_137e7869a44c', '006159', 'task34', 'task34																', 'rahulzoft', 'kol_242d0989603d', 'Open', 'b', '2016-05-12 14:59:55', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_238632711ba2', '006160', 'task35', 'task35', 'sumeshzoft', 'kol_64ef3babcb2f', 'Open', 'b', '2016-05-12 15:03:50', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_5c6eb021c731', '006161', 'task36', 'task36	', 'test', 'kol_e142d887742d', 'Open', 'b', '2016-05-12 15:07:23', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_6122b785893b', '006162', 'task37', 'task37		', 'test', 'kol_ecb819192cb1', 'Open', 'b', '2016-05-12 15:09:19', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_134c470966dc', '006163', 'task38', 'task38', 'test', 'kol_d65027424315', 'Open', 'b', '2016-05-12 15:12:23', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_ad3d3284da66', '006164', 'task39', 'task39', 'sanoop', 'kol_8f08c312b0bb', 'Open', 'b', '2016-05-12 15:14:21', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_370af67c40f7', '006165', 'task40', 'task40	', 'sanoop', 'kol_48094bf4567c', 'Open', 'b', '2016-05-12 15:16:43', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_0a23372b453c', '006166', 'task41', 'task41', 'a', 'kol_620226a28b26', 'Open', 'b', '2016-05-12 15:19:45', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_fb874fe8e9c1', '006167', 'task42', 'task42', 'sheela', 'kol_e659f2c7d490', 'Open', 'b', '2016-05-12 15:22:50', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_f8da0b09d2b2', '006168', 'task61', 'task61', 'sanoop', 'kol_85d08a7f1e63', 'Open', 'b', '2016-05-12 15:26:37', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_16d7108c119f', '006169', 'task43', 'task43', 'test', 'kol_67d23648e57b', 'Open', 'b', '2016-05-12 15:28:02', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_af9eece61eeb', '006170', 'task44', 'task44', 'test', 'kol_a10654d017b9', 'Open', 'b', '2016-05-12 15:29:25', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_1b6190278dc1', '006171', 'task45', 'task45', 'sanoop', 'kol_2946355aee82', 'Open', 'b', '2016-05-12 15:31:21', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_1d574be3a683', '006172', 'user 46task', 'user 46task', 'sanoop', 'kol_a31dacb74a50', 'Open', 'b', '2016-05-12 15:52:54', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_b064f6918461', '006173', 'user47 task', 'user47 task', 'a', 'kol_6022775009af', 'Open', 'b', '2016-05-12 15:58:54', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_740aba4d1b77', '006174', 'user 48 task', 'user 48 task', 'sheela', 'kol_426dbb162d62', 'Open', 'b', '2016-05-12 16:00:31', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_8c9ff93fc508', '006175', 'user 50 task', 'user 50 task\n	', 'a', 'kol_11cdf73f50ad', 'Open', 'b', '2016-05-12 16:04:12', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_4070a226680e', '006176', 'user51 task', 'user51 task', 'test', 'kol_d577d5f9845c', 'Open', 'b', '2016-05-12 16:05:28', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_b11fd3cae151', '006177', 'user 52 task', 'user52 task', 'sheela', 'kol_b110b6474bac', 'Open', 'b', '2016-05-12 16:06:47', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_755e1fd60fa3', '006178', 'task52', 'task52	', 'sumeshzoft', 'kol_b110b6474bac', 'Open', 'b', '2016-05-12 16:51:01', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_aa5505db7499', '006179', 'task53', 'task53', 'a', 'kol_bf323375eea2', 'Open', 'b', '2016-05-12 16:54:11', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_acc3dfde9965', '006180', 'task54', 'task54', 'test', 'kol_a41be56ed0ad', 'Open', 'b', '2016-05-12 16:55:39', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_3e0933006565', '006181', 'task55', 'task55', 'sanoop', 'kol_76567b8cf3e3', 'Open', 'b', '2016-05-12 16:57:21', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_cf38b378e45b', '006182', 'task56', 'task56', 'test', 'kol_81a6d0e8c65f', 'Open', 'b', '2016-05-12 16:59:39', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_5142fee1e138', '006183', 'task57', 'task57', 'test', 'kol_012e6ee03355', 'Open', 'b', '2016-05-12 17:01:30', 'b', '2016-05-17 10:30:20', 'y');
INSERT INTO `task_master` VALUES ('task_40e043fa71e5', '006184', 'task58', 'task58', 'rahulzoft', 'kol_0b371d22bec7', 'Open', 'b', '2016-05-12 17:03:43', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_07e81c0e4920', '006185', 'task59', 'task59', 'test', 'kol_571cb4b294dc', 'Open', 'b', '2016-05-12 17:05:23', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_2c3735ede6e8', '006186', 'task60', 'task60', 'sheela', 'kol_bcbb8659ac31', 'Open', 'b', '2016-05-12 17:07:03', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_211beb1fafa1', '006187', 'task61', 'task61', 'sanoop', 'kol_6a5a2a126552', 'Open', 'b', '2016-05-12 17:08:29', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_acc1b09fe929', '006188', 'task62', 'task62', 'sheela', 'kol_0f20d9affb5c', 'Open', 'b', '2016-05-12 17:09:57', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_dbe3c0f712e2', '006189', 'task63', 'task63	', 'sanoop', 'kol_64ad646eac44', 'Open', 'b', '2016-05-12 17:12:22', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_8a7310abe1be', '006190', 'task64', 'task64	', 'test', 'kol_c795413d8a04', 'Open', 'b', '2016-05-12 17:13:58', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_6e8f7af0cd6c', '006191', 'task64', 'task64', 'test', 'kol_c795413d8a04', 'Open', 'b', '2016-05-12 17:18:06', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_3a4dfdf2551c', '006192', 'task65', 'task65	', 'rahulzoft', 'kol_76afd8bb7ede', 'Open', 'b', '2016-05-12 17:18:58', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_35f0c8691a16', '006193', 'task66', 'task66', 'sheela', 'kol_2e1347283498', 'Open', 'b', '2016-05-12 17:21:41', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_25ceda8951cf', '006194', 'task67', 'task67	', 'sheela', 'kol_ed857910988f', 'Open', 'b', '2016-05-12 17:22:56', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_374d08f73925', '006195', 'test after install', 'test after install', 'a', 'generic', 'Open', 'b', '2016-05-12 20:30:26', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_0c8d3acd528b', '006196', 'new task created', 'new task created desc', 'sanoop', 'generic', 'Open', 'b', '2016-05-12 20:53:37', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_7ea55b29c0da', '006197', 'enquiry based task', 'enquiry based task desc', 'a', 'kol_4c12054cb98e', 'Open', 'b', '2016-05-12 20:54:58', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_3c84421a8a57', '006198', 'new', 'new', 'a', 'kol_4c12054cb98e', 'Closed', 'b', '2016-05-13 16:13:49', 'b', '2016-05-13 19:09:40', 'y');
INSERT INTO `task_master` VALUES ('task_a525ad7d0164', '006199', 'tert', 'ertre', 'sumeshzoft', 'kol_76567b8cf3e3', 'Open', 'b', '2016-05-13 16:37:05', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_b472ab8417d6', '006200', 'fdgfdg', 'dfgfd', 'a', 'kol_4c12054cb98e', 'Open', 'b', '2016-05-13 16:43:31', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_5b5c191caec6', '006201', 'fdg', 'fgfg', 'a', 'kol_6022775009af', 'Closed', 'b', '2016-05-13 16:49:54', 'b', '2016-05-13 16:50:00', 'y');
INSERT INTO `task_master` VALUES ('task_3f933560a8db', '006202', 'errr', 'ererr', 'sanoop', 'kol_6022775009af', 'On Hold', 'b', '2016-05-13 18:19:26', 'b', '2016-05-13 18:19:32', 'y');
INSERT INTO `task_master` VALUES ('task_59fae6bfda23', '006203', 'fdgf', 'fgg', 'sanoop', 'kol_4c12054cb98e', 'Open', 'b', '2016-05-13 19:10:24', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_3f33212ff261', '006204', 'fdgfdg', 'dfgfd', 'sanoop', 'kol_4c12054cb98e', 'Open', 'b', '2016-05-13 19:10:56', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_db5d20b1c21e', '006205', 'jhk', 'jhk', 'sanoop', 'generic', 'Open', 'b', '2016-05-13 19:11:12', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_219eb6b8ca30', '006206', 'ert', 'retre', 'sheela', 'kol_012e6ee03355', 'Open', 'b', '2016-05-17 09:54:48', 'b', '2016-05-17 10:39:38', 'y');
INSERT INTO `task_master` VALUES ('task_df4be737aee5', '006207', 'ret', 'ret', 'a', 'kol_012e6ee03355', 'Open', 'b', '2016-05-17 09:54:53', 'b', '2016-05-17 10:24:58', 'y');
INSERT INTO `task_master` VALUES ('task_e93490d4455c', '006208', 'task for user71', 'task for user71', 'sumeshzoft', 'kol_9227a994700a', 'Open', 'b', '2016-05-17 09:55:40', 'b', '2016-05-17 10:22:31', 'y');
INSERT INTO `task_master` VALUES ('task_1ad73e293866', '006209', 'task for user 71 task no 209', 'task for user 71 task no 209', 'sumeshzoft', 'kol_9227a994700a', 'Open', 'b', '2016-05-17 09:58:03', 'b', '2016-05-17 12:54:19', 'y');
INSERT INTO `task_master` VALUES ('task_825f8d6b0f9a', '006210', 'tsk for user 71 task 210', 'tsk for user 71 task 210', 'a', 'kol_9227a994700a', 'On Hold', 'b', '2016-05-17 10:25:01', 'b', '2016-05-17 10:35:54', 'y');
INSERT INTO `task_master` VALUES ('task_c7a2ee0c50ae', '006211', 'task for user71', 'task for user71', 'sanoop', 'kol_9227a994700a', 'Open', 'b', '2016-05-17 10:28:04', 'b', '2016-05-17 12:51:56', 'y');
INSERT INTO `task_master` VALUES ('task_a1378a4a657d', '006212', 'task for user71', 'task for user71', 'sanoop', 'kol_9227a994700a', 'Open', 'b', '2016-05-17 10:31:22', 'b', '2016-05-17 12:22:13', 'y');
INSERT INTO `task_master` VALUES ('task_5b488ad91ddc', '006213', 'task for user1 tsk 213', 'task for user1 tsk 213', 'rahulzoft', 'kol_34e2c9ed586f', 'Open', 'b', '2016-05-17 10:49:20', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_54229c357569', '006214', 'task10 for user 10', 'task10', 'a', 'kol_5962bb3e38dc', 'Open', 'b', '2016-05-17 11:56:06', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_6a835af3fed4', '006215', 'task10 for user 10 new', 'task10', 'rahulzoft', 'kol_5962bb3e38dc', 'Open', 'b', '2016-05-17 11:56:49', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_0d74740d8655', '006216', 'task10', 'task10', 'sanoop', 'kol_5962bb3e38dc', 'Open', 'b', '2016-05-17 11:58:04', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_dab5c6f5e2ba', '006217', 'task10', 'task10', 'a', 'kol_5962bb3e38dc', 'Open', 'b', '2016-05-17 12:01:14', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_6b9a362b72cc', '006218', 'task10', 'task10', 'a', 'kol_5962bb3e38dc', 'Open', 'b', '2016-05-17 12:04:28', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_36fd1aa1b40e', '006219', 'yu', 'yui', 'vijay', 'generic', 'Open', 'b', '2016-05-17 16:22:43', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_00735363fbec', '006220', 'dgt', 'fgdfg', 'admin', 'generic', 'Open', 'b', '2016-05-17 18:53:39', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_b7c95a9775b3', '006221', 'dfg', 'fdgfg', 'test', 'generic', 'Open', 'b', '2016-05-17 18:56:42', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_c791ac6ce31d', '006222', 'fff', 'dsfd', 'admin', 'generic', 'Open', 'b', '2016-05-17 18:56:48', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_8f818cc331fa', '006223', 'ggg', 'dgfdfg', 'vijay', 'generic', 'Open', 'b', '2016-05-17 18:57:08', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_05add06e4a01', '006224', 'xxxxxxxxfffff', 'fdf', 'sreejakr', 'generic', 'Open', 'b', '2016-05-17 18:58:14', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_ea94ef9cf0a5', '006225', 'ffggggggg', 'gfgfg', 'test', 'generic', 'Open', 'b', '2016-05-17 18:58:36', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_b496ea9b12d0', '006226', 'fdg', 'fdgf', 'sreejakr', 'generic', 'Open', 'b', '2016-05-17 19:03:46', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_e7e88c8be88a', '006227', 'gfh', 'gfh', 'test', 'generic', 'Open', 'b', '2016-05-17 19:05:53', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_fcab0c34b09c', '006228', 'gh', 'gh', 'sreejakr', 'generic', 'Open', 'b', '2016-05-17 19:06:00', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_edf0ca953160', '006229', 'rty', 'gfdhggfh', 'test', 'kol_4c12054cb98e', 'Open', 'b', '2016-05-20 13:06:00', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_8819dab4430f', '006230', 'hiih', 'hhjdlk', 'test', 'generic', 'Open', 'vijay', '2016-05-20 15:20:07', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_3dd4c43c3217', '006231', 'hiiiii', '	hjkhkljfdg', 'test', 'generic', 'Open', 'vijay', '2016-05-20 15:20:39', null, null, 'y');
INSERT INTO `task_master` VALUES ('task_0a39de72f7e2', '006232', 'ygu', 'gfh', 'test', 'generic', 'Open', 'b', '2016-05-20 16:14:07', null, null, 'y');

-- ----------------------------
-- Table structure for training_course
-- ----------------------------
DROP TABLE IF EXISTS `training_course`;
CREATE TABLE `training_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of training_course
-- ----------------------------
INSERT INTO `training_course` VALUES ('1', 'IELTS');
INSERT INTO `training_course` VALUES ('2', 'French');
INSERT INTO `training_course` VALUES ('3', 'OET');

-- ----------------------------
-- Table structure for user_message_tbl
-- ----------------------------
DROP TABLE IF EXISTS `user_message_tbl`;
CREATE TABLE `user_message_tbl` (
  `id` varchar(255) NOT NULL,
  `client_name` varchar(255) DEFAULT NULL,
  `message` text,
  `msubject` text,
  `sender` varchar(255) DEFAULT NULL,
  `reciever` varchar(255) DEFAULT NULL,
  `mtime` datetime DEFAULT NULL,
  `branch` varchar(255) DEFAULT NULL,
  `program_country` varchar(255) DEFAULT NULL,
  `enquiry_id` varchar(255) DEFAULT NULL,
  `reply` text,
  `readflag` int(11) DEFAULT '0',
  `replytime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `enquiruid` (`enquiry_id`) USING BTREE,
  CONSTRAINT `user_message_tbl_ibfk_1` FOREIGN KEY (`enquiry_id`) REFERENCES `enquiry_details` (`enquiry_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user_message_tbl
-- ----------------------------

-- ----------------------------
-- Table structure for work_country
-- ----------------------------
DROP TABLE IF EXISTS `work_country`;
CREATE TABLE `work_country` (
  `id` varchar(255) NOT NULL,
  `country` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of work_country
-- ----------------------------
INSERT INTO `work_country` VALUES ('1', 'Australia');
INSERT INTO `work_country` VALUES ('3', 'Canada');
INSERT INTO `work_country` VALUES ('5', 'New York');
INSERT INTO `work_country` VALUES ('6', 'Saudi Arabia');
INSERT INTO `work_country` VALUES ('7', 'India');
INSERT INTO `work_country` VALUES ('9', 'Germany');
INSERT INTO `work_country` VALUES ('a602d8223016', 'pakistan');

-- ----------------------------
-- View structure for employee_role_branch
-- ----------------------------
DROP VIEW IF EXISTS `employee_role_branch`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%`  VIEW `employee_role_branch` AS  ;

-- ----------------------------
-- View structure for enquiry_expiry_vw
-- ----------------------------
DROP VIEW IF EXISTS `enquiry_expiry_vw`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%`  VIEW `enquiry_expiry_vw` AS SELECT MinAssignedDate, DATE_ADD(MinAssignedDate,INTERVAL  mst.enquiry_duration SECOND) as end_date
       , mst.user_name as assigned_to, lst.enquiry_id
FROM MinAssignDate_Current_User_Vw lst,
                                master_common_pool_config mst
where mst.user_name = lst.assigned_to ;

-- ----------------------------
-- View structure for hyperlink_count_admin
-- ----------------------------
DROP VIEW IF EXISTS `hyperlink_count_admin`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%`  VIEW `hyperlink_count_admin` AS SELECT 'Total Enquiries' as status,-1 as Status_id,'D' as tabname,count(1)AS Total_count, 
sum(CASE WHEN east.enquiry_id is NULL THEN 0 ELSE 1 END) as study,
sum(CASE WHEN ewt.enquiry_id is NULL THEN 0 ELSE 1 END) as Work,
sum(CASE WHEN emt.enquiry_id is NULL THEN 0 ELSE 1 END) as Migrat,
sum(CASE WHEN ett.enquiry_id is NULL THEN 0 ELSE 1 END) as Training,enq.branch
from enquiry_details enq
LEFT JOIN  enquiry_assessment_study_tbl east  on (east.enquiry_id=enq.enquiry_id) and east.latest_flag='y'
LEFT JOIN enquiry_assessment_work_tbl ewt  on (ewt.enquiry_id = enq.enquiry_id) and ewt.latest_flag='y'
LEFT JOIN enquiry_assessment_migrate_tbl emt  on (emt.enquiry_id = enq.enquiry_id) and emt.latest_flag='y'
LEFT JOIN enquiry_assessment_training_tbl ett  on (ett.enquiry_id = enq.enquiry_id) and ett.latest_flag='y'
LEFT JOIN employee_role_branch erb ON (erb.USER_NAME=enq.enquiry_assigning)
group by `status`
UNION
SELECT 'Starred' as status, -2 as Status_id,'D' as tabname,sum( case when enq.rating=1 then 1 else 0 end )  AS Total_count,
sum(CASE WHEN east.enquiry_id is NULL THEN 0 ELSE 1 END) as study,
sum(CASE WHEN ewt.enquiry_id is NULL THEN 0 ELSE 1 END) as Work,
sum(CASE WHEN emt.enquiry_id is NULL THEN 0 ELSE 1 END) as Migrat,
sum(CASE WHEN ett.enquiry_id is NULL THEN 0 ELSE 1 END) as Training,enq.branch
from enquiry_details enq
LEFT JOIN  enquiry_assessment_study_tbl east  on (east.enquiry_id=enq.enquiry_id) and east.latest_flag='y' AND enq.rating=1
LEFT JOIN enquiry_assessment_work_tbl ewt  on (ewt.enquiry_id = enq.enquiry_id) and ewt.latest_flag='y' AND enq.rating=1
LEFT JOIN enquiry_assessment_migrate_tbl emt  on (emt.enquiry_id = enq.enquiry_id) and emt.latest_flag='y' AND enq.rating=1
LEFT JOIN enquiry_assessment_training_tbl ett  on (ett.enquiry_id = enq.enquiry_id) and ett.latest_flag='y' AND enq.rating=1
LEFT JOIN employee_role_branch erb ON (erb.USER_NAME=enq.enquiry_assigning)
GROUP BY `status`
UNION
select m.app_status , m.app_status_id as Status_id, 'A' as tabname, sum( case when st.assement_status_id IS NULL then 0 else 1 end)AS Total_count,
SUM(CASE WHEN east.enquiry_id is NULL THEN 0 ELSE 1  END) as study,
SUM(CASE WHEN ewt.enquiry_id is NULL THEN 0 ELSE 1  END)as Work,
sum(CASE WHEN emt.enquiry_id is NULL THEN 0 ELSE 1  END) as Migrat,
sum(CASE WHEN ett.enquiry_id is NULL THEN 0 ELSE 1  END) as Training,enq.branch
from mastertbl_app_status m
LEFT OUTER JOIN enquiry_assesment_status_tbl st on (m.app_status_id=st.enquiry_status)
LEFT OUTER JOIN enquiry_details enq on (enq.enquiry_id = st.enquiry_id)
LEFT JOIN  enquiry_assessment_study_tbl east  on (east.enquiry_id=st.enquiry_id) and east.latest_flag='y'
LEFT JOIN enquiry_assessment_work_tbl ewt  on (ewt.enquiry_id = st.enquiry_id) and ewt.latest_flag='y'
LEFT JOIN enquiry_assessment_migrate_tbl emt  on (emt.enquiry_id = st.enquiry_id) and emt.latest_flag='y'
LEFT JOIN enquiry_assessment_training_tbl ett  on (ett.enquiry_id = st.enquiry_id) and ett.latest_flag='y'
LEFT JOIN employee_role_branch erb ON erb.USER_NAME=enq.enquiry_assigning
group by m.app_status ;

-- ----------------------------
-- View structure for hyperlink_count_admin_followup
-- ----------------------------
DROP VIEW IF EXISTS `hyperlink_count_admin_followup`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%`  VIEW `hyperlink_count_admin_followup` AS SELECT 'Total Enquiries' as status, -1 as Status_id, 'D' as tabname, count(1)AS Total_count,
enq.assigned_branch as branch
from history_enquiry_assigning_tbl enq WHERE
enq.assigned_date>=NOW() AND 
 DATE(assigned_date) < DATE_ADD(CURRENT_DATE(),INTERVAL 1 DAY) AND enq.current_status<>'Assessment Pending'  
AND enq.latest_flag='y'and enq.assigned_by<>'Self Assigned'
group by `status`

UNION
select m.app_status AS `status` , m.app_status_id as Status_id, 'A' as tabname, 
 sum( case when m.date_reason_enable=1 THEN 
		(CASE WHEN CURDATE()=DATE(heat.assigned_date) THEN 1 ELSE 0 END)ELSE
		(CASE WHEN CURDATE()=DATE(heat.assigned_date) THEN 1 ELSE 0 END)END)AS Total_count,
heat.assigned_branch AS branch
from mastertbl_app_status m
RIGHT OUTER JOIN history_enquiry_assigning_tbl heat ON m.app_status_id=heat.current_status
WHERE DATE(heat.assigned_date) is not null AND heat.current_status<>'Assessment Pending' and heat.assigned_by<>'Self Assigned'
and heat.assigned_date>=NOW()
AND DATE(assigned_date) < DATE_ADD(CURRENT_DATE(),INTERVAL 1 DAY) AND heat.latest_flag='y'
and m.date_reason_enable=1
group by m.app_status 
having Total_count > 0 ;

-- ----------------------------
-- View structure for hyperlink_count_branch
-- ----------------------------
DROP VIEW IF EXISTS `hyperlink_count_branch`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER  VIEW `hyperlink_count_branch` AS SELECT 'Total Enquiries' as status, -1 as Status_id, 'D' as tabname, count(1)AS Total_count,
sum(CASE WHEN east.enquiry_id is NULL THEN 0 ELSE 1 END) as study,
sum(CASE WHEN ewt.enquiry_id is NULL THEN 0 ELSE 1 END) as Work,
sum(CASE WHEN emt.enquiry_id is NULL THEN 0 ELSE 1 END) as Migrat,
sum(CASE WHEN ett.enquiry_id is NULL THEN 0 ELSE 1 END) as Training,enq.staff_branch as branch
from enquiry_details enq
LEFT JOIN  enquiry_assessment_study_tbl east  on (east.enquiry_id=enq.enquiry_id) and east.latest_flag='y'
LEFT JOIN enquiry_assessment_work_tbl ewt  on (ewt.enquiry_id = enq.enquiry_id) and ewt.latest_flag='y'
LEFT JOIN enquiry_assessment_migrate_tbl emt  on (emt.enquiry_id = enq.enquiry_id) and emt.latest_flag='y'
LEFT JOIN enquiry_assessment_training_tbl ett  on (ett.enquiry_id = enq.enquiry_id) and ett.latest_flag='y'
LEFT JOIN employee_role_branch erb ON (erb.USER_NAME=enq.staff_usrname)
group by staff_branch
UNION
SELECT 'Starred' as status, -2 as Status_id, 'D' as tabname, sum( case when enq.rating=1 then 1 else 0 end )  AS Total_count,
sum(CASE WHEN east.enquiry_id is NULL THEN 0 ELSE 1 END) as study,
sum(CASE WHEN ewt.enquiry_id is NULL THEN 0 ELSE 1 END) as Work,
sum(CASE WHEN emt.enquiry_id is NULL THEN 0 ELSE 1 END) as Migrat,
sum(CASE WHEN ett.enquiry_id is NULL THEN 0 ELSE 1 END) as Training,enq.staff_branch as branch
from enquiry_details enq
LEFT JOIN  enquiry_assessment_study_tbl east  on (east.enquiry_id=enq.enquiry_id) and east.latest_flag='y' AND enq.rating=1
LEFT JOIN enquiry_assessment_work_tbl ewt  on (ewt.enquiry_id = enq.enquiry_id) and ewt.latest_flag='y' AND enq.rating=1
LEFT JOIN enquiry_assessment_migrate_tbl emt  on (emt.enquiry_id = enq.enquiry_id) and emt.latest_flag='y' AND enq.rating=1
LEFT JOIN enquiry_assessment_training_tbl ett  on (ett.enquiry_id = enq.enquiry_id) and ett.latest_flag='y' AND enq.rating=1
-- INNER JOIN employee_role_branch erb ON (erb.USER_NAME=enq.enquiry_assigning)
GROUP BY staff_branch
UNION
select m.app_status , m.app_status_id as Status_id, 'A' as tabname,  sum( case when st.assement_status_id IS NULL then 0 else 1 end)AS Total_count,
sum(CASE WHEN east.enquiry_id is NULL THEN 0 ELSE 1  END) as study,
   
sum(CASE WHEN ewt.enquiry_id is NULL THEN 0 ELSE 1  END) as Work,
   
sum(CASE WHEN emt.enquiry_id is NULL THEN 0 ELSE 1  END) as Migrat,
   
sum(CASE WHEN ett.enquiry_id is NULL THEN 0 ELSE 1  END)  as Training,enq.staff_branch
   

from mastertbl_app_status m
LEFT OUTER JOIN enquiry_assesment_status_tbl st on (m.app_status_id=st.enquiry_status)
LEFT OUTER JOIN enquiry_details enq on (enq.enquiry_id = st.enquiry_id)

LEFT JOIN  enquiry_assessment_study_tbl east  on (east.enquiry_id=st.enquiry_id) and east.latest_flag='y'
LEFT JOIN enquiry_assessment_work_tbl ewt  on (ewt.enquiry_id = st.enquiry_id) and ewt.latest_flag='y'
LEFT JOIN enquiry_assessment_migrate_tbl emt  on (emt.enquiry_id = st.enquiry_id) and emt.latest_flag='y'
LEFT JOIN enquiry_assessment_training_tbl ett  on (ett.enquiry_id = st.enquiry_id) and ett.latest_flag='y'
-- INNER JOIN employee_role_branch erb ON erb.USER_NAME=enq.enquiry_assigning
group by enq.staff_branch,m.app_status, m.app_status_id ;

-- ----------------------------
-- View structure for hyperlink_count_branch_followup
-- ----------------------------
DROP VIEW IF EXISTS `hyperlink_count_branch_followup`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%`  VIEW `hyperlink_count_branch_followup` AS SELECT 'Total Enquiries' as status, -1 as Status_id, 'D' as tabname, count(1)AS Total_count,
enq.assigned_branch as branch
from history_enquiry_assigning_tbl enq WHERE enq.assigned_date>=NOW() AND 
 DATE(assigned_date) <= DATE_ADD(CURRENT_DATE(),INTERVAL 1 DAY)  AND enq.current_status<>'Assessment Pending'
AND enq.assigned_by<>enq.assigned_to
AND enq.latest_flag='y' 
group by enq.assigned_branch
UNION
select m.app_status AS `status` , m.app_status_id as Status_id, 'A' as tabname, 
 sum( case when m.date_reason_enable=1 THEN 
		(CASE WHEN heat.assigned_date>=NOW() THEN 1 ELSE 0 END)ELSE
		(CASE WHEN heat.assigned_date>=NOW() THEN 1 ELSE 0 END)END)AS Total_count,
heat.assigned_branch AS branch

from mastertbl_app_status m
RIGHT OUTER JOIN history_enquiry_assigning_tbl heat ON m.app_status_id=heat.current_status
WHERE heat.current_status<>'Assessment Pending'
and m.date_reason_enable=1
and heat.assigned_date>=NOW()
AND DATE(assigned_date) <= DATE_ADD(CURRENT_DATE(),INTERVAL 1 DAY)
AND heat.latest_flag='y' AND heat.assigned_by<>heat.assigned_to
group by heat.assigned_branch,m.app_status, m.app_status_id 
having Total_count > 0 ;

-- ----------------------------
-- View structure for hyperlink_count_counselor
-- ----------------------------
DROP VIEW IF EXISTS `hyperlink_count_counselor`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%`  VIEW `hyperlink_count_counselor` AS SELECT 'Total Enquiries' as status, -1 as Status_id, 'D' as tabname, enq.enquiry_assigning as user, count(1)AS Total_count,
sum(CASE WHEN east.enquiry_id is NULL THEN 0 ELSE 1 END) as study,
sum(CASE WHEN ewt.enquiry_id is NULL THEN 0 ELSE 1 END) as Work,
sum(CASE WHEN emt.enquiry_id is NULL THEN 0 ELSE 1 END) as Migrat,
sum(CASE WHEN ett.enquiry_id is NULL THEN 0 ELSE 1 END) as Training,enq.branch
from enquiry_details enq
LEFT JOIN  enquiry_assessment_study_tbl east  on (east.enquiry_id=enq.enquiry_id) and east.latest_flag='y'
LEFT JOIN enquiry_assessment_work_tbl ewt  on (ewt.enquiry_id = enq.enquiry_id) and ewt.latest_flag='y'
LEFT JOIN enquiry_assessment_migrate_tbl emt  on (emt.enquiry_id = enq.enquiry_id) and emt.latest_flag='y'
LEFT JOIN enquiry_assessment_training_tbl ett  on (ett.enquiry_id = enq.enquiry_id) and ett.latest_flag='y'
LEFT JOIN employee_role_branch erb ON (erb.USER_NAME=enq.enquiry_assigning)
group by enq.enquiry_assigning,enq.branch
UNION
SELECT 'Starred' as status, -2 as Status_id, 'D' as tabname, enq.enquiry_assigning as user, sum( case when enq.rating=1 then 1 else 0 end )  AS Total_count,
sum(CASE WHEN east.enquiry_id is NULL THEN 0 ELSE 1 END) as study,
sum(CASE WHEN ewt.enquiry_id is NULL THEN 0 ELSE 1 END) as Work,
sum(CASE WHEN emt.enquiry_id is NULL THEN 0 ELSE 1 END) as Migrat,
sum(CASE WHEN ett.enquiry_id is NULL THEN 0 ELSE 1 END) as Training,enq.branch
from enquiry_details enq
LEFT JOIN  enquiry_assessment_study_tbl east  on (east.enquiry_id=enq.enquiry_id) and east.latest_flag='y' AND enq.rating=1
LEFT JOIN enquiry_assessment_work_tbl ewt  on (ewt.enquiry_id = enq.enquiry_id) and ewt.latest_flag='y' AND enq.rating=1
LEFT JOIN enquiry_assessment_migrate_tbl emt  on (emt.enquiry_id = enq.enquiry_id) and emt.latest_flag='y' AND enq.rating=1
LEFT JOIN enquiry_assessment_training_tbl ett  on (ett.enquiry_id = enq.enquiry_id) and ett.latest_flag='y' AND enq.rating=1
INNER JOIN employee_role_branch erb ON (erb.USER_NAME=enq.enquiry_assigning)
GROUP BY enq.enquiry_assigning
UNION
select m.app_status , m.app_status_id as Status_id, 'A' as tabname,  st.counsellor as user, sum( case when st.assement_status_id IS NULL then 0 else 1 end)AS Total_count,
sum(CASE WHEN east.enquiry_id is NULL THEN 0 ELSE 1  END) as study,
   
sum(CASE WHEN ewt.enquiry_id is NULL THEN 0 ELSE 1  END)  as Work,
   
sum(CASE WHEN emt.enquiry_id is NULL THEN 0 ELSE 1  END) as Migrat,
   
sum(CASE WHEN ett.enquiry_id is NULL THEN 0 ELSE 1  END) as Training,enq.branch
   

from mastertbl_app_status m
LEFT OUTER JOIN enquiry_assesment_status_tbl st on (m.app_status_id=st.enquiry_status)
LEFT OUTER JOIN enquiry_details enq on (enq.enquiry_id = st.enquiry_id)

LEFT JOIN  enquiry_assessment_study_tbl east  on (east.enquiry_id=st.enquiry_id) and east.latest_flag='y'
LEFT JOIN enquiry_assessment_work_tbl ewt  on (ewt.enquiry_id = st.enquiry_id) and ewt.latest_flag='y'
LEFT JOIN enquiry_assessment_migrate_tbl emt  on (emt.enquiry_id = st.enquiry_id) and emt.latest_flag='y'
LEFT JOIN enquiry_assessment_training_tbl ett  on (ett.enquiry_id = st.enquiry_id) and ett.latest_flag='y'
INNER JOIN employee_role_branch erb ON erb.USER_NAME=enq.enquiry_assigning
group by enq.enquiry_assigning,m.app_status, m.app_status_id

-- UNION
-- SELECT mst.task_follow_status, mst.task_follow_id as Status_Id, 'T' as tabname, enq.enquiry_assigning as User,
-- sum( case when det.assessment_substatus IS NULL then 0 else 1 end) AS Total_count,
-- sum(CASE WHEN (CASE WHEN east.enquiry_id is NULL THEN 0 ELSE 1 END) THEN(case when det.assessment_substatus IS NULL then 0 else 1 end)else 0 END) as study,
-- sum(CASE WHEN (CASE WHEN ewt.enquiry_id is NULL THEN 0 ELSE 1 END) THEN(case when det.assessment_substatus IS NULL then 0 else 1 end)ELSE 0 END ) as Work2,
-- sum(CASE WHEN (CASE WHEN emt.enquiry_id is NULL THEN 0 ELSE 1 END) THEN(case when det.assessment_substatus IS NULL then 0 else 1 end)ELSE 0 END) as Migrat,
-- sum(CASE WHEN (CASE WHEN ett.enquiry_id is NULL THEN 0 ELSE 1 END) THEN(case when det.assessment_substatus IS NULL then 0 else 1 end) ELSE 0 END) as Training,enq.branch
-- from mastertbl_task_follow_status mst
-- LEFT OUTER JOIN task_details det ON (det.assessment_substatus =mst.task_follow_id and det.latest_flag='y')
-- LEFT OUTER JOIN task_master tms ON (tms.task_id = det.task_id)
-- LEFT JOIN enquiry_details enq ON (tms.keyword=enq.enquiry_id)
-- LEFT JOIN employee_role_branch erb ON (erb.USER_NAME=tms.assigned_to)
-- LEFT JOIN enquiry_assessment_study_tbl east  on (east.enquiry_id=enq.enquiry_id )AND east.latest_flag='y'
-- LEFT JOIN enquiry_assessment_work_tbl ewt  on (ewt.enquiry_id = enq.enquiry_id )AND ewt.latest_flag='y'
-- LEFT JOIN enquiry_assessment_migrate_tbl emt  on (emt.enquiry_id = enq.enquiry_id )AND emt.latest_flag='y'
-- LEFT JOIN enquiry_assessment_training_tbl ett  on (ett.enquiry_id = enq.enquiry_id ) AND ett.latest_flag='y'
-- group by enq.enquiry_assigning , mst.task_follow_status,mst.task_follow_id ;

-- ----------------------------
-- View structure for hyperlink_count_counselor_followup
-- ----------------------------
DROP VIEW IF EXISTS `hyperlink_count_counselor_followup`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER  VIEW `hyperlink_count_counselor_followup` AS SELECT 'Total Enquiries' as status, -1 as Status_id, 'D' as tabname,enq.assigned_to as user,  count(1)AS Total_count,
enq.assigned_branch as branch
from history_enquiry_assigning_tbl enq WHERE enq.assigned_date>=NOW() AND 
 DATE(assigned_date) < DATE_ADD(CURRENT_DATE(),INTERVAL 1 DAY) AND enq.current_status<>'Assessment Pending'  
AND enq.latest_flag='y'and enq.assigned_by<>'Self Assigned'
group by enq.assigned_to  
UNION
select m.app_status AS `status` , m.app_status_id as Status_id, 'A' as tabname, heat.assigned_to AS user,
 sum( case when m.date_reason_enable=1 THEN 
		(CASE WHEN CURDATE()=DATE(heat.assigned_date) THEN 1 ELSE 0 END)ELSE
		(CASE WHEN CURDATE()=DATE(heat.assigned_date) THEN 1 ELSE 0 END)END)AS Total_count,
heat.assigned_branch AS branch
from mastertbl_app_status m
RIGHT OUTER JOIN history_enquiry_assigning_tbl heat ON m.app_status_id=heat.current_status
where DATE(heat.assigned_date) is not null AND heat.current_status<>'Assessment Pending' 
and heat.assigned_by<>'Self Assigned' 
and m.date_reason_enable=1
and heat.assigned_date>=NOW()
AND DATE(assigned_date) < DATE_ADD(CURRENT_DATE(),INTERVAL 1 DAY) 
AND heat.latest_flag='y'
group by heat.assigned_to,m.app_status, m.app_status_id 
having Total_count > 0 ;

-- ----------------------------
-- View structure for listofassigneddateforcurrentuser_vw
-- ----------------------------
DROP VIEW IF EXISTS `listofassigneddateforcurrentuser_vw`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%`  VIEW `listofassigneddateforcurrentuser_vw` AS select hs.created_date, assigned_to, hs.enquiry_id, assigned_date
from history_enquiry_assigning_tbl hs,
                                enquiry_details dt
where hs.enquiry_id = dt.enquiry_id
  and hs.assigned_to = dt.enquiry_assigning
  and dt.completion_flag <> 1 ;

-- ----------------------------
-- View structure for listofenquirieswithminassigneddate_vw
-- ----------------------------
DROP VIEW IF EXISTS `listofenquirieswithminassigneddate_vw`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%`  VIEW `listofenquirieswithminassigneddate_vw` AS select min(hs1.assigned_date) MinAssignedDate, hs1.assigned_to, hs1.enquiry_id
  from history_enquiry_assigning_tbl hs1,
			 history_enquiry_assigning_tbl hs2,
       enquiry_details dt	
 where hs1.enquiry_id = hs2.enquiry_id
   and hs1.assigned_to = dt.enquiry_assigning
   and hs1.enquiry_id = dt.enquiry_id
   and hs2.latest_flag = 'n'
   and hs1.assigned_date > hs2.assigned_date
   and hs1.assigned_by <> hs1.assigned_to
group by hs1.assigned_to, hs1.enquiry_id ;

-- ----------------------------
-- View structure for listofmaxassigneddateforotherusers_vw
-- ----------------------------
DROP VIEW IF EXISTS `listofmaxassigneddateforotherusers_vw`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%`  VIEW `listofmaxassigneddateforotherusers_vw` AS select max(created_date) MaxCreatedDate, hs.enquiry_id
from history_enquiry_assigning_tbl hs,
                                enquiry_details dt
where hs.enquiry_id = dt.enquiry_id
  and hs.assigned_to <> dt.enquiry_assigning
  and hs.assigned_to <> hs.assigned_by
  and dt.completion_flag <> 1
group by hs.enquiry_id ;

-- ----------------------------
-- View structure for minassigndate_current_user_vw
-- ----------------------------
DROP VIEW IF EXISTS `minassigndate_current_user_vw`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%`  VIEW `minassigndate_current_user_vw` AS SELECT curr.enquiry_id, curr.assigned_to, min(assigned_date) MinAssignedDate
  from ListOfMaxAssignedDateForOtherUsers_VW others RIGHT OUTER JOIN
     ListOfAssignedDateForCurrentUser_VW curr
ON others.enquiry_id = curr.enquiry_id
where curr.created_date > others.MaxCreatedDate
-- where curr.enquiry_id = 'koc_228d6705c090'
group by curr.enquiry_id, curr.assigned_to ;

-- ----------------------------
-- Procedure structure for getHyper
-- ----------------------------
DROP PROCEDURE IF EXISTS `getHyper`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `getHyper`()
BEGIN

SELECT 'Total Enquiries' as status,count(1)AS Total_count,sum(east.study_count) as study,sum(ewt.work_count) as Work,sum(emt.migrate_count) as Migrat,sum(ett.training_count) as Training,enq.branch,erb.USER_NAME

from enquiry_details enq
LEFT JOIN ( select DISTINCT enquiry_id, 1 as study_count  from enquiry_assessment_study_tbl) east  on (east.enquiry_id=enq.enquiry_id)
LEFT JOIN( select DISTINCT enquiry_id, 1 as work_count  from enquiry_assessment_work_tbl) ewt  on (ewt.enquiry_id = enq.enquiry_id)
LEFT JOIN( select DISTINCT enquiry_id, 1 as migrate_count  from enquiry_assessment_migrate_tbl) emt  on (emt.enquiry_id = enq.enquiry_id)
LEFT JOIN( select DISTINCT enquiry_id, 1 as training_count from enquiry_assessment_training_tbl) ett  on (ett.enquiry_id = enq.enquiry_id)
LEFT JOIN employee_role_branch erb ON (erb.USER_NAME=enq.enquiry_assigning)
group by branch,erb.USER_NAME

UNION

SELECT 'Starred' as status,sum( case when enq.rating=1 then 1
													 else 0 end )  AS Total_count,sum(east.study_count) as study,sum(ewt.work_count) as Work,sum(emt.migrate_count) as Migrat,sum(ett.training_count) as Training,enq.branch,erb.USER_NAME

from enquiry_details enq
LEFT JOIN ( select DISTINCT enquiry_id, 1 as study_count  from enquiry_assessment_study_tbl) east  on (east.enquiry_id=enq.enquiry_id AND enq.rating=1)
LEFT JOIN( select DISTINCT enquiry_id, 1 as work_count  from enquiry_assessment_work_tbl) ewt  on (ewt.enquiry_id = enq.enquiry_id AND enq.rating=1)
LEFT JOIN( select DISTINCT enquiry_id, 1 as migrate_count  from enquiry_assessment_migrate_tbl) emt  on (emt.enquiry_id = enq.enquiry_id AND enq.rating=1)
LEFT JOIN( select DISTINCT enquiry_id, 1 as training_count from enquiry_assessment_training_tbl) ett  on (ett.enquiry_id = enq.enquiry_id AND enq.rating=1)
LEFT JOIN employee_role_branch erb ON (erb.USER_NAME=enq.enquiry_assigning)
GROUP BY branch,erb.USER_NAME

UNION

SELECT m.app_status , sum( case when st.assement_status_id IS NULL then 0
													 else 1 end)AS Total_count, sum(east.study_count) as study,sum(ewt.work_count) as Work,sum(emt.migrate_count) as Migrat,sum(ett.training_count) as Training,enq.branch,erb.USER_NAME
                     
from  enquiry_details enq 

 LEFT JOIN  enquiry_assesment_status_tbl st  ON (enq.enquiry_id = st.enquiry_id)

LEFT JOIN mastertbl_app_status m on (m.app_status_id=st.enquiry_status)
 LEFT JOIN ( select DISTINCT enquiry_id, 1 as study_count  from enquiry_assessment_study_tbl) east  on (east.enquiry_id=st.enquiry_id)
 LEFT JOIN( select DISTINCT enquiry_id, 1 as work_count  from enquiry_assessment_work_tbl) ewt  on (ewt.enquiry_id = st.enquiry_id)
LEFT JOIN( select DISTINCT enquiry_id, 1 as migrate_count  from enquiry_assessment_migrate_tbl) emt  on (emt.enquiry_id = st.enquiry_id)
LEFT JOIN( select DISTINCT enquiry_id, 1 as training_count from enquiry_assessment_training_tbl) ett  on (ett.enquiry_id = st.enquiry_id)
LEFT JOIN employee_role_branch erb ON erb.USER_NAME=enq.enquiry_assigning
group by enq.branch,m.app_status,erb.USER_NAME

UNION

SELECT mst.task_follow_status ,
				sum( case when det.assessment_substatus IS NULL then 0 else 1 end) AS Total_count,
				sum(CASE WHEN east.study_count=1 THEN(case when det.assessment_substatus IS NULL then 0
													 else 1 end)else 0 END) as study,
				sum(CASE WHEN ewt.work_count=1 THEN(case when det.assessment_substatus IS NULL then 0 else 1 end)ELSE 0 END ) as Work2,
				sum(CASE WHEN emt.migrate_count=1 THEN(case when det.assessment_substatus IS NULL then 0 else 1 end)ELSE 0 END) as Migrat,
				sum(CASE WHEN ett.training_count=1 THEN(case when det.assessment_substatus IS NULL then 0 else 1 end) ELSE 0 END) as Training,enq.branch,erb.USER_NAME
from task_master tms
LEFT JOIN employee_role_branch erb ON (erb.USER_NAME=tms.assigned_to)
LEFT JOIN enquiry_details enq ON (tms.keyword=enq.enquiry_id)
LEFT JOIN task_details det ON (det.task_id=tms.task_id and det.latest_flag='y')
LEFT JOIN mastertbl_task_follow_status mst ON (mst.task_follow_id=det.assessment_substatus)
LEFT JOIN ( select DISTINCT enquiry_id, 1 as study_count  from enquiry_assessment_study_tbl) east  on (east.enquiry_id=enq.enquiry_id )
LEFT JOIN( select DISTINCT enquiry_id, 1 as work_count  from enquiry_assessment_work_tbl) ewt  on (ewt.enquiry_id = enq.enquiry_id )
LEFT JOIN( select DISTINCT enquiry_id, 1 as migrate_count  from enquiry_assessment_migrate_tbl) emt  on (emt.enquiry_id = enq.enquiry_id )
LEFT JOIN( select DISTINCT enquiry_id, 1 as training_count from enquiry_assessment_training_tbl) ett  on (ett.enquiry_id = enq.enquiry_id )

group by enq.branch,mst.task_follow_status,erb.USER_NAME;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for latestFlag
-- ----------------------------
DROP PROCEDURE IF EXISTS `latestFlag`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `latestFlag`(enq_id varchar(100),tableName varchar(100))
BEGIN
DECLARE updt DATETIME;
DECLARE crdt DATETIME;
SET @enq_id=enq_id;
SET @updtt =CONCAT("SELECT max(updated_date) INTO @updt
from ",tableName," WHERE enquiry_id='",enq_id,"' and latest_flag <> 'y'");
PREPARE stmtupdt FROM @updtt;
EXECUTE stmtupdt;
DEALLOCATE PREPARE stmtupdt;



SET @crdtt = CONCAT("SELECT max(created_date) INTO @crdt
from ", tableName, 
" WHERE enquiry_id='",enq_id,"'
  and latest_flag <> 'y'");
PREPARE stmtcrdt FROM @crdtt;
EXECUTE stmtcrdt;
DEALLOCATE PREPARE stmtcrdt;

IF (LENGTH(@updtt) > 0) THEN


  SET @upcr=CONCAT("UPDATE ", tableName,"
  set latest_flag = 'y'
  where enquiry_id ='" ,enq_id,"'
  and created_date = '",@crdt,"' LIMIT 1");
  PREPARE stmt3 FROM @upcr;
  EXECUTE stmt3;
  DEALLOCATE PREPARE stmt3;

ELSE 

 IF (@crdt > @updt) THEN
  
			
			SET @upcr1=CONCAT("UPDATE ", tableName,"
  set latest_flag = 'y'
  where enquiry_id ='" ,enq_id,"'
  and created_date = '",@crdt,"' LIMIT 1");
			PREPARE stmt5 FROM @upcr1;
			EXECUTE stmt5;
			DEALLOCATE PREPARE stmt5;

	ELSE

			SET @upup=CONCAT("UPDATE ", tableName,"
												set latest_flag = 'y'
												where enquiry_id ='" ,enq_id,"'
												and updated_date = '",@updt,"' LIMIT 1 ");
			PREPARE stmt4 FROM @upup;
			EXECUTE stmt4;
			DEALLOCATE PREPARE stmt4;

	END IF;
END IF;

END
;;
DELIMITER ;
