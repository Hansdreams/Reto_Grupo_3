/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImp;

import Acceso_BaseDatos.AccesoBaseDatos;
import Clases.Usuario;
import Interface.MetodosBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Daw112
 */
public class UsuarioDAOImp implements MetodosBD<Usuario>{
    
    private Connection getConnection()
    {
        return AccesoBaseDatos.getInstance().getConn();
    }

    @Override
    public ArrayList<Usuario> listar() {
        return null;
    }

    @Override
    public Usuario buscar(String aux) {
        return null;
    }

    @Override
    public void guardar(Usuario t) {
       
    }

    @Override
    public void eliminar(String aux) {
        
    }

    @Override
    public void modificar(Usuario t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Usuario buscarUsuarioEmail(String email, String pass)
    {
        Usuario auxUsuario = null;
        
        final String sql = "Select idUsuarios,Nombre,Apellidos,usuarios.Email,PWD,Perfil from usuarios "
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
                    auxUsuario = new Usuario(resultadoConsulta.getInt("idUsuarios"),resultadoConsulta.getString("Nombre"),resultadoConsulta.getString("Apellidos"),resultadoConsulta.getString("usuarios.Email"), resultadoConsulta.getString("PWD"), resultadoConsulta.getString("Perfil"));
    
                }
            }
        }
        catch (SQLException ex) 
        {
            System.out.println("SQLException: " + ex.getMessage());
        }
        
        return auxUsuario;
    }
    
}
