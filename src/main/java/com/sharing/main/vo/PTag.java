package com.sharing.main.vo;

public class PTag {
	
	private int ptNo;
	private int pNo;
	private int tbCode;
	
	public PTag() {}
	public PTag(int ptNo, int pNo, int tbCode) {
		this.ptNo = ptNo;
		this.pNo = pNo;
		this.tbCode = tbCode;
	}
	public int getPtNo() {
		return ptNo;
	}
	public void setPtNo(int ptNo) {
		this.ptNo = ptNo;
	}
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public int getTbCode() {
		return tbCode;
	}
	public void setTbCode(int tbCode) {
		this.tbCode = tbCode;
	}
	
	
}
