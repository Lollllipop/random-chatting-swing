package client_server_multiRoom_test1_Server;

import java.util.*;

/**
 * <pre>
 * 설명 : 전체 클라이언트의 수 등을 관리하기 위한 클래스
 * <pre>
 *
 * @author Dahan Choi
 */
public class Clients {
	
	private ArrayList<Client> clients = new ArrayList<Client>();
	 
	private static Clients instance;
	 
	private Clients() {};
	 
	public static Clients getInstance() {
		if (instance == null) {
			synchronized (Clients.class) {
				if (instance == null) {
					instance = new Clients();
				}
			}
		}
		return instance;
	}
	 
	public void removeClient(Client client) {
		this.clients.remove(client);
	}
	 
	public void addClient(Client client) {
		this.clients.add(client);
	}
	 
	public int getCount() {
		return clients.size();
	}	
	  
}
