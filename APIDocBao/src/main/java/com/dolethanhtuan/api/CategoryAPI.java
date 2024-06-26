package com.dolethanhtuan.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dolethanhtuan.dto.CategoryDTO;
import com.dolethanhtuan.response.ResponseSuccess;
import com.dolethanhtuan.service.ICategoryService;

@RestController

public class CategoryAPI {
	@Autowired
	private ICategoryService cateService;
	
	@GetMapping("api/v1/category/all")
	public ResponseSuccess<List<CategoryDTO>> getList(){
		return new ResponseSuccess<>(HttpStatus.OK.value(), "Get list success",cateService.getListCategory());
	}
}
