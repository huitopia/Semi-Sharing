package com.sharing.main.vo;

import java.sql.Timestamp;

public class MPost {
	private int pNo;
	private String pTitle;
	private Timestamp pDate;
	private String pText;
	private String pPic;
	private int ctCode;
	private int cCode;
	private int lCode;
	private int uNum;
	private int mViewCount;
	
	public MPost() {}
	public MPost(int pNo, String pTitle, Timestamp pDate, String pText, String pPic, int ctCode, int cCode, int lCode, int uNum, int mViewCount) {
		this.pNo = pNo;
		this.pTitle = pTitle;
		this.pDate = pDate;
		this.pText = pText;
		this.pPic = pPic;
		this.ctCode = ctCode;
		this.cCode = cCode;
		this.lCode = lCode;
		this.uNum = uNum;
		this.mViewCount = mViewCount;
	}
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public String getpTitle() {
		return pTitle;
	}
	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}
	public Timestamp getpDate() {
		return pDate;
	}
	public void setpDate(Timestamp pDate) {
		this.pDate = pDate;
	}
	public String getpText() {
		return pText;
	}
	public void setpText(String pText) {
		this.pText = pText;
	}
	public String getpPic() {
		return pPic;
	}
	public void setpPic(String pPic) {
		this.pPic = pPic;
	}
	public int getCtCode() {
		return ctCode;
	}
	public void setCtCode(int ctCode) {
		this.ctCode = ctCode;
	}
	public int getcCode() {
		return cCode;
	}
	public void setcCode(int cCode) {
		this.cCode = cCode;
	}
	public int getlCode() {
		return lCode;
	}
	public void setlCode(int lCode) {
		this.lCode = lCode;
	}
	public int getuNum() {
		return uNum;
	}
	public void setuNum(int uNum) {
		this.uNum = uNum;
	}
	public int getmViewCount() {
		return mViewCount;
	}
	public void setmViewCount(int mViewCount) {
		this.mViewCount = mViewCount;
	}
	
	

}
