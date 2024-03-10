package hongikclubfair.meetingproject.common.vo;

import hongikclubfair.meetingproject.domain.resume.domain.Gender;
import hongikclubfair.meetingproject.domain.resume.domain.Resume;

public record ResumePreviewVo(
	Long resumeId,
	Gender gender,
	String introduction
) {
	public static ResumePreviewVo fromResume(Resume resume) {
		return new ResumePreviewVo(resume.getId(), resume.getGender(), resume.getIntroduction());
	}
}
