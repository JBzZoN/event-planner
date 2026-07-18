package com.event.planner.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.event.planner.service.admin.VendorManagementService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/Management")
@RequiredArgsConstructor
public class VendorManagementController {
	
	private  final VendorManagementService vendorStats;
	
	@GetMapping
	public ResponseEntity<Object> VendorPage(){
		
		return ResponseEntity.ok(vendorStats.getVendorManagementPage());
	}
}
