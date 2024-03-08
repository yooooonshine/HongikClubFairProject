package hongikclubfair.meetingproject.domain.resume.exception;

import hongikclubfair.meetingproject.common.exception.MeetingException;

public class ResumeNotFoundException extends MeetingException {

	private final static MeetingException EXCEPTION = new ResumeNotFoundException();

	private ResumeNotFoundException() {
		super(ResumeErrorCode.RESUME_NOT_FOUND);
	}
}
