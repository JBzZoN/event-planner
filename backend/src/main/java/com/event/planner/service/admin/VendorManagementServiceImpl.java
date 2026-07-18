package com.event.planner.service.admin;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.event.planner.dto.VendorStatsDto;
import com.event.planner.dto.VendorSummaryDto;
import com.event.planner.repository.PlannerDetailRepository;
import com.event.planner.response.VendorManagementResponse;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class VendorManagementServiceImpl implements VendorManagementService{
	
	private final PlannerDetailRepository planner;
	
	private final ModelMapper modelMapper;
	@Override
	public VendorManagementResponse getVendorManagementPage() {
		Object[] vendorStats = (Object[])planner.countAllStatus();
		VendorStatsDto stats = new VendorStatsDto((Long)vendorStats[0],(Long)vendorStats[1],(Long)vendorStats[2]);
		List<VendorSummaryDto> vendorSummary = planner.findAll().stream().map(vendor -> modelMapper.map(vendor, VendorSummaryDto.class)).toList();
		return new VendorManagementResponse(stats, vendorSummary);
	}

}
