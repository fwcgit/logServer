package com.fu.log;

import java.util.Scanner;

import com.fu.log.tel.ClientControl;
import com.fu.log.tel.SessionTab;
import com.fu.log.tel.SocketServer;

public class Main {
	public static void main(String[] args) {
		
		SocketServer server = new SocketServer();
		server.initSocket();
		server.startServer();
		
		 Scanner sc=new Scanner(System.in);
		 
		 while(true) {
			 
			 String cmd = sc.nextLine();
			 
			 if(cmd.startsWith("list")) {
				 
				 SessionTab.getInstance().printClientList();
				 
			 }else if(cmd.startsWith("send")) {
				 
				 String[] strs = cmd.split(" ");
				 
				 if(strs.length == 3) {
					 
					 ClientControl.getInstance().sendData(strs[1], strs[2]);
					 
				 }else {
					 
					 System.out.println("send [session] [msg]");
					 
				 }
				
			 }else if(cmd.startsWith("close")) {
				 
				 String[] strs = cmd.split(" ");
				 
				 if(strs.length == 2) {
					 
					 ClientControl.getInstance().sendData(strs[1],"-close-");
					 
				 }else {
					 
					 System.out.println("send [session]");
					 
				 }
			 }else if(cmd.equals("exit")) {
				 System.exit(0);
			 }
			 
		 }
		 

	}

}