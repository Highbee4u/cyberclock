/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.util;
import bdfcybertech.gui.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author MUSTAFA
 */
public class TrayApp extends Thread {

    private TrayIcon trayIcon;
    private int time;
    private int ticketID;
    private TimerDialog parent;

    private Timer popMsg;
    private Timer popInterval;
    private Timer regetTime;
    
    private TimeAlertDialog alertDialog;

    private Timing parseTime;

    private int popTimeInterval = 300000;

    public TrayApp(){

    }

    public TrayApp(TimerDialog d, Timing tT, int tic, int t){
        time = t;
        ticketID = tic;
        parent = d;
        parseTime = tT;
    }

    private void init(){
        trayIcon = new TrayIcon(IconManager.getImage("date.png"), "BDF Cyber Tech", createPopupMenu());
        parent.dispose();

        popTimers();
        
        trayIcon.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                EventQueue.invokeLater(new Runnable(){
                    public void run(){
                        removeTray();
                        parent.reShow();                      
                    }
                });
            }
        });
        try{
            SystemTray.getSystemTray().add(trayIcon);
        }
        catch(AWTException e){
            System.err.println(e.getMessage());
        }
        finally{
            alertDialog = new TimeAlertDialog(this, parseTime, parent, ticketID, time);
            alertDialog.displayAlert();
            if(!popInterval.isRunning()){
                popInterval.start();
            }

            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    regetTime = new Timer(1000, new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            time = parseTime.getRemTime();
                            if(parseTime.isTimeEnd()){
                                //then, logout
                                parent.logout();                                
                                removeTray();
                                
                            }
                            else if(time <= 5){
                                popTimeInterval = 30000;
                                //popMsg.restart();
                            }
                            else{
                                popTimeInterval = 300000;
                                //popMsg.restart();
                            }
                        }
                    });
                    regetTime.start();
                }
            });
        }
    }

    private PopupMenu createPopupMenu() throws HeadlessException{
        PopupMenu exitMenu = new PopupMenu();
        MenuItem exit = new MenuItem("Show Time");
        exitMenu.add(exit);
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //System.exit(0);
                EventQueue.invokeLater(new Runnable(){
                    public void run(){
                        removeTray();
                        parent.reShow();

                    }
                });
            }
        });
        return exitMenu;
    }

    @Override
    public void run(){
        init();
    }

    public void removeTray(){
        SystemTray.getSystemTray().remove(trayIcon);
        popMsg.stop();
        popInterval.stop();
        regetTime.stop();
    }

    private void popTimers(){
        popMsg = new Timer(popTimeInterval, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                alertDialog.displayAlert();
                popMsg.stop();
                if(!popInterval.isRunning()){
                    popInterval.start();
                }
            }
        });

        popInterval = new Timer(9000, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                alertDialog.disposeAlert();
                popInterval.stop();
                if(!popMsg.isRunning()){
                    popMsg.start();
                }
            }
        });
    }

}

