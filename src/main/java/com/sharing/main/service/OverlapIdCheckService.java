package com.sharing.main.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sharing.main.dao.MemberDao;

// 회원 가입시 아이디 중복검사 요청 처리
public class OverlapIdCheckService implements CommandProcess {
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		
		String uId = request.getParameter("uId");
		
		MemberDao dao = new MemberDao();
		boolean overlap = dao.overlapIdCheck(uId);
		
		request.setAttribute("uId", uId);
		request.setAttribute("overlap", overlap);
		
		return "f:/WEB-INF/member/overlapIdCheck.jsp";
	}
}
