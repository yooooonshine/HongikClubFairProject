package hongikclubfair.meetingproject.domain.resume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hongikclubfair.meetingproject.domain.resume.domain.Gender;
import hongikclubfair.meetingproject.domain.resume.domain.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
	boolean existsByInstagramId(String instagramId);

	@Query(value = "select * "
		+ "from resume "
		+ "where gender in :#{#targetGenders.![name()]} and (target_gender=:#{#gender.name()} or target_gender='ANY') "
		+ "and id<>:id "
		+ "order by rand() "
		+ "limit 3", nativeQuery = true)
	List<Resume> recommendByGender(@Param("id") Long id, @Param("gender") Gender myGender,
		@Param("targetGenders") List<Gender> targetGenders);
}
