package com.event.planner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.event.planner.entity.Package;

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer>{
	
}
