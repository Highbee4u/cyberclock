/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech;
import bdfcybertech.gui.MainGUI;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author MUSTAFA
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private Dimension x;
    private MainGUI gui;
    public Main(){
        x = Toolkit.getDefaultToolkit().getScreenSize();        
        gui = new MainGUI();
        gui.setSize(x.width, x.height);
        gui.showMe();
    }
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new Main();
            }
        });
    }

}
