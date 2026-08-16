package com.event.planner.entity;

import com.event.planner.enums.ServiceType;

import jakarta.persistence.*;

@Entity
@Table(name = "type_table")
public class TypeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Integer typeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_code", nullable = false)
    private ServiceType typeCode;
    
}