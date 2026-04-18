
package com.mycompany.gestion_de_agenda_reuniones;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

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
    
    public void editarActividades() {
        System.out.println("\n--- EDITAR ACTIVIDAD ---");
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

            System.out.println("\nIngrese el ID de la actividad a editar:");
            String idEditar = sc.nextLine();

            Actividad actividadAEditar = null;
            for (Actividad act : listaActividades) {
                if (act.getId().equals(idEditar)) {
                    actividadAEditar = act;
                    break;
                }
            }

            if (actividadAEditar == null) {
                System.out.println(">>> Error: No se encontró ninguna actividad con el ID [" + idEditar + "].");
                return;
            }

            System.out.println("Pulse ENTER sin escribir nada para mantener el valor actual.");
            
            System.out.println("Título actual (" + actividadAEditar.getTitulo() + "):");
            String titulo = sc.nextLine();
            if (!titulo.isEmpty()) actividadAEditar.setTitulo(titulo);

            System.out.println("Hora Inicio actual (" + actividadAEditar.getHoraInicio() + "):");
            String hInicio = sc.nextLine();
            if (!hInicio.isEmpty()) actividadAEditar.setHoraInicio(java.time.LocalTime.parse(hInicio));

            System.out.println("Hora Fin actual (" + actividadAEditar.getHoraFin() + "):");
            String hFin = sc.nextLine();
            if (!hFin.isEmpty()) actividadAEditar.setHoraFin(java.time.LocalTime.parse(hFin));

            switch (actividadAEditar.getTipoClase()) {
                case "REUNION":
                    Reunion r = (Reunion) actividadAEditar;
                    System.out.println("Anfitrión actual (" + r.getAnfitrion() + "):");
                    String anfitrion = sc.nextLine();
                    if (!anfitrion.isEmpty()) r.setAnfitrion(anfitrion);
                    break;

                case "CLASE":
                    ClaseUniversitaria c = (ClaseUniversitaria) actividadAEditar;
                    System.out.println("Profesor actual (" + c.getProfesor() + "):");
                    String prof = sc.nextLine();
                    if (!prof.isEmpty()) c.setProfesor(prof);
                    
                    System.out.println("Sala actual (" + c.getSala() + "):");
                    String sala = sc.nextLine();
                    if (!sala.isEmpty()) c.setSala(sala);
                    
                    System.out.println("Asignatura actual (" + c.getAsignatura() + "):");
                    String asig = sc.nextLine();
                    if (!asig.isEmpty()) c.setAsignatura(asig);
                    break;

                case "EVALUACION":
                    Evaluacion e = (Evaluacion) actividadAEditar;
                    System.out.println("Temario actual (" + e.getTemario() + "):");
                    String temario = sc.nextLine();
                    if (!temario.isEmpty()) e.setTemario(temario);
                    
                    System.out.println("Ponderación actual (" + e.getPonderacion() + "):");
                    String pond = sc.nextLine();
                    if (!pond.isEmpty()) e.setPNota(Double.parseDouble(pond));
                    
                    System.out.println("¿Es grupal? true/false: ");
                    String grupal = sc.nextLine();
                    if (!grupal.isEmpty()) e.setEsGrupal(Boolean.parseBoolean(grupal));
                    break;
            }

            System.out.println(">>> ¡Actividad actualizada con éxito en la Agenda!");

        } catch (java.time.format.DateTimeParseException e) {
            System.out.println(">>> Error: Por favor, revisa el formato de las fechas u horas (HH:mm).");
        } catch (NumberFormatException e) {
            System.out.println(">>> Error: Por favor, ingresa solo números válidos en la Ponderación.");
        } catch (Exception e) {
            System.out.println(">>> Error inesperado: " + e.getMessage());
        }
    }
    
public void buscarFecha() {
        System.out.println("\n--- BUSCAR FECHA ---");
        System.out.println("Ingrese la fecha a buscar (dd/mm/aaaa) o '0' para cancelar:");
        String input = sc.nextLine();
        
        if (input.equals("0")) return;

        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaBuscada = LocalDate.parse(input, formato);

            if (agenda.getDiasHabilitados().contains(fechaBuscada)) {
                System.out.println(">>> ¡Éxito! La fecha " + input + " SÍ está habilitada en la Agenda.");
                
                // Verificamos si tiene actividades ese día
                java.util.List<Actividad> lista = agenda.buscarActividad(fechaBuscada);
                if (lista != null && !lista.isEmpty()) {
                    System.out.println(">>> Tiene " + lista.size() + " actividad(es) programada(s) para este día.");
                } else {
                    System.out.println(">>> Actualmente tiene el día libre (0 actividades).");
                }
            } else {
                System.out.println(">>> La fecha " + input + " NO está habilitada en la Agenda.");
            }
        } catch (DateTimeParseException e) {
            System.out.println(">>> Error: Formato incorrecto. Recuerde usar dd/mm/aaaa.");
        }
    }
    
    public void buscarActividad() {
        System.out.println("\n--- BUSCAR ACTIVIDAD ---");
        System.out.println("Ingrese la fecha de la actividad (dd/mm/aaaa) o '0' para cancelar:");
        String input = sc.nextLine();
        
        if (input.equals("0")) return;

        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(input, formato);

            java.util.List<Actividad> lista = agenda.buscarActividad(fecha);

            if (lista == null || lista.isEmpty()) {
                System.out.println(">>> No se encontraron actividades programadas para la fecha " + input + ".");
                return;
            }

            // Mostramos el resumen de actividades del día
            System.out.println("\n--- Actividades del " + input + " ---");
            for (Actividad act : lista) {
                System.out.println("ID: [" + act.getId() + "] | " + act.getHoraInicio() + " a " + act.getHoraFin() + " | " + act.getTitulo());
            }

            // Damos la opción de ver el detalle completo
            System.out.println("\n¿Desea ver los detalles de alguna? Ingrese el ID (o '0' para salir):");
            String idBusqueda = sc.nextLine();
            if (idBusqueda.equals("0")) return;

            Actividad encontrada = null;
            for (Actividad act : lista) {
                if (act.getId().equals(idBusqueda)) {
                    encontrada = act;
                    break;
                }
            }

            if (encontrada != null) {
                System.out.println("\n======== DETALLES DE LA ACTIVIDAD ========");
                System.out.println("Título: " + encontrada.getTitulo());
                System.out.println("Tipo: " + encontrada.getTipoClase());
                System.out.println("Horario: " + encontrada.getHoraInicio() + " - " + encontrada.getHoraFin());

                // Mostramos atributos específicos según el tipo de clase (Polimorfismo)
                switch (encontrada.getTipoClase()) {
                    case "REUNION":
                        Reunion r = (Reunion) encontrada;
                        System.out.println("Anfitrión: " + r.getAnfitrion());
                        break;
                    case "CLASE":
                        ClaseUniversitaria c = (ClaseUniversitaria) encontrada;
                        System.out.println("Asignatura: " + c.getAsignatura());
                        System.out.println("Profesor: " + c.getProfesor());
                        System.out.println("Sala: " + c.getSala());
                        break;
                    case "EVALUACION":
                        Evaluacion e = (Evaluacion) encontrada;
                        System.out.println("Temario: " + e.getTemario());
                        System.out.println("Ponderación: " + e.getPonderacion() + "%");
                        System.out.println("Formato: " + (e.getEsGrupal() ? "Grupal" : "Individual"));
                        break;
                }
                System.out.println("==========================================");
            } else {
                System.out.println(">>> Error: No se encontró ninguna actividad con ese ID.");
            }

        } catch (DateTimeParseException e) {
            System.out.println(">>> Error: Formato incorrecto. Recuerde usar dd/mm/aaaa.");
        }
    }
    
    public void monitorIntensidad() {
        System.out.println("\n--- MONITOR DE INTENSIDAD SEMANAL ---");
        System.out.println("El monitor diagnosticará la carga entre dos fechas.");

        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            System.out.println("Ingrese la fecha de INICIO (dd/mm/aaaa) o '0' para cancelar:");
            String fechaIniStr = sc.nextLine();
            if (fechaIniStr.equals("0")) return;
            LocalDate fechaInicio = LocalDate.parse(fechaIniStr, formato);

            System.out.println("Ingrese la fecha de FIN (dd/mm/aaaa):");
            String fechaFinStr = sc.nextLine();
            LocalDate fechaFin = LocalDate.parse(fechaFinStr, formato);

            if (fechaFin.isBefore(fechaInicio)) {
                System.out.println(">>> Error: La fecha de fin no puede ser anterior a la fecha de inicio.");
                return;
            }
            
            double lvlStress = 0.0;
            System.out.println("\nAnalizando periodo desde " + fechaIniStr + " hasta " + fechaFinStr + "...\n");

            List<Actividad> listCritico = agenda.obtenerActCriticas(fechaInicio , fechaFin);
            int totalActividades = listCritico.size();

            // Iteramos sobre todos los días habilitados para buscar los que caen en el rango
            for (Actividad act : listCritico) {
                if(act instanceof Evaluacion){
                    lvlStress += 1;
                }
                else{
                    lvlStress += 0.5;
                }
 
            }
            
            

            System.out.println("========== DIAGNÓSTICO ==========");
            System.out.println("Total de actividades en el periodo: " + totalActividades);

            // Sistema de alertas basado en la cantidad de horas
            if (lvlStress >= 5) {
                System.out.println("Estado: ¡ALERTA ROJA!  Carga académica demasiado alta. Considere reorganizar su tiempo.");
            } else if (lvlStress >= 3) {
                System.out.println("Estado: Carga media . Nivel manejable, pero no se descuide.");
            } else {
                System.out.println("Estado: Carga baja . Nivel bajo, descanse.");
            }
            System.out.println("=================================");

        } catch (DateTimeParseException e) {
            System.out.println(">>> Error: Formato de fecha incorrecto. Recuerde usar dd/mm/aaaa.");
        } catch (Exception e) {
            System.out.println(">>> Error inesperado del Monitor: " + e.getMessage());
        }
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
    
    
