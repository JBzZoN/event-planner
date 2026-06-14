package com.event.planner.entity;

import java.time.LocalDate;

import com.event.planner.enums.PlannerStatus;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Table(name = "planner_detail")
@Data
public class PlannerDetail {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_id")
    private Integer orgId;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "office_address")
    private String officeAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PlannerStatus status;

    @Column(name = "suspended_date")
    private LocalDate suspendedDate;

}
