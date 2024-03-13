package hongikclubfair.meetingproject.domain.resume.domain;

import hongikclubfair.meetingproject.domain.resume.dto.request.ModifyResumeRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Resume {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 20, unique = true)
	private String instagramId;

	@Column(nullable = false, length = 100)
	private String introduction;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender targetGender;

	public Resume(String instagramId, String introduction, Gender gender, Gender targetGender) {
		this.instagramId = instagramId;
		this.introduction = introduction;
		this.gender = gender;
		this.targetGender = targetGender;
	}

	public void modify(ModifyResumeRequest request) {
		this.instagramId = request.instagramId();
		this.introduction = request.introduction();
		this.gender = request.gender();
		this.targetGender = request.targetGender();
	}
}
