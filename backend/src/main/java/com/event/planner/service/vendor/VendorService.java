package com.event.planner.service.vendor;

import java.util.List;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.event.planner.dto.VendorRegistrationDto;
import com.event.planner.entity.Package;
import com.event.planner.entity.PackageGroup;
import com.event.planner.entity.PackageGroupItem;
import com.event.planner.entity.PlannerDetail;
import com.event.planner.entity.UserDetail;
import com.event.planner.enums.PlannerStatus;
import com.event.planner.enums.UserRole;
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

	public PlannerDetail getDetailsById(Jwt jwt) {
		return userDetailRepository.findPlannerDetailByEmailAddress(jwt.getClaimAsString("email"));
	}

	public void updateDetailById(Integer id, PlannerDetail planner) {
		if(plannerDetailRepository.findById(id).isEmpty()) return;
		plannerDetailRepository.save(planner);
	}

	public List<UserDetail> getContactByOrgId(Integer id) {
		return userDetailRepository.findByPlannerDetailOrgId(id);
	}

	public List<com.event.planner.entity.Package> getPackagesById(Jwt jwt) {
		String email = jwt.getClaimAsString("email");
		return packageRepository.findPackagesByUserEmail(email);
	}

	public void addPackage(Package userPackage, Jwt jwt) {
		
		PlannerDetail planner = userDetailRepository.findPlannerDetailByEmailAddress(jwt.getClaimAsString("email"));
		
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

	public void deletePackageById(Integer id) {
		packageRepository.deleteById(id);
	}

	public void registerVendor(VendorRegistrationDto vendorRegistrationDto, Jwt jwt) {
		
		PlannerDetail planner = new PlannerDetail();
		planner.setOfficeAddress(vendorRegistrationDto.getOfficeAddress());
		planner.setOrgName(vendorRegistrationDto.getOrganisationName());
		planner.setStatus(PlannerStatus.INACTIVE);
		planner.setSuspendedDate(null);
		
		plannerDetailRepository.save(planner);
		
		UserDetail userDetail = new UserDetail();
		userDetail.setAddress(vendorRegistrationDto.getPersonalAddress());
		userDetail.setEmailAddress(jwt.getClaimAsString("email"));
		userDetail.setName(vendorRegistrationDto.getName());
		userDetail.setPhone(vendorRegistrationDto.getPhone());
		userDetail.setPlannerDetail(planner);
		userDetail.setUserRole(UserRole.VENDOR);
		
		userDetailRepository.save(userDetail);
		
		Keycloak keycloak = Keycloak.getInstance(
	        "http://localhost:8081",
	        "master",
	        "admin@gmail.com",
	        "admin",
	        "angular-frontend"
	    );

	    String realmName = "master";

	    // Find the user
	    List<UserRepresentation> users = keycloak
            .realm(realmName)
            .users()
            .searchByEmail(
                    jwt.getClaimAsString("email"),
                    true
            );

	    if (users.isEmpty()) {
	        throw new RuntimeException("User not found");
	    }

	    UserRepresentation user = users.get(0);

	    // Get the role
	    RoleRepresentation vendorRole = keycloak
            .realm(realmName)
            .roles()
            .get("VENDOR")
            .toRepresentation();

	    // Assign role to user
	    keycloak
            .realm(realmName)
            .users()
            .get(user.getId())
            .roles()
            .realmLevel()
            .add(List.of(vendorRole));
	}
	
}
