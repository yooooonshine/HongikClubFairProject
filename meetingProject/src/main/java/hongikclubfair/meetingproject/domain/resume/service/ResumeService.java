package hongikclubfair.meetingproject.domain.resume.service;

import static hongikclubfair.meetingproject.domain.resume.domain.Gender.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hongikclubfair.meetingproject.domain.resume.domain.Gender;
import hongikclubfair.meetingproject.domain.resume.domain.Resume;
import hongikclubfair.meetingproject.domain.resume.dto.request.PostResumeRequest;
import hongikclubfair.meetingproject.domain.resume.dto.response.ResumeSimpleResponse;
import hongikclubfair.meetingproject.domain.resume.exception.InstagramIdDuplicateException;
import hongikclubfair.meetingproject.domain.resume.exception.ResumeNotFoundException;
import hongikclubfair.meetingproject.domain.resume.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ResumeService {
	private final ResumeRepository resumeRepository;
	private final static List<Gender> BOTH_GENDER = List.of(MALE, FEMALE);

	public Long postResume(PostResumeRequest request) {
		if (resumeRepository.existsByInstagramId(request.instagramId())) {
			throw InstagramIdDuplicateException.EXCEPTION;
		}
		Resume resume = request.toResume();

		return resumeRepository.save(resume).getId();
	}

	@Transactional(readOnly = true)
	public List<ResumeSimpleResponse> matchResume(Long id) {
		Resume resume = resumeRepository.findById(id)
			.orElseThrow(() -> ResumeNotFoundException.EXCEPTION);

		Gender resumeGender = resume.getGender();
		Gender targetGender = resume.getTargetGender();

		List<Resume> resumes = targetGender.equals(ANY) ?
			resumeRepository.recommendByGender(id, resumeGender, BOTH_GENDER) :
			resumeRepository.recommendByGender(id, resumeGender, List.of(targetGender));

		return resumes.stream()
			.map(ResumeSimpleResponse::fromResume)
			.toList();
	}
}
