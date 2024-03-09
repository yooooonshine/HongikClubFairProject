
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
        const instagramIdRegex = /^([A-Za-z0-9._]{1,30})$/;
        if (!instagramIdRegex.test(instagramId)) {
            alert('올바른 인스타 아이디를 입력해주세요');
            return;
        }
    }
    localStorage.setItem("instagramId", instagramId);
    localStorage.setItem("introduction", introduction);

    window.location.assign("/target_gender_choice");
}
