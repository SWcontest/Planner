export default function (){
    console.log("서버에 일정 전송")
    const url = "";
    let params = {
        headers : {
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
    };
    params.day = null;
    params.title = document.getElementById('schedule-name').value;
    params.contents = '';
    params.place = document.getElementById('search-address').value;
    params.start_time = document.getElementById('start-time').value;
    params.end_time = 0;
    const selectOption = document.getElementById('total-time');
    params.total_time = selectOption.options[selectOption.selectedIndex].value;
    fetch(url, {
        headers : {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },

    })
        .then(function(response) {
            return response.json();
        })
        .then(function(myJson) {
            console.log(JSON.stringify(myJson));
        });
    console.log(params)
}

