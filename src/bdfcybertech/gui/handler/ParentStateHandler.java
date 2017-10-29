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
public class ParentStateHandler {

    private JDialog parent;
    private JDialog me;
    private Timer t;

    public ParentStateHandler(JDialog par, JDialog in){
        parent = par;
        me = in;
    }

    private void disableParent(){
        parent.setEnabled(false);
    }

    private void enableParent(){
        parent.setEnabled(true);
    }

    private void parentStateChanger(){
        if(me.isActive()){
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
