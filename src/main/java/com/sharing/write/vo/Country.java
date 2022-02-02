package com.sharing.write.vo;

public class Country {
	private int cCode;
	private String cName;
	
	public Country() {}
	public Country(int cCode, String cName) {
		this.cCode = cCode;
		this.cName = cName;
	}
	public int getcCode() {
		return cCode;
	}
	public void setcCode(int cCode) {
		this.cCode = cCode;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	
	
}
