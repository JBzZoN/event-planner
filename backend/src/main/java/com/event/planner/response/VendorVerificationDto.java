package com.event.planner.response;

import java.time.LocalDate;

import lombok.Data;
@Data
public class VendorVerificationDto {
	private Integer verificationId;
	private String orgName;
	private String officeAddress;
	private String status;
	private LocalDate registrationDate;
	private String gstRegistrationCertificate;
}
