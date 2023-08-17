/**
 * 
 */
// 시작일과 종료일을 기준으로 평일 계산하는 함수
function getBusinessDays(startDate, endDate) {
  var daysBetween = Math.ceil((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;
  var weekdaysBetween = 0;
  for (var i = 0; i < daysBetween; i++) {
    var day = new Date(startDate);
    day.setDate(startDate.getDate() + i);
    if (day.getDay() != 0 && day.getDay() != 6) {
      weekdaysBetween++;
    }
  }
  return weekdaysBetween;
}

// 두 날짜 사이의 월 목록을 계산하는 함수
function getMonthsBetween(startDate, endDate) {
  var months = [];
  var date = new Date(startDate);
  while (date <= endDate) {
    var month = (date.getMonth() + 1).toString();
    var year = date.getFullYear().toString();
    //var monthYear = year+ "-" +month; //2023-4
    var monthYear = month; // 월
    if (!months.includes(monthYear)) {
      months.push(monthYear);
    }
    date.setMonth(date.getMonth() + 1);
    date.setDate(1);
  }
  return months;
}
/*function calRemainVac(empid){
	$.ajax({
		url : '<%=request.getContextPath()%>/Vacations/selectVac',
		type : 'post',
		data : {"empId" : empid},
		success : function(res){
			console.log(res)
			 var vacationList = res;
			console.log(vacationList)
		      var vacationCount = {};
		      for (var i = 0; i < vacationList.length; i++) {
		        var startDate = new Date(vacationList[i].vac_start);
		        var endDate = new Date(vacationList[i].vac_end);
		        var months = getMonthsBetween(startDate, endDate);
		        for (var j = 0; j < months.length; j++) {
		          var month = months[j];
		          var businessDays = getBusinessDays(startDate, endDate);
		          if (vacationCount[month] === undefined) {
		            vacationCount[month] = businessDays;
		          } else {
		            vacationCount[month] += businessDays;
		          }
		        }
		      }
		      // 각 월별 휴가 사용 횟수 출력
		      for (var month in vacationCount) {
		        console.log(month + "에 사용한 휴가 일수: " + vacationCount[month]);
		      }
		},
		
		error : function(xhr){
			//alert('상태 : ' + xhr.status + "\ncode : " +xhr.statust);	
		},
		dataType : 'json'
	})
}
*/
  /*function calculateSum() {
    var totalVac = 0;
    var totalPrevac = 0;
    var totalSum = 0;

    // 기본, 연차, 이월 연차 값 추출
    var empvac = parseInt(document.getElementById("empvac").innerHTML);
    var emprevac = parseInt(document.getElementById("emprevac").innerHTML);

    // 각 월별 값 추출
    var jan = parseInt(document.getElementById("jan").innerHTML);
    var feb = parseInt(document.getElementById("feb").innerHTML);
    var mar = parseInt(document.getElementById("mar").innerHTML);
    var apr = parseInt(document.getElementById("apr").innerHTML);
    var may = parseInt(document.getElementById("may").innerHTML);
    var jun = parseInt(document.getElementById("jun").innerHTML);
    var jul = parseInt(document.getElementById("jul").innerHTML);
    var aug = parseInt(document.getElementById("aug").innerHTML);
    var sep = parseInt(document.getElementById("sep").innerHTML);
    var oct = parseInt(document.getElementById("oct").innerHTML);
    var nov = parseInt(document.getElementById("nov").innerHTML);
    var dec = parseInt(document.getElementById("dec").innerHTML);

    // 총합 계산
    totalVac = empvac + jan + feb + mar + apr + may + jun + jul + aug + sep + oct + nov + dec;
    totalPrevac = emprevac;
    totalSum = totalVac + totalPrevac;

    // 총계 업데이트
    document.getElementById("vac-man-to").getElementsByTagName("td")[2].innerHTML = totalVac;
    document.getElementById("vac-man-to").getElementsByTagName("td")[15].innerHTML = totalPrevac;

    // 합계 업데이트
    document.getElementById("vac-man-sum").getElementsByTagName("td")[2].innerHTML = totalSum;
  }
*/
