/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImp;

import Acceso_BaseDatos.AccesoBaseDatos;
import Clases.Grupos;
import Interface.MetodosBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase contiene los metodos de una interface MetodoBD T con la Clase grupos
 * @author Grupo3
 * @version 1.0
 * @see grupos
 * @see AccesoBaseDatos
 */
public class GruposDAOImp implements MetodosBD<Grupos>{
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
     * @return Regresa una lista de objetos que representan los grupos.
     */
    @Override
    public List<Grupos> listar() {
        // Crear una lista para almacenar los grupos
        List<Grupos> grupos = new ArrayList<>();
        // Consulta SQL para seleccionar los grupos
        final String sql = "SELECT ID_GRUPO,COD_GRUP,idCurso,alumnos,estadoGrup FROM grupos";
        
        try ( Statement stmt = getConnection().createStatement();  ResultSet rs = stmt.executeQuery(sql);) {
            // Iterar a través de los resultados de la consulta
            while (rs.next()) {
                // Crear un objeto grupos a partir de los resultados de la consulta
                Grupos grupo = crearGrupos(rs);
                // Si no se pudo agregar el grupo a la lista, lanzar una excepción
                if (!grupos.add(grupo)) {
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
        return grupos;
        
    }
    
    //Método no utilizado
    @Override
    public Grupos buscar(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    //Método no utilizado
    @Override
    public void guardar(Grupos t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    //Método no utilizado
    @Override
    public void modificar(Grupos t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
    * Crea un objeto Grupos a partir de un conjunto de resultados de una consulta SQL.
    * 
    * @param rs El ResultSet que contiene los resultados de la consulta SQL.
    * @return Un objeto Grupos creado a partir de los resultados de la consulta.
    */
    private Grupos crearGrupos(final ResultSet rs) throws SQLException {
        // Crear y retornar un nuevo objeto Grupos con los datos del ResultSet
        return new Grupos(rs.getInt("ID_GRUPO"), rs.getString("COD_GRUP"), rs.getInt("idCurso"), rs.getInt("alumnos"), rs.getBoolean("estadoGrup"));
    }

}
