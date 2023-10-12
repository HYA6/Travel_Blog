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
	
	$.datepicker.setDefaults({
		dateFormat: 'yy-mm-dd',
		prevText: '이전 달',
		nextText: '다음 달',
		monthNames: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
		monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
		dayNames: ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		showOtherMonths: true,
		showMonthAfterYear: true,
		changeMonth: true,
		changeYear: true,
		yearRange: 'c-30:c+30'
	});
	$('#startDt').datepicker();
	$('#startDt').datepicker("option", "maxDate", $("#endDt").val());
    $('#startDt').datepicker("option", "onClose", function ( selectedDate ) {
        $("#endDt").datepicker( "option", "minDate", selectedDate );
    });
    $('#endDt').datepicker();
    $('#endDt').datepicker("option", "minDate", $("#startDt").val());
    $('#endDt').datepicker("option", "onClose", function ( selectedDate ) {
        $("#startDt").datepicker( "option", "maxDate", selectedDate );
    });
});

let beforeNext = 0; // 이전, 다음 버튼 누른 횟수
let simpleNum = 0; // 간단 양식 누른 횟수

// 이전 버튼
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

// 다음 버튼
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

// 간단 양식 POST 이전
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

//간단 양식 POST 다음
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
	let standard = $('.standardContents').length + 1;
	$('#standardContent').append('<textarea id="standardContent' + standard + 
			'" class="standardContents" name="postContent" row="5" style="background-color: white; resize: none;"></textarea>');
	$('#standardContent').append('<input type="hidden" name="postContentSeq" id="standardContentSeq' + standard + 
			'" value="' + standard + '"/>');
};

// 기본 양식 사진 미리보기
function standardAddImage(input) {
	// 내용 쓰는 곳에 사진 띄우기
	let file = input.files[0];
	$('#standardContent').append('<img src="' + URL.createObjectURL(file) + '" class="standardContents" style="max-width:500px;"/>');
	let standard = $('.standardContents').length;
	$('#standardContent').append('<input type="hidden" name="postImageSeq" id="standardImageSeq' + standard + 
			'" value="' + standard + '"/>');
	// 새로운 파일 첨부 버튼 생성
	let images = $('.standardImages').length;
	$('#standardImage' + images).after('<input type="file" id="standardImage' + ++images + '" class="standardImages"' + 
			' name="files" multiple="multiple" accept=".jpg,.png" onchange="standardAddImage(this)"/>');
};

// 간단 양식 사진 미리보기
function simpleLoadFile(input) {
	let inputClass = input.getAttribute('class');
	let inputNum = Number(inputClass[inputClass.length - 1]);
	let file = input.files[0];
	// div 안에 이미지 출력
	$(input).after('<img src="' + URL.createObjectURL(file) + '" class="simpleImage' + inputNum + 
			'" style="width: 100%; height: 100%; object-fit: contain;"/>');
	// 버튼 숨기기
	$(input).hide();
	// 다음 div에 있는 파일 첨부 버튼 보이기
	let simpleImages = $('.simpleImage' + inputNum).length;
	console.log(simpleImages);
	if (simpleImages == 1) {
		$('#simpleBtn' + inputNum + '_2').show();
	} else if (simpleImages == 2) {
		$('#simpleBtn' + inputNum + '_3').show();
	} else if (simpleImages == 3) {
		$('#simpleBtn' + inputNum + '_4').show();
	};
};
