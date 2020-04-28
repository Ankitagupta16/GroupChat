package server;
import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * Remote interface for server class
 * @author AnkitaGupta
 */

public interface ServerIF extends Remote {
	
public void updateChat(String userName, String chatMessage)throws RemoteException;

public void registerListener(String[] details)throws RemoteException;

public void leaveChat(String userName)throws RemoteException;

public void sendPM(int[] privateGroup, String privateMessage)throws RemoteException;
}

