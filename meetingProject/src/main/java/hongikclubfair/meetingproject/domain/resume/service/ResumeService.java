package hongikclubfair.meetingproject.domain.resume.service;

import static hongikclubfair.meetingproject.domain.resume.domain.Gender.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hongikclubfair.meetingproject.common.vo.ResumePreviewVo;
import hongikclubfair.meetingproject.domain.resume.domain.Gender;
import hongikclubfair.meetingproject.domain.resume.domain.Resume;
import hongikclubfair.meetingproject.domain.resume.dto.request.ModifyResumeRequest;
import hongikclubfair.meetingproject.domain.resume.dto.request.PostResumeRequest;
import hongikclubfair.meetingproject.domain.resume.dto.response.ResumeDetailResponse;
import hongikclubfair.meetingproject.domain.resume.dto.response.ResumeIdResponse;
import hongikclubfair.meetingproject.domain.resume.dto.response.ResumePreviewResponse;
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

	public ResumeIdResponse postResume(PostResumeRequest request) {
		if (resumeRepository.existsByInstagramId(request.instagramId())) {
			throw InstagramIdDuplicateException.EXCEPTION;
		}
		Resume resume = request.toResume();

		return ResumeIdResponse.fromResume(resumeRepository.save(resume));
	}

	@Transactional(readOnly = true)
	public ResumeIdResponse findIdByInstagramId(String instagramId) {
		return resumeRepository.findByInstagramId(instagramId)
			.map(ResumeIdResponse::fromResume)
			.orElseThrow(() -> ResumeNotFoundException.EXCEPTION);
	}

	@Transactional(readOnly = true)
	public ResumePreviewResponse matchResume(Long id) {
		Resume resume = resumeRepository.findById(id)
			.orElseThrow(() -> ResumeNotFoundException.EXCEPTION);

		Gender resumeGender = resume.getGender();
		Gender targetGender = resume.getTargetGender();

		List<Resume> resumes = targetGender.equals(ANY) ?
			resumeRepository.recommendByGender(id, resumeGender, BOTH_GENDER) :
			resumeRepository.recommendByGender(id, resumeGender, List.of(targetGender));

		List<ResumePreviewVo> previews = resumes.stream()
			.map(ResumePreviewVo::fromResume)
			.toList();

		return new ResumePreviewResponse(previews);
	}

	@Transactional(readOnly = true)
	public ResumeDetailResponse getResumeDetail(Long id) {
		return resumeRepository.findById(id)
			.map(ResumeDetailResponse::fromResume)
			.orElseThrow(() -> ResumeNotFoundException.EXCEPTION);
	}

	public void modifyResume(Long id, ModifyResumeRequest request) {
		resumeRepository.findById(id)
			.ifPresentOrElse(resume -> resume.modify(request.instagramId(), request.introduction()),
				() -> {
					throw ResumeNotFoundException.EXCEPTION;
				});
	}

}
