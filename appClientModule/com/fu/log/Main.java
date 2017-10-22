package com.fu.log;

import java.util.Scanner;

import com.fu.log.tel.ClientControl;
import com.fu.log.tel.SessionTab;
import com.fu.log.tel.SocketServer;


public class Main {
	
	private static Scanner sc;

	public static void main(String[] args) {
		
		SocketServer server = new SocketServer();
		server.initSocket();
		server.startServer();
		
		 sc = new Scanner(System.in);
		 
		 while(true) {
			 
			 String cmd = sc.nextLine();
			 
			 if(cmd.startsWith("list")) {
				 
				 SessionTab.getInstance().printClientList();
				 
			 }else if(cmd.startsWith("send")) {
				 
				 String[] strs = cmd.split(" ");
				 
				 if(strs.length == 3) {
					 
					 ClientControl.getInstance().sendData(strs[1], strs[2]);
					 
				 }else {
					 
					 System.err.println("send [session] [msg]");
					 
				 }
				
			 }else if(cmd.startsWith("close")) {
				 
				 String[] strs = cmd.split(" ");
				 
				 if(strs.length == 2) {
					 
					 ClientControl.getInstance().sendData(strs[1],"-close-");
					 
				 }else {
					 
					 System.err.println("send [session]");
					 
				 }
			 }else if(cmd.startsWith("filter")) {
				 
				 String[] strs = cmd.split(" ");
				 
				 if(strs.length == 2) {
					 
					LogPrint.getInstance().setFilter(strs[1]);
					 
				 }else {
					 
					 System.err.println("filter [name]");
					 
				 }
 
			 } else if(cmd.equals("exit")) {
				 System.exit(0);
			 }
			 
		 }
		 

	}

}