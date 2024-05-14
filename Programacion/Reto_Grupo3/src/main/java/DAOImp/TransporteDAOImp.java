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
 *
 * @author Gus y Joaco
 */
public class TransporteDAOImp implements MetodosBD<Transporte>{
    
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
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
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
    public void eliminar(String aux) {
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
