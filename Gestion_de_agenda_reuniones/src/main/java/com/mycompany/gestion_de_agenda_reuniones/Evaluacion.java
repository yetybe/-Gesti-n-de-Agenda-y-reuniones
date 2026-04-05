/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_de_agenda_reuniones;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author benjja
 */
public class Evaluacion  extends Actividad{
    
    private double ponderacionNota;
    private String temario;
    private boolean esGrupal;
    
    public Evaluacion(String tipoClase , String id, String titulo , LocalDate fecha, 
                   LocalTime horaInicio, LocalTime horaFin, 
                   double ponderacionNota , String temario , boolean esGrupal){
        
        
          super(tipoClase, id , titulo , fecha , horaInicio , horaFin);
          this.ponderacionNota = ponderacionNota;
          this.esGrupal = esGrupal;
          this.temario = temario;
    }
    
    public void setPNota(double ponderacionNota){
        this.ponderacionNota = ponderacionNota;
        
    }
    
    public void setTemario(String temario){
        this.temario = temario;
        
    }
    
    public void setEsGrupal(boolean esGrupal){
        this.esGrupal = esGrupal;
        
    }
   
    public double getPonderacion(){
        return ponderacionNota;
    }
    
    public String getTemario(){
        return temario;
    }
    
    public boolean getEsGrupal(){
        return esGrupal;
    }
    
    // Métodos
    // Sobreescritura de método obtenerDetalles
    @Override
    public String obtenerDetalles() {
        String modo = esGrupal ? "Grupal" : "Individual";
        return super.obtenerDetalles() + " | Ponderación: " + ponderacionNota + "% (" + modo + ")";
    }
    
}
