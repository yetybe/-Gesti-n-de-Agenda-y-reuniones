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


        
/**
 *
 * @author benjja
 */
public class Agenda {
    private Map<LocalDate, List<Actividad>> mapaActividades;
    
    public Agenda(){
        this.mapaActividades = new HashMap<>();
    }
    
    public void agregarFecha(LocalDate fecha){
          if (!mapaActividades.containsKey(fecha)) {
          mapaActividades.put(fecha, new ArrayList<>());
        }
    }
    public void agregarActividad(Actividad act){
        if (act == null) return;
        LocalDate fecha = act.getFecha();
        
        if(mapaActividades.containsKey(fecha)){
          mapaActividades.get(fecha).add(act);
        }
        else{
            return;
        }
        
    }
    
    public List<LocalDate> getDiasHabilitados() {
        List<LocalDate> lista =  new ArrayList<>(mapaActividades.keySet());
        java.util.Collections.sort(lista);
        
        return lista;
    }
    
    public int getSizeActividades(LocalDate fecha){
        return mapaActividades.get(fecha).size();
        
    }
    
    public List<Actividad> getActividades(LocalDate fecha){
        return mapaActividades.get(fecha);
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
    
    public List<Actividad> eliminarFecha(LocalDate fecha){
        return mapaActividades.remove(fecha);
        
        }
    
    public void eliminarActividadPorId(LocalDate fecha , String id){
        List<Actividad> listActividad = mapaActividades.get(fecha);
        for(Actividad actividad : listActividad){
            if (actividad.getId().equals(id)){
                listActividad.remove(actividad);
            }
            
        }
    }
 
    }
            
