function getPlanByDay(login_id) {

    const week = getWeek();
    console.log(week);

    week.forEach((plan_date, index) => {
        const url = `/api/v1/plan/${login_id}/${plan_date}`;

        fetch(url)
            .then((res) => {
                const contentType = res.headers.get("content-type");
                if(contentType && contentType.indexOf("application/json") !== -1) {
                    return res.json().then((data) => {
                        showWeekSchedule(data, index);
                        console.log(data)
                    });
                }
                else {
                    console.log("일정 없음 " + index)
                }
            })
            .catch(err => {
            console.log(err)
        })
    })


}

function showWeekSchedule(data, index) {
    const week = ['mon-tbody', 'tue-tbody', 'wen-tbody', 'thu-tbody', 'fri-tbody', 'sat-tbody', 'sun-tbody']

    data.forEach(plan_date => {
        const $tbody = document.getElementById(week[index]);
        const tr = document.createElement('tr');
        tr.innerHTML = `<td style="font-size: smaller">${plan_date.title}</td><td style="font-size: smaller">${plan_date.place}</td>`
        $tbody.appendChild(tr)
    })

}

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

getPlanByDay("root");