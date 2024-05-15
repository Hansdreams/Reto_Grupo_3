/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 * Esta clase contiene los atributos y metodos de de un transporte
 * @author Grupo3
 * @version 1.0
 */
public class Transporte {
    
    private int idTransporte;
    private String tipoTransporte;

    /**
     * 
     * @param idTransporte Numero identificador de un transporte
     * @param tipoTransporte Tipo de transporte
     */
    public Transporte(int idTransporte, String tipoTransporte) {
        this.idTransporte = idTransporte;
        this.tipoTransporte = tipoTransporte;
    }

    /**
     * Método que obtiene el id de un transporte
     * @return Regresa un id
     */
    public int getIdTransporte() {
        return idTransporte;
    }

    /**
     * Método que obtiene el tipo de transporte
     * @return Regresa el tipo de transporte
     */
    public String getTipoTransporte() {
        return tipoTransporte;
    }

}
