package com.event.planner.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.event.planner.dto.VendorSummaryDto;
import com.event.planner.entity.PlannerDetail;

@Configuration
public class ModelMapperConfig {
	@Bean
	public ModelMapper modelMapper() {
		 ModelMapper modelMapper = new ModelMapper();

		    TypeMap<PlannerDetail, VendorSummaryDto> typeMap =
		            modelMapper.createTypeMap(PlannerDetail.class, VendorSummaryDto.class);

		    typeMap.addMappings(mapper -> {
		        mapper.map(PlannerDetail::getSuspendedDate,
		                   VendorSummaryDto::setSuspendUntil);
		    });

		    return modelMapper;
	}
}
