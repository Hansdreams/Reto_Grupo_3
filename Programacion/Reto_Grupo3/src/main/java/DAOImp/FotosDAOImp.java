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
 *
 * @author Gus y Joaco
 */
public class FotosDAOImp implements MetodosBD<Fotos>{

    private Connection getConnection()
    {
        return AccesoBaseDatos.getInstance().getConn();
    }
    
    @Override
    public List<Fotos> listar() {
        
        List<Fotos> fotos = new ArrayList<>();
        
        final String sql = "SELECT idFotos,url,descripcion,id_actividad FROM fotos";
        
        try ( Statement stmt = getConnection().createStatement();  ResultSet rs = stmt.executeQuery(sql);) {
            
            while (rs.next()) {
                
                Fotos foto = crearFotos(rs);
                
                if (!fotos.add(foto)) {
                    throw new Exception("error no se ha insertado el objeto en la colección");
                }
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return fotos;
    }

    @Override
    public void guardar(Fotos t) {
        
        final String sql = "INSERT INTO fotos(idFotos,url,descripcion,id_Actividad) values('"+t.getIdFotos()+"','"+t.getUrl()+"','"+t.getDescripcion()+"','"+t.getIdActividad()+"')";
        
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            int salida = stmt.executeUpdate(sql);
    
            if (salida != 1) {
                throw new Exception(" No se ha guardado Fotos en la tabla Fotos");
            }else{
                System.out.println("Se guardo correctamento Fotos en la tabla Fotos");
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public Fotos buscar(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(String aux) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modificar(Fotos t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private Fotos crearFotos(final ResultSet rs) throws SQLException {
        return new Fotos(rs.getInt("idFotos"), rs.getString("url"), rs.getString("descripcion"), rs.getInt("id_actividad"));
    }
    
}
