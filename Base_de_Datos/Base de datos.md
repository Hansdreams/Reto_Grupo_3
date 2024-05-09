# BASE DE DATOS ![Mysql_logo](https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.webempresa.com%2Fblog%2Fque-es-mysql.html&psig=AOvVaw3HdxwKRYwt1o48GB_Z4wd4&ust=1715347301391000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCMCC-eXUgIYDFQAAAAAdAAAAABAI)
<br>
<h2>SCRIPT DE CREACIÓN DE LA BASE DE DATOS</h1>

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

-- -----------------------------------------------------
-- -----------------------------------------------------
-- Table `mydb`.`Departamentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RETO_PRUEBA`.`Departamentos` (
  `idDepartamentos` INT NOT NULL,
  `COD_DE` CHAR(3) NOT NULL UNIQUE,
  `Nombre` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idDepartamentos`),
  UNIQUE INDEX `COD_DE_UNIQUE` (`COD_DE` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `RETO_PRUEBA`.`JEFES`(
 `idDepartamentos` INT NOT NULL,
 `ID_Prof` INT,
  CONSTRAINT fk_JEFES_Departamento FOREIGN KEY (idDepartamentos) REFERENCES Departamentos (idDepartamentos) ON UPDATE CASCADE,
  CONSTRAINT fk_JEFES_Profesores FOREIGN KEY (ID_Prof) REFERENCES Profesores (ID_Prof) ON UPDATE CASCADE,
  UNIQUE INDEX (`idDepartamentos`))
ENGINE = InnoDB;

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


-- -----------------------------------------------------
-- Table `mydb`.`Solicitud [ACEX]`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RETO_PRUEBA`.`Solicitud` (
  `idSolicitud` INT NOT NULL,
  `Solicitante` INT NOT NULL,
  `nombreAct` VARCHAR(45) NOT NULL,
  `tipoActividad` ENUM('Extraordinaria', 'Complementaria') NOT NULL,
  `Departamento` INT NOT NULL,
  `Prevista` TINYINT(1) NOT NULL,
  `Transporte` TINYINT(1) NOT NULL,
  `FechaInicial` DATE NOT NULL,
  `FechaFinal` DATE NOT NULL,
  `HoraInicial` DATETIME NOT NULL,
  `HoraFinal` DATETIME NOT NULL,
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
  `HoraInicial` DATETIME NOT NULL,
  `HoraFinal` DATETIME NOT NULL,
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

-- -----------------------------------------------------
-- Table `mydb`.`Alojamientos`
-- -----------------------------------------------------

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
```
<h1> Tabla de la base de datos</h1>

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
| Nombre | VARCHAR(50) | Nombre |
|DESCRIPCION|Informacion del departamento|
**Jefes**
| --- | --- | --- |
| idDepartamentos | INT | Identificador del departamento |
| ID_Prof | INT |Identificador de profesor |
|DESCRIPCION|Informacion del profesor jefe *ID_Prof* de cada *departamento*|
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

# Creación de Selects
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

