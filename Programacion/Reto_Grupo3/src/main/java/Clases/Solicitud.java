/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import Enums.TipoActividad;
import Enums.Estado;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDateTime horaInicial;
    private LocalDateTime horaFinal;
    private boolean alojamiento;
    private String comentarioAdicional;
    private Estado estado;
    private String consultaEstado;
    private int maximoAlumnos;

    public Solicitud(int idSolicitud, int solicitante, String nombreActividad, TipoActividad tipoActividad, int departamento, boolean prevista, boolean transporte, LocalDate fechaInicial, LocalDate fechaFinal, LocalDateTime horaInicial, LocalDateTime horaFinal, boolean alojamiento, String comentarioAdicional, Estado estado, String consultaEstado, int maximoAlumnos) {
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

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public int getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(int solicitante) {
        this.solicitante = solicitante;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public TipoActividad getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(TipoActividad tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public boolean isPrevista() {
        return prevista;
    }

    public void setPrevista(boolean prevista) {
        this.prevista = prevista;
    }

    public boolean isTransporte() {
        return transporte;
    }

    public void setTransporte(boolean transporte) {
        this.transporte = transporte;
    }

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public LocalDateTime getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(LocalDateTime horaInicial) {
        this.horaInicial = horaInicial;
    }

    public LocalDateTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalDateTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    public boolean isAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(boolean alojamiento) {
        this.alojamiento = alojamiento;
    }

    public String getComentarioAdicional() {
        return comentarioAdicional;
    }

    public void setComentarioAdicional(String comentarioAdicional) {
        this.comentarioAdicional = comentarioAdicional;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getConsultaEstado() {
        return consultaEstado;
    }

    public void setConsultaEstado(String consultaEstado) {
        this.consultaEstado = consultaEstado;
    }

    public int getMaximoAlumnos() {
        return maximoAlumnos;
    }

    public void setMaximoAlumnos(int maximoAlumnos) {
        this.maximoAlumnos = maximoAlumnos;
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
        DateTimeFormatter fecFor = DateTimeFormatter.ofPattern("h:mm");
        return horaInicial.format(fecFor);
    }
    
    public String formatoHoraFinal(){
        DateTimeFormatter fecFor = DateTimeFormatter.ofPattern("h:mm");
        return horaFinal.format(fecFor);
    }

    @Override
    public String toString() {
        return "Solicitud{" + "idSolicitud=" + idSolicitud + ", solicitante=" + solicitante + ", nombreActividad=" + nombreActividad + ", tipoActividad=" + tipoActividad + ", departamento=" + departamento + ", prevista=" + prevista + ", transporte=" + transporte + ", fechaInicial=" + fechaInicial + ", fechaFinal=" + fechaFinal + ", horaInicial=" + horaInicial + ", horaFinal=" + horaFinal + ", alojamiento=" + alojamiento + ", comentarioAdicional=" + comentarioAdicional + ", estado=" + estado + ", consultaEstado=" + consultaEstado + ", maximoAlumnos=" + maximoAlumnos + '}';
    }
    
    
    
    
}
