package hongikclubfair.meetingproject.common.dto;

import java.time.LocalDateTime;

import hongikclubfair.meetingproject.common.exception.BaseErrorCode;

public record ErrorResponse (
	LocalDateTime timeStamp,
	boolean isSuccess,
	int status,
	String code,
	String reason,
	String path
) {
	public static ErrorResponse fromErrorCode(BaseErrorCode errorCode, String path) {
		ErrorReason reason = errorCode.getErrorCode();
		LocalDateTime now = LocalDateTime.now();

		return new ErrorResponse(now, false, reason.status(), reason.code(), reason.reason(), path);
	}
}
