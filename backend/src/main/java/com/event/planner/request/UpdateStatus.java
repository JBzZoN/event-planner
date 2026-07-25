package com.event.planner.request;

import com.event.planner.enums.VerificationStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatus {
	private Integer verificationId;
	private VerificationStatus status;
	private String remarks;
}
