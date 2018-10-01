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
	private ChatManager chatManager = ChatManager.getInstance();
	
	public InputListener(InputStream is) {
		this.is = is;
	}
	
	// 메소드
	public void run() {
		while (true) {
			String inputData = getInput();
			
			if (inputData.equals("connectedwithopponent")) {
				chatManager.setConnectedWithOpponent(true);
			} else if (inputData.equals("disconnectedwithopponent")) {
				chatManager.setConnectedWithOpponent(false);
			} else {
				chatManager.read(inputData); // 이게 이제 인터페이스에 데이터 전달
			}
		}
	}
	
	/**
	 * <pre>
	 * 설명 : 소켓으로 부터 데이터를 받아 String 형태로 만들어 전달해주는 메소드 / 데이터 전달받아 가공하는 성능 높이려면 이 메소드 처리
	 * <pre>
	 *
	 * @author	Dahan Choi
	 * @return	전달된 문자열
	 * @throws	인코딩에 대한 예외처리
	 */
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
