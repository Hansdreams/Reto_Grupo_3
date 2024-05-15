/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 * Esta clase contiene los atributos y metodos de fotos
 * @author Grupo3
 * @version 1.0
 */

public class Fotos {
    
    private int idFotos;
    private String url;
    private String descripcion;
    private int idActividad;

    /**
     * 
     * @param idFotos Numero identificador de las fotos
     * @param url Identificador de recursos uniforme de la foto
     * @param descripcion Descripcion de la foto
     * @param idActividad  Indica el numero identificador de la actividad asociada a la foto
     */
    
    public Fotos(int idFotos, String url, String descripcion, int idActividad) {
        this.idFotos = idFotos;
        this.url = url;
        this.descripcion = descripcion;
        this.idActividad = idActividad;
    }

     /**
     * Método para obtener el ID de la foto.
     * @return Regresa el ID de la foto.
     */
    public int getIdFotos() {
        return idFotos;
    }

    /**
     * Método para obtener la URL de la foto.
     * @return Regresa la URL de la foto.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Método para obtener la descripción de la foto.
     * @return Regresa la descripción de la foto.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método para obtener el ID de la actividad asociada a la foto.
     * @return Regresa el ID de la actividad asociada a la foto.
     */
    public int getIdActividad() {
        return idActividad;
    }
    
}
