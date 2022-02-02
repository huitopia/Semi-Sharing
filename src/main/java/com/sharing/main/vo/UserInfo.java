package com.sharing.main.vo;

import java.sql.Timestamp;

public class UserInfo {
	private int uNum;
	private String uId;
	private String uPw;
	private String uName;
	private Timestamp uBt;						
	private Timestamp uJDate;				
	private String uMail;
	private int uPhone;
	private String uPropic;
	
	public UserInfo() {}
	public UserInfo(int uNum, String uId, String uPw, String uName, Timestamp uBt, Timestamp uJDate, String uMail, int uPhone, String uPropic) {
		this.uNum = uNum;
		this.uId = uId;
		this.uPw = uPw;
		this.uName = uName;
		this.uBt = uBt;
		this.uJDate = uJDate;
		this.uMail = uMail;
		this.uPhone = uPhone;
		this.uPropic = uPropic;
		
	}
	public int getuNum() {
		return uNum;
	}
	public void setuNum(int uNum) {
		this.uNum = uNum;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getuPw() {
		return uPw;
	}
	public void setuPw(String uPw) {
		this.uPw = uPw;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public Timestamp getuBt() {
		return uBt;
	}
	public void setuBt(Timestamp uBt) {
		this.uBt = uBt;
	}
	public Timestamp getuJDate() {
		return uJDate;
	}
	public void setuJDate(Timestamp uJDate) {
		this.uJDate = uJDate;
	}
	public String getuMail() {
		return uMail;
	}
	public void setuMail(String uMail) {
		this.uMail = uMail;
	}
	public int getuPhone() {
		return uPhone;
	}
	public void setuPhone(int uPhone) {
		this.uPhone = uPhone;
	}
	public String getuPropic() {
		return uPropic;
	}
	public void setuPropic(String uPropic) {
		this.uPropic = uPropic;
	}
	
	
}
