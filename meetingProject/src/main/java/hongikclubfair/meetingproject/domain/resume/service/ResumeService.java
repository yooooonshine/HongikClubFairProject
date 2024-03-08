package hongikclubfair.meetingproject.domain.resume.service;

import org.springframework.stereotype.Service;

import hongikclubfair.meetingproject.domain.resume.domain.Resume;
import hongikclubfair.meetingproject.domain.resume.dto.request.PostResumeRequest;
import hongikclubfair.meetingproject.domain.resume.exception.InstagramIdDuplicateException;
import hongikclubfair.meetingproject.domain.resume.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumeService {
	private final ResumeRepository resumeRepository;

	public void postResume(PostResumeRequest request) {
		if (resumeRepository.existsByInstagramId(request.instagramId())) {
			throw InstagramIdDuplicateException.EXCEPTION;
		}
		Resume resume = request.toResume();

		resumeRepository.save(resume);
	}
}
