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
public class Lector extends Conexion{
    private int idLector;
    private String idUsuario;
    private Date fechaLecutra;
    private String [][] libros;
    
    private Lector(int idLector, String idUsuario, Date fechaLecutra, String [][] libros){
        this.idLector= idLector;
        this.idUsuario= idUsuario;
        this.fechaLecutra= fechaLecutra;
        this.libros= libros;
    }
    
    public Lector(String idUsuario, Date fechaLecutra, String [][] libros) {
        this.idLector= this.setIdLector();
        this.idUsuario = idUsuario;
        this.fechaLecutra = fechaLecutra;
        this.libros= libros;
    }
    
    private int setIdLector(){
        try{
            int ultimoLector=0;
            String q= "SELECT id_lector FROM lector ORDER BY id_lector DESC LIMIT 1;";
            PreparedStatement pstm= this.getConexion().prepareStatement(q);
            ResultSet res= pstm.executeQuery();
            while(res.next()){
                ultimoLector= res.getInt(1);
            }
            res.close();
            return ultimoLector+1;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public String[][] getLibros() {
        return libros;
    }

    public boolean setLibros(String[][] libros) {
        boolean ok= false;
        int i= 1;
        try{
            Statement st= this.getConexion().createStatement();
            for(String[] libro : this.libros) {
                String[] libroN= libros[i-1];
                if(!libro[0].equals(libroN[0])){
                    String sql="DELETE FROM lector_libro WHERE id_lector="+this.idLector+" AND id_libro="+libro[0];
                    st.execute(sql);
                }
                else if( (i+1) < libros.length ){
                    i++;                    
                }
                
            }
            for ( ;i<=libros.length-1;i++) {
                String[] libro= libros[i];
                String sql= "INSERT INTO lector_libro VALUES("+this.idLector+", "+libro[0]+")";
                st.execute(sql);
            }
            st.close();
            ok=true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return ok;
    }
    
    private boolean insertarLector(){
        try{
            String sql= "INSERT INTO lector(id_usuario, fecha_lectura) VALUES(?,?)";
            PreparedStatement pstm = this.getConexion().prepareStatement(sql);
            
            pstm.setString(1, this.idUsuario);
            pstm.setDate(2, new java.sql.Date(this.fechaLecutra.getTime()));
            
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    private void agregarLibrosLector(){
        try{
            String q= "INSERT INTO lector_libro VALUES(?,?)";
            PreparedStatement pstm= this.getConexion().prepareStatement(q);
            for (String[] libro : libros) {
                pstm.setInt(1, idLector);
                pstm.setString(2, libro[0]);
                pstm.execute();
            }
            pstm.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public boolean nuevoLector(){
        boolean ok= false;
        if(this.insertarLector()){
            this.agregarLibrosLector();
            ok= true;
        }
        return ok;
    }
    
    public static ArrayList<HashMap> searchLectores(String q){
        ArrayList<HashMap> listaLectores = new ArrayList<>();
        Conexion con = new Conexion();
        try{
            PreparedStatement pstm = con.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            while(res.next()){
                HashMap map = new HashMap();
                ResultSetMetaData meta = res.getMetaData();
                for (int i = 1, j= meta.getColumnCount(); i <= j; i++) {
                    map.put(meta.getColumnLabel(i), res.getString(i));
                }
                listaLectores.add(map);
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return listaLectores;
    }
    
    public static Lector buscarLector(String idUsuario){
        Conexion con= new Conexion();
        Lector lector= null;
        try{
            Date fechaActual= new Date();
            String q= "SELECT ll.id_lector, l.id_usuario, l.fecha_lectura, ll.id_libro,"
                    + " lb.nom_libro, lb.nom_editorial, lb.grado"
                    + " FROM lector_libro ll, lector l, usuario u, libro lb"
                    + " WHERE ll.id_lector=l.id_lector AND l.id_usuario=u.id_usuario"
                    + " AND ll.id_libro=lb.id_libro AND l.id_usuario="+idUsuario
                    + " AND l.fecha_lectura='"+new java.sql.Date(fechaActual.getTime())+"'";
            PreparedStatement pstm= con.getConexion().prepareStatement(q);
            ResultSet res= pstm.executeQuery();
            ArrayList<HashMap> datos = new ArrayList();
            while(res.next()){
                HashMap map= new HashMap();
                ResultSetMetaData data= res.getMetaData();
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    map.put(data.getColumnLabel(i), res.getString(i));
                }
                datos.add(map);
            }
            res.close();
            
            if(!datos.isEmpty()){
                String idLector= datos.get(0).get("id_lector").toString();
                String fecha= datos.get(0).get("fecha_lectura").toString();
                Date fechaLectura= new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
                String [][] libros = new String[datos.size()][4];
                String [] columName= {"id_libro", "nom_libro", "nom_editorial", "grado"};
                int i= 0;
                for (HashMap libro : datos) {
                    for (int j = 0; j < 4; j++) {
                        libros[i][j]= libro.get(columName[j]).toString();
                    }
                    i++;
                }
                lector= new Lector(Integer.valueOf(idLector), idUsuario, fechaLectura, libros);
            }
        }catch(SQLException | ParseException e){
            System.out.println(e.getMessage());
        }
        return lector;
    }
    
    public static DefaultTableModel modeloLector(){
        Date fechaActual= new Date();
        String q= "SELECT l.id_lector AS Lector, u.nombre_usu AS Nombres, u.apellido_usu AS Apellidos,"
                + " u.telf1_usuario AS tlf1, u.telf2_usuario AS tlf2, u.direccion_usu AS Direccion"
                + " FROM lector l, usuario u WHERE l.id_usuario=u.id_usuario AND l.fecha_lectura='"
                + new java.sql.Date(fechaActual.getTime())+"';";
        ArrayList<HashMap> lectoresDelDia= Lector.searchLectores(q);
        String [] ColumnName= {"Lector", "Nombres", "Apellidos", "tlf1", "tlf2", "Direccion"};
        Object [][] lectores= new Object[lectoresDelDia.size()][5];
        int i= 0;
        for (HashMap lector : lectoresDelDia) {
            for (int j = 0; j <= 4 ; j++) {
                if(j==3)
                    lectores[i][j]= lector.get(ColumnName[j])+"/"+lector.get(ColumnName[j+1]);                
                else if(j==4)
                    lectores[i][j]= lector.get(ColumnName[j+1]);
                else
                    lectores[i][j]= lector.get(ColumnName[j]);
            }
            i++;
        }
        String [] ColumnModel={"Lector", "Nombres", "Apellidos", "Telefonos", "Direccion"};
        return new DefaultTableModel(lectores, ColumnModel){
            @Override
            public boolean isCellEditable(int i, int il){
                return false;
            }  
        };
    }
}
