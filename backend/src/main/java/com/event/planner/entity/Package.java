package com.event.planner.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "package")
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
	
	@OneToMany(mappedBy = "packageEntity", cascade = CascadeType.ALL)
	private List<PackageGroup> packageGroup; 
	
	@OneToMany(mappedBy = "packageEntity", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<PackageType> packageType;
	
	@OneToMany(mappedBy = "packageEntity", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Booking> booking; 
	
}
