package hongikclubfair.meetingproject.common.exception;

import hongikclubfair.meetingproject.common.dto.ErrorReason;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MeetingException extends RuntimeException {
	private final BaseErrorCode errorCode;

	public ErrorReason getErrorReason() {
		return errorCode.getErrorCode();
	}
}
