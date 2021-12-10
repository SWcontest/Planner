
// 일정
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

//오늘 날짜 기준으로 1주일 날짜 구하기
function getPlanByDay(day) {

    const week = getWeek();

    const login_id = localStorage.getItem('user_id')

    const url = `/api/v1/plan/${login_id}/${week[day]}`;

    fetch(url)
        .then((res) => {
            const contentType = res.headers.get("content-type");
            if(contentType && contentType.indexOf("application/json") !== -1) {
                return res.json().then((data) => {
                    showDaySchedule(data);
                    console.log(data)
                });
            }
            else {
                console.log("일정 없음 " + index)
            }
        }).catch(err => {
            console.log(err)
    })
}

//해당 날짜 일정 불러오기
function showDaySchedule(plan_list) {
    const $tbody = document.getElementById('schedule-tbody');
    $tbody.innerHTML = '';
    plan_list.forEach((plan, index) => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
        <td>${plan.title}</td>
        <td>${plan.place}</td>
        <td>${plan.start_time}</td>
        <td>${plan.total_time}</td>
        <td>${index + 1}</td>`
        $tbody.appendChild(tr);
    })
}



// 초기값
const today = new Date();
getPlanByDay(today.getDay());
