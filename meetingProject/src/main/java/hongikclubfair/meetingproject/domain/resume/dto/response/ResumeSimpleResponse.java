package hongikclubfair.meetingproject.domain.resume.dto.response;

import hongikclubfair.meetingproject.domain.resume.domain.Gender;
import hongikclubfair.meetingproject.domain.resume.domain.Resume;

public record ResumeSimpleResponse(
	Long resumeId,
	Gender gender,
	String introduction
) {
	public static ResumeSimpleResponse fromResume(Resume resume) {
		return new ResumeSimpleResponse(resume.getId(), resume.getGender(), resume.getIntroduction());
	}
}
