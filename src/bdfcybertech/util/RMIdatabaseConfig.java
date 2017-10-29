/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.util;
import bdfcybertech.database.DbUpdate;
import bdfcybertech.database.DbQuery;

/**
 *
 * @author MUSTAFA
 */
public class RMIdatabaseConfig {

    private String ip;
    private String timerName;
    private DbUpdate up;
    private DbQuery qu;
    private boolean stat;
    private Thread t;

    public RMIdatabaseConfig(String timeN, String i){
        timerName = timeN;
        ip = i;
        t = new Thread(new Runnable(){
            public void run(){
                init();
            }
        });
    }

    private void init(){
        qu = new DbQuery("SELECT * FROM remote_sys WHERE time_name='" + timerName + "' OR ip='" + ip + "'");
        if(qu.isRowEmpty()){
            //then update the table
            up = new DbUpdate("INSERT into remote_sys(time_name, ip) VALUES ('" + timerName + "', '" + ip + "')");
            if(up.isSuccessfulUpdate()){
                stat = true;
            }
            else{
                stat = false;
                System.err.println("INSERT query not successful");
            }
        }
        else{
            //get the id of the qu
            up = new DbUpdate("UPDATE remote_sys SET time_name='" + timerName + "', ip='" + ip + "' WHERE id='" + qu.getIntegerResult("id") + "'");
            if(up.isSuccessfulUpdate()){
                stat = true;
            }
            else{
                stat = false;
                System.err.println("UPDATE query not successful");
            }
        }
    }

    public void configureRemoteAccess(){
        t.start();
        if(t.isAlive()){
            if(stat){
                //meaning evverything was successful
            }
            else{
                //System.err.println("Bad error happened when trying to make this timer available for remote access");
            }
        }
        else{
            //bad error
            System.err.println("Critical error with thread");
        }
    }



}
