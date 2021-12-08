

// 버튼 눌렀을 때 실행
const $submitBtn = document.getElementById("submit-button");

$submitBtn.addEventListener("click", () => {
    console.log("서버에 일정 전송");

    let params = {
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        login_id: '',
        day: null,
        title: '',
        contents: '',
        place: '',
        start_time: null,
        end_time: null,
        total_time: 0,
        activate: false,
    };
    params.day = null;
    params.title = document.getElementById('schedule-name').value;
    params.contents = '';
    params.place = document.getElementById('search-address').value;
    params.start_time = document.getElementById('start-time').value;
    params.end_time = 0;
    const selectOption = document.getElementById('total-time');
    params.total_time = selectOption.options[selectOption.selectedIndex].value;

    const url = "/api/v1/plan";
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
        });
    console.log(params)
})

// //요소들 가져오기
// const submitbtn = document.getElementById('submit-button');
//
// submitbtn.addEventListener("click", () => sendSchedule());
//
//
// //
// // const schedulelist = new Array();
//
// //
//
//
// // 일정 등록 할 때 저장 버튼 누르면 로컬스토리지로 이동
// function sendSchedule() {
//
//
//     const schedulename = document.getElementById('schedule-name').value;
//     const scheduleaddress = document.getElementById('search-address').value;
//
//
//     const scheduledata = {};
//
//     scheduledata.name = schedulename;
//     scheduledata.address = scheduleaddress;
//
//     console.log('눌렸음')
//
//     localStorage.setItem("schedule_data", JSON.stringify(scheduledata));
//
//     let box_template = `
//                                                 <div class="form-check form-check-flat">
//                                                     ${JSON.parse(localStorage.getItem("schedule_data")).name}
//                                                 </div>
//                                                 <i class="remove ti-close"></i>
//                                            `
//
//     console.log(box_template)
//
//     const todolist = document.querySelector(".save-list");
//
//     console.log(todolist)
//
//     let submittedschedule = document.createElement('li');
//     submittedschedule.innerHTML = box_template;
//
//     todolist.appendChild(submittedschedule);
// }


