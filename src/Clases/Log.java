package Clases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zirex
 */
public class Log extends Conexion{
    private int idRol;
    private String nomBibliotecario;
    private String usuario;
    private char[] pass;
    private int tipoUsuario;

    public Log(String nomBibliotecario, String usuario, char[] pass) {
        this.nomBibliotecario= nomBibliotecario;
        this.usuario = usuario;
        this.pass = pass;
    }

    private Log(int idRol, String nomBibliotecario, String userName, int tipoUsuario) {
        this.idRol= idRol;
        this.nomBibliotecario= nomBibliotecario;
        this.usuario= userName;
        this.tipoUsuario = tipoUsuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public String getNomBibliotecario() {
        return nomBibliotecario;
    }

    public String getUsuario() {
        return usuario;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public boolean passCorrecta(String clave) {
        try{
            String q="SELECT username FROM rol WHERE clave=md5('"+clave+"')";
            PreparedStatement pstm= this.getConexion().prepareStatement(q);
            ResultSet res= pstm.executeQuery();
            String user="";
            while(res.next()){
                user=res.getString("username");
            }
            res.close();
            if(user.equals(this.usuario)){
                return true;
            }
            else{
                return false;
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean setPass(char[] pass) {
        try{
            String clave="";
            for (char pas : pass) {
                clave+=pas;
            }
            String q= "UPDATE rol SET clave=md5('"+clave+"') WHERE id_rol="+this.idRol;
            PreparedStatement pstm= this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            this.pass = pass;
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    public boolean insertarRol(){
        try{
            String clave="";
            for (char pas : pass) {
                clave+=pas;
            }
            String q= "INSERT INTO rol(nom_bibliotecario, userName, clave) "
                    + "VALUES('"+this.nomBibliotecario+"', '"+this.usuario+"', md5('"+clave+"'))";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    public static boolean eliminarRol(String id){
        Conexion con= new Conexion();
        try{
            Statement st= con.getConexion().createStatement();
            String q="SELECT COUNT(*) AS 'resultado' FROM rol WHERE id_rol="+id;
            ResultSet res= st.executeQuery(q);
            int resultado= 0;
            while(res.next()){
                resultado= res.getInt("resultado");
            }
            res.close();
            if(resultado == 1){
                q= "DELETE FROM rol WHERE id_rol="+id;
                st.execute(q);
                st.close();
                return true;
            }
            else
                return false;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    public static DefaultTableModel getSusos(){
        Conexion con = new Conexion();
        try{
            String q= "SELECT COUNT(*) AS 'total' FROM rol WHERE id_rol != 1";
            Statement st= con.getConexion().createStatement();
            ResultSet res= st.executeQuery(q);
            int total= 0;
            while(res.next()){
                total= res.getInt("total");
            }
            ArrayList<HashMap> usuarios= new ArrayList<>();
            String [] columnName= {"Id del bibliotecario", "Nombre del bibliotecario", "User"};
            q= "SELECT id_rol AS 'Id del bibliotecario', nom_bibliotecario AS 'Nombre del bibliotecario', username AS 'User'"+
               " FROM rol WHERE id_rol!=1";
            res= st.executeQuery(q);
            while(res.next()){
                HashMap usuario= new HashMap();
                ResultSetMetaData data= res.getMetaData();
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    usuario.put(data.getColumnLabel(i), res.getString(i));
                }
                usuarios.add(usuario);
            }
            res.close();

            String [][] datos= new String[usuarios.size()][columnName.length];
            int aux= 0;
            for (HashMap map : usuarios) {
                for (int i = 0; i < datos[aux].length; i++) {
                    datos[aux][i]= map.get(columnName[i]).toString();
                }
                aux++;
            }
            return new DefaultTableModel(datos, columnName){
                @Override
                public boolean isCellEditable(int i, int il){
                    return false;
                }
            };
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
    
    public static Log existe(String username){
        Conexion con = new Conexion();
        try{String q= "SELECT id_rol, nom_bibliotecario, username, tipo_rol FROM rol WHERE username='"+username+"'";
            Statement st= con.getConexion().createStatement();
            ResultSet res= st.executeQuery(q);
            HashMap user= new HashMap();
            while(res.next()){
                ResultSetMetaData data= res.getMetaData();
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    user.put(data.getColumnLabel(i), res.getString(i));
                }
            }
            res.close();
            if(!user.isEmpty()){
                return new Log(Integer.parseInt(user.get("id_rol").toString()),
                               user.get("nom_bibliotecario").toString(),
                               user.get("username").toString(),
                               Integer.parseInt(user.get("tipo_rol").toString()));
            }
            else{
                return null;
            }
        }catch(SQLException | NumberFormatException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
