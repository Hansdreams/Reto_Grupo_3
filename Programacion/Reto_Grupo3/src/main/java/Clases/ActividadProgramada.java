/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author DAW112
 */
public class ActividadProgramada {
    
    private int idSolicitud;
    private boolean transporteContratado;
    private boolean alojamiento;
    private double presupuesto;
    private int alumnosParticipantes;
    private String comentarios;

    public ActividadProgramada(int idSolicitud, boolean transporteContratado, boolean alojamiento, double presupuesto, int alumnosParticipantes, String comentarios) {
        this.idSolicitud = idSolicitud;
        this.transporteContratado = transporteContratado;
        this.alojamiento = alojamiento;
        this.presupuesto = presupuesto;
        this.alumnosParticipantes = alumnosParticipantes;
        this.comentarios = comentarios;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public boolean isTransporteContratado() {
        return transporteContratado;
    }

    public void setTransporteContratado(boolean transporteContratado) {
        this.transporteContratado = transporteContratado;
    }

    public boolean isAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(boolean alojamiento) {
        this.alojamiento = alojamiento;
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
        return "ActividadProgramada{" + "idSolicitud=" + idSolicitud + ", transporteContratado=" + transporteContratado + ", alojamiento=" + alojamiento + ", presupuesto=" + presupuesto + ", alumnosParticipantes=" + alumnosParticipantes + ", comentarios=" + comentarios + '}';
    }
    
}
