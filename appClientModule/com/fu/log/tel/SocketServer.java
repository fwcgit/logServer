package com.fu.log.tel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.fu.log.config.AppConfig;

public class SocketServer extends ServerThread implements IServer  {

	private ServerSocket serverSocket;
	private boolean isRunServer;
	private Thread thread;
	
	
	@Override
	public void initSocket() {
		
		System.out.println("init server socket");
		
		try {
			serverSocket = new ServerSocket(AppConfig.port);
		} catch (IOException e) {
			e.printStackTrace();
			
			System.out.println("init server socket fail");
			
		}
	}

	@Override
	public void startServer() {
		
		System.out.println("start server socket");
		
		isRunServer = true;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void stopServer() {
		
		System.out.println("stop server socket");
		
		isRunServer = false;
		thread = null;
	}

	@Override
	public void run() {
		super.run();
		
		while(isRunServer) {
			if(null != serverSocket) {
				try {
					
					Socket clinet = serverSocket.accept();
					SocketClinet socketClinet = new SocketClinet(clinet);
					socketClinet.start();
					acceptClient(socketClinet);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
