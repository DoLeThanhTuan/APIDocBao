package com.dolethanhtuan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dolethanhtuan.dto.CategoryDTO;

@Service
public interface ICategoryService{
	List<CategoryDTO> getListCategory();

}
