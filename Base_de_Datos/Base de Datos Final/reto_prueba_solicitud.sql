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
-- Table structure for table `solicitud`
--

DROP TABLE IF EXISTS `solicitud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitud` (
  `idSolicitud` int NOT NULL AUTO_INCREMENT,
  `Solicitante` int NOT NULL,
  `nombreAct` varchar(45) NOT NULL,
  `tipoActividad` enum('extraordinaria','complementaria') NOT NULL,
  `Departamento` int NOT NULL,
  `Prevista` tinyint(1) NOT NULL,
  `Transporte` tinyint(1) NOT NULL,
  `FechaInicial` date NOT NULL,
  `FechaFinal` date NOT NULL,
  `HoraInicial` time NOT NULL,
  `HoraFinal` time NOT NULL,
  `Alojamiento` tinyint(1) DEFAULT NULL,
  `comentarioAdicional` varchar(500) NOT NULL,
  `AlumnosMAX` int NOT NULL,
  `Estado` enum('solicitada','realizada','denegada','aprobada') NOT NULL DEFAULT 'solicitada',
  `ConsultaEstado` varchar(300) NOT NULL DEFAULT '',
  PRIMARY KEY (`idSolicitud`),
  KEY `fk_Solicitud_Profesores` (`Solicitante`),
  KEY `fk_Solicitud_Departamento` (`Departamento`),
  CONSTRAINT `fk_Solicitud_Departamento` FOREIGN KEY (`Departamento`) REFERENCES `departamentos` (`idDepartamentos`) ON UPDATE CASCADE,
  CONSTRAINT `fk_Solicitud_Profesores` FOREIGN KEY (`Solicitante`) REFERENCES `profesores` (`ID_Prof`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud`
--

LOCK TABLES `solicitud` WRITE;
/*!40000 ALTER TABLE `solicitud` DISABLE KEYS */;
INSERT INTO `solicitud` VALUES (1,999,'Prueba','extraordinaria',20,1,1,'2011-05-24','2012-05-24','10:30:00','11:30:00',1,'Ninguno',10,'aprobada','kakakakak'),(2,999,'Prueba','extraordinaria',20,1,1,'2011-05-24','2012-05-24','10:30:00','11:30:00',1,'Ninguno',10,'solicitada','-'),(3,999,'Prueba','extraordinaria',20,1,1,'2024-05-11','2024-05-12','10:30:00','11:30:00',1,'Ninguno',10,'solicitada','-'),(4,3,'Prueba','extraordinaria',20,1,1,'2024-05-11','2024-05-12','10:30:00','11:30:00',1,'Ninguno',10,'solicitada','-'),(5,1,'aaa','complementaria',2,0,0,'2024-05-11','2024-05-12','10:30:00','11:30:00',0,'aaa',10,'solicitada',''),(6,3,'prueba3','complementaria',4,0,1,'2024-05-15','2024-05-16','16:30:00','17:30:00',1,'prueba',10,'aprobada','Aprobada'),(7,3,'bbbb','extraordinaria',1,1,1,'2024-05-15','2024-05-15','13:30:00','13:30:00',1,'aaaaa',1,'solicitada','-'),(8,999,'aaaaaa','complementaria',7,1,0,'2024-06-11','2024-06-12','10:30:00','11:30:00',1,'nueva ventana',10,'solicitada','-'),(9,999,'bbbbb','complementaria',17,0,1,'2024-05-12','2024-05-13','12:30:00','13:30:00',0,'asssss',30,'aprobada','bbbb'),(10,999,'ccc','complementaria',2,1,0,'2024-05-12','2024-05-13','13:30:00','14:30:00',1,'mmmmmm',2,'solicitada','-'),(11,999,'dddddd','extraordinaria',1,1,0,'2024-05-12','2024-05-13','13:30:00','13:40:00',0,'aaaaaa',3,'solicitada','-'),(12,3,'aaaaaa','complementaria',3,1,1,'2024-05-12','2024-05-13','17:50:00','18:50:00',1,'esta',20,'solicitada','-'),(13,1,'AAAAA','complementaria',4,0,0,'2004-02-10','2004-02-11','10:00:00','09:00:00',1,'Acampada',10,'solicitada','-'),(14,5,'FFFFFFFFF','extraordinaria',2,1,1,'2024-05-13','2024-05-14','18:40:00','19:40:00',1,'NINGUNO',85,'solicitada','-'),(16,4,'PRUEBAPRUEBA','complementaria',2,0,0,'2024-05-14','2024-05-15','20:08:00','21:08:00',1,'PRUEBAPRUEBA',85,'solicitada','-'),(17,6,'Solicitud 16','complementaria',3,1,1,'2024-05-14','2024-05-15','21:42:00','22:42:00',1,'-',79,'aprobada','TODO CORRECTO!'),(18,6,'Solicitud 17','extraordinaria',1,1,1,'2024-05-16','2024-05-17','21:47:00','22:47:00',0,'-',43,'realizada','-'),(19,1,'aaaaaa','extraordinaria',1,1,1,'2024-05-15','2024-05-15','20:00:00','20:30:00',1,'aaaaaa',22,'realizada',''),(20,1,'aaaaaa','complementaria',3,1,1,'2024-05-15','2024-05-15','20:32:00','23:20:00',0,'aaaa',85,'aprobada','aprobada'),(21,1,'Hola','extraordinaria',1,0,1,'2024-05-17','2024-05-18','11:00:00','10:00:00',1,'Hola',171,'aprobada','aprobada maquina');
/*!40000 ALTER TABLE `solicitud` ENABLE KEYS */;
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
