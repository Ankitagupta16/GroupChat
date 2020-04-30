package server;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import client.ClientIF;

/**
 * Implementation to remote interface ServerIF
 * @author AnkitaGupta
 *
 */

public class Server extends UnicastRemoteObject implements ServerIF {
	private Vector<Chatter> chatters;
	private static final long serialVersionUID = 1L;
	
	//Constructor
	public Server() throws RemoteException {
		super();
		chatters = new Vector<Chatter>(10, 1);
	}
	
	
	public static void main(String[] args) {
		startRMIRegistry();	
		String hostName = "localhost";
		String serviceName = "GroupChatService";
		
		//if hostName and serviceName is provided in argument then update their value
		if(args.length == 2){
			hostName = args[0];
			serviceName = args[1];
		}
		
		//hosting rmi service in server process
		try{
			ServerIF hello = new Server();
			Naming.rebind("rmi://" + hostName + "/" + serviceName, hello);
			System.out.println("Group Chat RMI Server is running...");
		}
		catch(Exception e){
			System.out.println("Server had problems starting");
		}	
	}

	
	/**
	 * Start the RMI Registry
	 */
	public static void startRMIRegistry() {
		try{
			java.rmi.registry.LocateRegistry.createRegistry(1099);
			System.out.println("RMI Server ready");
		}
		catch(RemoteException e) {
			e.printStackTrace();
		}
	}
		
	
	//-----------------------------------------------------------
	/*
	 *   REMOTE METHODS
	 */
	
	/**send text to all connected clients
	 * @param name,nextPost(new msg)
	 */
	@Override
	public void updateChat(String name, String nextPost) throws RemoteException {
		String message =  name + " : " + nextPost + "\n";
		sendToAll(message);
	}

	
	/**
	 * Receive a new client and display client's name to the console
	 * send on to register method
	 * @param details
	 */
	@Override
	public void registerListener(String[] details) throws RemoteException {	
		System.out.println(details[0] + " has joined the chat session");
		registerChatter(details);
	}

	
	/**
	 * register the clients interface 
	 * store it in a reference for future message to be sent to
	 * send a test message for confirmation / test connection
	 * update user list
	 * @param details
	 */
	private void registerChatter(String[] details){		
		try{
			ClientIF nextClient = ( ClientIF )Naming.lookup("rmi://" + details[1] + "/" + details[2]);
			
			chatters.addElement(new Chatter(details[0], nextClient));
			
			nextClient.messageFromServer("[Server] : Welcome " + details[0]+ " you can now chat\n");
			
			sendToAll(details[0] + " joined the group\n");
			
			updateUserList();		
		}
		catch(RemoteException | MalformedURLException | NotBoundException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Update all clients by remotely invoking their
	 * updateUserList RMI method
	 */
	private void updateUserList() {
		String[] currentUsers = getUserList();	
		for(Chatter c : chatters){
			try {
				c.getClient().updateUserList(currentUsers);
			} 
			catch (RemoteException e) {
				e.printStackTrace();
			}
		}	
	}
	

	/**
	 * generate a String array of current users
	 * @return
	 */
	private String[] getUserList(){
		// generate an array of current users
		String[] allUsers = new String[chatters.size()];
		for(int i = 0; i< allUsers.length; i++){
			allUsers[i] = chatters.elementAt(i).getName();
		}
		return allUsers;
	}
	

	/**
	 * Send a message to all users
	 * @param newMessage
	 */
	public void sendToAll(String newMessage){	
		for(Chatter c : chatters){
			try {
				c.getClient().messageFromServer(newMessage);
			} 
			catch (RemoteException e) {
				e.printStackTrace();
			}
		}	
	}

	
	/**
	 * remove a client from the list, notify everyone
	 */
	@Override
	public void leaveChat(String userName) throws RemoteException{
		
		for(Chatter c : chatters){
			if(c.getName().equals(userName)){
				System.out.println("\n"+userName+" left");
				chatters.remove(c);
				break;
			}
		}		
		if(!chatters.isEmpty()){
			updateUserList();
		}			
	}
	

	/**
	 * A method to send a private message to selected clients
	 * @param array of index of selected users, msg to be send
	 */
	@Override
	public void sendPM(int[] privateGroup, String privateMessage) throws RemoteException{
		Chatter pc;
		for(int i : privateGroup){
			pc= chatters.elementAt(i);
			pc.getClient().messageFromServer(privateMessage);
		}
	}
	
}




