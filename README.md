<h1 style="text-align: center;">RETO GRUPO 3</h1>


 # ÍNDICE
 

## BASE DE DATOS 

1. [Introducción de la base de datos](#introducción)
2. [Script de creación de la base de datos](#script)
3. [Tabla de la base de datos](#tablas)
4. [Selects de la base de datos](#selects)
5. [Facilitar el uso de inserts](#inserts)

## WEB

1. [Estructura de la Página Web](#estructura-de-la-página-web)
   - [Estructura General](#estructura-general)
   - [Decisiones de Diseño](#decisiones-de-diseño)
2. [Breve Explicación de los Tipos de Estilo Utilizados](#breve-explicación-de-los-tipos-de-estilo-utilizados)
   - [Estilo General (CSS)](#estilo-general-css)
   - [Clases CSS Específicas](#clases-css-específicas)
3. [Contenidos Incluidos en la Web](#contenidos-incluidos-en-la-web)
   - [Cabarceno.html](#cabarcenohtml)
   - [Galeria.html](#galeriahtml)
   - [Inicio.html](#iniciohtml)

## JAVA

1. [Aplicación](#aplicación)
2. [Diagrama de clases](#diagrama-de-clases)
3. [Diagrama de clases de Uso](#diagrama-de-clases-de-uso)
4. [Explicación del Funcionamiento General de la Aplicación](#explicacion-del-funcionamiento-general-de-la-aplicacion)
    - [Inicio de Sesión](#inicio-de-sesion)
    - [Ventana para los profesores](#ventana-para-los-profesores)
    - [Ventana para los Administradores/Equipo Directivo](#ventanapara-los-administradores-/-equipo-directivo)
    - [Ingreso como Super Usuario](#ingreso-como-super-ususario)
5. [Métodos Especiales dentro de la Aplicación](#métodos-especiales-dentro-de-la-aplicación)  
    - [Método cambiar Estado](#método-cambiar-estado)
    - [Método reservar Bus](#método-reservar-bus)  

## IMPLEMENTACIÓN Y DESPLIEGUE

1. [Tabla sobre la implementación y despliegue](#tabla-sobre-la-implementación-y-despliegue)

## Documentación Adicional del proyecto

  - [MANUAL DE USUARIO](https://github.com/Hansdreams/Reto_Grupo_3/blob/master/Documentacion/Manual%20de%20Usuario%20de%20la%20Aplicaci%C3%B3n%20de%20escritorio%20RET.pdf
)
 
  
  - [GUÍA DE DESPLIEGUE](https://github.com/Hansdreams/Reto_Grupo_3/blob/master/Gu%C3%ADa%20de%20despliegue%20Grupo%203.pdf)
 
    

  - [GENERAR INFORMES](https://github.com/Hansdreams/Reto_Grupo_3/blob/master/Documentacion/GENERAR_INFORMES_BD_G3.pdf)

  

<br>
<br>
<hr>

[![mysql-logo.png](https://i.postimg.cc/HL1S7PbT/mysql-logo.png)](https://postimg.cc/G4zPWKMf)

[![logo.png](https://i.postimg.cc/QxDvRCFX/logo.png)](https://postimg.cc/23HHWzdJ)

# INTRODUCCIÓN
Para implementar el sistema de gestión de actividades complementarias y extraescolares del departamento ACE, es fundamental diseñar una base de datos robusta que permita almacenar y gestionar de manera eficiente la información relacionada con las actividades, profesores, departamentos, cursos, grupos y fotos. Esta base de datos servirá como el núcleo del sistema, facilitando la organización y seguimiento de todas las actividades académicas y administrativas del departamento.

A continuación, se presenta una introducción detallada a la estructura de la base de datos, incluyendo las tablas necesarias y sus respectivas relaciones, que han sido cuidadosamente diseñadas para asegurar la integridad y consistencia de los datos.

<br>

# Entidad/Relación
[![image.png](https://i.postimg.cc/DZxBVQ5R/image.png)](https://postimg.cc/hz79Tdk0)

# SCRIPT

```sql
-- MySQL Workbench Forward Engineering
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `RETO_PRUEBA` DEFAULT CHARACTER SET utf8 ;
USE `RETO_PRUEBA` ;

-- -----------------------------------------------------
-- Table `mydb`.`Profesores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RETO_PRUEBA`.`Profesores` (
  `ID_Prof` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(25) NOT NULL,
  `Apellidos` VARCHAR(45) NOT NULL,
  `DNI` char(9) NOT NULL UNIQUE,
  `Email` VARCHAR(50) NOT NULL UNIQUE,
  `Estado` TINYINT NOT NULL,
  `Departamento` INT NOT NULL,
  `Perfil` ENUM('superusuario', 'equipo_directivo', 'administrador', 'profesor') NOT NULL,
  PRIMARY KEY (`ID_Prof`),
  UNIQUE INDEX `DNI_UNIQUE` (`DNI` ASC) VISIBLE,
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) VISIBLE,
  CONSTRAINT fk_Profesores_Departamento FOREIGN KEY (Departamento) REFERENCES Departamentos (idDepartamentos) ON UPDATE CASCADE)
ENGINE = InnoDB;

insert into profesores (ID_Prof,Nombre,Apellidos,DNI,Email,Estado,Departamento,Perfil)
values('999','super','usuario','12345678Z','super.usuario@educantabria.es',1,20,'superusuario');

-- -----------------------------------------------------
-- -----------------------------------------------------
-- Table `mydb`.`Departamentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RETO_PRUEBA`.`Departamentos` (
  `idDepartamentos` INT NOT NULL,
  `COD_DE` CHAR(3) NOT NULL UNIQUE,
  `Nombre` VARCHAR(50) NOT NULL,
  `JEFE` int,
  PRIMARY KEY (`idDepartamentos`),
  UNIQUE INDEX `COD_DE_UNIQUE` (`COD_DE` ASC) VISIBLE)

ENGINE = InnoDB;

INSERT INTO Departamentos (idDepartamentos, COD_DE, Nombre, JEFE) VALUES
(1, 'BG', 'Biologia y Geologia', NULL),
(2, 'DIB', 'Dibujo', NULL),
(3, 'ECO', 'Economia', NULL),
(4, 'EF', 'Educacion Fisica', NULL),
(5, 'FIL', 'Filosofia', NULL),
(6, 'FQ', 'Fisica y Quimica', NULL),
(7, 'FRA', 'Frances', NULL),
(8, 'GH', 'Geografia e Historia', NULL),
(9, 'ING', 'Ingles', NULL),
(10, 'LAT', 'Latin', NULL),
(11, 'LEN', 'Lengua Castellana y Literatura', NULL),
(12, 'MAT', 'Matematicas', NULL),
(13, 'MUS', 'Musica', NULL),
(14, 'TEC', 'Tecnologia', NULL),
(15, 'AG', 'Administracion y Gestion', NULL),
(16, 'FOL', 'Formacion y Orientacion Laboral', NULL),
(17, 'INF', 'Informatica y Comunicaciones', NULL),
(18, 'FM', 'Fabricacion Mecanica', NULL),
(19, 'TMV', 'Transporte y Mantenimiento de Vehiculos', NULL),
(20, 'S-U', 'Superusuario',999);

-- -----------------------------------------------------
-- Table `mydb`.`Cursos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RETO_PRUEBA`.`Cursos` (
  `ID_CURSO` int NOT NULL,
  `COD_CUR` VARCHAR(5) NOT NULL,
  `descripCur` VARCHAR(145) NOT NULL,
  `etapa` ENUM('ESO', 'BACHILLERATO', 'FPGS', 'FPGM', 'FPB', 'FBCE') NOT NULL,
  `estadoCur` TINYINT(1) NOT NULL,
  PRIMARY KEY (`ID_CURSO`),
  UNIQUE INDEX `COD_CUR_UNIQUE` (`COD_CUR` ASC) VISIBLE)
ENGINE = InnoDB;

INSERT INTO cursos (ID_CURSO, COD_CUR, descripCur, etapa, estadoCur) VALUES
(1, 'ESO1', 'Educacion Secundaria Obligatoria - 1º', 'ESO', 1),
(2, 'ESO2', 'Educacion Secundaria Obligatoria - 2º', 'ESO', 1),
(3, 'ESO3', 'Educacion Secundaria Obligatoria - 3º', 'ESO', 1),
(4, 'ESO4', 'Educacion Secundaria Obligatoria - 4º', 'ESO', 1),
(5, 'BCH1', 'Bachillerato 1º', 'Bachillerato', 1),
(6, 'BCH2', 'Bachillerato 2º', 'Bachillerato', 1),
(7, 'FM1', 'Fabricación y montaje - 1º', 'FPGM', 1),
(8, 'FM2', 'Fabricación y montaje - 2º', 'FPGM', 1),
(9, 'MV1', 'Mantenimiento de Vehiculos - 1º', 'FPGM', 1),
(10, 'MV2', 'Mantenimiento de Vehiculos - 2º', 'FPGM', 1),
(11, 'CAR1', 'Carroceria - 1º', 'FPGM', 1),
(12, 'CAR2', 'Carroceria - 2º', 'FPGM', 1),
(13, 'EVA1', 'Electromecánica de Vehiculos Automoviles - 1º', 'FPGM', 1),
(14, 'EVA2', 'Electromecánica de Vehiculos Automoviles - 2º', 'FPGM', 1),
(15, 'SMR1', 'Sistemas Microinformaticos y Redes - 1º', 'FPGM', 1),
(16, 'SMR2', 'Sistemas Microinformaticos y Redes - 2º', 'FPGM', 1),
(17, 'AF1', 'Administracion y Finanzas - 1º', 'FPGS', 1),
(18, 'AF2', 'Administracion y Finanzas - 2º', 'FPGS', 1),
(19, 'DAM1', 'Desarrollo de Aplicaciones Multiplataforma - 1º', 'FPGS', 1),
(20, 'DAM2', 'Desarrollo de Aplicaciones Multiplataforma - 2º', 'FPGS', 1),
(21, 'DAW1', 'Desarrollo de Aplicaciones Web - 1º', 'FPGS', 1),
(22, 'DAW2', 'Desarrollo de Aplicaciones Web - 2º', 'FPGS', 1),
(23, 'DFM1', 'Diseño en Fabricacion Mecanica - 1º', 'FPGS', 1),
(24, 'DFM2', 'Diseño en Fabricacion Mecanica - 2º', 'FPGS', 1);


-- -----------------------------------------------------
-- Table `mydb`.`Grupos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RETO_PRUEBA`.`Grupos` (
  `ID_GRUPO` int NOT NULL,
  `COD_GRUP` VARCHAR(8) NOT NULL,
  `idCurso` int NOT NULL,
  `alumnos` INT NOT NULL,
  `estadoGrup` TINYINT(1) NOT NULL,
  PRIMARY KEY (`ID_GRUPO`),
  CONSTRAINT fk_Grupos_Cursos FOREIGN KEY (idCurso) REFERENCES Cursos (ID_CURSO) ON UPDATE CASCADE)

ENGINE = InnoDB;

INSERT INTO grupos (ID_GRUPO, COD_GRUP, idCurso, alumnos, estadoGrup) VALUES
(1, 'ESO1A', 1, 22, 1),
(2, 'ESO1B', 1, 21, 1),
(3, 'ESO1C', 1, 20, 1),
(4, 'ESO1D', 1, 22, 1),
(5, 'ESO2A', 2, 19, 1),
(6, 'ESO2B', 2, 21, 1),
(7, 'ESO2C', 2, 20, 1),
(8, 'ESO2D', 2, 19, 1),
(9, 'ESO3A', 3, 25, 1),
(10, 'ESO3B', 3, 24, 1),
(11, 'ESO3C', 3, 25, 1),
(12, 'ESO4A', 4, 19, 1),
(13, 'ESO4B', 4, 18, 1),
(14, 'ESO4C', 4, 18, 1),
(15, 'ESO4D', 4, 17, 1),
(16, 'BCH1HCS', 5, 21, 1),
(17, 'BCH1CT', 5, 22, 1),
(18, 'BCH2HCS', 6, 20, 1),
(19, 'BDH2CT', 6, 19, 1),
(20, 'FM1', 7, 24, 1),
(21, 'FM2', 8, 18, 1),
(22, 'MV1', 9, 25, 1),
(23, 'MV2', 10, 15, 1),
(24, 'CAR1', 11, 25, 1),
(25, 'CAR2', 12, 16, 1),
(26, 'EVA1', 13, 24, 1),
(27, 'EVA2', 14, 14, 1),
(28, 'SMR1', 15, 28, 1),
(29, 'SMR2', 16, 19, 1),
(30, 'AF1', 17, 24, 1),
(31, 'AF2', 18, 25, 1),
(32, 'DAM1', 19, 28, 1),
(33, 'DAM2', 20, 35, 1),
(34, 'DAW1', 21, 24, 1),
(35, 'DAW2', 22, 18, 1),
(36, 'DFM1', 23, 16, 1),
(37, 'DFM2', 24, 9, 1);


-- -----------------------------------------------------
-- Table `mydb`.`Usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RETO_PRUEBA`.`Usuarios` (
  `idUsuarios` INT NOT NULL,
  `Email` VARCHAR(50) NOT NULL UNIQUE,
  `PWD` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idUsuarios`),
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) VISIBLE,
  CONSTRAINT fk_Usuarios_Profesores2 FOREIGN KEY (idUsuarios) REFERENCES Profesores (ID_Prof) ON UPDATE CASCADE)

ENGINE = InnoDB;

insert into usuarios(idUsuarios,Email,PWD)values('999','super.usuario@educantabria.es','1234');

-- -----------------------------------------------------
-- Table `mydb`.`Solicitud [ACEX]`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RETO_PRUEBA`.`Solicitud` (
  `idSolicitud` INT NOT NULL auto_increment,
  `Solicitante` INT NOT NULL,
  `nombreAct` VARCHAR(45) NOT NULL,
  `tipoActividad` ENUM('Extraordinaria', 'Complementaria') NOT NULL,
  `Departamento` INT NOT NULL,
  `Prevista` TINYINT(1) NOT NULL,
  `Transporte` TINYINT(1) NOT NULL,
  `FechaInicial` DATE NOT NULL,
  `FechaFinal` DATE NOT NULL,
  `HoraInicial` TIME NOT NULL,
  `HoraFinal` TIME NOT NULL,
  `Alojamiento` TINYINT(1) NULL,
  `comentarioAdicional` VARCHAR(500) NOT NULL,
  `AlumnosMAX` int not null,
  `Estado` ENUM('Solicitada', 'Realizada', 'Denegada', 'Aprobada') NOT NULL default 'solicitada',
  `ConsultaEstado` VARCHAR(300) NOT NULL default '',
  PRIMARY KEY (`idSolicitud`),
  constraint fk_Solicitud_Profesores foreign key (Solicitante) references profesores (ID_Prof) on update cascade,
  CONSTRAINT fk_Solicitud_Departamento FOREIGN KEY (Departamento) REFERENCES Departamentos (idDepartamentos) ON UPDATE CASCADE)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `mydb`.`Transportes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RETO_PRUEBA`.`Transportes` (
  `idTransportes` INT NOT NULL,
  `TipoTransporte` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTransportes`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TransporteAct`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RETO_PRUEBA`.`TransporteAct` (
  `IdActividad` INT NOT NULL,
  `tipoTransporte` INT NOT NULL,
  CONSTRAINT fk_TransporteAct_ActividadProgramada FOREIGN KEY (IdActividad) REFERENCES ActividadProgramada (IdSolicitud) ON UPDATE CASCADE,
  CONSTRAINT fk_TransporteAct_Transportes FOREIGN KEY (tipoTransporte) REFERENCES Transportes (idTransportes) ON UPDATE CASCADE)

ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`ActividadProgramada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RETO_PRUEBA`.`ActividadProgramada` (
  `IdSolicitud` INT NOT NULL,
  `Solicitante` INT NOT NULL,
  `nombreAct` VARCHAR(45) NOT NULL,
  `tipoActividad` ENUM('Extraordinaria', 'Complementaria') NOT NULL,
  `Departamento` INT NOT NULL,
  `Prevista` TINYINT(1) NOT NULL,
  `Transporte` TINYINT(1) NOT NULL,
  `FechaInicial` DATE NOT NULL,
  `FechaFinal` DATE NOT NULL,
  `HoraInicial` TIME NOT NULL,
  `HoraFinal` TIME NOT NULL,
  `Alojamiento` TINYINT(1) NULL,
  `comentarioAdicional` VARCHAR(200) NOT NULL,
  `TransporteContrato` TINYINT(1) NOT NULL,
  `AlumnosMAX` int not null,
  `Presupuesto` DECIMAL(6,2) NOT NULL,
  `AlumnosParticipantes` INT NOT NULL,
  `Comentario` TEXT NOT NULL,
  PRIMARY KEY (`IdSolicitud`),
CONSTRAINT fk_ActiviadadProgramada_Solicitud FOREIGN KEY (IdSolicitud) REFERENCES Solicitud (idSolicitud) ON UPDATE CASCADE)

ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ProfesorParticipante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RETO_PRUEBA`.`ProfesorParticipante` (
  `Actividad` INT NOT NULL,
  `IdProfesor` INT NOT NULL,
  `Rol` ENUM('Responsable', 'Participante') NOT NULL,
  CONSTRAINT fk_ProfesorParticipante_Profesores FOREIGN KEY (IdProfesor) REFERENCES Profesores (ID_Prof) ON UPDATE CASCADE,
  CONSTRAINT fk_ProfesorParticipante_Solicitud FOREIGN KEY (Actividad) REFERENCES Solicitud (idSolicitud) ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Fotos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RETO_PRUEBA`.`Fotos` (
  `idFotos` INT NOT NULL,
  `url` VARCHAR(225) NOT NULL,
  `descripcion` TEXT NOT NULL,
  `id_actividad` INT NOT NULL,
  PRIMARY KEY (`idFotos`),
    CONSTRAINT fk_Fotos_ActividadProgramada FOREIGN KEY (id_actividad) REFERENCES ActividadProgramada (IdSolicitud) ON UPDATE CASCADE)

ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`GruposAct`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RETO_PRUEBA`.`GruposAct` (
  `IdAct` INT NOT NULL,
  `idGrupo` INT NOT NULL,
  CONSTRAINT fk_GruposAct_Solicitud FOREIGN KEY (IdAct) REFERENCES Solicitud (IdSolicitud) ON UPDATE CASCADE,
  CONSTRAINT fk_GruposAct_Grupos FOREIGN KEY (idGrupo) REFERENCES Grupos (ID_GRUPO) ON UPDATE CASCADE)

ENGINE = InnoDB;


Create table if not exists `RETO_PRUEBA`.`CursosAct`(
`IdAct` INT NOT NULL,
`idCur` INT NOT NULL,
CONSTRAINT fk_CursosAct_Solicitud FOREIGN KEY (IdAct) REFERENCES Solicitud (IdSolicitud) ON UPDATE CASCADE,
CONSTRAINT fk_CursosAct_Cursos FOREIGN KEY (idCur) REFERENCES Cursos (ID_CURSO) ON UPDATE CASCADE)

ENGINE = InnoDB;

alter table departamentos add CONSTRAINT fk_Departamentos_Profesores FOREIGN KEY (JEFE) REFERENCES Profesores (ID_Prof) ON UPDATE CASCADE;

-- -----------------------------------------------------
-- Table `mydb`.`Alojamientos`
-- -----------------------------------------------------



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



```
# Tablas

<br>


| Nombre de columna | Tipo de dato | Descripcion |
| --- | --- | --- |
**Profesores**
| --- | --- | --- |
| ID_Prof | INT |Identificador de profesor |
| Nombre | VARCHAR(25) | Nombre |
| Apellidos | VARCHAR(45) | Apellidos |
| DNI | INT | DNI del profesor |
| Email | VARCHAR(50) |Email del profesor |
| Estado | TINYINT | Estado del profesor (activo/inactivo) |
| Departamento | INT | Departamento del profesor (foreign key) |
| Perfil | ENUM('superusuario', 'equipo_directivo', 'administrador', 'profesor') | Tipo de perfil |
DESCRIPCION | Informacion personal de profesor, *Email* para el acceso a la aplicacion y *departamento* al que pertenece, el perfil sabremos a que nivel puede entrar en la aplicación |
**Departamentos**
| --- | --- | --- |
| idDepartamentos | INT | Identificador del departamento |
| COD_DE | CHAR(3) | Codigo de departamento |
| Nombre | VARCHAR(50) | Nombre de departamento |
| JEFE | INT  | *ID_Prof* del profesor jefe de cada departamento |
|DESCRIPCION|Informacion del departamento|
**Cursos**
| --- | --- | --- |
| ID_CURSO|INT NOT NULL|Identificador de curso|
| COD_CUR | VARCHAR(5) | Codigo de curso |
| descripCur | VARCHAR(145) | Descripcion de curso |
| etapa | ENUM('ESO', 'BACHILLERATO', 'FPGS', 'FPGM', 'FPB', 'FBCE') | Estapa del curso |
| estadoCur | TINYINT(1) | Estado del curso (activo/inactivo) |
|DESCRIPCION|Información sobre el curso y su estado|
**Grupos**
| --- | --- | --- |
| ID_GRUPO|INT NOT NULL |Identificador de grupo 
| COD_GRUP | VARCHAR(8) | Codigo de grupo |
| ID_CURSO|INT NOT NULL|Identificador de curso (FOREING KEY)|
| alumnos | INT | Nº de estudiantes |
| estadoGrup | TINYINT(1) | Estado de grupo (activo/inactivo) |
|DESCRIPCION|Informacion del grupo, el curso *ID_CURSO* al que pertenece y su estado|
**Usuarios**
| --- | --- | --- |
| idUsuarios | INT | Identificador de usuario |
| Email | VARCHAR(50) | Email de usuario |
| PWD | VARCHAR(20) | Contraseña de usuario |
|DESCRIPCION|Tabla para el acceso a la aplicacion, el *Email* del profesor junto a su contraseña*PWD*|
**Solicitud**
| --- | -   -- | --- |
| idSolicitud | INT | Identificador de solicitud |
| Solicitante | INT | ID de solicitante (foreign key) |
| nombreAct | VARCHAR(45) | Nombre de actividad |
| tipoActividad | ENUM('Extraordinaria', 'Complementaria') | TIpo de actividad |
| Departamento | INT | ID de departamento (foreign key) |
| Prevista | TINYINT(1) | Actividad prevista (S/N)|
| Transporte | TINYINT(1) | Requiere de transporte (S/N) |
| FechaInicial | DATE | Fecha inicial de la activdad |
| FechaFinal | DATE | Fecha final de la activdad |
| HoraInicial | DATETIME | Hora inicial |
| HoraFinal | DATETIME | Hora final |
| Alojamiento | TINYINT(1) | Requiere de alojamiento (S/N) |
| AlumnosMAX| INT | Numero de alumnos maximos |
| comentarioAdicional | VARCHAR(200) | Comentario adicional sobre la activadad |
| Estado | ENUM('Solicitada', 'Realizada', 'Denegada', 'Aprobada') | Estado de la actividad |
| ConsultaEstado | VARCHAR(300) | Comentario sobre estado de la actividad |
|DESCRIPCION|Informacion de la solicitud de la actividad propuesta, mantiene relacion con profesores *Solicitante-ID_Prof*, departamentos *idDepartamentos*, dentro de esta tabla aparece el estado de la solicitud, donde se indica con un comentario el "Por qué" de su estado|
**Transportes**
| --- | --- | --- |
| idTransportes | INT | Identificador de transportes |
| TipoTransporte | VARCHAR(45) | Tipo de transporte |
| DESCRIPCION|Informacion de los tipos de transporte|
**TransporteAct**
| --- | --- | --- |
| IdActividad | INT | Identificador de la actividad |
| tipoTransporte | INT | Tipo de transporte (ID) (foreign key) |
|DESCRIPCION|Tabla sobre los transportes *ID_Transporte* involucrados en la actividad relacionando la actividad *IdActividad*|
**ActividadProgramada**
| --- | --- | --- |
| IdSolicitud | INT | Identificacion de solicitud (foreign key) |
| Solicitante | INT | ID de solicitante (foreign key) |
| nombreAct | VARCHAR(45) | Nombre de actividad |
| tipoActividad | ENUM('Extraordinaria', 'Complementaria') | TIpo de actividad |
| Departamento | INT | ID de departamento (foreign key) |
| Prevista | TINYINT(1) | Actividad prevista (S/N)|
| Transporte | TINYINT(1) | Requiere de transporte (S/N) |
| FechaInicial | DATE | Fecha inicial de la activdad |
| FechaFinal | DATE | Fecha final de la activdad |
| HoraInicial | DATETIME | Hora inicial |
| HoraFinal | DATETIME | Hora final |
| Alojamiento | TINYINT(1) | Requiere de alojamiento (S/N) |
| comentarioAdicional | VARCHAR(200) | Comentario adicional sobre la activadad |
| TransporteContrato | TINYINT(1) | Transporte Contratado (S/N) |
| AlojamientoContratado | TINYINT(1) | Alojamiento Contratado (S/N) |
| Presupuesto | DECIMAL(6,2) | Presupuesto total de la actividad (4 cifras maximo, +2 deciamles) |
| AlumnosMAX| INT | Numero de alumnos maximos |
| AlumnosParticipantes | INT | Numero de alumnos que participan |
| Comentario | TEXT | Additional comments |
|DESCRIPCION| Copia de la tabla *SOLICITUD* con algunos campos adicionales; cantidad de alumnos *AlumnosMAX*, transporte contradado *TransporteContrato*, presupuesto *presupuesto*, y comentario extenso *Comentario* sobre toda la actividad|
**ProfesorParticipante**
| --- | --- | --- |
| Actividad | INT | Identificacion de actividad (foreign key) |
| IdProfesor | INT | Identificacion de profesor (foreign key) |
| Rol | ENUM('Responsable', 'Participante') | Rol del profesor en la actividad |
|DESCRIPCION|Tabla que surge de la relación entre solicitud y profesores, para conocer todos los profesores que participan en las actividades, incluyendo su rol detro de esta|
**Fotos**
| --- | --- | --- |
| idFotos | INT | Identificador de la foto |
| url | VARCHAR(225) | URL de la imagen |
| descripcion | TEXT | Descripcion de la foto |
| id_actividad | INT | Identificacion de la actividad (foreign key) |
|DESCRIPCION|Informacion sobre la foto realizada en segun que actividad|
**GruposAct**
| --- | --- | --- |
| IdAct | INT | Identificacion de la actividad (foreign key) |
| CodGrupo | VARCHAR(8) | Codigo de grupo (foreign key) |
|DESCRIPCION|Tabla que surge de relacionar grupos con las actividades para conocer que grupos van a cada actividad|
**CursosAct**
| --- | --- | --- |
| IdAct | INT | Identificacion de la actividad (foreign key) |
| CodCur | VARCHAR(5) | Codigo de curso (foreign key) |
|DESCRIPCION|Tabla que surge de relacionar cursos con las actividades para conocer que cursos van a cada actividad|

<br>

# Selects
*Creamos diferentes selects para utilizarlos luego en caso necesario para filtrar de diferentes maneras:*
<br>

<h3> Select de profesores según el departamento al que pertenezcan </h3>

```sql
SELECT * FROM profesores 
INNER JOIN departamentos ON profesores.Departamento = departamentos.idDepartamentos 
WHERE idDepartamentos = ?;
```

<h3> Select de solicitud y profesor según ID de profesor </h3>

```sql
SELECT idSolicitud, solicitante, ID_Prof, Email, PWD  
FROM solicitud 
INNER JOIN profesores ON solicitud.Solicitante = profesores.ID_Prof
INNER JOIN usuarios ON profesores.ID_Prof = usuarios.idUsuarios 
WHERE Solicitante = ?;
```

<h3> select de profesores segun el departamento </h3>

```sql
 select ID_Prof,profesores.Nombre, apellidos,DNI , departamentos.Nombre from profesores inner join departamentos
 on profesores.Departamento=departamentos.idDepartamentos where ID_Prof=?;
```

 <h3> select de numero de alumnos segun el codigo de curso al que pertenecen </h3>

 ```sql
 select COD_CUR,sum(alumnos) as 'NumeroTotalAlumnos' from cursos inner join grupos on cursos.ID_CURSO=grupos.idCurso where cursos.COD_CUR=?;
 ```

<h3> Obtener detalles de solicitudes de actividades junto con sus estados actuales segun profesor </h3>

```sql
SELECT Solicitud.nombreAct, Profesores.Nombre AS Solicitante, Solicitud.Estado
FROM Solicitud
INNER JOIN Profesores ON Solicitud.Solicitante = Profesores.ID_Prof where ID_Prof=?;
```

<h3> Obtener detalles de actividades programadas y los profesores que participan en ellas </h3>

```sql
SELECT solicitud.nombreAct, Profesores.Nombre AS Participante, ProfesorParticipante.Rol
FROM solicitud
INNER JOIN ProfesorParticipante ON solicitud.idSolicitud = ProfesorParticipante.Actividad
INNER JOIN Profesores ON ProfesorParticipante.IdProfesor = Profesores.ID_Prof;
```

# Inserts

```sql
-- Inserts para la tabla Profesores
INSERT INTO `RETO_PRUEBA`.`Profesores` (`Nombre`, `Apellidos`, `DNI`, `Email`, `Estado`, `Departamento`, `Perfil`) VALUES ();

-- Inserts para la tabla Cursos
INSERT INTO `RETO_PRUEBA`.`Cursos` (`COD_CUR`, `descripCur`, `etapa`, `estadoCur`) VALUES ();

-- Inserts para la tabla Grupos
INSERT INTO `RETO_PRUEBA`.`Grupos` (`COD_GRUP`, `idCurso`, `alumnos`, `estadoGrup`) VALUES ();

-- Inserts para la tabla Solicitud
INSERT INTO `RETO_PRUEBA`.`Solicitud` (`Solicitante`, `nombreAct`, `tipoActividad`, `Departamento`, `Prevista`, `Transporte`, `FechaInicial`, `FechaFinal`, `HoraInicial`, `HoraFinal`, `Alojamiento`, `comentarioAdicional`, `AlumnosMAX`, `Estado`, `ConsultaEstado`) VALUES ();

-- Inserts para la tabla ActividadProgramada
INSERT INTO `RETO_PRUEBA`.`ActividadProgramada` (`Solicitante`, `nombreAct`, `tipoActividad`, `Departamento`, `Prevista`, `Transporte`, `FechaInicial`, `FechaFinal`, `HoraInicial`, `HoraFinal`, `Alojamiento`, `comentarioAdicional`, `TransporteContrato`, `AlumnosMAX`, `Presupuesto`, `AlumnosParticipantes`, `Comentario`) VALUES ();

-- Inserts para la tabla ProfesorParticipante
INSERT INTO `RETO_PRUEBA`.`ProfesorParticipante` (`Actividad`, `IdProfesor`, `Rol`) VALUES ();

-- Inserts para la tabla GruposAct
INSERT INTO `RETO_PRUEBA`.`GruposAct` (`IdAct`, `idGrupo`) VALUES ();

-- Inserts para la tabla CursosAct
INSERT INTO `RETO_PRUEBA`.`CursosAct` (`IdAct`, `idCur`) VALUES ();

```
<hr>

##  Estructura de la Página Web

La página web del IES Miguel Herrero Pereda está estructurada de manera clara y funcional, facilitando la navegación y el acceso a la información relevante para estudiantes, padres y visitantes en general. A continuación se detalla la estructura y las decisiones de diseño tomadas.

## Estructura General

1. **Cabecera (Header)**
    - **Logos**: Dos logos, uno a la izquierda (Logo Reto) y otro a la derecha (Logo IES), proporcionando identidad visual.

    - **Título Principal**: Un título prominente que indica el nombre del instituto y el grupo (IES Miguel Herrero Pereda - Grupo 3).

      [![header.png](https://i.postimg.cc/0yGhVyb3/header.png)](https://postimg.cc/3kRLR7jC)

2. **Navegación (Nav)**
    - **Menú de Navegación**: Un menú sencillo y cómodo de utilizar al que le hemos añadido un estilo vistoso que utiliza los colores del centro y el cual al clicar en los enlaces nos llevará a  a las páginas principales: Inicio, Galería y Contacto. 

      [![Barra.png](https://i.postimg.cc/qvXZsBR7/Barra.png)](https://postimg.cc/QFCbs3cL)

3. **Contenido Principal (Main Content)**
    - **Secciones y Artículos**: El contenido está organizado en secciones y artículos para una estructura lógica y legible. Aquí hemos decidido que sea una página de bienvenida por así decirlo, donde la gente que entre vea información sobre el centro. Esto lo reflejamos con una foto del centro y un vídeo sobre la historia de este. Además de texto descriptivo de cada elemento.

      [![Info-Inicio.png](https://i.postimg.cc/3xSfSSXM/Info-Inicio.png)](https://postimg.cc/4Yc1n12w)

    - **Tablas y Listas**: Utilizadas para presentar información detallada de manera clara, como en el caso de la actividad de visita al museo, la cual es un ejemplo de como presentamos la información de las actividades que se realizan en el centro, ya sean extraescolares o complementarias.

      [![Tablas.png](https://i.postimg.cc/kg6WCTrP/Tablas.png)](https://postimg.cc/87G7BBPZ)

4. **Pie de Página (Footer)**
  - Dentro de este tenemos los siguientes apartados los cuales los hemos querido poner de una forma clara y concisa para que se pueda ver la información básica del centro en todo momento dandoigual en que parte de la web estes.

    - **Redes Sociales**: Enlaces a las redes sociales del instituto, donde se puede adquirir más información de este y enterarnos de más actividades o iniciativas que se puedan llevar a cabo. Estos serían los enlaces de dichas redes:
        - [Educantabria](https://www.educantabria.es/web/ies-miguel-herrero-pereda)
        - [Instagram](https://www.instagram.com/ies.miguelherrero/)
        - [Facebook](https://www.facebook.com/profile.php?id=117957474897244&_rdr)

    - **Descripción del Instituto**: Información sobre la misión y visión del instituto.
    
    - **Información de Contacto**: Datos de contacto incluyendo teléfono, email y dirección, para que en cualquier momento seas capaz de poder contactar con el centro o de saber su ubicación sin tener que ir al apartado de contacto.

      [![Footer.png](https://i.postimg.cc/GpjwmJYq/Footer.png)](https://postimg.cc/t17mr6Bx)

5. **Imágenes**
    - **Galería de Imágenes**: Aquí presentamos la fotos de las actividades que se realizan durante el año escolar.Estas fotos acompañan y enriquecen el contenido textual, proporcionando un contexto visual de esas actividades. Si pinchamos encima de ellas nos lleva a la información antes mostrada de las actividades, la cuals e muestra en u tabla acompañada de dicha fotografía.

      [![Imagenes.png](https://i.postimg.cc/fbyjVMSp/Imagenes.png)](https://postimg.cc/GB0sNwQx)


####  Decisiones de Diseño

- **Logos y Títulos en el Header**: Como hemos visto más arriba en el documento, los logos ayudan a establecer una identidad visual y orientar al usuario desde el inicio. Además de los logos contamos con un título visible que obviamente nos muestra el nombre del centro escolar en el que nos encontramos. Hemos decidido utilizar este diseño porque nos parece que es fácil de ver y de entender la información que se muestra ahí.

- **Menú de Navegación (nav)**: Facilita la navegación rápida a las secciones principales al ser un estilo cómo de utilizar a lavez que bastante visual e intuitivo.Después de varios cambio en el "nav" hemos decidido ponerlo de la manera que se muestra porque nos parecía que a página se quedaba muy vacía en vez de ponerlo de forma horizontal debajo del header

- **Uso de Tablas y Listas**: Como hemos dicho antes muestran y rganizan la información de manera clara y concisa, facilitando la comprensión rápida de las actividades que se han realizado. Esta decisión fue rápida ya que nos pareció que era una forma bonita y sencilla de mostrar la información.

- **Secciones y Artículos**: Dividen el contenido en bloques manejables y fáciles de seguir. Como lo anterior, nos parecía muy visual y sencillo de ver y seguir.

- **Pie de Página**: Proporciona acceso a información adicional y métodos de contacto, cerrando la página con información útil. Diseño con bastantes cambios como el "nav", pero llegamos a un consenso rápido y solo introducimos ciertos adornos para dejarlo más adecuado al formato del resto de la página.

- **Imágenes**: Añaden valor visual y contexto a la información, haciendo la página más atractiva y dinámica. En este apartado no hubo mucha discusión porque el diseño que hemos utilizado es prácticamente el primero que hicimos, esta parte la tuvimos bastante clara a la hora de desarrollarla.

### Breve Explicación de los Tipos de Estilo Utilizados

- **Estilo General (CSS)**

  - **Colores y Fuentes**: Selección de colores y fuentes que son legibles y estéticamente agradables. Hemos utilizado los colores que representan al centro y al reto.

  - **Espaciado y Alineación**: Márgenes y padding adecuados para asegurar que el contenido esté bien distribuido y sea fácil de leer.

- **Clases CSS Específicas**
  - `.logo-left` y `.logo-right`: Estilos específicos para los logos en el header.
  
  ```
  .logo-left {
    flex: 0 0 auto;
    margin-top: -10px;
    background-color: white;
    border-radius: 5px;
  }

  ```
  ```
  .logo-right {
    flex: 0 0 auto;
    background-color: white;
    border-radius: 5px;
  }

  ```

  - `.main-title`: Estilo para el título principal, destacándolo visualmente.

  ```
  .main-title {
    text-align: center;
    justify-content: center;
    font-size: 50px;
  }
  ```

  - `.content-wrapper`: Clase para envolver el contenido principal, asegurando un margen adecuado.

  ```
  .content-wrapper {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 20px;
    background-color: #333;
    border: 1px solid rgb(255, 255, 255);
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    margin-top: 50px;
    margin-right: 20px;
  }

  ```
  - `.ImagenActividad`, `.Imagenes`, `.img-container`: Clases para dar formato a las imágenes y asegurarse de que se presenten de manera atractiva. En este caso solo he metido el fragmento de imagenes porque sería demasiado meter todo el resto del código.

  ```
  .Imagenes {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    justify-content: center;
    padding: 20px;
  } 

  ```


### Contenidos Incluidos en la Web

#### Inicio.html

- **Introducción al Instituto**: Descripción de la misión y los valores del IES Miguel Herrero Pereda.

- **Celebración del 60 Aniversario**: Información sobre el aniversario con un video embebido del acto de celebración.

- **Imagen Representativa del Instituto**: Foto principal que destaca la institución.

- **Información Adicional y Contacto**: Datos de contacto y enlaces a redes sociales.

Este diseño y estructura aseguran que la información relevante sea fácilmente accesible y que la experiencia del usuario sea intuitiva y agradable.


#### Galeria.html

- **Galería de Imágenes**: Muestra de diversas actividades del instituto a través de imágenes.
  - **Visita al Museo**
  - **Actividad en la Naturaleza**
  - **Aula Antigua**
  - **Visita a la Piscina**
  - **Bolera**
- **Descripción de las Imágenes**: Textos ocultos que se muestran al interactuar con las imágenes, proporcionando contexto adicional. y un mejor diseño más interactivo que dejar el texto fijo.

#### Contacto.html

- **Formulario de Opinión**: Sección donde los usuarios pueden enviar su nombre, email, teléfono, mensaje y edad a través de un formulario, es decir, dejar información para la posible comunicación del centro.

- **Ubicación del Instituto**: Mapa embebido de Google Maps mostrando la localización del IES Miguel Herrero Pereda.

- **Imagen del Instituto**: Foto destacada de la institución para acompañar el mapa.

- **Información de Contacto**: Datos detallados de contacto incluyendo teléfono, email y dirección física.

- **Enlaces a Redes Sociales**: Sección en el pie de página con enlaces a las cuentas de redes sociales del instituto.

Este diseño y estructura aseguran que la información de contacto sea fácilmente accesible y que los usuarios puedan comunicarse e interactuar con el instituto de manera intuitiva y eficiente.

<hr>


### Aplicación

Esta apliacion de escritorio esta diseñada para gestionar las actividaddes Complementarias y Extraescolares para el centro IES Miguel Herrero Pereda.

### Diagrama de clases

Diseñamos la aplicacion guiandonos del siguiente diagrama:

[![Diagrama-de-clases.png](https://i.postimg.cc/1XB9YzfM/Diagrama-de-clases.png)](https://postimg.cc/q6tVN4cC)

  Donde tenermos las diferentes clases que usa nuestra apliacion:

    En una solicitud puede tener cero o mas cursos y  cero a mas cursos pueden participar en una solicitud.
    En una solicitud puede tener cero o mas grupos dentro de ella y cero a mas grupos que pueden participar en una solicitud. 
    En una solicitud participa un solo departamento y en un departamento participan en una solicitud.
    Un profesor realiza una solicitud y una o mas solicitudes son realizadas por un profesor.
    Un profesor pùede ser un profesor partcipante y uno o mas participantes son profesores.
    Uno o mas profesores participan dentro de una solicitud y en una solicitud particpan uno o mas profesores.

### Diagrama de clases de Uso

[![Diagrama-de-uso.png](https://i.postimg.cc/fbYQrbPX/Diagrama-de-uso.png)](https://postimg.cc/d7Qf7Jwt)

|     |        | 
|--------------|--------------|
| **Caso de Uso:**| Crear solicitud| 
| **Actor:**| Profesor| 
| **Resumen:**| El profesor puede crear una o varios solicitudes para su posterior realizamiento de la actividad|
| **Precondiciones:**| Tener creado su usuario con su dato y tipo de perfil|
| **Postcondiciones:**| La solicitud se guardara en estado SOLICITADA |
| **-----------------------------------------------------------Flujo-----------------de---**| **Eventos---------------------------------------------------------------------------------------------**|
| **Actor** | **Sistema**|
| 1. El Profesor crea una solicitud (nombre de actividad, fecha de incio y final, etx). <br> 4. El profesor visualiza su solicitud y el estado en el que se encuentra. | 2. El sistema comprueba si los datos ingresador son correcto. <br> 3. El sistema guardar los datos en la BD. <br> 4. El sistema muesta la confirmacion. | 

 <br>

|     |        | 
|--------------|--------------|
| **Caso de Uso:**| Modificar solicitudes Aprobadas | 
| **Actor:**| Profesor| 
| **Resumen:**| El profesor puede modificar la solicitud y agregarle datos adicionales|
| **Precondiciones:**| Haber creado una solicitud previamente y que esta este en estado APROBADO|
| **Postcondiciones:**| - |
| **-----------------------------------------------------------Flujo-----------------de---**| **Eventos----------------------------------------------------------------------------**|
| **Actor** | **Sistema**|
| 1. El Profesor selecciona y modifica una solicitud en estado APROBADA. <br> 4. El profesor visualiza su solicitud en una tabla aparta que se encuentra. | 2. El sistema comprueba la solicitud el estado y ingresa los datos. <br> 3. El sistema guardar los datos en la BD. <br> 4. El sistema muesta la confirmacion. | 

 <br>

|     |        | 
|--------------|--------------|
| **Caso de Uso:**| Aprobar y Denegar Solicitudes | 
| **Actor:**| Equipo Directivo / Administrador| 
| **Resumen:**| El Equipo Directivo / Administrador podra visualizar todas las solicitudes hechar por los profesores.|
| **Precondiciones:**| Haber creado una solicitud previamente por parte del Profesor |
| **Postcondiciones:**| - |
| **-----------------------------------------------------------Flujo-----------------de---**| **Eventos----------------------------------------------------------------------------**|
| **Actor** | **Sistema**|
| 1. El Equipo Directivo / Administrador seleccionara una solicitud,<br> podra cambiar el estado en que se encuentra e ingresar un comentario. <br> 4. Equipo Directivo / Administrador visualizara la modificacion realizada |  2. El sistema guardar los datos en la BD. <br> 3. El sistema muesta la confirmacion. | 

 <br>

|     |        | 
|--------------|--------------|
| **Caso de Uso:**| Carga de Datos| 
| **Actor:**| Equipo Directivo / Administrador| 
| **Resumen:**| El Equipo Directivo / Administrador podra cargar y guardar los datos de los profesores del <br> Instituto|
| **Precondiciones:**| Tener el archivo CSV bien estructurado |
| **Postcondiciones:**| Guardara y creara cuantas personales para cada profesor, todos los profesores <br>se guardan con un perfil de Profesor |
| **-----------------------------------------------------------Flujo-----------------de---**| **Eventos----------------------------------------------------------------------------**|
| **Actor** | **Sistema**|
| 1. El Equipo Directivo / Administrador seleccionara una solicitud,<br> podra cambiar el estado en que se encuentra e ingresar un comentario. <br> 4. Equipo Directivo / Administrador visualizara la modificacion realizada |  2. El sistema guardar los datos en la BD. <br> 3. El sistema muesta la confirmacion. | 




### Explicacion del Funcionamiento General de la Aplicacion

1. **Inicio de Sesión**

    En el inicio de sesion tiene diferentes tipos de ingreso de Perfiles: Profesor, Administrador y Super Usuario.

    **Profesor**: El perfil podra hacer solicitudes para las actividades, actualizarla(agregar), subir fotos de la actividad.

    **Administrador/Super Usuario**: Este perfil podra cargar  los datos de un archivo .csv con la lista de profesores, modificar el estado de la solicitud, agregar un nuevo profesor, y visualizar todas las solicitudes que se han realizado.

    [![image004.png](https://i.postimg.cc/fLX40Pws/image004.png)](https://postimg.cc/N9gC3p9C)

2. **Ventana para los Profesores**

    Cada profesor tendra su ventana propia donde podran  visualizar 2 tablas: una con todas sus solicitudes hechas por el y la otra mostrando solo las solicitudes aprobadas, un boton para crear dichas solicitudes, a darle clic en una solicitud aprobada podra se mostrara una ventana donde podra ingresar datos adicionales para la solicitud, a dar clic en la tabla donde se muestran todas las solicitudes si la solicitud esta con el estado realizado, se abrira una ventan donde podra colocar las url de las fotos de la actividad.

    [![image006.png](https://i.postimg.cc/kX7rBwLr/image006.png)](https://postimg.cc/2399tdzT)

3. **Ventana para los Administradores/Equipo Directivo**

    En esta ventana se podra visualizar en un tabla todas las solicitudes realizadas por todos los profesores y podran cambiar el estado de la solicitud a APROBADA, DENEGADA o REALIZADA, tambien podran cargar y guardar los datos de los profesores mediante un archivo CSV, ingresar nuevos profesores y generarles sus usuarios para el uso de la aplicacion.

    [![image014.png](https://i.postimg.cc/Qtg4DZ8F/image014.png)](https://postimg.cc/phTYQ7nv)

4. **Ingreso como Super Usuario**

    Este es un tipo de usuario especial, que podra visualizar todas las ventanas previamente descritas, donde podra revisar 
    funcionamiento y arreglos de estas.

    [![image018.png](https://i.postimg.cc/4xKWd94k/image018.png)](https://postimg.cc/DSTrpmbB)

### Métodos Especiales dentro de la Aplicación

1. **Método cambiar Estado**

    Este metodo cambia automaticamente el estado de una solicitud SOLICITADA a DENEGADA, si esta no se aprueba en el plazo puesto en la solicitud creada "Si la Fecha Final de la Solicitud es mayor a la fecha actual del sistema y no ha sido APROBADA este cambiara a DENEGADA", asi mismo si "Si la Fecha Final de una solicitud APROBADA y modificada por el profesor previamente es mayor a la fecha actual del sistema este pasara automaticamente a REALIZADA".

2. **Método reservar Bus**

    Este es un metodo solicitado por el reto, que niega escoger la opcion de bus para el tipo de transporte utilizado para la actividad si esta no se a realizado con 2 semanas de anticipacion.

<hr>
<br>

## Tabla sobre la implementación y despliegue

| Tecnología    | Ventajas                                                                                                                                                                | Desventajas                                                                                                                                                      |
|---------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Ubuntu        | 1. Orientado a la estabilidad y seguridad.<br>2. Amplia disponibilidad de recursos para solucionar problemas.<br>3. Facilidad de uso, especialmente en la instalación y configuración de servicios como SSH, TCP y MySQL Server. | 1. Puede tener una curva de aprendizaje para usuarios no familiarizados con Linux.<br>2. Algunas configuraciones avanzadas pueden requerir conocimientos técnicos adicionales. |
| Ubuntu Server | 1. Orientado a la estabilidad y seguridad.<br>2. Amplia disponibilidad de recursos para solucionar problemas.<br>3. Facilidad de uso, especialmente en la instalación y configuración de servicios como SSH, TCP y MySQL Server. | 1. Puede requerir conocimientos técnicos avanzados para configuraciones personalizadas.<br>2. Curva de aprendizaje para usuarios no familiarizados con Linux.             |
| Alpine        | 1. Ligero y eficiente en recursos.<br>2. Seguridad mejorada debido a su enfoque minimalista.<br>3. Buena opción para contenedores y entornos de microservicios.                    | 1. Menor disponibilidad de recursos y documentación en comparación con sistemas más populares como Ubuntu.<br>2. Requiere más esfuerzo en la configuración inicial.             |
            

Hemos elegido **Ubuntu** como sistema operativo para alojar los servidores por varias razones. En primer lugar, el diseño de Ubuntu está orientado a la estabilidad y seguridad. Además, Ubuntu cuenta con una mayor cantidad de recursos disponibles que pueden servirnos de ayuda en caso de encontrarnos con algún problema. Otro punto a favor de Ubuntu frente a otros sistemas operativos probados, como **Ubuntu Server** o **Alpine**, es su facilidad de uso, que hace que la instalación y configuración de servicios como SSH, TCP o MySQL Server sean bastante más sencillos que en estos sistemas.


