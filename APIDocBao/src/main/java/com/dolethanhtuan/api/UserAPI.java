package com.dolethanhtuan.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dolethanhtuan.dto.UserDTO;
import com.dolethanhtuan.service.IUserService;

@RestController
public class UserAPI {
	@Autowired
	private IUserService userService;
	
	
	@GetMapping(path = "api/v1/user/all")
	public List<UserDTO> getAllUser(){
		List<UserDTO> listUser = userService.findAllUser();
		return listUser;
	}
	@GetMapping(path = "api/v1/user/count")
	public int countUser(){
		return userService.countUser();
	}
	@GetMapping(path="api/v1/user/{id}")
	public UserDTO getOneUserById(@PathVariable("id") int id) {
		return userService.findOneById(id);
	}
	@GetMapping(path="api/v1/user/UN")
	public UserDTO getOneUserByUsername(@RequestParam("username") String username) {
		return userService.findOneByUsername(username);
	}
	@GetMapping(path="api/v1/user")
	public List<UserDTO> search(@RequestParam("txtSearch") String txtSearch) {
		if(txtSearch == null)
			return null;
		return userService.findAllUserLikeUsername(txtSearch);
	}
	@PostMapping(path = "api/v1/user/add")
	public UserDTO addUser(@RequestBody UserDTO user) {
		if(userService.existUsername(user.getUsername()))
			return null;
		return userService.insertUser(user);
	}
	@PostMapping(path = "api/v1/user/signup")
	public UserDTO signup(@RequestBody UserDTO user) {
		if(userService.existUsername(user.getUsername()))
			return null;
		return userService.insertUser(user);
	}
	@PostMapping(path = {"api/v1/user/signin","api/v1/user/login"})
	public UserDTO signin(@RequestBody UserDTO user) {
		UserDTO userD = userService.findOneByUsernameAndPasswordAndStatus(user.getUsername(), user.getPassword(), 1);
		return userD;
	}
	@PutMapping(path = {"api/v1/user"})
	public UserDTO updateUser(@RequestBody UserDTO userDTO) {
		if(userDTO.getId() == 0)
			return null;
		return userService.updateUser(userDTO);
	}
	@DeleteMapping(path="api/v1/user/{id}")
	public void deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
	}
	@PutMapping(path="api/v1/user/active")
	public void deleteUser(@RequestParam("user_id") Integer user_id,
			@RequestParam("status") Integer status) {
		if(user_id == null)
			return;
		userService.activeUser(user_id, status);
	}
	@PostMapping(path="api/v1/user/cateCare")
	public void cateCare(@RequestBody UserDTO userD) {
		userService.addCateCare(userD);
	}
	
	
}
