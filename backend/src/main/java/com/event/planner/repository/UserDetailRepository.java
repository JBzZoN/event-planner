package com.event.planner.repository;

import com.event.planner.entity.PlannerDetail;
import com.event.planner.entity.UserDetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer>{
	public List<UserDetail> findByPlannerDetailOrgId(Integer orgId);
	
	@Query("""
	        SELECT u.plannerDetail
	        FROM UserDetail u
	        WHERE u.emailAddress = :email
	    """)
	    PlannerDetail findPlannerDetailByEmailAddress(@Param("email") String email);
	
}