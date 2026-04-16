/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gestion_de_agenda_reuniones;

import java.util.Scanner;

public class Consola {
    private Agenda agenda;
    private Scanner sc;

    public Consola(Agenda agenda) {
        this.agenda = agenda;
        this.sc = new Scanner(System.in);
    }
/*
    public void iniciar() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n========= AGENDA DE ACTIVIDADES =========");
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
            System.out.print("\nSeleccione una funcionalidad: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                ejecutarOpcion(opcion);
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
            }
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
*/
    
    // Aquí iremos implementando cada método parte por parte...
}
