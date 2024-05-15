/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 * Esta clase contiene los atributos y metodos de un grupo
 * @author Grupo3
 * @version 1.0
 */
public class Grupos {
    
    private int idGrupo;
    private String codgrupo;
    private int idCurso;
    private int alumnos;
    private boolean estadoGrupos;

    /**
     * 
     * @param idGrupo Numero identificador del grupo
     * @param codgrupo Codigo identificador del grupo
     * @param idCurso Numero identificador del curso asociado al grupo
     * @param alumnos Total numero de alumnos de cada grupo
     * @param estadoGrupos Indica si esta activo o no el grupo
     */
    
    public Grupos(int idGrupo, String codgrupo, int idCurso, int alumnos, boolean estadoGrupos) {
        this.idGrupo = idGrupo;
        this.codgrupo = codgrupo;
        this.idCurso = idCurso;
        this.alumnos = alumnos;
        this.estadoGrupos = estadoGrupos;
    }
    
    /**
     * Método para obtener el ID del grupo.
     * @return Regresa el ID del grupo.
     */
    public int getIdGrupo() {
        return idGrupo;
    }
    
    /**
     * Método para obtener el Codigo del grupo.
     * @return Regresa el Codigo del grupo
     */
    public String getCodgrupo() {
        return codgrupo;
    }

    /**
     * Método para obtener el ID
     * @return Regresa el ID del curso asociado al grupo
     */
    public int getIdCurso() {
        return idCurso;
    }

    /**
     * Método para obtener el numero de alumnos
     * @return Regresa el Numero de alumnos
     */
    public int getAlumnos() {
        return alumnos;
    }
    
    /**
     * Método para obtener el estado de un grupo
     * @return Regresa un boolean del estado del grupo
     */
    public boolean isEstadoGrupos() {
        return estadoGrupos;
    }
    
}
