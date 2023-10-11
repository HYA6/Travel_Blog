$(document).ready(function() {
	$('#postBefore').attr('disabled', true);
	$('#standardTable').hide();
	$('#simpleTable1').hide();
	$('#simpleTable2').hide();
	$('#simpleTable3').hide();
	$('#simpleTable4').hide();
	$('#simpleTable5').hide();
	$('#simpleLeft').hide();
	$('#simpleRight').hide();
	$('#tagTable').hide();
});

let beforeNext = 0; // 이전, 다음 버튼 누른 횟수
let simpleNum = 0; // 간단 양식 누른 횟수

function before() {
	let postForm = $('input[type="radio"]:checked').val();
	let categoryId = $('select > option:checked').val();
	let startDt = $('#startDt').val();
	let endDt = $('#endDt').val();
	// 양식에 따라 글 쓰는 테이블 보이기
	if (postForm == 'standard') {
		beforeNext -= 1;
		if (beforeNext == 1) {
			$('#tagTable').hide();
			$('#standardTable').show();
			$('#postNext').attr('disabled', false);
			$('#postSubmit').hide();
		} else if (beforeNext == 0) {
			$('#standardTable').hide();
			$('#table').show();
			$('#postBefore').attr('disabled', true);
		};
	} else if (postForm == 'simple') {
		beforeNext -= 1;
		if (beforeNext == 1) {
			simpleNum = 0;
			$('#tagTable').hide();
			$('#simpleTable1').show();
			$('#simpleLeft').show();
			$('#simpleRight').show();
			$('#postNext').attr('disabled', false);
			$('#postSubmit').hide();
		} else if (beforeNext == 0) {
			$('#simpleTable1').hide();
			$('#simpleTable2').hide();
			$('#simpleTable3').hide();
			$('#simpleTable4').hide();
			$('#simpleTable5').hide();
			$('#table').show();
			$('#simpleLeft').hide();
			$('#simpleRight').hide();
			$('#postBefore').attr('disabled', true);
		};
	};
};

function next() {
	let postForm = $('input[type="radio"]:checked').val();
	let categoryId = $('select > option:checked').val();
	let startDt = $('#startDt').val();
	let endDt = $('#endDt').val();
	if (postForm == null) {
		alert('양식을 선택하세요.');
		return false;
	};
	// 양식에 따라 글 쓰는 테이블 보이기
	if (postForm == 'standard') {
		beforeNext += 1;
		if (beforeNext == 1) {
			$('#table').hide();
			$('#standardTable').show();
			$('#postBefore').attr('disabled', false);
		} else if (beforeNext == 2) {
			$('#standardTable').hide();
			$('#tagTable').show();
			$('#postNext').attr('disabled', true);
			$('#postSubmit').show();
		};
	} else if (postForm == 'simple') {
		beforeNext += 1;
		if (beforeNext == 1) {
			simpleNum = 0;
			$('#table').hide();
			$('#simpleTable1').show();
			$('#simpleLeft').show();
			$('#simpleRight').show();
			$('#postBefore').attr('disabled', false);
		} else if (beforeNext == 2) {
			$('#simpleTable1').hide();
			$('#simpleTable2').hide();
			$('#simpleTable3').hide();
			$('#simpleTable4').hide();
			$('#simpleTable5').hide();
			$('#tagTable').show();
			$('#simpleLeft').hide();
			$('#simpleRight').hide();
			$('#postNext').attr('disabled', true);
			$('#postSubmit').show();
		};
	};
};

function simpleBefore() {
	if (simpleNum == 4) {
		$('#simpleTable5').hide();
		$('#simpleTable4').show();
	} else if (simpleNum == 3) {
		$('#simpleTable4').hide();
		$('#simpleTable3').show();
	} else if (simpleNum == 2) {
		$('#simpleTable3').hide();
		$('#simpleTable2').show();
	} else if (simpleNum == 1) {
		$('#simpleTable2').hide();
		$('#simpleTable1').show();
	} else if (simpleNum == 0) {
		simpleNum += 1;
	};
	simpleNum -= 1;
};

function simpleNext() {
	simpleNum += 1;
	if (simpleNum == 1) {
		$('#simpleTable1').hide();
		$('#simpleTable2').show();
	} else if (simpleNum == 2) {
		$('#simpleTable2').hide();
		$('#simpleTable3').show();
	} else if (simpleNum == 3) {
		$('#simpleTable3').hide();
		$('#simpleTable4').show();
	} else if (simpleNum == 4) {
		$('#simpleTable4').hide();
		$('#simpleTable5').show();
	} else if (simpleNum == 5) {
		simpleNum -= 1;
	};
};

// 기본 양식 글쓰기 추가
function standardAddText() {
	let text = $('.standardTexts').length;
	$('#standardContent').append('<textarea id="standardText' + text + 
			'" class="standardTexts" row="5" style="background-color: white; resize: none;"></textarea>');
};

// 기본 양식 사진 미리보기
function standardLoadFile(input) {
	let images = $('.standardImages').length;
	let file = input.files[0];
	// 내용 쓰는 곳에 사진 띄우기
	$('#standardContent').append('<img src="' + URL.createObjectURL(file) + '" style="max-width:500px;"/>');
	// 새로운 파일 첨부 버튼 생성
	$('#standardImage' + images).after('<input type="file" id="standardImage' + (++images) + '" class="standardImages"' + 
			' name="files" multiple="multiple" accept=".jpg,.png" onchange="loadFile(this)"/>');
};

// 간단 양식 사진 미리보기
function simpleLoadFile(input) {
	let file = input.files[0];
	$(input).after('<img src="' + URL.createObjectURL(file) + '" class="simpleImages" style="width: 100%; height: 100%; object-fit: contain;"/>');
	$(input).hide();
};



