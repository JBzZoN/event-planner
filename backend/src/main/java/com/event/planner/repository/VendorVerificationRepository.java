package com.event.planner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.planner.entity.VendorVerification;
import com.event.planner.enums.VerificationStatus;

public interface VendorVerificationRepository extends JpaRepository<VendorVerification, Integer>{
	List<VendorVerification> findByStatus(VerificationStatus status);
}
