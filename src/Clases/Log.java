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

    private Log(int idRol, String nomBibliotecario, String userName, char[]pass, int tipoUsuario) {
        this.idRol= idRol;
        this.nomBibliotecario= nomBibliotecario;
        this.usuario= userName;
        this.pass= pass;
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
            String q= "INSERT INTO rol(nom_bibliotecario, userName, clave) VALUES(?,?,?)";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            String clave="";
            for (char pas : pass) {
                clave+=pas;
            }
            pstm.setString(1, this.nomBibliotecario);
            pstm.setString(2, this.usuario);
            pstm.setString(3, "md5("+clave+")");
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    public boolean eliminarRol(String id){
        try{
            Statement st= this.getConexion().createStatement();
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
    
    public DefaultTableModel getLogin(){
        try{
            String q= "SELECT COUNT(*) AS 'total' FROM rol WHERE id_rol != 1";
            Statement st= this.getConexion().createStatement();
            ResultSet res= st.executeQuery(q);
            int total= 0;
            while(res.next()){
                total= res.getInt("total");
            }
            if(total>0){
                ArrayList<HashMap> usuarios= new ArrayList<>();
                String [] columnName= new String[4];
                q= "SELECT id_rol AS 'Id del bibliotecario', nom_bibliotecario AS 'Nombre del bibliotecario', username AS 'User', md5(clave) AS 'Clave'"+
                   " FROM rol WHERE id_rol!=1";
                res= st.executeQuery(q);
                while(res.next()){
                    HashMap usuario= new HashMap();
                    ResultSetMetaData data= res.getMetaData();
                    for (int i = 1; i <= data.getColumnCount(); i++) {
                        usuario.put(data.getColumnLabel(i), res.getString(i));
                        columnName[i-1]= data.getColumnLabel(i);
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
            }
            else{
                return null;
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
    
    public static Log existe(String username, char[]pass, int tipo){
        Conexion con = new Conexion();
        try{
            String clave="";
            for (char pas : pass) {
                clave+=pas;
            }
            String q= "SELECT * FROM rol WHERE username='"+username+"' AND clave= md5('"+clave+"') AND tipo_rol="+tipo;
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
                               user.get("clave").toString().toCharArray(),
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
