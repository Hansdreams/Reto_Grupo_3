/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 * Esta clase contiene los atributos y metodos de un Departamento
 * @author Grupo3
 * @version 1.0
 */

public class Departamentos {
    
    private int id;
    private String cod;
    private String nombre;
    private int jefe;

    /** 
     * @param id Numero de identificacion del departamento
     * @param cod Codigo de indetificacion del despartamento
     * @param nombre Nombre del departamento
     * @param jefe Numero indentificador del profesor jefe del departamento
     */
    
    public Departamentos(int id, String cod, String nombre, int jefe) {
        this.id = id;
        this.cod = cod;
        this.nombre = nombre;
        this.jefe = jefe;
    }
    
    /**
     * Método para obtener el ID del departamento.
     * @return Regresa el ID del departamento.
     */
    public int getId() {
        return id;
    }

    /**
     * Método para obtener el código del departamento.
     * @return Regresa el código del departamento.
     */
    public String getCod() {
        return cod;
    }

    /**
     * Método para obtener el nombre del departamento.
     * @return Regresa el nombre del departamento.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para obtener el ID del jefe del departamento.
     * @return Regresa el ID del jefe del departamento.
     */
    public int getJefe() {
        return jefe;
    }
    
}
