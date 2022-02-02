package com.sharing.main.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sharing.main.dao.MemberDao;
import com.sharing.main.vo.UserInfo;

public class MemberUpdateFormService implements CommandProcess{
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		HttpSession session = request.getSession();
		String uId = (String) session.getAttribute("uId");
		
		MemberDao dao = new MemberDao();
		UserInfo userinfo = dao.getUserInfo(uId);
		
		session.setAttribute("userinfo", userinfo);
		
		return "member/memberUpdateForm";
	}
}
