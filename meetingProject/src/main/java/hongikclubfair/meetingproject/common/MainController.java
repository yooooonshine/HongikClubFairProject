package hongikclubfair.meetingproject.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {

	@GetMapping("/")
	public String intro() {
		return "intro";
	}

	@GetMapping("/terms_of_use")
	public String use() {
		return "termsOfUse";
	}

	@GetMapping("/member_form")
	public String form() {
		return "memberForm";
	}

	@GetMapping("/member_form_deletion")
	public String deletion() {
		return "memberFormDeletion";
	}

	@GetMapping("/people_choice")
	public String peopleChoice() {
		return "peopleChoice";
	}

	@GetMapping("/target_gender_choice")
	public String genderChoice() {
		return "targetGenderChoice";
	}

	@GetMapping("/result")
	public String result() {
		return "result";
	}
}
