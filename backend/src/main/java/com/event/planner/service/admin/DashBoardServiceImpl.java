package com.event.planner.service.admin;

import org.springframework.beans.factory.annotation.Autowired;

import com.event.planner.repository.PlannerDetailRepository;
import com.event.planner.response.DashBoardDto;

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
