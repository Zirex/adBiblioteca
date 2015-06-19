package Vistas;

import Clases.Grafico;
import Clases.Lector;
import java.awt.Dimension;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zirex
 */
public class internalResumen extends javax.swing.JInternalFrame {

    /**
     * Creates new form internalResumen
     */
    public internalResumen() {
        initComponents();
        this.cargarTablaLector();        
    }
    
    private void cargarTablaLector(){
        DefaultTableModel model= Lector.modeloLector();
        this.tablaLectores.setModel(model);
        this.tablaLectores.getTableHeader().setReorderingAllowed(false);
//        this.tablaLibro.setRowHeight(30);
//        TableColumn column = this.tablaLibro.getColumnModel().getColumn(7);
//        column.setCellRenderer(new MyTableRenderer.BotonLibroRenderer());
//        column.setCellEditor(new MyTableRenderer.BotonLibroEditor(tablaLibro));
        
    }
    
    private void cargarGrafico(){
        Grafico miGrafico= new Grafico();
        Dimension d= this.contenedorEstadistico.getSize();
        int [] valor={30, 15, 50, 10, 25, 20, 3, 16, 13, 40, 35, 5};
        String [] grupoDato= {"Visitantes"};
        String [] ColumName= {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                              "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre",
                              "Diciembre"};
        String [] etiquetaGrafico= {"Lectores al mes", "Meses", "Lectores"};
        
        miGrafico.crearGrafico(d, valor, grupoDato, ColumName, etiquetaGrafico);
        
        this.lbGrafico.setBounds(0, 0, d.width, d.height);
        miGrafico.mostrarGrafico(lbGrafico);
        this.repaint();
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
        panelLectores = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaLectores = new javax.swing.JTable();
        panelPrestamos = new javax.swing.JPanel();
        panelEstadistico = new javax.swing.JPanel();
        contenedorEstadistico = new javax.swing.JPanel();
        lbGrafico = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Resumen de administración");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        contenedor.setLayout(new java.awt.GridLayout(3, 0));

        panelLectores.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Resumen de lectores"));
        panelLectores.setLayout(new javax.swing.BoxLayout(panelLectores, javax.swing.BoxLayout.LINE_AXIS));

        tablaLectores.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaLectores);

        panelLectores.add(jScrollPane1);

        contenedor.add(panelLectores);

        panelPrestamos.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Resumen de prestamos"));

        javax.swing.GroupLayout panelPrestamosLayout = new javax.swing.GroupLayout(panelPrestamos);
        panelPrestamos.setLayout(panelPrestamosLayout);
        panelPrestamosLayout.setHorizontalGroup(
            panelPrestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 687, Short.MAX_VALUE)
        );
        panelPrestamosLayout.setVerticalGroup(
            panelPrestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
        );

        contenedor.add(panelPrestamos);

        panelEstadistico.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Cuadro estadistico"));
        panelEstadistico.setLayout(new javax.swing.BoxLayout(panelEstadistico, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout contenedorEstadisticoLayout = new javax.swing.GroupLayout(contenedorEstadistico);
        contenedorEstadistico.setLayout(contenedorEstadisticoLayout);
        contenedorEstadisticoLayout.setHorizontalGroup(
            contenedorEstadisticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 687, Short.MAX_VALUE)
            .addGroup(contenedorEstadisticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lbGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE))
        );
        contenedorEstadisticoLayout.setVerticalGroup(
            contenedorEstadisticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 136, Short.MAX_VALUE)
            .addGroup(contenedorEstadisticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lbGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
        );

        panelEstadistico.add(contenedorEstadistico);

        contenedor.add(panelEstadistico);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        this.cargarGrafico();
    }//GEN-LAST:event_formComponentShown


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contenedor;
    private javax.swing.JPanel contenedorEstadistico;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbGrafico;
    private javax.swing.JPanel panelEstadistico;
    private javax.swing.JPanel panelLectores;
    private javax.swing.JPanel panelPrestamos;
    private javax.swing.JTable tablaLectores;
    // End of variables declaration//GEN-END:variables
}
