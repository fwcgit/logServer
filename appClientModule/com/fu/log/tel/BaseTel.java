package com.fu.log.tel;

import java.net.InetAddress;
import java.util.Map;
import java.util.Random;

public class BaseTel {
	
	private Map<String, SocketClinet> clientMap = SessionTab.getInstance().getClientMap(); 
	
	public void acceptClient(SocketClinet clinet) {
		
		System.out.println("accept clinet ip = " + clinet.getInetAddress().getHostAddress());
		
		String session = createSession(clinet.getInetAddress());
		
		System.out.println("accept clinet session = " + session);

		clinet.setSession(session);
		
		if(clinet.isConnected() && !clinet.isClosed()) {
			
			if(clientMap.containsKey(session)) {
				SocketClinet socketClinet = clientMap.get(session);
				socketClinet.stop();
				clientMap.remove(session);
			}
			
			clientMap.put(session, clinet);
		}
	}
	
	
	public void removeClient(String session) {
		
		if(clientMap.containsKey(session)) {
			SocketClinet clinet = clientMap.get(session);
			clinet = null;
			clientMap.remove(session);
		}
		
	}
	
	
	public void closeClient(String session) {
		
		if(clientMap.containsKey(session)) {
			SocketClinet clinet = clientMap.get(session);
			clinet.stop();
			clinet = null;
			clientMap.remove(session);
		}
		
	}
	
	public SocketClinet getClinet(String session) {
		if(clientMap.containsKey(session)) {
			return clientMap.get(session);
		}
		return null;
	}
		
		
	private String createSession(InetAddress address) {
		
		int[] randomKey = new int[] {2,4,6,5,9,3,5,4,3};
		String ip = address.getHostAddress();
		byte[] ips = ip.getBytes();
		
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < ips.length ; i++) {
			
			String hex = Integer.toHexString(ips[i]+random.nextInt(randomKey.hashCode()));
			if(hex.length() > 2) {
				hex = hex.substring(0, 2);
			}
			
			sb.append(hex);
		}
		
		return sb.toString();
	}
}
