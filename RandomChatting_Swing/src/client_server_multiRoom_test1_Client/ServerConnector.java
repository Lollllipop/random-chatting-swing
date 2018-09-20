package client_server_multiRoom_test1_Client;

import client_server_multiRoom_test1_Server.ConnectionRequestQueue;

public class ServerConnector extends Thread {
	
	// Singleton
	private static ServerConnector instance; 
	
	private ServerConnector() {};
	
	public static ServerConnector getInstance() {
		if(instance == null) {
			synchronized (ConnectionRequestQueue.class) {
				if(instance == null) {
					instance = new ServerConnector();
				}
			}
		}
		return instance;
	}

	// 서버 연결
	public void connect() {
		// TODO Auto-generated method stub
		
	}

	// 서버 종료
	public void disconnect() {
		// TODO Auto-generated method stub
		
	}
	
	// 채팅 보내기
	
	// 채팅 받기
	
	// 상태 받기 (전역 상태 변경)
	
	// 전역 상태 = 상대와 연결되었는지 안되었는지
	
}
