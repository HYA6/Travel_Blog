function blog() {
	var blogName = $('#blogName'); // 블로그 이름
	var blogThema = document.querySelector('input[type="checkbox"]:checked') // 체크된 블로그 테마
	var blogUrl = $('#blogUrl'); // 블로그 주소
	
	// 빈칸 확인
	if (blogName.val() == null || blogName.val() == '') {
		alert("블로그 이름을 입력해주세요.");
		blogName.focus();
		return false;
	} else if (blogThema == null) {
		alert("블로그 테마를 선택해주세요.");
		blogThema.focus();
		return false;
	} else if (blogUrl.val() == null || blogUrl.val() == '') {
		alert("블로그 주소를 입력해주세요.");
		blogUrl.focus();
		return false;
	};
	
	// fetch Ajax를 이용해서 REST API를 호출한다.
	const url = '/api/blogUrlCheck/' + blogUrl.val();
	console.log(url);
	fetch(url)
		.then(response =>{
			if (response.ok) {
				if (confirm("블로그를 생성하시겠습니까?")) {
					$('#DBUrl').val(blogUrl.val());
					$('#blogCreateForm').submit();
				} else {
					return false;
				}
			} else {
				alert('중복된 블로그 주소 입니다.');
				blogUrl.focus();
			}
		});
};