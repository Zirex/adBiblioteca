package Vistas;

import Clases.Log;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author zirex
 */
public class GUILogin extends javax.swing.JFrame {

    /**
     * Creates new form GUILogin
     */
    public GUILogin() {
        setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    private boolean validarCampos(){
        boolean ok= false;
        if(txtUsuario.getText().trim().isEmpty()){
            txtUsuario.setBorder(javax.swing.BorderFactory.createEtchedBorder(Color.lightGray, Color.red));
            txtUsuario.setToolTipText("Por favor digite el usuario.");
            ok= true;
        }
        if(txtClave.getText().isEmpty()){
            txtClave.setBorder(javax.swing.BorderFactory.createEtchedBorder(Color.lightGray, Color.red));
            txtClave.setToolTipText("Por favor digite la clave de usuario");
            ok= true;
        }
        if(cmbTipo.getSelectedIndex()==0){
            cmbTipo.setBackground(Color.red);
            cmbTipo.setToolTipText("Por favor seleccione un tipo de usuario");
            ok= true;
        }
        return ok;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtClave = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox();
        btnSalir = new javax.swing.JButton();
        btnEntrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AdBiblioteca");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/library.png")).getImage()
        );
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Usuario:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        txtUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusLost(evt);
            }
        });
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 220, -1));

        jLabel3.setText("Contraseña:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));

        txtClave.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtClaveFocusLost(evt);
            }
        });
        getContentPane().add(txtClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 220, -1));

        jLabel4.setText("Tipo de usuario:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, -1, -1));

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Tipo de usuario---", "Administrador", "Usuario" }));
        cmbTipo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbTipoFocusLost(evt);
            }
        });
        getContentPane().add(cmbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 220, -1));

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrar.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setToolTipText(null);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, -1, -1));

        btnEntrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/login.png"))); // NOI18N
        btnEntrar.setText("Ingresar");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEntrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/libroAbierto.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        if(validarCampos()){
            JOptionPane.showMessageDialog(this, "Por favor certifique que todos los campos esten correctos para continuar", "AdBiblioteca", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String username= txtUsuario.getText().trim();
        int tipoRol= 0;
        if(cmbTipo.getSelectedItem().equals("Administrador"))
            tipoRol= 1;
        
        Log suso= Log.existe(username);
        if(suso != null){
            boolean ok= true;
            ok=suso.passCorrecta(txtClave.getText());
            if(suso.getTipoUsuario()!=tipoRol){
                ok= false;
            }
            if(ok){
                new Principal(suso).setVisible(true);
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(this, "Los datos suministrados no son los correctos... \nPor favor rectifique todos los campos", "adBiblioteca", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "El usuario no existe, Por favor comuniquese con el administrador",
                                         "adBiblioteca", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void txtUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusLost
        if(!txtUsuario.getText().trim().isEmpty()){
            txtUsuario.setBorder(new javax.swing.JTextField().getBorder());
            txtUsuario.setToolTipText(null);
        }
    }//GEN-LAST:event_txtUsuarioFocusLost

    private void cmbTipoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbTipoFocusLost
        if(cmbTipo.getSelectedIndex() != 0){
            cmbTipo.setBackground(javax.swing.UIManager.getColor("comboBox.Background"));
            cmbTipo.setToolTipText(null);
        }
    }//GEN-LAST:event_cmbTipoFocusLost

    private void txtClaveFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtClaveFocusLost
        if(!txtClave.getText().isEmpty()){
            txtClave.setBorder(new javax.swing.JTextField().getBorder());
            txtClave.setToolTipText(null);
        }
    }//GEN-LAST:event_txtClaveFocusLost

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
            java.util.logging.Logger.getLogger(GUILogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUILogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUILogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUILogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUILogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}