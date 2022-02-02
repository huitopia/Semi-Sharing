<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<article>
	<div id="idCheckForm">
	<c:choose>
		<c:when test="${ overlap == true }">
			<h3>사용 불가능 아이디</h3>
			<div class="memberInputText">
				해당 ${ uId }는 이미 사용 중인 아이디입니다.
			</div>
			<div class="memberInputText">
				다른 아이디를 선택해주세요.
			</div>
			<form action="overlapIdCheck.mvc" name="idCheckFrom" id="idCheckForm" method="post">
				<div class="memberInputText">
					<span class="checkFormSpan">아이디</span>
					<input type="text" id="uId" name="uId" size="15" placeholder="4~12자의 영문 대소문자와 숫자로만 입력"/>
					<input type="submit" value="중복확인"/>
				</div>
			</form>
		</c:when>
		<c:otherwise>
			<h3>사용 가능 아이디</h3>
			<div class="memberInputText">
				해당 ${ uId }는 사용할 수 있는 아이디입니다.
			</div>
			<div class="memberInputText">
				<input type="button" value="${ uId }을(를) 아이디로 사용" id="btnIdCheckClose" data-id-value="${ uId }">
			</div>
		</c:otherwise>
	</c:choose>
	</div>
</article>