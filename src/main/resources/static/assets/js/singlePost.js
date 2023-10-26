$(document).ready(function() {
	$('#topCommentGup').val($('.topComments').length + 1);
	$('#secondCommentSeq').val($('.downComments').length + 1);
});

// 더보기
function replyOpenMore(event) {
	let commentGup = event.getAttribute('id').substring(9);
	let replyDiv = $('.replyOpenMore' + commentGup).parents('.commentsListDiv');
	replyDiv.toggle();
	console.log(replyDiv.css('display'));
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