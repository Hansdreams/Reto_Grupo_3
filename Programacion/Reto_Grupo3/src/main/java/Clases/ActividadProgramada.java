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
 * Esta clase contiene los atributos y metodos de una Actividad Programada
 * @author Grupo3
 * @version 1.0
 * @see Solicitud
 */
public class ActividadProgramada extends Solicitud{
  
    private boolean transporteContratado;
    private double presupuesto;
    private int alumnosParticipantes;
    private String comentarios;

    /**
     * @param transporteContratado Indica si hay o no un Transporte Contratado para la Activida Programada
     * @param presupuesto Presupuesto que se asignara para la Actividad Programada
     * @param alumnosParticipantes Numero que indica la cantidad de alumnos que participaran en la Actividad Programada
     * @param comentarios Comentario puesto por el Profesor idicando alguna observacion en la Actividad Programada
     * @param idSolicitud Numero que hacer referencia a una Solicitud (ID)
     * @param solicitante Numero que hacer referencia a un Profesor (ID)
     * @param nombreActividad Nombre puesto en la Solicitud
     * @param tipoActividad Indica si la Solicitud es EXTRAORDINARIA o COMPLEMENTARIA
     * @param departamento Numero que hacer referencia a un Departamento 
     * @param prevista Indica si estaba programada desde un inicio la Actividad 
     * @param transporte Indica si se necesitara un medio de transporte para la Actividad
     * @param fechaInicial Fecha que indica el dia que se hara la Actividad Programada
     * @param fechaFinal Fecha que indica el dia que se finalizara la Actividad Programada
     * @param horaInicial Tiempo donde inicia la Actividad Programada
     * @param horaFinal Tiempo donde acaba la Actividad Programada
     * @param alojamiento Indica si se necesitra alojamiento para la Actividad Programda
     * @param comentarioAdicional Comentario puesto en incio en la Solicitud
     * @param estado Indica el estado en que se encuentra la Activida Programada
     * @param consultaEstado Comentario puesto por un Administrado
     * @param maximoAlumnos Numero maximo de alumnos que pueden participar en la Activida Programda
     */
    public ActividadProgramada(boolean transporteContratado, double presupuesto, int alumnosParticipantes, String comentarios, int idSolicitud, int solicitante, String nombreActividad, TipoActividad tipoActividad, int departamento, 
            boolean prevista, boolean transporte, LocalDate fechaInicial, LocalDate fechaFinal, LocalTime horaInicial, LocalTime horaFinal, boolean alojamiento, String comentarioAdicional, Estado estado, String consultaEstado, int maximoAlumnos) {
        
        super(idSolicitud, solicitante, nombreActividad, tipoActividad, departamento, prevista, transporte, fechaInicial, fechaFinal, horaInicial, horaFinal, alojamiento, comentarioAdicional, estado, consultaEstado, maximoAlumnos);
        this.transporteContratado = transporteContratado;
        this.presupuesto = presupuesto;
        this.alumnosParticipantes = alumnosParticipantes;
        this.comentarios = comentarios;
    }
    
    /**
     * Metodo para saber si esta contrado el Transporte
     * @return Regresa un boolean 
     */
    public boolean isTransporteContratado() {
        return transporteContratado;
    }
    
    /**
     * Metodo que regresa la cantidad de presupuesto asignado a la Actividad Programada
     * @return Regresa la cantidad de presupuesto
     */
    public double getPresupuesto() {
        return presupuesto;
    }
    
    /**
     * Metodo que regresa la cantidad de alumnos participantes en la Actividad Programada
     * @return Regresa el numero de alumnos
     */
    public int getAlumnosParticipantes() {
        return alumnosParticipantes;
    }
    
    /**
     * Metodo que regresa el comentario puesto por el Profesor que hizo la Solicitud
     * @return Regresa un String que indica el comentario
     */
    public String getComentarios() {
        return comentarios;
    }

}
