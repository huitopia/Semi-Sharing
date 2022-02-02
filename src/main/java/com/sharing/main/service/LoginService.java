package com.sharing.main.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sharing.main.dao.MemberDao;
import com.sharing.main.vo.UserInfo;

public class LoginService implements CommandProcess {
	
	@Override
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uId = request.getParameter("uId");
		String uPw = request.getParameter("uPw");
		
		MemberDao dao = new MemberDao();
		int checkLogin = dao.checkMember(uId, uPw);
		
		if(checkLogin == -1) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('" + uId + "는 가입되지 않은 아이디입니다.');");
			out.println("window.history.back();");
			out.println("</script>");
			return null;
		} else if(checkLogin == 0) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('틀린 비밀번호입니다.');");
			out.println("window.history.back();");
			out.println("</script>");
			return null;
		} else if(checkLogin == 1) {
			HttpSession session = request.getSession();
			UserInfo userInfo = dao.getUserInfo(uId);
			session.setAttribute("userInfo", userInfo);
			session.setAttribute("uId", uId);
			session.setAttribute("isLogin", true);
		}
		return "r:mainpage.mvc";
	}
}
