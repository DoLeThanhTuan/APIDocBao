package com.dolethanhtuan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dolethanhtuan.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer>{
	RoleEntity findOneByRoleCode(String roleCode);
}
