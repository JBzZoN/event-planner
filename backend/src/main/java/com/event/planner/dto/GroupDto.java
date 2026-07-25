package com.event.planner.dto;

import java.util.List;

import lombok.Data;

@Data
public class GroupDto {
	private Integer groupId;
	private String groupName;
	List<ItemDto> items;
}
