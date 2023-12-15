package com.dolethanhtuan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dolethanhtuan.entity.NewsEntity;

public interface NewsRepository extends JpaRepository<NewsEntity, Integer>{
	NewsEntity findOneById(int id);
	List<NewsEntity> findByTitleContaining(String title);
	List<NewsEntity> findAllByCategory_id(Integer cate_id);
}
