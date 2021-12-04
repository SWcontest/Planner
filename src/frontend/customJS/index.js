// index.html js

const $badge = document.querySelectorAll(".today-table .badge");

$badge.forEach(el => {
    el.addEventListener("click", () => {
        console.log(el)
        if (el.classList.contains("badge-success")){
            el.classList.add("badge-danger")
            el.classList.remove("badge-success")
            el.innerHTML = "미완료"
            console.log(1)
        }
        else {
            el.classList.remove("badge-danger")
            el.classList.add("badge-success")
            el.innerHTML = "완료"
            console.log(2)
        }

    })
})
