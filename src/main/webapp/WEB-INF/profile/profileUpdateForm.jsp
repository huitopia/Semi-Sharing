<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>프로필 편집</title>
	</head>
	<body>
		<h3 id="profile">프로필 편집</h3>
		<div id="form" style="width: 800px; margin: 50px auto;">
			<form name="profileUpdate"  enctype="multipart/form-data" method="post" action="profileUpdate.mvc">	
			<table class="readTable">
				<tr >
				<td class="image">프로필사진선택</td>
					<td class="readdiv" >
						<input type="file" name="propic" id="propic" />
					</td>
				</tr >
				<tr>
				<tr>
				<td class="formButton" style="text-align: right;">
					<input  type="submit" value="수정완료" id="btnJoin"/>
				</td>
				</tr>
				</table>
			</form>
		</div>
	</body>
</html>