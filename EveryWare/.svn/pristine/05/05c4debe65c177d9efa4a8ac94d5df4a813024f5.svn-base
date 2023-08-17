/* ****************
 *  일정 편집
 * ************** */
var editEvent = function(event, element, view) {
	$('#deleteEvent').data('id', event._id);
	
    $('.popover.fade.top').remove();
    $(element).popover("hide");

    if (event.allDay === true) {
        editAllDay.prop('checked', true);
    } else {
        editAllDay.prop('checked', false);
    }

    if (event.end === null) {
        event.end = event.start;
    }

    if (event.allDay === true && event.end !== event.start) {
        editEnd.val(moment(event.end).subtract(1, 'days').format('YYYY-MM-DD HH:mm'))
    } else {
        editEnd.val(event.end.format('YYYY-MM-DD HH:mm'));
    }

    modalTitle.html('일정 수정');
    editId.val(event._id); 
    editTitle.val(event.title);
    editStart.val(event.start.format('YYYY-MM-DD HH:mm'));
    editType.val(event.type);
    editDesc.val(event.description);

    addBtnContainer.hide();
    modifyBtnContainer.show();
    eventModal.modal('show');
    $("#text").remove();

    //업데이트 버튼 클릭시
    $('#updateEvent').unbind();
    $('#updateEvent').on('click', function() {
		
		console.log(event.type);
		
		var msg = "";
		if(event.type == '회사') {
			if($('#userdept').val() == 'D001') {
				msg = "";
			} else {
				msg = "회사일정은 건드릴 수 없습니다"
			} 
		} else if(event.type == '부서') {
			if($('#jobname').val() != 'P002') {
				msg = "부서일정은 건드릴 수 없습니다"
			}
		}
		
		if(msg != "") {
			alert(msg);
			return false;
		}
				
        if (editStart.val() > editEnd.val()) {
            alert('끝나는 날짜가 앞설 수 없습니다.');
            return false;
        }

        if (editTitle.val() === '') {
            alert('일정명은 필수입니다.')
            return false;
        }
        
        var statusAllDay;
        var startDate;
        var endDate;
        var displayDate;

        if (editAllDay.is(':checked')) {
            statusAllDay = true;
            startDate = moment(editStart.val()).format('YYYY-MM-DD');
            endDate = moment(editEnd.val()).format('YYYY-MM-DD');
            displayDate = moment(editEnd.val()).add(1, 'days').format('YYYY-MM-DD');
        } else {
            statusAllDay = false;
            startDate = editStart.val();
            endDate = editEnd.val();
            displayDate = endDate;
        }

        eventModal.modal('hide');
       
        event.allDay = statusAllDay;
        event.title = editTitle.val();
        event.start = startDate;
        event.end = displayDate;
        event.type = editType.val();
        event.backgroundColor = editColor.val();
        event.description = editDesc.val();
        event._id = editId.val();
	
        $("#calendar").fullCalendar('updateEvent', event);
        
        
        //일정 업데이트
        $.ajax({
            url: "/EveryWare/updateSchedule.do",
            type: "get",
            data: {
            	schid : event._id,
        		schtype : event.type,
        		schtitle : event.title,
        		schstart : event.start,
        		schend : event.end,
        		schdeatil : event.description,
        		allday : event.allDay	
            },
            success: function (res) {
                if(res > 0) {
					alert("일정이 수정되었습니다");
				} else {
					alert("일정 수정이 실패했습니다");
					return false;
				}
            },
            error: function(xhr) {
            	alert("상태 : " + xhr.status);
            },
            dataType: "json"
        });
    });
};

$('#deleteEvent').unbind();
// 삭제버튼
$('#deleteEvent').on('click', function() {
	
	console.log(editType.val());
	
	var msg = "";
	if (editType.val() == '회사') {
		if ($('#userdept').val() == 'D001') {
			msg = "";
		} else {
			msg = "회사일정은 건드릴 수 없습니다"
		}
	} else if (editType.val() == '부서') {
		if ($('#jobname').val() != 'P002') {
			msg = "부서일정은 건드릴 수 없습니다"
		}
	}

	if (msg != "") {
		alert(msg);
		return false;
	}
	
	deleteId = $(this).data('id');
	$("#calendar").fullCalendar('removeEvents', deleteId);
	eventModal.modal('hide');

	$.ajax({
		url: "/EveryWare/deleteSchedule.do",
		type: "get",
		data: {
			schid2: deleteId
		},
		success: function(res) {
			if (res > 0) {
				alert("일정이 삭제되었습니다");
			} else {
				alert("일정 삭제가 실패했습니다");
				return false;
			}
		},
		error: function(xhr) {
			alert("상태 : " + xhr.status);
		}
	});
});
