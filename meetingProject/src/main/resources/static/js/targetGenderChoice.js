const male = "MALE";
const female = "FEMALE";
const any = "ANY";

function choiceGender(gender) {
    const targetGender = makeGenderResponseValue(gender);
    submitForm(targetGender);
}

function makeGenderResponseValue(gender) {
    if (gender === "male") {
        return male;
    } else if (gender === "female") {
        return female;
    } else {
        return any;
    }
}

function submitForm(targetGender) {
    const instagramId = localStorage.getItem("instagramId");
    const introduction = localStorage.getItem("introduction");
    const gender = localStorage.getItem("gender");

    if (instagramId === null || introduction === null || gender === null) {
        alert("기본정보를 입력해주세요.");

        window.location.assign("/member_form");
    }

    $.ajax({
        type: 'post',
        url: '/api/resume',
        data: {
            'instagramId': instagramId,
            'introduction': introduction,
            'gender': gender,
            'targetGender': targetGender
        },
        dataType: 'JSON',
        success: function (data) {
            if (data.status === 200) {
                localStorage.setItem("memberId", data.data);
                window.location.assign("/people_choice");
            }
            if (data.status === 400) {
                alert("형식이 잘못되었습니다.");
            } else if (data.status === 409) {
                alert("이미 존재하는 인스타아이디입니다. 기존 회원정보로 진행됩니다.");

                localStorage.setItem("memberId", requestId(instagramId));
                window.location.assign("/people_choice");
            } else if (data.status === 500) {
                alert("서버가 작동하지 않습니다.");
            } else {
                alert("알 수 없는 에러입니다.");
            }
        },
        error: function (jqrXHR, status, error) {
            alert("알 수 없는 에러입니다.");
        },
    });
}

function requestId(instagramId) {
    let memberId = 0;

    $.ajax({
        type: 'get',
        url: '/api/member/instagramId/' + instagramId,
        dataType: 'html',
        success: function (data) {

            if (data.status === 200) {
                memberId = data.data
            } else if (data.status === 400) {
                alert("요청이 잘못되었습니다.");
            } else if (data.status === 404) {
                alert("존재하지 않는 인스타아이디입니다.")
            } else if (data.status === 500) {
                alert("서버가 작동하지 않습니다.");
            } else {
                alert("알 수 없는 예외입니다.");
            }
        },
        error: function (jqrXHR, status, error) {
            alert("알 수 없는 에러입니다.");
        },
    })

    return memberId;
}

//이전 페이지로 돌아가기
function directBeforePage() {
    localStorage.removeItem("instagramId");
    localStorage.removeItem("introduction");
    localStorage.removeItem("gender");

    window.location.assign("/member_form");
}

//처음으로 돌아가기
function directIntro() {
    localStorage.clear();

    window.location.assign("/");
}
