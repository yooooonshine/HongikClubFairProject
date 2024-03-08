package hongikclubfair.meetingproject.domain.resume.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hongikclubfair.meetingproject.domain.resume.dto.request.PostResumeRequest;
import hongikclubfair.meetingproject.domain.resume.service.ResumeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resume")
public class ResumeController {

	private final ResumeService resumeService;

	@PostMapping
	public void postResume(@RequestBody PostResumeRequest request) {
		resumeService.postResume(request);
	}

}
