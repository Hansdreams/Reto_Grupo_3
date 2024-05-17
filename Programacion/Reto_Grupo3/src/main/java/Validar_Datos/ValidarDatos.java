/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validar_Datos;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 * Clase que contiene metodos estaticos que validaran la entra de datos para la aplicacion
 * @author Grupo3
 * @version 1.0
 */
public class ValidarDatos {

    /**
     * Motodo que muestra un JOptionPane con el mensaje ingresado
     *
     * @param mensaje
     */
    private static void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, "<html><p style = \" font: 16px \">" + mensaje.toUpperCase() + "</p></html>");
    }

    /**
     * Motodo que muestra un JOptionPane con el mensaje de Error de color rojo
     *
     * @param mensajeE
     */
    private static void mensajeError(String mensajeE) {

        JOptionPane.showMessageDialog(null, "<html><p style = \" font: 16px ; color: #FF0000 \"  >" + mensajeE.toUpperCase() + "</p></html>");

    }
    
    public static boolean validarDni(String dni) {

        final String valido = "TRWAGMYFPDXBNJZSQVHLCKE";
        boolean correcto = false;

        try {

            if (Pattern.matches("[0-9]{8}[A-ZÑ]", dni)) {

                String intDNI = dni.substring(0, 8);
                char letraDNI = dni.charAt(8);
                int valNumDni = Integer.parseInt(intDNI) % 23;

                if (dni.length() == 9 && valido.charAt(valNumDni) == letraDNI) {

                    mensaje("dni valido!!!");
                    correcto = true;
                    

                } 

            } 

        } catch (InputMismatchException ex) {

            System.out.println("Error InputMismatchException: "+ex);

        } catch (Exception e) {

            System.out.println("Error Exception: "+e);

        }

        return correcto;

    }
     
    public static boolean validarNombre(String nombre) {
        
        boolean correcto = false;
        
            try {

                if (Pattern.matches("[A-ZÁÉÍÓÚ][a-zñáéíóú]{2,25}",nombre)) {

                    correcto = true;

                } 

            } catch (InputMismatchException ex){
                
               mensajeError("Ingrese letras!");
                
            }


        return correcto;

    }
    
    public static boolean validarApellido(String apellido) {
        
        boolean correcto = false;
        
            try {

                if (Pattern.matches("[A-Z][a-z]{1,23}\\s[A-Z][a-z]{1,23}",apellido)) { 
                    
                    correcto = true;


                } else {

                    throw new InputMismatchException();

                }

            } catch (InputMismatchException ex){
                
               mensajeError("Ingrese letras!");
                
            }


        return correcto;

    }
    
    public static boolean validarCorreo(String correo){

        boolean valido=false;
        
        try {
            
            if(correo.matches("[A-ZÑÁÉÍÓÚa-zñáéíóú]{2,23}[.][A-ZÑÁÉÍÓÚa-zñáéíóú]{2,23}")){ 

                valido=true;
                
            }
            
        } catch (Exception e) {
            
            System.out.println("Error Exception: "+e);
            
        }
        
        return valido;
        
    }
    
    public static boolean validarContraseña(String contraseña){

        boolean valido=false;
        
        try {
            
            if(contraseña.matches("[0-9A-ZÑÁÉÍÓÚa-zñáéíóú]{3,23}")){ 

                valido=true;
                
            }
            
        } catch (Exception e) {
            
            System.out.println("Error Exception: "+e);
            
        }

        return valido;
        
    }
    
    public static boolean validarNombreActivida(String nombre){

        boolean valido=false;
        
        try {
            
            if(nombre.matches("[0-9A-ZÑÁÉÍÓÚa-zñáéíóú]{3,}")){ 

                valido=true;
                
            }
            
        } catch (Exception e) {
            
            System.out.println("Error Exception: "+e);
            
        }

        return valido;
        
    }
    
    public static boolean validarFecha(String fecha){

        boolean valido=false;
        int dia,mes,anio;
        LocalDate fec;
        
        try {
            
            if(fecha.matches("[0-9]{2}[-][0-9]{2}[-][0-9]{4}")){ 

                dia = Integer.parseInt(fecha.substring(0,2));
                mes = Integer.parseInt(fecha.substring(3,5));
                anio = Integer.parseInt(fecha.substring(6,fecha.length()));

                fec = LocalDate.of(anio, mes, dia);
                
                mensaje("Fecha correcto!!!");
                
                valido=true;
                
            }
            
        } catch (DateTimeException e) {
            
            mensajeError("Fecha Incorrecta!!!");
            
        }

        return valido;
        
    }
    
    public static boolean validarHora(String hora){

        boolean valido=false;
        int h,m;
        LocalTime tiempo;
        
        try {
            
            if(hora.matches("[0-9]{2}[:][0-9]{2}")){ 
                
                h = Integer.parseInt(hora.substring(0, 2));
                m = Integer.parseInt(hora.substring(3, hora.length()));
                
                tiempo = LocalTime.of(h, m);
                
                mensaje("Hora correcta!!!");
                
                valido=true;
                
            }
            
        } catch (DateTimeException e) {
            
            mensajeError("hora Incorrecta!!!");
            
        }

        return valido;
        
    }
    
    public static boolean validarComentario(String comentario){

        boolean valido=false;
        
        try {
            
            if(comentario.matches("[0-9A-ZÑÁÉÍÓÚa-zñáéíóú]{0,360}")){ 

                valido=true;
                
            }
            
        } catch (Exception e) {
            
            System.out.println("Error Exception: "+e);
            
        }

        return valido;
        
    }

    
}
