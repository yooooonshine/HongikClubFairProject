package hongikclubfair.meetingproject.common.dto;

public record ErrorReason(
	Integer status,
	String code,
	String reason
) {
}
