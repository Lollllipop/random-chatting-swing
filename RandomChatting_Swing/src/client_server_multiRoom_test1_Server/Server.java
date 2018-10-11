package client_server_multiRoom_test1_Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <pre>
 * 설명 : 가장 베이스가 되는 서버역할을 할 클래스
 * <pre>
 *
 * @author Dahan Choi
 */
public class Server {
	
	// 필드
	private ServerSocket serverSocket;
	
	private ConnectionRequestQueue 	connectionRequestQueue 	= ConnectionRequestQueue.getInstance();
	private Clients					clients					= Clients.getInstance();
	private RoomManager 			roomManager 			= RoomManager.getInstance();
	private TaskThreadPool			taskThreadPool			= TaskThreadPool.getInstance();
	
	// 메소드
	public void start() {
		System.out.println("[서버 실행]");
		
		try {
			serverSocket = new ServerSocket(58001);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true) {
			System.out.println("[연결 대기]");
			try {
				Socket socket = serverSocket.accept();
				
				InetSocketAddress socketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
				System.out.println("[연결 성공] - " + socketAddress.getHostName() + " 이 접속");
				
				Client client = new Client(socket);
				
				clients.addClient(client);
				connectionRequestQueue.offer(client);
			} catch (IOException e) {
				e.printStackTrace();
				
				if (!serverSocket.isClosed()) {
					this.stop();
				}
				
				return;
			}
		}
	}
	
	public void stop() {
		System.out.println("[서버 종료]");
		try {
			taskThreadPool.closeThreadPool();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
