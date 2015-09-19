/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author HOGAR
 */
public class GestionBD {
    private int BUFFER = 10485760;  
    //para guardar en memmoria
    private StringBuffer temp = null;
    //para guardar el archivo SQL
    private FileWriter  fichero = null;
    private PrintWriter pw = null;
    
    private String host= "localhost";
    private String port= "3306";
    private String user= "root";
    private String password= "0000";
    private String db="adBiblioteca";
    
 public boolean CrearBackup(String file_backup){
    boolean ok=false;
    try{       
        //sentencia para crear el BackUp
         Process run = Runtime.getRuntime().exec(
        "C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysqldump --host=" + this.host + " --port=" + this.port +
        " --user=" + this.user + " --password=" + this.password +
        " --compact --complete-insert --extended-insert --skip-quote-names" +
        " --skip-comments --skip-triggers " + this.db);
        //se guarda en memoria el backup
        InputStream in = run.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        temp = new StringBuffer();
        int count;
        char[] cbuf = new char[BUFFER];
        while ((count = br.read(cbuf, 0, BUFFER)) != -1)
            temp.append(cbuf, 0, count);
        br.close();
        in.close();        
        /* se crea y escribe el archivo SQL */
        fichero = new FileWriter(file_backup);
        pw = new PrintWriter(fichero);                                                    
        pw.println(temp.toString());  
        ok=true;
   }
    catch (Exception ex){
            ex.printStackTrace();
    } finally {
       try {           
         if (null != fichero)
              fichero.close();
       } catch (Exception e2) {
           e2.printStackTrace();
       }
    }   
    return ok; 
 }
 
 public boolean restaurarBackup(String respaldo){
     boolean ok= false;
     try{
         Process run= Runtime.getRuntime().exec("C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysql"
                 + " --host="+this.host+" --port="+this.port+" --user="+this.user+" --password="+this.password+" "+this.db
                 + " < "+respaldo);
         ok=true;
     }catch(Exception e){
         System.err.println(e.getMessage());
     }
     return ok;
 }
 }  
