package com.sharing.main.service;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sharing.main.dao.MemberDao;
import com.sharing.main.vo.UserInfo;

//회원가입 완료 요청을 처리할 모델 클래스
public class JoinResultService implements CommandProcess {
	
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String uId = request.getParameter("uId");
		String uPw = request.getParameter("pass1");
		String uName = request.getParameter("uName");
		String uBt = request.getParameter("uBt");
		String uMail = request.getParameter("uMail");
		int uPhone = Integer.parseInt(request.getParameter("uPhone"));
		
		System.out.println("uBt : " + uBt);
		String[] tempBt = uBt.split("-");		
		
		Timestamp ts = new Timestamp(Integer.parseInt(tempBt[0]), Integer.parseInt(tempBt[1]), Integer.parseInt(tempBt[2]), 0, 0, 0, 0);
				
	
		UserInfo userinfo = new UserInfo();
		userinfo.setuId(uId);
		userinfo.setuPw(uPw);
		userinfo.setuName(uName);
		userinfo.setuBt(ts);
		userinfo.setuMail(uMail);
		userinfo.setuPhone(uPhone);
		
		MemberDao dao = new MemberDao();
		dao.joinMember(userinfo);
		
		HttpSession session = request.getSession();
		session.setAttribute("uId", userinfo.getuId());
		session.setAttribute("isLogin", true);
		
		return "r:mainpage.mvc";
		
	}
	
}
