/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;
import com.mycompany.gestion_de_agenda_reuniones.Actividad;
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
    
    //Carga las fechas de la agenda en el cbxFechas de la ventana
    public static void cargarFechasComboBox(JComboBox<String> cbxFechas, Agenda actividades) {
        
        cbxFechas.removeAllItems();
        /*
         DateTimeFormatter permite cambiar el formato en el que se almacena las variables LocalDatte
        (yyyy/MM/dd) al formato universal (dd/MM/yyyy) , luego cuando iteramos sobre la listaFechas
        a cada fecha la cambiamos al formato unviersal y la añadimos al cbxFechas.
        */
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<LocalDate> fechas = actividades.getDiasHabilitados();
        for(LocalDate fecha : fechas){
            cbxFechas.addItem(fecha.format(formato));
        }
    }   
    
    /*
     Extrae el texto del ComboBox y lo convierte a LocalDate de forma segura.
     Retorna null si no hay ninguna fecha seleccionada.
     */
    public static LocalDate obtenerFechaDeComboBox(JComboBox<String> cbxFechas) {
        // Verificamos que el ComboBox no esté vacío
        if (cbxFechas.getSelectedItem() == null) {
            return null; 
        }
        
         String fechaTexto = cbxFechas.getSelectedItem().toString();
         DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // Retornamos el objeto ya transformado
        return LocalDate.parse(fechaTexto, formato);
    }
    
     //Busca las actividades de una fecha específica en la Agenda y llena el ComboBox.
    public static void cargarActividadesComboBox(JComboBox<String> cbxActividades, Agenda actividades, LocalDate fecha) {
        // 1. Limpiamos el ComboBox para borrar búsquedas anteriores
        cbxActividades.removeAllItems();
        if (fecha == null) {
            return; 
        }

        // 2. Traemos la lista de ese día específico
        List<Actividad> listaDelDia = actividades.buscarActividades(fecha);

        // 3. Verificamos que la lista exista y tenga elementos
        if (listaDelDia != null && !listaDelDia.isEmpty()) {
            // Llenamos el ComboBox con el formato "ID | Título"
            for (Actividad act : listaDelDia) {
                cbxActividades.addItem("[" + act.getId() + "] " + act.getTipoClase() + " | " + act.getTitulo());
            }
        } 
    }
    
}
