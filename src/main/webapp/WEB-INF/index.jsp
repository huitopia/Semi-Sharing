<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Insert title here</title>
	
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link type="text/css" rel="stylesheet" href="css/main.css" />
		<link type="text/css" rel="stylesheet" href="css/component.css" />
		<link type="text/css" rel="stylesheet" href="css/component.css" />
		

		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.scrolly.min.js"></script>
		<script type="text/javascript" src="js/jquery.poptrox.min.js"></script>
		<script type="text/javascript" src="js/skel.min.js"></script>
		<script type="text/javascript" src="js/util.js"></script>
		<script type="text/javascript" src="js/main.js"></script>
		<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="js/member.js"></script>
</head>
<body id = "top">
	<div id="wrap">		
		<%@ include file="pages/header.jsp" %>		
		<c:if test="${ not empty param.body }">
			<jsp:include page="${ param.body }" />
		</c:if>		
		<%@ include file="pages/footer.jsp" %>
	</div>
</body>
</html>