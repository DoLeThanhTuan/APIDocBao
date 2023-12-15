package com.dolethanhtuan.Request;

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
public class SignupRequest {
	private int id;
	private String username;
	private String password;
	private String fullname;
	private int status;
	private List<String> roleCodes = new ArrayList<>();
}
