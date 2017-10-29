/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.gui;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UIManager;
import java.awt.Color;

/**
 *
 * @author MUSTAFA
 */
public class MyLookAndFeel {

    public MyLookAndFeel(){

    }

    public static void setLook(){
        try{
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch(Exception e){
           System.err.println(e.getMessage());
        }
    }

}
