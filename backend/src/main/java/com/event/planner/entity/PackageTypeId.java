package com.event.planner.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class PackageTypeId implements Serializable {
    private Integer packageId;
    private Integer typeId;
}