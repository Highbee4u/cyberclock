/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.util;
import javax.swing.*;
import java.awt.Image;

/**
 *
 * @author MUSTAFA
 */
public class IconManager {

    protected ImageIcon ico;
    protected Image img;
    
    IconManager(String f){
        ico = new ImageIcon(getClass().getResource("/bdfcybertech/gui/images/" + f ));
        img = ico.getImage();
    }

    public static Image getImage(String file){
        IconManager im = new IconManager(file);
        return im.img;
    }

    public static Icon getIcon(String file){
        IconManager im = new IconManager(file);
        return im.ico;
    }
}
