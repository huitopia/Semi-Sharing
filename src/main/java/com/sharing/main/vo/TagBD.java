package com.sharing.main.vo;

public class TagBD {
	private int tbCode;
	private String tbName;
	private int thCode;
	
	public TagBD() {}
	public TagBD(int tbCode, String tbName, int thCode) {
		this.tbCode = tbCode;
		this.tbName = tbName;
		this.thCode = thCode;
	}
	public int getTbCode() {
		return tbCode;
	}
	public void setTbCode(int tbCode) {
		this.tbCode = tbCode;
	}
	public String getTbName() {
		return tbName;
	}
	public void setTbName(String tbName) {
		this.tbName = tbName;
	}
	public int getThCode() {
		return thCode;
	}
	public void setThCode(int thCode) {
		this.thCode = thCode;
	}
	
	
}
