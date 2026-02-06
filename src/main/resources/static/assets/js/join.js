// 이메일 도메인 선택
function setEmailDomain(domain){
	$("#emailDomain").val(domain);
}

// 아이디 중복 확인
function idCheck() {
    const userId = $("#userId").val();

    if (!userId) {
        alert("아이디를 입력해주세요.");
        return;
    }

    fetch(`/api/usersIdCheck/${userId}`)
        .then(response => {
            if (response.ok) {
                alert("사용 가능한 아이디입니다.");
                $("#idCheckBtn").val("true");
            } else {
                alert("이미 사용 중인 아이디입니다.");
                $("#idCheckBtn").val("false");
                $("#userId").focus();
            }
        })
        .catch(() => alert("아이디 확인 중 오류가 발생했습니다."));
}

// 비밀번호 확인 (UX용)
function passwordCheck() {
    const pw1 = $("#password1").val();
    const pw2 = $("#password2").val();

    if (pw1 !== pw2) {
        alert("비밀번호가 일치하지 않습니다.");
        $("#password2").focus();
        return false;
    }
    return true;
}

// 이메일 중복 확인
function emailCheck() {
	console.log('emailCheck() 실행');
	var emailId =$("#emailId").val();
	var emailDomain =$("#emailDomain").val();
	var userEmail = emailId+"@"+emailDomain;

    fetch(`/api/usersEmailCheck/${userEmail}`)
        .then(response => {
            if (response.ok) {
                $("#mail").val(userEmail);
                join();
            } else {
                alert("이미 사용 중인 이메일입니다.");
                $("#emailId").focus();
            }
        })
        .catch(() => alert("이메일 확인 중 오류가 발생했습니다."));
}

// 회원가입
function join() {
    console.log('join() 실행');

    if ($('#idCheckBtn').val() === 'false') {
        alert('아이디 중복 체크를 해주세요.');
        return;
    }
    if (passwordCheck() === false) {
        return;
    }

    if (!confirm("회원가입 하시겠습니까?")) return;

    const form = document.querySelector('#joinForm');
    const formData = new FormData(form);

    fetch('/users/join', {
        method: 'POST',
        body: formData
    })
        .then(res => res.json())
        .then(data => {
            if (!data.success) {
                alert(data.message); // @Valid 에러 메시지
            } else {
                alert("회원가입이 완료되었습니다.");
                location.href = '/blogChk';
            }
        })
        .catch(() => alert("회원가입 중 오류 발생"));
}