/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_de_agenda_reuniones;

import java.time.LocalDate;
import java.time.LocalTime;


/**
 *
 * @author tomas
 */
public class Reunion extends Actividad {
    
    private String anfitrion;
    
    public Reunion(String id, String titulo, String tipoActividad, LocalDate fecha, 
                   LocalTime horaInicio, LocalTime horaFin, 
                   String anfitrion) {
        
        super(id , titulo , tipoActividad , fecha , horaInicio , horaFin);
        this.anfitrion = anfitrion;

    }
    
    public String getAnfitrion() { return anfitrion; }
    public void setAnfitrion(String anfitrion) { this.anfitrion = anfitrion; }
    
    // Métodos
    // Sobreescritura de método obtenerDetalles
    /*@Override
    public String obtenerDetalles() {
        return super.obtenerDetalles() + " | Anfitrión: " + anfitrion;
    }*/
}
    

