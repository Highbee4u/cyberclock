/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.gui;
import bdfcybertech.util.*;
import bdfcybertech.ticket.UserManager;
import bdfcybertech.network.*;
import java.rmi.RemoteException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author MUSTAFA
 */
public class TimerDialog extends JDialog{

    private JButton addTimeBtn;
    private JLabel companyLbl;
    private JLabel counterLbl;
    private JButton hideBtn;
    private JButton logOutBtn;
    private JPanel outterPanel;
    private JSeparator sep1;
    private JSeparator sep2;
    private JSeparator sep3;
    private JSeparator sep4;
    private JLabel timeLeftLbl;
    private JLabel timeUnitLbl;

    private int ticketID;
    private int timeCount;

    private TimerDialog self = this;

    private TrayApp trayThread;

    private Timer timeHandler;
    private Timer autoCommitter;
    private Timing theTime;
    private UserManager user;

    private MainGUI main;
    //private JDialog parent;
    private Workers work;
    private HandleRMI rmi;
    private Thread newT;

    public TimerDialog(MainGUI in, JDialog d, int t, int time, HandleRMI rm){
        ticketID = t;
        timeCount = time;
        main = in;
        //parent = d;
        work = new Workers();
        rmi = rm;
        
        
        setTitle("V 1.0.0.2 | Developed by TECHNO GLOBAL WORLD - 08025481373, 08066131861.");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);
        setSize(783, 32);
        MyLookAndFeel.setLook();
        myComponents();


        theTime = new Timing(timeCount);
        user = new UserManager(self, main, theTime, timeCount, ticketID);

     /*
        try{
            //rmi.reHandleRMI(main, self, theTime, timeCount, ticketID);
        }
        catch(NullPointerException n){
            System.err.println("Error occured in timer dialog caused by HANDLERMI: " + n.getMessage());
        }
        catch(RemoteException re){
            System.err.println("Error when trying to continue REMOTE server (LOGOUT) : " + re.getMessage());
        }
      *
      * */
        setVisible(true);
        actionsList();
        
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
               timeHandler.start();
               theTime.start();
               autoCommitter.start();
            }
        });
    }

    public void reShow(){
        setVisible(true);
    }

    public void setNewTime(int t, int id){
        ticketID = id;
        timeCount = t;
        counterLbl.setText(String.valueOf(timeCount));
        theTime.continueTiming(timeCount);
        newT = new Thread(new Runnable(){
            public void run(){
                timeHandler.restart();
                theTime.restart();
            }
        });
        newT.start();
        counterLbl.updateUI();
        repaint();
    }

    public void logout(){
        stopAllTimer();
        user.clickLogout();
        if(trayThread.isAlive()){
            trayThread.removeTray();
        }
    }

    public void autoLogout(){
        stopAllTimer();
        user.autoLogout();
        if(trayThread.isAlive()){
            trayThread.removeTray();
        }
    }

    private synchronized void addTime(){
        new AddTicketDialog(self, timeCount, ticketID);
    }
    
    private void myComponents(){        
        outterPanel = new JPanel();
        timeLeftLbl = new JLabel();
        counterLbl = new JLabel();
        timeUnitLbl = new JLabel();
        sep1 = new JSeparator();
        logOutBtn = new JButton();
        sep2 = new JSeparator();
        addTimeBtn = new JButton();
        sep3 = new JSeparator();
        companyLbl = new JLabel();
        sep4 = new JSeparator();
        hideBtn = new JButton();


        outterPanel.setBackground(new Color(6, 161, 225));
        outterPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(166, 170, 254), 2), BorderFactory.createLineBorder(new Color(0, 51, 51))));
        outterPanel.setPreferredSize(new Dimension(783, 32));

        timeLeftLbl.setFont(new Font("Aharoni", 1, 11)); // NOI18N
        timeLeftLbl.setHorizontalAlignment(SwingConstants.CENTER);
        timeLeftLbl.setText("Time Left :");
        timeLeftLbl.setHorizontalTextPosition(SwingConstants.CENTER);

        counterLbl.setFont(new Font("Modern No. 20", 1, 24));
        counterLbl.setForeground(new Color(255, 255, 255));
        counterLbl.setHorizontalAlignment(SwingConstants.CENTER);
        counterLbl.setText(String.valueOf(timeCount));

        timeUnitLbl.setFont(new Font("Rod", 0, 10));
        timeUnitLbl.setHorizontalAlignment(SwingConstants.CENTER);
        timeUnitLbl.setText("minute(s)");

        sep1.setForeground(new Color(51, 51, 51));
        sep1.setOrientation(SwingConstants.VERTICAL);

        logOutBtn.setFont(new Font("Verdana", 0, 10));
        logOutBtn.setText("Log Out");

        sep2.setForeground(new Color(51, 51, 51));
        sep2.setOrientation(SwingConstants.VERTICAL);

        addTimeBtn.setFont(new Font("Verdana", 0, 10));
        addTimeBtn.setText("Add Time");

        sep3.setForeground(new Color(51, 51, 51));
        sep3.setOrientation(SwingConstants.VERTICAL);

        companyLbl.setFont(new Font("PMingLiU", 1, 11));
        companyLbl.setForeground(new Color(255, 255, 255));
        companyLbl.setHorizontalAlignment(SwingConstants.CENTER);
        companyLbl.setText("<html>&copy; B D F - Cyber Tech</html>");
        companyLbl.setHorizontalTextPosition(SwingConstants.CENTER);

        sep4.setForeground(new Color(0, 0, 0));
        sep4.setOrientation(SwingConstants.VERTICAL);

        hideBtn.setFont(new Font("Verdana", 0, 10));
        hideBtn.setText("hide to TRAY !!");

        GroupLayout outterPanelLayout = new GroupLayout(outterPanel);
        outterPanel.setLayout(outterPanelLayout);
        outterPanelLayout.setHorizontalGroup(
            outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(outterPanelLayout.createSequentialGroup()
                .addComponent(timeLeftLbl, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sep1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(counterLbl, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeUnitLbl, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logOutBtn, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sep2, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addTimeBtn, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sep3, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(companyLbl, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sep4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hideBtn, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );

        outterPanelLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {addTimeBtn, logOutBtn});

        outterPanelLayout.setVerticalGroup(
            outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(outterPanelLayout.createSequentialGroup()
                .addGroup(outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(sep4, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(companyLbl, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addGroup(outterPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(sep3, GroupLayout.Alignment.LEADING)
                        .addComponent(timeLeftLbl, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                        .addComponent(addTimeBtn, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.LEADING, outterPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(timeUnitLbl, GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                            .addComponent(logOutBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(sep1, GroupLayout.Alignment.LEADING)
                        .addComponent(sep2, GroupLayout.Alignment.LEADING)
                        .addComponent(counterLbl, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(hideBtn))
                .addContainerGap())
        );

        outterPanelLayout.linkSize(SwingConstants.VERTICAL, new Component[] {addTimeBtn, hideBtn, logOutBtn});

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(outterPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(outterPanel, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        pack();
    }

    private void actionsList(){
        hideBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                trayThread.start();
            }
        });

        addTimeBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //addTimeBtn.setEnabled(false);
                addTime();
            }
        });

        logOutBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                       logout();                                                
                    }
                });
            }
        });

        timeHandler = new Timer(500, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                user.repassUserCredentials(self, main, theTime, timeCount, ticketID);
                timeCount = theTime.getRemTime();
                trayThread = new TrayApp(self, theTime, ticketID, timeCount);
                counterLbl.setText(String.valueOf(timeCount));
                if(timeCount <= 5){
                    counterLbl.setForeground(Color.red);
                    if(theTime.isTimeEnd()){
                        autoLogout();
                    }
                }
                else{
                    counterLbl.setForeground(Color.WHITE);
                }
                counterLbl.updateUI();
            }
        });

        autoCommitter = new Timer(30000, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                work.doAutoCommit(ticketID, timeCount);
            }
        });
    }

    private void stopAllTimer(){
        timeHandler.stop();
        theTime.stop();
        autoCommitter.stop();
    }
}
