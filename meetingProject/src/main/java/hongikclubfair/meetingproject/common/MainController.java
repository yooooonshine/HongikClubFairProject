package hongikclubfair.meetingproject.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {

	@GetMapping("/")
	public String main() {
		log.info("why=================================");
		return "main";
	}

	@GetMapping("/gender")
	public String gender() {
		return "gender";
	}

	@GetMapping("/form")
	public String form() {
		return "form";
	}

	@GetMapping("/choice")
	public String choice() {
		return "choice";
	}

	@GetMapping("/result")
	public String result() {
		return "result";
	}
}
