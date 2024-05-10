


[![mysql-logo.png](https://i.postimg.cc/HL1S7PbT/mysql-logo.png)](https://postimg.cc/G4zPWKMf)
# BASE DE DATOS - RETO
[![logo.png](https://i.postimg.cc/QxDvRCFX/logo.png)](https://postimg.cc/23HHWzdJ)

# Índice

1. [Script de creación de la base de datos](#script)
2. [Tabla de la base de datos](#tablas)
3. [Selects de la base de datos](#selects)
4. [Facilitar el uso de inserts](#inserts)

<br>


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
  PRIMARY KEY (`IdActividad`),
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
  PRIMARY KEY (`Actividad`),
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
  PRIMARY KEY (`IdAct`),
  CONSTRAINT fk_GruposAct_Solicitud FOREIGN KEY (IdAct) REFERENCES Solicitud (IdSolicitud) ON UPDATE CASCADE,
  CONSTRAINT fk_GruposAct_Grupos FOREIGN KEY (idGrupo) REFERENCES Grupos (ID_GRUPO) ON UPDATE CASCADE)

ENGINE = InnoDB;


Create table if not exists `RETO_PRUEBA`.`CursosAct`(
`IdAct` INT NOT NULL,
`idCur` INT NOT NULL,
PRIMARY KEY (`IdAct`),
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
