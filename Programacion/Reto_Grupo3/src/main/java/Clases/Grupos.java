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
    
    private int idGrupo;
    private String codgrupo;
    private int idCurso;
    private int alumnos;
    private boolean estadoGrupos;

    public Grupos(int idGrupo, String codgrupo, int idCurso, int alumnos, boolean estadoGrupos) {
        this.idGrupo = idGrupo;
        this.codgrupo = codgrupo;
        this.idCurso = idCurso;
        this.alumnos = alumnos;
        this.estadoGrupos = estadoGrupos;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public String getCodgrupo() {
        return codgrupo;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public int getAlumnos() {
        return alumnos;
    }

    public boolean isEstadoGrupos() {
        return estadoGrupos;
    }
    
}
