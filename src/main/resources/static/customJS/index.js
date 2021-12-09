// index.html js


// 오늘 일정 조회
// 오늘 날짜에 해당하는 일정 불러오기
window.onload = function () {
    const login_id = "root";
    const today = new Date();
    const plan_date = `${today.getFullYear()}-${today.getMonth() + 1}-${today.getDate()}`;
    const url = `/api/v1/plan/${login_id}/${plan_date}`;

    console.log(url)

    fetch(url, {
        headers: {
            "Content-Type": "application/json",
            'Accept': 'application/json'
        }
    })
        .then((res) => {
            console.log(res);
            res.json().then((data) => {
                console.log(data);
                showTodaySchedule(data);
            });
        })
}

//메인 페이지-오늘의 일정 표 구성 내용
function showTodaySchedule(data) {
    const $tbody = document.getElementById("today-schedule");

    data.forEach(plan => {
        const activateClass = plan.activate ? 'badge-success':'badge-danger';
        const activateText = plan.activate ? '완료':'미완료';

        const tr = document.createElement('tr');
        tr.innerHTML = `<td>${plan.title}</td>
        <td class="font-weight-bold">${plan.id}</td>
        <td>${plan.place}</td>
        <td>${plan.start_time}</td>
        <td class = "font-weight-medium" > 
            <div class = "badge ${activateClass}" > ${activateText} </div>
        </td>`
        $tbody.appendChild(tr);
    })

    // 완료 미완료 클릭
    const $badge = document.querySelectorAll(".today-table .badge");

    //수행 여부 선택(미완료, 완료) classlist에서 해당 id 제거 혹은 추가
    $badge.forEach(el => {
        el.addEventListener("click", () => {
            console.log(el)
            if (el.classList.contains("badge-success")) {
                el.classList.add("badge-danger")
                el.classList.remove("badge-success")
                el.innerHTML = "미완료"
                console.log(1)
            } else {
                el.classList.remove("badge-danger")
                el.classList.add("badge-success")
                el.innerHTML = "완료"
                console.log(2)
            }

        })
    })

}

