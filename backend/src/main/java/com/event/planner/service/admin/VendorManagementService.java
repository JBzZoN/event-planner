package com.event.planner.service.admin;

import java.util.List;

import com.event.planner.dto.PackageDto;
import com.event.planner.response.VendorManagementResponse;

public interface VendorManagementService {
	
	VendorManagementResponse getVendorManagementPage();
	List<PackageDto> getVendorDetails(int orgId);
}
