package com.fu.log.tel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
	
	private ExecutorService executorService = Executors.newFixedThreadPool(100);
	
	private static ThreadPool pool;
	
	public static ThreadPool getInstance() {
		return pool == null ? pool = new ThreadPool() : pool;
	}
	
	private ThreadPool() {}

	public ExecutorService getExecutorService() {
		return executorService;
	};
	
	
	
}
