$(function(){
	
	$("#loginForm").submit(function(){
		var uId = $("#uId").val();
		var uPw = $("#userPass").val();
		
		if(uId.length <= 0) {
			alert("아이디를 입력해주세요");
			$("#uId").focus();
			return false;
		}
		if(uPw.length <= 0) {
			alert("비밀번호를 입력해주세요");
			$("#userPass").focus();
			return false;
		}
	});
	
	// 회원가입 폼, 회원정보 수정 폼에서 폼 컨트롤에서 키보드 입력을 체크해 유효한 값을 입력 받게 keyup 이벤트를 처리했다.
	$("#uId").on("keyup", function(){
		var regExp = /[^A-Za-z0-9]/gi;
		if(regExp.test($(this).val())){
			alert("영문 대소문자, 숫자만 입력 가능합니다.");
			$(this).val($(this).val().replace(regExp,""));
		}
	});
	
	/*영문 대소문자, 숫자만 입력하는 이벤트가 비밀번호 입력에도 
	유효 데이터인지 체크*/
	$("#pass1").on("keyup", inputCharReplace);
	$("#pass2").on("keyup", inputCharReplace);
	
	// 회원 가입 폼에서 아이디 중복확인 버튼 클릭되면 중복 확인하는 새 창을 띄워주는 함수
	$("#btnOverlapId").on("click", function(){
		var uId = $("#uId").val();
		url="overlapIdCheck.mvc?uId=" + uId;
		
		if(uId.length == 0) {
			alert("아이디를 입력해주세요.");
			return false;
		}
		if(uId.length < 4) {
			alert("아이디는 4자 이상 입력해주세요.");
			return false;
		}
		
		// 윈도우 팝업 창
		window.open(url, "idCheck", "toolbar=no, location=no, status=no, menubar=no, width=400, height=200");
	});
	
	// 새 창으로 띄운 아이디 찾기 폼에서 중복확인 버튼이 클릭되면 유효성 검사하는 함수
	$("#idCheckForm").on("submit", function(){
		var uId = $("#checkId").val();
		
		if(uId.length == 0) {
			alert("아이디를 입력해주세요.");
			return false;
		}
		if(uId.length < 4) {
			alert("아이디는 4자 이상 입력해주세요.");
			return false;
		}
	});
	
	// 아이디 중복확인 창에서 "아이디 사용 버튼"이 클릭되면 새 창을 닫고 부모창의 회원가입 폼에 입력해주는 함수
	$("#btnIdCheckClose").on("click", function(){
		var uId = $(this).attr("data-id-value");
		opener.document.joinForm.uId.value = uId;
		opener.document.joinForm.isIdCheck.value = true;
		window.close();
	});
	
	//회원 가입 폼 서브밋 될 때 이벤트 처리 - 폼 유효성 검사
	$("#JoinForm").on("submit", function(){
		return joinFormCheck();
	});
	
	// 비밀번호 확인 버튼이 클릭될 때 이벤트
	$("#btnPassCheck").click(function(){
		var oldId = $("#uId").val();
		var oldPass = $("#oldPass").val();
		
		if($.trim(oldPass).length == 0) {
			alert("기존 비밀번호를 입력해주세요.");
			return false;
		}
		var data = "uId=" + oldId + "&uPw=" + oldPass;
		console.log("data : " + data);
		
		$.post("passCheck.ajax", data, function(resultData, status, xhr) {
			if(status == "success") {
				var obj = resultData;
				console.log("resultData : " + resultData);
				
				if(obj.result == -1) {
					alert("존재하지 않는 아이디입니다.");
				} else if(obj.result == 0) {
					alert("비밀번호가 다릅니다. \n비밀번호를 다시 입력하고 확인해주세요");
					$("#oldPass").val("").focus();
				} else if(obj.result == 1){
					alert("비밀번호가 확인되었습니다. \n비밀번호를 수정해주세요.");
					$("#btnPassCheck").prop("disabled", true);
					$("#pass1").focus();
				}
			}
		});
	});
	
	$("#oldPass").change(function() {
		$("#btnPassCheck").prop("disabled", false);
	});
	
	// 회원정보 수정 폼에서 수정하기 버튼이 클릭되면 유효성 검사하는 함수
	$("#memberUpdateForm").on("submit", function() {
		if(! $("#btnPassCheck").prop("disabled")) {
			alert("기존 비밀번호 확인 후 수정 가능합니다. \n기존 비밀번호를 입력한 후에 확인 버튼을 클릭해주세요.");
			return false;
		}
		return joinFormCheck();
	});
	
});
	
/*회원 아이디, 비밀번호, 비밀번호 확인 컨트롤에 사용자가 입력한 값이
영문 대소문자, 숫자만 입력되도록 수정하는 함수*/
function inputCharReplace() {
	var regExp = /[^A-Za-z0-9]/gi;
	if(regExp.text($(this).val())) {
		alert("영문 대소문자, 숫자만 입력 가능합니다.");
		$(this).val($(this).val().replace(regExp, ""));
	}
}
	
// 회원 가입 폼과 회원정보 수정 폼의 유효성 검사하는 함수
function joinFormCheck() {
	var uId = $("#uId").val();
	var pass1 = $("#pass1").val();
	var pass2 = $("#pass2").val();
	var uName = $("#uName").val();
	var uBt = $("#uBt").val();
	var uPhone = $("#uPhone").val();
	var uMail = $("#uMail").val();
	var isIdCheck = $("#isIdCheck").val();
	
	if(uId.length == 0) {
		alert("아이디를 입력해주세요.");
		return false;
	}
	
	if(isIdCheck == 'false') {
		alert("아이디 중복 체크를 해주세요.");
		return false;
	}
	
	if(pass1.length == 0) {
		alert("비밀번호를 입력해주세요.");
		return false;
	}
	
	if(pass2.length == 0) {
		alert("비밀번호 확인을 입력해주세요.");
		return false;
	}
	
	if(pass1 != pass2) {
		alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
		return false;
	}
	
	if(uName.length == 0) {
		alert("이름을 입력해주세요.");
		return false;
	}
	
	if(uBt.length == 0) {
		alert(" 생년월일을 입력해주세요.");
		return false;
	}
	
	if(uPhone.length == 0) {
		alert("휴대폰 번호를 입력해주세요.");
		return false;
	}
	
	if(uMail.length == 0) {
		alert("이메일 주소를 입력해주세요.");
		return false;
	}
}

	
	