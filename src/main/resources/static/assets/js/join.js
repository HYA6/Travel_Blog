// 이메일 도메인 선택
function setEmailDomain(domain){
	$("#emailDomain").val(domain);
}

// 빈칸 확인
function blankCheck() {
	console.log('blankCheck() 실행');
	var userName = document.querySelector('#userName');
	var userId = document.querySelector('#userId');
	var password1 = document.querySelector('#password1');
	var password2 = document.querySelector('#password2');
	var userBirhtday = document.querySelector('#userBirhtday');
	// 이메일 형식
	var emailRule =  /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	var emailId =$("#emailId").val();
	var emailDomain =$("#emailDomain").val();
	var mail = emailId+"@"+emailDomain;
	$("#mail").val(mail);
	
	if (userName.value == null || userName.value == '') {
		alert("이름을 입력해주세요.");
		userName.focus();
		return false;
	} else if (userId.value == null || userId.value == '') {
		alert("아이디를 입력해주세요.");
		userId.focus();
		return false;
	} else if (password1.value == null || password1.value == '') {
		alert("비밀번호를 입력해주세요.");
		password1.focus();
		return false;
	} else if (password2.value == null || password2.value == '') {
		alert("비밀번호를 확인해주세요.");
		password2.focus();
		return false;
	} else if (userBirhtday.value == null || userBirhtday.value == '') {
		alert("생년월일을 선택해주세요.");
		userBirhtday.focus();
		return false;
	} else if (!emailRule.test(mail)) {
		alert("이메일을 형식에 맞게 입력해주세요.");
		$("#emailId").focus();
		return false;
	} else {
		return true;
	}
}

// 아이디 중복 확인
function idCheck() {
	var userId = document.querySelector('#userId');
	if (userId.value == null || userId.value == '') {
		alert("아이디를 입력해주세요.");
		userId.focus();
		return false;
	}
	// fetch Ajax를 이용해서 REST API를 호출한다.
	const url = '/api/idCheck/' + userId.value;
	console.log(url);
	fetch(url)
		.then(response => {
			console.log(response);
			// http 응답 코드에 따른 메시지 출력
			if (response.ok) {
				alert('사용 가능한 아이디입니다.');
				$('#idCheckBtn').val(true);
			} else {
				alert('중복된 아이디입니다.');
				userId.focus();
			}
		});
}

// 비밀번호 확인
function passwordCheck() {
	console.log('passwordCheck() 실행');
	var password1 = document.querySelector('#password1');
	var password2 = document.querySelector('#password2');
	if (password1.value == password2.value){
		return true;
	} else {
		alert("비밀번호가 일치하지 않습니다.");
		password2.focus();
		return false;
	}
}

// 이메일 중복 확인
function emailCheck() {
	console.log('emailCheck() 실행');
	var emailId =$("#emailId").val();
	var emailDomain =$("#emailDomain").val();
	var userEmail = emailId+"@"+emailDomain;
	var email = '';
	
	$.ajax({
		url: 'api/usersEmailCheck',
		type: 'POST',
		data: {'userEmail' : userEmail},
		success : function (data){
			// console.log(data);
			email = data;
			if (blankCheck() == false) { // 빈 칸 있는지 확인
				return false;
			}
			// console.log(email);
			// console.log(userEmail);
			if (userEmail != email){
				join();
			} else {
				alert("이미 있는 이메일입니다.");
				$("#emailId").focus();
				return false;
			}
        },
		error: function(){
			alert("아작스 실패");
        }
	});
}

// 회원가입
function join() {
	console.log('join() 실행');
	var joinForm = document.querySelector('#joinForm'); // form 태그
	
	if ($('#idCheckBtn').val() == 'false') { // 아이디 중복 체크 여부
		alert('아이디 중복 체크를 해주세요.');
		return false;
	}
	if (passwordCheck() == false) { // 비밀번호 일치하는지 확인
		return false;
	}
	
	// 전화번호 패턴
	var patt = new RegExp("[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}");
	var res = patt.test($("#userPhone").val());
	// console.log($("#userPhone").val())
	if($("#userPhone").val() != null && $("#userPhone").val() != '' && !res){ // 전화번호 내용이 있으면서 형식이 맞는지 확인
		alert("전화번호를 정확히 입력하여 주십시오.");
		$("#userPhone").focus();
		return false;
	}
	
	return confirm("회원가입 하시겠습니까?") ? joinForm.submit() : false;
}