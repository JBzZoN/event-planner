package com.event.planner.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Package {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "package_id")
	private Integer packageId;
	@ManyToOne
	@JoinColumn(name = "org_id")
	private PlannerDetail plannerDetail;
	@Column(name = "package_name")
	private String packageName;
	@Column(name = "package_price")
	private BigDecimal packagePrice;
	
}
