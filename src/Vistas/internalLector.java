package Vistas;

import Clases.Lector;
import Clases.Libro;
import Clases.Usuario;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zirex
 */
public class internalLector extends javax.swing.JInternalFrame {
    private Principal principal;
    private internalUsuario usuario;
    private ArrayList<HashMap> usuarios;
    private ArrayList<HashMap> libros;
    private String idLibro="";
    private String idUsuario="";
    private DefaultTableModel model;
    private Lector l;

    /**
     * Creates new form internalLector1
     */
    public internalLector(Principal principal, internalUsuario usuario) {
        initComponents();
        this.principal = principal;
        this.usuario = usuario;
        this.model = (DefaultTableModel) tablaLibros.getModel();
    }
    
    private void nuevoUsuario(){
        if(principal.estaCerrado(usuario)){
            usuario = new internalUsuario();
            principal.panel.add(usuario);
            usuario.show();
        }
        else{
            usuario.moveToFront();
            try {
                usuario.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(internalLector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void cargarTablaUsuario(){
        String [] Column = {"Id de usuario", "Apellidos", "Nombres", "Miembro"};
        Object [][] datos = new Object [this.usuarios.size()][Column.length];
        int i = 0;
        for (HashMap dato : this.usuarios) {
            String [] linea = {dato.get("id_usuario").toString(),
                               dato.get("apellido_usu").toString(), 
                               dato.get("nombre_usu").toString(),
                               dato.get("miembro").toString()};
            for(int j=0; j < datos[i].length; j++){
                datos[i][j] = linea[j];
            }
            i++;
        }
        DefaultTableModel model = new DefaultTableModel(datos, Column){
          @Override
          public boolean isCellEditable(int i, int il){
                return false;
            }  
        };
        this.tablaUsuario.setModel(model);
    }
    
    private Integer calcularEdad(String fecha){
    Date fechaNac=null;
        try {
            /**Se puede cambiar la mascara por el formato de la fecha
            que se quiera recibir, por ejemplo día mes año "dd-MM-yyyy"
            en este caso es año mes día*/
            fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        } catch (Exception ex) {
            System.out.println("Error:"+ex);
        }
        Calendar fechaNacimiento = Calendar.getInstance();
        //Se crea un objeto con la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        //Se asigna la fecha recibida a la fecha de nacimiento.
        fechaNacimiento.setTime(fechaNac);
        //Se restan la fecha actual y la fecha de nacimiento
        int año = fechaActual.get(Calendar.YEAR)- fechaNacimiento.get(Calendar.YEAR);
        int mes =fechaActual.get(Calendar.MONTH)- fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE)- fechaNacimiento.get(Calendar.DATE);
        //Se ajusta el año dependiendo el mes y el día
        if(mes<0 || (mes==0 && dia<0)){
            año--;
        }
        //Regresa la edad en base a la fecha de nacimiento
        return año;
    }
    
    private void llenarCamposUsuario(HashMap map){
        this.idUsuario = map.get("id_usuario").toString();
        this.txtEdad.setText(this.calcularEdad(map.get("fecha_nac_usu").toString())+"");
        txtGradoUsuario.setText(map.get("grado_estudio").toString());
        if(map.get("trabaja").toString().equals("1")){
            lblTrabaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/green.png")));
        }
    }
    
    private void llenarCamposLibro(HashMap map){
        this.idLibro= map.get("id_libro").toString();
        this.txtNombreLibro.setText(map.get("nom_libro").toString());
        this.txtEditorial.setText(map.get("nom_editorial").toString());
        this.txtGradoLibro.setText(map.get("grado").toString());
        if(map.get("prestamo").equals("1")){
            lblPrestamo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/green.png")));
        }
    }
    
    private void cargarTablaLibros(String q, String [] ColumName){
        this.libros = Libro.libros(q);        
        String [] Column = ColumName;
        Object [][] datos = new Object [this.libros.size()][Column.length];
        int i = 0;
        for (HashMap dato : this.libros) {
            for(int j=0; j < datos[i].length; j++){
                datos[i][j] = dato.get(Column[j]);
            }
            i++;
        }
        DefaultTableModel model = new DefaultTableModel(datos, Column){
          @Override
          public boolean isCellEditable(int i, int il){
                return false;
            }  
        };
        this.tablaLibro.setModel(model);
    }
    
    private void limpiarCampos(){
        this.idUsuario= "";
        this.txtNombreUsuario.setText("");
        this.txtEdad.setText("");
        this.txtGradoUsuario.setText("");
        this.lblTrabaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/red.png")));
        
        this.idLibro= "";
        this.txtNombreLibro.setText("");
        this.txtEditorial.setText("");
        this.txtGradoLibro.setText("");
        this.lblPrestamo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/red.png")));
        
        int filas= this.model.getRowCount()-1;
        for (int i = filas; i >= 0; i--) {
            this.model.removeRow(i);
        }
    }
    
    private void consultarLectorToday(){
        this.l= Lector.buscarLector(idUsuario);
        if(this.l != null){
            this.model.setDataVector(this.l.getLibros(), new Object[]{"id_libro", "nom_libro", "nom_editorial", "grado"});
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

        jpmMenuTabla = new javax.swing.JPopupMenu();
        menuDelete = new javax.swing.JMenuItem();
        contenedor = new javax.swing.JPanel();
        panelLector = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        txtGradoUsuario = new javax.swing.JTextField();
        lblTrabaja = new javax.swing.JLabel();
        btnBuscarUsu = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNombreLibro = new javax.swing.JTextField();
        txtEditorial = new javax.swing.JTextField();
        txtGradoLibro = new javax.swing.JTextField();
        lblPrestamo = new javax.swing.JLabel();
        btnBuscarLibro = new javax.swing.JButton();
        btnAgregarLibro = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaLibros = new javax.swing.JTable();
        panelUsuario = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuario = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cmbBuscar = new javax.swing.JComboBox();
        txtBuscarUsuario = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnAtrasUsuario = new javax.swing.JButton();
        panelLibro = new javax.swing.JPanel();
        JPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        cmbBuscarLibro = new javax.swing.JComboBox();
        txtBuscarLibro = new javax.swing.JTextField();
        btnLibro = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaLibro = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        btnAtrasLibro = new javax.swing.JButton();

        menuDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/borrar24x24.png"))); // NOI18N
        menuDelete.setText("Eliminar libro");
        menuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDeleteActionPerformed(evt);
            }
        });
        jpmMenuTabla.add(menuDelete);

        setClosable(true);
        setIconifiable(true);
        setTitle("Gestion Lectores");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        contenedor.setLayout(new java.awt.CardLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Nuevo visitante"));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Información del usuario"));

        jLabel1.setText("Nombre y apellido:");

        jLabel2.setText("Edad:");

        jLabel3.setText("Grado de estudio:");

        jLabel4.setText("Trabaja:");

        txtEdad.setEditable(false);

        txtGradoUsuario.setEditable(false);

        lblTrabaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/red.png"))); // NOI18N

        btnBuscarUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar1.png"))); // NOI18N
        btnBuscarUsu.setBorderPainted(false);
        btnBuscarUsu.setContentAreaFilled(false);
        btnBuscarUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarUsuActionPerformed(evt);
            }
        });

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
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEdad, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(lblTrabaja)
                            .addComponent(txtGradoUsuario))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtNombreUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscarUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtGradoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblTrabaja))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Información del libro"));

        jLabel5.setText("Nombre del libro:");

        jLabel6.setText("Editorial:");

        jLabel7.setText("Grado del libro:");

        jLabel8.setText("prestamo:");

        txtEditorial.setEditable(false);

        txtGradoLibro.setEditable(false);

        lblPrestamo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/red.png"))); // NOI18N

        btnBuscarLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar1.png"))); // NOI18N
        btnBuscarLibro.setBorderPainted(false);
        btnBuscarLibro.setContentAreaFilled(false);
        btnBuscarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarLibroActionPerformed(evt);
            }
        });

        btnAgregarLibro.setText("Agregar");
        btnAgregarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarLibroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEditorial)
                            .addComponent(txtNombreLibro, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPrestamo)
                            .addComponent(txtGradoLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregarLibro)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtNombreLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtGradoLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lblPrestamo)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnAgregarLibro)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        tablaLibros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id del libro", "Nombre del libro", "Editorial", "Grado del libro"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaLibros.setComponentPopupMenu(jpmMenuTabla);
        jScrollPane3.setViewportView(tablaLibros);
        if (tablaLibros.getColumnModel().getColumnCount() > 0) {
            tablaLibros.getColumnModel().getColumn(0).setPreferredWidth(20);
            tablaLibros.getColumnModel().getColumn(1).setPreferredWidth(90);
            tablaLibros.getColumnModel().getColumn(2).setPreferredWidth(90);
            tablaLibros.getColumnModel().getColumn(3).setPreferredWidth(20);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(btnGuardar)
                .addGap(104, 104, 104)
                .addComponent(btnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar)))
        );

        javax.swing.GroupLayout panelLectorLayout = new javax.swing.GroupLayout(panelLector);
        panelLector.setLayout(panelLectorLayout);
        panelLectorLayout.setHorizontalGroup(
            panelLectorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLectorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelLectorLayout.setVerticalGroup(
            panelLectorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLectorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        contenedor.add(panelLector, "card2");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuarios de la biblioteca"));

        tablaUsuario.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUsuario);

        jLabel9.setText("Hacer doble clic para seleccionar al usuario");

        jLabel10.setText("Buscar por:");

        cmbBuscar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar busqueda", "cedula", "nombre y apellido" }));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar1.png"))); // NOI18N
        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnAtrasUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/atras.png"))); // NOI18N
        btnAtrasUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtBuscarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 174, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAtrasUsuario)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtrasUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelUsuarioLayout = new javax.swing.GroupLayout(panelUsuario);
        panelUsuario.setLayout(panelUsuarioLayout);
        panelUsuarioLayout.setHorizontalGroup(
            panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelUsuarioLayout.setVerticalGroup(
            panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        contenedor.add(panelUsuario, "card3");

        JPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Libros de la biblioteca"));

        jLabel11.setText("Buscar por:");

        cmbBuscarLibro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar modo busqueda", "Nombre de libros", "Editorial de libros", "Autor de libros", "Area de libros" }));

        btnLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar1.png"))); // NOI18N
        btnLibro.setBorderPainted(false);
        btnLibro.setContentAreaFilled(false);
        btnLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLibroActionPerformed(evt);
            }
        });

        tablaLibro.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaLibro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaLibroMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaLibro);

        jLabel12.setText("Hacer doble clic para seleccionar al usuario");

        btnAtrasLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/atras.png"))); // NOI18N
        btnAtrasLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasLibroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPanel10Layout = new javax.swing.GroupLayout(JPanel10);
        JPanel10.setLayout(JPanel10Layout);
        JPanel10Layout.setHorizontalGroup(
            JPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanel10Layout.createSequentialGroup()
                        .addGroup(JPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(JPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbBuscarLibro, 0, 235, Short.MAX_VALUE))
                            .addComponent(txtBuscarLibro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPanel10Layout.createSequentialGroup()
                                .addComponent(btnLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanel10Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnAtrasLibro))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(JPanel10Layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPanel10Layout.setVerticalGroup(
            JPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cmbBuscarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtrasLibro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelLibroLayout = new javax.swing.GroupLayout(panelLibro);
        panelLibro.setLayout(panelLibroLayout);
        panelLibroLayout.setHorizontalGroup(
            panelLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLibroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelLibroLayout.setVerticalGroup(
            panelLibroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLibroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        contenedor.add(panelLibro, "card4");

        getContentPane().add(contenedor);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarUsuActionPerformed
        // TODO add your handling code here:
        if(txtNombreUsuario.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Por favor digite el nombre y apellido del lector", "adBiblioteca", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            String [] usu = this.txtNombreUsuario.getText().trim().toLowerCase().split(" ");
            if(usu.length==2){
                String sql= "SELECT id_usuario, nombre_usu, apellido_usu, fecha_nac_usu,"
                          + " grado_estudio, trabaja, miembro FROM usuario WHERE nombre_usu"
                          + " LIKE '"+usu[0]+"%' AND apellido_usu LIKE '"+usu[1]+"%'";
                this.usuarios= Usuario.usuarios(sql);
                
                if(this.usuarios.isEmpty()){
                    String msj= "No hay ningún usuario que coincida con los datos suministrados. \nDesea crear un nuevo usuario?";
                    int opc= JOptionPane.showConfirmDialog(this, msj, "adBiblioteca", JOptionPane.OK_OPTION);
                    if(opc == 0){
                        this.nuevoUsuario();
                    }
                }
                else{
                    if(this.usuarios.size() == 1){
                        HashMap map = this.usuarios.get(0);
                        this.llenarCamposUsuario(map);
                        this.consultarLectorToday();
                    }
                    else{
                        this.cargarTablaUsuario();
                        contenedor.removeAll();
                        contenedor.add(panelUsuario);
                        contenedor.repaint();
                        contenedor.revalidate();
                    }
                }            
            }
            else{
                JOptionPane.showMessageDialog(this, "Debe digitar el primer nombre y el primer apellido del usuario", "adBiblioteca", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnBuscarUsuActionPerformed

    private void btnBuscarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarLibroActionPerformed
        // TODO add your handling code here:
        String q= "";
        String [] ColumName= {"id_libro", "nom_libro", "nom_editorial", "grado", "prestamo"};
        if(this.txtNombreLibro.getText().isEmpty()){
            q= "SELECT id_libro, nom_libro, nom_editorial, grado, prestamo FROM libro";
            this.cargarTablaLibros(q, ColumName);
        }
        else{
            String nombreLibro = txtNombreLibro.getText().trim().toLowerCase();
            q= "SELECT id_libro, nom_libro, nom_editorial, grado, prestamo FROM libro WHERE nom_libro LIKE '"+nombreLibro+"%';";
            this.cargarTablaLibros(q, ColumName);
        }
        if(this.tablaLibro.getRowCount() > 0){
            contenedor.removeAll();
            contenedor.add(panelLibro);
            contenedor.repaint();
            contenedor.revalidate();
        }
        else{
            JOptionPane.showMessageDialog(this, "No existe ningun libro", "adBiblioteca", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarLibroActionPerformed

    private void tablaUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuarioMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            int fila= tablaUsuario.getSelectedRow();
            HashMap map = this.usuarios.get(fila);
            this.llenarCamposUsuario(map);
            this.consultarLectorToday();
            contenedor.removeAll();
            contenedor.add(panelLector);
            contenedor.repaint();
            contenedor.revalidate();
            
        }
    }//GEN-LAST:event_tablaUsuarioMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        if(cmbBuscar.getSelectedIndex() != 0){
            if(!txtBuscarUsuario.getText().trim().toLowerCase().equals("")){

            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tablaLibroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaLibroMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2){
            int fila = tablaLibro.getSelectedRow();
            HashMap map = this.libros.get(fila);
            this.llenarCamposLibro(map);
            contenedor.removeAll();
            contenedor.add(panelLector);
            contenedor.repaint();
            contenedor.revalidate();
            
        }
    }//GEN-LAST:event_tablaLibroMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if(!this.idUsuario.isEmpty() && this.model.getRowCount()!= 0){
            String [][] libros= new String[this.model.getRowCount()][this.model.getColumnCount()];
            for(int i=0; i< libros.length; i++){
                for(int j=0; j< libros[i].length; j++){
                    libros[i][j]= tablaLibros.getValueAt(i, j).toString();
                }
            }
            if(this.l == null){
                l= new Lector(this.idUsuario, new Date(), libros);
                if(l.nuevoLector()){
                    JOptionPane.showMessageDialog(this, "Exito.. Se registro un nuevo lector", "adBiblioteca", JOptionPane.INFORMATION_MESSAGE);
                    this.limpiarCampos();
                }
                else{
                    JOptionPane.showMessageDialog(this, "Error.. No se pudo registrar al lector", "adBiblioteca", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else{
                if(this.l.setLibros(libros)){
                    JOptionPane.showMessageDialog(this, "Exito.. Se actualizó el lector", "adBiblioteca", JOptionPane.INFORMATION_MESSAGE);
                    this.limpiarCampos();
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Los campos deben estar llenos para registrar al lector", "adBiblioteca", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.limpiarCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLibroActionPerformed
        // TODO add your handling code here:
        String busqueda= this.txtBuscarLibro.getText().trim().toLowerCase();
        String [] campoBusqueda = {"nom_libro", "nom_editorial", "nom_autor", "area"};
        String [] ColumName = {"id_libro", "nom_libro", "nom_editorial", "grado", "prestamo", "area"};
        int seleccion = this.cmbBuscarLibro.getSelectedIndex()-1;
        if(!busqueda.isEmpty() && seleccion > -1){
            String q= "SELECT id_libro, nom_libro, nom_editorial, grado, prestamo, area FROM"
                    + " libro WHERE "+campoBusqueda[seleccion]+"='"+busqueda+"';";
            this.cargarTablaLibros(q, ColumName);
        }
        else{
            JOptionPane.showMessageDialog(this, "Por favor verifique si seleccionó el modo de busqueda \ny que el campo no este vacio", "adBiblioteca", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnLibroActionPerformed

    private void btnAgregarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarLibroActionPerformed
        // TODO add your handling code here:
        if(!idLibro.isEmpty()){
            String id= this.idLibro;
            String nombreLibro= this.txtNombreLibro.getText().trim();
            String editorial= this.txtEditorial.getText();
            String gradoLibro= this.txtGradoLibro.getText();
            int filas= this.model.getRowCount();
            boolean existeId= false;
            if(filas>0){
                for (int i = 0; i<filas; i++) {
                    String id_libro= this.tablaLibros.getValueAt(i, 0).toString();
                    if(id_libro.equals(id)){
                       existeId= true;
                       break;
                    }
                }
            }
            if(!existeId){
                model.addRow(new Object[]{id, nombreLibro, editorial, gradoLibro});
            }
            else{
                JOptionPane.showMessageDialog(this, "El libro ya ha sido añadido en la tabla", "adBiblioteca", JOptionPane.INFORMATION_MESSAGE);
            }
            
            this.idLibro= "";
            this.txtNombreLibro.setText("");
            this.txtEditorial.setText("");
            this.txtGradoLibro.setText("");
            this.lblPrestamo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/red.png")));
        }
        else{
            JOptionPane.showMessageDialog(this, "Los campos de información libro deben estar llenos", "adBiblioteca", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarLibroActionPerformed

    private void menuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDeleteActionPerformed
        // TODO add your handling code here:
        int rows[] = tablaLibros.getSelectedRows();
        int len = rows.length-1;
        
        if(len != -1){
            for (;len>=0; len--) {
                int row = rows[len];
                this.model.removeRow(row);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún \n item para borrar",
                                                      "adBiblioteca", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_menuDeleteActionPerformed

    private void btnAtrasLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasLibroActionPerformed
        // TODO add your handling code here:
        contenedor.removeAll();
        contenedor.add(panelLector);
        contenedor.repaint();
        contenedor.revalidate();
    }//GEN-LAST:event_btnAtrasLibroActionPerformed

    private void btnAtrasUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasUsuarioActionPerformed
        // TODO add your handling code here:
        contenedor.removeAll();
        contenedor.add(panelLector);
        contenedor.repaint();
        contenedor.revalidate();
    }//GEN-LAST:event_btnAtrasUsuarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanel10;
    private javax.swing.JButton btnAgregarLibro;
    private javax.swing.JButton btnAtrasLibro;
    private javax.swing.JButton btnAtrasUsuario;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarLibro;
    private javax.swing.JButton btnBuscarUsu;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLibro;
    private javax.swing.JComboBox cmbBuscar;
    private javax.swing.JComboBox cmbBuscarLibro;
    private javax.swing.JPanel contenedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu jpmMenuTabla;
    private javax.swing.JLabel lblPrestamo;
    private javax.swing.JLabel lblTrabaja;
    private javax.swing.JMenuItem menuDelete;
    private javax.swing.JPanel panelLector;
    private javax.swing.JPanel panelLibro;
    private javax.swing.JPanel panelUsuario;
    private javax.swing.JTable tablaLibro;
    private javax.swing.JTable tablaLibros;
    private javax.swing.JTable tablaUsuario;
    private javax.swing.JTextField txtBuscarLibro;
    private javax.swing.JTextField txtBuscarUsuario;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEditorial;
    private javax.swing.JTextField txtGradoLibro;
    private javax.swing.JTextField txtGradoUsuario;
    private javax.swing.JTextField txtNombreLibro;
    private javax.swing.JTextField txtNombreUsuario;
    // End of variables declaration//GEN-END:variables
}
