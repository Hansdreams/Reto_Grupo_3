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
import javax.swing.JOptionPane;

/**
 * Esta clase contiene los metodos de una interface MetodoBD T con la Clase Usuario
 * @author Grupo3
 * @version 1.0
 * @see Usuario
 * @see AccesoBaseDatos
 */
public class UsuarioDAOImp implements MetodosBD<Usuario> {
    /**
     * Método que retorna la conexion de la clase AccesoBaseDatos
     * @return Regresa una Connection (Conn)
     */
    private Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }
    //Método no utilizado
    @Override
    public List<Usuario> listar() {
        return null;
    }
    /**
     * Método que busca un usuario por su ID en la base de datos
     * @param ID El ID del usuario a buscar
     * @return Un objeto Usuario que representa el usuario encontrado, o null si no se encuentra
     */
    @Override
    public Usuario buscar(int ID) {
        // Inicializar un objeto Usuario como nulo
        Usuario usuario = null;
        // Consulta SQL para seleccionar un usuario por su ID
        final String sql = "Select idUsuarios,Nombre,Apellidos,usuarios.Email,PWD,Perfil from usuarios "
                + "inner join profesores on ID_Prof=idUsuarios where idUsuarios=? ";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            // Establecer el parámetro de la consulta SQL (ID)
            stmt.setInt(1, ID);

            try (ResultSet rs = stmt.executeQuery();) {
                // Si hay resultados en la consulta
                if (rs.next()) {
                    // Crear un objeto Usuario con los datos obtenidos de la consulta
                    usuario = crearUsuario(rs);

                }

            }

        } catch (SQLException ex) {
            //Manejar otras excepciones
            System.out.println("SQLException: " + ex.getMessage());
        }
        return usuario;

    }
    
    //Método no utilizado
    @Override
    public void guardar(Usuario t) {

    }

    public void crearUsuariosContraseñas(Profesor p) {
        // Generar contraseña basada en el nombre y apellidos del profesor
        String contraseña = p.getNombre().substring(0, 4) + p.getApellidos().substring(0, 3);
        // Consulta SQL para insertar un nuevo usuario en la tabla usuarios
        final String sql = "insert into usuarios (idUsuarios,Email,PWD) values('"+p.getId_Prof()+"','"+p.getMail()+"','"+contraseña.toLowerCase()+"')";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            int salida = stmt.executeUpdate(sql);

            if (salida != 1) {
                // Si no se guardó correctamente, lanzar una excepción
                throw new Exception(" No se ha guardado Usuario en la tabla Usuario");
            } else {
                // Imprimir mensaje de éxito si se guardó correctamente
                System.out.println("Se guardo correctamento Usuario en la tabla Usuario");
            }

        } catch (SQLException ex) {
            // Manejar errores de SQL
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            // Manejar otras excepciones
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modificar(Usuario t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Usuario buscarUsuarioEmail(String email, String pass) {
        // Inicializar un objeto Usuario como nulo
        Usuario auxUsuario = null;
        // Consulta SQL para seleccionar un usuario por su email y contraseña
        final String sql = "Select idUsuarios,Email,PWD from usuarios where Email=? and PWD=?";

        try (PreparedStatement sentencia = getConnection().prepareStatement(sql);) {
            // Establecer los parámetros de la consulta SQL (email y contraseña)
            sentencia.setString(1, email);
            sentencia.setString(2, pass);
            // Si hay resultados en la consulta
            try (ResultSet resultadoConsulta = sentencia.executeQuery();) {
                if (resultadoConsulta.next()) {
                    // Crear un objeto Usuario con los datos obtenidos de la consulta
                    auxUsuario = new Usuario(resultadoConsulta.getInt("idUsuarios"), resultadoConsulta.getString("Email"),
                            resultadoConsulta.getString("PWD"));

                }
            }
        } catch (SQLException ex) {
            // Manejar errores de SQL
            System.out.println("SQLException: " + ex.getMessage());
        }

        return auxUsuario;
    }
    /**
     * Crea un objeto Usuario a partir de un conjunto de resultados de una consulta SQL.
     * @param rs El ResultSet que contiene los resultados de la consulta SQL.
     * @return Un objeto Usuario creado a partir de los resultados de la consulta.
     */
    private Usuario crearUsuario(final ResultSet rs) throws SQLException {
        return new Usuario(rs.getInt("idUsuarios"),rs.getString("Email"), rs.getString("PWD"));
    }

}
