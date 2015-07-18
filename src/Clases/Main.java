package Clases;

import Vistas.InicioPortada;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author zirex
 */
public class Main {
    public static void main(String[]args){
        InicioPortada ip= new InicioPortada();
        try{
             for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
             }
            SwingUtilities.updateComponentTreeUI(ip);
            ip.setVisible(true);
        }catch(UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex){}
    }
}
