
    // console.log("서버에 일정 전송")
    // const url = "";
    // let params = {
    //     headers : {
    //         'Content-Type': 'application/json',
    //             'Accept': 'application/json'
    //     },
    //     login_id: '',
    //     day: null,
    //     title: '',
    //     contents: '',
    //     place: '',
    //     start_time: null,
    //     end_time: null,
    //     total_time: 0,
    // };
    // params.day = null;
    // params.title = document.getElementById('schedule-name').value;
    // params.contents = '';
    // params.place = document.getElementById('search-address').value;
    // params.start_time = document.getElementById('start-time').value;
    // params.end_time = 0;
    // const selectOption = document.getElementById('total-time');
    // params.total_time = selectOption.options[selectOption.selectedIndex].value;
    // fetch(url, {
    //     headers : {
    //         'Content-Type': 'application/json',
    //         'Accept': 'application/json'
    //     },
    //
    // })
    //     .then(function(response) {
    //         return response.json();
    //     })
    //     .then(function(myJson) {
    //         console.log(JSON.stringify(myJson));
    //     });
    // console.log(params)



    //요소들 가져오기
    const submitbtn = document.getElementById('submit-button');

    submitbtn.addEventListener("click",() => sendSchedule());


    //
    // const schedulelist = new Array();

    //


// 일정 등록 할 때 저장 버튼 누르면 로컬스토리지로 이동
    function sendSchedule(){


        const schedulename = document.getElementById('schedule-name').value;
        const scheduleaddress = document.getElementById('search-address').value;


        const scheduledata = new Object();

        scheduledata.name = schedulename;
        scheduledata.address = scheduleaddress;

        console.log('눌렸음')
        console.log(JSON.stringify(scheduledata))


        localStorage.setItem("name",scheduledata.name);
        localStorage.setItem("address",scheduledata.address);

        const addname = document.getElementById('newbox');

        var submittedschedule = document.createElement('div');
        submittedschedule.innerHTML = localStorage.getItem("name");

        submittedschedule.setAttribute('class',"form-check form-check-flat");
        addname.appendChild(submittedschedule);


    }


