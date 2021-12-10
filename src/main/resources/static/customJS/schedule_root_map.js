// 일주일
function getWeek() {

    const currentDay = new Date();
    const theYear = currentDay.getFullYear();
    const theMonth = currentDay.getMonth();
    const theDate  = currentDay.getDate();
    const theDayOfWeek = currentDay.getDay();

    const thisWeek = [];

    for(let i=1; i<8; i++) {
        let resultDay = new Date(theYear, theMonth, theDate + (i - theDayOfWeek));
        let yyyy = resultDay.getFullYear();
        let mm = Number(resultDay.getMonth()) + 1;
        let dd = resultDay.getDate();

        mm = String(mm).length === 1 ? '0' + mm : mm;
        dd = String(dd).length === 1 ? '0' + dd : dd;

        thisWeek[i - 1] = yyyy + '-' + mm + '-' + dd;
    }

    return thisWeek;
}

// 지도
var container = document.getElementById('map');
var options = {
    center: new kakao.maps.LatLng(33.49927523032624, 126.52983583833799),
    level: 6
};

var map = new kakao.maps.Map(container, options); // 지도를 생성합니다


// 루트
let markers = [];
let routes = [];
let polyline = new kakao.maps.Polyline({
    path: routes, // 선을 구성하는 좌표배열 입니다
    strokeWeight: 5, // 선의 두께 입니다
    strokeColor: 'red', // 선의 색깔입니다
    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
    strokeStyle: 'solid' // 선의 스타일입니다
});

function getRouteByDay(day) {
    const login_id = localStorage.getItem('user_id')
    const week = getWeek();

    const url = `/api/v1/plan_route/${login_id}/${week[day]}`;

    markers.forEach(marker => marker.setMap(null));
    markers = [];
    polyline.setMap(null);
    routes = [];

    fetch(url)
        .then((res) => {
            res.json().then(data => {
                console.log(data);
                data.forEach(pos => {
                    setMarker(pos.lat, pos.lng);
                    setRoute(pos.lat, pos.lng);
                })
                for(let i = 0; i < data.length - 1; i++){
                    searchBusLaneAJAX(data[i].lat, data[i].lng, data[i+1].lat, data[i+1].lng);
                }
            }).then(() => {
                drawMarker();
                polyline = new kakao.maps.Polyline({
                    path: routes, // 선을 구성하는 좌표배열 입니다
                    strokeWeight: 5, // 선의 두께 입니다
                    strokeColor: 'red', // 선의 색깔입니다
                    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
                    strokeStyle: 'solid' // 선의 스타일입니다
                });

                polyline.setMap(map);
            })
        }).catch(err => {
        console.log(err);
    })
}



function setMarker(lat, lng) {
    const markerPosition = new kakao.maps.LatLng(lat, lng);
    const marker = new kakao.maps.Marker({
        position: markerPosition
    })
    markers.push(marker)
}

function drawMarker() {
    markers.forEach(marker => marker.setMap(map));
}


function setRoute(lat, lng) {
    routes.push(new kakao.maps.LatLng(lat, lng))
}
//
// var imageSrc = 'https://i.ibb.co/t8n2DW7/map-pin-2.png', // 마커이미지의 주소입니다
//     imageSize = new kakao.maps.Size(40,55), // 마커이미지의 크기입니다
//     imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.


//회색마커
var positions = [
    {
        title: '카카오',
        latlng: new kakao.maps.LatLng(33.51088772003216, 126.51325876616964)
    },
    {
        title: '생태연못',
        latlng: new kakao.maps.LatLng(33.505269764507176, 126.54377157476685)
    },
    {
        title: '텃밭',
        latlng: new kakao.maps.LatLng(33.5010712635577, 126.51927023143702)
    },
    {
        title: '근린공원',
        latlng: new kakao.maps.LatLng(33.49502795424776, 126.54784996095842)
    }
];

// 마커 이미지의 이미지 주소입니다
var imageSrc = "https://i.ibb.co/t8n2DW7/map-pin-2.png";


var iwRemoveable = true;

var iwContent ='<div style="width: 350px">'+
    '<button type="button" id="mon" class="btn btn-danger btn-rounded btn-icon" onclick="mon_click()" style="background-color: red; border: 1px solid red">\n' +
    '월 </button>\n' +
    '<button type="button" id="tue" class="btn btn-danger btn-rounded btn-icon" style="background-color: #FF5D05 ; border: 1px solid #FF5D05" onclick="location.href=\'tue.html\'">\n' +
    '화 </button>\n' +
    '<button type="button" id="wed" class="btn btn-danger btn-rounded btn-icon" style="background-color: #4E71D9 ; border: 1px solid #4E71D9" onclick="location.href=\'wednes.html\'">\n' +
    '수 </button>\n' +
    '<button type="button" id="thu" class="btn btn-danger btn-rounded btn-icon" style="background-color: #5FA55A ; border: 1px solid #5FA55A">\n' +
    '목 </button>\n' +
    '<button type="button" id="fri" class="btn btn-danger btn-rounded btn-icon" style="background-color: #927A6D ; border: 1px solid #927A6D">\n' +
    '금 </button>\n' +
    '<button type="button" id="sat" class="btn btn-danger btn-rounded btn-icon" style="background-color: #BB85EA ; border: 1px solid #BB85EA">\n' +
    '토 </button>\n' +
    '<button type="button" id="sun" class="btn btn-danger btn-rounded btn-icon" style="background-color:#D98B84; border: 1px solid #D98B84">\n' +
    '일 </button>' +
    '</div>';

for (var i = 0; i < positions.length; i++) {

    // 마커 이미지의 이미지 크기 입니다
    var imageSize = new kakao.maps.Size(40, 50);

    // 마커 이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커를 표시할 위치
        title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image: markerImage, // 마커 이미지
        clickable: true
    });
    // 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({
        content : iwContent,
        removable : iwRemoveable
    });

    kakao.maps.event.addListener(marker, 'click', makeOverListener(map, marker, infowindow));
}

function makeOverListener(map, marker, infowindow) {
    return function() {
        infowindow.open(map, marker);
    };
}

function mon_click() {
    polyline.setMap(null);
    marker.setMap(null);
    // 마커가 표시될 위치입니다

    var marker5 = new kakao.maps.Marker({
        position: positions[3].latlng, // 마커를 표시할 위치
    });


    var linePath = [
        new kakao.maps.LatLng(33.49300696630641, 126.51221789708927),
        new kakao.maps.LatLng(33.49617682451363, 126.53612710442246),
        new kakao.maps.LatLng(33.49502795424776, 126.54784996095842),
        new kakao.maps.LatLng(33.499559979698255, 126.53116842010202),
        new kakao.maps.LatLng(33.51863686299385, 126.52031636910279)
    ];

    var polyline5 = new kakao.maps.Polyline({
        path: linePath, // 선을 구성하는 좌표배열 입니다
        strokeWeight: 5, // 선의 두께 입니다
        strokeColor: 'red', // 선의 색깔입니다
        strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
        strokeStyle: 'solid' // 선의 스타일입니다
    });
    marker5.setMap(map);
    polyline5.setMap(map);
    infowindow.close();

    var table = document.getElementById("plan_detail");
    var newRow = table.insertRow(3);

    // 새 행(Row)에 Cell 추가
    var newCell1 = newRow.insertCell(0);
    var newCell2 = newRow.insertCell(1);
    var newCell3 = newRow.insertCell(2);
    var newCell4 = newRow.insertCell(3);
    var newCell5 = newRow.insertCell(4);

    // Cell에 텍스트 추가
    newCell1.innerText = '미정 일정';
    newCell2.innerText = '한마음 병원';
    newCell3.innerText = '미정';
    newCell4.innerText = '1시간';
    newCell5.innerText = '미정일정';

    table.rows[4]
}

function searchBusLaneAJAX(slat, slng, elat, elng) {
    const appkey = 'xzm+dv1+LZESXRkacw+Wot/SwZU38z9Ubd7tPsAt1eg';
    var xhr = new XMLHttpRequest();
    let url = `https://api.odsay.com/v1/api/searchPubTransPath?SX=${slat}&SY=${slng}&EX=${elat}&EY=${elng}&OPT=1&apiKey=${appkey}`;
    console.log(url)
    xhr.open("GET", url, true);
    xhr.send();
    xhr.onreadystatechange = function() {

        if (xhr.readyState == 4 && xhr.status == 200) {
            console.log( xhr.responseText ); // <- xhr.responseText 로 결과를 가져올 수 있음
        }
    }
}

getRouteByDay(today.getDay());
function getBusRoute() {
    const url = '/api/v1/bus/33.51810581785266/126.52124868512283/33.47217551996734/126.55016704094149';
    fetch(url)
        .then(res => res.json().then(data => console.log(data)));
}
getBusRoute();