package com.sharing.main.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sharing.main.dao.MemberDao;

// 회원정보 수정 폼에서 회원의 비밀번호 확인을 Ajax로 처리
public class PassCheckAction implements AjaxProcess{

	@Override
	public void ajaxProcess(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String uId = request.getParameter("uId");
		String uPw = request.getParameter("uPw");
		
		response.setContentType("application/json; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		StringBuilder sb = new StringBuilder();
		
		if(uId == null || uPw == null) {
			sb.append("<script>");
			sb.append(" alert('정상적인 접근이 아닙니다.');");
			sb.append("</script>");
			
			out.println(sb.toString());
			return;
		}
		
		MemberDao dao = new MemberDao();
		int result = dao.checkMember(uId, uPw);
		
		sb.append("{ \"result\" : \"" + result + "\"}");
		out.println(sb.toString());
		
	}
	
}
