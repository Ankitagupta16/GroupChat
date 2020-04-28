package server;
import client.ClientIF;

/**
 * A class for every user in Group chat 
 * @author AnkitaGupta
 *
 */
public class Chatter {
	public String name;
	public ClientIF client;
	
	//constructor
	public Chatter(String name, ClientIF client){
		this.name = name;
		this.client = client;
	}

	
	//getters and setters
	public String getName(){
		return name;
	}
	public ClientIF getClient(){
		return client;
	}

}
