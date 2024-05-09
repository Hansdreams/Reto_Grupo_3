/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author DAW112
 */
public class GruposAct {
    
    private int idActividad;
    private String codGrupo;

    public GruposAct(int idActividad, String codGrupo) {
        this.idActividad = idActividad;
        this.codGrupo = codGrupo;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(String codGrupo) {
        this.codGrupo = codGrupo;
    }

    @Override
    public String toString() {
        return "GruposAct{" + "idActividad=" + idActividad + ", codGrupo=" + codGrupo + '}';
    }

}
