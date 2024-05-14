/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Enums.Estado;
import Enums.TipoActividad;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author DAW112
 */
public class ActividadProgramada extends Solicitud{
  
    private boolean transporteContratado;
    private double presupuesto;
    private int alumnosParticipantes;
    private String comentarios;

    public ActividadProgramada(boolean transporteContratado, double presupuesto, int alumnosParticipantes, String comentarios, int idSolicitud, int solicitante, String nombreActividad, TipoActividad tipoActividad, int departamento, 
            boolean prevista, boolean transporte, LocalDate fechaInicial, LocalDate fechaFinal, LocalTime horaInicial, LocalTime horaFinal, boolean alojamiento, String comentarioAdicional, Estado estado, String consultaEstado, int maximoAlumnos) {
        
        super(idSolicitud, solicitante, nombreActividad, tipoActividad, departamento, prevista, transporte, fechaInicial, fechaFinal, horaInicial, horaFinal, alojamiento, comentarioAdicional, estado, consultaEstado, maximoAlumnos);
        this.transporteContratado = transporteContratado;
        this.presupuesto = presupuesto;
        this.alumnosParticipantes = alumnosParticipantes;
        this.comentarios = comentarios;
    }

    public boolean isTransporteContratado() {
        return transporteContratado;
    }

    public void setTransporteContratado(boolean transporteContratado) {
        this.transporteContratado = transporteContratado;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public int getAlumnosParticipantes() {
        return alumnosParticipantes;
    }

    public void setAlumnosParticipantes(int alumnosParticipantes) {
        this.alumnosParticipantes = alumnosParticipantes;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "ActividadProgramada{" + "transporteContratado=" + transporteContratado + ", presupuesto=" + presupuesto + ", alumnosParticipantes=" + alumnosParticipantes + ", comentarios=" + comentarios +",Solicitud="+super.toString()+'}';
    }

    

}
