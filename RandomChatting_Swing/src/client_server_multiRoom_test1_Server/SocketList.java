package client_server_multiRoom_test1_Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.*;

public class SocketList {
	
	 private HashMap<String, Socket> sockets = new HashMap<String, Socket>();
	 private String tempName = "tempNickName";
	 
	 public void sendMessageToAll(String nickName, String message) {
		 Socket s = null;
		 String resultMessage = null;
		 OutputStream os = null;
		 for (String key : sockets.keySet()) {
			 try {
				s = sockets.get(key);
				os = s.getOutputStream();
				
				resultMessage = nickName + "," + message;
				byte[] byteArr = resultMessage.getBytes("UTF-8");
				os.write(byteArr);
				os.flush(); 
			} catch (Exception e) {
				e.printStackTrace();
				try {
					os.close();
				} catch (IOException eee) {
					eee.printStackTrace();
				}
				
			} finally {
			}
		}
	}
	 
	 public void removeSocket(String key) {
		 this.sockets.remove(key);
	 }
	 
	 public void addSocket(Socket socket) {
		 this.sockets.put(tempName, socket);
	 }
	 
	public void setNickName(String nickName) {
		Socket socket = sockets.remove(tempName);
		sockets.put(nickName, socket);
	}
	 
	 public String readStream(Socket socket) { // 어떤 길이의 글이 들어와도 잘 읽기 위함
		 InputStream is = null;
		 try {
			LinkedList<Byte> byteArrList = new LinkedList<Byte>();
			is = socket.getInputStream();
			byte inputByteData;
			String message = null;
			try {
				while((inputByteData = (byte)is.read()) != -1) {
					byteArrList.add(inputByteData);
					if (is.available() == 0) {
						break;
					};
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			byte[] arrByteData = new byte[byteArrList.size()];
			
			for(int i = 0; i < arrByteData.length; i++) {
				arrByteData[i] = byteArrList.poll();
			}
			
			try {
				message = new String(arrByteData, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			return message;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	 }
}
