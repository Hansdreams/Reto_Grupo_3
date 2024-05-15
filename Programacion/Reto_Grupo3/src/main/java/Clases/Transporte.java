/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author DAW112
 */
public class Transporte {
    
    private int idTransporte;
    private String tipoTransporte;

    public Transporte(int idTransporte, String tipoTransporte) {
        this.idTransporte = idTransporte;
        this.tipoTransporte = tipoTransporte;
    }

    public int getIdTransporte() {
        return idTransporte;
    }

    public String getTipoTransporte() {
        return tipoTransporte;
    }

}
