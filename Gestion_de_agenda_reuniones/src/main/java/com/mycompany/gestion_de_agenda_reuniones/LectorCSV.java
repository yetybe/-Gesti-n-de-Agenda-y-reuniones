/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_de_agenda_reuniones;
import Excepciones.FechaInvalidaException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 *
 * @author benjja
 */
public class LectorCSV {
    
    public static Agenda leerDatosCSV() throws IOException{
        
        Agenda actividades = new Agenda();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("datos_agenda.csv"), StandardCharsets.UTF_8));
        String linea;
        boolean primeraLinea = true;
        
        while ((linea = br.readLine()) != null){
          
            
         if (linea.trim().isEmpty()) {
                    continue;
                }
         
          if (primeraLinea){
              primeraLinea = false;
              continue;
          }
          
         String[] datos = linea.split(",");
         LocalDate fecha = LocalDate.parse(datos[3]);
         actividades.agregarFechaCSV(fecha);
     
          String tipoClase = datos[0];
          String id = datos[1];
          String titulo = datos[2];
          LocalTime horaInicio = LocalTime.parse(datos[4]);
          LocalTime horaFin = LocalTime.parse(datos[5]);
          Actividad  act = null;
          
          switch(tipoClase){
              
              case "REUNION":
                  String anfitrion = datos[9];
                  act = new Reunion(tipoClase , id ,titulo , fecha , horaInicio, horaFin, anfitrion);
                  break;
              
              case "CLASE":
                  String sala = datos[6];
                  String profesor = datos[7];
                  String asignatura = datos[8];
                  
                  act = new ClaseUniversitaria(tipoClase ,id ,titulo , fecha , horaInicio, horaFin, sala , profesor , asignatura);
                  break;
              
              case "EVALUACION":
                  double ponderacion = Double.parseDouble(datos[10]);
                  String temario = datos[11];
                  boolean esGrupal = Boolean.parseBoolean(datos[12]);
                  
                  act = new Evaluacion(tipoClase , id ,titulo  , fecha , horaInicio, horaFin, ponderacion , temario , esGrupal);
                  break;
                  
              
             
          }
          if ( act != null){
                    actividades.agregarActividadCSV(act);
          }
          
        }
        
        return actividades;
        
        
    }
    
}
