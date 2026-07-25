package com.event.planner.request;

import java.time.LocalDate;

import com.event.planner.enums.PlannerStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVendorStatusRequest {
	private Integer orgId;
	private PlannerStatus status;
	private LocalDate suspendUntil;
}
