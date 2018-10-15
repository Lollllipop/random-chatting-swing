package client_server_multiRoom_test1_Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * <pre>
 * 설명 : 서버와 소켓 연결만을 관리해주는 클래스
 * <pre>
 *
 * @author Dahan Choi
 */
public class ServerConnector{
	// 필드
	private static final String HOST = "localhost";
	private static final int 	PORT = 58001;
	
	private InputListener 	inputListener 	= null;
	private OutputListener	outputListener 	= null;
	private Socket 			socket 			= null;
	private OutputStream 	os 				= null;
	private InputStream 	is 				= null;
	
	// Singleton
	private static ServerConnector instance; 
	
	private ServerConnector() {};
	
	public static ServerConnector getInstance() {
		if(instance == null) {
			synchronized (ServerConnector.class) {
				if(instance == null) {
					instance = new ServerConnector();
				}
			}
		}
		return instance;
	}
	
	// 메소드
	public void connect() {
		try {
			System.out.println("[연결 요청]");
			
			socket = new Socket(HOST, PORT);
			
			System.out.println("[연결 성공]");
			
			os = socket.getOutputStream();
			is = socket.getInputStream();
			
			outputListener 	= OutputListener.getInstance();
			inputListener 	= InputListener.getInstance();
			
			outputListener.setOutputStream(os);
			inputListener.setInputStream(is);
		
			inputListener.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
