package com.sharing.write.vo;

public class City {
	private int ctCode;
	private String ctName;
	private int cCode;
	
	public City() {}
	public City(int ctCode, String ctName, int cCode) {
		this.ctCode = ctCode;
		this.ctName = ctName;
		this.cCode = cCode;
	}
	public int getCtCode() {
		return ctCode;
	}
	public void setCtCode(int ctCode) {
		this.ctCode = ctCode;
	}
	public String getCtName() {
		return ctName;
	}
	public void setCtName(String ctName) {
		this.ctName = ctName;
	}
	public int getcCode() {
		return cCode;
	}
	public void setcCode(int cCode) {
		this.cCode = cCode;
	}
	
	
}
