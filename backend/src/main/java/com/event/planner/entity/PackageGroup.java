package com.event.planner.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "package_group")
@Data
public class PackageGroup {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "group_name", length = 100)
    private String groupName;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "package_id")
    private Package packageEntity;
    
    @OneToMany(mappedBy = "packageGroup", cascade = CascadeType.ALL)
    private List<PackageGroupItem> packageGroupItem;
	
}
