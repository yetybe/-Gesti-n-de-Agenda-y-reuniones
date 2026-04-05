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
public class ClaseUniversitaria extends Actividad {
    
    private String sala;
    private String profesor;
    private String asignatura;
    
    public ClaseUniversitaria(String tipoClase, String id, String titulo , LocalDate fecha, 
                   LocalTime horaInicio, LocalTime horaFin, 
                   String sala , String profesor , String asignatura  ){
        
        super(tipoClase ,id , titulo , fecha , horaInicio , horaFin);
        this.sala = sala;
        this.profesor = profesor;
        this.asignatura = asignatura;
    }
    
    // Setters
    public void setSala(String sala){
        this.sala = sala;
    }
    
    public void setProfesor(String profesor){
        this.profesor = profesor;
    }
    
    public void setAsignatura(String asignatura){
        this.asignatura = asignatura;
    }
    
    // Getters
    public String getSala(){
        return sala;
    }
    
    public String getProfesor(){
        return profesor;
    }
    
    public String getAsignatura(){
        return asignatura;
    }
    
    // Métodos
    // Sobreescritura de método obtenerDetalles
    @Override
    public String obtenerDetalles() {
        return super.obtenerDetalles() + " | Sala: " + sala + " | Prof: " + profesor;
    }
    
    // Sobreescritura de método exportarDatos
    @Override
    public String exportarDatos() {
        return super.exportarDatos() + "," + sala + "," + profesor + "," + asignatura;
    }
}
