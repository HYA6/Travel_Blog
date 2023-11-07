$(document).ready(function() {
	$('#topCommentGup').val($('.topComments').length + 1);
	$('#secondCommentSeq').val($('.downComments').length + 1);
	$('.simpleImageDiv2').parents('.simpleImageDivEach').hide();
	$('.simpleImageDiv3').parents('.simpleImageDivEach').hide();
	$('.simpleImageDiv4').parents('.simpleImageDivEach').hide();
	$('.simpleImageDiv5').parents('.simpleImageDivEach').hide();
	$('#simpleTextDiv2').parents('.simpleTextDivEach').hide();
	$('#simpleTextDiv3').parents('.simpleTextDivEach').hide();
	$('#simpleTextDiv4').parents('.simpleTextDivEach').hide();
	$('#simpleTextDiv5').parents('.simpleTextDivEach').hide();
	$('#simpleLeftBtn').attr("disabled", true);
	// 간단 양식 게시글이 1개이면 다음 버튼 비활성화
	if ($('.simpleImageDiv2').parents('.simpleImageDivEach').length == 0
			&& $('#simpleTextDiv2').parents('.simpleTextDivEach').length == 0) {
		$('#simpleRightBtn').attr("disabled", true);
	};
});

// 간단 양식 이전 버튼
function simpleBefore() {
	let simpleImageDiv1 = $('.simpleImageDiv1').parents('.simpleImageDivEach');
	let simpleImageDiv2 = $('.simpleImageDiv2').parents('.simpleImageDivEach');
	let simpleImageDiv3 = $('.simpleImageDiv3').parents('.simpleImageDivEach');
	let simpleImageDiv4 = $('.simpleImageDiv4').parents('.simpleImageDivEach');
	let simpleImageDiv5 = $('.simpleImageDiv5').parents('.simpleImageDivEach');
	let simpleTextDiv1 = $('#simpleTextDiv1').parents('.simpleTextDivEach');
	let simpleTextDiv2 = $('#simpleTextDiv2').parents('.simpleTextDivEach');
	let simpleTextDiv3 = $('#simpleTextDiv3').parents('.simpleTextDivEach');
	let simpleTextDiv4 = $('#simpleTextDiv4').parents('.simpleTextDivEach');
	let simpleTextDiv5 = $('#simpleTextDiv5').parents('.simpleTextDivEach');
	// 현재 이미지나 텍스트가 보이고 없지 않으면 이전으로 간다.
	if (simpleImageDiv5.css('display') != 'none' && simpleImageDiv5.css('display') != undefined
			|| simpleTextDiv5.css('display') != 'none' && simpleTextDiv5.css('display') != undefined) {
		simpleImageDiv5.hide();
		simpleTextDiv5.hide();
		simpleImageDiv4.show();
		simpleTextDiv4.show();
		// 다음 버튼 활성화
		$('#simpleRightBtn').attr("disabled", false);
	} else if (simpleImageDiv4.css('display') != 'none' && simpleImageDiv4.css('display') != undefined
			|| simpleTextDiv4.css('display') != 'none' && simpleTextDiv4.css('display') != undefined) {
		simpleImageDiv4.hide();
		simpleTextDiv4.hide();
		simpleImageDiv3.show();
		simpleTextDiv3.show();
		// 다음 버튼 활성화
		$('#simpleRightBtn').attr("disabled", false);
	} else if (simpleImageDiv3.css('display') != 'none' && simpleImageDiv3.css('display') != undefined
			|| simpleTextDiv3.css('display') != 'none' && simpleTextDiv3.css('display') != undefined) {
		simpleImageDiv3.hide();
		simpleTextDiv3.hide();
		simpleImageDiv2.show();
		simpleTextDiv2.show();
		// 다음 버튼 활성화
		$('#simpleRightBtn').attr("disabled", false);
	} else if (simpleImageDiv2.css('display') != 'none' && simpleImageDiv2.css('display') != undefined
			|| simpleTextDiv2.css('display') != 'none' && simpleTextDiv2.css('display') != undefined) {
		simpleImageDiv2.hide();
		simpleTextDiv2.hide();
		simpleImageDiv1.show();
		simpleTextDiv1.show();
		// 다음 버튼 활성화
		$('#simpleRightBtn').attr("disabled", false);
		// 이전 버튼 비활성화
		$('#simpleLeftBtn').attr("disabled", true);
	};
};

// 간단 양식 다음 버튼
function simpleNext() {
	let simpleImageDiv1 = $('.simpleImageDiv1').parents('.simpleImageDivEach');
	let simpleImageDiv2 = $('.simpleImageDiv2').parents('.simpleImageDivEach');
	let simpleImageDiv3 = $('.simpleImageDiv3').parents('.simpleImageDivEach');
	let simpleImageDiv4 = $('.simpleImageDiv4').parents('.simpleImageDivEach');
	let simpleImageDiv5 = $('.simpleImageDiv5').parents('.simpleImageDivEach');
	let simpleTextDiv1 = $('#simpleTextDiv1').parents('.simpleTextDivEach');
	let simpleTextDiv2 = $('#simpleTextDiv2').parents('.simpleTextDivEach');
	let simpleTextDiv3 = $('#simpleTextDiv3').parents('.simpleTextDivEach');
	let simpleTextDiv4 = $('#simpleTextDiv4').parents('.simpleTextDivEach');
	let simpleTextDiv5 = $('#simpleTextDiv5').parents('.simpleTextDivEach');
	// 현재 이미지나 텍스트가 보이고 다음 이미지나 텍스트가 있으면 넘긴다.
	if ((simpleImageDiv1.css('display') != 'none' && simpleImageDiv1.css('display') != undefined 
			|| simpleTextDiv1.css('display') != 'none' && simpleTextDiv1.css('display') != undefined) 
			&& (simpleImageDiv2.length != 0 || simpleTextDiv2.length != 0)) {
		simpleImageDiv1.hide();
		simpleTextDiv1.hide();
		simpleImageDiv2.show();
		simpleTextDiv2.show();
		// 이전 버튼 활성화
		$('#simpleLeftBtn').attr("disabled", false);
		if (simpleImageDiv3.length == 0) {
			$('#simpleRightBtn').attr("disabled", true);
		};
	} else if ((simpleImageDiv2.css('display') != 'none' && simpleImageDiv2.css('display') != undefined 
				|| simpleTextDiv2.css('display') != 'none' && simpleTextDiv2.css('display') != undefined) 
				&& (simpleImageDiv3.length != 0 || simpleTextDiv3.length != 0)) {
		simpleImageDiv2.hide();
		simpleTextDiv2.hide();
		simpleImageDiv3.show();
		simpleTextDiv3.show();
		// 다음 게시글이 없으면 다음 버튼 비활성화
		if (simpleImageDiv4.length == 0) {
			$('#simpleRightBtn').attr("disabled", true);
		};
	} else if ((simpleImageDiv3.css('display') != 'none' && simpleImageDiv3.css('display') != undefined 
			|| simpleTextDiv3.css('display') != 'none' && simpleTextDiv3.css('display') != undefined) 
			&& (simpleImageDiv4.length != 0 || simpleTextDiv4.length != 0)) {
		simpleImageDiv3.hide();
		simpleTextDiv3.hide();
		simpleImageDiv4.show();
		simpleTextDiv4.show();
		// 다음 게시글이 없으면 다음 버튼 비활성화
		if (simpleImageDiv5.length == 0) {
			$('#simpleRightBtn').attr("disabled", true);
		};
	} else if ((simpleImageDiv4.css('display') != 'none' && simpleImageDiv4.css('display') != undefined 
			|| simpleTextDiv4.css('display') != 'none' && simpleTextDiv4.css('display') != undefined) 
			&& (simpleImageDiv5.length != 0 || simpleTextDiv5.length != 0)) {
		simpleImageDiv4.hide();
		simpleTextDiv4.hide();
		simpleImageDiv5.show();
		simpleTextDiv5.show();
		// 다음 버튼 비활성화
		$('#simpleRightBtn').attr("disabled", true);
	};
};

// 더보기
function replyOpenMore(event) {
	let commentGup = event.getAttribute('id').substring(9);
	let replyDiv = $('.replyOpenMore' + commentGup).parents('.commentsListDiv');
	replyDiv.toggle();
//	console.log(replyDiv.css('display'));
	if (replyDiv.css('display') == 'none') {
		$('#replyShow' + commentGup).hide();
		$('#replyClose' + commentGup).show();
	} else {
		$('#replyShow' + commentGup).show();
		$('#replyClose' + commentGup).hide();
	};
};

// 답글
function replyOpen(event) {
	let commentId = event.getAttribute('id').substring(8);
	$('#reply' + commentId).toggle();
};