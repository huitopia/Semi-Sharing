<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<article>
<table class="listTable">
	<tr><td class="boardTitle" colspan="5"><h2>Q&A 리스트</h2></td></tr>
	<tr>			
		<td colspan="5" class="listWrite"><a href="QnAWrite.mvc">글쓰기</a></td>
	</tr>
	<tr>
		<th class="listThNo">NO</th>
		<th class="listThTitle">제목</th>
		<th class="listThWriter">작성자</th>
		<th class="listThRegDate">작성일</th>
		<th class="listThReadCount">조회수</th>
	</tr>
<!-- 
	 게시 글 리스트가 존재할 경우
	게시 글 상세보기로 링크를 적용할 때 type과 keyword 	파라미터는 필요 없다. 
		private int qNo;
	private String qTitle;
	private String qText;
	private Timestamp qDate;
	private String qPic;
	private int uNum;
	private int qnaViewCount; 
-->	
<c:if test="${ not empty boardList }">
	<c:forEach var="b" items="${ boardList }" varStatus="status">		
	<tr class="listTr">
		<td class="listTdNo">${ b.qNo  }</td>
		<td class="listTdTitle">
			<a href="QnADetail.mvc?no=
				${ b.qNo }&pageNum=${ currentPage }" >${ b.qTitle }</a>
		</td>
		<td class="listTdWriter">${ b.uNum }</td>
		<td class="listTdRegDate"><fmt:formatDate value="${ b.qDate }" 
			pattern="yyyy-MM-dd HH:mm:ss" /></td>
		<td class="listTdReadCount">${ b.qnaViewCount }</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="5" class="listPage">
			<%--
			/* 현재 페이지 그룹의 시작 페이지가 pageGroup보다 크다는 것은
			 * 이전 페이지 그룹이 존재한다는 것으로 현재 페이지 그룹의 시작 페이지에
			 * pageGroup을 마이너스 하여 링크를 설정하면 이전 페이지 그룹의
			 * startPage로 이동할 수 있다.
		 	 **/
		 	 --%>
		 	<c:if test="${ startPage > pageGroup }"> 
				<a href="QnAList.mvc?pageNum=${ startPage - pageGroup }">
					[이전]</a>
			</c:if>	
			<%--
			/* 현재 페이지 그룹의 startPage 부터 endPage 만큼 반복하면서
		 	 * 현재 페이지와 같은 그룹에 속한 페이지를 화면에 출력하고 링크를 설정한다.
		 	 * 현재 페이지는 링크를 설정하지 않는다.
		 	 **/
		 	--%>
			<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
				<c:if test="${ i == currentPage }">
					[ ${ i } ]
				</c:if>			
				<c:if test="${ i != currentPage }">
					<a href="QnAList.mvc?pageNum=${ i }">[ ${ i } ]</a>
				</c:if>			
			</c:forEach>
			<%-- 
			/* 현재 페이지 그룹의 마지막 페이지가 전체 페이지 보다 작다는 것은
			 * 다음 페이지 그룹이 존재한다는 것으로 현재 페이지 그룹의 시작 페이지에
			 * pageGroup을 플러스 하여 링크를 설정하면 다음 페이지 그룹의
			 * startPage로 이동할 수 있다.
		 	 **/
		 	 --%>
			<c:if test="${ endPage < pageCount }">
				<a href="QnAList.mvc?pageNum=${ startPage + pageGroup }">
					[다음]</a>
			</c:if>		
		</td>
	</tr>
</c:if>
<!-- 게시 글 리스트가 존재하지 않을 경우 -->
<c:if test="${ empty boardList }">
	<tr>
		<td colspan="5" class="listTdSpan">게시 글이 존재하지 않습니다.</td>
	</tr>
</c:if>
</table>
</article>



