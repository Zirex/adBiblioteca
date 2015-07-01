/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Usuario;
import Reportes.IReporte;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zirex
 */
public class internalMostrarUsuarios extends javax.swing.JInternalFrame {
    private Usuario usuario;
    private Principal principal;
    private internalUsuario usuTabla;
    private ArrayList<HashMap> listaUsuarios;
    private IReporte reporte;

    /**
     * Creates new form internalMostrarUsuarios
     */
    
    private void seleccionarUsuario(int fila){
        this.usuario = this.usuario.getUsuario(this.tablaUsuarios.getValueAt(fila, 0).toString());        
        if(this.principal.estaCerrado(this.usuTabla)){
            this.usuTabla= new internalUsuario();
            this.usuTabla.cargarUsuario(this.usuario);
            this.principal.panel.add(usuTabla);
            this.usuTabla.show();
        }
        else{
            this.usuTabla.cargarUsuario(this.usuario);
            this.usuTabla.moveToFront();
            try {
                this.usuTabla.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(internalMostrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public internalMostrarUsuarios(Principal principal, internalUsuario usuTabla) {
        initComponents();
        this.principal= principal;
        this.usuTabla= usuTabla;
        this.usuario= new Usuario();
        this.reporte= new IReporte();
        this.cargarTabla();
    }
    
    private void cargarTabla(){
        String [] ColumnName = {"id Usuario", "Apellidos", "Nombres", "Direccion", "Telefonos"};
        String q= "SELECT id_usuario, apellido_usu, nombre_usu, direccion_usu, telf1_usuario, telf2_usuario, miembro"
                + " FROM usuario ORDER BY id_usuario DESC;";
        this.listaUsuarios= this.usuario.usuarios(q);
        Object [][] datos = new Object [listaUsuarios.size()][ColumnName.length];
        int i = 0;
        for (HashMap usu : listaUsuarios) {
            String linea [] = {usu.get("id_usuario").toString(), usu.get("apellido_usu").toString(),
                               usu.get("nombre_usu").toString(), usu.get("direccion_usu").toString(),
                               usu.get("telf1_usuario").toString()+" / "+ usu.get("telf2_usuario").toString()};
            for (int j = 0; j < datos[i].length; j++) {
                datos[i][j] = linea[j];
            }
            i++;
        }
        DefaultTableModel model= new DefaultTableModel(datos, ColumnName){
            @Override
            public boolean isCellEditable(int i, int il){
                return false;
            }
        };
        this.tablaUsuarios.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpmUsuario = new javax.swing.JPopupMenu();
        jmiActualizar = new javax.swing.JMenuItem();
        jmiPrint = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        jmiActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar20px.png"))); // NOI18N
        jmiActualizar.setText("Modificar usuario");
        jmiActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiActualizarActionPerformed(evt);
            }
        });
        jpmUsuario.add(jmiActualizar);

        jmiPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/print.png"))); // NOI18N
        jmiPrint.setText("Imprimir lector");
        jmiPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPrintActionPerformed(evt);
            }
        });
        jpmUsuario.add(jmiPrint);

        setClosable(true);
        setIconifiable(true);
        setTitle("Tabla de Usuarios");

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaUsuarios.setComponentPopupMenu(jpmUsuario);
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUsuarios);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hacer doble clic ó clic derecho sobre la fila del usuario para visualizarlo detalladamente");
        getContentPane().add(jLabel1, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiActualizarActionPerformed
        // TODO add your handling code here:
        int fila =  this.tablaUsuarios.getSelectedRow();
        if(fila != -1)
            this.seleccionarUsuario(fila);
        else
            JOptionPane.showMessageDialog(this, "No ha seleccionado ninguna usuario", "adBiblioteca", JOptionPane.ERROR_MESSAGE);
        
    }//GEN-LAST:event_jmiActualizarActionPerformed

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            int fila = this.tablaUsuarios.getSelectedRow();
            this.seleccionarUsuario(fila);
        }
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    private void jmiPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPrintActionPerformed
        // TODO add your handling code here:
        int fila= this.tablaUsuarios.getSelectedRow();
        if(fila != -1){
            HashMap map= this.listaUsuarios.get(fila);
            if(map.get("miembro").equals("1")){            
                int idUsuario= Integer.parseInt(map.get("id_usuario").toString());
                this.reporte.cargarDetalleUsuario(idUsuario);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "No ha seleccionado ninguna usuario", "adBiblioteca", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jmiPrintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem jmiActualizar;
    private javax.swing.JMenuItem jmiPrint;
    private javax.swing.JPopupMenu jpmUsuario;
    private javax.swing.JTable tablaUsuarios;
    // End of variables declaration//GEN-END:variables
}
