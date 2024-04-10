package com.dolethanhtuan.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@NotBlank(message = "category code must not blank")
	private String cateCode;
	@NotNull(message = "category code must not null")
	private String cateName;
	private String cateDescription;
}
