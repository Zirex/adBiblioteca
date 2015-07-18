/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Log;
import Reportes.IReporte;
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
    private internalGestionCuentas iCuentas;
    private IReporte reporte;
    private Log suso;

    public Principal(Log suso) {
        initComponents();
        this.suso = suso;
        this.setLocationRelativeTo(null);
        this.reporte= new IReporte();
        
        btnUser.setText("Bienvenido "+this.suso.getNomBibliotecario());
        this.validarMenuUser(this.suso.getTipoUsuario());
    }
    
    private Principal() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.reporte= new IReporte();
        this.validarMenuUser(0);
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
    
    private void validarMenuUser(int tipo){
        if(tipo != 1){
            this.jmiGestionLibro.setVisible(false);
            this.btnReporte.setVisible(false);
            this.btnControlUser.setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpmLibro = new javax.swing.JPopupMenu();
        jmiGestionLibro = new javax.swing.JMenuItem();
        jmiMostrarLibros = new javax.swing.JMenuItem();
        jpmUsuario = new javax.swing.JPopupMenu();
        jmiGestionUsuario = new javax.swing.JMenuItem();
        jmiMostrarUsuarios = new javax.swing.JMenuItem();
        jpmReporte = new javax.swing.JPopupMenu();
        jmiReporteLibros = new javax.swing.JMenuItem();
        jmiReporteUsuarios = new javax.swing.JMenuItem();
        jmiReportePrestamos = new javax.swing.JMenuItem();
        jpmUser = new javax.swing.JPopupMenu();
        jmiConfiguracion = new javax.swing.JMenuItem();
        jmiSalir = new javax.swing.JMenuItem();
        Contenedor = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btnLibro = new javax.swing.JButton();
        btnUsuario = new javax.swing.JButton();
        btnLector = new javax.swing.JButton();
        btnPrestamo = new javax.swing.JButton();
        btnReporte = new javax.swing.JButton();
        btnResumen = new javax.swing.JButton();
        btnControlUser = new javax.swing.JButton();
        btnUser = new javax.swing.JButton();
        ContenedorDesktop = new javax.swing.JPanel();
        panel = new javax.swing.JDesktopPane();

        jmiGestionLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bookMenu.png"))); // NOI18N
        jmiGestionLibro.setText("Gestion de libros");
        jmiGestionLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiGestionLibroActionPerformed(evt);
            }
        });
        jpmLibro.add(jmiGestionLibro);

        jmiMostrarLibros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1432716129_magnifier.png"))); // NOI18N
        jmiMostrarLibros.setText("Consulta de libros");
        jmiMostrarLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiMostrarLibrosActionPerformed(evt);
            }
        });
        jpmLibro.add(jmiMostrarLibros);

        jmiGestionUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/usuarioNuevo.png"))); // NOI18N
        jmiGestionUsuario.setText("Registrar usuario");
        jmiGestionUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiGestionUsuarioActionPerformed(evt);
            }
        });
        jpmUsuario.add(jmiGestionUsuario);

        jmiMostrarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1432716129_magnifier.png"))); // NOI18N
        jmiMostrarUsuarios.setText("Tabla usuarios");
        jmiMostrarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiMostrarUsuariosActionPerformed(evt);
            }
        });
        jpmUsuario.add(jmiMostrarUsuarios);

        jmiReporteLibros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pdf.png"))); // NOI18N
        jmiReporteLibros.setText("Reporte libros");
        jmiReporteLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiReporteLibrosActionPerformed(evt);
            }
        });
        jpmReporte.add(jmiReporteLibros);

        jmiReporteUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pdf.png"))); // NOI18N
        jmiReporteUsuarios.setText("Reporte de usuarios");
        jmiReporteUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiReporteUsuariosActionPerformed(evt);
            }
        });
        jpmReporte.add(jmiReporteUsuarios);

        jmiReportePrestamos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pdf.png"))); // NOI18N
        jmiReportePrestamos.setText("Reporte prestamos");
        jmiReportePrestamos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiReportePrestamosActionPerformed(evt);
            }
        });
        jpmReporte.add(jmiReportePrestamos);

        jmiConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tool.png"))); // NOI18N
        jmiConfiguracion.setText("Configuración");
        jmiConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiConfiguracionActionPerformed(evt);
            }
        });
        jpmUser.add(jmiConfiguracion);

        jmiSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/outLog.png"))); // NOI18N
        jmiSalir.setText("Salir");
        jmiSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSalirActionPerformed(evt);
            }
        });
        jpmUser.add(jmiSalir);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADBiapp");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/library.png")).getImage()
        );
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        Contenedor.setLayout(new java.awt.BorderLayout());

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

        btnPrestamo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/prestamo1.png"))); // NOI18N
        btnPrestamo.setText("Prestamos");
        btnPrestamo.setBorderPainted(false);
        btnPrestamo.setContentAreaFilled(false);
        btnPrestamo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrestamo.setFocusable(false);
        btnPrestamo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPrestamo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrestamoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnPrestamo);

        btnReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reporte.png"))); // NOI18N
        btnReporte.setText("Reportes");
        btnReporte.setContentAreaFilled(false);
        btnReporte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReporte.setFocusable(false);
        btnReporte.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReporte.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReporteMouseClicked(evt);
            }
        });
        jToolBar1.add(btnReporte);

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

        btnControlUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/account.png"))); // NOI18N
        btnControlUser.setText("Cuentas");
        btnControlUser.setBorderPainted(false);
        btnControlUser.setContentAreaFilled(false);
        btnControlUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnControlUser.setFocusable(false);
        btnControlUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnControlUser.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnControlUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnControlUserActionPerformed(evt);
            }
        });
        jToolBar1.add(btnControlUser);

        btnUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/idUser.png"))); // NOI18N
        btnUser.setContentAreaFilled(false);
        btnUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUser.setFocusable(false);
        btnUser.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUserMouseClicked(evt);
            }
        });
        jToolBar1.add(javax.swing.Box.createHorizontalGlue());
        jToolBar1.add(btnUser);

        Contenedor.add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout ContenedorDesktopLayout = new javax.swing.GroupLayout(ContenedorDesktop);
        ContenedorDesktop.setLayout(ContenedorDesktopLayout);
        ContenedorDesktopLayout.setHorizontalGroup(
            ContenedorDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
        );
        ContenedorDesktopLayout.setVerticalGroup(
            ContenedorDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
        );

        Contenedor.add(ContenedorDesktop, java.awt.BorderLayout.CENTER);

        getContentPane().add(Contenedor);

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

    private void jmiGestionUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiGestionUsuarioActionPerformed
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
    }//GEN-LAST:event_jmiGestionUsuarioActionPerformed

    private void jmiMostrarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiMostrarUsuariosActionPerformed
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
    }//GEN-LAST:event_jmiMostrarUsuariosActionPerformed

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

    private void jmiGestionLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiGestionLibroActionPerformed
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
    }//GEN-LAST:event_jmiGestionLibroActionPerformed

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

    private void btnPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrestamoActionPerformed
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
    }//GEN-LAST:event_btnPrestamoActionPerformed

    private void btnReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReporteMouseClicked
       if(evt.getButton()== 1)
           this.jpmReporte.show(this.btnReporte, evt.getX(), evt.getY());
    }//GEN-LAST:event_btnReporteMouseClicked

    private void jmiReporteLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReporteLibrosActionPerformed
        reporte.cargarReporteLibros();
    }//GEN-LAST:event_jmiReporteLibrosActionPerformed

    private void jmiReporteUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReporteUsuariosActionPerformed
        reporte.cargarReporteUsuarios();
    }//GEN-LAST:event_jmiReporteUsuariosActionPerformed

    private void jmiReportePrestamosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReportePrestamosActionPerformed
        reporte.cargarReportePrestamos();
    }//GEN-LAST:event_jmiReportePrestamosActionPerformed

    private void btnUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUserMouseClicked
        if(evt.getButton()==1){
            this.jpmUser.show(this.btnUser, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_btnUserMouseClicked

    private void jmiSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSalirActionPerformed
        this.dispose();
        new GUILogin().setVisible(true);
    }//GEN-LAST:event_jmiSalirActionPerformed

    private void jmiConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiConfiguracionActionPerformed
        new GuiConfigUser(this, true, this.suso.getUsuario()).setVisible(true);
    }//GEN-LAST:event_jmiConfiguracionActionPerformed

    private void btnControlUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnControlUserActionPerformed
        if(estaCerrado(iCuentas)){
            iCuentas= new internalGestionCuentas();
            panel.add(iCuentas);
            posicion(iCuentas);
            iCuentas.show();
        }
        else{
            iCuentas.moveToFront();
            try{
                iCuentas.setIcon(false);
            }catch(PropertyVetoException e){
                System.err.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_btnControlUserActionPerformed

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
    private javax.swing.JPanel Contenedor;
    private javax.swing.JPanel ContenedorDesktop;
    private javax.swing.JButton btnControlUser;
    private javax.swing.JButton btnLector;
    private javax.swing.JButton btnLibro;
    private javax.swing.JButton btnPrestamo;
    private javax.swing.JButton btnReporte;
    private javax.swing.JButton btnResumen;
    private javax.swing.JButton btnUser;
    private javax.swing.JButton btnUsuario;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem jmiConfiguracion;
    private javax.swing.JMenuItem jmiGestionLibro;
    private javax.swing.JMenuItem jmiGestionUsuario;
    private javax.swing.JMenuItem jmiMostrarLibros;
    private javax.swing.JMenuItem jmiMostrarUsuarios;
    private javax.swing.JMenuItem jmiReporteLibros;
    private javax.swing.JMenuItem jmiReportePrestamos;
    private javax.swing.JMenuItem jmiReporteUsuarios;
    private javax.swing.JMenuItem jmiSalir;
    private javax.swing.JPopupMenu jpmLibro;
    private javax.swing.JPopupMenu jpmReporte;
    private javax.swing.JPopupMenu jpmUser;
    private javax.swing.JPopupMenu jpmUsuario;
    public javax.swing.JDesktopPane panel;
    // End of variables declaration//GEN-END:variables
}
