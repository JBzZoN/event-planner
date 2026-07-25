package com.event.planner.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.event.planner.enums.VerificationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class VendorVerification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer verificationId;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "org_id")
	private PlannerDetail planner;
	@Enumerated(EnumType.STRING)
	@JdbcTypeCode(SqlTypes.NAMED_ENUM)
	private VerificationStatus status;
	private LocalDateTime registrationDate;
	private LocalDateTime verifiedDate;
	private String remarks;
	private String gstRegistrationCertificate;
}
