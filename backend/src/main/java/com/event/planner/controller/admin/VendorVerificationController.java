package com.event.planner.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.planner.request.UpdateStatus;
import com.event.planner.response.VendorVerificationDto;
import com.event.planner.service.admin.VendorVerificationService;

@RestController
@RequestMapping("/admin/verification")
@CrossOrigin("*")
public class VendorVerificationController {
	
	@Autowired
	private VendorVerificationService vendors;
	
	@GetMapping
	public ResponseEntity<List<VendorVerificationDto>> unVerfiedVendors(){
		return ResponseEntity.ok(vendors.getUnverifiedVendors());
	}
	
	@PatchMapping
	public void updateVendorStatus(@RequestBody UpdateStatus request){
		System.out.print(request.toString());
		 vendors.updateVerificationStatus(request);
	}
}
