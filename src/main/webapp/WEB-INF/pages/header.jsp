<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
	<div class="hdst">
	<div id="lContent">		
		<a href="${ pageContext.servletContext.contextPath }/mainpage.mvc">
		<img src="images/logo.jpg" alt="Sharing" width="50px" height="50px" /></a>
	</div>
	<div id="rContent">
		<c:if test="${ sessionScope.isLogin }">
			<input onclick="location.href='profile.mvc' " type="button" value="Mypage">
		</c:if>
		<c:if test="${ sessionScope.isLogin }">
			<input onclick="location.href='QnAList.mvc' " type="button" value="Q&A">
		</c:if>
		<c:if test="${ sessionScope.isLogin }">
			<input type="button" value="${ uId }" onclick="location.href='memberUpdateForm.mvc'"/>
		</c:if>
		<c:if test="${ sessionScope.isLogin }">
			<input type="button" value="Logout" onclick="location.href='logout.mvc'"/>
		</c:if>
		<c:if test="${ not sessionScope.isLogin }">
			<input type="button" value="Login" onclick="location.href='loginForm.mvc'"/>
		</c:if>
	</div>
</header>
