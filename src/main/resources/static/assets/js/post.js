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

// 구글 맵
function initAutocomplete() {
	const map = new google.maps.Map(document.getElementById("googleMap"), {
		center: { lat: 37.5642135, lng: 127.0016985 },
		zoom: 8,
		mapTypeId: "roadmap",
	});
	// Create the search box and link it to the UI element.
	const input = document.getElementById("pac-input");
	const searchBox = new google.maps.places.SearchBox(input);

//	map.controls[google.maps.ControlPosition.TOP_LEFT].push(input); // 맵 안에 서치박스 넣는 코드
	// Bias the SearchBox results towards current map's viewport.
	map.addListener("bounds_changed", () => {
		searchBox.setBounds(map.getBounds());
	});

	let markers = [];

	// Listen for the event fired when the user selects a prediction and retrieve
	// more details for that place.
	searchBox.addListener("places_changed", () => {
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
				console.log("Returned place contains no geometry");
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
			' name="files" accept=".jpg,.png" onchange="standardAddImage(this)"/>');
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

// 대표 이미지
function thumbnailLoadFile(thnmbnail) {
	let file = thnmbnail.files[0];
	$('#thumbnailImg').attr('src', URL.createObjectURL(file));
	$('#thumbnailImg').show();
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
	} else if($('#categorySelect option:selected').val() == '') {
		alert('카테고리를 선택하세요.')
		return false;
	} else if($('#postPlace').val() == '') {
		alert('여행 장소를 입력하세요.')
		return false;
	};
	
	if($('#thumbnailImg').val() == '') {
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
	formData.append("thumbnailFile", postThumbnail)
	$.ajax({
		url: 'api/uploadThumbnail',
		type : "POST",
        processData : false ,
        contentType : false ,
        data : formData ,
        success : function (filePath) {
//        	console.log(filePath);
        	savePost(filePath);
        },
        error: function() {
			console.log('대표 이미지 업로드 실패');
			return false;
		}
	})
};

// 게시글 저장
function savePost(filePath) {
	console.log('게시글 저장한당');
	let postForm = $('input[type="radio"]:checked').val();
	let postStratDate = $('#startDt').val();
	let postEndDate = $('#endDt').val();
	let postPlace = $('#postPlace').val();
	let postSubject = $('#postSubject').val();
	let postTag = $('#hashtags-hidden').val();
	let blogId = $('#blogId').val();
	let categoryId = $("#categorySelect option:selected").val();
	$.ajax({
		url: 'api/createPost',
		type: 'POST',
		data: { 
			"postForm" : postForm,
		    "postStratDate" : postStratDate,
		    "postEndDate" : postEndDate,
		    "postPlace" : postPlace,
		    "postSubject" : postSubject,
		    "postTag" : postTag,
		    "postThumbnail" : filePath,
		    "blogId" : blogId,
		    "categoryId" : categoryId
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
		formData.append("files", $('.standardImages')[i].files[0]);
	};
//	console.log(formData);
	$.ajax({
		url: 'api/uploadImage',
		type : "POST",
        processData : false ,
        contentType : false ,
        data : formData ,
        success : function (filePathList) {
//        	console.log(filePathList);
        	saveImages(postId, filePathList);
        },
        error: function() {
			console.log('이미지 업로드 실패');
			return false;
		}
	})
};

// 이미지 DB에 저장
function saveImages(postId, filePathList) {
	console.log(filePathList);
	let postForm = $('input[type="radio"]:checked').val();
	if (postForm == 'standard') {
		console.log('기본 양식으로 저장한당');
		let postImageGup = $('#standardImageGup').val();
		for (let i=1; i < $('.standardImages').length; i++) {
			console.log($('#standardImage' + i).file);
			let standardImage = $('#standardImage' + i).val().substring(12);
			let postImageSeq = document.querySelectorAll('input[name="standardImageSeq"]')[i-1].value;
			let postImagePath = filePathList[i-1];
			$.ajax({
				url: 'api/saveImage',
				type: 'POST',
				data: {
					'postImageName' : standardImage,
					'postImagePath' : postImagePath,
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
	} else if (postForm == 'simple') {
		console.log('간단 양식으로 저장한당');
	};
};

// 내용 저장
function saveTexts(postForm) {
	
};