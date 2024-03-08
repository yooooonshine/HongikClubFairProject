package hongikclubfair.meetingproject.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {

	@GetMapping("/")
	public String main() {
		return "main";
	}

	@GetMapping("/api/gender")
	public String gender() {
		return "gender";
	}

	@GetMapping("/api/form")
	public String form() {
		return "form";
	}

	@GetMapping("/api/people_choice")
	public String peopleChoice() {
		return "peopleChoice";
	}

	@GetMapping("/api/gender_choice")
	public String genderChoice() {
		return "genderChoice";
	}

	@GetMapping("/api/result")
	public String result() {
		return "result";
	}
}
