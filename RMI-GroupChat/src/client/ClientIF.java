package client;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote interface for client classes
 * A method to receive a string from server
 * A method to update changes to list of users
 * @author AnkitaGupta
 */

public interface ClientIF extends Remote{

	public void messageFromServer(String message) throws RemoteException;

	public void updateUserList(String[] currentUsers) throws RemoteException;
	
}


