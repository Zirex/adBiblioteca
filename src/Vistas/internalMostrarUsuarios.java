/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zirex
 */
public class internalMostrarUsuarios extends javax.swing.JInternalFrame {
    private Usuario usuario;
    private Principal principal;
    private internalUsuario usuTabla;

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
        }
    }
    
    public internalMostrarUsuarios(Principal principal, internalUsuario usuTabla) {
        initComponents();
        this.principal= principal;
        this.usuTabla= usuTabla;
        this.usuario= new Usuario();
        this.cargarTabla();
    }
    
    private void cargarTabla(){
        String [] ColumnName = {"id Usuario", "Apellidos", "Nombres", "Direccion", "Telefonos"};
        String q= "SELECT id_usuario, apellido_usu, nombre_usu, direccion_usu, telf1_usuario, telf2_usuario"
                + " FROM usuario ORDER BY id_usuario DESC;";
        ArrayList<HashMap> usuarios= this.usuario.usuarios(q);
        Object [][] datos = new Object [usuarios.size()][ColumnName.length];
        int i = 0;
        for (HashMap usu : usuarios) {
            String linea [] = {usu.get("id_usuario").toString(), usu.get("apellido_usu").toString(),
                               usu.get("nombre_usu").toString(), usu.get("direccion_usu").toString(),
                               usu.get("telf1_usuario").toString()+" / "+ usu.get("telf2_usuario").toString()};
            for (int j = 0; j < datos[i].length; j++) {
                datos[i][j] = linea[j];
            }
            i++;
        }
        DefaultTableModel model= new DefaultTableModel(datos, ColumnName){
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();

        jmiActualizar.setText("Modificar usuario");
        jmiActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiActualizarActionPerformed(evt);
            }
        });
        jpmUsuario.add(jmiActualizar);

        setClosable(true);
        setIconifiable(true);
        setTitle("Tabla de Usuarios");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.X_AXIS));

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

        getContentPane().add(jScrollPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiActualizarActionPerformed
        // TODO add your handling code here:
        int fila =  this.tablaUsuarios.getSelectedRow();
        this.seleccionarUsuario(fila);
    }//GEN-LAST:event_jmiActualizarActionPerformed

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            int fila = this.tablaUsuarios.getSelectedRow();
            this.seleccionarUsuario(fila);
        }
    }//GEN-LAST:event_tablaUsuariosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem jmiActualizar;
    private javax.swing.JPopupMenu jpmUsuario;
    private javax.swing.JTable tablaUsuarios;
    // End of variables declaration//GEN-END:variables
}
