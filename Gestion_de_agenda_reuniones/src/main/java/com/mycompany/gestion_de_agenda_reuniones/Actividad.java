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
public  abstract class Actividad {
    
    protected String id;
    protected String titulo;
    protected String tipoActividad;
    protected LocalDate fecha;
    protected LocalTime horaInicio;
    protected LocalTime horaFin;
    
    public Actividad(String id, String titulo, String tipoActividad, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
        this.id = id;
        this.titulo = titulo;
        this.tipoActividad = tipoActividad;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
    
    // Setters
    public void setId(String id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setTipoActividad(String tipoActividad) { this.tipoActividad = tipoActividad; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }

    // Getters
    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getTipoActividad() { return tipoActividad; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }
    
    // Métodos
    // Método para usar sobreescrtitura en clases hijas
    public String obtenerDetalles() {
        return "ID: " + getId() + " | " + getTitulo() + " [" + getTipoActividad() + "]";
    }
}