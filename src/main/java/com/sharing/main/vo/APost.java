package com.sharing.main.vo;

import java.sql.Timestamp;

public class APost {

	private int aNo;
	private Timestamp aDate;
	private String aTitle;
	private String text;
	private int rNo;
	private int aViewCount;
	
	public APost(){};
	public APost(int aNo, Timestamp aDate, String aTitle, String text, int rNo, int aViewCount) {
		
		this.aNo = aNo;
		this.aDate = aDate;
		this.aTitle = aTitle;
		this.text = text;
		this.rNo = rNo;
		this.aViewCount = aViewCount;
	}
	public int getaNo() {
		return aNo;
	}
	public void setaNo(int aNo) {
		this.aNo = aNo;
	}
	public Timestamp getaDate() {
		return aDate;
	}
	public void setaDate(Timestamp aDate) {
		this.aDate = aDate;
	}
	public String getaTitle() {
		return aTitle;
	}
	public void setaTitle(String aTitle) {
		this.aTitle = aTitle;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getrNo() {
		return rNo;
	}
	public void setrNo(int rNo) {
		this.rNo = rNo;
	}
	public int getaViewCount() {
		return aViewCount;
	}
	public void setaViewCount(int aViewCount) {
		this.aViewCount = aViewCount;
	}
	
	
}
