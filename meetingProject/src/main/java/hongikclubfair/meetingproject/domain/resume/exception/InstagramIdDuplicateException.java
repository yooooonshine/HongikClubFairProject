package hongikclubfair.meetingproject.domain.resume.exception;

import hongikclubfair.meetingproject.common.exception.MeetingException;

public class InstagramIdDuplicateException extends MeetingException {
	public static final MeetingException EXCEPTION = new InstagramIdDuplicateException();

	private InstagramIdDuplicateException() {
		super(ResumeErrorCode.INSTAGRAM_ID_DUPLICATE);
	}
}
