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
 *
 * @author DAW112
 */
public class ProfesorDAOImp implements MetodosBD<Profesor>{
    
    private Connection getConnection()
    {
        return AccesoBaseDatos.getInstance().getConn();
    }

    @Override
    public List<Profesor> listar() {
        
        List<Profesor> profesores = new ArrayList<>();
        
        final String sql = "SELECT ID_Prof,Nombre,Apellidos,DNI,Email,Estado,Departamento,Perfil FROM profesores";
        
        try ( Statement stmt = getConnection().createStatement();  ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                Profesor solicitud = crearProfesor(rs);
                if (!profesores.add(solicitud)) {
                    throw new Exception("error no se ha insertado el objeto en la colección");
                }
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return profesores;
        
    }

    @Override
    public Profesor buscar(int ID) {
        
        Profesor profesor = null;
        
        final String sql = "select ID_Prof,Nombre,Apellidos,DNI,Email,Departamento,Perfil from profesores where ID_Prof =? and Estado = '"+1+"'";
        
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            
            stmt.setInt(1, ID);
            
            try ( ResultSet rs = stmt.executeQuery();) {
                
                if (rs.next()) {
                    
                    profesor = crearProfesor(rs);
                    
                }
                
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        }
        
        return profesor;
        
    }

    @Override
    public void guardar(Profesor t) {
        
        String perfil = "profesor";
        
        final String sql = "insert into profesores(ID_Prof,Nombre,Apellidos,DNI,Email,Estado,Departamento,Perfil) "
                + "values('"+t.getId_Prof()+"','"+t.getNombre()+"','"+t.getApellidos()+"','"+t.getDni()+"','"+t.getMail()+"','"+1+"','"+t.getDepartamento()+"','"+perfil+"')";
        
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            int salida = stmt.executeUpdate(sql);
    
            if (salida != 1) {
                throw new Exception(" No se ha guardado Profesor en la tabla profesor");
            }else{
                System.out.println("Se guardo correctamento Profesor en la tabla profesor");
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void eliminar(String aux) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modificar(Profesor t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private Profesor crearProfesor(final ResultSet rs) throws SQLException {
        return new Profesor(rs.getInt("ID_Prof"), rs.getString("Nombre"), rs.getString("Apellidos"), rs.getString("DNI")
                , rs.getString("Email"), rs.getString("Departamento"), Perfil.valueOf(rs.getString("Perfil").toUpperCase()));
    }
    
    
    
}
