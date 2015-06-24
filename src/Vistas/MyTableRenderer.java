package Vistas;

import Clases.Libro;
import java.awt.Component;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author zirex
 */
public class MyTableRenderer {
    
    public static class BotonLibroRenderer implements TableCellRenderer{

        public final Icon PREVIEW_ICON = new ImageIcon(getClass().getResource("/Imagenes/editar.png"));
        private JButton btnLibro= new JButton(PREVIEW_ICON);    

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            btnLibro.setFocusable(false); 
            btnLibro.setFocusPainted(false);
            btnLibro.setMargin(new Insets(0, 0, 0, 0));
            btnLibro.setToolTipText("Clic para modificar el libro de la fila");
            return btnLibro;
        }
    }
    
    public static class BotonLibroEditor extends AbstractCellEditor implements TableCellEditor{
        public final Icon PREVIEW_ICON = new ImageIcon(getClass().getResource("/Imagenes/editar.png"));
        private JButton btn= new JButton(PREVIEW_ICON);  
        
        public BotonLibroEditor(final JTable table){
            btn.setFocusable(false);
            btn.setFocusPainted(false); 
            btn.setMargin(new Insets(0, 0, 0, 0));
            btn.setToolTipText("Clic para modificar el libro de la fila");
            btn.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row= table.convertRowIndexToModel(table.getEditingRow());
                    String id_libro= table.getValueAt(row, 0).toString();
                    Libro libro = Libro.libroBD(id_libro);
                    internalLibro.txtNomLibro.setText(libro.getNomLibro());
                    internalLibro.txtEditorial.setText(libro.getEditorial());
                    internalLibro.txtAutor.setText(libro.getNomAutor());
                    internalLibro.cmbGrado.setSelectedIndex(Integer.parseInt(libro.getGrado()));
                    internalLibro.txtArea.setText(libro.getArea());
                    internalLibro.txaCota.setText(libro.getCota());
                    internalLibro.txtEjemplares.setText(libro.getEjemplar());
                    internalLibro.txtUbicacion.setText(libro.getUbicacion());
                    if(libro.getPrestamo().equals("0")){
                        internalLibro.rdbNo.setSelected(true);
                    }
                    else{
                        internalLibro.rdbSi.setSelected(true);
                    }
                    internalLibro.btnGuardar.setText("Actualizar");
                    internalLibro.libroTabla = libro;
                }
                
            });
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return btn;
        }
        
    }
    
    /* --------- DIBUJA UNA IMAGEN EN LA TABLA PRESTAMOS PARA SABER SI FUE DEVUELTO O NO ---------*/
    
    public static class ImageRenderer extends DefaultTableCellRenderer {
         @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Image img = null;
            if(value != null) {
                img = getToolkit().getImage(getClass().getResource("/Imagenes/green.png"));
            } else {
                img = getToolkit().getImage(getClass().getResource("/Imagenes/red.png"));
            }
            setSize(16, 16);
            setHorizontalAlignment(SwingConstants.CENTER);
            setIcon(new ImageIcon(img));
            super.getTableCellRendererComponent(table, "", isSelected,
                    hasFocus, row, column);
            return this;
            }        
     }
    
    /* --------- DIBUJA UNA IMAGEN EN LA TABLA PRESTAMOS PARA SABER SI FUE DEVUELTO O NO ---------*/
    public static class ImagenTablasPrestamo extends DefaultTableCellRenderer {
         @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Image img = null;
            int prestar= Integer.parseInt(value.toString());
            if(prestar == 1) {
                img = getToolkit().getImage(getClass().getResource("/Imagenes/green.png"));
            } else {
                img = getToolkit().getImage(getClass().getResource("/Imagenes/red.png"));
            }
            setSize(16, 16);
            setHorizontalAlignment(SwingConstants.CENTER);
            setIcon(new ImageIcon(img));
            super.getTableCellRendererComponent(table, "", isSelected,
                    hasFocus, row, column);
            return this;
            }        
     }
    
    /* --------- DIBUJA UNA IMAGEN EN LA TABLA LIBROS PARA SABER SI FUE DEVUELTO O NO ---------*/
    
    public static class ImageGestionLibro extends DefaultTableCellRenderer {
         @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Image img = null;
            boolean valor= Boolean.parseBoolean(value.toString());
            if(valor == true) {
                img = getToolkit().getImage(getClass().getResource("/Imagenes/green.png"));
            } else {
                img = getToolkit().getImage(getClass().getResource("/Imagenes/red.png"));
            }
            setSize(16, 16);
            setHorizontalAlignment(SwingConstants.CENTER);
            setIcon(new ImageIcon(img));
            super.getTableCellRendererComponent(table, "", isSelected,
                    hasFocus, row, column);
            return this;
            }        
     }
}
