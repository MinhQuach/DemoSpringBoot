package org.o7planning.hellospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/newfile")
	public String display() {
		return "NewFile";
	}
}
