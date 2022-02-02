package com.sharing.main.ajax;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ajax 요청을 처리하는 Controller 클래스 
@WebServlet(name="ajaxController", 
	urlPatterns="*.ajax", initParams=@WebInitParam(
			name="ajaxCommand", value="/WEB-INF/ajaxCommand.properties"))
public class AjaxController extends HttpServlet {
	
	// ajax 요청 명령과 그 명령을 처리할 모델 클래스의 인스턴스를 저장할 Map 객체 생성
	Map<String, AjaxProcess> ajaxMap = 
			new HashMap<String, AjaxProcess>();

	@Override
	public void init() throws ServletException {
		
		/* 요청 명령어와 명령어를 처리할 클래스 이름이 맵핑되어 있는 
		 * ajaxCommand.properties 파일의 위치를 서블릿 초기화
		 * 파라미터로 부터 읽어와 변수에 저장 한다.
		 **/
		String ajaxCommand = getInitParameter("ajaxCommand");
		
		/* properties 파일에 저장된 요청 명령어와 명령어를 처리할 
		 * 클래스 이름을 저장할 Properties 객체 생성		
		 * HashTable을 상속해 구현한 Properties 클래스는 key와 value를
		 * 모두 String 타입으로 관리할 수 있도록 구현한 Map계열의 클래스 이다.
		 * Properties는 주로 응용프로그램의 환경정보를 관리하는데 사용한다.
		 * Properties 클래스도 Map 계열의 클래스로 key의 중복을 허용하지
		 * 않고 데이터의 저장 순서를 유지하지 않는 특징을 가지고 있다.
		 * 중복된 key의 데이터가 입력되면 기존의 데이터를 덮어 쓴다.
		 **/ 
		Properties prop = new Properties();
		
		/* properties 파일을 읽기 위해 Stream을 선언하고 null로 초기화 한다.
		 * FileInputStream은 File의 내용을 Byte 단위로 읽을 수 있는 기반 스트림이고
		 * BufferedInputStream은 File을 Byte 단위로 읽을 때 작업 성능을 높이기 위해
		 * 임시 저장소(buffer) 역할을 담당하는 보조 스트림 이다. 
		 **/
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		
		try {
			// ajaxCommand.properties의 절대 경로를 구한다.
			String realPath = getServletContext().getRealPath(ajaxCommand);
			
			/* ajaxCommand.properties의 파일의 내용을 읽기 위해
			 * FileInputStream을 생성하고 uriCommand.properties 파일이
			 * 위치한 절대 경로를 생성자의 인수로 지정 한다.
			 **/ 
			fis = new FileInputStream(realPath);
			bis = new BufferedInputStream(fis);
			
			/* 파일에 연결된 스트림 객체를 Properties 클래스의 load()의 인수로
			 * 지정하면 properties 파일에 저장된 String 데이터를 한 라인씩 읽어
			 * 첫 번째 오는 '=' 문자나 ':' 문자를 기준으로 key와 value로 저장해 준다.
			 **/
			prop.load(bis);
			
		} catch(IOException e) {			
			throw new ServletException();
			
		} finally {			
			try {				
				// 보조 스트림을 먼저 닫고 기반 스트림을 닫는다.
				if(bis != null) bis.close();
				if(fis != null) fis.close();
				
			} catch(IOException ioe) { }
		}
		
		/* Propertiest 객체에 저장된 key 리스트를 Set 타입으로 리턴하는 
		 * keySet()을 호출하고 Set 객체에 접근하기 위해 Iterator 객체를 얻어 온다.
		 **/ 
		Iterator<Object> keyIter = prop.keySet().iterator();
		
		while(keyIter.hasNext()) {
			
			/* Propertiest 객체에 저장된 key를 명령어로 설정할 변수에 저장하고 
			 * 명령어를 처리할 key에 맵핑된 클래스 이름을 읽어와 변수에 저장 한다.
			 **/
			String command = (String) keyIter.next();
			String className = (String) prop.getProperty(command);
			System.out.println("command : " 
					+ command + ", className : " + className);
			
			try {
				/* Class 클래스 타입의 변수를 선언하고 key에 해당하는 요청 
				 * 명령어를 처리할 클래스의 정보를 메모리에 로딩 시킨다.
				 **/ 
				Class<?> commandClass = Class.forName(className);
				
				/* 명령어를 처리할 모델 클래스의 슈퍼 인터페이스 타입의 변수를 
				 * 선언하고 메모리에 로딩된 클래스의 인스턴스를 생성하여 할당 한다.
				 **/
				AjaxProcess ajaxProcess = 
						(AjaxProcess) commandClass.newInstance();
				
				/* 요청 명령어를 key로 하고 명령어를 처리할 인스턴스를
				 * value로 하여 Map에 저장 한다.
				 **/
				ajaxMap.put(command, ajaxProcess);
				
			} catch(ClassNotFoundException e) {				
				throw new ServletException();
				
			} catch(InstantiationException e) {				
				throw new ServletException();
				
			} catch(IllegalAccessException e) {				
				throw new ServletException();
			}
		}
	}
	
	public void doAjax(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		
		/* 모든 모델 클래스의 슈퍼 인터페이스인 CommandProcess 타입의
		 * 변수를 선언 하고 null로 초기화 한다.
		 **/
		AjaxProcess ajaxProcess = null;
		
		// Request 객체로 부터 요청 URI를 구한다.
		String command = request.getRequestURI();
		
		// 요청 URI에서 ContextPath를 제외한 요청 명령어를 추출 한다. 
		command = command.substring(request.getContextPath().length());	
		System.out.println("command : " + command);
		
		/* 요청 명령어와 명령어를 처리할 클래스의 인스턴스를 저장하고 있는 
		 * Map으로 부터 사용자의 요청을 처리하기 위해 요청 URI에서 추출한
		 * 요청 명령어에 해당하는 요청을 처리할 클래스의 인스턴스를 얻어와
		 * CommandProcess 타입의 변수에 저장하고 오버라이딩된
		 * requestProcess()를 호출하여 요청에 대한 처리를 위임 한다.
		 **/  
		ajaxProcess = ajaxMap.get(command);
		System.out.println("command : " + command 
				+ ", commandPro : " + ajaxProcess);
		
		if(ajaxProcess != null) {
			ajaxProcess.ajaxProcess(request, response);
		}/* else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			//out.println("");
		}*/
	}
	
	@Override
	public void doGet(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		
		doAjax(request, response);
	}
	
	@Override
	public void doPost(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		
		doAjax(request, response);
	}
}
