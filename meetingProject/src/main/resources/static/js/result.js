window.onload=function() {
    const chosenMemberId = localStorage.getItem("chosenMemberId");
    fetch("/resume/"+chosenMemberId)
        .then((response) => response.json())
        .then((result) => {
            console.log(result);
            chosenPerson(result.data.instagramId, result.data.introduction);
        })
        .catch((error) => {
            if (error.status === 400) {
                alert("잘못된 요청입니다.");
            } else if(error.status === 404){
                alert("선택된 사람을 찾지 못했습니다.");
            } else if(error.status === 500){
                alert("잘못된 요청입니다.");
            } else {
                chosenPerson("@seoyeonzn", "안녕하세요! 친해져요><")
                alert("서버와 연결되지 않았습니다.");
            }
        })
}

function chosenPerson(instagramId, introduction) {
    const insta = document.getElementById('instagramId');
    insta.innerText = instagramId;

    const intro = document.getElementById('introduction');
    intro.innerText = introduction;
}

function sendMessage() {
    const phoneNumber = $("#phoneNumber").val();

    if(!phoneNumber) {
        alert('전화번호를 입력해주세요');
        return;
    } else{
        const phoneNumberRegex = /^\d{3}-\d{4}-\d{4}$/;
        if (!phoneNumberRegex.test(phoneNumber)) {
            alert('올바른 전화번호를 입력해주세요');
            return;
        }
    }
    submitForm(phoneNumber);
}

function submitForm(phoneNumber) {
    const memberId = localStorage.getItem("memberId");

    $.ajax({
        type: 'post',
        url: '/api/resume/send/' + memberId,
        data: {
            'phoneNumber': phoneNumber,
        },
        dataType: 'JSON',
        success: function (data) {
            if(data.status === 200){
                window.localStorage.clear();
                window.location.assign("/intro");
            }
            if(data.status === 400){
                alert("잘못된 요청입니다.");
            } else if(data.status === 404){
                alert("선택된 사람을 찾지 못했습니다.");
            } else if(data.status === 500){
                alert("잘못된 요청입니다.");
            } else {
                alert("알 수 없는 에러입니다.");
            }
        },
        error: function (jqrXHR, status, error) {
            alert("서버와 연결되지 않았습니다.");
        },
    })
}

function goBack() {
    window.localStorage.removeItem("chosenMemberId");
    history.back();
}

function backToBeginning() {
    window.localStorage.clear();
    window.location.assign("/intro");
}