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
public class InputListener extends Thread {
	
	private static final String ENCODING_TYPE = "UTF-8";
	
	private InputStream 	is 				= null;
	private ChatManager 	chatManager 	= null;
	private ProtocolManager protocolManager	= new ProtocolManager();
	
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
	
	public void run() {
		while (true) {
			String inputData = getInput();
			
			// 여기서 protocol processing 처리를 해줘야 할듯
			String header 	= protocolManager.getHeader(inputData);
			String body 	= protocolManager.getBody(inputData);
			
			if (header.equals(ProtocolManager.CONNECT_WITH_OPPONENT)) {
				chatManager.setConnectedWithOpponent(true);
			} else if (header.equals(ProtocolManager.DISCONNECT_WITH_OPPONENT)) {
				chatManager.setConnectedWithOpponent(false);
			} else {
				chatManager.read(body);
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
			LinkedList<Byte> byteList = new LinkedList<Byte>();
			
			byte inputByteData;
			
			String message = null;
			
			while((inputByteData = (byte)is.read()) != -1) {
				byteList.add(inputByteData);
				if (is.available() == 0) {
					break;
				};
			}
			
			byte[] arrByteData  = new byte[byteList.size()];
			
			for(int i = 0; i < arrByteData.length; i++) {
				arrByteData[i] = byteList.poll();
			}
			
			message = new String(arrByteData, ENCODING_TYPE);
			
			return message;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
