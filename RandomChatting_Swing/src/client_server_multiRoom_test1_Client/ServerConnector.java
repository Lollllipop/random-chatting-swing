package client_server_multiRoom_test1_Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import client_server_multiRoom_test1_Server.ConnectionRequestQueue;

/**
 * <pre>
 * 설명 : 서버와 소켓 연결을 관리해주는 클래스
 * <pre>
 *
 * @author Dahan Choi
 */
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
	private Socket 			socket 			= null;
	private OutputStream 	os 				= null;
	private InputStream 	is 				= null;
	
	private String 	HOST = "localhost";
	private int 	PORT = 58001;
	
	// 메소드 선언
	/**
	 * <pre>
	 * 설명 : 서버와 연결을 해주는 메소드
	 * <pre>
	 *
	 * @author	Dahan Choi
	 */
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

	
	/**
	 * <pre>
	 * 설명 : 서버와 연결을 끊어주는 메소드
	 * <pre>
	 *
	 * @author	Dahan Choi
	 * @throws	OutputStream과 InputStream이 socket이 끊어지기 전에 끊어지면 에러 발생 가능성 존재하기 때문에 IOException 처리를 함
	 */
	public void disconnect() {
		try {
			os.close(); // 에러 발생 가능성 있음 조심
			is.close();
			socket.close(); // 서버에서 예외 처리 잘 해줘야 할듯
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
