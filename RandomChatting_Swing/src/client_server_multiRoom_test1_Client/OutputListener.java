package client_server_multiRoom_test1_Client;

import java.io.OutputStream;

public class OutputListener extends Thread{
	
	OutputStream os = null;

	public OutputListener(OutputStream os) {
		this.os = os;
	}

	public void run() {
		
	}
	
}
