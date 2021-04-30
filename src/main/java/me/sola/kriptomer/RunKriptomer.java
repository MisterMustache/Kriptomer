package me.sola.kriptomer;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Tilen Levak
 */
public class RunKriptomer {
    
    static boolean dev = false;
    
    public static void main(Dimension d, Point p) throws InterruptedException, IOException{
    
        System.out.println("STARTING PreOkno");
        
        //d = new java.awt.Dimension(1080, 1750);
        
        dev = true;
        
        startKriptomer(d, p);
        
    }
    
    public static void main(String args[]) throws IOException, InterruptedException{
        
        System.out.println("STARTING PreOkno");
        
        Dimension d = new java.awt.Dimension(1080, 750);
        Point p = new java.awt.Point(10, 10);
        
        startKriptomer(d, p);
        
    }
    
    public static void startKriptomer(Dimension d, Point p) throws InterruptedException, IOException{
        
        // Novo okno, ki nima okvirja
        JFrame frame = new JFrame("TitleLessJFrame");
        
        Image ikona = ImageIO.read(RunKriptomer.class.getResource("resources/icons/ikona0.png"));
        frame.setIconImage(ikona);
        
        // Slika okna
        //BufferedImage splash = ImageIO.read(new File("resources/splash.png"));
        BufferedImage splash = ImageIO.read(RunKriptomer.class.getResourceAsStream("resources/splash.png"));
        JLabel background = new JLabel(new ImageIcon(splash));
        
        // Okno ob zaprtju ne bo prekinilo programa, dodamo sliko, brez dekorja, velikost, na sredino monitorja
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add(background);
        frame.setUndecorated(true);
        frame.setSize(500, 375);
        frame.setLocationRelativeTo(null);
        
        // Prikaži okno
        frame.setVisible(true);
        
        
        
        // Čakaj 2 sekundi (pod normalnimi pogoji) nato zapri
        if (!dev){
            Thread.sleep(2000);
        }
        frame.dispose();
        System.out.println("> disposed\n- - - - - - - - - -");
        
        // Odpri glavno okno
        MainWindow.main(d, p);
        
    }
}
