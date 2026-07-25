package com.event.planner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.event.planner.entity.Package;
import com.event.planner.entity.PlannerDetail;

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer>{
	
	List<Package> findByPlannerDetail(PlannerDetail plannerDetail);
}
