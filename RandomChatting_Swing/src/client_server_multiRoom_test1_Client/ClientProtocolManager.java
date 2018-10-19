package client_server_multiRoom_test1_Client;

public class ClientProtocolManager {
	
	public static final String CONNECT_WITH_OPPONENT 	= "CON";
	public static final String DISCONNECT_WITH_OPPONENT = "DIS";
	public static final String MESSAGE_RECEIVE 			= "MES";
	public static final String DIVIDE_LINE				= "\n";
	
	public String getHeader(String inputData) {
		// 대문자로 변환해서 리턴해줄 것 대소문자 판별 두번 안하도록
		String header = inputData.split(DIVIDE_LINE)[0].toUpperCase();
		
		return header;
	}
	
	public String getBody(String inputData) {
		String body = inputData.split(DIVIDE_LINE)[1].toUpperCase();
		
		return body;
	}
	
}
