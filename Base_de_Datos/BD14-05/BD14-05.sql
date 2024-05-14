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
INSERT INTO `actividadprogramada` VALUES (6,3,'prueba3','Complementaria',4,0,1,'2024-05-15','2024-05-16','16:30:00','17:30:00',1,'prueba',1,10,540.50,40,'esto es una prueba'),(17,6,'Solicitud 16','Complementaria',3,1,1,'2024-05-14','2024-05-15','21:42:00','22:42:00',1,'-',1,79,500.50,50,'NINGUNO');
/*!40000 ALTER TABLE `actividadprogramada` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `cursosact`
--

DROP TABLE IF EXISTS `cursosact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cursosact` (
  `IdAct` int NOT NULL,
  `idCur` int NOT NULL,
  KEY `fk_CursosAct_Cursos` (`idCur`),
  CONSTRAINT `fk_CursosAct_Cursos` FOREIGN KEY (`idCur`) REFERENCES `cursos` (`ID_CURSO`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursosact`
--

LOCK TABLES `cursosact` WRITE;
/*!40000 ALTER TABLE `cursosact` DISABLE KEYS */;
INSERT INTO `cursosact` VALUES (16,2);
/*!40000 ALTER TABLE `cursosact` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `fotos`
--

DROP TABLE IF EXISTS `fotos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fotos` (
  `idFotos` int NOT NULL,
  `url` varchar(225) NOT NULL,
  `descripcion` text NOT NULL,
  `id_actividad` int NOT NULL,
  PRIMARY KEY (`idFotos`),
  KEY `fk_Fotos_ActividadProgramada` (`id_actividad`),
  CONSTRAINT `fk_Fotos_ActividadProgramada` FOREIGN KEY (`id_actividad`) REFERENCES `actividadprogramada` (`IdSolicitud`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fotos`
--

LOCK TABLES `fotos` WRITE;
/*!40000 ALTER TABLE `fotos` DISABLE KEYS */;
INSERT INTO `fotos` VALUES (1,'a','prueba',6),(2,'a','prueba',6);
/*!40000 ALTER TABLE `fotos` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `gruposact`
--

DROP TABLE IF EXISTS `gruposact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gruposact` (
  `IdAct` int NOT NULL,
  `idGrupo` int NOT NULL,
  KEY `fk_GruposAct_Grupos` (`idGrupo`),
  KEY `fk_gruposact_Solicitud` (`IdAct`),
  CONSTRAINT `fk_GruposAct_Grupos` FOREIGN KEY (`idGrupo`) REFERENCES `grupos` (`ID_GRUPO`) ON UPDATE CASCADE,
  CONSTRAINT `fk_gruposact_Solicitud` FOREIGN KEY (`IdAct`) REFERENCES `solicitud` (`idSolicitud`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gruposact`
--

LOCK TABLES `gruposact` WRITE;
/*!40000 ALTER TABLE `gruposact` DISABLE KEYS */;
INSERT INTO `gruposact` VALUES (17,1),(17,2);
/*!40000 ALTER TABLE `gruposact` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `profesorparticipante`
--

DROP TABLE IF EXISTS `profesorparticipante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesorparticipante` (
  `Actividad` int NOT NULL,
  `IdProfesor` int NOT NULL,
  `Rol` enum('responsable','participante') NOT NULL,
  KEY `fk_ProfesorParticipante_Profesores` (`IdProfesor`),
  CONSTRAINT `fk_ProfesorParticipante_Profesores` FOREIGN KEY (`IdProfesor`) REFERENCES `profesores` (`ID_Prof`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesorparticipante`
--

LOCK TABLES `profesorparticipante` WRITE;
/*!40000 ALTER TABLE `profesorparticipante` DISABLE KEYS */;
INSERT INTO `profesorparticipante` VALUES (16,1,'responsable'),(16,2,'participante'),(16,3,'participante'),(17,2,'responsable'),(17,1,'participante'),(17,3,'participante');
/*!40000 ALTER TABLE `profesorparticipante` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud`
--

LOCK TABLES `solicitud` WRITE;
/*!40000 ALTER TABLE `solicitud` DISABLE KEYS */;
INSERT INTO `solicitud` VALUES (1,999,'Prueba','extraordinaria',20,1,1,'2011-05-24','2012-05-24','10:30:00','11:30:00',1,'Ninguno',10,'aprobada','kakakakak'),(2,999,'Prueba','extraordinaria',20,1,1,'2011-05-24','2012-05-24','10:30:00','11:30:00',1,'Ninguno',10,'solicitada','-'),(3,999,'Prueba','extraordinaria',20,1,1,'2024-05-11','2024-05-12','10:30:00','11:30:00',1,'Ninguno',10,'solicitada','-'),(4,3,'Prueba','extraordinaria',20,1,1,'2024-05-11','2024-05-12','10:30:00','11:30:00',1,'Ninguno',10,'solicitada','-'),(5,1,'aaa','complementaria',2,0,0,'2024-05-11','2024-05-12','10:30:00','11:30:00',0,'aaa',10,'solicitada',''),(6,3,'prueba3','complementaria',4,0,1,'2024-05-15','2024-05-16','16:30:00','17:30:00',1,'prueba',10,'aprobada','Aprobada'),(7,3,'bbbb','extraordinaria',1,1,1,'2024-05-15','2024-05-15','13:30:00','13:30:00',1,'aaaaa',1,'solicitada','-'),(8,999,'aaaaaa','complementaria',7,1,0,'2024-06-11','2024-06-12','10:30:00','11:30:00',1,'nueva ventana',10,'solicitada','-'),(9,999,'bbbbb','complementaria',17,0,1,'2024-05-12','2024-05-13','12:30:00','13:30:00',0,'asssss',30,'aprobada','bbbb'),(10,999,'ccc','complementaria',2,1,0,'2024-05-12','2024-05-13','13:30:00','14:30:00',1,'mmmmmm',2,'solicitada','-'),(11,999,'dddddd','extraordinaria',1,1,0,'2024-05-12','2024-05-13','13:30:00','13:40:00',0,'aaaaaa',3,'solicitada','-'),(12,3,'aaaaaa','complementaria',3,1,1,'2024-05-12','2024-05-13','17:50:00','18:50:00',1,'esta',20,'solicitada','-'),(13,1,'AAAAA','complementaria',4,0,0,'2004-02-10','2004-02-11','10:00:00','09:00:00',1,'Acampada',10,'solicitada','-'),(14,5,'FFFFFFFFF','extraordinaria',2,1,1,'2024-05-13','2024-05-14','18:40:00','19:40:00',1,'NINGUNO',85,'solicitada','-'),(16,4,'PRUEBAPRUEBA','complementaria',2,0,0,'2024-05-14','2024-05-15','20:08:00','21:08:00',1,'PRUEBAPRUEBA',85,'solicitada','-'),(17,6,'Solicitud 16','complementaria',3,1,1,'2024-05-14','2024-05-15','21:42:00','22:42:00',1,'-',79,'aprobada','TODO CORRECTO!'),(18,6,'Solicitud 17','extraordinaria',1,1,1,'2024-05-16','2024-05-17','21:47:00','22:47:00',0,'-',43,'realizada','-');
/*!40000 ALTER TABLE `solicitud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transporteact`
--

DROP TABLE IF EXISTS `transporteact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transporteact` (
  `IdActividad` int NOT NULL,
  `tipoTransporte` int NOT NULL,
  KEY `fk_TransporteAct_Transportes` (`tipoTransporte`),
  KEY `fk_transporteact_ActividadProgramada` (`IdActividad`),
  CONSTRAINT `fk_transporteact_ActividadProgramada` FOREIGN KEY (`IdActividad`) REFERENCES `actividadprogramada` (`IdSolicitud`),
  CONSTRAINT `fk_TransporteAct_Transportes` FOREIGN KEY (`tipoTransporte`) REFERENCES `transportes` (`idTransportes`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transporteact`
--

LOCK TABLES `transporteact` WRITE;
/*!40000 ALTER TABLE `transporteact` DISABLE KEYS */;
INSERT INTO `transporteact` VALUES (6,1),(6,2);
/*!40000 ALTER TABLE `transporteact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transportes`
--

DROP TABLE IF EXISTS `transportes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transportes` (
  `idTransportes` int NOT NULL,
  `TipoTransporte` varchar(45) NOT NULL,
  PRIMARY KEY (`idTransportes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transportes`
--

LOCK TABLES `transportes` WRITE;
/*!40000 ALTER TABLE `transportes` DISABLE KEYS */;
INSERT INTO `transportes` VALUES (1,'Bus'),(2,'Caminando'),(3,'Taxi'),(4,'Avion');
/*!40000 ALTER TABLE `transportes` ENABLE KEYS */;
UNLOCK TABLES;

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

-- Dump completed on 2024-05-14 20:06:22
