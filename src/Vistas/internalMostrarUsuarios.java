/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Usuario;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zirex
 */
public class internalMostrarUsuarios extends javax.swing.JInternalFrame {
    private Usuario usuario;

    /**
     * Creates new form internalMostrarUsuarios
     */
    public internalMostrarUsuarios() {
        initComponents();
        this.usuario= new Usuario();
        this.cargarTabla();
    }
    
    private void cargarTabla(){
        String [] ColumnName = {"id Usuario", "Apellidos", "Nombres", "Direccion", "Telefonos"};
        String q= "SELECT id_usuario, apellido_usu, nombre_usu, direccion_usu, telf1_usuario, telf2_usuario"
                + " FROM usuario ORDER BY id_usuario DESC;";
        Object [][] datos = this.usuario.usuarios(q);
        DefaultTableModel model= new DefaultTableModel(datos, ColumnName);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();

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
        jScrollPane1.setViewportView(tablaUsuarios);

        getContentPane().add(jScrollPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaUsuarios;
    // End of variables declaration//GEN-END:variables
}
