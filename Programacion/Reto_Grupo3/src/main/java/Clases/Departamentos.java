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

    public void setId(int id) {
        this.id = id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getJefe() {
        return jefe;
    }

    public void setJefe(int jefe) {
        this.jefe = jefe;
    }

    @Override
    public String toString() {
        return "Departamentos{" + "id=" + id + ", cod=" + cod + ", nombre=" + nombre + ", jefe=" + jefe + '}';
    }

}
