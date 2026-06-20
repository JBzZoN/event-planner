package com.event.planner.controller.vendor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.planner.entity.PlannerDetail;
import com.event.planner.service.vendor.VendorService;

@RestController
@RequestMapping("vendor")
@CrossOrigin("*")
public class VendorController {
		
	@Autowired
	private VendorService vendorService;
	
	@GetMapping("test")
	public ResponseEntity<?> test() {
		return ResponseEntity.ok(vendorService.test());
	}
	
	@GetMapping("details/{id}")
	public ResponseEntity<PlannerDetail> getDetailById(@PathVariable Integer id) {
		return ResponseEntity.ok(vendorService.getDetailsById(id));
	}
	
	@PatchMapping("details/{id}")
	public void updateDetailById(@PathVariable Integer id, @RequestBody PlannerDetail planner) {
		vendorService.updateDetailById(id, planner);
	}
}
