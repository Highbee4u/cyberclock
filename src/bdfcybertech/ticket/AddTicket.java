/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.ticket;
import bdfcybertech.database.DbUpdate;
import bdfcybertech.database.DbQuery;
import javax.swing.*;

/**
 *
 * @author MUSTAFA
 */
public class AddTicket extends Ticketting{

    private int previousTime;
    private int newTime;
    private int prevID;
    private Thread upD;

    public AddTicket(){
    }
    
    
    public AddTicket(JPasswordField t, JDialog q, int prev, int cI){
        field = t;
        parent = q;
        previousTime = prev;
        prevID = cI;
        r();
    }

    public int getAddedTime(){
        return timeLeft;
    }

    public void init(){
        query = new DbQuery("SELECT * FROM unused_tickets WHERE pin='" + pin + "' AND active = 0");
        boolean stat = false;
        stat = query.isRowEmpty();
        if(stat){
            validTicket = false;
            //dont do anything
            //mean say new ticket is not valid
        }
        else{
            tickID = query.getIntegerResult("id"); //get the ticket ID;
            timeLeft = getRemainingTime();
            tempUpdate(tickID);
            addTicketTime();
            validTicket = true;
        }
    }
    
    private void addTicketTime(){
        newTime = previousTime + timeLeft;
    }

    public int getNewTime(){
        //updateDB();
        return newTime;
    }

    private void updateDB(){
        update = new DbUpdate("INSERT INTO using_tickets VALUES ('" + tickID + "', '" + set.getSysName() + "', '"
                 + newTime + "')");
        if(update.isSuccessfulUpdate()){
            update.performUpdate("UPDATE unused_tickets SET mins_rem='" + newTime +"' WHERE id='" + tickID + "'");
        }
        else{
            System.err.println("Error inserting new table");
        }
    }

    protected void autoCommit(int x, int y){
        
    }

    public synchronized void ticketContinue(){
        DbUpdate up;
        up = new DbUpdate("DELETE FROM unused_tickets WHERE id='" + prevID + "'");
        up.performUpdate("DELETE FROM using_tickets WHERE id='" + prevID + "'");
        if(up.isSuccessfulUpdate()){
            upD = new Thread(new Runnable(){
                public void run(){
                    updateDB();
                }
            });
            upD.start();
        }
    }

    public int getTicketID(){
        return tickID;
    }
}
