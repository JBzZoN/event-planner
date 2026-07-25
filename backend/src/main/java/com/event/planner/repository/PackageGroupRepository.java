package com.event.planner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.planner.entity.Package;
import com.event.planner.entity.PackageGroup;

@Repository
public interface PackageGroupRepository extends JpaRepository<PackageGroup, Integer>{
	List<PackageGroup> findByPack(Package pack);
}
	
