package com.dolethanhtuan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dolethanhtuan.dto.CategoryDTO;
import com.dolethanhtuan.entity.CategoryEntity;
import com.dolethanhtuan.repository.CategoryRepository;
import com.dolethanhtuan.service.ICategoryService;

@Component
public class CategoryService implements ICategoryService{
	@Autowired
	private CategoryRepository cateRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public List<CategoryDTO> getListCategory() {
		List<CategoryDTO> list = new ArrayList<>();
		for (CategoryEntity cateE : cateRepository.findAll()) {
			CategoryDTO cateD = modelMapper.map(cateE, CategoryDTO.class);
			list.add(cateD);
		}
		return list;
	}
}
