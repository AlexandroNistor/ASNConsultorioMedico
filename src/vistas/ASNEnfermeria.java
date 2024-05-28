/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import bbdd.ASNConexion;
import java.awt.Insets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import modelo.ASNPaciente;
import utilidades.ASNEncriptado;
import utilidades.ASNUtilidades;

/**
 *
 * @author sebas
 */
public class ASNEnfermeria extends javax.swing.JFrame {

    public static String dniPaciente;
    public static String nombrePaciente;
    public static String apellidosPaciente;
    public static String emailPaciente;

    /**
     * Creates new form ASNMedico
     */
    public ASNEnfermeria() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        botonBuscarPaciente = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        botonNuevoInforme = new javax.swing.JButton();
        botonNuevaCita = new javax.swing.JButton();
        fieldNombre = new javax.swing.JTextField();
        fieldApellidos = new javax.swing.JTextField();
        fieldTelefono = new javax.swing.JTextField();
        fieldEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaConsultas = new javax.swing.JTable();
        botonActualizarTabla = new javax.swing.JButton();
        fieldDNI = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel1.setText("CONSULTA ENFERMER�A");

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\sebas\\Documents\\NetBeansProjects\\ASNConsultorio\\recursos\\logo_good.png")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DNI PACIENTE");

        botonBuscarPaciente.setBackground(new java.awt.Color(0, 0, 0));
        botonBuscarPaciente.setForeground(new java.awt.Color(255, 255, 255));
        botonBuscarPaciente.setText("BUSCAR PACIENTE");
        botonBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarPacienteActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PACIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Apellidos");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tel�fono");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Email");

        botonNuevoInforme.setText("Nuevo Informe");
        botonNuevoInforme.setEnabled(false);
        botonNuevoInforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoInformeActionPerformed(evt);
            }
        });

        botonNuevaCita.setText("Nueva Cita");
        botonNuevaCita.setEnabled(false);
        botonNuevaCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevaCitaActionPerformed(evt);
            }
        });

        fieldNombre.setEnabled(false);

        fieldApellidos.setEnabled(false);

        fieldTelefono.setEnabled(false);

        fieldEmail.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(fieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(botonNuevoInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117)
                        .addComponent(botonNuevaCita, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(fieldApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(fieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(65, 65, 65)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(fieldEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(fieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonNuevoInforme)
                    .addComponent(botonNuevaCita))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("HISTORIAL DE CONSULTAS M�DICAS");

        tablaConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FECHA", "M�XIMA", "M�NIMA", "GLUCOSA", "PESO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaConsultas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaConsultasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaConsultas);

        botonActualizarTabla.setText("Actualizar Tabla");
        botonActualizarTabla.setEnabled(false);
        botonActualizarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarTablaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(fieldDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(493, 493, 493)
                        .addComponent(botonActualizarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(botonBuscarPaciente)
                    .addComponent(fieldDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonActualizarTabla)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarPacienteActionPerformed
        // TODO add your handling code here:
        try {
            ASNComprobarDni();
            DefaultTableModel modelo = (DefaultTableModel) tablaConsultas.getModel();
            ASNConexion.ASNconectar();
            ASNConexion.ASNcargaTablaConsultasEnfermeria(modelo, ASNEncriptado.encriptar(fieldDNI.getText()));
            ASNConexion.ASNCerrarConexion();
        } catch (Exception ex) {
            Logger.getLogger(ASNMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonBuscarPacienteActionPerformed

    private void botonNuevoInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoInformeActionPerformed
        // TODO add your handling code here:
        ASNNuevaConsultaEnfermeria nce = new ASNNuevaConsultaEnfermeria(this, true);
        nce.setVisible(true);
    }//GEN-LAST:event_botonNuevoInformeActionPerformed

    private void botonNuevaCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevaCitaActionPerformed
        // TODO add your handling code here:
        dniPaciente = fieldDNI.getText();
        nombrePaciente = fieldNombre.getText();
        apellidosPaciente = fieldApellidos.getText();
        emailPaciente = fieldEmail.getText();
        ASNNuevaCitaEnfermeria nce = new ASNNuevaCitaEnfermeria(this, true);
        nce.setVisible(true);
    }//GEN-LAST:event_botonNuevaCitaActionPerformed

    private void botonActualizarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarTablaActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel modelo = (DefaultTableModel) tablaConsultas.getModel();
            modelo.setRowCount(0);
            ASNConexion.ASNconectar();
            ASNConexion.ASNcargaTablaConsultasEnfermeria(modelo, ASNEncriptado.encriptar(fieldDNI.getText()));
            ASNConexion.ASNCerrarConexion();
        } catch (Exception ex) {
            Logger.getLogger(ASNMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonActualizarTablaActionPerformed

    private void tablaConsultasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaConsultasMouseClicked
        // TODO add your handling code here:
        ASNDatosFila();
    }//GEN-LAST:event_tablaConsultasMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ASNEnfermeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ASNEnfermeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ASNEnfermeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ASNEnfermeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ASNEnfermeria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizarTabla;
    private javax.swing.JButton botonBuscarPaciente;
    private javax.swing.JButton botonNuevaCita;
    private javax.swing.JButton botonNuevoInforme;
    private javax.swing.JTextField fieldApellidos;
    private javax.swing.JTextField fieldDNI;
    private javax.swing.JTextField fieldEmail;
    private javax.swing.JTextField fieldNombre;
    private javax.swing.JTextField fieldTelefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaConsultas;
    // End of variables declaration//GEN-END:variables
public void ASNComprobarDni() throws Exception {
        {
            String valorDNI = fieldDNI.getText();
            String valorDNIEncriptado = ASNEncriptado.encriptar(valorDNI);

            if (ASNUtilidades.ASNFormatoDniCorrecto(fieldDNI) && ASNUtilidades.ASNLetraDniCorrecta(fieldDNI)) {
                ASNConexion.ASNconectar();
                if (ASNConexion.ASNcompruebaDni(valorDNIEncriptado)) {
                    ASNPaciente paciente = ASNConexion.ASNrecuperaDatosPaciente(valorDNIEncriptado);
                    fieldNombre.setText(ASNEncriptado.desencriptar(paciente.getNombre()));
                    fieldApellidos.setText(ASNEncriptado.desencriptar(paciente.getApellidos()));
                    fieldTelefono.setText(String.valueOf(paciente.getTelefono()));
                    fieldEmail.setText(paciente.getEmail());
                    ASNConexion.ASNCerrarConexion();
                    fieldDNI.setEnabled(false);
                    botonNuevoInforme.setEnabled(true);
                    botonNuevaCita.setEnabled(true);
                    botonActualizarTabla.setEnabled(true);
                    dniPaciente = fieldDNI.getText();
                } else {
                    // Si no esta registrado el DNI.
                    JOptionPane.showMessageDialog(this, "No hay pacientes con el DNI proporcionado.\n"
                            + "A continuaci�n se abrira la ventana para a�adirlo.");
                    ASNPacientes np = new ASNPacientes(this, true);
                    np.setVisible(true);

                }
            } else {
                // Alertas de lo que va mal
                ASNConexion.ASNconectar();
                if (!ASNUtilidades.ASNFormatoDniCorrecto(fieldDNI)) {
                    ASNUtilidades.ASNLanzaAlertaFormatoDni(null, fieldDNI);
                    ASNConexion.ASNCerrarConexion();
                } else {
                    ASNUtilidades.ASNLanzaAlertaLetraDni(null, fieldDNI);
                    ASNConexion.ASNCerrarConexion();
                }
            }
        }
    }

    public void ASNDatosFila() {

        String contenido = "  FECHA DE CONSULTA:  " + String.valueOf(tablaConsultas.getValueAt(tablaConsultas.getSelectedRow(), 0));
        contenido += "\n\n  TENSI�N_M�XIMA:  \n " + String.valueOf(tablaConsultas.getValueAt(tablaConsultas.getSelectedRow(), 1));
        contenido += "\n\n  TENSI�N_M�NIMA:  \n " + String.valueOf(tablaConsultas.getValueAt(tablaConsultas.getSelectedRow(), 2));
        contenido += "\n\n  GLUCOSA:  \n " + String.valueOf(tablaConsultas.getValueAt(tablaConsultas.getSelectedRow(), 3));
        contenido += "\n\n  PESO:  \n " + String.valueOf(tablaConsultas.getValueAt(tablaConsultas.getSelectedRow(), 4));

        JTextArea t = new JTextArea(20, 60);
        t.setText(contenido);
        t.setEditable(false);
        t.setLineWrap(true);
        t.setFocusable(false);
        t.setAutoscrolls(true);
        t.setMargin(new Insets(10, 10, 10, 10));

        JOptionPane.showMessageDialog(this, new JScrollPane(t), "INFORME", 1);

    }

}
