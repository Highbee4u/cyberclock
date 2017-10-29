/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.ticket;
import bdfcybertech.database.*;

/**
 *
 * @author MUSTAFA
 */
public class TicketTimer {

    private String user;
    private int time;

    private boolean stat;

    private DbUpdate update;
    private DbQuery query;

    public TicketTimer(){
        
    }
    public TicketTimer(String tick, int t){
        user = tick;
        time = t;
        init();
    }

    private void init(){
        query = new DbQuery("SELECT id, mins_rem FROM unused_ticket WHERE pin");
        update = new DbUpdate("INSERT INTO using_tickets (id,user,mins_rem) VALUES ('" + getTicketID() +
                "', '" + user + "', '" + time + "')");
        if(update.isSuccessfulUpdate()){
            stat = true;
            //update.performUpdate("UPDATE unused_tickets SET active=1 WHERE id='" +  + "'");
        }
        else{
            stat = false;
        }
    }

    private int getTicketID(){
        Object o = query.getObjectResult("id");
        String s = String.valueOf(o);
        return Integer.parseInt(s);
    }
}
