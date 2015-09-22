package Vistas;

import Clases.Usuario;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
    private Date fechaMiembro= null;

    /**
     * Creates new form internalUsuario
     */
    public internalUsuario() {
        initComponents();
        this.jdtFechaNacimiento.getDateEditor().getUiComponent().addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if("date".equals(evt.getPropertyName())){
                    if(jdtFechaNacimiento.getDate() != null){
                        normalizeInput(jdtFechaNacimiento);
                    }
                }
            }
        });
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
        this.cmbGrado.setSelectedItem(this.usuario.getGradoEstudio());
        this.txtRepresentante.setText(this.usuario.getNombreRepresentante());
        if(this.usuario.getTrabaja().equals("1")){
            this.jcbEstudia.setSelected(true);
        }
        if(this.usuario.getFoto() != null){
            this.jcbMiembro.setSelected(true);
            this.jcbMiembro.setEnabled(false);
            this.txtFechaMiembro.setText(this.formateador.format(this.usuario.getFechaExpedicion()));
            this.jcfFoto.setFoto(new javax.swing.ImageIcon(this.usuario.getFoto()));            
        }
        if(this.usuario.getTrabaja().equals("1")){
            this.jcbTrabaja.setSelected(true);
        }
        
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
        if(this.jcbEstudia.isSelected()){
            if(this.txtInstitucion.getText().isEmpty()){
                showError(txtInstitucion, "Por favor digite el nombre de la institución");
                error = true;
            }
        }
        if(this.cmbGrado.getSelectedIndex() == 0){
            showError(cmbGrado, "Seleccione un grado de estudio para el usuario");
            error = true;
        }
        if(this.jcfFoto.getPathFoto() != null){
            String [] subGetPathFoto= this.jcfFoto.getPathFoto().split("\\.");
            int leng= subGetPathFoto.length-1;
            if(!subGetPathFoto[leng].equalsIgnoreCase("jpg"))
            JOptionPane.showMessageDialog(this, "El formato de la foto no es el correcto. Verifique que sea jpg", "adBiblioteca", JOptionPane.INFORMATION_MESSAGE);
            error= true;
        }
        if(this.jcfFoto.getPathFoto() != null && this.txtFechaMiembro.getText().isEmpty()){
            showError(txtFechaMiembro, "Por favor seleccione ser miembro");
            error = true;
        }
        if(this.jcbMiembro.isEnabled()){
            if(this.jcfFoto.getPathFoto() == null && !this.txtFechaMiembro.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Debe seleccionar una foto para poder ser miembro", "adBiblioteca", JOptionPane.INFORMATION_MESSAGE);
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
        this.cmbSexo.setSelectedItem("Sexo");
        this.jdtFechaNacimiento.setDate(null);
        this.txtTlf1.setText("");
        this.txtTlf2.setText("");
        this.txtDireccion.setText("");
        this.jcbEstudia.setSelected(false);
        this.cmbGrado.setSelectedIndex(0);
        this.txtRepresentante.setText("");
        this.jcbTrabaja.setSelected(false);
        this.jcbMiembro.setSelected(false);
        this.jcfFoto.setFoto(this.jcfFoto.getFotoDefault());
        
        normalizeInput(txtNomUsuario);
        normalizeInput(txtApUsuario);
        normalizeInput(cmbSexo);
        normalizeInput(jdtFechaNacimiento);
        normalizeInput(txtTlf1);
        normalizeInput(txtTlf2);
        normalizeInput(txtDireccion);
        normalizeInput(cmbGrado);
                
        this.btnGuardar.setText("Guardar");
    }
    
    private boolean updateUsuario(){
        String estudia= "0";
        String miembro= "0";
        String trabaja= "0";
        boolean cambio= false;
        if(!this.usuario.getCedUsuario().equals(txtCedula.getText().trim())){
            this.usuario.setCedUsuario(txtCedula.getText().trim());
            cambio = true;
        }
        if(!this.usuario.getNombre().equals(txtNomUsuario.getText().trim())){
            this.usuario.setNombre(txtNomUsuario.getText().trim());
            cambio = true;
        }
        if(!this.usuario.getApellido().equals(txtApUsuario.getText().trim())){
            this.usuario.setApellido(txtApUsuario.getText().trim());
            cambio = true;
        }
        if(!this.usuario.getSexo().equals(cmbSexo.getSelectedItem())){
            this.usuario.setSexo(cmbSexo.getSelectedItem().toString());
            cambio = true;
        }
        if(!this.usuario.getFechaNacimiento().equals(jdtFechaNacimiento.getDate())){
            this.usuario.setFechaNacimiento(jdtFechaNacimiento.getDate());
            cambio = true;
        }
        if(!this.usuario.getTlf1().equals(txtTlf1.getText().trim())){
            this.usuario.setTlf1(txtTlf1.getText().trim());
            cambio = true;
        }
        if(!this.usuario.getTlf2().equals(txtTlf2.getText().trim())){
            this.usuario.setTlf2(txtTlf2.getText().trim());
            cambio = true;
        }
        if(!this.usuario.getDireccion().equals(txtDireccion.getText().trim())){
            this.usuario.setDireccion(txtDireccion.getText().trim());
            cambio = true;
        }
        if(jcbEstudia.isSelected()){
            estudia= "1";
        }
        if(!this.usuario.getEstudia().equals(estudia)){
            this.usuario.setEstudia(estudia);
            if(estudia.equals("0")){
                this.usuario.setNombreInstitucion("");                
            }
            else{
                this.usuario.setNombreInstitucion(txtInstitucion.getText().trim());
            }
            cambio = true;
        }
        if(jcbMiembro.isSelected()){
            miembro= "1";
        }
        if(!this.usuario.getMiembro().equals(miembro)){
            this.usuario.setMiembro(miembro);
            if(miembro.equals("0"))
                this.usuario.setFechaExpedicion(null);
            else
                this.usuario.setFechaExpedicion(this.fechaMiembro);
            cambio = true;
        }
        if(!this.usuario.getNombreInstitucion().equals(txtInstitucion.getText().trim())){
            this.usuario.setNombreInstitucion(txtInstitucion.getText().trim());
            cambio = true;
        }
        if(!this.usuario.getNombreRepresentante().equals(txtRepresentante.getText().trim())){
            this.usuario.setNombreRepresentante(txtRepresentante.getText().trim());
            cambio = true;
        }
        if(this.jcfFoto.getPathFoto() != null){
            this.usuario.setFotoString(jcfFoto.getPathFoto());
            cambio = true;
        }
        if(this.usuario.getFechaExpedicion() == null && this.fechaMiembro != null){
            this.usuario.setFechaExpedicion(this.fechaMiembro);
            cambio = true;
        }
        else{
            if(this.usuario.getFechaExpedicion() != this.fechaMiembro){
                this.usuario.setFechaExpedicion(this.fechaMiembro);
                cambio = true;                
            }
        }
        if(!this.usuario.getGradoEstudio().equals(this.cmbGrado.getSelectedItem())){
            this.usuario.setGradoEstudio(this.cmbGrado.getSelectedItem().toString());
            cambio = true;
        }
        if(jcbTrabaja.isSelected()){
            trabaja = "1";
        }
        if(!this.usuario.getTrabaja().equals(trabaja)){
            this.usuario.setTrabaja(trabaja);
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

        contenedor = new javax.swing.JPanel();
        panelFoto = new javax.swing.JPanel();
        jcfFoto = new jcFoto.jcFoto();
        PanelDatos = new javax.swing.JPanel();
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
        jLabel10 = new javax.swing.JLabel();
        cmbGrado = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txtRepresentante = new javax.swing.JTextField();
        jcbMiembro = new javax.swing.JCheckBox();
        txtFechaMiembro = new javax.swing.JTextField();
        jcbTrabaja = new javax.swing.JCheckBox();
        panelBotones = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gestion usuarios");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        contenedor.setLayout(new java.awt.BorderLayout());

        jcfFoto.setBorder(null);

        javax.swing.GroupLayout panelFotoLayout = new javax.swing.GroupLayout(panelFoto);
        panelFoto.setLayout(panelFotoLayout);
        panelFotoLayout.setHorizontalGroup(
            panelFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jcfFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelFotoLayout.setVerticalGroup(
            panelFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFotoLayout.createSequentialGroup()
                .addComponent(jcfFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 224, Short.MAX_VALUE))
        );

        contenedor.add(panelFoto, java.awt.BorderLayout.LINE_START);

        PanelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos personales"));

        jLabel1.setText("Cedula usuario:");

        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });

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
        cmbSexo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSexoItemStateChanged(evt);
            }
        });

        jLabel5.setText("Fecha nac. usuario:");

        jLabel6.setText("Tlf. Contacto 1:");

        txtTlf1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTlf1FocusLost(evt);
            }
        });
        txtTlf1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTlf1KeyTyped(evt);
            }
        });

        jLabel7.setText("Tlf. Contacto 2:");

        txtTlf2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTlf2FocusLost(evt);
            }
        });
        txtTlf2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTlf2KeyTyped(evt);
            }
        });

        jLabel8.setText("Dirección usuario:");

        txtDireccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDireccionFocusLost(evt);
            }
        });

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
        txtInstitucion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtInstitucionFocusLost(evt);
            }
        });

        jLabel10.setText("Grado de estudio:");

        cmbGrado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Grado de estudio", "Primaria", "Secundaria", "Tecnico", "Universitario" }));
        cmbGrado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbGradoItemStateChanged(evt);
            }
        });

        jLabel9.setText("Representante:");

        jcbMiembro.setText("Miembro");
        jcbMiembro.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jcbMiembro.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jcbMiembroStateChanged(evt);
            }
        });

        txtFechaMiembro.setEditable(false);

        jcbTrabaja.setText("Trabaja");
        jcbTrabaja.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout PanelDatosLayout = new javax.swing.GroupLayout(PanelDatos);
        PanelDatos.setLayout(PanelDatosLayout);
        PanelDatosLayout.setHorizontalGroup(
            PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosLayout.createSequentialGroup()
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCedula)
                            .addComponent(txtNomUsuario)))
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTlf1))
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtApUsuario))
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbMiembro, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRepresentante)
                            .addComponent(txtFechaMiembro)))
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbTrabaja, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelDatosLayout.createSequentialGroup()
                                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(PanelDatosLayout.createSequentialGroup()
                                        .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4))
                                    .addComponent(jdtFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 101, Short.MAX_VALUE))
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcbEstudia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelDatosLayout.createSequentialGroup()
                                .addComponent(cmbGrado, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtInstitucion)
                            .addComponent(txtDireccion)
                            .addComponent(txtTlf2))))
                .addContainerGap())
        );
        PanelDatosLayout.setVerticalGroup(
            PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosLayout.createSequentialGroup()
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTlf1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTlf2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbEstudia)
                    .addComponent(txtInstitucion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbGrado, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbTrabaja)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaMiembro, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbMiembro))
                .addContainerGap())
        );

        contenedor.add(PanelDatos, java.awt.BorderLayout.CENTER);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardar32px.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        panelBotones.add(javax.swing.Box.createGlue());
        panelBotones.add(btnGuardar);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        panelBotones.add(btnCancelar);

        contenedor.add(panelBotones, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(contenedor);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbEstudiaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jcbEstudiaStateChanged
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
        if(this.jcbMiembro.isSelected()){
            this.fechaMiembro = new Date();            
            this.txtFechaMiembro.setText(this.formateador.format(this.fechaMiembro));
            normalizeInput(txtFechaMiembro);
        }
        else{
            this.txtFechaMiembro.setText("");
            this.fechaMiembro = null;
        }
    }//GEN-LAST:event_jcbMiembroStateChanged

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String cedula = this.txtCedula.getText().trim().toUpperCase();
        String nombreUsu= this.txtNomUsuario.getText().trim();
        String apellidoUsu= this.txtApUsuario.getText().trim();
        String sexo = this.cmbSexo.getSelectedItem().toString();
        Date fechaNac = this.jdtFechaNacimiento.getDate();
        String tlf1 = this.txtTlf1.getText().trim();
        String tlf2 = this.txtTlf2.getText().trim();
        String direccion = this.txtDireccion.getText().trim();
        String nombreInstituto = this.txtInstitucion.getText().trim();
        String gradoEstudio = this.cmbGrado.getSelectedItem().toString();
        String representante = this.txtRepresentante.getText().trim();
        String foto = this.jcfFoto.getPathFoto();
        
        String estudia= "0";
        String trabaja= "0";
        String miembro= "0";
        
        if(this.jcbEstudia.isSelected()){
            estudia = "1";
        }
        if(this.jcbTrabaja.isSelected()){
            trabaja = "1";
        }
        if(this.jcbMiembro.isSelected()){
            miembro= "1";
        }
        if(this.validarCampos()){
            JOptionPane.showMessageDialog(this, "Por favor verifique los datos obligatorios"
                                              + "\npara el registro de usuarios", "adBiblioteca", 
                                          JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if(this.usuario == null){
            this.usuario = new Usuario(cedula, nombreUsu, apellidoUsu, 
                                        sexo, fechaNac, tlf1, tlf2, 
                                        direccion, estudia, nombreInstituto, 
                                        gradoEstudio, representante,
                                        trabaja, miembro, this.fechaMiembro, foto);
            if(this.usuario.insertarUsuario()){
                this.limpiarCampo();
                JOptionPane.showMessageDialog(this, "Exito se guardo un nuevo Usuario", "Dialogo de confirmación", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(this, "No se pudo registrar el usuario", "Dialogo de error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            if(this.updateUsuario()){
                JOptionPane.showMessageDialog(this, "Exito, se actualizo el usuario", "adBiblioteca", JOptionPane.INFORMATION_MESSAGE);
                this.limpiarCampo();
            }
            else{
                JOptionPane.showMessageDialog(this, "No se ha modificado ningun campo del usuario \n No se hizo ningun cambio en la base de datos",
                                              "adBiblioteca", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.limpiarCampo();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtTlf1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTlf1KeyTyped
        soloNum(evt);
    }//GEN-LAST:event_txtTlf1KeyTyped

    private void txtTlf2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTlf2KeyTyped
        soloNum(evt);
    }//GEN-LAST:event_txtTlf2KeyTyped

    private void txtNomUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNomUsuarioFocusLost
        if(!txtNomUsuario.getText().isEmpty()){
            normalizeInput(txtNomUsuario);
        }
    }//GEN-LAST:event_txtNomUsuarioFocusLost

    private void txtNomUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomUsuarioKeyTyped
        soloABC(evt);
    }//GEN-LAST:event_txtNomUsuarioKeyTyped

    private void txtApUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtApUsuarioFocusLost
        if(!txtApUsuario.getText().isEmpty()){
            normalizeInput(txtApUsuario);
        }
    }//GEN-LAST:event_txtApUsuarioFocusLost

    private void txtApUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApUsuarioKeyTyped
        // TODO add your handling code here:
        soloABC(evt);
    }//GEN-LAST:event_txtApUsuarioKeyTyped

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        soloNum(evt);
    }//GEN-LAST:event_txtCedulaKeyTyped

    private void cmbSexoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSexoItemStateChanged
        if(evt.getStateChange() != ItemEvent.SELECTED){
            normalizeInput(cmbSexo);
        }
    }//GEN-LAST:event_cmbSexoItemStateChanged

    private void cmbGradoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbGradoItemStateChanged
        if(evt.getStateChange() != ItemEvent.SELECTED){
            normalizeInput(cmbGrado);
        }
    }//GEN-LAST:event_cmbGradoItemStateChanged

    private void txtTlf1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTlf1FocusLost
        if(!txtTlf1.getText().trim().isEmpty()){
            normalizeInput(txtTlf1);
        }
    }//GEN-LAST:event_txtTlf1FocusLost

    private void txtTlf2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTlf2FocusLost
        if(!txtTlf2.getText().trim().isEmpty()){
            normalizeInput(txtTlf2);
        }
    }//GEN-LAST:event_txtTlf2FocusLost

    private void txtDireccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDireccionFocusLost
        if(!txtDireccion.getText().trim().isEmpty()){
            normalizeInput(txtDireccion);
        }
    }//GEN-LAST:event_txtDireccionFocusLost

    private void txtInstitucionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtInstitucionFocusLost
        if(jcbEstudia.isSelected() && !txtInstitucion.getText().isEmpty()){
            normalizeInput(txtInstitucion);
        }
    }//GEN-LAST:event_txtInstitucionFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelDatos;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox cmbGrado;
    private javax.swing.JComboBox cmbSexo;
    private javax.swing.JPanel contenedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JCheckBox jcbEstudia;
    private javax.swing.JCheckBox jcbMiembro;
    private javax.swing.JCheckBox jcbTrabaja;
    private jcFoto.jcFoto jcfFoto;
    private com.toedter.calendar.JDateChooser jdtFechaNacimiento;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelFoto;
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
