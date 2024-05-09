/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author DAW112
 */
public class CursosAct {
    
    private int idActividad;
    private String codCurso;

    public CursosAct(int idActividad, String codCurso) {
        this.idActividad = idActividad;
        this.codCurso = codCurso;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(String codCurso) {
        this.codCurso = codCurso;
    }

    @Override
    public String toString() {
        return "CursosAct{" + "idActividad=" + idActividad + ", codCurso=" + codCurso + '}';
    }

}
