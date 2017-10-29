/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.gui.handler;
import bdfcybertech.util.ConfigFileWriter;
import bdfcybertech.util.ConfigFileReader;
import javax.swing.JOptionPane;
import javax.swing.JDialog;

/**
 *
 * @author MUSTAFA
 */
public class SettingsHandler {

    private ConfigFileWriter write;
    private ConfigFileReader read;

    private final String NAME = "C://name.ini";
    private final String CONFIG = "C://config.ini";
    
    private String sysName;
    private String serverIp;

    private boolean writeStat;

    private JDialog nParent;
    //private Thread r;
    private Thread w;

    public SettingsHandler(){
        initRead();
    }
    
    public SettingsHandler(JDialog in){
        nParent = in;
        initRead();
    }

    public SettingsHandler(JDialog in, String n, String ip){
        nParent = in;
        sysName = n;
        serverIp = ip;
        w = new Thread(new Runnable(){
            public void run(){
               initWrite();
            }
        });
        w.start();
    }

    private void initWrite(){
        write = new ConfigFileWriter(NAME, sysName);
        writeStat = write.getWriteStatus();
        
        if(writeStat){
            if(writeStat){
                write.writeAnother(CONFIG, serverIp);
                JOptionPane.showMessageDialog(nParent, "     SETTINGS SUCCESSFULLY SAVED .\n Application will SHUT DOWN. Pls ReLAUNCH",
                        "SUCCESS MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
            else{
                JOptionPane.showMessageDialog(nParent, "SETTINGS CANNOT BE SAVED  ***Contact Service***",
                        "SETTINGS ERROR | 1005", JOptionPane.PLAIN_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(nParent, "SETTINGS CANNOT BE SAVED  ***Contact Service***",
                    "SETTINGS ERROR | 1005", JOptionPane.PLAIN_MESSAGE);
        }
        nParent.dispose();
    }

    private void initRead(){
        read = new ConfigFileReader(NAME);
        sysName = read.getString();
        try{
            Thread.sleep(900);
        }
        catch(InterruptedException e){
            System.err.println(e.getMessage());
        }
        read.reFileReader(CONFIG);
        serverIp = read.getString();
    }

    public String getSysName(){
        return sysName;
    }

    public String getServerIp(){
        return serverIp;
    }

}
