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
			$('#createPost').hide();
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
			$('#createPost').hide();
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
			$('#createPost').show();
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
			$('#createPost').show();
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
			'" class="standardContents" row="5" style="background-color: white; resize: none;"></textarea>');
	$('#standardContent').append('<input type="hidden" name="standardContentSeq" value="' + standard + '"/>');
};

// 기본 양식 사진 미리보기
function standardAddImage(input) {
	// 내용 쓰는 곳에 사진 띄우기
	let file = input.files[0];
	$('#standardContent').append('<img src="' + URL.createObjectURL(file) + '" class="standardContents" title="' + 
			$(input).val().substring(12) + '" style="max-width:500px;"/>');
	let standard = $('.standardContents').length;
	$('#standardContent').append('<input type="hidden" name="standardImageSeq" value="' + standard + '"/>');
	// 새로운 파일 첨부 버튼 생성
	let imagesLen = $('.standardImages').length;
	$('#standardImage' + imagesLen).after('<input type="file" id="standardImage' + (imagesLen+1) + '" class="standardImages"' + 
			' name="files" multiple="multiple" accept=".jpg,.png" onchange="standardAddImage(this)"/>');
	// 버튼 값 저장 후 숨기기
	$('#standardImageDiv').append('<input type="text" id="standardImagesValue' + imagesLen + '" disabled/>');
	$('#standardImagesValue' + imagesLen).val($(input).val().substring(12));
	$(input).hide();
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

// 해시태그
const hashtagsInput = document.getElementById("hashtags");
const hashtagsContainer = document.getElementById("hashtags-container");
const hiddenHashtagsInput = document.getElementById("hashtags-hidden");

let hashtags = [];
function addHashtag(tag) {
    tag = tag.replace(/[\[\]]/g, '').trim();
    if(tag && !hashtags.includes(tag)) {
        const span = document.createElement("span");
        span.innerText = "#" + tag + " ";
        span.classList.add("hashtag");

        const removeButton = document.createElement("button");
        removeButton.innerText = "x";
        removeButton.classList.add("remove-button");
        removeButton.addEventListener("click", () => {
            hashtagsContainer.removeChild(span);
            hashtags = hashtags.filter((hashtag) => hashtag !== tag);
            hiddenHashtagsInput.value = hashtags.join(",");
        });

        span.appendChild(removeButton);
        hashtagsContainer.appendChild(span);
        hashtags.push(tag);
        hiddenHashtagsInput.value = hashtags.join(",");
    };
};

hashtagsInput.addEventListener("keydown", (event) => {
    if (event.key === 'Enter') {
        event.preventDefault();
        const tag = hashtagsInput.value.trim();
        if (tag) {
            addHashtag(tag);
            hashtagsInput.value = "";
        };
    };
});

// 게시글 저장
function createPost() {
	// 빈 곳 확인
	if($('#postSubject').val() == '') {
		alert('제목을 입력하세요.')
		return false;
	} else if($('#startDt').val() == '' || $('#endDt').val() == '') {
		alert('첫날과 마지막날을 모두 선택하세요.')
		return false;
	} else if($("#categorySelect option:selected").val() == '') {
		alert('카테고리를 선택하세요.')
		return false;
	} else if($("#hashtags-hidden").val() == '') {
		if(!confirm('태그 없이 저장하시겠습니까?')) {
			return false;
		}
	};
	// 양식에 따라 저장
	let postForm = $('input[type="radio"]:checked').val();
	savePost(postForm);
};

// 게시글 저장
function savePost(postForm) {
	if (postForm == 'standard') {
		console.log('기본 양식으로 저장한당');
		let postStratDate = $('#startDt').val();
		let postEndDate = $('#endDt').val();
		let postPlace = '';
		let postSubject = $('#postSubject').val();
		let postTag = $('#hashtags-hidden').val();
		let blogId = $('#blogId').val();
		let categoryId = $("#categorySelect option:selected").val();
		
		let postThumbnail = $('#postThumbnail').files[0];
		console.log(postThumbnail);
		
		// ajax로 전달할 폼 객체
	    var formData = new FormData();
	    // 폼 객체에 파일추가, append("변수명", 값)
	    formData.append("postForm", postForm);
	    formData.append("postStratDate", postStratDate);
	    formData.append("postEndDate", postEndDate);
	    formData.append("postPlace", postPlace);
	    formData.append("postSubject", postSubject);
	    formData.append("postTag", postTag);
	    formData.append("postThumbnail", postThumbnail);
	    formData.append("blogId", blogId);
	    formData.append("categoryId", categoryId);
		
		$.ajax({
			url: 'api/uploadPost',
			type: 'POST',
			data: { formData },
			dataType: "text",
			// processData: true=> get방식, false => post방식
	        // contentType: true => application/x-www-form-urlencoded, 
	        //                false => multipart/form-data
	        processData: false,
	        contentType: false,
			success : function(data) {
				console.log(data);
			},
			error: function() {
				console.log('게시글 저장 실패');
				return false;
			}
		});
		
	} else if (postForm == 'simple') {
		console.log('간단 양식으로 저장한당');
	};
};

// 이미지 저장
function saveImage(postForm) {
	for (let i=1; i < $('.standardImages').length; i++) {
		let standardImage = $('#standardImage' + i).val().substring(12);
		let postImageGup = $('#standardImageGup').val();
		let postImageSeq = document.querySelectorAll('input[name="standardImageSeq"]')[i-1].value;
		saveImage(standardImage, postImageGup, postImageSeq);
	};
	$.ajax({
		url: 'api/uploadFile',
		type: 'POST',
		data: {
			'postImageName' : standardImage,
			'postImageGup' : postImageGup,
			'postImageSeq' : postImageSeq,
			'blogId' : blogId,
			
		},
		success : function(data) {
			console.log(data);
		},
		error: function() {
			console.log('블로그 삭제 실패');
			return false;
		}
	});
};

// 내용 저장
function saveText(postForm) {
	
};