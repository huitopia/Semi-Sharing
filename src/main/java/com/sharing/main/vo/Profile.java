package com.sharing.main.vo;

public class Profile {
	private int uNum;
	private String uId;
	private String uPropic; 
	
	public Profile() {}
	
	
	public Profile(int uNum, String uId,String uPropic) {
		this.uNum = uNum;
		this.uId = uId;
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


	public String getuPropic() {
		return uPropic;
	}


	public void setuPropic(String uPropic) {
		this.uPropic = uPropic;
	}
	
	

}
