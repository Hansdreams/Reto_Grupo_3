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
 *
 * @author Gus y Joaco
 */
public class GruposDAOImp implements MetodosBD<Grupos>{
    
    private Connection getConnection()
    {
        return AccesoBaseDatos.getInstance().getConn();
    }

    @Override
    public List<Grupos> listar() {
        
        List<Grupos> grupos = new ArrayList<>();
        
        final String sql = "SELECT ID_GRUPO,COD_GRUP,idCurso,alumnos,estadoGrup FROM grupos";
        
        try ( Statement stmt = getConnection().createStatement();  ResultSet rs = stmt.executeQuery(sql);) {
            
            while (rs.next()) {
                
                Grupos grupo = crearGrupos(rs);
                
                if (!grupos.add(grupo)) {
                    throw new Exception("error no se ha insertado el objeto en la colección");
                }
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return grupos;
        
    }

    @Override
    public Grupos buscar(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void guardar(Grupos t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modificar(Grupos t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private Grupos crearGrupos(final ResultSet rs) throws SQLException {
        return new Grupos(rs.getInt("ID_GRUPO"),rs.getString("COD_GRUP"), rs.getInt("idCurso"),rs.getInt("alumnos"),rs.getBoolean("estadoGrup"));
    }
    
}
