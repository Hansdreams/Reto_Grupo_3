-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: reto_prueba
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `grupos`
--

DROP TABLE IF EXISTS `grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupos` (
  `ID_GRUPO` int NOT NULL,
  `COD_GRUP` varchar(8) NOT NULL,
  `idCurso` int NOT NULL,
  `alumnos` int NOT NULL,
  `estadoGrup` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID_GRUPO`),
  KEY `fk_Grupos_Cursos` (`idCurso`),
  CONSTRAINT `fk_Grupos_Cursos` FOREIGN KEY (`idCurso`) REFERENCES `cursos` (`ID_CURSO`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupos`
--

LOCK TABLES `grupos` WRITE;
/*!40000 ALTER TABLE `grupos` DISABLE KEYS */;
INSERT INTO `grupos` VALUES (1,'ESO1A',1,22,1),(2,'ESO1B',1,21,1),(3,'ESO1C',1,20,1),(4,'ESO1D',1,22,1),(5,'ESO2A',2,19,1),(6,'ESO2B',2,21,1),(7,'ESO2C',2,20,1),(8,'ESO2D',2,19,1),(9,'ESO3A',3,25,1),(10,'ESO3B',3,24,1),(11,'ESO3C',3,25,1),(12,'ESO4A',4,19,1),(13,'ESO4B',4,18,1),(14,'ESO4C',4,18,1),(15,'ESO4D',4,17,1),(16,'BCH1HCS',5,21,1),(17,'BCH1CT',5,22,1),(18,'BCH2HCS',6,20,1),(19,'BDH2CT',6,19,1),(20,'FM1',7,24,1),(21,'FM2',8,18,1),(22,'MV1',9,25,1),(23,'MV2',10,15,1),(24,'CAR1',11,25,1),(25,'CAR2',12,16,1),(26,'EVA1',13,24,1),(27,'EVA2',14,14,1),(28,'SMR1',15,28,1),(29,'SMR2',16,19,1),(30,'AF1',17,24,1),(31,'AF2',18,25,1),(32,'DAM1',19,28,1),(33,'DAM2',20,35,1),(34,'DAW1',21,24,1),(35,'DAW2',22,18,1),(36,'DFM1',23,16,1),(37,'DFM2',24,9,1);
/*!40000 ALTER TABLE `grupos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-14 20:05:47
