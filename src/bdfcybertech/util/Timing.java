/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.util;
import javax.swing.Timer;
import java.awt.event.*;

/**
 *
 * @author MUSTAFA
 */
public class Timing {

    private int currentTime;
    private Timer tim;
    private boolean timeEnd;

    public Timing(int t){
        currentTime = t;
        tick();
    }

    public void continueTiming(int t){
        currentTime = t;
        //tick();
    }

    public void start(){
        tim.start();
    }

    public void stop(){
        tim.stop();
    }

    public void restart(){
        tim.restart();
    }

    private void init(){
        if(currentTime >= 1){
            currentTime = currentTime - 1;
            timeEnd = false;
        }
        else{
            timeEnd = true;
            stop();            
        }
    }

    public int getRemTime(){
        return currentTime;
    }

    public boolean isTimeEnd(){
        return timeEnd;
    }

    public boolean isRunned(){
        return tim.isRunning();
    }

    private void tick(){
        tim = new Timer(59000, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                init();               
            }
        });
    }
}
