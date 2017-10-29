/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.network;
import rmiLIB.*;
import bdfcybertech.gui.MainGUI;
import bdfcybertech.ticket.UserManager;
import bdfcybertech.util.*;
import java.rmi.*;
import javax.swing.*;
import java.awt.event.*;
import java.rmi.registry.*;
import java.net.MalformedURLException;
/**
 *
 * @author MUSTAFA
 */
public class HandleRMI extends ServerRMIimpl{

    private Workers work;
    private MainGUI parent;
    private UserManager manage;
    private JDialog currentD; //the current dialog
    private int tickID;
    private int currTime;
    private Timing time;

    //the threads
    private Thread logoutTh;
    private Thread loginTh;
    private ServerRMI rm;
    private Timer t;

    private int inOp = 0; //this option is 0 for do nothin, 1 for logout, 2 for login 3 for alert

    public HandleRMI() throws RemoteException{
        super();
    }

    public HandleRMI(MainGUI p, Workers timerWork) throws RemoteException{
        super();
        //pass in timer workers to work on it
        manage = new UserManager();
        inOp = 2;
        work = timerWork;
        parent = p;
        //currentD = cur;
        //the above constructor is for remote login
        init();
    }

    public void reHandleRMI(MainGUI p, JDialog cur, Timing ti, int t, int tick, UserManager u) throws RemoteException{
        //pass in timer workers to work on it
        //work = timerWork;
        inOp = 1;
        time = ti;
        parent = p;
        currentD = cur;
        currTime = t;
        tickID = tick;
        manage = u;

        //start getting those values from user manager
        processNecessary();
        
        init();
        System.out.println("Server is still Alive");
        try{
            //rmiRegistry.rebind(serverObject, rm);
            //Naming.rebind(serverObject, rm);
            String[] x = Naming.list("rmi://" + host + ":1099/");
            System.out.println(x[0]);
        }
        catch(MalformedURLException m){
            System.err.println("Error at getting list : " + m.getMessage());
        }
        //this is for remote logout
    }

    private void initManager(){
        //manage = new UserManager(currentD, parent, time, currTime, tickID);
        System.out.println("User Manager is initialized here");
    }

    @Override
    protected void init(){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                switch(inOp){
                    case 1:
                        initManager();
                        break;
                    case 2:
                        //do something
                        break;
                    case 3:
                        //do something
                        break;
                    default:
                        //show error that its a bad request
                }//end of switch
            }
        });

    }

    @Override
    public int remoteLogout(){
        logoutTh = new Thread(new Runnable(){
            public void run(){
                manage.repassUserCredentials(currentD, parent, time, currTime, tickID);
                manage.clickLogout();
                //work.doClickLogout(parent, tickID, currTime);
            }

        });
        logoutTh.start();
        return 1;
    }

    @Override
    public void connectionBind(){
        serverObject = "rmi://" + host + ":1099/MyRMI";
        try{
            rmiRegistry = LocateRegistry.createRegistry(1099);
            rm = new HandleRMI();
            rmiRegistry.rebind(serverObject, rm);
            Naming.rebind(serverObject, rm);
            System.out.println("Server has started");
        }
        catch(RemoteException re){
            System.err.println("Error at Initializing RMI instance or binding : " + re.getMessage());
        }
        catch(MalformedURLException me){
            System.err.println("Error occured when trying to bind or binding URL error : " + me.getMessage());
        }
    }

    private void processNecessary(){
        t = new Timer(2000, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                currentD = manage.getCaller();
                tickID = manage.getTicketID();
                currTime = manage.getCurrentTime();
                time = manage.getTimer();
                parent = manage.getMain();       
            }
        });
        Thread th = new Thread(new Runnable(){
            public void run(){
                  t.start();
            }
        });
        if(!th.isAlive()){
            th.start();
        }

    }


    public static void main(String []args){
        try{
            HandleRMI x = new HandleRMI();
            x.setHost("localhost");
            x.connectionBind();
        }
        catch(RemoteException e){
            System.err.print("Error occur at MAIN : " + e.getMessage());
        }
    }

}
