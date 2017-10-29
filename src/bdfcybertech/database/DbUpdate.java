/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.database;
import java.sql.*;

/**
 *
 * @author MUSTAFA
 */
public class DbUpdate extends DbQuery{

    private int queryStat;

    public DbUpdate(){
        super();
    }

    public DbUpdate(String sql){
        super(sql);
        performQuery = sql;       
    }

    @Override
    protected void init() throws SQLException{
      dbAccess = new DbAdministrator(USER, PASS, dataBase);
      dbAccess.getConnected();
      if(dbAccess.isConnected()){
          stat = dbAccess.getReadyStatement();
          queryStat = stat.executeUpdate(performQuery);
          //close();
      }
      else{
          System.err.println("Error caused by DBA : | Problem connecting to db");
      }
    }

    public void performUpdate(String q){
        performQuery = q;
        try{
            init();
        }
        catch(SQLException sE){
            System.err.println("Error at Executing Query : " + sE.getMessage());
        }
        finally{
            //close();
        }
    }

    public boolean isSuccessfulUpdate(){
        if(queryStat != 0){
            return true;
        }
        else{
            return false;
        }
    }
}
