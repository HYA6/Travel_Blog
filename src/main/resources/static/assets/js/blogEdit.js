$(document).ready(function() {
	const thema = $('#blogThema').val().split(',');
	const blogThema = document.getElementsByName('blogThema');
//	console.log(thema[0]);
//	console.log(blogThema[0].value);
//	console.log(thema.length);
//	console.log(blogThema.length);
	for (var i=0; i < thema.length; i++) {
		for (var j=0; j < blogThema.length; j++) {
			if (thema[i] == blogThema[j].value) {
				blogThema[j].setAttribute('checked','checked');
				break;
			};
		};
	};
});

function blogEdit() {
	var blogName = $('#blogName'); // 블로그 이름
	var blogThema = document.querySelector('input[type="checkbox"]:checked') // 체크된 블로그 테마
	var blogUrl = $('#blogUrl'); // 입력한 블로그 주소
	var DBUrl = $('#url'); // DB에 저장된 블로그 주소
	
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
	const url = '/api/urlCheck/' + blogUrl.val();
	// console.log(url);
	if (blogUrl.val() != DBUrl.val()){
		fetch(url)
		.then(response =>{
			if (response.ok) {
				if (confirm("블로그를 수정하시겠습니까?")) {
					$('#url').val(blogUrl.val());
					$('#blogEditForm').submit();
				} else {
					return false;
				};
			} else {
				alert('중복된 블로그 주소 입니다.');
				blogUrl.focus();
			}
		})
	} else {
		return confirm("블로그를 수정하시겠습니까?") ? $('#blogEditForm').submit() : false;
	};
};

// 블로그 삭제
function blogDelete() {
	const blogId = $('#blogId').val();
	if (confirm('블로그를 삭제하시겠습니까?')) {
		$.ajax({
			url: 'api/blogDelete',
			type: 'POST',
			data: {'blogId' : blogId},
			success : function(data) {
				console.log(data + '번 블로그 삭제 완료');
				alert('블로그가 삭제되었습니다.');
				window.location.replace('blogDelete?blogId=' + blogId);
			},
			error: function() {
				console.log('블로그 삭제 실패');
				return false;
			}
		});
	} else {
		return false;
	};
};