/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import Enums.Rol;

/**
 * Esta clase contiene los atributos y metodos de un profesor participante
 * @author Grupo3
 * @version 1.0
 */
public class ProfesoresParticipantes {
    
    private int idActividad;
    private int idProfesor;
    private Rol rol;

    /**
     * 
     * @param idActividad Numero identificador de la activdad asociada a un profesor participante
     * @param idProfesor Numero identificador de un profesor que participa en una actividad
     * @param rol Enumeracion que indica que tipo de rol realiza el profesor participante > (RESPONSABLE,PARTICIPANTE)
     */
    public ProfesoresParticipantes(int idActividad, int idProfesor, Rol rol) {
        this.idActividad = idActividad;
        this.idProfesor = idProfesor;
        this.rol = rol;
    }

    /**
     * Método que obtiene la id de una actividad
     * @return Regresa el id de una actividad
     */
    public int getIdActividad() {
        return idActividad;
    }

    /**
     * Método que obtiene la id de un profesor participante en una actividad
     * @return Regresa el id de un profesor participante
     */
    public int getIdProfesor() {
        return idProfesor;
    }
   
    /**
     * Método que obtiene el rol que desempeña un profesor participante
     * @return Regresa el rol de un profesor participante
     */
    public Rol getRol() {
        return rol;
    }

    
}
