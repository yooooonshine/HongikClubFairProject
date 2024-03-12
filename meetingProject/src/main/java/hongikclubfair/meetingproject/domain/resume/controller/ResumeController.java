package hongikclubfair.meetingproject.domain.resume.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hongikclubfair.meetingproject.common.service.MessageService;
import hongikclubfair.meetingproject.common.vo.MessageInfoVo;
import hongikclubfair.meetingproject.domain.resume.dto.request.ModifyResumeRequest;
import hongikclubfair.meetingproject.domain.resume.dto.request.PostResumeRequest;
import hongikclubfair.meetingproject.domain.resume.dto.request.SendSMSRequest;
import hongikclubfair.meetingproject.domain.resume.dto.response.ResumeDetailResponse;
import hongikclubfair.meetingproject.domain.resume.dto.response.ResumeIdResponse;
import hongikclubfair.meetingproject.domain.resume.dto.response.ResumePreviewResponse;
import hongikclubfair.meetingproject.domain.resume.service.ResumeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resume")
public class ResumeController {

	private final ResumeService resumeService;
	private final MessageService messageService;

	@PostMapping
	public ResumeIdResponse postResume(PostResumeRequest request) {
		return resumeService.postResume(request);
	}

	@GetMapping("/instagramId/{instagramId}")
	public ResumeIdResponse findId(@PathVariable("instagramId") String instagramId) {
		return resumeService.findIdByInstagramId(instagramId);
	}

	@GetMapping("/match/{id}")
	public ResumePreviewResponse matchResume(@PathVariable("id") Long id) {
		return resumeService.matchResume(id);
	}

	@GetMapping("/{id}")
	public ResumeDetailResponse getResumeDetail(@PathVariable("id") Long id) {
		return resumeService.getResumeDetail(id);
	}

	@PatchMapping("/{id}")
	public void modifyResume(@PathVariable("id") Long id, ModifyResumeRequest request) {
		resumeService.modifyResume(id, request);
	}

	@PostMapping("/send/{id}")
	public void sendSMS(@PathVariable("id") Long id, SendSMSRequest request) {
		MessageInfoVo messageInfo = getResumeDetail(id).toMessageInfoVo();
		messageService.sendSMS(messageInfo, request.phoneNumber());
	}
}
