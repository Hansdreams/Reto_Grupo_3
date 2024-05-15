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
/**
 *
 * @author DAW112
 */
public class Solicitud {
    
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

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public int getSolicitante() {
        return solicitante;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public TipoActividad getTipoActividad() {
        return tipoActividad;
    }

    public int getDepartamento() {
        return departamento;
    }

    public boolean isPrevista() {
        return prevista;
    }

    public boolean isTransporte() {
        return transporte;
    }

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public LocalTime getHoraInicial() {
        return horaInicial;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public boolean isAlojamiento() {
        return alojamiento;
    }

    public String getComentarioAdicional() {
        return comentarioAdicional;
    }

    public Estado getEstado() {
        return estado;
    }

    public String getConsultaEstado() {
        return consultaEstado;
    }

    public int getMaximoAlumnos() {
        return maximoAlumnos;
    }

    //Formato de Fechas y horas
    
    public String formatoFechaInicial(){
        DateTimeFormatter fecFor = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return fechaInicial.format(fecFor);
    }
    
    public String formatoFechaFinal(){
        DateTimeFormatter fecFor = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return fechaFinal.format(fecFor);
    }
    
    public String formatoHoraInicial(){
        DateTimeFormatter fecFor = DateTimeFormatter.ofPattern("HH:mm");
        return horaInicial.format(fecFor);
    }
    
    public String formatoHoraFinal(){
        DateTimeFormatter fecFor = DateTimeFormatter.ofPattern("HH:mm");
        return horaFinal.format(fecFor);
    }
   
}
