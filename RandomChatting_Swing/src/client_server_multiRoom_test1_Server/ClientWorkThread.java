package client_server_multiRoom_test1_Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

public class ClientWorkThread implements Runnable {
	
	// 필드
	private Client client = null;
	private Room room = null;
	private InputStream is = null;
	
	// 생성자
	ClientWorkThread(Client client) {
		this.client = client;
		this.is = client.getInputStream();
	}
	
	// 메소드
	@Override
	public void run() {
		String message;
		
		while (true) {
			room.sendMassageToOpponent(client, receiveMessage());
		}
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	private String receiveMessage() {
		 try {
			LinkedList<Byte> byteArrList = new LinkedList<Byte>();
			byte inputByteData;
			String message = null;
			try {
				while((inputByteData = (byte)is.read()) != -1) {
					byteArrList.add(inputByteData);
					if (is.available() == 0) {
						break;
					};
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			byte[] arrByteData = new byte[byteArrList.size()];
			
			for(int i = 0; i < arrByteData.length; i++) {
				arrByteData[i] = byteArrList.poll();
			}
			
			try {
				message = new String(arrByteData, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			return message;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
