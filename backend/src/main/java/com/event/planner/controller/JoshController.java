package com.event.planner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class JoshController {
		
	@GetMapping("test")
	public String test() {
		return "Test ran successfully";
	}
	
}
