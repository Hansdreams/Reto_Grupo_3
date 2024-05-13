/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import Enums.Rol;

/**
 *
 * @author DAW112
 */
public class ProfesoresParticipantes {
    
    private int idActividad;
    private int idProfesor;
    private Rol rol;

    public ProfesoresParticipantes(int idActividad, int idProfesor, Rol rol) {
        this.idActividad = idActividad;
        this.idProfesor = idProfesor;
        this.rol = rol;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "ProfesoresParticipantes{" + "idActividad=" + idActividad + ", idProfesor=" + idProfesor + ", rol=" + rol + '}';
    }
 
}
