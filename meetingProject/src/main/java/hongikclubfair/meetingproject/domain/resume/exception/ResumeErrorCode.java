package hongikclubfair.meetingproject.domain.resume.exception;

import hongikclubfair.meetingproject.common.dto.ErrorReason;
import hongikclubfair.meetingproject.common.exception.BaseErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ResumeErrorCode implements BaseErrorCode {
	// 404
	RESUME_NOT_FOUND(404, "RESUME_NOT_FOUND", "해당 사람을 찾지 못하였습니다."),

	// 409
	INSTAGRAM_ID_DUPLICATE(409, "INSTAGRAM_ID_DUPLICATE", "중복된 인스타그램 ID 입니다.");
	private final int status;
	private final String code;
	private final String reason;

	@Override
	public ErrorReason getErrorCode() {
		return new ErrorReason(status, code, reason);
	}
}
