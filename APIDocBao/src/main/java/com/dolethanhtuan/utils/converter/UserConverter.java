package com.dolethanhtuan.utils.converter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dolethanhtuan.dto.NotificationDTO;
import com.dolethanhtuan.dto.UserDTO;
import com.dolethanhtuan.entity.RoleEntity;
import com.dolethanhtuan.entity.UserEntity;
import com.dolethanhtuan.repository.RoleRepository;
@Component
public class UserConverter {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	
	public UserEntity toEntity(UserEntity userEOld,UserDTO userD) {
		if(userD.getUsername() != null) {
			userEOld.setUsername(userD.getUsername());
		}
		if(userD.getPassword() != null) {
			userEOld.setPassword(userD.getPassword());
		}
		if(userD.getFullname() != null) {
			userEOld.setFullname(userD.getFullname());
		}
		if(userD.getStatus() != null)
			userEOld.setStatus(userD.getStatus());
		if(userD.getRoleCodes().size() !=0) {
			List<RoleEntity> roles = new ArrayList<>();
			for (String roleCode : userD.getRoleCodes()) {
				RoleEntity roleE = roleRepository.findOneByRoleCode(roleCode);
				if(roleE != null)
					roles.add(roleE);
			}
			userEOld.setRoles(roles);
		}
		return userEOld;
	}
	public UserEntity toEntity(UserDTO userD) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		UserEntity userE = modelMapper.map(userD, UserEntity.class);
		List<RoleEntity> listRoleE = new ArrayList<>();
		// Chuyển đổi chức vụ
		if(userD.getRoleCodes().size() == 0)
			listRoleE.add(roleRepository.findOneByRoleCode("USER"));
		else
			for (String roleCode : userD.getRoleCodes()) {
				RoleEntity roleE = roleRepository.findOneByRoleCode(roleCode);
				if(roleE != null)
					listRoleE.add(roleE);
			}
		userE.setRoles(listRoleE);
		return userE;
	}
	public UserDTO toDTO(UserEntity userE) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		// Chuyển đổi các dữ liệu cần thiết
		UserDTO userD = modelMapper.map(userE, UserDTO.class);
		List<String> listRoleD = new ArrayList<>();
		// Chuyển đổi chức vụ
		for (RoleEntity roleE : userE.getRoles()) {
			if(roleE != null)
				listRoleD.add(roleE.getRoleCode());
		}
		userD.setRoleCodes(listRoleD);
		List<NotificationDTO> listNotiDTO = userD.getListNotification().stream()
											.sorted(Comparator.comparing
													(NotificationDTO::getNotificateDate)
											.reversed()).collect(Collectors.toList());
		userD.setListNotification(listNotiDTO);
		return userD;
	}
}
