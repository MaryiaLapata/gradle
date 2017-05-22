-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: cdp_users
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(45) NOT NULL,
  `street` varchar(45) NOT NULL,
  `house_number` int(11) NOT NULL,
  `flat_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Minsk','Novaya',1,1),(2,'Minsk','Staraya',10,115),(6,'Minsk','Street1',111,10),(10,'Minsk','Staraya',10,115),(16,'Minsk','INovaya',1,1),(17,'Minsk','INovaya',1,1),(18,'Minsk','INovaya',1,1),(19,'Minsk','INNovaya',1,1),(20,'Minsk','Novaya',2,1),(21,'Minsk','Novaya',2,1),(22,'Minsk','Novaya',2,1),(23,'Minsk','Novaya',2,1),(24,'Minsk','Novaya',2,1),(25,'Minsk','Novaya',2,1),(26,'NY','BakerStreet',2,1),(27,'Minsk','Novaya',2,1),(28,'Minsk','Novaya',0,0),(29,'Minsk','Novaya',1,1),(30,'Minsk','Novaya',1,1),(31,'Minsk','Novaya',1,1),(32,'Minsk','Novaya',1,1);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupp`
--

DROP TABLE IF EXISTS `groupp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groupp` (
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`group_id`),
  CONSTRAINT `group_parent_fk` FOREIGN KEY (`group_id`) REFERENCES `license` (`license_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupp`
--

LOCK TABLES `groupp` WRITE;
/*!40000 ALTER TABLE `groupp` DISABLE KEYS */;
INSERT INTO `groupp` VALUES (6),(7),(14),(15);
/*!40000 ALTER TABLE `groupp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `license`
--

DROP TABLE IF EXISTS `license`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `license` (
  `license_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`license_id`),
  UNIQUE KEY `idlicense_id_UNIQUE` (`license_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `license`
--

LOCK TABLES `license` WRITE;
/*!40000 ALTER TABLE `license` DISABLE KEYS */;
INSERT INTO `license` VALUES (1,NULL),(2,NULL),(3,NULL),(4,NULL),(5,NULL),(6,NULL),(7,NULL),(8,'text'),(11,'Michael, Martin'),(12,'Mike, Michael'),(13,'New group'),(14,'New group'),(15,'Test group'),(16,'Ivan, Holly'),(17,', '),(18,'Hundos, Linda'),(19,'Hundos, Linda'),(20,'Hundos, Linda'),(21,'Hundos, Linda'),(22,'Hundos, Linda'),(23,'Hundos, Linda'),(24,'Hundos, Linda'),(25,'Hundos, Linda'),(26,'Hundos, Linda'),(27,'Hundos, Linda'),(28,'Hundos, Linda'),(29,'Hundos, Linda'),(30,'Linda1, Hundos1'),(31,'Hundos, Linda'),(32,'Hundos, Linda'),(34,'');
/*!40000 ALTER TABLE `license` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lisence_permission`
--

DROP TABLE IF EXISTS `lisence_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lisence_permission` (
  `lisence_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  KEY `permission_id_fk_idx` (`permission_id`),
  KEY `group_idfk_idx` (`lisence_id`),
  CONSTRAINT `lisence_idfk` FOREIGN KEY (`lisence_id`) REFERENCES `license` (`license_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `permission_idfk` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lisence_permission`
--

LOCK TABLES `lisence_permission` WRITE;
/*!40000 ALTER TABLE `lisence_permission` DISABLE KEYS */;
INSERT INTO `lisence_permission` VALUES (6,1),(7,1),(7,2),(30,1),(31,1),(32,1),(30,3),(30,2),(2,1),(2,2);
/*!40000 ALTER TABLE `lisence_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `object` varchar(45) DEFAULT NULL,
  `action_type` varchar(3) DEFAULT '000',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'Permission1','000'),(2,'Permission2','111'),(3,'Permission3','010');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(60) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `address_id_fk_idx` (`address_id`),
  CONSTRAINT `address_id_fk` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_parent_fk` FOREIGN KEY (`user_id`) REFERENCES `license` (`license_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'Holly','Ivan','Holly.Ivan@kronos.com',1,'32165478'),(3,'firstNameN','lastNameN','emailN@dot.com',1,'568974'),(5,'firstNameN','lastNameN','emailN@dot.com',6,'12345673'),(8,'Test','Test','Test',2,'Test'),(11,'Martin','Michael','Martin.Machael@kronos.com',10,NULL),(16,'Holly','Ivan','Holly.Ivan@kronos.com',1,NULL),(20,'Linda','Hundos','Linda.Hudson@kronos.com',16,'123456'),(21,'Linda','Hundos','Linda.Hudson@kronos.com',17,'123456'),(22,'Linda','Hundos','Linda.Hudson@kronos.com',18,'123456'),(23,'Linda','Hundos','Linda.Hudson@kronos.com',19,'123456'),(24,'Linda','Hundos','Linda.Hudson@kronos.com',20,'123456'),(25,'Linda','Hundos','Linda.Hudson@kronos.com',21,'123456'),(26,'Linda','Hundos','Linda.Hudson@kronos.com',22,'123456'),(27,'Linda','Hundos','Linda.Hudson@kronos.com',23,'123456'),(28,'Linda','Hundos','Linda.Hudson@kronos.com',24,'123456'),(29,'Linda','Hundos','Linda.Hudson@kronos.com',25,'123456'),(30,'Linda1','Hundos1','Linda@kronos.com',26,'1234567'),(31,'Linda','Hundos','Linda.Hudson@kronos',27,'123456'),(32,'Linda','Hundos','Linda.Hudson@kronos',28,'123456'),(34,'Holly','Ivan','Holly.Ivan@kronos.com',32,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_group` (
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`group_id`),
  KEY `group_id_fk_idx` (`group_id`),
  KEY `license_id_fk_idx` (`user_id`),
  CONSTRAINT `group_id_fk` FOREIGN KEY (`group_id`) REFERENCES `groupp` (`group_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_group`
--

LOCK TABLES `user_group` WRITE;
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
INSERT INTO `user_group` VALUES (29,6),(30,6),(31,6),(32,6),(30,14),(30,15);
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-22  6:35:11
