/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.sola.kriptomer;

import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.simple.parser.ParseException;

/**
 *
 * @author TheGoodSpice
 */
public class usodnaNapaka {
    
    //MainWindow mainWindow;
    
    public usodnaNapaka(Exception e) throws ParseException {
        //this.mainWindow = (MainWindow) mainWindow;
        
        String[] options = new String[]{"Ponovni zagon", "Izbriši konfiguracijsko datoteko", "Zapri program"};
        int response = JOptionPane.showOptionDialog(null, "Opa! Nekaj je šlo hudo narobe! Stabilnost seje se je porušila. "
                + "Kriptomer se bo interno znova zagnal! V primeru persistente napake ročno znova zaženite aplikacijo "
                + "oz. zavrzite konfiguracijsko datoteko.\n\n" + e.getClass() + "\n" + e.getMessage(), "USODNA NAPAKA",
                JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options, options[0]);

//        JOptionPane optionPane = new JOptionPane("Opa! Nekaj je šlo hudo narobe! Stabilnost seje se je porušila. "
//                + "Kriptomer se bo interno znova zagnal! V primeru persistente napake ročno znova zaženite aplikacijo "
//                + "oz. zavrzite konfiguracijsko datoteko.\n\n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
//        JDialog dialog = optionPane.createDialog("USODNA NAPAKA");
//        dialog.setAlwaysOnTop(true);
//        dialog.setVisible(true);

        //System.out.println(e.getStackTrace().toString());

        switch (response) {
            case 0:
                System.out.println("restart");
                break;
            case 1:
                System.out.println("del");
                break;
            case 2:
                System.out.println("zapri");
                break;
            case -1:
                System.out.println("zapri 2");
                break;
            default:
                System.out.println("default");
        }

        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, e);

        Dimension d = new java.awt.Dimension(1080, 750);
        Point p = new java.awt.Point(10, 10);

        MainWindow ma;
        try {
            ma = new MainWindow(d, p);
            ma.restartKriptomer(false);
        } catch (IOException | InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(AddProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
