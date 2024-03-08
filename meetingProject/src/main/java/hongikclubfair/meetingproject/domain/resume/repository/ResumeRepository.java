package hongikclubfair.meetingproject.domain.resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hongikclubfair.meetingproject.domain.resume.domain.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
