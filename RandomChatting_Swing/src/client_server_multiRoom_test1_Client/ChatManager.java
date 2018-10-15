package client_server_multiRoom_test1_Client;

import javax.swing.JTextArea;

/**
 * <pre>
 * 설명 : InputListener, OutputListener와 Jframe 사이에서 IO를 관리해주는 인터페이스 역할을 하는 클래스
 * <pre>
 *
 * @author Dahan Choi
 */
public class ChatManager {
	// 필드
	private boolean 		isConnectedWithOpponent = false;
	private OutputListener 	outputListener 			= OutputListener.getInstance();
	private JTextArea		chatArea				= null;
	
	// 생성자
	public ChatManager(JTextArea chatArea) {
		this.chatArea = chatArea;
	};
	
	// 메소드
	public boolean isConnectedWithOpponent() {
		return isConnectedWithOpponent;
	}

	public void setConnectedWithOpponent(boolean isConnectedWithOpponent) {
		this.isConnectedWithOpponent = isConnectedWithOpponent;
	}
	
	public void read(String inputData) {
		chatArea.append("(상대방) : " + inputData);
	}
	
	public void write(String message) {
		if (isConnectedWithOpponent) {
			chatArea.append("(나) : " + message);
			outputListener.outputMessage(message);	
		}
	}
	
}
