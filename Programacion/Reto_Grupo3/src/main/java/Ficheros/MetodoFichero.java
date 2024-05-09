/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ficheros;

import Clases.Cursos;
import Clases.Departamentos;
import Clases.Grupos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Clases.Profesor;
import Enums.Perfil;

/**
 *
 * @author DAW112
 */
public class MetodoFichero {

    public static ArrayList<Profesor> listarProfesores(File aux) {

        ArrayList<Profesor> leeProfesores = new ArrayList<>();

        BufferedReader br = null;
        String linea;
        String data[];
        int cont = 1;

        try {

            br = new BufferedReader(new FileReader(aux));

            while ((linea = br.readLine()) != null) {

                //StringTokenizer st = new StringTokenizer(linea,",");  
                data = linea.split(",");
                String Apellido = data[0];
                String nombre = data[1];
                String dni = data[2];
                String mail = data[3];
                String departamento = data[4];
                Perfil perfil = Perfil.PROFESOR;

                Profesor p = new Profesor(cont,nombre, Apellido, dni, mail, departamento,perfil);

                leeProfesores.add(p);

            }

        } catch (IOException ex) {

            System.err.println(ex.toString());

        } finally {

            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    System.out.println("Error al cerrar");
                }
            }

        }

        return leeProfesores;

    }

    public static ArrayList<Cursos> listarCursos(File aux) {

        ArrayList<Cursos> leeCursos = new ArrayList<>();

        BufferedReader br = null;
        String linea;
        String data[];

        try {

            br = new BufferedReader(new FileReader(aux));

            while ((linea = br.readLine()) != null) {

                //StringTokenizer st = new StringTokenizer(linea,",");  
                data = linea.split(",");
                int idCurso = Integer.parseInt(data[0]);
                String codCurso = data[1];
                String desCurso = data[2];
                String etapa = data[3];
                int activo = Integer.parseInt(data[4]);

                Cursos c = new Cursos(idCurso, codCurso, desCurso, etapa, activo);

                leeCursos.add(c);

            }

        } catch (IOException ex) {

            System.err.println(ex.toString());

        } finally {

            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    System.out.println("Error al cerrar");
                }
            }

        }

        return leeCursos;

    }
    
    public static ArrayList<Departamentos> listarDepartamentos(File aux) {

        ArrayList<Departamentos> leeDepartamentos = new ArrayList<>();

        BufferedReader br = null;
        String linea;
        String data[];

        try {

            br = new BufferedReader(new FileReader(aux));

            while ((linea = br.readLine()) != null) {

                //StringTokenizer st = new StringTokenizer(linea,",");  
                data = linea.split(",");
                int id = Integer.parseInt(data[0]);
                String cod = data[1];
                String nombre = data[2];
                int jefe = (int) (Math.floor(Math.random()*(15-1+1)+1));

                Departamentos d = new Departamentos(id, cod, nombre, jefe);

                leeDepartamentos.add(d);
                
            }

        } catch (IOException ex) {

            System.err.println(ex.toString());

        } finally {

            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    System.out.println("Error al cerrar");
                }
            }

        }

        return leeDepartamentos;

    }
    
    public static ArrayList<Grupos> listarGrupos(File aux) {

        ArrayList<Grupos> leeGrupos = new ArrayList<>();

        BufferedReader br = null;
        String linea;
        String data[];

        try {

            br = new BufferedReader(new FileReader(aux));

            while ((linea = br.readLine()) != null) {

                //StringTokenizer st = new StringTokenizer(linea,",");  
                data = linea.split(",");
                int idgrupo = Integer.parseInt(data[0]);
                String codGrupo = data[1];
                int idcurso = Integer.parseInt(data[2]);
                int alumno = Integer.parseInt(data[3]);
                int activo = Integer.parseInt(data[4]);

                Grupos g = new Grupos(idgrupo, codGrupo, idcurso, alumno, activo);

                leeGrupos.add(g);
                
            }

        } catch (IOException ex) {

            System.err.println(ex.toString());

        } finally {

            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    System.out.println("Error al cerrar");
                }
            }

        }

        return leeGrupos;

    }
    
    void pruebaFichero(){
        
        File c = new File("src/main/archivosCSV/cursos.csv");
        File d = new File("src/main/archivosCSV/departamentos.csv");
        File g = new File("src/main/archivosCSV/grupos.csv");
        File p = new File("src/main/archivosCSV/profesores.csv");
        
        ArrayList<Cursos> auxC = MetodoFichero.listarCursos(c);
        ArrayList<Departamentos> auxD = MetodoFichero.listarDepartamentos(d);
        ArrayList<Grupos> auxG = MetodoFichero.listarGrupos(g);
        ArrayList<Profesor> auxP = MetodoFichero.listarProfesores(p);
        
        for (Cursos cur : auxC) {
            
            System.out.println(cur.toString());
            
        }
        
        System.out.println("");
        
        for (Departamentos dep : auxD) {
            
            System.out.println(dep.toString());
            
        }
        
        System.out.println("");
        
        for (Grupos gru : auxG) {
            
            System.out.println(gru.toString());
            
        }
        
        System.out.println("");
        
        for (Profesor pro : auxP) {
            
            System.out.println(pro.toString());
            
        }
        
    }

}
