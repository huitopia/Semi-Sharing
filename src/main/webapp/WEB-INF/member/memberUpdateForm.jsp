<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<article>
	<div id="memberJoinForm" style="width: 800px; margin: 50px auto;">
	<h3 id="joinFormTitle">회원 정보 수정</h3>
	<form action="memberUpdateInfo.mvc" name="memberUpdateForm" id="memberUpdateForm" method="post">
		<div id="memberInputDefault">
			<div class="memberInputText">
				<span class="memberSpan">아이디</span>
				<input type="text" id="uId" name="uId" size="15" readonly 
					value="${ sessionScope.userinfo.uId }"/>					
			</div>
			<div class="memberInputText">
				<span class="memberSpan">기존 비밀번호</span>
				<input type="password" id="oldPass" name="oldPass" size="15"/>
				<input type="button" value="비밀번호 확인" id="btnPassCheck"/>					
			</div>
			<div class="memberInputText">
				<span class="memberSpan">신규 비밀번호</span>
				<input type="password" id="pass1" name="pass1" size="15" placeholder="4~12자의 영문 대소문자와 숫자로만 입력"/>				
			</div>
			<div class="memberInputText">
				<span class="memberSpan">신규 비밀번호 확인</span>
				<input type="password" id="pass2" name="pass2" size="15" />				
			</div>
			<div class="memberInputText">
				<span class="memberSpan">이름</span>
				<input type="text" id="uName" name="uName" size="15"
					value="${ sessionScope.userinfo.uName }"/>
			</div>
			<div class="memberInputText">
				<span class="memberSpan">생년월일</span>
				<input type="text" id="uBt" name="uBt" size="15" maxlength="8" placeholder="Ex)19881225"
					value="${ sessionScope.userinfo.uBt }"/>
			</div>
			<div class="memberInputText">
				<span class="memberSpan">이메일</span>
				<input type="text" id="uMail" name="uMail" size="15" maxlength="30" placeholder="Ex)abcde@xcvbd.com"
					value="${ sessionScope.userinfo.uMail }"/>
			</div>
			<div class="memberInputText">
				<span class="memberSpan">휴대폰</span>
				<input type="text" id="uPhone" name="uPhone" size="15" maxlength="11" placeholder="Ex)01056781234"
					value="${ sessionScope.userinfo.uPhone }"/>
			</div>
		</div>
		<div class="formButton">
			<input type="submit" value="수정완료" id="btnJoin"/>
		</div>
	</form>
	</div>
</article>