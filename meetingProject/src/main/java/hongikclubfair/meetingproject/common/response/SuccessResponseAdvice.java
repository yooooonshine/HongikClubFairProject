package hongikclubfair.meetingproject.common.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import hongikclubfair.meetingproject.common.dto.SuccessResponse;
import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice(basePackages = "hongikclubfair.meetingproject.domain")
public class SuccessResponseAdvice implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(
		Object body,
		MethodParameter returnType,
		MediaType selectedContentType,
		Class<? extends HttpMessageConverter<?>> selectedConverterType,
		ServerHttpRequest request,
		ServerHttpResponse response) {
		HttpServletResponse servletResponse = ((ServletServerHttpResponse)response).getServletResponse();
		int status = servletResponse.getStatus();
		HttpStatus resolve = HttpStatus.resolve(status);

		if (resolve == null) {
			return body;
		}

		if (resolve.is2xxSuccessful()) {
			return SuccessResponse.ok(body);
		}

		return body;
	}

}
