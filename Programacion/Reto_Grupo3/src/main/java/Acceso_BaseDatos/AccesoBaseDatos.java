package Acceso_BaseDatos;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Esta clase contiene los atributos y metodos para hacer la conexcion entre IDE y la Herramienta Grafica
 * @author Daw112
 * @version 1.0
 */
public class AccesoBaseDatos {
    
    private Connection conn = null; //atributo conexión
    
    /*private static final String BD = "reto_prueba"; // nombre de la BD que se usa
    private static final String USUARIO = "root";//son los datos para conectarse a la BD Mysql
    private static final String CLAVE = "mysql";//contraseña con la que se ingresa BD Mysql
    private static final String URL = "jdbc:mysql://localhost:3306/" + BD; //conexión al servidor de la BD*/
    
    // constantes a definir. pueden depender de la base a usar, qué credenciales tiene la BD...
    
    private static final String BD = "prueba2"; // nombre de la BD que se usa
    private static final String USUARIO = "g33";//son los datos para conectarse a la BD Mysql
    private static final String CLAVE = "Grupo3ubuntu.";//contraseña con la que se ingresa BD Mysql
    private static final String URL = "jdbc:mysql://10.0.10.78:3306/" + BD; //conexión al servidor de la BD

    private AccesoBaseDatos() {

        try {
            Properties properties = new Properties();
            properties.setProperty("user", USUARIO);
            properties.setProperty("password", CLAVE);
            properties.setProperty("useSSL", "false");
            properties.setProperty("autoReconnect", "true");
            conn = (Connection) DriverManager.getConnection(URL, properties);
            if (conn == null) {
                System.out.println("Error en conexion");
            } else {
                System.out.println("Conexion correcta a: " + URL);
            }
        } catch (SQLException ex) {
            // manejo de los errores
            System.out.println("SQLException: " + ex.getMessage()); //nos indica la descripción del error
            System.out.println("SQLState: " + ex.getSQLState()); 
            /*devuelve un código SQL estándar definido por ISO/ANSI 
            y el Open Group que identifica de forma unívoca el error que se ha producido.
            */

            System.out.println("VendorError: " + ex.getErrorCode()); 
            /*es un código de error que lanza la base de datos.
            En este caso el código de error es diferente dependiendo del proveedor de base de datos que estemos utilizando.*/
        }
    }

    public static AccesoBaseDatos getInstance() {
        return AccesoBaseDatosHolder.INSTANCE;
    }

    private static class AccesoBaseDatosHolder {//método privado

        private static final AccesoBaseDatos INSTANCE = new AccesoBaseDatos();//variable estática privada
    }
    
    
    //otros métodos que puedan ser necesarios
    
    //obtener la conexion
    public Connection getConn() {
        return conn;
    }
 
    // cerrar la conexión
    public boolean cerrar() {
        boolean siCerrada = false;
        try {
            conn.close();
            if (conn.isClosed()) {
                siCerrada = true;
            }
        } catch (SQLException sqe) {
            System.out.println("Se produjo un error en el cierre");
        }
        return siCerrada;
    }
    
}
