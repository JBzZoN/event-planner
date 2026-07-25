package com.event.planner.service.admin;

import java.util.List;

import com.event.planner.request.UpdateStatus;
import com.event.planner.response.VendorVerificationDto;

public interface VendorVerificationService {
	public List<VendorVerificationDto> getUnverifiedVendors();
	
	public void updateVerificationStatus(UpdateStatus status);
}
