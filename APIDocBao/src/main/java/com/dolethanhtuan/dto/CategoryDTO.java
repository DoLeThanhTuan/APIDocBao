package com.dolethanhtuan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO {
	private int id;
	private String cateCode;
	private String cateName;
	private String cateDescription;
}
