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
 *
 * @author Gus y Joaco
 */
public class CursosDAOImp implements MetodosBD<Cursos>{
    
    private Connection getConnection()
    {
        return AccesoBaseDatos.getInstance().getConn();
    }

    @Override
    public List<Cursos> listar() {
        
        List<Cursos> cursos = new ArrayList<>();
        
        final String sql = "SELECT ID_CURSO,COD_CUR,descripCur,etapa,estadoCur, sum(alumnos) FROM cursos INNER JOIN grupos ON ID_CURSO = idCurso group by ID_CURSO";
        
        try ( Statement stmt = getConnection().createStatement();  ResultSet rs = stmt.executeQuery(sql);) {
            
            while (rs.next()) {
                
                Cursos curso = crearCursos(rs);
                
                if (!cursos.add(curso)) {
                    throw new Exception("error no se ha insertado el objeto en la colección");
                }
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cursos;
        
    }

    @Override
    public Cursos buscar(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void guardar(Cursos t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modificar(Cursos t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private Cursos crearCursos(final ResultSet rs) throws SQLException {
        return new Cursos(rs.getInt("ID_CURSO"),rs.getString("COD_CUR"), rs.getString("descripCur"),rs.getString("etapa"),rs.getBoolean("estadoCur"),rs.getInt("sum(alumnos)"));
    }
    
}
