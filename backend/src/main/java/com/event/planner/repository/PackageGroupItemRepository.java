package com.event.planner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.planner.entity.PackageGroup;
import com.event.planner.entity.PackageGroupItem;

@Repository
public interface PackageGroupItemRepository extends JpaRepository<PackageGroupItem, Integer>{
	List<PackageGroupItem> findByPackageGroup(PackageGroup group);
	
}
