/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImp;

import Acceso_BaseDatos.AccesoBaseDatos;
import Clases.Profesor;
import Enums.Perfil;
import Interface.MetodosBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Esta clase contiene los metodos de una interface MetodoBD T con la Clase Profesor
 * @author Grupo3
 * @version 1.0
 * @see Cursos
 * @see AccesoBaseDatos
 */
public class ProfesorDAOImp implements MetodosBD<Profesor>{
    /**
     * Método que retorna la conexion de la clase AccesoBaseDatos
     * @return Regresa una Connection (Conn)
     */
    private Connection getConnection()
    {
        return AccesoBaseDatos.getInstance().getConn();
    }
    /**
     * Método que retorna una lista de profesores seleccionados de la base de datos
     * 
     * @return Una lista de objetos Profesor que representan los profesores almacenados en la base de datos
     */
    @Override
    public List<Profesor> listar() {
        // Crear una lista para almacenar los profesores
        List<Profesor> profesores = new ArrayList<>();
        // Consulta SQL para seleccionar los profesores
        final String sql = "SELECT ID_Prof,Nombre,Apellidos,DNI,Email,Estado,Departamento,Perfil FROM profesores";
        
        try ( Statement stmt = getConnection().createStatement();  ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                // Crear un objeto Profesor a partir de los resultados de la consulta
                Profesor profesor = crearProfesor(rs);
                 // Agregar el profesor a la lista
                if (!profesores.add(profesor)) {
                    // Si no se pudo agregar el profesor a la lista, lanzar una excepción
                    throw new Exception("error no se ha insertado el objeto en la colección");
                }
            }

        } catch (SQLException ex) {
            //Manejar erros de SQL
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            // Manejar otras excepciones
            System.out.println(ex.getMessage());
        }
        return profesores;
        
    }
    
     /**
     * Método que busca un profesor por su ID en la base de datos
     * 
     * @param ID El ID del profesor a buscar
     * @return Un objeto Profesor que representa al profesor encontrado, o null si no se encuentra
     */
    @Override
    public Profesor buscar(int ID) {
        
        Profesor profesor = null;
        // Consulta SQL para seleccionar un profesor por su ID
        final String sql = "select ID_Prof,Nombre,Apellidos,DNI,Email,Departamento,Perfil from profesores where ID_Prof =? and Estado = '"+1+"'";
        
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            
            stmt.setInt(1, ID);
            
            try ( ResultSet rs = stmt.executeQuery();) {
                
                if (rs.next()) {
                    
                    profesor = crearProfesor(rs);
                    
                }
                
            }

        } catch (SQLException ex) {
            //Manejar otras excepciones
            System.out.println("SQLException: " + ex.getMessage());
        }
        
        return profesor;
        
    }

    /**
     * Método que guarda un nuevo profesor en la base de datos.
     * 
     * @param t El profesor a guardar en la base de datos.
     */
    @Override
    public void guardar(Profesor t) {
        // Definir el perfil como "profesor"
        String perfil = "profesor";
        // Consulta SQL para insertar un nuevo profesor
        final String sql = "insert into profesores(ID_Prof,Nombre,Apellidos,DNI,Email,Estado,Departamento,Perfil) "
                + "values('"+t.getId_Prof()+"','"+t.getNombre()+"','"+t.getApellidos()+"','"+t.getDni()+"','"+t.getMail()+"','"+1+"','"+t.getDepartamento()+"','"+perfil+"')";
        
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            // Ejecutar la consulta SQL
            int salida = stmt.executeUpdate(sql);
            // Verificar si se ha guardado correctamente el profesor
            if (salida != 1) {
                throw new Exception(" No se ha guardado Profesor en la tabla profesor");
            }else{
                System.out.println("Se guardo correctamento Profesor en la tabla profesor");
            }

        } catch (SQLException ex) {
            //Manejar erros de SQL
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            // Manejar otras excepciones
            System.out.println(ex.getMessage());
        }
        
    }
    
    @Override
    public void modificar(Profesor t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     /**
     * Crea un objeto Profesor a partir de un conjunto de resultados de una consulta SQL.
     * 
     * @param rs El ResultSet que contiene los resultados de la consulta SQL.
     * @return Un objeto Profesor creado a partir de los resultados de la consulta.
     */
    private Profesor crearProfesor(final ResultSet rs) throws SQLException {
        return new Profesor(rs.getInt("ID_Prof"), rs.getString("Nombre"), rs.getString("Apellidos"), rs.getString("DNI"), rs.getString("Email"), rs.getString("Departamento"), Perfil.valueOf(rs.getString("Perfil").toUpperCase()));
    }
    
    
    
}
