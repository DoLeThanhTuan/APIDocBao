package com.dolethanhtuan.apiv2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dolethanhtuan.dto.CategoryDTO;
import com.dolethanhtuan.response.ResponseSuccess;
import com.dolethanhtuan.service.ICategoryService;

@RestController

public class CategoryAPIV2 {
	@Autowired
	private ICategoryService cateService;
	
	@GetMapping("api/category/all/v2")
	public ResponseSuccess<List<CategoryDTO>> getList(){
		return new ResponseSuccess<>(HttpStatus.OK.value(), "Get list success",cateService.getListCategory());
	}
}
