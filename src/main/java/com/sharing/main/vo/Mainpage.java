package com.sharing.main.vo;

import java.sql.Timestamp;

public class Mainpage {
	// no title rdate content picLoc uname lm ct cn like
	private int no;
	private String title;
	private Timestamp rdate;
	private String content;
	private String picLoc;
	private String uPropic;
	private String uname;
	private String lm;
	private String ct;
	private String cn;
	private String tname;
	private int like;
	

	
	public Mainpage() {}
	

	public Mainpage(int no, String title, Timestamp rdate, String content, String picLoc, String uPropic, String uname, String lm, String ct, String cn, int like) {
		this.no = no;
		this.title = title;
		this.rdate = rdate;
		this.content = content;
		this.picLoc = picLoc;
		this.uPropic = uPropic;
		this.uname = uname;
		this.lm = lm;
		this.ct = ct;
		this.cn = cn;
		this.like = like;
	}
	
	public Mainpage(int no, String title, Timestamp rdate, String content, String picLoc, String uPropic, String uname, String lm, String ct, String cn, String tname, int like) {
		this.no = no;
		this.title = title;
		this.rdate = rdate;
		this.content = content;
		this.picLoc = picLoc;
		this.uPropic = uPropic;
		this.uname = uname;
		this.lm = lm;
		this.ct = ct;
		this.cn = cn;
		this.tname = tname;
		this.like = like;
	}
	
	
	
	public String getTname() {
		return tname;
	}


	public void setTname(String tname) {
		this.tname = tname;
	}


	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getRdate() {
		return rdate;
	}
	public void setRdate(Timestamp rdate) {
		this.rdate = rdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicLoc() {
		return picLoc;
	}
	public void setPicLoc(String picLoc) {
		this.picLoc = picLoc;
	}
	public String getuPropic() {
		return uPropic;
	}
	public void setuPropic(String uPropic) {
		this.uPropic = uPropic;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getLm() {
		return lm;
	}
	public void setLm(String lm) {
		this.lm = lm;
	}
	public String getCt() {
		return ct;
	}
	public void setCt(String ct) {
		this.ct = ct;
	}
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}

	
	
}
