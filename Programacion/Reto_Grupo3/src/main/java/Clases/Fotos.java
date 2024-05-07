/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author DAW112
 */
public class Fotos {
 
    private int idFoto;
    private String url;
    private String descripcion;
    private int idActividad;

    public Fotos(int idFoto, String url, String descripcion, int idActividad) {
        this.idFoto = idFoto;
        this.url = url;
        this.descripcion = descripcion;
        this.idActividad = idActividad;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    @Override
    public String toString() {
        return "Fotos{" + "idFoto=" + idFoto + ", url=" + url + ", descripcion=" + descripcion + ", idActividad=" + idActividad + '}';
    }

}
