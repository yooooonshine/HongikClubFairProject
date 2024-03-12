window.onload = function() {
    const instagramId = localStorage.getItem("instagramId");
    const introduction = localStorage.getItem("introduction");
    const gender = localStorage.getItem("gender");

    checkTermsOfUse();
    checkMemberForm(instagramId, introduction, gender);
}


function checkMemberForm(instagramId, introduction, gender) {
    if (instagramId === null || introduction === null || gender === null) {
        alert("기본정보를 입력해주세요.");
        window.location.assign("/member_form");
    }
}

function checkTermsOfUse() {
    const termChecked1 = localStorage.getItem("termChecked1");
    const termChecked2 = localStorage.getItem("termChecked2");

    if(!termChecked1 && !termChecked2){
        alert("이용약관, 개인정보 수집/이용 동의서에 동의해주세요");
        localStorage.removeItem("termChecked1");
        localStorage.removeItem("termChecked2");
        window.location.assign("/terms_of_use");
    }
}


function choiceTargetGender(targetGender) {
    submitForm(targetGender);
}

function submitForm(targetGender) {
    const instagramId = localStorage.getItem("instagramId");
    const introduction = localStorage.getItem("introduction");
    const gender = localStorage.getItem("gender");

    let data = {
        'instagramId': instagramId,
        'introduction': introduction,
        'gender': gender,
        'targetGender': targetGender
    };

    $.ajax({
        type: 'post',
        url: '/api/resume',
        data: JSON.stringify(data),
        async: false,
        contentType: "application/json",
        dataType: 'JSON',
        success: function (data) {
            localStorage.setItem("memberId", data.data["id"]);
            window.location.assign("/people_choice");
        },
        error: function (request, status, error) {
            if (request.status === 400) {
            } else if (request.status === 409) {
                updateFrom(data);
            } else if (request.status === 500) {
                alert("서버가 작동하지 않습니다.");
            } else {
                alert(request.status + " 예외입니다.");
            }
        },
    });
}

function updateFrom(data) {
    $.ajax({
        type: 'patch',
        url: '/api/resume/' + instagramId,
        data: JSON.stringify(data),
        dataType: 'json',
        contentType : "application/json",
        async: false,
        success: function (data) {
            localStorage.setItem("memberId", data.data["id"]);
            window.location.assign("/people_choice");
        },
        error: function (request, status, error) {
            if (request.status === 400) {
                alert("요청이 잘못되었습니다.");
            } else if (request.status === 500) {
                alert("서버가 작동하지 않습니다.");
            } else {
                alert(request.status + " 예외입니다.");
            }
        },
    });

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
