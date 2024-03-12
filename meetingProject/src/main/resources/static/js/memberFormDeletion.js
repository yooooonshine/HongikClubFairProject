//기존 회원 정보 삭제하기
function clickDelete() {
    const instagramId = document.querySelector("#instagramId");
    checkInstagramId(instagramId);
    deleteMember(instagramId);
}
function checkInstagramId(instagramId) {
    const instagramIdRegex = /^([a-z0-9._]{3,30})$/;
    if (!instagramId.value) {
        alert('인스타그램 아이디를 입력해주세요');
        instagramId.focus();
        return;
    } else if (!instagramIdRegex.test(instagramId.value)) {
        alert('올바른 인스타그램 아이디를 입력해주세요');
        return;
    }
}
//삭제할 회원 정보 전송
function deleteMember(instagramId) {
    $.ajax({
        type: 'delete',
        url: '/api/resume/' + instagramId,
        async: false,
        dataType: 'JSON',
        success: function (data) {
            localStorage.clear();
            window.location.assign("/");
        },
        error: function (request, status, error) {
            if (request.status === 404) {
                alert("등록되지 않은 아이디입니다.");
            } else if (request.status === 500) {
               alert("서버가 작동하지 않습니다.");
            } else {
                alert(request.status + " 예외입니다.");
            }
        },
    });
}

//처음으로 돌아가기
function directIntro() {
    localStorage.clear();

    window.location.assign("/");
}