package client_server_multiRoom_test1_Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

/**
 * <pre>
 * 설명 : 입력을 관리해주는 스레드 클래스
 * <pre>
 *
 * @author Dahan Choi
 */
public class InputListener extends Thread{
	// 필드
	private InputStream is 			= null;
	private ChatManager chatManager = null;
	
	// 생성자
	private static InputListener instance;
	
	private InputListener() {};
	
	public static InputListener getInstance() {
		if(instance == null) {
			synchronized (InputListener.class) {
				if(instance == null) {
					instance = new InputListener();
				}
			}
		}
		return instance;
	}
	
	// 메소드
	public void run() {
		while (true) {
			String inputData = getInput();
			
			if (inputData.equals("connect")) { // 이게 바로 프로토콜 대화 형식을 맞추는 것
				chatManager.setConnectedWithOpponent(true);
				System.out.println("[상대방과 매칭완료]");
			} else if (inputData.equals("disconnect")) {
				chatManager.setConnectedWithOpponent(false);
				System.out.println("[상대방과 연결종료]");
			} else {
				chatManager.read(inputData);
			}
		}
	}
	
	public void setInputStream(InputStream is) {
		this.is = is;
	}
	
	public void setChatManager(ChatManager chatManager) {
		this.chatManager = chatManager;
	}
	
	private String getInput() {
		try {
			LinkedList<Byte> byteArrList = new LinkedList<Byte>();
			
			byte inputByteData;
			
			String resultMessage 	= null;
			String nickName 		= null;
			String message 			= null;
			
			while((inputByteData = (byte)is.read()) != -1) {
				byteArrList.add(inputByteData);
				if (is.available() == 0) {
					break;
				};
			}
			
			byte[] arrByteData  = new byte[byteArrList.size()];
			
			for(int i = 0; i < arrByteData.length; i++) {
				arrByteData[i] = byteArrList.poll();
			}
			
			resultMessage = new String(arrByteData, "UTF-8");
			int commaIndex = resultMessage.indexOf(",");
			nickName = resultMessage.substring(0, commaIndex);
			message = resultMessage.substring(commaIndex + 1, resultMessage.length());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
