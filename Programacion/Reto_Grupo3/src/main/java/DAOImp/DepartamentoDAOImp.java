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
 *
 * @author DAW112
 */
public class DepartamentoDAOImp implements MetodosBD<Departamentos>{

    private Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }
    
    @Override
    public List<Departamentos> listar() {
        
        List<Departamentos> dep = new ArrayList<>();
        
        final String sql = "SELECT idDepartamentos,COD_DE,Nombre,JEFE FROM departamentos"; 
        
        try ( Statement stmt = getConnection().createStatement();  ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                
                Departamentos departamento = crearDepartamentos(rs);
                
                if (!dep.add(departamento)) {
                    throw new Exception("error no se ha insertado el objeto en la colección");
                }
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return dep;
        
    }

    @Override
    public Departamentos buscar(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
        
    public Departamentos buscarID(String aux){
        
        Departamentos departamento = null;
        
        final String sql = "select idDepartamentos,COD_DE,Nombre,JEFE from departamentos where Nombre= '"+aux+"'";
        
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            try ( ResultSet rs = stmt.executeQuery();) {
                
                if (rs.next()) {
                    
                    departamento = crearDepartamentos(rs);
                    
                }
                
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        }
        
        return departamento;
    }

    @Override
    public void guardar(Departamentos t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(String aux) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modificar(Departamentos t) {
        
        int randon = (int) (Math.floor(Math.random()*(15-1+1)+1)); 
        
        final String sql = "update departamentos set JEFE = '"+ randon +"' where idDepartamentos = '"+ t.getId() +"'";
        
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            int salida = stmt.executeUpdate();
            
            if (salida != 1) {
                
                throw new Exception(" No se ha modificado JEFE de departamentos");
                
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
            
        } catch (Exception ex) {
            
            System.out.println(ex.getMessage());
            
        }
        
    }
    
    private Departamentos crearDepartamentos(final ResultSet rs) throws SQLException {
        return new Departamentos(rs.getInt("idDepartamentos"),rs.getString("COD_DE"), rs.getString("Nombre"),rs.getInt("JEFE"));
    }
  
}
