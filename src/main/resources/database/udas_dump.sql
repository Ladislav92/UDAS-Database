-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: localhost    Database: udas
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `id` int(11) NOT NULL,
  `city` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Banja Luka'),(2,'Prijedor'),(3,'Bile?a');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city_province`
--

DROP TABLE IF EXISTS `city_province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city_province` (
  `id` int(11) NOT NULL,
  `city_id` int(11) DEFAULT NULL,
  `city_province` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mjesto_idx` (`city_id`),
  CONSTRAINT `city_province_ibfk_1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city_province`
--

LOCK TABLES `city_province` WRITE;
/*!40000 ALTER TABLE `city_province` DISABLE KEYS */;
/*!40000 ALTER TABLE `city_province` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `education_level`
--

DROP TABLE IF EXISTS `education_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `education_level` (
  `id` int(11) NOT NULL,
  `education_level` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `education_level`
--

LOCK TABLES `education_level` WRITE;
/*!40000 ALTER TABLE `education_level` DISABLE KEYS */;
INSERT INTO `education_level` VALUES (1,'VSS'),(2,'NK'),(3,'VKV'),(4,'SSS'),(5,'VK');
/*!40000 ALTER TABLE `education_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employment_status`
--

DROP TABLE IF EXISTS `employment_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employment_status` (
  `id` int(11) NOT NULL,
  `employment_status` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employment_status`
--

LOCK TABLES `employment_status` WRITE;
/*!40000 ALTER TABLE `employment_status` DISABLE KEYS */;
INSERT INTO `employment_status` VALUES (1,'Zaposlen'),(2,'Nezaposlen'),(3,'Penzioner'),(4,'?koluje se'),(5,'Nepoznato');
/*!40000 ALTER TABLE `employment_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `injury`
--

DROP TABLE IF EXISTS `injury`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `injury` (
  `id` int(11) NOT NULL,
  `member_id` int(11) DEFAULT NULL,
  `injury_location_id` int(11) DEFAULT NULL,
  `amputation` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `clan_idx` (`member_id`),
  KEY `mjesto_povrede_idx` (`injury_location_id`),
  CONSTRAINT `injury_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `injury_ibfk_2` FOREIGN KEY (`injury_location_id`) REFERENCES `injury_location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `injury`
--

LOCK TABLES `injury` WRITE;
/*!40000 ALTER TABLE `injury` DISABLE KEYS */;
/*!40000 ALTER TABLE `injury` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `injury_cause`
--

DROP TABLE IF EXISTS `injury_cause`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `injury_cause` (
  `id` int(11) NOT NULL,
  `injury_cause` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `injury_cause`
--

LOCK TABLES `injury_cause` WRITE;
/*!40000 ALTER TABLE `injury_cause` DISABLE KEYS */;
INSERT INTO `injury_cause` VALUES (1,'Mina'),(2,'NUS'),(3,'Metak'),(4,'Nepoznato');
/*!40000 ALTER TABLE `injury_cause` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `injury_location`
--

DROP TABLE IF EXISTS `injury_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `injury_location` (
  `id` int(11) NOT NULL,
  `injury_location` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `injury_location`
--

LOCK TABLES `injury_location` WRITE;
/*!40000 ALTER TABLE `injury_location` DISABLE KEYS */;
INSERT INTO `injury_location` VALUES (1,'Natkoljenica'),(2,'Podlaktica'),(3,'Potkoljenica'),(4,'Nadlaktica'),(5,'Oko'),(6,'Uho'),(7,'Prsti');
/*!40000 ALTER TABLE `injury_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invalidity_ranking`
--

DROP TABLE IF EXISTS `invalidity_ranking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invalidity_ranking` (
  `id` int(11) NOT NULL,
  `invalidity_kategory` int(11) DEFAULT NULL,
  `invalidity_percentage` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invalidity_ranking`
--

LOCK TABLES `invalidity_ranking` WRITE;
/*!40000 ALTER TABLE `invalidity_ranking` DISABLE KEYS */;
/*!40000 ALTER TABLE `invalidity_ranking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invalidity_status`
--

DROP TABLE IF EXISTS `invalidity_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invalidity_status` (
  `id` int(11) NOT NULL,
  `invalidity_status` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invalidity_status`
--

LOCK TABLES `invalidity_status` WRITE;
/*!40000 ALTER TABLE `invalidity_status` DISABLE KEYS */;
INSERT INTO `invalidity_status` VALUES (1,'C?R'),(2,'RVI'),(3,'Nesre?a'),(4,'Nepoznato');
/*!40000 ALTER TABLE `invalidity_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `ssn` varchar(13) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `phone_number_2` varchar(45) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `city_province_id` int(11) DEFAULT NULL,
  `street_name` varchar(199) DEFAULT NULL,
  `home_number` varchar(10) DEFAULT NULL,
  `household_members` varchar(100) DEFAULT NULL,
  `death_date` date DEFAULT NULL,
  `education_level_id` int(11) DEFAULT NULL,
  `profession_id` int(11) DEFAULT NULL,
  `employment_status_id` int(11) DEFAULT NULL,
  `injury_cause_id` int(11) DEFAULT NULL,
  `sex` char(1) NOT NULL,
  `note` varchar(200) DEFAULT NULL,
  `invalidity_status_id` int(11) DEFAULT NULL,
  `residence_id` int(11) DEFAULT NULL,
  `invalidity_ranking_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `zanimanje_idx` (`profession_id`),
  KEY `stepen_obrazovanja_idx` (`education_level_id`),
  KEY `radni_status_idx` (`employment_status_id`),
  KEY `nacin_povrede_idx` (`injury_cause_id`),
  KEY `mjesna_zaj_idx` (`city_province_id`),
  KEY `mjesto_idx` (`city_id`),
  KEY `mjesto_idxx` (`city_id`),
  KEY `mjestoo_idx` (`city_id`),
  KEY `invalidity_status_id` (`invalidity_status_id`),
  KEY `residence_id` (`residence_id`),
  KEY `invalidity_ranking_id` (`invalidity_ranking_id`),
  CONSTRAINT `member_ibfk_1` FOREIGN KEY (`city_province_id`) REFERENCES `city_province` (`id`),
  CONSTRAINT `member_ibfk_2` FOREIGN KEY (`education_level_id`) REFERENCES `education_level` (`id`),
  CONSTRAINT `member_ibfk_3` FOREIGN KEY (`profession_id`) REFERENCES `profession` (`id`),
  CONSTRAINT `member_ibfk_4` FOREIGN KEY (`employment_status_id`) REFERENCES `employment_status` (`id`),
  CONSTRAINT `member_ibfk_5` FOREIGN KEY (`injury_cause_id`) REFERENCES `injury_cause` (`id`),
  CONSTRAINT `member_ibfk_6` FOREIGN KEY (`invalidity_status_id`) REFERENCES `invalidity_status` (`id`),
  CONSTRAINT `member_ibfk_7` FOREIGN KEY (`residence_id`) REFERENCES `residence` (`id`),
  CONSTRAINT `member_ibfk_8` FOREIGN KEY (`invalidity_ranking_id`) REFERENCES `invalidity_ranking` (`id`),
  CONSTRAINT `member_ibfk_9` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (2,'Mitar','Mitar',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,1,1,1,1,'M',NULL,NULL,NULL,NULL),(3,'Rade','Kornjaca',NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,1,1,1,1,'M',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meta`
--

DROP TABLE IF EXISTS `meta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meta` (
  `db_version` int(11) NOT NULL,
  PRIMARY KEY (`db_version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meta`
--

LOCK TABLES `meta` WRITE;
/*!40000 ALTER TABLE `meta` DISABLE KEYS */;
INSERT INTO `meta` VALUES (1);
/*!40000 ALTER TABLE `meta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profession`
--

DROP TABLE IF EXISTS `profession`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profession` (
  `id` int(11) NOT NULL,
  `profession` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profession`
--

LOCK TABLES `profession` WRITE;
/*!40000 ALTER TABLE `profession` DISABLE KEYS */;
INSERT INTO `profession` VALUES (1,'Stolar'),(2,'Profesor'),(3,'Vodoinstalater'),(4,'Elektri?ar'),(5,'Automehani?ar'),(6,'Ljekar'),(7,'Stomatolog'),(8,'Psiholog'),(9,'Pedagog');
/*!40000 ALTER TABLE `profession` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `residence`
--

DROP TABLE IF EXISTS `residence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `residence` (
  `id` int(11) NOT NULL,
  `residence` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `residence`
--

LOCK TABLES `residence` WRITE;
/*!40000 ALTER TABLE `residence` DISABLE KEYS */;
INSERT INTO `residence` VALUES (1,'Vlastiti objekat'),(2,'Podstanarski status'),(3,'Nerije?eno'),(4,'Nepoznato');
/*!40000 ALTER TABLE `residence` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-21 19:14:18
