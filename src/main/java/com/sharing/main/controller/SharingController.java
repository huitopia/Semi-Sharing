package com.sharing.main.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sharing.main.service.BoardDetailService;
import com.sharing.main.service.BoardListService;
import com.sharing.main.service.BoardWriteService;
import com.sharing.main.service.CommandProcess;
import com.sharing.main.service.JoinFormService;
import com.sharing.main.service.JoinResultService;
import com.sharing.main.service.LoginFormService;
import com.sharing.main.service.LoginService;
import com.sharing.main.service.LogoutService;
import com.sharing.main.service.MainpageService;
import com.sharing.main.service.MemberUpdateFormService;
import com.sharing.main.service.MemberUpdateResultService;
import com.sharing.main.service.OverlapIdCheckService;
import com.sharing.main.service.PostWriteFormService;
import com.sharing.main.service.PostWriteService;
import com.sharing.main.service.ProfileFormService;
import com.sharing.main.service.ProfileUpdateFormService;
import com.sharing.main.service.ProfileUpdateService;
import com.sharing.main.service.writeFormService;



// 게시판 및 회원관련 요청을 처리하는 프런트 컨트롤러
public class SharingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* 뷰 페이지 정보 중에서 앞부분과 뒷부분에서 중복되는 데이터를
	 * 최소화하기 위해서 사용하는 접두어와 접미어를 상수로 설정
	 *
	 * 이번 프로젝트는 뷰 페이지를 구성할 때 중복되는 코드를 최소화하기 위해서
	 * 웹 템플릿을 사용하는 프로젝트로 여러 JSP 페이지를 모듈화 하여 분리해 놓고
	 * 실행될 때 웹 템플릿을 통해서 하나의 JSP 페이지로 동작하도록 구현되어 있다.
	 * 그러므로 포워딩을 해야 할 경우에는 웹 템플릿 역할을 하는 index.jsp에
	 * 동적으로 포함시킬 컨텐츠 페이지를 body라는 파라미터를 통해서 전달하여
	 * 웹 템플릿 페이지인 index.jsp에서 body라는 파라미터 값을 읽어서 동적으로
	 * 포함되는 컨텐츠 페이지를 include 하도록 상대 참조 방식으로 지정하면 된다.
	 **/
	private final String PREFIX = "/WEB-INF/index.jsp?body=";
	private final String SUFFIX = ".jsp";
	
	/* 서블릿 초기화 메서드
	 * 이 서블릿 클래스의 인스턴스가 생성되고 아래 초기화 메서드가 딱 한 번 호출 된다.
	 **/
	public void init() throws ServletException {
		
		/* 애플리케이션 초기화 파라미터로 읽어온 업로드 파일이 저장될 폴더의 
		 * 로컬 경로를 구하여 그 경로와 파일명으로 File 객체를 생성한다. 
		 **/
		ServletContext sc = getServletContext();
		String uploadDir = sc.getInitParameter("uploadDir");
		String realPath = sc.getRealPath(uploadDir);
		File parentFile = new File(realPath);
		
		/* 파일 객체에 지정한 위치에 디렉토리가 존재하지 않거나 
		 * 파일 객체가 디렉토리가 아니라면 디렉토리를 생성한다.
		 **/
		if(! (parentFile.exists() && parentFile.isDirectory())) {
			parentFile.mkdir();
		}
		
		/* 업로드 폴더 정보를 ServletContext 객체의 속성에 저장한다.
		 * ServletContext 객체는 웹 애플리케이션 당 1개가 생성되며 웹 애플리케이션이
		 * 구동되는데 필요한 정보를 담고 있는 객체로 JSP 페이지에서는 application
		 * 내장객체로 접근할 수 있다. 아래와 같이 ServletContext 객체의 속성에
		 * 저장되면 이 웹 애플리케이션의 모든 컴포넌트에서 이 정보를 사용할 수 있다. 
		 **/		
		sc.setAttribute("uploadDir", uploadDir);
		sc.setAttribute("parentFile", parentFile);
		System.out.println("init - " + parentFile);
	}

	// get 방식의 요청을 처리하는 메소드
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		
		doProcess(request, response);
	}

	// post 방식의 요청을 처리하는 메소드
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");		
		doProcess(request, response);
	}

	/* doGet(), doPost()에서 호출하는 메소드
	 * 즉 get방식과 post방식 요청을 모두 처리하는 메소드 이다.
	 * 컨트롤러는 이 메소드 안에서 브라우저의 요청에 대한 처리를 요청 URL을 분석해
	 * 요청을 처리할 모델 클래스를 결정하고 해당 모델 클래스의 객체를 사용해(위임)
	 * 클라이언트의 요청을 처리한 후 그 결과를 뷰로 전달해 결과 화면을 만들게 된다.
	 * 뷰로 전달된 데이터는 html 형식의 문서에 출력하여 브라우저에게 응답한다.
	 **/
	public void doProcess(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {		
		
		// http://localhost:8080/Semi-SharingSave/index.mvc
		// http://localhost:8080/Semi-SharingSave/*.mvc
		
		// /Semi-SharingSave/index.mvc
		String requestURI = request.getRequestURI();

		// /Semi-SharingSave
		String contextPath = request.getContextPath();
		System.out.println("uri : " + requestURI + ", ctxPath : " + contextPath);
		
		//   /index.mvc
		String command = requestURI.substring(contextPath.length());
		System.out.println("command : " + command);
	

		CommandProcess service = null;
		String viewPage = null;
		
		
		

		

		if(command.equals("/mainpage.mvc") 
				|| command.equals("/*.mvc")
				|| command.equals("/index.mvc")) {

			service = new MainpageService();
			viewPage = service.requestProcess(request, response);
			
		}
		  else if(command.equals("/profile.mvc")) {
			service = new ProfileFormService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/profileUpdateForm.mvc")) {
			service = new ProfileUpdateFormService();
			viewPage = service.requestProcess(request, response);
			
		} 	else if(command.equals("/profileUpdate.mvc")) {
			service = new ProfileUpdateService();
			viewPage = service.requestProcess(request, response); 
		
		} else if(command.equals("/loginForm.mvc")) {
			//  로그인 폼을 보여주는 서비스 클래스를 실행
			// "member/loginForm"
			service = new LoginFormService();
			viewPage = service.requestProcess(request, response);
			
		}  else if(command.equals("/login.mvc")) {
			//  로그인을 처리하는 서비스 클래스를 실행
			// "member/loginForm"
			service = new LoginService();
			viewPage = service.requestProcess(request, response);
			
		}  else if(command.equals("/logout.mvc")) {
			//  로그 아웃을 처리하는 서비스 클래스를 실행
			service = new LogoutService();
			viewPage = service.requestProcess(request, response);
			
		}	else if(command.equals("/joinForm.mvc")) {
			//  회원가입을 처리하는 서비스 클래스를 실행
			service = new JoinFormService();
			viewPage = service.requestProcess(request, response);
			
		}	else if(command.equals("/joinResult.mvc")) {
			//  회원가입 폼을 보여주는 서비스 클래스를 실행
			service = new JoinResultService();
			viewPage = service.requestProcess(request, response);
			
		}	else if(command.equals("/overlapIdCheck.mvc")) {
			// 아이디 중복 확인
			service = new OverlapIdCheckService();
			viewPage = service.requestProcess(request, response);
			
		}	else if(command.equals("/memberUpdateForm.mvc")) {
			// 회원정보 수정 폼을 보여주는 서비스 클래스를 실행
			service = new MemberUpdateFormService();
			viewPage = service.requestProcess(request, response);
			
		}	else if(command.equals("/memberUpdateResult.mvc")) {
			// 회원정보 수정 완료 요청 클래스
			service = new MemberUpdateResultService();
			viewPage = service.requestProcess(request, response);
			
		}	else if(command.equals("/QnAList.mvc")) {
			service = new BoardListService();
			viewPage = service.requestProcess(request, response);
			
		} 	else if(command.equals("/QnADetail.mvc")) {
			service = new BoardDetailService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/QnAWrite.mvc")) {
			service = new writeFormService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/QnAProcess.mvc")) {			
			service = new BoardWriteService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/postWriteForm.mvc")) {
			service = new PostWriteFormService();
			viewPage = service.requestProcess(request, response);
			
		} else if(command.equals("/postWrite.mvc")) {
			service = new PostWriteService();
			viewPage = service.requestProcess(request, response);
		}
		
		
		/*
		 
		 service 페이지로 가는 if 문 사용 방법
		 
		 else if(command.equals("/[page name].mvc") {

			service = new [service name]();
			viewPage = service.requestProcess(request, response);
			
		} 	
		  
		  위에 존재하는 || command ~~ 는 특정 위치가 지정되지 않았을 때 메인페이지로 돌아가는 구문입니다!
		  else if 사용 시에는 사용하지 않습니다! 
		  위에 있는 if문에 이어서 사용하시면 됩니다
		  
		 */
		
		
		
		// viewPage = "member/loginForm"

		if(viewPage != null) {
			
			String view = viewPage.split(":")[0];
			System.out.println("view : " + view);
			
			if(view.equals("r") || view.equals("redirect")) {			
				
				response.sendRedirect(viewPage.split(":")[1]);
				
			} else if(view.equals("f") || view.equals("forward")) {				
	
				RequestDispatcher rd = 
						request.getRequestDispatcher(viewPage.split(":")[1]);	
				rd.forward(request, response);
				
			} else {				

				// post/postWriteForm
				/*
				 
				  PREFIX = "/WEB-INF/index.jsp?body= + post/postWriteForm + .jsp";
	
				  
				 */
				RequestDispatcher rd = 
						request.getRequestDispatcher(PREFIX + view + SUFFIX);	
				rd.forward(request, response);
			}
		}	
	}
}

