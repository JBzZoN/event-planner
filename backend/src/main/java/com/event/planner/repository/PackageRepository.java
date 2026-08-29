package com.event.planner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.event.planner.entity.Package;
import com.event.planner.entity.PlannerDetail;

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer>{
	public List<Package> findByPlannerDetailOrgId(Integer orgId);
	
	@Query("""
		    SELECT p
		    FROM Package p
		    JOIN p.plannerDetail pd
		    JOIN UserDetail ud ON ud.plannerDetail = pd
		    WHERE ud.emailAddress = :email
		""")
	List<Package> findPackagesByUserEmail(@Param("email") String email);
	
	List<Package> findByPlannerDetail(PlannerDetail plannerDetail);
}
