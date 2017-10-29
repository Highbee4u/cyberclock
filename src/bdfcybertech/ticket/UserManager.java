/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.ticket;

import bdfcybertech.util.Timing;
import bdfcybertech.gui.MainGUI;
import bdfcybertech.util.Workers;
import javax.swing.*;
//import java.awt.*;

/**
 *
 * @author MUSTAFA
 */
public class UserManager {

    private int currTime;
    private int ticketID;
    private String newPin;
    
    private JDialog caller;
    private Timing timer;

    private MainGUI main;
    private Ticket ticket;
    private Workers work;

    public UserManager(){
        //work = w;
        System.out.println("The empty constructor was called and object 'USER MANAGER' is initialized with worker");
    }

    public UserManager(JDialog curr, MainGUI in, Timing r, int t, int tick){
        currTime = t;
        caller = curr;
        timer = r;
        main = in;
        ticketID = tick;
        ticket = new Ticket(curr);
        work = new Workers(curr);
        //x = Toolkit.getDefaultToolkit().getScreenSize();
    }

    public void repassUserCredentials(JDialog curr, MainGUI in, Timing r, int t, int tick){
        currTime = t;
        caller = curr;
        timer = r;
        main = in;
        ticketID = tick;
        ticket = new Ticket(curr);
        work = new Workers(curr);
        //x = Toolkit.getDefaultToolkit().getScreenSize();
    }

    public void clickLogout(){
        //this line is important
        work.doClickLogout(caller, ticketID, currTime);
        timer.stop();
        main.showMe();
        //gui.setSize(x.width, x.height);
        //gui.setVisible(true);
    }

    public JDialog getCaller(){
        return caller;
    }

    public int getTicketID(){
        return ticketID;
    }

    public int getCurrentTime(){
        return currTime;
    }

    public Timing getTimer(){
        return timer;
    }

    public MainGUI getMain(){
        return main;
    }
    
    public void autoLogout(){
        ticket.autoLogout(ticketID);
        caller.dispose();
        timer.stop();
        main.showMe();
    }

    private void performSERVERlogout(){
        
    }

    public void addTime(String pin){
        newPin = pin;
    }
}
