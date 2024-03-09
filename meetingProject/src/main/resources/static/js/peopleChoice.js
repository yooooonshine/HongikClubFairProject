const MALE = "남자";
const FEMALE = "여자";


window.onload = function () {
    requestMatching();
};

//매칭 요청하기
function requestMatching() {
    const memberId = localStorage.getItem("memberId");

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
            }
            if (data.status === 400) {
                alert("형식이 잘못되었습니다.");
            } else if (data.status === 500) {
                alert("서버가 작동하지 않습니다.");
            } else {
                alert("알 수 없는 에러입니다.");
            }
        },
        error: function (jqrXHR, status, error) {
            deleteMemberCardAll();
            makeErrorMessage();
            alert("알 수 없는 에러입니다.");

        },
    })
}

//html 카드 만들기
function makeMemberCard(memberId, gender, introduction) {
    const genderText = makeGenderText(gender);

    const cardHtml =
        "<div class='col'>\n" +
        "            <div class='card' style='width: 18rem;'>\n" +
        "                <div class='card-body'>\n" +
        "                    <h5 id='gender' class='card-title'>" + "익명의" + genderText + "</h5>\n" +
        "                    <p id='introduce' class='card-text'> "+ introduction + "</p>\n" +
        "                    <button id=" + memberId + " type='button' class='btn btn-primary' onclick='choicePeople(this.id)'>선택</button>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "        </div>";

    $("#cardContainer").append(cardHtml);
}

//매칭된 사람이 없을 경우
function makeErrorMessage() {
    const errorMessage = "매칭될 사람이 아직 없어요.."
    $("#cardOuterContainer").append(errorMessage);
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