
package com.mycompany.gestion_de_agenda_reuniones;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Terminal {
    private Agenda agenda;
    private Scanner sc;

    public Terminal(Agenda agenda) {
        this.agenda = agenda;
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion = -1;
        while (opcion != 0) {
            limpiarPantalla();
            
            System.out.println("========= AGENDA DE ACTIVIDADES =========");
            System.out.println("1.  Habilitar Fecha en Agenda");
            System.out.println("2.  Agregar actividad");
            System.out.println("3.  Reporte Fechas Agenda");
            System.out.println("4.  Reporte Actividades Agenda");
            System.out.println("5.  Eliminar Fecha en Agenda");
            System.out.println("6.  Eliminar Actividad");
            System.out.println("7.  Editar Fechas");
            System.out.println("8.  Editar Actividades");
            System.out.println("9.  Buscar Fecha");
            System.out.println("10. Buscar Actividad");
            System.out.println("11. Monitor de Intensidad Semanal");
            System.out.println("0.  Volver al Launcher");
            System.out.println("\nSeleccione una funcionalidad: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                
                if (opcion != 0) {
                    limpiarPantalla();
                    ejecutarOpcion(opcion);
                    pausar();
                } else {
                    System.out.println("Saliendo de la terminal...");
                }
            } catch (NumberFormatException e) {
                limpiarPantalla();
                System.out.println(">>> Error: Ingrese un número válido.");
                pausar();
            }
        }
    }
    
    private void limpiarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }
    
    private void pausar() {
        System.out.println("\n[Presione ENTER para continuar...]");
        sc.nextLine();
    }
    
    public void habilitarFecha(){
        System.out.println("\n--- HABILITAR FECHA EN AGENDA ---");
        System.out.println("(Ingrese '0' para volver al menú principal)");
        System.out.println("Ingrese la nueva fecha (formato dd/mm/aaaa): ");
        String input = sc.nextLine();
        
        if (input.equals("0")) {
            return;
        }

        try {
            java.time.format.DateTimeFormatter formato = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
            java.time.LocalDate nuevaFecha = java.time.LocalDate.parse(input, formato);
            
            agenda.agregarFecha(nuevaFecha);
            
            System.out.println(">>> ¡Éxito! La fecha " + input + " ha sido habilitada en la agenda.");

        } catch (java.time.format.DateTimeParseException e) {
            System.out.println(">>> Error: El formato es incorrecto o la fecha no es válida.");
            System.out.println(">>> Asegúrese de usar dd/mm/aaaa (ej: 15/04/2026).");
        } catch (Exception e) {
            System.out.println(">>> Error de Agenda: " + e.getMessage());
        }
    }
    
    public void agregarActividad() {
    System.out.println("\n--- AGREGAR ACTIVIDAD ---");
    System.out.println("(Escriba '0' en cualquier momento para cancelar)");
    
    try {
        System.out.println("Seleccione tipo (1. Reunion | 2. Evaluacion | 3. Clase):");
        String opcionStr = sc.nextLine();
        if (opcionStr.equals("0")) return;
        int opcionTipo = Integer.parseInt(opcionStr);

        System.out.println("Ingrese Fecha (dd/mm/aaaa):");
        String fechaStr = sc.nextLine();
        if (fechaStr.equals("0")) return;
        
        java.time.format.DateTimeFormatter formato = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
        java.time.LocalDate fechaFinal = java.time.LocalDate.parse(fechaStr, formato);

        // VERIFICACIÓN CRÍTICA: ¿La fecha existe en la agenda?
        // Asumiendo que tu clase Agenda tiene un método para verificar esto
        if (!agenda.getDiasHabilitados().contains(fechaFinal)) {
            System.out.println(">>> Error: La fecha " + fechaStr + " no está habilitada en la agenda.");
            System.out.println(">>> Use primero la opción 1 para habilitarla.");
            return;
        }

        System.out.println("ID:");
        String id = sc.nextLine();
        if (id.equals("0")) return;
        
        System.out.println("Titulo:");
        String titulo = sc.nextLine();
        if (titulo.equals("0")) return;

        System.out.println("Hora inicio (HH:mm):");
        String hiStr = sc.nextLine();
        if (hiStr.equals("0")) return;
        java.time.LocalTime hrInicio = java.time.LocalTime.parse(hiStr);

        System.out.println("Hora final (HH:mm):");
        String hfStr = sc.nextLine();
        if (hfStr.equals("0")) return;
        java.time.LocalTime hrFinal = java.time.LocalTime.parse(hfStr);

        // Verificación lógica de horas
        if (hrFinal.isBefore(hrInicio)) {
            System.out.println(">>> Error: La hora de fin no puede ser antes que la de inicio.");
            return;
        }

        Actividad newAct = null;
        switch (opcionTipo) {
            case 1:
                System.out.println("Anfitrion:");
                newAct = new Reunion("REUNION", id, titulo, fechaFinal, hrInicio, hrFinal, sc.nextLine());
                break;
            case 2:
                System.out.println("Ponderacion (0-100):");
                double pond = Double.parseDouble(sc.nextLine());
                System.out.println("Temario:");
                String tem = sc.nextLine();
                System.out.println("¿Es grupal? (true/false):");
                boolean gr = Boolean.parseBoolean(sc.nextLine());
                newAct = new Evaluacion("EVALUACION", id, titulo, fechaFinal, hrInicio, hrFinal, pond, tem, gr);
                break;
            case 3:
                System.out.println("Asignatura:");
                String asig = sc.nextLine();
                System.out.println("Profesor:");
                String prof = sc.nextLine();
                System.out.println("Sala:");
                String sala = sc.nextLine();
                newAct = new ClaseUniversitaria("CLASE", id, titulo, fechaFinal, hrInicio, hrFinal, asig, prof, sala);
                break;
            }

            if (newAct != null) {
                agenda.agregarActividad(newAct);
                System.out.println(">>> ¡Actividad guardada con éxito!");
            }

        } catch (NumberFormatException e) {
        System.out.println(">>> Error: Se esperaba un valor numérico.");
        } catch (java.time.format.DateTimeParseException e) {
        System.out.println(">>> Error: Formato de fecha u hora inválido.");
        } catch (Exception e) {
        System.out.println(">>> Error inesperado: " + e.getMessage());
        }
    }
    
    public void reporteFechas(){
        System.out.println("\n--- REPORTE DE FECHAS ---");
        
        java.util.List<LocalDate> fechas = agenda.getDiasHabilitados();
    
        if (fechas == null || fechas.isEmpty()) {
            System.out.println("Error: No hay fechas registradas en el sistema en este momento.");
            return;
        }
        
        System.out.println("Fechas habilitadas actualmente:");
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        for (int i = 0; i < fechas.size(); i++) {
            System.out.println((i + 1) + ". " + fechas.get(i).format(formato));
        }
        
        System.out.println("-------------------------");
    }
    
    public void reporteActividades() {
        System.out.println("\n--- REPORTE DE ACTIVIDADES ---");
        
        java.util.List<Actividad> catalogoCompleto = agenda.obtenerTodoElCatalogo();
        
        if (catalogoCompleto == null || catalogoCompleto.isEmpty()) {
            System.out.println(">>> No hay actividades registradas en la agenda.");
            return;
        }

        for (Actividad act : catalogoCompleto) {
            String tipoClase = act.getTipoClase();
            java.time.LocalTime horaInicio = act.getHoraInicio();
            java.time.LocalTime horaFin = act.getHoraFin();
            String titulo = act.getTitulo();
            String detalles = "";

            switch (tipoClase) {
                case "REUNION":
                    Reunion auxReunion = (Reunion) act;
                    detalles = "Anfitrión: " + auxReunion.getAnfitrion();
                    break;
                case "EVALUACION":
                    Evaluacion auxEvaluacion = (Evaluacion) act;
                    detalles = "Ponderación: " + auxEvaluacion.getPonderacion() + "% | Temas: " + auxEvaluacion.getTemario();
                    break;
                case "CLASE":
                    ClaseUniversitaria auxClase = (ClaseUniversitaria) act;
                    detalles = "Prof: " + auxClase.getProfesor() + " | Sala: " + auxClase.getSala();
                    break;
                default:
                    detalles = "Sin detalles adicionales";
                    break;
            }

            System.out.println(tipoClase.toUpperCase() + " | " + horaInicio + " - " + horaFin + " | " + titulo + " | " + detalles);
        }
        
        System.out.println("------------------------------");
    }
    
    public void eliminarFecha(){
        System.out.println("\n--- ELIMINAR FECHA DE LA AGENDA ---");
        
        java.util.List<LocalDate> fechasDisponibles = agenda.getDiasHabilitados();
        if (fechasDisponibles == null || fechasDisponibles.isEmpty()) {
            System.out.println(">>> No hay fechas registradas en el sistema para eliminar.");
            return;
        }

        System.out.println("(Ingrese '0' para cancelar)");
        System.out.println("Ingrese la fecha que desea eliminar (dd/mm/aaaa):");
        String input = sc.nextLine();

        if (input.equals("0")) {
            return;
        }

        try {
            java.time.format.DateTimeFormatter formato = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
            java.time.LocalDate fechaFinal = java.time.LocalDate.parse(input, formato);

            java.util.List<Actividad> actividadesBorradas = agenda.eliminarFecha(fechaFinal);

            if (actividadesBorradas != null) {
                System.out.println(">>> ¡Éxito! Fecha eliminada.");
                System.out.println(">>> Se borraron " + actividadesBorradas.size() + " actividades junto con la fecha.");
            } else {
                System.out.println(">>> Error: La fecha " + input + " no está habilitada en la agenda.");
            }

        } catch (java.time.format.DateTimeParseException e) {
            System.out.println(">>> Error: El formato es incorrecto. Recuerde usar dd/mm/aaaa (ej: 15/04/2026).");
        } catch (Exception e) {
            System.out.println(">>> Error inesperado: " + e.getMessage());
        }
    }
    
    public void eliminarActividad() {
        System.out.println("\n--- ELIMINAR ACTIVIDAD ---");
        System.out.println("Ingrese la fecha de la actividad (dd/mm/aaaa) o '0' para cancelar:");
        String fechaStr = sc.nextLine();
        
        if (fechaStr.equals("0")) {
            return;
        }

        try {
            java.time.format.DateTimeFormatter formato = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
            java.time.LocalDate fechaFinal = java.time.LocalDate.parse(fechaStr, formato);

            java.util.List<Actividad> listaActividades = agenda.buscarActividad(fechaFinal);

            if (listaActividades == null || listaActividades.isEmpty()) {
                System.out.println(">>> No hay actividades registradas para esta fecha.");
                return;
            }

            System.out.println("\nActividades encontradas:");
            for (Actividad act : listaActividades) {
                System.out.println("[" + act.getId() + "] " + act.getTipoClase() + " | " + act.getTitulo());
            }

            System.out.println("\nIngrese el ID de la actividad a eliminar:");
            String idParaEliminar = sc.nextLine();

            boolean idExiste = false;
            for (Actividad act : listaActividades) {
                if (act.getId().equals(idParaEliminar)) {
                    idExiste = true;
                    break;
                }
            }

            if (idExiste) {
                agenda.eliminarActividadPorId(fechaFinal, idParaEliminar);
                System.out.println(">>> ¡Actividad con ID: " + idParaEliminar + " eliminada con éxito!");
            } else {
                System.out.println(">>> Error: No se encontró ninguna actividad con el ID [" + idParaEliminar + "] en esta fecha.");
            }

        } catch (java.time.format.DateTimeParseException e) {
            System.out.println(">>> Error: El formato es incorrecto. Recuerde usar dd/mm/aaaa.");
        } catch (Exception e) {
            System.out.println(">>> Error inesperado: " + e.getMessage());
        }
    }
    
    public void editarFechas() {
        System.out.println("\n--- EDITAR FECHA ---");
        
        java.util.List<LocalDate> fechasDisponibles = agenda.getDiasHabilitados();
        if (fechasDisponibles == null || fechasDisponibles.isEmpty()) {
            System.out.println(">>> No hay fechas registradas en el sistema para editar.");
            return;
        }

        System.out.println("Ingrese la fecha que desea modificar (dd/mm/aaaa) o '0' para cancelar:");
        String fechaAntiguaStr = sc.nextLine();

        if (fechaAntiguaStr.equals("0")) {
            return;
        }

        try {
            java.time.format.DateTimeFormatter formato = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
            java.time.LocalDate fechaAntigua = java.time.LocalDate.parse(fechaAntiguaStr, formato);

            if (!fechasDisponibles.contains(fechaAntigua)) {
                System.out.println(">>> Error: La fecha ingresada no existe en la agenda.");
                return;
            }

            System.out.println("Ingrese la nueva fecha (dd/mm/aaaa):");
            String fechaNuevaStr = sc.nextLine();
            java.time.LocalDate fechaNueva = java.time.LocalDate.parse(fechaNuevaStr, formato);

            if (fechasDisponibles.contains(fechaNueva)) {
                System.out.println(">>> Error: La nueva fecha ya está registrada en la agenda.");
                return;
            }

            agenda.cambiarFechas(fechaAntigua, fechaNueva);
            System.out.println(">>> ¡La fecha se ha cambiado con éxito de " + fechaAntiguaStr + " a " + fechaNuevaStr + "!");

        } catch (java.time.format.DateTimeParseException e) {
            System.out.println(">>> Error: El formato es incorrecto. Recuerde usar dd/mm/aaaa.");
        } catch (Exception e) {
            System.out.println(">>> Error inesperado: " + e.getMessage());
        }
    }
    
    public void editarActividades(){
    }
    
    public void buscarFecha(){
    }
    
    public void buscarActividad(){
    }
    
    public void monitorIntensidad(){
    }

    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1: habilitarFecha(); break;
            case 2: agregarActividad(); break;
            case 3: reporteFechas(); break;
            case 4: reporteActividades(); break;
            case 5: eliminarFecha(); break;
            case 6: eliminarActividad(); break;
            case 7: editarFechas(); break; 
            case 8: editarActividades(); break; 
            case 9: buscarFecha(); break;
            case 10: buscarActividad(); break;
            case 11: monitorIntensidad(); break;
            case 0: System.out.println("Saliendo de la terminal"); break;
            default: System.out.println("Opción no válida.");
        }
    }
    
}
    
    
