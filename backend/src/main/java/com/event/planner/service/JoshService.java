package com.event.planner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.planner.entity.PlannerDetail;
import com.event.planner.repository.PlannerDetailRepository;

@Service
public class JoshService {
	
	@Autowired
	private PlannerDetailRepository plannerDetailRepository;
	
	public List<PlannerDetail> test() {
		return plannerDetailRepository.findAll();
	}
	
}
