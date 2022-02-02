package com.sharing.main.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sharing.main.dao.ProfileDao;
import com.sharing.main.dao.WriteDao;
import com.sharing.main.vo.MPost;

public class ProfileUpdateService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
	
		String realPath = request.getServletContext().getRealPath("propic");		
		
		// 업로드 파일의 최대 크기를 100MB로 지정
		int maxFileSize = 100 * 1024 * 1024;
		
		// 파일의 인코딩 타입을 UTF-8로 지정
		String encoding = "UTF-8"; 
			
		/* 2. 파일 업로드를 처리할 MultipartRequest 객체 생성
		 * 
		 * WEB-INF/lib/cos.jar 파일을 살펴보면 MultipartRequet 클래스는 
		 * com.oreilly.servlet 패키지에 위치하며 파일 업로드를 직접적으로 처리하는
		 * 역할을 담당하는 클래스로 파일 업로드와 관련된 다양한 메소드를 정의하고 있다.
		 * 생성자는 5개로 오버로딩 되어 있고 아래 생성자가 호출되도록 정의되어 있다.
		 *
		 *	public MultipartRequest(HttpServletRequest request,
		 *			String saveDirectory,
		 *			int maxPostSize,
		 *			String encoding,
		 *			FileRenamePolicy policy) throws IOException {...}
		 *
		 * 이 생성자를 살펴보면 request, saveDirectory, maxPostSize는 필수사항으로
		 * 이 매개변수가 null이거나 0보다 작다면 생성자 안에서 예외를 발생시킨다.
		 * 
		 * request : MultipartRequest에 연결할 사용자의 요청 정보가 담긴 객체 
		 * saveDirectory : 업로드 된 파일을 저장할 서버의 디렉터리 지정
		 * maxPostSize : 업로드 파일의 최대 크기 지정
		 * encoding : 파일의 인코딩 방식 지정, 파일 이름이 한글일 경우 필히 utf-8 지정
		 * policy : 사용자가 업로드 한 파일을 저장할 서버의 디렉터리에 현재 업로드 되는
		 *            파일과 이름이 중복된 파일이 존재할 경우 현재 업로드 되는 파일의
		 *            이름을 어떻게 변경할지에 대한 정책을 지정하는 매개변수 이다.
		 *            일반적으로 new DefaultFileRenamePolicy()를 사용하며
		 *            이 클래스는 abc.jpg 파일을 업로드 할때 이미 같은 이름의 파일이 
		 *            존재하면 자동으로 abc1.jpg와 같이 파일을 변경해 준다.
		 *
		 * 아래와 같이 MultipartRequest 객체를 생성하면 saveDirectory에 지정한
		 * 서버의 디렉터리로 파일이 바로 업로드 된다.
		 **/	 
		MultipartRequest multi = new MultipartRequest(request, realPath, 
							maxFileSize, encoding, new DefaultFileRenamePolicy());	
		
		/* 사용자가 업로드한 파일 데이터 처리
		 * MultipartRequest 객체를 통해 파일 이름을 구하여 변수에 저장한다.
		 * 파일이 업로드 되지 않으면 fileName은 null 값을 받는다.
		 **/
		String fileName = multi.getFilesystemName("propic");
		System.out.println("업로드 된 파일명 : " + fileName);
		System.out.println("원본 파일명 : " + multi.getOriginalFileName("propic"));
		
		
		if(fileName == null) {		
			System.out.println("파일이 업로드 되지 않았음");		
		}	
		
		HttpSession session = request.getSession();
		String uId = (String) session.getAttribute("uId");
		
		/* ProfileDao 객체를 얻어 profile을 수정
		 * DB에 profile을 수정하고 브라우저에게 profile을 보여주는 페이지를 요청하라고 응답		
		 **/
		ProfileDao dao = new ProfileDao();
		dao.setProfile(uId, fileName);

	
		return "r:profile.mvc";		
	}
}
