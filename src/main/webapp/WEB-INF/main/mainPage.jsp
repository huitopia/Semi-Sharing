<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*" %>
<article>
	
				<section id="banner" data-video="images/banner">
					<div class="inner">
						<header>
							<h1>Sharing</h1>
							<p> 여행을 공유하다</p>
							<form name="searchForm" id="searchForm" style="width: 40em;">
							<input type="text" id="keyword" name="keyword" style="width: 25em; padding-left: 1em; text-align: center;" >
							<input type="submit" id="searchBt" value="search" >
							</form>
						</header>
						
					</div>
				</section>

			<!-- Main -->
				<div id="main">
					<div class="inner">

					<!-- Boxes -->
					

													
						<div class="Mtitle">
						<c:if test="${ searchOption  }"> <h4> '${ keyword }'에 대한 Sharing! </h4> </c:if>
						<c:if test="${ tagOption }"> <h4> '${ tagName }'에 대한 Sharing! </h4> </c:if>
						<c:if test="${ !searchOption and !tagOption }"> <h4> BEST SHARING </h4> </c:if>
							
							<hr class="MtitleHr">
						</div>
						<div class="thumbnails">
						
							<c:if test="${ searchOption and empty mainList}">
								<div> 검색 결과가 존재하지 않습니다. </div>
							</c:if>
							
							<c:if test="${ tagOption and empty mainList}">
								<div> 검색 결과가 존재하지 않습니다. </div>
							</c:if>
						

							<c:forEach var="m" items="${ mainList }" varStatus="status">
							
							<c:if test ="${ !empty mainList }">
							
							<div class="box">
								<a href="" class="image fit"><img class ="boxImg"  src="upload/${ m.picLoc }" alt="" /></a>
								<div class="inner">
									<div class="titleClass" style="float:left"><h4 style="font-size: 1em">${ m.title }</h4></div>
									
									<div class="likeCount" style="line-height:80%; float:right; margin-right: 0.6em; font-size: 1em;">
										<img src="images/pngegg.png" width="40px" height="40px"><br>
										${m.like }
									</div>
									<br>
									
									<div class="Mcontant">								
										<p>${m.content}</p>
									</div>					
									
									
											
									<div class="prodiv">
										
										<div class="probox">
										
											<div class ="propdiv">
												<c:if test="${empty m.uPropic}">
													<img class="propic" src="images/protest.jpg">
												</c:if>
												<c:if test="${!empty m.uPropic}">
													<img class="propic" src="propic/${m.uPropic}">
												</c:if>
											</div>
										</div>
										
										<div class="proname"> ${ m.uname }</div>
									</div>

								</div>
							</div>
							
							<br>
							
							</c:if>
							</c:forEach>
							
							<c:if test="${ searchOption }">
							
							<c:if test="${ endRow < listCount }">
							<img src="images/scrbt.png" width="40px" height="40px" style="margin-top:5em">
							</c:if>
							
							</c:if>
							
							
			

						</div>

					</div>
				
					<div class="inner">
						
						<div class="Mtitle">	
							<h4>TAG LIST</h4>
							<hr class="MtitleHr">
						</div>
						
						<div class="thumbnails">
					
							<table class="menu--luce" id ="tagBt">
								
								<c:forEach var="t" items="${tagList }" varStatus="vs">
									
										<c:if test="${vs.index % 7 == 0 and vs.index != 0}"> <tr></c:if>
									
					
										<td class = "tagListBt">
										
										<form name="tagForm" id="tagForm" style="height: 1em;">
										<input type="submit" class = "tagListBt" name="tagName" id="tagName" value="${t}">
										</form>							
										</td>
										
										<c:if test="${vs.index % 20 == 0 and vs.index != 0}"> </tr></c:if>
	
						
								</c:forEach>
								
							</table>
						</div>
					
					</div>
				</div>

			<!-- Footer -->

</article>