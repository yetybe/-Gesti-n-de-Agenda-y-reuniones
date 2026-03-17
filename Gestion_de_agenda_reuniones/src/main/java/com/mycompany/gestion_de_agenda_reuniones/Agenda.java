/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion_de_agenda_reuniones;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;

        
/**
 *
 * @author benjja
 */
public class Agenda {
    private Map<LocalDate, List<Actividad>> mapaActividades;
    
    public Agenda(){
        this.mapaActividades = new HashMap<>();
    }
    
    public void agregarActividad(Actividad act){
        if (act == null) return;
        
        LocalDate fecha = act.getFecha();
        mapaActividades.computeIfAbsent(fecha, k -> new ArrayList<>()).add(act);
    }
    public void eliminarActividad(LocalDate fecha, String id){
        List<Actividad> listaDelDia = mapaActividades.get(fecha);
        if (listaDelDia != null){
            boolean eliminado = listaDelDia.removeIf(act -> act.getId().equals(id));
            if (eliminado){
                if(listaDelDia.isEmpty()){
                    mapaActividades.remove(fecha);
                }
            }
        }     
    }
    
    
        
        
        
        
        
    }
            
