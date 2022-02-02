package com.sharing.main.vo;

import java.sql.Timestamp;

public class Comment {
	private int cmNo;
	private String cmText;
	private Timestamp cmDate;
	private int uNum;
	private int pNo;
	
	public Comment() {}
	public Comment(int cmNo, String cmText, Timestamp cmDate, int uNum, int pNo) {
		this.cmNo = cmNo;
		this.cmText = cmText;
		this.cmDate = cmDate;
		this.uNum = uNum;
		this.pNo = pNo;
	}
	public int getCmNo() {
		return cmNo;
	}
	public void setCmNo(int cmNo) {
		this.cmNo = cmNo;
	}
	public String getCmText() {
		return cmText;
	}
	public void setCmText(String cmText) {
		this.cmText = cmText;
	}
	public Timestamp getCmDate() {
		return cmDate;
	}
	public void setCmDate(Timestamp cmDate) {
		this.cmDate = cmDate;
	}
	public int getuNum() {
		return uNum;
	}
	public void setuNum(int uNum) {
		this.uNum = uNum;
	}
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	
	
}
