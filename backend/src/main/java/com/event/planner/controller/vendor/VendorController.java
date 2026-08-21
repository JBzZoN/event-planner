package com.event.planner.controller.vendor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.planner.dto.VendorRegistrationDto;
import com.event.planner.entity.Package;
import com.event.planner.entity.PlannerDetail;
import com.event.planner.entity.UserDetail;
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
	
	@GetMapping("details")
	public ResponseEntity<PlannerDetail> getDetailById(@AuthenticationPrincipal Jwt jwt) {
		return ResponseEntity.ok(vendorService.getDetailsById(jwt));
	}
	
	@GetMapping("contacts/{id}")
	public ResponseEntity<List<UserDetail>> getContactByOrgId(@PathVariable Integer id) {
		return ResponseEntity.ok(vendorService.getContactByOrgId(id));
	}
	
	@GetMapping("package") 	
	public ResponseEntity<List<Package>> getPackagesById(@AuthenticationPrincipal Jwt jwt) {
		return ResponseEntity.ok(vendorService.getPackagesById(jwt));
	}
	
	@DeleteMapping("package/{id}") 	
	public void deletePackageById(@PathVariable Integer id) {
		vendorService.deletePackageById(id);
	}
	
	@PostMapping("package")
	public void addPackage(@RequestBody Package userPackage, @AuthenticationPrincipal Jwt jwt) {
		vendorService.addPackage(userPackage, jwt);
	}	
	
	@PatchMapping("details/{id}")
	public void updateDetailById(@PathVariable Integer id, @RequestBody PlannerDetail planner) {
		vendorService.updateDetailById(id, planner);
	}
}
