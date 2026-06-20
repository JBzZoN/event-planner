package com.event.planner.service.vendor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.event.planner.entity.PlannerDetail;
import com.event.planner.repository.PlannerDetailRepository;

@Service
public class VendorService {
	
	@Autowired
	private PlannerDetailRepository plannerDetailRepository;
	
	public List<PlannerDetail> test() {
		return plannerDetailRepository.findAll();
	}

	public PlannerDetail getDetailsById(Integer id) {
		return plannerDetailRepository.findById(id).get();
	}

	public void updateDetailById(Integer id, PlannerDetail planner) {
		if(plannerDetailRepository.findById(id).isEmpty()) return;
		plannerDetailRepository.save(planner);
	}
	
}
