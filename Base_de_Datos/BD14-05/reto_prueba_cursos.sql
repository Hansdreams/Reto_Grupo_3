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
-- Table structure for table `cursos`
--

DROP TABLE IF EXISTS `cursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cursos` (
  `ID_CURSO` int NOT NULL,
  `COD_CUR` varchar(5) NOT NULL,
  `descripCur` varchar(145) NOT NULL,
  `etapa` enum('ESO','BACHILLERATO','FPGS','FPGM','FPB','FBCE') NOT NULL,
  `estadoCur` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID_CURSO`),
  UNIQUE KEY `COD_CUR_UNIQUE` (`COD_CUR`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos`
--

LOCK TABLES `cursos` WRITE;
/*!40000 ALTER TABLE `cursos` DISABLE KEYS */;
INSERT INTO `cursos` VALUES (1,'ESO1','Educacion Secundaria Obligatoria - 1º','ESO',1),(2,'ESO2','Educacion Secundaria Obligatoria - 2º','ESO',1),(3,'ESO3','Educacion Secundaria Obligatoria - 3º','ESO',1),(4,'ESO4','Educacion Secundaria Obligatoria - 4º','ESO',1),(5,'BCH1','Bachillerato 1º','BACHILLERATO',1),(6,'BCH2','Bachillerato 2º','BACHILLERATO',1),(7,'FM1','Fabricación y montaje - 1º','FPGM',1),(8,'FM2','Fabricación y montaje - 2º','FPGM',1),(9,'MV1','Mantenimiento de Vehiculos - 1º','FPGM',1),(10,'MV2','Mantenimiento de Vehiculos - 2º','FPGM',1),(11,'CAR1','Carroceria - 1º','FPGM',1),(12,'CAR2','Carroceria - 2º','FPGM',1),(13,'EVA1','Electromecánica de Vehiculos Automoviles - 1º','FPGM',1),(14,'EVA2','Electromecánica de Vehiculos Automoviles - 2º','FPGM',1),(15,'SMR1','Sistemas Microinformaticos y Redes - 1º','FPGM',1),(16,'SMR2','Sistemas Microinformaticos y Redes - 2º','FPGM',1),(17,'AF1','Administracion y Finanzas - 1º','FPGS',1),(18,'AF2','Administracion y Finanzas - 2º','FPGS',1),(19,'DAM1','Desarrollo de Aplicaciones Multiplataforma - 1º','FPGS',1),(20,'DAM2','Desarrollo de Aplicaciones Multiplataforma - 2º','FPGS',1),(21,'DAW1','Desarrollo de Aplicaciones Web - 1º','FPGS',1),(22,'DAW2','Desarrollo de Aplicaciones Web - 2º','FPGS',1),(23,'DFM1','Diseño en Fabricacion Mecanica - 1º','FPGS',1),(24,'DFM2','Diseño en Fabricacion Mecanica - 2º','FPGS',1);
/*!40000 ALTER TABLE `cursos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-14 20:05:48
