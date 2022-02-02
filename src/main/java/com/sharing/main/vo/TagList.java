package com.sharing.main.vo;

public class TagList {

	private int tno;
	private String bname;
	private String hname;
	
	public TagList() {}

	public TagList(int tno, String bname, String hname) {

		this.tno = tno;
		this.bname = bname;
		this.hname = hname;
	}

	public int getTno() {
		return tno;
	}

	public void setTno(int tno) {
		this.tno = tno;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getHname() {
		return hname;
	}

	public void setHname(String hname) {
		this.hname = hname;
	}
	
	
	
}
