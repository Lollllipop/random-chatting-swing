package client_server_multiRoom_test1_Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private ServerSocket serverSocket;
	
	private RoomManager 			roomManager 			= new RoomManager();
	private ConnectionRequestQueue 	connectionRequestQueue 	= ConnectionRequestQueue.getInstance();
	
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
				
				connectionRequestQueue.offer(socket);
				// 룸매니저에게 전달하기 생성하라고!
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
//			taskThreadPool.closeThreadPool();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
