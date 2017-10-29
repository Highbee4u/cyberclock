/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.database;
import bdfcybertech.gui.handler.SettingsHandler;
import java.sql.*;

/**
 *
 * @author MUSTAFA
 */
public class DbQuery {
    
    protected String performQuery;
    protected static String USER = "cyber";
    protected static String PASS = "alert01";
    protected String host;
    protected String dataBase = "cyber";

    protected DbAdministrator dbAccess;
    protected Statement stat;
    private ResultSet result;

    protected SettingsHandler set;

    public DbQuery(){
        performQuery = "";
    }
    
    public DbQuery(String inQ){
        performQuery = inQ;
        set = new SettingsHandler();
        host = set.getServerIp();
        try{
            init();
        }
        catch(SQLException e){
            System.err.println("Error at Executing Query : " + e.getMessage());
        }
        finally{
            //close();
        }
    }

    public DbQuery(String inhost, String inQ){
        performQuery = inQ;
        host = inhost;
        try{
            init();
        }
        catch(SQLException e){
            System.err.println("Error at Executing Query : " + e.getMessage());
        }
        finally{
            //close();
        }
    }

    protected void init() throws SQLException{
      dbAccess = new DbAdministrator(USER, PASS, dataBase);
      dbAccess.getConnected();
      if(dbAccess.isConnected()){
          stat = dbAccess.getReadyStatement();
          result = stat.executeQuery(performQuery);
      }
      else{
          System.err.println("Error caused by DBA : | Problem connecting to db");
      }
    }

    public void performQuery(String q){
        performQuery = q;
        try{
            init();
        }
        catch(Exception e){
            System.err.println("Error at Executing Query : " + e.getMessage());
        }
    }

    public void setDatabase(String db){
        dataBase = db;
    }

    public void setHost(String h){
        host = h;
    }

    public ResultSet getResultInSet(){
        return result;
    }

    public Object getObjectResult(String col){
        Object ret = new Object();
        try{
            ret = result.getObject(col);
        }
        catch(SQLException e){
            System.err.println("Error getting the object from the column : " + col + " : " + e.getMessage());
        }
        finally{
            //close();
        }
        return ret;
    }

    public int getIntegerResult(String col){
        int ret = 0;
        try{
            ret = result.getInt(col);
        }
        catch(SQLException e){
            System.err.println("Error getting the integer from the column : " + col + " : " + e.getMessage());
        }
        finally{
            //close();
        }
        return ret;
    }


    public boolean isRowEmpty(){
        boolean ret = true;
        try{
            if(!result.first()){
                ret = true;
            }
            else{
                ret = false;
            }
        }
        catch(SQLException e){
            //
        }
        return ret;
    }

    protected void close(){
        try{
            stat.close();
            result.close();
        }
        catch(SQLException e){
            
        }
    }
}
