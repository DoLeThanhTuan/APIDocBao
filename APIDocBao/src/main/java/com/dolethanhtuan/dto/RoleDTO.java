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
public class RoleDTO {
	private int id;
	@NotBlank(message = "roleCode must not blank")
	private String roleCode;
	@NotNull(message = "roleName must not null")
	private String roleName;
}
