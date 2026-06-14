package com.event.planner.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;

import com.event.planner.ResponseDto.DashBoardDto;
import com.event.planner.repository.PlannerDetailRepository;

public class DashBoardServiceImpl implements DashBoardService{
	@Autowired
	public PlannerDetailRepository plannerDetails;

	@Override
	public DashBoardDto DashboardStats() {
		long totalVendors = plannerDetails.count();
//		long totalUsers = 
		return null;
	}
	
	
}
