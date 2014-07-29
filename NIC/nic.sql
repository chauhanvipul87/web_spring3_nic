-- MySQL dump 10.13  Distrib 5.5.28, for Win32 (x86)
--
-- Host: localhost    Database: nic
-- ------------------------------------------------------
-- Server version	5.5.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `nextid`
--

DROP TABLE IF EXISTS `nextid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nextid` (
  `next_id` int(11) NOT NULL AUTO_INCREMENT,
  `tablename` varchar(50) DEFAULT NULL,
  `fieldname` varchar(20) DEFAULT NULL,
  `nextnum` int(11) DEFAULT NULL,
  PRIMARY KEY (`next_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nextid`
--

LOCK TABLES `nextid` WRITE;
/*!40000 ALTER TABLE `nextid` DISABLE KEYS */;
INSERT INTO `nextid` VALUES (1,'userdetails','userdetails_id',1165);
/*!40000 ALTER TABLE `nextid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `policydetails`
--

DROP TABLE IF EXISTS `policydetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `policydetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `policy_no` varchar(40) DEFAULT NULL COMMENT 'latest new policy no',
  `amount` float DEFAULT NULL,
  `exp_date` varchar(20) DEFAULT NULL,
  `old_policy` varchar(40) DEFAULT NULL COMMENT 'old policy no',
  `status` varchar(20) DEFAULT NULL,
  `userdetails_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT '0',
  `modified_datetime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policydetails`
--

LOCK TABLES `policydetails` WRITE;
/*!40000 ALTER TABLE `policydetails` DISABLE KEYS */;
INSERT INTO `policydetails` VALUES (1,'2011/6700007909',419,'2013-03-28','2011/6700007909','pending',1147,1,0,'2013-02-23 10:56:04'),(2,'2011/6700007629',419,'2013-03-13','2011/6700007629','pending',1148,1,0,'2013-02-23 10:56:04'),(3,'2011/6700007866',1205,'2013-03-29','2011/6700007866','pending',1149,1,0,'2013-02-23 10:56:04'),(4,'2011/6200007869',833,'2013-03-25','2011/6200007869','pending',1150,1,0,'2013-02-23 10:56:04'),(5,'2011/6200007872',604,'2013-03-26','2011/6200007872','pending',1151,1,0,'2013-02-23 10:56:04'),(6,'2011/6200007871',582,'2013-03-26','2011/6200007871','pending',1152,1,0,'2013-02-23 10:56:04'),(7,'2011/6200007333',14102,'2013-03-05','2011/6200007333','renew',1153,1,0,'2013-02-23 10:56:04'),(8,'2011/6200007780',148,'2013-03-22','2011/6200007780','pending',1154,1,0,'2013-02-23 10:56:04'),(9,'2011/6200007545',916,'2013-03-11','2011/6200007545','pending',1155,1,0,'2013-02-23 10:56:04'),(10,'2011/6200007506',573,'2013-03-27','2011/6200007506','pending',1156,1,0,'2013-02-23 10:56:04'),(11,'2011/6300007858',3039,'2013-03-29','2011/6300007858','pending',1157,1,0,'2013-02-23 10:56:05'),(12,'2011/6100007814',4439,'2013-03-26','2011/6100007814','pending',1158,1,0,'2013-02-23 10:56:05'),(13,'2011/6200007625',663,'2013-03-17','2011/6200007625','pending',1159,1,0,'2013-02-23 10:56:05'),(14,'2011/6300007477',1175,'2013-03-08','2011/6300007477','pending',1160,1,0,'2013-02-23 10:56:05'),(15,'2011/6700007527',380,'2013-03-14','2011/6700007527','pending',1161,1,0,'2013-02-23 10:56:05'),(16,'2011/6200007464',475,'2013-03-17','2011/6200007464','pending',1162,1,0,'2013-02-23 10:56:05'),(17,'2013/6200007334',14105,'2014-03-05','2011/6200007333','renew',1153,1,0,'2013-02-01 10:30:25'),(18,'2014/6200007335',10000,'2015-03-05','2011/6200007333','pending',1153,1,0,'2014-02-01 10:32:56'),(19,'2014/6700007910',120,'2014-03-04','2014/6700007910','renew',1163,1,0,'2014-02-01 12:15:47'),(20,'2015/670000700',140,'2015-03-04','2014/6700007910','pending',1163,1,0,'2014-02-01 12:17:28'),(21,'2014/670000569',120,'2013-03-01','2014/670000569','renew',1164,1,0,'2012-03-01 12:25:46'),(22,'2014/670000570',260,'2012-03-01','2014/670000569','renew',1164,1,0,'2012-03-01 12:26:13'),(23,'2014/670000571',130,'2013-03-03','2014/670000569','renew',1164,1,0,'2012-03-01 12:27:34'),(24,'2014/670000580',500,'2014-03-14','2014/670000569','renew',1164,1,0,'2013-02-01 12:29:14'),(25,'2015/670000800',600,'2015-02-06','2014/670000569','pending',1164,1,0,'2014-02-01 12:30:45');
/*!40000 ALTER TABLE `policydetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `policytype`
--

DROP TABLE IF EXISTS `policytype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `policytype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `policy_type` varchar(20) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policytype`
--

LOCK TABLES `policytype` WRITE;
/*!40000 ALTER TABLE `policytype` DISABLE KEYS */;
INSERT INTO `policytype` VALUES (1,'Mediclaim',0),(2,'Personal Accident',0),(3,'Fire',0),(4,'Vehicle',0);
/*!40000 ALTER TABLE `policytype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_login`
--

DROP TABLE IF EXISTS `user_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) DEFAULT NULL,
  `user_password` varchar(100) DEFAULT NULL,
  `usertype_id` int(11) DEFAULT '2',
  `fname` varchar(40) DEFAULT NULL,
  `lname` varchar(40) DEFAULT NULL,
  `address` varchar(600) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `birthdate` varchar(20) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `doj` varchar(20) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT '0',
  `modified_datetime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_login`
--

LOCK TABLES `user_login` WRITE;
/*!40000 ALTER TABLE `user_login` DISABLE KEYS */;
INSERT INTO `user_login` VALUES (1,'admin','*4ACFE3202A5FF5CF467898FC58AAB1D615029441',1,'Hashmukh','Patell','Radha Raman Soc','9898475818','1978-10-05','h_05@gmail.com','',0,'2012-11-11 18:33:31'),(2,'vipul','*4FED50C767ABBD98BB0C6B42533091D4476DA1F6',2,'Vipul','Chauhan','Morar Baug Society','7600722172','','chauhanvipul87@gmail.com','',0,'2012-11-11 18:33:31'),(3,'kush','*4B729A5DFC1DD63177F9FE38D0B9C1EBDFB4B5A5',1,'Kush','Darji','Vadodara','7891234578','2012-11-08','kush@yahoo.com','2012-11-13',0,'12112012 1448'),(4,'Vinay','*23AE809DDACAF96AF0FD78ED04B6A265E05AA257',2,'Vinay','Soni','Ram Park society,\r\nNear Vastalya Hostpital\r\nPadra','7897895623','2012-11-05','vinay@gmail.com','2012-11-29',0,'12112012 1454'),(5,'Rahul','*266D4A68801B4CF67C2F24EEECEB43D67CFE4FF2',1,'Rahul','Chauhan','Vadodara','7600451285','','chauhanrahul@yahoo.com','',0,'15112012 1051'),(6,'ajay','*BC108C851D5302A3944BBAB99E65DB732D27CF22',2,'Ajay','Chauhan','Padra','7600722172','','jvipulchauhan87@gmail.com','',0,'09122012 1303'),(7,'test','*94BDCEBE19083CE2A1F959FD02F964C7AF4CFC29',1,'vip','vi','vi','1212121212','2013-01-02','','2013-01-22',1,'05012013 1435');
/*!40000 ALTER TABLE `user_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userdetails`
--

DROP TABLE IF EXISTS `userdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userdetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userdetails_id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `address` varchar(300) DEFAULT NULL,
  `city` varchar(40) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `sheet_name` varchar(20) DEFAULT NULL,
  `policy_type` varchar(20) DEFAULT 'Mediclaim',
  `vehicle_no` varchar(40) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT '0',
  `modified_datetime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userdetails`
--

LOCK TABLES `userdetails` WRITE;
/*!40000 ALTER TABLE `userdetails` DISABLE KEYS */;
INSERT INTO `userdetails` VALUES (1,1147,'Narendra Prabhakar Chauhan','Jain Irrigation','Baroda','',NULL,'Mar2012','4','GJ-06-AB-3984',0,'2013-02-23 10:56:04'),(2,1148,'Divyesh Sharadchandra Shah','Navgari','Padra','',NULL,'Mar2012','4','GJ-06-AS-7859',0,'2013-02-23 10:56:04'),(3,1149,'Amit Mohan Patel','Malek Ranu','Ranu','',NULL,'Mar2012','4','GJ-17-C-4802',0,'2013-02-23 10:56:04'),(4,1150,'Mukeshbhai Vrajlal Thakkar','102,Miraj Tower,Mujmahuda,','Baroda','9925003089',NULL,'Mar2012','4','GJ-06-EC-1897',0,'2013-02-23 10:56:04'),(5,1151,'Bharatkumar Ambalal Patel','Samaspura,','Baroda','9909666439',NULL,'Mar2012','4','GJ-06-CK-2285',0,'2013-02-23 10:56:04'),(6,1152,'Gauravkumar Maheshchandra Parekh','96,Lakulesh Soc,Ghayaj,','Ghayaj','9825617878',NULL,'Mar2012','4','GJ-06-CR-1905',0,'2013-02-23 10:56:04'),(7,1153,'Kishor Udesing Sisodiya','C-37,Parul Soc,Zaver Nagar,','Baroda','',NULL,'Mar2012','4','GJ-06-CQ-8510',0,'2013-02-23 10:56:04'),(8,1154,'Daxaben Mukeshbhai Mistry','20,Kailash Soc,','Padra','',NULL,'Mar2012','4','GJ-06-DN-7491',0,'2013-02-23 10:56:04'),(9,1155,'Vishal Harshadbhai Patel','Matawali Khadki,Ghayaj','Ghayaj','',NULL,'Mar2012','4','GJ-06-FC-1506',0,'2013-02-23 10:56:04'),(10,1156,'Bharatbhai Sivabhai Rathod','A/14,Swami Narayan Duplex,','Baroda','',NULL,'Mar2012','4','GJ-06-CN-2908',0,'2013-02-23 10:56:04'),(11,1157,'Manharbhai M  Kachhiya','B-70,Vaikunthdham Soc,','Tarsali','',NULL,'Mar2012','4','GJ-06-UU-2694',0,'2013-02-23 10:56:04'),(12,1158,'Jayantibhai Fulabhai Gohil','','','',NULL,'Mar2012','4','GJ-06-AB-9884',0,'2013-02-23 10:56:05'),(13,1159,'Kanubhai Authorbhai Patel','Matawali Khadki,Ghayaj','Ghayaj','',NULL,'Mar2012','4','GJ-06-DM-8668',0,'2013-02-23 10:56:05'),(14,1160,'Dilipkumar Sankarbhai Patel','Navi Pansarawad,Navapura,','Padra','',NULL,'Mar2012','4','GJ-06-X-2825',0,'2013-02-23 10:56:05'),(15,1161,'Jitendra S Patel','Masar','Padra','',NULL,'Mar2012','4','GJ-06-R-4227',0,'2013-02-23 10:56:05'),(16,1162,'Ambalal Chhotabhai Patel','7,Maharaja So,','Padra','9277859482',NULL,'Mar2012','4','GJ-06-AG-3733',0,'2013-02-23 10:56:05'),(17,1163,'Johnson','ranu','vadodara','9898787485','chauhanvipul87@gmail.com',NULL,'1',NULL,0,'2014-02-01 12:15:47'),(18,1164,'Johnson ','ASDFASDF','indore, mp','9898787485','sdf@INDIA.COM',NULL,'1',NULL,0,'2012-03-01 12:25:46');
/*!40000 ALTER TABLE `userdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertype`
--

DROP TABLE IF EXISTS `usertype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usertype` (
  `usertype_id` int(11) NOT NULL AUTO_INCREMENT,
  `usertype` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`usertype_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertype`
--

LOCK TABLES `usertype` WRITE;
/*!40000 ALTER TABLE `usertype` DISABLE KEYS */;
INSERT INTO `usertype` VALUES (1,'admin'),(2,'staff');
/*!40000 ALTER TABLE `usertype` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-27 17:17:35
