<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<h2 style="width: 800px; margin: 50px auto;">포스트 쓰기</h2>
	<form name="writeForm" action="postWrite.mvc" id="writeForm" method="post" style="width: 800px; margin: 50px auto;"
		enctype="multipart/form-data" >
		<input type="hidden" name="uNum" value="${ userInfo.uNum }" />
		<table>		
			<tr>
				<td>
					<input type="text" name="pTitle"  placeholder="제목입력"/>
				</td>
			</tr>	
			<tr>
				<td>
					<textarea name="pText" placeholder="제목 입력" cols="50" rows="5"></textarea>
				</td>
			</tr>	
			<tr>
				<td class= WriteItem>
<!--
82	한국
101	미국
102	캐나다
81	일본
20	이집트
30	그리스
33	프랑스
44	영국
41	스위스
45	덴마크
49	독일


600	서울	82
601	부산	82
602	뉴욕	101
603	워싱턴D.C	101
604	캘리포니아	101
605	도쿄	81
606	오타와	102
607	카이로	20
608	아테네	30
609	파리	33
610	런던	44
611	베른	41
612	코펜하겐	45
613	베를린	49				
				
				
2001	남산타워	600
2002	광안대교	601
2003	자유의여신상	602
2004	백악관	603  
				-->
					<select name="cCode" id="country">
						<option value="82">대한민국</option>
						<option value="101">미국</option>
						<option value="81">일본</option>
					</select>
				</td>
				<tr>
					<td class= WriteItem>				
						<select name="ctCode" id="city">
							<option value="600">서울</option>
							<option value="602">뉴욕</option>
							<option value="605">도쿄</option>
						</select>
					</td>
				</tr>	 
				<tr>
					<td class= WriteItem>					
						<select name="lCode" id="landmark">
							<option value="2001">남산타워</option>
							<option value="2002">광안대교</option>
							<option value="2003">자유의 여신상</option>						
						</select>
					</td>				
				</tr>
				<tr>
					<td class= Picture>
						<input type="file" name="pPic" size="70" id="file" maxlength="50"/>
					</td>
				</tr>			
				<tr>
					<td colspan="3" class= uploadButton>				
						<input type= "submit" value ="업로드">
					</td>
				</tr>
			</table>
	</form>
