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
-- Table structure for table `departamentos`
--

DROP TABLE IF EXISTS `departamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departamentos` (
  `idDepartamentos` int NOT NULL,
  `COD_DE` char(3) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `JEFE` int DEFAULT NULL,
  PRIMARY KEY (`idDepartamentos`),
  UNIQUE KEY `COD_DE` (`COD_DE`),
  UNIQUE KEY `COD_DE_UNIQUE` (`COD_DE`),
  KEY `fk_Departamentos_Profesores` (`JEFE`),
  CONSTRAINT `fk_Departamentos_Profesores` FOREIGN KEY (`JEFE`) REFERENCES `profesores` (`ID_Prof`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamentos`
--

LOCK TABLES `departamentos` WRITE;
/*!40000 ALTER TABLE `departamentos` DISABLE KEYS */;
INSERT INTO `departamentos` VALUES (1,'BG','Biologia y Geologia',14),(2,'DIB','Dibujo',8),(3,'ECO','Economia',4),(4,'EF','Educacion Fisica',15),(5,'FIL','Filosofia',3),(6,'FQ','Fisica y Quimica',2),(7,'FRA','Frances',5),(8,'GH','Geografia e Historia',6),(9,'ING','Ingles',9),(10,'LAT','Latin',5),(11,'LEN','Lengua Castellana y Literatura',1),(12,'MAT','Matematicas',8),(13,'MUS','Musica',10),(14,'TEC','Tecnologia',10),(15,'AG','Administracion y Gestion',12),(16,'FOL','Formacion y Orientacion Laboral',5),(17,'INF','Informatica y Comunicaciones',15),(18,'FM','Fabricacion Mecanica',1),(19,'TMV','Transporte y Mantenimiento de Vehiculos',5),(20,'S-U','Superusuario',999);
/*!40000 ALTER TABLE `departamentos` ENABLE KEYS */;
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
