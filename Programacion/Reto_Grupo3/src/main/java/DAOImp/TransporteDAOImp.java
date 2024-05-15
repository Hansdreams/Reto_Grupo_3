/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImp;

import Acceso_BaseDatos.AccesoBaseDatos;
import Clases.Transporte;
import Interface.MetodosBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase contiene los metodos de una interface MetodoBD T con la Clase Transporte
 * @author Grupo3
 * @version 1.0
 * @see Transporte
 * @see AccesoBaseDatos
 */
public class TransporteDAOImp implements MetodosBD<Transporte>{
    /**
     * Método que retorna la conexion de la clase AccesoBaseDatos
     * @return Regresa una Connection (Conn)
     */
    private Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }

    @Override
    public List<Transporte> listar() {
        // Lista para almacenar los transportes
        List<Transporte> transporte = new ArrayList<>();
        // Consulta SQL para obtener todos los transportes
        final String sql = "SELECT idTransportes,TipoTransporte FROM transportes";
        
        try ( Statement stmt = getConnection().createStatement();  ResultSet rs = stmt.executeQuery(sql);) {
            // Recorrer el resultado de la consulta
            while (rs.next()) {
                // Crear objeto Transporte a partir de los datos de la consulta
                Transporte solicitud = crearTransporte(rs);
                // Agregar el transporte a la lista
                if (!transporte.add(solicitud)) {
                    // Lanzar una excepción si no se pudo agregar el transporte a la lista
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
        return transporte;
        
    }
    //Método no utilizado
    @Override
    public Transporte buscar(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    //Método no utilizado
    @Override
    public void guardar(Transporte t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    //Método no utilizado
    @Override
    public void modificar(Transporte t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    /**
     * Crea un objeto Transporte a partir de un conjunto de resultados de una consulta SQL.
     * @param rs El ResultSet que contiene los resultados de la consulta SQL.
     * @return Un objeto Transporte creado a partir de los resultados de la consulta.
     */
    private Transporte crearTransporte(final ResultSet rs) throws SQLException {
        return new Transporte(rs.getInt("idTransportes"), rs.getString("TipoTransporte"));
    }
    
}
