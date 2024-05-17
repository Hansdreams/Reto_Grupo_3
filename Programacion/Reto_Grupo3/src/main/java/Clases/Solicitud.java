/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import Enums.TipoActividad;
import Enums.Estado;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
/**
 * Esta clase contiene los atributos y metodos de una Solicitud
 * @author Grupo3
 * @version 1.0
 */
public class Solicitud{
    
    private int idSolicitud;
    private int solicitante;
    private String nombreActividad;
    private TipoActividad tipoActividad;
    private int departamento;
    private boolean prevista;
    private boolean transporte;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private LocalTime horaInicial;
    private LocalTime horaFinal;
    private boolean alojamiento;
    private String comentarioAdicional;
    private Estado estado;
    private String consultaEstado;
    private int maximoAlumnos;

    /**
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
    public Solicitud(int idSolicitud, int solicitante, String nombreActividad, TipoActividad tipoActividad, int departamento, boolean prevista, boolean transporte, LocalDate fechaInicial, LocalDate fechaFinal, LocalTime horaInicial, LocalTime horaFinal, boolean alojamiento, String comentarioAdicional, Estado estado, String consultaEstado, int maximoAlumnos) {
        this.idSolicitud = idSolicitud;
        this.solicitante = solicitante;
        this.nombreActividad = nombreActividad;
        this.tipoActividad = tipoActividad;
        this.departamento = departamento;
        this.prevista = prevista;
        this.transporte = transporte;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
        this.alojamiento = alojamiento;
        this.comentarioAdicional = comentarioAdicional;
        this.estado = estado;
        this.consultaEstado = consultaEstado;
        this.maximoAlumnos = maximoAlumnos;
    }

    /**
     * Método que obtiene la id de la solicitud
     * @return Regresa el numero id de la solicitud
     */
    public int getIdSolicitud() {
        return idSolicitud;
    }

    /**
     * Método que obtiene numero identificar del profesor solicitante
     * @return Regresa la id del profesor solicitante
     */
    public int getSolicitante() {
        return solicitante;
    }

    /**
     * Método que obtiene el nombre de la Actividad
     * @return Regresa el nombre de la actividad
     */
    public String getNombreActividad() {
        return nombreActividad;
    }

    /**
     * Método que obtiene un valor del enum > TipoActividad > (Extraordinaria o Complementaria)
     * @return Regresa el tipo de actividad
     */
    public TipoActividad getTipoActividad() {
        return tipoActividad;
    }

    /**
     * Método que obtiene el id del departamento asociada a la solicitud
     * @return Regrea el numero del id del departamento
     */
    public int getDepartamento() {
        return departamento;
    }

    /**
     * Método que obtiene si una solicitud esta prevista o no
     * @return Regresa un boolean 
     */
    public boolean isPrevista() {
        return prevista;
    }

    /**
     * Método que obtiene si hay transporte o no
     * @return Regresa un boolean
     */
    public boolean isTransporte() {
        return transporte;
    }

    /**
     * Método que obtiene la fecha inicial
     * @return Regresa un localdate
     */
    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    /**
     * Método que obtiene la fecha final
     * @return Regresa un localdate
     */
    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    /**
     * Método que obtiene la hora inicial
     * @return Regresa un LocalTime
     */
    public LocalTime getHoraInicial() {
        return horaInicial;
    }

    /**
     * Método que obtiene la hora final
     * @return Regresa un LocalTime
     */
    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    /**
     * Método que obtiene si hay alojamiento o no
     * @return Regresa un boolean
     */
    public boolean isAlojamiento() {
        return alojamiento;
    }

    /**
     * Método que obtiene un comentario sobre el alojamiento
     * @return Regresa un comentario
     */
    public String getComentarioAdicional() {
        return comentarioAdicional;
    }

    /**
     * Métood que obtiene el estado de un solicitud > Enum Estado > (SOLICITADA,APROBADA,DENEGADA,REALIZADA)
     * @return Regresa el estado de la solicitud
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Método que obtiene un comentario sobre el estado de la solicitud
     * @return Regresa un comentario
     */
    public String getConsultaEstado() {
        return consultaEstado;
    }

    /**
     * Método que obtiene el maximo de alumnos de una solicitud
     * @return Regresa una cantidad de alumnos
     */
    public int getMaximoAlumnos() {
        return maximoAlumnos;
    }

    //Formato de Fechas y horas
    
    /**
     * Método para dar formata a la fecha
     * @return Regresa un String de la fecha inicial formateada
     */
    public String formatoFechaInicial(){
        DateTimeFormatter fecFor = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return fechaInicial.format(fecFor);
    }
    
    /**
     * Método para dar formata a la fecha
     * @return Regresa un String de la fecha Final formateada
     */
    public String formatoFechaFinal(){
        DateTimeFormatter fecFor = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return fechaFinal.format(fecFor);
    }
    
    /**
     * Método para dar formata a la hora
     * @return Regresa un String de la Hora Inicial formateada
     */
    public String formatoHoraInicial(){
        DateTimeFormatter fecFor = DateTimeFormatter.ofPattern("HH:mm");
        return horaInicial.format(fecFor);
    }
    
    /**
     * Método para dar formata a la hora
     * @return Regresa un String de la Hora Final formateada
     */
    public String formatoHoraFinal(){
        DateTimeFormatter fecFor = DateTimeFormatter.ofPattern("HH:mm");
        return horaFinal.format(fecFor);
    }

    @Override
    public String toString() {
        return "Solicitud{" + "idSolicitud=" + idSolicitud + ", solicitante=" + solicitante + ", nombreActividad=" + nombreActividad + ", tipoActividad=" + tipoActividad + ", departamento=" + departamento + ", prevista=" + prevista + ", transporte=" + transporte + ", fechaInicial=" + fechaInicial + ", fechaFinal=" + fechaFinal + ", horaInicial=" + horaInicial + ", horaFinal=" + horaFinal + ", alojamiento=" + alojamiento + ", comentarioAdicional=" + comentarioAdicional + ", estado=" + estado + ", consultaEstado=" + consultaEstado + ", maximoAlumnos=" + maximoAlumnos + '}';
    }
    
    
    
    
}
