package hongikclubfair.meetingproject.domain.resume.dto.request;

import hongikclubfair.meetingproject.domain.resume.domain.Gender;

public record ModifyResumeRequest(
	String instagramId,
	String introduction,
	Gender gender,
	Gender targetGender
) {
}
