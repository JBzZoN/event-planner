package com.event.planner.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class PackageDto {
	private Integer packageId;
	private String packageName;
	private BigDecimal packagePrice;
	private List<GroupDto> groups;
}
