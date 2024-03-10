package hongikclubfair.meetingproject.common.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record SuccessResponse(
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime timeStamp,
	boolean isSuccess,
	int status,
	Object data
) {
	public static SuccessResponse ok(Object data) {
		LocalDateTime now = LocalDateTime.now();
		return new SuccessResponse(now, true, 200, data);
	}

	public static SuccessResponse ok() {
		LocalDateTime now = LocalDateTime.now();
		return new SuccessResponse(now, true, 200, null);
	}
}
