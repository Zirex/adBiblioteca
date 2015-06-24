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

public class IDetallePrestamo extends Conexion{
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
}
