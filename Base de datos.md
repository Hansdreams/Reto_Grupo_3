# BASE DE DATOS

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
DESCRIPCION | Informacion personal de profesor, *Email* para el acceso a la aplicacion y *departamento* al que pertenece |
**Departamentos**
| --- | --- | --- |
| idDepartamentos | INT | Identificador del departamento |
| COD_DE | CHAR(3) | Codigo de departamento |
| Nombre | VARCHAR(25) | Nombre |
| JEFE | INT | Jefe de departamento (foreign key) |
|DESCRIPCION|Informacion del departamento junto al profesor *Jefe* del mismo|
**Cursos**
| --- | --- | --- |
| COD_CUR | VARCHAR(5) | Codigo de curso |
| descripCur | VARCHAR(145) | Descripcion de curso |
| etapa | ENUM('ESO', 'BACHILLERATO', 'FPGS', 'FPGM', 'FPB', 'FBCE') | Estapa del curso |
| estadoCur | TINYINT(1) | Estado del curso (activo/inactivo) |
| AlumnosTotales | INT | Nº total de estudiantes |
|DESCRIPCION|Informacion del curso y el numero total de alumnos por curso|
**Grupos**
| --- | --- | --- |
| COD_GRUP | VARCHAR(8) | Codigo de grupo |
| COD_CUR | VARCHAR(5) | Codigo de curso (foreign key) |
| alumnos | INT | Nº de estudiantes |
| estadoGrup | TINYINT(1) | Estado de grupo (activo/inactivo) |
|DESCRIPCION|Informacion del grupo, el *codigo de curso* al que pertenece, y el numero de alumnos por grupo|
**Usuarios**
| --- | --- | --- |
| idUsuarios | INT | Identificador de usuario |
| Email | VARCHAR(50) | Email de usuario |
| PWD | VARCHAR(20) | Contraseña de usuario |
|DESCRIPCION|Tabala para el acceso a la aplicacion, el *Email* del profesor junto a *Password* encrypt|
**Solicitud**
| --- | --- | --- |
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
|DESCRIPCION|Informacion de la solicitud de la actividad propuesta, mantiene relacion con *profesores*, *departamentos*,*grupos* y *cursos*, dentro de esta tabla aparece el estado de la solicitud, donde se indica con un comentario el "Por que" de su estado|
**Transportes**
| --- | --- | --- |
| idTransportes | INT | Identificador de transportes |
| TipoTransporte | VARCHAR(45) | Tipo de transporte |
|DESCRIPCION|Informacion de los tipos de transporte|
**TransporteAct**
| --- | --- | --- |
| IdActividad | INT | Identificador de la actividad |
| tipoTransporte | INT | Tipo de transporte (ID) (foreign key) |
|DESCRIPCION|Tabla sobre los transportes involucrados en la actividad|
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
| Presupuesto | DECIMAL(6,2) | Presupuesto total de la actividad |
| AlumnosMAX| INT | Numero de alumnos maximos |
| AlumnosParticipantes | INT | Numero de alumnos que participan |
| Comentario | TEXT | Additional comments |
|DESCRIPCION| Copia de la tabla *SOLICITUD* con algunos campos adicionales; cantidad de alumnos, transporte contradado, *presupuesto*, y comentario extenso sobre toda la actividad|
**ProfesorParticipante**
| --- | --- | --- |
| Actividad | INT | Identificacion de actividad (foreign key) |
| IdProfesor | INT | Identificacion de profesor (foreign key) |
| Rol | ENUM('Responsable', 'Participante') | Rol del profesor en la actividad |
|DESCRIPCION|Informacion sobre profesores participantes en la actividad y sobre el *rol* que mantiene|
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
|DESCRIPCION|Grupos participantes en una actividad|
**CursosAct**
| --- | --- | --- |
| IdAct | INT | Identificacion de la actividad (foreign key) |
| CodCur | VARCHAR(5) | Codigo de curso (foreign key) |
|DESCRIPCION|Cursos participantes en una actividad||