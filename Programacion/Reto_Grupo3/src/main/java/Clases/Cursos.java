/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author DAW112
 */
public class Cursos {
    
    private int idCurso;//pendiente
    private String codCurso;
    private String desCurso;
    private String etapa;
    private int activo;
    private int totalAlumnos;//pendiente

    public Cursos(int idCurso, String codCurso, String desCurso, String etapa, int activo) {
        this.idCurso = idCurso;
        this.codCurso = codCurso;
        this.desCurso = desCurso;
        this.etapa = etapa;
        this.activo = activo;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(String codCurso) {
        this.codCurso = codCurso;
    }

    public String getDesCurso() {
        return desCurso;
    }

    public void setDesCurso(String desCurso) {
        this.desCurso = desCurso;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Cursos{" + "idCurso=" + idCurso + ", codCurso=" + codCurso + ", desCurso=" + desCurso + ", etapa=" + etapa + ", activo=" + activo + '}';
    }

}
