/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.network;
import java.net.*;
import java.io.*;

/**
 *
 * @author MUSTAFA
 */
public class InternetCheck{

    private URL l;
    private boolean conStatus;
    private InputStream in;
    
    public InternetCheck(){
        process();
    }

    private void process(){
        try{
            l = new URL("http://www.google.com/");
            in = l.openStream();
            if(in.read() != -1){
                conStatus = true;
            }
            else{
                conStatus = false;
            }
        }
        catch(MalformedURLException e){
            conStatus = false;
            //System.err.println(e.getMessage());
        }
        catch(IOException e2){
            conStatus = false;
            //System.err.println(e2.getMessage());
        }
    }

    public synchronized boolean getConnectionStatus(){
        return conStatus;
    }

}
