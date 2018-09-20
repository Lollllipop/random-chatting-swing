package client_server_multiRoom_test1_Server;

import java.net.Socket;
import java.util.concurrent.*;

public class TaskThreadPool {
	
	ExecutorService executorService;
	SocketList socketList;
	
	TaskThreadPool(SocketList socketList) {
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		this.socketList = socketList;
	}
	
	public void submitTask(Socket socket) {
		this.executorService.submit(this.createTask(socket));
	}
	
	public void closeThreadPool() {
		System.out.println("[쓰레드 풀 종료]");
		executorService.shutdown();
	}
	
	private Runnable createTask(Socket socket) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				String nickName;
				String message;

				nickName = socketList.readStream(socket);
				socketList.setNickName(nickName);
				
				while (true) {
					message = socketList.readStream(socket);
					socketList.sendMessageToAll(nickName, message); 
					System.out.println(nickName + " : " + message);
				}
			}
		};
		
		return runnable;
	}
	
}
