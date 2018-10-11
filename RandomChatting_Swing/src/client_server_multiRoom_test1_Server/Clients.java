package client_server_multiRoom_test1_Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.*;

/**
 * <pre>
 * 설명 : 전체 클라이언트의 수 등을 관리하기 위한 클래스
 * <pre>
 *
 * @author Dahan Choi
 */
public class Clients {
		
	// 필드
	private ArrayList<Client> clients = new ArrayList<Client>();
	 
	// Singleton
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
	 
	// 메소드
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
