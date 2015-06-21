package Clases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author zirex
 */
public class Grafico {
    private BufferedImage _imagen;
    private JFreeChart grafico; 
    private Dimension d;
    
    public Grafico(){}
    
    public void crearGrafico(Dimension d, int [] valor, String [] grupoDato, String [] ColumnName, String [] etiquetaGrafico){
        this.d= d;
        // Se guardan los datos
        DefaultCategoryDataset dataSet= new DefaultCategoryDataset();
        // Se llenan los datos
        int mes= Calendar.getInstance().get(Calendar.MONTH)+1;
        int i= 0;
        while(mes < 12){
            dataSet.setValue(valor[i], grupoDato[0], ColumnName[mes]);
            i++;
            mes++;
        }
        int aux= 0;
        for (; i < valor.length; i++) {
            dataSet.setValue(valor[i], grupoDato[0], ColumnName[aux]);
            aux++;
        }
        // Se crea el grafico
        this.grafico= ChartFactory.createBarChart(etiquetaGrafico[0], etiquetaGrafico[1],
                                                  etiquetaGrafico[2], dataSet, PlotOrientation.VERTICAL,
                                                  true, true, false);
        
        // Cambiar el color de las barras
        CategoryPlot plot= grafico.getCategoryPlot();
        BarRenderer render= (BarRenderer) plot.getRenderer();
        render.setSeriesPaint(0, Color.BLUE);
        
        // Se coloca el grafico en memoria
        this._imagen= this.grafico.createBufferedImage(this.d.width, this.d.height);
        System.out.println("Grafico creado");
    }
    
    public void mostrarGrafico(JLabel lb){
        ImageIcon imagen= new ImageIcon(_imagen);
        lb.setIcon(imagen);
        lb.repaint();
    }
    
}
