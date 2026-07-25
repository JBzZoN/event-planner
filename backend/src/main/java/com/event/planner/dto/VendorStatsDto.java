package com.event.planner.dto;

import lombok.Data;

@Data
public class VendorStatsDto {
	private Long totalVendors;
	private Long activeVendors;
	private Long inactiveVendors;
	private Long suspendedVendors;
	public VendorStatsDto() {}
	public VendorStatsDto(Long activeVendors, Long inactiveVendors, Long suspendedVendors) {
		super();
		this.activeVendors = activeVendors;
		this.inactiveVendors = inactiveVendors;
		this.suspendedVendors = suspendedVendors;
		this.totalVendors = activeVendors + inactiveVendors + suspendedVendors;
	}
	
}
