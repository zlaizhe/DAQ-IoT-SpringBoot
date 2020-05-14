-- MySQL dump 10.13  Distrib 5.7.26, for Win64 (x86_64)
--
-- Host: localhost    Database: iot
-- ------------------------------------------------------
-- Server version	5.7.26-log

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
-- Table structure for table `tb_data`
--

DROP TABLE IF EXISTS `tb_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` float DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `sensor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_data_sensor_1` (`sensor_id`),
  CONSTRAINT `fk_data_sensor_1` FOREIGN KEY (`sensor_id`) REFERENCES `tb_sensor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_data`
--

LOCK TABLES `tb_data` WRITE;
/*!40000 ALTER TABLE `tb_data` DISABLE KEYS */;
INSERT INTO `tb_data` VALUES (1,0,'2020-02-17 14:16:32',1),(2,0.1,'2020-02-17 14:16:33',1),(3,0.2,'2020-02-17 14:16:34',1),(4,0.3,'2020-02-17 14:16:35',1),(5,0.4,'2020-02-17 14:16:46',1),(6,0.5,'2020-02-17 14:16:47',1),(7,0.6,'2020-02-17 14:16:48',1),(8,0.7,'2020-02-17 14:16:49',1),(9,0.8,'2020-02-17 14:16:50',1),(10,0.9,'2020-02-17 14:16:51',1),(11,0,'2020-02-17 18:05:24',2),(12,0.2,'2020-02-17 18:05:25',2),(13,0.4,'2020-02-17 18:05:26',2),(14,0.6,'2020-02-17 18:05:27',2),(15,0.8,'2020-02-17 18:05:28',2),(16,1,'2020-02-17 18:05:29',2),(17,1.2,'2020-02-17 18:05:30',2),(18,1.4,'2020-02-17 18:05:31',2),(19,1.6,'2020-02-17 18:05:32',2),(20,1.8,'2020-02-17 18:05:33',2),(21,11.78,'2020-02-20 00:23:48',2),(22,61.97,'2020-05-13 16:26:07',9),(23,76.67,'2020-05-13 16:26:17',9),(24,11.96,'2020-05-13 16:26:19',9),(25,91.32,'2020-05-13 16:26:20',9),(26,71.42,'2020-05-13 16:26:22',9),(27,48.56,'2020-05-13 16:29:48',9),(28,31.27,'2020-05-13 16:29:49',9),(29,69.18,'2020-05-13 16:29:52',9),(30,39.23,'2020-05-13 16:29:54',9),(31,73.88,'2020-05-13 16:29:56',9),(32,65.97,'2020-05-13 16:57:12',9),(33,65.52,'2020-05-13 16:58:50',9),(40,-2.3,'2020-05-13 17:05:57',9),(41,-1.3,'2020-05-13 17:05:58',9),(42,-0.3,'2020-05-13 17:05:59',9),(43,0.699,'2020-05-13 17:06:00',9),(44,1.699,'2020-05-13 17:06:01',9),(45,64.29,'2020-05-13 21:45:38',9),(46,92.22,'2020-05-13 21:47:58',9),(47,42.12,'2020-05-13 21:48:06',9),(48,43.12,'2020-05-13 21:48:07',9),(49,44.12,'2020-05-13 21:48:08',9),(50,45.12,'2020-05-13 21:48:09',9),(51,46.12,'2020-05-13 21:48:10',9),(52,50.03,'2020-05-13 21:49:07',9),(53,51.03,'2020-05-13 21:49:08',9),(54,52.03,'2020-05-13 21:49:09',9),(55,53.03,'2020-05-13 21:49:10',9),(56,54.03,'2020-05-13 21:49:11',9),(58,13.74,'2020-05-13 21:52:54',9),(59,14.74,'2020-05-13 21:52:55',9),(60,15.74,'2020-05-13 21:52:56',9),(61,2,'2020-05-13 21:52:57',9),(62,1,'2020-05-13 21:52:58',9);
/*!40000 ALTER TABLE `tb_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_gateway`
--

DROP TABLE IF EXISTS `tb_gateway`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_gateway` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `port` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `location` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_gateway`
--

LOCK TABLES `tb_gateway` WRITE;
/*!40000 ALTER TABLE `tb_gateway` DISABLE KEYS */;
INSERT INTO `tb_gateway` VALUES (1,'127.0.0.1','8080','我是网关1号','天马-1-1-101'),(4,'127.0.0.4','8484','haha，我是网关4号','天马-1-1-104'),(5,'127.0.0.5','80','haha，我是网关5号','天马-1-1-105'),(6,'127.0.0.6','666','haha，我是网关6号','天马-1-1-106'),(7,'127.0.0.7','8787','我是网关7号','天马-1-1-107');
/*!40000 ALTER TABLE `tb_gateway` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_gateway_exception`
--

DROP TABLE IF EXISTS `tb_gateway_exception`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_gateway_exception` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `gate_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_exception_gateway_1` (`gate_id`),
  CONSTRAINT `fk_exception_gateway_1` FOREIGN KEY (`gate_id`) REFERENCES `tb_gateway` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_gateway_exception`
--

LOCK TABLES `tb_gateway_exception` WRITE;
/*!40000 ALTER TABLE `tb_gateway_exception` DISABLE KEYS */;
INSERT INTO `tb_gateway_exception` VALUES (1,'数据异常','2020-02-19 15:05:30',7),(2,'网络异常','2020-02-19 15:06:30',7),(3,'连接异常','2020-02-19 15:07:30',7);
/*!40000 ALTER TABLE `tb_gateway_exception` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sensor`
--

DROP TABLE IF EXISTS `tb_sensor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_sensor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `location` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `factory` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `install_time` date DEFAULT NULL,
  `produce_date` date DEFAULT NULL,
  `maintenance_time` date DEFAULT NULL,
  `gate_id` int(11) NOT NULL,
  `classify_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sensor_gate_1` (`gate_id`),
  KEY `fk_sensor_class_1` (`classify_id`),
  CONSTRAINT `fk_sensor_class_1` FOREIGN KEY (`classify_id`) REFERENCES `tb_sensor_classify` (`id`),
  CONSTRAINT `fk_sensor_gate_1` FOREIGN KEY (`gate_id`) REFERENCES `tb_gateway` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sensor`
--

LOCK TABLES `tb_sensor` WRITE;
/*!40000 ALTER TABLE `tb_sensor` DISABLE KEYS */;
INSERT INTO `tb_sensor` VALUES (1,'haha，我是温度传感器1号','天马-1-3-131','华美集团','2020-02-01','2020-01-01','2021-01-31',1,1),(2,'haha，我是温度传感器2号','天马-1-3-132','华美集团','2020-01-31','2019-12-31','2021-01-30',1,1),(3,'haha，我是湿度传感器1号','天马-1-3-133','xx集团','2020-01-28','2019-12-28','2021-01-27',5,2),(4,'haha，我是湿度传感器2号','天马-1-3-134','华美集团','2020-01-26','2019-12-26','2021-01-25',4,2),(6,'haha，我是温度传感器3号','天马-1-3-135','yy集团','2020-01-30','2019-12-30','2021-01-29',1,1),(7,'我是光照传感器1号','天马-1-1-107','mm集团','2020-01-01','2020-01-01','2020-02-02',1,3),(8,'haha，我是光照传感器2号','天马-2-1-201','东马集团','2020-02-01','2020-01-01','2020-08-01',5,3),(9,'红外传感器1号','天马-3-3-103','zz集团','2020-05-01','2020-05-03','2020-05-07',7,4);
/*!40000 ALTER TABLE `tb_sensor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sensor_classify`
--

DROP TABLE IF EXISTS `tb_sensor_classify`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_sensor_classify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sensor_classify`
--

LOCK TABLES `tb_sensor_classify` WRITE;
/*!40000 ALTER TABLE `tb_sensor_classify` DISABLE KEYS */;
INSERT INTO `tb_sensor_classify` VALUES (1,'温度传感器'),(2,'湿度传感器'),(3,'光照传感器'),(4,'红外传感器');
/*!40000 ALTER TABLE `tb_sensor_classify` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sensor_exception`
--

DROP TABLE IF EXISTS `tb_sensor_exception`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_sensor_exception` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `sensor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_exception_sensor_1` (`sensor_id`),
  CONSTRAINT `fk_exception_sensor_1` FOREIGN KEY (`sensor_id`) REFERENCES `tb_sensor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sensor_exception`
--

LOCK TABLES `tb_sensor_exception` WRITE;
/*!40000 ALTER TABLE `tb_sensor_exception` DISABLE KEYS */;
INSERT INTO `tb_sensor_exception` VALUES (1,'网络异常','2020-02-19 15:03:15',7);
/*!40000 ALTER TABLE `tb_sensor_exception` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `nickname` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `tel` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (1,'zhangsan','张三','01d7f40760960e7bd9443513f22ab9af','17788888877','xxxx@126.com');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-14 18:29:48
