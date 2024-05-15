/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 * Esta clase contiene los atributos y metodos de un Cursos
 * @author Grupo3
 * @version 1.0
 */

public class Cursos {
    
    private int idCurso;
    private String codCurso;
    private String desCurso;
    private String etapa;
    private boolean activo;
    private int totalAlumnos;

    
    /**
     * @param idCurso Numero de indetificacion del curso
     * @param codCurso Codigo de identificacion del curso
     * @param desCurso Descripcion del curso
     * @param etapa Etapa en la que se encuentra el curso
     * @param activo Indica si esta en activo o no el curso
     * @param totalAlumnos Variable interna para calcular alumnos totales en los cursos
    **/
    
    
    public Cursos(int idCurso, String codCurso, String desCurso, String etapa, boolean activo, int totalAlumnos) {
        this.idCurso = idCurso;
        this.codCurso = codCurso;
        this.desCurso = desCurso;
        this.etapa = etapa;
        this.activo = activo;
        this.totalAlumnos = totalAlumnos;
    }
    
    /**
     * Método para obtener el ID del curso.
     * @return Regresa el ID del curso.
     */
    public int getIdCurso() {
        return idCurso;
    }
    /**
     * Método para obtener el código del curso.
     * @return Regresa el código del curso.
     */
    public String getCodCurso() {
        return codCurso;
    }

    /**
     * Método para obtener la descripción del curso.
     * @return Regresa la descripción del curso.
     */
    public String getDesCurso() {
        return desCurso;
    }
    
    /**
     * Método para obtener la etapa del curso.
     * @return Regresa la etapa del curso.
     */
    public String getEtapa() {
        return etapa;
    }
    
    /**
     * Método para saber si el curso está activo.
     * @return Regresa un boolean indicando si el curso está activo.
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Método para obtener el número total de alumnos en el curso.
     * @return Regresa el número total de alumnos.
     */
    public int getTotalAlumnos() {
        return totalAlumnos;
    }

}
