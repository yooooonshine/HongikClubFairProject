function clickNext() {

    const gender = document.querySelector('input[name=btnradio]:checked');
    const instagramId = document.querySelector("#instagramId");
    const introduction = document.querySelector("#introduction");

    if (!gender) {
        alert('성별을 선택해주세요');
        document.querySelector('input[name=btnradio]').focus();
        return;
    }

    const instagramIdRegex = /^([a-z0-9._]{3,30})$/;
    if (!instagramId.value) {
        alert('인스타그램 아이디를 입력해주세요');
        instagramId.focus();
        return;
    }
    else if (!instagramIdRegex.test(instagramId.value)) {
        alert('올바른 인스타그램 아이디를 입력해주세요');
        return;
    }

    const introductionRegex = /^([A-Za-z0-9가-힣ㄱ-ㅎㅏ-ㅣ一-龥\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"\s+]{1,100})$/;
    if (!introduction.value) {
        alert('자기소개를 입력해주세요');
        introduction.focus();
        return;
    }
    else if (!introductionRegex.test(introduction.value)) {
        alert('자기소개는 영문, 한문, 숫자, 특수문자 100자 이내로 작성해주세요');
        introduction.focus();
        return;
    }

    localStorage.setItem("instagramId", instagramId.value);
    localStorage.setItem("introduction", introduction.value);
    localStorage.setItem("gender", gender.value);

    window.location.assign("/target_gender_choice");
}

//처음으로 돌아가기
function directIntro() {
    localStorage.clear();

    window.location.assign("/");
}