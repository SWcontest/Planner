// 카카오맵 그리기
var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨
    };

//지도를 미리 생성
var map = new daum.maps.Map(mapContainer, mapOption);
//주소-좌표 변환 객체를 생성
var geocoder = new daum.maps.services.Geocoder();
//마커를 미리 생성
var marker = new daum.maps.Marker({
    position: new daum.maps.LatLng(37.537187, 127.005476),
    map: map
});

let coords_x, coords_y;

// 주소 검색
function searchAddress() {
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = data.address; // 최종 주소 변수

            // 주소 정보를 해당 필드에 넣는다.
            document.getElementById("search-address").value = addr;
            // 주소로 상세 정보를 검색
            geocoder.addressSearch(data.address, function(results, status) {
                // 정상적으로 검색이 완료됐으면
                if (status === daum.maps.services.Status.OK) {

                    var result = results[0]; //첫번째 결과의 값을 활용

                    // 해당 주소에 대한 좌표를 받아서
                    var coords = new daum.maps.LatLng(result.y, result.x);
                    // 지도를 보여준다.
                    mapContainer.style.display = "block";
                    map.relayout();
                    // 지도 중심을 변경한다.
                    map.setCenter(coords);
                    // 마커를 결과값으로 받은 위치로 옮긴다.
                    marker.setPosition(coords)
                }

                //주소로 좌표 받아오기

                const addressname = document.getElementById('search-address').value;

                var callback = function(result, status) {
                    if (status === kakao.maps.services.Status.OK) {
                        console.log(result);
                        coords_x = result[0].road_address.x
                        coords_y = result[0].road_address.y
                    }
                };

                geocoder.addressSearch(`${addressname}`, callback);

            });
        }
    }).open();
}

// 버튼 눌렀을 때 실행
const $submitBtn = document.getElementById('submit-button');

$submitBtn.addEventListener("click", () => {
    console.log("서버에 일정 전송");

    let params = {
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        login_id: localStorage.getItem('user_id'),
        plan_date: '',
        title: '',
        contents: '',
        place: '',
        start_time: null,
        end_time: null,
        total_time: 0,
        activate: false,
        coordinate: coords_x + ',' + coords_y

    };
    params.plan_date = document.getElementById('plan-date').value;
    params.title = document.getElementById('schedule-name').value;
    params.contents = '';
    params.place = document.getElementById('search-address').value;
    params.start_time = document.getElementById('start-time').value;
    params.end_time = 0;
    const selectOption = document.getElementById('total-time');
    params.total_time = selectOption.options[selectOption.selectedIndex].value;

    const url = "/api/v1/plan";
    // const url = "plan/add_schedule_do";

    fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify(params)
    })
        .then(function (response) {
            return response.json();
        })
        .then(function (myJson) {
            console.log(JSON.stringify(myJson));
            show_saved_schedule(params)
        });
    console.log(params)
})

// 저장한 일정 보여주기
function show_saved_schedule(data) {
    const li = document.createElement('li');
    li.innerHTML = `
        <div class="form-check form-check-flat">
            ${data.title}
        </div>
        <i class="remove ti-close"></i>
    `;

    const $todo_list = document.querySelector('.todo-list');
    $todo_list.appendChild(li);
}

