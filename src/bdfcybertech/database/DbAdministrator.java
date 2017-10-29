/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.database;
import bdfcybertech.gui.handler.SettingsHandler;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author MUSTAFA
 */
public class DbAdministrator {

    private Connection con;
    private Statement stat;
    private String dbURL;

    private SettingsHandler set;

    private Properties p;
    private boolean conStatus;

    private String hostName;
    private String dbName;

    public DbAdministrator(){
        System.out.println("Bad initialization");
        System.exit(419);
    }

    public DbAdministrator(String user, String pass, String db){
        set = new SettingsHandler();
        hostName = set.getServerIp();
        p = new Properties();
        p.put("user", user);
        p.put("password", pass);
        dbName = db;
    }
    /**
    public DbAdministrator(String host, String db, String user, String pass){
        set = new SettingsHandler();
        hostName = set.getServerIp();
        p = new Properties();
        p.put("user", user);
        p.put("password", pass);
        hostName = host;
        dbName = db;
    }
**/
    public void setHost(String host){
        hostName = host;
    }

    private void loadDriver(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch(Exception e){
            System.err.println("Error at loading database driver : " + e.getMessage());
        }
    }

    private void connectDatabase() throws SQLException{
        loadDriver();
        dbURL =  "jdbc:mysql://" + hostName + ":3306/" + dbName;
        con = DriverManager.getConnection(dbURL, p);
        stat = con.createStatement();
    }

    public void getConnected(){
        try{
            connectDatabase();
            conStatus = true;
        }
        catch(SQLException sqlE){
            conStatus = false;
            System.err.println("Unable to connect to database : " + sqlE.getMessage() + " at Hostname : " + hostName);
        }
    }

    public boolean isConnected(){
        return conStatus;
    }

    public Statement getReadyStatement(){
        return stat;
    }

    public String getHost(){
        return hostName;
    }
}
