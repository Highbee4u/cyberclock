/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.gui.handler;
import java.awt.event.*;
/**
 *
 * @author MUSTAFA
 */
public class KeyDisabler implements KeyListener{


    public KeyDisabler(){
        
    }

    public void keyReleased(KeyEvent e){

    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_WINDOWS){

        }
    }
    public void keyTyped(KeyEvent e){

    }

    private void dis(){
    }

    public static void main(String []args){
        new KeyDisabler().dis();
    }

}
