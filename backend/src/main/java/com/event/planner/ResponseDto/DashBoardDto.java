package com.event.planner.ResponseDto;


public class DashBoardDto {
	private long totalVendors;
	private long totalUsers;
	private long totalPackages;
	private long pendingReports;
	private long totalRevenue;
	public DashBoardDto(long totalVendors, long totalUsers, long totalPackages, long pendingReports,
			long totalRevenue) {
		super();
		this.totalVendors = totalVendors;
		this.totalUsers = totalUsers;
		this.totalPackages = totalPackages;
		this.pendingReports = pendingReports;
		this.totalRevenue = totalRevenue;
	}
	
}
