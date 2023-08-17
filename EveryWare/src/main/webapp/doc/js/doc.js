/**
 * 
 */
$.approvalServer = function(){
	$.ajax({
		url: `${mypath}/approveDoc.do`,
		type: 'post',
		data: { "id" : docId,
						"note" : vnote},
		success: function(res){
			history.go(0);
		},
		error: function(xhr){
			alert('상태 : ' + xhr.status);
		},
		dataType: 'json'
		})
}

$.rejectServer = function(){
	$.ajax({
		url: `${mypath}/rejectDoc.do`,
		type: 'post',
		data: { "id" : docId,
						"note" : vnote },
		success: function(res){
			history.go(0);
		},
		error: function(xhr){
			alert('상태 : ' + xhr.status);
		},
		dataType: 'json'
		})
}

$.checkServer = function(){
	$.ajax({
		url: `${mypath}/checkApprover.do`,
		type: 'post',
		data: { "id" : docId },
		success: function(res){
			if(res == 'ok'){
				
				if(vtype == "confirm"){
					$.approvalServer();
					
				} else if(vtype == "reject"){
					$.rejectServer();
				}
				
				$('.approval-btn').remove();
				$('.reject-btn').remove();
				$('.dropdown-content').remove();
			} else{
				//결재순서 아님
				$('.modal').css('display', 'none');
				alert('다른 결재자가 처리중입니다.')
			}
		},
		error: function(xhr){
			alert('상태 : ' + xhr.status);
		},
		dataType: 'json'
	})
}

$.scrapServer = function(){
	$.ajax({
		url: `${mypath}/scrapDoc.do`,
		type: 'get',
		data: { "id" : docId,
						"isScrap" : isScrap },
		success: function(res){
			if(res=="insert"){
				isScrap = 1;
				$('#scrap-star').attr('class', 'fa fa-star');
				$('#snackbar').text('보관함에 추가합니다.');
			} else{
				isScrap = 0;
				$('#scrap-star').attr('class', 'fa fa-star-o');
				$('#snackbar').text('보관함에서 삭제합니다.');
			}
			showSnackbar();
		},
		error: function(xhr){
			alert('상태 : ' + xhr.status);
		},
		dataType: 'json'
	})
}