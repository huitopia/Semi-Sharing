package com.sharing.main.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sharing.main.dao.ProfileDao;
import com.sharing.main.vo.Profile;

public class ProfileFormService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 로그인 상태이므로 session에서 사용자 아이디를 읽어온다.
		HttpSession session = request.getSession();
		String uId = (String) session.getAttribute("uId");
		
		ProfileDao dao = new ProfileDao();
		Profile profile = dao.getProfile(uId);
		
		
		request.setAttribute("profile", profile);
		
		return "profile/profile";
	}
}
