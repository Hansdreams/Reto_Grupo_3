/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Clases;

import Acceso_BaseDatos.AccesoBaseDatos;
import Ficheros.MetodoFichero;
import java.io.File;
import java.util.ArrayList;
import Enums.Perfil;
import DAOImp.SolicitudDAOImp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author DAW112
 */
public class Prueba {

    private Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SolicitudDAOImp soli = new SolicitudDAOImp();
        
       /*int diaI = Integer.parseInt(textFechaInicial.getText().substring(0, 2));
        int mesI = Integer.parseInt(textFechaInicial.getText().substring(3, 5));
        int anioI = Integer.parseInt(textFechaInicial.getText().substring(6, textFechaInicial.getText().length()));
        LocalDate fecInicial = LocalDate.of(anioI, mesI, diaI);
        int diaF = Integer.parseInt(textFechaFinal.getText().substring(0, 2));
        int mesF = Integer.parseInt(textFechaFinal.getText().substring(3, 5));
        int anioF = Integer.parseInt(textFechaFinal.getText().substring(6, textFechaFinal.getText().length()));
        LocalDate fecFinal = LocalDate.of(anioF, mesF, diaF);
        int horaI = Integer.parseInt(textHoraInicial.getText().substring(0, 2));
        int minutosI = Integer.parseInt(textHoraInicial.getText().substring(3, textHoraInicial.getText().length()));*/
        
        List<Solicitud> aux = soli.listar();
        
        for (Solicitud solicitud : aux) {
            
            System.out.println(solicitud.toString());
            
        }
       
    }

}
