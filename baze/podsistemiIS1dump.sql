-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: podsistem3is1
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `podsistem3is1`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `podsistem3is1` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `podsistem3is1`;

--
-- Table structure for table `gledanje`
--

DROP TABLE IF EXISTS `gledanje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gledanje` (
  `gledanjeId` int NOT NULL AUTO_INCREMENT,
  `korisnikId` int NOT NULL,
  `videoSnimakId` int NOT NULL,
  `datumPocetka` date NOT NULL,
  `vremePocetka` int NOT NULL,
  `sekundPocetkaSnimka` int NOT NULL,
  `sekundeOdgledano` int NOT NULL,
  PRIMARY KEY (`gledanjeId`),
  UNIQUE KEY `gledanjeId_UNIQUE` (`gledanjeId`),
  KEY `FK_korisnikId_gledanje_idx` (`korisnikId`),
  KEY `FK_videoId_gledanje_idx` (`videoSnimakId`),
  CONSTRAINT `FK_korisnikId_gledanje` FOREIGN KEY (`korisnikId`) REFERENCES `korisnik` (`korisnikId`) ON UPDATE CASCADE,
  CONSTRAINT `FK_videoId_gledanje` FOREIGN KEY (`videoSnimakId`) REFERENCES `videosnimak` (`videoSnimakId`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gledanje`
--

LOCK TABLES `gledanje` WRITE;
/*!40000 ALTER TABLE `gledanje` DISABLE KEYS */;
INSERT INTO `gledanje` VALUES (1,1,1,'2024-02-11',50000,1,100),(2,4,1,'2022-11-11',0,0,56),(3,5,3,'2010-11-12',765,0,23),(4,1,1,'2024-02-12',0,0,1000);
/*!40000 ALTER TABLE `gledanje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grad`
--

DROP TABLE IF EXISTS `grad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grad` (
  `gradId` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) NOT NULL,
  PRIMARY KEY (`gradId`),
  UNIQUE KEY `gradId_UNIQUE` (`gradId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grad`
--

LOCK TABLES `grad` WRITE;
/*!40000 ALTER TABLE `grad` DISABLE KEYS */;
INSERT INTO `grad` VALUES (1,'Pozega'),(2,'Beograd'),(3,'NoviSad'),(4,'Madrid'),(5,'London'),(6,'Moskva'),(7,'AbuDhabi');
/*!40000 ALTER TABLE `grad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik` (
  `korisnikId` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `godiste` int NOT NULL,
  `pol` varchar(45) NOT NULL,
  `gradId` int NOT NULL,
  PRIMARY KEY (`korisnikId`),
  UNIQUE KEY `korisnikId_UNIQUE` (`korisnikId`),
  KEY `FK_gradId_korisnik_idx` (`gradId`),
  CONSTRAINT `FK_gradId_korisnik` FOREIGN KEY (`gradId`) REFERENCES `grad` (`gradId`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES (1,'Irina','majstorovicirina03@gmail.com',2003,'Z',2),(2,'Nikola','stulovic70@gmail.com',2002,'M',2),(3,'Marko','marko@gmail.com',1999,'M',4),(4,'Anabela','anabela@gmail.com',2005,'Z',5),(5,'Andrej','andrej@gmail.com',2003,'M',6);
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ocena`
--

DROP TABLE IF EXISTS `ocena`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ocena` (
  `ocenaId` int NOT NULL AUTO_INCREMENT,
  `korisnikId` int NOT NULL,
  `videosnimakId` int NOT NULL,
  `datumOcene` date NOT NULL,
  `vremeOcene` int NOT NULL,
  `ocena` int NOT NULL,
  PRIMARY KEY (`ocenaId`),
  UNIQUE KEY `ocenaId_UNIQUE` (`ocenaId`),
  KEY `FK_korId_ocena_idx` (`korisnikId`),
  KEY `FK_videoId_ocena_idx` (`videosnimakId`),
  CONSTRAINT `FK_korId_ocena` FOREIGN KEY (`korisnikId`) REFERENCES `korisnik` (`korisnikId`) ON UPDATE CASCADE,
  CONSTRAINT `FK_videoId_ocena` FOREIGN KEY (`videosnimakId`) REFERENCES `videosnimak` (`videoSnimakId`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ocena`
--

LOCK TABLES `ocena` WRITE;
/*!40000 ALTER TABLE `ocena` DISABLE KEYS */;
INSERT INTO `ocena` VALUES (2,1,1,'2024-02-11',54000,4),(3,4,2,'2017-01-01',1,4),(4,3,3,'2022-08-08',4242,5);
/*!40000 ALTER TABLE `ocena` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paket`
--

DROP TABLE IF EXISTS `paket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paket` (
  `paketId` int NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(45) NOT NULL,
  `trenutnaCena` int NOT NULL,
  PRIMARY KEY (`paketId`),
  UNIQUE KEY `paketId_UNIQUE` (`paketId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paket`
--

LOCK TABLES `paket` WRITE;
/*!40000 ALTER TABLE `paket` DISABLE KEYS */;
INSERT INTO `paket` VALUES (1,'Zlatni',5000),(2,'Platinum',50000),(3,'Srebrni',2000);
/*!40000 ALTER TABLE `paket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pretplata`
--

DROP TABLE IF EXISTS `pretplata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pretplata` (
  `pretplataId` int NOT NULL AUTO_INCREMENT,
  `korisnikId` int NOT NULL,
  `paketId` int NOT NULL,
  `datumPocetka` date NOT NULL,
  `vremePocetka` int NOT NULL,
  `cenaPretplate` int NOT NULL,
  PRIMARY KEY (`pretplataId`),
  UNIQUE KEY `pretplataId_UNIQUE` (`pretplataId`),
  KEY `FK_korId_pretplata_idx` (`korisnikId`),
  KEY `FK_paketId_pretplata_idx` (`paketId`),
  CONSTRAINT `FK_korId_pretplata` FOREIGN KEY (`korisnikId`) REFERENCES `korisnik` (`korisnikId`) ON UPDATE CASCADE,
  CONSTRAINT `FK_paketId_pretplata` FOREIGN KEY (`paketId`) REFERENCES `paket` (`paketId`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pretplata`
--

LOCK TABLES `pretplata` WRITE;
/*!40000 ALTER TABLE `pretplata` DISABLE KEYS */;
INSERT INTO `pretplata` VALUES (1,1,1,'2024-06-17',36000,5000),(2,3,3,'2024-02-11',6789,2000),(3,5,2,'2024-01-01',1,50000);
/*!40000 ALTER TABLE `pretplata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videosnimak`
--

DROP TABLE IF EXISTS `videosnimak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videosnimak` (
  `videoSnimakId` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) NOT NULL,
  `trajanje` int NOT NULL,
  `vlasnikId` int NOT NULL,
  `datumPostavke` date NOT NULL,
  `vremePostavke` int NOT NULL,
  PRIMARY KEY (`videoSnimakId`),
  UNIQUE KEY `videoSnimakId_UNIQUE` (`videoSnimakId`),
  KEY `FK_vlasnikId_videosnimak_idx` (`vlasnikId`),
  CONSTRAINT `FK_vlasnikId_videosnimak` FOREIGN KEY (`vlasnikId`) REFERENCES `korisnik` (`korisnikId`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videosnimak`
--

LOCK TABLES `videosnimak` WRITE;
/*!40000 ALTER TABLE `videosnimak` DISABLE KEYS */;
INSERT INTO `videosnimak` VALUES (1,'IrinaINikola',100,1,'2024-02-10',10),(2,'Me at the Zoo',39,3,'2007-05-12',40000),(3,'Crtani za decu',700,4,'2012-09-07',9872);
/*!40000 ALTER TABLE `videosnimak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `podsistem1is1`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `podsistem1is1` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `podsistem1is1`;

--
-- Table structure for table `grad`
--

DROP TABLE IF EXISTS `grad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grad` (
  `gradId` int NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(45) NOT NULL,
  PRIMARY KEY (`gradId`),
  UNIQUE KEY `gradId_UNIQUE` (`gradId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grad`
--

LOCK TABLES `grad` WRITE;
/*!40000 ALTER TABLE `grad` DISABLE KEYS */;
INSERT INTO `grad` VALUES (1,'Pozega'),(2,'Beograd'),(3,'NoviSad'),(4,'Madrid'),(5,'London'),(6,'Moskva'),(7,'AbuDhabi');
/*!40000 ALTER TABLE `grad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik` (
  `korisnikId` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `godiste` int NOT NULL,
  `pol` varchar(45) NOT NULL,
  `gradId` int NOT NULL,
  PRIMARY KEY (`korisnikId`),
  UNIQUE KEY `korisnikId_UNIQUE` (`korisnikId`),
  KEY `FK_gradId_Korisnik_idx` (`gradId`),
  CONSTRAINT `FK_gradId_Korisnik` FOREIGN KEY (`gradId`) REFERENCES `grad` (`gradId`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES (1,'Irina','majstorovicirina03@gmail.com',2003,'Z',2),(2,'Nikola','stulovic70@gmail.com',2002,'M',2),(3,'Marko','marko@gmail.com',1999,'M',4),(4,'Anabela','anabela@gmail.com',2005,'Z',5),(5,'Andrej','andrej@gmail.com',2003,'M',6);
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `podsistem2is1`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `podsistem2is1` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `podsistem2is1`;

--
-- Table structure for table `grad`
--

DROP TABLE IF EXISTS `grad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grad` (
  `gradId` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) NOT NULL,
  PRIMARY KEY (`gradId`),
  UNIQUE KEY `gradId_UNIQUE` (`gradId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grad`
--

LOCK TABLES `grad` WRITE;
/*!40000 ALTER TABLE `grad` DISABLE KEYS */;
INSERT INTO `grad` VALUES (1,'Pozega'),(2,'Beograd'),(3,'NoviSad'),(4,'Madrid'),(5,'London'),(6,'Moskva'),(7,'AbuDhabi');
/*!40000 ALTER TABLE `grad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kategorija`
--

DROP TABLE IF EXISTS `kategorija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kategorija` (
  `kategorijaId` int NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(45) NOT NULL,
  PRIMARY KEY (`kategorijaId`),
  UNIQUE KEY `kategorijaId_UNIQUE` (`kategorijaId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategorija`
--

LOCK TABLES `kategorija` WRITE;
/*!40000 ALTER TABLE `kategorija` DISABLE KEYS */;
INSERT INTO `kategorija` VALUES (1,'Muzicki'),(2,'VideoIgre'),(3,'Trka'),(4,'Zabava'),(5,'Igra'),(6,'Klasika');
/*!40000 ALTER TABLE `kategorija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kategorijavideo`
--

DROP TABLE IF EXISTS `kategorijavideo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kategorijavideo` (
  `kategorijaVideoId` int NOT NULL AUTO_INCREMENT,
  `kategorijaId` int NOT NULL,
  `videoId` int NOT NULL,
  PRIMARY KEY (`kategorijaVideoId`),
  UNIQUE KEY `kategorijaVideoId_UNIQUE` (`kategorijaVideoId`),
  KEY `FK_katId_KatVid_idx` (`kategorijaId`),
  KEY `FK_vidId_KatVid_idx` (`videoId`),
  CONSTRAINT `FK_katId_KatVid` FOREIGN KEY (`kategorijaId`) REFERENCES `kategorija` (`kategorijaId`) ON UPDATE CASCADE,
  CONSTRAINT `FK_vidId_KatVid` FOREIGN KEY (`videoId`) REFERENCES `videosnimak` (`videoSnimakId`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategorijavideo`
--

LOCK TABLES `kategorijavideo` WRITE;
/*!40000 ALTER TABLE `kategorijavideo` DISABLE KEYS */;
INSERT INTO `kategorijavideo` VALUES (1,3,1),(2,4,2),(3,5,1),(4,2,3),(5,1,3);
/*!40000 ALTER TABLE `kategorijavideo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik` (
  `korisnikId` int NOT NULL AUTO_INCREMENT,
  `Ime` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `godiste` int NOT NULL,
  `pol` varchar(45) NOT NULL,
  `gradId` int NOT NULL,
  PRIMARY KEY (`korisnikId`),
  UNIQUE KEY `korisnikId_UNIQUE` (`korisnikId`),
  KEY `FK_gradId_korisnik_idx` (`gradId`),
  CONSTRAINT `FK_gradId_korisnik` FOREIGN KEY (`gradId`) REFERENCES `grad` (`gradId`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES (1,'Irina','majstorovicirina03@gmail.com',2003,'Z',2),(2,'Nikola','stulovic70@gmail.com',2002,'M',2),(3,'Marko','marko@gmail.com',1999,'M',4),(4,'Anabela','anabela@gmail.com',2005,'Z',5),(5,'Andrej','andrej@gmail.com',2003,'M',6);
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videosnimak`
--

DROP TABLE IF EXISTS `videosnimak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videosnimak` (
  `videoSnimakId` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) NOT NULL,
  `trajanje` int NOT NULL,
  `vlasnikId` int NOT NULL,
  `datumPostavljanja` date NOT NULL,
  `vremePostavljanja` int NOT NULL,
  PRIMARY KEY (`videoSnimakId`),
  UNIQUE KEY `videoSnimakId_UNIQUE` (`videoSnimakId`),
  KEY `FK_vlasnikId_VidSnim_idx` (`vlasnikId`),
  CONSTRAINT `FK_vlasnikId_VidSnim` FOREIGN KEY (`vlasnikId`) REFERENCES `korisnik` (`korisnikId`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videosnimak`
--

LOCK TABLES `videosnimak` WRITE;
/*!40000 ALTER TABLE `videosnimak` DISABLE KEYS */;
INSERT INTO `videosnimak` VALUES (1,'IrinaINikola',100,1,'2024-02-10',10),(2,'Me at the Zoo',39,3,'2007-05-12',40000),(3,'Crtani za decu',700,4,'2012-09-07',9872);
/*!40000 ALTER TABLE `videosnimak` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-12  2:05:05
