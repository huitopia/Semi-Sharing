package com.sharing.write.vo;

public class Landmark {
	private int lCode;
	private String lName;
	private int ctCode;
	
	public Landmark() {}
	public Landmark(int lCode, String lName, int ctCode) {
		this.lCode = lCode;
		this.lName = lName;
		this.ctCode = ctCode;
	}
	public int getlCode() {
		return lCode;
	}
	public void setlCode(int lCode) {
		this.lCode = lCode;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public int getCtCode() {
		return ctCode;
	}
	public void setCtCode(int ctCode) {
		this.ctCode = ctCode;
	}
	
	
}

