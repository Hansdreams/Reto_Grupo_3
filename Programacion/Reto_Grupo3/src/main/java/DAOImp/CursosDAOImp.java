/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImp;
import Acceso_BaseDatos.AccesoBaseDatos;
import Clases.Cursos;
import Interface.MetodosBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase contiene los metodos de una interface MetodoBD T con la Clase Cursos
 * @author Grupo3
 * @version 1.0
 * @see Cursos AccesoBaseDatos
 */
public class CursosDAOImp implements MetodosBD<Cursos>{
    /**
     * Método que retorna la conexion de la clase AccesoBaseDatos
     * @return Regresa una Connection (Conn)
     */
    private Connection getConnection()
    {
        return AccesoBaseDatos.getInstance().getConn();
    }
    /**
     * Método que te regresa una lista seleccionada de la base de datos
     * @return Regresa una lista de objetos que representan los cursos.
     */
    @Override
    public List<Cursos> listar() {
       
        // Crear una lista para almacenar los cursos
        List<Cursos> cursos = new ArrayList<>();
        
        // Consulta SQL para seleccionar los cursos y sumar el número de alumnos de cada curso
        final String sql = "SELECT ID_CURSO,COD_CUR,descripCur,etapa,estadoCur, sum(alumnos) FROM cursos INNER JOIN grupos ON ID_CURSO = idCurso group by ID_CURSO";
        
        try ( Statement stmt = getConnection().createStatement();  ResultSet rs = stmt.executeQuery(sql);) {
            // Iterar a través de los resultados de la consulta
            while (rs.next()) {
                // Crear un objeto Cursos a partir de los resultados de la consulta
                Cursos curso = crearCursos(rs);
                // Agregar el curso a la lista
                if (!cursos.add(curso)) {
                    // Si no se pudo agregar el curso a la lista, lanzar una excepción
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
        return cursos;
        
    }
    //Método no utilizado
    @Override
    public Cursos buscar(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    //Método no utilizado
    @Override
    public void guardar(Cursos t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    //Método no utilizado
    @Override
    public void modificar(Cursos t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
    * Crea un objeto Curso a partir de un conjunto de resultados de una consulta SQL
    *
    * @param rs El ResultSet que contiene los resultados de la consulta SQL
    * @return Un objeto Curso creado a partir de los resultados de la consulta
    */
    private Cursos crearCursos(final ResultSet rs) throws SQLException {
        return new Cursos(rs.getInt("ID_CURSO"),rs.getString("COD_CUR"), rs.getString("descripCur"),rs.getString("etapa"),rs.getBoolean("estadoCur"),rs.getInt("sum(alumnos)"));
    }
    
}
