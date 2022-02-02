package com.sharing.main.service;

// Redirect 정보와 View 페이지의 경로 정보를 저장하는 클래스
public class ForwardService {
	
	/* Redirect 정보를 저장하는 변수
	 * true : Redirect, false : Forward
	 **/
	private boolean redirect;
	
	// View 페이지의 경로 정보를 저장하는 변수
	private String path;
	
	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
