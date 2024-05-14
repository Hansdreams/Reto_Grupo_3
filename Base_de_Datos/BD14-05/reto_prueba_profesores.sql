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
-- Table structure for table `profesores`
--

DROP TABLE IF EXISTS `profesores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesores` (
  `ID_Prof` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(25) NOT NULL,
  `Apellidos` varchar(45) NOT NULL,
  `DNI` char(9) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Estado` tinyint NOT NULL,
  `Departamento` int NOT NULL,
  `Perfil` enum('superusuario','equipo_directivo','administrador','profesor') NOT NULL,
  PRIMARY KEY (`ID_Prof`),
  UNIQUE KEY `DNI` (`DNI`),
  UNIQUE KEY `Email` (`Email`),
  UNIQUE KEY `DNI_UNIQUE` (`DNI`),
  UNIQUE KEY `Email_UNIQUE` (`Email`),
  KEY `fk_Profesores_Departamento` (`Departamento`),
  CONSTRAINT `fk_Profesores_Departamento` FOREIGN KEY (`Departamento`) REFERENCES `departamentos` (`idDepartamentos`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesores`
--

LOCK TABLES `profesores` WRITE;
/*!40000 ALTER TABLE `profesores` DISABLE KEYS */;
INSERT INTO `profesores` VALUES (1,' Pablo','Sanz Campo','22221940W','pablo.sanzcampo@educantabria.es',1,9,'profesor'),(2,' Luis Manuel','Serrano Ceballos','84434964K','luism.serranoceballos@educantabria.es',1,12,'profesor'),(3,' Olatz','San Miguel Martinez','04266894X','olatz.sanmiguelmart@educantabria.es',1,12,'profesor'),(4,' Alejandro','Carrera Ruiz','21156345S','alejandro.carreraruiz@educantabria.es',1,17,'profesor'),(5,' Lulu','Ortiz Royuela','63568530G','lulu.ortiz01@educantabria.es',1,17,'profesor'),(6,' Raul','Reigadas Fonfria','17394999M','rreigadasfo01@educantabria.es',1,12,'profesor'),(7,' Marcos','Fernandez Vallejo','58388245M','marcos.fernandezvallejo@educantabria.es',1,11,'profesor'),(8,' Jonatan','Hevia Ortiz','52821689C','joheviaort01@educantabria.es',1,9,'profesor'),(9,' Manuel','Gomez Arronte','78117930Y','mgomezarro01@educantabria.es',1,6,'profesor'),(10,' Yolanda','Escudero Valdes','08845506J','yescuderova01@educantabria.es',1,17,'profesor'),(11,' David','Benito Almeida','65003264B','david.benitoalmeida@educantabria.es',1,3,'profesor'),(12,' Francisco Jose','Trueba Rojas','55452491Y','fjtruebaroja01@educantabria.es',1,12,'profesor'),(13,' Maria','Gutierrez Casta�eda','68567981Z','mgutierrez95@educantabria.es',1,17,'profesor'),(14,' David','Sanchez Junco','03719263F','david.sanchezjunco@educantabria.es',1,5,'profesor'),(15,' Victoria','Martinez Balbas','53554206S','vmartinezba01@educantabria.es',1,8,'profesor'),(16,'Gustavo','Bautista Pocohuanca','1234578Y','gustavo.bautistapocohuanca@educantabria.es',1,2,'administrador'),(999,'super','usuario','12345678Z','super.usuario@educantabria.es',1,20,'superusuario');
/*!40000 ALTER TABLE `profesores` ENABLE KEYS */;
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
