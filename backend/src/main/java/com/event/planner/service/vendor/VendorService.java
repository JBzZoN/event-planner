package com.event.planner.service.vendor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.planner.entity.Package;
import com.event.planner.entity.PackageGroup;
import com.event.planner.entity.PackageGroupItem;
import com.event.planner.entity.PlannerDetail;
import com.event.planner.entity.UserDetail;
import com.event.planner.repository.PackageRepository;
import com.event.planner.repository.PlannerDetailRepository;
import com.event.planner.repository.UserDetailRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VendorService {
	
	@Autowired
	private PlannerDetailRepository plannerDetailRepository;
	
	@Autowired
	private PackageRepository packageRepository;
	
	@Autowired
	private UserDetailRepository userDetailRepository;
	
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

	public List<UserDetail> getContactByOrgId(Integer id) {
		return userDetailRepository.findByPlannerDetailOrgId(id);
	}

	public List<com.event.planner.entity.Package> getPackagesById(Integer id) {
		return packageRepository.findByPlannerDetailOrgId(id);
	}

	public void addPackage(Package userPackage, Integer id) {
		
		PlannerDetail planner = plannerDetailRepository.findById(id).get();
		
		for (PackageGroup group : userPackage.getPackageGroup()) {

		    group.setPackageEntity(userPackage);

		    for (PackageGroupItem item : group.getPackageGroupItem()) {

		        item.setPackageGroup(group);
		        item.setPackageMaster(userPackage);
		    }
		}
		
		userPackage.setPlannerDetail(planner);

		packageRepository.save(userPackage);
	}
	
}
