/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import Enums.Perfil;

/**
 * Esta clase contiene los atributos y metodos de un profesor
 * @author Grupo3
 * @version 1.0
 */
public class Profesor {
    
    private int id_Prof;
    private String nombre;
    private String apellidos;
    private String dni;
    private String mail;
    private String departamento;
    private Perfil perfil;

    /**
     * 
     * @param id_Prof Numero identificador del profesor
     * @param nombre Nombre del profesor
     * @param apellidos Apellidos del profesor
     * @param dni Documento nacional de identidad del profesor
     * @param mail Correo electronico del profesor
     * @param departamento Numero identificador del departamento asociado al profesor
     * @param perfil Enumeracion que indica el nivel de acceso a la aplicacion >(SUPERUSUARIO,EQUIPO_DIRECTIVO,ADMINISTRADOR,PROFESOR)
     */
    
    public Profesor(int id_Prof, String nombre, String apellidos, String dni, String mail, String departamento, Perfil perfil) {
        this.id_Prof = id_Prof;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.mail = mail;
        this.departamento = departamento;
        this.perfil = perfil;
    }

    /**
     * Método que obtiene el id del profesor
     * @return Regresa el id del profesor
     */
    public int getId_Prof() {
        return id_Prof;
    }

    /**
     * Método que obtiene el nombre del profesor
     * @return Regresa el nombre del profesor
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que obtiene los apellidos del profesor
     * @return Regresa los apellidos del profesor
     */
    public String getApellidos() {
        return apellidos;
    }
    
    /**
     * Método que obtiene el DNI del profesor
     * @return Regresa el DNI del profesor
     */
    public String getDni() {
        return dni;
    }
    
    /**
     * Método que obtiene el email del profesor
     * @return Regresa el email del proefesor
     */
    public String getMail() {
        return mail;
    }

    /**
     * Método que obtiene el numero de departamentos
     * @return Regresa el numero de departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Método que obtiene el Perfil del profesor
     * @return Regresa un valor del Enum Perfil asociado al profesor
     */
    public Perfil getPerfil() {
        return perfil;
    }

}
