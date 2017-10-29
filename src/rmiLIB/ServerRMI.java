package rmiLIB;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author MUSTAFA
 */
public interface ServerRMI extends Remote{

    public int remoteLogout() throws RemoteException;

    public int remoteLogin() throws RemoteException;

    public boolean remoteAlert(String alert) throws RemoteException;

}
