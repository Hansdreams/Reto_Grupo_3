/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author DAW112
 */
public class Departamentos {
    
    private int id;
    private String cod;
    private String nombre;
    private int jefe;

    public Departamentos(int id, String cod, String nombre, int jefe) {
        this.id = id;
        this.cod = cod;
        this.nombre = nombre;
        this.jefe = jefe;
    }

    public int getId() {
        return id;
    }

    public String getCod() {
        return cod;
    }

    public String getNombre() {
        return nombre;
    }

    public int getJefe() {
        return jefe;
    }

}
