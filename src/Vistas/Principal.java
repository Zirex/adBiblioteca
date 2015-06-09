/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;

/**
 *
 * @author zirex
 */
public class Principal extends javax.swing.JFrame {
    private internalLibro iLibro;
    private internalBuscarLibro iBLibro;
    private internalResumen iResumen;
    private internalUsuario iUsuario;
    private internalMostrarUsuarios iMUsuario;
    private internalLector iLector;
    private internalPrestamo iPrestamo;

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    public boolean estaCerrado(Object obj){
        JInternalFrame[] activos= panel.getAllFrames();
    boolean cerrado=true;
    int i=0;
    while (i<activos.length && cerrado){
	if(activos[i]==obj){
		cerrado=false;
	}
	i++;
    }
    return cerrado;
    }
    
    private void posicion(JInternalFrame frame){
       Dimension d= frame.getDesktopPane().getSize();
               frame.setLocation((d.width - frame.getSize().width)/2,
                                (d.height - frame.getSize().height)/2);
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
        jmGestionUsuario = new javax.swing.JMenuItem();
        jmMostrarUsuarios = new javax.swing.JMenuItem();
        jpmLibro = new javax.swing.JPopupMenu();
        jpmGestionLibro = new javax.swing.JMenuItem();
        jmiMostrarLibros = new javax.swing.JMenuItem();
        jToolBar1 = new javax.swing.JToolBar();
        btnLibro = new javax.swing.JButton();
        btnUsuario = new javax.swing.JButton();
        btnLector = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnResumen = new javax.swing.JButton();
        panel = new javax.swing.JDesktopPane();

        jmGestionUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/usuarioNuevo.png"))); // NOI18N
        jmGestionUsuario.setText("Registrar usuario");
        jmGestionUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmGestionUsuarioActionPerformed(evt);
            }
        });
        jpmUsuario.add(jmGestionUsuario);

        jmMostrarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1432716129_magnifier.png"))); // NOI18N
        jmMostrarUsuarios.setText("Tabla usuarios");
        jmMostrarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmMostrarUsuariosActionPerformed(evt);
            }
        });
        jpmUsuario.add(jmMostrarUsuarios);

        jpmGestionLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bookMenu.png"))); // NOI18N
        jpmGestionLibro.setText("Gestion de libros");
        jpmGestionLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpmGestionLibroActionPerformed(evt);
            }
        });
        jpmLibro.add(jpmGestionLibro);

        jmiMostrarLibros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1432716129_magnifier.png"))); // NOI18N
        jmiMostrarLibros.setText("Consulta de libros");
        jmiMostrarLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiMostrarLibrosActionPerformed(evt);
            }
        });
        jpmLibro.add(jmiMostrarLibros);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADBiapp");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/library.png")).getImage()
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        btnLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/book.png"))); // NOI18N
        btnLibro.setText("Libros");
        btnLibro.setContentAreaFilled(false);
        btnLibro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLibro.setFocusable(false);
        btnLibro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLibro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLibro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLibroMouseClicked(evt);
            }
        });
        jToolBar1.add(btnLibro);

        btnUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/usuario.png"))); // NOI18N
        btnUsuario.setText("Usuarios");
        btnUsuario.setBorderPainted(false);
        btnUsuario.setContentAreaFilled(false);
        btnUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuario.setFocusable(false);
        btnUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUsuario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUsuarioMouseClicked(evt);
            }
        });
        jToolBar1.add(btnUsuario);

        btnLector.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lector.png"))); // NOI18N
        btnLector.setText("Lectores");
        btnLector.setContentAreaFilled(false);
        btnLector.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLector.setFocusable(false);
        btnLector.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLector.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLectorActionPerformed(evt);
            }
        });
        jToolBar1.add(btnLector);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/prestamo1.png"))); // NOI18N
        jButton1.setText("Prestamos");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        btnResumen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/grafica.png"))); // NOI18N
        btnResumen.setText("Resumen");
        btnResumen.setBorderPainted(false);
        btnResumen.setContentAreaFilled(false);
        btnResumen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnResumen.setFocusable(false);
        btnResumen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnResumen.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnResumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResumenActionPerformed(evt);
            }
        });
        jToolBar1.add(btnResumen);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnResumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResumenActionPerformed
        // TODO add your handling code here:
        if(this.estaCerrado(iResumen)){
            iResumen = new internalResumen();
            panel.add(iResumen);
            iResumen.setSize(iResumen.getDesktopPane().getSize());
            iResumen.show();
        }
        else{
            iResumen.moveToFront();
            try {
                iResumen.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnResumenActionPerformed

    private void btnUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUsuarioMouseClicked
        // TODO add your handling code here:
        if(evt.getButton()==1){
            this.jpmUsuario.show(this.btnUsuario, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_btnUsuarioMouseClicked

    private void jmGestionUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmGestionUsuarioActionPerformed
        // TODO add your handling code here:
        if(this.estaCerrado(iUsuario)){
            iUsuario= new internalUsuario();
            panel.add(iUsuario);
            this.posicion(iUsuario);
            iUsuario.show();
        }
        else{
            iUsuario.moveToFront();
            try {
                iUsuario.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jmGestionUsuarioActionPerformed

    private void jmMostrarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmMostrarUsuariosActionPerformed
        // TODO add your handling code here:
        if(this.estaCerrado(iMUsuario)){
            iMUsuario = new internalMostrarUsuarios(this, this.iUsuario);
            panel.add(iMUsuario);
            iMUsuario.setSize(iMUsuario.getDesktopPane().getSize());
            iMUsuario.show();
        }
        else{
            iMUsuario.moveToFront();
            try {
                iMUsuario.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jmMostrarUsuariosActionPerformed

    private void btnLectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLectorActionPerformed
        // TODO add your handling code here:
        if(this.estaCerrado(iLector)){
            iLector = new internalLector(this, iUsuario);
            panel.add(iLector);
            this.posicion(iLector);
            iLector.show();
        }
        else{
            iLector.moveToFront();
            try {
                iLector.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnLectorActionPerformed

    private void btnLibroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLibroMouseClicked
        // TODO add your handling code here:
        if(evt.getButton()==1){
            this.jpmLibro.show(this.btnLibro, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_btnLibroMouseClicked

    private void jpmGestionLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpmGestionLibroActionPerformed
        // TODO add your handling code here:
        if(this.estaCerrado(iLibro)){
            iLibro= new internalLibro();
            panel.add(iLibro);
            this.posicion(iLibro);
            iLibro.show();            
        }
        else{
            iLibro.moveToFront();
            try {
                iLibro.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }//GEN-LAST:event_jpmGestionLibroActionPerformed

    private void jmiMostrarLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiMostrarLibrosActionPerformed
        // TODO add your handling code here:
        if(this.estaCerrado(iBLibro)){
            iBLibro= new internalBuscarLibro();
            panel.add(iBLibro);
            iBLibro.setSize(iBLibro.getDesktopPane().getSize());
            iBLibro.show();
        }
        else{
            iBLibro.moveToFront();
            try {
                iBLibro.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jmiMostrarLibrosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(this.estaCerrado(iPrestamo)){
            iPrestamo= new internalPrestamo(this, iUsuario);
            panel.add(iPrestamo);
            this.posicion(iPrestamo);
            iPrestamo.show();
        }
        else{
            iPrestamo.moveToFront();
            try{
                iPrestamo.setIcon(false);
            }catch(PropertyVetoException e){
                System.err.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLector;
    private javax.swing.JButton btnLibro;
    private javax.swing.JButton btnResumen;
    private javax.swing.JButton btnUsuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem jmGestionUsuario;
    private javax.swing.JMenuItem jmMostrarUsuarios;
    private javax.swing.JMenuItem jmiMostrarLibros;
    private javax.swing.JMenuItem jpmGestionLibro;
    private javax.swing.JPopupMenu jpmLibro;
    private javax.swing.JPopupMenu jpmUsuario;
    public javax.swing.JDesktopPane panel;
    // End of variables declaration//GEN-END:variables
}
