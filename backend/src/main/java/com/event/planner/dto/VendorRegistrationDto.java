package com.event.planner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorRegistrationDto {

    private String organisationName;
    private String phone;
    private String officeAddress;
    private String name;
    private String personalAddress;
}