<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<article>
	<div align="center" style="width: 800px; height: 600px; margin: 5em auto;">
		<h3 id="loginFormTitle">로그인 페이지</h3>
		<form action="login.mvc" id="loginForm" method="post">
			<div class="box" id="login" style="width: 25em;height: 10em; border: none;">
				<br>
				<div>
				<h3 style="float: left;"> ID &nbsp;&nbsp; : &nbsp;</h3>
				<input type="text" id="uId" name="uId" size="4" class="loginBox" placeholder="아이디를 입력하세요" style="float: right: ; width: 20em;"/>
				</div>
				<br/>
				<div>
				<h3 style="float: left;"> PW : &nbsp;</h3>
				<input type="password" id="userPass" name="uPw" size="4" class="loginBox" placeholder="비밀번호를 입력하세요" style="float: right: ; width: 20.3em;"/>
				</div>
			</div>				
			<div id="btnloginform" style="margin: 10px; ">
				<input type="submit" value="LOGIN" id="btnLogin"/>
				<input type="button" value="SIGN UP" id="btnJoin" onclick="location.href='joinForm.mvc'"/>
			</div>
		</form>
	</div>
</article>