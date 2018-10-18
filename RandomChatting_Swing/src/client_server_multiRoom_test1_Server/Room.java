package client_server_multiRoom_test1_Server;

/**
 * <pre>
 * 설명 : 각 방을 나타내는 클래스 
 * <pre>
 *
 * @author Dahan Choi
 */
public class Room {
	
	private Client client1;
	private Client client2;
	
	Room(Client client1, Client client2) {
		System.out.println("[매칭 성공]");
		this.client1 = client1;
		this.client2 = client2;
		
		client1.setConnectedRoom(this);
		client2.setConnectedRoom(this);
	}
	
	public void sendMassageToOpponent(Client client, byte[] messageBytes) {
		if (client == client1) {
			client2.sendMessage(messageBytes);
		} else {
			client1.sendMessage(messageBytes);
		}
	}
}
