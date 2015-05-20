package Clases;

import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author zirex
 */
public class Lector extends Conexion{
    private String idLector;
    private String idLibro;
    private String idUsuario;
    private Date fechaLecutra;

    public Lector(){}
    
    public Lector(String idLibro, String idUsuario, Date fechaLecutra) {
        this.idLibro = idLibro;
        this.idUsuario = idUsuario;
        this.fechaLecutra = fechaLecutra;
    }
    
    public boolean insertarLector(){
        try{
            String sql= "INSERT INTO lector(id_libro, id_usuario, fecha_lectura) VALUES(?,?,?)";
            PreparedStatement pstm = this.getConexion().prepareStatement(sql);
            
            pstm.setString(1, this.idLibro);
            pstm.setString(2, this.idUsuario);
            pstm.setDate(3, new java.sql.Date(this.fechaLecutra.getTime()));
            
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public HashMap lector(String nombreUsuario){
        HashMap map = new HashMap();
        try{
            String sql = "SELECT l.id_lector, lb.nombre_libro, u.nombre_usu, "
                       + "u.apellido_usu, u.fecha_nacimiento, u.grado_estudio, u.trabaja, "
                       + "l.fecha_lectura FROM lector l, libro lb, usuario u "
                       + "WHERE l.id_libro = lb.id_libro and l.id_usuario = u.id_usuario and u.nombre_usu = ?";
            PreparedStatement pstm = this.getConexion().prepareStatement(sql);
            pstm.setString(1, nombreUsuario);
            ResultSet res = pstm.executeQuery();
            while(res.next()){
                ResultSetMetaData data = res.getMetaData();
                for(int i = 1, j = data.getColumnCount(); i<= j; i++){
                    map.put(data.getColumnLabel(i).toLowerCase(), res.getString(i));
                }
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return map;
    }
    
    public ArrayList<HashMap> getUsuarios(String usuario){
        ArrayList<HashMap> usuarios = new ArrayList<>();
        String [] usu = usuario.split(" ");
        String sql= "SELECT id_usuario, nombre_usu, apellido_usu, fecha_nac_usu,"
                  + " grado_estudio, trabaja, miembro FROM usuario WHERE nombre_usu LIKE '"+usu[0]+"%' AND apellido_usu LIKE '"+usu[1]+"%'";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(sql);
            ResultSet res= pstm.executeQuery();
            while(res.next()){
                HashMap map = new HashMap();
                ResultSetMetaData data = res.getMetaData();
                for(int i= 1, j= data.getColumnCount(); i<= j; i++){
                    map.put(data.getColumnLabel(i).toLowerCase(), res.getString(i));
                }
                usuarios.add(map);
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return usuarios;
    }
}
