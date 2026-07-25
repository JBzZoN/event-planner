package com.event.planner.entity;

<<<<<<< HEAD
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "package_group_item")
@Data
public class PackageGroupItem {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "item_name", length = 100)
    private String itemName;

    @Column(name = "item_price", precision = 10, scale = 2)
    private BigDecimal itemPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "group_id")
    private PackageGroup packageGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id")
    @JsonIgnore
    private Package packageMaster;
=======
import java.math.BigDecimal; 
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Entity
@Data
public class PackageGroupItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer itemId;
	private String 	itemName;
	private BigDecimal itemPrice;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	private PackageGroup packageGroup;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "package_id")
	private Package pack;
>>>>>>> 842418649dc1293f1000848346c5585ae0e28da2
}
