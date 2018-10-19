package client_server_multiRoom_test1_Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * <pre>
 * 설명 : 클라이언트 클래스 
 * <pre>
 *
 * @author Dahan Choi
 */
public class Client {
	
//	private Socket 				socket;
	private ClientWorkThread 	workThread;
	private InputStream 		is;
	private OutputStream 		os; 
	
	public Client(Socket socket) {
		try {
			this.is 			= socket.getInputStream();
			this.os 			= socket.getOutputStream();
			this.workThread		= new ClientWorkThread(this);
			
			setWorkThreadtoThreadPool(workThread);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void setWorkThreadtoThreadPool(Runnable workThread) {
		TaskThreadPool.getInstance().submitTask(workThread);
	}
	
	public void setConnectedRoom(Room room) {
		this.workThread.setRoom(room);
	}
	
	public void sendMessage(byte[] messageBytes) {
		try {
			os.write(messageBytes);
			os.flush(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public InputStream getInputStream() {
		return is;
	}

}
