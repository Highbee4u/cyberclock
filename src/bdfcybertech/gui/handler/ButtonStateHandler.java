/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.gui.handler;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author MUSTAFA
 */
public class ButtonStateHandler{

    private JDialog me;
    private JDialog parent;
    private Timer t;

    public ButtonStateHandler(){
        
    }
    
    public ButtonStateHandler(JDialog m, JDialog n){
        parent = m;
        me = n;
    }

    private void disableParent(){
        parent.dispose();
    }

    private void enableParent(){
        parent.setVisible(true);
    }

    private void parentStateChanger(){
        if(me.isVisible()){
            disableParent();
        }
        else{
            t.stop();
            enableParent();
        }
    }

    public void doStateHandler(){
        ActionListener job = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                parentStateChanger();
            }
        };
        t = new Timer(300, job);
    }

    public void startCheck(){
        t.start();
    }
    
}
