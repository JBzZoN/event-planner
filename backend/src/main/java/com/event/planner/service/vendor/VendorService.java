package com.event.planner.service.vendor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.event.planner.dto.AddContactDto;
import com.event.planner.dto.VendorRegistrationDto;
import com.event.planner.entity.Package;
import com.event.planner.entity.PackageGroup;
import com.event.planner.entity.PackageGroupItem;
import com.event.planner.entity.PlannerDetail;
import com.event.planner.entity.UserDetail;
import com.event.planner.entity.VendorVerification;
import com.event.planner.enums.PlannerStatus;
import com.event.planner.enums.UserRole;
import com.event.planner.enums.VerificationStatus;
import com.event.planner.repository.PackageRepository;
import com.event.planner.repository.PlannerDetailRepository;
import com.event.planner.repository.UserDetailRepository;
import com.event.planner.repository.VendorVerificationRepository;

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
	
	@Autowired
	private VendorVerificationRepository vendorVerificationRepository;
	
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

	public void editPackage(Package userPackage, Jwt jwt) {
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

	public void addContact(AddContactDto addContactDto) {
		UserDetail user = new UserDetail();
		PlannerDetail planner = plannerDetailRepository.findById(addContactDto.getOrgId()).get();
		
		user.setAddress(addContactDto.getAddress());
		user.setEmailAddress(addContactDto.getEmailAddress());
		user.setName(addContactDto.getName());
		user.setPhone(addContactDto.getPhone());
		user.setUserRole(UserRole.VENDOR);
		user.setPlannerDetail(planner);
		
		
		userDetailRepository.save(user);
		
		
		// Keycloak
		Keycloak keycloak = Keycloak.getInstance(
	        "http://localhost:8081",
	        "master",
	        "admin@gmail.com",
	        "admin",
	        "angular-frontend"
	    );
	    
	    UserRepresentation userRep = new UserRepresentation();

	    userRep.setEmail(addContactDto.getEmailAddress());
	    userRep.setEnabled(true);
	    
	    CredentialRepresentation credential = new CredentialRepresentation();

	    credential.setType(CredentialRepresentation.PASSWORD);
	    credential.setValue(addContactDto.getPassword());
	    credential.setTemporary(true);
	    
	    RoleRepresentation role = keycloak
            .realm("master")
            .roles()
            .get("VENDOR")
            .toRepresentation();

	    
	    keycloak
        .realm("master")
        .users()
        .create(userRep);
	    
	    keycloak
        .realm("master")
        .users()
        .get(keycloak
        	    .realm("master")
        	    .users()
        	    .searchByEmail(addContactDto.getEmailAddress(), true)
        	    .get(0)
        	    .getId())
        .roles()
        .realmLevel()
        .add(List.of(role));
        
	    keycloak
        .realm("master")
        .users()
        .get(keycloak
        	    .realm("master")
        	    .users()
        	    .searchByEmail(addContactDto.getEmailAddress(), true)
        	    .get(0)
        	    .getId())
	    .resetPassword(credential);

	}

	public void verification(MultipartFile file, Integer orgId) {
		try {

	        Path gstDirectory = Paths.get("gst");

	        Files.createDirectories(gstDirectory);

	        String fileName = UUID.randomUUID() + ".pdf";

	        Path filePath = gstDirectory.resolve(fileName);

	        Files.copy(
	            file.getInputStream(),
	            filePath,
	            StandardCopyOption.REPLACE_EXISTING
	        );
	        
	        PlannerDetail plannerDetail = plannerDetailRepository.findById(orgId).get();
	        plannerDetail.setStatus(PlannerStatus.PENDING_VERIFICATION);
	        
	        plannerDetailRepository.save(plannerDetail);
	        
	        VendorVerification vendorVerification = new VendorVerification();
	        vendorVerification.setGstRegistrationCertificate("gst/" + fileName);
	        vendorVerification.setPlanner(plannerDetail);
	        vendorVerification.setRegistrationDate(LocalDateTime.now());
	        vendorVerification.setStatus(VerificationStatus.PENDING);
	        
	        vendorVerificationRepository.save(vendorVerification);

	    } catch (IOException e) {
	        throw new RuntimeException("Failed to save GST certificate", e);
	    }
	}
	
}
