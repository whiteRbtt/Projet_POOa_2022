CREATE DATABASE  IF NOT EXISTS `solarsystem` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `solarsystem`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: solarsystem
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `astronomicalbody`
--

DROP TABLE IF EXISTS `astronomicalbody`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `astronomicalbody` (
  `AstroId` int NOT NULL,
  `Name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FirstKnownExplorer` int DEFAULT NULL,
  `Type` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Climate` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Gravity` int DEFAULT NULL,
  `HasEndemicLifeform` bit(1) DEFAULT NULL,
  `FirstExploDate` date DEFAULT NULL,
  PRIMARY KEY (`AstroId`),
  KEY `FirstKnownExplorer_idx` (`FirstKnownExplorer`),
  CONSTRAINT `FirstKnownExplorer` FOREIGN KEY (`FirstKnownExplorer`) REFERENCES `explorer` (`ExploId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `astronomicalbody`
--

LOCK TABLES `astronomicalbody` WRITE;
/*!40000 ALTER TABLE `astronomicalbody` DISABLE KEYS */;
INSERT INTO `astronomicalbody` VALUES (100,'Atrebois',911,'plan??te','temp??r??',1,_binary '',NULL),(101,'Cravit??',411,'plan??te','chaotique',2,_binary '\0','9975-05-05'),(102,'Sombronce',414,'Plan??te morte',NULL,NULL,_binary '',NULL),(103,'Leviante',413,'Plan??te','temp??te',3,_binary '','9980-01-01'),(104,'Sabli??res',912,'plan??te','aride',1,_binary '\0',NULL),(110,'L\'intrus',910,'com??te','glac??',NULL,_binary '','8764-01-01'),(120,'Lune quantique',NULL,'lune vagabonde','quantique',1,_binary '\0',NULL),(121,'Rocaille',410,'lune d Atrebois',NULL,1,_binary '\0','9955-02-05'),(122,'la Lanterne',NULL,'lune de Cravite','volcanique',3,_binary '\0',NULL),(410,'la station solaire',510,'laboratoire de recherches nomai','torride',1,_binary '\0','9999-12-31'),(411,'le canon orbital',510,'relique nomai',NULL,NULL,_binary '\0','9999-12-31'),(412,'Station du trou blanc',510,'relique nomai',NULL,NULL,_binary '\0','9999-12-31'),(777,'l oeil de l univers',510,'origine du bing bang','quantique',NULL,_binary '\0','9999-12-31'),(999,'le Soleil',NULL,'astre',NULL,100,_binary '\0',NULL);
/*!40000 ALTER TABLE `astronomicalbody` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colony`
--

DROP TABLE IF EXISTS `colony`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `colony` (
  `Lifeform` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Location` int NOT NULL,
  `Period` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TribalName` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`Lifeform`,`Location`,`Period`),
  KEY `Location_idx` (`Location`),
  KEY `Period_idx` (`Period`),
  CONSTRAINT `Lifeform` FOREIGN KEY (`Lifeform`) REFERENCES `specie` (`ScientificName`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Location` FOREIGN KEY (`Location`) REFERENCES `astronomicalbody` (`AstroId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Period` FOREIGN KEY (`Period`) REFERENCES `era` (`EraName`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colony`
--

LOCK TABLES `colony` WRITE;
/*!40000 ALTER TABLE `colony` DISABLE KEYS */;
INSERT INTO `colony` VALUES ('Arena monstrum',104,'Ere des explorateurs',NULL),('Arena monstrum',104,'Ere pr??historique',NULL),('Coelacanthiformes gigantis',102,'Ere des explorateurs','le nid'),('Coelacanthiformes gigantis',102,'Ere des p??lerins','le nid'),('Coelacanthiformes gigantis',102,'Ere du grand chaudron cosmique','le nid'),('Coelacanthiformes gigantis',102,'Ere pr??historique','le nid'),('Coelacanthiformes gigantis',104,'Ere du grand chaudron cosmique',''),('Genios ex astris',100,'Ere des p??lerins','la mine'),('Genios ex astris',101,'Ere des p??lerins','la cit?? suspendue'),('Genios ex astris',103,'Ere des p??lerins','le laboratoire des hautes ??nergies'),('Genios ex astris',104,'Ere des p??lerins','la cit?? enfouie'),('Octopode pacificae',104,'Ere des p??lerins','Empire octogonal'),('Octopode pacificae',104,'Ere du grand chaudron cosmique',''),('Periculosum mollis',103,'Ere des explorateurs',NULL),('Periculosum mollis',103,'Ere pr??historique',NULL),('Quattuor luscus Arnoldus',100,'Ere des explorateurs','le village'),('Quattuor luscus Arnoldus',103,'Ere des explorateurs','le laboratoire de recherche antiques'),('Quattuor luscus Arnoldus',121,'Ere des explorateurs','l observatoire'),('Quattuor luscus tadpoles',100,'Ere des p??lerins',NULL),('Quattuor luscus tadpoles',100,'Ere pr??historique',NULL),('Silens mortem',100,'Ere des explorateurs',''),('Silens mortem',100,'Ere pr??historique',NULL),('Silens mortem',101,'Ere des explorateurs',NULL),('Silens mortem',101,'Ere pr??historique',NULL),('Silens mortem',110,'Ere des explorateurs',NULL),('Silens mortem',110,'Ere des p??lerins',NULL),('Silens mortem',110,'Ere pr??historique',NULL);
/*!40000 ALTER TABLE `colony` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `era`
--

DROP TABLE IF EXISTS `era`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `era` (
  `EraName` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Beginning` date NOT NULL,
  `Ending` date DEFAULT NULL,
  PRIMARY KEY (`EraName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `era`
--

LOCK TABLES `era` WRITE;
/*!40000 ALTER TABLE `era` DISABLE KEYS */;
INSERT INTO `era` VALUES ('Ere des explorateurs','9900-01-01',NULL),('Ere des p??lerins','8500-01-01','8764-01-01'),('Ere du grand chaudron cosmique','1000-01-01','8500-01-01'),('Ere pr??historique','8764-01-01','9900-01-01');
/*!40000 ALTER TABLE `era` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `explorer`
--

DROP TABLE IF EXISTS `explorer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `explorer` (
  `ExploId` int NOT NULL,
  `Name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IsAlive` bit(1) DEFAULT NULL,
  `BirthDate` date DEFAULT NULL,
  `Specie` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ExploId`),
  KEY `Specie_idx` (`Specie`),
  CONSTRAINT `Specie` FOREIGN KEY (`Specie`) REFERENCES `specie` (`ScientificName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `explorer`
--

LOCK TABLES `explorer` WRITE;
/*!40000 ALTER TABLE `explorer` DISABLE KEYS */;
INSERT INTO `explorer` VALUES (410,'Esker',_binary '','9940-01-23','Quattuor luscus Arnoldus'),(411,'Riebeck',_binary '','9962-11-11','Quattuor luscus Arnoldus'),(412,'Chester',_binary '','9951-08-14','Quattuor luscus Arnoldus'),(413,'Gabbro',_binary '','9969-05-25','Quattuor luscus Arnoldus'),(414,'Feldspath',NULL,'9973-04-25','Quattuor luscus Arnoldus'),(510,'Petite pierre',_binary '','9991-06-10','Quattuor luscus Arnoldus'),(910,'Pike',_binary '\0',NULL,'Genios ex astris'),(911,'Poke',_binary '\0',NULL,'Genios ex astris'),(912,'Falka',_binary '\0',NULL,'Genios ex astris'),(913,'Solanum',NULL,NULL,'Genios ex astris');
/*!40000 ALTER TABLE `explorer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specie`
--

DROP TABLE IF EXISTS `specie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specie` (
  `ScientificName` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `VernacularName` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IsIntelligent` bit(1) NOT NULL,
  `IsExtinct` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ScientificName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specie`
--

LOCK TABLES `specie` WRITE;
/*!40000 ALTER TABLE `specie` DISABLE KEYS */;
INSERT INTO `specie` VALUES ('Arena monstrum','Vers du d??sert',_binary '\0',NULL),('Coelacanthiformes gigantis','Coelacanthes',_binary '\0',_binary '\0'),('Genios ex astris','Nomais',_binary '',NULL),('Octopode pacificae','Octopode',_binary '',_binary ''),('Periculosum mollis','M??duses ??lectriques',_binary '\0',_binary '\0'),('Quattuor luscus Arnoldus','Atriens',_binary '',_binary '\0'),('Quattuor luscus tadpoles','Proto-Atriens',_binary '\0',_binary ''),('Silens mortem','Mati??re fantome',_binary '\0',_binary '\0');
/*!40000 ALTER TABLE `specie` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-11 19:12:04
