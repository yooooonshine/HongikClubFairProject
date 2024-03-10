package hongikclubfair.meetingproject.domain.resume.dto.response;

import hongikclubfair.meetingproject.common.vo.MessageInfoVo;
import hongikclubfair.meetingproject.domain.resume.domain.Resume;

public record ResumeDetailResponse(
	String instagramId,
	String introduction
) {
	public static ResumeDetailResponse fromResume(Resume resume) {
		return new ResumeDetailResponse(resume.getInstagramId(), resume.getIntroduction());
	}

	public MessageInfoVo toMessageInfoVo() {
		return new MessageInfoVo(instagramId, introduction);
	}
}
