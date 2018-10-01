package client_server_multiRoom_test1_Client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import client_server_multiRoom_test1_Server.ConnectionRequestQueue;

/**
 * <pre>
 * 설명 : 출력을 관리해주는 스레드 클래스
 * <pre>
 *
 * @author Dahan Choi
 */
// TODO 출력의 경우 그냥 static이나 singleton으로 입력과는 달리 그냥 이벤트가 있을시에만 실행되도록 만들면 될 듯
public class OutputListener {

	// Singleton
	private static OutputListener instance;

	private OutputListener() {}
	
	public static OutputListener getInstance() {
		if(instance == null) {
			synchronized (ConnectionRequestQueue.class) {
				if(instance == null) {
					instance = new OutputListener();
				}
			}
		}
		return instance;
	}
	
	public void setOutputStream(OutputStream os) {
		this.os = os;
	}
	
	// 필드
	private OutputStream 	os 			= null;
	private ChatManager 	chatManager = ChatManager.getInstance();
	
	public void outputMessage(String message) {
		try {
			byte[] byteArr = message.getBytes("UTF-8");
			os.write(byteArr);
			os.flush(); 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
}
