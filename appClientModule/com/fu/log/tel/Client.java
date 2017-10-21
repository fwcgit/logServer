package com.fu.log.tel;

import java.util.concurrent.ExecutorService;

public abstract class Client extends BaseTel {
	
	private ExecutorService executorService = ThreadPool.getInstance().getExecutorService();
	

	
	public void exeRun(Runnable runnable) {
		executorService.execute(runnable);
	}
	
	abstract void start();
	abstract void stop();
	
	
}
