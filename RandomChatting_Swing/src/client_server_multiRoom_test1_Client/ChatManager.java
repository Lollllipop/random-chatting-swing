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
	
	// Singleton
	public static ChatManager instance;
	
	private ChatManager() {};
	
	public static ChatManager getInstance() {
		if (instance == null) {
			synchronized (ChatManager.class) {
				if(instance == null) {
					instance = new ChatManager();
				}
			}
		}
		return instance;
	}
	
	public void setChatArea(JTextArea chatArea) {
		this.chatArea = chatArea;
	}
	
	// 필드 선언
	// TODO 전역 상태로 상대와 연결되었는지 안되었는지 이거 만들어야 함 꼭!
	private boolean 		isConnectedWithOpponent = false;
	private OutputListener 	outputListener 			= OutputListener.getInstance();
	private JTextArea		chatArea				= null;
	/*
	 * TODO 
	 * 인풋창은 입력 안되게 막아놓으면 될 것 같음. 아웃풋은 그럼 저절로 코드상으로 막으면 되니깐
	 */
	
	// 메소드 선언
	public boolean isConnectedWithOpponent() {
		return isConnectedWithOpponent;
	}

	public void setConnectedWithOpponent(boolean isConnectedWithOpponent) {
		this.isConnectedWithOpponent = isConnectedWithOpponent;
	}
	
	/**
	 * <pre>
	 * 설명 : 클라이언트로부터 서버로 출력이 이루어지는 메소드 
	 * <pre>
	 *
	 * @author	Dahan Choi
	 * @param	상대방의 입력
	 */
	public void read(String inputData) {
		chatArea.append("(상대방) : " + inputData);
	}
	
	/**
	 * <pre>
	 * 설명 : 서버로부터 전달된 입력을 인터페이스에 뿌려주는 메소드
	 * <pre>
	 *
	 * @author	Dahan Choi
	 * @param	출력할 메시지
	 */
	public void write(String message) {
		if (isConnectedWithOpponent) {
			outputListener.outputMessage(message);	
		}
	}
	// 상태 받기 (전역 상태 변경)
}
