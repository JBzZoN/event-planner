package com.event.planner.response;

import java.util.List;

import com.event.planner.dto.VendorStatsDto;
import com.event.planner.dto.VendorSummaryDto;

import lombok.Data;

@Data
public class VendorManagementResponse {
	private final VendorStatsDto stats;
	private final List<VendorSummaryDto> vendorList;
}
