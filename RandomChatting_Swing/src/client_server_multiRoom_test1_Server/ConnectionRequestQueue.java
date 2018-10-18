package client_server_multiRoom_test1_Server;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <pre>
 * 설명 : 방을 찾으려는 클라이언트들의 큐
 * <pre>
 *
 * @author Dahan Choi
 */
public class ConnectionRequestQueue {
	
	// TODO 동기화 처리 해줘야함 레이스 컨디션 발생 가능성 있으므로 메인 스레드와 클라이언트 스레드 사이에서
	// TODO 이 클래스가 RoomManager를 가지고 요청을 부탁하는게 나을듯
	
	private Queue<Client> 	queue 		= new LinkedList<Client>();
	private RoomManager 	roomManager = RoomManager.getInstance();
	
	private static ConnectionRequestQueue instance;
	
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
	
	public void offer(Client client) {
		queue.offer(client);
		
		if (getCount() >= 2) {
			roomManager.createRoom(poll(), poll());
		}
	}
	
	public Client poll() {
		return queue.poll();
	}
	
	public int getCount() {
		return queue.size();
	}
	
}
