package com.event.planner.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "package_type")
@Data
public class PackageType {

    @EmbeddedId
    private PackageTypeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("packageId") // maps to part of primary key
    @JoinColumn(name = "package_id")
    private Package packageEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("typeId")
    @JoinColumn(name = "type_id")
    private TypeTable type;

    
}