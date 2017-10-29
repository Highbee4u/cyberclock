/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.ticket;
import bdfcybertech.database.DbQuery;
import bdfcybertech.database.DbUpdate;

import javax.swing.*;

/**
 *
 * @author MUSTAFA
 */
public class Ticket extends Ticketting{

    public Ticket(){

    }

    public Ticket(JDialog q){
        parent = q;
    }

    public Ticket(JPasswordField t, JDialog q){
        field = t;
        parent = q;
        r();
    }



    protected synchronized void init(){
        query = new DbQuery("SELECT * FROM unused_tickets WHERE pin='" + pin + "' AND active = 0");
        boolean stat = false;
        stat = query.isRowEmpty();
        if(stat){
            //query.performQuery("SELECT * FROM using_tickets WHERE user='" + set.getSysName() + "'");
            //if(query.isRowEmpty()){
                    validTicket = false;
            //}
            //else{
                //tickID = query.getIntegerResult("id");
                //tempUpdate(tickID);
                //timeLeft = getRemainingTime();
                //validTicket = true;
            //}
        }
        else{
            tickID = query.getIntegerResult("id"); //get the ticket ID;
            tempUpdate(tickID);
            validTicket = true;
        }
    }
    

    public int getTicketID(){
        return tickID;
    }

    public synchronized void updateTicketTime(int id, int t){
        tickID = id;
        if(t > 5){
           update = new DbUpdate("UPDATE unused_tickets SET mins_rem='" + t + "', active=0 WHERE id='" + tickID + "'");
           if(update.isSuccessfulUpdate()){
               //then delete this ticket from using_tickets
               update.performUpdate("DELETE FROM using_tickets WHERE id='" + tickID + "'");
           }
           else{
               autoLogout(tickID);
               System.err.println("LOGOUT ERROR : Contact Service");
           }
        }
        else{
            autoLogout(tickID);
        }
    }

    public void autoCommit(int id, int t){
        tickID = id;
        timeLeft = t;
        update = new DbUpdate("UPDATE unused_tickets SET mins_rem='" + timeLeft + "', active=2 WHERE id='" + tickID + "'");
        if(update.isSuccessfulUpdate()){
            update.performUpdate("UPDATE using_tickets SET mins_rem='" + timeLeft + "' WHERE id='" + tickID + "'");
            if(!update.isSuccessfulUpdate()){
                update.performUpdate("INSERT INTO using_tickets VALUES ('" + tickID + "', '" + set.getSysName() + "', '" +
                        timeLeft + "')");
            }
        }
        else{
            //log user out coz smth is wrong
            //
            autoLogout(tickID);
            timeLeft = 0;
        }
    }

    public synchronized void ticketLogin(int id, int t){
        tickID = id;
        timeLeft = t;
        update = new DbUpdate("UPDATE unused_tickets SET mins_rem='" + timeLeft + "', active=2 WHERE id='" + tickID + "'");
        query = new DbQuery("SELECT id FROM using_tickets WHERE id='" + tickID + "'");
        if(query.isRowEmpty()){
            update.performUpdate("INSERT INTO using_tickets VALUES ('" + tickID + "', '" + set.getSysName() + "', '" +
                timeLeft + "')");
        }
        else{
            //do nothing
        }
    }

    public synchronized void autoLogout(int id){
        int currentId = id;
        update = new DbUpdate("DELETE FROM unused_tickets WHERE id='" + currentId + "'");
        if(update.isSuccessfulUpdate()){
            //then delete this ticket from using_tickets
            update.performUpdate("DELETE FROM using_tickets WHERE id='" + currentId + "'");
        }
        else{
            System.err.println("LOGOUT ERROR : Contact Service");
        }
    }

}
