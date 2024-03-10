package hongikclubfair.meetingproject.domain.resume.dto.request;

import hongikclubfair.meetingproject.domain.resume.domain.Gender;
import hongikclubfair.meetingproject.domain.resume.domain.Resume;

public record PostResumeRequest(
	String instagramId,
	String introduction,
	Gender gender,
	Gender targetGender
) {
	public Resume toResume() {
		return new Resume(instagramId, introduction, gender, targetGender);
	}
}
