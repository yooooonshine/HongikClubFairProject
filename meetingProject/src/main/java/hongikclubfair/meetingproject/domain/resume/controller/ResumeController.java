package hongikclubfair.meetingproject.domain.resume.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hongikclubfair.meetingproject.domain.resume.dto.request.PostResumeRequest;
import hongikclubfair.meetingproject.domain.resume.dto.response.ResumeSimpleResponse;
import hongikclubfair.meetingproject.domain.resume.service.ResumeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resume")
public class ResumeController {

	private final ResumeService resumeService;

	@PostMapping
	public Long postResume(@RequestBody PostResumeRequest request) {
		return resumeService.postResume(request);
	}

	@GetMapping("/instagramId/{instagramId}")
	public Long getInstagramId(@PathVariable("instagramId") String instagramId) {
		return resumeService.getInstagramId(instagramId);
	}

	@GetMapping("/match/{id}")
	public List<ResumeSimpleResponse> matchResume(@PathVariable("id") Long id) {
		return resumeService.matchResume(id);
	}
}
