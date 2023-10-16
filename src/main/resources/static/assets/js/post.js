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
	
	// 데이트피커
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
	$('#startDt').datepicker('option', 'maxDate', $('#endDt').val());
    $('#startDt').datepicker('option', 'onClose', function ( selectedDate ) {
        $('#endDt').datepicker( 'option', 'minDate', selectedDate );
    });
    $('#endDt').datepicker();
    $('#endDt').datepicker('option', 'minDate', $('#startDt').val());
    $('#endDt').datepicker('option', 'onClose', function ( selectedDate ) {
        $('#startDt').datepicker( 'option', 'maxDate', selectedDate );
    });
});

// 구글 맵
function initAutocomplete() {
	const map = new google.maps.Map(document.getElementById('googleMap'), {
		center: { lat: 37.5642135, lng: 127.0016985 },
		zoom: 8,
		mapTypeId: 'roadmap',
	});
	// Create the search box and link it to the UI element.
	const input = document.getElementById('pac-input');
	const searchBox = new google.maps.places.SearchBox(input);

//	map.controls[google.maps.ControlPosition.TOP_LEFT].push(input); // 맵 안에 서치박스 넣는 코드
	// Bias the SearchBox results towards current map's viewport.
	map.addListener('bounds_changed', () => {
		searchBox.setBounds(map.getBounds());
	});

	let markers = [];

	// Listen for the event fired when the user selects a prediction and retrieve
	// more details for that place.
	searchBox.addListener('places_changed', () => {
		const places = searchBox.getPlaces();
		if (places.length == 0) {
			return;
		};
		// Clear out the old markers.
		markers.forEach((marker) => {
			marker.setMap(null);
		});
		markers = [];
		// For each place, get the icon, name and location.
		const bounds = new google.maps.LatLngBounds();
		places.forEach((place) => {
			if (!place.geometry || !place.geometry.location) {
				console.log('Returned place contains no geometry');
				return;
			};
			const icon = {
					url: place.icon,
					size: new google.maps.Size(71, 71),
					origin: new google.maps.Point(0, 0),
					anchor: new google.maps.Point(17, 34),
					scaledSize: new google.maps.Size(25, 25),
			};
			// Create a marker for each place.
			markers.push(
				new google.maps.Marker({
					map,
					icon,
					title: place.name,
					position: place.geometry.location,
				}),
			);
			// 여행 장소 저장
			$('#postPlace').val(place.name);
			$('#postPlaceDiv').show();
			if (place.geometry.viewport) {
				// Only geocodes have viewport.
				bounds.union(place.geometry.viewport);
			} else {
				bounds.extend(place.geometry.location);
			}
		});
		map.fitBounds(bounds);
	});
};

//====================================================================================================================================

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
			$('#createPostBtn').hide();
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
			$('#createPostBtn').hide();
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
			$('#createPostBtn').show();
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
			$('#createPostBtn').show();
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

// ====================================================================================================================================

// 기본 양식 글쓰기 추가
function standardAddText() {
	let standard = $('.standardTextContents').length + 1;
	let seq = $('[name="postContent"]').length + 1;
	let standardContents = $('.standardTextContents');
	// 중간에 삭제 된 후 추가될 경우
	if (standard > 1) {
		let postContent = $('.standardSeq');
		let lastPostContent = postContent[postContent.length - 1];
		seq = Number(lastPostContent.value) + 1;
//		console.log('중간에 삭제 후 추가되는 기본 양식 순서: ' + seq);
		let standardId = standardContents[standard - 2].getAttribute('id');
		standard = Number(standardId[standardId.length-1]) + 1;
//		console.log('중간에 삭제 후 추가되는 기본 양식 텍스트 아이디: ' + standard);
	};
	$('#standardContent').append('<div id="standardTextDiv' + standard + '" class="standardTextDiv"></div>');
	$('#standardTextDiv' + standard).append('<textarea id="standardContent' + standard + 
			'" class="standardTextContents" name="postContent" placeholder="내용을 입력하세요."></textarea>');
	$('#standardTextDiv' + standard).append('<p name="' + standard +  
			'" class="standardTextBtn" onclick="standardDeleteText(this)">X</p>');
	$('#standardTextDiv' + standard).append('<input type="hidden" id="standardContentSeq' + standard + 
			'" name="standardContentSeq" class="standardSeq" value="' + seq + '"/>');
};

//기본 양식 글부분 삭제
function standardDeleteText(btn) {
	let deleteId = btn.getAttribute('name');
//	console.log('삭제할 기본 양식 이미지 파일 아이디: ' + deleteId);
	// 아이디가 standardImagesValueDiv, standardImage, standardImageLabel인 태그 삭제
	$('#standardTextDiv' + deleteId).remove();
};

// 기본 양식 사진 미리보기
function standardAddImage(input) {
	let file = input.files[0];
	let standardContents = $('.standardImgContents');
	let standard = standardContents.length + 1;
	let seq = $('[name="postContent"]').length + 1;
	// 중간에 삭제 된 후 추가될 경우
	if (standard > 1) {
		let postContent = $('.standardSeq');
		let lastPostContent = postContent[postContent.length - 1];
		seq = Number(lastPostContent.value) + 1;
//		console.log('중간에 삭제 후 추가되는 기본 양식 순서: ' + seq);
		let standardId = standardContents[standard - 2].getAttribute('id');
		standard = Number(standardId) + 1;
//		console.log('중간에 삭제 후 추가되는 기본 양식 이미지 파일 아이디: ' + standard);
	};
	// 내용 쓰는 곳에 사진 띄우기
	$('#standardContent').append('<img src="' + URL.createObjectURL(file) + '" id="' + standard + 
			'" class="standardImgContents" name="postContent" title="' + 
			$(input).val().substring(12) + '"/>');
	$('#standardContent').append('<input type="hidden" id="standardImageSeq' + standard + 
			'" name="standardImageSeq" class="standardSeq" value="' + seq + '"/>');
	// 새로운 파일 첨부 버튼 생성
	let imagesLen = $('.standardImages').length;
	$('#standardImage' + standard).after('<label id="standardImageLabel' + (standard+1) + 
			'" class="standardImageLabel" for="standardImage' + (standard+1) + '">파일 첨부</label>');
	$('#standardImageLabel' + (standard+1)).after('<input type="file" id="standardImage' + (standard+1) + 
			'" class="standardImages" name="files" accept=".jpg,.png" onchange="standardAddImage(this)"/>');
	// 버튼 값 저장 후 숨기기
	$('#standardImageDiv').append('<div id="standardImagesValueDiv' + standard + '" class="standardImagesValueDiv"></div>');
	$('#standardImagesValueDiv' + standard).append('<input type="text" id="standardImagesValue' + standard + '" disabled/>');
	// 나중에 수정 기능 추가하기
//	$('#standardImagesValueDiv' + standard).append('<p name="' + standard + 
//			'" class="standardImageBtn" style="margin-right: 0.5em;" onclick="standardUpdateImage(this)">수정</p>');
	$('#standardImagesValueDiv' + standard).append('<p name="' + standard + 
			'" class="standardImageBtn" onclick="standardDeleteImage(this)">X</p>'); // 삭제 버튼
	$('#standardImagesValue' + standard).val($(input).val().substring(12));
	$('#standardImageLabel' + standard).hide();
};

// 기본 양식 사진 미리보기 삭제
function standardDeleteImage(btn) {
	let deleteId = btn.getAttribute('name');
//	console.log('삭제할 기본 양식 이미지 파일 아이디: ' + deleteId);
	// 아이디가 standardImagesValueDiv, standardImage, standardImageLabel인 태그 삭제
	$('#standardImageLabel' + deleteId).remove();
	$('#standardImage' + deleteId).remove();
	$('#standardImagesValueDiv' + deleteId).remove();
	$('#' + deleteId).remove();
	$('#standardImageSeq' + deleteId).remove();
};

//====================================================================================================================================

// 간단 양식 사진 미리보기
function simpleLoadFile(input) {
	let inputId = input.getAttribute('id');
	let inputIdSeq = inputId.substring(9);
	let inputNum = Number(inputId[9]);
	let file = input.files[0];
	// div 안에 이미지 출력
	$(input).after('<img src="' + URL.createObjectURL(file) + '" class="simpleImage' + inputNum + 
		'" id="simpleImage' + inputIdSeq + '"/>');
	$(input).after('<p name="' + inputIdSeq + 
		'" class="simpleImageBtn" onclick="simpleDeleteImage(this)">X</p>');
	// 버튼 숨기기
	$('[for="simpleBtn' + inputIdSeq + '"]').hide();
//	$(input).hide();
};

// 간단 양식 사진 미리보기 삭제
function simpleDeleteImage(btn) {
	let deleteId = btn.getAttribute('name');
	$('#simpleBtn' + deleteId).val('');
	$('#simpleImage' + deleteId).remove();
	$(btn).remove();
	$('[for="simpleBtn' + deleteId + '"]').show();
};

//====================================================================================================================================

// 대표 이미지
function thumbnailLoadFile(thnmbnail) {
	let file = thnmbnail.files[0];
	$('#thumbnailImg').attr('src', URL.createObjectURL(file));
	$('#thumbnailImg').show();
//	console.log('썸네일 값: ' + $(thnmbnail).val().substring(12));
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

//====================================================================================================================================

// 게시글 저장 전 빈 곳 확인
function createPost() {
	// 제목, 여행 날짜, 카테고리, 여행 장소 확인
//	if($('#postSubject').val() == '') {
//		alert('제목을 입력하세요.')
//		return false;
//	} else if($('#categorySelect option:selected').val() == '') {
//		alert('카테고리를 선택하세요.')
//		return false;
//	} else if($('#startDt').val() == '' || $('#endDt').val() == '') {
//		alert('첫날과 마지막날을 모두 선택하세요.')
//		return false;
//	} else if($('#postPlace').val() == '') {
//		alert('여행 장소를 입력하세요.')
//		return false;
//	};
	// 게시글 내용 부분
	let postForm = $('input[type="radio"]:checked').val();
	if (postForm == 'standard') {
		let standardTextContents = $('.standardTextContents');
		if (standardTextContents.length == 0 && $('.standardImgContents').length == 0) {
			alert('게시글은 글 또는 사진 둘 중 하나는 반드시 있어야 합니다.');
			return false;
		} else if (standardTextContents.length != 0) {
			for(let i=0; i < standardTextContents.length; i++) {
				if (standardTextContents[i].value == '' || standardTextContents[i].value == null) {
					alert('게시글 내용을 입력해주세요.');
					return false;
					break;
				};
			};
		};
	} else if (postForm == 'simple') {
		if()
	};
	// 대표 이미지와 태그 확인
	if($('#postThumbnail').val() == '') {
		if(!confirm('대표 이미지 없이 저장하시겠습니까?')) {
			return false;
		};
	};
	if($('#hashtags-hidden').val() == '') {
		if(!confirm('태그 없이 저장하시겠습니까?')) {
			return false;
		};
	};
	// 대표 이미지 업로드 -> 게시글 저장 -> 게시글 이미지 업로드 -> 게시글 이미지 저장 -> 게시글 내용 저장
	uploadThumbnail();
};

// 대표 이미지 업로드
function uploadThumbnail() {
	let formData = new FormData();
	let postThumbnail = document.querySelector('#postThumbnail').files[0];
	// 대표 이미지가 비어있지 않을 경우 업로드한다.
	if (postThumbnail != undefined) {
		formData.append("thumbnailFile", postThumbnail)
		$.ajax({
			url: 'api/uploadThumbnail',
			type : "POST",
			processData : false ,
			contentType : false ,
			data : formData ,
			success : function (saveName) {
        		console.log(saveName);
        		if (saveName == 'N') {
        			alert('썸네일이 이미지 파일이 아닙니다.');
        			return false;
        		} else {
        			savePost(saveName);
        		};
			},
			error: function() {
				console.log('대표 이미지 업로드 실패');
				return false;
			}
		})
	} else {
		let filePath = null;
		savePost(filePath);
	};
};

// 게시글 저장
function savePost(saveName) {
	console.log('게시글 저장한당');
	let postForm = $('input[type="radio"]:checked').val(); // 게시글 양식
	let postStartDate = $('#startDt').val(); // 여행 시작 날짜
	let postEndDate = $('#endDt').val(); // 여행 끝 날짜
	let postPlace = $('#postPlace').val(); // 여행 장소
	let postSubject = $('#postSubject').val(); // 게시글 제목
	let postTag = $('#hashtags-hidden').val(); // 게시글 태그
	let blogId = $('#blogId').val(); // 게시글이 저장된 블로그 고유 번호
	let categoryId = $("#categorySelect option:selected").val(); // 선택된 카테고리 고유 번호
	$.ajax({
		url: 'api/createPost',
		type: 'POST',
		data: { 
			'postForm' : postForm,
		    'postStartDate' : postStartDate,
		    'postEndDate' : postEndDate,
		    'postPlace' : postPlace,
		    'postSubject' : postSubject,
		    'postTag' : postTag,
		    'postThumbnail' : saveName, // 게시글 썸네일 이름
		    'blogId' : blogId,
		    'categoryId' : categoryId
		},
		success : function(postId) {
//			console.log(postId);
			uploadImages(postId);
		},
		error: function() {
			console.log('게시글 저장 실패');
			return false;
		}
	});
};

// 이미지 업로드(이미지 업로드 후 DB에 저장)
function uploadImages(postId) {
	let formData = new FormData();
	for (let i=0; i < $('.standardImages').length-1; i++) {
		formData.append('files', $('.standardImages')[i].files[0]);
	};
//	console.log(formData);
	$.ajax({
		url: 'api/uploadImage',
		type : 'POST',
        processData : false ,
        contentType : false ,
        data : formData ,
        success : function (fileNameList) {
//        	console.log(fileNameList);
        	saveImages(postId, fileNameList);
        },
        error: function() {
			console.log('이미지 업로드 실패');
			return false;
		}
	})
};

// 이미지 DB에 저장
function saveImages(postId, fileNameList) {
	let postForm = $('input[type="radio"]:checked').val();
	if (postForm == 'standard') {
		console.log('기본 양식으로 저장한당');
		let postImageGup = $('#standardImageGup').val();
		for (let i=0; i < $('.standardImages').length-1 ; i++) {
			let postImageName = fileNameList[i];
			let postImageSeq = document.querySelectorAll('input[name="standardImageSeq"]')[i].value;
			$.ajax({
				url: 'api/saveImage',
				type: 'POST',
				async : false,
				data: {
					'postImageName' : postImageName,
					'postImageGup' : postImageGup,
					'postImageSeq' : postImageSeq,
					'postId' : postId
				},
				success : function(data) {
					console.log(data);
				},
				error: function() {
					console.log('이미지 저장 실패');
					return false;
				}
			});
		};
		saveTexts(postId);
	} else if (postForm == 'simple') {
		console.log('간단 양식으로 저장한당');
	};
};

// 내용 저장
function saveTexts(postId) {
	let postForm = $('input[type="radio"]:checked').val();
	if (postForm == 'standard') {
		console.log('기본 양식으로 저장한당');
		let postContentGup = $('#standardContentGup').val();
		for (let i=0; i < $('.standardTextContents').length; i++) {
			let postContent = document.querySelectorAll('.standardTextContents')[i].value;
			let postContentSeq = document.querySelectorAll('input[name="standardContentSeq"]')[i].value;
			$.ajax({
				url: 'api/saveContent',
				type: 'POST',
				async : false,
				data: {
					'postContent' : postContent,
					'postContentGup' : postContentGup,
					'postContentSeq' : postContentSeq,
					'postId' : postId
				},
				success : function(data) {
					console.log(data);
					location.replace('postToMain');
				},
				error: function() {
					console.log('이미지 저장 실패');
					return false;
				}
			});
		};
	} else if (postForm == 'simple') {
		console.log('간단 양식으로 저장한당');
	};
};