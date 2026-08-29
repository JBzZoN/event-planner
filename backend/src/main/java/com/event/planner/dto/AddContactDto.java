package com.event.planner.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddContactDto {
	public String name;
	public String emailAddress;
	public String password;
	public String phone;
	public String address;
	public Integer orgId;
}
