package com.event.planner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.planner.entity.PlannerDetail;

@Repository
public interface PlannerDetailRepository extends JpaRepository<PlannerDetail, Integer> {
	
}
