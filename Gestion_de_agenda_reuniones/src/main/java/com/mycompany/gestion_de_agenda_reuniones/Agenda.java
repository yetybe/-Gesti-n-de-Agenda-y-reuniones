/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion_de_agenda_reuniones;
import Excepciones.CamposActividadException;
import Excepciones.FechaInvalidaException;
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
    
    public boolean agregarFechaCSV(LocalDate fecha){
          if (!mapaActividades.containsKey(fecha)) {
          mapaActividades.put(fecha, new ArrayList<>());
          return true;
        }
        return false;
                
    }
    
    public void  agregarFecha(LocalDate fecha) throws FechaInvalidaException{
        if (fecha.isBefore(LocalDate.now())) {
            throw new FechaInvalidaException("Error crítico: No puedes agregar fechas en el pasado.");
        }
          if (!mapaActividades.containsKey(fecha)) {
          mapaActividades.put(fecha, new ArrayList<>());

        }else{
              throw new FechaInvalidaException("Error crítico: No puedes agregar fechas que ya estan en la agenda");
          }
        
    }
    public void agregarActividadCSV(Actividad act){
        if (act == null) return;
        LocalDate fecha = act.getFecha();
        
        if(mapaActividades.containsKey(fecha)){
          mapaActividades.get(fecha).add(act);
        }
        else{
            return;
        }
    }
    
    public Actividad buscarActividad(String id) {
        for (List<Actividad> lista : mapaActividades.values()) {
             for (Actividad act : lista) {
                if (act.getId().equals(id)) return act;
            }
        }
        return null;
       }
        
    public void agregarActividad(Actividad act) throws CamposActividadException {
        if (act == null){
            throw new CamposActividadException("Error crítico: La actividad está vacía.");
        }
        
        if (this.buscarActividad(act.getId()) != null){
            throw new CamposActividadException("Error crítico: Ya existe una actividad con el ID " + act.getId());
        }
        
        // 3. Extraer la fecha de la actividad
        LocalDate fechaActividad = act.getFecha();

        // 5. Agregamos la actividad a la lista de ese día
        mapaActividades.get(fechaActividad).add(act);
    }
       
    public List<LocalDate> getDiasHabilitados() {
        List<LocalDate> lista =  new ArrayList<>(mapaActividades.keySet());
        java.util.Collections.sort(lista);
        
        return lista;
    }
    
    public int getSizeActividades(LocalDate fecha){
        return mapaActividades.get(fecha).size();
        
    }
    
    public List<Actividad> obtenerTodoElCatalogo() {
        List<Actividad> todasLasActividades = new ArrayList<>();
    
        for (List<Actividad> listaDiaria : mapaActividades.values()) {
            todasLasActividades.addAll(listaDiaria); 
        }
    
        return todasLasActividades;
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
        Actividad actividadEncontrada = null;
        for(Actividad actividad : listActividad){
            if (actividad.getId().equals(id)){
                actividadEncontrada = actividad;
                break;
            }
            
        }
        if(actividadEncontrada != null){
          listActividad.remove(actividadEncontrada);
        }
    }
    
    public void cambiarFechas(LocalDate fechaVieja , LocalDate fechaNueva){
        List<Actividad>listaAct = mapaActividades.get(fechaVieja);
        if(listaAct != null){
            mapaActividades.put(fechaNueva, listaAct);
            mapaActividades.remove(fechaVieja);
          }
        

        }
    
    public List<Actividad> buscarActividad(LocalDate fecha){
          return mapaActividades.get(fecha);
    }
    
    /*
        Esta funcion extrae del mapa la lista completa de actividades criticas durante 
        una semana desde una fecha inicial hasta una fecha final.
    
    */
    public List<Actividad> obtenerActCriticas(LocalDate fechaInicio ,LocalDate fechaFinal) {
        
          List<Actividad> actividadesCriticas = new ArrayList<>();
        
          for (LocalDate fecha : mapaActividades.keySet()) {
            // Si la fecha está dentro de la ventana de 7 días...
            if (!fecha.isBefore(fechaInicio) && !fecha.isAfter(fechaFinal)) {
                
                List<Actividad> listaDelDia = mapaActividades.get(fecha);
                
                for (Actividad act : listaDelDia) {
                    // Solo guardamos a los "sospechosos" de causar estrés
                    if (act instanceof Evaluacion || act instanceof Reunion) {
                        actividadesCriticas.add(act);
                    }
                }
            }
        }
        
        return actividadesCriticas; // Retornamos la lista completa a la ventana
    }
    

 
}
            
