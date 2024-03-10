const MALE = "남성";
const FEMALE = "여성";


window.onload = function () {
    requestMatching();
};

//매칭 요청하기
function requestMatching() {
    const memberId = localStorage.getItem("memberId");

    $("#cardContainer").empty();

    document.querySelector("#button-rematching").disabled = true;

    if (memberId === null) {
        alert("기본 정보를 입력해주세요");

        window.location.assign("/member_form");
    }

    $.ajax({
        type: 'get',
        url: '/api/resume/match/' + memberId,
        async: false,
        dataType: 'json',
        success: function (data) {
            const memberInfos = data.data;
            for (let i = 0; i < memberInfos.length; i++) {
                let memberId = memberInfos[i]['resumeId'];
                let gender = memberInfos[i]['gender'];
                let introduction = memberInfos[i]['introduction'];
                makeMemberCard(memberId, gender, introduction);
            }

            setTimeout(() => {
                document.querySelector("#button-rematching").disabled = false;
            }, 2000)
        },
        error: function (request, status, error) {
            if (request.status === 400) {
                alert("형식이 잘못되었습니다.");
            } else if (request.status === 500) {
                alert("서버가 작동하지 않습니다.");
            } else {
                alert("peopleChoice" + request.status + " 예외입니다.");
            }

            deleteMemberCardAll();
            makeErrorMessage();
        },
    })
}

//html 카드 만들기
function makeMemberCard(memberId, gender, introduction) {
    const genderText = makeGenderText(gender);

    let cardHtml = '';
    cardHtml += `<div class="card ${gender.toLowerCase()}">`;
    cardHtml += `    <div class="title">`;
    cardHtml += `        <img src="/images/hongik-${gender === 'MALE' ? 'm' : 'f'}.svg" />`;
    cardHtml += `        <p class="name">익명의 ${genderText}</p>`;
    cardHtml += `    </div>`;
    cardHtml += `    <p class="introduce">${introduction}</p>`;
    cardHtml += `    <button id="${memberId}" onclick="choicePeople(this.id)">선택</button>`;
    cardHtml += `</div>`;

    $("#cardContainer").append(cardHtml);
}

//매칭된 사람이 없을 경우
function makeErrorMessage() {
    const errorMessage =
        "<div class='col'>매칭될 사람이 아직 없어요..</div>";

    $("#cardContainer").append(errorMessage);
}

//html 카드 제거
function deleteMemberCardAll() {
    $("#cardContainer").empty();
}

//남자, 여자 텍스트 만들기
function makeGenderText(gender) {
    if (gender === "MALE") {
        return MALE;
    } else if (gender === "FEMALE") {
        return FEMALE;
    } else {
        console.log("서버의 데이터 형식이 클라이언트와 맞지 않습니다.");
    }
}

//사람 선택
function choicePeople(memberId) {
    localStorage.setItem("chosenMemberId", memberId);

    window.location.assign("/result");
}

//이전 페이지로 돌아가기
function directBeforePage() {
    localStorage.removeItem("memberId");

    window.location.assign("/");
}

//처음으로 돌아가기
function directIntro() {
    localStorage.clear();

    window.location.assign("/");
}