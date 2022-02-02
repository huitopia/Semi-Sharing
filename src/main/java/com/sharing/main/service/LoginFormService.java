package com.sharing.main.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFormService implements CommandProcess {
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		
		// /WEB-INF/index.jsp?body=member/loginForm.jsp
		return "member/loginForm";
	}
}
