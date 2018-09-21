package client_server_multiRoom_test1_Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import client_server_multiRoom_test1_Server.ConnectionRequestQueue;

public class ServerConnector{
	
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
	
	// 필드 선언
	private InputListener 	inputListener 	= null;
	private OutputListener	outputListener 	= null;
	
	private String 	HOST = "localhost";
	private int 	PORT = 58001;
	
	private Socket 			socket 	= null;
	private OutputStream 	os 		= null;
	private InputStream 	is 		= null;
	
	// 서버 연결
	public void connect() {
		try {
			System.out.println("[연결 요청]");
			
			socket = new Socket(HOST, PORT);
			
			System.out.println("[연결 성공]");
			
			os = socket.getOutputStream();
			is = socket.getInputStream();
			
			outputListener = new OutputListener(os);
			inputListener = new InputListener(is);
			
			outputListener.start();
			inputListener.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
