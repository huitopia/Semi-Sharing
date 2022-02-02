package com.sharing.main.vo;

import java.sql.Timestamp;

public class Rpost {
	private int rNo;
	private String rTitle;
	private Timestamp rDate;
	private String rText;
	private int pNo;
	private int cmNo;
	private int uNum;
	
	public Rpost() {}
	public Rpost(int rNo, String rTitle, Timestamp rDate, String rText, int pNo, int cmNo, int uNum) {
		this.rNo = rNo;
		this.rTitle = rTitle;
		this.rDate = rDate;
		this.rText = rText;
		this.pNo = pNo;
		this.cmNo = cmNo;
		this.uNum = uNum;
	}
	public int getrNo() {
		return rNo;
	}
	public void setrNo(int rNo) {
		this.rNo = rNo;
	}
	public String getrTitle() {
		return rTitle;
	}
	public void setrTitle(String rTitle) {
		this.rTitle = rTitle;
	}
	public Timestamp getrDate() {
		return rDate;
	}
	public void setrDate(Timestamp rDate) {
		this.rDate = rDate;
	}
	public String getrText() {
		return rText;
	}
	public void setrText(String rText) {
		this.rText = rText;
	}
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public int getCmNo() {
		return cmNo;
	}
	public void setCmNo(int cmNo) {
		this.cmNo = cmNo;
	}
	public int getuNum() {
		return uNum;
	}
	public void setuNum(int uNum) {
		this.uNum = uNum;
	}
	
	
}
