-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.41-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema sims
--

CREATE DATABASE IF NOT EXISTS sims;
USE sims;

--
-- Definition of table `course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_id` int(10) unsigned NOT NULL auto_increment,
  `course_title` varchar(100) NOT NULL,
  `course_code` varchar(45) NOT NULL,
  `course_length` varchar(45) NOT NULL,
  `course_credit` varchar(45) NOT NULL,
  `course_summary` varchar(1000) NOT NULL,
  PRIMARY KEY  (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course`
--

/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` (`course_id`,`course_title`,`course_code`,`course_length`,`course_credit`,`course_summary`) VALUES 
 (1,'BTEC HND in Computing and Systems Development','Edexcel BTEC L- 5: MN179','2 Years','200','The BTEC (Business Technology Engineering Council) Higher National Diploma (HND) is a specialist programme with a strong work related emphasis. The qualification provides a thorough grounding in the key concepts and practical skills required in the sector with national recognition by employers allowing progression direct into employment or to degree.'),
 (3,'BTEC HND in Electrical and Electronic Engineering','Edexcel BTEC L- 5: MK177','2 Years','150','The BTEC (Business Technology Engineering Council) Higher National Diploma (HND) is a specialist programme with a strong work related emphasis. The qualification provides a thorough grounding in the key concepts and practical skills required in the sector with national recognition by employers allowing progression direct into employment or to degree.');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;


--
-- Definition of table `staff`
--

DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `staff_id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `father_name` varchar(45) NOT NULL,
  `mother_name` varchar(45) NOT NULL,
  `position` varchar(45) NOT NULL,
  `contact_no` varchar(45) NOT NULL,
  PRIMARY KEY  (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staff`
--

/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` (`staff_id`,`name`,`father_name`,`mother_name`,`position`,`contact_no`) VALUES 
 (1,'Mr. X','Mr. Z','Mrs. AAA','Manager','2343242'),
 (2,'Mr. Y','Mr. M','Mrs. BBB','Manager','2343242');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;


--
-- Definition of table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `student_id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `father_name` varchar(45) NOT NULL,
  `mother_name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `contact_no` varchar(45) NOT NULL,
  PRIMARY KEY  (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`student_id`,`name`,`father_name`,`mother_name`,`address`,`contact_no`) VALUES 
 (2,'Galib','Mr. Jamal','Mrs. yyy','Dhaka','3432432'),
 (4,'Mr.','B','cccc','Dhaka','4534'),
 (6,'cr','bbbb','cccc','ddd','123456'),
 (7,'Hasan','Mr R','Mrs. Y','Dhaka','432432'),
 (8,'AAAA','bbb','ccc','ddd','hhh'),
 (9,'aaa','aaa','aa','aa','aaa'),
 (10,'fdsf','dsf','dsf','dsf','dsf'),
 (11,'wwwww','dsf','dsf','dsf','dsf'),
 (13,'qq','qqq','qq','qqq','qq');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;


--
-- Definition of table `tutor`
--

DROP TABLE IF EXISTS `tutor`;
CREATE TABLE `tutor` (
  `tutor_id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `father_name` varchar(45) NOT NULL,
  `mother_name` varchar(45) NOT NULL,
  `subject` varchar(45) NOT NULL,
  `contact_no` varchar(45) NOT NULL,
  PRIMARY KEY  (`tutor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tutor`
--

/*!40000 ALTER TABLE `tutor` DISABLE KEYS */;
INSERT INTO `tutor` (`tutor_id`,`name`,`father_name`,`mother_name`,`subject`,`contact_no`) VALUES 
 (1,'Mr. Zaman','Abel Z','Sample','Math','3123123'),
 (2,'Mr. Hasan','Abedin','Sample','Math','3123123');
/*!40000 ALTER TABLE `tutor` ENABLE KEYS */;


--
-- Definition of table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` int(10) unsigned NOT NULL auto_increment,
  `role_id` int(10) unsigned NOT NULL,
  `uname` varchar(20) NOT NULL,
  `pass` varchar(45) NOT NULL,
  `isactive` int(10) unsigned NOT NULL default '1',
  PRIMARY KEY  (`user_id`),
  KEY `FK_user_info_role_id` (`role_id`),
  CONSTRAINT `FK_user_info_role_id` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_info`
--

/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` (`user_id`,`role_id`,`uname`,`pass`,`isactive`) VALUES 
 (1,1,'staff1','123456',1),
 (2,2,'student1','654321',1);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;


--
-- Definition of table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `role_id` int(10) unsigned NOT NULL auto_increment,
  `role_name` varchar(20) NOT NULL,
  PRIMARY KEY  (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_role`
--

/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`role_id`,`role_name`) VALUES 
 (1,'staff'),
 (2,'student');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
