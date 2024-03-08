package hongikclubfair.meetingproject.domain.resume.service;

import org.springframework.stereotype.Service;

import hongikclubfair.meetingproject.domain.resume.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumeService {
	private final ResumeRepository resumeRepository;

}
