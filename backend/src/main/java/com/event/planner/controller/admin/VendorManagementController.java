package com.event.planner.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.planner.request.UpdateVendorStatusRequest;
import com.event.planner.service.admin.VendorManagementService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/management")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VendorManagementController {
	
	private  final VendorManagementService vendorStats;
	
	@GetMapping
	public ResponseEntity<Object> VendorPage(){
		
		return ResponseEntity.ok(vendorStats.getVendorManagementPage());
	}
	@GetMapping("/{orgId}")
	public ResponseEntity<Object> VendorDetails(@PathVariable int orgId){
		return ResponseEntity.ok(vendorStats.getVendorDetails(orgId));
	}
	@PatchMapping
	public ResponseEntity<Object> changeVendorStatus(@RequestBody UpdateVendorStatusRequest req){
		vendorStats.updateVendorStatus(req);
		return ResponseEntity.noContent().build();
	}
}
