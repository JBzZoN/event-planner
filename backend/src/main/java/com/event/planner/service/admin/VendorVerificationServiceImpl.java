package com.event.planner.service.admin;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.event.planner.entity.PlannerDetail;
import com.event.planner.entity.VendorVerification;
import com.event.planner.enums.PlannerStatus;
import com.event.planner.enums.VerificationStatus;
import com.event.planner.repository.VendorVerificationRepository;
import com.event.planner.request.UpdateStatus;
import com.event.planner.response.VendorVerificationDto;


@Service
@Transactional
public class VendorVerificationServiceImpl implements VendorVerificationService {

	@Autowired
	private VendorVerificationRepository vendors;
	
	@Override
	public List<VendorVerificationDto> getUnverifiedVendors() {
		List<VendorVerificationDto> response =
				vendors.findByStatus(VerificationStatus.PENDING)
				.stream()
				.map(this::mapToDto)
				.toList();
		return response;
	}
	private VendorVerificationDto mapToDto(VendorVerification vendor) {

	    VendorVerificationDto dto = new VendorVerificationDto();

	    dto.setVerificationId(vendor.getVerificationId());

	    dto.setOrgName(vendor.getPlanner().getOrgName());

	    dto.setOfficeAddress(vendor.getPlanner().getOfficeAddress());

	    dto.setStatus(vendor.getStatus().name());

	    dto.setRegistrationDate(vendor.getRegistrationDate().toLocalDate());

	    dto.setGstRegistrationCertificate(vendor.getGstRegistrationCertificate());

	    return dto;
	}
	
	@Override
	public void updateVerificationStatus(UpdateStatus status) {

	    VendorVerification vendor =
	            vendors.findById(status.getVerificationId())
	                   .orElseThrow(() ->
	                       new RuntimeException("Vendor verification not found"));
	    PlannerDetail planner = vendor.getPlanner();
	    if(status.getStatus().equals(VerificationStatus.APPROVED))planner.setStatus(PlannerStatus.ACTIVE);
	    else if(status.getStatus().equals(VerificationStatus.REJECTED)) planner.setStatus(PlannerStatus.INACTIVE);
	    vendor.setRemarks(status.getRemarks());
	    if(status.getStatus().equals(VerificationStatus.APPROVED))vendor.setVerifiedDate(LocalDateTime.now());
	    vendor.setStatus(status.getStatus());
	    vendors.save(vendor);
	}

}
