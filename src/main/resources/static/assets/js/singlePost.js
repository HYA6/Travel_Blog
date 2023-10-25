// 댓글 답글 입력창 표시
function replyOpen(event) {
	let eventId = event.getAttribute('id');
	let commentId = eventId.subString(8);
	console.log(commentId);
//	$('#reply' + commentId).show();
};

// 답글 더보기
function replyOpenMore(event) {
	let eventId = event.getAttribute('id');
	let commentGup = eventId.substring(9);
	console.log(commentGup);
//	$('#replyOpenMore' + commentGup).show();
};