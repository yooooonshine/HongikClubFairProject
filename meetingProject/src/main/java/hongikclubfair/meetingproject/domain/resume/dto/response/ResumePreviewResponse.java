package hongikclubfair.meetingproject.domain.resume.dto.response;

import java.util.List;

import hongikclubfair.meetingproject.common.vo.ResumePreviewVo;

public record ResumePreviewResponse(
	List<ResumePreviewVo> previews
) {
}
