package com.event.planner.service.admin;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.planner.enums.ReportStatus;
import com.event.planner.repository.BookingRepository;
import com.event.planner.repository.PackageRepository;
import com.event.planner.repository.PlannerDetailRepository;
import com.event.planner.repository.ReportsRepository;
import com.event.planner.repository.UserDetailRepository;
import com.event.planner.response.DashBoardDto;

@Service
public class DashBoardServiceImpl implements DashBoardService{
	@Autowired
	private PlannerDetailRepository plannerDetails;
	
	@Autowired
	private UserDetailRepository userDeatil;
	
	@Autowired
	private PackageRepository packageCount;
	
	@Autowired
	private ReportsRepository reports;
	
	@Autowired
	private BookingRepository booking; 
	
	@Override
	public DashBoardDto DashboardStats() {
		long totalVendors = plannerDetails.count();
		long totalUsers = userDeatil.count();
		long totalPackages = packageCount.count();
		long pendingReport = reports.countByStatus(ReportStatus.UNDER_REVIEW);
		BigDecimal totalRevenue = booking.getTotalRevenue();
		System.out.println(totalVendors+" "+pendingReport);
		System.out.println(totalUsers+" "+totalPackages);
		System.out.println(totalRevenue);
		return null;
	}
	
	
}
