package Vistas;

import Clases.Libro;
import java.awt.event.ItemEvent;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author zirex
 */
public class internalLibro extends Interfaz {
    private Libro libro;
    public static Libro libroTabla;

    /**
     * Creates new form internalLibro
     */
    public internalLibro() {
        initComponents();
    }
    
    private void limpiarCampos(){
        txtNomLibro.setText("");
        txtEditorial.setText("");
        txtAutor.setText("");
        cmbGrado.setSelectedIndex(0);
        txtArea.setText("");
        txaCota.setText("");
        txtEjemplares.setText("");
        txtUbicacion.setText("");
        rdbSi.setSelected(false);
        rdbNo.setSelected(true);
        btnGuardar.setText("Guardar");
        
        normalizeInput(txtNomLibro);
        normalizeInput(txtEditorial);
        normalizeInput(txtAutor);
        normalizeInput(cmbGrado);
        normalizeInput(txtArea);
        normalizeInput(txaCota);
        normalizeInput(txtEjemplares);
        normalizeInput(txtUbicacion);
    }
    
    private boolean validarCampos(){
        boolean error= false;
        if(txtNomLibro.getText().trim().isEmpty()){
            showError(txtNomLibro, "Por favor digite el nombre del libro.");
            error=true;
        }
        if(txtEditorial.getText().trim().isEmpty()){
            showError(txtEditorial, "Por favor digite el nombre de la editorial.");
            error=true;
        }
        if(txtAutor.getText().trim().isEmpty()){
            showError(txtAutor, "Por favor digite el nombre del autor.");
            error=true;
        }
        if(cmbGrado.getSelectedIndex()==0){
            showError(cmbGrado, "Por favor seleccione el grado del libro.");
            error=true;
        }
        if(txtArea.getText().trim().isEmpty()){
            showError(txtArea, "Por favor digite el area a la que esta enfocada el libro.");
            error=true;
        }
        if(txaCota.getText().trim().isEmpty()){
            showError(txaCota, "Por favor digite la cota del libro.");
            error=true;
        }
        if(txtEjemplares.getText().trim().isEmpty()){
            showError(txtEjemplares, "Por favor digite una cifra de ejemplares.");
            error=true;
        }
        if(txtUbicacion.getText().trim().isEmpty()){
            showError(txtUbicacion, "Por favor digite la ubicación del libro.");
            error=true;
        }
        return error;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgPrestamo = new javax.swing.ButtonGroup();
        contenedor = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNomLibro = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtEditorial = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAutor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbGrado = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rdbSi = new javax.swing.JRadioButton();
        rdbNo = new javax.swing.JRadioButton();
        txtArea = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaCota = new javax.swing.JTextArea();
        txtEjemplares = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtUbicacion = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaLibros = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setTitle("Libros biblioteca publica");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setText("Nombre del libro:");

        txtNomLibro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNomLibroFocusLost(evt);
            }
        });

        jLabel2.setText("Nombre editoral:");

        txtEditorial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEditorialFocusLost(evt);
            }
        });

        jLabel3.setText("Nombre del autor:");

        txtAutor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAutorFocusLost(evt);
            }
        });

        jLabel4.setText("Grado del libro:");

        cmbGrado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Seleccione un grado--", "Primer grado", "Segundo grado", "Tercer grado", "Cuarto grado", "Quinto grado", "Sexto grado", "Septimo grado", "Octavo grado", "Noveno grado", "Decimo grado", "Universitario" }));
        cmbGrado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbGradoItemStateChanged(evt);
            }
        });

        jLabel5.setText("Area:");

        jLabel6.setText("Cota:");

        jLabel7.setText("Ejemplares:");

        jLabel8.setText("Permitir prestamo:");

        btgPrestamo.add(rdbSi);
        rdbSi.setText("Si");

        btgPrestamo.add(rdbNo);
        rdbNo.setSelected(true);
        rdbNo.setText("No");

        txtArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAreaFocusLost(evt);
            }
        });

        txaCota.setColumns(20);
        txaCota.setLineWrap(true);
        txaCota.setRows(5);
        txaCota.setAutoscrolls(false);
        txaCota.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txaCotaFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(txaCota);

        txtEjemplares.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEjemplaresFocusLost(evt);
            }
        });
        txtEjemplares.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEjemplaresKeyTyped(evt);
            }
        });

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

        jLabel9.setText("Ubicación:");

        txtUbicacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUbicacionFocusLost(evt);
            }
        });

        tablaLibros.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablaLibros);

        javax.swing.GroupLayout contenedorLayout = new javax.swing.GroupLayout(contenedor);
        contenedor.setLayout(contenedorLayout);
        contenedorLayout.setHorizontalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(contenedorLayout.createSequentialGroup()
                        .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(433, 433, 433))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorLayout.createSequentialGroup()
                        .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addGap(22, 22, 22)
                        .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNomLibro)
                            .addComponent(txtAutor)
                            .addComponent(cmbGrado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtArea)
                            .addComponent(jScrollPane1)
                            .addComponent(txtEjemplares)
                            .addComponent(txtUbicacion)
                            .addComponent(txtEditorial)
                            .addGroup(contenedorLayout.createSequentialGroup()
                                .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(contenedorLayout.createSequentialGroup()
                                        .addComponent(btnGuardar)
                                        .addGap(69, 69, 69)
                                        .addComponent(btnCancelar))
                                    .addGroup(contenedorLayout.createSequentialGroup()
                                        .addComponent(rdbSi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdbNo)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        contenedorLayout.setVerticalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNomLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbGrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contenedorLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(contenedorLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEjemplares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbSi)
                    .addComponent(rdbNo)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        getContentPane().add(contenedor);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarTablaLibro(){
        AbstractTableModel model= Libro.getLibros();
        this.tablaLibros.setModel(model);
        this.tablaLibros.getTableHeader().setReorderingAllowed(false);
        this.tablaLibros.setRowHeight(30);
        this.tablaLibros.setDefaultRenderer(Boolean.class, new MyTableRenderer.ImageGestionLibro());
        TableColumn column = this.tablaLibros.getColumnModel().getColumn(7);
        column.setCellRenderer(new MyTableRenderer.BotonLibroRenderer());
        column.setCellEditor(new MyTableRenderer.BotonLibroEditor(tablaLibros));
    }
    
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(this.validarCampos()){
            JOptionPane.showMessageDialog(this, "Por favor revise que los datos esten completos.", "adBiblioteca", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String nombreLibro = txtNomLibro.getText().trim();
        String editorial = txtEditorial.getText().trim();
        String autor = txtAutor.getText().trim();
        String grado = cmbGrado.getSelectedIndex()+"";
        String area = txtArea.getText().trim();
        String cota = txaCota.getText().trim();
        String ejemplar = txtEjemplares.getText().trim();
        String ubicacion = txtUbicacion.getText().trim();
        String prestar = "0";

        if(rdbSi.isSelected()){
            prestar = "1";
        }
        
        this.libro= new Libro(nombreLibro, editorial, autor, grado, area, cota, ejemplar, ubicacion, prestar);
        
        if(btnGuardar.getText().equals("Guardar")){
            if(this.libro.insertarRegistro()){
                JOptionPane.showMessageDialog(this, "Exito.. Se ha registrado un nuevo libro", "Dialogo de confirmación",
                    JOptionPane.INFORMATION_MESSAGE);
                this.limpiarCampos();
                this.cargarTablaLibro();
            }
            else{
                JOptionPane.showMessageDialog(this, "Error... Se ha generado un problema al registrar el libro", "Dialogo de error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            if(libroTabla.editarLibro(this.libro)){
                JOptionPane.showMessageDialog(this, "Exito.. Se ha actualizado el libro \n"+this.libro.getNomLibro(), "Dialogo de confirmación",
                    JOptionPane.INFORMATION_MESSAGE);
                this.limpiarCampos();
                this.cargarTablaLibro();
            }
            else{
                JOptionPane.showMessageDialog(this, "Error... Se ha generado un problema al actualizar el libro", "Dialogo de error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.limpiarCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        this.cargarTablaLibro();
    }//GEN-LAST:event_formInternalFrameOpened

    private void txtEjemplaresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEjemplaresKeyTyped
        soloNum(evt);
    }//GEN-LAST:event_txtEjemplaresKeyTyped

    private void txtNomLibroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNomLibroFocusLost
        if(!txtNomLibro.getText().trim().isEmpty()){
            normalizeInput(txtNomLibro);
        }
    }//GEN-LAST:event_txtNomLibroFocusLost

    private void txtEditorialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEditorialFocusLost
        if(!txtEditorial.getText().trim().isEmpty()){
            normalizeInput(txtEditorial);
        }
    }//GEN-LAST:event_txtEditorialFocusLost

    private void txtAutorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAutorFocusLost
        if(!txtAutor.getText().trim().isEmpty()){
            normalizeInput(txtAutor);
        }
    }//GEN-LAST:event_txtAutorFocusLost

    private void cmbGradoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbGradoItemStateChanged
        if(evt.getStateChange() != ItemEvent.SELECTED){
            normalizeInput(cmbGrado);
        }
    }//GEN-LAST:event_cmbGradoItemStateChanged

    private void txtAreaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAreaFocusLost
        if(!txtArea.getText().trim().isEmpty()){
            normalizeInput(txtArea);
        }
    }//GEN-LAST:event_txtAreaFocusLost

    private void txaCotaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txaCotaFocusLost
        if(!txaCota.getText().trim().isEmpty()){
            normalizeInput(txaCota);
        }
    }//GEN-LAST:event_txaCotaFocusLost

    private void txtEjemplaresFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEjemplaresFocusLost
        if(!txtEjemplares.getText().trim().isEmpty()){
            normalizeInput(txtEjemplares);
        }
    }//GEN-LAST:event_txtEjemplaresFocusLost

    private void txtUbicacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUbicacionFocusLost
        if(!txtUbicacion.getText().trim().isEmpty()){
            normalizeInput(txtUbicacion);
        }
    }//GEN-LAST:event_txtUbicacionFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgPrestamo;
    private javax.swing.JButton btnCancelar;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JComboBox cmbGrado;
    private javax.swing.JPanel contenedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JRadioButton rdbNo;
    public static javax.swing.JRadioButton rdbSi;
    private javax.swing.JTable tablaLibros;
    public static javax.swing.JTextArea txaCota;
    public static javax.swing.JTextField txtArea;
    public static javax.swing.JTextField txtAutor;
    public static javax.swing.JTextField txtEditorial;
    public static javax.swing.JTextField txtEjemplares;
    public static javax.swing.JTextField txtNomLibro;
    public static javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables
}
