package com.dolethanhtuan.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
	private int id;
	private String username;
	private String password;
	private String fullname;
	private Integer status;
	// Danh sách chức vụ của user
	private List<String> roleCodes = new ArrayList<>();
	// Danh sach danh muc quan tam
	private List<CategoryDTO> listCateCare = new ArrayList<>();
	// Danh sach thong bao
	private List<NotificationDTO> listNotification = new ArrayList<>();
}
