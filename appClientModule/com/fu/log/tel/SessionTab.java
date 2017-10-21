package com.fu.log.tel;

import java.util.HashMap;
import java.util.Map;

public class SessionTab {
	
	private Map<String, SocketClinet> clientMap = new HashMap<String, SocketClinet>();
	
	private static SessionTab sessionTab;
	
	public static SessionTab getInstance() {
		return sessionTab == null ? sessionTab = new SessionTab() : sessionTab;
	}
	
	private SessionTab() {};

	public Map<String, SocketClinet> getClientMap() {
		return clientMap;
	}
	
	
	public void printClientList() {
		
		int i = 0;

		if(clientMap.isEmpty()) {
			
			System.out.println("no client");
			
			return;
		}
		
		for(String key : clientMap.keySet()) {
			
			SocketClinet clinet = clientMap.get(key);
			
			System.out.println((i++)+"¡¢"+clinet.getName()+"-----"+clinet.getSession());
		}
		
	}
	
}
