package com.sharing.main.vo;

public class Contag {
	private int pno;
	private String hname;
	private String bname;
	
	public Contag() {}
	public Contag(int pno, String hname, String bname) {
		this.pno = pno;
		this.hname = hname;
		this.bname = bname;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	
	
}
