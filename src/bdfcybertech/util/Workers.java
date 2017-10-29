/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.util;
import bdfcybertech.gui.MainGUI;
import bdfcybertech.gui.TimerDialog;
import bdfcybertech.ticket.Ticket;
import bdfcybertech.ticket.AddTicket;
import bdfcybertech.gui.TimePreviewDialog;
import bdfcybertech.network.*;
import javax.swing.*;
import java.awt.event.*;


/**
 *
 * @author MUSTAFA
 */
public class Workers {

    protected MainGUI main;
    protected TimerDialog time;
    private JDialog parent;
    protected ServerConnection request;
    protected bdfcybertech.gui.handler.SettingsHandler sett;
    private Workers.AddTime add;
    private Workers self = this;
    protected HandleRMI rmi;

    public Workers(){
        sett = new bdfcybertech.gui.handler.SettingsHandler(parent);
        request = new ServerConnection(sett.getServerIp());
    }

    public Workers(JDialog d){
        parent = d;
    }

    public Workers(TimerDialog t, JDialog d){
        parent = d;
        time = t;
    }
    
    public Workers(MainGUI in, JDialog d, HandleRMI h){
        main = in;
        parent = d;
        rmi  = h;
        sett = new bdfcybertech.gui.handler.SettingsHandler(parent);
        request = new ServerConnection(sett.getServerIp());
    }

    public void doLoginWork(JPasswordField p, JButton b){
        new Workers.LoginWorker(parent, p, b).execute();
    }

    public void doNetworkCheck(JLabel l){
        new Workers.NetworkCheck(l).execute();
    }

    public void doAddingTime(JPasswordField p, JButton a, int c, int id){
        add = new Workers.AddTime(parent, p, a, c, id);
        add.execute();
    }

    public AddTicket getAdd(){
        return add.getRefAddTicket();
    }

    public void doSaveSettings(JDialog me, String a, String b){
        new Workers.SaveSettings(me, a, b).execute();
    }

    public void doClickLogout(JDialog me, int a, int b){
        new Workers.Logout(me, a, b).execute();
    }

    public void doAutoCommit(int a, int b){
        new Workers.AutoCommit(a, b).execute();
    }
/*
    public void doReadSettings(JDialog me, JTextField n, JTextField ip){
        new Workers.ReadSettings(parent, n, ip).execute();
    }
 * */

    class NetworkCheck extends SwingWorker<Timer, Void>{

        //private ServerConnection request;
        private Timer t;
        private JLabel conLbl;
        private String conStatus;
        private int i = 3000;
        
        public NetworkCheck(JLabel lbl){
            conLbl = lbl;           
            init();
        }

        private void init(){
            t = new Timer(i, new ActionListener(){
                public void actionPerformed(ActionEvent e){
                conStatus = request.getMessage();
                request.reProcess();
                conLbl.setText(conStatus);
                conLbl.setForeground(request.getMessageColor());
                conLbl.updateUI();
                }
            });
        }
        
        protected Timer doInBackground(){
            t.start();
            return t;
        }
        
        @Override
        protected void done(){
            if(t.isRunning()){
                t.setRepeats(false);
            }
        }
    }

    class LoginWorker extends SwingWorker<Ticket, Void>{
        protected JPasswordField pass;
        protected JDialog nParent;
        protected Ticket t;
        private JButton loginBtn;
        private boolean conStatus;

        public LoginWorker(JDialog np, JPasswordField p, JButton b){
            request = new ServerConnection(sett.getServerIp());
            nParent = np;
            pass = p;
            loginBtn = b;
            conStatus = request.getServerState();
        }

        protected Ticket doInBackground(){
            return workIt();
        }

        private Ticket workIt(){
            if(conStatus){
                t = new Ticket(pass, nParent);
                loginBtn.setEnabled(true);
                return t;
            }
            else{
                JOptionPane.showMessageDialog(nParent, "System is not connected to ADMIN. Check SETTINGS",
                        "SETTINGS ERROR | 1001", JOptionPane.PLAIN_MESSAGE);
                loginBtn.setEnabled(true);
                return t;              
            }
            
        }

        @Override
        protected void done(){
            //t = new Ticket();
            try{
                if(t.isGoodTicket()){
                    //SwingUtilities.invokeLater(new Runnable(){
                        //public void run(){
                    t.checkTicketStatus(t.getTicketID(), t.getRemainingTime());
                            new TimePreviewDialog(main, nParent, t.getTicketID(), t.getRemainingTime(), rmi);
                        //}
                    //});
                }
                else{
                    JOptionPane.showMessageDialog(nParent, "Expired / Incorrect PIN | try again or Contact Administrator.",
                            "PIN Error | 1002", JOptionPane.PLAIN_MESSAGE);
                }
            }
            catch(NullPointerException e){
                System.err.println(e.getMessage());
            }
            loginBtn.setEnabled(true);
        }
    }

    class AddTime extends SwingWorker<AddTicket, Void>{

        protected AddTicket at;
        private JButton addBtn;
        private JDialog par;
        private JPasswordField pass;
        private int currTime;
        private int currID;

        public AddTime(JDialog np, JPasswordField p, JButton b, int cT, int id){
            par = np;
            addBtn = b;
            pass = p;
            currTime = cT;
            currID = id;
        }

        @Override
        protected AddTicket doInBackground(){
            return workIt();
        }

        private AddTicket workIt(){
            at = new AddTicket(pass, par, currTime, currID);
            addBtn.setEnabled(true);
            return at;
        }

        public AddTicket getRefAddTicket(){
            return at;
        }

        @Override
        protected void done(){
            if(at.isGoodTicket()){
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        at.checkTicketStatus(at.getTicketID(), at.getNewTime());
                        new TimePreviewDialog(self, time, parent, at.getTicketID(), at.getNewTime(),
                                "Minutes remaining after adding " + at.getAddedTime() + " mins", "New Ticket Expires in 0 Day(s)");
                        
                    }
                });

            }
            else{
                JOptionPane.showMessageDialog(par, "Expired / Incorrect PIN | Bad attempt adding new Ticket.",
                        "PIN Error | 1002", JOptionPane.PLAIN_MESSAGE);
            }
            addBtn.setEnabled(true);
        }
    }

    class SaveSettings extends SwingWorker<bdfcybertech.gui.handler.SettingsHandler, Void>{

        private bdfcybertech.gui.handler.SettingsHandler set;
        private String name;
        private String servIp;
        private JDialog self;

        public SaveSettings(JDialog me){
            self = me;
        }
        
        public SaveSettings(JDialog me, String n, String ip){
            self = me;
            name = n;
            servIp =  ip;
        }

        protected bdfcybertech.gui.handler.SettingsHandler workIt(){
           set = new bdfcybertech.gui.handler.SettingsHandler(self, name, servIp);
           return set;
        }

        protected bdfcybertech.gui.handler.SettingsHandler doInBackground(){
            return workIt();
        }

        @Override
        protected void done(){
            //parent.dispose();
            //parent.dispose();
            System.gc();
        }
    }

    class Logout extends SwingWorker<Ticket, Void>{

        private Ticket tick;
        private int ticketID;
        private int currTime;
        private JDialog parent;
        
        public Logout(JDialog in, int t, int c){
            parent = in;
            ticketID = t;
            currTime = c;       
        }

        private Ticket workIt(){
            tick = new Ticket(parent);
            tick.updateTicketTime(ticketID, currTime);
            tick.checkTicketStatus(ticketID, currTime);
            return tick;

        }

        public Ticket doInBackground(){
            return workIt();
        }


        @Override
        protected void done(){            
            parent.dispose();
        }
    }

    class AutoCommit extends SwingWorker<Ticket, Void>{

        private Ticket tick;
        private int ticketID;
        private int currTime;
        
        public AutoCommit(int t, int c){
            ticketID = t;
            currTime = c;
        }

        private Ticket workIt(){
            tick = new Ticket();
            tick.autoCommit(ticketID, currTime);
            return tick;

        }

        public Ticket doInBackground(){
            return workIt();
        }

        @Override
        protected void done(){
            System.gc();
        }
        
    }

}
