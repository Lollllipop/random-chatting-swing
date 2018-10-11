package client_server_multiRoom_test1_Server;

/**
 * <pre>
 * 설명 : 각 방을 나타내는 클래스 
 * <pre>
 *
 * @author Dahan Choi
 */
public class Room {
	
	// 필드
	private Client client1;
	private Client client2;
	
	// 생성자
	Room(Client client1, Client client2) {
		System.out.println("[매칭 성공]");
		this.client1 = client1;
		this.client2 = client2;
		
		client1.setConnectedRoom(this);
		client2.setConnectedRoom(this);
	}
	
	// 메소드
	public void sendMassageToOpponent(Client client, String message) {
		if (client == client1) {
			client2.sendMessage(message);
		} else {
			client1.sendMessage(message);
		}
	}
}
