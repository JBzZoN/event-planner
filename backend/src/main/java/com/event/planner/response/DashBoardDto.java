package com.event.planner.response;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class DashBoardDto {
	private long totalVendors;
	private long totalUsers;
	private long totalPackages;
	private long pendingReports;
	private BigDecimal totalRevenue;
	public DashBoardDto(long totalVendors, long totalUsers, long totalPackages, long pendingReports,
			BigDecimal totalRevenue) {
		super();
		this.totalVendors = totalVendors;
		this.totalUsers = totalUsers;
		this.totalPackages = totalPackages;
		this.pendingReports = pendingReports;
		this.totalRevenue = totalRevenue;
	}
	
}
