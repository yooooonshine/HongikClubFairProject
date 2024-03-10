const MALE = "남성";
const FEMALE = "여성";


window.onload = function () {
    makeMemberCard("a1", "FEMALE", "아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아")
    makeMemberCard("a2", "FEMALE", "아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아")
    makeMemberCard("a3", "MALE", "아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아아")
    //requestMatching();
};

//매칭 요청하기
function requestMatching() {
    const memberId = localStorage.getItem("memberId");

    document.querySelector("#button-rematching").disabled = true;

    if (memberId === null) {
        alert("기본 정보를 입력해주세요");

        window.location.assign("/member_form");
    }

    let data = {"id" : memberId};

    $.ajax({
        type: 'get',
        url: '/api/resume',
        data: data,
        dataType: 'html',
        success: function (data) {
            if (data.status === 200) {
                const memberInfos = data.data;

                for (let memberInfo in memberInfos) {
                    let memberId = memberInfo['resumeId'];
                    let gender = memberInfo['gender'];
                    let introduction = memberInfo['introduction'];
                    makeMemberCard(memberId, gender, introduction);
                }

                setTimeout(() => {
                    document.querySelector("#button-rematching").disabled = false;
                }, 2000)
            }
            if (data.status === 400) {
                alert("형식이 잘못되었습니다.");
            } else if (data.status === 500) {
                alert("서버가 작동하지 않습니다.");
            } else {
                alert("알 수 없는 예외입니다.");
            }
        },
        error: function () {
            deleteMemberCardAll();
            makeErrorMessage();
            alert("서버와의 연결이 되지 않습니다.");

        },
    })
}

//html 카드 만들기
function makeMemberCard(memberId, gender, introduction) {
    const genderText = makeGenderText(gender);

    let cardHtml = '';
    cardHtml += `<div class="card ${gender.toLowerCase()}">`;
    cardHtml += `    <div class="title">`;
    cardHtml += `        <img src="../static/images/hongik-${gender === 'MALE' ? 'm' : 'f'}.svg" />`;
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