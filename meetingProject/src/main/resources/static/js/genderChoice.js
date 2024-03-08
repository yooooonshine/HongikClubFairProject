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

    $.ajax({
        type: 'post',
        url: '/api/resume',
        data: {
            'instagramId': instagramId,
            'introduction': introduction,
            'gender': gender,
            'targerGender': targetGender
        },
        dataType: 'JSON',
        success: function (data) {
            window.location.assign("/api/people_choice");
        }
    })
}

