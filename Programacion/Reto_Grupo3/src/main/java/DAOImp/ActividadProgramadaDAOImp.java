/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImp;
import Acceso_BaseDatos.AccesoBaseDatos;
import Clases.ActividadProgramada;
import Enums.Estado;
import Enums.TipoActividad;
import Interface.MetodosBD;
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
 * @author Gus y Joaco
 */
public class ActividadProgramadaDAOImp implements MetodosBD<ActividadProgramada>{

    private Connection getConnection()
    {
        return AccesoBaseDatos.getInstance().getConn();
    }
    
    @Override
    public List<ActividadProgramada> listar() {
        
        List<ActividadProgramada> actividadProgramada = new ArrayList<>();
        
        final String sql = "SELECT actividadprogramada.IdSolicitud,actividadprogramada.Solicitante,actividadprogramada.nombreAct,actividadprogramada.tipoActividad,actividadprogramada.Departamento,actividadprogramada.Prevista,"
                + "actividadprogramada.Transporte,actividadprogramada.FechaInicial,actividadprogramada.FechaFinal,actividadprogramada.HoraInicial,actividadprogramada.HoraFinal,actividadprogramada.Alojamiento,actividadprogramada.comentarioAdicional,\n" 
                + "TransporteContrato,actividadprogramada.AlumnosMAX,Presupuesto,AlumnosParticipantes,Comentario,Estado,ConsultaEstado FROM actividadprogramada INNER JOIN solicitud ON actividadprogramada.IdSolicitud=solicitud.idSolicitud";
        
        try ( Statement stmt = getConnection().createStatement();  ResultSet rs = stmt.executeQuery(sql);) {
            
            while (rs.next()) {
                ActividadProgramada programada = crearActividadProgramada(rs);
                
                if (!actividadProgramada.add(programada)) {
                    throw new Exception("error no se ha insertado el objeto en la colección");
                }
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return actividadProgramada;
        
    }

    @Override
    public ActividadProgramada buscar(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void guardar(ActividadProgramada t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public boolean guardarActividadProgramada(ActividadProgramada s){
        
        boolean guardado = false;
        
        int prevista,transporte,alojamiento,transporteContratado;
        
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
        
        if(s.isTransporteContratado()){
            transporteContratado = 1;
        }else{
            transporteContratado = 0;
        }
        
        if(s.isAlojamiento()){
            alojamiento = 1;
        }else{
            alojamiento = 0;
        }
        
        String sql = "INSERT INTO actividadprogramada(IdSolicitud,Solicitante,nombreAct,tipoActividad,Departamento,Prevista,Transporte,FechaInicial,FechaFinal,HoraInicial,HoraFinal,Alojamiento,comentarioAdicional,"
                + "TransporteContrato,AlumnosMAX,Presupuesto,AlumnosParticipantes,Comentario)\n" 
                + "values ('"+s.getIdSolicitud()+"','"+s.getSolicitante()+"','"+s.getNombreActividad()+"','"+s.getTipoActividad().toString().substring(0,1)+s.getTipoActividad().toString().substring(1,s.getTipoActividad().toString().length()).toLowerCase()+"',"
                + "'"+s.getDepartamento()+"','"+prevista+"','"+transporte+"','"+s.getFechaInicial()+"','"+s.getFechaFinal()+"','"+s.getHoraInicial()+"','"+s.getHoraFinal()+"','"+alojamiento+"','"+s.getComentarioAdicional()+"','"+transporteContratado+"',"
                + "'"+s.getMaximoAlumnos()+"','"+s.getPresupuesto()+"','"+s.getAlumnosParticipantes()+"','"+s.getComentarios()+"')";

        try ( PreparedStatement stmt = getConnection().prepareStatement(sql);) {

            int salida = stmt.executeUpdate(sql);
    
            if (salida != 1) {
                throw new Exception(" No se ha guardado Actividad Programada!!!");   
            }else{
                System.out.println("Se guardo correctamento Actividad Programada!!!");
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
    public void modificar(ActividadProgramada t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private ActividadProgramada crearActividadProgramada (final ResultSet rs) throws SQLException {  
        return new ActividadProgramada(rs.getBoolean("TransporteContrato"), rs.getDouble("Presupuesto"), rs.getInt("AlumnosParticipantes"), rs.getString("Comentario"),rs.getInt("actividadprogramada.IdSolicitud")
                , rs.getInt("actividadprogramada.Solicitante"), rs.getString("actividadprogramada.nombreAct"), TipoActividad.valueOf(rs.getString("actividadprogramada.tipoActividad").toUpperCase()), rs.getInt("actividadprogramada.Departamento"),
                rs.getBoolean("actividadprogramada.Prevista"), rs.getBoolean("actividadprogramada.Transporte"),rs.getDate("actividadprogramada.FechaInicial").toLocalDate(), rs.getDate("actividadprogramada.FechaFinal").toLocalDate(),
                rs.getTime("actividadprogramada.HoraInicial").toLocalTime(), rs.getTime("actividadprogramada.HoraFinal").toLocalTime(),rs.getBoolean("actividadprogramada.Alojamiento")
                        ,rs.getString("actividadprogramada.comentarioAdicional"), Estado.valueOf(rs.getString("Estado").toUpperCase()), rs.getString("ConsultaEstado"), rs.getInt("actividadprogramada.AlumnosMAX"));
        
        
    }
    
}
