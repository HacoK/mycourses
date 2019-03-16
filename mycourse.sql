-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mycourses
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `assignment`
--

DROP TABLE IF EXISTS `assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `assignment` (
  `assignment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `attachment` bit(1) DEFAULT NULL,
  `content` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `curriculum_id` bigint(20) NOT NULL,
  `deadline` datetime NOT NULL,
  `root_dir` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `startline` datetime NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `unit` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`assignment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment`
--

LOCK TABLES `assignment` WRITE;
/*!40000 ALTER TABLE `assignment` DISABLE KEYS */;
INSERT INTO `assignment` VALUES (1,'','特ed',4,'2019-03-13 00:00:00','D:/mycourses/storage/assignments/1/',1024,'2019-03-11 00:00:00','test','word|pdf','KB'),(2,'\0','1\n2\n3',4,'2019-03-13 00:00:00','D:/mycourses/storage/assignments/2/',0,'2019-03-11 00:00:00','another','zip','KB'),(3,'','塔尔',4,'2019-03-13 00:00:00','D:/mycourses/storage/assignments/3/',1,'2019-03-01 00:00:00','dwa','','MB'),(4,'','undefined',4,'2019-03-30 00:00:00','D:/mycourses/storage/assignments/4/',500,'2019-03-27 00:00:00','sad','','KB'),(5,'','内容',4,'2019-03-01 00:00:00','D:/mycourses/storage/assignments/5/',888,'2019-03-01 00:00:00','标题','word|pdf|ppt|md|excel|zip','KB'),(6,'\0','homework',4,'2019-03-20 00:02:03','D:/mycourses/storage/assignments/6/',100,'2019-03-11 00:02:02','homework','word|pdf','KB');
/*!40000 ALTER TABLE `assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `course` (
  `course_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `approved` int(11) NOT NULL,
  `course_name` varchar(255) NOT NULL,
  `courseware` varchar(255) NOT NULL,
  `description` longtext NOT NULL,
  `teacher_id` bigint(20) NOT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,1,'z','D:/mycourses/storage/coursewares/1/','zz\nzzz\nzzzz',1),(2,1,'s','D:/mycourses/storage/coursewares/2/','ss\nsss\nssss',1),(3,1,'范','D:/mycourses/storage/coursewares/3/','无',1),(4,1,'示例','D:/mycourses/storage/coursewares/4/','示例描述',1),(5,1,'another','D:/mycourses/storage/coursewares/5/','随便神马，啦啦啦啦啦',1),(6,-1,'课程1','D:/mycourses/storage/coursewares/6/','111111',1),(7,1,'课程2','D:/mycourses/storage/coursewares/7/','222222',1),(8,1,'课程3','D:/mycourses/storage/coursewares/8/','3333333',1),(9,1,'课程4','D:/mycourses/storage/coursewares/9/','444444444444',1),(10,-1,'课程5','D:/mycourses/storage/coursewares/10/','5555555555555',1),(11,0,'课程6','D:/mycourses/storage/coursewares/11/','66666666666666',1),(12,-1,'课程7','D:/mycourses/storage/coursewares/12/','777777777777',1),(13,1,'课程8','D:/mycourses/storage/coursewares/13/','888888888888',1),(14,1,'课程9','D:/mycourses/storage/coursewares/14/','9999999999',1),(15,1,'postgraduate','D:/mycourses/storage/coursewares/15/','研究生课程',9),(16,1,'J2EE','D:/mycourses/storage/coursewares/16/','J2EE与中间件',11);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cselec_rec`
--

DROP TABLE IF EXISTS `cselec_rec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cselec_rec` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `approved` int(11) NOT NULL,
  `curriculum_id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL,
  `withdraw` bit(1) NOT NULL,
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cselec_rec`
--

LOCK TABLES `cselec_rec` WRITE;
/*!40000 ALTER TABLE `cselec_rec` DISABLE KEYS */;
INSERT INTO `cselec_rec` VALUES (1,1,7,2,'\0'),(2,-1,2,2,'\0'),(3,-1,6,2,''),(4,1,2,2,'\0'),(5,1,4,2,'\0'),(7,1,4,6,'\0'),(8,-1,5,2,''),(9,-1,9,2,''),(10,-1,6,2,''),(11,-1,5,2,''),(12,-1,9,2,''),(13,0,6,2,'\0'),(14,0,5,2,'\0'),(15,-1,9,2,''),(16,-1,6,5,''),(17,-1,4,5,''),(18,1,2,6,'\0'),(19,-1,13,2,'\0'),(20,-1,13,5,'\0'),(21,1,13,6,'\0');
/*!40000 ALTER TABLE `cselec_rec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculum`
--

DROP TABLE IF EXISTS `curriculum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `curriculum` (
  `curriculum_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `approved` int(11) NOT NULL,
  `course_id` bigint(20) NOT NULL,
  `restriction` int(11) NOT NULL,
  `schedule` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `semester_season` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `semester_year` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `typest` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`curriculum_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum`
--

LOCK TABLES `curriculum` WRITE;
/*!40000 ALTER TABLE `curriculum` DISABLE KEYS */;
INSERT INTO `curriculum` VALUES (1,2,2,8,'周一 第2节\n周二 第3节\n周三 第4节\n周四 第5节\n周五 第4节\n周六 第3节\n周日 第2节','spring','2019','Undergraduate'),(2,3,1,8,'周一 第12节\n周二 第11节\n周三 第10节\n周四 第9节\n周五 第10节\n周六 第11节\n周日 第12节','spring','2019','Undergraduate'),(3,-1,5,22,'周一 第1,2,3,4节\n周五 第5,6,7节','autumn','2019','Undergraduate'),(4,3,4,55,'周一 第3,4节\n周三 第5,6节\n周五 第1,2节','autumn','2019','Undergraduate'),(5,1,5,88,'周一 第3,4节\n周五 第3,4节','spring','2020','Undergraduate'),(6,1,13,1,'周一 第1,2节\n周四 第1,2节','autumn','2019','Undergraduate'),(7,2,5,8,'周三 第5,6,7节\n周五 第3,4节','spring','2019','Undergraduate'),(8,0,3,1,'周二 第3,4节\n周四 第3,4节','autumn','2021','Undergraduate'),(9,1,4,1,'周二 第3,4节\n周四 第5,6,7节','spring','2020','Undergraduate'),(10,1,3,10,'周一 第3,4节\n周五 第3,4节','spring','2021','Doctor'),(11,1,15,10,'周六 第1,2,3,4节\n周日 第1,2,3,4节','spring','2019','Postgraduate'),(12,-1,16,8,'周三 第3,4节\n周五 第3,4节','spring','2019','Undergraduate'),(13,3,16,1,'周三 第3,4节\n周五 第3,4节','spring','2019','Undergraduate');
/*!40000 ALTER TABLE `curriculum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forum_reply`
--

DROP TABLE IF EXISTS `forum_reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `forum_reply` (
  `reply_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` longtext COLLATE utf8mb4_bin NOT NULL,
  `release_time` datetime NOT NULL,
  `reply_num` int(11) NOT NULL,
  `topic_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`reply_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forum_reply`
--

LOCK TABLES `forum_reply` WRITE;
/*!40000 ALTER TABLE `forum_reply` DISABLE KEYS */;
INSERT INTO `forum_reply` VALUES (1,'青蛙打网球·','2019-03-05 23:10:59',1,9,1),(2,'亲卫队请问','2019-03-05 23:13:25',2,9,1),(3,'hhhhhhhhh\nhhhhhhhhh\nhhhhhhhhh','2019-03-06 09:57:48',3,9,2),(4,'test','2019-03-12 14:29:16',1,41,2);
/*!40000 ALTER TABLE `forum_reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forum_topic`
--

DROP TABLE IF EXISTS `forum_topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `forum_topic` (
  `topic_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course_id` varchar(255) NOT NULL,
  `description` longtext NOT NULL,
  `release_time` datetime NOT NULL,
  `topic` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forum_topic`
--

LOCK TABLES `forum_topic` WRITE;
/*!40000 ALTER TABLE `forum_topic` DISABLE KEYS */;
INSERT INTO `forum_topic` VALUES (1,'4','test','2019-03-05 00:00:00','test','1'),(2,'4','zzzzzzzzzzzzzz','2019-03-05 00:00:00','zzz','1'),(3,'4','check','2019-03-05 00:00:00','check','1'),(4,'4','1111111\n1111111111111111\n111111111111111111111111','2019-03-05 00:00:00','11111','1'),(5,'4','22222222222222','2019-03-05 00:00:00','222','1'),(6,'4','44444444444\n44444\n444\n4','2019-03-05 00:00:00','444','1'),(7,'4','。。。\n。。。。。。\n。。。。。。。。。','2019-03-05 20:12:54','XXX','1'),(8,'4','洒下阿瑟东','2019-03-05 23:10:31','sa\'sa','1'),(9,'4','11111','2019-03-05 23:10:44','我','1'),(10,'4','zzzzzzzzzzzz','2019-03-06 09:58:05','zz','2'),(11,'15','1','2019-03-11 20:46:22','1','9'),(12,'15','2','2019-03-11 20:46:26','2','9'),(13,'15','3','2019-03-11 20:46:31','3','9'),(14,'15','4','2019-03-11 20:46:38','4','9'),(15,'15','5','2019-03-11 20:46:42','5','9'),(16,'15','6','2019-03-11 20:46:47','6','9'),(17,'15','7','2019-03-11 20:46:51','7','9'),(18,'15','8','2019-03-11 20:46:56','8','9'),(19,'15','9','2019-03-11 20:47:00','9','9'),(20,'15','10','2019-03-11 20:47:10','10','9'),(21,'15','11','2019-03-11 20:47:14','11','9'),(22,'15','12','2019-03-11 20:47:19','12','9'),(23,'15','13','2019-03-11 20:47:24','13','9'),(24,'15','14','2019-03-11 20:47:29','14','9'),(25,'15','15','2019-03-11 20:47:34','15','9'),(26,'15','16','2019-03-11 20:47:39','16','9'),(27,'15','17','2019-03-11 20:47:48','17','9'),(28,'15','18','2019-03-11 20:47:54','18','9'),(29,'15','19','2019-03-11 20:47:59','19','9'),(30,'15','20','2019-03-11 20:48:06','20','9'),(31,'15','21','2019-03-11 20:48:11','21','9'),(32,'15','22','2019-03-11 20:48:15','22','9'),(33,'15','23','2019-03-11 20:48:22','23','9'),(34,'15','24','2019-03-11 20:50:49','24','9'),(35,'15','25','2019-03-11 20:50:54','25','9'),(36,'4','emmm','2019-03-11 20:52:07','emmm','5'),(37,'4','......','2019-03-11 20:52:20','.......','5'),(38,'1','3','2019-03-11 20:53:07','3','6'),(39,'1','2','2019-03-11 20:53:12','2','6'),(40,'1','1','2019-03-11 20:53:16','1','6'),(41,'4','J2EE\n与中间件','2019-03-12 14:25:19','J2EE','1');
/*!40000 ALTER TABLE `forum_topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (4);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `score` (
  `score_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `curriculum_id` bigint(20) NOT NULL,
  `excel_path` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `score_type` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`score_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES (1,4,'D:/mycourses/storage/scoreExcels/1/test.xlsx','Publish','test'),(2,4,'D:/mycourses/storage/scoreExcels/2/test.xlsx','Private','another'),(3,4,'D:/mycourses/storage/scoreExcels/3/test.xlsx','Publish','成绩');
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `st_info`
--

DROP TABLE IF EXISTS `st_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `st_info` (
  `user_id` bigint(20) NOT NULL,
  `student_id` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `typest` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_161yajy5moyiae57txg3urkk` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `st_info`
--

LOCK TABLES `st_info` WRITE;
/*!40000 ALTER TABLE `st_info` DISABLE KEYS */;
INSERT INTO `st_info` VALUES (2,'161250098','Undergraduate'),(5,'161250000','Undergraduate'),(6,'161250001','Undergraduate'),(7,'M10080','Postgraduate'),(8,'D1998','Doctor'),(10,'M10000','Postgraduate');
/*!40000 ALTER TABLE `st_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_lqjrcobrh9jc8wpcar64q1bfh` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'','haco@nju.edu.cn','hacok','Teacher','HacoK'),(2,'','161250098@smail.nju.edu.cn','haco','Student','haco'),(4,'','admin@nju.edu.cn','admin','Administrator','admin'),(5,'','877728156@qq.com','zero','Student','zero'),(6,'','1269897230@qq.com','zack','Student','Zack'),(7,'','postgraduate@smail.nju.edu.cn','post','Student','post'),(8,'','doctor@smail.nju.edu.cn','doctor','Student','doctor'),(9,'','TC@nju.edu.cn','tc','Teacher','tc'),(10,'\0','graduate@smail.nju.edu.cn','graduate','Student','graduate'),(11,'','wwhaor@nju.edu.cn','whaor','Teacher','whaor');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-15 12:15:43
