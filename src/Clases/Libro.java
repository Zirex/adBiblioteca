package Clases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author zirex
 */
public class Libro extends Conexion{
    private String idLibro;
    private String nomLibro;
    private String editorial;
    private String nomAutor;
    private String grado;
    private String area;
    private String cota;
    private String ejemplar;
    private String ubicacion;
    private String prestamo;

    public Libro(String nomLibro, String editorial, String nomAutor, String grado, String area, String cota, String ejemplar, String ubicacion, String prestamo) {
        this.nomLibro = nomLibro;
        this.editorial = editorial;
        this.nomAutor = nomAutor;
        this.grado = grado;
        this.area = area;
        this.cota = cota;
        this.ejemplar = ejemplar;
        this.ubicacion = ubicacion;
        this.prestamo = prestamo;
    }

    private Libro(String idLibro, String nomLibro, String editorial, String nomAutor, String grado, String area, String cota, String ejemplar, String ubicacion, String prestamo) {
        this.idLibro = idLibro;
        this.nomLibro = nomLibro;
        this.editorial = editorial;
        this.nomAutor = nomAutor;
        this.grado = grado;
        this.area = area;
        this.cota = cota;
        this.ejemplar = ejemplar;
        this.ubicacion = ubicacion;
        this.prestamo = prestamo;
    }

    public String getNomLibro() {
        return nomLibro;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getNomAutor() {
        return nomAutor;
    }

    public String getGrado() {
        return grado;
    }

    public String getArea() {
        return area;
    }

    public String getCota() {
        return cota;
    }

    public String getEjemplar() {
        return ejemplar;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getPrestamo() {
        return prestamo;
    }
    
    public boolean insertarRegistro(){        
        try{
            String q= "INSERT INTO libro(nom_libro, nom_editorial, nom_autor, "
        + "grado, area, cota, ejemplares, ubicacion, prestamo) "
        + "VALUES(?,?,?,?,?,?,?,?,?);";
            PreparedStatement pstm= this.getConexion().prepareStatement(q);
            pstm.setString(1, this.nomLibro);
            pstm.setString(2, this.editorial);
            pstm.setString(3, this.nomAutor);
            pstm.setString(4, this.grado);
            pstm.setString(5, this.area);
            pstm.setString(6, this.cota);
            pstm.setString(7, this.ejemplar);
            pstm.setString(8, this.ubicacion);
            pstm.setString(9, this.prestamo);
            
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean editarLibro(Libro libro){
        Libro libroNuevo= libro;
        String sql= "UPDATE libro SET";
        if(!libroNuevo.getNomLibro().equals(this.nomLibro)){
            sql+= " nom_libro= '"+libroNuevo.getNomLibro()+"',";
        }
        if(!libroNuevo.getEditorial().equals(this.editorial)){
            sql+=" nom_editorial= '"+libroNuevo.getEditorial()+"',";
        }
        if(!libroNuevo.getNomAutor().equals(this.nomAutor)){
            sql+=" nom_autor= '"+libroNuevo.getNomAutor()+"',";
        }
        if(!libroNuevo.getGrado().equals(this.grado)){
            sql+=" grado= '"+libroNuevo.getGrado()+"',";
        }
        if(!libroNuevo.getArea().equals(this.area)){
            sql+=" area= '"+libroNuevo.getArea()+"',";
        }
        if(!libroNuevo.getCota().equals(this.cota)){
            sql+=" cota= '"+libroNuevo.getCota()+"',";
        }
        if(!libroNuevo.getEjemplar().equals(this.ejemplar)){
            sql+=" ejemplares= '"+libroNuevo.getEjemplar()+"',";
        }
        if(!libroNuevo.getUbicacion().equals(this.ubicacion)){
            sql+=" ubicacion= '"+libroNuevo.getUbicacion()+"',";
        }
        if(!libroNuevo.getPrestamo().equals(this.prestamo)){
            sql+=" prestamo= '"+libroNuevo.getPrestamo()+"',";
        }
        if(!sql.equals("UPDATE libro SET")){
            sql= sql.substring(0, sql.length()-1)+" WHERE id_libro= "+this.idLibro;
            try{
                PreparedStatement pstm= this.getConexion().prepareStatement(sql);
                pstm.execute();
                pstm.close();
                return true;
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
                return false;
            }
        }else{
            return false;
        }
    }
    
    public static Libro libroBD(String idLibro){
        Conexion con = new Conexion();
        Libro libro = null;
        try{
            String sql = "SELECT * FROM libro WHERE id_libro='"+idLibro+"';";
            PreparedStatement pstm= con.getConexion().prepareStatement(sql);
            ResultSet res= pstm.executeQuery();
            HashMap map= new HashMap();
            while(res.next()){
                ResultSetMetaData data= res.getMetaData();
                for (int i = 1, j= data.getColumnCount(); i <= j; i++) {
                    map.put(data.getColumnLabel(i), res.getString(i));
                }
            }
            libro = new Libro(map.get("id_libro").toString(), map.get("nom_libro").toString(),
                              map.get("nom_editorial").toString(), map.get("nom_autor").toString(), 
                              map.get("grado").toString(), map.get("area").toString(),
                              map.get("cota").toString(), map.get("ejemplares").toString(),
                              map.get("ubicacion").toString(),map.get("prestamo").toString());
            res.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return libro;
    }
    
    public static ArrayList<HashMap> libros(String q){
        ArrayList<HashMap> listaLibros = new ArrayList<>();
        Conexion con = new Conexion();
        try{
            PreparedStatement pstm = con.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            while(res.next()){
                HashMap map = new HashMap();
                ResultSetMetaData meta = res.getMetaData();
                for (int i = 1, j= meta.getColumnCount(); i <= j; i++) {
                    map.put(meta.getColumnLabel(i).toLowerCase(), res.getString(i));
                }
                listaLibros.add(map);
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return listaLibros;
    }
     
    public static AbstractTableModel getLibros (){
        AbstractTableModel model = new AbstractTableModel(){
            private Conexion con = new Conexion();
            private ArrayList<HashMap> listaLibros = new ArrayList<>();
            private Object[][] datos = this.libros();
            private String[] nameColumn = {"id", "Nombre", "Editorial", "grado", "cota",
                                           "ubicación", "prestamo", "Actualizar"};
            
            private Object [][] libros(){
                boolean activo= true;
                boolean inactivo= false;
                try{
                    String q= "SELECT id_libro, nom_libro, nom_editorial, grado, cota, ubicacion, prestamo FROM libro;";
                    PreparedStatement pstm = this.con.getConexion().prepareStatement(q);
                    ResultSet res = pstm.executeQuery();
                    while(res.next()){
                        HashMap map = new HashMap();
                        ResultSetMetaData meta = res.getMetaData();
                        for (int i = 1, j= meta.getColumnCount(); i <= j; i++) {
                            map.put(meta.getColumnLabel(i).toLowerCase(), res.getString(i));
                        }
                        listaLibros.add(map);
                    }
                    res.close();
                }catch(SQLException e){
                    System.out.println(e.getMessage());
                }
                Object[][] data = new Object[listaLibros.size()][8];
                int i=0;
                for (HashMap libro : listaLibros) {
                    String [] linea = {libro.get("id_libro").toString(), libro.get("nom_libro").toString(), 
                                       libro.get("nom_editorial").toString(), libro.get("grado").toString(),
                                       libro.get("cota").toString(), libro.get("ubicacion").toString(),
                                       libro.get("prestamo").toString(), ""};
                    for (int j = 0; j < data[i].length; j++) {
                        if(j==6){
                            if(Integer.valueOf(linea[j])==1){
                                data[i][j]= activo;
                            }
                            else{
                                data[i][j]= inactivo;
                            }
                        }
                        else{
                            if(j==7){
                                data[i][j]= new JButton();
                            }
                            else{
                                data[i][j]= linea[j];
                            }
                        }
                    }
                    i++;
                }
                return data;
            }

            @Override
            public int getRowCount() {
                return this.datos.length;
            }

            @Override
            public int getColumnCount() {
                return this.nameColumn.length;
            }

            @Override
            public Object getValueAt(int i, int i1) {
                return this.datos[i][i1];
            }
            
            @Override
            public String getColumnName(int columnIndex){
                return this.nameColumn[columnIndex];
            }
            
            @Override
            public Class<?> getColumnClass(int c){
                return this.datos[0][c].getClass();
            }
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if(columnIndex==7){
                    return true;
                }
                else{
                    return false;
                }
            }
        };
        
    return model;
    }
}
