var CHECK1 = false;
var CHECK2 = false;

function clickNext() {
    CHECK1 = $('#termCheckbox1').is(':checked');
    CHECK2 = $('#termCheckbox2').is(':checked');
    localStorage.setItem("termChecked1", CHECK1);
    localStorage.setItem("termChecked2", CHECK2);
    if(CHECK1 && CHECK2){
        window.location.assign("/member_form");
    } else if(!CHECK1){
        alert('서비스 이용약관에 동의해주세요');
        return;
    } else if(!CHECK2){
        alert('개인정보 수집/이용 동의서에 동의해주세요');
        return;
    }
}

//처음으로 돌아가기
function directIntro() {
    window.location.assign("/");
}