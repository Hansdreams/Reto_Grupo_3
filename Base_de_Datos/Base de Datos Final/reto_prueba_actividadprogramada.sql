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
-- Table structure for table `actividadprogramada`
--

DROP TABLE IF EXISTS `actividadprogramada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividadprogramada` (
  `IdSolicitud` int NOT NULL,
  `Solicitante` int NOT NULL,
  `nombreAct` varchar(45) NOT NULL,
  `tipoActividad` enum('Extraordinaria','Complementaria') NOT NULL,
  `Departamento` int NOT NULL,
  `Prevista` tinyint(1) NOT NULL,
  `Transporte` tinyint(1) NOT NULL,
  `FechaInicial` date NOT NULL,
  `FechaFinal` date NOT NULL,
  `HoraInicial` time NOT NULL,
  `HoraFinal` time NOT NULL,
  `Alojamiento` tinyint(1) DEFAULT NULL,
  `comentarioAdicional` varchar(200) NOT NULL,
  `TransporteContrato` tinyint(1) NOT NULL,
  `AlumnosMAX` int NOT NULL,
  `Presupuesto` decimal(6,2) NOT NULL,
  `AlumnosParticipantes` int NOT NULL,
  `Comentario` text NOT NULL,
  PRIMARY KEY (`IdSolicitud`),
  CONSTRAINT `fk_ActiviadadProgramada_Solicitud` FOREIGN KEY (`IdSolicitud`) REFERENCES `solicitud` (`idSolicitud`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividadprogramada`
--

LOCK TABLES `actividadprogramada` WRITE;
/*!40000 ALTER TABLE `actividadprogramada` DISABLE KEYS */;
INSERT INTO `actividadprogramada` VALUES (6,3,'prueba3','Complementaria',4,0,1,'2024-05-15','2024-05-16','16:30:00','17:30:00',1,'prueba',1,10,540.50,40,'esto es una prueba'),(17,6,'Solicitud 16','Complementaria',3,1,1,'2024-05-14','2024-05-15','21:42:00','22:42:00',1,'-',1,79,500.50,50,'NINGUNO'),(19,1,'aaaaaa','Extraordinaria',1,1,1,'2024-05-15','2024-05-15','20:00:00','20:30:00',1,'aaaaaa',1,22,500.00,10,'aaa'),(20,1,'aaaaaa','Complementaria',3,1,1,'2024-05-15','2024-05-15','20:32:00','23:20:00',0,'aaaa',1,85,100.00,80,'prueba'),(21,1,'Hola','Extraordinaria',1,0,1,'2024-05-17','2024-05-18','11:00:00','10:00:00',1,'Hola',1,171,150.00,100,'Holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa');
/*!40000 ALTER TABLE `actividadprogramada` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-16 17:27:19
