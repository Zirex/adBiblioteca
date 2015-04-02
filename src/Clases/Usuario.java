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
        {
            FileInputStream fis = null;
            try{
                //Variables para guardar una foto en la base de datos en formato blob
                File archivo = new File(this.fotoString);
                fis = new FileInputStream(archivo);

                String sql= "INSERT INTO usuario(ced_usuario, nombre_usu, apellido_usu,"
                        + "sexo, fecha_nac_usu, telf1_usuario, telf2_usuario, direccion_usu,"
                        + "estudia, miembro, nombre_inst, representante, foto_usu, fecha_expedicion)"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
                PreparedStatement pstm= this.getConexion().prepareStatement(sql);
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
                pstm.setBinaryStream(13, fis,(int) archivo.length());
                pstm.setDate(14, new java.sql.Date(this.fechaExpedicion.getTime()));
                
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
                    fis.close();
                }catch(IOException ex){
                    System.out.println(ex.getMessage());
                    return false;
                }
            }
        }
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
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyy", new Locale("es_ES"));
        try{
            String sql = "SELECT * FROM usuario WHERE id_usuario = ?;";
            PreparedStatement pstm= this.getConexion().prepareStatement(sql);
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            HashMap map= new HashMap();
            while(res.next()){
                ResultSetMetaData data = res.getMetaData();
                for (int i = 1, j=  data.getColumnCount(); i <=j; i++) {
                    if(i == 14){
                        //se lee la cadena de bytes de la base de datos
                        byte[] b= res.getBytes(i);
                        // esta cadena de bytes sera convertida en una imagen
                        this.foto = this.ConvertirImagen(b);
                    }
                    map.put(data.getColumnLabel(i).toLowerCase(), res.getString(i));
                }
                
                usuario = new Usuario(map.get("id_usuario")+"", map.get("ced_usuario")+"",
                                      map.get("nombre_usu")+"", map.get("apellido_usu")+"",
                                      map.get("sexo")+"", formateador.parse(map.get("fecha_nac_usu").toString()),
                                      map.get("telf1_usuario")+"", map.get("telf2_usuario")+"",
                                      map.get("direccion_usu")+"", map.get("estudia")+"",
                                      map.get("miembro")+"", map.get("nombre_inst")+"",
                                      map.get("representante")+"", this.foto,
                                      formateador.parse(map.get("fecha_expedicion").toString()));    
                
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
    
    public Object[][] usuarios(String q){
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
        Object[][] data = new Object[listaUsuarios.size()][6];
        int i=0;
        for (HashMap usuario : listaUsuarios) {
            String [] linea = {usuario.get("id_usuario").toString(), usuario.get("apellido_usu").toString(), 
                               usuario.get("nombre_usu").toString(), usuario.get("direccion_usu").toString(),
                               usuario.get("telf1_usuario").toString()+" / "+ usuario.get("telf2_usuario").toString(),
                               ""};
            for (int j = 0; j < data[i].length; j++) {
                if(j==5){
                        data[i][j]= new JButton();
                    }
                    else{
                        data[i][j]= linea[j];
                    }
            }
            i++;
        }
        return data;
    }
    
    public static AbstractTableModel ultimosUsuarios(){
        Usuario usuario = new Usuario();
        AbstractTableModel model = new AbstractTableModel(){
            private String q= "SELECT id_usuario, apellido_usu, nombre_usu, direccion_usu, telf1_usuario, telf2_usuario"
                            + " FROM usuario ORDER BY id_usuario DESC LIMIT 5;";
            private Object[][] datos = usuario.usuarios(q);
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
}
