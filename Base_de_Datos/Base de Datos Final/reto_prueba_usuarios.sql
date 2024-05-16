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
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idUsuarios` int NOT NULL,
  `Email` varchar(50) NOT NULL,
  `PWD` varchar(20) NOT NULL,
  PRIMARY KEY (`idUsuarios`),
  UNIQUE KEY `Email` (`Email`),
  UNIQUE KEY `Email_UNIQUE` (`Email`),
  CONSTRAINT `fk_Usuarios_Profesores2` FOREIGN KEY (`idUsuarios`) REFERENCES `profesores` (`ID_Prof`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'pablo.sanzcampo@educantabria.es','pabsan'),(2,'luism.serranoceballos@educantabria.es','luiser'),(3,'olatz.sanmiguelmart@educantabria.es','olasan'),(4,'alejandro.carreraruiz@educantabria.es','alecar'),(5,'lulu.ortiz01@educantabria.es','lulort'),(6,'rreigadasfo01@educantabria.es','raurei'),(7,'marcos.fernandezvallejo@educantabria.es','marfer'),(8,'joheviaort01@educantabria.es','jonhev'),(9,'mgomezarro01@educantabria.es','mangom'),(10,'yescuderova01@educantabria.es','yolesc'),(11,'david.benitoalmeida@educantabria.es','davben'),(12,'fjtruebaroja01@educantabria.es','fratru'),(13,'mgutierrez95@educantabria.es','margut'),(14,'david.sanchezjunco@educantabria.es','davsan'),(15,'vmartinezba01@educantabria.es','vicmar'),(16,'gustavo.bautistapocohuanca@educantabria.es','gusbau'),(999,'super.usuario@educantabria.es','1234');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-16 17:27:20
