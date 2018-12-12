/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.121_3306
Source Server Version : 50142
Source Host           : 192.168.1.121:3306
Source Database       : ia

Target Server Type    : MYSQL
Target Server Version : 50142
File Encoding         : 65001

Date: 2016-06-23 14:02:47
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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/admin_email_id' AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

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
)  ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/agencies_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/appointment_master' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/appointment_master' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/appointments_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/branch_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/counselor_counter_tbl' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for country_codes_tbl
-- ----------------------------
DROP TABLE IF EXISTS `country_codes_tbl`;
CREATE TABLE `country_codes_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `iso` char(2) NOT NULL,
  `name` varchar(80) NOT NULL,
  `nicename` varchar(80) NOT NULL,
  `iso3` char(3) DEFAULT NULL,
  `numcode` smallint(6) DEFAULT NULL,
  `phonecode` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/country_codes_tbl' AUTO_INCREMENT=240 DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/country_state_district' AUTO_INCREMENT=662 DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_assesment_children_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_assesment_languagetest_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_assesment_othertest_details_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_assesment_spouse_exp_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_assesment_spouse_languagetest_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_assesment_spouse_qualification' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_assesment_status_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_assessment_admission_test_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_assessment_langskills_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_assessment_migrate_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_assessment_other_skills_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_assessment_plustwo_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_assessment_relatives_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_assessment_spouse_details_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_assessment_sslc_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_assessment_study_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_assessment_technical_skills_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_assessment_teritary_qualification' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_assessment_training_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_assessment_work_exp_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_assessment_work_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_contact_numbers_tbl' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for enquiry_count
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_count`;
CREATE TABLE `enquiry_count` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) DEFAULT NULL,
  `count` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_count' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_datemodified_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_details' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_personal_details_tbl' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for enquiry_sources
-- ----------------------------
DROP TABLE IF EXISTS `enquiry_sources`;
CREATE TABLE `enquiry_sources` (
  `enquiry_source_id` int(11) NOT NULL AUTO_INCREMENT,
  `source_name` varchar(255) DEFAULT NULL,
  `source_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`enquiry_source_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/enquiry_sources' AUTO_INCREMENT=1016 DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/history_enquiry_assigning_tbl' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for log_table
-- ----------------------------
DROP TABLE IF EXISTS `log_table`;
CREATE TABLE `log_table` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `query` varchar(255) DEFAULT NULL,
  `operation_status` int(11) DEFAULT NULL,
  `system_name` varchar(255) DEFAULT NULL,
  `log_time` timestamp NULL DEFAULT NULL,
  `client_access` varchar(255) DEFAULT NULL,
  `table_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/log_table' AUTO_INCREMENT=277616 DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/login_auditor' AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/login_details_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/login_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/master_common_pool_config' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/master_counselor_limit' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for master_counselore_timelimit
-- ----------------------------
DROP TABLE IF EXISTS `master_counselore_timelimit`;
CREATE TABLE `master_counselore_timelimit` (
  `id` varchar(255) NOT NULL,
  `time_limit` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/master_counselore_timelimit' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for master_enquiry_status
-- ----------------------------
DROP TABLE IF EXISTS `master_enquiry_status`;
CREATE TABLE `master_enquiry_status` (
  `enq_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `enquiry_status` varchar(255) DEFAULT NULL,
  `feature` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`enq_status_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/master_enquiry_status' AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_admission_test
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_admission_test`;
CREATE TABLE `mastertbl_admission_test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admission_test` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_admission_test' AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_age
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_age`;
CREATE TABLE `mastertbl_age` (
  `age_id` varchar(255) NOT NULL,
  `age` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`age_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_age' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_app_status' AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_application_type
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_application_type`;
CREATE TABLE `mastertbl_application_type` (
  `app_type_id` varchar(100) NOT NULL,
  `application` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`app_type_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_application_type' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_batch_timing
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_batch_timing`;
CREATE TABLE `mastertbl_batch_timing` (
  `time_id` varchar(255) NOT NULL,
  `timing` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`time_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_batch_timing' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_country
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_country`;
CREATE TABLE `mastertbl_country` (
  `country_id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_country' AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_course_management
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_course_management`;
CREATE TABLE `mastertbl_course_management` (
  `course_id` varchar(255) NOT NULL,
  `course` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_course_management' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_course_type
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_course_type`;
CREATE TABLE `mastertbl_course_type` (
  `course_id` varchar(255) NOT NULL,
  `course_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_course_type' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_currency_list
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_currency_list`;
CREATE TABLE `mastertbl_currency_list` (
  `currency_id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(100) DEFAULT NULL,
  `currency` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`currency_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_currency_list' AUTO_INCREMENT=231 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_department
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_department`;
CREATE TABLE `mastertbl_department` (
  `department_id` varchar(255) NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_department' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_duration
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_duration`;
CREATE TABLE `mastertbl_duration` (
  `duration_id` int(11) NOT NULL AUTO_INCREMENT,
  `duration` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`duration_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_duration' AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_enquiry_methods
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_enquiry_methods`;
CREATE TABLE `mastertbl_enquiry_methods` (
  `enquiry_method_id` int(11) NOT NULL AUTO_INCREMENT,
  `method_name` varchar(255) DEFAULT NULL,
  `method_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`enquiry_method_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_enquiry_methods' AUTO_INCREMENT=1009 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_exam_board
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_exam_board`;
CREATE TABLE `mastertbl_exam_board` (
  `exam_board_id` varchar(255) NOT NULL,
  `exam_board` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`exam_board_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_exam_board' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_experience_duration
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_experience_duration`;
CREATE TABLE `mastertbl_experience_duration` (
  `experience_duration_id` varchar(255) NOT NULL,
  `duration` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`experience_duration_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_experience_duration' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_industry
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_industry`;
CREATE TABLE `mastertbl_industry` (
  `industry_id` varchar(100) NOT NULL DEFAULT '',
  `industry` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`industry_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_industry' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_institute
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_institute`;
CREATE TABLE `mastertbl_institute` (
  `institute_id` varchar(255) NOT NULL,
  `institute` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`institute_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_institute' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_intake
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_intake`;
CREATE TABLE `mastertbl_intake` (
  `intake_id` int(11) NOT NULL AUTO_INCREMENT,
  `intake` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`intake_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_intake' AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_jobtype
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_jobtype`;
CREATE TABLE `mastertbl_jobtype` (
  `jobtype_id` varchar(255) NOT NULL,
  `jobtype` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`jobtype_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_jobtype' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_language
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_language`;
CREATE TABLE `mastertbl_language` (
  `language_id` varchar(255) NOT NULL,
  `language` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`language_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_language' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_location
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_location`;
CREATE TABLE `mastertbl_location` (
  `location_id` varchar(100) NOT NULL DEFAULT '',
  `location` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_location' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_migration_category
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_migration_category`;
CREATE TABLE `mastertbl_migration_category` (
  `migrate_category_id` varchar(255) NOT NULL,
  `migrate_category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`migrate_category_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_migration_category' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_other_skills
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_other_skills`;
CREATE TABLE `mastertbl_other_skills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `skill` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_other_skills' AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_other_test
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_other_test`;
CREATE TABLE `mastertbl_other_test` (
  `other_test_id` varchar(255) NOT NULL,
  `other_test` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`other_test_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_other_test' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_profession
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_profession`;
CREATE TABLE `mastertbl_profession` (
  `proffesion_id` varchar(255) NOT NULL,
  `profession` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`proffesion_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_profession' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_program_field' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_program_level
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_program_level`;
CREATE TABLE `mastertbl_program_level` (
  `program_level_id` varchar(255) NOT NULL,
  `program_level` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`program_level_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_program_level' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_purpose
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_purpose`;
CREATE TABLE `mastertbl_purpose` (
  `purpose_id` varchar(255) NOT NULL,
  `purpose` varchar(255) DEFAULT NULL
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_purpose' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_qualification_duration
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_qualification_duration`;
CREATE TABLE `mastertbl_qualification_duration` (
  `duration_id` varchar(255) NOT NULL,
  `duration` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`duration_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_qualification_duration' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_salary
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_salary`;
CREATE TABLE `mastertbl_salary` (
  `salary_id` varchar(255) NOT NULL,
  `salary` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`salary_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_salary' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_score_management
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_score_management`;
CREATE TABLE `mastertbl_score_management` (
  `score_id` varchar(255) NOT NULL,
  `score` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`score_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_score_management' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_suggested_course' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_task_follow_status
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_task_follow_status`;
CREATE TABLE `mastertbl_task_follow_status` (
  `task_follow_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_follow_status` varchar(100) DEFAULT NULL,
  `enable` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`task_follow_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_task_follow_status' AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_technology
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_technology`;
CREATE TABLE `mastertbl_technology` (
  `tech_id` int(11) NOT NULL AUTO_INCREMENT,
  `technology` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tech_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_technology' AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_thread_delay
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_thread_delay`;
CREATE TABLE `mastertbl_thread_delay` (
  `thread_delay_id` int(11) NOT NULL,
  `thread_delay` int(11) NOT NULL,
  PRIMARY KEY (`thread_delay_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_thread_delay' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_timing
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_timing`;
CREATE TABLE `mastertbl_timing` (
  `timing_id` varchar(255) NOT NULL,
  `timing` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`timing_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_timing' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_training_program
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_training_program`;
CREATE TABLE `mastertbl_training_program` (
  `training_program_id` int(11) NOT NULL AUTO_INCREMENT,
  `program` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`training_program_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_training_program' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_university
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_university`;
CREATE TABLE `mastertbl_university` (
  `university_id` varchar(255) NOT NULL,
  `university` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`university_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_university' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for mastertbl_wrok_category
-- ----------------------------
DROP TABLE IF EXISTS `mastertbl_wrok_category`;
CREATE TABLE `mastertbl_wrok_category` (
  `work_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`work_category_id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/mastertbl_wrok_category' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for migration_country
-- ----------------------------
DROP TABLE IF EXISTS `migration_country`;
CREATE TABLE `migration_country` (
  `id` varchar(255) NOT NULL,
  `country` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/migration_country' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/newoffers_table' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/notice_table' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/privilage_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/reminder_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/resource_tbl' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/role_tbl' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for source_tbl
-- ----------------------------
DROP TABLE IF EXISTS `source_tbl`;
CREATE TABLE `source_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `source` varchar(30) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/source_tbl' AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for std_code_tbl
-- ----------------------------
DROP TABLE IF EXISTS `std_code_tbl`;
CREATE TABLE `std_code_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `place` varchar(50) DEFAULT NULL,
  `std_code` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/std_code_tbl' AUTO_INCREMENT=59 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for study_country
-- ----------------------------
DROP TABLE IF EXISTS `study_country`;
CREATE TABLE `study_country` (
  `id` varchar(255) NOT NULL,
  `country` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/study_country' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/study_work_migration_training_mstr_tbl' AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/task_details' DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/task_master' AUTO_INCREMENT=6233 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for training_course
-- ----------------------------
DROP TABLE IF EXISTS `training_course`;
CREATE TABLE `training_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/training_course' AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

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
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/user_message_tbl' DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for work_country
-- ----------------------------
DROP TABLE IF EXISTS `work_country`;
CREATE TABLE `work_country` (
  `id` varchar(255) NOT NULL,
  `country` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=FEDERATED CONNECTION='mysql://iamysqluser:root#123@45.120.188.39:3306/ia/work_country' DEFAULT CHARSET=latin1;
