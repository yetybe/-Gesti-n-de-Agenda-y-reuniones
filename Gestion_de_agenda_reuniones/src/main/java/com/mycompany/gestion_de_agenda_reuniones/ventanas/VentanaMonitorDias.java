/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.gestion_de_agenda_reuniones.ventanas;

import com.mycompany.gestion_de_agenda_reuniones.Actividad;
import com.mycompany.gestion_de_agenda_reuniones.Agenda;
import com.mycompany.gestion_de_agenda_reuniones.ClaseUniversitaria;
import com.mycompany.gestion_de_agenda_reuniones.Evaluacion;
import com.mycompany.gestion_de_agenda_reuniones.Reunion;
import java.awt.Color;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import utilidades.GestorUI;

/**
 *
 * @author tomas
 */
public class VentanaMonitorDias extends javax.swing.JFrame {

    /**
     * Creates new form VentanaMonitorSemanal
     */
    private Agenda actividades;
    public VentanaMonitorDias(Agenda a) {
          initComponents();
          actividades = a;
          GestorUI.cargarFechasComboBox(cbxFechasInicio, actividades);
          LocalDate fechaDefault = GestorUI.obtenerFechaDeComboBox(cbxFechasInicio);
          if (fechaDefault != null) {
            recargarCbxFinal(fechaDefault);
        }
         
         lblDiagnostico.setVisible(false);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        cbxFechasInicio = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        btnAnalizar = new javax.swing.JButton();
        lblDiagnostico = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblActividadesCriticas = new javax.swing.JTable();
        cbxFechasFinal = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitulo.setText("Monitor Carga Dias");

        cbxFechasInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFechasInicioActionPerformed(evt);
            }
        });

        jLabel2.setText("Fecha de inicio : ");

        btnAnalizar.setText("Analizar");
        btnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarActionPerformed(evt);
            }
        });

        lblDiagnostico.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDiagnostico.setText("DIAGNOSTICO");

        tblActividadesCriticas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Tipo Clase", "Horia Final", "Hora Inicio", "Titulo", "Detalles"
            }
        ));
        jScrollPane1.setViewportView(tblActividadesCriticas);
        if (tblActividadesCriticas.getColumnModel().getColumnCount() > 0) {
            tblActividadesCriticas.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblActividadesCriticas.getColumnModel().getColumn(1).setMaxWidth(120);
            tblActividadesCriticas.getColumnModel().getColumn(2).setResizable(false);
            tblActividadesCriticas.getColumnModel().getColumn(2).setPreferredWidth(70);
            tblActividadesCriticas.getColumnModel().getColumn(3).setResizable(false);
            tblActividadesCriticas.getColumnModel().getColumn(3).setPreferredWidth(70);
            tblActividadesCriticas.getColumnModel().getColumn(4).setPreferredWidth(200);
            tblActividadesCriticas.getColumnModel().getColumn(4).setMaxWidth(200);
            tblActividadesCriticas.getColumnModel().getColumn(5).setPreferredWidth(300);
            tblActividadesCriticas.getColumnModel().getColumn(5).setMaxWidth(300);
        }

        jLabel3.setText("Fecha de final : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(lblDiagnostico))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(318, 318, 318)
                        .addComponent(lblTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxFechasFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxFechasInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(364, 364, 364)
                        .addComponent(btnAnalizar)))
                .addContainerGap(289, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblTitulo)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxFechasInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxFechasFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(btnAnalizar)
                .addGap(16, 16, 16)
                .addComponent(lblDiagnostico)
                .addContainerGap(152, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(221, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //Funcion que permite analizar el nivel de estres que estara sometido el usuario durante un periodo de tiempo 
    //Apartir de una fecha escogida especificamente
    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
         //Obtenemos una Lista con los eventos criticos entre un periodoTiempo 
          LocalDate fechaInicio = GestorUI.obtenerFechaDeComboBox(cbxFechasInicio);
          LocalDate fechaFinal = GestorUI.obtenerFechaDeComboBox(cbxFechasFinal);
          List<Actividad> listSemana = actividades.obtenerActCriticas(fechaInicio , fechaFinal);
          
          /*
            Obtenemos la cantidad de dias totales , y 
            evaluaremos el nivel de estrees en base a los eventos criticos del periodo de tiempo
          */
          long diasPeriodo = ChronoUnit.DAYS.between(fechaInicio, fechaFinal) + 1;
          double lvlEstress = 0.0;
       
        for(Actividad act :listSemana){
          
            if(act instanceof Evaluacion){
                    lvlEstress += 1;
            }   else{
                    lvlEstress += 0.5;
            }
          }
        
        //Realizamos un diagnostico en base al lvlEstress
        if(lvlEstress >= 5){
          lblDiagnostico.setText("ALERTA ROJA: Carga altísima. En un periodo de " + diasPeriodo + " días.");
          lblDiagnostico.setForeground(Color.RED);
          lblDiagnostico.setVisible(true);
        }
        else if(lvlEstress > 2){
          lblDiagnostico.setText("ALERTA AMARILLA: Carga moderada. En estos " + diasPeriodo + " días.");
          lblDiagnostico.setForeground(new Color(204, 153, 0)); // Un amarillo oscuro legible
          lblDiagnostico.setVisible(true);
        } else {
          lblDiagnostico.setText("ALERTA VERDE: Periodo manejable. En los próximos " + diasPeriodo + " días.");
          lblDiagnostico.setForeground(new java.awt.Color(0, 153, 0)); // Verde oscuro
          lblDiagnostico.setVisible(true);
        }
        
        cargarTablaSeamanCritica(listSemana);
    }//GEN-LAST:event_btnAnalizarActionPerformed

    private void cbxFechasInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFechasInicioActionPerformed
        // TODO add your handling code here:
          LocalDate fechaSelec = GestorUI.obtenerFechaDeComboBox(cbxFechasInicio);
          if (fechaSelec != null) {
            recargarCbxFinal(fechaSelec);
        }
    }//GEN-LAST:event_cbxFechasInicioActionPerformed

    private void cargarTablaSeamanCritica(List<Actividad>listAct){

          DefaultTableModel modelo = (DefaultTableModel) tblActividadesCriticas.getModel();
          modelo.setRowCount(0);
 
          // 4. Recorremos la lista y creamos las filas
          for (Actividad act : listAct) {
            LocalDate fechaAct =act.getFecha();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaTable =fechaAct.format(formato);
            String tipoClase = act.getTipoClase();
            LocalTime horaInicio = act.getHoraInicio();
            LocalTime horaFin = act.getHoraFin();
            String titulo = act.getTitulo();
            String detalles = null;
            switch(tipoClase){
                case"REUNION":
                    Reunion auxReunion = (Reunion)act;
                    detalles = "Anfitrion: " + auxReunion.getAnfitrion();
                    modelo.addRow(new Object[]{ fechaTable ,tipoClase.toUpperCase(),horaInicio , horaFin, titulo , detalles });
                    break;
                case"EVALUACION":
                    Evaluacion auxEvaluacion = (Evaluacion)act;
                    detalles ="Ponderación: " + auxEvaluacion.getPonderacion() + "% | Temas: " + auxEvaluacion.getTemario();
                    modelo.addRow(new Object[]{ fechaTable ,tipoClase.toUpperCase(),horaInicio , horaFin, titulo , detalles });
                    break;

                    
            }

        }
    }
    
    // Esta funcion elimina la fecha seleccionada de una cbx (cbxFechaInicio) en la otra cbx (cbxFechaFinal)
    private void EliminarCbxFinal(String fecha , JComboBox cbxFinal){
        if (cbxFinal != null) {
            cbxFinal.removeItem(fecha);
        }
    }
    
    /*Esta funcion permite que al comenzar la ventana , desde un inicio el usuario      
        pueda ver fechas distintas tanto en la cbxInicio y cbxFinal
    */
    private void recargarCbxFinal(LocalDate fechaInicio) {
            // 1. Destruimos: Vaciamos completamente las opciones del ComboBox final
            cbxFechasFinal.removeAllItems();

            java.time.format.DateTimeFormatter formato = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // 2. Reconstruimos: Recorremos todas las fechas disponibles en tu agenda
            for (java.time.LocalDate fecha : actividades.getDiasHabilitados()) {

                // 3. El Filtro Arquitectónico: ¿Esta fecha es estrictamente DESPUÉS de la fecha de inicio?
                if (fecha.isAfter(fechaInicio)) {
                    // Solo si pasa la prueba, la volvemos texto y la añadimos a las opciones
                    cbxFechasFinal .addItem(fecha.format(formato));
                }
            }
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JComboBox<String> cbxFechasFinal;
    private javax.swing.JComboBox<String> cbxFechasInicio;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDiagnostico;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblActividadesCriticas;
    // End of variables declaration//GEN-END:variables
}
