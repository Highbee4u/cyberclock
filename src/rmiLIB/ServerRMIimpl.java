package rmiLIB;
import java.rmi.*;
import java.rmi.server.*;
import java.net.MalformedURLException;
import java.rmi.registry.*;

/**
 *
 * @author MUSTAFA
 */
public class ServerRMIimpl extends UnicastRemoteObject implements ServerRMI{

    //private ServerRMI rmi;
    protected String serverObject; //each will have its name according to system's name
    protected String host;
    protected Registry rmiRegistry = null;

    public ServerRMIimpl() throws RemoteException{
        super();
        init();
    }

    protected void init(){
        //get necessary things initiallized here
    }
    public void setHost(String h){
        host  = h;
    }

    public boolean remoteAlert(String alert){
        return true;
    }

    public int remoteLogin(){
       return 1;
    }

    public int remoteLogout(){
        return 0;
    }

    public void connectionBind(){
        serverObject = "rmi://" + host + ":1099/MyRMI";
        try{
            rmiRegistry = LocateRegistry.createRegistry(1099);
            ServerRMI rm = new ServerRMIimpl();
            rmiRegistry.rebind(serverObject, rm);
            Naming.rebind(serverObject, rm);
        }
        catch(RemoteException re){
            System.err.println("Error at Initializing RMI instance or binding : " + re.getMessage());
        }      
        catch(MalformedURLException me){
            System.err.println("Error occured when trying to bind or binding URL error : " + me.getMessage());
        }
    }

    public static void main(String []args){
        try{
            ServerRMIimpl x = new ServerRMIimpl();
            x.setHost("localhost");
            x.connectionBind();
            System.out.println("Server has started");
        }
        catch(RemoteException e){
            System.err.print("Error occur at MAIN : " + e.getMessage());
        }
    }
}
