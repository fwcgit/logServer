package com.fu.log;

public class LogPrint {
	
	private String filter;
	
	private static LogPrint logPrint;
	
	public static LogPrint getInstance(){
		
		return logPrint == null ? logPrint = new LogPrint() : logPrint;
	}
	
	public void printf(String filter,String msg){
		
		if(this.filter == null || this.filter.equals("*")){
			System.out.println(msg);
		}else if(this.filter.equals(filter)){
			System.out.println(msg);
		}
		
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}
	
	
}
