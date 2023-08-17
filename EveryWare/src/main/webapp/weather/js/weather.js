/*'use strict';*/


/*function changeIcon(data) {
	if (data === "01d") {
		$('.rightcolumn #weatherdiv').css('background-image', "url('https://source.unsplash.com/PEm_sLmJT-w/1600x900')");
	} else if (data === '03d') {
		$('.rightcolumn #weatherdiv').css('background-image', "url('https://source.unsplash.com/78wDBw9ajUk/1600x900')");
	} else if (data === '09d' || data === '10d') {
		$('.rightcolumn #weatherdiv').css('background-image', "url('https://source.unsplash.com/22x7fxFpl_8/1600x900')");
	} else if (data === '13d') {
		$('.rightcolumn #weatherdiv').css('background-image', "url(snowflakes-554635_1920.jpg)");
	} else if (data === '11d') {
		$('.rightcolumn #weatherdiv').css('background-image', "url('https://source.unsplash.com/jh2KTqHLMjE/1600x900')");
	} else {
		$('.rightcolumn #weatherdiv').css('background-image', "url('https://source.unsplash.com/v9bnfMCyKbg/1600x900')");
	}
}*/

$.getWeather = function() {
	const url = "https://api.openweathermap.org/data/2.5/forecast";
	const apikey = "059d1d499acd3fd20ce144a04049e811";
	let mydata;
	mydata = {
		lat: '36',
		lon: '38',
		appid: apikey,
		units: 'metric',
		lang: 'kr'
	}

	let params = Object.keys(mydata).map(key => key + '=' + mydata[key]).join('&');

	fetch(`${url}?${params}`)
		.then(result => result.json())
		.then(rs => {
			drawWeather(rs);
			function drawWeather(rs) {
				const today = new Date();
				const month = today.getMonth() + 1;
				const date = today.getDate();
				
				let nowtemp = rs.list[0].main.temp.toFixed(0);
				let maxtemp = rs.list[0].main.temp_max.toFixed(0);
				let mintemp = rs.list[0].main.temp_min.toFixed(0);
				let wind = rs.list[0].wind.speed.toFixed(1);
				let feels_like = rs.list[0].main.feels_like.toFixed(0);
				//changeIcon(rs.list[0].weather[0].icon);
				//changeIcon('09d');
				
				console.log("날씨 호출됨");
				
				document.getElementById('city').innerHTML = rs.city.name;
				document.getElementById('ntime').innerHTML = `${month}월 ${date}일`;
				document.getElementById('icon').src = `${contextPath}/weather/img/` + rs.list[0].weather[0].icon + ".png";
				document.getElementById('icon').alt = rs.list[0].weather[0].icon;
				document.getElementById('nowtemp').innerHTML = nowtemp + '&deg;';
				document.getElementById('maxmintemp').innerHTML = maxtemp + '&deg;' + ' / ' + mintemp + '&deg;';
				document.getElementById('desc').innerHTML = rs.list[0].weather[0].description;
				document.getElementById('clouds').innerHTML = rs.list[0].clouds.all + '&#37;';
				document.getElementById('wind').innerHTML = wind + ' m/s';
				document.getElementById('humidity').innerHTML = rs.list[0].main.humidity + '&#37;';
				document.getElementById('feels_like').innerHTML = feels_like + '&deg;';
			}


			let html = "";
			for (let i in rs.list) {
				if (i < 14) {
					let dayTime = new Date(rs.list[i].dt * 1000);
					let dayDate = dayTime.getDate() + '일 ' + dayTime.getHours() + '시';
					let maxtemp = rs.list[i].main.temp_max.toFixed(0);
					let mintemp = rs.list[i].main.temp_min.toFixed(0);

						if (dayTime.getHours() % 6 == 0) {
						html += `<li>
                  				 	<div class="dayWeather">
                     					<p class="daydate">${dayDate}</p>
                     					<img src="${contextPath}/weather/img/${rs.list[i].weather[0].icon}.png" alt="${rs.list[i].weather[0].icon}">
                     					<p class="daytemp">${maxtemp}&deg;/ ${mintemp}&deg;</p>
                     					<p class="daydesc">${rs.list[i].weather[0].description}</p>
                  					</div>
              					</li>`;
              					/*<img src="../weather/img/${rs.list[i].weather[0].icon}.png" alt="${rs.list[i].weather[0].icon}">*/
					}
				}
			}
			document.getElementById('swiper').innerHTML = html;
		});
}


//위치값 받아오기
/*if (navigator.geolocation) {
	console.log("abcddd");
	navigator.geolocation.getCurrentPosition(function(position) {
		myLat = position.coords.latitude;
		myLon = position.coords.longitude;
		getWeather(myLat, myLon);
	});
}*/