window.onload = function() {
    const chosenMemberId = localStorage.getItem("chosenMemberId");
    fetch("/api/resume/"+chosenMemberId)
        .then((response) => response.json())
        .then((result) => {
            makeMemberCard(result.data.instagramId, result.data.introduction);
        })
        .catch((error) => {
            if (error.status === 400) {
                alert("잘못된 요청입니다.");
            } else if(error.status === 404){
                alert("선택된 사람을 찾지 못했습니다.");
                window.location.assign("/people_choice");
            } else if(error.status === 500){
                alert("잘못된 요청입니다.");
            } else {
                alert("알 수 없는 예외입니다.");
            }
        })
}

function makeMemberCard(instagramId, introduction) {
    const insta = document.getElementById('instagramId');
    insta.innerText = "@"+instagramId;

    const intro = document.getElementById('introduction');
    intro.innerText = introduction;

    var qrcode = new QRCode(document.querySelector("#qrcode"), {
        text: `https://www.instagram.com/${instagramId}`,
        width: 300,
        height: 300,
    });
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
    const memberId = localStorage.getItem("chosenMemberId");

    phoneNumber = phoneNumber.replace("-", "");

    $.ajax({
        type: 'post',
        url: '/api/resume/send/' + memberId,
        data: {
            'phoneNumber': phoneNumber,
        },
        dataType: 'JSON',
        success: function (data) {
            window.localStorage.clear();
            window.location.assign("/");
        },
        error: function (request, status, error) {
            if(request.status === 400){
                alert("잘못된 요청입니다.");
            } else if(request.status === 404){
                alert("선택된 사람을 찾지 못했습니다.");
            } else if(request.status === 500){
                alert("서버가 작동하지 않습니다.");
            } else {
                alert("알 수 없는 예외입니다.");
            }
        },
    })
}

function directBeforePage() {
    window.localStorage.removeItem("chosenMemberId");
    history.back();
}

function directIntro() {
    window.localStorage.clear();
    window.location.assign("/");
}