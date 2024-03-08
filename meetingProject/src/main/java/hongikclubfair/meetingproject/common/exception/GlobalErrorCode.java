package hongikclubfair.meetingproject.common.exception;

import hongikclubfair.meetingproject.common.dto.ErrorReason;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GlobalErrorCode implements BaseErrorCode{

	// 500
	INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", "내부 서버 오류입니다.")
	;
	private final int status;
	private final String code;
	private final String reason;

	@Override
	public ErrorReason getErrorCode() {
		return null;
	}
}
