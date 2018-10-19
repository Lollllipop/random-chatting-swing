package client_server_multiRoom_test1_Server;

import java.util.ArrayList;

/**
 * <pre>
 * 설명 : 방을 관리하는 클래스
 * <pre>
 *
 * @author Dahan Choi
 */
public class RoomManager { 
	
	private ArrayList<Room> Rooms 		= new ArrayList<Room>();
	private int 			roomCount 	= 0;
	
	private static RoomManager instance;
	
	private RoomManager() {};
	
	public static RoomManager getInstance() {
		if (instance == null) {
			synchronized (RoomManager.class) {
				if (instance == null) {
					instance = new RoomManager();
				}
			}
		}
		return instance;
	}
	
	public void createRoom(Client client1, Client client2) {
		ServerProtocolManager protocolManager = new ServerProtocolManager();
		Room room = new Room(client1, client2);
		Rooms.add(room);
		
		byte[] messageBytes = protocolManager.makeConnectMessage();
		room.sendMassageToAll(messageBytes);
	}
	
	public void deleteRoom(Room room) {
		Rooms.remove(room);
	}
	
	public int getRoomCount() {
		return this.roomCount;
	}

}
