package com.event.planner.controller.vendor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("details/{id}")
	public ResponseEntity<PlannerDetail> getDetailById(@PathVariable Integer id) {
		return ResponseEntity.ok(vendorService.getDetailsById(id));
	}
	
	@GetMapping("contacts/{id}")
	public ResponseEntity<List<UserDetail>> getContactByOrgId(@PathVariable Integer id) {
		return ResponseEntity.ok(vendorService.getContactByOrgId(id));
	}
	
	@GetMapping("package/{id}") 	
	public ResponseEntity<List<Package>> getPackagesById(@PathVariable Integer id) {
		return ResponseEntity.ok(vendorService.getPackagesById(id));
	}
	
	@DeleteMapping("package/{id}") 	
	public void deletePackageById(@PathVariable Integer id) {
		vendorService.deletePackageById(id);
	}
	
	@PostMapping("package/{id}")
	public void addPackage(@RequestBody Package userPackage ,@PathVariable Integer id) {
		vendorService.addPackage(userPackage, id);
	}	
	
	@PatchMapping("details/{id}")
	public void updateDetailById(@PathVariable Integer id, @RequestBody PlannerDetail planner) {
		vendorService.updateDetailById(id, planner);
	}
}
