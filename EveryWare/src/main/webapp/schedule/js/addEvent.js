var eventModal = $('#eventModal');     //이벤트 추가 모달
var modalTitle = $('.modal-title');    //모달의 제목부분
var editAllDay = $('#edit-allDay');    //하루종일 체크여부
var editTitle  = $('#edit-title');     //일정명
var editStart  = $('#edit-start');     //시작날짜
var editEnd    = $('#edit-end');       //끝날짜
var editType   = $('#edit-type');      //개인일정, 부서일정
var editColor  = $('#edit-color');      
var editDesc   = $('#edit-desc');      //상세설명
var editId     = $('#edit-id');        

var addBtnContainer = $('.modalBtnContainer-addEvent');
var modifyBtnContainer = $('.modalBtnContainer-modifyEvent');

/* ****************
 *  새로운 일정 생성
 * ************** */
var newEvent = function(start, end, eventType) {

	$("#contextMenu").hide(); //드래그 해서 보인 메뉴를 숨김
	
	
	modalTitle.html('새로운 일정');
	editAllDay.val('');
	editTitle.val('');
	editStart.val(start);
	editEnd.val(end);
	editType.val(eventType).prop("selected", true);  
	editDesc.val('');
	
	
	addBtnContainer.show();
	modifyBtnContainer.hide();
	eventModal.modal('show');

	$('#save-event').unbind();
	
	$('#save-event').on('click', function() {
		var eventData = {
			allDay: false, 		    
			title: editTitle.val(), 
			start: editStart.val(),
			end: editEnd.val(), 	
			type: editType.val(),	
			description: editDesc.val(),	
			textColor: '#ffffff',
			empid : $("#userid").val(),
			deptid: $('#userdept').val(),
		};
	
		var msg = "";
		if (editType.val() == '회사') {
			if ($('#userdept').val() == 'D001') {
				msg = "";
			} else {
				msg = "회사일정은 건드릴 수 없습니다"
			}
		} else if (editType.val() == 'P002') {
			if ($('#jobname').val() != '부장') {
				msg = "부서일정은 건드릴 수 없습니다"
			}
		}
	
		if (eventData.start > eventData.end) {
			alert('끝나는 날짜가 앞설 수 없습니다.');
			return false;
		}

		if (eventData.title === '') {
			alert('일정명은 필수입니다.');
			return false;
		}
		
		var realEndDay;

		if (editAllDay.is(':checked')) {
			eventData.start = moment(eventData.start).format('YYYY-MM-DD');
			//render시 날짜표기수정
			eventData.end = moment(eventData.end).add(1, 'days').format('YYYY-MM-DD');
			
			//DB에 넣을때(선택)
			realEndDay = moment(eventData.end).format('YYYY-MM-DD');

			eventData.allDay = true;
		}

		$("#calendar").fullCalendar('renderEvent', eventData, true);
		eventModal.find('input, textarea').val('');
		editAllDay.prop('checked', false);
		eventModal.modal('hide');
		
		console.log(eventData.start, eventData.end)
		
		//새로운 일정 저장
		$.ajax({
			url: "/EveryWare/insertSchedule.do",
			type: "get",
			data: {
				alldayYn: eventData.allDay,             
				scheTitle: eventData.title,   			
				scheStart: eventData.start,  
				scheEnd: eventData.end,      
				scheType: eventData.type,    
				scheDetail: eventData.description,
				userid : eventData.empid,
				userdept : eventData.deptid,
			},
			success : function(res) {
				console.log(res); //1,0
				if(res > 0) {
					$('#calendar').fullCalendar('removeEvents');
    	            $('#calendar').fullCalendar('refetchEvents');
    	            alert("일정이 저장되었습니다");
				} else {
					alert("일정 저장 실패");
				}
			},
			error : function(xhr) {
				alert("상태 : " + xhr.status);
			},
			dataType: "json"
		});
	});
};