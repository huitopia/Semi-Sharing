package com.sharing.main.vo;

public class Follow {

	private int fNo;
	private int fCallUser;
	private int fTargetUser;
	
	public Follow() {}
	public Follow(int fNo, int fCallUser, int fTargetUser) {
		this.fNo = fNo;
		this.fCallUser = fCallUser;
		this.fTargetUser = fTargetUser;
	}
	public int getfNo() {
		return fNo;
	}
	public void setfNo(int fNo) {
		this.fNo = fNo;
	}
	public int getfCallUser() {
		return fCallUser;
	}
	public void setfCallUser(int fCallUser) {
		this.fCallUser = fCallUser;
	}
	public int getfTargetUser() {
		return fTargetUser;
	}
	public void setfTargetUser(int fTargetUser) {
		this.fTargetUser = fTargetUser;
	}
	
	
}
