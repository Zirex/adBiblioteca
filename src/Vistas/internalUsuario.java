/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Usuario;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author zirex
 */
public class internalUsuario extends Interfaz {
    private Usuario usuario = null; 
    private SimpleDateFormat formateador= new SimpleDateFormat("yyyy-MM-dd", new Locale("es_ES"));
    private String cedula = null;
    private String nombreUsu= null;
    private String apellidoUsu= null;
    private String sexo = null;
    private Date fechaNac = null;
    private String tlf1 = null;
    private String tlf2 = null;
    private String direccion = null;
    private String estudia = "0";
    private String miembro = "0";
    private String nombreInstituto = null;
    private String representante = null;
    private String foto = null;
    private Date fechaMiembro = null;

    /**
     * Creates new form internalUsuario
     */
    public internalUsuario() {
        initComponents();
    }
    
    public void cargarUsuario(Usuario usuario){
        this.limpiarCampo();
        this.usuario = usuario;
        this.txtCedula.setText(this.usuario.getCedUsuario());
        this.txtNomUsuario.setText(this.usuario.getNombre());
        this.txtApUsuario.setText(this.usuario.getApellido());
        this.cmbSexo.setSelectedItem(this.usuario.getSexo());
        this.jdtFechaNacimiento.setDate(this.usuario.getFechaNacimiento());
        this.txtTlf1.setText(this.usuario.getTlf1());
        this.txtTlf2.setText(this.usuario.getTlf2());
        this.txtDireccion.setText(this.usuario.getDireccion());
        if(this.usuario.getEstudia().equals("1")){
            this.jcbEstudia.setSelected(true);
            this.txtInstitucion.setText(this.usuario.getNombreInstitucion());
        }
        this.txtRepresentante.setText(this.usuario.getNombreRepresentante());
        if(this.usuario.getMiembro().equals("1")){
            this.jcbMiembro.setSelected(true);
            this.fechaMiembro = this.usuario.getFechaExpedicion();
            this.txtFechaMiembro.setText(this.formateador.format(this.fechaMiembro));
        }
        this.jcfFoto.setFoto(new javax.swing.ImageIcon(this.usuario.getFoto()));
        
        this.btnGuardar.setText("Actualizar");
    }
    
    private boolean validarCampos(){
        boolean error = false;
        if(this.txtNomUsuario.getText().isEmpty()){
            showError(txtNomUsuario, "Por favor digite el nombre del usuario.");
            error= true; 
        }
        if(this.txtApUsuario.getText().isEmpty()){
            showError(txtApUsuario, "Por favor digite el apellido del usuario.");
            error= true;
        }
        if(this.cmbSexo.getSelectedItem().equals("Sexo")){
            showError(cmbSexo, "Por favor seleccione el sexo del usuario.");
            error = true;
        }
        if(this.jdtFechaNacimiento.getDate() == null){
            showError(jdtFechaNacimiento, "Por favor digite la fecha de nacimiento del usuario.");
            error = true;
        }
        if(this.jcbMiembro.isSelected()){
            if(this.txtTlf1.getText().isEmpty()){
                showError(txtTlf1, "Por favor digite un numero de telefono.");
                error = true;
            }
            if(this.txtTlf2.getText().isEmpty()){
                showError(txtTlf2, "Por favor digite un numero de telefono.");
                error = true;
            }
            if(this.txtDireccion.getText().isEmpty()){
                showError(txtDireccion, "Por favor digite la dirección del usuario.");
                error = true;
            }
        }
        return error;
    }
    
    private void limpiarCampo(){
        this.usuario = null;
        this.txtCedula.setText("");
        this.txtNomUsuario.setText("");
        this.txtApUsuario.setText("");
        this.cmbSexo.setSelectedItem("");
        this.jdtFechaNacimiento.setDate(null);
        this.txtTlf1.setText("");
        this.txtTlf2.setText("");
        this.txtDireccion.setText("");
        this.jcbEstudia.setSelected(false);
        this.txtRepresentante.setText("");
        this.jcbMiembro.setSelected(false);
        this.jcfFoto.getFotoDefault();
        
        normalizeInput(txtNomUsuario);
        normalizeInput(txtApUsuario);
        normalizeInput(cmbSexo);
        normalizeInput(jdtFechaNacimiento);
        normalizeInput(txtTlf1);
        normalizeInput(txtTlf2);
        normalizeInput(txtDireccion);
                
        this.btnGuardar.setText("Guardar");
    }
    
    private boolean updateUsuario(){
        boolean cambio= false;
        if(!this.usuario.getCedUsuario().equals(this.cedula)){
            this.usuario.setCedUsuario(this.cedula);
            cambio = true;
        }
        if(!this.usuario.getNombre().equals(this.nombreUsu)){
            this.usuario.setNombre(this.nombreUsu);
            cambio = true;
        }
        if(!this.usuario.getApellido().equals(this.apellidoUsu)){
            this.usuario.setApellido(this.apellidoUsu);
            cambio = true;
        }
        if(!this.usuario.getSexo().equals(this.sexo)){
            this.usuario.setSexo(this.sexo);
            cambio = true;
        }
        if(!this.usuario.getFechaNacimiento().equals(this.fechaNac)){
            this.usuario.setFechaNacimiento(this.fechaNac);
            cambio = true;
        }
        if(!this.usuario.getTlf1().equals(this.tlf1)){
            this.usuario.setTlf1(this.tlf1);
            cambio = true;
        }
        if(!this.usuario.getTlf2().equals(this.tlf2)){
            this.usuario.setTlf2(this.tlf2);
            cambio = true;
        }
        if(!this.usuario.getDireccion().equals(this.direccion)){
            this.usuario.setDireccion(this.direccion);
            cambio = true;
        }
        if(!this.usuario.getEstudia().equals(this.estudia)){
            this.usuario.setEstudia(this.estudia);
            cambio = true;
        }
        if(!this.usuario.getMiembro().equals(this.miembro)){
            this.usuario.setMiembro(this.miembro);
            cambio = true;
        }
        if(!this.usuario.getNombreInstitucion().equals(this.nombreInstituto)){
            this.usuario.setNombreInstitucion(this.nombreInstituto);
            cambio = true;
        }
        if(!this.usuario.getNombreRepresentante().equals(this.representante)){
            this.usuario.setNombreRepresentante(this.representante);
            cambio = true;
        }
        if(this.jcfFoto.getPathFoto() != null){
            this.usuario.setFotoString(this.foto);
            cambio = true;
        }
        if(!this.usuario.getFechaExpedicion().equals(this.fechaMiembro)){
            this.usuario.setFechaExpedicion(this.fechaMiembro);
            cambio = true;
        }
        
        return cambio;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jcfFoto = new jcFoto.jcFoto();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNomUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtApUsuario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbSexo = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jdtFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txtTlf1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTlf2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jcbEstudia = new javax.swing.JCheckBox();
        txtInstitucion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtRepresentante = new javax.swing.JTextField();
        jcbMiembro = new javax.swing.JCheckBox();
        txtFechaMiembro = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gestion usuarios");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcfFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jcfFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos personales"));

        jLabel1.setText("Cedula usuario:");

        jLabel2.setText("Nombre usuario:");

        txtNomUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNomUsuarioFocusLost(evt);
            }
        });
        txtNomUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomUsuarioKeyTyped(evt);
            }
        });

        jLabel3.setText("Apellidos usuario:");

        txtApUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtApUsuarioFocusLost(evt);
            }
        });
        txtApUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApUsuarioKeyTyped(evt);
            }
        });

        jLabel4.setText("Sexo usuario:");

        cmbSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sexo", "M", "F" }));
        cmbSexo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbSexoFocusLost(evt);
            }
        });

        jLabel5.setText("Fecha nac. usuario:");

        jdtFechaNacimiento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jdtFechaNacimientoFocusLost(evt);
            }
        });

        jLabel6.setText("Tlf. Contacto 1:");

        txtTlf1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTlf1KeyTyped(evt);
            }
        });

        jLabel7.setText("Tlf. Contacto 2:");

        txtTlf2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTlf2KeyTyped(evt);
            }
        });

        jLabel8.setText("Dirección usuario:");

        jcbEstudia.setText("Estudia");
        jcbEstudia.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jcbEstudia.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jcbEstudiaStateChanged(evt);
            }
        });

        txtInstitucion.setEditable(false);
        txtInstitucion.setText("Nombre institución");
        txtInstitucion.setEnabled(false);

        jLabel9.setText("Representante:");

        jcbMiembro.setText("Miembro");
        jcbMiembro.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jcbMiembro.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jcbMiembroStateChanged(evt);
            }
        });

        txtFechaMiembro.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jcbEstudia)
                    .addComponent(jLabel9)
                    .addComponent(jcbMiembro))
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNomUsuario)
                        .addComponent(txtCedula)
                        .addComponent(txtApUsuario)
                        .addComponent(jdtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTlf1)
                        .addComponent(txtTlf2)
                        .addComponent(txtDireccion)
                        .addComponent(txtInstitucion, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                        .addComponent(txtRepresentante)
                        .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtFechaMiembro, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNomUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtApUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jdtFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTlf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTlf2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbEstudia)
                    .addComponent(txtInstitucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbMiembro, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaMiembro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardar32px.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addComponent(btnGuardar)
                .addGap(109, 109, 109)
                .addComponent(btnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbEstudiaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jcbEstudiaStateChanged
        // TODO add your handling code here:
        if(this.jcbEstudia.isSelected()){
            this.txtInstitucion.setEditable(true);
            this.txtInstitucion.setEnabled(true);
            this.txtInstitucion.setText("");
            this.txtInstitucion.requestFocus();
        }
        else{
            this.txtInstitucion.setEditable(false);
            this.txtInstitucion.setEnabled(false);
        }
    }//GEN-LAST:event_jcbEstudiaStateChanged

    private void jcbMiembroStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jcbMiembroStateChanged
        // TODO add your handling code here:
        if(this.jcbMiembro.isSelected()){
            this.fechaMiembro = new Date();            
            this.txtFechaMiembro.setText(this.formateador.format(this.fechaMiembro));
        }
        else{
            this.txtFechaMiembro.setText("");
            this.fechaMiembro = null;
        }
    }//GEN-LAST:event_jcbMiembroStateChanged

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        this.cedula = this.txtCedula.getText().trim().toUpperCase();
        this.nombreUsu= this.txtNomUsuario.getText().trim();
        this.apellidoUsu= this.txtApUsuario.getText().trim();
        this.sexo = this.cmbSexo.getSelectedItem().toString();
        this.fechaNac = this.jdtFechaNacimiento.getDate();
        this.tlf1 = this.txtTlf1.getText().trim();
        this.tlf2 = this.txtTlf2.getText().trim();
        this.direccion = this.txtDireccion.getText().trim();
        this.nombreInstituto = this.txtInstitucion.getText().trim();
        this.representante = this.txtRepresentante.getText().trim();
        this.foto = this.jcfFoto.getPathFoto();
        
        if(this.jcbEstudia.isSelected()){
            this.estudia = "1";
        }
        if(this.jcbMiembro.isSelected()){
            this.miembro= "1";
        }
        
        if(this.usuario == null){
            if(!this.validarCampos()){
                this.usuario = new Usuario(this.cedula, this.nombreUsu, this.apellidoUsu, 
                                           this.sexo, this.fechaNac, this.tlf1, this.tlf2, 
                                           this.direccion, this.estudia, this.miembro, 
                                           this.nombreInstituto, this.representante, 
                                           this.foto, this.fechaMiembro);
                if(this.usuario.insertarUsuario()){
                    this.limpiarCampo();
                    JOptionPane.showMessageDialog(this, "Exito se guardo un nuevo Usuario", "Dialogo de confirmación", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(this, "No se pudo registrar el usuario", "Dialogo de error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Por favor verifique los datos obligatorios \n para el registros de usuarios", "adBiblioteca", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else{
            if(this.updateUsuario()){
                JOptionPane.showMessageDialog(this, "Exito, se actualizo el usuario", "adBiblioteca", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(this, "No se ha modificado ningun campo del usuario \n No se hizo ningun cambio en la base de datos",
                                              "adBiblioteca", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.limpiarCampo();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtTlf1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTlf1KeyTyped
        // TODO add your handling code here:
        soloNum(evt);
    }//GEN-LAST:event_txtTlf1KeyTyped

    private void txtTlf2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTlf2KeyTyped
        // TODO add your handling code here:
        soloNum(evt);
    }//GEN-LAST:event_txtTlf2KeyTyped

    private void txtNomUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNomUsuarioFocusLost
        // TODO add your handling code here:
        if(!txtNomUsuario.getText().isEmpty()){
            normalizeInput(txtNomUsuario);
        }
    }//GEN-LAST:event_txtNomUsuarioFocusLost

    private void txtNomUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomUsuarioKeyTyped
        // TODO add your handling code here:
        soloABC(evt);
    }//GEN-LAST:event_txtNomUsuarioKeyTyped

    private void txtApUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtApUsuarioFocusLost
        // TODO add your handling code here:
        if(!txtApUsuario.getText().isEmpty()){
            normalizeInput(txtApUsuario);
        }
    }//GEN-LAST:event_txtApUsuarioFocusLost

    private void txtApUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApUsuarioKeyTyped
        // TODO add your handling code here:
        soloABC(evt);
    }//GEN-LAST:event_txtApUsuarioKeyTyped

    private void cmbSexoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSexoFocusLost
        // TODO add your handling code here:
        if(!cmbSexo.getSelectedItem().equals("Sexo")){
            normalizeInput(cmbSexo);
        }
    }//GEN-LAST:event_cmbSexoFocusLost

    private void jdtFechaNacimientoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jdtFechaNacimientoFocusLost
        // TODO add your handling code here:
        if(jdtFechaNacimiento.getDate() != null){
            normalizeInput(jdtFechaNacimiento);
        }
    }//GEN-LAST:event_jdtFechaNacimientoFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox cmbSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JCheckBox jcbEstudia;
    private javax.swing.JCheckBox jcbMiembro;
    private jcFoto.jcFoto jcfFoto;
    private com.toedter.calendar.JDateChooser jdtFechaNacimiento;
    private javax.swing.JTextField txtApUsuario;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFechaMiembro;
    private javax.swing.JTextField txtInstitucion;
    private javax.swing.JTextField txtNomUsuario;
    private javax.swing.JTextField txtRepresentante;
    private javax.swing.JTextField txtTlf1;
    private javax.swing.JTextField txtTlf2;
    // End of variables declaration//GEN-END:variables
}
