# Reto_Grupo_3
Repositorio para el Reto 

**BASE DE DATOS**

**Profesores**
| Nombre de columna | Tipo de dato | Descripcion |
| --- | --- | --- |
| --- | --- | --- |
| ID_Prof | INT |Identificador único para cada profesor |
| Nombre | VARCHAR(25) | Nombre |
| Apellidos | VARCHAR(45) | Apellidos |
| DNI | INT | DNI del profesor |
| Email | VARCHAR(50) |Email del profesor |
| Estado | TINYINT | Estado del profesor (activo/inactivo) |
| Departamento | INT | Departamento del profesor (foreign key) |
| Perfil | ENUM('superusuario', 'equipo_directivo', 'administrador', 'profesor') | Tipo de perfil |
DESCRIPCION | |
**Departamentos**
| --- | --- | --- |
| Nombre de columna | Tipo de dato | Descripcion |
| idDepartamentos | INT | Identificador del departamento |
| COD_DE | CHAR(3) | Codigo de departamento |
| Nombre | VARCHAR(25) | Nombre |
| JEFE | INT | Jefe de departamento (foreign key) |
**Cursos**
| Nombre de columna | Tipo de dato | Descripcion |
| --- | --- | --- |
| COD_CUR | VARCHAR(5) | Course code |
| descripCur | VARCHAR(145) | Course description |
| etapa | ENUM('ESO', 'BACHILLERATO', 'FPGS', 'FPGM', 'FPB', 'FBCE') | Course stage |
| estadoCur | TINYINT(1) | Course status (active/inactive) |
| AlumnosTotales | INT | Total number of students |
**Grupos**
| Nombre de columna | Tipo de dato | Descripcion |
| --- | --- | --- |
| COD_GRUP | VARCHAR(8) | Group code |
| COD_CUR | VARCHAR(5) | Course code (foreign key) |
| alumnos | INT | Number of students in the group |
| estadoGrup | TINYINT(1) | Group status (active/inactive) |
**Usuarios**
| Nombre de columna | Tipo de dato | Descripcion |
| --- | --- | --- |
| idUsuarios | INT | Unique identifier for each user |
| Email | VARCHAR(50) | User email address |
| PWD | VARCHAR(20) | User password |
**Solicitud**
| Nombre de columna | Tipo de dato | Descripcion |
| --- | --- | --- |
| idSolicitud | INT | Unique identifier for each request |
| Solicitante | INT | Requester ID (foreign key) |
| nombreAct | VARCHAR(45) | Activity name |
| tipoActividad | ENUM('Extraordinaria', 'Complementaria') | Activity type |
| Departamento | INT | Department ID (foreign key) |
| Prevista | TINYINT(1) | Is the activity planned? |
| Transporte | TINYINT(1) | Does the activity require transportation? |
| FechaInicial | DATE | Start date of the activity |
| FechaFinal | DATE | End date of the activity |
| HoraInicial | DATETIME | Start time of the activity |
| HoraFinal | DATETIME | End time of the activity |
| Alojamiento | TINYINT(1) | Does the activity require accommodation? |
| comentarioAdicional | VARCHAR(200) | Additional comments |
| Estado | ENUM('Solicitada', 'Realizada', 'Denegada', 'Aprobada') | Request status |
| ConsultaEstado | VARCHAR(300) | Status comment |
**Transportes**
| Nombre de columna | Tipo de dato | Descripcion |
| --- | --- | --- |
| idTransportes | INT | Unique identifier for each transport type |
| TipoTransporte | VARCHAR(45) | Transport type name |
**TransporteAct**
| Nombre de columna | Tipo de dato | Descripcion |
| --- | --- | --- |
| IdActividad | INT | Unique identifier for each activity transportation |
| tipoTransporte | INT | Transport type ID (foreign key) |
**ActividadProgramada**
| Nombre de columna | Tipo de dato | Descripcion |
| --- | --- | --- |
| IdSolicitud | INT | Request ID (foreign key) |
| TransporteContrato | TINYINT(1) | Is transportation contracted? |
| Alojamiento | TINYINT(1) | Is accommodation required? |
| Presupuesto | DECIMAL(6,2) | Budget for the activity |
| AlumnosParticipantes | INT | Number of participating students |
| Comentario | TEXT | Additional comments |
**ProfesorParticipante**
| Nombre de columna | Tipo de dato | Descripcion |
| --- | --- | --- |
| Actividad | INT | Activity ID (foreign key) |
| IdProfesor | INT | Professor ID (foreign key) |
| Rol | ENUM('Responsable', 'Participante') | Professor's role in the activity |
**Fotos**
| Nombre de columna | Tipo de dato | Descripcion |
| --- | --- | --- |
| idFotos | INT | Unique identifier for each photo |
| url | VARCHAR(225) | Photo URL |
| descripcion | TEXT | Photo description |
| id_actividad | INT | Activity ID (foreign key) |
**GruposAct**
| Nombre de columna | Tipo de dato | Descripcion |
| --- | --- | --- |
| IdAct | INT | Activity ID (foreign key) |
| CodGrupo | VARCHAR(8) | Group code (foreign key) |
**CursosAct**
| Nombre de columna | Tipo de dato | Descripcion |
| --- | --- | --- |
| IdAct | INT | Activity ID (foreign key) |
| CodCur | VARCHAR(5) | Course code (foreign key) |
**Alojamientos**
| Nombre de columna | Tipo de dato | Descripcion |
| --- | --- | --- |
| idAlojamientos | INT | Unique identifier for each accommodation type |
| TipoAlojamiento | VARCHAR(45) | Accommodation type name |
**AlojamientosAct**
| Nombre de columna | Tipo de dato | Descripcion |
| --- | --- | --- |
| IdActividad | INT | Activity ID (foreign key) |
| tipoAlojamiento | INT | Accommodation type ID (foreign key) |