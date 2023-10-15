// 새로고침, 뒤로가기 경고창
var checkUnload = true;
$(window).on("beforeunload", function(){
	if(checkUnload) return '이 페이지를 벗어나면 작성된 내용은 저장되지 않습니다.';
});
$("#toMainForm").submit(function(){
	checkUnload = false;
});

/* 메인 카테고리 추가 */
function addMain() { /* 추가할때마다 gup +1, lev 0, seq 0 */
	console.log('addMain() 실행');
	let mainLen = $('.categoryDiv').length - 1; // 실제 카테고리 개수
	let mainCategoryNum = $('#mainCategoryNum'); // 카테고리 개수를 값으로 저장하는 input
	let Mnum = Number(mainCategoryNum.val()) + 1; // 추가되었으므로 1 증가시킨다.
	
	if (mainLen == 11) {
		alert('메인 카테고리는 최대 10개까지 생성 가능합니다.');
		return false;
	};
	
	// 실제 카테고리 개수와 Mnum의 길이가 같을 때(중간 메인 카테고리 삭제 후 ) 처리
	if (mainLen == Mnum) {
		Mnum = mainLen + 1;
	};
	
	// 중간 카테고리가 삭제되었을 때 Mnum 처리
	if (Mnum > 1) {
		for (let i=1; i <= mainLen; i++) {
			let mainId = $('.categoryDiv')[i].getAttribute( 'id' ); // 카테고리Div id 속성값
//			console.log(i + '번째 mainId: ' + mainId); // 여기서 i와 mainId는 같아야 한다.
			// 중간에 비어있는 카테고리가 있으면(i와 mainId가 같지 않으면) Mnum에 i 대입
			if(mainId != 'categoryDiv' + i) { 
				Mnum = i;
				break;
			};
		};
	};
	
	// div 추가
	let mainDiv = document.createElement('div');
	mainDiv.setAttribute('class', 'categoryDiv');
	mainDiv.setAttribute('id', 'categoryDiv' + Mnum);
	mainDiv.style.borderBottom = '1px dashed gray';
	mainDiv.style.marginBottom = '1em';
	// 중간에 비어있으면 비어있는 곳에 추가
	if (mainLen == 0) {
		document.querySelectorAll('.categoryDiv')[Mnum - 1].after(mainDiv); 
	} else {
		for (let i=1; i <= mainLen; i++) {
			// 실제로 있는 메인 카테고리 id들
			let mainId = $('.categoryDiv')[i].getAttribute( 'id' );
			// 실제 메인 카테고리 id 숫자 가져오기
			let mainIdNum = Number(mainId[mainId.length - 1]); 
			if (mainId.length == 13) { mainIdNum = 10 };
			// 클릭한 버튼의 메인부분 id 숫자와 실제 메인 카테고리 id 숫자가 같으면 메인 카테고리 추가
			if (mainIdNum != i) { 
				$('.categoryDiv')[i].before(mainDiv);
				break;
			} else if(mainIdNum == mainLen) {
				$('.categoryDiv')[i].after(mainDiv); 
				break;
			};
		};
	};
	
	// p 추가
	let mainP = document.createElement('p');
	mainP.setAttribute('class', 'mainCategoryName');
	mainP.setAttribute('id', 'pM' + Mnum);
	mainP.setAttribute('onclick', 'saveChk(this)');
	mainP.style.display = 'inline-block';
	mainP.style.marginRight = '1em';
	mainP.style.cursor = 'pointer';
	mainP.innerHTML = Mnum + '번째 메인 카테고리';
	document.querySelectorAll('.categoryDiv')[Mnum].appendChild(mainP); 
	
	// 하위 카테고리 추가 버튼
	let mainInput1 = document.createElement('input');
	mainInput1.setAttribute('type', 'button');
	mainInput1.setAttribute('id', 'addS' + Mnum);
	mainInput1.setAttribute('value', '추가');
	mainInput1.setAttribute('onclick', 'saveChk(this)');
	mainInput1.style.height = '3em';
	mainInput1.style.lineHeight = '0';
	mainInput1.style.padding = '0 1.5em';
	mainInput1.style.marginRight = '0.5em';
	document.querySelectorAll('.categoryDiv')[Mnum].appendChild(mainInput1); 
	
	// 메인 카테고리 삭제 버튼
	let mainInput2 = document.createElement('input');
	mainInput2.setAttribute('type', 'button');
	mainInput2.setAttribute('id', 'deleteM' + Mnum);
	mainInput2.setAttribute('value', '삭제');
	mainInput2.setAttribute('onclick', 'saveChk(this)');
	mainInput2.style.height = '3em';
	mainInput2.style.lineHeight = '0';
	mainInput2.style.padding = '0 1.5em';
	document.querySelectorAll('.categoryDiv')[Mnum].appendChild(mainInput2); 
	
	// 메인 카테고리 아이디 저장하는 input
	let mainInput3 = document.createElement('input');
	mainInput3.setAttribute('type', 'hidden');
	mainInput3.setAttribute('id', 'mainId' + Mnum);
	document.querySelectorAll('.categoryDiv')[Mnum].appendChild(mainInput3); 

	console.log('추가 후 Mnum: ' + Mnum);
	
	$('p').css('color', '#646464');
	$('#pM' + Mnum).css('color', '#2ebaae');
	$('#categoryFormDiv').css('display', 'inline-block');
	mainCategoryNum.val(Mnum);
	$('#categoryGup').val(Mnum);
	$('#categoryLev').val(0);
	$('#categorySeq').val(0);
	$('#categoryGubun').html(Mnum + '번째 메인 카테고리 이름');
	$('#categoryName').val('');
	$("#categoryPublic").prop("checked", false);
	$("#categoryPrivate").prop("checked", false);
	console.log('==================================================');
};

/* 메인 카테고리 삭제 */
function deleteMain(event) { 
	console.log('deleteMain() 실행');
	let buttonId = event.getAttribute( 'id' );
	console.log('클릭된 삭제 버튼 id: ' + buttonId); // 클릭된 삭제 버튼 id 속성값
	let mainCategoryNum = $('#mainCategoryNum'); // 카테고리 개수를 값으로 저장하는 input
	let mainLen = document.querySelectorAll('.categoryDiv').length - 1; // 실제 카테고리 개수
	let categoryDiv = document.querySelectorAll('.categoryDiv'); // class 속성값이 categoryDiv인 태그들
	
	for (let i=1; i <= mainLen; i++) {
		let mainId = categoryDiv[i].getAttribute( 'id' ); // class 속성값이 categoryDiv인 태그 id 속성값
//		console.log(i + '번째 categoryDiv id: ' + mainId);
		if (mainId[mainId.length - 1] == buttonId[buttonId.length - 1]) { // id 속성값의 마지막 숫자가 같으면 삭제
			if (confirm('메인 카테고리를 삭제하시겠습니까?')) {
				// DB에 저장된 메인 카테고리 삭제하기(하위 카테고리 포함)
				let categoryGup = mainId[mainId.length - 1];
				let blogId = $('#blogId').val(); // 블로그 고유 번호
				// 카테고리 삭제
				$.ajax({
					url: 'api/deleteCategory',
					type: 'POST',
					data: { 
						'categoryGup' : categoryGup,
						'blogId' : blogId
					},
					success : function(data) {
						console.log(data);
						categoryDiv[i].remove();
						mainLen -= 1;
						mainCategoryNum.val(mainLen);
						$('#categoryFormDiv').css('display', 'none');
						$('#categoryName').val('');
						$("#categoryPublic").prop("checked", false);
						$("#categoryPrivate").prop("checked", false);
						console.log('삭제 후 카테고리 개수: ' + mainLen);
						$('#saveCheck').val(false);
						alert('메인 카테고리 삭제 완료');
						console.log('==================================================');
					},
					error: function() {
						console.log('카테고리 삭제 실패');
					}
				});
				break;
			} else {
				return false;
			};
		};
	};
};

/* 하위 카테고리 추가 */
function addSub(event) { /* 추가할때마다 gup 그대로, lev = 1, seq + 1 */
	console.log('addSub() 실행');
	let buttonId = event.getAttribute( 'id' );
	console.log('클릭된 추가 버튼 id: ' + buttonId); // 클릭된 버튼 id 속성값
	let mainDivId = Number(buttonId.substring(4)); // 메인 카테고리 id 숫자
	if (mainDivId == 0) { mainDivId = 10; };
//	console.log('mainDivId: ' + mainDivId);
	let subCategoryDiv = document.querySelectorAll('.sub' + mainDivId); // 메인 카테고리에 따른 실제 서브 카테고리 div 전부
	let subLen = subCategoryDiv.length; // 메인 카테고리에 따른 실제 서브 카테고리 개수
	
	if (subLen == 5) {
		alert('서브 카테고리는 최대 5개까지 생성 가능합니다.');
		return false;
	};
	
	// 실제 서브 카테고리가 없으면 0으로 저장한다.
	if (subLen == 0) {
		$('#subCategoryNum').val(0)
	};
	let subCategoryNum = $('#subCategoryNum'); // 서브 카테고리 개수를 값으로 저장하는 input
	let Snum = Number(subCategoryNum.val()) + 1; // 추가되었으므로 1 증가시킨다.
	
	// 메인 카테고리가 다른 서브 카테고리를 추가할 경우 Snum 처리
	// 실제 카테고리 개수 +1 과 Snum이 같지 않으면 Snum에 실제 카테고리 개수 +1 값을 넣어준다.
	if (subLen + 1 != Snum) {
		Snum = subLen + 1;
		console.log('Snum 처리 후: ' + Snum);
	};
	
	// 중간 카테고리가 삭제되었을 때 Snum 처리
	if (subLen >= 1) {
		for (let i=0; i < subLen; i++) {
			let subId = subCategoryDiv[i].getAttribute( 'id' ); // subCategoryDiv id 속성값
//			console.log((i+1) + '번째 subId: ' + subId); // 여기서 i+1과 subId는 같아야 한다.
			// 중간에 비어있는 카테고리가 있으면(i+1과 subId가 같지 않으면) Snum에 i+1 대입
			if(subId != 'subCategoryDiv' + mainDivId + '_' + (i + 1)) { 
				Snum = (i + 1);
				console.log('for문 Snum 처리 후: ' + Snum);
				break;
			};
		};
	};
	
	// div 추가
	let subDiv = document.createElement('div');
	subDiv.setAttribute('class', 'subCategoryDiv sub' + mainDivId);
	subDiv.setAttribute('id', 'subCategoryDiv' + mainDivId + '_' + Snum);
	// 중간에 비어있으면 비어있는 곳에 추가
	let mainLen = $('.categoryDiv').length; // 메인 카테고리 div 개수
	for (let i=1; i < mainLen; i++) {
		// 실제로 있는 메인 카테고리 id들
		let mainId = $('.categoryDiv')[i].getAttribute( 'id' );
		// 실제 메인 카테고리 id 숫자 가져오기
		let mainIdNum = Number(mainId[mainId.length - 1]); 
		if (mainId.length == 13) { mainIdNum = 10 };
		// 클릭한 버튼의 메인부분 id 숫자와 실제 메인 카테고리 id 숫자가 같으면 서브 카테고리 추가
		if (mainDivId == mainIdNum) { 
			// 실제 서브 카테고리 개수가 0이면 div 안에 새로 생성
			if (subLen == 0) { 
				$('.categoryDiv')[i].appendChild(subDiv);
				break;
			} else {
				for (let j=0; j < subLen; j++) {
					// 실제 서브 카테고리 id들
					let subId = subCategoryDiv[j].getAttribute( 'id' ); 
					// 실제 서브 카테고리 id 숫자 가져오기
					let subIdNum = Number(subId[subId.length - 1]); 
//					console.log((j+1) + '번째 subIdNum: ' + subIdNum); // 여기서 j+1과 subIdNum는 같아야 한다.
					if(subIdNum != j+1) {
						subCategoryDiv[j].before(subDiv);
						break;
					} else if (subIdNum == subLen) { 
						// 실제 서브 카테고리 id 숫자와 실제 서브 카테고리 개수가 같으면 div 안에(맨 밑에) 새로 생성
						$('.categoryDiv')[i].appendChild(subDiv);
						break;
					};
				};
				break;
			};
		};
	};
	
	subCategoryDiv = document.querySelectorAll('.sub' + mainDivId); // div 추가 후 메인 카테고리에 따른 실제 서브 카테고리 div 전부
	// p, input 넣는다.
	for (let i=0; i < subCategoryDiv.length ; i++) {
		let subCategoryDivId = subCategoryDiv[i].getAttribute( 'id' );
		// 서브 카테고리 div 중 추가된 div와 같은 아이디인 곳에 p, input을 넣는다.
		if (subCategoryDivId == 'subCategoryDiv' + mainDivId + '_' + Snum) {
			// p 추가
			let subP = document.createElement('p');
			subP.setAttribute('class', 'subCategoryName');
			subP.setAttribute('id', 'pS' + mainDivId + '_' + Snum);
			subP.setAttribute('onclick', 'saveChk(this)');
			subP.style.display = 'inline-block';
			subP.style.marginRight = '1em';
			subP.style.cursor = 'pointer';
			subP.innerHTML = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└ ' + mainDivId + '-' + Snum + '번째 서브 카테고리';
			document.querySelectorAll('.sub' + mainDivId)[i].appendChild(subP);
			
//			// 마지막 위치의 카테고리 추가 버튼
//			let subInput1 = document.createElement('input');
//			subInput1.setAttribute('type', 'button');
//			subInput1.setAttribute('id', 'addL' + mainDivId + '_' + Snum);
//			subInput1.setAttribute('value', '하위 카테고리 추가');
//			subInput1.setAttribute('onclick', 'saveChk(this)');
//			subInput1.style.height = '3em';
//			subInput1.style.lineHeight = '0';
//			subInput1.style.padding = '0 1.5em';
//			subInput1.style.marginRight = '0.5em';
//			document.querySelectorAll('.sub' + mainDivId)[i].appendChild(subInput1); 
			
			// 서브 카테고리 삭제 버튼
			let subInput2 = document.createElement('input');
			subInput2.setAttribute('type', 'button');
			subInput2.setAttribute('id', 'deleteS' + mainDivId + '_' + Snum);
			subInput2.setAttribute('value', '삭제');
			subInput2.setAttribute('onclick', 'saveChk(this)');
			subInput2.style.height = '3em';
			subInput2.style.lineHeight = '0';
			subInput2.style.padding = '0 1.5em';
			document.querySelectorAll('.sub' + mainDivId)[i].appendChild(subInput2); 

			// 서브 카테고리 아이디 저장하는 input
			let subInput3 = document.createElement('input');
			subInput3.setAttribute('type', 'hidden');
			subInput3.setAttribute('id', 'subId' + mainDivId + '_' + Snum);
			document.querySelectorAll('.sub' + mainDivId)[i].appendChild(subInput3); 
			
			break;
		};
	};
	
	// 색 지정
	$('p').css('color', '#646464');
	$('#pS' + mainDivId + '_' + Snum).css('color', '#2ebaae');
	// 입력칸 보이기
	$('#categoryFormDiv').css('display', 'inline-block');
	// 각각의 값 지정
	subCategoryNum.val(Snum);
	$('#categoryGup').val(mainDivId);
	$('#categoryLev').val(1);
	$('#categorySeq').val(Snum);
	// 입력칸 내용 초기화
	$('#categoryGubun').html(mainDivId + '-' + Snum + '번째 서브 카테고리 이름');
	$('#categoryName').val('');
	$("#categoryPublic").prop("checked", false);
	$("#categoryPrivate").prop("checked", false);
	// 저장 여부 false로 갱신
	$('#saveCheck').val(false);
	console.log('==================================================');
};

/* 하위 카테고리 삭제 */
function deleteSub(event) {
	console.log('deleteSub() 실행');
	let buttonId = event.getAttribute( 'id' );
	console.log('클릭된 삭제 버튼 id: ' + buttonId); // 클릭된 삭제 버튼 id 속성값 ('deleteS' + 메인 카테고리 id 숫자 + '_' + 서브 카테고리 id 숫자)
	
	let mainDivId = Number(buttonId.substring(7,8)); // 메인 카테고리 id 숫자
	if (buttonId.length == 11) { mainDivId = 10; };
	let subCategoryDiv = document.querySelectorAll('.sub' + mainDivId); // 메인 카테고리에 따른 실제 서브 카테고리 div 전부
	let len = subCategoryDiv.length; // 메인 카테고리에 따른 실제 서브 카테고리 개수
	
	for (let i=0; i < len; i++) {
		let subId = subCategoryDiv[i].getAttribute( 'id' ); // class 속성값이 subCategoryDiv인 태그 id 속성값
//		console.log(i + '번째 categoryDiv id: ' + mainId);
		if (subId[subId.length - 1] == buttonId[buttonId.length - 1]) { // id 속성값의 마지막 숫자가 같으면 삭제
			if (confirm('서브 카테고리를 삭제하시겠습니까?')) {
				// DB에 저장된 하위 카테고리 삭제하기
				let categoryId = $('#subId' + mainDivId + '_' + subId[subId.length - 1]).val();
				let blogId = $('#blogId').val(); // 블로그 고유 번호
				
				if (categoryId != '' && categoryId != null) {
					// 카테고리 삭제
					$.ajax({
						url: 'api/deleteCategory',
						type: 'POST',
						data: { 
							'categoryId' : categoryId,
							'blogId' : blogId
						},
						success : function(data) {
							console.log(data);
							alert('서브 카테고리 삭제 완료');
							subCategoryDiv[i].remove();
							len -= 1;
							$('#subCategoryNum').val(len);
							$('#categoryFormDiv').css('display', 'none');
							$('#categoryName').val('');
							$("#categoryPublic").prop("checked", false);
							$("#categoryPrivate").prop("checked", false);
							console.log('삭제 후 서브 카테고리 개수: ' + len);
							$('#saveCheck').val(false);
							console.log('==================================================');
						},
						error: function() {
							console.log('카테고리 삭제 실패');
						}
					});
					break;
				} else {
					subCategoryDiv[i].remove();
					len -= 1;
					$('#subCategoryNum').val(len);
					$('#categoryFormDiv').css('display', 'none');
					$('#categoryName').val('');
					$("#categoryPublic").prop("checked", false);
					$("#categoryPrivate").prop("checked", false);
					console.log('삭제 후 서브 카테고리 개수: ' + len);
					$('#saveCheck').val(false);
					console.log('==================================================');
					break;
				};
			} else {
				return false;
			};
		};
	};
};

/* 마지막 위치의 카테고리 추가 */
/*
function addLast(event) {  추가할때마다 gup 그대로, lev = 2, seq + 1 
	console.log('addLast() 실행');
//	console.log(event);
};
*/

/* 마지막 위치의 카테고리 삭제 */
/*
function deleteLast(event) {
	console.log('deleteLast() 실행');
//	console.log(event);
};
*/

// 카테고리 선택 시 
function clickP(event) {
	console.log('clickP() 실행');
	let buttonId = event.getAttribute( 'id' );
	console.log('클릭된 카테고리 아이디: ' + buttonId);
	
	let mainDivId = Number(buttonId.substring(2,3)); // 메인 카테고리 id 숫자
	if (buttonId.length == 4 || buttonId.length == 6) { mainDivId = 10; };
//	console.log('mainDivId: ' + mainDivId);
	let categoryName = $('#categoryName');
	let categoryPublic = $('#categoryPublic');
	let categoryPrivate = $('#categoryPrivate');
	let saveCheck = $('#saveCheck');
	let blogId = $('#blogId').val(); // 블로그 고유 번호
	let categoryId = '';
	
	if (buttonId.length == 3 || buttonId.length == 4){
		// 클릭한 카테고리가 메인 카테고리라면
		categoryId = $('#mainId' + mainDivId).val();
		if (categoryId != null && categoryId != '') {
			$.ajax({
				url: 'api/selectCategoryById',
				type: 'POST',
				data: {
					'categoryId' : categoryId,
					'categoryName' : '',
					'categoryPrivate' : '',
					'blogId' : blogId
				},
				success : function(dto) {
					if (dto != null && dto != '') {
						console.log('메인 카테고리 불러오기 완료');
						categoryName.val(dto.categoryName);
						if (categoryPublic.val() == dto.categoryPrivate) {
							categoryPublic.prop("checked", true);
						} else if(categoryPrivate.val() == dto.categoryPrivate) {
							categoryPrivate.prop("checked", true);
						};
					} else { // 저장된 내용이 없으면 입력칸을 비운다.
						console.log('해당 메인 카테고리에 저장된 내용 없음');
						categoryName.val('');
						categoryPublic.prop("checked", false);
						categoryPrivate.prop("checked", false);
					};
				},
				error: function() {
					console.log('메인 카테고리 불러오기 실패');
				}
			});
		} else {
			console.log('해당 카테고리 고유 번호가 없음');
			categoryName.val('');
			categoryPublic.prop("checked", false);
			categoryPrivate.prop("checked", false);
		};
		saveCheck.val(false);
		$('#categoryLev').val(0);
		$('#categorySeq').val(0);
		$('#categoryGubun').html(mainDivId + '번째 메인 카테고리 이름');
	} else {
		// 클릭한 카테고리가 서브 카테고리라면
		let subDivId = Number(buttonId[buttonId.length - 1]);
//			console.log('subDivId: ' + subDivId);
		categoryId = $('#subId' + mainDivId + '_' + subDivId).val();
		if (categoryId != null && categoryId != '') {
			$.ajax({
				url: 'api/selectCategoryById',
				type: 'POST',
				data: {
					'categoryId' : categoryId,
					'categoryName' : '',
					'categoryPrivate' : '',
					'blogId' : blogId
				},
				success : function(dto) {
					if (dto != null && dto != '') {
						console.log('서브 카테고리 불러오기 완료');
						categoryName.val(dto.categoryName);
						if (categoryPublic.val() == dto.categoryPrivate) {
							categoryPublic.prop("checked", true);
						} else if(categoryPrivate.val() == dto.categoryPrivate) {
							categoryPrivate.prop("checked", true);
						};
					} else {
						console.log('해당 서브 카테고리에 저장된 내용 없음');
						categoryName.val('');
						categoryPublic.prop("checked", false);
						categoryPrivate.prop("checked", false);
					};
				},
				error: function() {
					console.log('서브 카테고리 불러오기 실패');
				}
			});
		} else {
			console.log('해당 카테고리 고유 번호가 없음');
			categoryName.val('');
			categoryPublic.prop("checked", false);
			categoryPrivate.prop("checked", false);
		};
		saveCheck.val(false);
		$('#categoryLev').val(1);
		$('#categorySeq').val(subDivId);
		$('#categoryGubun').html(mainDivId + '-' + subDivId + '번째 서브 카테고리 이름');
	};
	$('p').css('color', '#646464');
	event.style.color = '#2ebaae';
	$('#categoryFormDiv').css('display', 'inline-block');
	$('#categoryGup').val(mainDivId);
	console.log('==================================================');
};

// 카테고리 입력한 내용 DB에 저장 및 수정
function createCategory() {
	let categoryName = $('#categoryName').val(); // 카테고리 이름
	let categoryPrivate = document.querySelector('input[type="radio"]:checked') // 체크된 카테고리 공개 여부
	let categoryGup = $('#categoryGup').val(); // 카테고리 그룹(상위 카테고리 구분)
	let categoryLev = $('#categoryLev').val(); // 카테고리 레벨(하위 카테고리 구분)
	let categorySeq = $('#categorySeq').val(); // 카테고리 출력 순서(카테고리 내의 출력 순서)
	let blogId = $('#blogId').val(); // 블로그 고유 번호
	
	// 빈칸 확인
	if (categoryName == null || categoryName == '') {
		alert("카테고리 이름을 입력해주세요.");
		$('#categoryName').focus();
		return false;
	} else if (categoryPrivate == null) {
		alert("카테고리 공개 여부를 선택해주세요.");
		return false;
	};
	
	// 아이디 지정
	let categoryId = '';
	if (categorySeq == 0) {
		categoryId = $('#mainId' + categoryGup).val();
	} else {
		categoryId = $('#subId' + categoryGup + '_' + categorySeq).val();
	};
	
	// 자물쇠 모양
	let lock = document.createElement('i');
	lock.setAttribute('class', 'fas fa-lock');
	lock.style.marginRight = '1em';
	
	// 카테고리 저장 및 수정
	$.ajax({
		url: 'api/createCategory',
		type: 'POST',
		data: {
			'categoryId' : categoryId,
			'categoryName' : categoryName,
			'categoryPrivate' : categoryPrivate.value,
			'categoryGup' : categoryGup,
			'categoryLev' : categoryLev,
			'categorySeq' : categorySeq,
			'blogId' : blogId
		},
		success : function(data) {
			console.log(data + '번 카테고리 저장 및 수정 완료');
			alert('카테고리 저장 및 수정 완료');
			// 저장 후 카테고리 이름(p태그) 바꾸기
			if (categoryLev == 0) {
				// 메인 카테고리
				$('#pM' + categoryGup).html(categoryName);
				if (categoryPrivate.value == 'N') {
					$('#pM' + categoryGup).css('margin-right', '0.5em');
					$('#pM' + categoryGup).after(lock);
				};
				$('#mainId' + categoryGup).val(data);
			} else if (categoryLev == 1) {
				// 서브 카테고리
				$('#pS' + categoryGup + '_' + categorySeq).html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└ ' + categoryName);
				if (categoryPrivate.value == 'N') {
					$('#pS' + categoryGup + '_' + categorySeq).css('margin-right', '0.5em');
					$('#pS' + categoryGup + '_' + categorySeq).after(lock);
				};
				$('#subId' + categoryGup + '_' + categorySeq).val(data);
			};
			$('#saveCheck').val(true);
			$('#toMaincheck').val(true);
		},
		error: function() {
			console.log('카테고리 저장 실패');
			return false;
		}
	});
};

// 저장 여부 확인
function saveChk(event) {
	let categoryName = $('#categoryName').val(); // 카테고리 이름
	let categoryPrivate = document.querySelector('input[type="radio"]:checked') // 체크된 카테고리 공개 여부
	let categoryGup = $('#categoryGup').val(); // 카테고리 그룹(상위 카테고리 구분)
	let categoryLev = $('#categoryLev').val(); // 카테고리 그룹(상위 카테고리 구분)
	let categorySeq = $('#categorySeq').val(); // 카테고리 출력 순서(카테고리 내의 출력 순서)
	let blogId = $('#blogId').val(); // 블로그 고유 번호
	let DBCheck = '';
	
	if ($('#saveCheck').val() == 'false'){
		if (categoryName != '' || categoryPrivate != null) { // 이름이나 공개 여부에 값이 있으면
			// 입력되어있는 값과 DB에 저장된 값이 다를 경우
			$.ajax({
				url: 'api/selectCategory',
				type: 'POST',
				data: {
					'categoryGup' : categoryGup,
					'categoryLev' : categoryLev,
					'categorySeq' : categorySeq,
					'blogId' : blogId
				},
				success : function(dto) {
//					console.log(typeof(dto)); // string
					if (dto != '') {
						if (categoryName != dto.categoryName || categoryPrivate.value != dto.categoryPrivate) {
							// DB에 내용이 있다면 내용과 비교해서 다를 경우 저장 여부 확인
							if (confirm('입력한 내용이 저장되지 않았습니다. 계속하시겠습니까?')) {
								execute(event)
							} else {
								return false
							};
						} else {
							// DB에 내용이 있다면 내용과 비교해서 같을 경우 id에 따라 함수 실행
							execute(event)
						};
					} else {
						// DB에 내용이 없다면 그냥 저장 여부 확인
						if (confirm('입력한 내용이 저장되지 않았습니다. 계속하시겠습니까?')) {
							execute(event)
						} else {
							return false
						};
					};
				},
				error : function() { console.log('카테고리 불러오기 실패'); }
			});
		} else {
			// 이름이나 공개 여부에 값이 없으면 id에 따라 함수 실행
			execute(event);
		};
	} else {
		// saveCheck 값이 true(방금 저장한 상태)이면 id에 따라 함수 실행
		execute(event);
	};
};

// 클릭한 버튼에 따른 함수 실행
function execute(event) {
	let id = event.getAttribute( 'id' );
//	console.log(id.substring(0,7));
	if (id == 'addMainCategory') {
		addMain();
	} else if (id.substring(0,7) == 'deleteM') {
		deleteMain(event);
	} else if (id.substring(0,4) == 'addS') {
		addSub(event);
	} else if (id.substring(0,7) == 'deleteS') {
		deleteSub(event);
	} else if (id.substring(0,1) == 'p') {
		clickP(event);
	};
};

// 카테고리가 한 개도 없을 경우 다음으로 버튼 막기
function checkCategory() {
	if ($('.categoryDiv').length == 1 || $('#toMaincheck').val() == 'false') {
		alert('카테고리를 만들어주세요.');
		return false;
	} else {
		$('#toMainForm').submit();
	};
};