package client_server_multiRoom_test1_Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

public class InputListener extends Thread{
	
	InputStream is 			= null;
	ChatManager chatManager = ChatManager.getInstance();
	
	public InputListener(InputStream is) {
		this.is = is;
	}

	public void run() {
		while (true) {
			// TODO chatManager에 전달해줘야함
			// TODO 어떤 데이터를 받는 건지 생각해야 함
			String inputData = getInput();
			chatManager.write(inputData);
		}
	}
	
	private String getInput() {
		// TODO 데이터를 받아서 내보내는 처리를 여기서 깔끔하게 할 것
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
