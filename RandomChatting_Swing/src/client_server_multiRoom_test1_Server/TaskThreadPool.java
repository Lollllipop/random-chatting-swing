package client_server_multiRoom_test1_Server;

import java.net.Socket;
import java.util.concurrent.*;

/**
 * <pre>
 * 설명 : 스레드풀을 이용하기 위한 클래스
 * <pre>
 *
 * @author Dahan Choi
 */
public class TaskThreadPool {
	
	// 필드
	ExecutorService executorService;
	
	// Singleton
	private static TaskThreadPool instance;
	
	private TaskThreadPool() {
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	};
	
	public static TaskThreadPool getInstance() {
		if (instance == null) {
			synchronized (TaskThreadPool.class) {
				if (instance == null) {
					instance = new TaskThreadPool();
				}
			}
		}
		return instance;
	}
	
	// 메소드
	public void submitTask(Runnable runnable) {
		this.executorService.submit(runnable);
	}
	
	public void closeThreadPool() {
		executorService.shutdown();
	}
	
}
