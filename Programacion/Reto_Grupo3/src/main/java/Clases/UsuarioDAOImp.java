/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Daw112
 */
public class UsuarioDAOImp {
    
    private Connection getConnection()
    {
        return AccesoBaseDatos.getInstance().getConn();
    }
    
    public Usuario buscarUsuarioEmail(String email, String pass)
    {
        Usuario auxUsuario = null;
        
        final String sql = "Select usuarios.Email,PWD,Perfil from usuarios "
                + "inner join profesores on ID_Prof=idUsuarios where usuarios.Email=? and PWD=?";

        try ( 
                PreparedStatement sentencia = getConnection().prepareStatement(sql);
            ) 
        {
            sentencia.setString(1, email);
            sentencia.setString(2, pass);
            
            try (
                    ResultSet resultadoConsulta = sentencia.executeQuery();
                )
            {
                if (resultadoConsulta.next()) 
                {
                    auxUsuario = new Usuario(resultadoConsulta.getString("usuarios.Email"), resultadoConsulta.getString("PWD"), resultadoConsulta.getString("Perfil"));
                   
                }
            }
        }
        catch (SQLException ex) 
        {
            System.out.println("SQLException: " + ex.getMessage());
        }
        
        return auxUsuario;
    }
    
    /*public Usuario buscarUsuarioEmail2(String email, String pass)
    {
        Usuario auxUsuario = null;
        
        //final String sql = "Select usuarios.Email,PWD,Perfil from usuarios INNER JOIN profesores on profesores.ID_Prof = usuarios.idUsuarios where ID_Prof=? and PWD=?";
        final String sql = "select email,PWD from usuarios where Email=? and PWD =?;";
        try ( 
                PreparedStatement sentencia = getConnection().prepareStatement(sql);
            ) 
        {
            sentencia.setString(1, email);
            sentencia.setString(2, pass);
            
            try (
                    ResultSet resultadoConsulta = sentencia.executeQuery();
                )
            {
                if (resultadoConsulta.next()) 
                {
                    auxUsuario = new Usuario(resultadoConsulta.getString("Email"), resultadoConsulta.getString("PWD"));
                   
                }
            }
        }
        catch (SQLException ex) 
        {
            System.out.println("SQLException: " + ex.getMessage());
        }
        
        return auxUsuario;
    }*/
}
