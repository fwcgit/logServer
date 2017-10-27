package com.fu.log.tel;

import java.net.InetAddress;
import java.util.Map;

public class BaseTel {
	
	private Map<String, SocketClinet> clientMap = SessionTab.getInstance().getClientMap(); 
	
	public void acceptClient(SocketClinet clinet) {
		
	
		if(clinet.isConnected() && !clinet.isClosed()) {
			
			System.out.println("request clinet ip = " + clinet.getInetAddress().getHostAddress());
			String session = createSession(clinet.getInetAddress());
			clinet.setSession(session);
			
			if(clientMap.containsKey(session)){
				
				SocketClinet socketClinet = clientMap.get(session);
				
				System.out.println("remove clinet session = " + socketClinet.getSession());
				
				socketClinet.closeSocket();
				clientMap.remove(session);
				
			}else{
				
				System.out.println("accept clinet session = " + session);
				clientMap.put(session, clinet);
			}
			
		}
	}
	
	
	public void removeClient(String session) {
		
		if(clientMap.containsKey(session)) {
			@SuppressWarnings("unused")
			SocketClinet clinet = clientMap.get(session);
			clinet = null;
			clientMap.remove(session);
		}
		
	}
	
	
	public void closeClient(String session) {
		
		if(clientMap.containsKey(session)) {
			
			SocketClinet clinet = clientMap.get(session);
			
			System.out.println(clinet.getName() + ":" + session+":remove and disconnect");

			clinet.closeSocket();
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
		
		StringBuffer sb = new StringBuffer();
	
		for(int i = 0; i < ips.length ; i++) {
			
			String hex = Integer.toHexString(ips[i]+ randomKey[i % randomKey.length]);
			if(hex.length() > 2) {
				hex = hex.substring(0, 2);
			}
			
			sb.append(hex);
		}
		
		return sb.toString();
	}
}
