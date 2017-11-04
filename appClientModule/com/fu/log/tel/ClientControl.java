package com.fu.log.tel;

public class ClientControl extends BaseTel {

	private static ClientControl clientControl;
	public static ClientControl getInstance() {
		
		synchronized (ClientControl.class) {
			if(clientControl == null) {
				 clientControl = new ClientControl();
			}
		}
		
		return clientControl;
	}
	
	private ClientControl() {}
	
	public void sendData(String session,String data) {
		
		SocketClinet clinet = getClinet(session);
		if(null != clinet) {
			clinet.sendData(data);
		}
	}
}
