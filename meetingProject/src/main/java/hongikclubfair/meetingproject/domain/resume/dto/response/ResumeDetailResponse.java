package hongikclubfair.meetingproject.domain.resume.dto.response;

import hongikclubfair.meetingproject.domain.resume.domain.Resume;

public record ResumeDetailResponse(
	String instagramId,
	String introduction
) {
	public static ResumeDetailResponse fromResume(Resume resume) {
		return new ResumeDetailResponse(resume.getInstagramId(), resume.getIntroduction());
	}
}
