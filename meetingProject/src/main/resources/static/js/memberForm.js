function clickNext() {
    const instagramId = $("#instagramId").val();
    const introduction = $("#introduction").val();
    const gender = $('input[name=btnradio]:checked').val();

    if(!instagramId) {
        alert('인스타 아이디를 입력해주세요');
        return;
    } else if(!introduction) {
        alert('자기 소개를 입력해주세요');
        return;
    } else{
        const instagramIdRegex = /^([a-z0-9._]{3,30})$/;
        if (!instagramIdRegex.test(instagramId)) {
            alert('올바른 인스타 아이디를 입력해주세요');
            return;
        }
        const introductionRegex = /^([A-Za-z0-9가-힣ㄱ-ㅎㅏ-ㅣ一-龥\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"\s+]{1,100})$/;
        if (!introductionRegex.test(introduction)) {
            alert('영문, 한문, 숫자, 특수문자 100자 이내로 작성해주세요');
            return;
        }
    }
    localStorage.setItem("instagramId", instagramId);
    localStorage.setItem("introduction", introduction);

    window.location.assign("/target_gender_choice");
}
