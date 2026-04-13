/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;
import com.mycompany.gestion_de_agenda_reuniones.Agenda;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JComboBox;
/**
 *
 * @author benjja
 */


public class GestorUI {

    public static void cargarFechasComboBox(JComboBox<String> cbxFechas, Agenda actividades) {
        
        cbxFechas.removeAllItems();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<LocalDate> fechas = actividades.getDiasHabilitados();
        
        for(LocalDate fecha : fechas){
            cbxFechas.addItem(fecha.format(formato));
        }
    }
    
    public static void cargarActividadesFecha(LocalDate fecha , JComboBox<String> cbxActividades){
        
        
    }
}
