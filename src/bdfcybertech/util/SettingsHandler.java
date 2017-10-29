/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.util;
import bdfcybertech.gui.SysConfigDialog;
import javax.swing.*;

/**
 *
 * @author MUSTAFA
 */
public class SettingsHandler {
    
    private static final String KEY = "BDF DOT NET ABIDEMI AKANDE()";
    private String val;
    private boolean valid;
    private JDialog parent;

    public SettingsHandler(JDialog d, String in){
        val = in;
        parent = d;
        init();
    }

    private void checkKey(){
        if(val.equals(KEY)){
            valid = true;
        }
        else{
            valid = false;
        }
    }

    private void init(){
        checkKey();
        if(valid){
            //then show settings
            new SysConfigDialog(parent);
        }
        else{
             JOptionPane.showMessageDialog(parent, "Invalid PRODUCT KEY | Contact Service",
                     "License Key Error", JOptionPane.PLAIN_MESSAGE);
        }

    }

}
