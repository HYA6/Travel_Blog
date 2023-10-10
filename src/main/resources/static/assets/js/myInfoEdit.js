$(document).ready(function() {
	// 유저 정보에 저장되어 있는 이메일 적용
	// console.log($("#mail").val());
	const mail = $("#mail").val().split('@'); // 유저 정보에 저장되어 있는 이메일을 @을 경계로 나눈다.
//	console.log(mail[0]);
	$("#emailId").val(mail[0]);
	$("#emailDomain").val(mail[1]);
	
	// 유저 정보에 저장되어 있는 성별에 따라 체크 표시하기
	const gender = $('#gender').val();
//	console.log(gender);
	if (gender == 'on' || gender == '' || gender == null) {
		$('#genderN').prop("checked", true);
	} else if (gender == '남자') {
		$('#genderM').prop("checked", true);
	} else if (gender == '여성') {
		$('#genderF').prop("checked", true);
	};
});

// 이메일 도메인 선택
function setEmailDomain(domain){
	$("#emailDomain").val(domain);
};

// 빈칸 확인
function infoBlankCheck() {
	console.log('infoBlankCheck() 실행');
	let password2 = $('#password2');
	let password3 = $('#password3');
	
	if (password2.val() == null || password2.val() == '') {
		alert("새로운 비밀번호를 입력해주세요.");
		password2.focus();
		return false;
	} else if (password3.val() == null || password3.val() == '') {
		alert("새로운 비밀번호를 확인해주세요.");
		password3.focus();
		return false;
	} else {
		return true;
	};
};

// 현재 비밀번호 확인
function infoPasswordCheck() {
	console.log('infoPasswordCheck() 실행');
	let password1 = $('#password1');
	let passwordCheck = $('#passwordCheck');
	if (password1.val() == passwordCheck.val()){
		return true;
	} else {
		alert("현재 비밀번호가 일치하지 않습니다.");
		password1.focus();
		return false;
	};
};

// 새로운 비밀번호 확인
function infoNewPasswordCheck() {
	console.log('infoPasswordCheck() 실행');
	let password1 = $('#password1');
	let password2 = $('#password2');
	let password3 = $('#password3');
	if (password1.val() == password2.val()){
		alert("기존 비밀번호와 일치합니다.");
		password2.focus();
		return false;
	} else if (password2.val() != password3.val()) {
		alert("새로운 비밀번호가 일치하지 않습니다.");
		password3.focus();
		return false;
	} else {
		return true;
	};
};

//이메일 중복 확인
function infoEmailCheck() {
	console.log('emailCheck() 실행');
	
	// 이메일 형식
	let emailRule =  /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	let emailId =$("#emailId").val(); // 이메일 아이디 부분
	let emailDomain =$("#emailDomain").val(); // 이메일 도메인 부분
	let userEmail = emailId+"@"+emailDomain; // 합친거
	let email = '';
	const mail = $("#mail").val().split('@'); // DB에 저장된 사용자의 이메일을 @을 경계로 나눈다.
	let passwordCheck = document.querySelector('#passwordCheck'); // DB에 저장된 비밀번호
	
	if (!emailRule.test(userEmail)) {
		alert("이메일을 형식에 맞게 입력해주세요.");
		$("#emailId").focus();
		return false;
	};
	
	// 입력한 이메일 중복 검사
	if (emailId != mail[0] || emailDomain != mail[1]) { 
		$.ajax({
			url: 'api/usersEmailCheck',
			type: 'POST',
			data: {'userEmail' : userEmail},
			success : function (data){
//				console.log(data);
				email = data;
				if (passwordCheck != null && infoBlankCheck() == false) { // 빈 칸 있는지 확인
					return false;
				}
//				console.log(email);
//				console.log(userEmail);
				if (userEmail != email){
					$("#mail").val(userEmail);
					infoEdit();
				} else {
					alert("이미 있는 이메일입니다.");
					$("#emailId").focus();
					return false;
				}
	        },
			error: function(){
				console.log("ajax 실패");
				alert("저장 실패");
				return false;
	        }
		});
	} else {
		if (passwordCheck != null && infoBlankCheck() == false) { // 빈 칸 확인
			return false;
		}
		infoEdit();
	};
};

// 정보 수정
function infoEdit() {
	console.log('infoEdit() 실행');
	let infoForm = $('#infoForm'); // form 태그
	let passwordCheck = $('#passwordCheck'); // DB에 저장된 비밀번호
	console.log(passwordCheck.val())
	
	if (passwordCheck.val() != null && infoPasswordCheck() == false) { // 현재 비밀번호 일치하는지 확인
		return false;
	};
	
	if (passwordCheck.val() != null && infoNewPasswordCheck() == false) { // 새로운 비밀번호 일치하는지 확인
		return false;
	};
	
	// 전화번호 패턴
	let patt = new RegExp("[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}");
	let userPhone = $("#userPhone")
	let res = patt.test(userPhone.val());
	// console.log($("#userPhone").val())
	if(userPhone.val() != null && userPhone.val() != '' && !res){ // 전화번호 내용이 있으면서 형식이 맞는지 확인
		alert("전화번호를 정확히 입력하여 주십시오.");
		userPhone.focus();
		return false;
	};
	
	return confirm("수정 하시겠습니까?") ? infoForm.submit() : false;
};

// 회원 탈퇴
function deleteInfo() {
	const userNum = $('#userNum').val();
	if (confirm("탈퇴 하시겠습니까?")) {
		$.ajax({
			url: 'api/myInfoDelete/' + userNum,
			type: 'GET',
			success : function(data) {
				console.log(data + '번 유저 삭제 완료');
				alert('회원 탈퇴가 완료되었습니다.');
				window.location.replace('myInfoDelete');
			},
			error: function() {
				console.log("회원 탈퇴 실패");
				return false;
			}
		});
	} else {
		return false;
	};
};