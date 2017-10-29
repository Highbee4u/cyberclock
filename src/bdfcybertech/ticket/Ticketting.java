/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.ticket;
import bdfcybertech.database.DbQuery;
import bdfcybertech.database.DbUpdate;
import bdfcybertech.gui.handler.SettingsHandler;
import bdfcybertech.util.InputsManager;
import java.util.Date;
import javax.swing.*;
/**
 *
 * @author MUSTAFA
 */
public abstract class Ticketting {

    protected String pin = "";
    protected int tickID;
    protected JPasswordField field;
    protected DbQuery query;
    protected DbUpdate update;
    protected InputsManager check;
    protected JDialog parent;
    protected boolean validTicket;
    protected String date;
    protected Date d;

    protected int timeLeft;

    protected SettingsHandler set = new SettingsHandler();
    
    abstract void init();

    protected void r(){
        char[] pass = field.getPassword();

        for(int a = 0; a < pass.length; a++){
            pin += pass[a];
        }
        check = new InputsManager(pin);

        if(check.isGoodInput()){
            field.setText("");
            init();
            //System.out.println(pin);
        }
        else{
            field.setText("");
            JOptionPane.showMessageDialog(parent, "Bad Characters / Invalid input provided. Please check PIN values & try again",
                    "PIN Error | 1001", JOptionPane.PLAIN_MESSAGE);
        }
    }

    
    public int getRemainingTime(){
        timeLeft = query.getIntegerResult("mins_rem");
        return timeLeft;
    }
    
    public abstract int getTicketID();

    abstract void autoCommit(int id, int t);

    
    public boolean isGoodTicket(){
        return validTicket;
    }
    
    protected synchronized boolean tempUpdate(int id){
        tickID = id;
        update = new DbUpdate("UPDATE unused_tickets SET active=1 WHERE id='" + tickID + "'");
        return update.isSuccessfulUpdate();
    }

    public synchronized boolean tempCancel(int id){
        tickID = id;
        update = new DbUpdate("UPDATE unused_tickets SET active=0 WHERE id='" + tickID + "'");
        return update.isSuccessfulUpdate();
    }

    public synchronized void checkTicketStatus(int id, int m){
       tickID = id;
       d = new Date();
       date = d.getDate() + "/" + (d.getMonth() + 1) + "/" + (d.getYear() + 1900);
       int mins_rem = m;

       query = new DbQuery("SELECT * FROM used_tickets WHERE get_id='" + tickID + "'");
       if(!query.isRowEmpty()){
           //mins_rem = query.getIntegerResult("mins_rem");
           //System.out.println("Am here when row is not empty");
           update = new DbUpdate("UPDATE used_tickets SET mins_rem='" + mins_rem + "' WHERE get_id='" + tickID +"'");
       }
       else{
           update = new DbUpdate("INSERT INTO used_tickets (get_id, mins_rem, date_used) VALUES ('" + tickID + "', '" + mins_rem + "', '" + date + "')");
           //System.out.println("Am here when row is empty and am inserting new datas");
       }
    }
    
}
