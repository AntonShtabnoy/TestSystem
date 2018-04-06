CREATE DATABASE  IF NOT EXISTS `test_system` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `test_system`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: test_system
-- ------------------------------------------------------
-- Server version	5.5.59

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
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `answerId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(400) DEFAULT NULL,
  `isCorrect` bit(1) NOT NULL DEFAULT b'0',
  `questionId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`answerId`),
  UNIQUE KEY `answerId_UNIQUE` (`answerId`),
  KEY `questionId_idx` (`questionId`),
  CONSTRAINT `question_id` FOREIGN KEY (`questionId`) REFERENCES `question` (`questionId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1,'C','\0',5),(2,'B','\0',5),(3,'A','',5),(13,'extends','',14),(14,'don\'t know','\0',14),(15,'callable','',14),(16,'opera','\0',15),(17,'mozila','\0',15),(18,'google','',15);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `link`
--

DROP TABLE IF EXISTS `link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `link` (
  `linkId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `link` varchar(400) DEFAULT NULL,
  `literatureId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`linkId`),
  UNIQUE KEY `linkId_UNIQUE` (`linkId`),
  KEY `literatureId_idx` (`literatureId`),
  CONSTRAINT `literatureId` FOREIGN KEY (`literatureId`) REFERENCES `literature` (`literatureId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `link`
--

LOCK TABLES `link` WRITE;
/*!40000 ALTER TABLE `link` DISABLE KEYS */;
INSERT INTO `link` VALUES (1,'Thread links',2),(2,'kjh',3);
/*!40000 ALTER TABLE `link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `literature`
--

DROP TABLE IF EXISTS `literature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `literature` (
  `literatureId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(400) CHARACTER SET latin1 DEFAULT NULL,
  `questionId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`literatureId`),
  UNIQUE KEY `literatureId_UNIQUE` (`literatureId`),
  KEY `question_Id_idx` (`questionId`),
  CONSTRAINT `id_question` FOREIGN KEY (`questionId`) REFERENCES `question` (`questionId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `literature`
--

LOCK TABLES `literature` WRITE;
/*!40000 ALTER TABLE `literature` DISABLE KEYS */;
INSERT INTO `literature` VALUES (2,'Thread book',14),(3,'lkjh',15);
/*!40000 ALTER TABLE `literature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `questionId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(400) CHARACTER SET latin1 NOT NULL,
  `testId` int(10) unsigned NOT NULL,
  `isDeleted` bit(1) NOT NULL,
  PRIMARY KEY (`questionId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (5,' How',1,'\0'),(14,' How does work Thread?',1,'\0'),(15,' How does Internet work?',1,'\0');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `roleId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_TUTOR'),(3,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statistic`
--

DROP TABLE IF EXISTS `statistic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `statistic` (
  `staticticId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `isCorrect` bit(1) NOT NULL DEFAULT b'0',
  `questionId` int(10) unsigned NOT NULL,
  `userId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`staticticId`),
  KEY `userId_idx` (`userId`),
  KEY `questionId_idx` (`questionId`),
  CONSTRAINT `questionId` FOREIGN KEY (`questionId`) REFERENCES `question` (`questionId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statistic`
--

LOCK TABLES `statistic` WRITE;
/*!40000 ALTER TABLE `statistic` DISABLE KEYS */;
INSERT INTO `statistic` VALUES (26,'2018-04-06 11:00:21','\0',14,2),(27,'2018-04-06 11:00:21','',5,2),(28,'2018-04-05 22:45:39','',5,2),(29,'2018-04-05 23:06:05','',5,2),(30,'2018-04-05 23:11:25','',5,2);
/*!40000 ALTER TABLE `statistic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `testId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(400) DEFAULT NULL,
  `topicId` int(10) unsigned NOT NULL,
  `isDeleted` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`testId`),
  UNIQUE KEY `testId_UNIQUE` (`testId`),
  KEY `topicId_idx` (`topicId`),
  CONSTRAINT `topicId` FOREIGN KEY (`topicId`) REFERENCES `topic` (`topicId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (1,'Collections','Test about Collections.',1,'\0'),(2,'POSIX','POSIX',2,'\0');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `topicId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `name` varchar(255) CHARACTER SET latin1 NOT NULL,
  `isDeleted` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`topicId`),
  UNIQUE KEY `topicId_UNIQUE` (`topicId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (1,'Test about Java.','Java','\0'),(2,'Tests about c++.','C++','\0');
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) CHARACTER SET latin1 NOT NULL,
  `lastName` varchar(255) CHARACTER SET latin1 NOT NULL,
  `login` varchar(255) CHARACTER SET latin1 NOT NULL,
  `password` varchar(255) CHARACTER SET latin1 NOT NULL,
  `roleId` int(10) unsigned NOT NULL,
  `isDeleted` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `roleId_idx` (`roleId`),
  CONSTRAINT `roleId` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Anton','Shtabnoy','admin','$2a$10$b7ETijrdO6hkYSTZmgjBiuPLuJQYn4kwxKZelyQadeQWGKe0BwCNW',1,'\0'),(2,'Maxim','Zhykovsku','user','$2a$10$uAUdbabp1tIv8KyvpwF0P.Yb/Py4xb/TeEpaAGu2YNqOxdkIjjdT.',3,'\0'),(8,'Vika','Azarenka','Aza','$2a$10$EQqeRlQcahXWv5Md2NuVRetkZXTab/WJbTN6C6rTKZvALUKEhrXZq',3,'\0'),(9,'Brain','Wilson','user2','$2a$10$6fwEiMrMcoSqO9kYm5eeQenfSF0waq6uj4h2u5xTEnmrdz4Watgy.',3,''),(10,'Luba','Dobrushevich','tutor','$2a$10$SVKz1G0mnX4ZXTysY8p39eC9lk.GwO..VEJtzmTQaWPYO7H2GLl3q',2,'\0');
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

-- Dump completed on 2018-04-06 14:05:28
