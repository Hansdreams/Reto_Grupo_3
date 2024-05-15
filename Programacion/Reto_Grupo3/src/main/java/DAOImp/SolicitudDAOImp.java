/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImp;

import Acceso_BaseDatos.AccesoBaseDatos;
import Interface.MetodosBD;
import Clases.Solicitud;
import Enums.Estado;
import Enums.TipoActividad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
/**
 * Esta clase contiene los metodos de una interface MetodoBD T con la Clase Solicitud
 * @author Grupo3
 * @version 1.0
 * @see Solicitud
 * @see AccesoBaseDatos
 */
public class SolicitudDAOImp implements MetodosBD<Solicitud>{
    /**
     * Método que retorna la conexion de la clase AccesoBaseDatos
     * @return Regresa una Connection (Conn)
     */
    private Connection getConnection()
    {
        return AccesoBaseDatos.getInstance().getConn();
    }
    /**
     * Método que lista todas las solicitudes en la base de datos
     * 
     * @return Una lista de objetos Solicitud que representan las solicitudes almacenadas en la base de datos
     */
    @Override
    public List<Solicitud> listar() {
        
        List<Solicitud> solicitudes = new ArrayList<>();
        
        final String sql = "SELECT idSolicitud,Solicitante,nombreAct,tipoActividad,Departamento,Prevista,Transporte,FechaInicial,FechaFinal,HoraInicial,HoraFinal,Alojamiento,comentarioAdicional,AlumnosMAX,Estado,ConsultaEstado FROM solicitud";
        
        try ( Statement stmt = getConnection().createStatement();  ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                Solicitud solicitud = crearSolicitud(rs);
                if (!solicitudes.add(solicitud)) {
                    throw new Exception("error no se ha insertado el objeto en la colección");
                }
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return solicitudes;
        
    }
    /**
     * Método que busca una solicitud por su ID en la base de datos.
     * 
     * @param ID El ID de la solicitud a buscar.
     * @return Un objeto Solicitud que representa la solicitud encontrada, o null si no se encuentra.
     */
    @Override
    public Solicitud buscar(int ID) {
        
        Solicitud solicitud = null;
        
        String sql = "SELECT idSolicitud,Solicitante,nombreAct,tipoActividad,Departamento,Prevista,Transporte,FechaInicial,FechaFinal,HoraInicial,HoraFinal,Alojamiento,comentarioAdicional,AlumnosMAX,Estado,ConsultaEstado FROM solicitud WHERE idSolicitud=?";
        
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            
            stmt.setInt(1, ID);
            
            try ( ResultSet rs = stmt.executeQuery();) {
                
                if (rs.next()) {
                    
                    solicitud = crearSolicitud(rs);
                    
                }
                
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        }
        return solicitud;
        
    }

    //metodo no utilizado
    public void guardar(Solicitud t) {

    }
    /**
     * Método que guarda una solicitud en la base de datos
     * 
     * @param s La solicitud a guardar en la base de datos
     * @return true si la solicitud se guardó correctamente, false de lo contrario
     */
    public boolean guardarSolicitud(Solicitud s){
        
        boolean guardado = false;
        
        int prevista,transporte,alojamiento;
        
        // Convertir valores booleanos a enteros para almacenar en la base de datos
        prevista = s.isPrevista() ? 1 : 0;
        transporte = s.isTransporte() ? 1 : 0;
        alojamiento = s.isAlojamiento() ? 1 : 0;
        
        // Consulta SQL para insertar una nueva solicitud en la base de datos
        String sql = "insert into solicitud(Solicitante,nombreAct,tipoActividad,Departamento,Prevista,Transporte,FechaInicial,FechaFinal,HoraInicial,HoraFinal,Alojamiento,comentarioAdicional,AlumnosMax,ConsultaEstado)\n" +
                     "values ('"+s.getSolicitante()+"','"+s.getNombreActividad()+"','"+s.getTipoActividad().toString().substring(0,1)+s.getTipoActividad().toString().substring(1,s.getTipoActividad().toString().length()).toLowerCase()+"','"+s.getDepartamento()+"','"+prevista+"'"
                + ",'"+transporte+"','"+s.getFechaInicial()+"','"+s.getFechaFinal()+"','"+s.getHoraInicial()+"','"+s.getHoraFinal()+"','"+alojamiento+"','"+s.getComentarioAdicional()+"','"+s.getMaximoAlumnos()+"','"+s.getConsultaEstado()+"')";

        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            int salida = stmt.executeUpdate(sql);
    
            if (salida != 1) {
                // Si no se guardó correctamente, lanzar una excepción
                throw new Exception(" No se ha guardado Solitud en la tabla Solicitud");   
            }else{
                System.out.println("Se guardo correctamento Solicitud en la tabla Solicitud");
                guardado = true;
            }

       } catch (SQLException ex) {
            // Manejar errores de SQL
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            // Manejar otras excepciones
            System.out.println(ex.getMessage());
        }
        
        return guardado;
    }
    //Método no utilizado
    @Override
    public void modificar(Solicitud t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public boolean actualizarEstado(String estado, int solicitud, int solicitante, String comentario){
        
        boolean actualizado = false;
            // Consulta SQL para actualizar el estado de una solicitud
        final String sql = "update solicitud set Estado= '"+estado+"', ConsultaEstado='"+comentario+"' where idSolicitud= '"+solicitud+"' and Solicitante= '"+solicitante+"'";
        
        
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            
            int salida = stmt.executeUpdate();
            
            if (salida != 1) {
                actualizado = false;
            }else{
                actualizado = true;
            }

        } catch (SQLException ex) {
        // Manejar errores de SQL
        System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
        // Manejar otras excepciones
        System.out.println(ex.getMessage());
    }
        return actualizado;
        
    }
    /**
    * Crea un objeto Solicitud a partir de un conjunto de resultados de una consulta SQL.
    *
    * @param rs El ResultSet que contiene los resultados de la consulta SQL.
    * @return Un objeto Solicitud creado a partir de los resultados de la consulta.
    */
    private Solicitud crearSolicitud (final ResultSet rs) throws SQLException {    
               return new Solicitud(rs.getInt("idSolicitud"), rs.getInt("Solicitante"), rs.getString("nombreAct"), TipoActividad.valueOf(rs.getString("tipoActividad").toUpperCase()), rs.getInt("Departamento"), rs.getBoolean("Prevista"), rs.getBoolean("Transporte"), rs.getDate("FechaInicial").toLocalDate(), rs.getDate("FechaFinal").toLocalDate(), rs.getTime("HoraInicial").toLocalTime(), rs.getTime("HoraFinal").toLocalTime(), rs.getBoolean("Alojamiento"), rs.getString("comentarioAdicional"), Estado.valueOf(rs.getString("Estado").toUpperCase()), rs.getString("ConsultaEstado"), rs.getInt("AlumnosMAX"));
    }
    
}
