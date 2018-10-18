package client_server_multiRoom_test1_Client;

public class ProtocolManager {
	
	public static final String CONNECT_WITH_OPPONENT 	= "CON";
	public static final String DISCONNECT_WITH_OPPONENT = "DIS";
	public static final String MESSAGE_RECEIVE 			= "MES";
	
	public String getHeader(String inputData) {
		// 대문자로 변환해서 리턴해줄 것 대소문자 판별 두번 안하도록
		System.out.println(inputData);
		return null;
	}
	
	public String getBody(String inputData) {
		return null;
	}
	
}
