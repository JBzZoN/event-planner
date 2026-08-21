package com.event.planner.controller.vendor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.planner.dto.VendorRegistrationDto;
import com.event.planner.service.vendor.VendorService;

@RestController
@RequestMapping("register")
public class VendorRegistration {
	
	@Autowired
	VendorService vendorService;

	@PostMapping("/vendor")
	public void registerVendor(@RequestBody VendorRegistrationDto vendorRegistrationDto,
	        @AuthenticationPrincipal Jwt jwt) {
		vendorService.registerVendor(vendorRegistrationDto, jwt);
	}	
	
}
