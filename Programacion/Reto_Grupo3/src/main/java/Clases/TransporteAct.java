/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author DAW112
 */
public class TransporteAct {
    
    private int idActividad;
    private int tipoTransporte;

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(int tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    @Override
    public String toString() {
        return "TransporteAct{" + "idActividad=" + idActividad + ", tipoTransporte=" + tipoTransporte + '}';
    }

}
