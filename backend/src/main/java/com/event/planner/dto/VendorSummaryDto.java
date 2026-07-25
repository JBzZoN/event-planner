package com.event.planner.dto;

import java.time.LocalDate;

import com.event.planner.enums.PlannerStatus;

import lombok.Data;

@Data
public class VendorSummaryDto {
	public Integer orgId;
	public String orgName;
	public String officeAddress;
	public PlannerStatus status;
	public LocalDate suspendUntil;
}
