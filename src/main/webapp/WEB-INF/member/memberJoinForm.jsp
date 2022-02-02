<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<article>
	<div id="memberJoinForm" style="width: 800px; margin: 50px auto;">
	<h3 id="joinFormTitle">회원가입 페이지</h3>
	<br><br>
	<form action="joinResult.mvc" name="joinForm" id="joinForm" method="post">
		<!-- 회원 아이디 중복 검사여부 정보 hidden 필드로 저장 -->
		<input type="hidden" name="isIdCheck" id="isIdCheck" value="false"/>
		<div id="memberInputDefault">
			<div class="memberInputText" style="margin-bottom: 1em;">
				<span class="memberSpan" >아이디</span>
				<br>
				<input type="text" id="uId" name="uId" size="15" placeholder="4~12자의 영문 대소문자와 숫자로만 입력"  style="float: left; width: 37em;"/>
				<input type="button" value="중복확인" id="btnOverlapId" style="float: right;"/>					
			</div>
			<br>
			<br>
			<div class="memberInputText" style="margin-bottom: 1em;">
				<span class="memberSpan"  style="float: left;">비밀번호</span>
				<input type="password" id="pass1" name="pass1" size="15" placeholder="4~12자의 영문 대소문자와 숫자로만 입력"/>					
			</div>
			<div class="memberInputText" style="margin-bottom: 1em;">
				<span class="memberSpan">비밀번호 확인</span>
				<input type="password" id="pass2" name="pass2" size="15"/>				
			</div>
			<div class="memberInputText" style="margin-bottom: 1em;">
				<span class="memberSpan">이름</span>
				<input type="text" id="uName" name="uName" size="15"/>
			</div>
			<div class="memberInputText" style="margin-bottom: 1em;">
				<span class="memberSpan">생년월일</span>
				<input type="date" id="uBt" name="uBt" size="15" maxlength="8" placeholder="Ex)19881225"/>
			</div>
			<div class="memberInputText" style="margin-bottom: 1em;">
				<span class="memberSpan">이메일</span>
				<input type="text" id="uMail" name="uMail" size="15" maxlength="30" placeholder="Ex)abcde@xcvbd.com"/>
			</div>
			<div class="memberInputText" style="margin-bottom: 1em;">
				<span class="memberSpan">휴대폰</span>
				<input type="text" id="uPhone" name="uPhone" size="15" maxlength="11" placeholder="Ex)01056781234"/>
			</div>
		</div>

		<div class="formButton" style="text-align: center;">
				<br>
				<br>
			<input type="submit" value="Sign Up"/>
		</div>
	</form>
	</div>
</article>