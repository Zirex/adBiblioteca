/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import java.awt.Color;

/**
 *
 * @author Kraken
 */
public abstract class Interfaz extends javax.swing.JInternalFrame {
        
    public static void soloNum(java.awt.event.KeyEvent evt) {
        char caracter = evt.getKeyChar();
        if(((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
            evt.consume();  // ignorar el evento de teclado
        }
    }
    
    public static void soloABC(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (!(Character.isLetter(c) || c == ' ' || c == 8)) {
            evt.consume();
        }
    }
    
    public static void showError(javax.swing.JTextField txt, String msj) {
        txt.setBorder(javax.swing.BorderFactory.createEtchedBorder(Color.lightGray, Color.red));
        txt.setToolTipText(msj);
    }
    
    public static void showError(javax.swing.JComboBox cmb, String msj) {
        cmb.setBackground(Color.red);
        cmb.setToolTipText(msj);
    }
    
    public static void showError(javax.swing.JTextArea txt, String msj) {
        txt.setBackground(Color.red);
        txt.setToolTipText(msj);
    }
    
    public static void showError(com.toedter.calendar.JDateChooser dateChooser, String msj){
        dateChooser.setBorder(javax.swing.BorderFactory.createLineBorder(Color.red));
        dateChooser.setToolTipText(msj);
    }
    
    public static void normalizeInput(javax.swing.JTextField txt) {
        txt.setBorder(new javax.swing.JTextField().getBorder());
        txt.setToolTipText(null);
    }
    
    public static void normalizeInput(javax.swing.JComboBox cmb) {
        cmb.setBackground(javax.swing.UIManager.getColor("comboBox.Background"));
        cmb.setToolTipText(null);
    }
    
    public static void normalizeInput(javax.swing.JTextArea txt) {
        txt.setBorder(javax.swing.UIManager.getBorder("textArea.Border"));
        txt.setToolTipText(null);
    }
    
    public static void normalizeInput(com.toedter.calendar.JDateChooser dateChooser){
        dateChooser.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        dateChooser.setToolTipText(null);
    }
    
    public static boolean isPhone (String tel) {
        try {
            int tam = tel.length();
            Long.parseLong(tel);
            return (tam>6 && tam<13);
        } catch (NumberFormatException e) {
            return false;
        }
    }    
}
