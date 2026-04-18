/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_de_agenda_reuniones;
import Excepciones.FechaInvalidaException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;


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
          
         String[] datos = linea.split(",", -1);
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
                  String anfitrion = (datos.length > 9 && !datos[9].isEmpty()) ? datos[9] : "";
                  act = new Reunion(tipoClase , id ,titulo , fecha , horaInicio, horaFin, anfitrion);
                  break;
              
              case "CLASE":
                  String sala = (datos.length > 6 && !datos[6].isEmpty()) ? datos[6] : "";
                  String profesor = (datos.length > 7 && !datos[7].isEmpty()) ? datos[7] : "";
                  String asignatura = (datos.length > 8 && !datos[8].isEmpty()) ? datos[8] : "";
                  
                  act = new ClaseUniversitaria(tipoClase ,id ,titulo , fecha , horaInicio, horaFin, sala , profesor , asignatura);
                  break;
              
              case "EVALUACION":
                  double ponderacion = (datos.length > 10 && !datos[10].isEmpty()) ? Double.parseDouble(datos[10]) : 0.0;
                  String temario = (datos.length > 11 && !datos[11].isEmpty()) ? datos[11] : "";
                  boolean esGrupal = (datos.length > 12 && !datos[12].isEmpty()) ? Boolean.parseBoolean(datos[12]) : false;
                  
                  act = new Evaluacion(tipoClase , id ,titulo  , fecha , horaInicio, horaFin, ponderacion , temario , esGrupal);
                  break;
                  
              
             
          }
          if ( act != null){
                    actividades.agregarActividadCSV(act);
          }
          
        }
        
        return actividades;
        
        
    }
    
    public static void guardarDatosCSV(Agenda agenda) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get("datos_agenda.csv"), StandardCharsets.UTF_8);
        writer.write("TipoClase,id,titulo,fecha,horaInicio,horaFin,sala,profesor,asignatura,anfitrion,ponderacionNota,temario,esGrupal\n");
        
        List<LocalDate> fechas = new ArrayList<>(agenda.getMapaActividades().keySet());
        Collections.sort(fechas);
        
        for (LocalDate fecha : fechas) {
            List<Actividad> actividades = agenda.buscarActividad(fecha);
            for (Actividad act : actividades) {
                writer.write(act.exportarDatos() + "\n");
            }
        }
        
        writer.close();
    }
        
}
