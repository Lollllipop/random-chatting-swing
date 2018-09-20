package client_server_multiRoom_test1_Server;

import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectionRequestQueue {
	
	private Queue<Socket> queue = new LinkedList<Socket>();
	
	private static ConnectionRequestQueue instance; // Singleton
	
	private ConnectionRequestQueue() {}
	
	public static ConnectionRequestQueue getInstance() {
		if(instance == null) {
			synchronized (ConnectionRequestQueue.class) {
				if(instance == null) {
					instance = new ConnectionRequestQueue();
				}
			}
		}
		return instance;
	}
	
	public void offer(Socket socket) {
		queue.offer(socket);
	}
	
	public Socket poll() {
		return queue.poll();
	}
	
}
