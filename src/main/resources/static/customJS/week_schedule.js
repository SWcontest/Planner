function getPlanByDay(login_id, day) {
    const url = `/api/v1/plan/${login_id}/${day}`;

    fetch(url)
        .then((res) => {
            return res.json();
        })
        .then((data) => {
            makeTemplate(data);
            console.log(JSON.stringify(data));
        }).catch(err => {
            console.log(err)
    })
}

function makeTemplate(data) {

}

getPlanByDay("root", 5);