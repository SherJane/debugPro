package jwc.debug_pro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/test")
public class TestController {

	@RequestMapping("/hello")
	public String sayHello() {
		return "hello";
	}
}
