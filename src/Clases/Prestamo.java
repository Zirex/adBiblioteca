/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zirex
 */
public class Prestamo extends Lector{
    private String idPrestamo;
    private Date fechaDev;

    private Prestamo(String idPrestamo, String idUsuario, Date fechaLecutra, Date fechaDev, String[][] libros) {
        super(idUsuario, fechaLecutra, libros);
        this.idPrestamo = idPrestamo;
        this.fechaDev= fechaDev;
    }    
    
    public Prestamo(String idUsuario, Date fechaLecutra, String[][] libros) {
        super(idUsuario, fechaLecutra, libros);
    }
    
    private boolean insertarPrestamo(){
        try{
            String q="INSERT INTO prestamo(fecha_prestamo, id_lector) VALUES(?,?)";
            PreparedStatement pstm= this.getConexion().prepareStatement(q);
            pstm.setDate(1, new java.sql.Date(this.fechaLecutra.getTime()));
            pstm.setInt(2, this.idLector);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    private void insertarLibroPrestamo(){
        try{
            String q = "INSERT INTO lector_libro VALUES(?,?,?)";
            PreparedStatement pstm= this.getConexion().prepareStatement(q);
            for (String[] libro : libros) {
                pstm.setInt(1, this.idLector);
                pstm.setString(2, libro[0]);
                pstm.setInt(3, 1);
                pstm.execute();
            }
            pstm.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void setLibrosPrestamo(String [][] librosLector){
        try{
            ArrayList<String> librosP= new ArrayList();
            for (String [] libro : this.libros) {
                librosP.add(libro[0]);
            }
            Statement st= this.getConexion().createStatement();
            for (String[] libro : librosLector) {
                boolean ok= false;
                for (String libroP : librosP) {
                    if(libro[0].equals(libroP)){
                        String q= "UPDATE lector_libro SET prestamo="+1+" WHERE id_lector="+this.idLector+
                                  " AND id_libro="+libro[0];
                        st.execute(q);
                        librosP.remove(libroP);
                        break;
                    }
                }
                if(librosP.isEmpty()){
                    break;
                }
            }
            for (int i= 0;  i< librosP.size(); i++) {
                String q= "INSERT lector_libro VALUES("+this.idLector+", "+librosP.get(i)+", 1);";
                st.execute(q);                
            }
            st.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public boolean nuevoPrestamo(){
        boolean ok= false;
        Lector l= Lector.buscarLector(this.idUsuario);
        if(l==null){
            if(this.insertarLector())
                if(this.insertarPrestamo()){
                    this.insertarLibroPrestamo();
                    ok= true;
                }
        }
        else{
            this.idLector= l.getIdLector();
            if(this.insertarPrestamo()){
                this.setLibrosPrestamo(l.getLibros());
                ok=true;
            }
        }
        return ok;
    }
    
    public boolean devolucion(){
        try{
            String q= "UPDATE prestamo SET fecha_dev='"+new java.sql.Date(new Date().getTime())+"' "
                    + "WHERE id_prestamo="+this.idPrestamo;
            Statement st= this.getConexion().createStatement();
            st.execute(q);
            st.close();
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static Prestamo buscarPrestamo(String idPrestamo){
        Prestamo prestamo= null;
        Conexion con= new Conexion();
        try{
            String q= "SELECT p.id_prestamo, p.fecha_prestamo, p.fecha_dev, p.id_lector,"
                    + " l.id_usuario FROM prestamo p, lector l WHERE p.id_lector=l.id_lector"
                    + " AND id_prestamo="+idPrestamo;
            Statement st= con.getConexion().createStatement();
            ResultSet res= st.executeQuery(q);
            HashMap map = new HashMap();
            while(res.next()){
                ResultSetMetaData meta= res.getMetaData();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    map.put(meta.getColumnLabel(i), res.getString(i));
                }
            }
            if(!map.isEmpty()){
                q="SELECT ll.id_libro, lb.nom_libro, lb.nom_editorial, lb.grado "
                + "FROM lector_libro ll, libro lb WHERE ll.prestamo= 1 AND ll.id_libro=lb.id_libro "
                + "AND ll.id_lector="+map.get("id_lector");
                res= st.executeQuery(q);
                ArrayList<HashMap> datos= new ArrayList();
                while(res.next()){
                    HashMap libro= new HashMap();
                    ResultSetMetaData meta= res.getMetaData();
                    for (int i = 1; i <= meta.getColumnCount(); i++) {
                        libro.put(meta.getColumnLabel(i), res.getString(i));
                    }
                    datos.add(libro);
                }
                res.close();
                String [][] libros= new String[datos.size()][4];
                int i= 0;
                for (HashMap dato : datos) {
                    String [] libro= {dato.get("id_libro")+"", dato.get("nom_libro")+"",
                                      dato.get("nom_editorial")+"", dato.get("grado")+""};
                    libros[i]= libro;
                    i++;
                }
                
                String idUsu= map.get("id_usuario")+"";
                Date fechaPre= new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fecha_prestamo").toString());
                String fecha= map.get("fecha_dev")+"";
                Date fechaDevo= null;
                if(!fecha.equals("null")){
                    fechaDevo= new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
                }
                prestamo= new Prestamo(idPrestamo, idUsu, fechaPre, fechaDevo, libros);
            }
            
        }catch(SQLException | ParseException e){
            System.err.println(e.getMessage());
        }
        return prestamo;
    }
    
    private static ArrayList<HashMap> getPrestamos(String q){
        ArrayList<HashMap> prestamos= new ArrayList();
        Conexion con= new Conexion();
        try{
            Statement st= con.getConexion().createStatement();
            ResultSet res= st.executeQuery(q);
            while(res.next()){
                HashMap map= new HashMap();
                ResultSetMetaData data= res.getMetaData();
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    map.put(data.getColumnLabel(i), res.getString(i));
                }
                prestamos.add(map);
            }
            res.close();
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return prestamos;
    }
    
    public static DefaultTableModel modeloTabla(String q){
        ArrayList<HashMap> prestamos= Prestamo.getPrestamos(q);
        String [] ColumName = {"Numero prestamo","Id del lector","Nombre del lector","Devuelto"};
        Object[][] datos= new Object[prestamos.size()][ColumName.length];
        int i= 0;
        for (HashMap prestamo : prestamos) {
            for (int j = 0; j < datos[i].length; j++) {
                datos[i][j]= prestamo.get(ColumName[j]);
            }
            i++;
        }
        DefaultTableModel modelo;
        modelo = new DefaultTableModel(datos, ColumName){
            @Override
            public boolean isCellEditable(int i, int il){
                return false;
            }
            
            public Class<?> getColumnClass(int c){
                if(c == 3){
                    return Date.class;
                }else{
                    return String.class;
                }
            }
        };
        return modelo;
    }
}
