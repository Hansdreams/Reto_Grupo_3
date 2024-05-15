/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImp;

import Acceso_BaseDatos.AccesoBaseDatos;
import Interface.MetodosBD;
import Clases.Fotos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase contiene los metodos de una interface MetodoBD T con la Clase Fotos
 * @author Grupo3
 * @version 1.0
 * @see Fotos
 * @see AccesoBaseDatos
 */
public class FotosDAOImp implements MetodosBD<Fotos>{
    /**
     * Método que retorna la conexion de la clase AccesoBaseDatos
     * @return Regresa una Connection (Conn)
     */
    private Connection getConnection()
    {
        return AccesoBaseDatos.getInstance().getConn();
    }
    /**
     * Método que te regresa una lista seleccionada de la base de datos
     * @return Regresa una lista de objetos que representan las fotos.
     */
    @Override
    public List<Fotos> listar() {
        // Crear una lista para almacenar las fotos
        List<Fotos> fotos = new ArrayList<>();
        // Consulta SQL para seleccionar las fotos
        final String sql = "SELECT idFotos,url,descripcion,id_actividad FROM fotos";
        
        try ( Statement stmt = getConnection().createStatement();  ResultSet rs = stmt.executeQuery(sql);) {
            // Iterar a través de los resultados de la consulta
            while (rs.next()) {
                
                Fotos foto = crearFotos(rs);
                
                if (!fotos.add(foto)) {
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
        return fotos;
    }
    
    /**
     * Guarda una foto en la base de datos.
     * 
     * @param t La foto a guardar en la base de datos.
     */
    @Override
    public void guardar(Fotos t) {
        // Consulta SQL para insertar la foto en la tabla 'fotos'
        final String sql = "INSERT INTO fotos(idFotos,url,descripcion,id_Actividad) values('"+t.getIdFotos()+"','"+t.getUrl()+"','"+t.getDescripcion()+"','"+t.getIdActividad()+"')";
        
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            int salida = stmt.executeUpdate(sql);
    
            if (salida != 1) {
                throw new Exception(" No se ha guardado Fotos en la tabla Fotos");
            }else{
                System.out.println("Se guardo correctamento Fotos en la tabla Fotos");
            }

        } catch (SQLException ex) {
            // Manejar errores de SQL
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            // Manejar otras excepciones
            System.out.println(ex.getMessage());
        }
        
    }
    
    //Método no utilizado
    @Override
    public Fotos buscar(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    //Método no utilizado
    @Override
    public void modificar(Fotos t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * Crea un objeto Fotos a partir de un conjunto de resultados de una consulta SQL.
     * 
     * @param rs El ResultSet que contiene los resultados de la consulta SQL.
     * @return Un objeto Fotos creado a partir de los resultados de la consulta.
     */
    private Fotos crearFotos(final ResultSet rs) throws SQLException {
        // Crear y retornar un nuevo objeto Fotos con los datos del ResultSet
        return new Fotos(rs.getInt("idFotos"), rs.getString("url"), rs.getString("descripcion"), rs.getInt("id_actividad"));
    }
    
}
