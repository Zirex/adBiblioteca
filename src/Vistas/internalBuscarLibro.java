/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Libro;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author zirex
 */
public class internalBuscarLibro extends javax.swing.JInternalFrame {
    
    /**
     * Creates new form internalBuscarLibro
     */
    public internalBuscarLibro() {
        initComponents();        
    }
    
    private void cargarTablaLibros(String q, String [] ColumName){
        ArrayList<HashMap> libros = Libro.libros(q);        
        String [] Column = ColumName;
        Object [][] datos = new Object [libros.size()][Column.length];
        int i = 0;
        for (HashMap dato : libros) {
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
        this.setAnchoColumnas();
    }
    
    private void setAnchoColumnas(){
        JViewport scroll =  (JViewport) tablaLibro.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna=0;
        TableColumnModel modeloColumna = tablaLibro.getColumnModel();
        TableColumn columnaTabla;
        for (int i = 0; i < tablaLibro.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            switch(i){
                case 0: anchoColumna = (90*ancho)/100;
                        break;
                case 1: anchoColumna = (90*ancho)/100;
                        break;
                case 2: anchoColumna = (90*ancho)/100;
                        break;
                case 3: anchoColumna = (20*ancho)/100;
                        break;
                case 4: anchoColumna = (20*ancho)/100;
                        break;
                case 5: anchoColumna = (40*ancho)/100;
                        break;
                case 6: anchoColumna = (90*ancho)/100;
                        break;
            }                     
            columnaTabla.setPreferredWidth(anchoColumna);           
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

        JPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        cmbBuscarLibro = new javax.swing.JComboBox();
        txtBuscarLibro = new javax.swing.JTextField();
        btnLibro = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaLibro = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setTitle("Busqueda de Libros");

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

            },
            new String [] {
                "Libro", "Editorial", "Autor", "Grado", "Prestamo", "Area", "Ubicación"
            }
        ));
        jScrollPane2.setViewportView(tablaLibro);
        if (tablaLibro.getColumnModel().getColumnCount() > 0) {
            tablaLibro.getColumnModel().getColumn(0).setPreferredWidth(60);
            tablaLibro.getColumnModel().getColumn(1).setPreferredWidth(60);
            tablaLibro.getColumnModel().getColumn(2).setPreferredWidth(60);
            tablaLibro.getColumnModel().getColumn(3).setPreferredWidth(10);
            tablaLibro.getColumnModel().getColumn(4).setPreferredWidth(10);
            tablaLibro.getColumnModel().getColumn(5).setPreferredWidth(20);
            tablaLibro.getColumnModel().getColumn(6).setPreferredWidth(90);
        }

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
                        .addComponent(btnLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 68, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        JPanel10Layout.setVerticalGroup(
            JPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cmbBuscarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLibroActionPerformed
        // TODO add your handling code here:
        String busqueda= this.txtBuscarLibro.getText().trim().toLowerCase();
        String [] campoBusqueda = {"nom_libro", "nom_editorial", "nom_autor", "area"};
        String [] ColumName = {"Libro", "Editorial", "Autor", "Grado", "Prestamo", "Area", "Ubicación"};
        int seleccion = this.cmbBuscarLibro.getSelectedIndex()-1;
        if(!busqueda.isEmpty() && seleccion > -1){
            String q= "SELECT nom_libro AS Libro, nom_editorial AS Editorial, nom_autor AS Autor,"
                    + " grado AS Grado, prestamo AS Prestamo, area As Area, ubicacion AS Ubicación FROM"
                    + " libro WHERE "+campoBusqueda[seleccion]+" LIKE'"+busqueda+"%';";
            this.cargarTablaLibros(q, ColumName);
        }
        else{
            JOptionPane.showMessageDialog(this, "Por favor verifique si seleccionó el modo de busqueda \ny que el campo no este vacio", "adBiblioteca", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnLibroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanel10;
    private javax.swing.JButton btnLibro;
    private javax.swing.JComboBox cmbBuscarLibro;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaLibro;
    private javax.swing.JTextField txtBuscarLibro;
    // End of variables declaration//GEN-END:variables
}
