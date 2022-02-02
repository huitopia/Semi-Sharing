package com.sharing.main.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostWriteFormService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 특별한 처리가 없어 그냥 뷰 정보만 반환함
		
		
		return "post/postWriteForm";
	}

}
