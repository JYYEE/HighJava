//인사과인지 검사

/*D001	인사팀
D002	총무팀
D003	총무팀
D004	개발팀
D005	영업팀
P001	대표이사
P002	부장
P003	차장
P004	과장
P005	대리
P006	사원
B001	공지사항*/

isHR = ($('#userdept').val() == 'D001');
isDirector = ($('#jobname').val() == 'P002');
/*
인사과 모든 인원은 회사일정조정가능

인사과 P002은 자신의 부서일정 조정가능

P002은 자기 부서 일정 조정가능 ==> 회사일정X

일반 사원은 읽기만 가능 O
*/
CanEdit = true;
CanSelect = true;

if(isHR && isDirector) { //인사과이면서 P002
	CanEdit = true;
	CanSelect = true;
}
if(!(isHR) && !(isDirector)) { //일반사원
	$('#updateEvent').hide();
	$('#deleteEvent').hide();
	CanEdit = false;
	CanSelect = false;
}
if(isDirector && !(isHR)) { //인사과가 아니면서 P002
	$('.com').hide();  //회사일정
	CanEdit = true;
	CanSelect = true;
}
if(!(isDirector) && isHR) {//인사과이면서 P002이 아닌
	$('.dept').hide();  //부서일정
	CanEdit = true;
	CanSelect = true;
} 

var calendar = $('#calendar').fullCalendar({
	locale: 'ko',
	timezone: "local",
	nextDayThreshold: "09:00:00",
	allDaySlot: true,
	displayEventTime: true,
	displayEventEnd: true,
	firstDay: 0, //월요일이 먼저 오게 하려면 1, 일요일은 0
	weekNumbers: false,
	//******************************
	selectable: CanEdit,  //선택기능 드래그, 
	editable: CanSelect,    //수정
	//******************************
	weekNumberCalculation: "ISO",
	eventLimit: true,
	views: {
		month: {
			eventLimit: 5
		}
	},
	eventLimitClick: 'week', //popover
	navLinks: true,
	defaultDate: moment(),
	timeFormat: 'HH:mm',
	defaultTimedEventDuration: '01:00:00',
	minTime: '00:00:00',
	maxTime: '24:00:00',
	slotLabelFormat: 'HH:mm',
	weekends: true,
	nowIndicator: true,
	dayPopoverFormat: 'MM/DD dddd',
	longPressDelay: 0,
	eventLongPressDelay: 0,
	selectLongPressDelay: 0,
	
	header: {
		left: 'today, viewWeekends',
		center: 'prev, title, next',
		right: 'month,agendaWeek,agendaDay,listWeek'
	},
	views: {
		month: {
			columnFormat: 'dddd'
		},
		agendaWeek: {
			columnFormat: 'M/D ddd',
			titleFormat: 'YYYY년 M월 D일',
			eventLimit: false
		},
		agendaDay: {
			columnFormat: 'dddd',
			eventLimit: false
		},
		listWeek: {
			columnFormat: ''
		}
	},
	
	eventRender: function(event, element, view) {
		//일정에 hover시 요약
		element.popover({
			title: $('<div/>', {
				class: 'popoverTitleCalendar',
				text: event.title
			}).css({
				'background': event.backgroundColor,
				'color': event.textColor
			}),
			content: $('<div />', {
				class: 'popoverInfoCalendar'
			}).append('<p><strong>구분:</strong> ' + event.type + '</p>')
			  .append('<p><strong>시간:</strong> ' + getDisplayEventDate(event) + '</p>')
			  .append('<div class="popoverDescCalendar"><strong>설명:</strong> ' + event.description + '</div>'),
			delay: {
				show: "800",
				hide: "50"
			},
			trigger: 'hover',
			placement: 'top',
			html: true,
			container: 'body'
		});
		return filtering(event);
	},

	/* ****************
	 *  일정 받아옴 
	 * ************** */
	events: function(start, end, timezone, callback) {
		$.ajax({
			url: "/EveryWare/selectAllSchedule.do",
			type: "get",
			data: {
				deptid : $("#userdept").val()
			},
			dataType: 'json',
			success: function(res) {
				var events = [];
				var fixedDate = res.map(function(array) {
					var isCompanySchedule = (array.sch_type == "회사");
					var isEditable = true;
					var isSelectable = true;
					
					if (array.sch_type == isCompanySchedule) {
						if ($('#userdept').val() == 'D001') {
							//msg = "";
						} else {
							//msg = "회사일정은 건드릴 수 없습니다"
							isEditable = false;
							isSelectable = false;
						}
					} else if (array.sch_type != isCompanySchedule) {
						if ($('#jobname').val() != 'P002') {
							//msg = "부서일정은 건드릴 수 없습니다"
							isEditable = false;
							isSelectable = false;
						}
					}
					
					var evt = {
						_id: array.sch_id,
						allDay: charToBoolean(array.sch_boolean),
						title: array.sch_name,
						start: array.sch_startdate,
						end: array.sch_enddate,
						description: array.sch_detail,
						type: array.sch_type,
						deptid: array.code,
						textColor: "#fefefe",
						//backgroundColor: array.sch_type === "회사" ? "#00FF00" : "#FFC0CB"
						//******************************************************************
						backgroundColor: isCompanySchedule ? "#3DF5A7" : "#096FE0",
						editable: isEditable,
						selectable: isSelectable
					}
					events.push(evt);
					return array;
				})
				callback(events);
				callback(fixedDate);
			},
			error: function(xhr) {
				alert("상태 : " + xhr.status);
			}
		});
	},

	eventAfterAllRender: function(view) {
		if (view.name == "month") {
			$(".fc-content").css('height', 'auto');
		}
	},

	//*********************************************************************************************************************************
	eventResize: function(event, delta, revertFunc, jsEvent, ui, view) {
		$('.popover.fade.top').remove();
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
		
		var newDates = calDateWhenResize(event);
		
		//리사이즈한 일정 업데이트
		$.ajax({
			url: "/EveryWare/selectSchedule.do",
			type: "get",
			data : { "id" : event._id },
			success: function(data) {
				$.ajax({
					url: "/EveryWare/resizeSchedule.do",
					type: "get",
					data: {
						resizeStart: newDates.startDate,
						resizeEnd: newDates.endDate,
						schid: data.sch_id
					},
					success: function(res) {
						if(res > 0) {
							alert("수정되었습니다");
						} else {
							alert("수정 실패")
						}
					},
					error: function(xhr) {
						alert(xhr.status);
					}
				});
			},
			error: function(xhr) {
				alert(xhr.status);
			}
		});
	},
	//*********************************************************************************************************************************

	eventDragStart: function(event, jsEvent, ui, view) {
		draggedEventIsAllDay = event.allDay;
	},

	//*********************************************************************************************************************************
	//일정 드래그앤드롭
	eventDrop: function(event, delta, revertFunc, jsEvent, ui, view) {
		$('.popover.fade.top').remove();
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
		
		//주,일 view일때 종일 <-> 시간 변경불가
		if (view.type === 'agendaWeek' || view.type === 'agendaDay') {
			if (draggedEventIsAllDay !== event.allDay) {
				alert('드래그앤드롭으로 종일<->시간 변경은 불가합니다.');
				location.reload();
				return false;
			}
		}

		// 드랍시 수정된 날짜반영
		var newDates = calDateWhenDragnDrop(event);
		
		//드롭한 일정 업데이트
		$.ajax({
			url: "/EveryWare/selectSchedule.do",
			type: "get",
			data : { "id" : event._id },
			success: function(data) {
				$.ajax({
					url: "/EveryWare/resizeSchedule.do",
					type: "get",
					data: {
						resizeStart: newDates.startDate,
						resizeEnd: newDates.endDate,
						schid: data.sch_id
					},
					success: function(res) {
						if(res > 0) {
							alert("수정되었습니다");
						} else {
							alert("수정 실패")
						}
					},
					error: function(xhr) {
						alert(xhr.status);
					}
				});
			},
			error: function(xhr) {
				alert(xhr.status);
			}
		});
	},
	//*********************************************************************************************************************************

	select: function(startDate, endDate, jsEvent, view) {
		$(".fc-body").unbind('click');
		$(".fc-body").on('click', 'td', function(e) {

			$("#contextMenu")
				.addClass("contextOpened")
				.css({
					display: "block",
					left: e.pageX,
					top: e.pageY
				});
			return false;
		});

		var today = moment();

		if (view.name == "month") {
			startDate.set({
				hours: today.hours(),
				minute: today.minutes()
			});
			startDate = moment(startDate).format('YYYY-MM-DD HH:mm');
			endDate = moment(endDate).subtract(1, 'days');

			endDate.set({
				hours: today.hours() + 1,
				minute: today.minutes()
			});
			endDate = moment(endDate).format('YYYY-MM-DD HH:mm');
		} else {
			startDate = moment(startDate).format('YYYY-MM-DD HH:mm');
			endDate = moment(endDate).format('YYYY-MM-DD HH:mm');
		}

		//날짜 클릭시 카테고리 선택메뉴
		var $contextMenu = $("#contextMenu");
		$contextMenu.on("click", "a", function(e) {
			e.preventDefault();

			//닫기 버튼이 아닐때
			if ($(this).data().role !== 'close') {
				newEvent(startDate, endDate, $(this).html());
			}

			$contextMenu.removeClass("contextOpened");
			$contextMenu.hide();
		});

		$('body').on('click', function() {
			$contextMenu.removeClass("contextOpened");
			$contextMenu.hide();
		});
	},

	//이벤트 클릭시 수정이벤트
	eventClick: function(event, jsEvent, view) {
		editEvent(event);
	}
});

//********************************************************************************************************************

//db의 y/n를 boolean으로 바꿔주기
function charToBoolean(a) {
	if (a == "true") return true;
	else if (a == "false") return false;
}


var typeFilter = [];
var empFilter = [];

var draggedEventIsAllDay;
var activeInactiveWeekends = true;


function getDisplayEventDate(event) {
	var displayEventDate;
	if(event.allDay == false) {
		var startTimeEventInfo = moment(event.start).format('HH:mm');
		var endTimeEventInfo = moment(event.end).format('HH:mm');
		displayEventDate = startTimeEventInfo + " - " + endTimeEventInfo;
	} else {
		displayEventDate = "하루종일";
	}
	return displayEventDate;
}


function filtering(event) {
	var username = $('input:checkbox.filter:checked').map(function() {
		return $(this).val();
	}).get();
	var types = $('#type_filter').val();

	show_username = username.indexOf(event.username) >= 0;

	if(types && types.length > 0) {
		if(types[0] == "all") {
			show_type = true;
		} else {
			show_type = types.indexOf(event.type) >= 0;
		}
	}
	return true;
}


function calDateWhenResize(event) {
	var newDates = {
		startDate: '',
		endDate: ''
	};
	if(event.allDay) {
		newDates.startDate = moment(event.start._d).format('YYYY-MM-DD');
		newDates.endDate = moment(event.end._d).subtract(1, 'days').format('YYYY-MM-DD');
	} else {
		newDates.startDate = moment(event.start._d).format('YYYY-MM-DD HH:mm');
		newDates.endDate = moment(event.end._d).format('YYYY-MM-DD HH:mm');
	}
	return newDates;
}


function calDateWhenDragnDrop(event) {
	// 드랍시 수정된 날짜반영
	var newDates = {
		startDate: '',
		endDate: ''
	}

	//하루짜리 all day
	if(event.allDay && event.end === null) {
		newDates.startDate = moment(event.start._d).format('YYYY-MM-DD');
		newDates.endDate = newDates.startDate;
	}

	//2일이상 all day
	else if(event.allDay && event.end !== null) {
		newDates.startDate = moment(event.start._d).format('YYYY-MM-DD');
		newDates.endDate = moment(event.end._d).subtract(1, 'days').format('YYYY-MM-DD');
	}

	//all day가 아님
	else if(!event.allDay) {
		newDates.startDate = moment(event.start._d).format('YYYY-MM-DD');
		newDates.endDate = moment(event.end._d).format('YYYY-MM-DD');
	}
	return newDates;
}