$("#writeForm").on("submit", function() {
		if($("#country").val().length <= 0) {
			alert("국가명이 입력되지 않았습니다.\n 국가명을 입력해주세요");
			$("#country").focus();			
			return false;
		}
		if($("#city").val().length <= 0) {
			alert("도시명이 입력되지 않았습니다.\n 도시명을 입력해주세요");
			$("#city").focus();
			return false;
		}
		if($("#landmark").val().length <= 0) {
			alert("랜드마크/태그가 입력되지 않았습니다.\n 랜드마크/태그를 입력해주세요");
			$("#landmark").focus();
			return false;
		}
		if($("#pDate").val().length <= 0) {
			alert("날짜가 입력되지 않았습니다.\n 날짜를 입력해주세요");
			$("#pDate").focus();
			return false;
		}
		if($("#file").val().length <= 0) {
			var result = confirm("파일이 추가되지 않았습니다." +
					"\n첨부파일 없이 게시 글을 등록 하시겠습니까?");
			if(!result) {
				$("#file").focus();
				return false;
			}
		}		
	});


