package Clases;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author zirex
 */
public class Usuario extends Conexion{
    private String idUsuario;
    private String cedUsuario;
    private String nombre;
    private String apellido;
    private String sexo;
    private Date fechaNacimiento;
    private String tlf1;
    private String tlf2;
    private String direccion;
    private String estudia;
    private String miembro;
    private String nombreInstitucion;
    private String nombreRepresentante;
    private String fotoString;
    private Date fechaExpedicion;
    private Image foto;

    public Usuario(){}
    
    public Usuario(String cedUsuario, String nombre, String apellido, String sexo, Date fechaNacimiento, String tlf1, String tlf2, String direccion, String estudia, String miembro, String nombreInstitucion, String nombreRepresentante, String foto, Date fechaExpedicion) {
        this.cedUsuario = cedUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.tlf1 = tlf1;
        this.tlf2 = tlf2;
        this.direccion = direccion;
        this.estudia = estudia;
        this.miembro = miembro;
        this.nombreInstitucion = nombreInstitucion;
        this.nombreRepresentante = nombreRepresentante;
        this.fotoString = foto;
        this.fechaExpedicion = fechaExpedicion;
    }

    private Usuario(String idUsuario, String cedUsuario, String nombre, String apellido, String sexo, Date fechaNacimiento, String tlf1, String tlf2, String direccion, String estudia, String miembro, String nombreInstitucion, String nombreRepresentante, Image foto, Date fechaExpedicion) {
        this.idUsuario = idUsuario;
        this.cedUsuario = cedUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.tlf1 = tlf1;
        this.tlf2 = tlf2;
        this.direccion = direccion;
        this.estudia = estudia;
        this.miembro = miembro;
        this.nombreInstitucion = nombreInstitucion;
        this.nombreRepresentante = nombreRepresentante;
        this.foto = foto;
        this.fechaExpedicion = fechaExpedicion;
    }
    
    public boolean insertarUsuario(){
        FileInputStream fis= null;
        File archivo = null;
        String sql = "";
        try{
            PreparedStatement pstm;
            
            if(this.fotoString != null){
                //Variables para guardar una foto en la base de datos en formato blob
                archivo = new File(this.fotoString);
                fis = new FileInputStream(archivo);                
                
                sql= "INSERT INTO usuario(ced_usuario, nombre_usu, apellido_usu, "
                   + "sexo, fecha_nac_usu, telf1_usuario, telf2_usuario, direccion_usu, "
                   + "estudia, miembro, nombre_inst, representante, fecha_expedicion, foto_usu) "
                   + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
                
                pstm= this.getConexion().prepareStatement(sql);
                pstm.setDate(13, new java.sql.Date(this.fechaExpedicion.getTime()));
                pstm.setBinaryStream(14, fis,(int) archivo.length());
            }
            else{
                sql= "INSERT INTO usuario(ced_usuario, nombre_usu, apellido_usu, "
                   + "sexo, fecha_nac_usu, telf1_usuario, telf2_usuario, direccion_usu, "
                   + "estudia, miembro, nombre_inst, representante) "
                   + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
                
                pstm= this.getConexion().prepareStatement(sql);
            }
            
            pstm.setString(1, this.cedUsuario);
            pstm.setString(2, this.nombre);
            pstm.setString(3, this.apellido);
            pstm.setString(4, this.sexo);
            pstm.setDate(5, new java.sql.Date(this.fechaNacimiento.getTime()));
            pstm.setString(6, this.tlf1);
            pstm.setString(7, this.tlf2);
            pstm.setString(8, this.direccion);
            pstm.setString(9, this.estudia);
            pstm.setString(10, this.miembro);
            pstm.setString(11, this.nombreInstitucion);
            pstm.setString(12, this.nombreRepresentante);

            pstm.execute();
            pstm.close();
            return true;
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
            return false;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }finally{
            try{
                if(fis != null){
                    fis.close();
                }
            }catch(IOException ex){
                System.out.println(ex.getMessage());
                return false;
            }
        }
        
    }
    
    // Metodos set para actualizar los datos de un usuario registrado
    private void updateCampo(String nombreCampo, String dato, Date fecha){
        try{
            String q = "UPDATE usuario SET "+nombreCampo+"=? WHERE id_usuario =?;";
            PreparedStatement pstm= this.getConexion().prepareStatement(q);
            if(!dato.equals(""))
                pstm.setString(1, dato);
            else
                if(fecha == null)
                    pstm.setDate(1, null);
                else
                    pstm.setDate(1, new java.sql.Date(fecha.getTime()));                    
            pstm.setString(2, this.idUsuario);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void setCedUsuario(String cedUsuario) {
        this.cedUsuario = cedUsuario;
        this.updateCampo("ced_usuario", this.cedUsuario, null);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        this.updateCampo("nombre_usu", this.nombre, null);
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
        this.updateCampo("apellido_usu", this.apellido, null);
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
        this.updateCampo("sexo", this.sexo, null);
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        this.updateCampo("fecha_nac_usu", "", this.fechaNacimiento);
    }

    public void setTlf1(String tlf1) {
        this.tlf1 = tlf1;
        this.updateCampo("telf1_usuario", this.tlf1, null);
    }

    public void setTlf2(String tlf2) {
        this.tlf2 = tlf2;
        this.updateCampo("telf2_usuario", this.tlf2, null);
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
        this.updateCampo("direccion_usu", this.direccion, null);
    }

    public void setEstudia(String estudia) {
        this.estudia = estudia;
        this.updateCampo("estudia", this.estudia, null);
    }

    public void setMiembro(String miembro) {
        this.miembro = miembro;
        this.updateCampo("miembro", this.miembro, null);
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
        this.updateCampo("nombre_inst", this.nombreInstitucion, null);
    }

    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
        this.updateCampo("representante", this.nombreRepresentante, null);
    }

    public void setFotoString(String fotoString) {
        this.fotoString = fotoString;
        FileInputStream fis = null;
        try{
            File file= new File(this.fotoString);
            fis = new FileInputStream(file);
            
            String q= "UPDATE usuario SET foto_usu=? WHERE id_usuario=?";
            PreparedStatement pstm= this.getConexion().prepareStatement(q);
            pstm.setBinaryStream(1, fis, (int)file.length());
            pstm.setString(2, this.idUsuario);
            pstm.execute();
            pstm.close();
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(fis != null){
                    fis.close();
                }
            }catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
        this.updateCampo("fecha_expedicion", "", this.fechaExpedicion);
    }
    
    //metodo que dada una cadena de bytes la convierte en una imagen con extension png
    private Image ConvertirImagen(byte[] bytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator readers = ImageIO.getImageReadersByFormatName("png");
        ImageReader reader = (ImageReader) readers.next();
        Object source = bis; // File or InputStream
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        return reader.read(0, param);
    } 
    
    public Usuario getUsuario(String id){
        Usuario usuario = null;
        Image foto= null;
        Date fechaExpedicion= null; 
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd", new Locale("es_ES"));
        try{
            String sql = "SELECT * FROM usuario WHERE id_usuario = ?;";
            PreparedStatement pstm= this.getConexion().prepareStatement(sql);
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            HashMap map= new HashMap();
            while(res.next()){
                ResultSetMetaData datos = res.getMetaData();
                for (int i = 1, j=  datos.getColumnCount(); i <=j; i++) {
                    if(i == 14 && res.getBytes(i) != null){
                        //se lee la cadena de bytes de la base de datos
                        byte[] b= res.getBytes(i);
                        // esta cadena de bytes sera convertida en una imagen
                        foto = this.ConvertirImagen(b);
                    }
                    map.put(datos.getColumnLabel(i).toLowerCase(), res.getString(i));
                }               
                
                if(map.get("fecha_expedicion") != null){
                    fechaExpedicion = formateador.parse(map.get("fecha_expedicion").toString());
                }
                
                usuario = new Usuario(map.get("id_usuario")+"", map.get("ced_usuario")+"",
                                      map.get("nombre_usu")+"", map.get("apellido_usu")+"",
                                      map.get("sexo")+"", formateador.parse(map.get("fecha_nac_usu").toString()),
                                      map.get("telf1_usuario")+"", map.get("telf2_usuario")+"",
                                      map.get("direccion_usu")+"", map.get("estudia")+"",
                                      map.get("miembro")+"", map.get("nombre_inst")+"",
                                      map.get("representante")+"", foto,
                                      fechaExpedicion);    
                
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }catch(ParseException e){
            System.out.println(e.getMessage());
        }catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return usuario;
    }
    
    public ArrayList<HashMap> usuarios(String q){
        ArrayList<HashMap> listaUsuarios = new ArrayList<>();
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            while(res.next()){
                HashMap map = new HashMap();
                ResultSetMetaData meta = res.getMetaData();
                for (int i = 1, j= meta.getColumnCount(); i <= j; i++) {
                    map.put(meta.getColumnLabel(i).toLowerCase(), res.getString(i));
                }
                listaUsuarios.add(map);
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return listaUsuarios;
    }
    
    public static AbstractTableModel ultimosUsuarios(){
        final Usuario usuario = new Usuario();
        AbstractTableModel model = new AbstractTableModel(){
            private String q= "SELECT id_usuario, apellido_usu, nombre_usu, direccion_usu, telf1_usuario, telf2_usuario"
                            + " FROM usuario ORDER BY id_usuario DESC LIMIT 5;";
            private ArrayList<HashMap> lista = usuario.usuarios(q);
            private Object [][] datos = usuarios();
            
            //Metodo para cargar los usuarios en una matriz objecto
            private Object [][] usuarios(){
                Object [][] datos = new Object[lista.size()][6];
                int i=0;
                for (HashMap usu : lista) {
                    String [] linea = {usu.get("id_usuario").toString(), usu.get("apellido_usu").toString(), 
                                       usu.get("nombre_usu").toString(), usu.get("direccion_usu").toString(),
                                       usu.get("telf1_usuario").toString()+" / "+ usu.get("telf2_usuario").toString(),
                                       ""};
                    for (int j = 0; j < datos[i].length; j++) {
                        if(j==5){
                                datos[i][j]= new JButton();
                            }
                            else{
                                datos[i][j]= linea[j];
                            }
                    }
                    i++;
                }
                return datos;
            }
            
            private String[] nameColumn = {"id", "Apellidos", "Nombres", "dirección", "Telefonos",
                                            "Actualizar"};
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
                if(columnIndex==5){
                    return true;
                }
                else{
                    return false;
                }
            }
        };
        
    return model;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getCedUsuario() {
        return cedUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getTlf1() {
        return tlf1;
    }

    public String getTlf2() {
        return tlf2;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEstudia() {
        return estudia;
    }

    public String getMiembro() {
        return miembro;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    public Image getFoto() {
        return foto;
    }
  
}
