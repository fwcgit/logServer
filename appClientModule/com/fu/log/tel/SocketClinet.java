package com.fu.log.tel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import com.fu.log.LogPrint;

public class SocketClinet extends Client implements Runnable  {
	
	private Socket socket;
	
	private boolean isRun;
	
	private String name = "";
	
	private String session;
	
	private boolean isConnect = false;
	
	private long checkConnectTime = 60 * 1000;
	
	private Timer timer;
	
	private OutputStream os;
	
	public SocketClinet(Socket socket) {
		super();
		
		this.socket = socket;
		
	}
	
	@Override
	void start() {
		
		isConnect = true;
		isRun = true;
		exeRun(this);
		startTimer();
		
		sendData("-update-name-");
	}
	
	
	@Override
	void stop() {
		
		System.out.println(name + ":" + session+":remove and disconnect");
		
		isRun = false;
		
		try {
			
			if(null != socket) {
				socket.close();
			}
			
			socket = null;
			
			removeClient(session);
			
			stopTimer();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendData(String data) {
		
		try {
			
			if(null == os && isConnect) {
				os = socket.getOutputStream();
			}
			
			os.write(data.getBytes());
			os.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
			
			stop();
			
		}
	}

	@Override
	public void run() {
		
		try {
			
			
			InputStream is  = socket.getInputStream();
			
			while(isRun) {
				
				
				byte[] buff = new byte[1024 * 4];
				int len = 0;
				
				while((len = is.read(buff)) > 0) {
					
					isConnect = true;
					
					if(len > 1) {
						
						String str = new String(buff, 0, len);
						
						if(str.startsWith("-update-name-")) {
							String[] datas = str.split(":");
							if(datas.length >=2) {
								name = datas[1];
							}
						}
						
						LogPrint.getInstance().printf(name, name+">>"+str);
						
					}
					
					
					
				}
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			stop();
		}
				
		
	}
	
	
	protected void startTimer() {
		
		if(null != timer) {
			timer.cancel();
			timer = null;
		}
		
		timer = new Timer(false);
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				
				if(!isConnect) {
					stop();
				}
				
				isConnect = false;
			}
		}, checkConnectTime, checkConnectTime);
	}
	
	protected void stopTimer() {
		
		if(null != timer) {
			timer.cancel();
			timer = null;
		}
	}
	
	public InetAddress getInetAddress() {
		return socket.getInetAddress();
	}
	
	public boolean isConnected() {
		return socket.isConnected();
	}
	
	public boolean isClosed() {
		return socket.isClosed();
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
