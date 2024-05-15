/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import Enums.Perfil;
/**
 *
 * @author DAW112
 */
public class Profesor {
    
    private int id_Prof;
    private String nombre;
    private String apellidos;
    private String dni;
    private String mail;
    private String departamento;
    private Perfil perfil;

    public Profesor(int id_Prof, String nombre, String apellidos, String dni, String mail, String departamento, Perfil perfil) {
        this.id_Prof = id_Prof;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.mail = mail;
        this.departamento = departamento;
        this.perfil = perfil;
    }

    public int getId_Prof() {
        return id_Prof;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDni() {
        return dni;
    }

    public String getMail() {
        return mail;
    }

    public String getDepartamento() {
        return departamento;
    }

    public Perfil getPerfil() {
        return perfil;
    }

}
