package client_server_multiRoom_test1_Client;

/**
 * <pre>
 * 설명 : IO를 관리해주는 인터페이스 역할을 하는 클래스
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

	// 채팅 받기
	public void write(String inputData) {
		// TODO Auto-generated method stub
		
	}
	
	// 채팅 보내기
	
	// 상태 받기 (전역 상태 변경)
	
	// 전역 상태 = 상대와 연결되었는지 안되었는지
}
