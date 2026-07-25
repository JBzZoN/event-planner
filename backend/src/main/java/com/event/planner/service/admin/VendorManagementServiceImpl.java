package com.event.planner.service.admin;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.event.planner.dto.GroupDto;
import com.event.planner.dto.ItemDto;
import com.event.planner.dto.PackageDto;
import com.event.planner.dto.VendorStatsDto;
import com.event.planner.dto.VendorSummaryDto;
import com.event.planner.entity.Package;
import com.event.planner.entity.PackageGroup;
import com.event.planner.entity.PackageGroupItem;
import com.event.planner.entity.PlannerDetail;
import com.event.planner.repository.PackageGroupItemRepository;
import com.event.planner.repository.PackageGroupRepository;
import com.event.planner.repository.PackageRepository;
import com.event.planner.repository.PlannerDetailRepository;
import com.event.planner.response.VendorManagementResponse;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class VendorManagementServiceImpl implements VendorManagementService{
	
	private final PlannerDetailRepository planner;
	private final PackageRepository packageRepo;
	private final PackageGroupRepository groupRepo;
	private final PackageGroupItemRepository itemRepo;
	
	private final ModelMapper modelMapper;
	@Override
	public VendorManagementResponse getVendorManagementPage() {
		Object[] vendorStats = (Object[])planner.countAllStatus();
		VendorStatsDto stats = new VendorStatsDto((Long)vendorStats[0],(Long)vendorStats[1],(Long)vendorStats[2]);
		List<VendorSummaryDto> vendorSummary = planner.findAll().stream().map(vendor -> modelMapper.map(vendor, VendorSummaryDto.class)).toList();
		return new VendorManagementResponse(stats, vendorSummary);
	}
	@Override
	public List<PackageDto> getVendorDetails(int orgId) {
		PlannerDetail plannerDetail = planner.findById(orgId).orElseThrow(()-> new RuntimeException("Planner not found"));
		
		return packageRepo.findByPlannerDetail(plannerDetail).stream()
									.map(this::mapPackage).toList();
	}
	
	private  PackageDto mapPackage(Package pack){
		PackageDto dto = modelMapper.map(pack, PackageDto.class);
		List<GroupDto> groups = groupRepo.findByPackageEntity(pack).stream()
								.map(this::mapPackageGroup).toList();
		dto.setGroups(groups);
		return dto;
		
	}
	
	private GroupDto mapPackageGroup(PackageGroup packageGroup) {
		GroupDto dto = modelMapper.map(packageGroup, GroupDto.class);
		List<ItemDto> items = itemRepo.findByPackageGroup(packageGroup).stream()
								.map(this::mapPackageGroupItem).toList();
		dto.setItems(items);		
		return dto;
	}
	
	private ItemDto mapPackageGroupItem(PackageGroupItem item) {
		ItemDto dto = modelMapper.map(item, ItemDto.class);
		return dto;
	}

}
