/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.network;
import bdfcybertech.util.IconManager;
import java.awt.Color;
import java.net.*;
import java.io.*;
import javax.swing.Icon;
/**
 *
 * @author MUSTAFA
 */
public class ServerConnection {

    private boolean conStatus;
    private URL l;
    private URLConnection c;

    private String host;
    private String retMessage;
    //private InternetCheck internet;
    private String conType;

    private Color col;

    public ServerConnection(String h){
        host = h;
        try{
            process();
        }
        catch(NullPointerException e){
            col = Color.RED;
            retMessage = "Not Connected to the Server";
            //System.err.println(e.getMessage());
        }
    }

    public void reProcess(){
        try{
            process();
        }
        catch(NullPointerException e){
            col = Color.RED;
            retMessage = "Not Connected to the Server";
            //System.err.println(e.getMessage());
        }
    }

    private synchronized void process() throws NullPointerException{
        try{
            l = new URL("http://" + host + "/bdfcybertech/settings/setting.ini");
            c = l.openConnection();
        }
        catch(MalformedURLException e){
            System.err.println(e.getMessage());
        }
        catch(IOException e2){
            System.err.println(e2.getMessage());
        }

        conType = c.getContentType();
        
        if(conType.equals("text/plain")){
            //internet = new InternetCheck();
                    //if(internet.getConnectionStatus()){
                        conStatus = true;
                        col = Color.BLUE;
                        retMessage = "Connected to the Server | I N T E R N E T";
                        /**
                    }
                    else{
                        conStatus = true;
                        col = Color.PINK;
                        retMessage = "No I N T E R N E T Connection Available";
                    }
                         **/
        }
        else{
            conStatus = false;
            col = Color.RED;
            retMessage = "Not Connected to the Server";
        }
    }

    public String getMessage(){
        return retMessage;
    }

    public Color getMessageColor(){
        return col;
    }

    public synchronized Icon getConnectionStatIcon(){

        if(conStatus){
            return IconManager.getIcon("");
        }
        else{
            return IconManager.getIcon("");
        }
    }

    public synchronized boolean getServerState(){
        return conStatus;
    }

}
