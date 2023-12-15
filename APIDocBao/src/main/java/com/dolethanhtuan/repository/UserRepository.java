package com.dolethanhtuan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dolethanhtuan.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	UserEntity findOneByUsernameAndPasswordAndStatus(String username,String password,int status);
	UserEntity findOneById(int id);
	List<UserEntity> findAllByUsernameContaining(String username);
	UserEntity findOneByUsername(String username);
	boolean existsByUsername(String username);
}
