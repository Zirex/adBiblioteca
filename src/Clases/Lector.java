package Clases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zirex
 */
public class Lector extends Conexion{
    protected int idLector;
    protected String idUsuario;
    protected String devuelto;
    protected Date fechaLecutra;
    protected String [][] libros;
    
    private Lector(int idLector, String idUsuario, Date fechaLecutra, String [][] libros, String devuelto){
        this.idLector= idLector;
        this.idUsuario= idUsuario;
        this.fechaLecutra= fechaLecutra;
        this.libros= libros;
        this.devuelto=devuelto;
    }
    
    public Lector(String idUsuario, Date fechaLecutra, String [][] libros) {
        this.idLector= this.setIdLector();
        this.idUsuario = idUsuario;
        this.fechaLecutra = fechaLecutra;
        this.libros= libros;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public int getIdLector() {
        return idLector;
    }

    public Date getFechaLecutra() {
        return fechaLecutra;
    }

    public String getDevuelto() {
        return devuelto;
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
                    String sql="UPDATE libro SET en_servicio= en_servicio-1 WHERE id_libro="+libro[0];
                    st.execute(sql);
                }
                else if( (i+1) < libros.length ){
                    i++;                    
                }
                
            }
            for ( ; i <= libros.length-1; i++) {
                String[] libro= libros[i];
                String sql= "INSERT INTO lector_libro(id_lector, id_libro) VALUES("+this.idLector+", "+libro[0]+")";
                st.execute(sql);
                sql= "UPDATE libro SET en_servicio= en_servicio+1 WHERE id_libro="+libro[0];
                st.execute(sql);
            }
            st.close();
            ok=true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return ok;
    }
    
    protected boolean insertarLector(){
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
            String q= "INSERT INTO lector_libro(id_lector, id_libro) VALUES(?,?)";
            String q1= "UPDATE libro SET en_servicio= en_servicio+1 WHERE id_libro=?";
            PreparedStatement pstm= this.getConexion().prepareStatement(q),
                              pstm1= this.getConexion().prepareStatement(q1);
            for (String[] libro : libros) {
                pstm.setInt(1, idLector);
                pstm.setString(2, libro[0]);
                pstm.execute();
                
                pstm1.setString(1, libro[0]);
                pstm1.execute();
            }
            pstm.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public boolean devolverLector(){
        boolean ok= false;
        try{
            String q= "UPDATE libro SET en_servicio= en_servicio-1 WHERE id_libro=?";
            String q1= "UPDATE lector SET devuelto=1 WHERE id_lector="+this.idLector;
            PreparedStatement pstm= this.getConexion().prepareStatement(q),
                              pstm1= this.getConexion().prepareStatement(q1);
            for (String[] libro : libros) {
                pstm.setString(1, libro[0]);
                pstm.execute();
            }
            pstm.close();
            pstm1.execute();
            pstm1.close();
            ok= true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return ok;
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
            String q="SELECT * FROM lector WHERE id_usuario="+idUsuario
                    +" AND fecha_lectura='"+new java.sql.Date(fechaActual.getTime())+"'";
            Statement st= con.getConexion().createStatement();
            ResultSet res= st.executeQuery(q);
            HashMap map= new HashMap();
            while(res.next()){
                ResultSetMetaData data= res.getMetaData();
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    map.put(data.getColumnLabel(i), res.getString(i));
                }
            }
            if(!map.isEmpty()){
                q= "SELECT ll.id_libro, l.nom_libro, l.nom_editorial, l.grado "
                 + "FROM lector_libro ll, libro l WHERE ll.id_libro= l.id_libro"
                 + " AND ll.id_lector="+map.get("id_lector").toString();
                res= st.executeQuery(q);
                ArrayList<HashMap> datos= new ArrayList();
                while(res.next()){
                    HashMap libro= new HashMap();
                    ResultSetMetaData data= res.getMetaData();
                    for (int i = 1; i <= data.getColumnCount(); i++) {
                        libro.put(data.getColumnLabel(i), res.getString(i));
                    }
                    datos.add(libro);
                }
                res.close();
                
                String idLector= map.get("id_lector").toString();
                String fecha= map.get("fecha_lectura").toString();
                String activo= map.get("devuelto").toString();
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
                lector= new Lector(Integer.valueOf(idLector), idUsuario, fechaLectura, libros, activo);
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
                    lectores[i][j]= lector.get(ColumnName[j])+" / "+lector.get(ColumnName[j+1]);                
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
    
    public static String libroEnServicio(String idLibro){
        Conexion con= new Conexion();
        String msj= null;
        try{
            Statement st= con.getConexion().createStatement();
            String q= "SELECT ejemplares-en_servicio AS 'disponibilidad' FROM libro WHERE id_libro="+idLibro;
            ResultSet res= st.executeQuery(q);
            int resultado=0;
            while(res.next()){
                resultado= res.getInt(1);
            }
            res.close();
            if(resultado==0){
                msj="El libro no cuenta con ejemplares disponibles para usar";
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return msj;
    }
    
    public static int[] visitantes(){
        Conexion con= new Conexion();
        int[] valor= new int[12];
        Calendar fecha= Calendar.getInstance();
        try{
            String q= "SELECT COUNT(*) AS visitantes FROM lector WHERE YEAR(fecha_lectura) =? "
                    + "AND MONTH(fecha_lectura) =?;";
            PreparedStatement pstm= con.getConexion().prepareStatement(q);
            ResultSet res = null;
            int mes= fecha.get(Calendar.MONTH)+2;
            int año= fecha.get(Calendar.YEAR)-1;
            int i= 0;
            while(mes <= 12){
                pstm.setInt(1, año);
                pstm.setInt(2, mes);
                res= pstm.executeQuery();
                while(res.next()){
                    valor[i]= res.getInt("visitantes");
                }
                mes++;
                i++;
            }
            for (int j = 1; j <= fecha.get(Calendar.MONTH)+1; j++) {
                pstm.setInt(1, fecha.get(Calendar.YEAR));
                pstm.setInt(2, j);
                res= pstm.executeQuery();
                while(res.next()){
                    valor[i]=res.getInt("visitantes");
                }
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return valor;
    }
}
