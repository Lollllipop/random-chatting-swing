package client_server_multiRoom_test1_Server;

import java.io.UnsupportedEncodingException;

public class ServerProtocolManager {
	
	public static final String CONNECT_WITH_OPPONENT 	= "CON";
	public static final String DISCONNECT_WITH_OPPONENT = "DIS";
	public static final String MESSAGE_RECEIVE 			= "MES";
	
	private static final String DIVIDE_LINE				= "\n";
	
	public byte[] makeConnectMessage() {
		StringBuilder message = new StringBuilder();
		
		message.append(CONNECT_WITH_OPPONENT);
		message.append(DIVIDE_LINE);
		
		return stringToBytes(message);
	}
	
	public byte[] makeDisConnectMessage() {
		StringBuilder message = new StringBuilder();
		
		message.append(DISCONNECT_WITH_OPPONENT);
		message.append(DIVIDE_LINE);
		
		return stringToBytes(message);
	}
	
	public byte[] makeMessage(String message) {
		StringBuilder tempMessage = new StringBuilder();
		
		tempMessage.append(MESSAGE_RECEIVE);
		tempMessage.append(DIVIDE_LINE);
		tempMessage.append(message);
		
		return stringToBytes(tempMessage);
	}
	
	private byte[] stringToBytes(StringBuilder data) {
		byte[] bytes 	= null;
		String tempData = data.toString();
		
		try {
			bytes = tempData.getBytes(Config.CHARACTER_ENCODING_TYPE);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return bytes;
	}
	
}
