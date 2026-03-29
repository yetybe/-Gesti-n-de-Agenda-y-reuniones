/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_de_agenda_reuniones;
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
          LocalDate fecha = LocalDate.parse(datos[4]);
          actividades.agregarFecha(fecha);
          
          String id = datos[1];
          String titulo = datos[2];
          String tipoActividad = datos[3];
          LocalTime horaInicio = LocalTime.parse(datos[5]);
          LocalTime horaFin = LocalTime.parse(datos[6]);
          Actividad  act = null;
          String tipoClase = datos[0];
          
          switch(tipoClase){
              
              case "REUNION":
                  String anfitrion = datos[10];
                  act = new Reunion(id ,titulo , tipoActividad , fecha , horaInicio, horaFin, anfitrion);
                  break;
              
              case "CLASE":
                  String sala = datos[7];
                  String profesor = datos[8];
                  String asignatura = datos[9];
                  
                  act = new ClaseUniversitaria(id ,titulo , tipoActividad , fecha , horaInicio, horaFin, sala , profesor , asignatura);
                  break;
              
              case "EVALUACION":
                  double ponderacion = Double.parseDouble(datos[11]);
                  String temario = datos[12];
                  boolean esGrupal = Boolean.parseBoolean(datos[13]);
                  
                  act = new Evaluacion(id ,titulo , tipoActividad , fecha , horaInicio, horaFin, ponderacion , temario , esGrupal);
                  break;
                  
              
             
          }
          if ( act != null){
                    actividades.agregarActividad(act);
          }
          
        }
        
        return actividades;
        
        
    }
    
}
