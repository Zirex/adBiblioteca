package Reportes;

import Clases.Conexion;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class IReporte extends Conexion{
    private final String logo= "/Imagenes/gobierno.png";
    
    public void cargarDetallePrestamo(int idPrestamo, int idLector){
        JasperReport repor;
        JasperPrint re;
        
        try{
            URL in= this.getClass().getResource("DetallePrestamo.jasper");
            InputStream subDetallePrestamo= this.getClass().getResourceAsStream("SubDetallePrestamo.jasper");
            repor= (JasperReport) JRLoader.loadObject(in);
            Map<String, Object> parametros= new HashMap<>();
            parametros.clear();
            parametros.put("logo", getClass().getResourceAsStream(logo));
            parametros.put("idPrestamo", idPrestamo);
            parametros.put("idLector", idLector);
            parametros.put("SubDetallePrestamo", subDetallePrestamo);
            re= JasperFillManager.fillReport(repor, parametros, this.getConexion());
            JasperViewer.viewReport(re, false);
        }catch(JRException e){
            System.err.println(e.getMessage());
        }
    }
    
    public void cargarDetalleUsuario(int idUsuario){
        JasperReport repor;
        JasperPrint re;
        try{
            URL in= this.getClass().getResource("DetalleUsuario.jasper");
            repor= (JasperReport) JRLoader.loadObject(in);
            Map<String, Object> parametros= new HashMap<>();
            parametros.clear();
            parametros.put("logo", this.getClass().getResourceAsStream(logo));
            parametros.put("idUsuario", idUsuario);
            re= JasperFillManager.fillReport(repor, parametros, this.getConexion());
            JasperViewer.viewReport(re, false);
        }catch(JRException e){
            System.err.println(e.getMessage());
        }
    }
    
    public void cargarReporteLibros(){
        JasperReport repor;
        JasperPrint re;
        try{
            URL in= this.getClass().getResource("ReporteLibros.jasper");
            repor= (JasperReport) JRLoader.loadObject(in);
            Map<String, Object> parametros= new HashMap<>();
            parametros.clear();
            parametros.put("logo", this.getClass().getResourceAsStream(logo));
            re= JasperFillManager.fillReport(repor, parametros, this.getConexion());
            JasperViewer.viewReport(re, false);
        }catch(JRException e){
            System.err.println(e.getMessage());
        }
    }
    
    public void cargarReporteUsuarios(){
        JasperReport repor;
        JasperPrint re;
        try{
            URL in= this.getClass().getResource("ReporteUsuarios.jasper");
            repor= (JasperReport) JRLoader.loadObject(in);
            Map<String, Object> parametros= new HashMap<>();
            parametros.clear();
            parametros.put("logo", this.getClass().getResourceAsStream(logo));
            re= JasperFillManager.fillReport(repor, parametros, this.getConexion());
            JasperViewer.viewReport(re, false);
        }catch(JRException e){
            System.err.println(e.getMessage());
        }
    }
    
    public void cargarReportePrestamos(){
        JasperReport repor;
        JasperPrint re;
        try{
            URL in= this.getClass().getResource("ReportePrestamos.jasper");
            repor= (JasperReport) JRLoader.loadObject(in);
            Map<String, Object> parametros= new HashMap<>();
            parametros.clear();
            parametros.put("logo", this.getClass().getResourceAsStream(logo));
            parametros.put("green", this.getClass().getResourceAsStream("/Imagenes/green.png"));
            parametros.put("red", this.getClass().getResourceAsStream("/Imagenes/red.png"));
            re= JasperFillManager.fillReport(repor, parametros, this.getConexion());
            JasperViewer.viewReport(re, false);
        }catch(JRException e){
            System.err.println(e.getMessage());
        }
    }
}
