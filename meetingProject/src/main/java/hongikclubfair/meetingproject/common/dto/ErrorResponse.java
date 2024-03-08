package hongikclubfair.meetingproject.common.dto;

import java.time.LocalDateTime;

import hongikclubfair.meetingproject.common.exception.BaseErrorCode;
import hongikclubfair.meetingproject.common.exception.MeetingException;

public record ErrorResponse(
	LocalDateTime timeStamp,
	boolean isSuccess,
	int status,
	String code,
	String reason,
	String path
) {
	public static ErrorResponse fromException(MeetingException exception, String path) {
		ErrorReason reason = exception.getErrorReason();
		LocalDateTime now = LocalDateTime.now();

		return new ErrorResponse(now, false, reason.status(), reason.code(), reason.reason(), path);
	}

	public static ErrorResponse fromErrorCode(BaseErrorCode errorCode, String path) {
		ErrorReason reason = errorCode.getErrorCode();
		LocalDateTime now = LocalDateTime.now();

		return new ErrorResponse(now, false, reason.status(), reason.code(), reason.reason(), path);
	}
}
