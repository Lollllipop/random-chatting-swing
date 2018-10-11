package client_server_multiRoom_test1_Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.LinkedList;

/**
 * <pre>
 * 설명 : 클라이언트 클래스
 * <pre>
 *
 * @author Dahan Choi
 */
public class Client {
	
	// 필드
	private Socket 				socket;
	private ClientWorkThread 	workThread;
	private Room 				connectedRoom;
	private InputStream 		is;
	private OutputStream 		os; 
	
	// 생성자
	public Client(Socket socket) {
		try {
			this.socket 		= socket;
			this.is 			= socket.getInputStream();
			this.os 			= socket.getOutputStream();
			this.workThread		= new ClientWorkThread(this);
			
			setWorkThreadtoThreadPool(workThread);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 메소드
	private void setWorkThreadtoThreadPool(Runnable workThread) {
		TaskThreadPool.getInstance().submitTask(workThread);
	}
	
	public void setConnectedRoom(Room room) {
		this.connectedRoom = room;
		this.workThread.setRoom(room);
	}
	
	public void sendMessage(String message) {
		try {
			byte[] bytes = message.getBytes("UTF-8");
			os.write(bytes);
			os.flush(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public InputStream getInputStream() {
		return is;
	}

}
