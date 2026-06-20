package com.event.planner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.planner.entity.Reports;
import com.event.planner.enums.ReportStatus;

@Repository
public interface ReportsRepository extends JpaRepository<Reports, Integer>{
	public int countByStatus(ReportStatus status);
}
