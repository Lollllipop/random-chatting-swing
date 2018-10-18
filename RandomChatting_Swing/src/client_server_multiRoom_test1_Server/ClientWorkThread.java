package client_server_multiRoom_test1_Server;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

public class ClientWorkThread implements Runnable {
	
	private Client 		client 	= null;
	private Room 		room 	= null;
	private InputStream is 		= null;
	
	ClientWorkThread(Client client) {
		this.client 	= client;
		this.is		 	= client.getInputStream();
	}
	
	@Override
	public void run() {
		while (true) {
			room.sendMassageToOpponent(client, receiveMessage());
		}
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}

	public Room getRoom() {
		return room;
	}
	
	private byte[] receiveMessage() {
		 try {
			LinkedList<Byte> byteList = new LinkedList<Byte>();
			byte inputByteData;
			
			try {
				while((inputByteData = (byte)is.read()) != -1) {
					byteList.add(inputByteData);
					if (is.available() == 0) {
						break;
					};
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			byte[] arrByteData = new byte[byteList.size()];
			
			for(int i = 0; i < arrByteData.length; i++) {
				arrByteData[i] = byteList.poll();
			}
			
			return arrByteData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
