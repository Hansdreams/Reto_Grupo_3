/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImp;

import Interface.MetodosBD;
import Clases.Departamentos;
import Acceso_BaseDatos.AccesoBaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase implementa los métodos de la interfaz MetodosBD para la clase Departamentos
 * @author Grupo3
 * @version 1.0
 * @see Departamentos
 * @see AccesoBaseDatos
 */
public class DepartamentoDAOImp implements MetodosBD<Departamentos>{
     /**
     * Método que retorna la conexion de la clase AccesoBaseDatos
     * @return Regresa una Connection (Conn)
     */
    private Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }
    
    /**
     * Lista todos los departamentos desde la base de datos.
     * 
     * @return Una lista de objetos Departamentos que representan los departamentos.
     */
    @Override
    public List<Departamentos> listar() {
        
        // Crear una lista para almacenar los departamentos
        List<Departamentos> dep = new ArrayList<>();
        
        // Consulta SQL para seleccionar los departamentos
        final String sql = "SELECT idDepartamentos,COD_DE,Nombre,JEFE FROM departamentos"; 
        
        try ( Statement stmt = getConnection().createStatement();  ResultSet rs = stmt.executeQuery(sql);) {
            // Iterar a través de los resultados de la consulta
            while (rs.next()) {
                // Crear un objeto Departamentos a partir de los resultados de la consulta
                Departamentos departamento = crearDepartamentos(rs);
                // Agregar el departamento a la lista
                if (!dep.add(departamento)) {
                    // Si no se pudo agregar el departamento a la lista, lanzar una excepción
                    throw new Exception("error no se ha insertado el objeto en la colección");
                }
            }

        } catch (SQLException ex) {
            // Manejar errores de SQL
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            // Manejar otras excepciones
            System.out.println(ex.getMessage());
        }
        
        return dep;
        
    }

    //Método no utilizado
    @Override
    public Departamentos buscar(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * Busca un departamento por su nombre en la base de datos
     * @param aux String del nombre del departamento a buscar
     * @return Regresa el objeto Departamentos que representa el departamento encontrado, o null si no se encuentra.
     */
    public Departamentos buscarID(String aux){
        
        Departamentos departamento = null;
        // Consulta SQL para buscar un departamento por su nombre
        final String sql = "select idDepartamentos,COD_DE,Nombre,JEFE from departamentos where Nombre= '"+aux+"'";
        
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            try ( ResultSet rs = stmt.executeQuery();) {
                
                if (rs.next()) {
                    // Crear un objeto Departamentos a partir de los resultados de la consulta
                    departamento = crearDepartamentos(rs);
                    
                }
                
            }

        } catch (SQLException ex) {
        // Manejar errores de SQL
            System.out.println("SQLException: " + ex.getMessage());
        }
        
        return departamento;
    }
    
    //Método no utilizado
    @Override
    public void guardar(Departamentos t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    /**
    * Modifica el jefe de un departamento en la base de datos.
    * 
    * @param t El departamento con la información actualizada.
    */
    @Override
    public void modificar(Departamentos t) {
        // Generar un número aleatorio entre 1 y 15 para el nuevo jefe del departamento
        int randon = (int) (Math.floor(Math.random()*(15-1+1)+1)); 
        // Consulta SQL para actualizar el jefe del departamento
        final String sql = "update departamentos set JEFE = '"+ randon +"' where idDepartamentos = '"+ t.getId() +"'";
        
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            int salida = stmt.executeUpdate();
            
            if (salida != 1) {
                // Si no se actualizó correctamente el jefe del departamento, lanzar una excepción
                throw new Exception(" No se ha modificado JEFE de departamentos");
                
            }

        } catch (SQLException ex) {
        // Manejar errores de SQL
            System.out.println("SQLException: " + ex.getMessage());
            
        } catch (Exception ex) {
                    // Manejar otras excepciones

            System.out.println(ex.getMessage());
            
        }
        
    }
    
    /**
    * Crea un objeto Departamentos a partir de un conjunto de resultados de una consulta SQL.
    * 
    * @param rs El ResultSet que contiene los resultados de la consulta SQL.
    * @return El objeto Departamentos creado a partir de los resultados de la consulta.
    */
private Departamentos crearDepartamentos(final ResultSet rs) throws SQLException {
    // Crear y retornar un nuevo objeto Departamentos con los datos del ResultSet
    return new Departamentos(rs.getInt("idDepartamentos"), rs.getString("COD_DE"), rs.getString("Nombre"), rs.getInt("JEFE"));
}
  
}
