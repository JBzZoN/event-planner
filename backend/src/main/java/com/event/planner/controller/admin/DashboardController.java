package com.event.planner.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.planner.service.admin.DashBoardService;

@RestController
@RequestMapping("/admin/dashboard")
@CrossOrigin("*")
public class DashboardController {
	
	@Autowired
	private DashBoardService dashBoardService;
	
	@GetMapping
	public ResponseEntity<?> dashboard(){
		dashBoardService.DashboardStats();
		return ResponseEntity.ok(dashBoardService.DashboardStats());
	}
}
