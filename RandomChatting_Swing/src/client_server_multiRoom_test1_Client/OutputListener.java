package client_server_multiRoom_test1_Client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * <pre>
 * 설명 : 출력을 관리해주는 스레드 클래스
 * <pre>
 *
 * @author Dahan Choi
 */
public class OutputListener {

	// Singleton
	private static OutputListener instance;

	private OutputListener() {}
	
	public static OutputListener getInstance() {
		if(instance == null) {
			synchronized (OutputListener.class) {
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
