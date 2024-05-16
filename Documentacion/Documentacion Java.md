
# Indice de Contenidos

1.  


### Aplicacion

Esta apliacion de escritorio esta diseñada para gestionar las actividaddes Complementarias y Extraescolares para el centro IES Miguel Herrero Pereda.

### Diagrama de clase

Diseñamos la aplicacion guiandonos del siguiente diagrama:

ARCHIVO DIAGRAMA DE CLASE SUBIDO EN LA CARPETA DOCUMENTACION.

Donde tenermos las diferentes clases que usa nuestra apliacion:

    En una solicitud puede tener cero o mas cursos y  cero a mas cursos pueden participar en una solicitud.
    En una solicitud puede tener cero o mas grupos dentro de ella y cero a mas grupos que pueden participar en una solicitud. 
    En una solicitud participa un solo departamento y en un departamento participan en una solicitud.
    Un profesor realiza una solicitud y una o mas solicitudes son realizadas por un profesor.
    Un profesor pùede ser un profesor partcipante y uno o mas participantes son profesores.
    Uno o mas profesores participan dentro de una solicitud y en una solicitud particpan uno o mas profesores.

### Diagrama de clases de Uso

ARCHIVO DIAGRAMA DE CLASES DE USO SUBIDO EN LA CARPETA DOCUMENTACION.

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

1. **Inicio de Sesion**

    En el inicio de sesion tiene diferentes tipos de ingreso de Perfiles: Profesor, Administrador y Super Usuario.

    **Profesor**: El perfil podra hacer solicitudes para las actividades, actualizarla(agregar), subir fotos de la actividad.

    **Administrador/Super Usuario**: Este perfil podra cargar  los datos de un archivo .csv con la lista de profesores, modificar el estado de la solicitud, agregar un nuevo profesor, y visualizar todas las solicitudes que se han realizado.

    SACAR IMAGEN DEL MANUAL DE USUARIO DENTRO DE LA CARPETA DOCUMENTOS (Imagen 1)

2. **Ventana para los Profesores**

    Cada profesor tendra su ventana propia donde podran  visualizar 2 tablas: una con todas sus solicitudes hechas por el y la otra mostrando solo las solicitudes aprobadas, un boton para crear dichas solicitudes, a darle clic en una solicitud aprobada podra se mostrara una ventana donde podra ingresar datos adicionales para la solicitud, a dar clic en la tabla donde se muestran todas las solicitudes si la solicitud esta con el estado realizado, se abrira una ventan donde podra colocar las url de las fotos de la actividad.

    SACAR IMAGEN DEL MANUAL DE USUARIO DENTRO DE LA CARPETA DOCUMENTOS (Imagen 2)

3. **Ventana para los Administradores/Equipo Directivo**

    En esta ventana se podra visualizar en un tabla todas las solicitudes realizadas por todos los profesores y podran cambiar el estado de la solicitud a APROBADA, DENEGADA o REALIZADA, tambien podran cargar y guardar los datos de los profesores mediante un archivo CSV, ingresar nuevos profesores y generarles sus usuarios para el uso de la aplicacion.

    SACAR IMAGEN DEL MANUAL DE USUARIO DENTRO DE LA CARPETA DOCUMENTOS (Imagen 6)

4. **Ingreso como Super Usuario**

    Este es un tipo de usuario especial, que podra visualizar todas las ventanas previamente descritas, donde podra revisar 
    funcionamiento y arreglos de estas.

    SACAR IMAGEN DEL MANUAL DE USUARIO DENTRO DE LA CARPETA DOCUMENTOS (Imagen 7)

### Metodos Especiales dentro de la Aplicacion

1. **Metodo cambiar Estado**

    Este metodo cambia automaticamente el estado de una solicitud SOLICITADA a DENEGADA, si esta no se aprueba en el plazo puesto en la solicitud creada "Si la Fecha Final de la Solicitud es mayor a la fecha actual del sistema y no ha sido APROBADA este cambiara a DENEGADA", asi mismo si "Si la Fecha Final de una solicitud APROBADA y modificada por el profesor previamente es mayor a la fecha actual del sistema este pasara automaticamente a REALIZADA".

2. **Metodo reservar Bus**

    Este es un metodo solicitado por el reto, que niega escoger la opcion de bus para el tipo de transporte utilizado para la actividad si esta no se a realizado con 2 semanas de anticipacion.



    

