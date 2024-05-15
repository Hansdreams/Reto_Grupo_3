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
 *
 * @author DAW112
 */
public class SolicitudDAOImp implements MetodosBD<Solicitud>{

    private Connection getConnection()
    {
        return AccesoBaseDatos.getInstance().getConn();
    }
    
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

    @Override
    public void guardar(Solicitud t) {

    }
    
    public boolean guardarSolicitud(Solicitud s){
        
        boolean guardado = false;
        
        int prevista,transporte,alojamiento;
        
        if(s.isPrevista()){
            prevista = 1;
        }else{
            prevista = 0;
        }
        
        if(s.isTransporte()){
            transporte = 1;
        }else{
            transporte = 0;
        }
 
        if(s.isAlojamiento()){
            alojamiento = 1;
        }else{
            alojamiento = 0;
        }
        
        String sql = "insert into solicitud(Solicitante,nombreAct,tipoActividad,Departamento,Prevista,Transporte,FechaInicial,FechaFinal,HoraInicial,HoraFinal,Alojamiento,comentarioAdicional,AlumnosMax,ConsultaEstado)\n" +
                     "values ('"+s.getSolicitante()+"','"+s.getNombreActividad()+"','"+s.getTipoActividad().toString().substring(0,1)+s.getTipoActividad().toString().substring(1,s.getTipoActividad().toString().length()).toLowerCase()+"','"+s.getDepartamento()+"','"+prevista+"'"
                + ",'"+transporte+"','"+s.getFechaInicial()+"','"+s.getFechaFinal()+"','"+s.getHoraInicial()+"','"+s.getHoraFinal()+"','"+alojamiento+"','"+s.getComentarioAdicional()+"','"+s.getMaximoAlumnos()+"','"+s.getConsultaEstado()+"')";

        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            int salida = stmt.executeUpdate(sql);
    
            if (salida != 1) {
                throw new Exception(" No se ha guardado Solitud en la tabla Solicitud");   
            }else{
                System.out.println("Se guardo correctamento Solicitud en la tabla Solicitud");
                guardado = true;
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return guardado;
    }
    
    @Override
    public void modificar(Solicitud t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public boolean actualizarEstado(String estado, int solicitud, int solicitante, String comentario){
        
        boolean actualizado = false;
        
        final String sql = "update solicitud set Estado= '"+estado+"', ConsultaEstado='"+comentario+"' where idSolicitud= '"+solicitud+"' and Solicitante= '"+solicitante+"'";
        
        
        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            
            int salida = stmt.executeUpdate();
            
            if (salida != 1) {
                actualizado = false;
            }else{
                actualizado = true;
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return actualizado;
        
    }
    
    private Solicitud crearSolicitud (final ResultSet rs) throws SQLException {    
               return new Solicitud(rs.getInt("idSolicitud"), rs.getInt("Solicitante"), rs.getString("nombreAct"), TipoActividad.valueOf(rs.getString("tipoActividad").toUpperCase()), rs.getInt("Departamento"), rs.getBoolean("Prevista"), rs.getBoolean("Transporte"), rs.getDate("FechaInicial").toLocalDate(), rs.getDate("FechaFinal").toLocalDate(), rs.getTime("HoraInicial").toLocalTime(), rs.getTime("HoraFinal").toLocalTime(), rs.getBoolean("Alojamiento"), rs.getString("comentarioAdicional"), Estado.valueOf(rs.getString("Estado").toUpperCase()), rs.getString("ConsultaEstado"), rs.getInt("AlumnosMAX"));
    }
    
}
