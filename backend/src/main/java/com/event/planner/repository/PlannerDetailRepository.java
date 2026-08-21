package com.event.planner.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.event.planner.entity.PlannerDetail;

@Repository
public interface PlannerDetailRepository extends JpaRepository<PlannerDetail, Integer> {
	@Query(value = """
		    SELECT
		        COUNT(CASE WHEN status = 'ACTIVE' THEN 1 END),
		        COUNT(CASE WHEN status = 'INACTIVE' THEN 1 END),
		        COUNT(CASE WHEN status = 'SUSPENDED' THEN 1 END)
		    FROM planner_detail
		    """, nativeQuery = true)
		Object countAllStatus();		
}
	