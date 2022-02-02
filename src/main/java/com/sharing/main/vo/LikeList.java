package com.sharing.main.vo;

import java.sql.Timestamp;

public class LikeList {
	private int lno;
	private Timestamp ldate;
	private int pno;
	private int unum;
	
	public LikeList() {}
	public LikeList(int lno, Timestamp ldate, int pno, int unum) {
		this.lno = lno;
		this.ldate = ldate;
		this.pno = pno;
		this.unum = unum;
	}
	public int getLno() {
		return lno;
	}
	public void setLno(int lno) {
		this.lno = lno;
	}
	public Timestamp getLdate() {
		return ldate;
	}
	public void setLdate(Timestamp ldate) {
		this.ldate = ldate;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getUnum() {
		return unum;
	}
	public void setUnum(int unum) {
		this.unum = unum;
	}
	
	
}
