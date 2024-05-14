/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validar_Datos;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author DAW112
 */
public class ValidarDatos {
    
    public static boolean validarDni(String dni) {

        final String valido = "TRWAGMYFPDXBNJZSQVHLCKE";
        boolean correcto = false;

        try {

            if (Pattern.matches("[0-9]{8}[A-ZÑ]", dni)) {

                String intDNI = dni.substring(0, 8);
                char letraDNI = dni.charAt(8);
                int valNumDni = Integer.parseInt(intDNI) % 23;

                if (dni.length() == 9 && valido.charAt(valNumDni) == letraDNI) {

                    correcto = true;
                    JOptionPane.showMessageDialog(null, "Dni correcto!");

                } else {

                    throw new Exception();

                }

            } else {

                throw new InputMismatchException();

            }

        } catch (InputMismatchException ex) {

            JOptionPane.showMessageDialog(null, "Ingrese el formato de dni valido!");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Dni Incorrecto!");

        }

        return correcto;

    }
     
    public static boolean validarNombre(String nombre) {
        
        boolean correcto = false;
        
            try {

                if (Pattern.matches("[A-ZÁÉÍÓÚ][a-zñáéíóú]{2,25}",nombre)) {
                    
                    correcto = true;
                    
                    JOptionPane.showMessageDialog(null, "Nombre correcto!");

                } else {

                    throw new InputMismatchException();

                }

            } catch (InputMismatchException ex){
                
               JOptionPane.showMessageDialog(null, "Ingrese letras!");
                
            }


        return correcto;

    }
    
    public static boolean validarApellido(String apellido) {
        
        boolean correcto = false;
        
            try {

                if (Pattern.matches("[A-Z][a-z]{1,23}\\s[A-Z][a-z]{1,23}",apellido)) { 
                    
                    correcto = true;
                    
                    JOptionPane.showMessageDialog(null, "Apellido/s correcto!");

                } else {

                    throw new InputMismatchException();

                }

            } catch (InputMismatchException ex){
                
               JOptionPane.showMessageDialog(null, "Ingrese letras!");
                
            }


        return correcto;

    }
    
    public static String validarCorreo(String mensaje){
        String cadena="";
        boolean valido=false;
        do{
            System.out.println(mensaje);
            cadena=new Scanner(System.in).nextLine();
            
            if(cadena.matches("[A-ZÑa-zñÁÉÍÓÚáéíóú0-9+_.-]+@(educantabria.es+)$")){ 
                
                System.out.println("Correo correcto!");
                valido=true;
                
            }else{
                
                System.out.println("Error!: formato del correo incorrecto!");
                
            }
            
        }while(!valido);
        
        return cadena.toLowerCase();
    }

    
}
