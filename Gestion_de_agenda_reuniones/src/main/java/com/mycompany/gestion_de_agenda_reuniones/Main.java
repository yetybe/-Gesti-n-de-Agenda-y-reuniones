/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestion_de_agenda_reuniones;
import com.mycompany.gestion_de_agenda_reuniones.ventanas.VentanaLauncher;
import java.io.IOException;


/**
 *
 * @author Benjamin
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Agenda agendaActividades = null;
        System.out.println("Iniciando sistema y leyendo archivo CSV....");
        agendaActividades = LectorCSV.leerDatosCSV();
        
        if ( agendaActividades == null) return;
        System.out.println("¡Datos cargados exitosamente!");
        
        VentanaLauncher ventanaInicial = new VentanaLauncher(agendaActividades);
        ventanaInicial.setLocationRelativeTo(null);
        ventanaInicial.setVisible(true);   
    }
}
