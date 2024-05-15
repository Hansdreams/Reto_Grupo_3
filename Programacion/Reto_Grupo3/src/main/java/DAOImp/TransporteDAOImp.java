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
        
        List<Transporte> transporte = new ArrayList<>();
        
        final String sql = "SELECT idTransportes,TipoTransporte FROM transportes";
        
        try ( Statement stmt = getConnection().createStatement();  ResultSet rs = stmt.executeQuery(sql);) {
            
            while (rs.next()) {
                
                Transporte solicitud = crearTransporte(rs);
                if (!transporte.add(solicitud)) {
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

    @Override
    public Transporte buscar(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void guardar(Transporte t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modificar(Transporte t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private Transporte crearTransporte(final ResultSet rs) throws SQLException {
        return new Transporte(rs.getInt("idTransportes"), rs.getString("TipoTransporte"));
    }
    
}
