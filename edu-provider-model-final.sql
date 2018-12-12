CREATE TABLE `edu_mastr_college_campuses` (
`campus_id` int(11) NOT NULL,
`college_id` int(11) NULL DEFAULT NULL,
`campus_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`country` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`location` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`contact_address` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
PRIMARY KEY (`campus_id`) ,
INDEX `fk_key_campuses` (`college_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `edu_mastr_college_contacts` (
`college_contact_id` int(11) NOT NULL,
`college_id` int(11) NULL DEFAULT NULL,
`contact_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`department` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`phone_no` longtext CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL,
`email_d` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
PRIMARY KEY (`college_contact_id`) ,
INDEX `fk_key_college_contacts` (`college_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `edu_mastr_college_courses` (
`college_course_id` int(11) NOT NULL,
`college_id` int(11) NULL DEFAULT NULL,
`course_id` int(11) NULL DEFAULT NULL,
PRIMARY KEY (`college_course_id`) ,
INDEX `fk_key1_college_course` (`college_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `edu_mastr_college_courses_offered` (
`course_id` int(11) NOT NULL,
`university_id` int(11) NULL DEFAULT NULL,
`program_level` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`program_field` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`duration` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`college_id` int(11) NULL DEFAULT NULL,
PRIMARY KEY (`course_id`) ,
INDEX `fk_key_colege_corse_ofered` (`college_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `edu_mastr_college_processing_fee` (
`processing_fee_id` int(11) NOT NULL,
`college_id` int(11) NULL DEFAULT NULL,
`processing_fee` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`currency` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`amount` double NULL DEFAULT NULL,
PRIMARY KEY (`processing_fee_id`) ,
INDEX `fk_key_pr_fee` (`college_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `edu_mastr_cours_academic_reqmt` (
`academic_reqmt_id` int(11) NOT NULL,
`course_id` int(11) NULL DEFAULT NULL,
`level` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`field` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`duration` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
PRIMARY KEY (`academic_reqmt_id`) ,
INDEX `fk_key5_aca_req` (`course_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `edu_mastr_cours_experience_reqmt` (
`exp_reqmt_id` int(11) NOT NULL,
`course_id` int(11) NULL DEFAULT NULL,
`experience_level` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`experience_field` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`experince_duration` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
PRIMARY KEY (`exp_reqmt_id`) ,
INDEX `fk_key3_exp_corse` (`course_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `edu_mastr_cours_languagetest` (
`language_reqmt_id` int(11) NOT NULL,
`course_id` int(11) NULL DEFAULT NULL,
`language_test` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`reading` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`listening` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`writing` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`speaking` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`over_all` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
PRIMARY KEY (`language_reqmt_id`) ,
INDEX `fk_key_coursentry_languagetest1` (`course_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `edu_mastr_course_basics` (
`course__id` int(11) NULL DEFAULT NULL,
`college_id` int(11) NULL DEFAULT NULL,
`college_course_id` int(11) NULL DEFAULT NULL,
`sex` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`age_from` int(11) NULL DEFAULT NULL,
`age_to` int(11) NULL DEFAULT NULL,
`marital_status` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`intake` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`careeer` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`advantage` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`row_id` int(11) NOT NULL,
PRIMARY KEY (`row_id`) ,
INDEX `fk_key2_course_id` (`course__id`),
INDEX `fk_key1_courses_1` (`college_course_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `edu_mastr_course_documents` (
`document_id` int(11) NOT NULL,
`course_id` int(11) NULL DEFAULT NULL,
`document_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
PRIMARY KEY (`document_id`) ,
INDEX `fk_master_coursentry_documents_master_college_courses_offered_1` (`course_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `edu_mastr_course_fee` (
`course_fee_id` int(11) NOT NULL,
`course_id` int(11) NULL DEFAULT NULL,
`application_fee_currency` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`application_fee` double NULL DEFAULT NULL,
`annual_fee_currency` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`annual_fee` double NULL DEFAULT NULL,
`semester_fee_currency` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`semester_fee` double NULL DEFAULT NULL,
`other_fee_currency` double NULL DEFAULT NULL,
`other_fee` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`currency` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
PRIMARY KEY (`course_fee_id`) ,
INDEX `fk_master_coursentry_fee_master_college_courses_offered_1` (`course_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `edu_mastr_course_other_reqmts` (
`course_other_reqmt_id` int(11) NULL DEFAULT NULL,
`course_id` int(11) NULL DEFAULT NULL,
`course_other_reqmt` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`other_reqmt_details` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
INDEX `fk_key66_other_req` (`course_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `edu_mastr_coursentry_admissiontest` (
`admissiontest_reqmt_id` int(11) NOT NULL,
`course_id` int(11) NULL DEFAULT NULL,
`admission_test` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`reading` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`listening` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`writing` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`speaking` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
PRIMARY KEY (`admissiontest_reqmt_id`) ,
INDEX `fk_key4_admissiontest` (`course_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `edu_mastr_english_reqmt` (
`medium_id` int(11) NOT NULL,
`course_id` int(11) NULL DEFAULT NULL,
`board` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`duration` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
PRIMARY KEY (`medium_id`) ,
INDEX `fk_key6_eng_req` (`course_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `edu_mastr_providers` (
`college_id` int(11) NOT NULL,
`college_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`university_id` int(11) NULL DEFAULT NULL,
`description` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`remarks` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`website` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`college_address` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`commission` int(11) NULL DEFAULT NULL,
`currency` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`institution_type` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`agency_status` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`tieup_name` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`contract_from` date NULL DEFAULT NULL,
`contract_to` date NULL DEFAULT NULL,
`contract_by` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`renewal_by` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`created_date` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`updated_date` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`created_user` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`updated_user` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
PRIMARY KEY (`college_id`) 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `edu_mastr_university_courses_offered` (
`course_id` int(11) NOT NULL,
`university_id` int(11) NULL DEFAULT NULL,
`program_level` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`program_field` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
`duration` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
PRIMARY KEY (`course_id`) ,
INDEX `fk_master_university_1` (`university_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `master_university` (
`university_id` int(11) NOT NULL,
`university_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
PRIMARY KEY (`university_id`) 
)
AUTO_INCREMENT=1;


ALTER TABLE `edu_mastr_college_campuses` ADD CONSTRAINT `fk_key_campuses` FOREIGN KEY (`college_id`) REFERENCES `edu_mastr_providers` (`college_id`);
ALTER TABLE `edu_mastr_college_contacts` ADD CONSTRAINT `fk_key_college_contacts` FOREIGN KEY (`college_id`) REFERENCES `edu_mastr_providers` (`college_id`);
ALTER TABLE `edu_mastr_college_courses_offered` ADD CONSTRAINT `fk_key_colege_corse_ofered` FOREIGN KEY (`college_id`) REFERENCES `edu_mastr_providers` (`college_id`);
ALTER TABLE `edu_mastr_college_processing_fee` ADD CONSTRAINT `fk_key_pr_fee` FOREIGN KEY (`college_id`) REFERENCES `edu_mastr_providers` (`college_id`);
ALTER TABLE `edu_mastr_cours_academic_reqmt` ADD CONSTRAINT `fk_key5_aca_req` FOREIGN KEY (`course_id`) REFERENCES `edu_mastr_college_courses_offered` (`course_id`);
ALTER TABLE `edu_mastr_cours_experience_reqmt` ADD CONSTRAINT `fk_key3_exp_corse` FOREIGN KEY (`course_id`) REFERENCES `edu_mastr_college_courses_offered` (`course_id`);
ALTER TABLE `edu_mastr_cours_languagetest` ADD CONSTRAINT `fk_key_coursentry_languagetest1` FOREIGN KEY (`course_id`) REFERENCES `edu_mastr_college_courses_offered` (`course_id`);
ALTER TABLE `edu_mastr_course_basics` ADD CONSTRAINT `fk_key2_course_id` FOREIGN KEY (`course__id`) REFERENCES `edu_mastr_college_courses_offered` (`course_id`);
ALTER TABLE `edu_mastr_course_documents` ADD CONSTRAINT `fk_master_coursentry_documents_master_college_courses_offered_1` FOREIGN KEY (`course_id`) REFERENCES `edu_mastr_college_courses_offered` (`course_id`);
ALTER TABLE `edu_mastr_course_fee` ADD CONSTRAINT `fk_master_coursentry_fee_master_college_courses_offered_1` FOREIGN KEY (`course_id`) REFERENCES `edu_mastr_college_courses_offered` (`course_id`);
ALTER TABLE `edu_mastr_course_other_reqmts` ADD CONSTRAINT `fk_key66_other_req` FOREIGN KEY (`course_id`) REFERENCES `edu_mastr_college_courses_offered` (`course_id`);
ALTER TABLE `edu_mastr_coursentry_admissiontest` ADD CONSTRAINT `fk_key4_admissiontest` FOREIGN KEY (`course_id`) REFERENCES `edu_mastr_college_courses_offered` (`course_id`);
ALTER TABLE `edu_mastr_english_reqmt` ADD CONSTRAINT `fk_key6_eng_req` FOREIGN KEY (`course_id`) REFERENCES `edu_mastr_college_courses_offered` (`course_id`);
ALTER TABLE `edu_mastr_university_courses_offered` ADD CONSTRAINT `fk_master_university_1` FOREIGN KEY (`university_id`) REFERENCES `master_university` (`university_id`);

