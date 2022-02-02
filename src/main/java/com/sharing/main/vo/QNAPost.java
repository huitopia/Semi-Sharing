package com.sharing.main.vo;

import java.sql.Timestamp;

public class QNAPost {
	private int qNo;
	private String qTitle;
	private String qText;
	private Timestamp qDate;
	private String qPic;
	private int uNum;
	private int qnaViewCount;
	
	public QNAPost() {}
	public QNAPost(int qNo, String qTitle, String qText, Timestamp qDate, String qPic, int uNum, int qnaViewCount) {
		this.qNo = qNo;
		this.qTitle = qTitle;
		this.qDate = qDate;
		this.qPic = qPic;
		this.uNum = uNum;
		this.qnaViewCount = qnaViewCount;
	}
	public int getqNo() {
		return qNo;
	}
	public void setqNo(int qNo) {
		this.qNo = qNo;
	}
	public String getqTitle() {
		return qTitle;
	}
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}
	public String getqText() {
		return qText;
	}
	public void setqText(String qText) {
		this.qText = qText;
	}
	public Timestamp getqDate() {
		return qDate;
	}
	public void setqDate(Timestamp qDate) {
		this.qDate = qDate;
	}
	public String getqPic() {
		return qPic;
	}
	public void setqPic(String qPic) {
		this.qPic = qPic;
	}
	public int getuNum() {
		return uNum;
	}
	public void setuNum(int uNum) {
		this.uNum = uNum;
	}
	public int getQnaViewCount() {
		return qnaViewCount;
	}
	public void setQnaViewCount(int qnaViewCount) {
		this.qnaViewCount = qnaViewCount;
	}
	
	
}
