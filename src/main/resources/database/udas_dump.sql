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
-- Table structure for table `clan`
--

DROP TABLE IF EXISTS `clan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clan` (
  `id_clan` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(45) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  `jmbg` varchar(13) DEFAULT NULL,
  `datum_rodjenja` date DEFAULT NULL,
  `tel1` varchar(45) DEFAULT NULL,
  `tel2` varchar(45) DEFAULT NULL,
  `id_mjesto` int(11) NOT NULL,
  `id_mjesna` int(11) DEFAULT NULL,
  `ulica` varchar(100) DEFAULT NULL,
  `broj_stana_kuce` varchar(10) DEFAULT NULL,
  `clanovi_domacinstva` varchar(100) DEFAULT NULL,
  `datum_smrti` date DEFAULT NULL,
  `id_stepen_obr` int(11) NOT NULL,
  `id_zanimanje` int(11) NOT NULL,
  `id_radni_status` int(11) NOT NULL,
  `id_nacin_povrd` int(11) NOT NULL,
  `pol` char(1) NOT NULL,
  `napomena` varchar(200) DEFAULT NULL,
  `id_status_invalidnosti` int(11) DEFAULT NULL,
  `id_stambeno_pitanje` int(11) DEFAULT NULL,
  `id_stepen_invalidnosti` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_clan`),
  KEY `zanimanje_idx` (`id_zanimanje`),
  KEY `stepen_obrazovanja_idx` (`id_stepen_obr`),
  KEY `radni_status_idx` (`id_radni_status`),
  KEY `nacin_povrede_idx` (`id_nacin_povrd`),
  KEY `mjesna_zaj_idx` (`id_mjesna`),
  KEY `mjesto_idx` (`id_mjesto`),
  KEY `mjesto_idxx` (`id_mjesto`),
  KEY `mjestoo_idx` (`id_mjesto`),
  KEY `id_status_invalidnosti` (`id_status_invalidnosti`),
  KEY `id_stambeno_pitanje` (`id_stambeno_pitanje`),
  KEY `id_stepen_invalidnosti` (`id_stepen_invalidnosti`),
  CONSTRAINT `clan_ibfk_1` FOREIGN KEY (`id_status_invalidnosti`) REFERENCES `status_invalidnosti` (`id_status_invalidnosti`),
  CONSTRAINT `clan_ibfk_2` FOREIGN KEY (`id_stambeno_pitanje`) REFERENCES `stambeno_pitanje` (`id_stambeno_pitanje`),
  CONSTRAINT `clan_ibfk_3` FOREIGN KEY (`id_stepen_invalidnosti`) REFERENCES `stepen_invalidnosti` (`id_stepen_invalidnosti`),
  CONSTRAINT `mjesna` FOREIGN KEY (`id_mjesna`) REFERENCES `mjesna_zajednica` (`id_mjesna_zajednica`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `mjesto_fk` FOREIGN KEY (`id_mjesto`) REFERENCES `mjesto` (`id_mjesto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `nacin_povrede` FOREIGN KEY (`id_nacin_povrd`) REFERENCES `nacin_povrede` (`id_nacin_povrede`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `radni_status` FOREIGN KEY (`id_radni_status`) REFERENCES `radni_status` (`id_radni_status`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `stepen_obrazovanja` FOREIGN KEY (`id_stepen_obr`) REFERENCES `stepen_obrazovanja` (`id_stepen_obrazovanja`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `zanimanje` FOREIGN KEY (`id_zanimanje`) REFERENCES `zanimanje` (`id_zanimanje`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clan`
--

LOCK TABLES `clan` WRITE;
/*!40000 ALTER TABLE `clan` DISABLE KEYS */;
INSERT INTO `clan` VALUES (2,'Mitar','Mitar',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,1,1,1,1,'M',NULL,NULL,NULL,NULL),(3,'Rade','Kornjaca',NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,1,1,1,1,'M',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `clan` ENABLE KEYS */;
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
-- Table structure for table `mjesna_zajednica`
--

DROP TABLE IF EXISTS `mjesna_zajednica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mjesna_zajednica` (
  `id_mjesna_zajednica` int(11) NOT NULL AUTO_INCREMENT,
  `id_mjesto` int(11) NOT NULL,
  `mjesna_zajednica` varchar(45) NOT NULL,
  PRIMARY KEY (`id_mjesna_zajednica`),
  KEY `mjesto_idx` (`id_mjesto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mjesna_zajednica`
--

LOCK TABLES `mjesna_zajednica` WRITE;
/*!40000 ALTER TABLE `mjesna_zajednica` DISABLE KEYS */;
/*!40000 ALTER TABLE `mjesna_zajednica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mjesto`
--

DROP TABLE IF EXISTS `mjesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mjesto` (
  `id_mjesto` int(11) NOT NULL AUTO_INCREMENT,
  `mjesto` varchar(45) NOT NULL,
  PRIMARY KEY (`id_mjesto`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mjesto`
--

LOCK TABLES `mjesto` WRITE;
/*!40000 ALTER TABLE `mjesto` DISABLE KEYS */;
INSERT INTO `mjesto` VALUES (1,'Banja Luka'),(2,'Prijedor'),(3,'Bile?a');
/*!40000 ALTER TABLE `mjesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mjesto_povrede`
--

DROP TABLE IF EXISTS `mjesto_povrede`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mjesto_povrede` (
  `id_mjesto_povrede` int(11) NOT NULL AUTO_INCREMENT,
  `mjesto_povrede` varchar(45) NOT NULL,
  PRIMARY KEY (`id_mjesto_povrede`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mjesto_povrede`
--

LOCK TABLES `mjesto_povrede` WRITE;
/*!40000 ALTER TABLE `mjesto_povrede` DISABLE KEYS */;
INSERT INTO `mjesto_povrede` VALUES (1,'Natkoljenica'),(2,'Podlaktica'),(3,'Potkoljenica'),(4,'Nadlaktica'),(5,'Oko'),(6,'Uho'),(7,'Prsti');
/*!40000 ALTER TABLE `mjesto_povrede` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nacin_povrede`
--

DROP TABLE IF EXISTS `nacin_povrede`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nacin_povrede` (
  `id_nacin_povrede` int(11) NOT NULL AUTO_INCREMENT,
  `nacin_povrede` varchar(45) NOT NULL,
  PRIMARY KEY (`id_nacin_povrede`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nacin_povrede`
--

LOCK TABLES `nacin_povrede` WRITE;
/*!40000 ALTER TABLE `nacin_povrede` DISABLE KEYS */;
INSERT INTO `nacin_povrede` VALUES (1,'Mina'),(2,'NUS'),(3,'Metak'),(4,'Nepoznato');
/*!40000 ALTER TABLE `nacin_povrede` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `povreda`
--

DROP TABLE IF EXISTS `povreda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `povreda` (
  `id_povreda` int(11) NOT NULL AUTO_INCREMENT,
  `id_clan` int(11) NOT NULL,
  `id_mjesto_povrede` int(11) NOT NULL,
  `amputacija` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id_povreda`),
  KEY `clan_idx` (`id_clan`),
  KEY `mjesto_povrede_idx` (`id_mjesto_povrede`),
  CONSTRAINT `clan` FOREIGN KEY (`id_clan`) REFERENCES `clan` (`id_clan`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `mjesto_povrede` FOREIGN KEY (`id_mjesto_povrede`) REFERENCES `mjesto_povrede` (`id_mjesto_povrede`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `povreda`
--

LOCK TABLES `povreda` WRITE;
/*!40000 ALTER TABLE `povreda` DISABLE KEYS */;
/*!40000 ALTER TABLE `povreda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `radni_status`
--

DROP TABLE IF EXISTS `radni_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `radni_status` (
  `id_radni_status` int(11) NOT NULL AUTO_INCREMENT,
  `radni_status` varchar(30) NOT NULL,
  PRIMARY KEY (`id_radni_status`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `radni_status`
--

LOCK TABLES `radni_status` WRITE;
/*!40000 ALTER TABLE `radni_status` DISABLE KEYS */;
INSERT INTO `radni_status` VALUES (1,'Zaposlen'),(2,'Nezaposlen'),(3,'Penzioner'),(4,'?koluje se'),(5,'Nepoznato');
/*!40000 ALTER TABLE `radni_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stambeno_pitanje`
--

DROP TABLE IF EXISTS `stambeno_pitanje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stambeno_pitanje` (
  `id_stambeno_pitanje` int(11) NOT NULL AUTO_INCREMENT,
  `stambeno_pitanje` varchar(45) NOT NULL,
  PRIMARY KEY (`id_stambeno_pitanje`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stambeno_pitanje`
--

LOCK TABLES `stambeno_pitanje` WRITE;
/*!40000 ALTER TABLE `stambeno_pitanje` DISABLE KEYS */;
INSERT INTO `stambeno_pitanje` VALUES (1,'Vlastiti objekat'),(2,'Podstanarski status'),(3,'Nerije?eno'),(4,'Nepoznato');
/*!40000 ALTER TABLE `stambeno_pitanje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_invalidnosti`
--

DROP TABLE IF EXISTS `status_invalidnosti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status_invalidnosti` (
  `id_status_invalidnosti` int(11) NOT NULL AUTO_INCREMENT,
  `status_invalidnosti` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_status_invalidnosti`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_invalidnosti`
--

LOCK TABLES `status_invalidnosti` WRITE;
/*!40000 ALTER TABLE `status_invalidnosti` DISABLE KEYS */;
INSERT INTO `status_invalidnosti` VALUES (1,'C?R'),(2,'RVI'),(3,'Nesre?a'),(4,'Nepoznato');
/*!40000 ALTER TABLE `status_invalidnosti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stepen_invalidnosti`
--

DROP TABLE IF EXISTS `stepen_invalidnosti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stepen_invalidnosti` (
  `id_stepen_invalidnosti` int(11) NOT NULL AUTO_INCREMENT,
  `kategorija_invalidnosti` int(11) NOT NULL,
  `procenat_invalidnosti` int(11) NOT NULL,
  PRIMARY KEY (`id_stepen_invalidnosti`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stepen_invalidnosti`
--

LOCK TABLES `stepen_invalidnosti` WRITE;
/*!40000 ALTER TABLE `stepen_invalidnosti` DISABLE KEYS */;
/*!40000 ALTER TABLE `stepen_invalidnosti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stepen_obrazovanja`
--

DROP TABLE IF EXISTS `stepen_obrazovanja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stepen_obrazovanja` (
  `id_stepen_obrazovanja` int(11) NOT NULL AUTO_INCREMENT,
  `stepen_obrazovanja` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_stepen_obrazovanja`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stepen_obrazovanja`
--

LOCK TABLES `stepen_obrazovanja` WRITE;
/*!40000 ALTER TABLE `stepen_obrazovanja` DISABLE KEYS */;
INSERT INTO `stepen_obrazovanja` VALUES (1,'VSS'),(2,'NK'),(3,'VKV'),(4,'SSS'),(5,'VK');
/*!40000 ALTER TABLE `stepen_obrazovanja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zanimanje`
--

DROP TABLE IF EXISTS `zanimanje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zanimanje` (
  `id_zanimanje` int(11) NOT NULL AUTO_INCREMENT,
  `zanimanje` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_zanimanje`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zanimanje`
--

LOCK TABLES `zanimanje` WRITE;
/*!40000 ALTER TABLE `zanimanje` DISABLE KEYS */;
INSERT INTO `zanimanje` VALUES (1,'Stolar'),(2,'Profesor'),(3,'Vodoinstalater'),(4,'Elektri?ar'),(5,'Automehani?ar'),(6,'Ljekar'),(7,'Stomatolog'),(8,'Psiholog'),(9,'Pedagog');
/*!40000 ALTER TABLE `zanimanje` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-15 18:39:47
