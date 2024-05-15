/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Gus y Joaco
 */
public class Fotos {
    
    private int idFotos;
    private String url;
    private String descripcion;
    private int idActividad;

    public Fotos(int idFotos, String url, String descripcion, int idActividad) {
        this.idFotos = idFotos;
        this.url = url;
        this.descripcion = descripcion;
        this.idActividad = idActividad;
    }

    public int getIdFotos() {
        return idFotos;
    }

    public String getUrl() {
        return url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getIdActividad() {
        return idActividad;
    }
    
}
