package com.event.planner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.event.planner.entity.Package;

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer>{
	
	public List<Package> findByPlannerDetailOrgId(Integer orgId);
	
}	
