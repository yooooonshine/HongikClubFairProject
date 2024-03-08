package hongikclubfair.meetingproject.common.response;

import static hongikclubfair.meetingproject.common.exception.GlobalErrorCode.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import hongikclubfair.meetingproject.common.dto.ErrorResponse;
import hongikclubfair.meetingproject.common.exception.MeetingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MeetingException.class)
	public ResponseEntity<ErrorResponse> handleCustomException(MeetingException e, HttpServletRequest request) {
		ErrorResponse errorResponse = ErrorResponse.fromException(e, request.getRequestURI());

		return ResponseEntity.status(errorResponse.status())
			.body(errorResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
		ErrorResponse errorResponse = ErrorResponse.fromErrorCode(INTERNAL_SERVER_ERROR, request.getRequestURI());

		return ResponseEntity.status(errorResponse.status())
			.body(errorResponse);
	}
}
