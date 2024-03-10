package hongikclubfair.meetingproject.domain.resume.dto.response;

import hongikclubfair.meetingproject.domain.resume.domain.Resume;

public record ResumeIdResponse(
	Long id
) {
	public static ResumeIdResponse fromResume(Resume resume) {
		return new ResumeIdResponse(resume.getId());
	}
}
