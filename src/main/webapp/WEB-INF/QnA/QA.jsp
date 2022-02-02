<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" href="css/board.css" rel="stylesheet">
<script src="../js/jquery-3.3.1.min.js"></script>
</head>
<body>
<!--    CSS 적용해야 할 곳  -->
<div class="innerBox">
<h1>Q&A</h1>
<ul>
<li>
<a href="">공지사항</a>
</li>
<li>
<a href="">FAQ/Q&A</a>
</li>
</ul>
</div>
   <!--            -->
   <table class="QnATable">
   <tr><td class="boardTitle" colspan="5"><h2>QnA리스트</h2></td></tr>
   <tr>
      <td colspan="5">
     <form name="QnAForm" id="QnAForm">
     <select name="type" id="type">
     <option value="title">제목</option>
     <option value="writer">작성자</option>
     <option value="content">내용</option>
     </select>
     <input type="text" name="keyword" id="keyword" />
     <input type="submit" value="검색" />
     </form>
     </td>
     </tr>
     
     
<c:if test="${ searchOption }">
<tr>
<td colspan="5" id="searchComment" style="board: 1px solid red">
"${ keyword }" 검색 결과</td>
</tr>
</c:if>
<c:if test="${ not searchOption }">
<tr>
<td colspan="5" class="listWrite"><a href="writeForm.bbs">글쓰기</a></td>
</tr>
</c:if>
<c:if test="${ searchOption }">
<tr>
<td colspan="2" class="boardListLink"><a href="boardList.bbs">리스트</a></td>
<td colspan="3" class="listWrite"><a href="writeForm.bbs">글쓰기</a></td>
</tr>
</c:if>
<tr>
<th class="listThNo">NO</th>
<th class="listThTitle">제목</th>
<th class="listThWriter">작성자</th>
<th class="listThRegDate">작성일</th>
<th class="listThReadCount">조회수</th>
</tr>
<c:if test="${ searchOption and not empty boardList }">
<c:forEach var="b" items="${ QA }" varStatus="status">
<tr class="listTr">
<td class="listTdNo">${ b.no }</td>
<td class="listTdTitle">
<a href="boardDetail.bbs?no=${ b.no }&pageNum=${ currentPage }
&type=${ type }&keyword=${ keyword }">${ b.title }</a>
</td>
<td class="listTdWriter">${ b.writer }</td>
<td class="listTdRegDate"><fmt:formatDate value="${ b.regDate }"
pattern="yyyy-MM-dd HH:mm:ss" /></td>
<td class="listTdReadCount">${ b.readCount }</td>
</tr>
</c:forEach>
<tr>
<td colspan="5" class="listPage">
<c:if test="${ startPage > pageGroup }">
<a href="boardList.bbs?pageNum=${ startPage - pageGroup }
&type=${ type }&keyword=${ keyword }">[이전]</a>
</c:if>
<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
<c:if test="${ i == currentPage }">
[ ${ i } ]
</c:if>
<c:if test="${ i != currentPage }">
<a href="boardList.bbs?pageNum=${ i }&type=${ type }
&keyword=${ keyword }">[ ${ i } ]</a>
</c:if>
</c:forEach>
<c:if test="${ endPage < pageCount }">
<a href="boardList.bbs?pageNum=${ startPage + pageGroup }
&type=${ type }&keyword=${ keyword }">[다음]</a>
</c:if>
</td>
</tr>
</c:if>

<!--    게시글 출력  -->
<c:if test="${ not searchOption and not empty QA }">
<c:forEach var="b" items="${ QA }" varStatus="status">
<tr class="listTr">
<td class="listTdNo">${ b.no }</td>
<td class="listTdTitle">
<a href="boardDetail.bbs?no=
${ b.no }&pageNum=${ currentPage }" >${ b.title }</a>
</td>
<td class="listTdWriter">${ b.writer }</td>
<td class="listTdRegDate"><fmt:formatDate value="${ b.regDate }"
pattern="yyyy-MM-dd HH:mm:ss" /></td>
<td class="listTdReadCount">${ b.readCount }</td>
</tr>
</c:forEach>
<tr>
<td colspan="5" class="listPage">
<c:if test="${ startPage > pageGroup }">
<a href="boardList.bbs?pageNum=${ startPage - pageGroup }">
[이전]</a>
</c:if>
<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
<c:if test="${ i == currentPage }">
[ ${ i } ]
</c:if>
<c:if test="${ i != currentPage }">
<a href="boardList.bbs?pageNum=${ i }">[ ${ i } ]</a>
</c:if>
</c:forEach>
<c:if test="${ endPage < pageCount }">
<a href="boardList.bbs?pageNum=${ startPage + pageGroup }">
[다음]</a>
</c:if>
</td>
</tr>
</c:if>
<!-- 검색 요청이면서 검색된 리스트가 존재하지 않을 경우 -->
<c:if test="${ searchOption and empty boardList }">
<tr>
<td colspan="5" class="listTdSpan">
"${ keyword }"가 포함된 게시 글이 존재하지 않습니다.</td>
</tr>
</c:if>
<!-- 일반 게시 글 리스트 요청이면서 게시 글 리스트가 존재하지 않을 경우 -->
<c:if test="${ not searchOption and empty boardList }">
<tr>
<td colspan="5" class="listTdSpan">게시 글이 존재하지 않습니다.</td>
</tr>
</c:if>
</table>
</body>
</html>