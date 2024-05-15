/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImp;

import Acceso_BaseDatos.AccesoBaseDatos;
import Clases.Usuario;
import Clases.Profesor;
import Enums.Perfil;
import Interface.MetodosBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daw112
 */
public class UsuarioDAOImp implements MetodosBD<Usuario> {

    private Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }

    @Override
    public List<Usuario> listar() {
        return null;
    }

    @Override
    public Usuario buscar(int ID) {

        Usuario usuario = null;

        final String sql = "Select idUsuarios,Nombre,Apellidos,usuarios.Email,PWD,Perfil from usuarios "
                + "inner join profesores on ID_Prof=idUsuarios where idUsuarios=? ";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            stmt.setInt(1, ID);

            try (ResultSet rs = stmt.executeQuery();) {

                if (rs.next()) {

                    usuario = crearUsuario(rs);

                }

            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        }
        return usuario;

    }

    @Override
    public void guardar(Usuario t) {

    }

    public void crearUsuariosContraseñas(Profesor p) {

        String contraseña = p.getNombre().substring(0, 4) + p.getApellidos().substring(0, 3);

        final String sql = "insert into usuarios (idUsuarios,Email,PWD) values('"+p.getId_Prof()+"','"+p.getMail()+"','"+contraseña.toLowerCase()+"')";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            int salida = stmt.executeUpdate(sql);

            if (salida != 1) {
                throw new Exception(" No se ha guardado Usuario en la tabla Usuario");
            } else {
                System.out.println("Se guardo correctamento Usuario en la tabla Usuario");
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modificar(Usuario t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Usuario buscarUsuarioEmail(String email, String pass) {
        
        Usuario auxUsuario = null;

        final String sql = "Select idUsuarios,Email,PWD from usuarios where Email=? and PWD=?";

        try (PreparedStatement sentencia = getConnection().prepareStatement(sql);) {

            sentencia.setString(1, email);
            sentencia.setString(2, pass);

            try (ResultSet resultadoConsulta = sentencia.executeQuery();) {
                if (resultadoConsulta.next()) {
                    auxUsuario = new Usuario(resultadoConsulta.getInt("idUsuarios"), resultadoConsulta.getString("Email"),
                            resultadoConsulta.getString("PWD"));

                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }

        return auxUsuario;
    }

    private Usuario crearUsuario(final ResultSet rs) throws SQLException {
        return new Usuario(rs.getInt("idUsuarios"),rs.getString("Email"), rs.getString("PWD"));
    }

}
