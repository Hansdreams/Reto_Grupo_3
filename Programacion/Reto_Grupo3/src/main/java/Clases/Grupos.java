/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author DAW112
 */
public class Grupos {
    
    private int idGrupo;//pendiente
    private String codgrupo;
    private int idCurso;
    private int alumnos;
    private int activo;

    public Grupos(int idGrupo, String codgrupo, int idCurso, int alumnos, int activo) {
        this.idGrupo = idGrupo;
        this.codgrupo = codgrupo;
        this.idCurso = idCurso;
        this.alumnos = alumnos;
        this.activo = activo;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getCodgrupo() {
        return codgrupo;
    }

    public void setCodgrupo(String codgrupo) {
        this.codgrupo = codgrupo;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(int alumnos) {
        this.alumnos = alumnos;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Grupos{" + "idGrupo=" + idGrupo + ", codgrupo=" + codgrupo + ", idCurso=" + idCurso + ", alumnos=" + alumnos + ", activo=" + activo + '}';
    }
    
    
    
}
