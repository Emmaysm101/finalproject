package org.perscholas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/adminHome")
	public String adminHome() {
		return "adminHome";
	}
}
